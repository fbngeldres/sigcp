package pe.com.pacasmayo.sgcp.presentacion.action.parteTecnico;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteConsumidoBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteConsumoBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoCombustibleBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteProductoBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoProductoBean;
import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoConsumoComponentesBean;
import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoPuestoTrabajoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.MesBean;
import pe.com.pacasmayo.sgcp.bean.OrdenReporteBean;
import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ReporteAnexo_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteCombustibles_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteHojaCal_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteTecnicoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteTecnicoOperacionesSub_B_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteTecnicoSub_A_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteTecnicoSub_B_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteTecnicoSub_B_Detalle_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteProductoTerminado_Bean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.SubReporteCombustibleBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.impl.ComponenteConsumidoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ComponenteConsumoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.MesBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ProductoBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ReporteConsumoVentasCal_List;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.DivisionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.GenerarParteTecnicoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.OrdenReporteLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PeriodoContableLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.SociedadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.SociedadLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProduccionMesLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.GenerarParteTecnicoLogic;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.OrdenReporteLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.logica.stock.PeriodoContableLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProcesoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

import com.opensymphony.xwork2.Preparable;

public class GenerarParteTecnicoAction extends AplicacionAction implements ConstantesLogicaNegocio, Preparable {
	private static final long serialVersionUID = 1L;

	private String mensajeError = "";
	private Log logger = LogFactory.getLog(this.getClass());

	private static LineaNegocioLogicFacade lineaNegocioFacade = new LineaNegocioLogic();
	private static PeriodoContableLogicFacade periodoContableFacade = new PeriodoContableLogic();
	private static AjusteProduccionMesLogicFacade ajusteProduccionFacade = new AjusteProduccionMesLogic();
	private static GenerarParteTecnicoLogicFacade generarParteTecnicoFacde = new GenerarParteTecnicoLogic();
	private static OrdenReporteLogicFacade ordenLogicFacade = new OrdenReporteLogic();
	private static ProductoLogicFacade productoLogicFacade = new ProductoLogic();
	private List<LineaNegocioBean> lineas = new ArrayList<LineaNegocioBean>();
	private static ParametroSistemaLogicFacade parametroLogic = new ParametroSistemaLogic();
	private List<DetalleParteTecnicoConsumoComponentesBean> parteTecnicoComponentes = new ArrayList<DetalleParteTecnicoConsumoComponentesBean>();
	private List<DetalleParteTecnicoPuestoTrabajoComponenteBean> parteTecnicoPuestoTrabajoComponentes = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
	private List<DetalleParteTecnicoPuestoTrabajoComponenteBean> parteTecnicoConsumoPuestoTrabajoComponentes = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();

	private List<ConsumoProductoBean> parteTecnicoConsumoCombustible = new ArrayList<ConsumoProductoBean>();
	private List<ComponenteConsumidoBean> parteTecnicoAnexoCrudo = new ArrayList<ComponenteConsumidoBean>();
	private List<ComponenteConsumidoBean> parteTecnicoAnexoClinker = new ArrayList<ComponenteConsumidoBean>();
	private List<ComponenteConsumidoBean> parteTecnicoAnexoCemento = new ArrayList<ComponenteConsumidoBean>();
	private List<String> combustibles = new ArrayList<String>();
	private List<ReporteProductoTerminado_Bean> listReporteProductoTerminado = new ArrayList<ReporteProductoTerminado_Bean>();
	// private List<ReporteConsumosVentasCal_Bean> listReporteConsumosVentasCal
	// = new ArrayList<ReporteConsumosVentasCal_Bean>();

	private List<ReporteConsumoVentasCal_List> listaVentasConsumoCal = new ArrayList<ReporteConsumoVentasCal_List>();
	private String ejecutaConsulta;
	private String lineaSeleccionada;
	private Integer anioSeleccionado = -1;
	private String mesSeleccionado = "-1";
	private String productoSeleccionado = "-1";
	private String productoSeleccionadoTemp;
	private List<String> nombreTiposCemento;
	private List<String> nombreTiposCrudo;
	private List<String> nombreTiposClinker;

	private static DivisionLogicFacade divisionFacade = new DivisionLogic();
	private static SociedadLogicFacade sociedadFacade = new SociedadLogic();
	private static UnidadLogicFacade unidadFacade = new UnidadLogic();

	private String valorInforme;
	private Long valorDivision;
	private Long valorSociedad;
	private Long valorUnidad;
	private Long valorLineaNegocio;
	private Integer valorAnnio;
	private Short valorMes;
	private Long valorProducto;

	private String valorInformeFiltrado;
	private Long valorDivisionFiltrado;
	private Long valorSociedadFiltrado;
	private Long valorUnidadFiltrado;
	private Long valorLineaNegocioFiltrado;
	private Integer valorAnnioFiltrado;
	private Short valorMesFiltrado;
	private Long valorProductoFiltrado;

	private String mensajeErrorValidacion = getText(CONST_MENSAJE_ERROR);
	private String tituloCampoDivision = getText(CONST_DIVISION);
	private String tituloCampoSociedad = getText(CONST_SOCIEDAD);
	private String tituloCampoUnidad = getText(CONST_UNIDAD);
	private String tituloCampoLineaNegocio = getText(CONST_LINEANEGOCIO);
	private String tituloCampoAnio = getText(CONST_ANIO);
	private String tituloCampoMes = getText(CONST_MES);
	private String tituloCampoInforme = getText(CONST_INFORME);

	private String NOMBRE_RECORTAR = "Cemento ";

	private List<DivisionBean> divisiones = new ArrayList<DivisionBean>();
	private List<SociedadBean> sociedades = new ArrayList<SociedadBean>();
	private List<UnidadBean> unidades = new ArrayList<UnidadBean>();
	private List<LineaNegocioBean> lineasNegocio = new ArrayList<LineaNegocioBean>();
	private List<Integer> anios = new ArrayList<Integer>();
	private List<MesBean> meses = new ArrayList<MesBean>();
	private List<UtilBean> informes = new ArrayList<UtilBean>();
	private List<ProductoBean> productos = new ArrayList<ProductoBean>();

	private String tipoReporte;
	private Integer xlsPDF;
	private ReporteAjusteProduccionBean reporteAjuste;
	private String mensajeErrorReporte;
	ReporteParteTecnicoBean reporte;
	private FileResolver REPORT_FILE_RESOLVER;

	/**
	 * Método que permite asignar privilegios de aplicación al usuario dle
	 * action
	 * 
	 * @throws AplicacionGlobalException
	 */
	public void prepare() throws AplicacionGlobalException {
		asignaPrivilegios();
	}

