package pe.com.pacasmayo.sgcp.presentacion.action.partediario;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: GestionarParteDiarioAction.java
 * Modificado: Jun 16, 2012 10:23:10 AM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;

import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.EstadoNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.MesBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.impl.MesBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

import pe.com.pacasmayo.sgcp.logica.facade.DivisionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoNotificacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.SociedadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaOperacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.SociedadLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.ConstantesSap;
import pe.com.pacasmayo.sgcp.logica.notificacion.EstadoNotificacionLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionDiariaLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.TablaOperacionLogic;

import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.ConstantesTransaccion;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.opensymphony.xwork2.Preparable;

public class GestionarParteDiarioAction extends AplicacionAction implements ConstantesLogicaNegocio,
		ConstantesMensajePresentacion, ConstantesSap, Preparable, ParameterAware {

	private static DivisionLogicFacade divisionFacade = new DivisionLogic();
	private static SociedadLogicFacade sociedadFacade = new SociedadLogic();
	private static UnidadLogicFacade unidadFacade = new UnidadLogic();
	private static LineaNegocioLogicFacade lineaNegocioFacade = new LineaNegocioLogic();
	private static EstadoNotificacionLogicFacade estadoNotificacionFacade = new EstadoNotificacionLogic();
	private static NotificacionDiariaLogicFacade notificacionDiariaLogicFacade = new NotificacionDiariaLogic();
	private static NotificacionProduccionLogicFacade notificacionProduccionLogicFacade = new NotificacionProduccionLogic();
	private static PuestoTrabajoLogicFacade puestoTrabajoLogicFacade = new PuestoTrabajoLogic();
	private static TablaOperacionLogicFacade tablaOperacionLogic = new TablaOperacionLogic();
	

	private List<DivisionBean> divisiones;
	private List<SociedadBean> sociedades;
	private List<UnidadBean> unidades;
	private List<LineaNegocioBean> lineasNegocio;
	private List<EstadoNotificacionBean> estados;
	private List<NotificacionDiariaBean> notificacionesPlanta;
	private List<NotificacionProduccionBean> notificacionesProduccion;
	private List<PuestoTrabajoBean> puestosTrabajo;

	private Long valorDivision;
	private Long valorSociedad;
	private Long valorUnidad;
	private Long valorLineaNegocio;
	private Long valorEstadoNotificacion;
	private Long valorPuestoTrabajo;
	private String codigoCompuesto;

	private Short mes;
	private Integer anio;
	private List<UtilBean> anios;
	private List<MesBean> meses;

	private String fechaNotificacion;

	private Date fechaInicio;
	private Date fechaFin;

	private String ELEMENTO_NO_SELECCIONADO = getText(NOTIFICACION_NO_SELECCIONADA_CERRAR);
	private String ELEMENTO_NO_SELECCIONADO_ELIMINAR = getText(NOTIFICACION_NO_SELECCIONADA_ELIMINAR);
	private String mensajeErrorValidacion = getText(CONST_MENSAJE_ERROR);
	private String tituloCampoDivision = getText(CONST_DIVISION);
	private String tituloCampoSociedad = getText(CONST_SOCIEDAD);
	private String tituloCampoUnidad = getText(CONST_UNIDAD);
	private String tituloCampoLineaNegocio = getText(CONST_LINEANEGOCIO);
	private String tituloCampoMes = getText(CONST_MES);

	private NotificacionDiariaBean notificacion = new NotificacionDiariaBeanImpl();

	private Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 7916896258988633084L;

	// Valores para mantener filtros
	private Long valorDivisionFiltrado;
	private Long valorSociedadFiltrado;
	private Long valorUnidadFiltrado;
	private Long valorLineaNegocioFiltrado;
	private Long valorEstadoFiltrado;
	private Integer valorAnioFiltrado;
	private Short valorMesFiltrado;
	private Long valorPuestoTrabajoFiltrado;

	public void prepare() throws Exception {
		super.asignaPrivilegios();
	}

	public void setParameters(Map arg0) {

	}

	public String doListarGestionPD() throws AplicacionGlobalException {

		verificarExitoOperacion();
		cargarListas();

		return SUCCESS;
	}

	public String doIniciarDatosNotificarProduccion() {
		return SUCCESS;
	}

	public String doListarGestionPDFiltrados() throws AplicacionGlobalException {
		limpiarMensajesyErrores();
		Long codigoLineaNegocio = null;
		if (valorLineaNegocio != null && !valorLineaNegocio.equals("")) {
			codigoLineaNegocio = Long.valueOf(valorLineaNegocio);
		} else {
			throw new AplicacionGlobalException("Debe seleccionar una linea de negocio");
		}

		try {
			salvarValoresFiltros();
			InicializarFechas();

			notificacionesPlanta = notificacionDiariaLogicFacade.obtenerNotificacionesDiariasEstadoPorFiltros(valorSociedad,
					valorUnidad, codigoLineaNegocio, valorEstadoNotificacion, fechaInicio, fechaFin);
			cargarDivision();
			cargarAnios();
			cargarMeses();
			cargarEstadosNotificacion();
			return SUCCESS;
		} catch (LogicaException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

	}

	private void salvarValoresFiltros() {
		valorDivisionFiltrado = valorDivision;
		valorSociedadFiltrado = valorSociedad;
		valorUnidadFiltrado = valorUnidad;
		valorLineaNegocioFiltrado = valorLineaNegocio;
		valorEstadoFiltrado = valorEstadoNotificacion;
		valorAnioFiltrado = anio;
		valorMesFiltrado = mes;
		valorPuestoTrabajoFiltrado = valorPuestoTrabajo;
	}

	private void InicializarFechas() {
		int primerDiaMes = 1;

		if ((anio == null)) {
			anio = Calendar.getInstance().get(Calendar.YEAR);
		}

		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.set(Calendar.YEAR, anio);
		calendarInicio.set(Calendar.MONTH, mes.intValue() - 1);
		calendarInicio.set(Calendar.DAY_OF_MONTH, primerDiaMes);
		fechaInicio = calendarInicio.getTime();

		Calendar calendarFin = Calendar.getInstance();
		calendarFin.set(Calendar.YEAR, anio);
		calendarFin.set(Calendar.MONTH, mes.intValue() - 1);
		calendarFin.set(Calendar.DAY_OF_MONTH, calendarInicio.getActualMaximum(Calendar.DAY_OF_MONTH));
		fechaFin = calendarFin.getTime();

	}

	public String cerrar() throws AplicacionGlobalException {
		NotificacionDiariaBean notificacionDiariaBean = null;

		try {
			notificacionDiariaBean = notificacionDiariaLogicFacade.obtenerNotificacionDiaria(notificacion.getCodigo());

			
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			cargarListas();
			return INPUT;
		}
		try {

			salvarValoresFiltros();

			limpiarMensajesyErrores();
			ResultadoBeanImpl exitoOperacion = new ResultadoBeanImpl();

			exitoOperacion = notificacionDiariaLogicFacade.cerrarNotificacion(notificacion, usuario);
			if (exitoOperacion.getExitoOperacion()) {

				addActionMessage(exitoOperacion.getMensajeOperacion());

			} else {
				// si es falso muestra mensaje de error
				if (exitoOperacion.getMensajeOperacion().length() != 0) {
					addActionError(exitoOperacion.getMensajeOperacion());
				} else {
					addActionError("No se realiz\u00f3 la operaci\u00f3n con \u00e9xito");
				}
				return INPUT;
			}

			return SUCCESS;
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			return INPUT;
			// throw new AplicacionException(e.getMessage(), e);
		} finally {
			cargarListas();
		
		}

	}

	public String reprocesar() throws AplicacionException {
		try {

			salvarValoresFiltros();
			cargarListas();
			limpiarMensajesyErrores();
			boolean exitoOperacion = false;

			notificacion = notificacionDiariaLogicFacade.obtenerNotificacionDiaria(notificacion.getCodigo());

			exitoOperacion = notificacionDiariaLogicFacade.reprocesarNotificacion(notificacion, getUsuarioSession());
			if (exitoOperacion) {
				setExitoOperacion();
			} else {
				// si es falso muestra mensaje de error
				addActionError("NO SE REALIZO LA OPERACION CON EXITO");
				return INPUT;
			}

			return SUCCESS;
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			return INPUT;
		} catch (AplicacionGlobalException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			return INPUT;
		}
	}

	public String abrir() throws AplicacionException {
		try {

			salvarValoresFiltros();
			cargarListas();
			limpiarMensajesyErrores();
			boolean exitoOperacion = false;

			exitoOperacion = notificacionDiariaLogicFacade.abrirNotificacionDiaria(notificacion.getCodigo(), getUsuarioSession());
			if (exitoOperacion) {
				setExitoOperacion();
			} else {
				// si es falso muestra mensaje de error
				addActionError("No se realizo la operación con exito");
				return INPUT;
			}

			return SUCCESS;
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			return INPUT;
		} catch (AplicacionGlobalException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			return INPUT;
		}
	}

	public String enviarCorreo() {
		try {
			// se obtiene el contexto para ubicar la direccion de los reportes
			ServletContext context = org.apache.struts2.ServletActionContext.getServletContext();
			String reportsDirPath = context.getRealPath("/reportes/jasperReport/parteDiario_Produccion/exportar/");
			System.out.println("-->" +reportsDirPath);
			tablaOperacionLogic.enviarCorreo(getMes(), getAnio(), getValorUnidad(),reportsDirPath);

			salvarValoresFiltros();
			cargarListas();

			setExitoOperacion();
			return SUCCESS;
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			return INPUT;
		} catch (AplicacionGlobalException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			return INPUT;
		}

	}

	/**
	 * Carga las listas de los combos
	 * 
	 * @throws AplicacionGlobalException
	 */
	private void cargarListas() throws AplicacionGlobalException {

		cargarDivisiones();
		cargarAnios();
		cargarMeses();
		cargarEstadosNotificacion();
		cargarPuestosTrabajos();

	}

	/**
	 * Carga las listas de los combos
	 * 
	 * @throws AplicacionGlobalException
	 */
	private void cargarListasNotificaciones() throws AplicacionGlobalException {

		cargarDivisiones();
		cargarAnios();
		cargarMeses();
		cargarEstadosNotificacionEliminar();
		cargarPuestosTrabajos();

	}

	private void cargarPuestosTrabajos() {
		try {
			puestosTrabajo = puestoTrabajoLogicFacade.obtenerPuestosTrabajo();
		} catch (LogicaException e) {
			logger.error(e);
		}

	}

	/**
	 * Carga los años a mostrar para la consulta
	 */
	private void cargarAnios() {
		anios = new ArrayList<UtilBean>();
		int esteAnio = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = -1; i <= ConstantesAplicacionAction.ANIOS_A_FUTURO_PRODUCCION_SEMANAL - 1; i++) {
			UtilBean utilBean = new UtilBeanImpl();
			utilBean.setCodigo(new Integer(esteAnio + i));
			utilBean.setValor(new Integer(esteAnio + i).toString());
			anios.add(utilBean);
		}
		setAnio(Calendar.getInstance().get(Calendar.YEAR));
	}

	private void cargarMeses() {
		meses = new ArrayList<MesBean>();
		meses.add(new MesBeanImpl(Long.valueOf(1), "Enero"));
		meses.add(new MesBeanImpl(Long.valueOf(2), "Febrero"));
		meses.add(new MesBeanImpl(Long.valueOf(3), "Marzo"));
		meses.add(new MesBeanImpl(Long.valueOf(4), "Abril"));
		meses.add(new MesBeanImpl(Long.valueOf(5), "Mayo"));
		meses.add(new MesBeanImpl(Long.valueOf(6), "Junio"));
		meses.add(new MesBeanImpl(Long.valueOf(7), "Julio"));
		meses.add(new MesBeanImpl(Long.valueOf(8), "Agosto"));
		meses.add(new MesBeanImpl(Long.valueOf(9), "Septiembre"));
		meses.add(new MesBeanImpl(Long.valueOf(10), "Octubre"));
		meses.add(new MesBeanImpl(Long.valueOf(11), "Noviembre"));
		meses.add(new MesBeanImpl(Long.valueOf(12), "Diciembre"));
		setMes(Short.valueOf(FechaUtil.getMesActual() + ""));
	}

	/**
	 * Metodo para cargar el filtro divisiones para la JSP
	 */
	private void cargarDivisiones() throws AplicacionGlobalException {
		try {

			if (esUsuarioAdmin()) {
				divisiones = divisionFacade.obtenerDivisiones();
				sociedades = sociedadFacade.obtenerSociedades();
				unidades = unidadFacade.obtenerUnidades();
				if (divisiones != null && divisiones.size() > 0)
					setValorDivision(Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_DIVISION)));
				if (sociedades != null && sociedades.size() > 0)
					setValorSociedad(Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_SOCIEDAD)));
				if (unidades != null && unidades.size() > 0)
					setValorUnidad(Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_UNIDAD)));
				salvarValoresFiltros();
			} else {
				divisiones = getUsuarioCargo().getDivisionBeanList();
				setValorDivision(usuario.getPersona().getCargo().getDivisionCargoBean().getDivisionBean().getCodigo());
			}

		} catch (LogicaException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}
	}

	private void cargarDivision() throws AplicacionGlobalException {
		try {

			if (esUsuarioAdmin()) {
				divisiones = divisionFacade.obtenerDivisiones();
			} else {
				divisiones = getUsuarioCargo().getDivisionBeanList();
			}

		} catch (LogicaException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}
	}

	/**
	 * Metodo para validar si se selecciono alguna unidad en el combo y llamar
	 * al metodo que carga las lineas de negocio
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public void cargarEstadosNotificacion() throws AplicacionGlobalException {
		try {
			estados = estadoNotificacionFacade.obtenerEstadoNotificacionGestionar();
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}
	}

	/**
	 * Metodo para validar si se selecciono alguna unidad en el combo y llamar
	 * al metodo que carga las lineas de negocio
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public void cargarEstadosNotificacionEliminar() throws AplicacionGlobalException {
		try {
			estados = estadoNotificacionFacade.obtenerEstadoNotificacion();
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}
	}

	/**
	 * Metodo para validar si se selecciono alguna division en el combo y llamar
	 * al metodo que carga las sociedades
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarSociedades() throws AplicacionGlobalException {
		try {

			if (getValorDivision() != null) {
				sociedades = sociedadFacade.obtenerSociedadesPorCodigoDivision(getValorDivision());
			} else if (!esUsuarioAdmin()) {

				sociedades = sociedadFacade.obtenerSociedadesPorCodigoDivision(usuario.getPersona().getCargo()
						.getDivisionCargoBean().getDivisionBean().getCodigo());
			}

			if (getValorSociedad() == null) {
				setValorSociedad(usuario.getPersona().getCargo().getSociedadCargoBean().getSociedadBean().getCodigo());
			}

		} catch (LogicaException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Metodo para validar si se selecciono alguna sociedad en el combo y llamar
	 * al metodo que carga las unidades
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarUnidades() throws AplicacionGlobalException {
		try {
			if (getValorSociedad() != null) {

				unidades = unidadFacade.obtenerUnidadesPorCodigoSociedad(getValorSociedad());
			} else if (!esUsuarioAdmin()) {
				unidades = unidadFacade.obtenerUnidadesPorCodigoSociedad(usuario.getPersona().getCargo().getSociedadCargoBean()
						.getSociedadBean().getCodigo());
			}

			setValorUnidad(usuario.getPersona().getCargo().getUnidadCargoBean().getUnidadBean().getCodigo());

		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Metodo para validar si se selecciono alguna unidad en el combo y llamar
	 * al metodo que carga las lineas de negocio
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarLineasNegocio() throws AplicacionGlobalException {
		try {

			if (getValorUnidad() != null) {
				lineasNegocio = lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(getValorUnidad());
			} else if (!esUsuarioAdmin()) {
				lineasNegocio = lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(usuario.getPersona().getCargo()
						.getUnidadCargoBean().getUnidadBean().getCodigo());
			}

			if (lineasNegocio != null && lineasNegocio.size() > 0)
				setValorLineaNegocio(lineasNegocio.get(0).getCodigo());

		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/*
	 * public String doCargarLineasNegocio() throws AplicacionGlobalException {
	 * if (getValorUnidad() == null) {
	 * setValorUnidad(usuario.getPersona().getCargo
	 * ().getUnidadCargoBean().getUnidadBean().getCodigo()); } try {
	 * lineasNegocio = lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(new
	 * Long(getValorUnidad())); if (lineasNegocio != null &&
	 * lineasNegocio.size() > 0)
	 * setValorLineaNegocio(lineasNegocio.get(0).getCodigo()); } catch
	 * (LogicaException e) { addActionError(e.getMensaje()); throw new
	 * AplicacionGlobalException(e.getMensaje(), e); } return SUCCESS; }
	 */

	public String formularoEliminarPorPuestotrabajo() throws AplicacionGlobalException {

		verificarExitoOperacion();
		cargarListasNotificaciones();
		return SUCCESS;
	}

	public String filtrarParteDiarioPorPuestoTrabajo() throws AplicacionGlobalException {

		limpiarMensajesyErrores();

		try {
			salvarValoresFiltros();
			InicializarFechas();

			notificacionesProduccion = notificacionProduccionLogicFacade.obtenerNotificacionesProduccion(valorLineaNegocio,
					valorEstadoNotificacion, valorPuestoTrabajo, fechaInicio, fechaFin);

			cargarDivision();
			cargarAnios();
			cargarMeses();
			cargarEstadosNotificacionEliminar();
			cargarPuestosTrabajos();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return SUCCESS;
	}

	public String eliminarNotificacionPlantaPorPuestotrabajo() throws AplicacionGlobalException {

		int posNotificacion = 0;
		int posPuestoTrabajo = 1;
		Long codigoNotificacion;
		boolean operacion;
		String[] splitcodigos = codigoCompuesto.split("-");
		if (splitcodigos != null && splitcodigos.length == 2) {
			valorPuestoTrabajo = Long.valueOf(splitcodigos[posPuestoTrabajo]);
			codigoNotificacion = Long.valueOf(splitcodigos[posNotificacion]);
			try {
				cargarListas();
				operacion = notificacionProduccionLogicFacade.eliminarPorCodigoNotificacionPuestoTrabajo(codigoNotificacion,
						valorPuestoTrabajo, getUsuarioSession());

				if (operacion) {
					setExitoOperacion();

				} else {

					addActionError("No se realizo la operación con exito");
					return INPUT;
				}
			} catch (LogicaException e) {
				addActionError(e.getMensaje());
				logger.error(e);
				return INPUT;
			}

		}

		return SUCCESS;
	}

	/**
	 * @return the divisiones
	 */
	public List<DivisionBean> getDivisiones() {
		return divisiones;
	}

	/**
	 * @param divisiones the divisiones to set
	 */
	public void setDivisiones(List<DivisionBean> divisiones) {
		this.divisiones = divisiones;
	}

	/**
	 * @return the sociedades
	 */
	public List<SociedadBean> getSociedades() {
		return sociedades;
	}

	/**
	 * @param sociedades the sociedades to set
	 */
	public void setSociedades(List<SociedadBean> sociedades) {
		this.sociedades = sociedades;
	}

	/**
	 * @return the unidades
	 */
	public List<UnidadBean> getUnidades() {
		return unidades;
	}

	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(List<UnidadBean> unidades) {
		this.unidades = unidades;
	}

	/**
	 * @return the lineasNegocio
	 */
	public List<LineaNegocioBean> getLineasNegocio() {
		return lineasNegocio;
	}

	/**
	 * @param lineasNegocio the lineasNegocio to set
	 */
	public void setLineasNegocio(List<LineaNegocioBean> lineasNegocio) {
		this.lineasNegocio = lineasNegocio;
	}

	/**
	 * @return the valorDivision
	 */
	public Long getValorDivision() {

		if (valorDivision == null) {
			return getValorDivisionFiltrado();
		}
		return valorDivision;
	}

	/**
	 * @param valorDivision the valorDivision to set
	 */
	public void setValorDivision(Long valorDivision) {
		this.valorDivision = valorDivision;
	}

	/**
	 * @return the valorUnidad
	 */
	public Long getValorUnidad() {

		if (getValorUnidadFiltrado() != null) {
			return getValorUnidadFiltrado();
		}
		return valorUnidad;
	}

	/**
	 * @param valorUnidad the valorUnidad to set
	 */
	public void setValorUnidad(Long valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

	/**
	 * @return the valorLineaNegocio
	 */
	public Long getValorLineaNegocio() {
		if (getValorLineaNegocioFiltrado() != null) {
			return getValorLineaNegocioFiltrado();
		}

		return valorLineaNegocio;
	}

	/**
	 * @param valorLineaNegocio the valorLineaNegocio to set
	 */
	public void setValorLineaNegocio(Long valorLineaNegocio) {
		this.valorLineaNegocio = valorLineaNegocio;
	}

	/**
	 * @return the estados
	 */
	public List<EstadoNotificacionBean> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(List<EstadoNotificacionBean> estados) {
		this.estados = estados;
	}

	/**
	 * @return the notificacionesPlanta
	 */
	public List<NotificacionDiariaBean> getNotificacionesPlanta() {
		return notificacionesPlanta;
	}

	/**
	 * @param notificacionesPlanta the notificacionesPlanta to set
	 */
	public void setNotificacionesPlanta(List<NotificacionDiariaBean> notificacionesPlanta) {
		this.notificacionesPlanta = notificacionesPlanta;
	}

	/**
	 * @return the notificacion
	 */
	public NotificacionDiariaBean getNotificacion() {
		return notificacion;
	}

	/**
	 * @param notificacion the notificacion to set
	 */
	public void setNotificacion(NotificacionDiariaBean notificacion) {
		this.notificacion = notificacion;
	}

	/**
	 * @return the valorEstadoNotificacion
	 */
	public Long getValorEstadoNotificacion() {
		if (getValorEstadoFiltrado() != null) {
			return getValorEstadoFiltrado();
		}

		return valorEstadoNotificacion;
	}

	/**
	 * @param valorEstadoNotificacion the valorEstadoNotificacion to set
	 */
	public void setValorEstadoNotificacion(Long valorEstadoNotificacion) {
		this.valorEstadoNotificacion = valorEstadoNotificacion;
	}

	/**
	 * @return the mes
	 */
	public Short getMes() {
		if (getValorMesFiltrado() != null) {
			return getValorMesFiltrado();
		}

		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(Short mes) {
		this.mes = mes;
	}

	/**
	 * @return the anio
	 */
	public Integer getAnio() {
		if (getValorAnioFiltrado() != null) {
			return getValorAnioFiltrado();
		}

		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return the eLEMENTO_NO_SELECCIONADO_ELIMINAR
	 */
	public String getElementoNoSeleccionadoEliminar() {
		return ELEMENTO_NO_SELECCIONADO_ELIMINAR;
	}

	/**
	 * @return the elementoNoSeleccionado
	 */
	public String getElementoNoSeleccionado() {
		return ELEMENTO_NO_SELECCIONADO;
	}

	/**
	 * @param elementoNoSeleccionado the elementoNoSeleccionado to set
	 */
	public void setElementoNoSeleccionado(String elementoNoSeleccionado) {
		this.ELEMENTO_NO_SELECCIONADO = elementoNoSeleccionado;
	}

	public String getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(String fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public List<UtilBean> getAnios() {
		return anios;
	}

	public void setAnios(List<UtilBean> anios) {
		this.anios = anios;
	}

	public List<MesBean> getMeses() {
		return meses;
	}

	public void setMeses(List<MesBean> meses) {
		this.meses = meses;
	}

	public Long getValorDivisionFiltrado() {
		return valorDivisionFiltrado;
	}

	public void setValorDivisionFiltrado(Long valorDivisionFiltrado) {
		this.valorDivisionFiltrado = valorDivisionFiltrado;
	}

	public Long getValorSociedadFiltrado() {
		return valorSociedadFiltrado;
	}

	public void setValorSociedadFiltrado(Long valorSociedadFiltrado) {
		this.valorSociedadFiltrado = valorSociedadFiltrado;
	}

	public Long getValorUnidadFiltrado() {
		return valorUnidadFiltrado;
	}

	public void setValorUnidadFiltrado(Long valorUnidadFiltrado) {
		this.valorUnidadFiltrado = valorUnidadFiltrado;
	}

	public Long getValorLineaNegocioFiltrado() {
		return valorLineaNegocioFiltrado;
	}

	public void setValorLineaNegocioFiltrado(Long valorLineaNegocioFiltrado) {
		this.valorLineaNegocioFiltrado = valorLineaNegocioFiltrado;
	}

	public Long getValorEstadoFiltrado() {
		return valorEstadoFiltrado;
	}

	public void setValorEstadoFiltrado(Long valorEstadoFiltrado) {
		this.valorEstadoFiltrado = valorEstadoFiltrado;
	}

	public Integer getValorAnioFiltrado() {
		return valorAnioFiltrado;
	}

	public void setValorAnioFiltrado(Integer valorAnioFiltrado) {
		this.valorAnioFiltrado = valorAnioFiltrado;
	}

	public Short getValorMesFiltrado() {
		return valorMesFiltrado;
	}

	public void setValorMesFiltrado(Short valorMesFiltrado) {
		this.valorMesFiltrado = valorMesFiltrado;
	}

	public String getMensajeErrorValidacion() {
		return mensajeErrorValidacion;
	}

	public void setMensajeErrorValidacion(String mensajeErrorValidacion) {
		this.mensajeErrorValidacion = mensajeErrorValidacion;
	}

	public String getTituloCampoDivision() {
		return tituloCampoDivision;
	}

	public void setTituloCampoDivision(String tituloCampoDivision) {
		this.tituloCampoDivision = tituloCampoDivision;
	}

	public String getTituloCampoSociedad() {
		return tituloCampoSociedad;
	}

	public void setTituloCampoSociedad(String tituloCampoSociedad) {
		this.tituloCampoSociedad = tituloCampoSociedad;
	}

	public String getTituloCampoUnidad() {
		return tituloCampoUnidad;
	}

	public void setTituloCampoUnidad(String tituloCampoUnidad) {
		this.tituloCampoUnidad = tituloCampoUnidad;
	}

	public String getTituloCampoLineaNegocio() {
		return tituloCampoLineaNegocio;
	}

	public void setTituloCampoLineaNegocio(String tituloCampoLineaNegocio) {
		this.tituloCampoLineaNegocio = tituloCampoLineaNegocio;
	}

	public String getTituloCampoMes() {
		return tituloCampoMes;
	}

	public void setTituloCampoMes(String tituloCampoMes) {
		this.tituloCampoMes = tituloCampoMes;
	}

	public Long getValorSociedad() {
		if (getValorSociedadFiltrado() != null) {
			return getValorSociedadFiltrado();
		}
		return valorSociedad;
	}

	public void setValorSociedad(Long valorSociedad) {
		this.valorSociedad = valorSociedad;
	}

	public boolean isUsuarioPermitido() {
		ParametroSistemaLogicFacade parametroLogic = new ParametroSistemaLogic();
		Boolean usuarioPermitido = Boolean.FALSE;
		try {
			ParametroSistemaBean parametro = parametroLogic.obtenerParametroSistema(ConstantesParametro.USUARIO_REPROCESAR);
			if (getUsuarioSession().getPersona().getCorreo().equals(parametro.getValor())) {
				usuarioPermitido = Boolean.TRUE;
			}

		} catch (LogicaException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return usuarioPermitido;
	}

	/**
	 * @return the notificacionesProduccion
	 */
	public List<NotificacionProduccionBean> getNotificacionesProduccion() {
		return notificacionesProduccion;
	}

	/**
	 * @param notificacionesProduccion the notificacionesProduccion to set
	 */
	public void setNotificacionesProduccion(List<NotificacionProduccionBean> notificacionesProduccion) {
		this.notificacionesProduccion = notificacionesProduccion;
	}

	/**
	 * @return the valorPuestoTrabajo
	 */
	public Long getValorPuestoTrabajo() {
		if (valorPuestoTrabajo == null) {
			return getValorPuestoTrabajoFiltrado();
		}
		return valorPuestoTrabajo;
	}

	/**
	 * @param valorPuestoTrabajo the valorPuestoTrabajo to set
	 */
	public void setValorPuestoTrabajo(Long valorPuestoTrabajo) {
		this.valorPuestoTrabajo = valorPuestoTrabajo;
	}

	/**
	 * @return the codigoCompuesto
	 */
	public String getCodigoCompuesto() {
		return codigoCompuesto;
	}

	/**
	 * @param codigoCompuesto the codigoCompuesto to set
	 */
	public void setCodigoCompuesto(String codigoCompuesto) {
		this.codigoCompuesto = codigoCompuesto;
	}

	/**
	 * @return the puestosTrabajo
	 */
	public List<PuestoTrabajoBean> getPuestosTrabajo() {
		return puestosTrabajo;
	}

	/**
	 * @param puestosTrabajo the puestosTrabajo to set
	 */
	public void setPuestosTrabajo(List<PuestoTrabajoBean> puestosTrabajo) {
		this.puestosTrabajo = puestosTrabajo;
	}

	/**
	 * @return the valorPuestoTrabajoFiltrado
	 */
	public Long getValorPuestoTrabajoFiltrado() {
		return valorPuestoTrabajoFiltrado;
	}

	/**
	 * @param valorPuestoTrabajoFiltrado the valorPuestoTrabajoFiltrado to set
	 */
	public void setValorPuestoTrabajoFiltrado(Long valorPuestoTrabajoFiltrado) {
		this.valorPuestoTrabajoFiltrado = valorPuestoTrabajoFiltrado;
	}



}
