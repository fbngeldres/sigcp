package pe.com.pacasmayo.sgcp.util;

//import com.pacasmayo.rendiciones.model.vo.ProveedorVO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.IndexedColors;

import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoConsumoComponentesBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.impl.ProductoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.GenerarParteTecnicoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MedicionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MovimientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaKardexLogicFacade;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.GenerarParteTecnicoLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.TablaKardexLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MedicionLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.querier.EquivalenciasccvarcalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorconsumopuestotrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedicionDiariaDTO;

public class XLSFactory implements ConstantesLogicaNegocio {

	// Objetos Apache-POI para conversion a Excel
	private HSSFWorkbook libroXLS = null;
	private HSSFSheet hojaXLS = null;
	private HSSFRow filaXLS = null;
	private static Log logger = LogFactory.getLog(XLSFactory.class.getCanonicalName());
	private static MedicionLogicFacade medicionLogic = new MedicionLogic();
	private HSSFCell celdaXLS = null;
	private static TablaKardexLogicFacade tablaKardexLogic = new TablaKardexLogic();
	private HSSFFont fuenteTit = null;
	private HSSFCellStyle estiloTit = null;
	private HSSFFont fuenteDat = null;
	private HSSFFont fuenteNegrita = null;
	private HSSFFont fuenteCabTab = null;
	private HSSFFont fuenteTitPT = null;
	private HSSFFont fuenteNombrePro = null;
	private HSSFCellStyle estiloDat = null;
	private static GenerarParteTecnicoLogicFacade generarParteTecnicoLogic = new GenerarParteTecnicoLogic();
	public static final String CODIGO_PROCESOS_COLUMNAS = "codigos.procesos.stockfis.desviacion";
	public static final String CODIGO_VARIABLE_CALIDAD_HUMEDAD = "codigo.variable.calidad.humedad";
	public static String TITULO_REPORTE_DETALLE_PARTE_DIARIO = "reporte.dinamico.titulo.parte.diario.detalle";
	public static String DIVISION_LABEL = "Division :";
	public static String SOCIEDAD_LABEL = "Sociedad :";
	public static String UNIDAD_LABEL = "Unidad :";
	public static String PROCESO_LABEL = "Proceso";
	public static String PRODUCTO_LABEL = "Producto";
	public static String MES_LABEL = "Mes :";
	public static String FECHA_CREACION_LABEL = "Creacion :";
	private static Long codigoClinker = Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLINKER1));
	private static Long codigoProcesoClinkerHH = Long.valueOf(ManejadorPropiedades
			.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HH));
	private static Long codigoProcesoClinkerHV = Long.valueOf(ManejadorPropiedades
			.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HV));
	private static MovimientoLogicFacade movimientoLogicFacade = new MovimientoLogic();

	public XLSFactory() {
		this.libroXLS = new HSSFWorkbook();
		this.creaEstiloTitulo();
		this.creaEstiloDatos();

	}

	public XLSFactory(InputStream inputStream) throws IOException {
		this.libroXLS = new HSSFWorkbook(inputStream, true);
	}

	public HSSFWorkbook creaLibro(ArrayList cabecera, ArrayList data, String[] propiedades) {
		return creaLibro(cabecera, data, propiedades, "Hoja1");
	}

	public HSSFWorkbook creaLibroReporteDensidades(ArrayList cabecera, ArrayList data, String[] propiedades) {
		return creaLibroDensidades(cabecera, data, propiedades, "Hoja1");
	}

	public HSSFWorkbook creaLibro(ArrayList cabecera, ArrayList data, String[] propiedades, String nombreHoja) {
		hojaXLS = libroXLS.createSheet(nombreHoja);
		creaCabecera(cabecera);
		creaDatos(data, propiedades);
		return libroXLS;
	}

	public HSSFWorkbook creaLibroDensidades(ArrayList cabecera, ArrayList data, String[] propiedades, String nombreHoja) {
		hojaXLS = libroXLS.createSheet(nombreHoja);
		creaCabeceraDensidades(cabecera);

		return libroXLS;
	}

	
	/**
	 * Crea la cabecera de este documento XLS; param cabecera ArrayList
	 * conteniendo los datos de la cabecera return Nada
	 */
	private void creaCabecera(ArrayList cabecera) {
		filaXLS = hojaXLS.createRow(0);
		for (short i = 0; i < cabecera.size(); i++) {

			celdaXLS = filaXLS.createCell(i);
			celdaXLS.setCellStyle(estiloTit);
			celdaXLS.setCellValue(cabecera.get(i).toString());
		}
	}

	private void creaCabeceraDensidades(ArrayList cabecera) {
		filaXLS = hojaXLS.createRow(0);
		filaXLS = hojaXLS.createRow(1);

		short pos = 1;
		celdaXLS = filaXLS.createCell(pos);

		celdaXLS.setCellStyle(estiloTit);
		celdaXLS.setCellValue("Promedio con Igual Rock Type:");

		filaXLS = hojaXLS.createRow(2);
		filaXLS = hojaXLS.createRow(3);
		filaXLS = hojaXLS.createRow(4);

		for (short i = 0; i < cabecera.size(); i++) {

			celdaXLS = filaXLS.createCell(i);
			celdaXLS.setCellStyle(estiloTit);
			celdaXLS.setCellValue(cabecera.get(i).toString());
		}
	}

	private void creaCabeceraAssays(ArrayList cabecera) {
		filaXLS = hojaXLS.createRow(0);
		for (short i = 0; i < cabecera.size(); i++) {

			celdaXLS = filaXLS.createCell(i);
			celdaXLS.setCellStyle(estiloTit);
			celdaXLS.setCellValue(cabecera.get(i).toString());
		}
	}

	/**
	 * Crea la cabecera de este documento XLS; param cabecera ArrayList
	 * conteniendo los datos de la cabecera return Nada
	 */
	private void creaDatos(ArrayList datos, String[] propiedades) {

		if (datos != null && datos.size() > 0) {

			for (short i = 0; i < datos.size(); i++) {
				filaXLS = hojaXLS.createRow(i + 1);
				celdaXLS = filaXLS.createCell((short) 0);

				celdaXLS.setCellStyle(estiloDat);

				HSSFCellStyle estiloCelda = libroXLS.createCellStyle();

				estiloCelda.setWrapText(true);
				estiloCelda.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				estiloCelda.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
				estiloCelda.setFont(fuenteDat);

				for (int j = 0; j < propiedades.length; j++) {

					hojaXLS.setColumnWidth((short) (j), (short) 8500);

					celdaXLS = filaXLS.createCell((short) (j));

					celdaXLS.setCellStyle(estiloCelda);

				}

			}

		}

	}

	
	
	public Double getVentasRelacionadas(Double ventasATerceros, Double ventasRelacionadas, Double ventas) {

		Double total = ventasATerceros + ventasRelacionadas;

		Double porcentaje = (ventasRelacionadas / total);

		return NumberUtil.Redondear2Decimales(ventas * porcentaje);
	}

	public Double getVentasATerceros(Double ventasATerceros, Double ventasRelacionadas, Double ventas) {

		Double total = ventasATerceros + ventasRelacionadas;

		Double porcentaje = (ventasATerceros / total);

		return NumberUtil.Redondear2Decimales(ventas * porcentaje);
	}

	

	private String validar(Object valor) {
		if (valor != null) {
			return valor.toString();
		}
		return "";
	}

	/**
	 * Asigna el valor correpondiente a la celda POI deacuerdo al tipo de datos
	 * del objeto entrante param object Objeto con el tipo de datos desconocido
	 * celdaXLS celda a la cual se le va a asignar el valor del objeto return
	 * Nada
	 */

	public String NumFormat(Double numero) {
		Locale.setDefault(Locale.CANADA);
		DecimalFormat df2 = new DecimalFormat("#.##");

		return df2.format(numero);
	}

	/**
	 * Asigna un estilo de fuente POI para los titulos return Nada
	 */
	private void creaEstiloTitulo() {
		fuenteTit = libroXLS.createFont();
		estiloTit = libroXLS.createCellStyle();
		fuenteTit.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteTit.setFontHeight((short) 160);
		fuenteTit.setFontName("Arial");
		estiloTit.setFont(fuenteTit);
		estiloTit.setFillForegroundColor((short) 22);
		estiloTit.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloTit.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloTit.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloTit.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloTit.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloTit.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloTit.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	}

	/**
	 * Asigna un estilo de fuente POI para los datos return Nada
	 */
	private void creaEstiloDatos() {
		fuenteDat = libroXLS.createFont();
		estiloDat = libroXLS.createCellStyle();
		fuenteDat.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fuenteDat.setFontHeight((short) 160);
		fuenteDat.setFontName("Arial");
		estiloDat.setFont(fuenteDat);
		estiloDat.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloDat.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloDat.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloDat.setBorderTop(HSSFCellStyle.BORDER_THIN);
	}

	private void crearCelda(int col, int width, String valor, HSSFCellStyle estilo) {
		celdaXLS = filaXLS.createCell(col);
		hojaXLS.setColumnWidth(col, width);
		celdaXLS.setCellStyle(estilo);
		celdaXLS.setCellValue(valor);

	}

	private void combinarCelda(int filaIni, int filaFin, int colIni, int colFin) {
		hojaXLS.addMergedRegion(new CellRangeAddress(filaIni, filaFin, colIni, colFin));
	}

	// Metodo que permite insertar imagenes a el excel
	private static int loadPicture(File ruta, HSSFWorkbook wb) throws IOException {
		int pictureIndex;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			// read in the image file
			fis = new FileInputStream(ruta);
			bos = new ByteArrayOutputStream();
			int c;
			// copy the image bytes into the ByteArrayOutputStream
			while ((c = fis.read()) != -1)
				bos.write(c);

			// add the image bytes to the workbook
			pictureIndex = wb.addPicture(bos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG);

		} finally {
			if (fis != null)
				fis.close();
			if (bos != null)
				bos.close();
		}
		return pictureIndex;
	}

	// CABECERA DEL REPORTE PARTE DIARIO - EXCEL
	public void crearReporteDetPTCabecera(String titulo, String divisionCab, String sociedadCab, String unidadCab,
			String procesoCab, String productoCab, Short mes, String rutaImg) throws AplicacionException, IOException {

		HSSFCellStyle estiloCeldaTitulos = libroXLS.createCellStyle();
		estiloCeldaTitulos.setWrapText(true);
		estiloCeldaTitulos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		estiloCeldaTitulos.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloCeldaTitulos.setFont(fuenteTit);

		HSSFCellStyle estiloTituloPT = libroXLS.createCellStyle();
		estiloTituloPT.setWrapText(true);
		estiloTituloPT.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloTituloPT.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fuenteTitPT = libroXLS.createFont();
		fuenteTitPT.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteTitPT.setFontHeight((short) 280);
		fuenteTitPT.setFontName("Arial");
		estiloTituloPT.setFont(fuenteTitPT);

		HSSFPatriarch patriarch = hojaXLS.createDrawingPatriarch();
		HSSFClientAnchor anchor;
		anchor = new HSSFClientAnchor(10, 10, 80, 200, (short) 0, 0, (short) 2, 4);
		anchor.setAnchorType(2);
		File file = new File(rutaImg + "logo.jpg");
		HSSFPicture picture = patriarch.createPicture(anchor, loadPicture(file, libroXLS));

		filaXLS = hojaXLS.createRow(0);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, "", estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(1);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, FECHA_CREACION_LABEL, estiloCeldaTitulos);
		crearCelda(7, 5000, FechaUtil.formatearFechaHHMMSS(new Date()) + "", estiloCeldaTitulos);

		combinarCelda(2, 4, 0, 7);

		filaXLS = hojaXLS.createRow(2);
		crearCelda(0, 5000, titulo, estiloTituloPT);

		filaXLS = hojaXLS.createRow(5);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, "", estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(6);

		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, DIVISION_LABEL, estiloCeldaTitulos);
		crearCelda(2, 5000, divisionCab, estiloCeldaTitulos);

		crearCelda(3, 5000, SOCIEDAD_LABEL, estiloCeldaTitulos);
		crearCelda(4, 5000, sociedadCab, estiloCeldaTitulos);

		crearCelda(5, 5000, UNIDAD_LABEL, estiloCeldaTitulos);
		crearCelda(6, 5000, unidadCab, estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		combinarCelda(7, 7, 0, 7);

		filaXLS = hojaXLS.createRow(7);
		crearCelda(1, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(8);

		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, PROCESO_LABEL, estiloCeldaTitulos);
		crearCelda(2, 5000, procesoCab, estiloCeldaTitulos);

		crearCelda(3, 5000, PRODUCTO_LABEL, estiloCeldaTitulos);
		crearCelda(4, 5000, productoCab, estiloCeldaTitulos);

		crearCelda(5, 5000, MES_LABEL, estiloCeldaTitulos);
		crearCelda(6, 5000, FechaUtil.numeroMesANombreMes(mes).toString(), estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		combinarCelda(9, 9, 0, 7);

		filaXLS = hojaXLS.createRow(9);
		crearCelda(1, 5000, "", estiloCeldaTitulos);

	}

	// CREACION DEL REPORTE PARTE DIARIO - EXCEL
	public HSSFWorkbook crearReporteDetPT(List<Tablakardex> kardexParteDiario, String titulo, String divisionCab,
			String sociedadCab, String unidadCab, String procesoCab, String productoCab, Short mes,
			List<Produccion> producciones, String rutaImg, Integer annio) throws AplicacionException, IOException {

		// String productoCab = productoCabe;

		hojaXLS = libroXLS.createSheet("ReporteParteDiario");

		HSSFCellStyle estiloCelda = libroXLS.createCellStyle();
		estiloCelda.setWrapText(true);
		estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCelda.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fuenteDat.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fuenteDat.setFontHeight((short) 180);
		fuenteDat.setFontName("Arial");
		estiloCelda.setFont(fuenteDat);

		HSSFCellStyle estiloCeldaIngreso = libroXLS.createCellStyle();
		estiloCeldaIngreso.setWrapText(true);
		estiloCeldaIngreso.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaIngreso.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fuenteNegrita = libroXLS.createFont();
		fuenteNegrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteNegrita.setFontHeight((short) 180);
		fuenteNegrita.setFontName("Arial");
		estiloCeldaIngreso.setFont(fuenteNegrita);

		HSSFCellStyle estiloCabTabla = libroXLS.createCellStyle();
		estiloCabTabla.setWrapText(true);
		estiloCabTabla.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCabTabla.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCabTabla.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

		fuenteCabTab = libroXLS.createFont();
		fuenteCabTab.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteCabTab.setFontHeight((short) 160);
		fuenteCabTab.setFontName("Arial");
		estiloCabTabla.setFont(fuenteCabTab);

		HSSFCellStyle estiloNombProducto = libroXLS.createCellStyle();
		estiloNombProducto.setWrapText(true);
		estiloNombProducto.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloNombProducto.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fuenteNombrePro = libroXLS.createFont();
		fuenteNombrePro.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteNombrePro.setFontHeight((short) 200);
		fuenteNombrePro.setFontName("Arial");
		estiloNombProducto.setFont(fuenteNombrePro);

		// CREAR CABECERA
		crearReporteDetPTCabecera(titulo, divisionCab, sociedadCab, unidadCab, procesoCab, productoCab, mes, rutaImg);
		// /
		List<Tablakardex> kardexParteDiarioHHVV = null;
		// CREAR DETALLE
		// RECOORRER TABLA PRODUCCION
		Integer anio = null;

		int n = 9;

		for (Produccion pro : producciones) {
			Producto producto = pro.getProducto();
			Proceso proceso = pro.getProceso();
			Boolean esMateriaPrima;
			Long codigoProducto = producto.getPkCodigoProducto();
			Long codigoLineaNegocio = proceso.getLineanegocio().getPkCodigoLineanegocio();

			esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(pro.getProducto().getPkCodigoProducto());
			String var = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESOS_COLUMNAS);

			List<Tablakardex> kardexProdcuto_proceso = traer_Kardex_Prod_Proc(proceso, pro.getProducto().getPkCodigoProducto(),
					kardexParteDiario, esMateriaPrima);

			List<String> nombresComponentes = null;
			int tam = -1;

			String nombreUnidadMedida = "-";

			if (producto.getUnidadmedida().getNombreUnidadmedida() != null) {
				nombreUnidadMedida = producto.getUnidadmedida().getNombreUnidadmedida();
			}

			// int colIngreso = 0;
			int contVar = 0;
			int colVar = -1;
			int colFactor = -1;
			int colFactorConsumo = -1;
			// int indice = 0;
			int colDespacho = -1;
			if (kardexProdcuto_proceso != null && kardexProdcuto_proceso.size() > 0) {

				Map<String, Map<Long, Double>> listaFactorCalidadHumedad = FactorconsumopuestotrabajoQuerier
						.obtenerPromedioFactorVariableCalidadPorPeriodoContable(codigoLineaNegocio, null, annio, mes, "Humedad");

				if (producto.getPkCodigoProducto().compareTo(codigoClinker) == 0
						&& proceso.getPkCodigoProceso().compareTo(codigoProcesoClinkerHH) == 0) {
					kardexParteDiarioHHVV = new ArrayList<Tablakardex>();
					Proceso procesoHV = new Proceso();
					procesoHV.setPkCodigoProceso(codigoProcesoClinkerHV);
					procesoHV.setLineanegocio(proceso.getLineanegocio());
					kardexParteDiarioHHVV = traer_Kardex_Prod_Proc(procesoHV, pro.getProducto().getPkCodigoProducto(),
							kardexParteDiario, esMateriaPrima);

					if (kardexParteDiarioHHVV.size() == 0) {
						kardexParteDiarioHHVV = TablaKardexQuerier.obtenerTablaKardexPorProcesoProducto(codigoProcesoClinkerHV,
								producto.getPkCodigoProducto(), kardexProdcuto_proceso.get(0).getProducciondiaria()
										.getPartediario().getPeriodocontable().getAnoPeriodocontable(), kardexProdcuto_proceso
										.get(0).getProducciondiaria().getPartediario().getPeriodocontable()
										.getMesPeriodocontable(), false);
					}

					// Agregando consumos de componentes de Clinkerizacion HV a
					// la tablakardex
					for (Tablakardex kardex : kardexProdcuto_proceso) {
						for (Tablakardex kardexHV : kardexParteDiarioHHVV) {
							if (kardex.getFechaTablakardex().equals(kardexHV.getFechaTablakardex())) {
								kardex.getConsumocomponentes().addAll(kardexHV.getConsumocomponentes());
							}
						}
					}
				}
				anio = kardexProdcuto_proceso.get(0).getProducciondiaria().getPartediario().getPeriodocontable()
						.getAnoPeriodocontable();
				mes = kardexProdcuto_proceso.get(0).getProducciondiaria().getPartediario().getPeriodocontable()
						.getMesPeriodocontable();

				Map<String, Double> mapaDiamovimientosLogisticos = null;
				boolean esmovimientosLogistico = false;
				ProductoBean productoBean = new ProductoBeanImpl();
				productoBean.setCodigo(codigoProducto);
				try {
					mapaDiamovimientosLogisticos = movimientoLogicFacade.obtenerMovimientoLogistico(codigoLineaNegocio,
							productoBean, anio, mes);
				} catch (LogicaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (mapaDiamovimientosLogisticos != null && mapaDiamovimientosLogisticos.size() > 0) {
					esmovimientosLogistico = true;
				}

				List<MedicionDiariaDTO> mediciones = medicionLogic.obtenerDetalleRegistroMedicionDAO(
						proceso.getPkCodigoProceso(), producto.getPkCodigoProducto(), proceso.getLineanegocio()
								.getPkCodigoLineanegocio(), anio, mes);
				Map<Date, Map<String, Double>> mapaMediciones = PDFFactory.obtenerPorfechaMedicionesDiarias(mediciones);
				List<String> nombreMediosAlmacenamiento = PDFFactory.obtenerNombreMediosAlmacenamiento(mediciones);

				if (NumberUtil.decimalMayorMasMenos(kardexProdcuto_proceso.get(0).getSaldoInicialTablakardex(), 0.0)
						|| NumberUtil.decimalMayorMasMenos(kardexProdcuto_proceso.get(kardexProdcuto_proceso.size() - 1)
								.getStockFinalTablakardex(), 0.0)) {
					int col = 0;

					filaXLS = hojaXLS.createRow(n);
					crearCelda(col, 5000, "", estiloCelda);
					n++;
					filaXLS = hojaXLS.createRow(n);

					crearCelda(col, 5000, pro.getProducto().getDescripcionProducto(), estiloNombProducto);
					n++;
					filaXLS = hojaXLS.createRow(n);
					crearCelda(col, 5000, "DIA", estiloCabTabla);
					col++;
					boolean tieneHumedadIngreso = false;
					boolean tieneHumedadConsumo = false;
					boolean tieneDespacho = false;
					int columnaIngreso = 0;
					int columnaconsumo = 0;
					int columnaStockFinal = 0;
					double sumaStockFinal = 0d;
					if (esMateriaPrima) {
						Long codigoVariableHumedad = Long.parseLong(ManejadorPropiedades
								.obtenerPropiedadPorClave(CODIGO_VARIABLE_CALIDAD_HUMEDAD));

						Productovariablecalidad productoVariableCalidad = ProductoVariableCalidadQuerier
								.obtenerProductoVariableCalidad(pro.getProducto().getPkCodigoProducto(), codigoVariableHumedad);
						tieneHumedadIngreso = productoVariableCalidad != null;
						tieneHumedadConsumo = EquivalenciasccvarcalidadQuerier.verificarSiPoseeHumedad(pro.getProducto()
								.getPkCodigoProducto());

						// CREACION DE COLUMNAS
						if (tieneHumedadIngreso) {
							crearCelda(col, 5000, ConstantesReporteDinamico.INGRESO_TM_HUM, estiloCabTabla);
							col++;
							crearCelda(col, 5000, ConstantesReporteDinamico.FACT_HUM_INGR, estiloCabTabla);
							col++;
							crearCelda(col, 5000, ConstantesReporteDinamico.INGRESO_TM_SECAS, estiloCabTabla);
							columnaIngreso = col;
							col++;

						} else {

							crearCelda(col, 5000, ConstantesReporteDinamico.INGRESO, estiloCabTabla);
							columnaIngreso = col;
							// colIngreso = col;
							col++;
						}

						crearCelda(col, 5000, ConstantesReporteDinamico.INGRESO_ACUM, estiloCabTabla);
						col++;

						if (tieneHumedadConsumo) {
							crearCelda(col, 5000, ConstantesReporteDinamico.CONSUMO_TM_HUM, estiloCabTabla);
							col++;

							crearCelda(col, 5000, ConstantesReporteDinamico.FACT_HUM_CONSUMO, estiloCabTabla);
							col++;

							crearCelda(col, 5000, ConstantesReporteDinamico.CONSUMO_TM_SECAS, estiloCabTabla);
							columnaconsumo = col;
							col++;

						} else {
							crearCelda(col, 5000, ConstantesReporteDinamico.CONSUMO, estiloCabTabla);
							columnaconsumo = col;
							col++;
						}

						crearCelda(col, 5000, ConstantesReporteDinamico.CONSUMO_ACUM, estiloCabTabla);
						col++;

						if (esmovimientosLogistico) {
							crearCelda(col, 5000, "Movimiento Logistico", estiloCabTabla);
							col++;
						}

						crearCelda(col, 5000, ConstantesReporteDinamico.STOCK_FINAL + ConstantesReporteDinamico.ESPACIO_BLANCO
								+ nombreUnidadMedida, estiloCabTabla);
						columnaStockFinal = col;
						col++;
					} else {

						Map<String, String> mapNombre = new HashMap<String, String>();
						for (Tablakardex kardx : kardexProdcuto_proceso) {
							if (kardx.getProducciondiaria().getOrdenproduccion() != null) {

								if (kardx.getConsumocomponentes() != null) {
									for (Iterator<Consumocomponente> iterator = kardx.getConsumocomponentes().iterator(); iterator
											.hasNext();) {
										Consumocomponente consumoComponente = iterator.next();
										if (consumoComponente.getConsumoConsumocomponente() != 0) {
											String nombreProducto = consumoComponente.getComponente()
													.getProductoByFkCodigoProductoComponente().getNombreProducto();
											mapNombre.put(nombreProducto, nombreProducto);
										}

									}
								}

							}
						}

						nombresComponentes = new ArrayList<String>(mapNombre.keySet());
						Collections.sort(nombresComponentes);
						for (String nombreProducto : nombresComponentes) {
							crearCelda(col, 5000, nombreProducto, estiloCabTabla);
							col++;
						}

						ParametroSistema registroParametroSis = new ParametroSistema();
						registroParametroSis = ParametroSistemaQuerier.obtenerParametroSistema("PRODUCTOS_DESPACHO");

						String[] codigosProductos = registroParametroSis.getValorParametro().split(",");

						for (String codPro : codigosProductos) {
							if (codPro.equals(producto.getPkCodigoProducto().toString())) {
								tieneDespacho = true;
							}
						}

						if (tieneDespacho) {
							crearCelda(col, 5000, ConstantesReporteDinamico.DESPACHO, estiloCabTabla);
							col++;
						}

						if (pro.getProducto().getPkCodigoProducto().compareTo(codigoClinker) == 0
								&& pro.getProceso().getPkCodigoProceso().compareTo(codigoProcesoClinkerHH) == 0) {
							crearCelda(col, 5000, ConstantesReporteDinamico.INGRESO_HORNOSHH, estiloCabTabla);
						} else {
							crearCelda(col, 5000, ConstantesReporteDinamico.INGRESO, estiloCabTabla);
							columnaIngreso = col;
						}
						// colIngreso = col;
						col++;
						// VALIDACION
						if (pro.getProducto().getPkCodigoProducto().compareTo(codigoClinker) == 0
								&& pro.getProceso().getPkCodigoProceso().compareTo(codigoProcesoClinkerHH) == 0) {
							crearCelda(col, 5000, ConstantesReporteDinamico.INGRESO_HORNOSHV, estiloCabTabla);
							col++;
							crearCelda(col, 5000, ConstantesReporteDinamico.INGREGO_TOTAL, estiloCabTabla);
							columnaIngreso = col;
							col++;
						}
						crearCelda(col, 5000, ConstantesReporteDinamico.CONSUMO, estiloCabTabla);
						columnaconsumo = col;
						col++;

						if (esmovimientosLogistico) {
							crearCelda(col, 5000, "Movimiento Logistico", estiloCabTabla);
							col++;
						}

						crearCelda(col, 5000, ConstantesReporteDinamico.STOCK_FINAL + ConstantesReporteDinamico.ESPACIO_BLANCO
								+ nombreUnidadMedida, estiloCabTabla);
						columnaStockFinal = col;
						col++;
					}

					if (var.indexOf(pro.getProceso().getPkCodigoProceso().toString()) >= 0) {
						try {
							crearCelda(col, 5000, ConstantesReporteDinamico.STOCK_FISICO, estiloCabTabla);
							col++;
							crearCelda(col, 5000, ConstantesReporteDinamico.DIFERENCIA, estiloCabTabla);
							col++;
							crearCelda(col, 5000, ConstantesReporteDinamico.PORCEN_VARIACION, estiloCabTabla);
							col++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (pro.getProceso()
							.getPkCodigoProceso()
							.compareTo(
									Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO))) == 0) {
						for (int i = 0; i < nombreMediosAlmacenamiento.size(); i++) {
							crearCelda(col, 5000, nombreMediosAlmacenamiento.get(i), estiloCabTabla);
							col++;
						}
					}
					n++;

					// if (colIngreso > 0) {
					filaXLS = hojaXLS.createRow(n);
					crearCelda(0, 5000, ConstantesReporteDinamico.SALDO_INICIAL + ConstantesReporteDinamico.ESPACIO_TAB
							+ nombreUnidadMedida, estiloCeldaIngreso);
					crearCelda(1, 5000,
							NumberUtil.numeroMayorCeroRedondeadoFUS(kardexProdcuto_proceso.get(0).getSaldoInicialTablakardex()),
							estiloCeldaIngreso);
					n++;
					// }

					// LLENAR DETALLE
					double ingresoAcum = 0d, consumoAcum = 0d, tMHumXFacHum = 0d, tMHum = 0d;
					double humXfacConsumo = 0d, humedad = 0d;

					Map<Integer, Double> MapSuma = new HashMap<Integer, Double>();
					Double saldoFinalHH = -1.0;
					for (Tablakardex tablakardex : kardexProdcuto_proceso) {

						col = 0;
						filaXLS = hojaXLS.createRow(n);
						crearCelda(col, 5000, FechaUtil.obtenerDiaFecha(tablakardex.getFechaTablakardex()).toString(),
								estiloCelda);
						calcularTotales(MapSuma, col, 0.0);
						col++;
						Double ingresoTablakardex = tablakardex.getIngresoTablakardex();
						Double consumoTablakardex = tablakardex.getConsumoTablakardex();
						if (esMateriaPrima) {
							ingresoAcum += ingresoTablakardex;
							consumoAcum += consumoTablakardex;

							if (tieneHumedadIngreso) {
								crearCelda(col, 5000,
										NumberUtil.numeroMayorCeroRedondeadoFUS(tablakardex.getIngresoHumedadTablakardex()),
										estiloCelda);
								calcularTotales(MapSuma, col,
										NumberUtil.Redondear2Decimales(tablakardex.getIngresoHumedadTablakardex()));
								col++;

								if (ingresoTablakardex != 0) {
									double factorHumIngreso = (1 - (ingresoTablakardex / tablakardex
											.getIngresoHumedadTablakardex())) * 100;
									// Nuevo calculo para hallar el Promedio
									// Humedad
									tMHumXFacHum += (factorHumIngreso * tablakardex.getIngresoHumedadTablakardex());
									tMHum += tablakardex.getIngresoHumedadTablakardex();
									crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(factorHumIngreso), estiloCelda);
									calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(factorHumIngreso));
									if (NumberUtil.decimalMayorMasMenos(factorHumIngreso, 0.0)) {
										contVar++;
									}
									colFactor = col;
									col++;
								} else {
									crearCelda(col, 5000, " ", estiloCelda);
									calcularTotales(MapSuma, col, 0.0);
									col++;
								}

								crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(ingresoTablakardex), estiloCelda);
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(ingresoTablakardex));
								col++;

							} else {
								crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(ingresoTablakardex), estiloCelda);
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(ingresoTablakardex));
								col++;

							}

							crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(ingresoAcum), estiloCelda);
							calcularTotales(MapSuma, col, 0.0);
							col++;

							if (tieneHumedadConsumo) {
								// COMUMO HUMEDO
								crearCelda(col, 5000,
										NumberUtil.numeroMayorCeroRedondeadoFUS(tablakardex.getConsumoHumedadTablakardex()),
										estiloCelda);
								calcularTotales(MapSuma, col,
										NumberUtil.Redondear2Decimales(tablakardex.getConsumoHumedadTablakardex()));
								col++;
								// FACTOR HUMEDAD CONSUMO
								if (consumoTablakardex != 0) {
									Map<Long, Double> humedadesPorProducto = listaFactorCalidadHumedad.get(FechaUtil
											.convertirDateToString(tablakardex.getFechaTablakardex()));
									Double factorHumConsumo = null;
									if (humedadesPorProducto != null) {
										factorHumConsumo = humedadesPorProducto.get(codigoProducto);
									}

									System.out.println("-->" + factorHumConsumo);
									if (factorHumConsumo == null) {
										factorHumConsumo = 0d;
									}
									// Nuevo calculo para hallar el Promedio
									// Humedad
									humXfacConsumo += (factorHumConsumo * tablakardex.getConsumoHumedadTablakardex());
									humedad += tablakardex.getConsumoHumedadTablakardex();
									colFactorConsumo = col;

									crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(factorHumConsumo), estiloCelda);
									calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(factorHumConsumo));
									col++;

								} else {

									crearCelda(col, 5000, " ", estiloCelda);
									calcularTotales(MapSuma, col, 0.0);
									col++;
								}
								// CONSUMO SECAS
								crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(consumoTablakardex), estiloCelda);
								calcularTotales(MapSuma, col, consumoTablakardex);
								col++;

							} else {
								crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(consumoTablakardex), estiloCelda);
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(consumoTablakardex));
								col++;
							}

							crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(consumoAcum), estiloCelda);
							calcularTotales(MapSuma, col, 0.0);
							col++;

							Double movimientoLogistico = 0d;
							if (esmovimientosLogistico) {
								movimientoLogistico = mapaDiamovimientosLogisticos.get(FechaUtil
										.convertirDateToString(tablakardex.getFechaTablakardex()));

								if (movimientoLogistico == null) {
									movimientoLogistico = 0d;
								}
								crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(movimientoLogistico), estiloCelda);
								calcularTotales(MapSuma, col, movimientoLogistico);
								col++;
							}

							crearCelda(col, 5000,
									NumberUtil.numeroMayorCeroRedondeadoFUS(tablakardex.getStockFinalTablakardex()), estiloCelda);
							sumaStockFinal = tablakardex.getStockFinalTablakardex();
							calcularTotales(MapSuma, col, 0.0);
							col++;

						} else {

							Map<String, Double> MapValorComp = new HashMap<String, Double>();
							String nombre = "";
							Set<Consumocomponente> consumocomponentes = tablakardex.getConsumocomponentes();
							for (int i = 0; i < nombresComponentes.size(); i++) {
								for (Consumocomponente consumocomponente : consumocomponentes) {
									if (nombresComponentes.get(i).equals(
											consumocomponente.getComponente().getProductoByFkCodigoProductoComponente()
													.getNombreProducto())) {
										double valor = 0;
										if (MapValorComp.get(nombresComponentes.get(i)) != null) {

											valor = MapValorComp.get(nombresComponentes.get(i))
													+ consumocomponente.getConsumoConsumocomponente();
											MapValorComp.put(nombresComponentes.get(i), valor);

										} else {
											MapValorComp.put(nombresComponentes.get(i),
													consumocomponente.getConsumoConsumocomponente());
										}
									}

								}

							}

							for (int i = 0; i < nombresComponentes.size(); i++) {
								if (MapValorComp.get(nombresComponentes.get(i)) != null) {
									crearCelda(col, 5000,
											NumberUtil.numeroMayorCeroRedondeadoFUS(MapValorComp.get(nombresComponentes.get(i))),
											estiloCelda);
									calcularTotales(MapSuma, col,
											NumberUtil.Redondear2Decimales(MapValorComp.get(nombresComponentes.get(i))));
									col++;
								} else {
									crearCelda(col, 5000, " ", estiloCelda);
									calcularTotales(MapSuma, col, 0.0);
									col++;
								}
							}

							if (tieneDespacho) {
								Double totMovimientoIngreso = MovimientoQuerier.obtenerMovimientoPorProductoXFechaXClasificacion(
										Long.valueOf(1), producto.getPkCodigoProducto(), tablakardex.getFechaTablakardex());
								Double totMovimientoSalida = MovimientoQuerier.obtenerMovimientoPorProductoXFechaXClasificacion(
										Long.valueOf(2), producto.getPkCodigoProducto(), tablakardex.getFechaTablakardex());
								Double totMovimiento = totMovimientoSalida - totMovimientoIngreso;
								colDespacho = col;
								crearCelda(col, 500, NumberUtil.Redondear2Decimales(totMovimiento).toString(), estiloCelda);
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(totMovimiento));
								col++;
							}

							crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(ingresoTablakardex), estiloCelda);
							calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(ingresoTablakardex));
							col++;
							// INGRESO DE LAS COLUMNAS DE HHVV
							Double ingresoHHVV = 0.0;
							if (pro.getProducto().getPkCodigoProducto().compareTo(codigoClinker) == 0
									&& pro.getProceso().getPkCodigoProceso().compareTo(codigoProcesoClinkerHH) == 0) {
								if (kardexParteDiarioHHVV != null) {

									Tablakardex tablakardexhhvv = tablaKardexLogic.obtenerKrdexHVSegunFecha(
											kardexParteDiarioHHVV, tablakardex.getFechaTablakardex());

									if (tablakardexhhvv != null) {

										ingresoHHVV = tablakardexhhvv.getIngresoTablakardex();
										crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(ingresoHHVV), estiloCelda);
										calcularTotales(MapSuma, col, ingresoHHVV);
										col++;

									} else {
										crearCelda(col, 5000, " ", estiloCelda);
										calcularTotales(MapSuma, col, 0.0);
										col++;

									}
								}
								crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(ingresoHHVV + ingresoTablakardex),
										estiloCelda);
								calcularTotales(MapSuma, col, (ingresoHHVV + ingresoTablakardex));
								col++;
								// indice++;
							}
							crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(consumoTablakardex), estiloCelda);
							calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(consumoTablakardex));
							col++;

							Double movimientoLogistico = 0d;
							if (esmovimientosLogistico) {
								movimientoLogistico = mapaDiamovimientosLogisticos.get(FechaUtil
										.convertirDateToString(tablakardex.getFechaTablakardex()));

								if (movimientoLogistico == null) {
									movimientoLogistico = 0d;
								}
								crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(movimientoLogistico), estiloCelda);
								calcularTotales(MapSuma, col, movimientoLogistico);
								col++;
							}

							// Particularidad en el calculo del Saldo Final en
							// Hornos HH
							if (pro.getProducto().getPkCodigoProducto().compareTo(codigoClinker) == 0
									&& pro.getProceso().getPkCodigoProceso().compareTo(codigoProcesoClinkerHH) == 0) {

								if (saldoFinalHH == -1.0) {

									crearCelda(
											col,
											5000,
											NumberUtil.numeroMayorCeroRedondeadoFUS(tablakardex.getStockFinalTablakardex()
													+ ingresoHHVV ), estiloCelda);
									saldoFinalHH = NumberUtil.Redondear2Decimales(tablakardex.getStockFinalTablakardex()
											+ ingresoHHVV);
									sumaStockFinal = saldoFinalHH;
								} else {

									crearCelda(
											col,
											5000,
											NumberUtil
													.numeroMayorCeroRedondeadoFUS(((saldoFinalHH + ingresoHHVV + ingresoTablakardex) - consumoTablakardex)
															+ movimientoLogistico), estiloCelda);
									saldoFinalHH = ((saldoFinalHH + ingresoHHVV + ingresoTablakardex) - consumoTablakardex)
											+ movimientoLogistico;
									sumaStockFinal = saldoFinalHH;
								}
								calcularTotales(MapSuma, col, 0.0);
							} else {
								crearCelda(col, 5000,
										NumberUtil.numeroMayorCeroRedondeadoFUS(tablakardex.getStockFinalTablakardex()),
										estiloCelda);
								sumaStockFinal = tablakardex.getStockFinalTablakardex();
								calcularTotales(MapSuma, col, 0.0);
							}
							col++;
						}
						if (var.indexOf(pro.getProceso().getPkCodigoProceso().toString()) >= 0) {
							double dif = 0.0;
							if (tablakardex.getStockFinalTablakardex() != null && tablakardex.getStockFisicoTablakardex() != null) {
								dif = tablakardex.getStockFisicoTablakardex() - tablakardex.getStockFinalTablakardex();
							}

							crearCelda(col, 5000,
									NumberUtil.numeroMayorCeroRedondeadoFUS(tablakardex.getStockFisicoTablakardex()), estiloCelda);
							calcularTotales(MapSuma, col, 0.0);
							col++;

							crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(dif), estiloCelda);
							calcularTotales(MapSuma, col, 0.0);
							col++;

							crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(tablakardex.getVariacionTablakardex()),
									estiloCelda);
							if (NumberUtil.decimalMayorMasMenos(tablakardex.getVariacionTablakardex(), 0.0)) {
								contVar++;
							}
							colVar = col;

							calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(tablakardex.getVariacionTablakardex()));
							col++;

						}

						if (pro.getProceso()
								.getPkCodigoProceso()
								.compareTo(
										Long.valueOf(ManejadorPropiedades
												.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO))) == 0) {

							Map<String, Double> mapa = mapaMediciones.get(tablakardex.getFechaTablakardex());

							for (int i = 0; i < nombreMediosAlmacenamiento.size(); i++) {

								if (mapa != null) {
									Double valor = NumberUtil.validateDouble(mapa.get(nombreMediosAlmacenamiento.get(i)));
									crearCelda(col, 5000, NumberUtil.redondear(valor, 4) + "", estiloCelda);
									calcularTotales(MapSuma, col, NumberUtil.redondear(valor, 4));
									col++;
								} else {
									crearCelda(col, 5000, "", estiloCelda);
									calcularTotales(MapSuma, col, 0.0);
									col++;
								}
							}
						}
						// contVar++;
						n++;
					}
					filaXLS = hojaXLS.createRow(n);
					col = 0;
					for (int i = 0; i < MapSuma.size(); i++) {
						if (MapSuma.get(i) != null && MapSuma.get(i) > 0 || (MapSuma.get(i) * -1) > 0) {

							if (colVar == i) {
								if (contVar > 0) {

									crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(MapSuma.get(i) / contVar),
											estiloCeldaIngreso);
								} else {
									crearCelda(col, 5000, "", estiloCeldaIngreso);
								}
								col++;
								continue;
							}

							// COLUMNA DESPACHO
							if (colDespacho == i) {
								crearCelda(col, 5000,
										NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(MapSuma.get(i))),
										estiloCeldaIngreso);
								col++;
								continue;
							}

							if (colFactor == i) {
								if (contVar > 0) {
									if (tMHum <= 0) {
										tMHum = 1d;
									}
									crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(tMHumXFacHum / tMHum),
											estiloCeldaIngreso);
								} else {
									crearCelda(col, 5000, "", estiloCeldaIngreso);
								}
								col++;
								continue;
							}

							if (colFactorConsumo == i) {
								if (humedad > 0) {

									crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(humXfacConsumo / humedad),
											estiloCeldaIngreso);
								} else {
									crearCelda(col, 5000, "", estiloCeldaIngreso);
								}
								col++;
								continue;
							}

							crearCelda(col, 5000, NumberUtil.numeroMayorCeroRedondeadoFUS(MapSuma.get(i)), estiloCeldaIngreso);

						} else {
							crearCelda(col, 5000, "", estiloCeldaIngreso);

						}
						col++;
					}
					n++;

					if (anio != null && mes != null) {

						filaXLS = hojaXLS.createRow(n);
						col = 0;
						DetalleParteTecnicoConsumoComponentesBean lista;
						lista = generarParteTecnicoLogic.obtenerDetalleParteTecnicoConsumoparaPDDAO(producto, proceso
								.getLineanegocio().getPkCodigoLineanegocio(), mes, anio);
						for (int i = 0; i < MapSuma.size(); i++) {
							if (i == 0) {
								crearCelda(col, 5000, PDFFactory.AJUSTE_LABEL, estiloCeldaIngreso);

							} else if (i == columnaIngreso) {
								crearCelda(col, 5000,
										NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(lista.getIngreso())),
										estiloCeldaIngreso);

							} else if (i == columnaconsumo) {
								crearCelda(col, 5000,
										NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(lista.getConsumo())),
										estiloCeldaIngreso);

							} else if (i == columnaStockFinal) {
								crearCelda(
										col,
										5000,
										NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(sumaStockFinal
												+ lista.getIngreso())
												- lista.getConsumo()), estiloCeldaIngreso);
							} else {
								crearCelda(col, 5000, "", estiloCeldaIngreso);
							}
							col++;
						}
						n++;
					}

				}
			}
		}

		return libroXLS;
	}

	private static void calcularTotales(Map<Integer, Double> mapSuma, int col, Double d) {
		Double suma = mapSuma.get(col);
		if (suma != null) {
			mapSuma.put(col, suma + d);
		} else {
			mapSuma.put(col, d);
		}
	}

	private List<Tablakardex> traer_Kardex_Prod_Proc(Proceso proceso, Long codigoProducto, List<Tablakardex> kardexParteDiario,
			Boolean esMateriaPrima) {
		List<Tablakardex> kardexProdcuto_proceso = new ArrayList<Tablakardex>();
		Long codigoProceso = proceso.getPkCodigoProceso();
		Long codigoLineaNegocio = proceso.getLineanegocio().getPkCodigoLineanegocio();

		for (Tablakardex tablakardex : kardexParteDiario) {
			if (esMateriaPrima) {
				if (tablakardex.getProducciondiaria().getProducto() != null) {
					if (tablakardex.getProducciondiaria().getProducto().getPkCodigoProducto().compareTo(codigoProducto) == 0
							&& tablakardex.getProducciondiaria().getPartediario().getLineanegocio().getPkCodigoLineanegocio()
									.compareTo(codigoLineaNegocio) == 0) {
						kardexProdcuto_proceso.add(tablakardex);
					}
				}

			} else {
				if (tablakardex.getProducciondiaria().getOrdenproduccion() != null) {
					Ordenproduccion ordenProduccion = tablakardex.getProducciondiaria().getOrdenproduccion();
					if (ordenProduccion.getProduccion().getProceso().getPkCodigoProceso().compareTo(codigoProceso) == 0
							&& ordenProduccion.getProduccion().getProducto().getPkCodigoProducto().compareTo(codigoProducto) == 0
							&& tablakardex.getProducciondiaria().getPartediario().getLineanegocio().getPkCodigoLineanegocio()
									.compareTo(codigoLineaNegocio) == 0) {
						kardexProdcuto_proceso.add(tablakardex);
					}

				}
			}
		}

		return kardexProdcuto_proceso;
	}

	/**
	 * Creacion del Reporte Cubicaciones
	 * 
	 * @param cubicaciones
	 */

	public HSSFWorkbook crearReporteCubicaciones(List<Cubicacion> cubicaciones, String division, String sociedad, String unidad,
			Short mes, String ruta) {

		// CREAR CABECERA
		hojaXLS = libroXLS.createSheet("Reprote_Cubicaciones");

		HSSFCellStyle estiloResultado = libroXLS.createCellStyle();
		estiloResultado.setWrapText(true);
		estiloResultado.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		fuenteNombrePro = libroXLS.createFont();
		fuenteNombrePro.setFontHeight((short) 160);
		fuenteNombrePro.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fuenteNombrePro.setFontName("Arial");
		estiloResultado.setFont(fuenteNombrePro);
		estiloResultado.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloResultado.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloResultado.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloResultado.setBorderTop(HSSFCellStyle.BORDER_THIN);

		HSSFCellStyle estiloNegrita = libroXLS.createCellStyle();
		estiloNegrita.setWrapText(true);
		estiloNegrita.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		fuenteNegrita = libroXLS.createFont();
		fuenteNegrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteNegrita.setFontHeight((short) 200);
		fuenteNegrita.setFontName("Arial");
		estiloNegrita.setFont(fuenteNegrita);

		try {
			crearReporteCabecera(ManejadorPropiedades.obtenerPropiedadPorClave("reporte.cubicaciones.titulo"), division,
					sociedad, unidad, mes, ruta);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// /

		int fila = 10;
		creaEstiloTitulo();

		filaXLS = hojaXLS.createRow(fila);
		crearCelda(0, 5000, ManejadorPropiedades.obtenerPropiedadPorClave("reporte.cubicaciones.nombre.producto"), estiloTit);
		crearCelda(1, 5000, ManejadorPropiedades.obtenerPropiedadPorClave("reporte.cubicaciones.nombre.almacen"), estiloTit);
		crearCelda(2, 5000, ManejadorPropiedades.obtenerPropiedadPorClave("reporte.cubicaciones.volumen"), estiloTit);
		crearCelda(3, 5000, ManejadorPropiedades.obtenerPropiedadPorClave("reporte.cubicaciones.densidad"), estiloTit);
		crearCelda(4, 5000, ManejadorPropiedades.obtenerPropiedadPorClave("reporte.cubicaciones.toneladas"), estiloTit);
		fila++;
		creaEstiloDatos();
		Long codigoProducto = Long.valueOf(-1);
		double suma = 0.0;
		Double densidad = 0d;
		for (int i = 0; i < cubicaciones.size(); i++) {
			Cubicacion cubicacion = cubicaciones.get(i);

			if (cubicacion.getDensidadMedioalmacenamiento() != null) {
				densidad = cubicacion.getDensidadMedioalmacenamiento();
			} else {
				densidad = cubicacion.getMedioalmacenamiento().getDensidadMedioalmacenamiento();
			}
			filaXLS = hojaXLS.createRow(fila);

			String nombre = "";
			Long pk = cubicacion.getCubicacionproducto().getProduccion().getProducto().getPkCodigoProducto();
			Double producto = NumberUtil.multiplicar(cubicacion.getVolumenM3Cubicacion(), densidad);

			if (pk.compareTo(codigoProducto) != 0) {
				nombre = cubicacion.getCubicacionproducto().getProduccion().getProducto().getDescripcionProducto();

				codigoProducto = pk;
			}

			crearCelda(0, 8000, nombre, estiloDat);
			crearCelda(1, 5000, cubicacion.getMedioalmacenamiento().getNombreMedioalmacenamiento(), estiloDat);
			crearCelda(2, 5000, cubicacion.getVolumenM3Cubicacion() + "", estiloResultado);
			crearCelda(3, 5000, densidad + "", estiloResultado);
			crearCelda(4, 5000, producto + "", estiloResultado);
			suma += producto;
			fila++;

			/**
			 * Agregar la fila de Totales
			 */
			if ((i + 1) < cubicaciones.size()
					&& pk.compareTo(cubicaciones.get(i + 1).getCubicacionproducto().getProduccion().getProducto()
							.getPkCodigoProducto()) != 0) {
				filaXLS = hojaXLS.createRow(fila);
				crearCelda(4, 5000, NumberUtil.Redondear2Decimales(suma) + "", estiloNegrita);
				suma = 0.0;
				fila++;
			}
			if ((i + 1) == cubicaciones.size()) {
				filaXLS = hojaXLS.createRow(fila);
				crearCelda(4, 5000, NumberUtil.Redondear2Decimales(suma) + "", estiloNegrita);
				suma = 0.0;
				fila++;
			}

		}
		return libroXLS;
	}

	public void crearReporteCabecera(String titulo, String divisionCab, String sociedadCab, String unidadCab, Short mes,
			String rutaImg) throws AplicacionException, IOException {

		HSSFCellStyle estiloCeldaTitulos = libroXLS.createCellStyle();
		estiloCeldaTitulos.setWrapText(true);
		estiloCeldaTitulos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		estiloCeldaTitulos.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloCeldaTitulos.setFont(fuenteTit);

		HSSFCellStyle estiloTituloPT = libroXLS.createCellStyle();
		estiloTituloPT.setWrapText(true);
		estiloTituloPT.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloTituloPT.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fuenteTitPT = libroXLS.createFont();
		fuenteTitPT.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteTitPT.setFontHeight((short) 280);
		fuenteTitPT.setFontName("Arial");
		estiloTituloPT.setFont(fuenteTitPT);

		HSSFPatriarch patriarch = hojaXLS.createDrawingPatriarch();
		HSSFClientAnchor anchor;
		anchor = new HSSFClientAnchor(10, 10, 80, 200, (short) 0, 0, (short) 2, 4);
		anchor.setAnchorType(2);
		File file = new File(rutaImg + "logo.jpg");
		patriarch.createPicture(anchor, loadPicture(file, libroXLS));

		filaXLS = hojaXLS.createRow(0);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, "", estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(1);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, FECHA_CREACION_LABEL, estiloCeldaTitulos);
		crearCelda(7, 5000, FechaUtil.formatearFechaHHMMSS(new Date()) + "", estiloCeldaTitulos);

		combinarCelda(2, 4, 0, 7);

		filaXLS = hojaXLS.createRow(2);
		crearCelda(0, 5000, titulo, estiloTituloPT);

		filaXLS = hojaXLS.createRow(5);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, "", estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(6);

		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, DIVISION_LABEL, estiloCeldaTitulos);
		crearCelda(2, 5000, divisionCab, estiloCeldaTitulos);

		crearCelda(3, 5000, SOCIEDAD_LABEL, estiloCeldaTitulos);
		crearCelda(4, 5000, sociedadCab, estiloCeldaTitulos);

		crearCelda(5, 5000, UNIDAD_LABEL, estiloCeldaTitulos);
		crearCelda(6, 5000, unidadCab, estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		combinarCelda(7, 7, 0, 7);

		filaXLS = hojaXLS.createRow(7);
		crearCelda(1, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(8);

		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, MES_LABEL, estiloCeldaTitulos);
		crearCelda(2, 5000, FechaUtil.numeroMesANombreMes(mes).toString(), estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, "", estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		combinarCelda(9, 9, 0, 7);

		filaXLS = hojaXLS.createRow(9);
		crearCelda(1, 5000, "", estiloCeldaTitulos);

	}

	
	

	


	

	

	public void crearCabeceraSI(String titulo, String rutaImg) throws AplicacionException, IOException {

		HSSFCellStyle estiloCeldaTitulos = libroXLS.createCellStyle();
		estiloCeldaTitulos.setWrapText(true);
		estiloCeldaTitulos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		estiloCeldaTitulos.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloCeldaTitulos.setFont(fuenteTit);

		HSSFCellStyle estiloTituloPT = libroXLS.createCellStyle();
		estiloTituloPT.setWrapText(true);
		estiloTituloPT.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloTituloPT.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fuenteTitPT = libroXLS.createFont();
		fuenteTitPT.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteTitPT.setFontHeight((short) 280);
		fuenteTitPT.setFontName("Arial");
		estiloTituloPT.setFont(fuenteTitPT);

		HSSFPatriarch patriarch = hojaXLS.createDrawingPatriarch();
		HSSFClientAnchor anchor;
		anchor = new HSSFClientAnchor(10, 10, 80, 200, (short) 0, 0, (short) 2, 4);
		anchor.setAnchorType(2);
		File file = new File(rutaImg + "logo.jpg");
		patriarch.createPicture(anchor, loadPicture(file, libroXLS));

		filaXLS = hojaXLS.createRow(0);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, "", estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(1);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, FECHA_CREACION_LABEL, estiloCeldaTitulos);
		crearCelda(7, 5000, FechaUtil.formatearFechaHHMMSS(new Date()) + "", estiloCeldaTitulos);

		combinarCelda(2, 4, 0, 7);

		filaXLS = hojaXLS.createRow(2);
		crearCelda(0, 5000, titulo, estiloTituloPT);

		filaXLS = hojaXLS.createRow(3);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);
		crearCelda(6, 5000, "", estiloCeldaTitulos);
		crearCelda(7, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(4);
		crearCelda(1, 5000, "", estiloCeldaTitulos);

	}

	

	

	public void crearCabeceraConsolidadoTablaValor(Integer anno, Short mes, String titulo, String rutaImg)
			throws AplicacionException, IOException {

		HSSFCellStyle estiloCeldaTitulos = libroXLS.createCellStyle();
		estiloCeldaTitulos.setWrapText(true);
		estiloCeldaTitulos.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloCeldaTitulos.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		estiloCeldaTitulos.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estiloCeldaTitulos.setFont(fuenteTit);

		HSSFCellStyle estiloTituloPT = libroXLS.createCellStyle();
		estiloTituloPT.setWrapText(true);
		estiloTituloPT.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estiloTituloPT.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fuenteTitPT = libroXLS.createFont();
		fuenteTitPT.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fuenteTitPT.setFontHeight((short) 280);
		fuenteTitPT.setFontName("Arial");
		estiloTituloPT.setFont(fuenteTitPT);

		HSSFPatriarch patriarch = hojaXLS.createDrawingPatriarch();
		HSSFClientAnchor anchor;
		anchor = new HSSFClientAnchor(10, 10, 80, 200, (short) 0, 0, (short) 2, 4);
		anchor.setAnchorType(2);
		File file = new File(rutaImg + "logo.jpg");
		patriarch.createPicture(anchor, loadPicture(file, libroXLS));

		filaXLS = hojaXLS.createRow(0);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, "", estiloCeldaTitulos);
		crearCelda(5, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(1);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		crearCelda(1, 5000, "", estiloCeldaTitulos);
		crearCelda(2, 5000, "", estiloCeldaTitulos);
		crearCelda(3, 5000, "", estiloCeldaTitulos);
		crearCelda(4, 5000, FECHA_CREACION_LABEL, estiloCeldaTitulos);
		crearCelda(5, 5000, FechaUtil.formatearFechaHHMMSS(new Date()) + "", estiloCeldaTitulos);

		combinarCelda(2, 2, 0, 5);
		combinarCelda(3, 3, 0, 5);
		combinarCelda(4, 4, 0, 5);
		combinarCelda(5, 5, 0, 5);
		combinarCelda(6, 6, 0, 5);
		combinarCelda(7, 7, 0, 5);

		filaXLS = hojaXLS.createRow(2);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		filaXLS = hojaXLS.createRow(3);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		filaXLS = hojaXLS.createRow(4);
		crearCelda(0, 5000, "", estiloCeldaTitulos);
		filaXLS = hojaXLS.createRow(5);
		crearCelda(0, 5000, "", estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(6);
		crearCelda(0, 5000, "Reporte Mensual " + FechaUtil.numeroMesANombreMes(mes) + " " + anno, estiloCeldaTitulos);

		filaXLS = hojaXLS.createRow(7);
		crearCelda(0, 5000, titulo, estiloCeldaTitulos);

	}

}
