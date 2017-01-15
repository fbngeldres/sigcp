package pe.com.pacasmayo.sgcp.logica.notificacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionDiariaLogic.java
 * Modificado: Jun 23, 2010 3:23:06 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.AlmacenBean;
import pe.com.pacasmayo.sgcp.bean.CapacidadBolsaProductoBean;
import pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.bean.EstadoMovimientoBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.MovimientoBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.PeriodoContableBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.TipoDocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.bean.TipoMovimientoBean;
import pe.com.pacasmayo.sgcp.bean.UbicacionBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DocumentoMaterialBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.EstadoMovimientoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.MovimientoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AlmacenLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.CapacidadBolsaProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.DocumentoMaterialLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.HojaRutaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParteDiarioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PeriodoContableLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.RfcPeMMConMatPrimaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.RfcPeMMIngresosDiarioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.RfcPeMMStockDespachosLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.SociedadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaKardexLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TipoDocumentoMaterialLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TipoMovimientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UbicacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadMedidaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.AlmacenLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.CapacidadBolsaProductoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.SociedadLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.TipoMovimientoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UbicacionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadMedidaLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.ParteDiarioLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.TablaKardexLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.UsuarioLogic;
import pe.com.pacasmayo.sgcp.logica.stock.DocumentoMaterialLogic;
import pe.com.pacasmayo.sgcp.logica.stock.PeriodoContableLogic;
import pe.com.pacasmayo.sgcp.logica.stock.TipoDocumentoMaterialLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadonotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadopartediario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Partediario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablerocontrol;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.querier.AjusteProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.DocumentoMaterialQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoNotificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoParteDiarioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorVariacionProduccionPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorVariacionPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorconsumopuestotrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.LineaNegocioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MedioAlmacenamientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionDiariaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OrdenProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParteDiarioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionDiariaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoGeneradoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaOperacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TableroControlQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UsuarioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ValorPromVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificaciondiariaDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

public class NotificacionDiariaLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		ConstantesLogicaNegocio, NotificacionDiariaLogicFacade, ConstantesSap {

	private static final String ERROR_OBTENIENDO_CANT_MATERIAL = "El error obteniendo la cantidad de material";
	private static ParteDiarioLogicFacade parteDiarioLogicFacade = new ParteDiarioLogic();
	private static DocumentoMaterialLogicFacade documentoFacade = new DocumentoMaterialLogic();

	private static SociedadLogicFacade sociedadFacade = new SociedadLogic();
	private static TablaKardexLogicFacade kardexDiarioLogic = new TablaKardexLogic();
	private static TipoDocumentoMaterialLogicFacade tipoDocumentoFacade = new TipoDocumentoMaterialLogic();
	private static TipoMovimientoLogicFacade tipoMovimientoFacade = new TipoMovimientoLogic();
	private static ProductoLogicFacade productoFacade = new ProductoLogic();
	private static HojaRutaLogicFacade hojaRutaLogic = new HojaRutaLogic();
	private static PeriodoContableLogicFacade periodoContableFacade = new PeriodoContableLogic();
	private static UnidadMedidaLogicFacade unidadMedidaFacade = new UnidadMedidaLogic();
	private static AlmacenLogicFacade almacenLogicFacade = new AlmacenLogic();
	private static UbicacionLogicFacade ubicacionLogicFacade = new UbicacionLogic();
	private static ParametroSistemaLogicFacade parametroSistemaLogicFacade = new ParametroSistemaLogic();
	private static CapacidadBolsaProductoLogicFacade capacidadBolsaProductoLogicFacade = new CapacidadBolsaProductoLogic();
	private static LineaNegocioLogicFacade lineaNegocioFacade = new LineaNegocioLogic();

	private static RfcPeMMIngresosDiarioLogicFacade rfcPeMMIngresosDiarioFacade = new RfcPeMMIngresosDiarioLogic();
	private static RfcPeMMConMatPrimaLogicFacade rfcPeMMConMatPrimaFacade = new RfcPeMMConMatPrimaLogic();
	private static RfcPeMMStockDespachosLogicFacade rfcPeMMStockDespachosFacade = new RfcPeMMStockDespachosLogic();

	private static final String ESTADO_APROBADO = ManejadorPropiedades.obtenerPropiedadPorClave(ESTADONOTIFICACION_APROBADO);
	private static final String ESTADO_CERRADO = ManejadorPropiedades.obtenerPropiedadPorClave(ESTADONOTIFICACION_CERRADO);

	private static final String CODIGO_ESTADO_PARTEDIARIO_CERRADO = ManejadorPropiedades
			.obtenerPropiedadPorClave(CODIGO_ESTADOPARTEDIARIO_CERRADO);

	private String TIPO_DOCUMENTO_MATERIAL_INGRESO = ManejadorPropiedades.obtenerPropiedadPorClave(TIPODOCUMENTO_INGRESO);
	private String TIPO_DOC_MAT_SALIDA = ManejadorPropiedades.obtenerPropiedadPorClave(TIPODOCUMENTO_SALIDA);

	private String ERROR_CODIGO_PRODUCTO_SAP = ManejadorPropiedades
			.obtenerPropiedadPorClave(CODIGO_PRODUCTO_SAP_NO_EXISTE_EN_SGCP);

