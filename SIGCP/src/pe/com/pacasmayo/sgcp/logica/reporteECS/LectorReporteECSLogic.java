package pe.com.pacasmayo.sgcp.logica.reporteECS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.DatoReporteBean;
import pe.com.pacasmayo.sgcp.bean.HoraBean;
import pe.com.pacasmayo.sgcp.bean.RegistroReporteEcsBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DatoReporteBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.RegistroReporteECSLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hora;
import pe.com.pacasmayo.sgcp.persistencia.querier.HoraQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: LectorReporteECSLogic.java
 * Modificado: Jun 22, 2010 9:43:47 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class LectorReporteECSLogic implements ConstantesAplicacionAction, ConstantesMensajeAplicacion {

	private Log logger = LogFactory.getLog(this.getClass());
	public final static String FORMATO_ENCODING = "ISO-8859-1";
	public final static String CARACTER_SEPARACION_COLUMNAS = ",";
	public final static String FORMATO_FECHA_HORA = "dd-MMM-yy HH:mm";
	public final static int TOTAL_HORAS = 24;
	public final static String VARIABLE_OPERACION = "VO";
	public final static String VARIABLE_PRODUCCION = "VP";
	private Map<Short, Hora> horasCargadas = new HashMap<Short, Hora>();
	private String rutaGenerado;
	private String rutaProcesado;
	private static BeanFactory beanFactory;
	String mensajeError = "";

	public LectorReporteECSLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/**
	 * Método para obtener y mostrar la información del directorio
	 * 
	 * @param ruta
	 * @throws LogicaException
	 */
	public File obtenerDirectorio(String ruta) throws LogicaException {
		File fichero = new File(ruta);

		if (fichero.exists()) {
			logger.info("Nombre: " + fichero.getName());
			logger.info("Ruta Completa: " + fichero.getAbsolutePath());
			logger.info("Tamano: " + fichero.length() + " bytes");
			logger.info("Ultima modificación: " + new Date(fichero.lastModified()));
			logger.info("Puede escribir: " + fichero.canRead());
			logger.info("Puede leer: " + fichero.canWrite());

			if (fichero.isDirectory()) {
				logger.info("Directorio:");
				mostrarContenidoDirectorio(fichero);
				return fichero;
			}
			logger.info("No es un directorio");
			return null;
		}

		// No se encontro el archivo
		mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_ENCONTRADO_EN_RUTA);
		logger.error(mensajeError);
		throw new LogicaException(mensajeError);

	}

	/**
	 * Método para motrar el contenido del directorio
	 * 
	 * @param directorio
	 */
	public void mostrarContenidoDirectorio(File directorio) {
		String[] ficheros = directorio.list();

		for (int i = 0; i < ficheros.length; i++)
			logger.info("\t" + ficheros[i]);
	}

	/**
	 * Método para leer los archivos de un directorio
	 * 
	 * @throws LogicaException
	 */
	public void leerArchivosDirectorio() throws LogicaException {
		// 1. Establece ruta de acceso al archivo
		rutaGenerado = ManejadorPropiedades.obtenerPropiedadPorClave(URL_BASE_REPORTE_ECS_GENERADO).trim();

		rutaProcesado = ManejadorPropiedades.obtenerPropiedadPorClave(URL_BASE_REPORTE_ECS_PROCESADO).trim();
		logger.debug("Ruta Generado:" + rutaGenerado);
		logger.debug("Ruta Procesado:" + rutaProcesado);
		mensajeError = "";

		// 1. Obtener directorio y su infomraci�n
		File directorioOrigen = obtenerDirectorio(rutaGenerado);
		File directorioDestino = obtenerDirectorio(rutaProcesado);

		if (directorioOrigen != null) {
			String[] ficheros = directorioOrigen.list();
			File reporte = null;

			// Formato final del nombre del archivo:
			// YYYY-MM-DD_NOMBRE_TIPO.CSV
			// Donde:
			// NOMBRE = TIPO VARIABLE + SIGLA PROCESO + SIGLA PUESTO TRABAJO
			// TIPO DE VARIBLE = 2 CARACTERES: VP (VARIABLE PRODUCCION), VO
			// (Variable de Operaci�n)
			// SIGLA PROCESO = 3 CARACTERES
			// SIGLA PUESTO DE TRABAJO = 3 CARACTERES
//Lee los archivos del directorio
			for (int i = 0; i < ficheros.length; i++) {

				reporte = new File(directorioOrigen, ficheros[i]);

				if (!reporte.exists())
					reporte = new File(rutaGenerado, ficheros[i]);

				if (reporte.isFile()) {
					boolean fueLeidoSatisfactoriamente = false;

					try {
						logger.debug("Procesando archivo:" + reporte.getName() + " de la ruta:" + reporte.getAbsolutePath());

						// 1. Se lee el archivo para su carga
						fueLeidoSatisfactoriamente = leerArchivo(reporte, ficheros[i]);

						if (fueLeidoSatisfactoriamente) {

							File reporteProcesado = new File(directorioDestino, ficheros[i]);

							// 2. Se copia el archivo a ruta destino
							copiarArchivo(reporte, reporteProcesado);
							// 3. Se elimina el archivo de la carpeta original
							reporte.delete();
						}

					} catch (LogicaException e) {
						logger.info(e.getMensaje());
					}
				}
			}// end for
		}
	}

	/**
	 * M�todo para leer archivos provenientes de ECS
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws LogicaException
	 */
	public boolean leerArchivo(File reporte, String nombreArchivo) throws LogicaException {

		boolean fueLeidoSatisfactoriamente = false;

		try {

			// 1. Define el controlador para manipular el archivo
			FileInputStream stream = new FileInputStream(reporte);
			InputStreamReader fichero = new InputStreamReader(stream, FORMATO_ENCODING);
			BufferedReader lector = new BufferedReader(fichero);

			// 2. Define linea del archivo
			String lineaArchivo;
			ArchivoReporte archivo = new ArchivoReporte();
			String[] columnas = null;

			archivo.setNombreArchivo(nombreArchivo);
			archivo.setFechaLectura(new Date());
			// El tipo de variable: VP o VO, es un dato que sale el nombre del
			// archivo
			// bajo el formato yyyy-MM-dd_XX..., donde XX se corresponde con VP
			// o VO
			archivo.setTipo(nombreArchivo.substring(11, 13));

			LineaArchivo linea = null;
			List<ColumnaArchivo> columnasArchivo = null;
			List<LineaArchivo> lineas = new ArrayList<LineaArchivo>();
			int posicionLinea = 0;

			// 3. se obtiene linea del archivo
			while ((lineaArchivo = lector.readLine()) != null) {
				// 5. se obtienen las columnas de la linea del archivo
				columnas = lineaArchivo.split(CARACTER_SEPARACION_COLUMNAS, -1);
				ColumnaArchivo columna = null;
				linea = new LineaArchivo();
				columnasArchivo = new ArrayList<ColumnaArchivo>();

				for (int indice = 0; indice < columnas.length; indice++) {
					columna = new ColumnaArchivo();
					columna.setValor(columnas[indice]);
					columna.setPosicion(String.valueOf(indice));
					columnasArchivo.add(columna);
				}
				linea.setPosicion(posicionLinea);
				linea.setColumnas(columnasArchivo);
				lineas.add(posicionLinea, linea);
				posicionLinea++;
			}
			archivo.setLineas(lineas);

			stream.close();
			fichero.close();

			procesarDatosArchivo(archivo);

			fueLeidoSatisfactoriamente = true;

		} catch (FileNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_ENCONTRADO_EN_RUTA);

			throw new LogicaException(mensajeError, e);
		} catch (UnsupportedEncodingException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_PUEDE_SER_DECODIFICADO);

			throw new LogicaException(mensajeError, e);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_PUEDE_SER_LEIDO);

			throw new LogicaException(mensajeError, e);
		}

		return fueLeidoSatisfactoriamente;
	}

	/**
	 * M�todo para copiar un archivo de origen a un destino
	 * 
	 * @param fuente
	 * @param destino
	 * @throws LogicaException
	 */
	public static void copiarArchivo(File origen, File destino) throws LogicaException {

		String mensajeError = "";

		try {
			InputStream archivoEntrada = new FileInputStream(origen);
			OutputStream archivoSalida = new FileOutputStream(destino);

			/* Se crea un buffer para leer del archivo en bloques de 4096 bytes */
			BufferedInputStream entrada = new BufferedInputStream(archivoEntrada, 4096);
			BufferedOutputStream salida = new BufferedOutputStream(archivoSalida);

			int tamano;

			while ((tamano = entrada.read()) > 0) {
				salida.write(tamano);
			}

			entrada.close();
			archivoEntrada.close();
			salida.close();
			archivoSalida.close();

		} catch (FileNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_ENCONTRADO_EN_RUTA);
			throw new LogicaException(mensajeError, e);
		} catch (IOException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_PUEDE_SER_LEIDO);
			throw new LogicaException(mensajeError, e);
		}

	}

	/**
	 * Método para organizar los datos a cargar en la base de datos
	 * 
	 * @param archivo
	 * @throws LogicaException
	 */
	public boolean procesarDatosArchivo(ArchivoReporte archivo) throws LogicaException {

		boolean fueProcesadoSatisfactoriamente = false;
		mensajeError = "";

		ArchivoReporte archivoAux = new ArchivoReporte();
		archivoAux.setNombreArchivo(archivo.getNombreArchivo());
		archivoAux.setTipo(archivo.getTipo());
		archivoAux.setFechaLectura(archivo.getFechaLectura());

		LineaArchivo linea = null;
		List<LineaArchivo> lineas = new ArrayList<LineaArchivo>();

		// Verificar la cantidad de lineas del archivo a procesar
		if (archivo.getLineas().size() >= TOTAL_HORAS + 1) {
			// 24 horas
			for (int indice = 1; indice <= TOTAL_HORAS; indice++) {

				linea = archivo.getLineas().get(indice);

				if ((indice - 1) > 0) {

					if (indice != TOTAL_HORAS) {
						if (archivo.getTipo().equals(VARIABLE_PRODUCCION)) {
							lineas.add(indice - 1, procesarLinea(linea, archivo.getLineas().get(indice - 1)));
						} else {
							// Primera linea
							lineas.add(indice - 1, procesarLinea(linea));
						}
					} else {
						if (archivo.getTipo().equals(VARIABLE_PRODUCCION)) {
							// como la linea que se va a corregir dice max, se
							// le coloca la fecha de la linea original
							LineaArchivo ultimaLineaCorreccion = archivo.getLineas().get(indice + 2);
							ultimaLineaCorreccion.getColumnas().get(0).setValor(linea.getColumnas().get(0).getValor());
							lineas.add(indice - 1, procesarLinea(ultimaLineaCorreccion, archivo.getLineas().get(indice - 1)));
						} else {
							// Primera linea
							lineas.add(indice - 1, procesarLinea(linea));
						}

					}
				} else {
					// La primera linea se procesao igual sea el archivo de tipo
					// VP
					// (Variable de Producci�n)
					// o VO (Variable de Operaci�n)
					LineaArchivo lineaAux = linea;
					List<ColumnaArchivo> columnas = new ArrayList<ColumnaArchivo>();
					linea = new LineaArchivo();
					ColumnaArchivo columna = new ColumnaArchivo();

					columna = lineaAux.getColumnas().get(0);
					columnas.add(columna);

					for (int ind = 1; ind < lineaAux.getColumnas().size(); ind++) {
						columna = procesarValorColumna(lineaAux.getColumnas().get(ind));
						columnas.add(columna);
					}
					linea.setColumnas(columnas);
					linea.setPosicion(indice - 1);
					lineas.add(indice - 1, linea);
				}

			}

			archivoAux.setLineas(lineas);

			if (cargarDatos(archivoAux))
				fueProcesadoSatisfactoriamente = true;
		} else {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_CONTIENE_DATOS_REQUERIDOS);
			logger.error(mensajeError + ":" + archivo.getNombreArchivo());
			throw new LogicaException(mensajeError + ":" + archivo.getNombreArchivo());
		}

		return fueProcesadoSatisfactoriamente;
	}

	/**
	 * M�todo para cragar los datos del reporte en la BD
	 * 
	 * @param archivo
	 * @throws LogicaException
	 */
	public boolean cargarDatos(ArchivoReporte archivo) throws LogicaException {

		boolean fueCargadoSatisfactoriamente = false;

		RegistroReporteECSLogicFacade registroReporteFacade = new RegistroReporteECSLogic();
		RegistroReporteEcsBean registroReporteECS = new RegistroReporteEcsBeanImpl();
		List<DatoReporteBean> datosReporte = new ArrayList<DatoReporteBean>();
		DatoReporteBean datoReporte = null;

		LineaArchivo linea = null;
		ColumnaArchivo columna = null;

		// Se cargan las horas de la BD en memoria
		cargarHoras();

		for (int indiceLinea = 0; indiceLinea < archivo.getLineas().size(); indiceLinea++) {

			linea = archivo.getLineas().get(indiceLinea);
			datoReporte = new DatoReporteBeanImpl();

			// Se determina la hora en el archivo y se establece en datoReporte
			String horaAux = linea.getColumnas().get(0).getValor();
			String[] horaProcesada = horaAux.split("\"");

			Locale localidad = Locale.US;
			Date fechaHoraArchivo = FechaUtil.convertirAFecha(horaProcesada[1], FORMATO_FECHA_HORA, localidad);
			GregorianCalendar calendario = new GregorianCalendar();
			calendario.setTime(fechaHoraArchivo);

			int horaCalendario = calendario.get(calendario.HOUR_OF_DAY);

			if (horaCalendario == 0)
				horaCalendario = 24;

			if (horasCargadas == null || horasCargadas.size() <= 0) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_ARCHIVOECS_NO_ENCUENTRA_HORAS_EN_BD);

				logger.error(mensajeError);
				throw new LogicaException(mensajeError);
			}

			HoraBean hora = beanFactory.transformarHora(horasCargadas.get((short) horaCalendario));
			datoReporte.setHora(hora);

			for (int indiceColumna = 1; indiceColumna < linea.getColumnas().size(); indiceColumna++) {
				// Se establecen los datos del reprote de acuerdo con la columna
				// del archivo
				columna = linea.getColumnas().get(indiceColumna);
				establecerVariableDatoRegistro(indiceColumna, columna.getValor(), datoReporte);
			}
			datosReporte.add(datoReporte);
		}
		// Se establecen los datos del reporte
		registroReporteECS.setDatosReporte(datosReporte);
		// Se establece el nombre del reporte
		registroReporteECS.setNombreRegistroReportEecs(archivo.getNombreArchivo());
		// Se establece fecha de registro del reporte
		registroReporteECS.setFechaRegistroReporteEcs(new Date());

		registroReporteFacade.insertarRegistroReporteECS(registroReporteECS);

		fueCargadoSatisfactoriamente = true;

		return fueCargadoSatisfactoriamente;
	}

	/**
	 * M�todo para establecer los valores asociados a los datos del archivo de
	 * reporte ecs
	 * 
	 * @param indice
	 * @param valorVariable
	 * @param dato
	 */
	private void establecerVariableDatoRegistro(int indice, String valorVariable, DatoReporteBean dato) {
		switch (indice) {
		case 1:
			dato.setVariable1Datoreporte(Double.valueOf(valorVariable));
			break;
		case 2:
			dato.setVariable2Datoreporte(Double.valueOf(valorVariable));
			break;
		case 3:
			dato.setVariable3Datoreporte(Double.valueOf(valorVariable));
			break;
		case 4:
			dato.setVariable4Datoreporte(Double.valueOf(valorVariable));
			break;
		case 5:
			dato.setVariable5Datoreporte(Double.valueOf(valorVariable));
			break;
		case 6:
			dato.setVariable6Datoreporte(Double.valueOf(valorVariable));
			break;
		case 7:
			dato.setVariable7Datoreporte(Double.valueOf(valorVariable));
			break;
		case 8:
			dato.setVariable8Datoreporte(Double.valueOf(valorVariable));
			break;
		case 9:
			dato.setVariable9Datoreporte(Double.valueOf(valorVariable));
			break;
		case 10:
			dato.setVariable10Datoreporte(Double.valueOf(valorVariable));
			break;
		case 11:
			dato.setVariable11Datoreporte(Double.valueOf(valorVariable));
			break;
		case 12:
			dato.setVariable12Datoreporte(Double.valueOf(valorVariable));
			break;
		case 13:
			dato.setVariable13Datoreporte(Double.valueOf(valorVariable));
			break;
		case 14:
			dato.setVariable14Datoreporte(Double.valueOf(valorVariable));
			break;
		case 15:
			dato.setVariable15Datoreporte(Double.valueOf(valorVariable));
			break;
		}
	}

	/**
	 * M�todo para procesar la linea de cada archivo y generar el consumo real
	 * entre una linea y otra
	 * 
	 * @param lineaActual
	 * @param lineaAnterior
	 * @return
	 */
	private LineaArchivo procesarLinea(LineaArchivo lineaActual, LineaArchivo lineaAnterior) {

		LineaArchivo linea = new LineaArchivo();
		List<ColumnaArchivo> columnasArchivo = new ArrayList<ColumnaArchivo>();
		ColumnaArchivo columnaLineaActual = null;
		ColumnaArchivo columnaLineaAnterior = null;
		ColumnaArchivo columnaLineaTotalInsumo = null;

		// Se especifica la nueva columna inicial
		columnaLineaActual = lineaActual.getColumnas().get(0);
		columnasArchivo.add(columnaLineaActual);

		// Se especifican las columnas 1 a 15 del archivo
		for (int indice = 1; indice < lineaActual.getColumnas().size(); indice++) {
			columnaLineaActual = procesarValorColumna(lineaActual.getColumnas().get(indice));

			columnaLineaAnterior = procesarValorColumna(lineaAnterior.getColumnas().get(indice));

			// Se restan los valores de la columna en la linea actual con los
			// valores de las columnas en la linea anterior para obtener el
			// valor de consumo entre una hora y otra

			Double valorActual = Double.valueOf(columnaLineaActual.getValor());
			Double valorAnterior = Double.valueOf(columnaLineaAnterior.getValor());

			if (valorActual < 0)
				valorActual = new Double(0);
			if (valorAnterior < 0)
				valorAnterior = new Double(0);

			Double valorPreliminar = Math.abs(valorActual - valorAnterior);
			Double valor = Math.round(valorPreliminar * Math.pow(10, (double) 2)) / Math.pow(10, (double) 2);

			columnaLineaTotalInsumo = new ColumnaArchivo();
			columnaLineaTotalInsumo.setValor(valor.toString());
			columnaLineaTotalInsumo.setPosicion(String.valueOf(indice));
			// Se establece columna de la nueva linea del archivo
			columnasArchivo.add(columnaLineaTotalInsumo);
		}
		linea.setColumnas(columnasArchivo);

		return linea;
	}

	/**
	 * M�todo para procesar la linea de cada archivo
	 * 
	 * @param lineaActual
	 * @return
	 */
	private LineaArchivo procesarLinea(LineaArchivo lineaActual) {

		LineaArchivo linea = new LineaArchivo();
		List<ColumnaArchivo> columnasArchivo = new ArrayList<ColumnaArchivo>();
		ColumnaArchivo columnaLineaActual = null;
		ColumnaArchivo columnaLineaTotalInsumo = null;

		// Se especifica la nueva columna inicial
		columnaLineaActual = lineaActual.getColumnas().get(0);
		columnasArchivo.add(columnaLineaActual);

		// Se especifican las columnas 1 a 15 del archivo
		for (int indice = 1; indice < lineaActual.getColumnas().size(); indice++) {

			columnaLineaActual = procesarValorColumna(lineaActual.getColumnas().get(indice));

			Double valorActual = Double.valueOf(columnaLineaActual.getValor());
			Double valor = new Double(0);

			if (valorActual < 0)
				valorActual = new Double(0);
			else
				valor = Math.round(valorActual * Math.pow(10, (double) 2)) / Math.pow(10, (double) 2);

			columnaLineaTotalInsumo = new ColumnaArchivo();
			columnaLineaTotalInsumo.setValor(valor.toString());
			columnaLineaTotalInsumo.setPosicion(String.valueOf(indice));
			// Se establece columna de la nueva linea del archivo
			columnasArchivo.add(columnaLineaTotalInsumo);

		}
		linea.setColumnas(columnasArchivo);

		return linea;
	}

	/**
	 * M�todo para procesar la columna y obtener el valor real de consumo
	 * 
	 * @param columnaOriginal
	 * @return
	 */
	private ColumnaArchivo procesarValorColumna(ColumnaArchivo columnaOriginal) {

		String valorAux = despejarValor(columnaOriginal.getValor());
		String valor;

		if (valorAux.startsWith("\"") || valorAux.endsWith("\"")) {
			valor = despejarValor(valorAux);
		} else
			valor = valorAux;

		if (valor.length() > 0 && StringUtils.isNumeric(valor.toUpperCase().replaceAll("\\.", ""))) {
			columnaOriginal.setValor(valor);
		} else
			columnaOriginal.setValor("0");

		return columnaOriginal;
	}

	/**
	 * M�todo para eliminar las "" del valor de la columna
	 * 
	 * @param valorColumna
	 * @return
	 */
	private String despejarValor(String valorColumna) {
		String valorOriginal = valorColumna.trim();

		String valorAux1 = (valorOriginal.startsWith("\"")) ? valorOriginal.substring(1) : valorOriginal;
		String valorAux2 = (valorAux1.length() > 0 && valorAux1.endsWith("\"")) ? valorAux1.substring(0, valorAux1.length() - 1)
				: valorAux1;
		return valorAux2;
	}

	/**
	 * M�todo para cargar en memoria las horas
	 */
	private void cargarHoras() {

		List<Hora> horas = HoraQuerier.getAll();

		for (int i = 0; i < horas.size(); i++) {
			Hora hora = horas.get(i);
			horasCargadas.put(hora.getHoraHora(), hora);
		}
	}

	/**
	 * Clase encargada de manejar el archivo del reporte
	 * 
	 * @author lplaz
	 */
	private class ArchivoReporte {
		private List<LineaArchivo> lineas;
		private String nombreArchivo;
		private Date fechaLectura;
		private String tipo;

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getNombreArchivo() {
			return nombreArchivo;
		}

		public void setNombreArchivo(String nombreArchivo) {
			this.nombreArchivo = nombreArchivo;
		}

		public Date getFechaLectura() {
			return fechaLectura;
		}

		public void setFechaLectura(Date fechaLectura) {
			this.fechaLectura = fechaLectura;
		}

		public List<LineaArchivo> getLineas() {
			return lineas;
		}

		public void setLineas(List<LineaArchivo> lineas) {
			this.lineas = lineas;
		}

	}

	/**
	 * Clase encargada de manejar las lineas de cada achivo
	 * 
	 * @author lplaz
	 */
	private class LineaArchivo {
		private List<ColumnaArchivo> columnas;
		private int posicion;

		public int getPosicion() {
			return posicion;
		}

		public void setPosicion(int posicion) {
			this.posicion = posicion;
		}

		public List<ColumnaArchivo> getColumnas() {
			return columnas;
		}

		public void setColumnas(List<ColumnaArchivo> columnas) {
			this.columnas = columnas;
		}
	}

	/**
	 * Clase encargada de manejar la columna de cada archivo
	 * 
	 * @author lplaz
	 */
	private class ColumnaArchivo {
		private String posicion;
		private String valor;

		public String getPosicion() {
			return posicion;
		}

		public void setPosicion(String posicion) {
			this.posicion = posicion;
		}

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}

	}
}
