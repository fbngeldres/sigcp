package pe.com.pacasmayo.sgcp.logica.partediario;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteEquivalenciasccvarcalidadBean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteDiarioSub_A_A_A_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteDiarioSub_A_A_B_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteDiarioSub_A_A_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteParteDiarioSub_A_Bean;
import pe.com.pacasmayo.sgcp.bean.ReporteVarCalidadProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteVarCalidadProductoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ReporteVarCalidadPuestoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteVariableCalidadBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ProductoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.IntegracionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.integrador.excepciones.IntegradorExcepcion;
import pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade;
import pe.com.pacasmayo.sgcp.integrador.operacion.impl.OperacionesImpl;
import pe.com.pacasmayo.sgcp.logica.facade.AjusteProduccionMesLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MovimientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParametroSistemaLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ParteDiarioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.AjusteProduccionMesLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.ParametroSistemaLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Almacen;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Balanceproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Equivalenciasccvarcalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadopartediario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorconsumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorvariacionproduccionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorvariacionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Kardexmedioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Partediario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productogenerado;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablevariacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.RendimientoTermico;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablaoperacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Valorpromvariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.BalanceProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteNotificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ConsumoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.DivisionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EquivalenciasccvarcalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoParteDiarioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorconsumopuestotrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.IngresoProductoProcesoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MedicionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoAjusteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OrdenProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParteDiarioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PeriodoContableQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProcesoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionDiariaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoGeneradoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoVariableVariacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.Querier;
import pe.com.pacasmayo.sgcp.persistencia.querier.RendimientoTermicoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.SociedadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaOperacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ValorPromVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;
import pe.com.pacasmayo.sgcp.util.PDFFactory;
import pe.com.pacasmayo.sgcp.util.XLSFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;



public class ParteDiarioLogic implements ConstantesFiltros, ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		ParteDiarioLogicFacade, ConstantesLogicaNegocio {

	private static final String TODOS = "Todos";
	private final String CONCEPTO_SALDO_INICIAL = "Saldo Inicial";

	private final String CONCEPTO_PRODUCCION = "Producci\u00F3n";
	private final String CONCEPTO_CONSUMO = "Consumo";

	private static OperacionesFacade operacionesFacade = new OperacionesImpl();

	public enum ProcesoFormula {
		CLINKERIZACION, CALCINACION_CAL
	}

