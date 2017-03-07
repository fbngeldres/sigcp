package pe.com.pacasmayo.sgcp.presentacion.action.stock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;

import pe.com.pacasmayo.sgcp.bean.CubicacionProductoBean;
import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.EstadoCubicacionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.impl.CubicacionProductoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.DivisionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoCubicacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProcesoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.SociedadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.SociedadLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogic;
import pe.com.pacasmayo.sgcp.logica.stock.CubicacionProductoLogic;
import pe.com.pacasmayo.sgcp.logica.stock.EstadoCubicacionLogic;
import pe.com.pacasmayo.sgcp.persistencia.querier.CubicacionProductoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.FechaUtil.MESES_ESPANNOL;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.opensymphony.xwork2.Preparable;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsultarCubicacionMesAction.java
 * Modificado: Jun 7, 2010 7:13:51 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ConsultarCubicacionMesAction extends AplicacionAction implements Preparable, ParameterAware, ConstantesLogicaNegocio {

	private static final long serialVersionUID = 1L;
	private static DivisionLogicFacade divisionFacade = new DivisionLogic();
	private static SociedadLogicFacade sociedadFacade = new SociedadLogic();
	private static UnidadLogicFacade unidadFacade = new UnidadLogic();
	private static LineaNegocioLogicFacade lineaNegocioFacade = new LineaNegocioLogic();
	private static ProcesoLogicFacade procesoFacade = new ProcesoLogic();
	private static ProductoLogicFacade productoFacade = new ProductoLogic();
	private static EstadoCubicacionLogicFacade estadoCubicacionLogicFacade = new EstadoCubicacionLogic();
	private static CubicacionProductoLogicFacade cubicacionProductoLogicFacade = new CubicacionProductoLogic();

	private List<DivisionBean> divisiones;
	private List<SociedadBean> sociedades;
	private List<UnidadBean> unidades;
	private List<LineaNegocioBean> lineasNegocio;
	private List<ProcesoBean> procesos;
	private List<ProductoBean> productos;
	private List<EstadoCubicacionBean> estadosCubicacion;
	private List<CubicacionProductoBean> cubicacionesProductos;

	private CubicacionProductoBean cubicacionProductoBean = new CubicacionProductoBeanImpl();

	private String valorDivision;
	private String valorSociedad;
	private String valorUnidad;
	private String valorLineaNegocio;
	private String valorProceso;
	private String valorProducto;

	private String valorDivisionFiltrado;
	private String valorSociedadFiltrado;
	private String valorUnidadFiltrado;
	private String valorLineaNegocioFiltrado;
	private String valorProcesoFiltrado;
	private String valorProductoFiltrado;

	private String valorEstadoCub;
	private String anioCubicacion;
	private String mesCubicacion;
	private String[] codigosCubicaciones;

	private String mensajeErrorValidacion = getText(CONST_MENSAJE_ERROR);
	private String tituloCampoDivision = getText(CONST_DIVISION);
	private String tituloCampoSociedad = getText(CONST_SOCIEDAD);
	private String tituloCampoUnidad = getText(CONST_UNIDAD);
	private String tituloCampoLineaNegocio = getText(CONST_LINEANEGOCIO);
	private String tituloCampoMes = getText(CONST_MES);

	private List<UtilBean> anios;
	private List<FechaUtil.MESES_ESPANNOL> meses = Arrays.asList(MESES_ESPANNOL.values());

	private Log logger = LogFactory.getLog(this.getClass());

	public void prepare() throws Exception {

		asignaPrivilegios();
	}

	public String consultarCubicacionMes() throws AplicacionGlobalException {

		verificarExitoOperacion();

		cargarFiltrodivisiones();
		doCargarEstadosCubicacion();
		cargarAnios();
		return SUCCESS;
	}

	public String listar() throws AplicacionGlobalException {
		valorDivisionFiltrado = valorDivision;
		valorSociedadFiltrado = valorSociedad;
		valorUnidadFiltrado = valorUnidad;
		valorLineaNegocioFiltrado = valorLineaNegocio;
		valorProcesoFiltrado = valorProceso;
		valorProductoFiltrado = valorProducto;

		cargarFiltrosConsultaCubicacion();
		cargarAnios();

		if (!StringUtils.isBlank(getValorLineaNegocio())) {
			verificarExitoOperacion();

			Map<String, Object> propiedades = agregarPropiedadesFiltrado();

			try {
				cubicacionesProductos = cubicacionProductoLogicFacade.obtenerCubicionesProductoPorPropiedades(propiedades);

			} catch (LogicaException e) {
				addActionError(e.getMensaje());
				logger.error(e.getMessage());
				throw new AplicacionGlobalException(e.getMensaje(), e);

			} catch (RuntimeException e) {
				String mensajeError;
				if (e instanceof SesionVencidaException) {
					mensajeError = getText(SESION_VENCIDA);
					addActionError(mensajeError);
				} else if (e instanceof EntornoEjecucionException) {
					mensajeError = getText(COMUNICACION_BD_FALLO);
					addActionError(mensajeError);
				} else {
					mensajeError = getText(ERROR_FATAL_FALLO);
					addActionError(mensajeError);
				}
				throw new AplicacionGlobalException(mensajeError, e);
			}
		} else {
			String mensajeError = "Datos Seleccionados Insuficientes.";
			addActionError(mensajeError);
		}
		return SUCCESS;
	}

	private void cargarFiltrosConsultaCubicacion() throws AplicacionGlobalException {
		cargarFiltrodivisiones();
		doCargarSociedades();
		doCargarUnidades();
		doCargarLineasNegocio();
		doCargarEstadosCubicacion();
	}

	private Map<String, Object> agregarPropiedadesFiltrado() {
		Map<String, Object> propiedades = new HashMap<String, Object>();

		String valorLineaNegocioStr = getValorLineaNegocio();
		String valorProcesoStr = getValorProceso();
		String valorProductoStr = getValorProducto();
		String valorEstadoCubicacionStr = getValorEstadoCub();
		String anioCubicacionStr = getAnioCubicacion();
		String mesCubicacionStr = getMesCubicacion();

		if (!StringUtils.isBlank(valorLineaNegocioStr)) {
			propiedades.put(CubicacionProductoQuerier.CODIGO_LINEA_NEGOCIO, new Long(valorLineaNegocioStr));
		}

		if (!StringUtils.isBlank(valorProcesoStr)) {
			propiedades.put(CubicacionProductoQuerier.CODIGO_PROCESO, new Long(valorProcesoStr));
		}

		if (!StringUtils.isBlank(valorProductoStr)) {
			propiedades.put(CubicacionProductoQuerier.CODIGO_PRODUCTO, new Long(valorProductoStr));
		}

		if (!StringUtils.isBlank(valorEstadoCubicacionStr)) {
			propiedades.put(CubicacionProductoQuerier.CODIGO_ESTADO_CUBICACION, new Long(valorEstadoCubicacionStr));
		}

		if (!StringUtils.isBlank(anioCubicacionStr)) {
			propiedades.put(CubicacionProductoQuerier.ANIO, new Integer(anioCubicacionStr));
		}

		if (!StringUtils.isBlank(mesCubicacionStr)) {
			propiedades.put(CubicacionProductoQuerier.MES, new Short(FechaUtil.mesToShort(mesCubicacionStr)));
		}

		return propiedades;
	}

	public String doAprobar() throws AplicacionException, AplicacionGlobalException {

		String mensajeError = "";
		try {
			if (codigosCubicaciones != null) {
				for (int i = 0; i < codigosCubicaciones.length; i++) {
					cubicacionProductoLogicFacade.aprobarCubicacion(Long.parseLong(codigosCubicaciones[i]));
				}
			} else {
				mensajeError = "Debe seleccionar al menos un registro a Aprobar.";
				throw new AplicacionGlobalException(mensajeError);
			}
			setExitoOperacion();
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionException(e.getMensaje(), e);
		} catch (RuntimeException e) {
			if (e instanceof SesionVencidaException) {
				mensajeError = getText(SESION_VENCIDA);
				addActionError(mensajeError);
			} else if (e instanceof EntornoEjecucionException) {
				mensajeError = getText(COMUNICACION_BD_FALLO);
				addActionError(mensajeError);
			} else {
				mensajeError = getText(ERROR_FATAL_FALLO);
				addActionError(mensajeError);
			}
			throw new AplicacionException(mensajeError, e);
		}
		return SUCCESS;
	}

	public String doAnular() throws AplicacionGlobalException {

		String mensajeError = "";
		try {
			// cubicacionProductoLogicFacade.anularCubicacion(cubicacionProductoBean.getCodigo());
			if (codigosCubicaciones != null) {
				for (int i = 0; i < codigosCubicaciones.length; i++) {
					cubicacionProductoLogicFacade.anularCubicacion(Long.parseLong(codigosCubicaciones[i]));
				}
			} else {
				mensajeError = "Debe seleccionar al menos un registro a Aprobar.";
				throw new AplicacionGlobalException(mensajeError);
			}
			setExitoOperacion();
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);

		} catch (RuntimeException e) {
			if (e instanceof SesionVencidaException) {
				mensajeError = getText(SESION_VENCIDA);
				addActionError(mensajeError);
			} else if (e instanceof EntornoEjecucionException) {
				mensajeError = getText(COMUNICACION_BD_FALLO);
				addActionError(mensajeError);
			} else {
				mensajeError = getText(ERROR_FATAL_FALLO);
				addActionError(mensajeError);
			}
			throw new AplicacionGlobalException(mensajeError, e);
		}
		return SUCCESS;
	}

	public String doRevertir() throws AplicacionGlobalException {

		String mensajeError = "";
		try {

			if (codigosCubicaciones != null) {
				Boolean validarReversion = cubicacionProductoLogicFacade.validarCubicaciones(codigosCubicaciones);

				if (validarReversion) {
					for (int i = 0; i < codigosCubicaciones.length; i++) {

						cubicacionProductoLogicFacade.revertirCubicacion(Long.parseLong(codigosCubicaciones[i]));
					}
				} else {
					cargarFiltrosConsultaCubicacion();
					cargarAnios();
					addActionError("Sin \u00e9xito. Cubicaci\u00F3n anulada y/o parte t\u00e9cnico cerrado");
					return INPUT;
				}

			} else {
				mensajeError = "Debe seleccionar al menos un registro a revertir.";
				throw new AplicacionGlobalException(mensajeError);
			}
			setExitoOperacion();
		} catch (LogicaException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);

		} catch (RuntimeException e) {
			e.printStackTrace();
			if (e instanceof SesionVencidaException) {
				mensajeError = getText(SESION_VENCIDA);
				addActionError(mensajeError);
			} else if (e instanceof EntornoEjecucionException) {
				mensajeError = getText(COMUNICACION_BD_FALLO);
				addActionError(mensajeError);
			} else {
				mensajeError = getText(ERROR_FATAL_FALLO);
				addActionError(mensajeError);
			}
			throw new AplicacionGlobalException(mensajeError, e);
		}
		return SUCCESS;
	}

	/**
	 * Este método carga los años para el combo
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

	/**
	 * Metodo para cargar el filtro divisiones para la JSP
	 * 
	 * @throws AplicacionGlobalException
	 */
	private void cargarFiltrodivisiones() throws AplicacionGlobalException {
		try {
			if (esUsuarioAdmin()) {
				divisiones = divisionFacade.obtenerDivisiones();
				sociedades = sociedadFacade.obtenerSociedades();
				unidades = unidadFacade.obtenerUnidades();

				if (divisiones != null && divisiones.size() > 0)
					setValorDivision(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_DIVISION));
				if (sociedades != null && sociedades.size() > 0)
					setValorSociedadFiltrado(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_SOCIEDAD));
				if (unidades != null && unidades.size() > 0)
					setValorUnidadFiltrado(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_UNIDAD));

			} else {
				divisiones = getUsuarioCargo().getDivisionBeanList();
				setValorDivision(usuario.getPersona().getCargo().getDivisionCargoBean().getDivisionBean().getCodigo().toString());
				if (usuario.getPersona().getCargo().getSociedadCargoBean() != null) {
					setValorSociedadFiltrado(usuario.getPersona().getCargo().getSociedadCargoBean().getSociedadBean().getCodigo()
							.toString());
				}

				if (usuario.getPersona().getCargo().getUnidadCargoBean() != null) {
					setValorUnidadFiltrado(usuario.getPersona().getCargo().getUnidadCargoBean().getUnidadBean().getCodigo().toString());
				}
			}
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
			if (getValorDivision() != null && !getValorDivision().equals("")) {
				sociedades = sociedadFacade.obtenerSociedadesPorCodigoDivision(Long.parseLong(getValorDivision()));
			} else if (!esUsuarioAdmin()) {

				sociedades = sociedadFacade.obtenerSociedadesPorCodigoDivision(Long.parseLong(usuario.getPersona().getCargo()
						.getDivisionCargoBean().getDivisionBean().getCodigo().toString()));
				
				setValorSociedad(usuario.getPersona().getCargo().getSociedadCargoBean().getSociedadBean().getCodigo().toString());
			}

			//setValorSociedad(usuario.getPersona().getCargo().getSociedadCargoBean().getSociedadBean().getCodigo().toString());
		} catch (LogicaException e) {
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
			if (getValorSociedad() != null && !getValorSociedad().equals("")) {

				unidades = unidadFacade.obtenerUnidadesPorCodigoSociedad(Long.parseLong(getValorSociedad()));
			} else if (!esUsuarioAdmin()) {
				unidades = unidadFacade.obtenerUnidadesPorCodigoSociedad(Long.parseLong(usuario.getPersona().getCargo()
						.getSociedadCargoBean().getSociedadBean().getCodigo().toString()));

				setValorUnidad(usuario.getPersona().getCargo().getUnidadCargoBean().getUnidadBean().getCodigo().toString());
			}

			/*if (getValorUnidad() != null )
				setLineasNegocio(lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(Long.parseLong(getValorUnidad())));*/
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
			if (getValorUnidad() != null && !getValorUnidad().equals("")) {
				lineasNegocio = lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(Long.parseLong(getValorUnidad()));
			} else if (!esUsuarioAdmin()) {
				lineasNegocio = lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(Long.parseLong(usuario.getPersona()
						.getCargo().getUnidadCargoBean().getUnidadBean().getCodigo().toString()));
			}
			if (lineasNegocio != null && lineasNegocio.size() == 1) {
				setValorLineaNegocioFiltrado(lineasNegocio.get(0).getCodigo().toString());
			}
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}
		return SUCCESS;
	}

	/**
	 * Método para validar si se selecciono alguna linea de negocio en el combo
	 * y llamar al metodo que carga las los procesos
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarProcesos() throws AplicacionGlobalException {
		if (getValorLineaNegocio() != null && !getValorLineaNegocio().equals("")) {
			try {
				procesos = procesoFacade.obtenerProcesosPorCodigoLineaNegocio(new Long(getValorLineaNegocio()));
			} catch (LogicaException e) {
				logger.error(e.getMensaje());
				addActionError(e.getMensaje());
				throw new AplicacionGlobalException(e.getMensaje(), e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Método para validar si se selecciono algun proceso en el combo y llamar
	 * al metodo que carga los productos
	 * 
	 * @return String SUCCESS
	 * @throws Exception
	 */
	public String doCargarProductos() throws AplicacionGlobalException {
		if (getValorProceso() != null && !getValorProceso().equals("")) {
			try {
				productos = productoFacade.obtenerProductosPorProceso(new Long(getValorProceso()));
			} catch (LogicaException e) {
				logger.error(e.getMensaje());
				addActionError(e.getMensaje());
				throw new AplicacionGlobalException(e.getMensaje(), e);
			}
		}
		return SUCCESS;
	}

	public String doCargarEstadosCubicacion() throws AplicacionGlobalException {
		try {
			estadosCubicacion = estadoCubicacionLogicFacade.getEstadosCubicacion();
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}
		return SUCCESS;
	}
	
	
	public String doConsultarCubicacion() {
		return SUCCESS;
	}

	public List<DivisionBean> getDivisiones() {
		return divisiones;
	}

	public void setDivisiones(List<DivisionBean> divisiones) {
		this.divisiones = divisiones;
	}

	public List<SociedadBean> getSociedades() {
		return sociedades;
	}

	public void setSociedades(List<SociedadBean> sociedades) {
		this.sociedades = sociedades;
	}

	public List<UnidadBean> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadBean> unidades) {
		this.unidades = unidades;
	}

	public List<LineaNegocioBean> getLineasNegocio() {
		return lineasNegocio;
	}

	public void setLineasNegocio(List<LineaNegocioBean> lineasNegocio) {
		this.lineasNegocio = lineasNegocio;
	}

	public String getValorDivision() {
		if (valorDivision == null) {
			return getValorDivisionFiltrado();
		}

		return valorDivision;
	}

	public void setValorDivision(String valorDivision) {
		this.valorDivision = valorDivision;
	}

	public String getValorSociedad() {
		if (valorSociedad == null) {
			return getValorSociedadFiltrado();
		} 
		return valorSociedad;
	}

	public void setValorSociedad(String valorSociedad) {
		this.valorSociedad = valorSociedad;
	}

	public String getValorUnidad() {
		if (valorUnidad == null) {
			return getValorUnidadFiltrado();
		} 
		return valorUnidad;
	}

	public void setValorUnidad(String valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

	public String getValorLineaNegocio() {
		if (valorLineaNegocio == null) {
			return getValorLineaNegocioFiltrado();
		}
		return valorLineaNegocio;
	}

	public void setValorLineaNegocio(String valorLineaNegocio) {
		this.valorLineaNegocio = valorLineaNegocio;
	}

	public List<EstadoCubicacionBean> getEstadosCubicacion() {
		return estadosCubicacion;
	}

	public void setEstadosCubicacion(List<EstadoCubicacionBean> estadosCubicacion) {
		this.estadosCubicacion = estadosCubicacion;
	}

	public List<CubicacionProductoBean> getCubicacionesProductos() {
		return cubicacionesProductos;
	}

	public void setCubicacionesProductos(List<CubicacionProductoBean> cubicacionesProductos) {
		this.cubicacionesProductos = cubicacionesProductos;
	}

	public String getAnioCubicacion() {
		return anioCubicacion;
	}

	public void setAnioCubicacion(String anioCubicacion) {
		this.anioCubicacion = anioCubicacion;
	}

	public String getMesCubicacion() {
		return mesCubicacion;
	}

	public void setMesCubicacion(String mesCubicacion) {
		this.mesCubicacion = mesCubicacion;
	}

	public CubicacionProductoBean getCubicacionProductoBean() {
		return cubicacionProductoBean;
	}

	public void setCubicacionProductoBean(CubicacionProductoBean cubicacionProductoBean) {
		this.cubicacionProductoBean = cubicacionProductoBean;
	}

	public String getValorEstadoCub() {
		return valorEstadoCub;
	}

	public void setValorEstadoCub(String valorEstadoCub) {
		this.valorEstadoCub = valorEstadoCub;
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

	public String getValorProceso() {
		if (valorProceso == null) {
			return getValorProcesoFiltrado();
		}
		return valorProceso;
	}

	public void setValorProceso(String valorProceso) {
		this.valorProceso = valorProceso;
	}

	public String getValorProducto() {
		if (valorProducto == null) {
			return getValorProductoFiltrado();
		}
		return valorProducto;
	}

	public void setValorProducto(String valorProducto) {
		this.valorProducto = valorProducto;
	}

	public List<ProcesoBean> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<ProcesoBean> procesos) {
		this.procesos = procesos;
	}

	public List<ProductoBean> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoBean> productos) {
		this.productos = productos;
	}

	public void setParameters(Map arg0) {

	}

	public String getValorDivisionFiltrado() {
		return valorDivisionFiltrado;
	}

	public void setValorDivisionFiltrado(String valorDivisionFiltrado) {
		this.valorDivisionFiltrado = valorDivisionFiltrado;
	}

	public String getValorSociedadFiltrado() {
		return valorSociedadFiltrado;
	}

	public void setValorSociedadFiltrado(String valorSociedadFiltrado) {
		this.valorSociedadFiltrado = valorSociedadFiltrado;
	}

	public String getValorUnidadFiltrado() {
		return valorUnidadFiltrado;
	}

	public void setValorUnidadFiltrado(String valorUnidadFiltrado) {
		this.valorUnidadFiltrado = valorUnidadFiltrado;
	}

	public String getValorLineaNegocioFiltrado() {
		return valorLineaNegocioFiltrado;
	}

	public void setValorLineaNegocioFiltrado(String valorLineaNegocioFiltrado) {
		this.valorLineaNegocioFiltrado = valorLineaNegocioFiltrado;
	}

	public String getValorProcesoFiltrado() {
		return valorProcesoFiltrado;
	}

	public void setValorProcesoFiltrado(String valorProcesoFiltrado) {
		this.valorProcesoFiltrado = valorProcesoFiltrado;
	}

	public String getValorProductoFiltrado() {
		return valorProductoFiltrado;
	}

	public void setValorProductoFiltrado(String valorProductoFiltrado) {
		this.valorProductoFiltrado = valorProductoFiltrado;
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

	public String[] getCodigosCubicaciones() {
		return codigosCubicaciones;
	}

	public void setCodigosCubicaciones(String[] codigosCubicaciones) {
		this.codigosCubicaciones = codigosCubicaciones;
	}
}
