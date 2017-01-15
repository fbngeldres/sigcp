package pe.com.pacasmayo.sgcp.presentacion.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProduccionMesLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProduccionMesQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.CubicacionProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EquivalenciasccvarcalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.ConsumosFactoresPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.CubicacionReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroPuestoTrabajoProduccionDTO;

import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DownloadUploadServlet.java
 * Modificado: Jan 26, 2012 6:34:22 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class DownloadServlet extends HttpServlet implements ConstantesLogicaNegocio, ConstantesMensajeAplicacion {

	private static final long serialVersionUID = -8778257979221401914L;
	private Logger logger = Logger.getLogger(DownloadServlet.class);
	private static String[] codigosClinkerCubicacion = ManejadorPropiedades.obtenerPropiedadPorClave(
			CODIGO_PRODUCTOS_CLINKER_CUBICACION).split("-");
	public static final String ETIQUETA_ESTADO_INICIAL = "estado.inicial";
	private static AjusteProduccionMesLogicFacade ajusteLogic = new AjusteProduccionMesLogic();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String contextPath = getServletContext().getRealPath(System.getProperty("file.separator"));

		String anioStr = req.getParameter("anio");
		String mesStr = req.getParameter("mes");
		String lineaStr = req.getParameter("linea");

		String fileName = ManejadorPropiedades.obtenerPropiedadPorClave(CUBICAJE) + mesStr + anioStr + ".pdf";

		try {
			byte[] byteArray = generarReporteCubicacion(contextPath, anioStr, mesStr, lineaStr);
			res.setContentType("application/octet-stream");
			res.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");

			ServletOutputStream stream = res.getOutputStream();
			stream.write(byteArray);
			stream.close();
		} catch (JRException e) {
			logger.error(e);
			new ServletException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_GENERANDO_REPORTE) + " " + fileName);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e);
			new ServletException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_GENERANDO_REPORTE) + " " + fileName);
		} catch (LogicaException e) {
			logger.error(e);
			new ServletException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_GENERANDO_REPORTE) + " " + fileName);
		}

	}

	public static byte[] generarReporteCubicacion(String jrxmlPath, String anioStr, String mesStr, String lineaStr)
			throws IOException, JRException, ElementoNoEncontradoException, LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);

			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);

			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);

			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {

			Integer anio = new Integer(anioStr);
			Short mes = ((Integer) FechaUtil.MESES_ESPANNOL.valueOf(mesStr).ordinal()).shortValue();
			mes++;
			Long linea = new Long(lineaStr);
			Boolean estadoInicial = false;
			String estadoCubicacionAprobada = ManejadorPropiedades
					.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO);

			Map<String, Object> reportParams = new HashMap<String, Object>();

			Ajusteproduccion ajusteProduccion = AjusteProduccionQuerier.getByLineaNegocioMesYAno(mes, anio, linea);
			if (ajusteProduccion.getEstadoajusteproduccion().getNombreEstadoajusteproduccion()
					.equals(ManejadorPropiedades.obtenerPropiedadPorClave(ETIQUETA_ESTADO_INICIAL))) {
				estadoCubicacionAprobada = null;
				estadoInicial = true;
			}

			List<Cubicacion> cubis = CubicacionProductoQuerier.obtenerCubicacionProducto(null, mes.shortValue(), anio,
					estadoCubicacionAprobada);

			String codigoClinkerI = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODCUTO_CLK_I);

			// Este codigo es copia de ParteTecnicoServiceImpl, debio ser una
			// llamada
			// begin copia
			Calendar fechaMesCalendar = FechaUtil.getUltimoDiaMes((short) (mes - 1), anio);

			Date fechaMesFinal = fechaMesCalendar.getTime();
			fechaMesCalendar.set(Calendar.DAY_OF_MONTH, 1);
			Date fechaMesInicial = fechaMesCalendar.getTime();
			// end copia

			List<CubicacionReporteDTO> cubDTOs = new LinkedList<CubicacionReporteDTO>();
			Double ajsuteLogistico;
			for (Cubicacion cubicacion : cubis) {
				Cubicacionproducto cubicacionproducto = cubicacion.getCubicacionproducto();
				Producto productoComponente = cubicacionproducto.getProduccion().getProducto();
				ajsuteLogistico = null;
				boolean esClinkerI = codigoClinkerI.equals(productoComponente.getPkCodigoProducto().toString());
				Double[] stocksMensuales = ajusteLogic.obtenerStocksMensualesComponenteDAO(productoComponente, linea,
						mes.shortValue(), anio, esClinkerI);

				double libros = stocksMensuales[0] + stocksMensuales[1] - stocksMensuales[2];
				cubicacionproducto.setStockLibros(libros);

				// Obtener datos para tabla datos proyectados

				Long codigoProducto = productoComponente.getPkCodigoProducto();

				double stockFisico = ajusteLogic.obtenerStockFisicoDAO(productoComponente, linea, mes, anio, fechaMesInicial,
						fechaMesFinal, estadoInicial);

				double[] valores = ajusteLogic.obtenerConceptosLibroDAO(codigoProducto, linea, mes, anio, fechaMesInicial,
						fechaMesFinal, 1L);

				Ajusteproducto ajusteProducto = AjusteProductoQuerier.obtenerPorProductoLineaNegPeriodoContYGrupo(codigoProducto,
						linea, mes, anio);

				// TODO: quitar estas dos consultas y obtener numero de lista
				// todosAjustes
				double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponente(codigoProducto, linea,
						mes, anio);
				double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProducto(codigoProducto,
						linea, mes, anio);

				List<Map> todosAjustes = new LinkedList<Map>();
				todosAjustes.addAll(MovimientoAjusteQuerier.obtenerObjetosAjustesPorConsumoDeComponente(codigoProducto, linea,
						mes, anio));
				todosAjustes.addAll(MovimientoAjusteQuerier.obtenerObjetosAjustesPorConsumoDeProducto(codigoProducto, linea, mes,
						anio));

				RegistroTablaBalanceDTO registroTablaBalanceDTO = new RegistroTablaBalanceDTO();
				registroTablaBalanceDTO.setSaldoInicial(valores[0]);
				registroTablaBalanceDTO.setIngreso(valores[1]);
				registroTablaBalanceDTO.setConsumo(valores[2]);
				registroTablaBalanceDTO.setSaldoFinal(valores[3]);

				ajsuteLogistico = valores[4];
				if (ajsuteLogistico == null) {
					ajsuteLogistico = 0d;
				}
				Map<String, Double> datosProyectados = new HashMap<String, Double>();
				datosProyectados.put("saldoInicial", valores[0]);
				datosProyectados.put("ajusteLogistico", ajsuteLogistico);
				List<RegistroPuestoTrabajoProduccionDTO> produccionPuestoTrabajo = null;
				if (ajusteProducto != null) {
					double ajuste = 0;
					produccionPuestoTrabajo = obtenerProduccionPuestoTrabajoMesBD(ajusteProducto.getPkCodigoAjusteproducto());

					if (produccionPuestoTrabajo != null && !produccionPuestoTrabajo.isEmpty()) {
						for (RegistroPuestoTrabajoProduccionDTO r : produccionPuestoTrabajo) {
							ajuste += r.getAjusteTM();
						}
					}

					if (estadoInicial) {
						ajuste = 0d;
					}

					datosProyectados.put("ingreso", valores[1] + ajuste);
				} else {
					datosProyectados.put("ingreso", valores[1]);
				}

				String grupoProducto = cubicacion.getCubicacionproducto().getProduccion().getProducto().getGrupoProducto();
				if (StringUtils.isNotBlank(grupoProducto)
						&& validarClinkerCubicacion(cubicacion.getCubicacionproducto().getProduccion().getProducto())) {

					Double Totalkardex = TablaKardexQuerier.obtenerTMTotalKardex(cubicacion.getCubicacionproducto()
							.getFechaCubicacionproducto(), cubicacion.getCubicacionproducto().getProduccion().getProducto()
							.getPkCodigoProducto(), cubicacion.getCubicacionproducto().getLineanegocio()
							.getPkCodigoLineanegocio());
			
					datosProyectados.put("totalTMKardex", Totalkardex.doubleValue() + datosProyectados.get("saldoInicial"));
				} else {
					Tablakardex tablaKardex = TablaKardexQuerier.obtenerPorFechaProductoYLineaNegocio(cubicacion
							.getCubicacionproducto().getFechaCubicacionproducto(), cubicacion.getCubicacionproducto()
							.getProduccion().getProducto().getPkCodigoProducto(), cubicacion.getCubicacionproducto()
							.getLineanegocio().getPkCodigoLineanegocio());
					datosProyectados.put("totalTMKardex", tablaKardex != null ? tablaKardex.getStockFinalTablakardex() : 0D);
				}

				Double fHum = 0d;

				fHum = obtenerHumedad(cubicacion.getCubicacionproducto().getProduccion().getProducto(), fechaMesInicial);

				if (estadoInicial) {
					consumoPorAjuste = 0d;
					consumoPorAjusteProducto = 0d;
				}

				datosProyectados.put("consumoSolo", valores[2]);
				datosProyectados.put("consumo", valores[2] + consumoPorAjuste + consumoPorAjusteProducto);
				datosProyectados.put("fisico", stockFisico);
				datosProyectados.put(
						"libros",
						((datosProyectados.get("saldoInicial") + datosProyectados.get("ingreso")) - datosProyectados
								.get("consumo")) + datosProyectados.get("ajusteLogistico"));

			
				datosProyectados.put("fMenosL", datosProyectados.get("fisico") - datosProyectados.get("libros"));

				if (datosProyectados.get("fisico") == 0d) {
					datosProyectados.put("porcentajeDesviacion", 0d);
				} else {
					datosProyectados.put("porcentajeDesviacion",
							datosProyectados.get("fMenosL") * 100 / datosProyectados.get("fisico"));
				}

				datosProyectados.put("facHum", fHum); // ingresando factor de
				// humedad en el pdf
				Double densidad = 0d;
				if (cubicacion.getDensidadMedioalmacenamiento() != null) {
					densidad = cubicacion.getDensidadMedioalmacenamiento();
				} else {
					densidad = cubicacion.getMedioalmacenamiento().getDensidadMedioalmacenamiento();
				}
				datosProyectados.put("densidadCubicacion", densidad);

				// Agregar beans a dto que los agrupa
				CubicacionReporteDTO dto = new CubicacionReporteDTO();
				dto.setDatosProyectados(datosProyectados);
				dto.setCubicacion(cubicacion);
				dto.setAjustes(todosAjustes);
				dto.setProduccionPuestoTrabajo(produccionPuestoTrabajo);
				cubDTOs.add(dto);
			}

			// Ordenar los dtos segun reglas de ranking
			//Collections.sort(cubDTOs, new CubicacionReporteComparator());
			return null;//RptAbstract.exportToPDF(cubDTOs, reportParams, Reportes.CUBICAJE, jrxmlPath);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public static Double obtenerHumedad(Producto cubicacionproducto, Date fecha) {

		Double factorHumedad = 0d;
		String estadoCubicacionAprobada = ManejadorPropiedades.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_APROBADO);
		Date fechaAnterior = FechaUtil.obtenerFechaDiaAnterior(fecha);
		Double sumatoriaCubicacions = 0d;
		Double sumatoriaCubicacionsporHumedad = 0d;
		Double sumatoriakardexs = 0d;
		Double sumatoriaKardexsporHumedad = 0d;
		Double factorHumedadcubicacion = 0d;
		Double factorHumedadActual = 0d;

		List<Cubicacion> cubicacions = CubicacionProductoQuerier.obtenerCubicacionProducto(
				cubicacionproducto.getPkCodigoProducto(), (short) FechaUtil.obtenerMesFecha(fechaAnterior),
				FechaUtil.obtenerAnnioFecha(fechaAnterior), estadoCubicacionAprobada);

		for (Cubicacion cubicacion : cubicacions) {
			if (cubicacion != null) {
				sumatoriaCubicacions += NumberUtil.multiplicar(cubicacion.getVolumenM3Cubicacion(),
						cubicacion.getDensidadMedioalmacenamiento());
				factorHumedadcubicacion = cubicacion.getCubicacionproducto().getHumedadPonderadaCubicacionproducto();
			}

		}

		if (factorHumedadcubicacion == null) {
			try {
				factorHumedadcubicacion = ajusteLogic.obtenerFactorHumPonderadoConsumoDAO(cubicacionproducto, fechaAnterior);
			} catch (LogicaException e) {
				factorHumedadcubicacion = 0d;
			}
		}

		sumatoriaCubicacionsporHumedad = NumberUtil.Redondear2Decimales(sumatoriaCubicacions) * factorHumedadcubicacion;

		Date fechaUltimoDia = FechaUtil.getUltimoDiaMes((short) (FechaUtil.obtenerMesFecha(fecha) - 1),
				FechaUtil.obtenerAnnioFecha(fecha)).getTime();

		factorHumedadActual = obtenerHumedadPonderado(cubicacionproducto, fechaUltimoDia);

		try {
			sumatoriakardexs = ajusteLogic.obtenerCantidadMensualKardexDAO(cubicacionproducto, fecha);
		} catch (LogicaException e) {
			sumatoriakardexs = 0d;
		}

		sumatoriaKardexsporHumedad = factorHumedadActual * sumatoriakardexs;

		factorHumedad = NumberUtil.dividir((sumatoriaKardexsporHumedad + sumatoriaCubicacionsporHumedad),
				(sumatoriakardexs + sumatoriaCubicacions));

		return factorHumedad;
	}

	private static Double obtenerHumedadPonderado(Producto producto, Date fecha) {
		Double fHum = 0d;

		// Validacion HUmedad Ingreso
		Long codigoVariableHumedad = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(CODIGO_VARIABLE_CALIDAD_HUMEDAD));

		Productovariablecalidad productoVariableCalidad = ProductoVariableCalidadQuerier.obtenerProductoVariableCalidad(
				producto.getPkCodigoProducto(), codigoVariableHumedad);

		if (productoVariableCalidad != null) {

			try {
				fHum = ajusteLogic.obtenerFactorHumPonderadoDAO(producto, fecha);
			} catch (LogicaException e) {
				fHum = 0d;
			}

		}

		// Validacion HUmedad Consumo
		Boolean tieneHumedad = EquivalenciasccvarcalidadQuerier.verificarSiPoseeHumedad(producto.getPkCodigoProducto());
		if (tieneHumedad) {
			if (fHum == 0) {
				try {
					fHum = ajusteLogic.obtenerFactorHumPonderadoConsumoDAO(producto, fecha);
				} catch (LogicaException e) {
					fHum = 0d;
				}

			}

			// Validacion HUmedad Consumo

			if (fHum == 0.00) {
				try {
					fHum = ajusteLogic.obtenerFactorHumedadVariableCalidadDAO(producto);
				} catch (LogicaException e) {
					fHum = 0d;
				}
			}
		}

		return fHum;
	}

	private static boolean validarClinkerCubicacion(Producto producto) {
		for (int i = 0; i < codigosClinkerCubicacion.length; i++) {
			if (producto.getPkCodigoProducto().toString().equals(codigosClinkerCubicacion[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO Copia vulgar del metodo
	 * {@link ParteTecnicoServiceImpl#obtenerProduccionPuestoTrabajoMesBD(Long)}
	 * quitandole el manejo de sesion porque sino se cierra y el reporte no se
	 * genera!!!
	 */
	public static List<RegistroPuestoTrabajoProduccionDTO> obtenerProduccionPuestoTrabajoMesBD(Long codigoAjusteProducto) {

		List<RegistroPuestoTrabajoProduccionDTO> registrosDto = null;

		try {
			List<Puestotrabajoproduccion> puestoTrabProdList = PuestoTrabajoProduccionQuerier
					.obtenerPorCodigoAjusteProducto(codigoAjusteProducto);

			if (puestoTrabProdList != null && puestoTrabProdList.size() > 0) {
				registrosDto = new ArrayList<RegistroPuestoTrabajoProduccionDTO>();
				Ajusteproducto ajusteproducto = puestoTrabProdList.get(0).getAjusteproducto();

				Long codigoProducto = ajusteproducto.getProducto().getPkCodigoProducto();

				Ajusteproduccion ajusteproduccion = ajusteproducto.getAjusteproduccion();
				Periodocontable periodocontable = ajusteproduccion.getPeriodocontable();

				Set<Long> puestos = new HashSet<Long>();
				for (Puestotrabajoproduccion puestotrabajoproduccion : puestoTrabProdList) {
					puestos.add(puestotrabajoproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo());
				}

				Long codigoLineanegocio = ajusteproduccion.getLineanegocio().getPkCodigoLineanegocio();

				String codigosClinker = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTOS_CLINKER);

				boolean esClinker = codigosClinker.indexOf(codigoProducto.toString()) >= 0;

				Map<Long, ConsumosFactoresPuestoTrabajoDTO> factoresCrudoYCarbon = new HashMap<Long, ConsumosFactoresPuestoTrabajoDTO>();

				if (esClinker) {
					factoresCrudoYCarbon = ajusteLogic.obtenerFactoresCrudoCarbonPorPuestoDAO(codigoProducto, codigoLineanegocio,
							periodocontable.getAnoPeriodocontable(), periodocontable.getMesPeriodocontable(), puestos);
				}

				for (Puestotrabajoproduccion puestotrabajoproduccion : puestoTrabProdList) {
					Puestotrabajo puestotrabajo = puestotrabajoproduccion.getPuestotrabajo();

					RegistroPuestoTrabajoProduccionDTO registro = new RegistroPuestoTrabajoProduccionDTO();
					registro.setCodigoPuestoTrabajo(puestotrabajo.getPkCodigoPuestotrabajo());
					registro.setNombrePuestoTrabajo(puestotrabajo.getNombrePuestotrabajo());

					registro.setProduccionHR(puestotrabajoproduccion.getHrPuestotrabajoproduccion());
					Double produccionTM = puestotrabajoproduccion.getTmPuestotrabajoproduccion();
					registro.setProduccionTM(produccionTM);
					registro.setProduccionTMPH(puestotrabajoproduccion.getTmRealPuestotrabajoproduccion());
					Double produccionKcal = puestotrabajoproduccion.getKcalPuestotrabajoproduccion();
					registro.setProduccionKCAL(produccionKcal);

					registro.setProduccionRealHR(puestotrabajoproduccion.getHrRealPuestotrabajoproduccion());
					registro.setProduccionRealKCAL(puestotrabajoproduccion.getKcalRealPuestotrabajoproducci());
					registro.setProduccionRealTM(puestotrabajoproduccion.getTmRealPuestotrabajoproduccion());
					registro.setProduccionRealTMPH(puestotrabajoproduccion.getTmphRealPuestotrabajoproducci());

					registro.setAjusteTM(puestotrabajoproduccion.getTmAjustePuestotrabajoproducci());
					registro.setAjusteHR(puestotrabajoproduccion.getHrAjustePuestotrabajoproducci());

					Double[] produccionHorasArray = AjusteProduccionMesQuerier.obtenerProduccionyHoras(codigoProducto,
							codigoLineanegocio, periodocontable.getMesPeriodocontable(), periodocontable.getAnoPeriodocontable(),
							puestotrabajo.getPkCodigoPuestotrabajo());

					Double produccionTonaledasAnterior = produccionHorasArray[0];
					Double produccionHorasAnterior = produccionHorasArray[1];

					Producto producto = puestotrabajoproduccion.getAjusteproducto().getProducto();

					Object[] tasas = AjusteProduccionMesQuerier.obtenerTasasMaxMinYNominalporPuestoyProduccion(
							producto.getPkCodigoProducto(), puestotrabajo.getPkCodigoPuestotrabajo(),
							periodocontable.getMesPeriodocontable(), periodocontable.getAnoPeriodocontable());

					registro.setMinimoRendimiento((Double) tasas[0]);
					registro.setMaximoRendimiento((Double) tasas[1]);
					registro.setTasaProduccionNominal((Double) tasas[2]);

					registro.setMesAnteriorHR(produccionHorasAnterior);
					registro.setMesAnteriorTM(produccionTonaledasAnterior);

					ConsumosFactoresPuestoTrabajoDTO consumosFactores = factoresCrudoYCarbon.get(puestotrabajo
							.getPkCodigoPuestotrabajo());
					if (consumosFactores != null && produccionTM != 0) {
						// TODO: eliminar este "if". fue traducción de un if
						// (len >
						// 2) que no tiene
						// sentido
						if (consumosFactores.getFactAntrac() != null) {
							registro.setFactAntrac(consumosFactores.getFactAntrac());
						}
					}

					registrosDto.add(registro);
				}

				// return registrosDto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return registrosDto;
	}

}
