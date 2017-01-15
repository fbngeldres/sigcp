package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.servidor;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import pe.com.pacasmayo.sgcp.bean.HoraBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.ColumnaReporteLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.DatoReporteLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoNotificacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.HoraLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionCambioProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionOperacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.OrdenProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.HoraLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.EstadoNotificacionLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionCambioProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionDiariaLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionOperacionLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.reporteECS.ColumnaReporteLogic;
import pe.com.pacasmayo.sgcp.logica.reporteECS.DatoReporteLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnaplantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadonotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;
import pe.com.pacasmayo.sgcp.persistencia.querier.ColumnaPlantillaProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HoraQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PlantillaProductoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificacionProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificaciondiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.NotificacionService;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionServiceImpl.java
 * Modificado: Aug 4, 2010 10:20:55 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class NotificacionServiceImpl extends RemoteServiceServlet implements NotificacionService, ConstantesAplicacionAction {

	private static final long serialVersionUID = -1658004157415169148L;

	// Fachadas
	private ColumnaReporteLogicFacade columnsReporteFacade = new ColumnaReporteLogic();
	private DatoReporteLogicFacade datoReporteFacade = new DatoReporteLogic();
	private NotificacionDiariaLogicFacade notificacionDiariaFacade = new NotificacionDiariaLogic();

	private EstadoNotificacionLogicFacade estadoNotificacionFacade = new EstadoNotificacionLogic();
	private NotificacionOperacionLogicFacade notificacionOperacionFacade = new NotificacionOperacionLogic();
	private NotificacionProduccionLogicFacade notificacionProduccionFacade = new NotificacionProduccionLogic();
	private NotificacionCambioProduccionLogicFacade notificacionCambioProduccionFacade = new NotificacionCambioProduccionLogic();
	private OrdenProduccionLogicFacade ordenFacade = new OrdenProduccionLogic();
	private HoraLogicFacade horaLogicFacade = new HoraLogic();

	private Integer CAMBIO_PRODUCCION_OK = new Integer(1);
	private Integer ERROR_NO_EXISTE_NOTIF_DIA_SIG = new Integer(2);
	private Integer ERROR_ORDEN_PROD_DISTINTA = new Integer(3);

	/** Logger Instance */
	protected static Logger logger = Logger.getLogger(NotificacionServiceImpl.class);

	private HttpSession getSession() {
		return this.getThreadLocalRequest().getSession();
	}

	/**
	 * Metodo que retorna el usuario actual
	 * 
	 * @return
	 */
	protected UsuarioBean getUsuarioSession() {
		HttpSession sesion = getSession();
		return (UsuarioBean) sesion.getAttribute(USUARIO_SESION);
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerColumnasReporte(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ColumnareporteDTO> obtenerColumnasReporte(Long codigoPuestoTrabajo, Long codigoProceso, String estadoColumna,
			String estadoPlantilla) throws ServicioGWTGlobalException {
		List<ColumnareporteDTO> columnas = null;

		logger.debug("iniciando metodo: obtenerColumnasReporte");

		try {
			columnas = columnsReporteFacade
					.obtenerColumnasDTO(codigoPuestoTrabajo, codigoProceso, estadoColumna, estadoPlantilla);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}
		return columnas;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerDatosReporte(java.util.Date, java.lang.String,
	 * java.lang.String, java.lang.Long)
	 */
	public List<DatoReporteDTO> obtenerDatosReporte(Date fecha, String nombre, String tipoVariable, Long codigoProceso)
			throws ServicioGWTGlobalException {

		List<DatoReporteDTO> datosReporte = null;

		try {
			String tipoVarProduccion = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesGWT.TIPO_VARIABLE_PRODUCCION);

			if (tipoVariable.equals(tipoVarProduccion)) {
				String patroFechaArchivoVP = ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesGWT.PATRON_FECHA_ARCHIVO_VP);

				SimpleDateFormat dateFormat = new SimpleDateFormat(patroFechaArchivoVP);

				String patronNombreArchivoVP = ManejadorPropiedades
						.obtenerPropiedadPorClave(ConstantesGWT.PATRON_NOMBRE_ARCHIVO_VP);

				String nombreArchivo = MessageFormat.format(patronNombreArchivoVP, new Object[] { dateFormat.format(fecha),
						nombre.replace("%", "") });

				datosReporte = datoReporteFacade
						.cargarDatosVariablesPorHoraDTO(fecha, nombreArchivo, tipoVariable, codigoProceso);
			} else {
				datosReporte = datoReporteFacade.cargarDatosVariablesPorHoraDTO(fecha, nombre, tipoVariable, codigoProceso);
			}
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}

		if (datosReporte != null) {
			Collections.sort(datosReporte, new Comparator<DatoReporteDTO>() {
				public int compare(DatoReporteDTO o1, DatoReporteDTO o2) {
					return o1.getHora() - o2.getHora();
				}
			});

			ArrayList<DatoReporteDTO> datosOrdenados = new ArrayList<DatoReporteDTO>();
			ArrayList<DatoReporteDTO> primeros = new ArrayList<DatoReporteDTO>();
			for (DatoReporteDTO drDTO : datosReporte) {
				if (drDTO.getHora() >= 1 && drDTO.getHora() <= 6) {
					primeros.add(drDTO);
				} else {
					datosOrdenados.add(drDTO);
				}
			}
			datosOrdenados.addAll(primeros);
			datosReporte = datosOrdenados;

		}

		return datosReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#registrarNotificacionDiaria(java.lang.Long,
	 * java.lang.Long, java.util.Date)
	 */
	public Boolean registrarNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoPuestoTrabajo) throws ServicioGWTGlobalException {

		try {
			NotificaciondiariaDTO notificacionDiaria = obtenerNotificacionDiaria(codigoLineaNegocio, codigoTableroControl,
					fechaRegistro);
			UsuarioBean usuario = getUsuarioSession();
			if (notificacionDiaria == null) {

				Estadonotificacion estadonotificacion = estadoNotificacionFacade
						.obtenerEstadoNotificacionPorNombreDO(NOMBRE_ESTADO_NOTIFICACION);

				Long codigoEstadoNotificaicon = estadonotificacion.getPkCodigoEstadonotificacion();

				notificacionDiariaFacade.insertarNotificacionPlanta(usuario.getCodigo(), codigoLineaNegocio,
						codigoTableroControl, codigoEstadoNotificaicon, fechaRegistro,usuario);
			} else {

				notificacionDiariaFacade.eliminarNotificacionesProduccion(codigoLineaNegocio, codigoTableroControl,
						fechaRegistro, notificacionDiaria.getPkCodigoNotificaciondiaria(), codigoPuestoTrabajo, usuario);
			}
			return true;
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#registrarNotificacionesOperacion(java.util.List,
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.util.Date)
	 */
	public Boolean registrarNotificacionesOperacion(List<DatoReporteDTO> datos, Long codigoRegistroReporteECS,
			Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro) throws ServicioGWTGlobalException {

		for (DatoReporteDTO datoreporteDTO : datos) {
			Long codigoHora = datoreporteDTO.getPkCodigoHora();

			try {
				notificacionOperacionFacade.insertarNotificacionOperacion(codigoRegistroReporteECS, codigoNotificacionDiaria,
						codigoHora, codigoPuestoTrabajo, fechaRegistro);
			} catch (LogicaException e) {
				throw new ServicioGWTGlobalException(e.getMessage(), e);
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#registrarNotificacionesProduccion(java.util.List,
	 * java.util.List, java.lang.Long, java.lang.Long, java.lang.Long,
	 * java.util.Date)
	 */

	public Boolean registrarNotificacionesProduccion(List<DatoReporteDTO> datos, List<ColumnareporteDTO> columnas,
			Long codigoRegistroReporteECS, Long codigoNotificacionDiaria, Long codigoPuestoTrabajo, Date fechaRegistro)
			throws ServicioGWTGlobalException {
		for (DatoReporteDTO datoreporteDTO : datos) {
			Long codigoHora = datoreporteDTO.getPkCodigoHora();
			Integer codigoOrden = datoreporteDTO.getCodigoOrden();
			Integer codigoMedio = datoreporteDTO.getCodigoSilo();
			String obs = datoreporteDTO.getObservaciones();
			Boolean cambioProduccionNormal = datoreporteDTO.getCambioProduccionNormal();
			Boolean cambioProduccionLavado = datoreporteDTO.getCambioProduccionLavado();
			String cambioProduccionHora = datoreporteDTO.getCambioProduccionHora();

			Double energia = datoreporteDTO.getVariable13Datoreporte();
			Double agua = datoreporteDTO.getVariable12Datoreporte();
			Double horaEcs = datoreporteDTO.getVariable15Datoreporte();

			try {
				Integer codigoPlantilla = datoreporteDTO.getCodigoPlantilla();

				Plantillaproducto plantillaproducto = new Plantillaproducto();
				plantillaproducto.setPkCodigoPlantillaproducto(codigoPlantilla.longValue());

				Notificacionproduccion notificacionProduccion = notificacionProduccionFacade.insertarNotificacionProduccion(
						codigoRegistroReporteECS, codigoNotificacionDiaria, codigoHora, codigoPuestoTrabajo, fechaRegistro,
						codigoOrden, codigoMedio, obs, cambioProduccionNormal, cambioProduccionLavado, cambioProduccionHora,
						energia, agua, horaEcs, plantillaproducto);

				List<Columnaplantillaproducto> listaColumnaPlantillaProducto = ColumnaPlantillaProductoQuerier
						.obtenerColumnasPorCodigoPlantillaProducto(codigoPlantilla.longValue());

				notificacionProduccionFacade.crearComponentesNotificacion(notificacionProduccion, datoreporteDTO,
						listaColumnaPlantillaProducto);

				if (datoreporteDTO.getCambioProduccionNormal()) {
					notificacionCambioProduccionFacade.modificarNotificacionProduccion(notificacionProduccion, datoreporteDTO);
				}
			} catch (LogicaException e) {
				e.printStackTrace();
				throw new ServicioGWTGlobalException(e.getMessage(), e);
			} catch (SesionVencidaException e) {
				e.printStackTrace();
				throw new ServicioGWTGlobalException(e.getMessage(), e);
			} catch (EntornoEjecucionException e) {
				e.printStackTrace();
				throw new ServicioGWTGlobalException(e.getMessage(), e);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServicioGWTGlobalException(e.getMessage(), e);
			}
		}
		
	
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerNotificacionDiaria(java.lang.Long,
	 * java.lang.Long, java.util.Date)
	 */
	public NotificaciondiariaDTO obtenerNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro)
			throws ServicioGWTGlobalException {

		NotificaciondiariaDTO notificacionDiaria = null;

		try {
			notificacionDiaria = notificacionDiariaFacade.obtenerNotificacionDiariaDTO(codigoLineaNegocio, codigoTableroControl,
					fechaRegistro);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}

		return notificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#verificarSiExistenRegistrosNotifProd(java.lang.Long,
	 * java.lang.Long, java.util.Date, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public Boolean verificarSiExistenRegistrosNotifProd(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoPuestoTranajo) throws ServicioGWTGlobalException {

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			Notificaciondiaria notificacionDiaria = notificacionDiariaFacade.obtenerNotificacionDiariaDO(codigoLineaNegocio,
					codigoTableroControl, fechaRegistro);

			if (notificacionDiaria == null) {
				return new Boolean(false);
			}

			Set<Notificacionproduccion> notificacionproduccions = notificacionDiaria.getNotificacionproduccions();

			if (notificacionproduccions == null || notificacionproduccions.size() == 0) {
				return new Boolean(false);
			}

			EqualPredicate nameEqlPredicate = new EqualPredicate(codigoPuestoTranajo);
			BeanPredicate beanPredicate = new BeanPredicate(CODIGO_PUESTOTRABAJO, nameEqlPredicate);

			Collection listaPorPuestoTrabajo = CollectionUtils.select(notificacionproduccions, beanPredicate);

			if (listaPorPuestoTrabajo == null || listaPorPuestoTrabajo.size() == 0) {
				return new Boolean(false);
			}

			return new Boolean(true);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerFechaDiaAnterior(java.util.Date)
	 */
	public Date obtenerFechaDiaAnterior(Date fechaActual) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return calendar.getTime();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#getOrdenesDTOByMesYProceso(java.util.Date,
	 * java.lang.Long)
	 */
	public List<OrdenProduccionDTO> getOrdenesByMesPlantillaLiberadas(Date fecha, Long codigoPlantillaReporte)
			throws ServicioGWTGlobalException {
		List<OrdenProduccionDTO> listaOrdenes = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);
			Integer mes = cal.get(Calendar.MONTH) + 1;
			Integer annio = cal.get(Calendar.YEAR);
			listaOrdenes = ordenFacade.getOrdenesByMesPlantillaLiberadas(mes, annio, codigoPlantillaReporte);

			if (listaOrdenes == null) {
				listaOrdenes = ordenFacade.getOrdenesByMesPlantillaHistoricas(mes, annio, codigoPlantillaReporte);
			} else if (listaOrdenes.size() == 0) {
				listaOrdenes = ordenFacade.getOrdenesByMesPlantillaHistoricas(mes, annio, codigoPlantillaReporte);
			}
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}
		return listaOrdenes;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerNotificacionesProduccion(java.lang.Long)
	 */
	public List<NotificacionProduccionDTO> obtenerNotificacionesProduccion(Long codigoNotificacionDiaria)
			throws ServicioGWTGlobalException {

		List<NotificacionProduccionDTO> notificacionesDTO = new ArrayList<NotificacionProduccionDTO>();
		try {
			List<Notificacionproduccion> notificacionesProduccion = notificacionProduccionFacade
					.obtenerNotificacionesProduccionByCodigoNotificacionDiaria(codigoNotificacionDiaria);
			for (Iterator<Notificacionproduccion> iterator = notificacionesProduccion.iterator(); iterator.hasNext();) {
				Notificacionproduccion notificacionproduccion = iterator.next();

				NotificacionProduccionDTO dto = new NotificacionProduccionDTO();
				dto.setPkCodigoNotificacionproduccio(notificacionproduccion.getPkCodigoNotificacionproduccio());

				notificacionesDTO.add(dto);
			}

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMessage(), e);
		}
		return notificacionesDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerDatosBD(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date)
	 */
	public List<DatoReporteDTO> obtenerDatosBD(Long codigoLineaNegocio, Long codigoTableroControl, Long codigoProceso,
			Long codigoPuestoTrabajo, Date fechaReg) throws ServicioGWTGlobalException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();

			List<ColumnareporteDTO> columnasReporte = obtenerColumnasReportePorNotifDAO(codigoLineaNegocio, codigoTableroControl,
					fechaReg, codigoPuestoTrabajo);

			Notificaciondiaria notificacionDiaria = notificacionDiariaFacade.obtenerNotificacionDiariaDO(codigoLineaNegocio,
					 codigoTableroControl, fechaReg);

			 
			List<DatoReporteDTO> datos = crearListaDatoreporte(columnasReporte, notificacionDiaria, codigoPuestoTrabajo);
			
			return datos;
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
			
		}
	}

	/**
	 * Crea la lista de datos reporte, que son usado para crear la tala de
	 * variable de produccion en la gui
	 * 
	 * @param columnasReporte
	 * @param notificacionDiaria
	 * @param puestoTrabajo
	 * @return List<DatoReporteDTO>
	 * @throws LogicaException si la busqueda de los objetos
	 *             Columnaplantillaproducto falla
	 */
	@SuppressWarnings("unchecked")
	private List<DatoReporteDTO> crearListaDatoreporte(List<ColumnareporteDTO> columnasReporte,
			Notificaciondiaria notificacionDiaria, Long codigoPuestoTrabajo) throws LogicaException {

		Set<Notificacionproduccion> notificacionProduccions = notificacionDiaria.getNotificacionproduccions();
		List<DatoReporteDTO> listaDatos = new ArrayList<DatoReporteDTO>();

		Long codigoEstadonotificacion = notificacionDiaria.getEstadonotificacion().getPkCodigoEstadonotificacion();
		Boolean notificacionAprobada = codigoEstadonotificacion.longValue() == 3L; //

		String propiedadCodigoComponente = CODIGO_COMPONENTE;

		for (Iterator ittProd = notificacionProduccions.iterator(); ittProd.hasNext();) {
			Notificacionproduccion notificacionProduccion = (Notificacionproduccion) ittProd.next();

			if (notificacionProduccion.getPuestotrabajo().getPkCodigoPuestotrabajo().longValue() == codigoPuestoTrabajo) {

				Ordenproduccion ordenproduccion = notificacionProduccion.getOrdenproduccion();
				Producto producto = ordenproduccion.getProduccion().getProducto();
				Long codigoProducto = producto.getPkCodigoProducto();
				Medioalmacenamiento medioalmacenamiento = notificacionProduccion.getMedioalmacenamiento();

				Long codigoPlantilla = notificacionProduccion.getPlantillaproducto().getPkCodigoPlantillaproducto();
				List<Columnaplantillaproducto> listaColumnaPlantillaProducto;
				try {
					listaColumnaPlantillaProducto = ColumnaPlantillaProductoQuerier
							.obtenerColumnasPorCodigoPlantillaProducto(codigoPlantilla.longValue());
				} catch (SesionVencidaException e) {
					throw new LogicaException(e.getMensaje(), e);
				} catch (EntornoEjecucionException e) {
					throw new LogicaException(e.getMensaje(), e);
				}

				DatoReporteDTO datoReporte = new DatoReporteDTO();
				datoReporte.setNotificacionAprobada(notificacionAprobada);
				datoReporte.setEstadoNotificaciondiaria(notificacionProduccion.getNotificaciondiaria().getEstadonotificacion()
						.getNombreEstadonotificacion());
				setValoresFijosADatoReporte(notificacionProduccion, ordenproduccion, producto, codigoProducto,
						medioalmacenamiento, datoReporte);
				for (Iterator ittCompNotif = notificacionProduccion.getComponentenotificacions().iterator(); ittCompNotif
						.hasNext();) {

					Componentenotificacion componentenotificacion = (Componentenotificacion) ittCompNotif.next();
					Componente componente = componentenotificacion.getComponente();

					EqualPredicate nameEqlPredicate = new EqualPredicate(componente.getPkCodigoComponente());

					BeanPredicate beanPredicate = new BeanPredicate(propiedadCodigoComponente, nameEqlPredicate);

					Columnaplantillaproducto columnaplantillaproducto = (Columnaplantillaproducto) CollectionUtils.find(
							listaColumnaPlantillaProducto, beanPredicate);
					if (columnaplantillaproducto != null) {
						Columnareporte columnareporte = columnaplantillaproducto.getColumnareporte();

						double valor = componentenotificacion.getValorComponentenotificacion();

						Short posicion = columnareporte.getPosicionColumnareporte();
						setValorVariable(posicion, valor, datoReporte);
					}
				}
				listaDatos.add(datoReporte);
			}
		}

		Collections.sort(listaDatos, new Comparator<DatoReporteDTO>() {
			public int compare(DatoReporteDTO o1, DatoReporteDTO o2) {
				return o1.getHora() - o2.getHora();
			}
		});

		ArrayList<DatoReporteDTO> datosOrdenados = new ArrayList<DatoReporteDTO>();
		ArrayList<DatoReporteDTO> primeros = new ArrayList<DatoReporteDTO>();
		for (DatoReporteDTO drDTO : listaDatos) {
			if (drDTO.getHora() >= 1 && drDTO.getHora() <= 6) {
				primeros.add(drDTO);
			} else {
				datosOrdenados.add(drDTO);
			}
		}
		datosOrdenados.addAll(primeros);
		listaDatos = datosOrdenados;

		return listaDatos;
	}

	/**
	 * Asina los valores fijos a un dato reporte
	 * 
	 * @param notificacionProduccion
	 * @param ordenproduccion
	 * @param producto
	 * @param codigoProducto
	 * @param medioalmacenamiento
	 * @param datoReporte
	 * @return DatoReporteDTO
	 */
	private void setValoresFijosADatoReporte(Notificacionproduccion notificacionProduccion, Ordenproduccion ordenproduccion,
			Producto producto, Long codigoProducto, Medioalmacenamiento medioalmacenamiento, DatoReporteDTO datoReporte) {

		datoReporte.setPkCodigoHora(notificacionProduccion.getHora().getPkCodigoHora());
		Registroreporteecs registroreporteecs = notificacionProduccion.getRegistroreporteecs();
		if (registroreporteecs != null) {
			datoReporte.setPkRegistroReporte(registroreporteecs.getPkCodigoRegistroreporteecs());
		}
		datoReporte.setValorHora(notificacionProduccion.getHora().getHoraHora());
		datoReporte.setPkCodigoHora(notificacionProduccion.getHora().getPkCodigoHora());
		datoReporte.setCodigoTurno(notificacionProduccion.getHora().getTurno().getPkCodigoTurno());
		datoReporte.setCodigoOrden(ordenproduccion.getPkCodigoOrdenproduccion().intValue());
		datoReporte.setCodigoLineaNegocio(ordenproduccion.getProduccion().getProceso().getLineanegocio()
				.getPkCodigoLineanegocio());
		datoReporte.setNombreLineaNegocio(ordenproduccion.getProduccion().getProceso().getLineanegocio().getNombreLineanegocio());
		datoReporte.setCodigoNotificacionPlanta(notificacionProduccion.getPkCodigoNotificacionproduccio());
		datoReporte.setCodigoProducto(codigoProducto.intValue());

		if (medioalmacenamiento != null) {
			datoReporte.setCodigoSilo(medioalmacenamiento.getPkCodigoMedioalmacenamiento().intValue());
			datoReporte.setNombreSilo(medioalmacenamiento.getNombreMedioalmacenamiento());
		}

		datoReporte.setObservaciones(notificacionProduccion.getObservacionNotificacionproducc());
		datoReporte.setNumeroOrden(producto.getNombreProducto() + " "
				+ ordenproduccion.getProduccion().getProceso().getSiglasProceso());
		datoReporte.setCambioProduccionLavado(notificacionProduccion.getProduccionlavadoNotificacionpr());
		datoReporte.setCambioProduccionNormal(notificacionProduccion.getCambioproduccionNotificacionpr());
		datoReporte.setCambioProduccionHora(notificacionProduccion.getHoraCambioproduccionNotificac());

		datoReporte.setVariable13Datoreporte(notificacionProduccion.getEnergiaEcsNotificacionproduccion());
		datoReporte.setVariable12Datoreporte(notificacionProduccion.getAguaEcsNotificacionproduccion());
		datoReporte.setVariable15Datoreporte(notificacionProduccion.getHoraEcsNotificacionproduccion());

		Plantillaproducto plantillaproducto = notificacionProduccion.getPlantillaproducto();
		Date fechaPlantillaproducto = plantillaproducto.getFechaPlantillaproducto();
		String fechaStr = FechaUtil.convertirDateToString(fechaPlantillaproducto);

		datoReporte.setPlantilla(plantillaproducto.getProducto().getNombreProducto() + " " + fechaStr + " v"
				+ plantillaproducto.getVersionPlantillaproducto());
		datoReporte.setCodigoPlantilla(plantillaproducto.getPkCodigoPlantillaproducto().intValue());
	}

	/**
	 * permite almcenar un valor de una celda del grid dada la posicion de la
	 * columna del reporte
	 * 
	 * @param pos posicion de la columna
	 * @param valor valor a almacenar
	 * @param datoreporte objeto donde se almacenara el valor de la celda
	 */
	public void setValorVariable(Short pos, double valor, DatoReporteDTO datoreporte) {

		switch (pos) {
		case 1:
			datoreporte.setVariable1Datoreporte(valor);
			break;
		case 2:
			datoreporte.setVariable2Datoreporte(valor);
			break;
		case 3:
			datoreporte.setVariable3Datoreporte(valor);
			break;
		case 4:
			datoreporte.setVariable4Datoreporte(valor);
			break;
		case 5:
			datoreporte.setVariable5Datoreporte(valor);
			break;
		case 6:
			datoreporte.setVariable6Datoreporte(valor);
			break;
		case 7:
			datoreporte.setVariable7Datoreporte(valor);
			break;
		case 8:
			datoreporte.setVariable8Datoreporte(valor);
			break;
		case 9:
			datoreporte.setVariable9Datoreporte(valor);
			break;
		case 10:
			datoreporte.setVariable10Datoreporte(valor);
			break;
		case 11:
			datoreporte.setVariable11Datoreporte(valor);
			break;
		case 12:
			datoreporte.setVariable12Datoreporte(valor);
			break;
		case 13:
			datoreporte.setVariable13Datoreporte(valor);
			break;
		case 14:
			datoreporte.setVariable14Datoreporte(valor);
			break;
		case 15:
			datoreporte.setVariable15Datoreporte(valor);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerDatosReporteCargaManual()
	 */
	public List<DatoReporteDTO> obtenerDatosReporteCargaManual() throws ServicioGWTGlobalException {
		try {
			List<HoraBean> obtenerHoras = horaLogicFacade.obtenerHoras();
			List<DatoReporteDTO> datosReporte = new ArrayList<DatoReporteDTO>();
			for (HoraBean horaBean : obtenerHoras) {
				DatoReporteDTO datoReporteDTO = new DatoReporteDTO();
				datoReporteDTO.setPkCodigoHora(horaBean.getCodigo());
				datoReporteDTO.setHora(horaBean.getHora());
				datoReporteDTO.setValorHora(horaBean.getHora());
				datosReporte.add(datoReporteDTO);
			}

			return datosReporte;
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#validarSiExisteNotficacionDiaSiguinte(java.lang.Long,
	 * java.lang.Long, java.util.Date)
	 */
	public Integer validarSiExisteNotficacionDiaSiguinte(Long codigoLineaNegocio, Long codigoPuestoTrabajo,
			Long codigoTableroControl, Date fechaReg, String hora, Long codigoOrdenProduc) throws ServicioGWTGlobalException {

		// Verifica si el cambio de produccion se realizara en la ultima
		// hora del turno. Para luego validar si existe una notificacion
		// registrada para el dia siguiente
		try {
			Short ultimaHora = HoraQuerier.obtenerUltimaHoraDeUltimoTurno();
			if (!hora.equals(ultimaHora.toString())) {
				return CAMBIO_PRODUCCION_OK;
			}

			Date fecha = FechaUtil.obtenerFechaDiaSiguiente(fechaReg);

			Short horaInicioPrimerTurno = HoraQuerier.obtenerHoraInicioPrimerTurno();

			Notificacionproduccion notificacionproduccion = NotificacionProduccionQuerier
					.obtenerSegunCamposNotifDiariaFechaYHora(codigoLineaNegocio, codigoPuestoTrabajo, codigoTableroControl,
							fecha, horaInicioPrimerTurno);

			if (notificacionproduccion == null) {
				return ERROR_NO_EXISTE_NOTIF_DIA_SIG;
			}

			Long codigoOrdenProducDiaSig = notificacionproduccion.getOrdenproduccion().getPkCodigoOrdenproduccion();

			if (codigoOrdenProduc.longValue() != codigoOrdenProducDiaSig.longValue()) {
				return ERROR_ORDEN_PROD_DISTINTA;
			}

			return CAMBIO_PRODUCCION_OK;
		} catch (ElementoNoEncontradoException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente.
	 * NotificacionService#obtenerColumnasReportePorNotif(java.lang.Long,
	 * java.lang.Long, java.util.Date, java.lang.Long)
	 */

	public List<ColumnareporteDTO> obtenerColumnasReportePorNotif(Long codigoLineaNegocio, Long codigoTableroControl,
			Date fechaRegistro, Long codigoPuestoTrabajo) throws ServicioGWTGlobalException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();

			List<ColumnareporteDTO> columnasDTO = obtenerColumnasReportePorNotifDAO(codigoLineaNegocio, codigoTableroControl,
					fechaRegistro, codigoPuestoTrabajo);

			return columnasDTO;

		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException("Ocurrio un error intentando cargar las columnas de la plantilla");
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException("Ocurrio un error intentando cargar las columnas de la plantilla");
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	public List<ColumnareporteDTO> obtenerColumnasReportePorNotifDAO(Long codigoLineaNegocio, Long codigoTableroControl,
			Date fechaRegistro, Long codigoPuestoTrabajo) throws ServicioGWTGlobalException {

		try {

			List<Columnareporte> columnasDO = NotificacionProduccionQuerier.obtenerColsPlantillaReporteSegunDatosNotifYPuesto(
					codigoLineaNegocio, codigoTableroControl, fechaRegistro, codigoPuestoTrabajo);

			Collections.sort(columnasDO, new Comparator<Columnareporte>() {
				public int compare(Columnareporte o1, Columnareporte o2) {
					return o1.getPosicionColumnareporte().compareTo(o2.getPosicionColumnareporte());
				}
			});

			List<ColumnareporteDTO> columnasDTO = new ArrayList<ColumnareporteDTO>();

			for (Columnareporte columnareporte : columnasDO) {
				ColumnareporteDTO columnaDTO = new ColumnareporteDTO();

				columnaDTO.setPkCodigoColumnareporte(columnareporte.getPkCodigoColumnareporte());
				columnaDTO.setPkCodigoEstadocolumnareporte(columnareporte.getEstadocolumnareporte()
						.getPkCodigoEstadocolumnareporte());
				columnaDTO.setPkCodigoPlantillareporte(columnareporte.getPlantillareporte().getPkCodigoPlantillareporte());
				columnaDTO.setNombreColumnareporte(columnareporte.getNombreColumnareporte());
				columnaDTO.setPosicionColumnareporte(columnareporte.getPosicionColumnareporte());

				columnasDTO.add(columnaDTO);
			}
			return columnasDTO;

		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException("Ocurrio un error intentando cargar las columnas de la plantilla");
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException("Ocurrio un error intentando cargar las columnas de la plantilla");
		}
	}

	public boolean validarRegistrosNotificacionDiaria(List<DatoReporteDTO> datosProduccion, List<ColumnareporteDTO> columnas)
			throws ServicioGWTGlobalException {
		Boolean notificadoMp = Boolean.FALSE;
		Boolean notificadoCombustible = Boolean.FALSE;
		ArrayList<Long> codigosPlantilla = new ArrayList<Long>();
		Map<Long, Plantillaproducto> plantillaProductoMap = new HashMap<Long, Plantillaproducto>();
		Plantillaproducto plantillaproducto;
		List<Columnaplantillaproducto> columnasProductos = new ArrayList<Columnaplantillaproducto>();

		try {

			for (DatoReporteDTO datoReporteDTO : datosProduccion) {
				if (!codigosPlantilla.contains(datoReporteDTO.getCodigoPlantilla().longValue())) {
					codigosPlantilla.add(datoReporteDTO.getCodigoPlantilla().longValue());
				}
			}

			List<Plantillaproducto> listaPlantillas = PlantillaProductoQuerier.obtenerPlantillasByCodigos(codigosPlantilla);

			for (Plantillaproducto plantillait : listaPlantillas) {
				plantillaProductoMap.put(plantillait.getPkCodigoPlantillaproducto(), plantillait);
			}

			for (DatoReporteDTO datoReporteDTO : datosProduccion) {
				plantillaproducto = plantillaProductoMap.get(datoReporteDTO.getCodigoPlantilla().longValue());
				columnasProductos.clear();
				columnasProductos.addAll(plantillaproducto.getColumnaplantillaproductos());
				double valor;
				if (datoReporteDTO.getVariable15Datoreporte() == 0.00
						&& notificacionProduccionFacade.validaValoresIngresados(datoReporteDTO)) {
					notificadoMp = Boolean.FALSE;
					notificadoCombustible = Boolean.FALSE;

					for (Columnaplantillaproducto columnaplantillaproducto : columnasProductos) {

						Tipocategoriaproducto componente = columnaplantillaproducto.getComponente()
								.getProductoByFkCodigoProductoComponente().getTipocategoriaproducto();
						Short posicionProducto = columnaplantillaproducto.getColumnareporte().getPosicionColumnareporte();

						if (!notificadoMp
								&& ((componente != null && !componente.getNombreTipocategoriaproducto().equals("combustible")) || componente == null)) {

							valor = notificacionProduccionFacade.obtenerValorDeVariableSegunValorColumna(datoReporteDTO,
									posicionProducto);

							if (valor == 0.00) {
								notificadoMp = Boolean.FALSE;
							} else {
								notificadoMp = Boolean.TRUE;
							}

						}

						if (!notificadoCombustible && componente != null
								&& componente.getNombreTipocategoriaproducto().equals("combustible")) {

							valor = notificacionProduccionFacade.obtenerValorDeVariableSegunValorColumna(datoReporteDTO,
									posicionProducto);

							if (valor == 0.00) {
								notificadoCombustible = Boolean.FALSE;
							} else {
								notificadoCombustible = Boolean.TRUE;
							}
						}

					}

					if (notificadoMp && notificadoCombustible) {
						return Boolean.FALSE;
					} else if (notificadoMp) {
						return Boolean.FALSE;
					} else if (notificadoCombustible) {
						return Boolean.TRUE;
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return Boolean.TRUE;
	}

}