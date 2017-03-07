package pe.com.pacasmayo.sgcp.presentacion.action.parteTecnico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.DetalleAjusteProduccionMesBean;
import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaGrupoAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoAjusteProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PeriodoContableLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProduccionMesLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProductoLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.EstadoAjusteProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.stock.PeriodoContableLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.util.ConstantesTransaccion;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.opensymphony.xwork2.Preparable;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AjusteProduccionMesAction.java
 * Modificado: Jul 29, 2010 2:43:44 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AjusteProduccionMesAction extends AplicacionAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensajeError = "";
	private Log logger = LogFactory.getLog(this.getClass());
	private static LineaNegocioLogicFacade lineaNegocioFacade = new LineaNegocioLogic();
	private static EstadoAjusteProduccionLogicFacade estadoAjusteProduccionFacade = new EstadoAjusteProduccionLogic();
	private static PeriodoContableLogicFacade periodoContableFacade = new PeriodoContableLogic();
	private static AjusteProduccionMesLogicFacade ajusteProduccionFacade = new AjusteProduccionMesLogic();
	private static AjusteProductoLogicFacade ajusteProductoFacade = new AjusteProductoLogic();

	private static List<DetalleAjusteProduccionMesBean> ajustesProduccionDetalle = null;
	private List<LineaNegocioBean> lineas = new ArrayList<LineaNegocioBean>();
	private List<EstadoAjusteProduccionBean> estadosAjusteProduccion = new ArrayList<EstadoAjusteProduccionBean>();
	private List<Integer> anios = new ArrayList<Integer>();
	private List<String> meses = new ArrayList<String>();
	private List<PlantillaGrupoAjusteBean> gruposAjuste = new ArrayList<PlantillaGrupoAjusteBean>();
	private List<ProductoBean> productosGrupoAjuste = new ArrayList<ProductoBean>();
	private static Map<Long, List<ProductoBean>> productosGrupo = null;

	private DetalleAjusteProduccionMesBean ajuste = new DetalleAjusteProduccionMesBeanImpl();

	private String ejecutaConsulta;
	private String lineaSeleccionada;
	private String estadoAjusteProduccionSeleccionada;
	private Integer anioSeleccionado;
	private String mesSeleccionado;
	private String grupoAjusteSeleccionado;
	private String productoAjusteSeleccionado;

	public String getProductoAjusteSeleccionado() {
		return productoAjusteSeleccionado;
	}

	public void setProductoAjusteSeleccionado(String productoAjusteSeleccionado) {
		this.productoAjusteSeleccionado = productoAjusteSeleccionado;
	}

	public String getGrupoAjusteSeleccionado() {
		return grupoAjusteSeleccionado;
	}

	public void setGrupoAjusteSeleccionado(String grupoAjusteSeleccionado) {
		this.grupoAjusteSeleccionado = grupoAjusteSeleccionado;
	}

	/**
	 * Método que permite asignar privilegios de aplicación al usuario dle
	 * action
	 * 
	 * @throws AplicacionGlobalException
	 */
	public void prepare() throws AplicacionGlobalException {
		asignaPrivilegios();
	}

	/**
	 * Método encargado de listar las lineas de negocio propias del usuario, los
	 * estados posibles del ajuste y los meses y años de periodo contable
	 * abierto
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String listarParametrosAjusteProduccion() throws AplicacionGlobalException {

		verificarExitoOperacion();

		try {
			inicializarCombos();

		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(mensajeError, e);
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
		}

		return SUCCESS;
	}

	/*
	 * Setea los datos iniciales para los combos de la página
	 */
	private void inicializarCombos() throws LogicaException {
		// 1. Se cargan las lineas de negocio
		lineas = lineaNegocioFacade.obtenerLineaNegocioPorUsuario(usuario);
		// 2. Se definen los estado de la produccion
		estadosAjusteProduccion = estadoAjusteProduccionFacade.obtenerEstadosAjusteProduccion();
		// 3. Se establecen los meses
		meses = periodoContableFacade.obtenerMesesPorPeriodoAbierto();
		// 4. Se establecen los años
		anios = periodoContableFacade.obtenerAnosPorPeriodoAbierto();
	}

	/**
	 * Método para listar los ajuste de la producción mensual
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String listarAjusteProduccionMes() throws AplicacionGlobalException {
		try {
			inicializarCombos();

			if (getLineaSeleccionada() != null) {

				Short mes = null;

				if (mesSeleccionado != null && mesSeleccionado.length() > 0) {
					mes = FechaUtil.mesToShort(mesSeleccionado);
				}

				Long estado = null;

				if (estadoAjusteProduccionSeleccionada != null && estadoAjusteProduccionSeleccionada.length() > 0) {
					estado = Long.valueOf(estadoAjusteProduccionSeleccionada);
				}

				ajustesProduccionDetalle = ajusteProduccionFacade.obtenerAjusteProduccionMesPorLineaNegocioYEstado(
						Long.parseLong(getLineaSeleccionada()), anioSeleccionado, mes, estado);

				if (ajustesProduccionDetalle == null || ajustesProduccionDetalle.size() == 0)
					addActionMessage(getText(NO_DISPONE_REGISTROS_ASOCIADOS));

			} else {
				String mensajeError = "Datos Seleccionados Insuficientes.";
				addActionError(mensajeError);
			}
		} catch (NumberFormatException e) {
			mensajeError = getText(FALLA_CONVERSION_NUMERICA);
			addActionError(mensajeError);
			logger.error(mensajeError);
			throw new AplicacionGlobalException(mensajeError, e);
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(mensajeError, e);
		}
		return SUCCESS;
	}

	/**
	 * Método para listar los grupos de Ajuste asociados a la linea de negocio
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String listarGrupoAjuste() {
		if (getLineaSeleccionada() != null) {
			try {
				gruposAjuste = ajusteProduccionFacade.obtenerGruposProducto(Long.parseLong(getLineaSeleccionada()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LogicaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cargarProductos();
		}

		return SUCCESS;
	}

	/**
	 * Método para listar los productos asociados al grupo de Ajuste relacionado
	 * con la linea de negocio
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String listarGrupoAjusteProduccion() {

		if (grupoAjusteSeleccionado != null && grupoAjusteSeleccionado != "-1")
			productosGrupoAjuste = productosGrupo.get(Long.parseLong(getGrupoAjusteSeleccionado()));
		else
			productosGrupoAjuste = new ArrayList<ProductoBean>();

		return SUCCESS;
	}

	/**
	 * Método para listar los parametros requeridos para alimentar el formulario
	 * que apoya el registro de de ajuste de producción mensual
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String formularioIngresar() throws AplicacionGlobalException {

		listarParametrosAjusteProduccion();

		return SUCCESS;
	}

	/**
	 * Método para redireccionar a la pagina de registro de ajuste de produccion
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String formularioRegistroAjusteProduccion() {

		return SUCCESS;
	}

	/**
	 * Método para redireccionar a la pagina de registro de ajuste de produccion
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String formularioRegistroAjustePorProducto() {

		return SUCCESS;
	}

	/**
	 * Método para obtener al ajuste aprobar
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String formularioAprobar() {

		for (int i = 0; i < ajustesProduccionDetalle.size(); i++) {
			DetalleAjusteProduccionMesBean detalle = ajustesProduccionDetalle.get(i);
			if (detalle.getCodigoAjuste().equals(ajuste.getCodigoAjuste())) {
				ajuste = detalle;
				break;
			}
		}

		return SUCCESS;
	}

	public String eliminarAjusteProduccionMes() {

		// Validar estado ajuste produccion
		try {

			inicializarCombos();
			AjusteProduccionBean ajusteProduccionBean = ajusteProduccionFacade.obtenerAjustePorCodigo(ajuste.getCodigoAjuste());
			String estadoAprobado = ManejadorPropiedades.obtenerPropiedadPorClave(PARTETECNICO_CONSTANTE_APROBADO);
			if (ajusteProduccionBean.getEstadoajusteproduccion().getNombre().equals(estadoAprobado)) {
				addActionError(getText(PARTETECNICO_AJUSTE_REGRISTRO_APROBADO));
				return SUCCESS;
			}
			addActionMessage(ManejadorPropiedades.obtenerPropiedadPorClave(EXITO_OPERACION_REALIZADA));
			ajusteProduccionFacade.eliminarAjustePorCodigo(ajuste.getCodigoAjuste(), getUsuario());
			ajustesProduccionDetalle = new ArrayList<DetalleAjusteProduccionMesBean>();
		} catch (LogicaException e) {
			logger.error(e);
			addActionError(e.getMensaje());
		} catch (Exception e) {
			logger.error(e);
			addActionError(ManejadorPropiedades.obtenerPropiedadPorClave(FALLA_PRODUCTO_FALLO_OPERACION));
		}

		return SUCCESS;
	}

	private String log = "";
	private boolean activoLog;

	/**
	 * @return the activoLog
	 */
	public boolean isActivoLog() {
		return activoLog;
	}

	/**
	 * @param activoLog the activoLog to set
	 */
	public void setActivoLog(boolean activoLog) {
		this.activoLog = activoLog;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * Método para aprobar el ajuste
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String aprobar() throws AplicacionGlobalException {
		AjusteProduccionBean ajusteProduccion = null;

		try {
			ajusteProduccion = ajusteProduccionFacade.obtenerAjustePorCodigo(ajuste.getCodigoAjuste());

			
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(mensajeError, e);
		}
		try {


			Object[] listaResultado = ajusteProduccionFacade.aprobarAjusteProduccion(ajusteProduccion.getCodigo(), getUsuario());

			Boolean resultadoOperacion = Boolean.FALSE;

			if (listaResultado != null) {
				if (listaResultado[0] != null) {

					resultadoOperacion = ((Boolean) (listaResultado[0]));
				}
			}

			if (resultadoOperacion) {

				addActionMessage(ManejadorPropiedades.obtenerPropiedadPorClave(EXITO_OPERACION_REALIZADA));

			} else {

				addActionError(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_ENVIO_SAP));

			}

			if (listaResultado[1] != null) {
				List<ResultadoBeanImpl> logLista = (List<ResultadoBeanImpl>) listaResultado[1];
				for (ResultadoBeanImpl resultadoBeanImpl : logLista) {
					log += resultadoBeanImpl.getMensajeOperacion();

				}

			}
			activoLog = true;

		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(mensajeError, e);
		} finally {

			
		}

		return SUCCESS;
	}

	/**
	 * Método que activará el registro fisico del ajuste de la produccion
	 * mensual
	 * 
	 * @return
	 * @throws AplicacionException
	 */
	public String ingresar() {

		setExitoOperacion();

		return SUCCESS;
	}

	/**
	 * Método para cargar la produccion asociada al grupo de ajuste de la linea
	 * de negocio definida
	 */
	private void cargarProductos() {
		List<ProductoBean> produccionGrupoAjuste = new ArrayList<ProductoBean>();

		if (gruposAjuste != null) {
			productosGrupo = new HashMap<Long, List<ProductoBean>>();

			for (int indice = 0; indice < gruposAjuste.size(); indice++) {
				try {
					produccionGrupoAjuste = ajusteProduccionFacade.obtenerProductoGrupos(gruposAjuste.get(indice).getCodigo());
				} catch (LogicaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (produccionGrupoAjuste != null && produccionGrupoAjuste.size() > 0)
					productosGrupo.put(gruposAjuste.get(indice).getCodigo(), produccionGrupoAjuste);
			}
		}
	}

	private List<AjusteProductoBean> ajusteProductos;

	public String formularioGestionarParteTecnico() {
		try {
			verificarExitoOperacion();
			inicializarCombos();
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String filtrarAjusteProducto() throws AplicacionGlobalException {
		try {
			inicializarCombos();
			if (getLineaSeleccionada() != null) {

				Short mes = null;

				if (mesSeleccionado != null && mesSeleccionado.length() > 0) {
					mes = FechaUtil.mesToShort(mesSeleccionado);
				}

				Long estado = null;

				if (estadoAjusteProduccionSeleccionada != null && estadoAjusteProduccionSeleccionada.length() > 0) {
					estado = Long.valueOf(estadoAjusteProduccionSeleccionada);
				}

				ajusteProductos = ajusteProductoFacade.obtenerAjusteProducto(Long.parseLong(getLineaSeleccionada()),
						anioSeleccionado, mes, estado);

				if (ajusteProductos == null && ajusteProductos.size() == 0)
					addActionMessage(getText(NO_DISPONE_REGISTROS_ASOCIADOS));

			} else {
				String mensajeError = "Datos Seleccionados Insuficientes.";
				addActionError(mensajeError);
			}
		} catch (NumberFormatException e) {
			mensajeError = getText(FALLA_CONVERSION_NUMERICA);
			addActionError(mensajeError);
			logger.error(mensajeError);
			throw new AplicacionGlobalException(mensajeError, e);
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(mensajeError, e);
		}
		return SUCCESS;
	}

	public String formularioEliminarAjusteProducto() {

		return SUCCESS;
	}

	public String eliminarAjusteProducto() {

		if (ajuste != null && ajuste.getCodigoAjuste() != null) {
			try {
				AjusteProductoBean ajusteProductoBean;

				ajusteProductoBean = ajusteProductoFacade.obtenerPorCodigo(ajuste.getCodigoAjuste());

				if (ajusteProductoBean != null) {

					String estadoAprobado = ManejadorPropiedades.obtenerPropiedadPorClave(PARTETECNICO_CONSTANTE_APROBADO);

					if (!ajusteProductoBean.getAjusteproduccion().getEstadoajusteproduccion().getNombre().equals(estadoAprobado)) {

						ajusteProductoFacade.eliminarAjusteProducto(ajuste.getCodigoAjuste(), getUsuarioSession());
						setExitoOperacion();
					} else {
						addActionError(getText(PARTETECNICO_REGRISTRO_APROBADO));
						return INPUT;
					}
				} else {
					addActionError(getText(PARTETECNICO_REGRISTRO_NO_ENCONTRADO));
					return INPUT;
				}
			} catch (LogicaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			addActionError(getText(PARTETECNICO_CODIGO_NO_VALIDO));
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * @return the ajusteProductos
	 */
	public List<AjusteProductoBean> getAjusteProductos() {
		return ajusteProductos;
	}

	/**
	 * @param ajusteProductos the ajusteProductos to set
	 */
	public void setAjusteProductos(List<AjusteProductoBean> ajusteProductos) {
		this.ajusteProductos = ajusteProductos;
	}

	public static List<DetalleAjusteProduccionMesBean> getAjustesProduccionDetalle() {
		return ajustesProduccionDetalle;
	}

	public static void setAjustesProduccionDetalle(List<DetalleAjusteProduccionMesBean> ajustesProduccionDetalle) {
		AjusteProduccionMesAction.ajustesProduccionDetalle = ajustesProduccionDetalle;
	}

	public List<LineaNegocioBean> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaNegocioBean> lineas) {
		this.lineas = lineas;
	}

	public String getLineaSeleccionada() {
		return lineaSeleccionada;
	}

	public void setLineaSeleccionada(String lineaSeleccionada) {
		this.lineaSeleccionada = lineaSeleccionada;
	}

	public String getEstadoAjusteProduccionSeleccionada() {
		return estadoAjusteProduccionSeleccionada;
	}

	public void setEstadoAjusteProduccionSeleccionada(String estadoAjusteProduccionSeleccionada) {
		this.estadoAjusteProduccionSeleccionada = estadoAjusteProduccionSeleccionada;
	}

	public List<EstadoAjusteProduccionBean> getEstadosAjusteProduccion() {
		return estadosAjusteProduccion;
	}

	public void setEstadosAjusteProduccion(List<EstadoAjusteProduccionBean> estadosAjusteProduccion) {
		this.estadosAjusteProduccion = estadosAjusteProduccion;
	}

	public List<Integer> getAnios() {
		return anios;
	}

	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}

	public List<String> getMeses() {
		return meses;
	}

	public void setMeses(List<String> meses) {
		this.meses = meses;
	}

	public Integer getAnioSeleccionado() {
		return anioSeleccionado;
	}

	public void setAnioSeleccionado(Integer anioSeleccionado) {
		this.anioSeleccionado = anioSeleccionado;
	}

	public String getMesSeleccionado() {
		return mesSeleccionado;
	}

	public void setMesSeleccionado(String mesSeleccionado) {
		this.mesSeleccionado = mesSeleccionado;
	}

	public String getEjecutaConsulta() {
		return ejecutaConsulta;
	}

	public void setEjecutaConsulta(String ejecutaConsulta) {
		this.ejecutaConsulta = ejecutaConsulta;
	}

	public DetalleAjusteProduccionMesBean getAjuste() {
		return ajuste;
	}

	public void setAjuste(DetalleAjusteProduccionMesBean ajuste) {
		this.ajuste = ajuste;
	}

	public List<PlantillaGrupoAjusteBean> getGruposAjuste() {
		return gruposAjuste;
	}

	public void setGruposAjuste(List<PlantillaGrupoAjusteBean> gruposAjuste) {
		this.gruposAjuste = gruposAjuste;
	}

	public List<ProductoBean> getProductosGrupoAjuste() {
		return productosGrupoAjuste;
	}

	public void setProductosGrupoAjuste(List<ProductoBean> productosGrupoAjuste) {
		this.productosGrupoAjuste = productosGrupoAjuste;
	}

	public static Map<Long, List<ProductoBean>> getProductosGrupo() {
		return productosGrupo;
	}

	public static void setProductosGrupo(Map<Long, List<ProductoBean>> productosGrupo) {
		AjusteProduccionMesAction.productosGrupo = productosGrupo;
	}

}
