package pe.com.pacasmayo.sgcp.presentacion.action.notificacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionPlantaAction.java
 * Modificado: Jun 22, 2010 10:22:39 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.EntidadBean;
import pe.com.pacasmayo.sgcp.bean.EstadoNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.TableroControlBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.impl.EntidadBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.DivisionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoNotificacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionDiariaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProcesoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.SociedadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TableroControlLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.SociedadLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.EstadoNotificacionLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionDiariaLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.TableroControlLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.FechaUtil.MESES_ESPANNOL;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.opensymphony.xwork2.Preparable;

public class NotificacionPlantaAction extends AplicacionAction implements
		ConstantesLogicaNegocio, ConstantesMensajePresentacion, Preparable,
		ParameterAware {

	private static DivisionLogicFacade divisionFacade = new DivisionLogic();
	private static SociedadLogicFacade sociedadFacade = new SociedadLogic();
	private static UnidadLogicFacade unidadFacade = new UnidadLogic();
	private static LineaNegocioLogicFacade lineaNegocioFacade = new LineaNegocioLogic();

	private static PuestoTrabajoLogicFacade puestoTrabajoFacade = new PuestoTrabajoLogic();
	private static EstadoNotificacionLogicFacade estadoNotificacionFacade = new EstadoNotificacionLogic();
	private static NotificacionDiariaLogicFacade notificacionDiariaLogicFacade = new NotificacionDiariaLogic();

	private static ProcesoLogicFacade procesoFacade = new ProcesoLogic();
	private static TableroControlLogicFacade tableroControlFacade = new TableroControlLogic();

	private List<DivisionBean> divisiones;
	private List<SociedadBean> sociedades;
	private List<UnidadBean> unidades;
	private List<LineaNegocioBean> lineasNegocio;
	private List<EstadoNotificacionBean> estados;
	private List<PuestoTrabajoBean> puestosDeTrabajo;
	private List<NotificacionDiariaBean> notificacionesPlanta;
	private List<ProcesoBean> procesos;
	private List<TableroControlBean> tablerosControl;
	private List<EntidadBean> variablesProdOper;

	private Long valorDivision;
	private Long valorSociedad;
	private Long valorUnidad;
	private Long valorLineaNegocio;
	private Long valorEstadoNotificacion;
	private String valorProceso;
	private String valorTableroControl;
	private String valorVariablesProdOper;
	private String mes;
	private Integer anio;
	private List<UtilBean> anios;
	private List<FechaUtil.MESES_ESPANNOL> meses = Arrays.asList(MESES_ESPANNOL
			.values());

	private String fechaNotificacion;

	private Date fechaInicio;
	private Date fechaFin;

	private String ELEMENTO_NO_SELECCIONADO = getText(NOTIFICACION_NO_SELECCIONADA);

	private String mensajeErrorValidacion = getText(CONST_MENSAJE_ERROR);
	private String tituloCampoDivision = getText(CONST_DIVISION);
	private String tituloCampoSociedad = getText(CONST_SOCIEDAD);
	private String tituloCampoUnidad = getText(CONST_UNIDAD);
	private String tituloCampoLineaNegocio = getText(CONST_LINEANEGOCIO);
	private String tituloCampoMes = getText(CONST_MES);

	private NotificacionDiariaBean notificacion = new NotificacionDiariaBeanImpl();

	private String mensajeError = "";

	private String VARIABLE_VACIO = "VACIO";
	private String VARIABLE_PRODUCCION = "PRODUCCION";
	private String VARIABLE_OPERACION = "OPERACION";

	private static final long serialVersionUID = 7916896258988633084L;

	// Valores para mantener filtros
	private Long valorDivisionFiltrado;
	private Long valorSociedadFiltrado;
	private Long valorUnidadFiltrado;
	private Long valorLineaNegocioFiltrado;
	private Long valorEstadoFiltrado;
	private Integer valorAnioFiltrado;
	private String valorMesFiltrado;

	public void prepare() throws Exception {
		super.asignaPrivilegios();
	}

	@SuppressWarnings("unchecked")
	public void setParameters(Map arg0) {

	}

	public String doListarNotificacionPlanta() throws AplicacionGlobalException {

		super.verificarExitoOperacion();
		cargarListas();
		// Seteando Año y Mes
		setAnio(FechaUtil.getAnnoActual());
		setMes(FechaUtil.numeroMesANombreMes(FechaUtil.getMesActual()
				.shortValue()));
		return SUCCESS;
	}

	public String doIniciarDatosNotificarProduccion() {
		return SUCCESS;
	}

	public String doListarNotificacionPlantaFiltrados()
			throws AplicacionGlobalException {
		limpiarMensajesyErrores();

		try {
			salvarValoresFiltros();
			cargarListasfiltrado();

			InicializarFechas();

			notificacionesPlanta = notificacionDiariaLogicFacade
					.obtenerNotificacionesDiariasPorFiltros(getValorSociedad(),
							getValorUnidad(), getValorLineaNegocio(),
							getValorEstadoNotificacion(), fechaInicio, fechaFin);
			return SUCCESS;
		} catch (LogicaException e) {
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
	}

	private void InicializarFechas() {
		int primerDiaMes = 1;

		Short mesNumber = FechaUtil.mesToShort(mes);

		if (anio == null) {
			anio = Calendar.getInstance().get(Calendar.YEAR);
		}

		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.set(Calendar.YEAR, anio);
		calendarInicio.set(Calendar.MONTH, mesNumber.intValue() - 1);
		calendarInicio.set(Calendar.DAY_OF_MONTH, primerDiaMes);
		fechaInicio = calendarInicio.getTime();

		Calendar calendarFin = Calendar.getInstance();
		calendarFin.set(Calendar.YEAR, anio);
		calendarFin.set(Calendar.MONTH, mesNumber.intValue() - 1);
		calendarFin.set(Calendar.DAY_OF_MONTH,
				calendarInicio.getActualMaximum(Calendar.DAY_OF_MONTH));
		fechaFin = calendarFin.getTime();

	}

	public String aprobar() throws AplicacionException {
		try {
			salvarValoresFiltros();
			cargarListas();
			limpiarMensajesyErrores();
			notificacion = notificacionDiariaLogicFacade.aprobarNotificacion(
					notificacion, usuario);
			setExitoOperacion();
			return SUCCESS;
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionException(e.getMessage(), e);
		} catch (AplicacionGlobalException e) {
			addActionError(e.getMensaje());
			throw new AplicacionException(e.getMessage(), e);
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
		cargarPuestosTrabajo();
		cargarEstadosNotificacion();

	}

	/**
	 * Carga las listas de los combos
	 * 
	 * @throws AplicacionGlobalException
	 */
	private void cargarListasfiltrado() throws AplicacionGlobalException {

		try {

			divisiones = divisionFacade.obtenerDivisiones();
			sociedades = sociedadFacade.obtenerSociedades();
			unidades = unidadFacade.obtenerUnidades();

		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}
		cargarAnios();
		cargarEstadosNotificacion();

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

	}

	public String doCargarVariablesProdOper() {
		variablesProdOper = new ArrayList<EntidadBean>();

		EntidadBean itemVariable = new EntidadBeanImpl();
		itemVariable.setCodigo(new Long(OPCION_1));
		itemVariable.setNombre(getText(VARIABLE_PRODUCCION));

		variablesProdOper.add(itemVariable);

		itemVariable = new EntidadBeanImpl();
		itemVariable.setCodigo(new Long(OPCION_2));
		itemVariable.setNombre(getText(VARIABLE_OPERACION));

		variablesProdOper.add(itemVariable);

		return SUCCESS;
	}

	public String doCambiarVistaVariable() {
		if (valorVariablesProdOper == null || valorVariablesProdOper.equals(""))
			return VARIABLE_VACIO;

		int variable = Integer.parseInt(valorVariablesProdOper);
		switch (variable) {
		case 1:
			return VARIABLE_PRODUCCION;
		case 2:
			return VARIABLE_OPERACION;
		}

		return VARIABLE_VACIO;
	}

	/**
	 * Metodo para cargar el filtro divisiones para la JSP
	 */
	private void cargarDivisiones() throws AplicacionGlobalException {
		try {

			divisiones = divisionFacade.obtenerDivisiones();
			sociedades = sociedadFacade.obtenerSociedades();
			unidades = unidadFacade.obtenerUnidades();

			if (divisiones != null && divisiones.size() > 0)
				setValorDivision(Long.valueOf(ManejadorPropiedades
						.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_DIVISION)));
			if (sociedades != null && sociedades.size() > 0) {
				setValorSociedadFiltrado(Long.valueOf(ManejadorPropiedades
						.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_SOCIEDAD)));
			}
			if (unidades != null && unidades.size() > 0) {
				setValorUnidadFiltrado(Long.valueOf(ManejadorPropiedades
						.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_UNIDAD)));
			}

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
	public void cargarPuestosTrabajo() throws AplicacionGlobalException {
		try {
			puestosDeTrabajo = puestoTrabajoFacade.obtenerPuestosTrabajo();
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
	public void cargarEstadosNotificacion() throws AplicacionGlobalException {
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
				sociedades = sociedadFacade
						.obtenerSociedadesPorCodigoDivision(getValorDivision());
			}

			// setValorSociedad(usuario.getPersona().getCargo().getSociedadCargoBean().getSociedadBean().getCodigo());
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Metodo para validar si se selecciono algun tablero de control en el combo
	 * y llamar al metodo que carga los puestos de trabajo
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarPuestosDeTrabajo() {
		try {
			puestosDeTrabajo = puestoTrabajoFacade.obtenerPuestosTrabajo();
		} catch (LogicaException e) {
			mensajeError = getText(ERROR_FALLA_CONSULTA_LISTA) + " "
					+ PuestoTrabajoBean.class.getName().toString();
			addActionError(mensajeError + " " + e.getMensaje());
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

				unidades = unidadFacade
						.obtenerUnidadesPorCodigoSociedad(getValorSociedad());
			}

			// setValorUnidad(usuario.getPersona().getCargo().getUnidadCargoBean().getUnidadBean().getCodigo());

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

				lineasNegocio = lineaNegocioFacade
						.obtenerLineaNegocioPorCodigoUnidad(Long
								.valueOf(getValorUnidad()));

				if (lineasNegocio != null && lineasNegocio.size() == 1)
					setValorLineaNegocio(lineasNegocio.get(0).getCodigo());
			}
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Metodo para validar si se selecciono alguna unidad en el combo y llamar
	 * al metodo que carga los tableros de control
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarTablerosControl() throws AplicacionGlobalException {
		if (getValorUnidad() != null) {
			try {
				tablerosControl = tableroControlFacade
						.obtenerTablerosControlPorUnidad(new Long(
								getValorUnidad()));
			} catch (LogicaException e) {
				addActionError(e.getMensaje());
				throw new AplicacionGlobalException(e.getMensaje(), e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Metodo para validar si se selecciono alguna linea de negocion en el combo
	 * y llamar al metodo que carga los procesos
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarProcesos() throws AplicacionGlobalException {
		if (getValorLineaNegocio() != null
				&& !getValorLineaNegocio().equals("")) {
			try {
				procesos = procesoFacade
						.obtenerProcesosPorCodigoLineaNegocio(new Long(
								getValorLineaNegocio()));
			} catch (LogicaException e) {
				addActionError(e.getMensaje());
				throw new AplicacionGlobalException(e.getMensaje(), e);
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
	 * @param divisiones
	 *            the divisiones to set
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
	 * @param sociedades
	 *            the sociedades to set
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
	 * @param unidades
	 *            the unidades to set
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
	 * @param lineasNegocio
	 *            the lineasNegocio to set
	 */
	public void setLineasNegocio(List<LineaNegocioBean> lineasNegocio) {
		this.lineasNegocio = lineasNegocio;
	}

	/**
	 * @return the procesos
	 */
	public List<ProcesoBean> getProcesos() {
		return procesos;
	}

	/**
	 * @param tablerosControl
	 *            the tablerosControl to set
	 */
	public void setTablerosControl(List<TableroControlBean> tablerosControl) {
		this.tablerosControl = tablerosControl;
	}

	/**
	 * @return the tablerosControl
	 */
	public List<TableroControlBean> getTablerosControl() {
		return tablerosControl;
	}

	/**
	 * @param procesos
	 *            the procesos to set
	 */
	public void setProcesos(List<ProcesoBean> procesos) {
		this.procesos = procesos;
	}

	/**
	 * @return the puestoDeTrabajo
	 */
	public List<PuestoTrabajoBean> getPuestosDeTrabajo() {
		return puestosDeTrabajo;
	}

	/**
	 * @param puestoDeTrabajo
	 *            the puestoDeTrabajo to set
	 */
	public void setPuestosDeTrabajo(List<PuestoTrabajoBean> puestosDeTrabajo) {
		this.puestosDeTrabajo = puestosDeTrabajo;
	}

	/**
	 * @return the valorDivision
	 */
	public Long getValorDivision() {
		if (valorDivision == null || valorDivision.equals("")) {
			return getValorDivisionFiltrado();
		}

		return valorDivision;
	}

	/**
	 * @param valorDivision
	 *            the valorDivision to set
	 */
	public void setValorDivision(Long valorDivision) {
		this.valorDivision = valorDivision;
	}

	/**
	 * @return the valorSociedad
	 */
	public Long getValorSociedad() {
		if (valorSociedad == null) {
			return getValorSociedadFiltrado();

		}
		return valorSociedad;
	}

	/**
	 * @param valorSociedad
	 *            the valorSociedad to set
	 */
	public void setValorSociedad(Long valorSociedad) {
		this.valorSociedad = valorSociedad;
	}

	/**
	 * @return the valorUnidad
	 */
	public Long getValorUnidad() {

		if (valorUnidad == null) {
			return getValorUnidadFiltrado();
		}
		return valorUnidad;
	}

	/**
	 * @param valorUnidad
	 *            the valorUnidad to set
	 */
	public void setValorUnidad(Long valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

	/**
	 * @return the valorLineaNegocio
	 */
	public Long getValorLineaNegocio() {
		if (valorLineaNegocio == null) {
			return getValorLineaNegocioFiltrado();
		}

		return valorLineaNegocio;
	}

	/**
	 * @param valorLineaNegocio
	 *            the valorLineaNegocio to set
	 */
	public void setValorLineaNegocio(Long valorLineaNegocio) {
		this.valorLineaNegocio = valorLineaNegocio;
	}

	/**
	 * @param valorProceso
	 *            the valorProceso to set
	 */
	public void setValorProceso(String valorProceso) {
		this.valorProceso = valorProceso;
	}

	/**
	 * @return the valorProceso
	 */
	public String getValorProceso() {
		return valorProceso;
	}

	/**
	 * @param valorTableroControl
	 *            the valorTableroControl to set
	 */
	public void setValorTableroControl(String valorTableroControl) {
		this.valorTableroControl = valorTableroControl;
	}

	/**
	 * @return the valorTableroControl
	 */
	public String getValorTableroControl() {
		return valorTableroControl;
	}

	/**
	 * @return the estados
	 */
	public List<EstadoNotificacionBean> getEstados() {
		return estados;
	}

	/**
	 * @param estados
	 *            the estados to set
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
	 * @param notificacionesPlanta
	 *            the notificacionesPlanta to set
	 */
	public void setNotificacionesPlanta(
			List<NotificacionDiariaBean> notificacionesPlanta) {
		this.notificacionesPlanta = notificacionesPlanta;
	}

	/**
	 * @return the notificacion
	 */
	public NotificacionDiariaBean getNotificacion() {
		return notificacion;
	}

	/**
	 * @param notificacion
	 *            the notificacion to set
	 */
	public void setNotificacion(NotificacionDiariaBean notificacion) {
		this.notificacion = notificacion;
	}

	/**
	 * @return the valorEstadoNotificacion
	 */
	public Long getValorEstadoNotificacion() {
		if (valorEstadoNotificacion == null) {
			return getValorEstadoFiltrado();
		}

		return valorEstadoNotificacion;
	}

	/**
	 * @param valorEstadoNotificacion
	 *            the valorEstadoNotificacion to set
	 */
	public void setValorEstadoNotificacion(Long valorEstadoNotificacion) {
		this.valorEstadoNotificacion = valorEstadoNotificacion;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		if (mes == null || mes.equals("")) {
			return getValorMesFiltrado();
		}

		return mes;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return the anio
	 */
	public Integer getAnio() {
		if (anio == null) {
			return getValorAnioFiltrado();
		}

		return anio;
	}

	/**
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return the elementoNoSeleccionado
	 */
	public String getElementoNoSeleccionado() {
		return ELEMENTO_NO_SELECCIONADO;
	}

	/**
	 * @param elementoNoSeleccionado
	 *            the elementoNoSeleccionado to set
	 */
	public void setElementoNoSeleccionado(String elementoNoSeleccionado) {
		this.ELEMENTO_NO_SELECCIONADO = elementoNoSeleccionado;
	}

	public String getValorVariablesProdOper() {
		return valorVariablesProdOper;
	}

	public void setValorVariablesProdOper(String valorVariablesProdOper) {
		this.valorVariablesProdOper = valorVariablesProdOper;
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

	public List<FechaUtil.MESES_ESPANNOL> getMeses() {
		return meses;
	}

	public void setMeses(List<FechaUtil.MESES_ESPANNOL> meses) {
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

	public String getValorMesFiltrado() {
		return valorMesFiltrado;
	}

	public void setValorMesFiltrado(String valorMesFiltrado) {
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

}
