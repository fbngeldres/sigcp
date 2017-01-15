package pe.com.pacasmayo.sgcp.logica.notificacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RfcPeMMConMatPrimaLogic.java
 * Modificado: Jun 29, 2010 8:08:05 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.RfcPeMMConMatPrimaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaconsumo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteNotificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlantillaConsumoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import util.ConexionSAP;

public class RfcPeMMConMatPrimaLogic implements ConstantesMensajeAplicacion, RfcPeMMConMatPrimaLogicFacade, ConstantesSap,
		ConstantesLogicaNegocio {

	private static final Object NOMBRE_RFC_CONSUMOS = "Consumos";
	public static final String PROCESOS_EXCEPTION_RFC = "excepcion.rfc.combustibles.procesos";
	public static final String PRODUCTO_EXCEPTIO_RFC = "excepcion.rfc.combustibles.productos";
	private final double FACTOR_GALONES = 3.785412d;

	/** Logger Instance */
	protected static Logger logger = Logger.getLogger(RfcPeMMConMatPrimaLogic.class);
	private String mensajeError;
	public static final ParametroSistemaLogicFacade parametroSitema = new ParametroSistemaLogic();
	private String delimitador = "\\u00AC";

	/*
	 * BLDATT - Fec.Doc BKTXT - Tex.Cab BWART - Tipo Movimiento KOSTL - Centro
	 * de Costo AUFNR - Orden MATNR - Material MENGE - Cantidad WEMPF - Destino
	 * SGTXT - Tex.Det
	 */
	public static void main(String[] args) {
		Date fechaNotif = FechaUtil.FormaterFecha("26/06/2014");
		String fechaP_BUDAT = PropiedadesSap.formatoFechaPbudat(fechaNotif);
		String fechaBLDATT = PropiedadesSap.formatoFechaBLDATT(fechaNotif);

		RfcPeMMConMatPrimaLogic rfc = new RfcPeMMConMatPrimaLogic();
		try {
			rfc.ingresarDatosPorLote(fechaP_BUDAT, 1491L, fechaBLDATT);
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.RfcPeMMConMatPrimaLogicFacade
	 * #ingresarDatosPorLote(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public ResultadoBeanImpl ingresarDatosPorLote(String fechaP_BUDAT, Long codigoNotif, String fechaBLDATT)
			throws LogicaException {

		ParametroSistemaBean parametro = parametroSitema
				.obtenerParametroSistemaDAO(ConstantesParametro.TIPO_CONSUMO_CONSIGNACION);

		if (parametro == null) {
			mensajeError = "No se tiene registrado el parametro de sistema, TIPO_CONSUMO_CONSIGNACION";
			throw new LogicaException(mensajeError);
		}

		if (parametro.getValor() == null) {
			mensajeError = "No se tiene registrado el valor del parametro de sistema, TIPO_CONSUMO_CONSIGNACION";
			throw new LogicaException(mensajeError);
		}

		ConexionSAP conexionSAP = null;
		String codigoSapObjetocosto = null;
		try {
			// Obtener las ordenes de produccion.
			List<Ordenproduccion> ordenes = NotificacionProduccionQuerier.obtenerOrdenesProduccionSegunNotifDiaria(codigoNotif);

			if (ordenes == null) {
				return new ResultadoBeanImpl();
			}
			if (ordenes.size() == 0) {
				return new ResultadoBeanImpl();
			}
			System.out.println("ANTES DE LA CONEXION A SAP");
			System.out.println("MANDANTE_ENTRENAMIENTO " + MANDANTE);
			System.out.println("USUARIO_ENTRENAMIENTO " + USUARIO);
			System.out.println("CONTRASENA_ENTRENAMIENTO " + CONTRASENA);
			System.out.println("IDIOMA_ENTRENAMIENTO " + IDIOMA);
			System.out.println("IPSAP_ENTRENAMIENTO " + IPSAP);
			System.out.println("NUMSISTEMA_ENTRENAMIENTO " + NUMSISTEMA);

			// realizar la conexion a SAP , Se ingresa MANDANTE,USUARIO
			// ,CONTRASENA , IDIOMA , IPSAP y NUMSISTEMA
			// Esta informacion es cargada desde un archivo de configuracion
			conexionSAP = new ConexionSAP(MANDANTE, USUARIO, CONTRASENA, IDIOMA, IPSAP, NUMSISTEMA);
			System.out.println("CONECTO A SAP");
			conexionSAP.RegistrarRFC(RFC_CONSUMO);
			logger.debug("REGISTRO EL RFC CONSUMO " + RFC_CONSUMO);

			conexionSAP.IngresarDatosInput(CODIGO_SAP_SOCIEDAD, "P_BUKRS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_CENTRO, "P_WERKS");
			conexionSAP.IngresarDatosInput(CODIGO_SAP_ALMACEN, "P_LGORT");
			conexionSAP.IngresarDatosInput(fechaP_BUDAT, FECHA_CONTABILIZACION_CAMPO_CONSUMO);

			int fila = 1;

			conexionSAP.CreaTabla(TABLA_SALIDA_GT_MSEG);
			for (Ordenproduccion ordenproduccion : ordenes) {

				Produccion produccion = ordenproduccion.getProduccion();
				Proceso proceso = produccion.getProceso();
				// Obtenemos las cantidades de las materias primas.
				List<Componentenotificacion> componentenotificacions = ComponenteNotificacionQuerier
						.obtenerSegunOrdenProducionYNotif(ordenproduccion, codigoNotif);

				Map<Long, Consumocomponente> mapaComponentes = agruparConsumosPorComponente(componentenotificacions);
				Set<Entry<Long, Consumocomponente>> entrySet = mapaComponentes.entrySet();

				for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
					Entry<Long, Consumocomponente> entry = (Entry<Long, Consumocomponente>) iterator.next();
					Consumocomponente consumocomponente = entry.getValue();
					// conexionSAP.CreaTabla(TABLA_SALIDA_GT_MSEG);
					Producto producto = consumocomponente.getComponente().getProductoByFkCodigoProductoComponente();
					// se seteo el
					// consumocomponente.getPkCodigoConsumocomponente() como el
					// codigo de puesto de trabajo

					String material = producto.getCodigoSapProducto().trim();
					Double consumo = consumocomponente.getConsumoConsumocomponente();

					if (consumo == 0L) {
						continue;
					}
					if (producto.getTipoconsumo() == null) {
						continue;
					}

					if (producto.getTipoconsumo().getNombreTipoconsumo().equals(parametro.getValor())) {
						continue;
					}
					// Envio de solo combustibles solidos
					// Validacion para que solo se envie carbon y no Bunker
					if (producto.getTipocategoriaproducto() != null) {
						if (producto.getTipocategoriaproducto().getNombreTipocategoriaproducto()
								.equals(ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_CATEG_PRODUCTO_COMBUSTIBLE))) {
							if (producto.getEstadoFisicoSolidoProducto() != null) {
								if (!producto.getEstadoFisicoSolidoProducto()) {
									continue;
								}
							}
						}

					}

					// formato con separador de miles , y separador de decimales
					// el . y toma solo 3 decimales
					NumberFormat decimalFormat = NumberFormat.getInstance(Locale.UK);

					String consumoStr = decimalFormat.format(consumo).replace(",", "").trim();

					// Obtengo el codigo del CENTRO DE COSTO
					codigoSapObjetocosto = obtenerCodigoCentroCosto(produccion, consumocomponente.getComponente());

					if (codigoSapObjetocosto == null) {
						continue;
					}

					System.out.println("Envio " + produccion.getProducto().getNombreProducto() + " Componente  _ "
							+ producto.getNombreProducto() + " VALOR : " + consumoStr + " CENTRO COSTO " + codigoSapObjetocosto);
					// Ingreso los datos a enviar a SAP
					conexionSAP.IngresarDatoTabla(fechaBLDATT, "BLDATT", fila);
					conexionSAP.IngresarDatoTabla("", "BLDAT", fila);
					conexionSAP.IngresarDatoTabla(TEXCAB, "BKTXT", fila);
					conexionSAP.IngresarDatoTabla(TIPO_MOVIMIENTO_CONSUMO, "BWART", fila);
					conexionSAP.IngresarDatoTabla(codigoSapObjetocosto, "KOSTL", fila);
					conexionSAP.IngresarDatoTabla("", "AUFNR", fila); // orden
					conexionSAP.IngresarDatoTabla(material, "MATNR", fila);
					conexionSAP.IngresarDatoTabla(consumoStr, "MENGE", fila);
					conexionSAP.IngresarDatoTabla(DESTINO_MATPROC_PCTLP, "WEMPF", fila);
					conexionSAP.IngresarDatoTabla(proceso.getNombreProceso() + " " + producto.getNombreProducto(), "SGTXT", fila);
					fila++;

				}

			}
			System.out.println("Envio Combustible");
			// Obtengo las ordenes de produccion y Puesto de trabajo
			List<Object[]> ordenesCombustible = NotificacionProduccionQuerier
					.obtenerOrdenesProduccionPuestoTrabajoByCodigoNotifDiaria(codigoNotif);

			for (Object[] ordenproduccionObj : ordenesCombustible) {
				Ordenproduccion ordenproduccion = (Ordenproduccion) ordenproduccionObj[0];
				Puestotrabajo puestoTrabajo = (Puestotrabajo) ordenproduccionObj[1];
				Produccion produccion = ordenproduccion.getProduccion();

				Proceso proceso = produccion.getProceso();

				// Obtengo los consumos de las materias primas
				List<Componentenotificacion> componentenotificacions = ComponenteNotificacionQuerier
						.obtenerSegunOrdenProducionYNotifYPuesto(ordenproduccion, codigoNotif,
								puestoTrabajo.getPkCodigoPuestotrabajo());

				Map<Long, Consumocomponente> mapaComponentes = agruparConsumosPorComponente(componentenotificacions);
				Set<Entry<Long, Consumocomponente>> entrySet = mapaComponentes.entrySet();

				for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
					Entry<Long, Consumocomponente> entry = (Entry<Long, Consumocomponente>) iterator.next();
					Consumocomponente consumocomponente = entry.getValue();
					// conexionSAP.CreaTabla(TABLA_SALIDA_GT_MSEG);
					Producto producto = consumocomponente.getComponente().getProductoByFkCodigoProductoComponente();
					// se seteo el
					// consumocomponente.getPkCodigoConsumocomponente() como el
					// codigo de puesto de trabajo

					String material = producto.getCodigoSapProducto().trim();
					Double consumo = consumocomponente.getConsumoConsumocomponente();

					if (consumo == 0L) {
						continue;
					}

					// Validaciones para enviar BUNKER
					if (producto.getTipocategoriaproducto() == null) {
						continue;
					}
					if (producto.getTipoconsumo() == null) {
						continue;
					}

					if (producto.getTipoconsumo().getNombreTipoconsumo().equals(parametro.getValor())) {
						continue;
					}
					if (!producto.getTipocategoriaproducto().getNombreTipocategoriaproducto()
							.equals(ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_CATEG_PRODUCTO_COMBUSTIBLE))) {
						continue;
					}
					// Envio de solo se envie Bunker y no carbon
					if (producto.getEstadoFisicoSolidoProducto() != null) {
						if (producto.getEstadoFisicoSolidoProducto()) {
							continue;
						}
					}

					// formato con separador de miles , y separador de decimales
					// el . y toma solo 3 decimales
					NumberFormat decimalFormat = NumberFormat.getInstance(Locale.UK);

					// Convierto a Galones
					consumo = consumo / FACTOR_GALONES;

					String consumoStr = decimalFormat.format(consumo).replace(",", "").trim();

					// Obtengo el codigo del centro de costo
					codigoSapObjetocosto = obtenerCodigoCentroCostoProductoPuestoTrabajo(consumocomponente.getComponente()
							.getProductoByFkCodigoProductoComponente().getPkCodigoProducto(),
							puestoTrabajo.getPkCodigoPuestotrabajo());
					if (codigoSapObjetocosto == null) {
						continue;
					}

					System.out.println("Envio Combustible " + produccion.getProducto().getNombreProducto() + " Componente  _ "
							+ producto.getNombreProducto() + " VALOR : " + consumoStr + " CENTRO COSTO " + codigoSapObjetocosto);
					// Ingreso a la tabla los valores a enviar a SAP
					conexionSAP.IngresarDatoTabla(fechaBLDATT, "BLDATT", fila);
					conexionSAP.IngresarDatoTabla("", "BLDAT", fila);
					conexionSAP.IngresarDatoTabla(TEXCAB, "BKTXT", fila);
					conexionSAP.IngresarDatoTabla(TIPO_MOVIMIENTO_CONSUMO, "BWART", fila);
					conexionSAP.IngresarDatoTabla(codigoSapObjetocosto, "KOSTL", fila);
					conexionSAP.IngresarDatoTabla("", "AUFNR", fila); // orden
					conexionSAP.IngresarDatoTabla(material, "MATNR", fila);
					conexionSAP.IngresarDatoTabla(consumoStr, "MENGE", fila);
					conexionSAP.IngresarDatoTabla(DESTINO_MATPROC_PCTLP, "WEMPF", fila);
					conexionSAP.IngresarDatoTabla(proceso.getNombreProceso() + " " + producto.getNombreProducto(), "SGTXT", fila);
					fila++;

				}

			}

			System.out.println("Antes de EJECUTAR a SAP ");
			// Ejecuto el RFC en SAP
			conexionSAP.EjecutarRFC();

			conexionSAP.CreaTabla("GT_LOG");
			// Obtengo el resultado que me envia el RFC
			ArrayList<String> Resultado = conexionSAP.ObtenerDatosTabla();
			// Proceso el resultado y lo convierto a un objeto del SGCP
			return enviarResultadoOperacionRFC(Resultado);
		} catch (Exception e) {
			String errorBase = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ERROR_COMUNICACION_SAP);
			mensajeError = MessageFormat.format(errorBase, new Object[] { NOMBRE_RFC_CONSUMOS });
			mensajeError.concat(" Registre los consumos de materia prima de forma manual en SAP.");
			logger.error(e);
			e.printStackTrace();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (conexionSAP != null) {
				conexionSAP.DesconectarSAP();
			}
		}
	}

	private ResultadoBeanImpl enviarResultadoOperacionRFC(ArrayList<String> mensajesSAP) {

		ResultadoBeanImpl resultadoBean = new ResultadoBeanImpl();

		for (String string : mensajesSAP) {
			System.out.println(string);
		}

		if (mensajesSAP != null && mensajesSAP.size() > 0) {
			String resultado = mensajesSAP.get(0);

			String[] cadenaMensaje = resultado.split(delimitador);

			if (cadenaMensaje.length >= 10) {
				resultadoBean.setMensajeOperacion(cadenaMensaje[3] + " " + cadenaMensaje[5] + " " + cadenaMensaje[7]);
				if (cadenaMensaje[10].equals(procesoRFCCorrecto)) {
					resultadoBean.setExitoOperacion(Boolean.TRUE);
				} else {
					resultadoBean.setExitoOperacion(Boolean.FALSE);
				}
			}

		}
		return resultadoBean;
	}

	private Map<Long, Consumocomponente> agruparConsumosPorComponente(List<Componentenotificacion> componentenotificacions) {
		Map<Long, Consumocomponente> mapaComponentes = new HashMap<Long, Consumocomponente>();
		for (Iterator<Componentenotificacion> itt = componentenotificacions.iterator(); itt.hasNext();) {
			Componentenotificacion componentenotificacion = itt.next();
			Componente componente = componentenotificacion.getComponente();

			Consumocomponente consumocomponente = mapaComponentes.get(componente.getPkCodigoComponente());
			if (consumocomponente == null) {
				consumocomponente = new Consumocomponente(componente, null,
						componentenotificacion.getValorComponentenotificacion());
				// SETEO AQUI EL CODIGO DEL PUESTO DE TRABAJO PARA PODER HACER
				// LA VALICACION
				consumocomponente.setPkCodigoConsumocomponente(componentenotificacion.getNotificacionproduccion()
						.getPuestotrabajo().getPkCodigoPuestotrabajo());
				mapaComponentes.put(componente.getPkCodigoComponente(), consumocomponente);
			} else {
				double consumo = consumocomponente.getConsumoConsumocomponente().doubleValue()
						+ componentenotificacion.getValorComponentenotificacion().doubleValue();
				consumocomponente.setConsumoConsumocomponente(consumo);
			}
		}
		return mapaComponentes;
	}

	private String obtenerCodigoCentroCosto(Produccion produccion, Componente componente) throws LogicaException {
		Plantillaconsumo plantillaConsumo = null;
		try {
			plantillaConsumo = PlantillaConsumoQuerier.obtenerPlantillaConsumo(produccion.getProceso(), produccion.getProducto(),
					componente);
		} catch (AplicacionException e) {
			throw new LogicaException(e.getMensaje());
		}
		if (plantillaConsumo == null) {
			return null;
		}

		return plantillaConsumo.getObjetocosto().getCodigoSapObjetocosto();
	}

	private String obtenerCodigoCentroCostoProductoPuestoTrabajo(Long codigoProductoAjusteProducto, Long codigoPuestoTrabajo)
			throws LogicaException {
		Plantillaconsumo plantillaConsumo = null;
		try {
			plantillaConsumo = PlantillaConsumoQuerier.obtenerPlantillaConsumoByProductoPuestoTrabajo(
					codigoProductoAjusteProducto, codigoPuestoTrabajo);
		} catch (AplicacionException e) {
			throw new LogicaException(e.getMensaje());
		}
		if (plantillaConsumo == null) {
			return null;
		}
		return plantillaConsumo.getObjetocosto().getCodigoSapObjetocosto().trim();
	}

}
