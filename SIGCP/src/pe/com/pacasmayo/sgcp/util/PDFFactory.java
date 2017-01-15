package pe.com.pacasmayo.sgcp.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoConsumoComponentesBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteEquivalenciasccvarcalidadBean;
import pe.com.pacasmayo.sgcp.bean.ReporteVarCalidadProductoBean;
import pe.com.pacasmayo.sgcp.bean.ReporteVarCalidadProductoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ReporteVarCalidadPuestoBean;
import pe.com.pacasmayo.sgcp.bean.impl.ProductoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.GenerarParteTecnicoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MedicionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MovimientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TablaKardexLogicFacade;
import pe.com.pacasmayo.sgcp.logica.parteTecnico.GenerarParteTecnicoLogic;
import pe.com.pacasmayo.sgcp.logica.partediario.TablaKardexLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MedicionLogic;
import pe.com.pacasmayo.sgcp.logica.stock.MovimientoLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablevariacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.querier.EquivalenciasccvarcalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.MovimientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoVariableCalidadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TablaKardexQuerier;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedicionDiariaDTO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfCell;

public class PDFFactory implements ConstantesLogicaNegocio {
	public static String TITULO_REPORTE_DETALLE_PARTE_DIARIO = "reporte.dinamico.titulo.parte.diario.detalle";
	public static String DIVISION_LABEL = "Division :";
	public static String SOCIEDAD_LABEL = "Sociedad :";
	public static String UNIDAD_LABEL = "Unidad :";
	public static String PROCESO_LABEL = "Proceso";
	public static String PRODUCTO_LABEL = "Producto";
	public static String MES_LABEL = "Mes :";
	public static String FECHA_CREACION_LABEL = "Creacion :";
	public static final String CODIGO_VARIABLE_CALIDAD_HUMEDAD = "codigo.variable.calidad.humedad";
	public static final String CODIGO_PROCESOS_COLUMNAS = "codigos.procesos.stockfis.desviacion";
	private static final Font fontNormal = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK);
	public static final Font fontPiePagina = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
	public static final Font fontTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL, BaseColor.BLACK);
	private static final Font fontBold = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD, BaseColor.BLACK);
	private static final Log logger = LogFactory.getLog(PDFFactory.class);
	private static Long codigoClinker = Long.valueOf(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_CLINKER1));
	private static Long codigoProcesoClinkerHH = Long.valueOf(ManejadorPropiedades
			.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HH));
	private static Long codigoProcesoClinkerHV = Long.valueOf(ManejadorPropiedades
			.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HV));
	public static String AJUSTE_LABEL = "Ajuste";
	private static GenerarParteTecnicoLogicFacade generarParteTecnicoLogic = new GenerarParteTecnicoLogic();
	private static TablaKardexLogicFacade tablaKardexLogic = new TablaKardexLogic();
	private static MedicionLogicFacade medicionLogic = new MedicionLogic();
	private static MovimientoLogicFacade movimientoLogicFacade = new MovimientoLogic();

	public static PdfPTable crearTituloYsubTitulo(String titulo, String division, String sociedad, String unidad, String proceso,
			String producto, Short mes, String img) {
		PdfPTable tabla = new PdfPTable(8);
		try {
			tabla.setWidths(new int[] { 5, 15, 5, 15, 5, 15, 5, 15 });
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		}
		tabla.setWidthPercentage(100);
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK);

		Image imagenCPSAA;
		try {
			imagenCPSAA = Image.getInstance(img);
			imagenCPSAA.setWidthPercentage(75);
			PdfPCell cell = new PdfPCell();
			cell.addElement(imagenCPSAA);
			cell.setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setBorder(PdfCell.NO_BORDER);
			tabla.addCell(cell);
		} catch (BadElementException e) {
			logger.error(e.getMessage());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		// tabla.addCell(crearCelda(" ", PdfCell.ALIGN_MIDDLE,
		// PdfCell.ALIGN_CENTER, 8, PdfCell.NO_BORDER, font));
		tabla.addCell(crearCelda(" ", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_CENTER, 8, PdfCell.NO_BORDER, font));

		tabla.addCell(crearCelda(titulo, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_CENTER, 8, PdfCell.NO_BORDER, font));
		font = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
		tabla.addCell(crearCelda(" ", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_CENTER, 8, PdfCell.NO_BORDER, font));
		tabla.addCell(crearCelda(" ", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_CENTER, 8, PdfCell.NO_BORDER, font));

		tabla.addCell(crearCelda(DIVISION_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(division, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(SOCIEDAD_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(sociedad, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(UNIDAD_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(unidad, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(FECHA_CREACION_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(FechaUtil.formatearFechaHHMMSS(new Date()) + "", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0,
				PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(PROCESO_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(proceso, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(PRODUCTO_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(producto, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(MES_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(FechaUtil.numeroMesANombreMes(mes), PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0,
				PdfCell.NO_BORDER, font));

		tabla.addCell(crearCelda(" ", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_CENTER, 8, PdfCell.NO_BORDER, font));
		tabla.addCell(crearCelda(" ", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_CENTER, 8, PdfCell.NO_BORDER, font));

		return tabla;
	}

	/**
	 * Metodo Crear Celda con los siguientes variables
	 * 
	 * @param label
	 * @param valign
	 * @param halign
	 * @param colspan
	 * @param border
	 * @param font
	 * @return
	 */
	private static PdfPCell crearCelda(String label, int valign, int halign, int colspan, int border, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(String.format("%s", label), font));
		cell.setVerticalAlignment(valign);
		cell.setHorizontalAlignment(halign);
		cell.setColspan(colspan);
		cell.setBorder(border);
		return cell;
	}

	/**
	 * Metodo Crear Celda con los siguientes variables
	 * 
	 * @param label
	 * @param valign
	 * @param halign
	 * @param colspan
	 * @param border
	 * @param font
	 * @return
	 */
	private static PdfPCell crearCelda(String label, int valign, int halign, int colspan, Font font, float borderWidthBottom,
			float borderWidthTop, float borderWidthLeft, float borderWidthRight) {
		PdfPCell cell = new PdfPCell(new Phrase(String.format("%s", label), font));
		cell.setVerticalAlignment(valign);
		cell.setHorizontalAlignment(halign);
		cell.setColspan(colspan);
		cell.setBorderWidthBottom(borderWidthBottom);
		cell.setBorderWidthLeft(borderWidthLeft);
		cell.setBorderWidthRight(borderWidthRight);
		cell.setBorderWidthTop(borderWidthTop);

		return cell;
	}

	/**
	 * @param label
	 * @param valign
	 * @param halign
	 * @param colspan
	 * @param font
	 * @param borderWidthBottom
	 * @param borderWidthTop
	 * @param borderWidthLeft
	 * @param borderWidthRight
	 * @param color
	 * @return
	 */
	private static PdfPCell crearCelda(String label, int valign, int halign, int colspan, Font font, float borderWidthBottom,
			float borderWidthTop, float borderWidthLeft, float borderWidthRight, BaseColor color) {
		PdfPCell cell = new PdfPCell(new Phrase(String.format("%s", label), font));
		cell.setVerticalAlignment(valign);
		cell.setHorizontalAlignment(halign);
		cell.setColspan(colspan);
		cell.setBorderWidthBottom(borderWidthBottom);
		cell.setBorderWidthLeft(borderWidthLeft);
		cell.setBorderWidthRight(borderWidthRight);
		cell.setBorderWidthTop(borderWidthTop);
		cell.setBackgroundColor(color);
		return cell;
	}

	public static PdfPCell crearCelda(String nombreCelda, int haling, Integer colspan, int border, BaseColor color) {
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(String.format("%s", nombreCelda), fontNormal));
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(haling);
		cell.setColspan(colspan);
		cell.setBackgroundColor(color);
		cell.setBorder(border);
		return cell;
	}

	/**
	 * @param nombreCelda
	 * @param valign
	 * @param haling
	 * @param colspan
	 * @param rowspan
	 * @param border
	 * @param color
	 * @param font
	 * @return
	 */
	public static PdfPCell crearCelda(String nombreCelda, int valign, int haling, Integer colspan, Integer rowspan, int border,
			BaseColor color, Font font) {
		PdfPCell cell = null;
		cell = new PdfPCell(new Phrase(String.format("%s", nombreCelda), font));
		cell.setVerticalAlignment(valign);
		cell.setHorizontalAlignment(haling);
		cell.setRowspan(rowspan);
		cell.setColspan(colspan);
		cell.setBackgroundColor(color);
		cell.setBorder(border);
		return cell;
	}

	public static PdfPTable crearCabeceraYDetalle(Producto producto, Long codigoProceso, List<Tablakardex> kardexParteDiario,
			Map<String, Map<Long, Double>> listaFactorCalidadHumedad, Long codigoLineaNegocio) throws SesionVencidaException,
			ElementoNoEncontradoException, EntornoEjecucionException, DocumentException {
		Boolean esMateriaPrima;
		Integer anio = null;
		Short mes = null;
		List<Tablakardex> kardexParteDiarioHHVV = null;
		Long codigoProducto = producto.getPkCodigoProducto();
		esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(producto.getPkCodigoProducto());
		List<String> nombresComponentes = null;
		String var = ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESOS_COLUMNAS);
		// int tam = -1;
		List<Tablakardex> kardexProdcuto_proceso = traer_Kardex_Prod_Proc(codigoProceso, producto.getPkCodigoProducto(),
				kardexParteDiario, esMateriaPrima, codigoLineaNegocio);

		if (kardexProdcuto_proceso.size() == 0) {
			return null;

		}
		anio = kardexProdcuto_proceso.get(0).getProducciondiaria().getPartediario().getPeriodocontable().getAnoPeriodocontable();
		mes = kardexProdcuto_proceso.get(0).getProducciondiaria().getPartediario().getPeriodocontable().getMesPeriodocontable();

		List<MedicionDiariaDTO> mediciones = null;
		try {
			mediciones = medicionLogic.obtenerDetalleRegistroMedicionDAO(codigoProceso, producto.getPkCodigoProducto(),
					codigoLineaNegocio, anio, mes);
		} catch (LogicaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String nombreUnidadMedida = "-";

		if (producto.getUnidadmedida().getNombreUnidadmedida() != null) {
			nombreUnidadMedida = producto.getUnidadmedida().getNombreUnidadmedida();
		}

		Map<String, Double> mapaDiamovimientosLogisticos = null;
		boolean esmovimientosLogistico = false;
		ProductoBean productoBean = new ProductoBeanImpl();
		productoBean.setCodigo(codigoProducto);
		try {
			mapaDiamovimientosLogisticos = movimientoLogicFacade.obtenerMovimientoLogistico(codigoLineaNegocio, productoBean,
					anio, mes);
		} catch (LogicaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (mapaDiamovimientosLogisticos != null && mapaDiamovimientosLogisticos.size() > 0) {
			esmovimientosLogistico = true;
		}

		Map<Date, Map<String, Double>> mapaMediciones = obtenerPorfechaMedicionesDiarias(mediciones);
		List<String> nombreMediosAlmacenamiento = obtenerNombreMediosAlmacenamiento(mediciones);

		if (producto.getPkCodigoProducto().compareTo(codigoClinker) == 0 && codigoProceso.compareTo(codigoProcesoClinkerHH) == 0) {
			kardexParteDiarioHHVV = new ArrayList<Tablakardex>();
			kardexParteDiarioHHVV = traer_Kardex_Prod_Proc(codigoProcesoClinkerHV, producto.getPkCodigoProducto(),
					kardexParteDiario, esMateriaPrima, codigoLineaNegocio);
			if (kardexParteDiarioHHVV.size() == 0) {
				if (kardexProdcuto_proceso.size() > 0) {
					kardexParteDiarioHHVV = TablaKardexQuerier.obtenerTablaKardexPorProcesoProducto(codigoProcesoClinkerHV,
							producto.getPkCodigoProducto(), kardexProdcuto_proceso.get(0).getProducciondiaria().getPartediario()
									.getPeriodocontable().getAnoPeriodocontable(), kardexProdcuto_proceso.get(0)
									.getProducciondiaria().getPartediario().getPeriodocontable().getMesPeriodocontable(), false);
				}

			}

			// Agregando consumos de componentes de Clinkerizacion HV a la
			// tablakardex
			for (Tablakardex kardex : kardexProdcuto_proceso) {
				for (Tablakardex kardexHV : kardexParteDiarioHHVV) {
					if (kardex.getFechaTablakardex().equals(kardexHV.getFechaTablakardex())) {
						kardex.getConsumocomponentes().addAll(kardexHV.getConsumocomponentes());
					}
				}
			}
		}

		boolean tieneHumedadIngreso = false;
		boolean tieneHumedadConsumo = false;

		boolean tieneDespacho = false;

		int col = 0;
		int columnaIngreso = 0;
		int columnaconsumo = 0;
		int columnaStockFinal = 0;

		double sumaStockFinal = 0d;
		double acumuladoMovimientoLogistico = 0d;
		Map<Integer, String> mapaCol = new HashMap<Integer, String>();
		if (kardexProdcuto_proceso != null && kardexProdcuto_proceso.size() > 0) {

			if (NumberUtil.decimalMayorMasMenos(kardexProdcuto_proceso.get(0).getSaldoInicialTablakardex(), 0.0)
					|| NumberUtil.decimalMayorMasMenos(kardexProdcuto_proceso.get(kardexProdcuto_proceso.size() - 1)
							.getStockFinalTablakardex(), 0.0)) {
				col = 0;
				mapaCol.put(col, ConstantesReporteDinamico.DIA);
				col++;
				if (esMateriaPrima) {
					Long codigoVariableHumedad = Long.parseLong(ManejadorPropiedades
							.obtenerPropiedadPorClave(CODIGO_VARIABLE_CALIDAD_HUMEDAD));

					Productovariablecalidad productoVariableCalidad = ProductoVariableCalidadQuerier
							.obtenerProductoVariableCalidad(producto.getPkCodigoProducto(), codigoVariableHumedad);
					tieneHumedadIngreso = productoVariableCalidad != null;
					tieneHumedadConsumo = EquivalenciasccvarcalidadQuerier
							.verificarSiPoseeHumedad(producto.getPkCodigoProducto());

					// -CREACION DE COLUMNAS
					if (tieneHumedadIngreso) {
						mapaCol.put(col, ConstantesReporteDinamico.INGRESO_TM_HUM);
						col++;
						mapaCol.put(col, ConstantesReporteDinamico.FACT_HUM_INGR);
						col++;
						mapaCol.put(col, ConstantesReporteDinamico.INGRESO_TM_SECAS);
						// determinamos la columna de ingreso para el ajuste
						columnaIngreso = col;
						col++;
					} else {
						mapaCol.put(col, ConstantesReporteDinamico.INGRESO);
						// determinamos la columna de ingreso para el ajuste
						columnaIngreso = col;
						col++;
					}
					mapaCol.put(col, ConstantesReporteDinamico.INGRESO_ACUM);
					col++;
					if (tieneHumedadConsumo) {
						mapaCol.put(col, ConstantesReporteDinamico.CONSUMO_TM_HUM);
						col++;
						mapaCol.put(col, ConstantesReporteDinamico.FACT_HUM_CONSUMO);
						col++;
						mapaCol.put(col, ConstantesReporteDinamico.CONSUMO_TM_SECAS);
						// determinamos la columna de consumo para el ajuste
						columnaconsumo = col;
						col++;
					} else {
						mapaCol.put(col, ConstantesReporteDinamico.CONSUMO);
						// determinamos la columna de consumo para el ajuste
						columnaconsumo = col;
						col++;
					}
					mapaCol.put(col, ConstantesReporteDinamico.CONSUMO_ACUM);
					col++;

					if (esmovimientosLogistico) {
						mapaCol.put(col, "Movimiento Logistico");
						col++;
					}

					mapaCol.put(col, ConstantesReporteDinamico.STOCK_FINAL + ConstantesReporteDinamico.ESPACIO_BLANCO
							+ nombreUnidadMedida);
					// determinamos la columna de stockfinal para el ajuste
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
						mapaCol.put(col, nombreProducto);
						col++;
					}

					ParametroSistema registroParametroSis;
					registroParametroSis = ParametroSistemaQuerier.obtenerParametroSistema("PRODUCTOS_DESPACHO");

					String[] codigosProductos = registroParametroSis.getValorParametro().split(",");

					for (String codPro : codigosProductos) {
						if (codPro.equals(producto.getPkCodigoProducto().toString())) {
							tieneDespacho = true;
						}
					}

					if (tieneDespacho) {
						mapaCol.put(col, ConstantesReporteDinamico.DESPACHO);
						col++;
					}

					if (producto.getPkCodigoProducto().compareTo(codigoClinker) == 0
							&& codigoProceso.compareTo(codigoProcesoClinkerHH) == 0) {
						mapaCol.put(col, ConstantesReporteDinamico.INGRESO_HORNOSHH);
					} else {
						mapaCol.put(col, ConstantesReporteDinamico.INGRESO);
						// determinamos la columna de ingreso para el ajuste
						columnaIngreso = col;
					}
					col++;
					if (producto.getPkCodigoProducto().compareTo(codigoClinker) == 0
							&& codigoProceso.compareTo(codigoProcesoClinkerHH) == 0) {
						mapaCol.put(col, ConstantesReporteDinamico.INGRESO_HORNOSHV);
						col++;
						mapaCol.put(col, ConstantesReporteDinamico.INGREGO_TOTAL);
						// determinamos la columna de ingreso para el ajuste
						columnaIngreso = col;
						col++;
					}
					mapaCol.put(col, ConstantesReporteDinamico.CONSUMO);
					// determinamos la columna de consumo para el ajuste
					columnaconsumo = col;
					col++;

					if (esmovimientosLogistico) {
						mapaCol.put(col, "Movimiento Logistico");
						col++;
					}

					mapaCol.put(col, ConstantesReporteDinamico.STOCK_FINAL + ConstantesReporteDinamico.ESPACIO_BLANCO
							+ nombreUnidadMedida);
					// determinamos la columna de stockfinal para el ajuste
					columnaStockFinal = col;
					col++;
				}

				if (var.indexOf(codigoProceso.toString()) >= 0) {

					mapaCol.put(col, ConstantesReporteDinamico.STOCK_FISICO);
					col++;
					mapaCol.put(col, ConstantesReporteDinamico.DIFERENCIA);
					col++;
					mapaCol.put(col, ConstantesReporteDinamico.PORCEN_VARIACION);
					col++;

				}
				if (codigoProceso.compareTo(Long.valueOf(ManejadorPropiedades
						.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO))) == 0) {
					for (int i = 0; i < nombreMediosAlmacenamiento.size(); i++) {
						mapaCol.put(col, nombreMediosAlmacenamiento.get(i));
						col++;
					}
				}

				int[] valores = new int[mapaCol.size()];
				for (int i = 0; i < valores.length; i++) {
					valores[i] = 5;
				}

				PdfPTable table = new PdfPTable(mapaCol.size());
				table.setWidths(valores);
				table.setWidthPercentage(getWidthTable(mapaCol.size()));
				table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);

				table.addCell(crearCelda(producto.getDescripcionProducto(), PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_LEFT,
						mapaCol.size(), PdfCell.NO_BORDER, fontBold));

				for (int i = 0; i < mapaCol.size(); i++) {

					table.addCell(crearCelda(mapaCol.get(i), PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 0, 3, fontBold));
				}

				table.addCell(crearCelda(
						ConstantesReporteDinamico.SALDO_INICIAL
								+ ConstantesReporteDinamico.ESPACIO_TAB
								+ nombreUnidadMedida
								+ ConstantesReporteDinamico.ESPACIO_TAB
								+ NumberUtil.numeroMayorCeroRedondeadoFUS(kardexProdcuto_proceso.get(0)
										.getSaldoInicialTablakardex()), PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_LEFT,
						mapaCol.size(), PdfCell.NO_BORDER, fontBold));

				// LLENAR DETALLE

				double ingresoAcum = 0d, consumoAcum = 0d, tmHumXFacHum = 0d, tmHum = 0d;
				double humXfacConsumo = 0d, humedad = 0d;
				int num = 0;
				int colVaria = -1;
				int colFactorIngreso = -1;
				int colFactorConsumo = -1;
				int contador = 0;
				int colDespacho = -1;

				Map<Integer, Double> MapSuma = new HashMap<Integer, Double>();
				Double saldoFinalHH = -1.0;

				try {

					for (Tablakardex tablakardex : kardexProdcuto_proceso) {
						mapaCol = new HashMap<Integer, String>();
						col = 0;

						mapaCol.put(col, FechaUtil.obtenerDiaFecha(tablakardex.getFechaTablakardex()));
						calcularTotales(MapSuma, col, 0.0);
						col++;
						Double ingresoTablakardex = NumberUtil.Redondear2Decimales(tablakardex.getIngresoTablakardex());
						Double consumoTablakardex = NumberUtil.Redondear2Decimales(tablakardex.getConsumoTablakardex());
						// Double consumoHumedadTablakardex =
						// NumberUtil.Redondear2Decimales(tablakardex.getConsumoHumedadTablakardex());
						if (esMateriaPrima) {
							ingresoAcum += ingresoTablakardex;
							consumoAcum += consumoTablakardex;
							if (tieneHumedadIngreso) {
								mapaCol.put(col, tablakardex.getIngresoHumedadTablakardex() + "");
								calcularTotales(MapSuma, col, tablakardex.getIngresoHumedadTablakardex());
								col++;
								if (ingresoTablakardex != 0) {
									double factorHumIngreso = (1 - (ingresoTablakardex / tablakardex
											.getIngresoHumedadTablakardex())) * 100;
									// Nuevo calculo para hallar el Promedio
									// Humedad
									tmHumXFacHum += (factorHumIngreso * tablakardex.getIngresoHumedadTablakardex());
									tmHum += tablakardex.getIngresoHumedadTablakardex();
									mapaCol.put(col, NumberUtil.Redondear2Decimales(factorHumIngreso) + "");
									colFactorIngreso = col;
									calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(factorHumIngreso));
									if (NumberUtil.decimalMayorMasMenos(factorHumIngreso, 0.0)) {
										contador++;
									}
									col++;
								} else {
									mapaCol.put(col, "0.0");
									calcularTotales(MapSuma, col, 0.0);
									col++;
								}
								mapaCol.put(col, NumberUtil.Redondear2Decimales(ingresoTablakardex) + "");
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(ingresoTablakardex));
								col++;
							} else {
								mapaCol.put(col, NumberUtil.Redondear2Decimales(ingresoTablakardex) + "");
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(ingresoTablakardex));
								col++;
							}
							mapaCol.put(col, NumberUtil.Redondear2Decimales(ingresoAcum) + "");
							calcularTotales(MapSuma, col, 0.0);
							col++;
							if (tieneHumedadConsumo) {
								mapaCol.put(col, NumberUtil.Redondear2Decimales(tablakardex.getConsumoHumedadTablakardex()) + "");
								calcularTotales(MapSuma, col,
										NumberUtil.Redondear2Decimales(tablakardex.getConsumoHumedadTablakardex()));
								col++;
								if (consumoTablakardex != 0) {

									// double factorHumConsumo = (1 -
									// (consumoTablakardex / tablakardex
									// .getConsumoHumedadTablakardex())) * 100;
									//
									// // Nuevo calculo para hallar el Promedio
									// // Humedad
									// humXfacConsumo += (factorHumConsumo *
									// tablakardex.getConsumoHumedadTablakardex());
									// humedad +=
									// tablakardex.getConsumoHumedadTablakardex();

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
									mapaCol.put(col, NumberUtil.Redondear2Decimales(factorHumConsumo) + "");
									calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(factorHumConsumo));
									col++;
								} else {
									mapaCol.put(col, "0.0");
									calcularTotales(MapSuma, col, 0.0);
									col++;
								}
								mapaCol.put(col, NumberUtil.Redondear2Decimales(consumoTablakardex) + "");
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(consumoTablakardex));
								col++;

							} else {
								mapaCol.put(col, NumberUtil.Redondear2Decimales(consumoTablakardex) + "");
								calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(consumoTablakardex));
								col++;
							}
							mapaCol.put(col, NumberUtil.Redondear2Decimales(consumoAcum) + "");
							calcularTotales(MapSuma, col, 0.0);
							col++;

							Double movimientoLogistico = 0d;
							if (esmovimientosLogistico) {
								movimientoLogistico = mapaDiamovimientosLogisticos.get(FechaUtil
										.convertirDateToString(tablakardex.getFechaTablakardex()));

								if (movimientoLogistico == null) {
									movimientoLogistico = 0d;
								}
								mapaCol.put(col, NumberUtil.Redondear2Decimales(movimientoLogistico) + "");
								acumuladoMovimientoLogistico += movimientoLogistico;
								calcularTotales(MapSuma, col, movimientoLogistico);
								col++;
							}

							mapaCol.put(col, NumberUtil.Redondear2Decimales(tablakardex.getStockFinalTablakardex()) + "");
							sumaStockFinal = tablakardex.getStockFinalTablakardex();
							calcularTotales(MapSuma, col, 0.0);
							col++;
						} else {

							Map<String, Double> MapValorComp = new HashMap<String, Double>();
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

									mapaCol.put(col, NumberUtil.Redondear2Decimales(MapValorComp.get(nombresComponentes.get(i)))
											+ "");
									calcularTotales(MapSuma, col,
											NumberUtil.Redondear2Decimales(MapValorComp.get(nombresComponentes.get(i))));
									col++;
								} else {
									mapaCol.put(col, "0.0");
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
								mapaCol.put(col, totMovimiento.toString());
								calcularTotales(MapSuma, col, totMovimiento);
								col++;
							}

							mapaCol.put(col, NumberUtil.Redondear2Decimales(ingresoTablakardex) + "");
							calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(ingresoTablakardex));
							col++;
							Double ingresoHHVV = 0.0;
							if (producto.getPkCodigoProducto().compareTo(codigoClinker) == 0
									&& codigoProceso.compareTo(codigoProcesoClinkerHH) == 0) {

								if (kardexParteDiarioHHVV != null) {

									Tablakardex tablakardexhhvv = tablaKardexLogic.obtenerKrdexHVSegunFecha(
											kardexParteDiarioHHVV, tablakardex.getFechaTablakardex());

									if (tablakardexhhvv != null) {
										ingresoHHVV = tablakardexhhvv.getIngresoTablakardex();
										mapaCol.put(col, ingresoHHVV + "");
										calcularTotales(MapSuma, col, ingresoHHVV);
										col++;

									} else {
										mapaCol.put(col, "0.0");
										calcularTotales(MapSuma, col, 0.0);
										col++;

									}

								}
								mapaCol.put(col, (ingresoHHVV + ingresoTablakardex) + "");
								calcularTotales(MapSuma, col, (ingresoHHVV + ingresoTablakardex));
								col++;

							}
							mapaCol.put(col, NumberUtil.Redondear2Decimales(consumoTablakardex) + "");
							calcularTotales(MapSuma, col, NumberUtil.Redondear2Decimales(consumoTablakardex));
							col++;

							Double movimientoLogistico = 0d;
							if (esmovimientosLogistico) {
								movimientoLogistico = mapaDiamovimientosLogisticos.get(FechaUtil
										.convertirDateToString(tablakardex.getFechaTablakardex()));

								if (movimientoLogistico == null) {
									movimientoLogistico = 0d;
								}
								mapaCol.put(col, NumberUtil.Redondear2Decimales(movimientoLogistico) + "");
								acumuladoMovimientoLogistico += movimientoLogistico;
								calcularTotales(MapSuma, col, movimientoLogistico);
								col++;
							}

							// Particularidad en el calculo del Saldo Final en
							// Hornos HH
							if (producto.getPkCodigoProducto().compareTo(codigoClinker) == 0
									&& codigoProceso.compareTo(codigoProcesoClinkerHH) == 0) {
								// CALCULAR CON EL SALDO FINAL
								// Si saldoFinalHH = -1.0 significa que es el
								// primer
								// recorrido
								if (saldoFinalHH == -1.0) {
									mapaCol.put(
											col,
											NumberUtil.Redondear2Decimales(tablakardex.getStockFinalTablakardex() + ingresoHHVV
													)
													+ "");
									saldoFinalHH = NumberUtil.Redondear2Decimales(tablakardex.getStockFinalTablakardex()
											+ ingresoHHVV );
									sumaStockFinal = saldoFinalHH;
								} else {
									mapaCol.put(
											col,
											NumberUtil
													.Redondear2Decimales(((saldoFinalHH + ingresoHHVV + ingresoTablakardex) - consumoTablakardex)
															+ movimientoLogistico)
													+ "");
									saldoFinalHH = ((saldoFinalHH + ingresoHHVV + ingresoTablakardex) - consumoTablakardex)
											+ movimientoLogistico;
									sumaStockFinal = saldoFinalHH;
								}

								calcularTotales(MapSuma, col, 0.0);
							} else {
								mapaCol.put(col, NumberUtil.Redondear2Decimales(tablakardex.getStockFinalTablakardex()) + "");
								sumaStockFinal = tablakardex.getStockFinalTablakardex();
								calcularTotales(MapSuma, col, 0.0);
							}
							col++;
						}
						// Columnas adicionales segun procesos ingresados en el
						// properties
						if (var.indexOf(codigoProceso.toString()) >= 0) {
							double dif = 0.0;
							if (tablakardex.getStockFinalTablakardex() != null && tablakardex.getStockFisicoTablakardex() != null) {
								dif = tablakardex.getStockFisicoTablakardex() - tablakardex.getStockFinalTablakardex();
							}
							mapaCol.put(col, NumberUtil.Redondear2Decimales(tablakardex.getStockFisicoTablakardex()) + "");
							calcularTotales(MapSuma, col, 0.0);
							col++;
							// Diferencia
							mapaCol.put(col, NumberUtil.Redondear2Decimales(dif) + "");
							calcularTotales(MapSuma, col, 0.0);
							col++;

							// Columna % Variacion
							mapaCol.put(col, NumberUtil.Redondear2Decimales(tablakardex.getVariacionTablakardex()) + "");
							calcularTotales(MapSuma, col, tablakardex.getVariacionTablakardex());
							// Se hallara el promedio solo con los %Variacion >
							// que
							// 0
							if (NumberUtil.decimalMayorMasMenos(tablakardex.getVariacionTablakardex(), 0.0)) {
								contador++;
							}
							// Asigno Columna de % Variacion para luego
							// al final colocar el promedio debajo de dicha
							// columna
							colVaria = col;
							col++;

						}

						if (codigoProceso.compareTo(Long.valueOf(ManejadorPropiedades
								.obtenerPropiedadPorClave(CODIGO_PROCESO_MOLIENDA_CEMENTO))) == 0) {

							Map<String, Double> mapa = mapaMediciones.get(tablakardex.getFechaTablakardex());

							for (int i = 0; i < nombreMediosAlmacenamiento.size(); i++) {
								if (mapa != null) {
									Double valor = NumberUtil.validateDouble(mapa.get(nombreMediosAlmacenamiento.get(i)));
									mapaCol.put(col, NumberUtil.redondear(valor, 4) + "");
									calcularTotales(MapSuma, col, NumberUtil.redondear(valor, 4));
									col++;
								} else {
									mapaCol.put(col, "");
									calcularTotales(MapSuma, col, 0.0);
									col++;
								}
							}
						}

						// Se imprime todo el detalle de la tabla
						for (int i = 0; i < mapaCol.size(); i++) {
							// Dandole el estilo de Zebra
							if (i == 0) {
								table.addCell(crearCelda(mapaCol.get(i), PdfCell.ALIGN_CENTER, 0, 0, obtenerColorFila(num)));
								continue;
							}
							Double numero2 = NumberUtil.convertirStringToDouble(mapaCol.get(i));
							if (NumberUtil.decimalMayorMasMenos(numero2, 0.0)) {
								String numero = NumberUtil.formatearNumeroLocaleUS(NumberUtil.convertirStringToDouble(mapaCol
										.get(i)));
								table.addCell(crearCelda(numero, PdfCell.ALIGN_RIGHT, 0, 0, obtenerColorFila(num)));
							} else {
								table.addCell(crearCelda("", PdfCell.ALIGN_RIGHT, 0, 0, obtenerColorFila(num)));
							}
						}

						num++;

					}

					for (int i = 0; i < MapSuma.size(); i++) {
						if (MapSuma.get(i) != null) {
							// Si la Suma de la columna es > 0 se mostrara
							if (MapSuma.get(i) > 0 || (MapSuma.get(i) * -1) > 0) {

								// COLUMNA VARIACION
								// SE CALCULA EL PROMEDIO
								if (colVaria == i) {
									if (contador > 0) {
										table.addCell(crearCelda(
												NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(MapSuma.get(i)
														/ contador)), PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0,
												PdfCell.NO_BORDER, fontBold));
									} else {
										table.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0,
												PdfCell.NO_BORDER, fontBold));
									}

									continue;
								}

								// COLUMNA DESPACHO
								if (colDespacho == i) {
									table.addCell(crearCelda(
											NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(MapSuma.get(i))),
											PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0, PdfCell.NO_BORDER, fontBold));
									continue;
								}

								// COLUMNA FACTOR HUMEDAD
								// SE CALCULA EL PROMEDIO
								if (colFactorIngreso == i) {
									if (contador > 0) {
										if (tmHum <= 0) {
											tmHum = 1d;
										}
										table.addCell(crearCelda(
												NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(tmHumXFacHum
														/ tmHum)), PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0,
												PdfCell.NO_BORDER, fontBold));
									} else {
										table.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0,
												PdfCell.NO_BORDER, fontBold));
									}
									continue;
								}

								// COLUMNA FACTOR HUMEDAD
								// SE CALCULA EL PROMEDIO

								if (colFactorConsumo == i) {

									if (humedad > 0) {

										table.addCell(crearCelda(
												NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(humXfacConsumo
														/ humedad)), PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0,
												PdfCell.NO_BORDER, fontBold));
									} else {
										table.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0,
												PdfCell.NO_BORDER, fontBold));
									}
									continue;
								}

								// TODAS LAS DEMAS COLUMNAS
								table.addCell(crearCelda(
										NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(MapSuma.get(i))),
										PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0, PdfCell.NO_BORDER, fontBold));
							} else {
								table.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 0, PdfCell.NO_BORDER,
										fontBold));
							}

						} else {
							table.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 0, PdfCell.NO_BORDER,
									fontBold));
						}
					}
					if (anio != null && mes != null) {
						DetalleParteTecnicoConsumoComponentesBean lista;
						lista = generarParteTecnicoLogic.obtenerDetalleParteTecnicoConsumoparaPDDAO(producto, codigoLineaNegocio,
								mes, anio);
						for (int i = 0; i < MapSuma.size(); i++) {
							if (i == 0) {
								table.addCell(crearCelda(AJUSTE_LABEL, PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_LEFT, 0,
										PdfCell.NO_BORDER, fontBold));
							} else if (i == columnaIngreso) {
								table.addCell(crearCelda(
										(NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(lista.getIngreso()))),
										PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0, PdfCell.NO_BORDER, fontBold));
							} else if (i == columnaconsumo) {
								table.addCell(crearCelda(
										(NumberUtil.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(lista.getConsumo()))),
										PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0, PdfCell.NO_BORDER, fontBold));
							} else if (i == columnaStockFinal) {
								table.addCell(crearCelda(
										(NumberUtil
												.formatearNumeroLocaleUS(NumberUtil.Redondear2Decimales(((sumaStockFinal + lista
														.getIngreso()) - lista.getConsumo()) + 0d))),
										PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_RIGHT, 0, PdfCell.NO_BORDER, fontBold));
							} else {
								table.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 0, PdfCell.NO_BORDER,
										fontBold));
							}
						}

						table.addCell(crearCelda(" ", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_CENTER, MapSuma.size(),
								PdfCell.NO_BORDER, fontBold));
					}

				} catch (Exception e) {
					logger.error(e.getMessage());
				}

				return table;
			}
		}
		return null;
	}

	public static List<String> obtenerNombreMediosAlmacenamiento(List<MedicionDiariaDTO> mediciones) {

		Map<String, String> mapMediciones = new HashMap<String, String>();
		for (MedicionDiariaDTO medicionDiariaDTO : mediciones) {
			if (medicionDiariaDTO.getCantidadMedioAlmacenamiento() != null
					&& medicionDiariaDTO.getCantidadMedioAlmacenamiento() > 0) {

				mapMediciones.put(medicionDiariaDTO.getNombreTipoMedioAlmacenamiento(),
						medicionDiariaDTO.getNombreTipoMedioAlmacenamiento());
			}
		}
		ArrayList<String> listaNombre = new ArrayList<String>(mapMediciones.keySet());
		Collections.sort(listaNombre);

		return listaNombre;
	}

	public static Map<Date, Map<String, Double>> obtenerPorfechaMedicionesDiarias(List<MedicionDiariaDTO> mediciones) {
		Map<Date, Map<String, Double>> mapDiaPuestoTrabajos = new HashMap<Date, Map<String, Double>>();
		Map<Date, Date> mapMediciones = new HashMap<Date, Date>();
		for (MedicionDiariaDTO medicionDiariaDTO : mediciones) {
			mapMediciones.put(medicionDiariaDTO.getFechaRegistroMedicion(), medicionDiariaDTO.getFechaRegistroMedicion());
		}
		Map<String, Double> medicionPorMedioAlmacenamiento;
		for (Date medicionDiariaDTO2 : mapMediciones.keySet()) {
			EqualPredicate nameEqlPredicate = new EqualPredicate(medicionDiariaDTO2);
			BeanPredicate beanPredicate = new BeanPredicate("fechaRegistroMedicion", nameEqlPredicate);
			Collection collection = CollectionUtils.select(mediciones, beanPredicate);
			medicionPorMedioAlmacenamiento = new HashMap<String, Double>();
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				MedicionDiariaDTO object = (MedicionDiariaDTO) iterator.next();
				medicionPorMedioAlmacenamiento.put(object.getNombreTipoMedioAlmacenamiento(),
						object.getCantidadMedioAlmacenamiento());

			}

			mapDiaPuestoTrabajos.put(medicionDiariaDTO2, medicionPorMedioAlmacenamiento);
		}

		return mapDiaPuestoTrabajos;
	}

	/** fin Prueba Stephany * */

	private static void calcularTotales(Map<Integer, Double> mapSuma, int col, Double d) {
		Double suma = mapSuma.get(col);
		if (suma != null) {
			mapSuma.put(col, suma + d);
		} else {
			mapSuma.put(col, d);
		}

	}

	private static int getWidthTable(int size) {
		if (size > 0 && size <= 4) {
			return 20;
		} else if (size > 4 && size < 10) {
			return 50;
		} else {
			return 100;
		}
	}

	private static BaseColor obtenerColorFila(int num) {
		if (num % 2 == 0) {
			return new BaseColor(214, 227, 242);
		} else {
			return new BaseColor(255, 255, 255);
		}
	}

	private static List<Tablakardex> traer_Kardex_Prod_Proc(Long codigoProceso, Long codigoProducto,
			List<Tablakardex> kardexParteDiario, Boolean esMateriaPrima, Long codigoLineaNegocio) {
		List<Tablakardex> kardexProdcuto_proceso = new ArrayList<Tablakardex>();

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

	/***************************************************************************
	 * Agregado por Jordan Torres
	 * 
	 * @param list
	 */

	public static PdfPTable espacioEnBlanco() {
		PdfPTable tabla = new PdfPTable(5);
		tabla.addCell(crearCelda(" ", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 5, PdfPCell.NO_BORDER, fontTitulo));
		return tabla;
	}

	/*
	 * EQUIVALENCIA SCC VARIABLE CALIDAD
	 */
	public static PdfPTable crearCabeceraYDetalleReporteVarCalidad(Proceso proceso, ReporteVarCalidadPuestoBean reportePuesto,
			String fechaInicio, String fechaFin) {
		Puestotrabajo puestoTrabajo = reportePuesto.getPuestotrabajo();
		int columnas = reportePuesto.getColspan() + 1;
		int[] sizeColumnas = new int[columnas];
		for (int i = 0; i < sizeColumnas.length; i++) {
			sizeColumnas[i] = 5;
		}
		PdfPTable tabla = new PdfPTable(columnas);
		try {

			tabla.setWidths(sizeColumnas);
			tabla.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);

			tabla.addCell(crearCelda("Puesto Trabajo : " + puestoTrabajo.getDescripcionPuestotrabajo(), PdfPTable.ALIGN_MIDDLE,
					PdfPTable.ALIGN_LEFT, columnas, 1, 0, BaseColor.WHITE, fontBold));
			tabla.addCell(crearCelda("Proceso : " + proceso.getDescripcionProceso(), PdfPTable.ALIGN_MIDDLE,
					PdfPTable.ALIGN_LEFT, columnas, 1, 0, BaseColor.WHITE, fontBold));

			tabla.addCell(crearCelda("Dia", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 3, 15, colorFondo(248, 248, 248),
					fontBold));

			for (ReporteVarCalidadProductoBean i : reportePuesto.getProductos()) {
				tabla.addCell(crearCelda(i.getProducto().getNombreProducto(), PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER,
						i.getColspan(), 1, 15, colorFondo(248, 248, 248), fontBold));
			}

			for (ReporteVarCalidadProductoBean i : reportePuesto.getProductos()) {
				for (ReporteVarCalidadProductoComponenteBean componente : i.getProductoComponente()) {
					tabla.addCell(crearCelda(componente.getComponente().getProductoByFkCodigoProductoComponente()
							.getNombreProducto(), PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, componente.getColspan(), 1, 15,
							colorFondo(248, 248, 248), fontBold));
				}
			}

			for (ReporteVarCalidadProductoBean i : reportePuesto.getProductos()) {
				for (ReporteVarCalidadProductoComponenteBean componente : i.getProductoComponente()) {
					for (ReporteEquivalenciasccvarcalidadBean j : componente.getComponenteDescripcion()) {

						tabla.addCell(crearCelda(j.getDatoCalidad().getDescripVarCalidad(), PdfPTable.ALIGN_MIDDLE,
								PdfPTable.ALIGN_CENTER, 1, 1, 15, colorFondo(248, 248, 248), fontBold));
					}
				}
			}

			// LLENADO DETALLE

			Map<Integer, Integer> mapaFila = new HashMap<Integer, Integer>();
			Map<Integer, Double> MapSuma = new HashMap<Integer, Double>();
			int col = 0;

			Integer diaIni = Integer.parseInt(FechaUtil.obtenerDiaFecha(FechaUtil.FormaterFecha(fechaInicio)));
			long diff = FechaUtil.diffFecha(FechaUtil.FormaterFecha(fechaFin), FechaUtil.FormaterFecha(fechaInicio));
			for (long k = 0; k < diff; k++) {
				col = 0;
				tabla.addCell(crearCelda((diaIni) + "", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 15, fontBold));
				calcularTotales(MapSuma, col, 0.0);
				col++;
				for (ReporteVarCalidadProductoBean i : reportePuesto.getProductos()) {
					for (ReporteVarCalidadProductoComponenteBean componente : i.getProductoComponente()) {
						for (ReporteEquivalenciasccvarcalidadBean variables : componente.getComponenteDescripcion()) {

							if (variables.getVarCalDia()[diaIni - 1] != null) {
								tabla.addCell(crearCelda(
										NumberUtil.Redondear2Decimales(Double.parseDouble(variables.getVarCalDia()[diaIni - 1]))
												+ "", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 15, fontBold));

								calcularTotales(MapSuma, col,
										NumberUtil.Redondear2Decimales(Double.parseDouble(variables.getVarCalDia()[diaIni - 1])));
								sumarFilas(mapaFila, col);
								col++;
							} else {
								tabla.addCell(crearCelda("", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 15, fontBold));
								calcularTotales(MapSuma, col, 0.0);
								col++;
							}
						}
					}
				}
				diaIni++;
			}

			for (int i = 0; i < MapSuma.size(); i++) {
				if (i == 0) {
					tabla.addCell(crearCelda("Promedio", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 1, 15,
							colorFondo(248, 248, 248), fontBold));
					continue;
				}
				if (MapSuma.get(i) != null && mapaFila.get(i) != null) {
					if (MapSuma.get(i) > 0 || (MapSuma.get(i) * -1) > 0) {
						tabla.addCell(crearCelda(NumberUtil.Redondear2Decimales(MapSuma.get(i) / mapaFila.get(i)) + "",
								PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 1, 1, 15, colorFondo(248, 248, 248), fontBold));
					} else {
						tabla.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 1, 1, 15,
								colorFondo(248, 248, 248), fontBold));
					}
				} else {
					tabla.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 1, 1, 15,
							colorFondo(248, 248, 248), fontBold));
				}
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tabla;
	}

	/*
	 * PRODUCTO VARIABLE CALIDAD
	 */
	public static PdfPTable crearCabeceraYDetalleReporteVarCalidad(Proceso proceso,
			List<ReporteVarCalidadProductoComponenteBean> productosVarableCalidad, int columnas, String diaInicio, String diaFin) {

		int colum = columnas + 1;
		int[] sizeColumnas = new int[colum];
		for (int i = 0; i < sizeColumnas.length; i++) {
			sizeColumnas[i] = 3;
		}
		PdfPTable tabla = new PdfPTable(colum);
		try {
			tabla.setWidths(sizeColumnas);
			tabla.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);

			tabla.addCell(crearCelda(proceso.getDescripcionProceso(), PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, colum, 1,
					PdfCell.NO_BORDER, BaseColor.WHITE, fontBold));

			tabla.addCell(crearCelda("Dia", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 2, 15, colorFondo(248, 248, 248),
					fontBold));

			for (ReporteVarCalidadProductoComponenteBean reporteProducto : productosVarableCalidad) {
				Producto producto = reporteProducto.getProducto();
				tabla.addCell(crearCelda(producto.getNombreProducto(), PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 1, 15,
						colorFondo(248, 248, 248), fontBold));
			}

			for (ReporteVarCalidadProductoComponenteBean reporteProducto : productosVarableCalidad) {
				for (ReporteEquivalenciasccvarcalidadBean datoCalidad : reporteProducto.getComponenteDescripcion()) {
					Productovariablecalidad variableCalidad = datoCalidad.getVariableProductoCalidad();
					tabla.addCell(crearCelda(variableCalidad.getVariablecalidad().getNombreVariablecalidad(),
							PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 1, 15, colorFondo(248, 248, 248), fontBold));
				}

			}

			// LLENADO DETALLE

			Map<Integer, Integer> mapaFila = new HashMap<Integer, Integer>();
			Map<Integer, Double> MapSuma = new HashMap<Integer, Double>();
			int col = 0;

			Integer diaIni = Integer.parseInt(FechaUtil.obtenerDiaFecha(FechaUtil.FormaterFecha(diaInicio)));
			long diff = FechaUtil.diffFecha(FechaUtil.FormaterFecha(diaFin), FechaUtil.FormaterFecha(diaInicio));
			for (long i = 0; i < diff; i++) {
				col = 0;
				tabla.addCell(crearCelda((diaIni) + "", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 15, fontBold));
				calcularTotales(MapSuma, col, 0.0);
				col++;
				for (ReporteVarCalidadProductoComponenteBean reporteProducto : productosVarableCalidad) {
					for (ReporteEquivalenciasccvarcalidadBean datoCalidad : reporteProducto.getComponenteDescripcion()) {
						if (datoCalidad.getVarCalDia()[diaIni - 1] != null) {
							tabla.addCell(crearCelda(
									NumberUtil.Redondear2Decimales(Double.parseDouble(datoCalidad.getVarCalDia()[diaIni - 1]))
											+ "", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 15, fontBold));
							calcularTotales(MapSuma, col,
									NumberUtil.Redondear2Decimales(Double.parseDouble(datoCalidad.getVarCalDia()[diaIni - 1])));
							sumarFilas(mapaFila, col);
							col++;
						} else {
							tabla.addCell(crearCelda("", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 15, fontBold));
							calcularTotales(MapSuma, col, 0.0);
							col++;
						}
					}
				}
				diaIni++;
			}

			for (int i = 0; i < MapSuma.size(); i++) {
				if (i == 0) {
					tabla.addCell(crearCelda("Promedio", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 1, 1, 15,
							colorFondo(248, 248, 248), fontBold));
					continue;
				}
				if (MapSuma.get(i) != null && mapaFila.get(i) != null) {
					if (MapSuma.get(i) > 0 || (MapSuma.get(i) * -1) > 0) {
						tabla.addCell(crearCelda(NumberUtil.Redondear2Decimales(MapSuma.get(i) / mapaFila.get(i)) + "",
								PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 1, 1, 15, colorFondo(248, 248, 248), fontBold));
					} else {
						tabla.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 1, 1, 15,
								colorFondo(248, 248, 248), fontBold));
					}
				} else {
					tabla.addCell(crearCelda("", PdfCell.ALIGN_MIDDLE, PdfPCell.ALIGN_CENTER, 1, 1, 15,
							colorFondo(248, 248, 248), fontBold));
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabla;
	}

	

	private static void sumarFilas(Map<Integer, Integer> map, int col) {
		Integer f = 0;
		if (map.get(col) != null) {
			f = map.get(col);
			f++;
			map.put(col, f);
		} else {
			f++;
			map.put(col, f);
		}
	}

	private static List<Puestotrabajo> hallarPuestoTrabajo(List<Productovariablevariacion> proVarVariacion) {
		ArrayList<Puestotrabajo> listaPuestotrabajo = null;
		Map<Long, Puestotrabajo> MapPuestoTrabajo = new HashMap<Long, Puestotrabajo>();

		for (Productovariablevariacion pvv : proVarVariacion) {
			Puestotrabajo puestoTraba = pvv.getPuestotrabajo();
			MapPuestoTrabajo.put(puestoTraba.getPkCodigoPuestotrabajo(), puestoTraba);
		}
		listaPuestotrabajo = new ArrayList<Puestotrabajo>(MapPuestoTrabajo.values());
		Collections.sort(listaPuestotrabajo, new Comparator<Puestotrabajo>() {

			public int compare(Puestotrabajo o1, Puestotrabajo o2) {
				int comparar = o1.getNombrePuestotrabajo().compareTo(o2.getNombrePuestotrabajo());
				return comparar;
			}

		});

		return listaPuestotrabajo;
	}

	private static List<Producto> hallarProductoSegunPuestoTrabajo(List<Productovariablevariacion> proVarVariacion,
			Puestotrabajo puestos) {
		List<Producto> pro;
		Map<Long, Producto> mapProductos = new HashMap<Long, Producto>();
		for (Productovariablevariacion pvv : proVarVariacion) {
			Puestotrabajo puestoTraba = pvv.getPuestotrabajo();

			if (puestoTraba.getPkCodigoPuestotrabajo().compareTo(puestos.getPkCodigoPuestotrabajo()) == 0) {
				mapProductos.put(pvv.getProducto().getPkCodigoProducto(), pvv.getProducto());
			}
		}
		pro = new ArrayList<Producto>(mapProductos.values());
		return pro;
	}

	

	public static BaseColor colorFondo(int rojo, int verde, int azul) {
		return new BaseColor(rojo, verde, azul);
	}

	// CABECERA DEL REPORTE
	public static PdfPTable crearCabeceraYDetalleReporteVarCalidad(String division, String sociedad, String unidad, String img) {
		PdfPTable tabla = new PdfPTable(8);

		try {
			tabla.setWidths(new int[] { 5, 15, 5, 15, 5, 15, 5, 15 });

			tabla.setWidthPercentage(100);
			Image imagenCPSAA;

			imagenCPSAA = Image.getInstance(img);
			imagenCPSAA.setWidthPercentage(80);
			PdfPCell cell = new PdfPCell();
			cell.addElement(imagenCPSAA);
			cell.setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setBorder(PdfCell.NO_BORDER);
			tabla.addCell(cell);
		} catch (BadElementException e) {
			logger.error(e.getMessage());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		}

		tabla.addCell(crearCelda(" ", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 8, PdfPCell.NO_BORDER, fontTitulo));
		tabla.addCell(crearCelda(" ", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 8, PdfPCell.NO_BORDER, fontTitulo));
		tabla.addCell(crearCelda(" ", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 8, PdfPCell.NO_BORDER, fontTitulo));
		tabla.addCell(crearCelda(DIVISION_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(division, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(SOCIEDAD_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(sociedad, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(UNIDAD_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(unidad, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(FECHA_CREACION_LABEL, PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(FechaUtil.formatearFechaHHMMSS(new Date()) + "", PdfCell.ALIGN_MIDDLE, PdfCell.ALIGN_LEFT, 0,
				PdfCell.NO_BORDER, fontBold));

		return tabla;
	}

	// TITULO DEL REPORTE
	public static PdfPTable crearCabeceraYDetalleReporteVarCalidad(String titulo, Integer mes, Integer anio) {
		PdfPTable tabla = new PdfPTable(5);
		tabla.setWidthPercentage(100);

		String tituloPrincipal = titulo + ": " + FechaUtil.numeroMesANombreMes(Short.parseShort(mes.toString())).toUpperCase()
				+ " " + anio;
		tabla.addCell(crearCelda(" ", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 5, PdfPCell.NO_BORDER, fontTitulo));
		tabla.addCell(crearCelda(tituloPrincipal, PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 5, PdfPCell.NO_BORDER,
				fontTitulo));
		return tabla;
	}

	


	

	public static PdfPTable crearCabeceraGenerarReporteTv(Short mes, Integer anno, String titulo, String img) {
		PdfPTable tabla = new PdfPTable(5);

		try {

			tabla.setWidthPercentage(100);
			Image imagenCPSAA;

			imagenCPSAA = Image.getInstance(img);
			imagenCPSAA.setWidthPercentage(80);
			PdfPCell cell = new PdfPCell();
			cell.addElement(imagenCPSAA);
			cell.setVerticalAlignment(PdfCell.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setBorder(PdfCell.NO_BORDER);
			tabla.addCell(cell);
		} catch (BadElementException e) {
			logger.error(e.getMessage());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		Font fonttituloBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
		tabla.addCell(crearCelda(FECHA_CREACION_LABEL + " " + FechaUtil.formatearFechaHHMMSS(new Date()), PdfCell.ALIGN_MIDDLE,
				PdfCell.ALIGN_RIGHT, 5, PdfCell.NO_BORDER, fontBold));
		tabla.addCell(crearCelda(" ", PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 5, PdfPCell.NO_BORDER, fontTitulo));
		tabla.addCell(crearCelda(" Reporte Mensual " + FechaUtil.numeroMesANombreMes(mes) + " " + anno, PdfPTable.ALIGN_MIDDLE,
				PdfPTable.ALIGN_CENTER, 8, PdfPCell.NO_BORDER, fonttituloBold));
		tabla.addCell(crearCelda(titulo, PdfPTable.ALIGN_MIDDLE, PdfPTable.ALIGN_CENTER, 5, PdfPCell.NO_BORDER, fonttituloBold));

		return tabla;
	}

	public static PdfPTable getFooterTable(int x, int y) {
		PdfPTable table = new PdfPTable(1);
		table.setTotalWidth(780);
		table.setLockedWidth(true);
		table.getDefaultCell().setFixedHeight(28);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(String.format("Pag. %d de %d", x, y));
		return table;
	}
}