	public String doListar() throws AplicacionGlobalException {

		verificarExitoOperacion();

		// 1. Se cargan las Divisones
		try {
			if (esUsuarioAdmin()) {
				divisiones = divisionFacade.obtenerDivisiones();
				sociedades = sociedadFacade.obtenerSociedades();
				unidades = unidadFacade.obtenerUnidades();

				if (divisiones != null && divisiones.size() > 0)
					setValorDivision(Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_DIVISION)));
				if (sociedades != null && sociedades.size() > 0)
					setValorSociedadFiltrado(Long.valueOf(ManejadorPropiedades
							.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_SOCIEDAD)));
				if (unidades != null && unidades.size() > 0)
					setValorUnidadFiltrado(Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_POR_DEFECTO_UNIDAD)));
			} else {
				divisiones = getUsuarioCargo().getDivisionBeanList();

				setValorDivision(usuario.getPersona().getCargo().getDivisionCargoBean().getDivisionBean().getCodigo());
				setValorSociedadFiltrado(usuario.getPersona().getCargo().getSociedadCargoBean().getSociedadBean().getCodigo());
				setValorUnidadFiltrado(usuario.getPersona().getCargo().getUnidadCargoBean().getUnidadBean().getCodigo());
			}

			// 2. Se establecen los meses

			cargarMeses();

			// 3. Se establecen los años
			anios = periodoContableFacade.obtenerAnosPorPeriodoAbierto();

			// 4. Se establecen los informes
			cargarComboInformes();

			valorInforme = "";
			lineasNegocio = new ArrayList<LineaNegocioBean>();
			productos = new ArrayList<ProductoBean>();

		} catch (LogicaException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	private void cargarMeses() {

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

	}

	/**
	 * Este metodo carga las sociedades de acuerdo a la division seleccionada
	 * por el usuario
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargarSociedades() throws AplicacionGlobalException {
		try {
			if (getValorDivision() != null) {
				sociedades = sociedadFacade.obtenerSociedadesPorCodigoDivision(getValorDivision());
			}

		} catch (LogicaException e) {
			e.printStackTrace();
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * Este metodo carga las unidad de acuerdo a la sociedad seleccionada por el
	 * usuario
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargarUnidades() throws AplicacionGlobalException {

		try {
			if (valorSociedad != null) {

				unidades = unidadFacade.obtenerUnidadesPorCodigoSociedad(valorSociedad);
				setValorSociedadFiltrado(valorSociedad);
			} else if (getValorSociedad() != null) {

				unidades = unidadFacade.obtenerUnidadesPorCodigoSociedad(getValorSociedad());
			} else {

				unidades = null;

				setValorUnidad(null);
				setValorUnidadFiltrado(null);
				setValorLineaNegocioFiltrado(null);
				setValorLineaNegocio(null);
			}

		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Este metodo carga las lineas de negocio de acuerdo a la unidad
	 * seleccionada por el usuario
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargarLineasNegocio() throws AplicacionGlobalException {

		try {
			if (valorUnidad != null) {

				lineasNegocio = lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(valorUnidad);
				setValorUnidadFiltrado(valorUnidad);
			} else if (getValorUnidad() != null) {

				lineasNegocio = lineaNegocioFacade.obtenerLineaNegocioPorCodigoUnidad(getValorUnidad());
			}

			if (getValorLineaNegocio() == null) {
				if (lineasNegocio != null && lineasNegocio.size() == 1) {
					setValorLineaNegocioFiltrado(lineasNegocio.get(0).getCodigo());
				}
			}
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Este metodo carga los productos
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargarProductos() {

		if (valorLineaNegocio != null) {

			if (valorInforme.equals("1"))
				try {
					productos = ajusteProduccionFacade.obtenerProductosLineaNegocio(valorLineaNegocio);
				} catch (LogicaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if (valorInforme.equals("2"))
				productos = new ArrayList<ProductoBean>();
		} else if (valorInforme != null && valorInforme.equals("1")) {
			valorLineaNegocio = Long.valueOf(-1);
			try {
				productos = ajusteProduccionFacade.obtenerProductosLineaNegocio(valorLineaNegocio);
			} catch (LogicaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (valorInforme != null && valorInforme.equals("2")) {
			productos = new ArrayList<ProductoBean>();
		}

		return SUCCESS;
	}

	public String cargarComboOculto() {
		return SUCCESS;
	}

	private void cargarComboInformes() {

		informes = new ArrayList<UtilBean>();

		UtilBean informe = new UtilBeanImpl();
		informe.setCodigo(0);
		informe.setValor("Resumen");
		informes.add(informe);

		informe = new UtilBeanImpl();
		informe.setCodigo(1);
		informe.setValor("Diferencias");
		informes.add(informe);
	}

	/**
	 * Método para listar los Productos
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String listarProductos() throws AplicacionGlobalException {

		try {

			if (valorInforme.equals("1")) {

				if (lineaSeleccionada != null && lineaSeleccionada.trim().length() > 0 && !lineaSeleccionada.equals("-1")) {
					productos = ajusteProduccionFacade.obtenerProductosLineaNegocio(Long.parseLong(lineaSeleccionada));

					ProductoBean productoGeneral = new ProductoBeanImpl();
					productoGeneral.setCodigo(new Long(0));
					productoGeneral.setNombre("Resumen");
					productos.add(productoGeneral);
				}
			} else if (valorInforme.equals("2")) {
				if (lineaSeleccionada != null && lineaSeleccionada.trim().length() > 0 && !lineaSeleccionada.equals("-1")) {
					productos = ajusteProduccionFacade.obtenerProductosLineaNegocioYClaseProducto(valorLineaNegocio,
							Long.parseLong(getText(CODIGO_TIPO_CLASIFICACION_PRODUCTO_COMBUSTIBLE)));

					ProductoBean productoGeneral = new ProductoBeanImpl();
					productoGeneral.setCodigo(new Long(0));
					productoGeneral.setNombre("Resumen");
					productos.add(productoGeneral);
				}
			}

			if (productoSeleccionadoTemp != null && !productoSeleccionadoTemp.equals("-1")
					&& (productoSeleccionado == null || productoSeleccionado.equals("-1")))
				setProductoSeleccionado(productoSeleccionado);

		} catch (NumberFormatException e) {
			mensajeError = getText(FALLA_CONVERSION_NUMERICA);
			addActionError(mensajeError);
			logger.error(mensajeError);
			throw new AplicacionGlobalException(mensajeError, e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addActionError(e.getMessage());
			throw new AplicacionGlobalException(mensajeError, e);
		}
		return SUCCESS;
	}

	/**
	 * Obtiene el cosumo de componentes de clinker
	 * 
	 * @param codigoLinea
	 * @param codigoUnidad
	 * @param estadoParteTec2
	 */
	private void ObtenerConsumoComponenteProductoClinker(Long codigoLinea, Long codigoUnidad, Long estadoParteTec2) {
		try {
			Map<String, List<ConsumoComponenteProductoBean>> mapaConsumo = generarParteTecnicoFacde
					.obtenerConsumoConponenteGrupoClinker(codigoLinea, codigoUnidad, Long.valueOf(valorSociedad),
							Long.valueOf(valorDivision), valorAnnio, valorMes, estadoParteTec2);
			if (mapaConsumo == null || mapaConsumo.isEmpty()) {
				parteTecnicoAnexoClinker = new ArrayList<ComponenteConsumidoBean>();
				nombreTiposClinker = new ArrayList<String>();
			} else {
				nombreTiposClinker = new ArrayList<String>();
				nombreTiposClinker.add(getText(PARTE_TECNICO_PRODUCCION_CLINKER));
				List<ProductoBean> productosBean = obtenerListaProductos(mapaConsumo);
				obtenerConsumoComponente(mapaConsumo, productosBean, parteTecnicoAnexoClinker, nombreTiposClinker);
			}

		} catch (LogicaException e) {
			mensajeError = getText(ConstantesLogicaNegocio.NOMBRE_GRUPO_CLINKER) + " - " + e.getMensaje();
			addActionError(mensajeError);
		}
	}

	/**
	 * Obtiene el consumo de componentes de cemento
	 * 
	 * @param codigoLinea
	 * @param codigoUnidad
	 * @param estadoParteTec2
	 */
	private void ObtenerConsumoComponenteProductoCemento(Long codigoLinea, Long codigoUnidad, Long estadoParteTec) {
		try {
			Map<String, List<ConsumoComponenteProductoBean>> mapaConsumo = generarParteTecnicoFacde
					.obtenerConsumoConponenteGrupoCemento(codigoLinea, codigoUnidad, Long.valueOf(valorSociedad),
							Long.valueOf(valorDivision), valorAnnio, valorMes, estadoParteTec);
			if (mapaConsumo == null || mapaConsumo.isEmpty()) {
				parteTecnicoAnexoCemento = new ArrayList<ComponenteConsumidoBean>();
				nombreTiposCemento = new ArrayList<String>();
			} else {
				nombreTiposCemento = new ArrayList<String>();
				nombreTiposCemento.add(getText(PARTE_TECNICO_PRODUCCION_CEMENTO));
				List<ProductoBean> productosBean = obtenerListaProductos(mapaConsumo);
				obtenerConsumoComponente(mapaConsumo, productosBean, parteTecnicoAnexoCemento, nombreTiposCemento);
				reducirNombre(NOMBRE_RECORTAR, nombreTiposCemento);
			}

		} catch (LogicaException e) {
			mensajeError = getText(ConstantesLogicaNegocio.NOMBRE_GRUPO_CEMENTO) + " - " + e.getMensaje();
			addActionError(mensajeError);
		}
	}

	private void reducirNombre(String quitarString, List<String> nombreTipos) {
		List<String> nombres = new ArrayList<String>();
		nombres.add(nombreTipos.get(0));
		for (int i = 1; i < nombreTipos.size(); i++) {
			String nombre = nombreTipos.get(i).replace(quitarString, "");
			nombre = nombre.replace(quitarString.toLowerCase(), "");
			nombre = nombre.replace(quitarString.toUpperCase(), "");
			nombres.add(nombre);
		}
		nombreTipos.clear();
		nombreTipos.addAll(nombres);
	}

	/**
	 * Obtiene el consumo de crudo
	 * 
	 * @param codigoLinea
	 * @param codigoUnidad
	 * @param estadoParteTec2
	 */
	private void ObtenerConsumoComponenteProductoCrudo(Long codigoLinea, Long codigoUnidad, Long estadoParteTec) {
		try {
			Map<String, List<ConsumoComponenteProductoBean>> mapaConsumo = generarParteTecnicoFacde
					.obtenerConsumoConponenteGrupoCrudo(codigoLinea, codigoUnidad, Long.valueOf(valorSociedad),
							Long.valueOf(valorDivision), valorAnnio, valorMes, estadoParteTec);

			if (mapaConsumo == null || mapaConsumo.isEmpty()) {
				parteTecnicoAnexoCrudo = new ArrayList<ComponenteConsumidoBean>();
				nombreTiposCrudo = new ArrayList<String>();
			} else {

				nombreTiposCrudo = new ArrayList<String>();
				nombreTiposCrudo.add(getText(PARTE_TECNICO_PRODUCCION_CRUDO));

				List<ProductoBean> productosBean = obtenerListaProductos(mapaConsumo);

				obtenerConsumoComponente(mapaConsumo, productosBean, parteTecnicoAnexoCrudo, nombreTiposCrudo);

			}

		} catch (LogicaException e) {
			e.printStackTrace();
			mensajeError = getText(ConstantesLogicaNegocio.NOMBRE_GRUPO_CRUDO) + " - " + e.getMensaje();
			addActionError(mensajeError);
		}
	}

	private List<ProductoBean> obtenerListaProductos(Map<String, List<ConsumoComponenteProductoBean>> mapaConsumo) {
		Map<Long, ProductoBean> productosAjustados = new HashMap<Long, ProductoBean>();
		Iterator it = mapaConsumo.entrySet().iterator();
		List<ConsumoComponenteProductoBean> lista;
		while (it.hasNext()) {
			Map.Entry llave = (Map.Entry) it.next();
			lista = mapaConsumo.get(llave.getKey());

			if (lista != null) {
				for (ConsumoComponenteProductoBean consumoComponenteProductoBean : lista) {
					ProductoBean producto = consumoComponenteProductoBean.getProducto();
					productosAjustados.put(producto.getCodigo(), producto);
				}
			}
		}
		List<ProductoBean> productos = new ArrayList<ProductoBean>(productosAjustados.values());
		Collections.sort(productos, new Comparator<ProductoBean>() {

			public int compare(ProductoBean o1, ProductoBean o2) {
				// TODO Auto-generated method stub
				return o1.getNombre().compareTo(o2.getNombre());
			}

		});
		return productos;
	}

	/**
	 * Arregla los datos de consumo para mostrar en pantalla con un limite de
	 * LIMITE_PRODUCTOS_ANEXO
	 * 
	 * @param mapaConsumo consumo de los componentes
	 * @param productosBean productos consumidores
	 * @param parteTecnicoAnexo lista donde se coloca el consumo
	 * @param nombreTipos objeto donde se colocan los nombres delos productos
	 *            consumidores
	 */
	private void obtenerConsumoComponente(Map<String, List<ConsumoComponenteProductoBean>> mapaConsumo,
			List<ProductoBean> productosBean, List<ComponenteConsumidoBean> parteTecnicoAnexo, List<String> nombreTipos)
			throws NumberFormatException, LogicaException {
		// Cantidad Decimales
		Integer cantidadDecimales;
		ParametroSistemaLogicFacade parametro = new ParametroSistemaLogic();
		// si hay consumos para cemento creo un mapa para controlar la posicion
		// de los tipos de producto final
		Map<Long, Integer> mapaTipoPosicion = new LinkedHashMap<Long, Integer>();

		int iLimite = productosBean.size();

		for (int i = 0; i < iLimite; i++) {
			ProductoBean producto = productosBean.get(i);
			mapaTipoPosicion.put(producto.getCodigo(), new Integer(i + 1));
			nombreTipos.add(producto.getNombre());
		}

		for (String componente : mapaConsumo.keySet()) {
			List<ConsumoComponenteProductoBean> consumos = mapaConsumo.get(componente);

			ComponenteConsumidoBean compConsumido = new ComponenteConsumidoBeanImpl();
			List<ComponenteConsumoBean> cantidadesCompConsumidos = new ArrayList<ComponenteConsumoBean>();

			// se define el nombre de la fila (materiales consumidos)
			ComponenteConsumoBean nombreComponenteConsumido = new ComponenteConsumoBeanImpl();
			nombreComponenteConsumido.setValorConsumo(componente);
			cantidadesCompConsumidos.add(0, nombreComponenteConsumido);

			// se definen los valores de consumo para cada uno de los materiales

			for (int i = 1; i <= iLimite; i++) {
				ComponenteConsumoBean cantCompConsumido = new ComponenteConsumoBeanImpl();
				cantCompConsumido.setValorConsumo("0.00");
				cantidadesCompConsumidos.add(i, cantCompConsumido);

				for (ConsumoComponenteProductoBean consumo : consumos) {
					Integer posicion = mapaTipoPosicion.get(consumo.getCodigoProductoConsumidor());

					if (i == posicion.intValue()) {
						cantidadDecimales = Integer.parseInt(parametro.obtenerParametroSistema(consumo.getUnidadMedida())
								.getValor());
						cantCompConsumido.setUnidadMedida(consumo.getUnidadMedida());
						cantCompConsumido.setValorConsumo(NumberUtil.redondeoReportePT(consumo.getConsumo(), cantidadDecimales,
								consumo.getUnidadMedida()));
						cantidadesCompConsumidos.set(i, cantCompConsumido);
						break;
					}
				}

				compConsumido.setConsumos(cantidadesCompConsumidos);
			}
			parteTecnicoAnexo.add(compConsumido);
		}

	}

	/**
	 * Valida si se colocaron los campos necesarios para combustible
	 * 
	 * @return true si estan todos los campos correctos
	 */
	private boolean validaCamposRequeridosCombustible() {

		if (valorDivision == null) {
			addFieldError("valorDivision", "Debe seleccionar una División.");
			return false;
		}
		if (valorSociedad == null) {
			addFieldError("valorSociedad", "Debe seleccionar una Sociedad.");
			return false;
		}
		if (valorAnnio == null) {
			addFieldError("valorAnnio", "Debe seleccionar el Año.");
			return false;
		}
		if (valorMes == null) {
			addFieldError("valorMes", "Debe seleccionar el Mes.");
			return false;
		}
		if (valorInforme == null || valorInforme.trim().length() <= 0 || valorInforme.equals("-1")) {
			addFieldError("valorInforme", "Debe seleccionar el Tipo de Informe.");
			return false;
		}
		return true;
	}

	/**
	 * Método para validar combos de presentación
	 * 
	 * @return
	 */
	public boolean validarCombos() {
		boolean valido = true;

		if (mesSeleccionado.equals("-1"))
			addActionMessage(getText(PARTETECNICO_MES_REQUERIDO));
		if (anioSeleccionado.toString().equals("-1"))
			addActionMessage(getText(PARTETECNICO_ANIO_REQUERIDO));
		if (productoSeleccionado.equals("-1"))
			addActionMessage(getText(PARTETECNICO_PRODUCTO_REQUERIDO));

		if (mesSeleccionado.equals("-1") || anioSeleccionado.equals("-1") || productoSeleccionado.equals("-1")) {

			valido = false;
		}
		return valido;
	}

	private List<ReporteParteTecnicoSub_A_Bean> generarListaReporteParteTecnicoResumen() {
		try {
			List<ReporteParteTecnicoSub_A_Bean> subReportePtComponentes = new ArrayList<ReporteParteTecnicoSub_A_Bean>();
			ReporteParteTecnicoSub_A_Bean reporteSubPT;
			// ParametroSistemaLogicFacade parametro = new
			// ParametroSistemaLogic();
			ParametroSistemaBean parametroBean = null;
			Integer dato = 0;
			for (DetalleParteTecnicoConsumoComponentesBean ptComponentes : parteTecnicoComponentes) {

				reporteSubPT = new ReporteParteTecnicoSub_A_Bean();
				reporteSubPT.setValor_1(ptComponentes.getTipoProducto());
				reporteSubPT.setValor_2(ptComponentes.getOrdenProceso());
				reporteSubPT.setValor_3(ptComponentes.getProceso());
				reporteSubPT.setValor_4(ptComponentes.getLinea());
				reporteSubPT.setValor_5(ptComponentes.getComponente());
				reporteSubPT.setValor_6(ptComponentes.getUnidadMedida());

				parametroBean = parametroLogic.obtenerParametroSistema(ptComponentes.getUnidadMedida());

				if (parametroBean != null && parametroBean.getValor() != null) {
					dato = Integer.parseInt(parametroLogic.obtenerParametroSistema(ptComponentes.getUnidadMedida()).getValor());
				} else {
					dato = ConstantesParametro.CANTIDAD_DECIMALES_SGCP_DEFAULT;
				}

				reporteSubPT.setValor_7(NumberUtil.redondeoReportePT(ptComponentes.getSaldoInicial(), dato,
						ptComponentes.getUnidadMedida()));

				reporteSubPT.setValor_8(NumberUtil.redondeoReportePT(ptComponentes.getIngreso(), dato,
						ptComponentes.getUnidadMedida()));

				reporteSubPT.setValor_9(NumberUtil.redondeoReportePT(ptComponentes.getConsumo(), dato,
						ptComponentes.getUnidadMedida()));
				reporteSubPT.setValor_10(NumberUtil.redondeoReportePT(ptComponentes.getSaldoFinal(), dato,
						ptComponentes.getUnidadMedida()));
				
				reporteSubPT.setValor_13(NumberUtil.redondeoReportePT(ptComponentes.getMovimientoLogistico(), dato,
						ptComponentes.getUnidadMedida()));
				
				
				reporteSubPT.setValor_11(NumberUtil.redondeoReportePT(ptComponentes.getProduccionAcumulada(), dato,
						ptComponentes.getUnidadMedida()));
				reporteSubPT.setValor_12(NumberUtil.redondeoReportePT(ptComponentes.getConsumoAcumulado(), dato,
						ptComponentes.getUnidadMedida()));

				subReportePtComponentes.add(reporteSubPT);
			}
			return subReportePtComponentes;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	private List<ReporteParteTecnicoSub_A_Bean> generarListaReporteParteTecnico() {
		try {
			List<ReporteParteTecnicoSub_A_Bean> subReportePtComponentes = new ArrayList<ReporteParteTecnicoSub_A_Bean>();
			ReporteParteTecnicoSub_A_Bean reporteSubPT;
			// ParametroSistemaLogicFacade parametro = new
			// ParametroSistemaLogic();
			boolean primeraFila = true;
			for (DetalleParteTecnicoConsumoComponentesBean ptComponentes : parteTecnicoComponentes) {

				reporteSubPT = new ReporteParteTecnicoSub_A_Bean();
				reporteSubPT.setValor_1(ptComponentes.getTipoProducto());
				reporteSubPT.setValor_2(ptComponentes.getOrdenProceso());
				reporteSubPT.setValor_3(ptComponentes.getProceso());
				reporteSubPT.setValor_4(ptComponentes.getLinea());
				reporteSubPT.setValor_5(ptComponentes.getComponente());
				reporteSubPT.setValor_6(ptComponentes.getUnidadMedida());

				Integer dato = Integer.parseInt(parametroLogic.obtenerParametroSistema(ptComponentes.getUnidadMedida())
						.getValor());
				reporteSubPT.setValor_7(NumberUtil.redondeoReportePT(ptComponentes.getSaldoInicial(), dato,
						ptComponentes.getUnidadMedida()));
				if (primeraFila) {
					reporteSubPT.setValor_8(NumberUtil.redondeoReportePT(ptComponentes.getIngreso(), dato,
							ptComponentes.getUnidadMedida()));
					primeraFila = false;
				} else {
					reporteSubPT.setValor_8(NumberUtil.redondeoReportePT(ptComponentes.getConsumo(), dato,
							ptComponentes.getUnidadMedida()));
				}

				reporteSubPT.setValor_9(NumberUtil.redondeoReportePT(ptComponentes.getConsumo(), dato,
						ptComponentes.getUnidadMedida()));
				reporteSubPT.setValor_10(NumberUtil.redondeoReportePT(ptComponentes.getSaldoFinal(), dato,
						ptComponentes.getUnidadMedida()));
				reporteSubPT.setValor_11(NumberUtil.redondeoReportePT(ptComponentes.getProduccionAcumulada(), dato,
						ptComponentes.getUnidadMedida()));
				reporteSubPT.setValor_12(NumberUtil.redondeoReportePT(ptComponentes.getConsumoAcumulado(), dato,
						ptComponentes.getUnidadMedida()));

				subReportePtComponentes.add(reporteSubPT);
			}
			return subReportePtComponentes;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	private String formarNombreEstado() {
		List<AjusteProduccionBean> ajustesEstados = null;
		String estado = "Generado";
		try {
			ajustesEstados = ajusteProduccionFacade.obtenerAjustePorduccionPorPerdiodoLineaNegocio(getValorLineaNegocio(),
					getValorMes(), getValorAnnio());
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
		}

		if (ajustesEstados != null && ajustesEstados.size() > 0) {
			if (ajustesEstados.size() == 1) {
				estado = ajustesEstados.get(0).getEstadoajusteproduccion().getNombre();
			} else {
				// SI estados son iguales
				if (ajustesEstados.get(0).getEstadoajusteproduccion().getNombre()
						.equals(ajustesEstados.get(1).getEstadoajusteproduccion().getNombre())) {
					estado = ajustesEstados.get(0).getEstadoajusteproduccion().getNombre();
				} else {
					for (int i = 0; i < ajustesEstados.size(); i++) {
						AjusteProduccionBean ajusteProduccionBean = ajustesEstados.get(i);
						if ((i + 1) == ajustesEstados.size()) {
							estado += ajusteProduccionBean.getLineanegocio().getNombre() + " : "
									+ ajusteProduccionBean.getEstadoajusteproduccion().getNombre();
						} else {
							estado += ajusteProduccionBean.getLineanegocio().getNombre() + " : "
									+ ajusteProduccionBean.getEstadoajusteproduccion().getNombre() + " - ";
						}

					}
				}
			}
		}

		return estado;
	}

	public String exportarPdf() {
		try {

			String REDIRECCION = ERROR;
			String RUTA_CARPETA = "";
			String nombreDivision = "";
			String nombreSociedad = "";
			String nombreUnidad = "";
			String nombreLineaNegocio = "";
			String estado = "";

			if (xlsPDF != null) {
				if (xlsPDF == 1) {
					tipoReporte = "PDF";
				} else {
					tipoReporte = "XLS";
				}
			}

			try {
				// REDIRECCION = doFiltrar();
				nombreDivision = divisionFacade.obtenerDivision(getValorDivision()).getNombre();
				nombreSociedad = sociedadFacade.obtenerSociedad(getValorSociedad()).getNombre();

				if (getValorUnidad().compareTo(Long.valueOf(-1)) != 0) {
					nombreUnidad = unidadFacade.obtenerUnidad(Long.valueOf(getValorUnidad())).getNombre();
				}
				if (getValorLineaNegocio() == null) {
					setValorLineaNegocio(Long.valueOf(-1));
				}
				if (getValorLineaNegocio().compareTo(Long.valueOf(-1)) != 0) {
					nombreLineaNegocio = lineaNegocioFacade.obtenerLineaNegocio(Long.valueOf(getValorLineaNegocio())).getNombre();
				}

				estado = formarNombreEstado();

			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
			} catch (LogicaException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}

			if (valorInforme.equals("0")) {
				// Informe Resumen
				String titulo = ManejadorPropiedades.obtenerPropiedadPorClave("reporte.parte.tecnico.titulo.producto.terminado");
				titulo = MessageFormat.format(titulo, FechaUtil.numeroMesANombreMes(getValorMes()), getValorAnnio().toString());
				reporte = new ReporteParteTecnicoBean(titulo, nombreDivision, nombreSociedad, nombreUnidad, nombreLineaNegocio,
						"Producto", FechaUtil.numeroMesANombreMes(getValorMes()), getValorAnnio().toString(), estado);
				generarReporteResumen();

				// Consumo por Puesto de Trabajo
				generarReporteConsumoPuestoTrabajo();

				// Informe Producto Terminado
				reporte.setReporteProductoTerminado(generarReporteProductoTerminado());

				// Informe Hoja Cal - PT y Consumos_Ventas_Cal
				reporte.setReporteHojaCal(generarReporteHojaCal());

				// Combustible

				reporte.setReporteCombustibles(generarReporteCombustibles());

				// Anexo
				cargarConsumoAnexo();
				reporte.setReporteAnexo(generarReporteAnexo());

			
				REDIRECCION = "resumen";
				RUTA_CARPETA = "/reportes/jasperReport/parteTecnico/resumen/";
			} else if (valorInforme.equals("1")) {
				// Diferencias
				reporteAjuste = ajusteProduccionFacade.obtenerReporteAjusteProduccion(valorAnnio, valorMes, valorLineaNegocio);
				REDIRECCION = "diferencia";
				RUTA_CARPETA = "/reportes/jasperReport/reporteAjustes/";

			}

			// se obtiene el contexto para ubicar la direccion de los reportes
			ServletContext context = org.apache.struts2.ServletActionContext.getServletContext();
			String reportsDirPath = context.getRealPath(RUTA_CARPETA);

			// se crea el file resolver y se asigna a la variable con el mismo
			// nombre del parametro de
			// jasper que se le asigna al reporte
			REPORT_FILE_RESOLVER = new SimpleFileResolver(new File(reportsDirPath));
			HashMap reportParams = new HashMap();
			reportParams.put("REPORT_FILE_RESOLVER", REPORT_FILE_RESOLVER);

			return REDIRECCION;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			addActionError(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_GENERANDO_REPORTE));
		}
		return ERROR;
	}

	

	private void generarReporteConsumoPuestoTrabajo() {

		parteTecnicoConsumoPuestoTrabajoComponentes = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
		try {
			parteTecnicoConsumoPuestoTrabajoComponentes = generarParteTecnicoFacde.obtenerPuestoTrabajoConsumo(
					Long.valueOf(valorDivision), Long.valueOf(valorSociedad), Long.valueOf(valorUnidad),
					Long.valueOf(valorLineaNegocio), valorMes, Integer.valueOf(valorAnnio));
		} catch (LogicaException e) {
			e.printStackTrace();
		}
		reporte.setReporteConsumoPorPuestoTrabajo(obtenerReporteConsumoPuestoTrabajo());

	}

	private List<ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean> obtenerReporteConsumoPuestoTrabajo() {
		List<ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean> lista = new ArrayList<ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean>();
		Map<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>> productos;
		ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean bean;
		String[] listanombreClinker = nombresProductos(ConstantesParametro.PRODUCTOS_CLINKER);
		String[] listanombreCrudo = nombresProductos(ConstantesParametro.PRODUCTOS_CRUDO);
		String[] listanombreBituminoso = nombresProductos(ConstantesParametro.PRODUCTOS_BITUMINOSO);
		String[] listanombreAntracita = nombresProductos(ConstantesParametro.PRODUCTOS_ANTRACITA);
		List<DetalleParteTecnicoPuestoTrabajoComponenteBean> listaConsumoXproducto = null;
		if (parteTecnicoConsumoPuestoTrabajoComponentes != null && parteTecnicoConsumoPuestoTrabajoComponentes.size() > 0) {
			ArrayList<String> procesos = hallarProcesosPT_Resumen(parteTecnicoConsumoPuestoTrabajoComponentes);

			for (String proceso : procesos) {

				listaConsumoXproducto = hallarConsumoPorProducto(proceso, parteTecnicoConsumoPuestoTrabajoComponentes);
				productos = hallarProductos(proceso, listaConsumoXproducto);

				ArrayList<String> prod = new ArrayList<String>(productos.keySet());
				Collections.sort(prod, new Comparator<String>() {

					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						return o1.compareTo(o2);
					}

				});

				for (String string : prod) {
					bean = new ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean(productos.get(string), listanombreClinker,
							listanombreCrudo, listanombreBituminoso, listanombreAntracita, tipoReporte);
					lista.add(bean);
				}
			}

		}
		return lista;
	}

	private String[] nombresProductos(String nombreParametro) {
		ArrayList<String> nombres = new ArrayList<String>();
		try {
			ParametroSistemaBean parametro = parametroLogic.obtenerParametroSistema(nombreParametro);
			if (parametro == null) {
				return nombres.toArray(new String[0]);
			}
			String[] paraStrings = parametro.getValor().split("-");

			for (int i = 0; i < paraStrings.length; i++) {
				ProductoBean productoBean = productoLogicFacade.obtenerProducto(Long.valueOf(paraStrings[i]));
				if (productoBean == null) {
					continue;
				}
				nombres.add(productoBean.getNombre());
			}

		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombres.toArray(new String[nombres.size()]);
	}

	private Map<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>> hallarProductos(String proceso,
			List<DetalleParteTecnicoPuestoTrabajoComponenteBean> listaConsumoXproducto) {

		Map<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>> mapaConsumoPuesto = new HashMap<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>>();

		for (DetalleParteTecnicoPuestoTrabajoComponenteBean detalle : listaConsumoXproducto) {
			List<DetalleParteTecnicoPuestoTrabajoComponenteBean> lista = mapaConsumoPuesto.get(detalle.getComponente());
			if (lista == null) {
				lista = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
				lista.add(detalle);
				mapaConsumoPuesto.put(detalle.getComponente(), lista);
			} else {
				lista.add(detalle);
			}
		}

		return mapaConsumoPuesto;
	}

	private List<DetalleParteTecnicoPuestoTrabajoComponenteBean> hallarConsumoPorProducto(String proceso,
			List<DetalleParteTecnicoPuestoTrabajoComponenteBean> lista) {
		List<DetalleParteTecnicoPuestoTrabajoComponenteBean> listaConsumoXproducto = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();

		for (DetalleParteTecnicoPuestoTrabajoComponenteBean detalle : lista) {
			if (detalle.getProceso().equals(proceso)) {
				listaConsumoXproducto.add(detalle);
			}
		}

		return listaConsumoXproducto;
	}

	private void cargarConsumoAnexo() throws AplicacionGlobalException {
		if (validaCamposRequeridosCombustible()) {
			Long codigoLinea = null;
			Long codigoUnidad = null;
			if (valorLineaNegocio != null) {
				try {
					codigoLinea = Long.valueOf(valorLineaNegocio);
				} catch (NumberFormatException e) {
					mensajeError = getText(FALLA_CONVERSION_NUMERICA);
					addActionError(mensajeError);
					logger.error(mensajeError);
					throw new AplicacionGlobalException(mensajeError, e);
				}
			}

			if (valorUnidad != null) {
				try {
					codigoUnidad = Long.valueOf(valorUnidad);
				} catch (NumberFormatException e) {
					mensajeError = getText(FALLA_CONVERSION_NUMERICA);
					addActionError(mensajeError);
					logger.error(mensajeError);
					throw new AplicacionGlobalException(mensajeError, e);
				}

			}
			ObtenerConsumoComponenteProductoCrudo(codigoLinea, codigoUnidad, null);
			ObtenerConsumoComponenteProductoCemento(codigoLinea, codigoUnidad, null);
			ObtenerConsumoComponenteProductoClinker(codigoLinea, codigoUnidad, null);
		}
	}

	private List<ReporteAnexo_Bean> generarReporteAnexo() throws NumberFormatException, LogicaException {
		ReporteAnexo_Bean reporteAnexo = new ReporteAnexo_Bean();
		List<ReporteAnexo_Bean> listaReporteAnexo = new ArrayList<ReporteAnexo_Bean>();
		reporteAnexo.setReporteCrudo(generarReporteCrudo());
		reporteAnexo.setReporteCemento(generarReporteCemento());
		reporteAnexo.setReporteClinker(generarReporteClinker());
		listaReporteAnexo.add(reporteAnexo);
		return listaReporteAnexo;
	}

	private void generarReporteResumen() throws LogicaException {

		parteTecnicoComponentes = new ArrayList<DetalleParteTecnicoConsumoComponentesBean>();
		parteTecnicoComponentes = generarParteTecnicoFacde.obtenerTotalBalanceProductosLineaNegocio(Long.valueOf(valorDivision),
				Long.valueOf(valorSociedad), Long.valueOf(valorUnidad), Long.valueOf(valorLineaNegocio), valorMes,
				Integer.valueOf(valorAnnio), null);
		combustibles = generarParteTecnicoFacde.obtenerCombustibles(Long.valueOf(valorDivision), Long.valueOf(valorSociedad),
				Long.valueOf(valorUnidad), Long.valueOf(valorLineaNegocio));

		parteTecnicoPuestoTrabajoComponentes = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
		parteTecnicoPuestoTrabajoComponentes = generarParteTecnicoFacde.obtenerPuestosTrabajoPorLineaNegocio(
				Long.valueOf(valorDivision), Long.valueOf(valorSociedad), Long.valueOf(valorUnidad),
				Long.valueOf(valorLineaNegocio), valorMes, Integer.valueOf(valorAnnio), null);

		reporte.setSubReportePtComponentes(generarListaReporteParteTecnicoResumen());
		reporte.setSubReportePTOperacionesComponentes(generarReporteOperacionesPT_Resumen());

	}

	private List<ReporteHojaCal_Bean> generarReporteHojaCal() throws AplicacionException {
		List<ReporteHojaCal_Bean> listaHojaCal = new ArrayList<ReporteHojaCal_Bean>();
		ReporteHojaCal_Bean hojaCal = new ReporteHojaCal_Bean();
		try {
			listaVentasConsumoCal = generarParteTecnicoFacde.obtenerConsumosVentasProductos(getValorDivision(),
					getValorSociedad(), Long.valueOf(getValorLineaNegocio()), Long.valueOf(getValorUnidad()), valorAnnio,
					valorMes);

			listReporteProductoTerminado = new ArrayList<ReporteProductoTerminado_Bean>();
			productos = ajusteProduccionFacade.obtenerProductosLineaNegocio(valorLineaNegocio);
			// Ordenar
			List<OrdenReporteBean> ordenReporteProductoTerminado;
			ArrayList<ProductoBean> productosOrdenado = null;

			ordenReporteProductoTerminado = ordenLogicFacade
					.obtenerOrdenResumenProductoOrdenadoPorTipo(ConstantesParametro.LISTAR_HOJACAL);
			productosOrdenado = new ArrayList<ProductoBean>();

			for (OrdenReporteBean orden : ordenReporteProductoTerminado) {

				for (ProductoBean string : productos) {
					if (string.getNombre().equals(orden.getProducto().getNombre())) {
						productosOrdenado.add(string);
					}
				}
			}

			// fin orden

			for (ProductoBean producto : productosOrdenado) {
				ReporteProductoTerminado_Bean reporteProductoTerminado = new ReporteProductoTerminado_Bean();
				valorProducto = producto.getCodigo();

				parteTecnicoComponentes = generarParteTecnicoFacde.obtenerTotalBalanceComponentes(Long.valueOf(valorDivision),
						Long.valueOf(valorSociedad), Long.valueOf(valorUnidad), Long.valueOf(valorProducto),
						Long.valueOf(valorLineaNegocio), valorMes, valorAnnio, null);

				reporteProductoTerminado.setNombreproducto(producto.getDescripcion());
				reporteProductoTerminado.setSubReportePtComponentes(generarListaReporteParteTecnico());

				parteTecnicoPuestoTrabajoComponentes = generarParteTecnicoFacde.obtenerPuestosTrabajoPorProducto(
						Long.valueOf(valorDivision), Long.valueOf(valorSociedad), Long.valueOf(valorUnidad),
						Long.valueOf(valorProducto), Long.valueOf(valorLineaNegocio), valorMes, valorAnnio, null);

				reporteProductoTerminado.setSubReportePTOperacionesComponentes(generarReporteOperacionesPT_ProductoTerminado());
				listReporteProductoTerminado.add(reporteProductoTerminado);
			}

			hojaCal.setReporteConsumosVentasCal(listaVentasConsumoCal);
			hojaCal.setSubReporteProductoterminadoCal(listReporteProductoTerminado);
			listaHojaCal.add(hojaCal);
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaHojaCal;
	}

	private List<ReporteProductoTerminado_Bean> generarReporteProductoTerminado() throws LogicaException {
		listReporteProductoTerminado = new ArrayList<ReporteProductoTerminado_Bean>();
		productos = ajusteProduccionFacade.obtenerProductosLineaNegocio(valorLineaNegocio);
		// Ordenar
		List<OrdenReporteBean> ordenReporteProductoTerminado;
		ArrayList<ProductoBean> productosOrdenado = null;
		try {
			ordenReporteProductoTerminado = ordenLogicFacade
					.obtenerOrdenResumenProductoOrdenadoPorTipo(ConstantesParametro.LISTAR_PRODUCTOS_TERMINADOS);
			productosOrdenado = new ArrayList<ProductoBean>();

			for (OrdenReporteBean orden : ordenReporteProductoTerminado) {

				for (ProductoBean string : productos) {
					if (string.getNombre().equals(orden.getProducto().getNombre())) {
						productosOrdenado.add(string);
					}
				}
			}
		} catch (AplicacionException e1) {
			// TODO manejo de Log
			e1.printStackTrace();
		}

		// fin orden

		for (ProductoBean producto : productosOrdenado) {
			ReporteProductoTerminado_Bean reporteProductoTerminado = new ReporteProductoTerminado_Bean();
			valorProducto = producto.getCodigo();

			parteTecnicoComponentes = generarParteTecnicoFacde.obtenerTotalBalanceComponentes(Long.valueOf(valorDivision),
					Long.valueOf(valorSociedad), Long.valueOf(valorUnidad), Long.valueOf(valorProducto),
					Long.valueOf(valorLineaNegocio), valorMes, valorAnnio, null);

			reporteProductoTerminado.setNombreproducto(producto.getDescripcion());
			reporteProductoTerminado.setSubReportePtComponentes(generarListaReporteParteTecnico());

			parteTecnicoPuestoTrabajoComponentes = generarParteTecnicoFacde.obtenerPuestosTrabajoPorProducto(
					Long.valueOf(valorDivision), Long.valueOf(valorSociedad), Long.valueOf(valorUnidad),
					Long.valueOf(valorProducto), Long.valueOf(valorLineaNegocio), valorMes, valorAnnio, null);

			reporteProductoTerminado.setSubReportePTOperacionesComponentes(generarReporteOperacionesPT_ProductoTerminado());
			listReporteProductoTerminado.add(reporteProductoTerminado);
		}

		return listReporteProductoTerminado;
	}

	public List<ReporteCombustibles_Bean> generarReporteCombustibles() throws AplicacionGlobalException {
		List<ReporteCombustibles_Bean> listaReporteCombustible = new ArrayList<ReporteCombustibles_Bean>();

		if (validaCamposRequeridosCombustible()) {
			Long codigoLinea = null;
			Long codigoUnidad = null;
			if (valorLineaNegocio != null) {
				try {
					codigoLinea = Long.valueOf(valorLineaNegocio);
				} catch (NumberFormatException e) {
					mensajeError = getText(FALLA_CONVERSION_NUMERICA);
					addActionError(mensajeError);
					logger.error(mensajeError);
					throw new AplicacionGlobalException(mensajeError, e);
				}
			}

			if (valorUnidad != null) {
				try {
					codigoUnidad = Long.valueOf(valorUnidad);
				} catch (NumberFormatException e) {
					mensajeError = getText(FALLA_CONVERSION_NUMERICA);
					addActionError(mensajeError);
					logger.error(mensajeError);
					throw new AplicacionGlobalException(mensajeError, e);
				}

			}

			try {
				Map<String, List<ConsumoCombustibleBean>> mapaCombustiblesSolidos = generarParteTecnicoFacde
						.obtenerProductosCombustibles(Long.valueOf(valorDivision), Long.valueOf(valorSociedad), codigoUnidad,
								codigoLinea, valorMes, valorAnnio, true);

				Map<String, List<ConsumoCombustibleBean>> mapaCombustiblesLiquidos = generarParteTecnicoFacde
						.obtenerProductosCombustibles(Long.valueOf(valorDivision), Long.valueOf(valorSociedad), codigoUnidad,
								codigoLinea, valorMes, valorAnnio, false);
				String titulo = ManejadorPropiedades.obtenerPropiedadPorClave(TITULO_REPORTE_CONSUMO_COMBUSTIBLE);
				ReporteCombustibles_Bean reporteCombustible = new ReporteCombustibles_Bean();
				if (mapaCombustiblesSolidos != null) {
					reporteCombustible
							.setSubReporteCombustibleSolido(generarReporteCombustiblePorEstado(mapaCombustiblesSolidos));
					reporteCombustible.setTitulo(titulo);
				}
				if (mapaCombustiblesLiquidos != null) {
					reporteCombustible
							.setSubReporteCombustibleLiquido(generarReporteCombustiblePorEstado(mapaCombustiblesLiquidos));
					reporteCombustible.setTitulo(titulo);
				}

				listaReporteCombustible.add(reporteCombustible);
			} catch (LogicaException e) {
				e.printStackTrace();
				mensajeError = getText(ConstantesLogicaNegocio.NOMBRE_GRUPO_CRUDO) + " - " + e.getMensaje();
				addActionError(mensajeError);
			}

		}

		return listaReporteCombustible;
	}

	private List<ReporteParteTecnicoOperacionesSub_B_Bean> generarReporteOperacionesPT_Resumen() {
		List<ReporteParteTecnicoOperacionesSub_B_Bean> lista = new ArrayList<ReporteParteTecnicoOperacionesSub_B_Bean>();

		ReporteParteTecnicoOperacionesSub_B_Bean bean;
		if (parteTecnicoPuestoTrabajoComponentes != null && parteTecnicoPuestoTrabajoComponentes.size() > 0) {
			ArrayList<String> procesos = hallarProcesosPT_Resumen(parteTecnicoPuestoTrabajoComponentes);

			Map<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>> detalleReporte = obtenerDatosReporte();
			for (int i = 0; i < procesos.size(); i++) {

				bean = new ReporteParteTecnicoOperacionesSub_B_Bean(detalleReporte.get(procesos.get(i)));
				lista.add(bean);
			}

		}

		return lista;
	}

	private List<ReporteParteTecnicoOperacionesSub_B_Bean> generarReporteOperacionesPT_ProductoTerminado() {
		List<ReporteParteTecnicoOperacionesSub_B_Bean> lista = new ArrayList<ReporteParteTecnicoOperacionesSub_B_Bean>();

		ReporteParteTecnicoOperacionesSub_B_Bean bean;
		if (parteTecnicoPuestoTrabajoComponentes != null && parteTecnicoPuestoTrabajoComponentes.size() > 0) {
			ArrayList<String> procesos = hallarProcesosPT_ProductoTerminado();
			Map<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>> detalleReporte = obtenerDatosReporte();
			for (int i = 0; i < procesos.size(); i++) {
				bean = new ReporteParteTecnicoOperacionesSub_B_Bean(detalleReporte.get(procesos.get(i)));
				lista.add(bean);
			}

		}

		return lista;
	}

	private Map<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>> obtenerDatosReporte() {
		Map<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>> procesoDetalle = new HashMap<String, List<DetalleParteTecnicoPuestoTrabajoComponenteBean>>();
		// for (int i = 0; i < procesos.size(); i++) {
		List<DetalleParteTecnicoPuestoTrabajoComponenteBean> lista;
		for (DetalleParteTecnicoPuestoTrabajoComponenteBean beanDetalle : parteTecnicoPuestoTrabajoComponentes) {
			if (procesoDetalle.get(beanDetalle.getProceso()) != null) {
				lista = procesoDetalle.get(beanDetalle.getProceso());
				lista.add(beanDetalle);
			} else {
				lista = new ArrayList<DetalleParteTecnicoPuestoTrabajoComponenteBean>();
				lista.add(beanDetalle);
				procesoDetalle.put(beanDetalle.getProceso(), lista);

			}
		}

		// }
		return procesoDetalle;
	}

	private ArrayList<String> hallarProcesosPT_Resumen(List<DetalleParteTecnicoPuestoTrabajoComponenteBean> lista) {

		ArrayList<String> procesos = new ArrayList<String>();
		for (DetalleParteTecnicoPuestoTrabajoComponenteBean beanDetalle : lista) {
			if (!compararCadenas(beanDetalle.getProceso(), procesos)) {
				procesos.add(beanDetalle.getProceso());
			}
		}
		// Ordenar
		List<OrdenReporteBean> ordenReporteOperacionesPT;
		ArrayList<String> procesosOrdenados = null;
		try {
			ordenReporteOperacionesPT = ordenLogicFacade
					.obtenerOrdenResumenProductoOrdenadoPorTipo(ConstantesParametro.LISTAR_OPERACIONES_PUESTOTRABAJO_RESUMEN);
			procesosOrdenados = new ArrayList<String>();
			for (OrdenReporteBean orden : ordenReporteOperacionesPT) {
				Proceso proceso = null;
				try {
					proceso = ProcesoQuerier.getById(orden.getProceso().getCodigo());
				} catch (ElementoNoEncontradoException e) {
					e.printStackTrace();
				}

				for (String string : procesos) {
					if (proceso != null && string != null) {
						if (string.equals(proceso.getNombreProceso())) {
							procesosOrdenados.add(proceso.getNombreProceso());
						}
					}

				}
			}
		} catch (AplicacionException e1) {
			// TODO Manejo de Log
			e1.printStackTrace();
		}

		return procesosOrdenados;
	}

	private ArrayList<String> hallarProcesosPT_ProductoTerminado() {
		ArrayList<String> procesos = new ArrayList<String>();
		for (DetalleParteTecnicoPuestoTrabajoComponenteBean beanDetalle : parteTecnicoPuestoTrabajoComponentes) {
			if (!compararCadenas(beanDetalle.getProceso(), procesos)) {
				procesos.add(beanDetalle.getProceso());
			}

		}

		return procesos;
	}

	private boolean compararCadenas(String bleCovariableCompmp, ArrayList<String> variables) {

		for (String variale : variables) {
			if (bleCovariableCompmp.compareToIgnoreCase(variale) == 0) {
				return true;
			}
		}
		return false;
	}

	private List<ReporteParteTecnicoSub_B_Bean> generarReporteCrudo() throws NumberFormatException, LogicaException {
		List<ReporteParteTecnicoSub_B_Bean> listaReporteCrudo = new ArrayList<ReporteParteTecnicoSub_B_Bean>();
		ReporteParteTecnicoSub_B_Bean cabecera = new ReporteParteTecnicoSub_B_Bean();
		ParametroSistemaLogicFacade parametro = new ParametroSistemaLogic();

		int i = 1;
		try {
			for (String nombre : nombreTiposCrudo) {
				BeanUtils.setProperty(cabecera, "col" + i, nombre);
				i++;
			}
			List<ReporteParteTecnicoSub_B_Detalle_Bean> detalle = new ArrayList<ReporteParteTecnicoSub_B_Detalle_Bean>();
			ReporteParteTecnicoSub_B_Detalle_Bean det;
			String unidadMedida = ConstantesParametro.TIPOMEDIDA_TM;

			for (ComponenteConsumidoBean ccbean : parteTecnicoAnexoCrudo) {
				det = new ReporteParteTecnicoSub_B_Detalle_Bean();
				i = 1;
				for (ComponenteConsumoBean ccosumidobean : ccbean.getConsumos()) {

					String valor = ccosumidobean.getValorConsumo();
					unidadMedida = ccosumidobean.getUnidadMedida();
					BeanUtils.setProperty(det, "col" + i, valor);
					i++;
				}
				detalle.add(det);

			}
			Double[] sumas = new Double[4];
			sumas[0] = null;
			sumas[1] = null;
			sumas[2] = null;
			sumas[3] = null;
			for (ReporteParteTecnicoSub_B_Detalle_Bean reporte : detalle) {
				if (reporte.getCol2() != null && !reporte.getCol2().toString().trim().equals("")) {
					if (sumas[0] == null) {
						sumas[0] = 0D;
					}

					sumas[0] += Double.valueOf(reporte.getCol2().toString());
				}
				if (reporte.getCol3() != null && !reporte.getCol3().toString().trim().equals("")) {
					if (sumas[1] == null) {
						sumas[1] = 0D;
					}
					sumas[1] += Double.valueOf(reporte.getCol3().toString());
				}

				if (reporte.getCol4() != null && !reporte.getCol4().toString().trim().equals("")) {
					if (sumas[2] == null) {
						sumas[2] = 0D;
					}
					sumas[2] += Double.valueOf(reporte.getCol4().toString());
				}
				if (reporte.getCol5() != null && !reporte.getCol5().toString().trim().equals("")) {
					if (sumas[3] == null) {
						sumas[3] = 0D;
					}
					sumas[3] += Double.valueOf(reporte.getCol5().toString());
				}
			}
			ReporteParteTecnicoSub_B_Detalle_Bean detalleSuma = new ReporteParteTecnicoSub_B_Detalle_Bean();
			Integer numDecimales = ConstantesParametro.CANTIDAD_DECIMALES_SGCP_DEFAULT;
			if (unidadMedida == null) {
				unidadMedida = ConstantesParametro.TIPOMEDIDA_TM;
			}
			ParametroSistemaBean parametrobean = parametro.obtenerParametroSistema(unidadMedida);
			if (parametrobean != null) {
				numDecimales = Integer.parseInt(parametrobean.getValor());
			}

			detalleSuma.setCol1("TOTAL");
			if (sumas[0] != null) {
				detalleSuma.setCol2(NumberUtil.redondeoReportePT(sumas[0], numDecimales, unidadMedida));

			}
			if (sumas[1] != null) {
				detalleSuma.setCol3(NumberUtil.redondeoReportePT(sumas[1], numDecimales, unidadMedida));
			}

			if (sumas[2] != null) {
				detalleSuma.setCol4(NumberUtil.redondeoReportePT(sumas[2], numDecimales, unidadMedida));
			}
			if (sumas[3] != null) {
				detalleSuma.setCol5(NumberUtil.redondeoReportePT(sumas[3], numDecimales, unidadMedida));
			}
			detalle.add(detalleSuma);

			cabecera.setDetalleReporteTecnico(detalle);
		} catch (IllegalAccessException e) {
			// Manejo de excepciones
			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}
		listaReporteCrudo.add(cabecera);
		return listaReporteCrudo;
	}

	private List<ReporteParteTecnicoSub_B_Bean> generarReporteClinker() throws LogicaException {
		List<ReporteParteTecnicoSub_B_Bean> listaReporteCrudo = new ArrayList<ReporteParteTecnicoSub_B_Bean>();
		ReporteParteTecnicoSub_B_Bean cabecera = new ReporteParteTecnicoSub_B_Bean();
		ParametroSistemaLogicFacade parametro = new ParametroSistemaLogic();
		int i = 1;
		try {
			for (String nombre : nombreTiposClinker) {
				BeanUtils.setProperty(cabecera, "col" + i, nombre);
				i++;
			}
			List<ReporteParteTecnicoSub_B_Detalle_Bean> detalle = new ArrayList<ReporteParteTecnicoSub_B_Detalle_Bean>();

			ReporteParteTecnicoSub_B_Detalle_Bean det;
			for (ComponenteConsumidoBean ccbean : parteTecnicoAnexoClinker) {
				det = new ReporteParteTecnicoSub_B_Detalle_Bean();
				i = 1;
				for (ComponenteConsumoBean ccosumidobean : ccbean.getConsumos()) {

					String valor = ccosumidobean.getValorConsumo();

					BeanUtils.setProperty(det, "col" + i, valor);
					i++;
				}
				detalle.add(det);

			}
			cabecera.setDetalleReporteTecnico(detalle);
		} catch (IllegalAccessException e) {
			// TODO manejo de log
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		listaReporteCrudo.add(cabecera);
		return listaReporteCrudo;
	}

	private List<ReporteParteTecnicoSub_B_Bean> generarReporteCemento() throws LogicaException {
		List<ReporteParteTecnicoSub_B_Bean> listaReporteCemento = new ArrayList<ReporteParteTecnicoSub_B_Bean>();
		ReporteParteTecnicoSub_B_Bean cabecera = new ReporteParteTecnicoSub_B_Bean();
		// ParametroSistemaLogicFacade parametro = new ParametroSistemaLogic();

		int i = 1;
		try {
			for (String nombre : nombreTiposCemento) {
				BeanUtils.setProperty(cabecera, "col" + i, nombre);
				i++;
			}
			List<ReporteParteTecnicoSub_B_Detalle_Bean> detalle = new ArrayList<ReporteParteTecnicoSub_B_Detalle_Bean>();

			ReporteParteTecnicoSub_B_Detalle_Bean det;
			String unidadMedida = ConstantesParametro.TIPOMEDIDA_TM;
			Boolean esproducto = false;
			Double acumuladorComponenteClinker = 0d;
			String[] listanombreClinker = nombresProductos(ConstantesParametro.PRODUCTOS_CLINKER);
			for (ComponenteConsumidoBean ccbean : parteTecnicoAnexoCemento) {
				det = new ReporteParteTecnicoSub_B_Detalle_Bean();
				i = 1;
				for (ComponenteConsumoBean ccosumidobean : ccbean.getConsumos()) {

					String valor = ccosumidobean.getValorConsumo();

					if (!NumberUtils.isNumber(valor)) {

						esproducto = false;
						for (int j = 0; j < listanombreClinker.length; j++) {
							if (valor.equals(listanombreClinker[j])) {
								esproducto = true;
							}
						}

					}

					if (esproducto) {
						if (NumberUtils.isNumber(valor)) {
							acumuladorComponenteClinker += Double.valueOf(valor);
						}
					}

					unidadMedida = ccosumidobean.getUnidadMedida();
					BeanUtils.setProperty(det, "col" + i, valor);
					i++;
				}
				detalle.add(det);

			}

			Double acumuladorTotalComponete = 0d;
			Double[] sumas = new Double[5];
			sumas[0] = 0d;
			sumas[1] = 0d;
			sumas[2] = 0d;
			sumas[3] = 0d;
			sumas[4] = 0d;
			for (ReporteParteTecnicoSub_B_Detalle_Bean reporte : detalle) {
				if (reporte.getCol2() != null && !reporte.getCol2().toString().trim().equals("")) {
					sumas[0] += Double.valueOf(reporte.getCol2().toString());
				}
				if (reporte.getCol3() != null && !reporte.getCol3().toString().trim().equals("")) {
					sumas[1] += Double.valueOf(reporte.getCol3().toString());
				}
				if (reporte.getCol4() != null && !reporte.getCol4().toString().trim().equals("")) {
					sumas[2] += Double.valueOf(reporte.getCol4().toString());
				}
				if (reporte.getCol5() != null && !reporte.getCol5().toString().trim().equals("")) {
					sumas[3] += Double.valueOf(reporte.getCol5().toString());
				}
				if (reporte.getCol6() != null && !reporte.getCol6().toString().trim().equals("")) {
					sumas[4] += Double.valueOf(reporte.getCol6().toString());
				}
			}
			ReporteParteTecnicoSub_B_Detalle_Bean detalleSuma = new ReporteParteTecnicoSub_B_Detalle_Bean();
			Integer numDecimales = ConstantesParametro.CANTIDAD_DECIMALES_SGCP_DEFAULT;
			ParametroSistemaBean parametrobean = parametroLogic.obtenerParametroSistema(unidadMedida);
			if (parametrobean != null) {
				numDecimales = Integer.parseInt(parametrobean.getValor());
			}

			detalleSuma.setCol1("TOTAL");
			if (sumas[0] > 0) {
				acumuladorTotalComponete += sumas[0];
				detalleSuma.setCol2(NumberUtil.redondeoReportePT(sumas[0], numDecimales, unidadMedida));
			}
			if (sumas[1] > 0) {
				acumuladorTotalComponete += sumas[1];
				detalleSuma.setCol3(NumberUtil.redondeoReportePT(sumas[1], numDecimales, unidadMedida));
			}
			if (sumas[2] > 0) {
				acumuladorTotalComponete += sumas[2];
				detalleSuma.setCol4(NumberUtil.redondeoReportePT(sumas[2], numDecimales, unidadMedida));
			}
			if (sumas[3] > 0) {
				acumuladorTotalComponete += sumas[3];
				detalleSuma.setCol5(NumberUtil.redondeoReportePT(sumas[3], numDecimales, unidadMedida));
			}

			if (sumas[4] > 0) {
				acumuladorTotalComponete += sumas[4];
				detalleSuma.setCol6(NumberUtil.redondeoReportePT(sumas[4], numDecimales, unidadMedida));
			}
			detalle.add(detalleSuma);
			if (tipoReporte.equals("XLS")) {
				cabecera.setFactor(NumberUtil.dividirSinRedondear(acumuladorComponenteClinker, acumuladorTotalComponete));
			}

			cabecera.setDetalleReporteTecnico(detalle);
		} catch (IllegalAccessException e) {
			// TODO manejo de log
			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}
		listaReporteCemento.add(cabecera);
		return listaReporteCemento;
	}

	private List<SubReporteCombustibleBean> generarReporteCombustiblePorEstado(
			Map<String, List<ConsumoCombustibleBean>> mapaCombustiblesSolidos) throws LogicaException {
		Integer cantidadDecimales = ConstantesParametro.CANTIDAD_DECIMALES_SGCP_DEFAULT;
		String unidadMedida = ConstantesParametro.TIPOMEDIDA_DEFAULT;
		List<SubReporteCombustibleBean> subReporteCombustible = new ArrayList<SubReporteCombustibleBean>();
		SubReporteCombustibleBean reporte;
		List<ReporteParteTecnicoSub_A_Bean> reportesCombustible;
		ReporteParteTecnicoSub_A_Bean combustibleSolido;

		ParametroSistemaBean parametroSistemaBean;

		try {
			ArrayList<String> nombresComponentes = new ArrayList<String>(mapaCombustiblesSolidos.keySet());

			for (String nombre : nombresComponentes) {
				reporte = new SubReporteCombustibleBean();

				List<ConsumoCombustibleBean> listaConsumo = mapaCombustiblesSolidos.get(nombre);
				reportesCombustible = new ArrayList<ReporteParteTecnicoSub_A_Bean>();
				for (ConsumoCombustibleBean ccBean : listaConsumo) {

					combustibleSolido = new ReporteParteTecnicoSub_A_Bean();
					combustibleSolido.setValor_1(ccBean.getNombreProductoCombustible());
					combustibleSolido.setValor_2(ccBean.getNombrePuestoTrabajo());
					parametroSistemaBean = parametroLogic.obtenerParametroSistema(ccBean.getUnidadMedida());

					if (parametroSistemaBean != null) {
						cantidadDecimales = Integer.parseInt(parametroSistemaBean.getValor());
					}
					if (!StringUtils.isEmpty(ccBean.getUnidadMedida())) {
						unidadMedida = ccBean.getUnidadMedida();
					}

					combustibleSolido.setValor_3(NumberUtil.redondeoReportePT(ccBean.getConsumoProduccionMes(),
							cantidadDecimales, unidadMedida));
					combustibleSolido.setValor_4(NumberUtil.redondeoReportePT(ccBean.getConsumoProduccionAcumulado(),
							cantidadDecimales, unidadMedida));
					combustibleSolido.setValor_5(NumberUtil.redondeoReportePT(ccBean.getConsumoCalentamientoMes(),
							cantidadDecimales, unidadMedida));
					combustibleSolido.setValor_6(NumberUtil.redondeoReportePT(ccBean.getConsumoCalentamientoAcumulado(),
							cantidadDecimales, unidadMedida));
					combustibleSolido.setValor_7(NumberUtil.redondeoReportePT(ccBean.getConsumoTotalMes(), cantidadDecimales,
							unidadMedida));
					combustibleSolido.setValor_8(NumberUtil.redondeoReportePT(ccBean.getConsumoTotalAcumulado(),
							cantidadDecimales, unidadMedida));
					reportesCombustible.add(combustibleSolido);
				}

				reporte.setTitulo(nombre);
				reporte.setReporteCombustible(reportesCombustible);
				subReporteCombustible.add(reporte);

			}
			// listaComponente

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return subReporteCombustible;
	}

	/**
	 * @return the rEPORT_FILE_RESOLVER
	 */
	public FileResolver getREPORT_FILE_RESOLVER() {
		return REPORT_FILE_RESOLVER;
	}

	/**
	 * @param report_file_resolver the rEPORT_FILE_RESOLVER to set
	 */
	public void setREPORT_FILE_RESOLVER(FileResolver report_file_resolver) {
		REPORT_FILE_RESOLVER = report_file_resolver;
	}

	public ReporteParteTecnicoBean getReporte() {
		return reporte;
	}

	public void setReporte(ReporteParteTecnicoBean reporte) {
		this.reporte = reporte;
	}

	public List<LineaNegocioBean> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaNegocioBean> lineas) {
		this.lineas = lineas;
	}

	public String getEjecutaConsulta() {
		return ejecutaConsulta;
	}

	public void setEjecutaConsulta(String ejecutaConsulta) {
		this.ejecutaConsulta = ejecutaConsulta;
	}

	public String getLineaSeleccionada() {
		return lineaSeleccionada;
	}

	public void setLineaSeleccionada(String lineaSeleccionada) {
		this.lineaSeleccionada = lineaSeleccionada;
	}

	public String getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(String productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<DetalleParteTecnicoConsumoComponentesBean> getParteTecnicoComponentes() {
		return parteTecnicoComponentes;
	}

	public void setParteTecnicoComponentes(List<DetalleParteTecnicoConsumoComponentesBean> parteTecnicoComponentes) {
		this.parteTecnicoComponentes = parteTecnicoComponentes;
	}

	public String getProductoSeleccionadoTemp() {
		return productoSeleccionadoTemp;
	}

	public void setProductoSeleccionadoTemp(String productoSeleccionadoTemp) {
		this.productoSeleccionadoTemp = productoSeleccionadoTemp;
	}

	public List<DetalleParteTecnicoPuestoTrabajoComponenteBean> getParteTecnicoPuestoTrabajoComponentes() {
		return parteTecnicoPuestoTrabajoComponentes;
	}

	public void setParteTecnicoPuestoTrabajoComponentes(
			List<DetalleParteTecnicoPuestoTrabajoComponenteBean> parteTecnicoPuestoTrabajoComponentes) {
		this.parteTecnicoPuestoTrabajoComponentes = parteTecnicoPuestoTrabajoComponentes;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public List<ConsumoProductoBean> getParteTecnicoConsumoCombustible() {
		return parteTecnicoConsumoCombustible;
	}

	public void setParteTecnicoConsumoCombustible(List<ConsumoProductoBean> parteTecnicoConsumoCombustible) {
		this.parteTecnicoConsumoCombustible = parteTecnicoConsumoCombustible;
	}

	public List<ComponenteConsumidoBean> getParteTecnicoAnexoCrudo() {
		return parteTecnicoAnexoCrudo;
	}

	public void setParteTecnicoAnexoCrudo(List<ComponenteConsumidoBean> parteTecnicoAnexoCrudo) {
		this.parteTecnicoAnexoCrudo = parteTecnicoAnexoCrudo;
	}

	public List<ComponenteConsumidoBean> getParteTecnicoAnexoClinker() {
		return parteTecnicoAnexoClinker;
	}

	public void setParteTecnicoAnexoClinker(List<ComponenteConsumidoBean> parteTecnicoAnexoClinker) {
		this.parteTecnicoAnexoClinker = parteTecnicoAnexoClinker;
	}

	public List<ComponenteConsumidoBean> getParteTecnicoAnexoCemento() {
		return parteTecnicoAnexoCemento;
	}

	public void setParteTecnicoAnexoCemento(List<ComponenteConsumidoBean> parteTecnicoAnexoCemento) {
		this.parteTecnicoAnexoCemento = parteTecnicoAnexoCemento;
	}

	public List<String> getCombustibles() {
		return combustibles;
	}

	public void setCombustibles(List<String> combustibles) {
		this.combustibles = combustibles;
	}

	public List<String> getNombreTiposCemento() {
		return nombreTiposCemento;
	}

	public void setNombreTiposCemento(List<String> nombreTiposCemento) {
		this.nombreTiposCemento = nombreTiposCemento;
	}

	public List<String> getNombreTiposCrudo() {
		return nombreTiposCrudo;
	}

	public void setNombreTiposCrudo(List<String> nombreTiposCrudo) {
		this.nombreTiposCrudo = nombreTiposCrudo;
	}

	public List<String> getNombreTiposClinker() {
		return nombreTiposClinker;
	}

	public void setNombreTiposClinker(List<String> nombreTiposClinker) {
		this.nombreTiposClinker = nombreTiposClinker;
	}

	public String getValorInforme() {

		if (getValorInformeFiltrado() != null) {
			return getValorInformeFiltrado();
		}
		return valorInforme;
	}

	public void setValorInforme(String valorInforme) {
		this.valorInforme = valorInforme;
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

	public String getTituloCampoAnio() {
		return tituloCampoAnio;
	}

	public void setTituloCampoAnio(String tituloCampoAnio) {
		this.tituloCampoAnio = tituloCampoAnio;
	}

	public String getTituloCampoMes() {
		return tituloCampoMes;
	}

	public void setTituloCampoMes(String tituloCampoMes) {
		this.tituloCampoMes = tituloCampoMes;
	}

	public String getTituloCampoInforme() {
		return tituloCampoInforme;
	}

	public void setTituloCampoInforme(String tituloCampoInforme) {
		this.tituloCampoInforme = tituloCampoInforme;
	}

	/**
	 * @return the anioSeleccionado
	 */
	public Integer getAnioSeleccionado() {
		return anioSeleccionado;
	}

	/**
	 * @param anioSeleccionado the anioSeleccionado to set
	 */
	public void setAnioSeleccionado(Integer anioSeleccionado) {
		this.anioSeleccionado = anioSeleccionado;
	}

	/**
	 * @return the mesSeleccionado
	 */
	public String getMesSeleccionado() {
		return mesSeleccionado;
	}

	/**
	 * @param mesSeleccionado the mesSeleccionado to set
	 */
	public void setMesSeleccionado(String mesSeleccionado) {
		this.mesSeleccionado = mesSeleccionado;
	}

	/**
	 * @return the anios
	 */
	public List<Integer> getAnios() {
		return anios;
	}

	/**
	 * @param anios the anios to set
	 */
	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}

	/**
	 * @return the meses
	 */
	public List<MesBean> getMeses() {
		return meses;
	}

	/**
	 * @param meses the meses to set
	 */
	public void setMeses(List<MesBean> meses) {
		this.meses = meses;
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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the xlsPDF
	 */
	public Integer getXlsPDF() {
		return xlsPDF;
	}

	/**
	 * @param xlsPDF the xlsPDF to set
	 */
	public void setXlsPDF(Integer xlsPDF) {
		this.xlsPDF = xlsPDF;
	}

	/**
	 * @return the reporteAjuste
	 */
	public ReporteAjusteProduccionBean getReporteAjuste() {
		return reporteAjuste;
	}

	/**
	 * @param reporteAjuste the reporteAjuste to set
	 */
	public void setReporteAjuste(ReporteAjusteProduccionBean reporteAjuste) {
		this.reporteAjuste = reporteAjuste;
	}

	/**
	 * @return the valorDivision
	 */
	public Long getValorDivision() {
		return valorDivision;
	}

	/**
	 * @param valorDivision the valorDivision to set
	 */
	public void setValorDivision(Long valorDivision) {
		this.valorDivision = valorDivision;
	}

	/**
	 * @return the valorSociedad
	 */
	public Long getValorSociedad() {

		if (getValorSociedadFiltrado() != null) {
			return getValorSociedadFiltrado();
		}
		return valorSociedad;
	}

	/**
	 * @param valorSociedad the valorSociedad to set
	 */
	public void setValorSociedad(Long valorSociedad) {
		this.valorSociedad = valorSociedad;
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
	 * @return the valorAnnio
	 */
	public Integer getValorAnnio() {
		if (getValorAnnioFiltrado() != null) {
			return getValorAnnioFiltrado();
		}
		return valorAnnio;
	}

	/**
	 * @param valorAnnio the valorAnnio to set
	 */
	public void setValorAnnio(Integer valorAnnio) {
		this.valorAnnio = valorAnnio;
	}

	/**
	 * @return the valorMes
	 */
	public Short getValorMes() {
		if (getValorMesFiltrado() != null) {
			return getValorMesFiltrado();
		}
		return valorMes;
	}

	/**
	 * @param valorMes the valorMes to set
	 */
	public void setValorMes(Short valorMes) {
		this.valorMes = valorMes;
	}

	/**
	 * @return the valorProducto
	 */
	public Long getValorProducto() {
		if (getValorProductoFiltrado() != null) {
			return getValorProductoFiltrado();
		}
		return valorProducto;
	}

	/**
	 * @param valorProducto the valorProducto to set
	 */
	public void setValorProducto(Long valorProducto) {
		this.valorProducto = valorProducto;
	}

	/**
	 * @return the valorProductoFiltrado
	 */
	public Long getValorProductoFiltrado() {
		return valorProductoFiltrado;
	}

	/**
	 * @param valorProductoFiltrado the valorProductoFiltrado to set
	 */
	public void setValorProductoFiltrado(Long valorProductoFiltrado) {
		this.valorProductoFiltrado = valorProductoFiltrado;
	}

	/**
	 * @return the informes
	 */
	public List<UtilBean> getInformes() {
		return informes;
	}

	/**
	 * @param informes the informes to set
	 */
	public void setInformes(List<UtilBean> informes) {
		this.informes = informes;
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
	 * @return the productos
	 */
	public List<ProductoBean> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<ProductoBean> productos) {
		this.productos = productos;
	}

	/**
	 * @return the valorInformeFiltrado
	 */
	public String getValorInformeFiltrado() {
		return valorInformeFiltrado;
	}

	/**
	 * @param valorInformeFiltrado the valorInformeFiltrado to set
	 */
	public void setValorInformeFiltrado(String valorInformeFiltrado) {
		this.valorInformeFiltrado = valorInformeFiltrado;
	}

	/**
	 * @return the valorDivisionFiltrado
	 */
	public Long getValorDivisionFiltrado() {
		return valorDivisionFiltrado;
	}

	/**
	 * @param valorDivisionFiltrado the valorDivisionFiltrado to set
	 */
	public void setValorDivisionFiltrado(Long valorDivisionFiltrado) {
		this.valorDivisionFiltrado = valorDivisionFiltrado;
	}

	/**
	 * @return the valorSociedadFiltrado
	 */
	public Long getValorSociedadFiltrado() {
		return valorSociedadFiltrado;
	}

	/**
	 * @param valorSociedadFiltrado the valorSociedadFiltrado to set
	 */
	public void setValorSociedadFiltrado(Long valorSociedadFiltrado) {
		this.valorSociedadFiltrado = valorSociedadFiltrado;
	}

	/**
	 * @return the valorUnidadFiltrado
	 */
	public Long getValorUnidadFiltrado() {
		return valorUnidadFiltrado;
	}

	/**
	 * @param valorUnidadFiltrado the valorUnidadFiltrado to set
	 */
	public void setValorUnidadFiltrado(Long valorUnidadFiltrado) {
		this.valorUnidadFiltrado = valorUnidadFiltrado;
	}

	/**
	 * @return the valorLineaNegocioFiltrado
	 */
	public Long getValorLineaNegocioFiltrado() {
		return valorLineaNegocioFiltrado;
	}

	/**
	 * @param valorLineaNegocioFiltrado the valorLineaNegocioFiltrado to set
	 */
	public void setValorLineaNegocioFiltrado(Long valorLineaNegocioFiltrado) {
		this.valorLineaNegocioFiltrado = valorLineaNegocioFiltrado;
	}

	/**
	 * @return the valorAnnioFiltrado
	 */
	public Integer getValorAnnioFiltrado() {
		return valorAnnioFiltrado;
	}

	/**
	 * @param valorAnnioFiltrado the valorAnnioFiltrado to set
	 */
	public void setValorAnnioFiltrado(Integer valorAnnioFiltrado) {
		this.valorAnnioFiltrado = valorAnnioFiltrado;
	}

	/**
	 * @return the valorMesFiltrado
	 */
	public Short getValorMesFiltrado() {
		return valorMesFiltrado;
	}

	/**
	 * @param valorMesFiltrado the valorMesFiltrado to set
	 */
	public void setValorMesFiltrado(Short valorMesFiltrado) {
		this.valorMesFiltrado = valorMesFiltrado;
	}

	/**
	 * @return the mensajeErrorReporte
	 */
	public String getMensajeErrorReporte() {
		return mensajeErrorReporte;
	}

	/**
	 * @param mensajeErrorReporte the mensajeErrorReporte to set
	 */
	public void setMensajeErrorReporte(String mensajeErrorReporte) {
		this.mensajeErrorReporte = mensajeErrorReporte;
	}

}