	private String ERROR_TIPO_MOV_RFC = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_SAP_TIPO_MOV_NO_EXISTE);

	private String codigoSAPProducto = "";
	private String codigoSAPTipoMovimiento = "";
	private String DELIMITADOR = "\\u00AC";
	private Date fechaMovimiento;

	// AGREGADO POR FABIAN GELDRES
	// Resuelve el caso 1495
	private String SIGNO_MENOS = "-";
	private String INGRESO_MERCADERIA = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_SAP_INGRESO_MERCADERIA);

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;
	private static BeandozerFactory beandozerFactory;
	private String mensajeError = "";
	private UsuarioBean usuarioSesion;

	public NotificacionDiariaLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
		NotificacionDiariaLogic.beandozerFactory = BeandozerFactoryImpl.getInstance();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionDiariaLogicFacade
	 * #obtenerNotificacionDiaria(java.lang.Long)
	 */
	public NotificacionDiariaBean obtenerNotificacionDiaria(Long codigo) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		NotificacionDiariaBean notificacionDiariaBean = null;

		try {
			notificacionDiariaBean = beanFactory.transformarNotificacionDiaria(NotificacionDiariaQuerier.getById(codigo));
			return notificacionDiariaBean;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public NotificacionDiariaBean obtenerUltimaNotificacionDiaria(NotificacionDiariaBean notificacion) throws LogicaException {

		NotificacionDiariaBean notificacionDiariaBean = null;
		Date fechaInicio = null;
		Date fechaFin = null;
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(notificacion.getFechaNotificacionDiaria());
			c1.set(Calendar.DAY_OF_MONTH, 1);
			fechaInicio = c1.getTime();
			c1 = Calendar.getInstance();
			c1.setTime(notificacion.getFechaNotificacionDiaria());
			c1.set(Calendar.DAY_OF_MONTH, c1.getMaximum(Calendar.DAY_OF_MONTH));
			fechaFin = c1.getTime();

			notificacionDiariaBean = beanFactory.transformarNotificacionDiaria(NotificacionDiariaQuerier
					.obtenerUltimaNotificacionDiaria(notificacion.getLineaNegocio().getCodigo(), fechaInicio, fechaFin));
			// notificacionDiariaBean =
			// beanFactory.transformarNotificacionDiaria(NotificacionDiariaQuerier.getById(notificacion.getCodigo()));

			return notificacionDiariaBean;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}
	}

	public NotificacionDiariaBean obtenerUltimaNotificacionDiariaAprobada(NotificacionDiariaBean notificacion)
			throws LogicaException {

		NotificacionDiariaBean notificacionDiariaBean = null;
		Date fechaInicio = null;
		Date fechaFin = null;
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(notificacion.getFechaNotificacionDiaria());
			c1.set(Calendar.DAY_OF_MONTH, 1);
			fechaInicio = c1.getTime();
			c1 = Calendar.getInstance();
			c1.setTime(notificacion.getFechaNotificacionDiaria());
			c1.set(Calendar.DAY_OF_MONTH, c1.getMaximum(Calendar.DAY_OF_MONTH));
			fechaFin = c1.getTime();

			notificacionDiariaBean = beanFactory.transformarNotificacionDiaria(NotificacionDiariaQuerier
					.obtenerUltimaNotificacionDiariaAprobada(notificacion.getLineaNegocio().getCodigo(), fechaInicio, fechaFin));

			return notificacionDiariaBean;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}
	}

	public NotificacionDiariaBean obtenerPrimeraNotificacionDiariaAprobada(NotificacionDiariaBean notificacion)
			throws LogicaException {

		NotificacionDiariaBean notificacionDiariaBean = null;
		Date fechaInicio = null;
		Date fechaFin = null;
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(notificacion.getFechaNotificacionDiaria());
			c1.set(Calendar.DAY_OF_MONTH, 1);
			fechaInicio = c1.getTime();
			c1 = Calendar.getInstance();
			c1.setTime(notificacion.getFechaNotificacionDiaria());
			c1.set(Calendar.DAY_OF_MONTH, c1.getMaximum(Calendar.DAY_OF_MONTH));
			fechaFin = c1.getTime();

			notificacionDiariaBean = beanFactory.transformarNotificacionDiaria(NotificacionDiariaQuerier
					.obtenerPrimeraNotificacionDiariaAprobada(notificacion.getLineaNegocio().getCodigo(), fechaInicio, fechaFin));

			return notificacionDiariaBean;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		}
	}

	/**
	 * Metodo para aprobar notificaciones segun sus ids (aprobar por lotes)
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws LogicaException
	 */
	public static void main4(String[] args) throws NumberFormatException, LogicaException {
		// //DIA 1
		// insertarProductoVarVariacion(115, 3, 146, "01/05/2012", 39, 98,
		// 0.9287);
		// Integer[] id1 = { 607 };
		// aprobar(id1);
		// //DIA 2
		// insertarProductoVarVariacion(116, 3, 146, "02/05/2012", 39, 98,
		// 0.9281);
		// Integer[] id2 = { 609 };
		// aprobar(id2);
		// //DIA 3-10
		// insertarProductoVarVariacion(109, 3, 146, "03/05/2012", 39, 97,
		// 0.9437);
		// Integer[] id3 = { 611, 613, 617, 618, 619, 623, 624, 626 };
		// aprobar(id3);
		// //DIA 11
		// insertarProductoVarVariacion(117, 3, 146, "11/05/2012", 39, 98,
		// 0.9305);
		// Integer[] id4 = { 628 };
		// aprobar(id4);
		// //DIA 12-17
		// insertarProductoVarVariacion(118, 3, 146, "12/05/2012", 39, 98,
		// 0.9329);
		// Integer[] id5 = { 631, 632, 634, 636, 638, 640 };
		// aprobar(id5);
		// //DIA 18-19
		// insertarProductoVarVariacion(112, 3, 146, "18/05/2012", 39, 96,
		// 0.9449);
		// Integer[] id6 = { 642, 643 };
		// aprobar(id6);
		// //DIA 20
		// insertarProductoVarVariacion(119, 3, 146, "20/05/2012", 39, 98,
		// 0.9299);
		// Integer[] id7 = { 646 };
		// aprobar(id7);
		// //DIA 21-18
		// insertarProductoVarVariacion(120, 3, 146, "21/05/2012", 39, 98,
		// 0.9365);
		// Integer[] id8 = { 647, 650, 651, 653, 658, 659, 660, 662 };
		//
		// aprobar(id8);
		// // //DIA 29-31
		// insertarProductoVarVariacion(121, 3, 146, "29/05/2012", 39, 98,
		// 0.9293);
		// Integer[] id9 = { 664
		// ,666,
		// 668
		// };

		Integer[] id9 = {

		2046, 2045, 2044, 2043, 2042, 2041, 2040, 2039, 2038, 2037, 2036, 2035, 2034, 2033, 2032, 2031, 2030, 2029, 2028, 2027,
				2026, 2025, 2024, 2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016

		};
		aprobar(id9);

	}

	private static void aprobar(Integer[] id) throws NumberFormatException, LogicaException {
		NotificacionDiariaLogic notificacionDiariaLogic = new NotificacionDiariaLogic();
		// for con los codigos de las notificaciones que se quieren aprobar
		List<NotificacionDiariaBean> notificacionesAprobar = new ArrayList<NotificacionDiariaBean>();
		for (int i = 0; i < id.length; i++) {
			Long idNotif = new Long(id[i] + "");
			System.out.println("idNotif " + idNotif);
			NotificacionDiariaBean notificacionDiaria = notificacionDiariaLogic.obtenerNotificacionDiaria(idNotif);
			System.out
					.println("AGREGO " + notificacionDiaria.getCodigo() + "_" + notificacionDiaria.getFechaNotificacionDiaria());
			notificacionesAprobar.add(notificacionDiaria);
		}
		Collections.sort(notificacionesAprobar, new Comparator<NotificacionDiariaBean>() {

			public int compare(NotificacionDiariaBean o1, NotificacionDiariaBean o2) {
				int compareFechas = o1.getFechaNotificacionDiaria().compareTo(o2.getFechaNotificacionDiaria());
				return compareFechas;
			}
		});

		UsuarioLogic usuarioLogic = new UsuarioLogic();

		// usuario rita
		UsuarioBean usuario = usuarioLogic.obtenerUsuario(new Long(2));

		for (NotificacionDiariaBean notificacion : notificacionesAprobar) {
			System.out.println("APROBANDO NOTIFICACION " + notificacion.getFechaNotificacionDiaria());
			// notificacionDiariaLogic.reprocesarNotificacion(notificacion,
			// usuario);
			notificacionDiariaLogic.aprobarNotificacion(notificacion, usuario);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * obtenerNotificacionDiariaDataObject(java.lang.Long)
	 */
	public Notificaciondiaria obtenerNotificacionDiariaDataObject(Long codigo) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		Notificaciondiaria notificacionDiaria = null;

		try {
			notificacionDiaria = NotificacionDiariaQuerier.getById(codigo);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return notificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionDiariaLogicFacade
	 * #obtenerNotificacionesDiariasPorFiltros(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<NotificacionDiariaBean> obtenerNotificacionesDiariasPorFiltros(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoEstado, Date fechaInicio, Date fechaFin) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		List<NotificacionDiariaBean> notificacionDiariaList = null;

		try {
			notificacionDiariaList = (List<NotificacionDiariaBean>) beandozerFactory.tranformarLista(NotificacionDiariaQuerier
					.obtenerNotificacionesDiariasPorFiltros(codigoLineaNegocio, codigoEstado, fechaInicio, fechaFin),
					NotificacionDiariaBean.class);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return notificacionDiariaList;
	}

	/*
	 * Agregado por Stephany Metodo para el filtro para mostrar todas las
	 * notificaciones menos el estado Generado
	 */

	public List<NotificacionDiariaBean> obtenerNotificacionesDiariasEstadoPorFiltros(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoEstado, Date fechaInicio, Date fechaFin) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		List<NotificacionDiariaBean> notificacionDiariaList = null;

		try {
			notificacionDiariaList = beanFactory.transformarListaNotificacionDiariaSimple(NotificacionDiariaQuerier
					.obtenerNotificacionesDiariasEstadoPorFiltros(codigoLineaNegocio, codigoEstado, fechaInicio, fechaFin));

			for (NotificacionDiariaBean notificacionDiariaBean : notificacionDiariaList) {
				if (notificacionDiariaBean.getEstadoNotificacion().getNombreEstadoNotificacion()
						.equals(EstadoNotificacionQuerier.ESTADO_APROBADO)) {
					notificacionDiariaBean.getEstadoNotificacion().setNombreEstadoNotificacion(
							EstadoNotificacionQuerier.ESTADO_ABIERTO);
				}
			}
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return notificacionDiariaList;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * obtenerNotificacionDiariaDTO(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date)
	 */
	public NotificaciondiariaDTO obtenerNotificacionDiariaDTO(Long codigoLineaNegocio, Long codigoTableroControl,
			Date fechaRegistro) throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		Notificaciondiaria notificaciondiaria = this.obtenerNotificacionDiariaDO(codigoLineaNegocio, codigoTableroControl,
				fechaRegistro);

		if (notificaciondiaria == null) {
			return null;
		}

		NotificaciondiariaDTO notificaciondiariaDTO = new NotificaciondiariaDTO();
		try {
			notificaciondiariaDTO.setPkCodigoNotificaciondiaria(notificaciondiaria.getPkCodigoNotificaciondiaria());
			notificaciondiariaDTO.setPkCodigoEstadonotificacion(notificaciondiaria.getEstadonotificacion()
					.getPkCodigoEstadonotificacion());
			notificaciondiariaDTO.setPkCodigoLineanegocio(codigoLineaNegocio);
			notificaciondiariaDTO.setPkCodigoUsuarioRegistra(notificaciondiaria.getUsuarioByFkCodigoUsuarioRegistra()
					.getPkCodigoUsuario());
			notificaciondiariaDTO.setPkCodigoTablerocontrol(codigoTableroControl);
			notificaciondiariaDTO.setFechaNotificaciondiaria(fechaRegistro);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return notificaciondiariaDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * obtenerNotificacionDiariaDO(java.lang.Long, java.lang.Long,
	 * java.util.Date)
	 */
	public Notificaciondiaria obtenerNotificacionDiariaDO(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro)
			throws LogicaException {
		/**
		 * Este metodo no controla sesion , ya que #obtenerNotificacionDiariaDTO
		 * , NotificacionServiceImpl#verificarSiExistenRegistrosNotifProd y
		 * NotificacionServiceImpl#obtenerDatosBD gestionan dichas conexiones
		 */
		String mensajeError = "";

		Notificaciondiaria notificacion = null;

		try {
			notificacion = NotificacionDiariaQuerier.obtenerNotificacionDiaria(codigoLineaNegocio, codigoTableroControl,
					fechaRegistro);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		}

		return notificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * actualizarEstadoNotificacion
	 * (pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean)
	 */
	public void actualizarEstadoNotificacion(NotificacionDiariaBean notificacionDiariaBean, Long codigoEstado)
			throws LogicaException {
		Transaction tx = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Notificaciondiaria notificacion = NotificacionDiariaQuerier.getById(notificacionDiariaBean.getCodigo());

			DateFormat df = new SimpleDateFormat(FechaUtil.PATRON_FECHA_APLICACION);
			String fechaStr = df.format(notificacion.getFechaNotificaciondiaria());

			try {
				Long codigoEstadoAprobado = new Long(
						ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_ESTADONOTIFICACION_APROBADO));
				notificacion.setFechaNotificaciondiaria(df.parse(fechaStr));
				if (codigoEstado.longValue() == codigoEstadoAprobado.longValue()) {
					notificacion.setFechaAprobacionNotificaciondi(Calendar.getInstance().getTime());
					Usuario usuarioAprueba = new Usuario();
					usuarioAprueba.setPkCodigoUsuario(usuarioSesion.getCodigo());
					notificacion.setUsuarioByFkCodigoUsuarioAprueba(usuarioAprueba);
				}
			} catch (ParseException e) {
				throw new LogicaException(ERROR_FATAL_FALLO);
			}

			Estadonotificacion estado = EstadoNotificacionQuerier.getById(codigoEstado);
			notificacion.setEstadonotificacion(estado);
			NotificacionDiariaQuerier.update(notificacion);

			tx.commit();
		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * obtenerNotificacionesDiariasCantera(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 * java.util.Date, java.util.Date)
	 */
	public List<NotificacionDiariaBean> obtenerNotificacionesDiariasCantera(Long codigoUnidad, Long codigoLineaNegocio,
			Long codigoActividad, Long codigoTableroControl, Long codigoPuestoTrabajo, Long codigoEstado, Date fechaInicio,
			Date fechaFin) throws LogicaException {

		List<NotificacionDiariaBean> notificacionDiariaList = null;

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			notificacionDiariaList = beanFactory.transformarListaNotificacionDiariaSimple(NotificacionDiariaQuerier
					.obtenerNotificacionesDiariasCantera(codigoUnidad, codigoLineaNegocio, codigoActividad, codigoPuestoTrabajo,
							codigoTableroControl, codigoEstado, fechaInicio, fechaFin));
			return notificacionDiariaList;
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * insertarNotificacionPlanta(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date, java.lang.Long)
	 */
	public void insertarNotificacionPlanta(Long codigoUsuarioRegistra, Long codigoLineaNegocio, Long codigoTableroControl,
			Long codigoEstadoNotificacion, Date fechaRegistro, UsuarioBean usuario) throws LogicaException {
		usuarioSesion = usuario;
		Transaction tx = null;
		Session session = null;
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Notificaciondiaria notificacionDiaria = new Notificaciondiaria();

			Usuario usuarioAprueba = null;
			notificacionDiaria.setUsuarioByFkCodigoUsuarioAprueba(usuarioAprueba);

			Estadonotificacion estadonotificacion = EstadoNotificacionQuerier.getById(codigoEstadoNotificacion);
			notificacionDiaria.setEstadonotificacion(estadonotificacion);

			Lineanegocio lineanegocio = LineaNegocioQuerier.getById(codigoLineaNegocio);
			notificacionDiaria.setLineanegocio(lineanegocio);

			Usuario usuarioRegistra = UsuarioQuerier.getById(codigoUsuarioRegistra);
			notificacionDiaria.setUsuarioByFkCodigoUsuarioRegistra(usuarioRegistra);

			Tablerocontrol tablerocontrol = TableroControlQuerier.getById(codigoTableroControl);
			notificacionDiaria.setTablerocontrol(tablerocontrol);

			notificacionDiaria.setFechaNotificaciondiaria(fechaRegistro);
			notificacionDiaria.setFechaAprobacionNotificaciondi(null);

			NotificacionDiariaQuerier.save(notificacionDiaria);

			tx.commit();

		} catch (ParametroInvalidoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);
			logger.error(mensajeError, e);
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * obtenerNotificacionDiariaPorCodigo(java.lang.Long)
	 */
	public NotificacionDiariaBean obtenerNotificacionDiariaPorCodigo(Long codigoNotificacionDiaria) throws LogicaException {
		if (codigoNotificacionDiaria == null) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(NOTIFICACION_DIARIA_CODIGO) + ": "
					+ ManejadorPropiedades.obtenerPropiedadPorClave(FORMATO_INVALIDO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}
		NotificacionDiariaBean notificaciondiaria = null;

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			notificaciondiaria = beanFactory.transformarNotificacionDiariaSimple(NotificacionDiariaQuerier
					.getById(codigoNotificacionDiaria));
			return notificaciondiaria;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * obtenerNotificacionDiariaCantera(java.lang.Long, java.util.Date)
	 */
	public NotificacionDiariaBean obtenerNotificacionDiariaCantera(Long codigoLineaNegocio, Date fechaRegistro)
			throws LogicaException {
		if (codigoLineaNegocio == null) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_LINEANEGOCIO_REQUERIDO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}

		if (fechaRegistro == null) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_FECHA_REGISTRO_REQUERIDA);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}

		NotificacionDiariaBean notificaciondiaria = null;

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			notificaciondiaria = beanFactory.transformarNotificacionDiaria(NotificacionDiariaQuerier
					.obtenerNotificacionDiariaCanterasDespachos(codigoLineaNegocio, fechaRegistro));
			return notificaciondiaria;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * obtenerNotificacionDiariaCanteraBase(java.lang.Long, java.util.Date)
	 */
	public NotificacionDiariaBean obtenerNotificacionDiariaCanteraBase(Long codigoLineaNegocio, Date fechaRegistro)
			throws LogicaException {
		if (codigoLineaNegocio == null) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_LINEANEGOCIO_REQUERIDO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}

		if (fechaRegistro == null) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_FECHA_REGISTRO_REQUERIDA);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}

		NotificacionDiariaBean notificaciondiaria = null;

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			Notificaciondiaria notificacion = NotificacionDiariaQuerier.obtenerNotificacionDiariaCanterasDespachos(
					codigoLineaNegocio, fechaRegistro);
			if (notificacion == null)
				return null;
			notificaciondiaria = new NotificacionDiariaBeanImpl();
			notificaciondiaria.setFechaNotificacionDiaria(notificacion.getFechaNotificaciondiaria());
			notificaciondiaria.setObservacionNotificacionDiaria(notificacion.getObservacionNotificaciondiaria());
			notificaciondiaria.setCodigo(notificacion.getPkCodigoNotificaciondiaria());

			return notificaciondiaria;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * eliminarNotificacionDiaria(java.lang.Long, java.lang.Long,
	 * java.util.Date)
	 */
	public void eliminarNotificacionesProduccion(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoNotifDiaria, Long codigoPuestoTrabajo, UsuarioBean usuario) throws LogicaException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			session.beginTransaction();
			NotificacionDiariaQuerier.eliminarNotificacionesProduccion(codigoNotifDiaria, codigoPuestoTrabajo);
			session.getTransaction().commit();
		} catch (ElementoEliminadoException e) {
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
			throw new LogicaException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade#
	 * aprobarNotificacion(pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean)
	 */
	public NotificacionDiariaBean aprobarNotificacion(NotificacionDiariaBean notificacion, UsuarioBean usuario)
			throws LogicaException {

		if (notificacion == null || notificacion.getCodigo() == null) {
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(NOTIFICACION_NO_SELECCIONADA));
		}
		Date fechaNotif = null;
		logger.debug("1... " + (new Date()));
		try {
			Long codigoNotif = notificacion.getCodigo();
			usuarioSesion = usuario;
			notificacion = this.obtenerNotificacionDiaria(codigoNotif);
			System.out.println("2... " + (new Date()));
			if (notificacion == null
					|| notificacion.getEstadoNotificacion().getNombreEstadoNotificacion().equals(ESTADO_APROBADO)) {
				throw new LogicaException(ERROR_NOTIFICACION_APROBADA);
			}

			fechaNotif = notificacion.getFechaNotificacionDiaria();
			logger.debug(" Empezando SAP 3... " + (new Date()));
			boolean movimientosAutomaticos = MovimientoQuerier.verificarMovimientosAutomaticosEnFecha(fechaNotif);
			if (movimientosAutomaticos == false) {
				logger.debug("4... " + (new Date()));
				registrarMovimientoIngresoMateriasPrimas(fechaNotif, usuario);
				logger.debug("5... " + (new Date()));
				registrarMovimientoDespachoProductosTerminados(fechaNotif, usuario);
			}
			logger.debug(" Finalizando SAP 6... " + (new Date()));
			parteDiarioLogicFacade.registrarPartediario(notificacion, usuario);
			logger.debug("7... " + (new Date()));
			notificacion.setFechaNotificacionDiaria(fechaNotif);

			cambiarAEstadoAprobado(notificacion);
			logger.debug("8... " + (new Date()));
			
			logger.debug("9... " + (new Date()));
			
			logger.debug("10... " + (new Date()));
			return notificacion;
		} catch (ElementoEliminadoException e) {
			documentoFacade.eliminarDocumentosGeneradosSap(fechaNotif);
			throw new LogicaException(e);
		} catch (ElementoNoEncontradoException e) {
			documentoFacade.eliminarDocumentosGeneradosSap(fechaNotif);
			throw new LogicaException(e);
		} catch (ParametroInvalidoException e) {
			documentoFacade.eliminarDocumentosGeneradosSap(fechaNotif);
			throw new LogicaException(e);
		}
	}

	public static void main(String[] args) {
		String fechaFormateada = PropiedadesSap.formatoFechaPbudat(FechaUtil.FormaterFecha("10/10/2015"));
		try {
			System.out.println(" 1 -->" + (new Date()));
			NotificacionDiariaLogic no = new NotificacionDiariaLogic();
			UsuarioLogic u = new UsuarioLogic();
			System.out.println(" 2 -->" + (new Date()));
			no.registrarMovimientoIngresoMateriasPrimas(FechaUtil.FormaterFecha("15/04/2016"), u.obtenerUsuario(160L));
			System.out.println(" 3 -->" + (new Date()));
			no.registrarMovimientoDespachoProductosTerminados(FechaUtil.FormaterFecha("15/04/2016"), u.obtenerUsuario(160L));
			System.out.println(" 4 -->" + (new Date()));
			// List<String> stri =
			// rfcPeMMStockDespachosFacade.obtenerDatosTabla(fechaFormateada);
			// for (String string : stri) {
			// System.out.println(string);
			// }
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void registrarMovimientoIngresoMateriasPrimas(Date fechaNotif, UsuarioBean usuario) throws LogicaException {
		SociedadBean sociedad = sociedadFacade.obtenerSociedadPorCodigoSAP(CODIGO_SAP_SOCIEDAD);

		TipoDocumentoMaterialBean tipodocumentomaterial = tipoDocumentoFacade
				.obtenerTipoDocumentoPorNombre(TIPO_DOCUMENTO_MATERIAL_INGRESO);

		UnidadMedidaBean unidadMedida = unidadMedidaFacade.obtenerUnidadMedidaPorNombre(ManejadorPropiedades
				.obtenerPropiedadPorClave(UNIDADMEDIDA));

		String fechaFormateada = PropiedadesSap.formatoFechaPbudat(fechaNotif);

		PeriodoContableBean periodo = obtenerPeriodoContable(fechaNotif);

		List<String> registrosRFC = rfcPeMMIngresosDiarioFacade.obtenerDatosTabla(fechaFormateada);

		for (Iterator<String> iterator = registrosRFC.iterator(); iterator.hasNext();) {
			String registro = iterator.next();
			String[] campos = registro.split(DELIMITADOR);

			String codigoSAP = campos[6];
			codigoSAPProducto = obtenerCodigoSapTransformado(codigoSAP);
			ProductoBean producto = productoFacade.obtenerProductoPorCodigoSAP(codigoSAPProducto);

			if (producto == null) {
				String mensaje = MessageFormat.format(ERROR_CODIGO_PRODUCTO_SAP, new Object[] { codigoSAPProducto,
						RfcPeMMIngresosDiarioLogic.NOMBRE_RFC_INGRESOS });
				logger.error(mensaje);
				documentoFacade.eliminarDocumentosGeneradosSap(fechaNotif);
				throw new LogicaException(mensaje);
			}

			List<HojaRutaBean> hojasRuta = hojaRutaLogic.obtenerHojasRutaActivaPorProducto(producto.getCodigo());

			if (hojasRuta != null && hojasRuta.size() > 0) {

				String fecha = campos[4];
				fechaMovimiento = FechaUtil.convertirAFecha(fecha, FechaUtil.PATRON_FECHA_APLICACION, null);

				DocumentoMaterialBean documentoMaterial = new DocumentoMaterialBeanImpl();
				documentoMaterial.setUsuario(usuario);
				documentoMaterial.setSociedad(sociedad);
				documentoMaterial.setOrigenSapMovimiento(true);
				documentoMaterial.setFechaDocumentomaterial(fechaMovimiento);
				documentoMaterial.setPeriodocontable(periodo);
				documentoMaterial.setTipodocumentomaterial(tipodocumentomaterial);

				EstadoMovimientoBean estadomovimiento = new EstadoMovimientoBeanImpl();
				estadomovimiento.setCodigo(new Long(OPCION_1));

				MovimientoBean movimientoBean = new MovimientoBeanImpl();
				movimientoBean.setEstadomovimiento(estadomovimiento);
				movimientoBean.setDocumentomaterial(documentoMaterial);

				String codigoSapAlmacen = campos[5].trim();
				asignarUbicacionPorDefectoDeAlmacen(movimientoBean, codigoSapAlmacen);

				movimientoBean.setFechaMovimiento(fechaMovimiento);
				double cantidadTM = 0d;

				try {
					LineaNegocioBean lineaNegocio = lineaNegocioFacade.obtenerLineasNegocioSegunHojaruta(producto.getCodigo());
					movimientoBean.setLineanegocio(lineaNegocio);

					NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
					cantidadTM = numberFormat.parse(campos[7].trim()).doubleValue();

					// AGREGADO POR FABIAN GELDRES RESUELVE EL CASI 1495
					if (campos[7].trim().indexOf(SIGNO_MENOS) >= 0 && campos[campos.length - 1].trim().equals(INGRESO_MERCADERIA)) {
						cantidadTM = cantidadTM * -1;
					}

				} catch (ParseException e) {
					documentoFacade.eliminarDocumentosGeneradosSap(fechaNotif);
					logger.error(ERROR_OBTENIENDO_CANT_MATERIAL);
					throw new LogicaException(ERROR_OBTENIENDO_CANT_MATERIAL);
				}
				movimientoBean.setCantidadMovimiento(cantidadTM);

				movimientoBean.setProducto(producto);
				movimientoBean.setUnidadmedida(unidadMedida);
				movimientoBean.setCodigoSapproducto(codigoSAPProducto);
				codigoSAPTipoMovimiento = campos[10];
				TipoMovimientoBean tipoMovimiento = tipoMovimientoFacade
						.obtenerTipoMovimientoPorCodigoSAP(codigoSAPTipoMovimiento);
				movimientoBean.setTipomovimiento(tipoMovimiento);

				List<MovimientoBean> movimientos = new ArrayList<MovimientoBean>();
				movimientos.add(movimientoBean);
				documentoMaterial.setMovimientos(movimientos);

				documentoFacade.insertarDocumentoMaterial(documentoMaterial);

			}
		}
	}

	/**
	 * Metodo que recibe el codigo sap del alamcen y busca en el archivo de
	 * propiedades la ubicacion por defecto para dicho almacen, ya que un
	 * alamcen puede tener varias ubicaciones por BD pero esto solo aplica para
	 * tembladera
	 * 
	 * @param movimientoBean MovimientoBean movimiento al que se le asignara la
	 *            ubicacion
	 * @param codigoSapAlmacen String codigo sap del alamcen
	 * @throws LogicaException si la busqueda del alacen o ubicion falla
	 */
	private void asignarUbicacionPorDefectoDeAlmacen(MovimientoBean movimientoBean, String codigoSapAlmacen)
			throws LogicaException {
		AlmacenBean almacenBean = almacenLogicFacade.obtenerAlmacenPorCodigoSAP(codigoSapAlmacen);

		if (almacenBean == null) {
			// si no existe no se registra
			return;
		}

		// es una sola clave y hay que concatenar el codigo del alamcen
		String claveAlmacen = ALMACEN_PARA_UBICACION_POR_DEFECTO + almacenBean.getCodigo();

		Long codigoUbicacion = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(claveAlmacen));
		UbicacionBean ubicacionBean = ubicacionLogicFacade.obtenerUbicacion(codigoUbicacion);

		movimientoBean.setUbicacionByFkCodigoUbicacionOrigen(ubicacionBean);
	}

	/**
	 * Valida el producto obtenido con el codigo sap, si este es null se busca
	 * con el codigo sap bolsa en caso de que exista con codigo sap bolsa hay
	 * que transformar la cantidad a toneladas
	 * 
	 * @param producto
	 * @return
	 * @throws LogicaException
	 */
	private ProductoBean obtenerProductoSegunCodigoSap(Long unidadMedida) throws LogicaException {
		ProductoBean producto = null;

		producto = productoFacade.obtenerProductoPorCodigoSAPUnidadMedida(codigoSAPProducto, unidadMedida);

		if (producto == null) {
			String mensaje = MessageFormat.format(ERROR_CODIGO_PRODUCTO_SAP, new Object[] { codigoSAPProducto,
					RfcPeMMStockDespachosLogic.NOMBRE_RFC_DESPACHOS });
			logger.error(mensaje);
			throw new LogicaException(mensaje);
		}

		return producto;
	}

	private ResultadoBeanImpl exportarConsumoDeMateriasPrimas(Date fechaNotif, Long codigoNotif) throws LogicaException {
		String fechaP_BUDAT = PropiedadesSap.formatoFechaPbudat(fechaNotif);
		String fechaBLDATT = PropiedadesSap.formatoFechaBLDATT(fechaNotif);
		ResultadoBeanImpl estadoRetornoRFC;
		estadoRetornoRFC = rfcPeMMConMatPrimaFacade.ingresarDatosPorLote(fechaP_BUDAT, codigoNotif, fechaBLDATT);
		return estadoRetornoRFC;
	}

	private PeriodoContableBean obtenerPeriodoContable(Date fecha) throws LogicaException {
		String fechaString = FechaUtil.convertirDateToString(fecha);
		String[] fechaStringArray = fechaString.split(FechaUtil.SEPARADOR_CAMPOS_FECHA_APP);

		Short mesPeriodo = new Short(fechaStringArray[MES]);
		int anioPeriodo = Integer.parseInt(fechaStringArray[ANIO]);

		return periodoContableFacade.getPeriodoContablePorFecha(mesPeriodo, anioPeriodo);
	}

	public void registrarMovimientoDespachoProductosTerminados(Date fechaNotif, UsuarioBean usuario) throws LogicaException {

		ParametroSistemaBean parametroSistemaBean = parametroSistemaLogicFacade
				.obtenerParametroSistemaDAO(ConstantesParametro.CODIGO_UNIDAD_MEDIDA_TM);

		if (parametroSistemaBean == null) {
			throw new LogicaException("No se encontró el parametro de sistema " + ConstantesParametro.CODIGO_UNIDAD_MEDIDA_TM);
		}

		if (parametroSistemaBean.getValor() == null) {
			throw new LogicaException("No se encontró el valor del parametro de sistema "
					+ ConstantesParametro.CODIGO_UNIDAD_MEDIDA_TM);
		}
		Long codigo_unidad_medida_TM = Long.valueOf(parametroSistemaBean.getValor());

		String fechaFormateada = PropiedadesSap.formatoFechaPbudat(fechaNotif);
		SociedadBean sociedad = sociedadFacade.obtenerSociedadPorCodigoSAP(CODIGO_SAP_SOCIEDAD);
		TipoDocumentoMaterialBean tipoDocMat = tipoDocumentoFacade.obtenerTipoDocumentoPorNombre(TIPO_DOC_MAT_SALIDA);
		// String unidadMedidaProperty =
		// ManejadorPropiedades.obtenerPropiedadPorClave(UNIDADMEDIDA);
		UnidadMedidaBean unidadMedida = unidadMedidaFacade.obtenerUnidadMedidaPorCodigo(codigo_unidad_medida_TM);

		PeriodoContableBean periodo = obtenerPeriodoContable(fechaNotif);

		// Codigo estado liberada
		Long codgoEstadoLiberada = Long
				.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(ESTADO_ORDEN_PRODUCCION_LIBERADA));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaNotif);
		int mes = calendar.get(Calendar.MONTH) + 1;
		int anio = calendar.get(Calendar.YEAR);

		List<String> registrosRFC = rfcPeMMStockDespachosFacade.obtenerDatosTabla(fechaFormateada);

		String codigosMovAnulacionTranslados = ManejadorPropiedades
				.obtenerPropiedadPorClave(CODIGOS_MOVIMIENTOS_ANULACION_TRANSLADO);
		String codigosMovTraslados = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGOS_MOVIMIENTOS_TRANSLADO);

		NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);

		for (String registro : registrosRFC) {

			System.out.println(fechaNotif + "--->" + registro);
			String[] campos = registro.split(DELIMITADOR);
			boolean negativo = false;
			String cantidadMov = null;
			double cantidadTM = 0d;
			double cantidad = 0d;

			String codigoSAP = campos[4];
			codigoSAPProducto = obtenerCodigoSapTransformado(codigoSAP);

			ProductoBean producto = obtenerProductoSegunCodigoSap(unidadMedida.getCodigo());

			try {
				cantidadMov = campos[5].trim();
				negativo = cantidadMov.indexOf("-") >= 0;
				cantidad = numberFormat.parse(cantidadMov).doubleValue();
				System.out.println(cantidad);

				String unidadMedidaStr = campos[6].trim();
				// Si el producto viene en bolsas, se lleva a TM
				if (unidadMedidaStr.equals(BOLSAS)) {
					cantidad = obtenerCantidadTM(cantidad);
				}
				cantidadTM = NumberUtil.redondear(cantidad, 4);
			} catch (ParseException e) {
				logger.error(ERROR_OBTENIENDO_CANT_MATERIAL);
				throw new LogicaException(ERROR_OBTENIENDO_CANT_MATERIAL);
			}

			String fecha = campos[1];
			fechaMovimiento = FechaUtil.convertirAFecha(fecha, FechaUtil.PATRON_FECHA_RFC_DESPACHOS, null);

			DocumentoMaterialBean documentoMaterial = new DocumentoMaterialBeanImpl();
			documentoMaterial.setUsuario(usuario);
			documentoMaterial.setSociedad(sociedad);
			documentoMaterial.setOrigenSapMovimiento(true);
			documentoMaterial.setFechaDocumentomaterial(fechaMovimiento);
			documentoMaterial.setTipodocumentomaterial(tipoDocMat);
			documentoMaterial.setPeriodocontable(periodo);

			EstadoMovimientoBean estadomovimiento = new EstadoMovimientoBeanImpl();
			// Estado generado
			estadomovimiento.setCodigo(new Long(1));

			MovimientoBean movimientoBean = new MovimientoBeanImpl();
			movimientoBean.setEstadomovimiento(estadomovimiento);
			logger.debug(cantidadTM);
			movimientoBean.setCantidadMovimiento(cantidadTM);
			movimientoBean.setDocumentomaterial(documentoMaterial);

			LineaNegocioBean lineaNegocioBean = lineaNegocioFacade.obtenerLineasNegocioSegunHojaruta(producto.getCodigo());
			movimientoBean.setLineanegocio(lineaNegocioBean);
			movimientoBean.setFechaMovimiento(fechaMovimiento);
			movimientoBean.setProducto(producto);
			movimientoBean.setUnidadmedida(unidadMedida);
			movimientoBean.setCodigoSapproducto(codigoSAPProducto);

			String codigoSapAlmacen = campos[3].trim();
			asignarUbicacionPorDefectoDeAlmacen(movimientoBean, codigoSapAlmacen);

			String tipoMovSap = campos[7].trim();
			if (codigosMovTraslados.equals(tipoMovSap)) {
				if (negativo) {
					// Salida de material
					TipoMovimientoBean tipoMovimiento = tipoMovimientoFacade.obtenerTipoMovimiento(Long.valueOf(1));
					movimientoBean.setTipomovimiento(tipoMovimiento);
				} else {
					// Ingreso de material
					TipoMovimientoBean tipoMovimiento = tipoMovimientoFacade.obtenerTipoMovimiento(Long.valueOf(3));
					movimientoBean.setTipomovimiento(tipoMovimiento);
				}
			} else if (codigosMovAnulacionTranslados.equals(tipoMovSap)) {
				if (negativo) {
					// Ingreso de material
					TipoMovimientoBean tipoMovimiento = tipoMovimientoFacade.obtenerTipoMovimiento(Long.valueOf(3));
					movimientoBean.setTipomovimiento(tipoMovimiento);
				} else {
					// Salida de material
					TipoMovimientoBean tipoMovimiento = tipoMovimientoFacade.obtenerTipoMovimiento(Long.valueOf(1));
					movimientoBean.setTipomovimiento(tipoMovimiento);
				}
			} else {
				TipoMovimientoBean tipoMovimiento = tipoMovimientoFacade.obtenerTipoMovimientoPorCodigoSAP(tipoMovSap);
				if (tipoMovimiento != null) {
					movimientoBean.setTipomovimiento(tipoMovimiento);
				} else {
					String error = MessageFormat.format(ERROR_TIPO_MOV_RFC, new Object[] { tipoMovSap });
					logger.error(error);
					throw new LogicaException(error);
				}
			}

			asignarMedioAlmacenamiento(codgoEstadoLiberada, mes, campos, producto, movimientoBean, lineaNegocioBean.getCodigo(),
					anio);

			List<MovimientoBean> movimientos = new ArrayList<MovimientoBean>();
			movimientos.add(movimientoBean);

			documentoMaterial.setMovimientos(movimientos);

			documentoFacade.insertarDocumentoMaterial(documentoMaterial);

		}

	}

	private Double obtenerCantidadTM(Double cantidad) {

		Double factor = Double.parseDouble(ManejadorPropiedades.obtenerPropiedadPorClave(FACTOR_CONVERSION_BOLSA));

		try {
			CapacidadBolsaProductoBean bolsaProductoBean = null;
			ParametroSistemaBean parametroBean = null;
			if (codigoSAPProducto != null) {
				bolsaProductoBean = capacidadBolsaProductoLogicFacade.obtenerPorCodigoSap(codigoSAPProducto);
			}

			if (bolsaProductoBean != null && bolsaProductoBean.getCapacidadNetaBolsa() != null) {

				factor = bolsaProductoBean.getCapacidadNetaBolsa();
			} else {
				parametroBean = parametroSistemaLogicFacade.obtenerParametroSistema(ConstantesParametro.CAPACIDAD_BOLSA_DEFECTO);

				if (parametroBean != null && parametroBean.getValor() != null && !parametroBean.getValor().equals("")) {

					factor = NumberUtil.convertirStringToDouble(parametroBean.getValor());
				}
			}
		} catch (LogicaException e) {
			logger.error(e);
		}

		cantidad = cantidad * (factor / 1000d);

		return cantidad;
	}

	/**
	 * Asigana el medio de almacenamiento si si el rfc envia un valor valido
	 * 
	 * @param codgoEstadoLiberada
	 * @param mes
	 * @param hashProductoMediosAlmc
	 * @param campos
	 * @param producto
	 * @param movimientoBean
	 * @param codigoLineaNeg
	 * @param anio
	 * @throws LogicaException
	 */
	private void asignarMedioAlmacenamiento(Long codgoEstadoLiberada, int mes, String[] campos, ProductoBean producto,
			MovimientoBean movimientoBean, Long codigoLineaNeg, Integer anio) throws LogicaException {
		int tamanoArrayConSilo = 8;

		if (campos.length > tamanoArrayConSilo && !StringUtils.isBlank(campos[8])) {

			short numeroMedioAlmacenamiento = (short) 0;
			try {
				numeroMedioAlmacenamiento = Short.parseShort(campos[8]);
			} catch (Exception e) {
				logger.error(e);
				return;
			}
			if (numeroMedioAlmacenamiento == 0) {
				return;
			}

			Ordenproduccion ordenProduccion = OrdenProduccionQuerier.obtenerPorProductoMesYEstado(mes, producto.getCodigo(),
					codgoEstadoLiberada, codigoLineaNeg, anio);

			if (ordenProduccion == null) {
				// String mensaje =
				// MessageFormat.format(ERROR_RFC_DESPACHOS_PRODUCTO_NO_TIENE_ORDEN_PROD_LIBERADA,
				// producto.getCodigo());
				// logger.error(mensaje);
				// documentoFacade.eliminarDocumentosGeneradosSap(movimientoBean.getFechaMovimiento());
				// throw new LogicaException(mensaje);
				return;
			}

			Long codigoProceso = ordenProduccion.getProduccion().getProceso().getPkCodigoProceso();

			try {
				Medioalmacenamiento silo = MedioAlmacenamientoQuerier.findSiloByNumeroYProduccion(numeroMedioAlmacenamiento,
						producto.getCodigo(), codigoProceso);
				MedioAlmacenamientoBean medioAlmacenamientoBean = beanFactory.transformarMedioAlmacenamiento(silo);
				movimientoBean.setMedioalmacenamiento(medioAlmacenamientoBean);
			} catch (SesionVencidaException e) {
				documentoFacade.eliminarDocumentosGeneradosSap(movimientoBean.getFechaMovimiento());
				throw new LogicaException(e.getMensaje(), e);
			} catch (ElementoNoEncontradoException e) {
				documentoFacade.eliminarDocumentosGeneradosSap(movimientoBean.getFechaMovimiento());
				throw new LogicaException(e.getMensaje(), e);
			} catch (EntornoEjecucionException e) {
				documentoFacade.eliminarDocumentosGeneradosSap(movimientoBean.getFechaMovimiento());
				throw new LogicaException(e.getMensaje(), e);
			}

		}
	}

	public String obtenerCodigoSapTransformado(String codigoSAP) {
		// expresion regular para comprobar si tiene el formato NNN-NNNNN donde
		// N es un digito
		String regex = "\\d{3}\\-\\d{5}";

		boolean formatoCorrecto = codigoSAP.matches(regex);

		if (formatoCorrecto) {
			return codigoSAP;
		}

		String codigoSap8Dig = codigoSAP.substring(codigoSAP.length() - 8, codigoSAP.length());
		String resultado = codigoSap8Dig.substring(0, 3) + "-" + codigoSap8Dig.substring(3, codigoSap8Dig.length());

		return resultado;
	}

	private void cambiarAEstadoAprobado(NotificacionDiariaBean notificacion) throws LogicaException {
		try {
			Long codigoEstadoAprobado = new Long(
					ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_ESTADONOTIFICACION_APROBADO));
			actualizarEstadoNotificacion(notificacion, codigoEstadoAprobado);
		} catch (LogicaException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(NOTIFICACIONPLANTA_ESTADO_NO_CAMBIADO);
			throw new LogicaException(mensajeError, e);
		}
	}

	/*
	 * Comentado por Jordan
	 */
	public NotificacionDiariaBean obtenerNotificacion(Long codigoLineaNegocio, Date fechaRegistro) throws LogicaException {
		if (codigoLineaNegocio == null) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_LINEANEGOCIO_REQUERIDO);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}

		if (fechaRegistro == null) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(REPORTEECS_FECHA_REGISTRO_REQUERIDA);
			logger.error(mensajeError);
			throw new LogicaException(mensajeError);
		}

		NotificacionDiariaBean notificaciondiaria = null;

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			notificaciondiaria = beanFactory.transformarNotificacionDiaria(NotificacionDiariaQuerier.obtenerNotificacion(
					codigoLineaNegocio, fechaRegistro));
			return notificaciondiaria;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public boolean desaprobarNotificacion(Long lineaNegocio, Date fecha) {

		Transaction tx = null;
		Session session = null;
		boolean condicion = false;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			// Necesito Traer los FK de los TablaKardex
			List<Tablakardex> tablakardexes = TablaKardexQuerier.obtenerTablaKardexPorPeriodoParteDiario(lineaNegocio, fecha);
			List<Long> codigos = obtenerCodigosKardex(tablakardexes);

			// Eliminar Consumo Componente por Codigo TablaKarkex

			// Eliminar ConsumoComponente
			ConsumoComponenteQuerier.eliminarPorCodigosTablaKardex(codigos);

			// Eliminar KardexMedioAlmacenamiento
			TablaKardexQuerier.eliminarKardexAlmacenamientoPorCodigosTablaKardex(codigos);

			// Eliminar valorpromvariablecalidad
			ValorPromVariableCalidadQuerier.eliminarPorCodigoTablaKardex(codigos);

			// Eliminar Tabla Kardex
			// FABIAN FALTA ESTO DE ELIMINAR PERO ANTES TIENES QUE ACTUALIZAR
			// LOS SALDO DE PRODUCCION DIARIA

			ProduccionDiariaQuerier.actualizarProduccionDiarioConValoresKardex(tablakardexes);
			// ELIMINAR KARDEX
			TablaKardexQuerier.eliminarPorcodigos(codigos);

			// Obtener Codigos ConsumoPuesto de Trabajo

			codigos = ConsumoPuestoTrabajoQuerier.obtenerCodigosPorLineaNegYFecha(lineaNegocio, fecha);

			// Eliminar factorconsumopuestotrabajo
			FactorconsumopuestotrabajoQuerier.eliminarPorCodigodConsumoPuestoTrabajo(codigos);
			// Eliminar factorvariacionpuestotrabajo

			FactorVariacionPuestoTrabajoQuerier.eliminarPorCodigodConsumoPuestoTrabajo(codigos);

			// Eliminar consumopuestotrabajo
			ConsumoPuestoTrabajoQuerier.eliminarPorFechaLineaNeg(codigos);

			// Eliminar Producto Generado
			codigos = ProductoGeneradoQuerier.obtenerCodigosPorLineNegFecha(lineaNegocio, fecha);

			FactorVariacionProduccionPuestoTrabajoQuerier.eliminarPorCodigoProductoGenerado(codigos);

			ProductoGeneradoQuerier.eliminarPorFechaLineaNeg(codigos);

			// Eliminar TablaOperacion
			TablaOperacionQuerier.eliminarPorFechaLineaNeg(lineaNegocio, fecha);

			// Eliminar Movimiento
			MovimientoQuerier.eliminarPorFechaYMovimientoSAP(fecha, true);

			// Elminar DocumentoMateria
			DocumentoMaterialQuerier.eliminarPorFechaYMovimientoSAP(fecha, true);

			// Eliminacion de PARTE DIARIO Y
			if (FechaUtil.obtenerDiaFecha(fecha).equals("01")) {

				ProduccionPuestoTrabajoQuerier.eliminarFechaLineaNegocio(lineaNegocio, fecha);

				ProduccionDiariaQuerier.eliminarSegunFechaLineaNeg(lineaNegocio, fecha);
				ParteDiarioQuerier.eliminarFechaLineaNegocio(lineaNegocio, fecha);

			}

			Notificaciondiaria notificacion = NotificacionDiariaQuerier.obtenerNotificacion(lineaNegocio, fecha);
			Estadonotificacion estadonotificacion = new Estadonotificacion();
			estadonotificacion.setPkCodigoEstadonotificacion(Long.valueOf(1));
			notificacion.setEstadonotificacion(estadonotificacion);
			notificacion.setUsuarioByFkCodigoUsuarioAprueba(null);
			notificacion.setFechaAprobacionNotificaciondi(null);
			NotificacionDiariaQuerier.update(notificacion);

			tx.commit();
			condicion = true;
		} catch (ParametroInvalidoException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} catch (ElementoExistenteException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} catch (ElementoEliminadoException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);

			if (!condicion) {
				if (session != null) {
					PersistenciaFactory.rollbackTransaction(session.getTransaction());
				}
			}
		}
		return condicion;
	}

	private List<Long> obtenerCodigosKardex(List<Tablakardex> tablakardexes) {
		List<Long> lista = new ArrayList<Long>();
		for (Tablakardex tk : tablakardexes) {
			lista.add(tk.getPkCodigoTablakardex());
		}

		return lista;

	}

	public static void main3(String[] args) {
		NotificacionDiariaLogic n = new NotificacionDiariaLogic();
		UsuarioLogic u = new UsuarioLogic();
		try {
			n.cerrarNotificacion(n.obtenerNotificacionDiaria(1552L), u.obtenerUsuario(11L));
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultadoBeanImpl cerrarNotificacion(NotificacionDiariaBean notificacion, UsuarioBean usuario) throws LogicaException {

		ResultadoBeanImpl estadoProcesoRfc = new ResultadoBeanImpl();

		if (notificacion == null || notificacion.getCodigo() == null) {
			throw new LogicaException(ManejadorPropiedades.obtenerPropiedadPorClave(NOTIFICACION_NO_SELECCIONADA_CERRAR));
		}

		Long codigoNotif = notificacion.getCodigo();
		usuarioSesion = usuario;

		notificacion = this.obtenerNotificacionDiaria(codigoNotif);

		// SI la notificacion es nula
		if (notificacion == null) {
			throw new LogicaException("No Se encontro Notificacion");
		}

		// Si el estado es cerrado
		if (notificacion != null && notificacion.getEstadoNotificacion().getNombreEstadoNotificacion().equals(ESTADO_CERRADO)) {
			mensajeError = MessageFormat.format(ERROR_NOTIFICACION_CERRADO,
					new Object[] { notificacion.getFechaNotificacionDiaria() });
			throw new LogicaException(mensajeError);
		}

		// validar que la primera notificacion este cerrada
		NotificacionDiariaBean notificacionBean = this.obtenerPrimeraNotificacionDiariaAprobada(notificacion);
		if (notificacionBean != null) {
			if (!FechaUtil.comparaFechasSoloDiaMesYAnio(notificacion.getFechaNotificacionDiaria(),
					notificacionBean.getFechaNotificacionDiaria())) {
				if (notificacionBean.getEstadoNotificacion().getNombreEstadoNotificacion().equals(ESTADO_APROBADO)) {
					mensajeError = MessageFormat.format(ERROR_NOTIFICACION_CIERRE,
							new Object[] { notificacionBean.getFechaNotificacionDiaria() });
					throw new LogicaException(mensajeError);
				}
			}

		}

		// si la fecha de notificacion es mayor a la ultima fecha de cierre
		notificacionBean = this.obtenerUltimaNotificacionDiaria(notificacion);

		if (notificacionBean != null
				&& !FechaUtil.comparaFechasSoloDiaMesYAnio(notificacion.getFechaNotificacionDiaria(),
						FechaUtil.obtenerFechaDiaSiguiente(notificacionBean.getFechaNotificacionDiaria()))) {
			mensajeError = MessageFormat.format(ERROR_NOTIFICACION_CIERRE,
					new Object[] { FechaUtil.obtenerFechaDiaSiguiente(notificacionBean.getFechaNotificacionDiaria()) });
			throw new LogicaException(mensajeError);
		}

		Transaction tx = null;
		Session session = null;

		Integer annio;
		Long codigoEstadoCerrado = Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_ESTADONOTIFICACION_CERRADO));
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Notificaciondiaria notificacionDiaria = NotificacionDiariaQuerier.getById(codigoNotif);
			if (activacionExportarConsumoMateriaPrimas()) {

				estadoProcesoRfc = exportarConsumoDeMateriasPrimas(notificacionDiaria.getFechaNotificaciondiaria(), codigoNotif);

				if (estadoProcesoRfc.getExitoOperacion()) {
					// Cambio de Estado para la notificacion
					Estadonotificacion estadoCerrado = EstadoNotificacionQuerier.getById(codigoEstadoCerrado);
					notificacionDiaria.setEstadonotificacion(estadoCerrado);
					notificacionDiaria.setUsuarioByFkCodigoUsuarioCierra(beanFactory.transformarUsuarioBean(usuario));
					NotificacionDiariaQuerier.update(notificacionDiaria);

					// Cerrar parte diario al ultima notificacion
					Short mes = (short) FechaUtil.obtenerMesFecha(notificacion.getFechaNotificacionDiaria());
					annio = FechaUtil.obtenerAnnioFecha(notificacion.getFechaNotificacionDiaria());

					if (notificacion != null
							&& FechaUtil.comparaFechasSoloDiaMesYAnio(notificacion.getFechaNotificacionDiaria(), FechaUtil
									.getUltimoDiaMes((short) (mes - 1), annio).getTime())) {
						Estadopartediario estadoPd = EstadoParteDiarioQuerier.getById(Long
								.valueOf(CODIGO_ESTADO_PARTEDIARIO_CERRADO));
						Partediario parteDiario = ParteDiarioQuerier.getByPeriodoContableYLineaNegocio(mes, annio, notificacion
								.getLineaNegocio().getCodigo());

						parteDiario.setEstadopartediario(estadoPd);
						ParteDiarioQuerier.update(parteDiario);
					}
				}

			}

			tx.commit();

		} catch (ElementoNoEncontradoException e) {
			// TODO Manejo de Mensaje Excepciones
			e.printStackTrace();
		} catch (ParametroInvalidoException e) {

			e.printStackTrace();
		} catch (ElementoExistenteException e) {

			e.printStackTrace();
		} catch (ElementoEliminadoException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			if (session != null)
				PersistenciaFactory.closeSession(session);

		}

		
		return estadoProcesoRfc;

	}

	private Boolean activacionExportarConsumoMateriaPrimas() {
		Boolean activo = Boolean.FALSE;
		ParametroSistema parametro = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.ENVIO_SAP_CONSUMO_MATERIA_PRIMA);

		if (parametro == null) {
			logger.error("No se encontro parametro del sistema activacionExportarConsumoMateriaPrimas ENVIO_SAP_CONSUMO_MATERIA_PRIMA ");
			return activo;
		}

		if (parametro.getValorParametro().equals("1")) {
			activo = Boolean.TRUE;
		}

		logger.debug("Envio de consumos a SAP " + activo);
		return activo;
	}

	public boolean reprocesarNotificacion(NotificacionDiariaBean notificacionBean, UsuarioBean usuario) throws LogicaException {
		Transaction tx = null;
		Session session = null;
		boolean condicion = false;
		try {

			usuarioSesion = usuario;

			// Si la notificacion se encuentra con estado Cerrado , no se puede
			// Reprocesar CERRADAS solo Aprobadas
			if (notificacionBean.getEstadoNotificacion().getNombreEstadoNotificacion().equals(ESTADO_CERRADO)) {
				mensajeError = MessageFormat.format(ERROR_NOTIFICACION_CERRADO_REPROCESAR,
						new Object[] { notificacionBean.getFechaNotificacionDiaria() });
				throw new LogicaException(mensajeError);
			}
			// validar la fecha de la notificacion para cerrar -
			// obtenerUltimaNotificacionDiariaAprobada
			NotificacionDiariaBean notificacionBeanAprobado = this.obtenerUltimaNotificacionDiariaAprobada(notificacionBean);

			if (!FechaUtil.comparaFechasSoloDiaMesYAnio(notificacionBean.getFechaNotificacionDiaria(),
					notificacionBeanAprobado.getFechaNotificacionDiaria())) {
				mensajeError = MessageFormat.format(ERROR_NOTIFICACION_REPROCESO,
						new Object[] { notificacionBeanAprobado.getFechaNotificacionDiaria() });
				throw new LogicaException(mensajeError);

			}
			// No reprocesar si el Parte Tecnico esta con estado generado
			// si ajuste produccion trae != null mensaje de error

			Short mes = Short.valueOf(FechaUtil.obtenerMesFecha(notificacionBean.getFechaNotificacionDiaria()) + "");
			Integer annio = FechaUtil.obtenerAnnioFecha(notificacionBean.getFechaNotificacionDiaria());
			Long lineaNegocio = notificacionBeanAprobado.getLineaNegocio().getCodigo();

			Ajusteproduccion ajuste = AjusteProduccionQuerier.getByLineaNegocioMesYAno(mes, annio, lineaNegocio);

			if (ajuste != null) {
				if (!ajuste.getEstadoajusteproduccion().getNombreEstadoajusteproduccion().equals("Inicial")) {
					mensajeError = ERROR_REPROCESAR_PTGENERADO;
					throw new LogicaException(mensajeError);
				}
			}

			Date fecha = notificacionBeanAprobado.getFechaNotificacionDiaria();

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			// Necesito Traer los FK de los TablaKardex
			List<Tablakardex> tablakardexes = TablaKardexQuerier.obtenerTablaKardexPorPeriodoParteDiario(lineaNegocio, fecha);
			List<Long> codigos = obtenerCodigosKardex(tablakardexes);

			if (codigos != null && codigos.size() > 0) {

				// Eliminar Consumo Componente por Codigo TablaKarkex

				// Eliminar ConsumoComponente
				ConsumoComponenteQuerier.eliminarPorCodigosTablaKardex(codigos);

				// Eliminar KardexMedioAlmacenamiento
				TablaKardexQuerier.eliminarKardexAlmacenamientoPorCodigosTablaKardex(codigos);

				// Eliminar valorpromvariablecalidad
				ValorPromVariableCalidadQuerier.eliminarPorCodigoTablaKardex(codigos);

				// Eliminar Tabla Kardex
				// FABIAN FALTA ESTO DE ELIMINAR PERO ANTES TIENES QUE
				// ACTUALIZAR
				// LOS SALDO DE PRODUCCION DIARIA

				ProduccionDiariaQuerier.actualizarProduccionDiarioConValoresKardex(tablakardexes);
				// ELIMINAR KARDEX
				TablaKardexQuerier.eliminarPorcodigos(codigos);
			}
			// Obtener Codigos ConsumoPuesto de Trabajo

			codigos = ConsumoPuestoTrabajoQuerier.obtenerCodigosPorLineaNegYFecha(lineaNegocio, fecha);

			if (codigos != null && codigos.size() > 0) {

				// Eliminar factorconsumopuestotrabajo
				FactorconsumopuestotrabajoQuerier.eliminarPorCodigodConsumoPuestoTrabajo(codigos);
				// Eliminar factorvariacionpuestotrabajo

				FactorVariacionPuestoTrabajoQuerier.eliminarPorCodigodConsumoPuestoTrabajo(codigos);

				// Eliminar consumopuestotrabajo
				ConsumoPuestoTrabajoQuerier.eliminarPorFechaLineaNeg(codigos);
			}
			// Eliminar Producto Generado
			codigos = ProductoGeneradoQuerier.obtenerCodigosPorLineNegFecha(lineaNegocio, fecha);

			if (codigos != null && codigos.size() > 0) {
				FactorVariacionProduccionPuestoTrabajoQuerier.eliminarPorCodigoProductoGenerado(codigos);

				ProductoGeneradoQuerier.eliminarPorFechaLineaNeg(codigos);
			}
			// Eliminar TablaOperacion
			TablaOperacionQuerier.eliminarPorFechaLineaNeg(lineaNegocio, fecha);

			// Eliminar Movimiento
			MovimientoQuerier.eliminarPorFechaYMovimientoSAP(fecha, true);

			// Elminar DocumentoMateria
			DocumentoMaterialQuerier.eliminarPorFechaYMovimientoSAP(fecha, true);

			// Eliminacion de PARTE DIARIO Y
			if (FechaUtil.obtenerDiaFecha(fecha).equals("01")) {

				ProduccionPuestoTrabajoQuerier.eliminarFechaLineaNegocio(lineaNegocio, fecha);

				ProduccionDiariaQuerier.eliminarSegunFechaLineaNeg(lineaNegocio, fecha);
				ParteDiarioQuerier.eliminarFechaLineaNegocio(lineaNegocio, fecha);

			}

			Notificaciondiaria notificacion = NotificacionDiariaQuerier.obtenerNotificacion(lineaNegocio, fecha);
			Estadonotificacion estadonotificacion = new Estadonotificacion();
			estadonotificacion.setPkCodigoEstadonotificacion(Long.valueOf(1));
			notificacion.setEstadonotificacion(estadonotificacion);
			notificacion.setUsuarioByFkCodigoUsuarioAprueba(null);
			notificacion.setFechaAprobacionNotificaciondi(null);
			NotificacionDiariaQuerier.update(notificacion);

			tx.commit();
			condicion = true;
		} catch (ParametroInvalidoException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} catch (ElementoExistenteException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} catch (ElementoEliminadoException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
			if (session != null) {
				PersistenciaFactory.rollbackTransaction(session.getTransaction());
			}
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);

			if (!condicion) {
				if (session != null) {
					PersistenciaFactory.rollbackTransaction(session.getTransaction());
				}
			}
		}

		
		return condicion;
	}

	public boolean abrirNotificacionDiaria(Long codigo, UsuarioBean usuario) throws LogicaException {
		Transaction tx = null;
		Session session = null;
		Notificaciondiaria notificacion;
		Boolean condicion = Boolean.FALSE;
		Long ESTADO_ABIERTO = 3L;
		try {
			usuarioSesion = usuario;
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			notificacion = NotificacionDiariaQuerier.getById(codigo);
			Estadonotificacion estadonotificacion = new Estadonotificacion();
			estadonotificacion.setPkCodigoEstadonotificacion(ESTADO_ABIERTO);
			notificacion.setEstadonotificacion(estadonotificacion);

			NotificacionDiariaQuerier.update(notificacion);
			tx.commit();
			condicion = Boolean.TRUE;
		} catch (ElementoNoEncontradoException e) {
			mensajeError = e.getMensaje();
			throw new LogicaException(mensajeError, e);
		} catch (ParametroInvalidoException e) {
			mensajeError = e.getMensaje();
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			mensajeError = e.getMensaje();
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = e.getMensaje();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);

			if (!condicion) {
				if (session != null) {
					PersistenciaFactory.rollbackTransaction(session.getTransaction());
				}
			}
		}

		
		return condicion;
	}



}