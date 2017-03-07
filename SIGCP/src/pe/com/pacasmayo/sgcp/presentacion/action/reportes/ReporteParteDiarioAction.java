package pe.com.pacasmayo.sgcp.presentacion.action.reportes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.MesBean;
import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.impl.MesBeanImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.ReporteGestionStockProduccionBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.ReportePuestoTrabajoProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.DivisionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParteDiarioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PeriodoContableLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProcesoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.SociedadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaKardexLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaOperacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.SociedadLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.ParteDiarioLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.TablaKardexLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.TablaOperacionLogic;
import pe.com.pacasmayo.sgcp.logica.stock.PeriodoContableLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.FechaUtil.MESES_ESPANNOL;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.opensymphony.xwork2.Preparable;

public class ReporteParteDiarioAction extends AplicacionAction implements ConstantesLogicaNegocio, ConstantesMensajePresentacion,
		Preparable {

	private static final long serialVersionUID = 6583370650682346554L;
	private List<MesBean> meseslst;
	private Log logger = LogFactory.getLog(this.getClass());
	@SuppressWarnings("unchecked")
	private Map parameter;
	private FileResolver REPORT_FILE_RESOLVER;
	private List<DivisionBean> divisiones;
	private List<SociedadBean> sociedades;
	private List<UnidadBean> unidades;
	private List<LineaNegocioBean> lineasNegocio;
	private List<ProcesoBean> procesos;
	private List<ProductoBean> productos;
	private List<PuestoTrabajoBean> puestosTrabajo;
	private String RUTA_CARPETA;
	public static BeanFactory beanFactory;

	private Long valorDivision;
	private Long valorSociedad;
	private Long valorUnidad;
	private String valorLineaNegocio;
	private Long valorPuestoTrabajo;
	private Long valorProceso;
	private Long valorProducto;
	private String valorTipoReporte;
	private int valorReporte;

	private Long valorSociedadFiltrado;
	private Long valorUnidadFiltrado;

	private String valorTipoExp;

	private static DivisionLogicFacade logicaDivision = new DivisionLogic();
	private static SociedadLogicFacade logicaSociedad = new SociedadLogic();
	private static UnidadLogicFacade logicaUnidad = new UnidadLogic();
	private static LineaNegocioLogicFacade logicaLineaNegocio = new LineaNegocioLogic();
	private static ProcesoLogicFacade logicaProceso = new ProcesoLogic();
	private static ProductoLogicFacade logicaProducto = new ProductoLogic();
	private static PuestoTrabajoLogicFacade logicaPuestoTrabajo = new PuestoTrabajoLogic();
	private static TablaKardexLogicFacade tablaKardexLogicFacade = new TablaKardexLogic();
	private static TablaOperacionLogicFacade tablaOperacionLogicFacade = new TablaOperacionLogic();
	private static PeriodoContableLogicFacade periodoContable = new PeriodoContableLogic();
	private String fechaInicio;
	private String fechaFin;

	private ReporteParteDiarioBean reporte;
	private String dataDireccionReporte;
	private String valorCargaCompletada;
	private List<ReporteParteDiarioBean> reporteb;

	private Short mes;
	private Integer anio;
	private String tipoProducto;
	private List<UtilBean> anios;
	private List<FechaUtil.MESES_ESPANNOL> meses = Arrays.asList(MESES_ESPANNOL.values());

	private ReporteGestionStockProduccionBean reporteProduccionStock;
	private ReportePuestoTrabajoProduccionBean reportePuestoTrabajoProduccion;
	private InputStream file = new ByteArrayInputStream(new byte[1024]);

	private String fileName;
	private String contentType;
	private Integer xlsPDF;

	private static final String PRODUCTO_CLINKERIZACION_HV = "CLINKERIZACI\u00D3N  HV";
	private static final String PRODUCTO_CLINKERIZACION_HH = "CLINKERIZACI\u00D3N HH";
	private static final String PRODUCTO_CLINKERIZACION = "CLINKERIZACI\u00D3N";

	public void prepare() throws Exception {
		asignaPrivilegios();
	}

	/**
	 * Carga los a�os a mostrar para la consulta
	 */
	private void cargarAnios() {

		try {
			anios = new ArrayList<UtilBean>();

			List<Integer> aniosP = periodoContable.obtenerAnosPorPeriodo();

			for (Integer anno : aniosP) {
				UtilBean utilBean = new UtilBeanImpl();
				utilBean.setCodigo((int) anno);
				utilBean.setValor(String.valueOf(anno));
				anios.add(utilBean);
			}
		} catch (LogicaException e) {
			logger.error(e);
		}

	}

	public String doReporte() throws AplicacionGlobalException {

		try {
			verificarExitoOperacion();
			cargarListasFiltros();
		} catch (Exception e) {
			// TODO: Manejo de Log
			e.printStackTrace();
		}
		// CARGAR PROCESOS

		try {
			procesos = logicaProceso.obtenerProcesos();

			for (int i = 0; i < procesos.size(); i++) {
				if (procesos.get(i).getNombre().trim().equals(PRODUCTO_CLINKERIZACION_HV.trim())) {
					procesos.remove(i);
				}
				if (procesos.get(i).getNombre().trim().equals(PRODUCTO_CLINKERIZACION_HH.trim())) {
					procesos.get(i).setNombre(PRODUCTO_CLINKERIZACION);
				}
			}
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
			procesos = null;
		} catch (Exception e) {
			// TODO: Manejo de Log
			e.printStackTrace();
		}
		try {
			cargarMeses();
			cargarAnios();

		} catch (Exception e) {
			// TODO: Manejo de Log
			e.printStackTrace();
		}
		anio = FechaUtil.getAnnoActual();
		mes = FechaUtil.getMesActual().shortValue();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String generarReporteParteDiario() throws Exception {
		limpiarMensajesyErrores();
		ParteDiarioLogicFacade logicaParteDiario = new ParteDiarioLogic();

		generarReporteProduccionPuestoTrabajo_exportar(logicaParteDiario);

		// se obtiene el contexto para ubicar la direccion de los reportes
		ServletContext context = org.apache.struts2.ServletActionContext.getServletContext();
		String reportsDirPath = context.getRealPath("/reportes/jasperReport/parteDiario/");

		// se crea el file resolver y se asigna a la variable con el mismo
		// nombre del parametro de
		// jasper que se le asigna al reporte
		REPORT_FILE_RESOLVER = new SimpleFileResolver(new File(reportsDirPath));
		HashMap reportParams = new HashMap();
		reportParams.put("REPORT_FILE_RESOLVER", REPORT_FILE_RESOLVER);

		return SUCCESS;
	}

	private void generarReporteProduccionPuestoTrabajo_exportar(ParteDiarioLogicFacade logicaParteDiario) throws Exception {

		try {

			// SALIO EL LINEA DE NEGOCIO
			reporte = logicaParteDiario.obtenerParteDiarioEntreFechas(Long.valueOf(valorDivision),
					Long.valueOf(getValorSociedad()), Long.valueOf(getValorUnidad()), valorProceso, valorPuestoTrabajo,
					valorProducto, anio.toString(), mes.toString());

			if (reporte == null) {
				addActionMessage(getText(SEGURIDAD_GRUPOUSUARIOPRIVILEGIO_NO_ENCONTRADO));
				return;
			}
		} catch (NumberFormatException e) {
			String mensajeError = getText(ERROR_CONSULTA_LISTA) + " Parte diario" + ", " + getText(FALLA_CONVERSION_NUMERICA);
			logger.error(mensajeError, e);
			addActionError(mensajeError);
			throw new AplicacionGlobalException(mensajeError, e);
		} catch (LogicaException e) {
			String mensajeError = getText(ERROR_CONSULTA_LISTA) + " Parte diario";
			addActionError(mensajeError);
			throw new AplicacionGlobalException(mensajeError, e);
		}

	}

	public String crearReporteStock() {
		if (xlsPDF != null) {
			if (xlsPDF == 1) {
				valorTipoExp = "PDF";
			} else {
				valorTipoExp = "XLS";
			}
		}
		try {
			reporteProduccionStock = tablaKardexLogicFacade.obtenerStockReporte(mes, anio, valorUnidadFiltrado);

			RUTA_CARPETA = "/reportes/jasperReport/parteDiario_Stock/";
		} catch (LogicaException e) {
			// TODO Manejo Log
			e.printStackTrace();
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
		return SUCCESS;
	}

	public String crearReporteProduccion() {
		if (xlsPDF != null) {
			if (xlsPDF == 1) {
				valorTipoExp = "PDF";
			} else {
				valorTipoExp = "XLS";
			}
		}
		try {
			reportePuestoTrabajoProduccion = tablaOperacionLogicFacade.obtenerProduccionPuestotrabajoReporte(mes, anio,
					valorUnidad);
			RUTA_CARPETA = "/reportes/jasperReport/parteDiario_Produccion/";
		} catch (NumberFormatException e) {
			// TODO Manejo Log
			e.printStackTrace();
		} catch (LogicaException e) {
			// TODO Manejo Log
			e.printStackTrace();
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
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String generarReporteDetalleParteDiario() throws Exception {

		try {

			ParteDiarioLogicFacade parteDiarioLogic = new ParteDiarioLogic();
			String fs = File.separator;
			String ruta = ServletActionContext.getServletContext().getRealPath(fs) + "images" + fs;

			ByteArrayOutputStream baos = parteDiarioLogic.generarReporteDetalleParteDiario(valorProceso, valorProducto, anio,
					mes, Long.valueOf(valorDivision), Long.valueOf(valorSociedad), Long.valueOf(valorUnidad), ruta);

			/*
			 * ServletActionContext.getResponse().setHeader(
			 * "Content-Disposition", "attachment; filename=\"" +
			 * ManejadorPropiedades.obtenerPropiedadPorClave(
			 * "reporte.dinamico.parte.diario.nombre.reporte.pdf") + "\"");
			 */

			fileInputStream = new ByteArrayInputStream(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	// REPORTE PARTE DIARIO - EXCEL

	public String generarReporteDetalleParteDiarioEXCEL() throws Exception {

		ParteDiarioLogicFacade parteDiarioLogic = new ParteDiarioLogic();
		String fs = File.separator;
		String ruta = ServletActionContext.getServletContext().getRealPath(fs) + "images" + fs;

		ByteArrayOutputStream baos = parteDiarioLogic.generarReporteDetalleParteDiarioEXCEL(valorProceso, valorProducto, anio,
				mes, Long.valueOf(valorDivision), Long.valueOf(valorSociedad), Long.valueOf(valorUnidad), ruta);

		/*
		 * ServletActionContext.getResponse().setHeader( "Content-Disposition",
		 * "attachment; filename=\"" +
		 * ManejadorPropiedades.obtenerPropiedadPorClave
		 * ("reporte.dinamico.parte.diario.nombre.reporte.xls") + "\"");
		 */

		fileInputStream = new ByteArrayInputStream(baos.toByteArray());
		return SUCCESS;
	}

	private InputStream fileInputStream;

	public InputStream getFileInputStream() {

		return fileInputStream;

	}

	public String getExcelContentType() {
		return "application/vnd.ms-excel";
	}

	public String getPdfContentType() {
		return "application/pdf";
	}

	public static void borrarDirectorio(File directorio) {

		File[] ficheros = directorio.listFiles();
		for (int x = 0; x < ficheros.length; x++) {
			if (ficheros[x].isDirectory()) {
				borrarDirectorio(ficheros[x]);
			}
			ficheros[x].delete();
		}
	}

	/**
	 * Carga las listas de division, sociedad ... de acuerdo a los permisos del
	 * usuario
	 * 
	 * @throws AplicacionGlobalException
	 */
	private void cargarListasFiltros() throws AplicacionGlobalException {
		try {
			if (esUsuarioAdmin()) {
				divisiones = logicaDivision.obtenerDivisiones();
				sociedades = logicaSociedad.obtenerSociedades();
				unidades = logicaUnidad.obtenerUnidades();

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
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

	}

	/**
	 * Carga la lista de sociedades a mostrar de acuerdo a la divici�n
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargaSociedadesParteDiario() throws AplicacionGlobalException {
		try {

			if (getValorDivision() != null && getValorDivision() > 0) {
				sociedades = logicaSociedad.obtenerSociedadesPorCodigoDivision(getValorDivision());
			} else if (!esUsuarioAdmin()) {

				sociedades = logicaSociedad.obtenerSociedadesPorCodigoDivision(usuario.getPersona().getCargo()
						.getDivisionCargoBean().getDivisionBean().getCodigo());
			}

		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Carga la lista de unidades a mostrar por la sociedad
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargaUnidadesParteDiario() throws AplicacionGlobalException {
		try {
			if (getValorSociedad() != null && getValorSociedad() > 0) {

				unidades = logicaUnidad.obtenerUnidadesPorCodigoSociedad(getValorSociedad());
			} else if (!esUsuarioAdmin()) {
				unidades = logicaUnidad.obtenerUnidadesPorCodigoSociedad(Long.parseLong(usuario.getPersona().getCargo()
						.getSociedadCargoBean().getSociedadBean().getCodigo().toString()));

				setValorUnidadFiltrado(usuario.getPersona().getCargo().getUnidadCargoBean().getUnidadBean().getCodigo());
			}

			if (getValorUnidad() != null)
				setLineasNegocio(logicaLineaNegocio.obtenerLineaNegocioPorCodigoUnidad(getValorUnidad()));
		} catch (LogicaException e) {
			addActionError(e.getMensaje());
			throw new AplicacionGlobalException(e.getMensaje(), e);
		}

		return SUCCESS;
	}

	/**
	 * Carga la lista de linea de Negocio a mostrar por la sociedad
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargaLineaNegocioParteDiario() throws AplicacionGlobalException {
		if (getValorUnidad() != null && !getValorUnidad().equals("") && !getValorUnidad().equals(" ")) {
			try {
				lineasNegocio = logicaLineaNegocio.obtenerLineaNegocioBasicoPorCodigoUnidad(new Long(getValorUnidad()));
			} catch (LogicaException e) {
				String mensajeError = getText(ERROR_FALLA_CONSULTA_LISTA) + " " + UnidadBean.class.getName().toString() + " "
						+ e.getMensaje();
				addActionError(mensajeError);
			}
		}
		return SUCCESS;
	}

	/**
	 * Carga la lista de Procesos a mostrar por la sociedad
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargaProcesosParteDiario() throws AplicacionGlobalException {
		if (valorLineaNegocio != null && !valorLineaNegocio.equals("") && !valorLineaNegocio.equals(" ")) {
			try {
				procesos = logicaProceso.obtenerProcesosBasicosPorCodigoLineaNegocio(new Long(valorLineaNegocio));
			} catch (LogicaException e) {
				String mensajeError = getText(ERROR_FALLA_CONSULTA_LISTA) + " " + UnidadBean.class.getName().toString() + " "
						+ e.getMensaje();
				addActionError(mensajeError);
			}
		}
		return SUCCESS;
	}

	/**
	 * Carga la lista de producto a mostrar por la sociedad
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargaProductoParteDiario() throws AplicacionGlobalException {
		if (valorProceso != null && !valorProceso.equals("") && !valorProceso.equals(" ")) {
			try {

				productos = logicaProducto.obtenerProductosPorProceso(Long.valueOf(valorProceso));

			} catch (LogicaException e) {
				String mensajeError = getText(ERROR_FALLA_CONSULTA_LISTA) + " " + UnidadBean.class.getName().toString() + " "
						+ e.getMensaje();
				addActionError(mensajeError);
			}
		}
		return SUCCESS;
	}

	/**
	 * Carga la lista de puestos de trabajo a mostrar por la unidad
	 * 
	 * @return
	 * @throws AplicacionGlobalException
	 */
	public String cargaPuestoTrabajoParteDiario() throws AplicacionGlobalException {
		if (getValorUnidad() != null && !getValorUnidad().equals("") && !getValorUnidad().equals(" ")) {
			try {

				puestosTrabajo = logicaPuestoTrabajo.obtenerPuestosTrabajoPorUnidad(Long.valueOf(getValorUnidad()));

			} catch (LogicaException e) {
				String mensajeError = getText(ERROR_FALLA_CONSULTA_LISTA) + " " + UnidadBean.class.getName().toString() + " "
						+ e.getMensaje();
				addActionError(mensajeError);
			}
		}
		return SUCCESS;
	}

	private void cargarMeses() {
		meseslst = new ArrayList<MesBean>();
		meseslst.add(new MesBeanImpl(Long.valueOf(1), "Enero"));
		meseslst.add(new MesBeanImpl(Long.valueOf(2), "Febrero"));
		meseslst.add(new MesBeanImpl(Long.valueOf(3), "Marzo"));
		meseslst.add(new MesBeanImpl(Long.valueOf(4), "Abril"));
		meseslst.add(new MesBeanImpl(Long.valueOf(5), "Mayo"));
		meseslst.add(new MesBeanImpl(Long.valueOf(6), "Junio"));
		meseslst.add(new MesBeanImpl(Long.valueOf(7), "Julio"));
		meseslst.add(new MesBeanImpl(Long.valueOf(8), "Agosto"));
		meseslst.add(new MesBeanImpl(Long.valueOf(9), "Septiembre"));
		meseslst.add(new MesBeanImpl(Long.valueOf(10), "Octubre"));
		meseslst.add(new MesBeanImpl(Long.valueOf(11), "Noviembre"));
		meseslst.add(new MesBeanImpl(Long.valueOf(12), "Diciembre"));

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
	 * @return the procesos
	 */
	public List<ProcesoBean> getProcesos() {
		return procesos;
	}

	/**
	 * @param procesos the procesos to set
	 */
	public void setProcesos(List<ProcesoBean> procesos) {
		this.procesos = procesos;
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
	public String getValorLineaNegocio() {
		return valorLineaNegocio;
	}

	/**
	 * @param valorLineaNegocio the valorLineaNegocio to set
	 */
	public void setValorLineaNegocio(String valorLineaNegocio) {
		this.valorLineaNegocio = valorLineaNegocio;
	}

	/**
	 * @return the valorProceso
	 */
	public Long getValorProceso() {
		return valorProceso;
	}

	/**
	 * @param valorProceso the valorProceso to set
	 */
	public void setValorProceso(Long valorProceso) {
		this.valorProceso = valorProceso;
	}

	/**
	 * @return the valorProducto
	 */
	public Long getValorProducto() {
		return valorProducto;
	}

	/**
	 * @param valorProducto the valorProducto to set
	 */
	public void setValorProducto(Long valorProducto) {
		this.valorProducto = valorProducto;
	}

	/**
	 * @return the logicaDivision
	 */
	public static DivisionLogicFacade getLogicaDivision() {
		return logicaDivision;
	}

	/**
	 * @param logicaDivision the logicaDivision to set
	 */
	public static void setLogicaDivision(DivisionLogicFacade logicaDivision) {
		ReporteParteDiarioAction.logicaDivision = logicaDivision;
	}

	/**
	 * @return the logicaSociedad
	 */
	public static SociedadLogicFacade getLogicaSociedad() {
		return logicaSociedad;
	}

	/**
	 * @param logicaSociedad the logicaSociedad to set
	 */
	public static void setLogicaSociedad(SociedadLogicFacade logicaSociedad) {
		ReporteParteDiarioAction.logicaSociedad = logicaSociedad;
	}

	/**
	 * @return the logicaUnidad
	 */
	public static UnidadLogicFacade getLogicaUnidad() {
		return logicaUnidad;
	}

	/**
	 * @param logicaUnidad the logicaUnidad to set
	 */
	public static void setLogicaUnidad(UnidadLogicFacade logicaUnidad) {
		ReporteParteDiarioAction.logicaUnidad = logicaUnidad;
	}

	/**
	 * @return the logicaLineaNegocio
	 */
	public static LineaNegocioLogicFacade getLogicaLineaNegocio() {
		return logicaLineaNegocio;
	}

	/**
	 * @param logicaLineaNegocio the logicaLineaNegocio to set
	 */
	public static void setLogicaLineaNegocio(LineaNegocioLogicFacade logicaLineaNegocio) {
		ReporteParteDiarioAction.logicaLineaNegocio = logicaLineaNegocio;
	}

	/**
	 * @return the logicaProceso
	 */
	public static ProcesoLogicFacade getLogicaProceso() {
		return logicaProceso;
	}

	/**
	 * @param logicaProceso the logicaProceso to set
	 */
	public static void setLogicaProceso(ProcesoLogicFacade logicaProceso) {
		ReporteParteDiarioAction.logicaProceso = logicaProceso;
	}

	/**
	 * @return the logicaProducto
	 */
	public static ProductoLogicFacade getLogicaProducto() {
		return logicaProducto;
	}

	/**
	 * @param logicaProducto the logicaProducto to set
	 */
	public static void setLogicaProducto(ProductoLogicFacade logicaProducto) {
		ReporteParteDiarioAction.logicaProducto = logicaProducto;
	}

	/**
	 * @return the logger
	 */
	public Log getLogger() {
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public void setLogger(Log logger) {
		this.logger = logger;
	}

	/**
	 * @return the parameter
	 */
	public Map getParameter() {
		return parameter;
	}

	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(Map parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the valorTipoReporte
	 */
	public String getValorTipoReporte() {
		return valorTipoReporte;
	}

	/**
	 * @param valorTipoReporte the valorTipoReporte to set
	 */
	public void setValorTipoReporte(String valorTipoReporte) {
		this.valorTipoReporte = valorTipoReporte;
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

	/**
	 * @return the dataDireccionReporte
	 */
	public String getDataDireccionReporte() {
		return dataDireccionReporte;
	}

	/**
	 * @param dataDireccionReporte the dataDireccionReporte to set
	 */
	public void setDataDireccionReporte(String dataDireccionReporte) {
		this.dataDireccionReporte = dataDireccionReporte;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public ReporteParteDiarioBean getReporte() {
		return reporte;
	}

	public void setReporte(ReporteParteDiarioBean reporte) {
		this.reporte = reporte;
	}

	public List<ReporteParteDiarioBean> getReporteb() {
		return reporteb;
	}

	public void setReporteb(List<ReporteParteDiarioBean> reporteb) {
		this.reporteb = reporteb;
	}

	public Long getValorPuestoTrabajo() {
		return valorPuestoTrabajo;
	}

	public void setValorPuestoTrabajo(Long valorPuestoTrabajo) {
		this.valorPuestoTrabajo = valorPuestoTrabajo;
	}

	public String getValorCargaCompletada() {
		return valorCargaCompletada;
	}

	public void setValorCargaCompletada(String valorCargaCompletada) {
		this.valorCargaCompletada = valorCargaCompletada;
	}

	/**
	 * @return the mes
	 */
	public Short getMes() {
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
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return the anios
	 */
	public List<UtilBean> getAnios() {
		return anios;
	}

	/**
	 * @param anios the anios to set
	 */
	public void setAnios(List<UtilBean> anios) {
		this.anios = anios;
	}

	/**
	 * @return the meses
	 */
	public List<FechaUtil.MESES_ESPANNOL> getMeses() {
		return meses;
	}

	/**
	 * @param meses the meses to set
	 */
	public void setMeses(List<FechaUtil.MESES_ESPANNOL> meses) {
		this.meses = meses;
	}

	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}



	/**
	 * @return the fileInputStream
	 */
	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}

	/**
	 * @return the valorTipoExp
	 */
	public String getValorTipoExp() {
		return valorTipoExp;
	}

	/**
	 * @param valorTipoExp the valorTipoExp to set
	 */
	public void setValorTipoExp(String valorTipoExp) {
		this.valorTipoExp = valorTipoExp;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getValorReporte() {
		return valorReporte;
	}

	public void setValorReporte(int valorReporte) {
		this.valorReporte = valorReporte;
	}

	public List<MesBean> getMeseslst() {
		return meseslst;
	}

	public void setMeseslst(List<MesBean> meseslst) {
		this.meseslst = meseslst;
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
	 * @return the reporteProduccionStock
	 */
	public ReporteGestionStockProduccionBean getReporteProduccionStock() {
		return reporteProduccionStock;
	}

	/**
	 * @param reporteProduccionStock the reporteProduccionStock to set
	 */
	public void setReporteProduccionStock(ReporteGestionStockProduccionBean reporteProduccionStock) {
		this.reporteProduccionStock = reporteProduccionStock;
	}

	/**
	 * @return the reportePuestoTrabajoProduccion
	 */
	public ReportePuestoTrabajoProduccionBean getReportePuestoTrabajoProduccion() {
		return reportePuestoTrabajoProduccion;
	}

	/**
	 * @param reportePuestoTrabajoProduccion the reportePuestoTrabajoProduccion
	 *            to set
	 */
	public void setReportePuestoTrabajoProduccion(ReportePuestoTrabajoProduccionBean reportePuestoTrabajoProduccion) {
		this.reportePuestoTrabajoProduccion = reportePuestoTrabajoProduccion;
	}

	public Integer getXlsPDF() {
		return xlsPDF;
	}

	public void setXlsPDF(Integer xlsPDF) {
		this.xlsPDF = xlsPDF;
	}

}