	private Logger logger = Logger.getLogger(this.getClass());
	private String mensajeError = "";
	private static BeanFactory beanFactory;
	private ParametroSistemaLogicFacade parametroSistemaLogic = new ParametroSistemaLogic();
	private final String CLINKERIZACION_HV = ManejadorPropiedades.obtenerPropiedadPorClave(PROCESO_CLINKERIZACION_HV)
			.toLowerCase();
	private final String CLINKERIZACION_HH = ManejadorPropiedades.obtenerPropiedadPorClave(PROCESO_CLINKERIZACION_HH)
			.toLowerCase();
	private final String PRODUCTO_CLKI = "CLK - I";
	private final String CLINKERIZACION = "CLINKERIZACIÓN";
	private final String PROC_MOLIENDA_CAL = ManejadorPropiedades.obtenerPropiedadPorClave(PROCESO_MOLIENDA_CAL).toLowerCase();
	private final String PROC_CALCINACION_CAL = ManejadorPropiedades.obtenerPropiedadPorClave(PROCESO_CALCINACION_CAL)
			.toLowerCase();
	private final String SIGLAS_CRUDO = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_CRUDO_SIGLAS).toLowerCase();
	private final String PETROLEO_BUNKER = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_PETROLEOBUNKER);
	private final String TIPO_COMBUSTIBLE = ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_CATEG_PRODUCTO_COMBUSTIBLE)
			.toLowerCase();
	private final String PODER_CALORIFICO = ManejadorPropiedades
			.obtenerPropiedadPorClave(VAR_CALIDAD_PODER_CALORIFICO_CARBON_PETROLEO);

	private final double FACTOR_GALONES = 3.785412d;

	private final long PK_PRODUCTO_CAL_GRANULADA = new Long(
			ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CAL_GRANULADA));

	String PERDIDAS_FUEGO = ManejadorPropiedades.obtenerPropiedadPorClave(VAR_CALIDAD_PERDIDA_FUEGO);
	private static AjusteProduccionMesLogicFacade ajusteProduccionFacade = new AjusteProduccionMesLogic();
	private static MovimientoLogicFacade movimientoLogicFacade = new MovimientoLogic();

	@SuppressWarnings("static-access")
	public ParteDiarioLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	private ReporteParteDiarioBean llenarCabezalReporteParteDiario(ReporteParteDiarioBean Reporte, Long division, Long sociedad,
			Long unidad, Long proceso, Long puestoTrabajo, Long producto, String fechaInicio, String fechaFin)
			throws ElementoNoEncontradoException {
		Reporte.setDivision(DivisionQuerier.getById(division).getDescripcionDivision());
		Reporte.setSociedad(SociedadQuerier.getById(sociedad).getDescripcionSociedad());
		Reporte.setUnidad(UnidadQuerier.getById(unidad).getDescripcionUnidad());
		// Reporte.setLineaNegocio(LineaNegocioQuerier.getById(lineaNegocio).getDescripcionLineanegocio());
		if (proceso != null) {
			Reporte.setProceso(ProcesoQuerier.getById(proceso).getDescripcionProceso());
		} else {
			Reporte.setProceso(TODOS);
		}

		if (puestoTrabajo == null)
			Reporte.setPuestoTrabajo(TODOS);
		else
			Reporte.setPuestoTrabajo(PuestoTrabajoQuerier.getById(puestoTrabajo).getDescripcionPuestotrabajo());

		if (producto == null)
			Reporte.setProducto(TODOS);
		else
			Reporte.setProducto(ProductoQuerier.getById(producto).getDescripcionProducto());

		return Reporte;
	}

	public ReporteParteDiarioBean obtenerParteDiarioEntreFechas(Long division, Long sociedad, Long unidad, Long proceso,
			Long puestoTrabajo, Long producto, String anio, String mes) throws AplicacionException, LogicaException,
			ParseException {

		logger.info("*****************obtenerParteDiarioEntreFechas");
		// se obtiene la sesion
		Session session = crearSesion();

		ReporteParteDiarioBean Reporte = new ReporteParteDiarioBean();
		List<Productogenerado> listaProductoGenerado;
		DateFormat df = new SimpleDateFormat(FechaUtil.PATRON_FECHA_APLICACION);
		/**
		 * listas de los reportes
		 */
		List<ReporteParteDiarioSub_A_Bean> ReporteParteDiarioSub = new ArrayList<ReporteParteDiarioSub_A_Bean>();
		List<ReporteParteDiarioSub_A_A_Bean> registroReporteDiarioMes = new ArrayList<ReporteParteDiarioSub_A_A_Bean>();

		Integer anho = Integer.parseInt(anio);
		Integer mes_ = Integer.parseInt(mes) - 1;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(anho, mes_, 1);
		String fechaInicio = "0" + cal.getActualMinimum(GregorianCalendar.DAY_OF_MONTH) + "/" + mes + "/" + anio;
		String fechaFin = FechaUtil.convertirDateToString(FechaUtil.getUltimoDiaMes(Short.valueOf(mes_.toString()),
				Integer.valueOf(anio)).getTime());
		try {

			Calendar fechaRecorrerInicio = new GregorianCalendar();
			fechaRecorrerInicio.setTime(df.parse(fechaInicio));
			fechaRecorrerInicio.set(Calendar.DATE, 1);

			Calendar fechaRecorrerFin = new GregorianCalendar();
			fechaRecorrerFin.setTime(df.parse(fechaFin));
			fechaRecorrerFin.set(Calendar.DATE, 1);
			int cicloVuelta = PRIMERA_VUELTA;

			Calendar fechaLimiteInicial = new GregorianCalendar();
			Calendar fechaLimiteFinal = new GregorianCalendar();
			Date fechaFinal = null;
			Date fechaInicial = null;

			Calendar recorrerFechaTEMP = new GregorianCalendar();
			// llenar Cabezal
			Reporte = llenarCabezalReporteParteDiario(Reporte, division, sociedad, unidad, proceso, puestoTrabajo, producto,
					fechaInicio, fechaFin);
			Long puestoTrabajoTEMP = null;
			Long productoTEMP = null;
			int diaFechaTEMP = 99;
			int contadorPuestoTrabajo = 0;
			ReporteParteDiarioSub_A_A_A_Bean registro_ReporteParteDiarioSub_A_A_A_Bean_TM = null;
			ReporteParteDiarioSub_A_A_A_Bean registro_ReporteParteDiarioSub_A_A_A_Bean_HR = null;
			ReporteParteDiarioSub_A_A_A_Bean registro_ReporteParteDiarioSub_A_A_A_Bean_TMPH = null;
			ReporteParteDiarioSub_A_A_A_Bean registro_ReporteParteDiarioSub_A_A_A_Bean_KCAL = null;
			ReporteParteDiarioSub_A_A_B_Bean registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TM = null;
			// -----------------------------------------------
			ReporteParteDiarioSub_A_A_B_Bean registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_HR = null;
			ReporteParteDiarioSub_A_A_B_Bean registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TMPH = null;
			ReporteParteDiarioSub_A_A_B_Bean registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_KCAL = null;
			// -----------------------------------------------

			String puestoTrabCalculaKcal = ManejadorPropiedades.obtenerPropiedadPorClave(PUESTO_TRABAJO_CALCULA_KCAL);

			while (fechaRecorrerInicio.compareTo(fechaRecorrerFin) == 0 || fechaRecorrerInicio.compareTo(fechaRecorrerFin) < 0) {
				// Nuevo Mes
				ReporteParteDiarioSub_A_Bean registro_ReporteParteDiarioSub_A_Bean = new ReporteParteDiarioSub_A_Bean();

				registro_ReporteParteDiarioSub_A_Bean.setMes(new Integer(fechaRecorrerInicio.get(Calendar.MONTH) + 1).toString());
				registro_ReporteParteDiarioSub_A_Bean.setAnno(new Integer(fechaRecorrerInicio.get(Calendar.YEAR)).toString());

				if (fechaRecorrerInicio.compareTo(fechaRecorrerFin) == 0) {
					fechaLimiteFinal = new GregorianCalendar();
					fechaLimiteFinal.setTime(df.parse(fechaFin));
					fechaFinal = fechaLimiteFinal.getTime();
				}
				if (fechaRecorrerInicio.compareTo(fechaRecorrerFin) < 0) {
					fechaLimiteFinal = null;
					fechaFinal = null;
				}
				if (cicloVuelta == PRIMERA_VUELTA) {
					if (fechaLimiteInicial != null) {
						fechaLimiteInicial.setTime(df.parse(fechaInicio));
						fechaInicial = fechaLimiteInicial.getTime();
					}
					cicloVuelta = YA_NO_ES_LA_PRIMERA_VUELTA;
				} else {
					fechaLimiteInicial = null;
					fechaInicial = null;
				}

				listaProductoGenerado = ProductoGeneradoQuerier.obtenerProductoGeneradoPorVarioFiltros(puestoTrabajo, proceso,
						producto, Integer.valueOf(fechaRecorrerInicio.get(Calendar.YEAR)),
						Integer.valueOf(fechaRecorrerInicio.get(Calendar.MONTH)), fechaFinal, fechaInicial);

				// Exception : si es proceso Clinkerizacion HH + Clinkerizacion
				// HV
				if (proceso != null && proceso.compareTo(39L) == 0) {
					if (listaProductoGenerado == null) {
						listaProductoGenerado = ProductoGeneradoQuerier.obtenerProductoGeneradoPorVarioFiltros(puestoTrabajo,
								40L, producto, Integer.valueOf(fechaRecorrerInicio.get(Calendar.YEAR)),
								Integer.valueOf(fechaRecorrerInicio.get(Calendar.MONTH)), fechaFinal, fechaInicial);
					} else {
						listaProductoGenerado.addAll(ProductoGeneradoQuerier.obtenerProductoGeneradoPorVarioFiltros(
								puestoTrabajo, 40L, producto, Integer.valueOf(fechaRecorrerInicio.get(Calendar.YEAR)),
								Integer.valueOf(fechaRecorrerInicio.get(Calendar.MONTH)), fechaFinal, fechaInicial));
					}
				}

				if (!listaProductoGenerado.isEmpty()) {

					registroReporteDiarioMes = new ArrayList<ReporteParteDiarioSub_A_A_Bean>();
					contadorPuestoTrabajo = 0;
					for (int a = 0; a < listaProductoGenerado.size(); a++) {
						Puestotrabajo puesto = listaProductoGenerado.get(a).getTablaoperacion().getProduccionpuestotrabajo()
								.getPuestotrabajo();
						String codigoPuestotrabajoStr = puesto.getPkCodigoPuestotrabajo().toString();
						// productoTEMP = null;
						boolean calculaKcal = puestoTrabCalculaKcal.indexOf(codigoPuestotrabajoStr) >= 0;
						if (puestoTrabajoTEMP != puesto.getPkCodigoPuestotrabajo()) {
							puestoTrabajoTEMP = puesto.getPkCodigoPuestotrabajo();
							productoTEMP = null;
							ReporteParteDiarioSub_A_A_Bean registro_ReporteParteDiarioSub_A_A_Bean = new ReporteParteDiarioSub_A_A_Bean();
							List<ReporteParteDiarioSub_A_A_A_Bean> registroReportePuestoTrabajo = new ArrayList<ReporteParteDiarioSub_A_A_A_Bean>();
							List<ReporteParteDiarioSub_A_A_B_Bean> listaRegistroReporteProducto = new ArrayList<ReporteParteDiarioSub_A_A_B_Bean>();
							registro_ReporteParteDiarioSub_A_A_A_Bean_TM = new ReporteParteDiarioSub_A_A_A_Bean();
							registro_ReporteParteDiarioSub_A_A_A_Bean_HR = new ReporteParteDiarioSub_A_A_A_Bean();
							registro_ReporteParteDiarioSub_A_A_A_Bean_TMPH = new ReporteParteDiarioSub_A_A_A_Bean();
							registro_ReporteParteDiarioSub_A_A_A_Bean_KCAL = new ReporteParteDiarioSub_A_A_A_Bean();

							registro_ReporteParteDiarioSub_A_A_A_Bean_TM.setConcepto("TM");
							registro_ReporteParteDiarioSub_A_A_A_Bean_HR.setConcepto("HR");
							registro_ReporteParteDiarioSub_A_A_A_Bean_TMPH.setConcepto("TMPH");
							if (calculaKcal) {
								registro_ReporteParteDiarioSub_A_A_A_Bean_KCAL.setConcepto("KCAL");
							}

							registroReportePuestoTrabajo.add(registro_ReporteParteDiarioSub_A_A_A_Bean_TM);
							registroReportePuestoTrabajo.add(registro_ReporteParteDiarioSub_A_A_A_Bean_HR);
							registroReportePuestoTrabajo.add(registro_ReporteParteDiarioSub_A_A_A_Bean_TMPH);

							if (calculaKcal) {
								registroReportePuestoTrabajo.add(registro_ReporteParteDiarioSub_A_A_A_Bean_KCAL);
							}

							registro_ReporteParteDiarioSub_A_A_Bean.setPuestoTrabajo(puesto.getDescripcionPuestotrabajo());
							registro_ReporteParteDiarioSub_A_A_Bean.setRegistroReportePuestoTrabajo(registroReportePuestoTrabajo);
							registro_ReporteParteDiarioSub_A_A_Bean.setRegistroReporteProducto(listaRegistroReporteProducto);
							registroReporteDiarioMes.add(registro_ReporteParteDiarioSub_A_A_Bean);

							diaFechaTEMP = 99;
							contadorPuestoTrabajo++;
						}
						recorrerFechaTEMP.setTime(listaProductoGenerado.get(a).getTablaoperacion().getFechaTablaoperacion());

						if (diaFechaTEMP != recorrerFechaTEMP.get(Calendar.DATE)) {
							diaFechaTEMP = recorrerFechaTEMP.get(Calendar.DATE);
							// LLENAR TM
							// METODO LLENA LOS VALORES DEL MES DEPENDIENDO DE
							// LA FECHA QUE SE ENVIE AL METODO diaFechaTEMP
							if (NumberUtil.decimalMayor(listaProductoGenerado.get(a).getTablaoperacion()
									.getTotalTmTablaoperacion(), 0.0)) {
								llenarRegistroPuestoTrabajo(registro_ReporteParteDiarioSub_A_A_A_Bean_TM, diaFechaTEMP,
										listaProductoGenerado.get(a).getTablaoperacion().getTotalTmTablaoperacion());
							}

							// LLENAR HR
							if (NumberUtil.decimalMayor(listaProductoGenerado.get(a).getTablaoperacion()
									.getTotalHoraTablaoperacion(), 0.0)) {
								llenarRegistroPuestoTrabajo(registro_ReporteParteDiarioSub_A_A_A_Bean_HR, diaFechaTEMP,
										listaProductoGenerado.get(a).getTablaoperacion().getTotalHoraTablaoperacion());
							}

							if (calculaKcal) {
								if (NumberUtil.decimalMayor(listaProductoGenerado.get(a).getKcalProductogenerado(), 0.0)) {
									llenarRegistroPuestoTrabajo(registro_ReporteParteDiarioSub_A_A_A_Bean_KCAL, diaFechaTEMP,
											listaProductoGenerado.get(a).getKcalProductogenerado());
								}

							}

							Double totalTm = listaProductoGenerado.get(a).getTablaoperacion().getTotalTmTablaoperacion();
							Double totalHoras = listaProductoGenerado.get(a).getTablaoperacion().getTotalHoraTablaoperacion();
							Double totalTmPorHora = null;
							if (totalHoras != 0 && totalTm != null && totalHoras != null) {
								totalTmPorHora = NumberUtil.reducirAdosDecimales(totalTm / totalHoras);
							}
							// PARA LLENAR TMPH
							llenarRegistroPuestoTrabajo(registro_ReporteParteDiarioSub_A_A_A_Bean_TMPH, diaFechaTEMP,
									totalTmPorHora);

						}

						Ordenproduccion ordenproduccion = listaProductoGenerado.get(a).getOrdenproduccion();

						Double produccion = ProductoGeneradoQuerier.obtenerCantidadProductoPorFechaOrdenProdYPuest(
								ordenproduccion, recorrerFechaTEMP.getTime(), Long.valueOf(codigoPuestotrabajoStr));

						Double kcal = listaProductoGenerado.get(a).getKcalProductogenerado();
						Double horaProd = listaProductoGenerado.get(a).getHorasProductogenerado();

						double tmph = 0.0;

						if (horaProd != null && NumberUtil.decimalMayor(horaProd, 0.0) && produccion != null) {
							tmph = NumberUtil.reducirAdosDecimales(produccion / horaProd);
						}

						if (productoTEMP != ordenproduccion.getProduccion().getProducto().getPkCodigoProducto()) {
							productoTEMP = ordenproduccion.getProduccion().getProducto().getPkCodigoProducto();

							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_HR = new ReporteParteDiarioSub_A_A_B_Bean();
							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TMPH = new ReporteParteDiarioSub_A_A_B_Bean();
							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TM = new ReporteParteDiarioSub_A_A_B_Bean();
							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_KCAL = new ReporteParteDiarioSub_A_A_B_Bean();

							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TM.setProducto(ordenproduccion.getProduccion()
									.getProducto().getNombreProducto());

							if (calculaKcal) {
								registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_KCAL.setConcepto("KCAL");
							}

							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_HR.setConcepto("HR");
							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TMPH.setConcepto("TMPH");
							registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TM.setConcepto("TM");
							registroReporteDiarioMes.get(contadorPuestoTrabajo - 1).getRegistroReporteProducto()
									.add(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TM);
							registroReporteDiarioMes.get(contadorPuestoTrabajo - 1).getRegistroReporteProducto()
									.add(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_HR);
							registroReporteDiarioMes.get(contadorPuestoTrabajo - 1).getRegistroReporteProducto()
									.add(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TMPH);

							if (calculaKcal) {
								registroReporteDiarioMes.get(contadorPuestoTrabajo - 1).getRegistroReporteProducto()
										.add(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_KCAL);

							}

						}

						if (calculaKcal) {
							if (NumberUtil.decimalMayor(kcal, 0.0)) {
								llenarRegistroProducto(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_KCAL,
										recorrerFechaTEMP.get(Calendar.DATE), kcal);
							}

						}

						if (NumberUtil.decimalMayor(produccion, 0.0)) {
							llenarRegistroProducto(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TM,
									recorrerFechaTEMP.get(Calendar.DATE), produccion);
						}
						if (NumberUtil.decimalMayor(horaProd, 0.0)) {
							llenarRegistroProducto(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_HR,
									recorrerFechaTEMP.get(Calendar.DATE), horaProd);
						}
						if (NumberUtil.decimalMayor(tmph, 0.0)) {
							llenarRegistroProducto(registro_ReporteParteDiarioSub_A_A_B_Bean_PRODUCTO_TMPH,
									recorrerFechaTEMP.get(Calendar.DATE), tmph);
						}

					}

					for (int i = 0; i < registroReporteDiarioMes.size(); i++) {
						Double vTM = 0d;
						Double vHR = 0d;
						Double vTMPH = 0d;
						for (int j = 0; j < registroReporteDiarioMes.get(i).getRegistroReporteProducto().size(); j++) {
							if (registroReporteDiarioMes.get(i).getRegistroReporteProducto().get(j).getConcepto() == "TM")
								vTM = registroReporteDiarioMes.get(i).getRegistroReporteProducto().get(j).getSuma();
							if (registroReporteDiarioMes.get(i).getRegistroReporteProducto().get(j).getConcepto() == "HR")
								vHR = registroReporteDiarioMes.get(i).getRegistroReporteProducto().get(j).getSuma();
							if (registroReporteDiarioMes.get(i).getRegistroReporteProducto().get(j).getConcepto() == "TMPH") {
								vTMPH = vTM / vHR;
								registroReporteDiarioMes.get(i).getRegistroReporteProducto().get(j).setValorTMPH(vTMPH);
							}
						}
						Double vTM2 = 0d;
						Double vHR2 = 0d;
						Double vTMPH2 = 0d;
						for (int j = 0; j < registroReporteDiarioMes.get(i).getRegistroReportePuestoTrabajo().size(); j++) {
							if (registroReporteDiarioMes.get(i).getRegistroReportePuestoTrabajo().get(j).getConcepto() == "TM")
								vTM2 = registroReporteDiarioMes.get(i).getRegistroReportePuestoTrabajo().get(j).getSuma();
							if (registroReporteDiarioMes.get(i).getRegistroReportePuestoTrabajo().get(j).getConcepto() == "HR")
								vHR2 = registroReporteDiarioMes.get(i).getRegistroReportePuestoTrabajo().get(j).getSuma();
							if (registroReporteDiarioMes.get(i).getRegistroReportePuestoTrabajo().get(j).getConcepto() == "TMPH") {
								vTMPH2 = vTM2 / vHR2;
								registroReporteDiarioMes.get(i).getRegistroReportePuestoTrabajo().get(j).setValorTMPH(vTMPH2);
							}
						}

					}

				} else {
					registroReporteDiarioMes = null;
				}

				if (registroReporteDiarioMes != null) {
					registro_ReporteParteDiarioSub_A_Bean.setRegistroReporteDiarioMes(registroReporteDiarioMes);
					ReporteParteDiarioSub.add(registro_ReporteParteDiarioSub_A_Bean);
				}
				fechaRecorrerInicio.add(Calendar.MONTH, 1);

			}
			Reporte.setReporteParteDiarioSub(ReporteParteDiarioSub);

			Reporte.setMes(FechaUtil.numeroMesANombreMes(Short.valueOf(mes)));
			// Reporte.setFechaFin(fechaFin);

		} catch (SesionVencidaException e) {
			logger.error(e.getMensaje(), e);
			throw new LogicaException(e.getMessage(), e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje(), e);
			throw new LogicaException(e.getMessage(), e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new LogicaException(e.getMessage(), e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return Reporte;
	}

	private void llenarRegistroPuestoTrabajo(ReporteParteDiarioSub_A_A_A_Bean Registro, int variable, Double valor) {

		switch (variable) {
		case 1:
			Registro.setValor_1(valor);

			break;
		case 2:
			Registro.setValor_2(valor);
			break;
		case 3:
			Registro.setValor_3(valor);
			break;
		case 4:
			Registro.setValor_4(valor);
			break;
		case 5:
			Registro.setValor_5(valor);
			break;
		case 6:
			Registro.setValor_6(valor);
			break;
		case 7:
			Registro.setValor_7(valor);
			break;
		case 8:
			Registro.setValor_8(valor);
			break;
		case 9:
			Registro.setValor_9(valor);
			break;
		case 10:
			Registro.setValor_10(valor);
			break;
		case 11:
			Registro.setValor_11(valor);
			break;
		case 12:
			Registro.setValor_12(valor);
			break;
		case 13:
			Registro.setValor_13(valor);
			break;
		case 14:
			Registro.setValor_14(valor);
			break;
		case 15:
			Registro.setValor_15(valor);
			break;
		case 16:
			Registro.setValor_16(valor);
			break;
		case 17:
			Registro.setValor_17(valor);
			break;
		case 18:
			Registro.setValor_18(valor);
			break;
		case 19:
			Registro.setValor_19(valor);
			break;
		case 20:
			Registro.setValor_20(valor);
			break;
		case 21:
			Registro.setValor_21(valor);
			break;
		case 22:
			Registro.setValor_22(valor);
			break;
		case 23:
			Registro.setValor_23(valor);
			break;
		case 24:
			Registro.setValor_24(valor);
			break;
		case 25:
			Registro.setValor_25(valor);
			break;
		case 26:
			Registro.setValor_26(valor);
			break;
		case 27:
			Registro.setValor_27(valor);
			break;
		case 28:
			Registro.setValor_28(valor);
			break;
		case 29:
			Registro.setValor_29(valor);
			break;
		case 30:
			Registro.setValor_30(valor);
			break;
		case 31:
			Registro.setValor_31(valor);
			break;
		}

	}

	private void llenarRegistroProducto(ReporteParteDiarioSub_A_A_B_Bean Registro, int variable, Double valor) {

		switch (variable) {
		case 1:
			Registro.setValor_1(valor);

			break;
		case 2:
			Registro.setValor_2(valor);
			break;
		case 3:
			Registro.setValor_3(valor);
			break;
		case 4:
			Registro.setValor_4(valor);
			break;
		case 5:
			Registro.setValor_5(valor);
			break;
		case 6:
			Registro.setValor_6(valor);
			break;
		case 7:
			Registro.setValor_7(valor);
			break;
		case 8:
			Registro.setValor_8(valor);
			break;
		case 9:
			Registro.setValor_9(valor);
			break;
		case 10:
			Registro.setValor_10(valor);
			break;
		case 11:
			Registro.setValor_11(valor);
			break;
		case 12:
			Registro.setValor_12(valor);
			break;
		case 13:
			Registro.setValor_13(valor);
			break;
		case 14:
			Registro.setValor_14(valor);
			break;
		case 15:
			Registro.setValor_15(valor);
			break;
		case 16:
			Registro.setValor_16(valor);
			break;
		case 17:
			Registro.setValor_17(valor);
			break;
		case 18:
			Registro.setValor_18(valor);
			break;
		case 19:
			Registro.setValor_19(valor);
			break;
		case 20:
			Registro.setValor_20(valor);
			break;
		case 21:
			Registro.setValor_21(valor);
			break;
		case 22:
			Registro.setValor_22(valor);
			break;
		case 23:
			Registro.setValor_23(valor);
			break;
		case 24:
			Registro.setValor_24(valor);
			break;
		case 25:
			Registro.setValor_25(valor);
			break;
		case 26:
			Registro.setValor_26(valor);
			break;
		case 27:
			Registro.setValor_27(valor);
			break;
		case 28:
			Registro.setValor_28(valor);
			break;
		case 29:
			Registro.setValor_29(valor);
			break;
		case 30:
			Registro.setValor_30(valor);
			break;
		case 31:
			Registro.setValor_31(valor);
			break;
		}

	}

	private Session crearSesion() {
		try {
			return PersistenciaFactory.currentSession();
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
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.facade.ParteDiarioLogicFacade#
	 * registrarPartediario(pe.com.pacasmayo.sgcp.bean.NotificacionBean)
	 */
	public void registrarPartediario(NotificacionDiariaBean notificacionBean, UsuarioBean usuario) throws LogicaException {
		Session session = null;
		logger.debug("PDL 1... " + (new Date()));
		Partediario partediario = null;
		Transaction tx = null;
		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Calendar cal = Calendar.getInstance();
			cal.setTime(notificacionBean.getFechaNotificacionDiaria());

			int mes = cal.get(Calendar.MONTH) + 1;
			int anio = cal.get(Calendar.YEAR);

			Periodocontable periodoContable = PeriodoContableQuerier.getByMesYAno((short) mes, anio);

			partediario = ParteDiarioQuerier.getByPeriodoContableYLineaNegocio(mes, anio, notificacionBean.getLineaNegocio()
					.getCodigo());

			if (partediario == null) {
				partediario = crearNuevoParteDiario(notificacionBean, usuario, periodoContable);
				session.saveOrUpdate(partediario);
			}
			logger.debug("PDL 2... " + (new Date()));
			registroProducciondiariaPuestoTrabajo(notificacionBean, partediario, session);
			logger.debug("PDL 3... " + (new Date()));
			registroProduccionDiariaPorProducto(notificacionBean, partediario, session);
			logger.debug("PDL 4... " + (new Date()));
			tx.commit();
		} catch (ElementoNoEncontradoException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMessage(), e);
		} catch (SesionVencidaException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMessage(), e);
		} catch (EntornoEjecucionException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			throw new LogicaException(e.getMessage(), e);
		} catch (NonUniqueResultException e) {
			logger.error(e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (LogicaException e) {
			PersistenciaFactory.rollbackTransaction(tx);
			logger.error(e);
			mensajeError = e.getMensaje();
			throw new LogicaException(mensajeError, e);
		}

		finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Crea una nueva instancia del Objeto ParteDiario
	 * 
	 * @param notificacionBean notififcacion diaria
	 * @param usuario usuario que registra
	 * @param periodoContable periodo contable
	 * @throws ElementoNoEncontradoException si falla la busqueda del estado del
	 *             parte diario
	 */
	private Partediario crearNuevoParteDiario(NotificacionDiariaBean notificacionBean, UsuarioBean usuario,
			Periodocontable periodoContable) throws ElementoNoEncontradoException {

		Partediario parteDiario = new Partediario();
		parteDiario.setPeriodocontable(periodoContable);

		parteDiario.setLineanegocio(beanFactory.transformarLineaNegocioBean(notificacionBean.getLineaNegocio()));
		parteDiario.setUsuarioByFkCodigoUsuarioRegistra(beanFactory.transformarUsuarioBean(usuario));

		Estadopartediario estadopartediario = EstadoParteDiarioQuerier.getByNombre(ESTADO_PARTE_DIARIO_ABIERTO);
		parteDiario.setEstadopartediario(estadopartediario);

		return parteDiario;
	}

	/**
	 * Realizar el registro del parte diario, agrupando los datos por dia del
	 * mes indicado por la notificacion diaria
	 * 
	 * @param notificacionBean notificacion diraria
	 * @param partediario
	 * @param session
	 * @param session
	 * @throws LogicaException
	 */

	private void registroProduccionDiariaPorProducto(NotificacionDiariaBean notificacionBean, Partediario partediario,
			Session session) throws LogicaException {
		try {

			logger.debug("registroProduccionDiariaPorProducto 1 ... " + (new Date()));
			String codigoAlmacenPp = ManejadorPropiedades.obtenerPropiedadPorClave(ALMACEN_POR_DEFECTO_PP);
			Almacen almacenPp = Querier.getById(Almacen.class, new Long(codigoAlmacenPp));
			String codigoUbicacionPp = ManejadorPropiedades.obtenerPropiedadPorClave(UBICACION_POR_DEFECTO_PP);
			Ubicacion ubicacionPp = Querier.getById(Ubicacion.class, new Long(codigoUbicacionPp));

			String codigoAlmacenPt = ManejadorPropiedades.obtenerPropiedadPorClave(ALMACEN_POR_DEFECTO_PT);
			Almacen almacenPt = Querier.getById(Almacen.class, new Long(codigoAlmacenPt));
			String codigoUbicacionPt = ManejadorPropiedades.obtenerPropiedadPorClave(UBICACION_POR_DEFECTO_PT);
			Ubicacion ubicacionPt = Querier.getById(Ubicacion.class, new Long(codigoUbicacionPt));
			logger.debug("registroProduccionDiariaPorProducto 2 ... " + (new Date()));
			registrarParteDiarioPtYPp(notificacionBean, partediario, session, almacenPt, ubicacionPt, almacenPp, ubicacionPp);
			logger.debug("registroProduccionDiariaPorProducto 3 ... " + (new Date()));

			// ESTE Analizando
			registrarParteDiarioPtYPpNoProducidosEnElDia(notificacionBean, partediario, session, almacenPt, ubicacionPt,
					almacenPp, ubicacionPp);
			logger.debug("registroProduccionDiariaPorProducto 4 ... " + (new Date()));
			String codigoAlmacenMp = ManejadorPropiedades.obtenerPropiedadPorClave(ALMACEN_POR_DEFECTO_MP);
			String codigoUbicacionMp = ManejadorPropiedades.obtenerPropiedadPorClave(UBICACION_POR_DEFECTO_MP);
			Almacen almacenMP = Querier.getById(Almacen.class, new Long(codigoAlmacenMp));
			Ubicacion ubicacionMP = Querier.getById(Ubicacion.class, new Long(codigoUbicacionMp));
			logger.debug("registroProduccionDiariaPorProducto 5 ... " + (new Date()));
			registrarParteDiarioMp(notificacionBean, partediario, session, almacenMP, ubicacionMP);
			logger.debug("registroProduccionDiariaPorProducto 6 ... " + (new Date()));

			// ESTE
			registrarParteDiariMpNoProducidosEnElDia(notificacionBean, partediario, session, almacenMP, ubicacionMP);
			logger.debug("registroProduccionDiariaPorProducto 7 ... " + (new Date()));
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Registra el parte diario de los productos (materias primas) que no fueron
	 * producidos en el dia de la notificacion que se esta aprobando, esto con
	 * el obtivo de mantaner salods iniciales y consumos
	 * 
	 * @param notificacionBean NotificacionDiariaBean
	 * @param partediario Partediario
	 * @param session Session
	 * @param almacenMP Almacen
	 * @param ubicacionMP Ubicacion
	 * @throws LogicaException
	 * @throws ElementoNoEncontradoException
	 */
	private void registrarParteDiariMpNoProducidosEnElDia(NotificacionDiariaBean notificacionBean, Partediario partediario,
			Session session, Almacen almacenMP, Ubicacion ubicacionMP) throws LogicaException, ElementoNoEncontradoException {
		logger.debug("==============================registrarParteDiariMpNoProducidosEnElDia================================");
		List<Producto> productos = NotificacionProduccionQuerier.obtenerComponentesNoNotificados(notificacionBean.getCodigo(),
				session, notificacionBean.getLineaNegocio().getCodigo());

		Long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));
		Long codigoVariableHumedad = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(CODIGO_VARIABLE_CALIDAD_HUMEDAD));
		Productovariablecalidad productoVariableCalidad = null;
		Producciondiaria producciondiaria = null;
		for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {

			Producto producto = iterator.next();
			Long codigoTipoproducto = producto.getTipoproducto().getPkCodigoTipoproducto();

			boolean esProductoProceso = codigoTipoproducto.longValue() == codigoPP.longValue();
			boolean poseeCompEnHojaRuta = poseeCompEnHojaRuta(producto);

			if (esProductoProceso && poseeCompEnHojaRuta) {
				continue;
			}

			productoVariableCalidad = ProductoVariableCalidadQuerier.obtenerProductoVariableCalidad(
					producto.getPkCodigoProducto(), codigoVariableHumedad);

			producciondiaria = ProduccionDiariaQuerier
					.getByPartediarioYComponente(partediario.getPkCodigoPartediario(), producto);

			if (producciondiaria == null) {
				producciondiaria = new Producciondiaria(partediario, null, 0d, 0d, 0d, 0d, 0d);
				producciondiaria.setProducto(producto);
				session.save(producciondiaria);
			}

			Date fechaNotif = notificacionBean.getFechaNotificacionDiaria();

			double consumosTnHum = 0d;
			double consumoTnSecas = 0d;

			double saldoInicial = obtenerStockFinalDiaAnteriorPorComponente(producciondiaria, producto,
					notificacionBean.getFechaNotificacionDiaria());

			double ingresoTnSecas = 0d;
			double ingresoTnHum = 0d;
			double factorHum = productoVariableCalidad != null ? obtenerFactorHum(producto,
					productoVariableCalidad.getCodigoProcesoScc(), fechaNotif, productoVariableCalidad.getVariablecalidad()
							.getNombreVariablecalidad()) : 0d;

			// Lineanegocio lineanegocio =
			// beanFactory.transformarLineaNegocioBean(notificacionBean.getLineaNegocio());
			// Ingreso viene de los movimientos
			double ingresoTnSecasPorMovimientos = obtenerIngresoMp(producto, notificacionBean.getFechaNotificacionDiaria());

			if (producto.getTipoproducto().getPkCodigoTipoproducto().longValue() == codigoPP.longValue()) {
				// ingreso viene del registro de pp
				double ingresoTnHumPorIngresoPP = obtenerIngresoProductoProceso(producto, notificacionBean.getLineaNegocio()
						.getCodigo(), fechaNotif);

				if (factorHum == 0d) {
					ingresoTnSecas = ingresoTnHumPorIngresoPP + ingresoTnSecasPorMovimientos;
					ingresoTnHum = ingresoTnSecas;
				} else {
					double ingresoTmSecasPorIngresoPP = ingresoTnHumPorIngresoPP * (1 - (factorHum / 100d));
					double ingresoTnHumPorMovimientos = ingresoTnSecasPorMovimientos / (1 - (factorHum / 100d));

					ingresoTnSecas = ingresoTnSecasPorMovimientos + ingresoTmSecasPorIngresoPP;
					ingresoTnHum = ingresoTnHumPorMovimientos + ingresoTnHumPorIngresoPP;
				}

			} else {
				ingresoTnSecas = ingresoTnSecasPorMovimientos;
				if (factorHum == 0d) {
					ingresoTnHum = ingresoTnSecas;
				} else {
					ingresoTnHum = ingresoTnSecas / (1 - (factorHum / 100d));
				}
			}
			Date fechaKardex = obtenerFechaKardex(notificacionBean);

			double stockFinal = (ingresoTnSecas + saldoInicial) - consumoTnSecas;

			double ajusteLogisticoProducto = obtenerAjusteProductoLogistico(producciondiaria.getPartediario().getLineanegocio()
					.getPkCodigoLineanegocio(), producto.getPkCodigoProducto(), fechaNotif);
			stockFinal += ajusteLogisticoProducto;

			Tablakardex tablakardex = new Tablakardex(producciondiaria, almacenMP, ubicacionMP, fechaKardex, ingresoTnSecas,
					consumoTnSecas, saldoInicial, null, stockFinal, 0d);
			tablakardex.setIngresoHumedadTablakardex(ingresoTnHum);
			tablakardex.setConsumoHumedadTablakardex(consumosTnHum);
			tablakardex.setAjustelogisticoTablakardex(ajusteLogisticoProducto);

			session.save(tablakardex);

			if (productoVariableCalidad != null && factorHum != 0d) {
				Valorpromvariablecalidad valorpromvariablecalidad = new Valorpromvariablecalidad(tablakardex,
						productoVariableCalidad, factorHum);
				session.save(valorpromvariablecalidad);
			}

			actualizarTotalesProducciondiariaPorComponente(producciondiaria, tablakardex,
					notificacionBean.getFechaNotificacionDiaria(), producto, session);

		}
	}

	/**
	 * Registra el parte diario de los productos (terminados y en proceso) que
	 * no fueron producidos en el dia de la notificacion que se esta aprobando,
	 * esto con el obtivo de mantaner salods iniciales y consumos
	 * 
	 * @param notificacionBean NotificacionDiariaBean
	 * @param partediario Partediario
	 * @param session Session
	 * @param almacenPt Almacen
	 * @param ubicacionPt Ubicacion
	 * @param almacenPp Almacen
	 * @param ubicacionPp Ubicacion
	 * @throws LogicaException
	 */
	private void registrarParteDiarioPtYPpNoProducidosEnElDia(NotificacionDiariaBean notificacionBean, Partediario partediario,
			Session session, Almacen almacenPt, Ubicacion ubicacionPt, Almacen almacenPp, Ubicacion ubicacionPp)
			throws LogicaException {
		try {
			logger.debug("==========================================registrarParteDiarioPtYPpNoProducidosEnElDia=======================================================");
			// Lineanegocio lineanegocio =
			// beanFactory.transformarLineaNegocioBean(notificacionBean.getLineaNegocio());
			Long codigoLineaNegocio = notificacionBean.getLineaNegocio().getCodigo();
			Date fechaNotf;
			fechaNotf = obtenerFechaKardex(notificacionBean);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaNotf);
			int mes = calendar.get(Calendar.MONTH) + 1;
			int anio = calendar.get(Calendar.YEAR);
			logger.debug("registrarParteDiarioPtYPpNoProducidosEnElDia 1 ... " + (new Date()));

			Long codEstadoLib = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(ESTADO_ORDEN_PRODUCCION_LIBERADA));

			List<Ordenproduccion> ordenes = OrdenProduccionQuerier.obtenerPorLineaNegocioMesYEstadoNoNotif(mes, anio,
					codigoLineaNegocio, codEstadoLib, notificacionBean.getCodigo());
			System.out.println("registrarParteDiarioPtYPpNoProducidosEnElDia 2 ... " + (new Date()));
			Almacen almacen = null;
			Ubicacion ubicacion = null;

			for (Ordenproduccion ordenproduccion : ordenes) {
				logger.debug("========================INCIO PROCESAMIENTO ... " + (new Date()));
				Producto producto = ordenproduccion.getProduccion().getProducto();
				logger.debug(" 1 ... " + (new Date()));
				Producciondiaria producciondiaria = ProduccionDiariaQuerier.getByPartediarioYOrdenProduccion(
						partediario.getPkCodigoPartediario(), ordenproduccion.getPkCodigoOrdenproduccion());
				logger.debug(" 2 ... " + (new Date()));
				if (producciondiaria == null) {
					producciondiaria = new Producciondiaria(partediario, ordenproduccion, 0d, 0d, 0d, 0d, 0d);
					session.save(producciondiaria);
				}

				double consumoTnSecas = 0d;

				String tipoproducto = producto.getTipoproducto().getNombreTipoproducto().toLowerCase();
				logger.debug(" 3 ... " + (new Date()));
				if (tipoproducto.equals(PRODUCTO_TERMINADO)) {
					almacen = almacenPt;
					ubicacion = ubicacionPt;
					consumoTnSecas = obtenerConsumosPt(notificacionBean, producto);
				} else {
					almacen = almacenPp;
					ubicacion = ubicacionPp;
					consumoTnSecas = obtenerConsumosPP(notificacionBean, producto);

					if (producto.getPkCodigoProducto().longValue() == PK_PRODUCTO_CAL_GRANULADA) {
						double consumoPorDespachos = obtenerConsumosPt(notificacionBean, producto);
						consumoTnSecas += consumoPorDespachos;
					}

				}
				logger.debug(" 4 ... " + (new Date()));
				Double consumosTnHum = consumoTnSecas;

				double saldoInicial = obtenerStockFinalDiaAnteriorPorOrden(producciondiaria, ordenproduccion, fechaNotf);
				logger.debug(" 5 ... " + (new Date()));
				// El ingreso se obtiene de los componentes que se consumen para
				// hacer el producto
				double ingresoTnSecas = 0d;
				Double ingresoTnHum = 0d;
				double stockFinal = (ingresoTnSecas + saldoInicial) - consumoTnSecas;
				double ajusteLogisticoProducto = obtenerAjusteProductoLogistico(producciondiaria.getPartediario()
						.getLineanegocio().getPkCodigoLineanegocio(), producto.getPkCodigoProducto(), fechaNotf);
				stockFinal += ajusteLogisticoProducto;
				Tablakardex tablakardex = new Tablakardex(producciondiaria, almacen, ubicacion, fechaNotf, ingresoTnSecas,
						consumoTnSecas, saldoInicial, null, stockFinal, 0d);
				tablakardex.setIngresoHumedadTablakardex(ingresoTnHum);
				tablakardex.setConsumoHumedadTablakardex(consumosTnHum);
				tablakardex.setAjustelogisticoTablakardex(ajusteLogisticoProducto);
				Produccion produccion = ordenproduccion.getProduccion();
				logger.debug(" 5 ... " + (new Date()));
				Map<Long, Double> mapaSilosAnteriores = obtenerStockFinalDiaAnteriorSilos(producciondiaria, ordenproduccion,
						fechaNotf);

				session.save(tablakardex);
				logger.debug(" 6 ... " + (new Date()));
				crearConsumosComponentesPoducto(notificacionBean, producto, tablakardex, session, produccion.getProceso());
				logger.debug(" 7 ... " + (new Date()));
				crearStockSilos(notificacionBean, producto, tablakardex, fechaNotf, mapaSilosAnteriores, session);
				logger.debug(" 8 ... " + (new Date()));
				tablakardex.setVariacionTablakardex(0d);
				double stockFisico = obtenerStockFisicoMedicion(partediario, fechaNotf, producto);
				tablakardex.setStockFisicoTablakardex(stockFisico);
				logger.debug(" 9 ... " + (new Date()));
				if (stockFisico != 0d) {
					double variacion = (stockFisico - stockFinal) * 100 / stockFisico;
					tablakardex.setVariacionTablakardex(variacion);
					session.update(tablakardex);
				}
				logger.debug(" 10 ... " + (new Date()));
				actualizarTotalesProducciondiariaPorOrden(producciondiaria, tablakardex,
						notificacionBean.getFechaNotificacionDiaria(), ordenproduccion, session);
				logger.debug("============================FIN PROCESAMIENTO ... " + (new Date()));
			}
			System.out
					.println("==========================================registrarParteDiarioPtYPpNoProducidosEnElDia=======================================================");
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}

	}

	/**
	 * Obtiene el sotck fisico de las mediciones, se consulta el dia siguiente a
	 * la fecha de la notificacion
	 * 
	 * @param partediario
	 * @param fechaNotf
	 * @param producto
	 * @return
	 */
	private double obtenerStockFisicoMedicion(Partediario partediario, Date fechaNotf, Producto producto) {
		Long codigoLineanegocio = partediario.getLineanegocio().getPkCodigoLineanegocio();
		Long codigoProducto = producto.getPkCodigoProducto();

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaNotf);
		cal.add(Calendar.DAY_OF_MONTH, 1);

		Integer mesAsInt = cal.get(Calendar.MONTH) + 1;
		Date fechaDiaSig = cal.getTime();
		int anio = cal.get(Calendar.YEAR);

		double stockFisico = MedicionQuerier.obtenerFisico(codigoLineanegocio, codigoProducto, anio, mesAsInt.shortValue(),
				fechaDiaSig);
		return stockFisico;
	}

	private void registrarParteDiarioMp(NotificacionDiariaBean notificacionBean, Partediario partediario, Session session,
			Almacen almacenMP, Ubicacion ubicacionMP) throws ElementoNoEncontradoException, LogicaException {

		List<Producto> productos = NotificacionProduccionQuerier.obtenerComponentesSegunNotificacionDiaria(
				notificacionBean.getCodigo(), session);

		Long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));
		for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
			Producto producto = iterator.next();
			Long codigoTipoproducto = producto.getTipoproducto().getPkCodigoTipoproducto();

			boolean esProductoProceso = codigoTipoproducto.longValue() == codigoPP.longValue();
			boolean poseeCompEnHojaRuta = poseeCompEnHojaRuta(producto);

			if (esProductoProceso && poseeCompEnHojaRuta) {
				continue;
			}

			Producciondiaria producciondiaria = ProduccionDiariaQuerier.getByPartediarioYComponente(
					partediario.getPkCodigoPartediario(), producto);

			if (producciondiaria == null) {
				producciondiaria = new Producciondiaria(partediario, null, 0d, 0d, 0d, 0d, 0d);
				producciondiaria.setProducto(producto);
				session.save(producciondiaria);
			}

			Tablakardex tablaKardex = new Tablakardex();
			Proceso proceso = HojaRutaQuerier.getProcesoSegunHojaRuta(producto.getPkCodigoProducto(), notificacionBean
					.getLineaNegocio().getCodigo());
			if (proceso == null) {
				String nombreProducto = producto.getNombreProducto();
				mensajeError = MessageFormat.format(ERROR_PRODUCTO_HOJA_RUTA, new Object[] { nombreProducto });
				logger.error(mensajeError);
				continue;
				//throw new LogicaException(mensajeError);
			}

			tablaKardex = registroParteDiarioMP(notificacionBean, producto, proceso, producciondiaria, almacenMP, ubicacionMP,
					session);

			actualizarTotalesProducciondiariaPorComponente(producciondiaria, tablaKardex,
					notificacionBean.getFechaNotificacionDiaria(), producto, session);
		}
	}

	private boolean poseeCompEnHojaRuta(Producto producto) throws LogicaException {

		try {
			return HojaRutaComponenteQuerier.poseeCompEnHojaRuta(producto.getPkCodigoProducto());
		} catch (SesionVencidaException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		}

	}

	/**
	 * Realizar el registro de parte diaro de productos en proceso y productos
	 * terminados
	 * 
	 * @param notificacionBean
	 * @param partediario
	 * @param session
	 * @param ubicacionPp
	 * @param almacenPp
	 * @param ubicacionPt
	 * @param almacenPt
	 * @param almacenMP
	 * @throws ElementoNoEncontradoException
	 * @throws LogicaException
	 */

	private void registrarParteDiarioPtYPp(NotificacionDiariaBean notificacionBean, Partediario partediario, Session session,
			Almacen almacenPt, Ubicacion ubicacionPt, Almacen almacenPp, Ubicacion ubicacionPp)
			throws ElementoNoEncontradoException, LogicaException {
		List<Ordenproduccion> ordenes = NotificacionProduccionQuerier.obtenerOrdenesProduccionSegunNotifDiaria(notificacionBean
				.getCodigo());

		for (Iterator<Ordenproduccion> iterator = ordenes.iterator(); iterator.hasNext();) {
			Ordenproduccion ordenproduccion = iterator.next();
			Producto producto = ordenproduccion.getProduccion().getProducto();

			Producciondiaria producciondiaria = ProduccionDiariaQuerier.getByPartediarioYOrdenProduccion(
					partediario.getPkCodigoPartediario(), ordenproduccion.getPkCodigoOrdenproduccion());

			if (producciondiaria == null) {
				producciondiaria = new Producciondiaria(partediario, ordenproduccion, 0d, 0d, 0d, 0d, 0d);
				session.save(producciondiaria);
			}

			String tipoproducto = producto.getTipoproducto().getNombreTipoproducto().toLowerCase();

			Tablakardex tablaKardex = new Tablakardex();
			if (tipoproducto.equals(PRODUCTO_TERMINADO)) {
				tablaKardex = registroParteDiarioPT(notificacionBean, ordenproduccion, producto, producciondiaria, almacenPt,
						ubicacionPt, session);
			} else if (tipoproducto.equals(PRODUCTO_EN_PROCESO)) {
				tablaKardex = registroParteDiarioPP(notificacionBean, ordenproduccion, producto, producciondiaria, almacenPp,
						ubicacionPt, session);
			}

			actualizarTotalesProducciondiariaPorOrden(producciondiaria, tablaKardex,
					notificacionBean.getFechaNotificacionDiaria(), ordenproduccion, session);
		}
	}

	/**
	 * Obtiene el fator de humedad de la bd de SCC
	 * 
	 * @param descVarCalidad
	 * @param ordenproduccion Ordenproduccion
	 * @param notificacionBean NotificacionDiariaBean
	 * @return double
	 * @throws LogicaException si la consulta a scc falla
	 */
	// TODO: quitar este metodo de aqui , ponerlo en algun utilitario
	public static double obtenerFactorHum(Producto producto, Long codigoProcesoScc, Date fecha, String descVarCalidad)
			throws LogicaException {
		Long codigoProductoScc = producto.getCodigoSccProducto();

		if (codigoProductoScc == null) {
			// no tien codigo producto scc == no tiene humedad
			return 0;
		}

		try {
			Double factorHumedadSac = operacionesFacade.obtenerVariableCalidadMpSac(fecha, codigoProcesoScc, codigoProductoScc,
					descVarCalidad);
			return factorHumedadSac;
		} catch (IntegradorExcepcion e) {
			throw new LogicaException(e.getMensaje(), e);
		}

	}

	private void actualizarTotalesProducciondiariaPorOrden(Producciondiaria producciondiaria, Tablakardex tablaKardex,
			Date fechaNotif, Ordenproduccion ordenproduccion, Session session) {
		actualizarTotalesProducciondiaria(producciondiaria, tablaKardex, fechaNotif, ordenproduccion, null, session);
	}

	private void actualizarTotalesProducciondiariaPorComponente(Producciondiaria producciondiaria, Tablakardex tablaKardex,
			Date fechaNotif, Producto producto, Session session) {
		actualizarTotalesProducciondiaria(producciondiaria, tablaKardex, fechaNotif, null, producto, session);
	}

	/**
	 * Actualiza los totales que se almacenan en la tabla produccion diaria
	 * conforme el registro de tabla karde a ingresar
	 * 
	 * @param producciondiaria produccion diaria
	 * @param tablaKardex tabla kardex
	 * @param ordenproduccion
	 * @param session
	 * @throws LogicaException
	 */

	private void actualizarTotalesProducciondiaria(Producciondiaria producciondiaria, Tablakardex tablaKardex, Date fechaNotif,
			Ordenproduccion ordenproduccion, Producto producto, Session session) {

		Double saldoInicial = producciondiaria.getSaldoInicialProducciondiaria() == null ? 0d : producciondiaria
				.getSaldoInicialProducciondiaria();

		Double ingresoProduccionProduc = producciondiaria.getIngresoProduccionProducciondi() == null ? 0d : producciondiaria
				.getIngresoProduccionProducciondi();
		double ingreso = ingresoProduccionProduc + tablaKardex.getIngresoTablakardex();

		Double consumoProduc = producciondiaria.getConsumoProducciondiaria() == null ? 0d : producciondiaria
				.getConsumoProducciondiaria();
		double consumo = consumoProduc + tablaKardex.getConsumoTablakardex();

		Double ajusteLogisticoProducto = producciondiaria.getAjustelogisticoProducciondiaria() == null ? 0d : producciondiaria
				.getAjustelogisticoProducciondiaria();

		double ajusteLogistico = ajusteLogisticoProducto + tablaKardex.getAjustelogisticoTablakardex();

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaNotif);

		int primerdiaMes = 1;

		Double saldoinicial = 0d;
		if (cal.get(Calendar.DAY_OF_MONTH) == primerdiaMes) {
			saldoinicial = tablaKardex.getSaldoInicialTablakardex();
			producciondiaria.setSaldoInicialProducciondiaria(saldoinicial);
		}

		double saldoFinal = (saldoInicial + ingreso) - consumo;
		saldoFinal += ajusteLogistico;

		producciondiaria.setSaldoFinalProducciondiaria(saldoFinal);
		producciondiaria.setIngresoProduccionProducciondi(ingreso);
		producciondiaria.setConsumoProducciondiaria(consumo);
		producciondiaria.setAjustelogisticoProducciondiaria(ajusteLogistico);

		session.update(producciondiaria);
	}

	/**
	 * Realiza el registro del parte diario para las materias primas
	 * 
	 * @param notificacionBean notificacion diaria
	 * @param ordenproduccion orde de produccion
	 * @param producto producto
	 * @param producciondiaria produccion diaria
	 * @param ubicacionPlanta
	 * @param almacenMP
	 * @param session
	 * @param factorHum
	 * @return Objeto Tablakardex que posee información correspondiente al
	 *         ingreso, producción, consumo, componentes, las TM Húmedas, la
	 *         conversión a TM secas y las variaciones de stock en libros vs
	 *         físico.
	 * @throws LogicaException
	 */
	private Tablakardex registroParteDiarioMP(NotificacionDiariaBean notificacionBean, Producto producto, Proceso proceso,
			Producciondiaria producciondiaria, Almacen almacenMP, Ubicacion ubicacionPlanta, Session session)
			throws LogicaException {

		Long codigoVariableHumedad = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(CODIGO_VARIABLE_CALIDAD_HUMEDAD));

		Productovariablecalidad productoVariableCalidad = ProductoVariableCalidadQuerier.obtenerProductoVariableCalidad(
				producto.getPkCodigoProducto(), codigoVariableHumedad);

		Date fechaNotif = notificacionBean.getFechaNotificacionDiaria();

		double consumosTnHum = obtenerConsumosMp(notificacionBean, producto);
		double consumoTnSecas = obtenerConsumoComponenteEnTmSecas(producto, notificacionBean);

		double factorHum = 0d;
		if (productoVariableCalidad != null) {
			factorHum = obtenerFactorHum(producto, productoVariableCalidad.getCodigoProcesoScc(), fechaNotif,
					productoVariableCalidad.getVariablecalidad().getNombreVariablecalidad());
		}

		if (factorHum == 0 && productoVariableCalidad != null) {
			// Obtener Humedad almacenada dias anteriores

			Valorpromvariablecalidad promvarcalidad = ValorPromVariableCalidadQuerier.obtenerUltimoValorPromCalidadIngreso(
					productoVariableCalidad.getPkCodigoProductovariablecalid(), fechaNotif);
			if (promvarcalidad != null) {

				factorHum = promvarcalidad.getValorValorpromvariblecalidad();
			}
		}

		double saldoInicial = obtenerStockFinalDiaAnteriorPorComponente(producciondiaria, producto,
				notificacionBean.getFechaNotificacionDiaria());

		Long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));

		double ingresoTnSecas = 0d;
		double ingresoTnHum = 0d;

		// Ingreso viene de los movimientos
		double ingresoTnSecasPorMovimientos = obtenerIngresoMp(producto, notificacionBean.getFechaNotificacionDiaria());

		if (producto.getTipoproducto().getPkCodigoTipoproducto().longValue() == codigoPP.longValue()) {
			// ingreso viene del registro de pp
			double ingresoTnHumPorIngresoPP = obtenerIngresoProductoProceso(producto, notificacionBean.getLineaNegocio()
					.getCodigo(), fechaNotif);

			if (factorHum == 0d) {
				ingresoTnSecas = ingresoTnHumPorIngresoPP + ingresoTnSecasPorMovimientos;
				ingresoTnHum = ingresoTnSecas;
			} else {
				double ingresoTmSecasPorIngresoPP = ingresoTnHumPorIngresoPP * (1 - (factorHum / 100d));
				double ingresoTnHumPorMovimientos = ingresoTnSecasPorMovimientos / (1 - (factorHum / 100d));

				ingresoTnSecas = ingresoTnSecasPorMovimientos + ingresoTmSecasPorIngresoPP;
				ingresoTnHum = ingresoTnHumPorMovimientos + ingresoTnHumPorIngresoPP;
			}

		} else {
			ingresoTnSecas = ingresoTnSecasPorMovimientos;
			if (factorHum == 0d) {
				ingresoTnHum = ingresoTnSecas;
			} else {
				ingresoTnHum = ingresoTnSecas / (1 - (factorHum / 100d));
			}
		}

		double stockFinal = (ingresoTnSecas + saldoInicial) - consumoTnSecas;

		double ajusteLogisticoProducto = obtenerAjusteProductoLogistico(producciondiaria.getPartediario().getLineanegocio()
				.getPkCodigoLineanegocio(), producto.getPkCodigoProducto(), fechaNotif);
		stockFinal += ajusteLogisticoProducto;

		Date fechaKardex = obtenerFechaKardex(notificacionBean);

		Tablakardex tablakardex = new Tablakardex(producciondiaria, almacenMP, ubicacionPlanta, fechaKardex, ingresoTnSecas,
				consumoTnSecas, saldoInicial, null, stockFinal, 0d);
		tablakardex.setIngresoHumedadTablakardex(ingresoTnHum);
		tablakardex.setConsumoHumedadTablakardex(consumosTnHum);
		tablakardex.setAjustelogisticoTablakardex(ajusteLogisticoProducto);
		session.save(tablakardex);

		if (productoVariableCalidad != null && factorHum != 0d) {
			Valorpromvariablecalidad valorpromvariablecalidad = new Valorpromvariablecalidad(tablakardex,
					productoVariableCalidad, factorHum);
			session.save(valorpromvariablecalidad);
		}

		return tablakardex;
	}

	/**
	 * Obtiene el ingreso de un producto en proceso que no posee componentes en
	 * su hoja de ruta(similar a una materia prima)
	 * 
	 * @param producto Producto
	 * @param codigoLineaNegocio Long
	 * @param fechaNotif Date
	 * @return double
	 * @throws LogicaException si la consulta falla
	 */
	private double obtenerIngresoProductoProceso(Producto producto, Long codigoLineaNegocio, Date fechaNotif)
			throws LogicaException {
		try {
			return IngresoProductoProcesoQuerier.obtenerIngresoProductoPorFechaYLineaNegocio(producto, codigoLineaNegocio,
					fechaNotif);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e);
		}
	}

	/**
	 * Obtiene el consumo seco, de una materia prima
	 * 
	 * @param producto Producto
	 * @param fechaNotif Date
	 * @return double
	 * @throws LogicaException
	 */
	private double obtenerConsumoComponenteEnTmSecas(Producto producto, NotificacionDiariaBean notificacionBean)
			throws LogicaException {
		try {
			Date fecha = notificacionBean.getFechaNotificacionDiaria();
			return ConsumoPuestoTrabajoQuerier.obtenerConsumoComponentesPorFecha(fecha, producto.getPkCodigoProducto());
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Obtiene la catidad de movimiento de tipo ingreso para la fecha pasada
	 * como parametro
	 * 
	 * @param lineanegocio linea de negocio
	 * @param producto producto
	 * @param fechaNotf fecha del mov
	 * @param tipoMovIngreso movimiento de tipo ingreso
	 * @return double
	 * @throws LogicaException
	 */
	private double obtenerIngresoMp(Producto producto, Date fechaNotf) throws LogicaException {
		try {
			Long clasifMovIngreso = new Long(1);
			String[] codigos = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_MOVIMIENTOS_MOVIMIENTO_LOGISTICO).split(",");

			return MovimientoQuerier.obtenerCantidadSegunLineaNegProductoFechaYTipo(producto, fechaNotf, clasifMovIngreso,
					codigos);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Realiza el registro del parte diario para los productos en proceso
	 * 
	 * @param notificacionBean notificacion diaria
	 * @param ordenproduccion orde de produccion
	 * @param producto producto
	 * @param producciondiaria produccion diaria
	 * @param ubicacionPlanta
	 * @param almacenPpYPt
	 * @param session
	 * @param factorHum
	 * @return Tablakardex
	 * @throws LogicaException
	 */
	private Tablakardex registroParteDiarioPP(NotificacionDiariaBean notificacionBean, Ordenproduccion ordenproduccion,
			Producto producto, Producciondiaria producciondiaria, Almacen almacenPpYPt, Ubicacion ubicacionPlanta, Session session)
			throws LogicaException {

		// El consumo se obtiene consultando cuando el producto se comporta como
		// componente de otro producto
		Double consumosTnHum = 0d;
		double consumoTnSecas = obtenerConsumosPP(notificacionBean, producto);

		if (producto.getPkCodigoProducto().longValue() == PK_PRODUCTO_CAL_GRANULADA) {
			double consumoPorDespachos = obtenerConsumosPt(notificacionBean, producto);
			consumoTnSecas += consumoPorDespachos;
		}
		double saldoInicial = obtenerStockFinalDiaAnteriorPorOrden(producciondiaria, ordenproduccion,
				notificacionBean.getFechaNotificacionDiaria());

		Date fechaNotf = obtenerFechaKardex(notificacionBean);

		// El ingreso se obtiene de los componentes que se consumen para hacer
		// el producto
		double ingresoTnSecas = obtenerIngresoPpYPt(ordenproduccion, fechaNotf);
		Double ingresoTnHum = ingresoTnSecas;
		double stockFinal = (ingresoTnSecas + saldoInicial) - consumoTnSecas;

		double ajusteLogisticoProducto = obtenerAjusteProductoLogistico(producciondiaria.getPartediario().getLineanegocio()
				.getPkCodigoLineanegocio(), producto.getPkCodigoProducto(), fechaNotf);
		stockFinal += ajusteLogisticoProducto;

		Tablakardex tablakardex = new Tablakardex(producciondiaria, almacenPpYPt, ubicacionPlanta, fechaNotf, ingresoTnSecas,
				consumoTnSecas, saldoInicial, null, stockFinal, 0d);
		tablakardex.setIngresoHumedadTablakardex(ingresoTnHum);
		tablakardex.setConsumoHumedadTablakardex(consumosTnHum);
		tablakardex.setAjustelogisticoTablakardex(ajusteLogisticoProducto);

		Partediario partediario = producciondiaria.getPartediario();
		double stockFisico = obtenerStockFisicoMedicion(partediario, fechaNotf, producto);
		tablakardex.setStockFisicoTablakardex(stockFisico);
		if (stockFisico != 0d) {
			double variacion = (stockFisico - stockFinal) * 100 / stockFisico;
			tablakardex.setVariacionTablakardex(variacion);
		}
		session.saveOrUpdate(tablakardex);

		Proceso proceso = ordenproduccion.getProduccion().getProceso();
		crearConsumosComponentesPoducto(notificacionBean, producto, tablakardex, session, proceso);

		return tablakardex;
	}

	/**
	 * Obtien el consumo de un producto en proceso(se toma de los consumos por
	 * puesto de trabajo ya que estos ya estan afectados por las variables de
	 * calidad)
	 * 
	 * @param notificacionBean NotificacionDiariaBean
	 * @param producto Producto
	 * @return double
	 * @throws LogicaException
	 */
	private double obtenerConsumosPP(NotificacionDiariaBean notificacionBean, Producto producto) throws LogicaException {
		try {
			return ConsumoPuestoTrabajoQuerier.obtenerConsumoComponentesPorFecha(notificacionBean.getFechaNotificacionDiaria(),
					producto.getPkCodigoProducto());
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Obtiene el ingreso de componentes consumidos para realizar el producto
	 * pasado como parametro
	 * 
	 * @param NotificacionDiariaBean notificacaion diaria
	 * @param Producto producto
	 * @return double ingreso de componetes consumidos
	 * @throws LogicaException
	 */
	private double obtenerIngresoPpYPt(Ordenproduccion orden, Date fechaNotif) throws LogicaException {

		try {
			return ProductoGeneradoQuerier.obtenerCantidadProductoPorFechaYOrdenProd(orden, fechaNotif);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	private double obtenerStockFinalDiaAnteriorPorOrden(Producciondiaria producciondiaria, Ordenproduccion ordenproduccion,
			Date fechaNotf) throws LogicaException {
		return obtenerStockFinalDiaAnterior(producciondiaria, ordenproduccion, null, fechaNotf);
	}

	private double obtenerStockFinalDiaAnteriorPorComponente(Producciondiaria producciondiaria, Producto producto, Date fechaNotf)
			throws LogicaException {
		return obtenerStockFinalDiaAnterior(producciondiaria, null, producto, fechaNotf);
	}

	/**
	 * Obtiene el stock final del dia anterior para determinado producto o
	 * componente, esto para ser usado como saldo inicial del dia actual cuan se
	 * quiere registrar el parte diario, hay que tomar en cuenta si es el primer
	 * dia del mes, ya que en ese caso hay que tomar el valor del ultimo dia del
	 * mes anterior.
	 * 
	 * @param producciondiaria produccion diaria
	 * @param ordenproduccion orden de produccion
	 * @param fechaNotf fecha de la notificacion
	 * @return valor del stock final del dia anterior para determinado producto
	 *         o componente
	 * @throws LogicaException
	 */
	private double obtenerStockFinalDiaAnterior(Producciondiaria producciondiaria, Ordenproduccion ordenproduccion,
			Producto producto, Date fechaNotf) throws LogicaException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaNotf);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int anio = cal.get(Calendar.YEAR);
		Integer mes = cal.get(Calendar.MONTH) + 1;

		Partediario partediarioActual = producciondiaria.getPartediario();
		Long codigoLinea = partediarioActual.getLineanegocio().getPkCodigoLineanegocio();
		double finalDiaAnterior = 0.0d;
		try {
			Producciondiaria prodDiaria = null;

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(fechaNotf);
			int primerdiaMes = 1;

			if (cal2.get(Calendar.DAY_OF_MONTH) == primerdiaMes) {

				if (ordenproduccion != null) {
					finalDiaAnterior = obtenerSaldoFinalMesAnterior(ordenproduccion.getProduccion().getProducto(),
							mes.shortValue(), anio, codigoLinea);
				} else {
					finalDiaAnterior = obtenerSaldoFinalMesAnterior(producto, mes.shortValue(), anio, codigoLinea);
				}

			} else {

				if (ordenproduccion != null) {
					prodDiaria = ProduccionDiariaQuerier.getByPartediarioYProductoOrden(codigoLinea, anio, mes.shortValue(),
							ordenproduccion);
				} else {
					prodDiaria = ProduccionDiariaQuerier.getByPartediarioYComponenteAnt(codigoLinea, anio, mes.shortValue(),
							producto);
				}

				if (prodDiaria == null) {
					return 0d;
				}
				finalDiaAnterior = TablaKardexQuerier.obtenerStockFinalDiaAnterior(prodDiaria);
			}
			return finalDiaAnterior;

		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}

	}

	public static void main(String[] args) {
		ParteDiarioLogic pt = new ParteDiarioLogic();
		Producto producto;
		try {
			producto = ProductoQuerier.getById(181L);
			pt.obtenerSaldoFinalMesAnterior(producto, (short) 1, 2016, 1L);
		} catch (ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private double obtenerSaldoFinalMesAnterior(Producto producto, short mes, int anio, Long codigoLinea) {
		Double resultado = 0.0d;
		try {
			List<Balanceproducto> balances = BalanceProductoQuerier.obtenerBalanceProducto_(producto.getPkCodigoProducto(),
					codigoLinea, mes, anio, null);

			if (balances != null && balances.size() > 0) {

				double saldoInicial = 0d;
				double ingreso = 0d;
				double consumo = 0d;
				double ajuste = PuestoTrabajoProduccionQuerier.obtenerAjustePTrabajoProducnPorIdAjusteProducto(
						producto.getPkCodigoProducto(), codigoLinea, mes, anio);

				double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponenteValidLineNego(
						producto.getPkCodigoProducto(), codigoLinea, mes, anio);
				double consumoPorAjusteProducto = MovimientoAjusteQuerier
						.obtenerAjustesPorConsumoDeProductoValidandoLineaNegocio(producto.getPkCodigoProducto(), codigoLinea,
								mes, anio);

				for (int i = 0; i < balances.size(); i++) {

					String nombreConcepto = balances.get(i).getConcepto().getNombreConcepto();

					if (nombreConcepto.equals(CONCEPTO_SALDO_INICIAL)) {
						saldoInicial += balances.get(i).getMontoBalanceproducto();
					}

					if (nombreConcepto.equals(CONCEPTO_PRODUCCION)) {
						ingreso += balances.get(i).getMontoBalanceproducto();
					}
					if (nombreConcepto.equals(CONCEPTO_CONSUMO)) {
						consumo += balances.get(i).getMontoBalanceproducto();
					}
				}

				resultado = ((ingreso + ajuste) + saldoInicial) - (consumo + consumoPorAjuste + consumoPorAjusteProducto);

				ProductoBean productoBean = new ProductoBeanImpl();
				productoBean.setCodigo(producto.getPkCodigoProducto());
				Double movimientoLogiscto = movimientoLogicFacade.obtenerMovimentoLogisticoProductoDAO(codigoLinea, productoBean,
						anio, mes);

				if (movimientoLogiscto == null) {
					movimientoLogiscto = 0d;
				}
				resultado += movimientoLogiscto;
			} else {
				String codigoClinkerI = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODCUTO_CLK_I);
				boolean esClinkerI = codigoClinkerI.equals(producto.getPkCodigoProducto().toString());
				double consumoPorAjuste = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeComponente_(
						producto.getPkCodigoProducto(), codigoLinea, mes, anio);
				double consumoPorAjusteProducto = MovimientoAjusteQuerier.obtenerAjustesPorConsumoDeProducto_(
						producto.getPkCodigoProducto(), codigoLinea, mes, anio);
				Double[] arrayStocks;
				arrayStocks = ajusteProduccionFacade.obtenerStocksMensualComponenteDAO(producto, codigoLinea, mes, anio,
						esClinkerI);

				if (arrayStocks != null) {
					double ingreso = arrayStocks[1];
					double saldoInicial = arrayStocks[0];
					double consumo = arrayStocks[2] + consumoPorAjuste + consumoPorAjusteProducto;
					double ajuste = arrayStocks[3];
					resultado = ((saldoInicial + ingreso) - consumo);
					resultado += ajuste;
				}
			}

			ParametroSistemaBean parametro = parametroSistemaLogic
					.obtenerParametroSistemaDAO(ConstantesParametro.CANTIDAD_DECIMALES_SGCP);
			int cantidadDecimales = 0;
			if (parametro != null) {
				cantidadDecimales = Integer.parseInt(parametro.getValor());
			} else {
				cantidadDecimales = ConstantesParametro.CANTIDAD_DECIMALES_SGCP_DEFAULT;
			}
			resultado = NumberUtil.redondear(resultado, cantidadDecimales);
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return resultado;
	}

	/**
	 * Obtiene el stock final del dia anterior para determinado producto o
	 * componente, esto para ser usado como saldo inicial del dia actual cuan se
	 * quiere registrar el parte diario, hay que tomar en cuenta si es el primer
	 * dia del mes, ya que en ese caso hay que tomar el valor del ultimo dia del
	 * mes anterior.
	 * 
	 * @param producciondiaria produccion diaria
	 * @param ordenproduccion orden de produccion
	 * @param fechaNotf fecha de la notificacion
	 * @return valor del stock final del dia anterior para determinado producto
	 *         o componente
	 * @throws LogicaException
	 */
	private Map<Long, Double> obtenerStockFinalDiaAnteriorSilos(Producciondiaria producciondiaria,
			Ordenproduccion ordenproduccion, Date fechaNotf) throws LogicaException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaNotf);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int anio = cal.get(Calendar.YEAR);
		Integer mes = cal.get(Calendar.MONTH) + 1;

		Partediario partediarioActual = producciondiaria.getPartediario();
		Long codigoLinea = partediarioActual.getLineanegocio().getPkCodigoLineanegocio();

		try {
			Producciondiaria prodDiaria = null;
			prodDiaria = ProduccionDiariaQuerier.getByPartediarioYProductoOrden(codigoLinea, anio, mes.shortValue(),
					ordenproduccion);

			if (prodDiaria == null) {
				return null;
			}

			return TablaKardexQuerier.obtenerListaStockSilosDiaAnterior(prodDiaria);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}

	}

	/**
	 * Obtiene de las notificaciones los consumos de la materia prima para un
	 * dia determinado
	 * 
	 * @param notificacionBean notificacion diaria
	 * @param ordenproduccion orden de produccion
	 * @param componente producto (MP)
	 * @return suma de las cantiades consumidas de la MP para todos los puestos
	 *         de trabajo
	 * @throws LogicaException
	 */
	private double obtenerConsumosMp(NotificacionDiariaBean notificacionBean, Producto producto) throws LogicaException {
		try {
			return ComponenteNotificacionQuerier.getConsumoProductoComponente(notificacionBean, producto);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	private void crearConsumosComponentesPoducto(NotificacionDiariaBean notificacionBean, Producto producto,
			Tablakardex tablakardex, Session session, Proceso proceso) throws LogicaException {
		try {
			Date fechaNotif = notificacionBean.getFechaNotificacionDiaria();
			Long pkLineaNegocio = notificacionBean.getLineaNegocio().getCodigo();
			List<Consumopuestotrabajo> consumosPuestotrabajo = ConsumoPuestoTrabajoQuerier
					.obtenerListaConsumoComponentePorProductoProcesoLineaNegYFecha(fechaNotif, producto, proceso, pkLineaNegocio);

			Map<Long, Consumocomponente> mapaComponentes = new HashMap<Long, Consumocomponente>();
			for (Consumopuestotrabajo consumopuestotrabajo : consumosPuestotrabajo) {

				Componente componente = consumopuestotrabajo.getComponente();
				Consumocomponente consumocomponente = mapaComponentes.get(componente.getPkCodigoComponente());
				double consumoIter = consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue();
				double consumoHM = consumopuestotrabajo.getCantidadHumedaConsumopuestotrabajo().doubleValue();
				if (consumocomponente == null) {
					consumocomponente = new Consumocomponente(componente, tablakardex, consumoIter);

					if (isAfectaHumedadDiferencial(consumocomponente.getComponente().getProductoByFkCodigoProducto()
							.getNombreProducto())) {
						Long comp = consumocomponente.getComponente().getProductoByFkCodigoProducto().getPkCodigoProducto();
						Double factorSemiSeco = 0d;
						Double factorDiferencia = 0d;

						Factorconsumopuestotrabajo factorPuestoTrabajo1 = FactorconsumopuestotrabajoQuerier
								.obtenerFactorConsumoPorCodigoConsumoPuestoTrabajo(consumopuestotrabajo
										.getPkCodigoConsumopuestotrabajo());
						Factorconsumopuestotrabajo factorPuestoTrabajo = obtenerFactorCarbones(tablakardex.getFechaTablakardex(),
								comp);
						Double valor = 0d;

						if (factorPuestoTrabajo != null)
							valor = factorPuestoTrabajo.getValorFactorconsumopuestotrabajo();

						Double valor2 = 0d;
						if (factorPuestoTrabajo1 != null)
							valor2 = factorPuestoTrabajo1.getValorFactorconsumopuestotrabajo();

						if ((valor > 0.0) && (valor2 > 0.0)) {
							factorDiferencia = valor2 - valor;
						}
						factorSemiSeco = (consumoHM - (consumoHM * (factorDiferencia / 100)));
						consumocomponente.setFkCodigoFactorconsumopuestotrabajoFactor1(factorPuestoTrabajo1);
						consumocomponente.setFkCodigoFactorconsumopuestotrabajoFactor2(factorPuestoTrabajo);
						consumocomponente.setConsumoConsumocomponenteSemiseco(factorSemiSeco);
						consumocomponente.setConsumoConsumocomponenteDiferencia(factorDiferencia);
						consumocomponente.setConsumoConsumocomponenteHumedo(consumoHM);
					}
					mapaComponentes.put(componente.getPkCodigoComponente(), consumocomponente);
				} else {
					// double consumoIter =
					// consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue();--seco
					// double consumoHM =
					// consumopuestotrabajo.getCantidadHumedaConsumopuestotrabajo().doubleValue();--humedo

					double consumoAct = consumocomponente.getConsumoConsumocomponente().doubleValue();
					if (isAfectaHumedadDiferencial(consumocomponente.getComponente().getProductoByFkCodigoProducto()
							.getNombreProducto())) {
						double consumoActHM = consumocomponente.getConsumoConsumocomponenteHumedo().doubleValue();
						double consumoSemiseco = (consumoHM - (consumoHM * (consumocomponente
								.getConsumoConsumocomponenteDiferencia() / 100)));
						// consumocomponente.setcon
						consumocomponente.setConsumoConsumocomponenteSemiseco(consumoSemiseco
								+ consumocomponente.getConsumoConsumocomponenteSemiseco());
						consumocomponente.setConsumoConsumocomponenteHumedo(consumoActHM + consumoHM);
					}

					// el valor de la tabla consumo componente
					consumocomponente.setConsumoConsumocomponente(consumoAct + consumoIter);
				}
			}

			Set<Long> keySet = mapaComponentes.keySet();
			for (Iterator<Long> iterator = keySet.iterator(); iterator.hasNext();) {
				Long key = iterator.next();
				session.save(mapaComponentes.get(key));
			}

		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	private boolean isAfectaHumedadDiferencial(String nombreProducto) {
		// TODO Cambiar por Parametros de la BD o Properties
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("CAR MIX S1");
		lista.add("CAR MIX S2");

		for (String producto : lista) {
			if (producto.equals(nombreProducto)) {
				return true;
			}
		}
		return false;
	}

	private Factorconsumopuestotrabajo obtenerFactorCarbones(Date fechaTablakardex, Long comp) {
		Factorconsumopuestotrabajo factor = null;
		// TODO MODIFICAR POR PARAMETROS
		factor = FactorconsumopuestotrabajoQuerier.obtenerFactorConsumoPorCodigoConsumoYCodigoProductoPuestoTrabajo(
				Long.valueOf(98), Long.valueOf(157), comp, fechaTablakardex);
		if (factor == null) {
			factor = FactorconsumopuestotrabajoQuerier.obtenerFactorConsumoPorCodigoConsumoYCodigoProductoPuestoTrabajo(
					Long.valueOf(96), Long.valueOf(157), comp, fechaTablakardex);
		}
		if (factor == null) {
			factor = FactorconsumopuestotrabajoQuerier.obtenerFactorConsumoPorCodigoConsumoYCodigoProductoPuestoTrabajo(
					Long.valueOf(97), Long.valueOf(157), comp, fechaTablakardex);
		}

		if (factor != null && factor.getValorFactorconsumopuestotrabajo() == 0d) {
			factor = FactorconsumopuestotrabajoQuerier.obtenerFactorConsumoPorCodigoConsumoYCodigoProductoPuestoTrabajo(
					Long.valueOf(96), Long.valueOf(157), comp, fechaTablakardex);
		}

		if (factor != null && factor.getValorFactorconsumopuestotrabajo() == 0d) {
			factor = FactorconsumopuestotrabajoQuerier.obtenerFactorConsumoPorCodigoConsumoYCodigoProductoPuestoTrabajo(
					Long.valueOf(97), Long.valueOf(157), comp, fechaTablakardex);
		}

		return factor;
	}

	/**
	 * Realiza el registro del parte diario para los productos terminados
	 * 
	 * @param notificacionBean notificacion diaria
	 * @param ordenproduccion orde de produccion
	 * @param producto producto
	 * @param producciondiaria produccion diaria
	 * @param ubicacionPlanta
	 * @param almacenPpYPt
	 * @param session
	 * @param factorhum
	 * @return Objeto Tablakardex que posee información correspondiente al
	 *         ingreso, producción, consumo, componentes, las TM Húmedas, la
	 *         conversión a TM secas y las variaciones de stock en libros vs
	 *         físico.
	 * @throws LogicaException
	 */
	private Tablakardex registroParteDiarioPT(NotificacionDiariaBean notificacionBean, Ordenproduccion ordenproduccion,
			Producto producto, Producciondiaria producciondiaria, Almacen almacenPpYPt, Ubicacion ubicacionPlanta, Session session)
			throws LogicaException {

		Date fechaNotf = obtenerFechaKardex(notificacionBean);

		// El consumo se obtiene de lo movimientos de despacho
		double consumoTnSecas = obtenerConsumosPt(notificacionBean, producto);

		Double consumosTnHum = consumoTnSecas;
		double saldoInicial = obtenerStockFinalDiaAnteriorPorOrden(producciondiaria, ordenproduccion, fechaNotf);

		// El ingreso se obtiene del consumo de los componentes del producto
		double ingresoTnSecas = obtenerIngresoPpYPt(ordenproduccion, fechaNotf);
		Double ingresoTnHum = ingresoTnSecas;

		double stockFinal = (ingresoTnSecas + saldoInicial) - consumoTnSecas;

		double ajusteLogisticoProducto = obtenerAjusteProductoLogistico(producciondiaria.getPartediario().getLineanegocio()
				.getPkCodigoLineanegocio(), producto.getPkCodigoProducto(), fechaNotf);
		stockFinal += ajusteLogisticoProducto;

		Tablakardex tablakardex = new Tablakardex(producciondiaria, almacenPpYPt, ubicacionPlanta, fechaNotf, ingresoTnSecas,
				consumoTnSecas, saldoInicial, null, stockFinal, 0d);

		tablakardex.setIngresoHumedadTablakardex(ingresoTnHum);
		tablakardex.setConsumoHumedadTablakardex(consumosTnHum);
		tablakardex.setAjustelogisticoTablakardex(ajusteLogisticoProducto);

		Produccion produccion = ordenproduccion.getProduccion();

		Map<Long, Double> mapaSilosAnteriores = obtenerStockFinalDiaAnteriorSilos(producciondiaria, ordenproduccion, fechaNotf);

		session.save(tablakardex);

		crearConsumosComponentesPoducto(notificacionBean, producto, tablakardex, session, produccion.getProceso());

		crearStockSilos(notificacionBean, producto, tablakardex, fechaNotf, mapaSilosAnteriores, session);

		tablakardex.setVariacionTablakardex(0d);

		Partediario partediario = producciondiaria.getPartediario();
		double stockFisico = obtenerStockFisicoMedicion(partediario, fechaNotf, producto);
		tablakardex.setStockFisicoTablakardex(stockFisico);

		if (stockFisico != 0d) {
			double variacion = (stockFisico - stockFinal) * 100 / stockFisico;
			tablakardex.setVariacionTablakardex(variacion);
			session.update(tablakardex);
		}

		return tablakardex;
	}

	private double obtenerAjusteProductoLogistico(Long codigoLineNegocio, Long producto, Date fechaDiaria) {
		Double ajustoProducto = 0d;
		try {
			ajustoProducto = movimientoLogicFacade.obtenerMovimentoLogisticoProductoDiariaDAO(codigoLineNegocio, producto,
					fechaDiaria);
		} catch (LogicaException e) {
			logger.error(e);

		}
		return ajustoProducto;
	}

	@SuppressWarnings("unchecked")
	private void crearStockSilos(NotificacionDiariaBean notificacionBean, Producto producto, Tablakardex tablakardex,
			Date fechaNotif, Map<Long, Double> mapaSilosAnteriores, Session session) throws LogicaException {

		List<Medioalmacenamiento> mediosAlmc = NotificacionProduccionQuerier.obtenerMediosAlmacPorProductoNotificado(
				notificacionBean, producto);

		Lineanegocio lineanegocio = beanFactory.transformarLineaNegocioBean(notificacionBean.getLineaNegocio());

		for (Iterator<Medioalmacenamiento> iterator = mediosAlmc.iterator(); iterator.hasNext();) {
			Medioalmacenamiento medioAlmac = iterator.next();

			Double ingresoSilo = obtenerIngresoProductoEnSilo(producto, notificacionBean.getCodigo(), medioAlmac);
			Double saldoI;
			if (mapaSilosAnteriores != null) {
				saldoI = mapaSilosAnteriores.get(medioAlmac.getPkCodigoMedioalmacenamiento());
			} else {
				saldoI = 0d;
			}

			Double saldoInicial = saldoI == null ? 0d : saldoI;
			Double consumoSilo;
			try {
				consumoSilo = MovimientoQuerier.obtenerMovProProductoFechaYMedioAlmc(lineanegocio, producto, medioAlmac,
						fechaNotif);
				if (consumoSilo == null) {
					consumoSilo = 0d;
				}
			} catch (ElementoNoEncontradoException e) {
				throw new LogicaException(e);
			} catch (EntornoEjecucionException e) {
				throw new LogicaException(e);
			}
			Double stockFinalSilo = ingresoSilo + saldoInicial - consumoSilo;

			Kardexmedioalmacenamiento kardexmedioalmacenamiento = new Kardexmedioalmacenamiento(tablakardex, medioAlmac,
					saldoInicial, ingresoSilo, consumoSilo, stockFinalSilo);

			tablakardex.getKardexmedioalmacenamientos().add(kardexmedioalmacenamiento);

			session.save(kardexmedioalmacenamiento);
		}
	}

	/**
	 * Obtiene la cantidad de procuto que se almaceno en un silo el dia que se
	 * esta notificando
	 * 
	 * @param producto
	 * @param codigoNotifDiaria
	 * @param medioAlmac
	 * @return
	 * @throws LogicaException
	 */
	private double obtenerIngresoProductoEnSilo(Producto producto, Long codigoNotifDiaria, Medioalmacenamiento medioAlmac)
			throws LogicaException {

		try {
			List<Componente> componentes = HojaRutaComponenteQuerier.obtenerComponentesPorProductoSegunHojaRuta(producto
					.getPkCodigoProducto());

			double ingreso = 0d;

			for (Iterator<Componente> iterator = componentes.iterator(); iterator.hasNext();) {
				Componente componente = iterator.next();

				ingreso += ComponenteNotificacionQuerier.getConsumoComponentePorSilo(componente, codigoNotifDiaria, medioAlmac);
			}

			return ingreso;

		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Hace una conversion de la fecha de la notificacion, necesaria ya que esta
	 * dando un error de que cuando se guarda la fecha directa le adiciona un
	 * dia
	 * 
	 * @param notificacionBean
	 * @return
	 * @throws LogicaException
	 */
	private Date obtenerFechaKardex(NotificacionDiariaBean notificacionBean) throws LogicaException {
		Date fechaNotf = notificacionBean.getFechaNotificacionDiaria();

		DateFormat df = new SimpleDateFormat(FechaUtil.PATRON_FECHA_APLICACION);
		String fechaStr = df.format(fechaNotf);

		try {
			fechaNotf = df.parse(fechaStr);
		} catch (ParseException e) {
			throw new LogicaException(ERROR_FATAL_FALLO);
		}
		return fechaNotf;
	}

	/**
	 * Obtiene los consumos deun producto terminado, segun los movimientos de
	 * salida registrados
	 * 
	 * @param notificacionBean
	 * @param producto
	 * @return
	 * @throws LogicaException
	 */
	private double obtenerConsumosPt(NotificacionDiariaBean notificacionBean, Producto producto) throws LogicaException {
		try {
			Lineanegocio lineanegocio = beanFactory.transformarLineaNegocioBean(notificacionBean.getLineaNegocio());
			List<Movimiento> movimientosPT = MovimientoQuerier.obtenerCantidadSegunLineaNegProductoFecha(lineanegocio, producto,
					notificacionBean.getFechaNotificacionDiaria());

			String codAnulacionSalida = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_MOVIMIENTOS_ANULACION_SALIDA);
			String codEntregaRetornoLibre = ManejadorPropiedades
					.obtenerPropiedadPorClave(CODIGO_MOVIMIENTOS_ENTREGA_RETORNO_LIBRE);
			String codAnulacionSalida1 = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_MOVIMIENTOS_ANULACION_SALIDA1);
			Long clasifMovSalida = new Long(2);
			double consumo = 0d;

			for (int i = 0; i < movimientosPT.size(); i++) {
				Movimiento movimiento = movimientosPT.get(i);

				boolean traslado = false;
				for (int j = 0; j < movimientosPT.size(); j++) {
					if (i == j) {
						continue;
					}
					Movimiento mov = movimientosPT.get(j);

					traslado |= movimiento.esTranslado(mov);
				}

				if (movimiento.getTipomovimiento().getClasificaciontipomovimiento().getPkCodigoClasificaciontipomovi()
						.longValue() == clasifMovSalida.longValue()
						&& !traslado) {
					consumo += movimiento.getCantidadMovimiento();
				}
				// TODO CAMBIO INGRESANDO DATOS ADICIONALES DE SALIDA
				String codigoSapTipomovimiento = movimiento.getTipomovimiento().getCodigoSapTipomovimiento();
				if (codigoSapTipomovimiento.equals(codAnulacionSalida) || codigoSapTipomovimiento.equals(codEntregaRetornoLibre)
						|| codAnulacionSalida1.indexOf(codigoSapTipomovimiento) >= 0) {
					consumo -= movimiento.getCantidadMovimiento();
				}
			}
			return consumo;
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Realiza el registro del parte diario agrupando por puesto de trabajo
	 * 
	 * @param notificacionBean
	 * @param partediario
	 * @param session2
	 * @param session
	 * @throws AplicacionException
	 */

	private void registroProducciondiariaPuestoTrabajo(NotificacionDiariaBean notificacionBean, Partediario partediario,
			Session session) throws LogicaException {
		try {

			List<Puestotrabajo> puestosDeTrabajo = NotificacionProduccionQuerier
					.obtenerPuestosDeTrabajoSegunNotifDiaria(notificacionBean.getCodigo());

			Date fechaNotf = obtenerFechaKardex(notificacionBean);

			for (Iterator<Puestotrabajo> iterator = puestosDeTrabajo.iterator(); iterator.hasNext();) {
				Puestotrabajo puestoTrabajo = iterator.next();

				Produccionpuestotrabajo produccionPuestoTrabajo = obtenerProduccionPuestoTrabajo(partediario, puestoTrabajo,
						session);

				Tablaoperacion tablaoperacion = obtenerTablaOperacion(produccionPuestoTrabajo, fechaNotf, session);

				List<Ordenproduccion> ordenes = NotificacionProduccionQuerier
						.obtenerOrdenesProduccionSegunNotifDiariayPuestoTrab(notificacionBean.getCodigo(),
								puestoTrabajo.getPkCodigoPuestotrabajo());

				for (Iterator<Ordenproduccion> ittOrdenProd = ordenes.iterator(); ittOrdenProd.hasNext();) {
					Ordenproduccion ordenproduccion = ittOrdenProd.next();

					List<Notificacionproduccion> notificacionesProduccion = NotificacionProduccionQuerier
							.obtenerPorNotifPuestoTrabYOrden(notificacionBean.getCodigo(),
									puestoTrabajo.getPkCodigoPuestotrabajo(), ordenproduccion.getPkCodigoOrdenproduccion());

					Productogenerado productogenerado = new Productogenerado(tablaoperacion, ordenproduccion, 0d, 0d);
					session.save(productogenerado);

					double horasOperacion = 0d;

					Map<Long, Consumopuestotrabajo> mapaConsumoComponentes = new HashMap<Long, Consumopuestotrabajo>();
					for (Iterator<Notificacionproduccion> ittNotif = notificacionesProduccion.iterator(); ittNotif.hasNext();) {
						Notificacionproduccion notificacionProduccion = ittNotif.next();

						Set listaComponentes = notificacionProduccion.getComponentenotificacions();

						if (notificacionProduccion.getCambioproduccionNotificacionpr()) {
							String[] horaCambio = notificacionProduccion.getHoraCambioproduccionNotificac().split(":");
							double minutos = Double.parseDouble(horaCambio[1]);
							horasOperacion += (minutos / 60d);
						} else {
							horasOperacion += notificacionProduccion.getHoraEcsNotificacionproduccion();
						}

						totalizarConsumoComponentes(mapaConsumoComponentes, listaComponentes, productogenerado);
					}
					transformarConsumosATmSecas(mapaConsumoComponentes, productogenerado, horasOperacion, session);
					calcularProduccion(ordenproduccion, mapaConsumoComponentes, productogenerado, horasOperacion, puestoTrabajo,
							fechaNotf, session);
					actualizarTablaOperacion(tablaoperacion, productogenerado, session);
				}
			}
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Metodo que calcula las producciones de los PP y PT
	 * 
	 * @param ordenproduccion Ordenproduccion
	 * @param mapaConsumoComponentes Map<Long, Consumopuestotrabajo> mapa que
	 *            contiene los cmoponentes que se conumen en determinado puesto
	 *            de trabajo
	 * @param productogenerado Productogenerado producto que esta generando el
	 *            puesto de trabajo
	 * @param horasOperacion horasOperacion horas
	 * @param puestoTrabajo Puestotrabajo puesto de trabajo
	 * @param fechaNotif Date fecha
	 * @param session Session sesion hibernate
	 * @throws LogicaException si el calculo de la produccion falla
	 */
	private void calcularProduccion(Ordenproduccion ordenproduccion, Map<Long, Consumopuestotrabajo> mapaConsumoComponentes,
			Productogenerado productogenerado, double horasOperacion, Puestotrabajo puestoTrabajo, Date fechaNotif,
			Session session) throws LogicaException {

		Proceso proceso = ordenproduccion.getProduccion().getProceso();
		Producto producto = ordenproduccion.getProduccion().getProducto();

		String nombreProceso = proceso.getNombreProceso().toLowerCase();
		Set<Entry<Long, Consumopuestotrabajo>> entrySet = mapaConsumoComponentes.entrySet();

		Boolean bValidaPuestoTrabajo = false;
		double variacionProduccion = 1.00;
		try {
			// OBTENGO FACTOR DE ERROR DE PRODUCCION

			Productovariablevariacion productovariablevariacion = ProductoVariableVariacionQuerier
					.obtenerValorVaribaleVariacionProduccion(producto.getPkCodigoProducto(), proceso.getPkCodigoProceso(),
							puestoTrabajo.getPkCodigoPuestotrabajo(), fechaNotif);

			if (productovariablevariacion != null && productovariablevariacion.getValorProductovariablevariacion() != null
					&& productovariablevariacion.getValorProductovariablevariacion() > 0d) {
				variacionProduccion = productovariablevariacion.getValorProductovariablevariacion();
			}

			ParametroSistema parametroSistema = ParametroSistemaQuerier
					.obtenerParametroSistema(ConstantesParametro.PUESTO_TRABAJO_KCAL);
			if (parametroSistema != null) {
				String[] parametroPuestoTrabajo = parametroSistema.getValorParametro().split(",");
				for (String codPuestoTrabajo : parametroPuestoTrabajo) {
					if (puestoTrabajo.getPkCodigoPuestotrabajo() == Long.parseLong(codPuestoTrabajo)) {
						bValidaPuestoTrabajo = true;
					}
				}
			} else {
				logger.error("No se encontró registros en el parametroSistema PUESTO_TRABAJO_KCAL");
			}

			RendimientoTermico rendimientoTermico = RendimientoTermicoQuerier.obtenerRendimientoTermico(
					puestoTrabajo.getPkCodigoPuestotrabajo(), producto.getPkCodigoProducto());

			// Proceso Clinkerizacion en Hornos Verticales
			if (nombreProceso.equals(CLINKERIZACION_HV)) {
				calcularProduccionClkHornosVerticales(productogenerado, fechaNotif, entrySet, variacionProduccion);
			}
			// Proceso Clinkerizacion en Hornos Horizontales
			else if (nombreProceso.equals(CLINKERIZACION_HH)) {
				calcularProduccionClkHornosHorizontales(productogenerado, puestoTrabajo, proceso, entrySet, variacionProduccion,
						bValidaPuestoTrabajo, rendimientoTermico);
			}

			// Proceso Molienda de Cal o Calcinacion de Cal
			else if (nombreProceso.equals(PROC_MOLIENDA_CAL) || nombreProceso.equals(PROC_CALCINACION_CAL)) {
				calcularProduccionMoliendaCal(ordenproduccion, productogenerado, puestoTrabajo, fechaNotif, proceso, entrySet,
						variacionProduccion, bValidaPuestoTrabajo, rendimientoTermico);
			}
			// Proceso Molienda Cemento, Carbon, Crudo y el resto
			else {
				calcularProduccionProductosRestantes(productogenerado, producto, nombreProceso, entrySet, variacionProduccion);
			}
			productogenerado.setHorasProductogenerado(horasOperacion);
			session.saveOrUpdate(productogenerado);

			if (productovariablevariacion != null && productovariablevariacion.getValorProductovariablevariacion() != null
					&& productovariablevariacion.getValorProductovariablevariacion() > 0d) {
				Factorvariacionproduccionpuestotrabajo factorvariacionproduccionpuestotrabajo = new Factorvariacionproduccionpuestotrabajo(
						productogenerado, productovariablevariacion,
						productovariablevariacion.getValorProductovariablevariacion());
				session.saveOrUpdate(factorvariacionproduccionpuestotrabajo);
			}
		} catch (SesionVencidaException e) {
			throw new LogicaException(e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e);
		}
	}

	/**
	 * Calula la produccion de los productos de los procesos molienda de
	 * cemento, molienda de crudo, molienda de carbon
	 * 
	 * @param productogenerado Productogenerado
	 * @param producto Producto
	 * @param nombreProceso String
	 * @param entrySet Set<Entry<Long, Consumopuestotrabajo>>
	 * @param variacionProduccion
	 */
	private void calcularProduccionProductosRestantes(Productogenerado productogenerado, Producto producto, String nombreProceso,
			Set<Entry<Long, Consumopuestotrabajo>> entrySet, double variacionProduccion) {
		String moliendaCarbon = ManejadorPropiedades.obtenerPropiedadPorClave(PROCESO_MOLIENDA_CARBON).toLowerCase();
		double produccionTm = 0d;
		String crudoNegro = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_CRUDO_NEGRO).toLowerCase();
		for (Entry<Long, Consumopuestotrabajo> entry : entrySet) {
			Consumopuestotrabajo consumopuestotrabajo = entry.getValue();
			Double cantidadConsumopuestotrabajo = consumopuestotrabajo.getCantidadConsumopuestotrabajo();
			double consumoComponente = cantidadConsumopuestotrabajo == null ? 0d : cantidadConsumopuestotrabajo.doubleValue();

			if (consumoComponente == 0d) {
				continue;
			}

			String nombreProducto = producto.getNombreProducto().toLowerCase();

			Tipocategoriaproducto tipocategoriaproducto = consumopuestotrabajo.getComponente()
					.getProductoByFkCodigoProductoComponente().getTipocategoriaproducto();

			if (nombreProducto.equals(crudoNegro) || nombreProceso.equals(moliendaCarbon) || tipocategoriaproducto == null) {
				produccionTm += consumoComponente;
			} else {
				String nombreTipocategoria = tipocategoriaproducto.getNombreTipocategoriaproducto().toLowerCase();

				if (!nombreTipocategoria.equals(TIPO_COMBUSTIBLE)) {
					produccionTm += consumoComponente;
				}

			}
		}
		// HALLAR PRODUCCION SEGUN VARIABLE PRODUCCION
		produccionTm = produccionTm * variacionProduccion;
		productogenerado.setProduccionTmProductogenerado(produccionTm);
	}

	/**
	 * Calula la produccion de los productos de los procesos molienda de cal
	 * 
	 * @param ordenproduccion Ordenproduccion
	 * @param productogenerado Productogenerado
	 * @param puestoTrabajo Puestotrabajo
	 * @param fechaNotif Date
	 * @param proceso Proceso
	 * @param petroleoBunkerLowwer String
	 * @param entrySet Set<Entry<Long, Consumopuestotrabajo>>
	 * @param variacionProduccion
	 * @param rendimientoTermico
	 * @param bValidaPuestoTrabajo
	 * @throws LogicaException
	 * @throws ElementoNoEncontradoException
	 */
	private void calcularProduccionMoliendaCal(Ordenproduccion ordenproduccion, Productogenerado productogenerado,
			Puestotrabajo puestoTrabajo, Date fechaNotif, Proceso proceso, Set<Entry<Long, Consumopuestotrabajo>> entrySet,
			double variacionProduccion, Boolean bValidaPuestoTrabajo, RendimientoTermico rendimientoTermico)
			throws LogicaException {

		String hornoWaels = ManejadorPropiedades.obtenerPropiedadPorClave(PUESTO_TRABAJO_HORNOWAELS).toLowerCase();
		String puestoHCal = ManejadorPropiedades.obtenerPropiedadPorClave(PUESTO_TRABAJO_HCAL).toLowerCase();

		String nombrePuestoTrabajo = puestoTrabajo.getNombrePuestotrabajo().toLowerCase();

		if (nombrePuestoTrabajo.equals(hornoWaels) || nombrePuestoTrabajo.equals(puestoHCal)) {
			calcularProduccionHornosWaelsHcal(productogenerado, entrySet, fechaNotif, ordenproduccion, proceso,
					variacionProduccion, bValidaPuestoTrabajo, rendimientoTermico);
		} else {
			calcularProduccionMolinosCal(productogenerado, entrySet, variacionProduccion);
		}

	}

	private void calcularProduccionMolinosCal(Productogenerado productogenerado, Set<Entry<Long, Consumopuestotrabajo>> entrySet,
			double variacionProduccion) {
		double produccionTm = 0d;

		for (Entry<Long, Consumopuestotrabajo> entry : entrySet) {
			Consumopuestotrabajo consumopuestotrabajo = entry.getValue();
			Double cantidadConsumopuestotrabajo = consumopuestotrabajo.getCantidadConsumopuestotrabajo();
			double consumoComponente = cantidadConsumopuestotrabajo == null ? 0d : cantidadConsumopuestotrabajo.doubleValue();

			if (consumoComponente == 0d) {
				continue;
			}

			Tipocategoriaproducto tipocategoriaproducto = consumopuestotrabajo.getComponente()
					.getProductoByFkCodigoProductoComponente().getTipocategoriaproducto();

			if (tipocategoriaproducto != null) {
				String nombreTipocategoria = tipocategoriaproducto.getNombreTipocategoriaproducto().toLowerCase();
				if (!nombreTipocategoria.equals(TIPO_COMBUSTIBLE)) {
					produccionTm += consumoComponente;
				}
			} else {
				produccionTm += consumoComponente;
			}
		}
		// CALCULAR PRODUCCION
		produccionTm = produccionTm * variacionProduccion;
		productogenerado.setProduccionTmProductogenerado(produccionTm);
	}

	private void calcularProduccionHornosWaelsHcal(Productogenerado productogenerado,
			Set<Entry<Long, Consumopuestotrabajo>> entrySet, Date fechaNotif, Ordenproduccion ordenproduccion, Proceso proceso,
			double variacionProduccion, Boolean bValidaPuestoTrabajo, RendimientoTermico rendimientoTermico)
			throws LogicaException {

		String calizaCalFabrica = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_CALIZACAL_FABRICA).toLowerCase();
		double consumoCalizaCal = 0d;
		double perdidasfuegoCal = 0d;
		double perdidasfuegoCaliza = 0d;
		double consumoCarbon = 0d;
		double totalPoderCalorificoCarbon = 0d;
		double totalCantidadCarbones = 0;
		double consumoPetroleo = 0d;

		for (Entry<Long, Consumopuestotrabajo> entry : entrySet) {
			Consumopuestotrabajo consumopuestotrabajo = entry.getValue();
			Double cantidadConsumopuestotrabajo = consumopuestotrabajo.getCantidadConsumopuestotrabajo();
			double consumoComponente = cantidadConsumopuestotrabajo == null ? 0d : cantidadConsumopuestotrabajo.doubleValue();

			if (consumoComponente == 0d) {
				continue;
			}

			Producto componente = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente();
			String nombreProducto = componente.getNombreProducto().toLowerCase();

			if (nombreProducto.equals(calizaCalFabrica)) {
				consumoCalizaCal += consumoComponente;

				String perdidaFuegoCal = ManejadorPropiedades.obtenerPropiedadPorClave(VAR_CALIDAD_PERDIDA_FUEGO);
				perdidasfuegoCal = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo, perdidaFuegoCal);

				String perdidaFuegoCaliza = ManejadorPropiedades.obtenerPropiedadPorClave(VAR_CALIDAD_PERDIDA_FUEGO_CRUDO_NEGRO);
				perdidasfuegoCaliza = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
						perdidaFuegoCaliza);

			}
			String nombreBunkerCal = ManejadorPropiedades.obtenerPropiedadPorClave("producto.petroleoBunkerCAL");
			if (nombreProducto.equals(PETROLEO_BUNKER.toLowerCase()) || nombreProducto.equals(nombreBunkerCal.toLowerCase())) {
				consumoPetroleo += consumoComponente;

			}

			Tipocategoriaproducto tipocategoriaproducto = componente.getTipocategoriaproducto();

			if (tipocategoriaproducto != null) {
				String nombreTipoCategoria = tipocategoriaproducto.getNombreTipocategoriaproducto().toLowerCase();

				if (nombreTipoCategoria.equals(TIPO_COMBUSTIBLE)
						&& (!nombreProducto.equals(PETROLEO_BUNKER.toLowerCase()) && !nombreProducto.equals(nombreBunkerCal
								.toLowerCase()))) {

					consumoCarbon += consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue();
					totalPoderCalorificoCarbon += obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
							PODER_CALORIFICO);
					totalCantidadCarbones++;
				}
			}
		}

		double poderCalorificoCarbonPromedio = 0d;
		if (totalCantidadCarbones != 0d) {
			poderCalorificoCarbonPromedio = totalPoderCalorificoCarbon / totalCantidadCarbones;
		}

		double calRechazada = obtenerCalRechazada(fechaNotif);

		double produccionCal = (consumoCalizaCal * (1 - ((perdidasfuegoCal - perdidasfuegoCaliza) / 100d)) + (consumoCarbon / 100))
				* 0.9 - calRechazada;
		// CALCULO PRODUCCION DE CAL
		produccionCal = produccionCal * variacionProduccion;

		productogenerado.setProduccionTmProductogenerado(produccionCal);

		double kCal = 0d;

		if (totalCantidadCarbones != 0d) {
			poderCalorificoCarbonPromedio = totalPoderCalorificoCarbon / totalCantidadCarbones;
			if (bValidaPuestoTrabajo) {
				Double carbonDesc = 0d, petroleoDesc = 0d;
				List<Double> listValores = new ArrayList<Double>();
				listValores = calcularKiloCaloriasPorRendimientoTermico(consumoCarbon, poderCalorificoCarbonPromedio,
						consumoPetroleo, 0d, 0d, produccionCal, rendimientoTermico, 0d, 0d, calRechazada,
						ProcesoFormula.CALCINACION_CAL);
				kCal = listValores.get(0);
				petroleoDesc = listValores.get(1);
				carbonDesc = listValores.get(2);
				// productogenerado.setKcalProductogenerado(kCal);
				productogenerado.setDescuentoAdicionalLiquido(petroleoDesc);
				productogenerado.setDescuentoAdicionalSolido(carbonDesc);
			} else {

				kCal = ((((consumoPetroleo * FACTOR_GALONES) / 1000.0) * 0.98 * 9850.0) + (consumoCarbon * poderCalorificoCarbonPromedio))
						/ ((produccionCal + calRechazada));

			}

			productogenerado.setKcalProductogenerado(kCal);
		} else {
			productogenerado.setKcalProductogenerado(0d);
		}
	}

	/**
	 * Calula la produccion de Clinker en los hornos Horizontales
	 * 
	 * @param productogenerado Productogenerado
	 * @param puestoTrabajo Puestotrabajo
	 * @param proceso Proceso
	 * @param petroleoBunkerLowwer String
	 * @param entrySet Set<Entry<Long, Consumopuestotrabajo>>
	 * @param variacionProduccion
	 * @throws LogicaException
	 * @throws ElementoNoEncontradoException
	 */
	private void calcularProduccionClkHornosHorizontales(Productogenerado productogenerado, Puestotrabajo puestoTrabajo,
			Proceso proceso, Set<Entry<Long, Consumopuestotrabajo>> entrySet, double variacionProduccion,
			Boolean bValidaPuestoTrabajo, RendimientoTermico rendimientoTermico) throws LogicaException,
			ElementoNoEncontradoException {

		double crudo = 0d;
		double carbones = 0d;

		double cenizas = 0d;
		double cantidadCenizas = 0;
		double cantidadPoderCalorifico = 0;

		double perdidasF = 0d;
		double cantidadPerdidasF = 0;
		double poderCalorificoCarbon = 0d;

		double consumoPetroleo = 0d;
		Double calentamientoPetroleo = 0d;
		Double calentamientoCarbon = 0d;

		double kCal = 0d;

		for (Entry<Long, Consumopuestotrabajo> entry : entrySet) {
			Consumopuestotrabajo consumopuestotrabajo = entry.getValue();

			Producto componente = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente();
			if (consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue() == 0d) {
				continue;
			}

			double consumoComponente = consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue();
			double consumoComponenteCalentamiento = consumopuestotrabajo.getCantidadCalentamientoConsumopuestotrabajo();

			String nombreProductoLower = componente.getNombreProducto().toLowerCase();
			String siglasProducto = componente.getSiglasProducto().toLowerCase();

			boolean esDistintoDePetroleoBunker = !nombreProductoLower.equals(PETROLEO_BUNKER.toLowerCase());
			boolean esTipoCombustible = false;
			Tipocategoriaproducto tipocategoriaproducto = componente.getTipocategoriaproducto();
			if (tipocategoriaproducto != null) {
				esTipoCombustible = tipocategoriaproducto.getNombreTipocategoriaproducto().toLowerCase().equals(TIPO_COMBUSTIBLE);
			}

			if (esTipoCombustible && esDistintoDePetroleoBunker) {

				carbones += consumoComponente;
				calentamientoCarbon = consumoComponenteCalentamiento;
				String descCeniza = ManejadorPropiedades.obtenerPropiedadPorClave(VAR_CALIDAD_CENIZAS_CARBONES);

				double valorVarCenizas = 0d;
				double valorPoderCalorifico = 0d;

				valorVarCenizas = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo, descCeniza);
				valorPoderCalorifico = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
						PODER_CALORIFICO);

				if (valorVarCenizas > 0d) {
					cantidadCenizas++;
				}

				cenizas += valorVarCenizas;

				poderCalorificoCarbon += valorPoderCalorifico;

				if (valorPoderCalorifico > 0d) {
					cantidadPoderCalorifico++;
				}
			} else {
				if (SIGLAS_CRUDO.indexOf(siglasProducto) >= 0) {
					crudo += consumoComponente;

					double valorPerdidasFuego = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
							PERDIDAS_FUEGO);

					perdidasF += valorPerdidasFuego;

					cantidadPerdidasF++;
				} else if (nombreProductoLower.equals(PETROLEO_BUNKER.toLowerCase())) {
					consumoPetroleo = consumoComponente;
					calentamientoPetroleo = consumoComponenteCalentamiento;
				}
			}
		}

		if (cantidadCenizas != 0) {
			cenizas = cenizas / cantidadCenizas;
		}

		if (cantidadPoderCalorifico != 0) {
			poderCalorificoCarbon = poderCalorificoCarbon / cantidadPoderCalorifico;
		}

		if (cantidadPerdidasF != 0) {
			perdidasF = perdidasF / cantidadPerdidasF;
		}

		carbones = NumberUtil.reducirAdosDecimales(carbones);
		// crudo =0.1d;
		double produccionClinkerHH = 0d;
		if (crudo > 0d) {
			produccionClinkerHH = crudo * (1 - (perdidasF / 100d)) + carbones * (cenizas / 100d);

			// CALCULO SU VARIACION DE PRODUCCIION
			produccionClinkerHH = produccionClinkerHH * variacionProduccion;
		}

		productogenerado.setProduccionTmProductogenerado(produccionClinkerHH);
		productogenerado.setKcalProductogenerado(0d);

		if (produccionClinkerHH != 0d) {

			String densidadPetroleoStr = ManejadorPropiedades.obtenerPropiedadPorClave(DESIDAD_PETROLEO);
			String poderCalorificoPetroleoStr = ManejadorPropiedades.obtenerPropiedadPorClave(PODER_CALORIFICO_PETROLEO);

			Producto petroleo = Querier.findByPropertyUniqueResult(Producto.class, ProductoQuerier.NOMBRE_PRODUCTO,
					PETROLEO_BUNKER);
			if (petroleo != null) {
				double densidadPetroleo = ProductoVariableVariacionQuerier.obtenerValorVaribaleVariacionSegunNombre(
						proceso.getPkCodigoProceso(), petroleo.getPkCodigoProducto(), puestoTrabajo.getPkCodigoPuestotrabajo(),
						densidadPetroleoStr);

				double poderCalorificoPetroleo = ProductoVariableVariacionQuerier.obtenerValorVaribaleVariacionSegunNombre(
						proceso.getPkCodigoProceso(), petroleo.getPkCodigoProducto(), puestoTrabajo.getPkCodigoPuestotrabajo(),
						poderCalorificoPetroleoStr);

				// si no hay registro se coloca 1,
				// para que no afecte el calculo
				if (poderCalorificoPetroleo == 0d) {
					poderCalorificoPetroleo = 1;
				}

				poderCalorificoCarbon = NumberUtil.reducirAdosDecimales(poderCalorificoCarbon);
				produccionClinkerHH = NumberUtil.reducirAdosDecimales(produccionClinkerHH);

				if (bValidaPuestoTrabajo) {
					Double carbonDesc = 0d, petroleoDesc = 0d;
					List<Double> listValores = new ArrayList<Double>();
					listValores = calcularKiloCaloriasPorRendimientoTermico(carbones, poderCalorificoCarbon, consumoPetroleo,
							densidadPetroleo, poderCalorificoPetroleo, produccionClinkerHH, rendimientoTermico,
							calentamientoCarbon, calentamientoPetroleo, 0d, ProcesoFormula.CLINKERIZACION);
					kCal = listValores.get(0);
					petroleoDesc = listValores.get(1);
					carbonDesc = listValores.get(2);

					productogenerado.setDescuentoAdicionalLiquido(petroleoDesc);
					productogenerado.setDescuentoAdicionalSolido(carbonDesc);
				} else {

					kCal = ((carbones * poderCalorificoCarbon) + (consumoPetroleo * densidadPetroleo * poderCalorificoPetroleo))
							/ produccionClinkerHH;
				}

				productogenerado.setKcalProductogenerado(kCal);
			}
		}
	}

	private List<Double> calcularKiloCaloriasPorRendimientoTermico(double carbones, double poderCalorificoCarbon,
			double consumoPetroleo, double densidadPetroleo, double poderCalorificoPetroleo, double produccion,
			RendimientoTermico rendimientoTermico, Double calentamientoCarbon, Double calentamientoPetroleo,
			Double produccionRechazada, ProcesoFormula formula) {

		Double densidad = densidadPetroleo;
		Double poderCalorifico = poderCalorificoPetroleo;
		try {
			ParametroSistemaBean pdendidadPetroleo = parametroSistemaLogic
					.obtenerParametroSistemaDAO(ConstantesParametro.DENSIDAD_PETROLEO_KCAL);
			ParametroSistemaBean prendimientoTermico = parametroSistemaLogic
					.obtenerParametroSistemaDAO(ConstantesParametro.RENDIMIENTO_TERMICO_PETROLEO_KCAL);

			if (pdendidadPetroleo != null) {
				densidad = Double.valueOf(pdendidadPetroleo.getValor());
			}

			if (prendimientoTermico != null) {
				poderCalorifico = Double.valueOf(prendimientoTermico.getValor());
			}
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		List<Double> listValores = new ArrayList<Double>();
		Long kCal2 = rendimientoTermico.getValorKiloCalorias2();
		carbones = carbones - calentamientoCarbon;
		consumoPetroleo = consumoPetroleo - calentamientoPetroleo;
		// Double kCal = ((carbones * poderCalorificoCarbon) +
		// (((consumoPetroleo * FACTOR_GALONES) / 1000.0) * densidad *
		// poderCalorifico))
		// / produccionClinkerHH;
		Double kCal = calcularKcalPuestoTrabajoRedimientoTermico(formula, carbones, poderCalorificoCarbon, consumoPetroleo,
				densidad, poderCalorifico, produccion, produccionRechazada);

		boolean bValida = true;
		double carbon = 0d, petroleo = 0d;
		double progresionCarbonKcal = Double.parseDouble(ParametroSistemaQuerier.obtenerParametroSistema(
				ConstantesParametro.PROGRESION_CARBON_KCAL).getValorParametro());
		double progresionBunkerKcal = Double.parseDouble(ParametroSistemaQuerier.obtenerParametroSistema(
				ConstantesParametro.PROGRESION_BUNKER_KCAL).getValorParametro());

		while (kCal > kCal2) {
			if (bValida) {
				if (consumoPetroleo >= progresionBunkerKcal) {
					petroleo += progresionBunkerKcal;
					consumoPetroleo -= progresionBunkerKcal;
				} else {
					bValida = false;
				}
			} else {
				if (carbones >= progresionCarbonKcal) {
					carbon += progresionCarbonKcal;
					carbones -= progresionCarbonKcal;
				} else {
					break;
				}
			}
			// kCal = ((carbones * poderCalorificoCarbon) + (((consumoPetroleo *
			// FACTOR_GALONES) / 1000.0)) * densidad
			// * poderCalorifico)
			// / produccionClinkerHH;
			kCal = calcularKcalPuestoTrabajoRedimientoTermico(formula, carbones, poderCalorificoCarbon, consumoPetroleo,
					densidad, poderCalorifico, produccion, produccionRechazada);

		}

		listValores.add(kCal);
		listValores.add(petroleo);
		listValores.add(carbon);

		return listValores;
	}

	private Double calcularKcalPuestoTrabajoRedimientoTermico(ProcesoFormula formula, Double carbones,
			Double poderCalorificoCarbon, Double consumoPetroleo, Double densidadPetroleo, Double poderCalorifico,
			Double produccion, Double produccionRechazada) {
		Double calculoFormula = 0d;
		switch (formula) {
		case CLINKERIZACION:
			// calculoFormula = ((carbones * poderCalorificoCarbon) +
			// (((consumoPetroleo * FACTOR_GALONES) / 1000.0)) * densidad
			// * poderCalorifico)
			// / produccionClinkerHH;
			if (produccion > 0) {
				calculoFormula = ((carbones * poderCalorificoCarbon) + (((consumoPetroleo * FACTOR_GALONES) / 1000.0))
						* densidadPetroleo * poderCalorifico)
						/ produccion;
			}

			break;
		case CALCINACION_CAL:
			// calculoFormula = ((((consumoPetroleo * FACTOR_GALONES) / 1000.0)
			// * 0.98 * 9850.0) + (consumoCarbon *
			// poderCalorificoCarbonPromedio))
			// / ((produccionCal + calRechazada));
			Double produccion2 = (produccion + produccionRechazada);

			if (produccion2 > 0) {
				calculoFormula = ((((consumoPetroleo * FACTOR_GALONES) / 1000.0) * 0.98 * 9850.0) + (carbones * poderCalorificoCarbon))
						/ (produccion2);
			}

			break;
		default:
			logger.error("calcularKcalPuestoTrabajoRedimientoTermico No se encontro formula especifica ");
			break;
		}

		return calculoFormula;
	}

	/**
	 * Calula la produccion de Clinker en los hornos
	 * 
	 * @param productogenerado Productogenerado
	 * @param fechaNotif Date
	 * @param entrySet Set<Entry<Long, Consumopuestotrabajo>>
	 * @param variacionProduccion
	 * @throws LogicaException
	 */
	private void calcularProduccionClkHornosVerticales(Productogenerado productogenerado, Date fechaNotif,
			Set<Entry<Long, Consumopuestotrabajo>> entrySet, double variacionProduccion) throws LogicaException {

		String descPerdidasFuegoCrudoNegro = ManejadorPropiedades.obtenerPropiedadPorClave(VAR_CALIDAD_PERDIDA_FUEGO_CRUDO_NEGRO);

		double crudo = 0d;
		double perdidasF = 0d;
		double cantidadPerdidasF = 0d;
		double cantidadHumedadCrudo = 0d;
		double totalPoderCalorificoCarbon = 0d;
		double totalCantidadCArbones = 0d;
		Date ultimaDiaProducCrudoNegro;

		double humedadCrudo = 0d;

		ultimaDiaProducCrudoNegro = obtenerUltimoDiaProduccionCrudoNegro();

		if (ultimaDiaProducCrudoNegro == null) {

			ultimaDiaProducCrudoNegro = fechaNotif;
		}

		for (Entry<Long, Consumopuestotrabajo> entry : entrySet) {
			Consumopuestotrabajo consumopuestotrabajo = entry.getValue();
			if (consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue() == 0d) {
				continue;
			}
			Producto componente = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente();

			String siglas = componente.getSiglasProducto().toLowerCase();
			if (SIGLAS_CRUDO.indexOf(siglas) >= 0) {
				crudo += consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue();

				double valorPerdidasFuego = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
						descPerdidasFuegoCrudoNegro);

				if (valorPerdidasFuego > 0d) {
					cantidadPerdidasF++;
				}

				perdidasF += valorPerdidasFuego;

				String descHumedadCrudo = ManejadorPropiedades.obtenerPropiedadPorClave(VAR_CALIDAD_HUMEDAD_CRUDO);

				double valorhumedadCrudo = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
						descHumedadCrudo);

				if (valorhumedadCrudo > 0d) {
					cantidadHumedadCrudo++;
				}

				humedadCrudo += valorhumedadCrudo;

				double poderCalorifico = obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
						PODER_CALORIFICO, ultimaDiaProducCrudoNegro);
				totalPoderCalorificoCarbon += poderCalorifico;

				if (poderCalorifico > 0d) {
					totalCantidadCArbones++;
				}

			}
		}

		if (cantidadPerdidasF != 0d) {
			perdidasF = perdidasF / cantidadPerdidasF;
		}

		perdidasF = NumberUtil.reducirAdosDecimales(perdidasF);

		if (cantidadHumedadCrudo != 0d) {
			humedadCrudo = humedadCrudo / cantidadHumedadCrudo;
		}

		humedadCrudo = NumberUtil.reducirAdosDecimales(humedadCrudo);

		double produccionClinkerHV = crudo * (1 - (perdidasF / 100d));

		// CALCULO SU FACTOR DE PRODUCCION
		produccionClinkerHV = produccionClinkerHV * variacionProduccion;

		productogenerado.setProduccionTmProductogenerado(produccionClinkerHV);

		produccionClinkerHV = NumberUtil.reducirAdosDecimales(produccionClinkerHV);

		double porcentajeCarbonSecoCrudoNegro = obtenerPorcentajeCarbonSecoCrudoNegro(ultimaDiaProducCrudoNegro);

		double consumoCarboHV = crudo * porcentajeCarbonSecoCrudoNegro / 100d;

		double poderCalorificoPromedio = 0d;

		if (totalCantidadCArbones != 0d) {
			poderCalorificoPromedio = totalPoderCalorificoCarbon / totalCantidadCArbones;

		}

		if (produccionClinkerHV != 0) {

			double kCal = (consumoCarboHV * poderCalorificoPromedio + humedadCrudo * 100d) / produccionClinkerHV;

			productogenerado.setKcalProductogenerado(kCal);
		} else {
			productogenerado.setKcalProductogenerado(0d);
		}
	}

	private Date obtenerUltimoDiaProduccionCrudoNegro() {
		String crudoNegro = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_CRUDO_NEGRO);
		return TablaOperacionQuerier.obtenerUltimoDiaProduccionCrudoNegro(crudoNegro);
	}

	/**
	 * Obtiene el porcentaje represato por el cabron (sin humedad) en el
	 * cmoponente crudo negro
	 * 
	 * @param fechaNotif Date
	 * @return double
	 */
	private double obtenerPorcentajeCarbonSecoCrudoNegro(Date fechaNotif) {
		String crudoNegro = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_CRUDO_NEGRO);
		List<Consumopuestotrabajo> componentesCrudoNegro = ConsumoPuestoTrabajoQuerier
				.obtenerConsumoComponentesPorFechaYProductoGenerado(fechaNotif, crudoNegro);

		double carbon = 0d;
		double consumoTotalCrudo = 0d;

		String tipoProdCombustible = ManejadorPropiedades.obtenerPropiedadPorClave(TIPO_CATEG_PRODUCTO_COMBUSTIBLE).toLowerCase();

		for (Consumopuestotrabajo consumopuestotrabajo : componentesCrudoNegro) {
			Producto componente = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente();
			double valorConsumoComponente = consumopuestotrabajo.getCantidadConsumopuestotrabajo().doubleValue();

			Tipocategoriaproducto tipocategoriaproducto = componente.getTipocategoriaproducto();
			if (tipocategoriaproducto != null
					&& tipocategoriaproducto.getNombreTipocategoriaproducto().toLowerCase().equals(tipoProdCombustible)) {
				carbon += valorConsumoComponente;
			}

			consumoTotalCrudo += valorConsumoComponente;
		}

		if (consumoTotalCrudo == 0d) {
			return 0d;
		}

		// Regla de tres para sacar el % de carbon
		double porcentajeCarbon = carbon * 100 / consumoTotalCrudo;

		return porcentajeCarbon;
	}

	/**
	 * Obtiene la cal rechazada que viene de otras plantas
	 * 
	 * @param fechaNotif Date
	 * @return double
	 */
	private double obtenerCalRechazada(Date fechaNotif) {
		// TODO Falta crear tabla donde se va a registrar este valor y hacer la
		// consulta (consultar con el usuario como se va a realizar el registro)
		return 0;
	}

	/**
	 * Obtiene el valor de una variable de calidad en un componente en un puesto
	 * de trabajo
	 * 
	 * @param consumopuestotrabajo Consumopuestotrabajo
	 * @param variableCalidadDesc String
	 * @return double
	 * @throws LogicaException
	 */
	private double obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(Consumopuestotrabajo consumopuestotrabajo,
			String variableCalidadDesc) throws LogicaException {
		try {
			return FactorconsumopuestotrabajoQuerier.obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
					variableCalidadDesc);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Obtiene el valor de una variable de calidad en un componente en un puesto
	 * de trabajo con respecto al ultimo dia de produccion del Crudo-Negro
	 * 
	 * @param consumopuestotrabajo Consumopuestotrabajo
	 * @param variableCalidadDesc String
	 * @return double
	 * @throws LogicaException
	 */
	private double obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(Consumopuestotrabajo consumopuestotrabajo,
			String variableCalidadDesc, Date ultimoDiaProduccion) throws LogicaException {
		try {
			return FactorconsumopuestotrabajoQuerier.obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(consumopuestotrabajo,
					variableCalidadDesc, ultimoDiaProduccion);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Toma los consumos de los componentes en puesto de trabajo, busca las
	 * variables de calidad y errores de balanza(factores de variacón)
	 * asociadas a dicho componente en ese puesto en el sistema SAC y afecta
	 * dicho consumo si corresponde
	 * 
	 * @param mapaConsumoComponentes Map<Long, Consumopuestotrabajo>
	 * @param productogenerado Productogenerado
	 * @param session Session
	 * @throws LogicaException
	 */
	private void transformarConsumosATmSecas(Map<Long, Consumopuestotrabajo> mapaConsumoComponentes,
			Productogenerado productogenerado, double horasOperacion, Session session) throws LogicaException {
		Set<Entry<Long, Consumopuestotrabajo>> entrySet = mapaConsumoComponentes.entrySet();
		for (Entry<Long, Consumopuestotrabajo> entry : entrySet) {
			Consumopuestotrabajo consumopuestotrabajo = entry.getValue();

			double consumoTmHumedas = consumopuestotrabajo.getCantidadHumedaConsumopuestotrabajo().doubleValue();
			Date fecha = productogenerado.getTablaoperacion().getFechaTablaoperacion();

			consumopuestotrabajo.setCantidadConsumopuestotrabajo(0d);
			session.save(consumopuestotrabajo);

			List<Equivalenciasccvarcalidad> variablesCalidad = EquivalenciasccvarcalidadQuerier
					.obtenerVariablesCalidadSegunPuestoYProduccion(consumopuestotrabajo);

			for (Equivalenciasccvarcalidad equivalenciasccvarcalidad : variablesCalidad) {
				double variableCalidadVal = 0d;
				String descripVarCalidad = equivalenciasccvarcalidad.getDescripVarCalidad();

				// Obtener el valor de la variable en SAC
				double variableCalidadValSAC = obtenerVariableCalidadPorPuestoTrabajo(fecha,
						equivalenciasccvarcalidad.getCodigoProcesoscc(), equivalenciasccvarcalidad.getCodigoProductoscc(),
						equivalenciasccvarcalidad.getCodigoPuestotrabajoscc(), descripVarCalidad);
				variableCalidadVal = 0d;

				// Si no hay valor de variable en SAC
				// Determinar si las horas de producción son menos que 4
				if (variableCalidadValSAC == 0d && horasOperacion < 4d) {
					// buscar ultimo promedio en SGCP

					variableCalidadValSAC = FactorconsumopuestotrabajoQuerier.obtenerUltimoFactorConsumoPorConsumoComponente(
							consumopuestotrabajo, equivalenciasccvarcalidad);
				}

				variableCalidadVal = variableCalidadValSAC;

				if (equivalenciasccvarcalidad.getAfectaConsumoEquivalenciasccvarcalidad()) {
					if (variableCalidadVal != 0d) {
						consumoTmHumedas = consumoTmHumedas * (1 - (variableCalidadVal / 100d));
					}
				}

				// se guarda el valor de la variable
				Factorconsumopuestotrabajo factor = new Factorconsumopuestotrabajo(consumopuestotrabajo,
						equivalenciasccvarcalidad, variableCalidadVal);
				session.save(factor);
			}

			Productovariablevariacion productoVariableV = ProductoVariableVariacionQuerier.obtenerSegunConsumoPuestoTrab(
					consumopuestotrabajo, session);

			double consumoTnSecas = 0d;

			if (productoVariableV != null) {
				Double errorBalanza = productoVariableV.getValorProductovariablevariacion();
				Factorvariacionpuestotrabajo factorvariacion = new Factorvariacionpuestotrabajo(consumopuestotrabajo,
						productoVariableV, errorBalanza);
				session.save(factorvariacion);

				if (errorBalanza != 0d) {
					consumoTnSecas = consumoTmHumedas * errorBalanza;
				} else {
					consumoTnSecas = consumoTmHumedas;
				}
			} else {
				consumoTnSecas = consumoTmHumedas;
			}

			consumoTnSecas = NumberUtil.reducirAdosDecimales(consumoTnSecas);

			consumopuestotrabajo.setCantidadConsumopuestotrabajo(consumoTnSecas);
			session.update(consumopuestotrabajo);
		}

	}

	/**
	 * Consulta en la BD si existe un registr de Produccion puesto de trabajo,
	 * para el parte diario y puesto de trabajo especificados si no existe crea
	 * un nuevo valor
	 * 
	 * @param partediario Partediario
	 * @param puestoTrabajo Puestotrabajo
	 * @param session
	 * @return Produccionpuestotrabajo
	 * @throws ElementoNoEncontradoException
	 */
	private Produccionpuestotrabajo obtenerProduccionPuestoTrabajo(Partediario partediario, Puestotrabajo puestoTrabajo,
			Session session) throws ElementoNoEncontradoException {
		Produccionpuestotrabajo produccionPuestoTrabajo = ProduccionPuestoTrabajoQuerier.obtenerProduccionPuestoTrabajo(
				partediario, puestoTrabajo);

		if (produccionPuestoTrabajo == null) {
			produccionPuestoTrabajo = new Produccionpuestotrabajo(puestoTrabajo, partediario);
			session.save(produccionPuestoTrabajo);
		}
		return produccionPuestoTrabajo;
	}

	/**
	 * Consulta en la BD si existe un registro de Tabla operacion, para la
	 * Produccionpuestotrabajoo y fecha especificados si no existe crea un nuevo
	 * valor
	 * 
	 * @param produccionPuestoTrabajo Produccionpuestotrabajo
	 * @param fecha Date
	 * @param session
	 * @return Tablaoperacion
	 * @throws AplicacionException
	 * @throws EntornoEjecucionException
	 * @throws
	 * @throws ElementoNoEncontradoException
	 */
	private Tablaoperacion obtenerTablaOperacion(Produccionpuestotrabajo produccionPuestoTrabajo, Date fecha, Session session)
			throws EntornoEjecucionException, ElementoNoEncontradoException {

		Tablaoperacion tablaoperacion = TablaOperacionQuerier.obtenerPorPuestoTrabajoyFecha(
				produccionPuestoTrabajo.getPkCodigoProduccionpuestotraba(), fecha);

		if (tablaoperacion == null) {
			tablaoperacion = new Tablaoperacion(produccionPuestoTrabajo, fecha, 0d, 0d);
			session.save(tablaoperacion);
		}
		return tablaoperacion;
	}

	/**
	 * Toma la lista de componentes pasados en el set, los va incuyendo en el
	 * mapa, si ya existen el mapa solo actualiza el valor de las toneladas,
	 * luego toma el mapa obtiene las humedades y transforma los valores a tm
	 * secas
	 * 
	 * @param mapaConsumoPorComponentes
	 * @param listaComponentes
	 * @param productogenerado
	 * @param session
	 * @throws LogicaException
	 */

	private void totalizarConsumoComponentes(Map<Long, Consumopuestotrabajo> mapaConsumoPorComponentes, Set listaComponentes,
			Productogenerado productogenerado) {

		for (Iterator<Componentenotificacion> ittComponentes = listaComponentes.iterator(); ittComponentes.hasNext();) {
			Componentenotificacion componentenotificacion = ittComponentes.next();

			Componente componente = componentenotificacion.getComponente();

			Long key = componente.getPkCodigoComponente();
			Consumopuestotrabajo consumopuestotrabajo = mapaConsumoPorComponentes.get(key);
			double valorConsumo = componentenotificacion.getValorComponentenotificacion().doubleValue();

			Double valorConsumoEsCalentamiento = 0d;

			String petroleoBunker = ManejadorPropiedades.obtenerPropiedadPorClave(PRODUCTO_PETROLEOBUNKER).toLowerCase();
			String petroleoBunkerCAL = ManejadorPropiedades.obtenerPropiedadPorClave("producto.petroleoBunkerCAL").toLowerCase();
			String nombreComponente = componente.getProductoByFkCodigoProductoComponente().getNombreProducto().toLowerCase();

			if (nombreComponente.equals(petroleoBunker) || nombreComponente.equals(petroleoBunkerCAL)) {
				// transformacion a galones
				valorConsumo = valorConsumo / FACTOR_GALONES;
			}

			if (componentenotificacion.getNotificacionproduccion() != null) {
				if (componentenotificacion.getNotificacionproduccion().getEscalentamientoNotificacionpr() != null) {
					if (componentenotificacion.getNotificacionproduccion().getEscalentamientoNotificacionpr()) {
						valorConsumoEsCalentamiento += valorConsumo;
					}
				}
			}

			if (consumopuestotrabajo == null) {
				consumopuestotrabajo = new Consumopuestotrabajo(productogenerado, componente, 0d);
				consumopuestotrabajo.setCantidadHumedaConsumopuestotrabajo(valorConsumo);
				consumopuestotrabajo.setCantidadCalentamientoConsumopuestotrabajo(valorConsumoEsCalentamiento);
				mapaConsumoPorComponentes.put(key, consumopuestotrabajo);
			} else {
				Double valor = consumopuestotrabajo.getCantidadHumedaConsumopuestotrabajo().doubleValue() + valorConsumo;
				consumopuestotrabajo.setCantidadHumedaConsumopuestotrabajo(valor);
				consumopuestotrabajo.setCantidadCalentamientoConsumopuestotrabajo(valorConsumoEsCalentamiento);
			}
		}
	}

	/**
	 * Obtiene el factor de humedad deun componente en un puesto de trabajo
	 * 
	 * @param long1
	 * @param long2
	 * @param long3
	 * @param fecha
	 * @return
	 * @throws LogicaException
	 */
	private double obtenerVariableCalidadPorPuestoTrabajo(Date fecha, Long codigoProcesoScc, Long codigoProductoScc,
			Long codSccPuestotrabajo, String variableCalDesc) throws LogicaException {
		try {
			double varCaliadd = operacionesFacade.obtenerVariableCalidadSacPorPuestoTrabajo(fecha, codigoProcesoScc,
					codigoProductoScc, codSccPuestotrabajo, variableCalDesc);
			return varCaliadd;
		} catch (IntegracionException e) {
			throw new LogicaException(e.getMensaje(), e);
		}
	}

	/**
	 * Actualiza la tabla operacion cuando se crea un nuevo producto generado
	 * 
	 * @param tablaoperacion
	 * @param productogenerado
	 * @param session
	 */
	private void actualizarTablaOperacion(Tablaoperacion tablaoperacion, Productogenerado productogenerado, Session session) {
		Double totalHoraTablaoperacion = tablaoperacion.getTotalHoraTablaoperacion() == null ? 0d : tablaoperacion
				.getTotalHoraTablaoperacion();
		Double totalTmTablaoperacion = tablaoperacion.getTotalTmTablaoperacion() == null ? 0d : tablaoperacion
				.getTotalTmTablaoperacion();

		Double horasProductogenerado = productogenerado.getHorasProductogenerado();
		Double produccionTmProductogenerado = productogenerado.getProduccionTmProductogenerado();

		tablaoperacion.setTotalHoraTablaoperacion(totalHoraTablaoperacion + horasProductogenerado);
		tablaoperacion.setTotalTmTablaoperacion(totalTmTablaoperacion + produccionTmProductogenerado);

		session.update(tablaoperacion);
	}

	// TODO: se puede refactorizar este metodo.
	public ByteArrayOutputStream generarReporteDetalleParteDiario(Long codigoProceso, Long codigoProducto, Integer anio,
			Short mes, Long division, Long sociedad, Long unidad, String rutaimg) throws LogicaException, DocumentException {

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

		Proceso proceso = null;
		Producto producto = null;
		String nombreProceso = "Todos";
		String nombreProducto = "Todos";
		String nombreDivision = "";
		String nombreSociedad = "";
		String nombreUnidad = "";

		List<Tablakardex> kardexParteDiario = null;
		Document document = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		Rectangle rct = new Rectangle(36, 54, 559, 788);
		writer.setBoxSize("trim", rct);

		HeaderFooter event = new HeaderFooter();
		writer.setPageEvent(event);

		document.open();
		try {

			if (codigoProceso != null) {
				try {
					proceso = ProcesoQuerier.getById(codigoProceso);
				} catch (ElementoNoEncontradoException e) {
					logger.error(e.getMensaje());
				}
			}
			if (codigoProducto != null) {
				try {
					producto = ProductoQuerier.getById(codigoProducto);
				} catch (ElementoNoEncontradoException e) {
					logger.error(e.getMensaje());
				}
			}

			if (proceso != null) {
				nombreProceso = proceso.getNombreProceso();
				if (nombreProceso.equals(CLINKERIZACION_HH.toUpperCase())) {
					nombreProceso = CLINKERIZACION;
				}
			}
			if (producto != null) {
				nombreProducto = producto.getDescripcionProducto();
			}

			try {
				nombreDivision = DivisionQuerier.getById(division).getNombreDivision();
				nombreSociedad = SociedadQuerier.getById(sociedad).getNombreSociedad();
				nombreUnidad = UnidadQuerier.getById(unidad).getNombreUnidad();
			} catch (SesionVencidaException e) {
				logger.error(e.getMensaje());
			} catch (ElementoNoEncontradoException e) {
				logger.error(e.getMensaje());
			} catch (EntornoEjecucionException e) {
				logger.error(e.getMensaje());
			}
			rutaimg = rutaimg + "logo.jpg";

			String titulo = ManejadorPropiedades.obtenerPropiedadPorClave(PDFFactory.TITULO_REPORTE_DETALLE_PARTE_DIARIO);
			document.add(PDFFactory.crearTituloYsubTitulo(titulo, nombreDivision, nombreSociedad, nombreUnidad, nombreProceso,
					nombreProducto, mes, rutaimg));

			boolean esMateriaPrima = false;
			if (producto != null) {
				try {
					esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(producto.getPkCodigoProducto());
				} catch (SesionVencidaException e) {
					logger.error(e.getMensaje());
				} catch (ElementoNoEncontradoException e) {
					logger.error(e.getMensaje());
				} catch (EntornoEjecucionException e) {
					logger.error(e.getMensaje());
				}

			}

			if (esMateriaPrima) {
				kardexParteDiario = TablaKardexQuerier.obtenerTablaKardexPorProcesoProducto(null, codigoProducto, anio, mes,
						esMateriaPrima);
			} else {
				kardexParteDiario = TablaKardexQuerier.obtenerTablaKardexPorProcesoProducto(codigoProceso, codigoProducto, anio,
						mes, false);
			}

			try {
				Map<String, Map<Long, Double>> listaFactorCalidadHumedad = FactorconsumopuestotrabajoQuerier
						.obtenerPromedioFactorVariableCalidadPorPeriodoContable(null, unidad, anio, mes, "Humedad");

				List<Produccion> producciones = ProduccionQuerier.getByProcesoYProducto(codigoProceso, codigoProducto);

				for (int i = 0; i < producciones.size(); i++) {
					if (producciones.get(i).getProceso().getNombreProceso().equals(CLINKERIZACION_HV.toUpperCase())
							&& producciones.get(i).getProducto().getNombreProducto().equals(PRODUCTO_CLKI)) {
						producciones.remove(i);
					}
				}

				for (Produccion pro : producciones) {
					PdfPTable table = PDFFactory.crearCabeceraYDetalle(pro.getProducto(), pro.getProceso().getPkCodigoProceso(),
							kardexParteDiario, listaFactorCalidadHumedad, pro.getProceso().getLineanegocio()
									.getPkCodigoLineanegocio());
					if (table != null) {
						document.add(table);
						document.newPage();
					}

				}

			} catch (AplicacionException e) {
				logger.error(e.getMensaje());
			}
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		document.close();
		return baos;
	}

	static class HeaderFooter extends PdfPageEventHelper {
		public void onEndPage(PdfWriter writer, Document document) {
			Rectangle rect = writer.getBoxSize("trim");
			// PIE DE PAGINA
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
					new Phrase(String.format("Pagina " + writer.getPageNumber()), PDFFactory.fontPiePagina),
					((rect.getLeft() + rect.getRight()) / 2) + 480, rect.getBottom() - 38, 0);
		}
	}

	public ByteArrayOutputStream generarReporteDetalleParteDiarioEXCEL(Long codigoProceso, Long codigoProducto, Integer anio,
			Short mes, Long division, Long sociedad, Long unidad, String rutaImg) throws LogicaException, DocumentException {

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
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			Proceso proceso = null;
			Producto producto = null;
			String nombreProceso = "Todos";
			String nombreProducto = "Todos";
			String nombreDivision = "";
			String nombreSociedad = "";
			String nombreUnidad = "";
			String nombretitulo = "";
			HSSFWorkbook libroXLS = null;
			List<Tablakardex> kardexParteDiario = null;
			XLSFactory xlsFactory = new XLSFactory();

			if (codigoProceso != null) {
				proceso = ProcesoQuerier.getById(codigoProceso);
			}
			if (codigoProducto != null) {
				producto = ProductoQuerier.getById(codigoProducto);
			}

			if (proceso != null) {
				nombreProceso = proceso.getNombreProceso();
				if (nombreProceso.equals(CLINKERIZACION_HH.toUpperCase())) {
					nombreProceso = CLINKERIZACION;
				}
			}
			if (producto != null) {
				nombreProducto = producto.getDescripcionProducto();
			}

			nombreDivision = DivisionQuerier.getById(division).getNombreDivision();
			nombreSociedad = SociedadQuerier.getById(sociedad).getNombreSociedad();
			nombreUnidad = UnidadQuerier.getById(unidad).getNombreUnidad();
			nombretitulo = ManejadorPropiedades.obtenerPropiedadPorClave(XLSFactory.TITULO_REPORTE_DETALLE_PARTE_DIARIO);

			boolean esMateriaPrima = false;
			if (producto != null) {
				esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(producto.getPkCodigoProducto());
			}

			if (esMateriaPrima) {
				kardexParteDiario = TablaKardexQuerier.obtenerTablaKardexPorProcesoProducto(codigoProceso, codigoProducto, anio,
						mes, esMateriaPrima);
			} else {
				kardexParteDiario = TablaKardexQuerier.obtenerTablaKardexPorProcesoProducto(codigoProceso, codigoProducto, anio,
						mes, false);
			}

			List<Produccion> producciones = ProduccionQuerier.getByProcesoYProducto(codigoProceso, codigoProducto);

			for (int i = 0; i < producciones.size(); i++) {
				if (producciones.get(i).getProceso().getNombreProceso().equals(CLINKERIZACION_HV.toUpperCase())
						&& producciones.get(i).getProducto().getNombreProducto().equals(PRODUCTO_CLKI)) {
					producciones.remove(i);
				}
			}

			libroXLS = xlsFactory.crearReporteDetPT(kardexParteDiario, nombretitulo, nombreDivision, nombreSociedad,
					nombreUnidad, nombreProceso, nombreProducto, mes, producciones, rutaImg, anio);

			libroXLS.write(baos);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
		} catch (SesionVencidaException e) {
			logger.error(e.getMensaje());
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return baos;
	}

	/**
	 * Agregado por Fabian Geldres
	 * 
	 * @throws IntegradorExcepcion
	 * @throws ParseException
	 * @throws IntegradorExcepcion
	 */
	public List<ReporteVariableCalidadBean> obtenerVarCalidadProcesoPuestoTrabajo(Long codigoProceso, Long codigoPuestoTrabajo,
			String fechaInicio, String fechaFin) throws LogicaException {

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

		List<ReporteVariableCalidadBean> variableCalidad = null;

		try {
			List<Equivalenciasccvarcalidad> equiVarCalidad = EquivalenciasccvarcalidadQuerier
					.obtenerVariablesCalidadPuestoYProceso(codigoPuestoTrabajo, codigoProceso);

			DateFormat df = new SimpleDateFormat(FechaUtil.PATRON_FECHA_APLICACION);

			variableCalidad = obtenerReporteVarSccCalidad(equiVarCalidad);
			OperacionesFacade operaciones = new OperacionesImpl();

			for (ReporteVariableCalidadBean equival : variableCalidad) {
				for (ReporteVarCalidadPuestoBean puesto : equival.getReposteVariableCalidadPuestoTrabajo()) {
					for (ReporteVarCalidadProductoBean producto : puesto.getProductos()) {
						for (ReporteVarCalidadProductoComponenteBean componente : producto.getProductoComponente()) {
							for (ReporteEquivalenciasccvarcalidadBean equivalencia : componente.getComponenteDescripcion()) {
								Equivalenciasccvarcalidad v = equivalencia.getDatoCalidad();
								List<Object[]> varcalidad;
								varcalidad = operaciones.obtenerPromedioVarCalidadRangoFechasReporte(df.parse(fechaInicio),
										df.parse(fechaFin), v.getCodigoProcesoscc(), v.getCodigoPuestotrabajoscc(),
										v.getCodigoProductoscc(), v.getDescripVarCalidad());
								for (int j = 0; j < varcalidad.size(); j++) {

									Integer dia = FechaUtil.devolverDiaSAC(varcalidad.get(j)[2]);
									equivalencia.getVarCalDia()[dia - 1] = varcalidad.get(j)[4] + "";

								}
							}
						}

					}

				}
			}
		}

		catch (IntegradorExcepcion e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (ParseException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return variableCalidad;
	}

	private List<ReporteVariableCalidadBean> obtenerReporteVarSccCalidad(List<Equivalenciasccvarcalidad> variableCalidad) {
		// Nivel 1 Extraer los Procesos del la lista de variables calidad
		List<Proceso> procesos = listaProceso(variableCalidad);
		List<ReporteVariableCalidadBean> reporte1 = new ArrayList<ReporteVariableCalidadBean>();
		ReporteVariableCalidadBean reporteProc;

		// ORDENAR PROCESOS
		Collections.sort(procesos, new Comparator<Proceso>() {
			public int compare(Proceso p1, Proceso p2) {
				int comp = p1.getOrdenEjecucionProceso().compareTo(p2.getOrdenEjecucionProceso());
				return comp;
			}
		});

		for (Proceso proceso : procesos) {
			List<Equivalenciasccvarcalidad> variablesCalPorProceso = obtenerVarCalidadPorProceso(variableCalidad, proceso);

			List<Puestotrabajo> puestoTrabajo = listaPuestoTrabajo(variablesCalPorProceso);

			// ORDENAR PUESTO TRABAJOS
			Collections.sort(puestoTrabajo, new Comparator<Puestotrabajo>() {
				public int compare(Puestotrabajo p1, Puestotrabajo p2) {
					int comp = p1.getDescripcionPuestotrabajo().compareTo(p2.getDescripcionPuestotrabajo());
					return comp;
				}

			});
			reporteProc = new ReporteVariableCalidadBean();
			reporteProc.setProceso(proceso);

			List<ReporteVarCalidadPuestoBean> listaPuesto = new ArrayList<ReporteVarCalidadPuestoBean>();
			for (Puestotrabajo puestotrabajo2 : puestoTrabajo) {

				ReporteVarCalidadPuestoBean repor = new ReporteVarCalidadPuestoBean();
				repor.setPuestotrabajo(puestotrabajo2);
				List<Producto> productos = listaProductoPorPuestoTrabajo(puestotrabajo2, variablesCalPorProceso);
				List<Equivalenciasccvarcalidad> variablesCalPorProceso2 = obtenerVarCalidadPorPuestoTrabajo(
						variablesCalPorProceso, puestotrabajo2);

				ReporteVarCalidadProductoBean rvarCalProduc;
				List<ReporteVarCalidadProductoBean> listaProducto = new ArrayList<ReporteVarCalidadProductoBean>();

				for (Producto producto : productos) {

					rvarCalProduc = new ReporteVarCalidadProductoBean();
					rvarCalProduc.setProducto(producto);
					List<Componente> componentes = listaProductoPorProducto(producto, variablesCalPorProceso2);
					ReporteVarCalidadProductoComponenteBean rvarpcom;
					List<ReporteVarCalidadProductoComponenteBean> listaComp = new ArrayList<ReporteVarCalidadProductoComponenteBean>();
					for (Componente componente : componentes) {
						rvarpcom = new ReporteVarCalidadProductoComponenteBean();
						rvarpcom.setComponente(componente);
						List<Equivalenciasccvarcalidad> equivalencia = listaEquivalencias(componente, variablesCalPorProceso2);
						ReporteEquivalenciasccvarcalidadBean resccvar;
						List<ReporteEquivalenciasccvarcalidadBean> listaEqui = new ArrayList<ReporteEquivalenciasccvarcalidadBean>();
						for (Equivalenciasccvarcalidad equivalenciasccvarcalidad : equivalencia) {
							resccvar = new ReporteEquivalenciasccvarcalidadBean();
							resccvar.setDatoCalidad(equivalenciasccvarcalidad);
							listaEqui.add(resccvar);
						}
						rvarpcom.setComponenteDescripcion(listaEqui);
						listaComp.add(rvarpcom);

					}

					rvarCalProduc.setProductoComponente(listaComp);
					listaProducto.add(rvarCalProduc);
				}

				repor.setProductos(listaProducto);
				listaPuesto.add(repor);
			}
			reporteProc.setReposteVariableCalidadPuestoTrabajo(listaPuesto);
			reporte1.add(reporteProc);

		}
		return reporte1;
	}

	private List<Equivalenciasccvarcalidad> obtenerVarCalidadPorProceso(List<Equivalenciasccvarcalidad> variableCalidad,
			Proceso proceso) {
		List<Equivalenciasccvarcalidad> varCalidad = new ArrayList<Equivalenciasccvarcalidad>();
		for (Equivalenciasccvarcalidad equival : variableCalidad) {
			if (NumberUtil.verificarIgualdad(equival.getProceso().getPkCodigoProceso(), proceso.getPkCodigoProceso())) {
				varCalidad.add(equival);
			}
		}
		return varCalidad;
	}

	private List<Equivalenciasccvarcalidad> obtenerVarCalidadPorPuestoTrabajo(List<Equivalenciasccvarcalidad> variableCalidad,
			Puestotrabajo puestoTrabajo) {
		List<Equivalenciasccvarcalidad> varCalidad = new ArrayList<Equivalenciasccvarcalidad>();
		for (Equivalenciasccvarcalidad equival : variableCalidad) {
			if (NumberUtil.verificarIgualdad(equival.getPuestotrabajo().getPkCodigoPuestotrabajo(),
					puestoTrabajo.getPkCodigoPuestotrabajo())) {
				varCalidad.add(equival);
			}
		}
		return varCalidad;
	}

	/**
	 * @param puestotrabajo2
	 * @param proceso
	 * @param variableCalidad
	 * @return
	 */
	private List<Producto> listaProductoPorPuestoTrabajo(Puestotrabajo puestotrabajo2,
			List<Equivalenciasccvarcalidad> variableCalidad) {
		List<Producto> productos = new ArrayList<Producto>();
		for (Equivalenciasccvarcalidad equival : variableCalidad) {
			// Valido por el proceso y el puesto de trabajo
			if (NumberUtil.verificarIgualdad(equival.getPuestotrabajo().getPkCodigoPuestotrabajo(),
					puestotrabajo2.getPkCodigoPuestotrabajo())) {
				if (!verificarSiObjetoExiste(productos, equival.getComponente().getProductoByFkCodigoProducto(),
						"pkCodigoProducto")) {
					productos.add(equival.getComponente().getProductoByFkCodigoProducto());

				}
			}

		}
		return productos;
	}

	/**
	 * @param producto
	 * @param variableCalidad
	 * @return
	 */
	private List<Componente> listaProductoPorProducto(Producto producto, List<Equivalenciasccvarcalidad> variableCalidad) {
		List<Componente> componentes = new ArrayList<Componente>();
		for (Equivalenciasccvarcalidad equival : variableCalidad) {
			// Valido por el proceso y el puesto de trabajo
			if (NumberUtil.verificarIgualdad(equival.getComponente().getProductoByFkCodigoProducto().getPkCodigoProducto(),
					producto.getPkCodigoProducto())) {
				if (!verificarSiObjetoExiste(componentes, equival.getComponente(), "pkCodigoComponente")) {
					componentes.add(equival.getComponente());

				}
			}

		}
		return componentes;
	}

	/**
	 * @param componente
	 * @param variableCalidad
	 * @return
	 */
	private List<Equivalenciasccvarcalidad> listaEquivalencias(Componente componente,
			List<Equivalenciasccvarcalidad> variableCalidad) {
		List<Equivalenciasccvarcalidad> varCalidad = new ArrayList<Equivalenciasccvarcalidad>();
		for (Equivalenciasccvarcalidad equival : variableCalidad) {
			if (NumberUtil.verificarIgualdad(equival.getComponente().getPkCodigoComponente(), componente.getPkCodigoComponente())) {
				if (!verificarSiObjetoExiste(varCalidad, equival, "pkCodigoEquiscccalidadscc")) {
					varCalidad.add(equival);

				}
			}

		}
		return varCalidad;
	}

	/**
	 * Agregado por FabianGeldres Este metodo obtiene la lista de procesos sin
	 * repetir
	 * 
	 * @param variableCalidad
	 * @return
	 */
	private List<Proceso> listaProceso(List<Equivalenciasccvarcalidad> variableCalidad) {
		List<Proceso> procesos = new ArrayList<Proceso>();
		for (Equivalenciasccvarcalidad equivalenciasccvarcalidad : variableCalidad) {
			if (!verificarProcesoExiste(equivalenciasccvarcalidad.getProceso(), procesos)) {
				procesos.add(equivalenciasccvarcalidad.getProceso());
			}
		}

		return procesos;
	}

	/**
	 * Agregado por Fabian Geldres Este metodo te obtiene la lista de puesto de
	 * trabajo segun un proceso
	 * 
	 * @param variableCalidad
	 * @param proceso
	 * @return
	 */
	private List<Puestotrabajo> listaPuestoTrabajo(List<Equivalenciasccvarcalidad> variableCalidad) {
		List<Puestotrabajo> puestoTrabajos = new ArrayList<Puestotrabajo>();
		for (Equivalenciasccvarcalidad equivalenciasccvarcalidad : variableCalidad) {
			if (!verificarSiObjetoExiste(puestoTrabajos, equivalenciasccvarcalidad.getPuestotrabajo(), "pkCodigoPuestotrabajo")) {
				puestoTrabajos.add(equivalenciasccvarcalidad.getPuestotrabajo());
			}

		}

		return puestoTrabajos;
	}

	/**
	 * Agregado por Fabian Geldres Verefica si el proceso existe dentro de la
	 * lista
	 * 
	 * @param proceso
	 * @param procesos
	 * @return
	 */
	@Deprecated
	private boolean verificarProcesoExiste(Proceso proceso, List<Proceso> procesos) {
		for (Proceso proceso2 : procesos) {
			if (NumberUtil.verificarIgualdad(proceso2.getPkCodigoProceso(), proceso.getPkCodigoProceso())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo Generico para verificar si el objeto existe en la lista la
	 * condicion que la lista y el objeto deban de ser del mismo tipo de clase
	 * 
	 * @param lista
	 * @param objeto
	 * @param atributo
	 * @return
	 */
	private boolean verificarSiObjetoExiste(List<?> lista, Object objeto, String atributo) {
		try {
			for (Object object : lista) {
				String codigo1 = BeanUtils.getSimpleProperty(object, atributo);
				String codigo2 = BeanUtils.getSimpleProperty(objeto, atributo);
				if (codigo1.equals(codigo2)) {
					return true;
				}
			}
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
		}
		return false;
	}





}
