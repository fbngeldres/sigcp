package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.ListGridTesteable;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Validaciones;

import com.dbaccess.cellmanager.Cell;
import com.dbaccess.cellmanager.CellSelector;
import com.dbaccess.cellmanager.CellSelectorGroup;
import com.dbaccess.cellmanager.ComparePercentFormulaEvaluator;
import com.dbaccess.cellmanager.FixedCellSelector;
import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.RowFilter;
import com.dbaccess.cellmanager.RowFilterCellSelector;
import com.dbaccess.cellmanager.SimpleCell;
import com.dbaccess.cellmanager.SimpleRow;
import com.dbaccess.cellmanager.SumAllFormulaEvaluator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.validator.RequiredIfFunction;
import com.smartgwt.client.widgets.form.validator.RequiredIfValidator;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HeaderSpan;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.events.ChangedHandler;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridConsumoComponentesPuestoTrabajo.java
 * Modificado: Sep 20, 2010 2:28:22 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

// Este grid en la pantalla sale dos veces, una como "Consumo Comp P.Trabajo" y otra como "Consumo Comp" (?)
public class GridConsumoComponentesPuestoTrabajo extends ListGridTesteable implements ClienteServicioGwt {

	private static final String EDITADO = "editado";
	public static final String CODIGO_PUESTO_TRABAJO = "codigoPuesto";
	public static final String PUESTO_TRABAJO = "puestoTrabajo";
	public static final String CODIGO_COMPONENTE = "codigoComponente";
	public static final String CODIGO_PRODUCTO_COMPONENTE = "codigoProductoComponente";
	public static final String GRUPO_COMPONENTE = "grupoComponente";
	public static final String DIFERENCIA = "Diferencia TM";
	public static final String DIFERENCIA_INICIAL = "diferenciaInicial";
	public static final String DIFERENCIA_MAS_AJUSTE_MANUAL = "Dif. + Aj.Manual TM";
	public static final String COMPONENTE = "componente";
	public static final String CONSUMO_TONELADAS = "consumoTM";
	public static final String CONSUMO_PORCENTAJE = "consumoPorcentaje";
	public static final String FIJO_100 = "fijo_100";
	public static final String CARBONES_PORCENTAJE = "carbonesPorcentaje";
	public static final String CONSUMOREAL_TONELADAS = "consumoRealTM";
	public static final String CONSUMOREAL_PORCENTAJE = "consumoRealPorcentaje";
	private static final String DOSIFICACION = "dosificacion";

	public static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);
	static final String GRAPH_SOURCE_ID = "gridConsumoComponentePuestoTrabajo";
	public static final String GROUPED_GRAPH_SOURCE_ID = "gridConsumoComponenteAgrupado";
	public static final String INICIALIZADO = "diferenciaCopiada";
	private static final String CONSUMOREAL_TONELADAS_DIRECTOBD = "consumoRealTmDirectoBD";
	private static final String DIFERENCIA_DIRECTO_BD = "diferenciaDirectoBD";
	private static final String DIFERENCIA_PORC_INICIAL = "diferenciaPorcInicial";
	private static final String DIFERENCIA_PORCENTAJE = "diferenciaPorcentaje";
	public static final int ETAPA_CON_CERO = 0;
	public static final int ETAPA_DE_INICIAL = 1;
	public static final int ETAPA_SUGERIR = 2;

	private final GridMovimientoComponentes gridMovimientoComponentes;
	private GridConsumoComponentesPuestoTrabajo gridConsumoComponentes = null;
	private GridPuestoTrabajoProduccion gridPuestoTrabajoProduccion;
	// private String nombreCompMayorIndice;
	private final Map<String, String> mapaPropiedades;
	private GridMovimientosDeAjuste gridMovimientosDeAjuste;

	private GridConsumoComponentesPuestoTrabajo gridConsumoPuestoTrabajo;
	private boolean sinAgrupar;
	private SmartGWTCellManager cellManager;

	public GridConsumoComponentesPuestoTrabajo(GridMovimientoComponentes gridMovimientoComponentes, boolean sinAgrupar,
			GridConsumoComponentesPuestoTrabajo gridConsumoComponentes, Map<String, String> mapaPropiedades,
			GridMovimientosDeAjuste gridMovimientosDeAjuste) {

		// HACK: deberían ser grids separados
		// Mientras la misma clase sea usada para los dos grids (agrupado por
		// puesto y no por puesto)
		// habrá una fila infinita de bugs. Cada cambio que se hace para un caso
		// termina dañando el otro,
		// y no es razonable esperar que el programador este tan pendiente de
		// esto como para evitarlo.
		// La solucion es tener clases separadas. Al final no comparten nada
		// estos dos grids
		// aparte de una que otra configuración de columnas.
		// this.id = (mostrarColPuesto ? GRAPH_SOURCE_ID:
		// GROUPED_GRAPH_SOURCE_ID);

		this.sinAgrupar = sinAgrupar;

		this.setID(sinAgrupar ? GRAPH_SOURCE_ID : GROUPED_GRAPH_SOURCE_ID);
		this.gridMovimientoComponentes = gridMovimientoComponentes;
		this.gridConsumoComponentes = gridConsumoComponentes;
		this.gridMovimientosDeAjuste = gridMovimientosDeAjuste;
		this.mapaPropiedades = mapaPropiedades;

		ListGridField codigoPuesto = new ListGridField(CODIGO_PUESTO_TRABAJO);
		codigoPuesto.setType(ListGridFieldType.INTEGER);
		codigoPuesto.setHidden(true);

		ListGridField puesto = new ListGridField(PUESTO_TRABAJO, CONSTANTES.tituloColPuestoTrabajo());
		puesto.setType(ListGridFieldType.TEXT);
		puesto.setCanEdit(false);
		puesto.setAlign(Alignment.CENTER);
		puesto.setHidden(!sinAgrupar);
		puesto.setIncludeInRecordSummary(false);
		puesto.setShowGridSummary(true);
		puesto.setSortDirection(SortDirection.ASCENDING);
		puesto.setSummaryFunction(new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				return " Totales";
			}
		});

		ListGridField codigoComponente = new ListGridField(CODIGO_COMPONENTE);
		codigoComponente.setType(ListGridFieldType.INTEGER);
		codigoComponente.setHidden(true);

		ListGridField componente = new ListGridField(COMPONENTE, CONSTANTES.tituloColComponente());
		componente.setType(ListGridFieldType.TEXT);
		componente.setCanEdit(false);
		componente.setAlign(Alignment.CENTER);
		componente.setIncludeInRecordSummary(false);
		componente.setShowGridSummary(false);

		ListGridField produccionTM = new ListGridField(CONSUMO_TONELADAS, CONSTANTES.tituloColTM());
		produccionTM.setType(ListGridFieldType.FLOAT);
		produccionTM.setCanEdit(false);
		produccionTM.setShowGridSummary(true);
		produccionTM.setAlign(Alignment.CENTER);
		produccionTM.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(produccionTM);

		ListGridField produccionPorcentaje = new ListGridField(CONSUMO_PORCENTAJE, CONSTANTES.tituloColPorcentaje());
		produccionPorcentaje.setType(ListGridFieldType.FLOAT);
		produccionPorcentaje.setCanEdit(false);
		produccionPorcentaje.setWidth(50);
		produccionPorcentaje.setShowGridSummary(true);
		produccionPorcentaje.setAlign(Alignment.CENTER);
		produccionPorcentaje.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(produccionPorcentaje);

		ListGridField produccionRealTM = new ListGridField(CONSUMOREAL_TONELADAS, CONSTANTES.tituloColTM());
		produccionRealTM.setType(ListGridFieldType.FLOAT);
		produccionRealTM.setCanEdit(false);
		produccionRealTM.setShowGridSummary(true);
		produccionRealTM.setAlign(Alignment.CENTER);
		produccionRealTM.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(produccionRealTM);
		asignarFormato(produccionRealTM);

		RequiredIfValidator ifValidator = new RequiredIfValidator();
		ifValidator.setErrorMessage(CONSTANTES.campoRequerido());
		ifValidator.setExpression(new RequiredIfFunction() {
			public boolean execute(FormItem formItem, Object value) {
				return true;
			}
		});

		ListGridField produccionRealPorcentaje = new ListGridField(CONSUMOREAL_PORCENTAJE, CONSTANTES.tituloColPorcentaje());
		produccionRealPorcentaje.setType(ListGridFieldType.FLOAT);
		produccionRealPorcentaje.setCanEdit(false);
		produccionRealPorcentaje.setWidth(50);
		produccionRealPorcentaje.setValidators(ifValidator);
		produccionRealPorcentaje.setShowGridSummary(true);
		produccionRealPorcentaje.setAlign(Alignment.CENTER);
		produccionRealPorcentaje.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(produccionRealPorcentaje);

		ListGridField diferenciaPorcentaje = new ListGridField(DIFERENCIA_PORCENTAJE, CONSTANTES.tituloColDiferenciaPorcentaje());
		diferenciaPorcentaje.setType(ListGridFieldType.FLOAT);
		diferenciaPorcentaje.setCanEdit(true);
		diferenciaPorcentaje.setWidth(50);
		diferenciaPorcentaje.setBaseStyle("backgroung_grid_consumo_componente_puestotrabajo");
		diferenciaPorcentaje.setValidators(ifValidator);
		diferenciaPorcentaje.setShowGridSummary(true);
		diferenciaPorcentaje.setHidden(!sinAgrupar);
		diferenciaPorcentaje.setAlign(Alignment.CENTER);
		diferenciaPorcentaje.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(diferenciaPorcentaje);

		ListGridField carbonesPorcentaje = new ListGridField(CARBONES_PORCENTAJE, CONSTANTES.tituloColCarbonPorcentaje());
		carbonesPorcentaje.setType(ListGridFieldType.FLOAT);
		carbonesPorcentaje.setCanEdit(false);
		carbonesPorcentaje.setWidth(60);
		carbonesPorcentaje.setShowGridSummary(false);
		carbonesPorcentaje.setAlign(Alignment.CENTER);
		carbonesPorcentaje.setHidden(true);
		asignarFormato(carbonesPorcentaje);

		ListGridField produccionRealDiferencia = new ListGridField(DIFERENCIA);
		produccionRealDiferencia.setType(ListGridFieldType.FLOAT);
		produccionRealDiferencia.setCanEdit(false);
		produccionRealDiferencia.setShowGridSummary(true);
		produccionRealDiferencia.setAlign(Alignment.CENTER);
		asignarFormato(produccionRealDiferencia);

		ListGridField produccionRealDiferenciaMasAjusteManual = new ListGridField(DIFERENCIA_MAS_AJUSTE_MANUAL);
		produccionRealDiferenciaMasAjusteManual.setType(ListGridFieldType.FLOAT);
		produccionRealDiferenciaMasAjusteManual.setCanEdit(false);
		produccionRealDiferenciaMasAjusteManual.setShowGridSummary(true);
		produccionRealDiferenciaMasAjusteManual.setAlign(Alignment.CENTER);
		asignarFormato(produccionRealDiferenciaMasAjusteManual);

		ListGridField dosificacion = new ListGridField(DOSIFICACION, CONSTANTES.tituloColDosificacion());
		dosificacion.setType(ListGridFieldType.FLOAT);
		dosificacion.setCanEdit(false);
		dosificacion.setShowGridSummary(true);
		dosificacion.setHidden(!sinAgrupar);
		dosificacion.setAlign(Alignment.CENTER);
		asignarFormato(dosificacion);

		ListGridField regEditado = new ListGridField(EDITADO, CONSTANTES.tituloColEditado(), 40);
		regEditado.setAlign(Alignment.CENTER);
		regEditado.setType(ListGridFieldType.IMAGE);
		regEditado.setHidden(!sinAgrupar);
		regEditado.setImageURLPrefix("../../images/");
		regEditado.setCanEdit(false);
		regEditado.setAlign(Alignment.CENTER);
		regEditado.setImageURLSuffix(".png");
		regEditado.setShowHover(true);
		regEditado.setHoverCustomizer(new HoverCustomizer() {
			public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value.toString() == null) {
					return "";
				}

				if (value.toString().indexOf(ConstantesGWT.EDITADO_MANUAL) > 0) {
					return CONSTANTES.hoverAjusteComponenteEditadoManual();
				}

				return CONSTANTES.hoverAjusteComponenteAutomatico();
			}
		});

		HeaderSpan headerPT = new HeaderSpan("", new String[] { PUESTO_TRABAJO });
		HeaderSpan headerComponente = new HeaderSpan("", new String[] { COMPONENTE });
		HeaderSpan headerProduccion = new HeaderSpan(CONSTANTES.tituloColConsumo(), new String[] { CONSUMO_TONELADAS,
				CONSUMO_PORCENTAJE });
		HeaderSpan headerProduccionReal = new HeaderSpan(CONSTANTES.tituloColConsumoReal(), new String[] { CONSUMOREAL_TONELADAS,
				CONSUMOREAL_PORCENTAJE, CARBONES_PORCENTAJE });
		HeaderSpan headerDiferencia = new HeaderSpan("", new String[] { DIFERENCIA_PORCENTAJE, DIFERENCIA });
		HeaderSpan headerDiferenciaMasAjusteManual = new HeaderSpan("", new String[] { DIFERENCIA_MAS_AJUSTE_MANUAL });

		if (sinAgrupar) {
			HeaderSpan headerDosificacion = new HeaderSpan("", new String[] { DOSIFICACION });
			HeaderSpan headerRegEditado = new HeaderSpan("", new String[] { EDITADO });

			setHeaderSpans(headerPT, headerComponente, headerProduccion, headerProduccionReal, headerDiferencia,
					headerDiferenciaMasAjusteManual, headerDosificacion, headerRegEditado);
		} else {
			setHeaderSpans(headerPT, headerComponente, headerProduccion, headerProduccionReal, headerDiferencia,
					headerDiferenciaMasAjusteManual);
		}

		setHeaderHeight(50);
		setWidth100();
		setHeight(400);

		// Se definen caracteristicas generales del grig
		setShowAllRecords(true);
		setCanEdit(true);
		setEditEvent(ListGridEditEvent.CLICK);
		setEditByCell(true);

		if (!sinAgrupar) {
			setShowGridSummary(true);
		} else {
			markForRedraw();
		}

		setFields(codigoPuesto, puesto, codigoComponente, componente, produccionTM, produccionPorcentaje, produccionRealTM,
				produccionRealPorcentaje, carbonesPorcentaje, diferenciaPorcentaje, produccionRealDiferencia,
				produccionRealDiferenciaMasAjusteManual, dosificacion, regEditado);

	}

	protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
		if (getFieldName(colNum).equals(COMPONENTE)) {
			String nombreComponente = record.getAttribute(COMPONENTE);
			if (nombreComponente.equals("CAR MIX S1") || nombreComponente.equals("CAR MIX S2")) {
				return "font-weight:bold; color:blue;";
			}
		}
		return super.getCellCSSText(record, rowNum, colNum);
	}

	@Override
	protected String getGridSummary(ListGridField field) {
		String valueStr = super.getGridSummary(field);
		try {
			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			Double d = nf.parse(valueStr);
			String format = nf.format(d);
			return format;
		} catch (Exception e) {
			return valueStr;
		}
	}

	@Override
	protected boolean canEditCell(int rowNum, int colNum) {
		ListGridRecord record = getRecord(rowNum);

		String nombreComponente = record.getAttribute(COMPONENTE);
		String MIX1 = mapaPropiedades.get(ConstantesGWT.CAR_MIX_S1);
		String MIX2 = mapaPropiedades.get(ConstantesGWT.CAR_MIX_S2);

		ListGridField field = this.getField(colNum);

		if (field.getName().equals(DIFERENCIA_PORCENTAJE)) {
			Cell cell = cellManager.getCell(record, colNum);
			if (cell == null) {
				// HACK. deberia ser true
				return false;
			}
			if (cell.isFormula()) {
				return false;
			}
		}

		if (field.getName().equals(CARBONES_PORCENTAJE)) {
			if (!nombreComponente.equals(MIX1) && !nombreComponente.equals(MIX2)) {
				return false;
			}

			Double pocentajeCarbon = record.getAttributeAsDouble(CARBONES_PORCENTAJE);
			if (pocentajeCarbon.doubleValue() == 0d || pocentajeCarbon.doubleValue() == 100d) {
				return false;
			}
		}

		return true;

	}

	private void asignarFormato(ListGridField columna) {
		columna.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
					return nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}
			}
		});
	}

	/**
	 * Método para cargar los datos al grid de consumo de componentes
	 * 
	 * @param totales
	 * @param existeEnBD
	 * @param esClinker
	 * @param registrosTablaBalanceDTO
	 */
	private void cargaGridConsumoComponentes(List<RegistroTablaConsumosPuestoTrabajoDTO> registroTablaConsumoComponentesDTO,
			Map<String, Double> totales, boolean existeEnBD, String nombreBunker, boolean esClinker) {
		Double tnProd = 0D;
		Double porcentajeTnProd = 0D;
		Double porcentajeRealTn = 0D;
		Double porcentajeCarbones = 0D;

		String MIX1 = mapaPropiedades.get(ConstantesGWT.CAR_MIX_S1);
		String MIX2 = mapaPropiedades.get(ConstantesGWT.CAR_MIX_S2);

		Map<String, Double> totalesMixPorPuesto = new HashMap<String, Double>();
		if (esClinker) {
			for (RegistroTablaConsumosPuestoTrabajoDTO dto : registroTablaConsumoComponentesDTO) {
				Double total = totalesMixPorPuesto.get(dto.getNombrePuestoTrabajo());
				if (total == null) {
					total = 0d;
					totalesMixPorPuesto.put(dto.getNombrePuestoTrabajo(), total);
				}

				if (dto.getNombreComponente().equals(MIX1) || dto.getNombreComponente().equals(MIX2)) {
					total += dto.getMontoConsumido();
					totalesMixPorPuesto.put(dto.getNombrePuestoTrabajo(), total);
				}

			}
			this.showField(CARBONES_PORCENTAJE);
		} else {
			this.hideField(CARBONES_PORCENTAJE);
		}

		double mayor = 0d;

		long codigoPuestoAux = 0L;
		String baseStyle = null;

		for (RegistroTablaConsumosPuestoTrabajoDTO dto : registroTablaConsumoComponentesDTO) {
			ListGridRecord registro = new ListGridRecord();

			if (dto.getNombreComponente().equals(nombreBunker)) {
				continue;
			}

			// Esto es para distinguir en la gui los registros por puesto de
			// trabajo
			long codigoPuestoTrabajo = dto.getCodigoPuestoTrabajo().longValue();
			if (codigoPuestoTrabajo != codigoPuestoAux && codigoPuestoAux != 0L) {
				if (baseStyle == null) {
					baseStyle = ConstantesGWT.CSS_LIGHT_GRAY_BG;
				} else {
					baseStyle = null;
				}
			}
			codigoPuestoAux = codigoPuestoTrabajo;

			registro.set_baseStyle(baseStyle);

			registro.setAttribute(CODIGO_PUESTO_TRABAJO, codigoPuestoTrabajo);
			registro.setAttribute(PUESTO_TRABAJO, dto.getNombrePuestoTrabajo());

			registro.setAttribute(CODIGO_COMPONENTE, dto.getCodigoComponente());
			registro.setAttribute(CODIGO_PRODUCTO_COMPONENTE, dto.getCodigoProductoComponente());
			registro.setAttribute(COMPONENTE, dto.getNombreComponente());
			registro.setAttribute(DOSIFICACION, dto.getDosificacion());
			// dto.getProduccionRealTM()
			// TODO: borrar esto
			registro.setAttribute(DIFERENCIA, 0);

			if (dto.isEditadoManual()) {
				registro.setAttribute(EDITADO, ConstantesGWT.EDITADO_MANUAL);
			} else {
				registro.setAttribute(EDITADO, ConstantesGWT.EDITADO_AUTOMATICO);
			}

			registro.setAttribute(GRUPO_COMPONENTE, dto.getGrupoComponente());

			tnProd = dto.getMontoConsumido();

			// esto es para identificar el de mayor indice de produccion ya que
			// ese registro no puede ser editado
			if (tnProd > mayor) {
				mayor = tnProd;
			}

			if (existeEnBD) {
				porcentajeTnProd = dto.getProduccionPorcentaje();
				porcentajeRealTn = dto.getProduccionRealPorcentaje();
				porcentajeCarbones = dto.getPorcetanjeCarbones();
			} else {
				porcentajeTnProd = calcularPorcentajeProducido(tnProd, totales.get(dto.getNombrePuestoTrabajo()));
				porcentajeRealTn = porcentajeTnProd;

				if (esClinker) {

					double pocentajeCarb = 0d;
					if (dto.getNombreComponente().equals(MIX1) || dto.getNombreComponente().equals(MIX2)) {
						Double totalSumaCarbMix = totalesMixPorPuesto.get(dto.getNombrePuestoTrabajo());
						if (totalSumaCarbMix == 0d) {
							pocentajeCarb = 0d;
						} else {
							pocentajeCarb = dto.getMontoConsumido() * 100 / totalSumaCarbMix;
						}
					} else {
						pocentajeCarb = 100d;
					}
					porcentajeCarbones = pocentajeCarb;
				}
			}

			registro.setAttribute(CONSUMO_TONELADAS, tnProd);

			// TODO: quitar esto
			registro.setAttribute(CONSUMO_PORCENTAJE, 0);

			// TODO: quitar esto
			registro.setAttribute(CARBONES_PORCENTAJE, 0);

			// TODO: quitar esto
			registro.setAttribute(CONSUMOREAL_TONELADAS, 0);
			registro.setAttribute(CONSUMOREAL_TONELADAS_DIRECTOBD, dto.getProduccionRealTM());
			registro.setAttribute(CONSUMOREAL_PORCENTAJE, 0);
			// registro.setAttribute(DIFERENCIA, tnReal - tnProd);
			addData(registro);
		}

	}

	public void cargarGridConsumoAgrupado() {
		// Vaciar records
		this.getRecordList().removeList(this.getRecords());
		// this.limpiarGrid();

		// if (true) return;
		// Esto es el equivalente de select distinct codigo_puesto_trabajo
		Set<String> codigosPuestos = new HashSet<String>();
		for (Record cptRecord : gridConsumoPuestoTrabajo.getRecords()) {
			String codigoComponente = cptRecord.getAttribute(CODIGO_COMPONENTE);

			boolean elementoNuevo = codigosPuestos.add(codigoComponente);
			if (elementoNuevo) {
				Record record = new ListGridRecord();
				record.setAttribute(CODIGO_COMPONENTE, codigoComponente);
				// Aqui se asume que el producto no esta repetido como varios
				// componentes.
				record.setAttribute(CODIGO_PRODUCTO_COMPONENTE, cptRecord.getAttribute(CODIGO_PRODUCTO_COMPONENTE));
				record.setAttribute(COMPONENTE, cptRecord.getAttribute(COMPONENTE));
				// record.setAttribute(CONSUMO_TONELADAS, 0);
				// record.setAttribute(CONSUMO_PORCENTAJE, 0);
				// record.setAttribute(CONSUMOREAL_TONELADAS, 0);
				// record.setAttribute(CONSUMOREAL_PORCENTAJE, 0);
				// record.setAttribute(DIFERENCIA, 0);

				addData(record);
			}
		}
	}

	/**
	 * Método para exportar datos del grid
	 * 
	 * @return
	 */
	public List<RegistroTablaConsumosPuestoTrabajoDTO> exportarGridConsumoComponentePuestoTrab() {
		return exportarGridConsumoComponentesPuestoTrab(null);
	}

	/**
	 * Método para exportar datos del grid, filtrando por un codigo de puesto de
	 * trabajo
	 * 
	 * @return
	 */
	public List<RegistroTablaConsumosPuestoTrabajoDTO> exportarGridConsumoComponentesPuestoTrab(String codigoPuestoStr) {
		List<RegistroTablaConsumosPuestoTrabajoDTO> registrosDTO = new ArrayList<RegistroTablaConsumosPuestoTrabajoDTO>();
		RecordList listaRecord = this.getRecordList();
		for (int i = 0; i < listaRecord.getLength(); i++) {
			String codigoPuestoRecordStr = listaRecord.get(i).getAttribute(CODIGO_PUESTO_TRABAJO);
			crearRegistroDto(registrosDTO, listaRecord, i, codigoPuestoRecordStr);
		}

		return registrosDTO;
	}

	private void crearRegistroDto(List<RegistroTablaConsumosPuestoTrabajoDTO> registrosDTO, RecordList listaRecord,
			int recordNum, String codigoPuestoRecordStr) {

		// para los datos de la agrupacion no se hace nada
		if (codigoPuestoRecordStr == null) {
			return;
		}

		Long codigoPuesto = Long.valueOf(codigoPuestoRecordStr);
		Record record = listaRecord.get(recordNum);

		RegistroTablaConsumosPuestoTrabajoDTO registroDTO = new RegistroTablaConsumosPuestoTrabajoDTO();
		registroDTO.setCodigoPuestoTrabajo(codigoPuesto);
		registroDTO.setNombrePuestoTrabajo(record.getAttribute(PUESTO_TRABAJO));
		Long codigoComponente = Long.valueOf(record.getAttribute(CODIGO_COMPONENTE));
		registroDTO.setCodigoComponente(codigoComponente);
		registroDTO.setCodigoProductoComponente(Long.valueOf(record.getAttribute(CODIGO_PRODUCTO_COMPONENTE)));
		registroDTO.setNombreComponente(record.getAttribute(COMPONENTE));
		registroDTO.setMontoConsumido(Double.valueOf(record.getAttribute(CONSUMO_TONELADAS)));
		registroDTO.setProduccionPorcentaje(Double.valueOf(record.getAttribute(CONSUMO_PORCENTAJE)));
		registroDTO.setPorcetanjeCarbones(Double.valueOf(record.getAttribute(CARBONES_PORCENTAJE)));
		registroDTO.setProduccionRealTM(Double.valueOf(record.getAttribute(CONSUMOREAL_TONELADAS)));
		registroDTO.setProduccionRealPorcentaje(Double.valueOf(record.getAttribute(CONSUMOREAL_PORCENTAJE)));

		String valorColEditado = record.getAttribute(EDITADO).toString();
		boolean editadoManual = valorColEditado.equals(ConstantesGWT.EDITADO_MANUAL);
		registroDTO.setEditadoManual(editadoManual);

		registrosDTO.add(registroDTO);
	}

	private Double calcularPorcentajeProducido(Double tnProd, Double totalTn) {
		return tnProd * 100 / totalTn;
	}

	/**
	 * Método que redefine el evento ante un cambio de valor en las celdas
	 * 
	 * @param ajuste
	 */
	// TODO: borrar esto. celda nunca es editable!
	private void addAjusteCellEditorHandler(ListGridField ajuste) {
		ajuste.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				String celda = (String) event.getValue();

				if (!Validaciones.isDouble(celda)) {
					Window.alert(CONSTANTES.validacionAjusteDebeSerUnMontoNumerico());
					cancelEditing();
				}
			}
		});
	}

	/**
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {
		// TODO: conseguir una mejor forma
		ListGridRecord[] records = getRecords();
		if (records.length > 0) {
			for (ListGridRecord listGridRecord : records) {
				removeData(listGridRecord);
			}
		}
	}

	public void mostarConsumoComponentesPuestoTrabajo(
			Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>> registroTablaConsumoPuestoTrabajoDTO, boolean existeEnBD,
			String nombreBunker, boolean esClinker) {
		List<RegistroTablaConsumosPuestoTrabajoDTO> list = new ArrayList<RegistroTablaConsumosPuestoTrabajoDTO>();
		ArrayList<String> keyList = new ArrayList<String>(registroTablaConsumoPuestoTrabajoDTO.keySet());
		Collections.sort(keyList, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.toLowerCase().compareTo(o2.toLowerCase());
			}
		});

		Map<String, Double> totales = new HashMap<String, Double>();
		for (String key : keyList) {
			List<RegistroTablaConsumosPuestoTrabajoDTO> consumos = registroTablaConsumoPuestoTrabajoDTO.get(key);
			double total = 0d;
			for (RegistroTablaConsumosPuestoTrabajoDTO registroTablaConsumosPuestoTrabajoDTO : consumos) {
				if (registroTablaConsumosPuestoTrabajoDTO.getNombreComponente().equals(nombreBunker)) {
					continue;
				}
				total += registroTablaConsumosPuestoTrabajoDTO.getProduccionRealTM();
			}
			totales.put(key, total);
			list.addAll(consumos);
		}

		cargaGridConsumoComponentes(list, totales, existeEnBD, nombreBunker, esClinker);
	}

	public ArrayList<RegistroTablaAjusteDTO> obtenerMovimientoDeAjuste() {
		ArrayList<RegistroTablaAjusteDTO> registrosAjuste = new ArrayList<RegistroTablaAjusteDTO>();

		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			Double diferencia = Double.valueOf(listGridRecord.getAttribute(DIFERENCIA));
			Double tnProd = listGridRecord.getAttributeAsDouble(CONSUMO_TONELADAS);
			Double tnReal = listGridRecord.getAttributeAsDouble(CONSUMOREAL_TONELADAS);

			String nombrePuesto = listGridRecord.getAttribute(PUESTO_TRABAJO);
			String nombreComponente = listGridRecord.getAttribute(COMPONENTE);
			String codigoPuesto = listGridRecord.getAttribute(CODIGO_PUESTO_TRABAJO);
			Long codigoComponente = new Long(listGridRecord.getAttribute(CODIGO_COMPONENTE));

			Double ingreso = 0D;
			Double consumo = 0D;

			consumo = diferencia;

			if (diferencia.doubleValue() != 0d) {
				RegistroTablaAjusteDTO registroAjuste = crearRegistroAjuste(nombrePuesto, nombreComponente,
						codigoComponente.toString(), codigoPuesto.toString(), tnProd, ingreso, consumo, tnReal);

				registrosAjuste.add(registroAjuste);
			}
		}

		return registrosAjuste;
	}

	private RegistroTablaAjusteDTO crearRegistroAjuste(String nombrePuestoTrabajo, String nombreComponente,
			String codigoComponente, String codigoPuesto, Double tnProd, Double ingreso, Double consumo, Double saldoFinal) {
		RegistroTablaAjusteDTO registroAjuste = new RegistroTablaAjusteDTO();
		registroAjuste.setCodigoComponente(new Long(codigoComponente));
		registroAjuste.setTipoComponente("Hoja de Ruta");
		registroAjuste.setNombreComponente(nombreComponente);
		registroAjuste.setCodigoPuesto(new Long(codigoPuesto));
		registroAjuste.setNombrePuesto(nombrePuestoTrabajo);
		registroAjuste.setConsumo(consumo);
		registroAjuste.setIngreso(ingreso);
		registroAjuste.setSaldoInicial(0D);
		registroAjuste.setSaldoFinal(0D);
		return registroAjuste;
	}

	public void mostrarUOcultarColumnas(boolean esClinker) {

		if (esClinker) {
			showField(CARBONES_PORCENTAJE, true);
			hideField(CONSUMOREAL_PORCENTAJE, true);
			hideField(DOSIFICACION, true);

		} else {
			hideField(CARBONES_PORCENTAJE, true);
			showField(CONSUMOREAL_PORCENTAJE, true);
			showField(DOSIFICACION, true);
		}

	}

	public void mostrarUOcultarColumnaPorcentajeConsumo(boolean esClinkerCalGranulada) {

		if (esClinkerCalGranulada) {
			hideField(CONSUMO_PORCENTAJE, true);
			hideField(DIFERENCIA_PORCENTAJE, true);
		} else {
			showField(CONSUMO_PORCENTAJE, true);
			showField(DIFERENCIA_PORCENTAJE, true);
		}
		getField(DIFERENCIA).setCanEdit(esClinkerCalGranulada && sinAgrupar);
	}

	/**
	 * Obtiene grupos y "supergrupos" a partir de un grupo. El string de grupo
	 * es jerárquico, separado por punto. Ejs. "CARBON.MIX" significa dos grupos
	 * a la vez: "CARBON" y "CARBON.MIX". "CRUDO" es solo un grupo: "CRUDO"
	 * 
	 * @param grupo grupo del cual extraer grupos y "supergrupos"
	 * @return grupos y "supergrupos" extraídos
	 */
	private Set<String> extraerGrupos(String grupo) {
		// TODO: sacar este metodo a una clase de utilitarios
		Set<String> grupos = new HashSet<String>();

		if (grupo == null) {
			grupos.add(null);
			return grupos;
		}

		boolean primero = true;
		String partesAcum = new String();
		String[] partes = grupo.split("\\.");
		for (String parte : partes) {
			if (primero) {
				primero = false;
			} else {
				partesAcum += ".";
			}
			partesAcum += parte;
			grupos.add(partesAcum);
		}

		return grupos;
	}

	public GridPuestoTrabajoProduccion getGridPuestoTrabajoProduccion() {
		return gridPuestoTrabajoProduccion;
	}

	public void setGridPuestoTrabajoProduccion(GridPuestoTrabajoProduccion gridPuestoTrabajoProduccion) {
		this.gridPuestoTrabajoProduccion = gridPuestoTrabajoProduccion;
	}

	public List<Row> getGraphRows() {
		// OJO: fuente en este grid y también en todos los que dependan de este
		// grid
		final String[] columnasFuente = { CODIGO_COMPONENTE, CODIGO_PRODUCTO_COMPONENTE, CONSUMOREAL_PORCENTAJE,
				CONSUMO_TONELADAS, CONSUMOREAL_TONELADAS, GRUPO_COMPONENTE, CODIGO_PUESTO_TRABAJO, DIFERENCIA,
				CONSUMO_PORCENTAJE, CONSUMOREAL_TONELADAS_DIRECTOBD, DIFERENCIA_PORCENTAJE, INICIALIZADO };
		List<Row> rows = new LinkedList<Row>();

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		int rowNum = 0;
		for (Record record : this.getRecords()) {
			SimpleRow row = CellGraphUtil.convertToRow(this, record, columnasFuente, rowNum, colNumbers);

			// Actualmente, en lugar de guardar en la BD los campos que el
			// usuario tipea, se guarda el resultado de formulas
			// que trabajan sobre lo que el usuario tipea.
			// La forma de trabajar es una especie de des-serialización:
			// - Se lee el resultado final
			// - Se hace ingeniería inversa sacando las piezas del resultado
			// - Se ponen las piezas editables en pantalla
			// - Se pone una formula en pantalla que recalcula el resultado
			// final a partir de las piezas
			//
			// nota: el segundo resultado final no necesariamente es igual al
			// primero, por
			// error de precisión de decimales. Sin haber tocado la pantalla
			// puede ser que un save produzca una modificación.

			if (gridPuestoTrabajoProduccion.isSeAjustaConsumo()) {
				getGraphRowsSiAjusta(colNumbers, row, record, rowNum);
			} else {
				getGraphRowsNoAjusta(colNumbers, row, record, rowNum);
			}

			rows.add(row);
			rowNum++;
		}

		return rows;
	}

	public void agregarFormulasAdicionales(List<Row> rows) {
		if (gridPuestoTrabajoProduccion.isSeAjustaConsumo()) {
			agregarFormulasAdicionalesSiAjusta(rows);
		}
	}

	private void agregarFormulasAdicionalesSiAjusta(List<Row> rows) {
		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);
		// Conseguir las filas con el mayor consumo para su puesto de trabajo
		Collection<Row> maxRows = MaxRowSelector.select(rows, CONSUMO_TONELADAS, CODIGO_PUESTO_TRABAJO);

		// Ciclar por esas filas, cambiando el SimpleCell en
		// DIFERENCIA_PORCENTAJE por una celda con la siguiente fórmula:
		// diferencia_porcentaje = 100 - [sum(diferencia_porc) otras filas del
		// mismo puesto]
		for (Row maxRow : maxRows) {
			CellSelector otrosPorcSelector = new ExcludeRowFilterCellSelector(new RowFilter(CODIGO_PUESTO_TRABAJO, maxRow
					.getCell(CODIGO_PUESTO_TRABAJO).getValue()), GRAPH_SOURCE_ID, DIFERENCIA_PORCENTAJE, maxRow);
			maxRow.putCell(FIJO_100, new SimpleCell(100));
			CellSelector cienSelector = new FixedCellSelector(GRAPH_SOURCE_ID, FIJO_100, maxRow);

			CellSelectorGroup diferenciaInicialGroup = new CellSelectorGroup("uno", cienSelector, "-dos", otrosPorcSelector);

			// TODO: arreglar este rowNum. Mientras no se hace refreshcell no es
			// un problema
			// el 0.
			int rowNum = 0;
			Record record = ((SmartGWTRow) maxRow).getRecord();
			maxRow.putCell(DIFERENCIA_PORCENTAJE, new SmartGWTCell(diferenciaInicialGroup, new SumAllFormulaEvaluator(), record,
					DIFERENCIA_PORCENTAJE, colNumbers.get(DIFERENCIA_PORCENTAJE), rowNum, this));
		}
	}

	// Caso clinker, cal
	private void getGraphRowsNoAjusta(Map<String, Integer> colNumbers, SimpleRow row, Record record, int rowNum) {

		// La diferencia es un campo editable. Pero hay que inicializarlo
		// exactamente una vez
		// a partir de los ajustes en grid ajuste, al cargar la pantalla.
		// El código para copiar diferencia_inicial -> inicial está en
		// pantallaajuste.
		// Misteriosamente desde el momento en que se carga la pantalla el
		// usuario tiene
		// 2 sitios donde editar el mismo dato:
		// - Este grid
		// - El grid de movimientos de ajuste
		// Y el usuario puede descuadrarlos y el sistema lo permite.
		// TODO: quitar boton eliminar para ajustes automáticos en grid mov aj
		// TODO: ARREGLAR, NO SIRVE PARA EL CASO SIN AJUSTE GUARDADO (SUGERIR)
		// diferencia_inicial = [sum(mda.ajuste) where mda.puesto=this.puesto
		// and mda.productoComp=this.productoComp and mda.tipo_mov=automatico]
		CellSelector ajustesAutomaticosSelector = new RowFilterCellSelector(new RowFilter(
				GridMovimientosDeAjuste.COLUMNA_CODIGO_PRODUCTO_COMP, record.getAttribute(CODIGO_PRODUCTO_COMPONENTE),
				GridMovimientosDeAjuste.CODIGO_PUESTO_TRABAJO, record.getAttribute(CODIGO_PUESTO_TRABAJO),
				GridMovimientosDeAjuste.COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_AUTOMATICO),
				GridMovimientosDeAjuste.GRAPH_SOURCE_ID, GridMovimientosDeAjuste.COLUMNA_AJUSTE_CONSUMO);
		CellSelectorGroup diferenciaInicialGroup = new CellSelectorGroup("uno", ajustesAutomaticosSelector);
		row.putCell(DIFERENCIA_INICIAL, new SmartGWTCell(diferenciaInicialGroup, new SumAllFormulaEvaluator(), record,
				DIFERENCIA_INICIAL, SmartGWTCell.INVISIBLE_COL, rowNum, this));

		agregarDiferenciaMasAjManual(colNumbers, rowNum, record, row);

		agregarConsumoRealTm(colNumbers, rowNum, record, row);

		// % carbon
		// si este es carbon = suma(consumoreal carbones mismo puesto) / este
		// consumoreal
		// si este no es carbon = 100
		if (colNumbers.containsKey(CARBONES_PORCENTAJE)) {
			CellSelector carbonesPuestoSelector = new RowFilterCellSelector(new RowFilter(CODIGO_PUESTO_TRABAJO,
					record.getAttribute(CODIGO_PUESTO_TRABAJO), GRUPO_COMPONENTE, ConstantesGWT.GRUPO_CARBON_MIX),
					GRAPH_SOURCE_ID, CONSUMOREAL_TONELADAS);
			CellSelector consumoRealTmSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSUMOREAL_TONELADAS, row);
			CellSelector grupoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, GRUPO_COMPONENTE, row);
			CellSelectorGroup porcentajeCarbonGroup = new CellSelectorGroup(PorcentajeCarbonEvaluator.CARBON_PUESTO,
					carbonesPuestoSelector, PorcentajeCarbonEvaluator.CONSUMOREAL_TM, consumoRealTmSelector,
					PorcentajeCarbonEvaluator.GRUPO, grupoSelector);
			row.putCell(CARBONES_PORCENTAJE, new SmartGWTCell(porcentajeCarbonGroup, new PorcentajeCarbonEvaluator(), record,
					CARBONES_PORCENTAJE, colNumbers.get(CARBONES_PORCENTAJE), rowNum, this));
		}
	}

	// Caso terminados, crudos, etc.
	private void getGraphRowsSiAjusta(Map<String, Integer> colNumbers, SimpleRow row, Record record, int rowNum) {
		// diferencia_bd = consumo_real_bd - consumo - ajuste_manual
		CellSelector consumoRealDirectoBDSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSUMOREAL_TONELADAS_DIRECTOBD, row);
		CellSelector consumoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSUMO_TONELADAS, row);
		CellSelector ajustesManualesSelector = new RowFilterCellSelector(new RowFilter(
				GridMovimientosDeAjuste.COLUMNA_CODIGO_PRODUCTO_COMP, record.getAttribute(CODIGO_PRODUCTO_COMPONENTE),
				GridMovimientosDeAjuste.CODIGO_PUESTO_TRABAJO, record.getAttribute(CODIGO_PUESTO_TRABAJO),
				GridMovimientosDeAjuste.COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_MANUAL), GridMovimientosDeAjuste.GRAPH_SOURCE_ID,
				GridMovimientosDeAjuste.COLUMNA_AJUSTE_CONSUMO);
		CellSelectorGroup difDirectoBDGroup = new CellSelectorGroup("uno", consumoRealDirectoBDSelector, "-dos", consumoSelector,
				"-tres", ajustesManualesSelector);
		row.putCell(DIFERENCIA_DIRECTO_BD, new SmartGWTCell(difDirectoBDGroup, new SumAllFormulaEvaluator(), record,
				DIFERENCIA_DIRECTO_BD, SmartGWTCell.INVISIBLE_COL, rowNum, this));

		// diferencia_porc_inicial = diferencia_bd + consumo / ptp.consumototal
		CellSelector diferenciaDirectoBDSelector = new FixedCellSelector(GRAPH_SOURCE_ID, DIFERENCIA_DIRECTO_BD, row);
		// consumo esta arriba
		CellSelector consumoTotalSelector = new RowFilterCellSelector(new RowFilter(
				GridPuestoTrabajoProduccion.CODIGO_PUESTO_TRABAJO, record.getAttribute(CODIGO_PUESTO_TRABAJO)),
				GridPuestoTrabajoProduccion.GRAPH_SOURCE_ID, GridPuestoTrabajoProduccion.COLUMNA_PRODUCCION_REAL_TM);
		CellSelectorGroup difPorcInicialGroup = new CellSelectorGroup(PorcentualFormulaEvaluator.SUMA_1,
				diferenciaDirectoBDSelector, PorcentualFormulaEvaluator.SUMA_2, consumoSelector,
				PorcentualFormulaEvaluator.DIVISOR, consumoTotalSelector);
		row.putCell(DIFERENCIA_PORC_INICIAL, new SmartGWTCell(difPorcInicialGroup, new PorcentualFormulaEvaluator(), record,
				DIFERENCIA_PORC_INICIAL, SmartGWTCell.INVISIBLE_COL, rowNum, this));

		// diferencia = (ptp.consumototal * diferencia_porc) - consumo
		CellSelector diferenciaPorcentajeSelector = new FixedCellSelector(GRAPH_SOURCE_ID, DIFERENCIA_PORCENTAJE, row);
		CellSelectorGroup diferenciaGroup = new CellSelectorGroup(PercentMinusFormulaEvaluator.PORCENTAJE,
				diferenciaPorcentajeSelector, PercentMinusFormulaEvaluator.VALOR, consumoTotalSelector,
				PercentMinusFormulaEvaluator.RESTA, consumoSelector);
		row.putCell(DIFERENCIA, new SmartGWTCell(diferenciaGroup, new PercentMinusFormulaEvaluator(), record, DIFERENCIA,
				colNumbers.get(DIFERENCIA), rowNum, this));

		agregarConsumoPorc(colNumbers, row, record, rowNum);

		agregarDiferenciaMasAjManual(colNumbers, rowNum, record, row);

		agregarConsumoRealTm(colNumbers, rowNum, record, row);

		agregarConsumoRealPorc(colNumbers, row, record, rowNum);
	}

	private void agregarConsumoRealPorc(Map<String, Integer> colNumbers, SimpleRow row, Record record, int rowNum) {
		// consumo_real_porc = consumoreal_tm / [sum(consumoreal_tm) where
		// puesto=this.puesto] * 100
		CellSelector consumoRealTmSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSUMOREAL_TONELADAS, row);
		CellSelector consumoRealPuestoSelector = new RowFilterCellSelector(new RowFilter(CODIGO_PUESTO_TRABAJO,
				record.getAttribute(CODIGO_PUESTO_TRABAJO)), GRAPH_SOURCE_ID, CONSUMOREAL_TONELADAS);
		CellSelectorGroup porcentajeRealConsumoGroup = new CellSelectorGroup(ComparePercentFormulaEvaluator.DIVIDENDO,
				consumoRealTmSelector, ComparePercentFormulaEvaluator.DIVISOR, consumoRealPuestoSelector);
		row.putCell(CONSUMOREAL_PORCENTAJE, new SmartGWTCell(porcentajeRealConsumoGroup, new ComparePercentFormulaEvaluator(),
				record, CONSUMOREAL_PORCENTAJE, colNumbers.get(CONSUMOREAL_PORCENTAJE), rowNum, this));
	}

	private void agregarConsumoPorc(Map<String, Integer> colNumbers, SimpleRow row, Record record, int rowNum) {
		// consumo_porc = this.consumo_tm / [sum(consumo_tm) where puesto =
		// this.puesto]
		CellSelector consumoSelectorRep = new FixedCellSelector(GRAPH_SOURCE_ID, CONSUMO_TONELADAS, row);
		CellSelector consumoPuestoSelector = new RowFilterCellSelector(new RowFilter(CODIGO_PUESTO_TRABAJO,
				record.getAttribute(CODIGO_PUESTO_TRABAJO)), GRAPH_SOURCE_ID, CONSUMO_TONELADAS);
		CellSelectorGroup consumoPorcGroup = new CellSelectorGroup(ComparePercentFormulaEvaluator.DIVIDENDO, consumoSelectorRep,
				ComparePercentFormulaEvaluator.DIVISOR, consumoPuestoSelector);
		row.putCell(CONSUMO_PORCENTAJE, new SmartGWTCell(consumoPorcGroup, new ComparePercentFormulaEvaluator(), record,
				CONSUMO_PORCENTAJE, colNumbers.get(CONSUMO_PORCENTAJE), rowNum, this));
	}

	private void agregarConsumoRealTm(Map<String, Integer> colNumbers, int rowNum, Record record, SimpleRow row) {
		// consumo_real_tm = consumo_tm + diferencia_mas_aj_manual
		CellSelector consumoTmSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSUMO_TONELADAS, row);
		CellSelector diferenciaMasAjManualSelector = new FixedCellSelector(GRAPH_SOURCE_ID, DIFERENCIA_MAS_AJUSTE_MANUAL, row);
		CellSelectorGroup consumoRealTmGroup = new CellSelectorGroup("uno", consumoTmSelector, "dos",
				diferenciaMasAjManualSelector);
		row.putCell(CONSUMOREAL_TONELADAS, new SmartGWTCell(consumoRealTmGroup, new SumAllFormulaEvaluator(), record,
				CONSUMOREAL_TONELADAS, colNumbers.get(CONSUMOREAL_TONELADAS), rowNum, this));
	}

	private void agregarDiferenciaMasAjManual(Map<String, Integer> colNumbers, int rowNum, Record record, SimpleRow row) {
		// diferencia_mas_ajmanual = diferencia + sum(mda.ajuste)
		CellSelector ajustesManualesSelector = new RowFilterCellSelector(new RowFilter(
				GridMovimientosDeAjuste.COLUMNA_CODIGO_PRODUCTO_COMP, record.getAttribute(CODIGO_PRODUCTO_COMPONENTE),
				GridMovimientosDeAjuste.CODIGO_PUESTO_TRABAJO, record.getAttribute(CODIGO_PUESTO_TRABAJO),
				GridMovimientosDeAjuste.COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_MANUAL), GridMovimientosDeAjuste.GRAPH_SOURCE_ID,
				GridMovimientosDeAjuste.COLUMNA_AJUSTE_CONSUMO);
		CellSelector diferenciaSelector = new FixedCellSelector(GRAPH_SOURCE_ID, DIFERENCIA, row);
		CellSelectorGroup diferenciaMasAjManualGroup = new CellSelectorGroup("uno", ajustesManualesSelector, "dos",
				diferenciaSelector);
		row.putCell(DIFERENCIA_MAS_AJUSTE_MANUAL, new SmartGWTCell(diferenciaMasAjManualGroup, new SumAllFormulaEvaluator(),
				record, DIFERENCIA_MAS_AJUSTE_MANUAL, colNumbers.get(DIFERENCIA_MAS_AJUSTE_MANUAL), rowNum, this));
	}

	public List<Row> getGroupedGraphRows() {
		final String[] columnasFuente = { CODIGO_COMPONENTE, CODIGO_PRODUCTO_COMPONENTE };
		List<Row> rows = new LinkedList<Row>();

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		int rowNum = 0;
		for (Record record : this.getRecords()) {
			SimpleRow row = CellGraphUtil.convertToRow(this, record, columnasFuente, rowNum, colNumbers);

			// consumo_tm = [sum(ccpt.consumo_tm) where componente=este]
			CellSelector consumoSelector = new RowFilterCellSelector(new RowFilter(CODIGO_COMPONENTE,
					record.getAttribute(CODIGO_COMPONENTE)), GRAPH_SOURCE_ID, CONSUMO_TONELADAS);
			CellSelectorGroup consumoGroup = new CellSelectorGroup("uno", consumoSelector);
			row.putCell(CONSUMO_TONELADAS, new SmartGWTCell(consumoGroup, new SumAllFormulaEvaluator(), record,
					CONSUMO_TONELADAS, colNumbers.get(CONSUMO_TONELADAS), rowNum, this));

			CellSelector consumoTmSelector = new FixedCellSelector(GROUPED_GRAPH_SOURCE_ID, CONSUMO_TONELADAS, row);

			// porcentaje_consumo = consumo_tm / [ccpt.consumo_tm sin where] *
			// 100
			if (colNumbers.containsKey(CONSUMO_PORCENTAJE)) {
				CellSelector todoConsumoSelector = new RowFilterCellSelector(new RowFilter(), GRAPH_SOURCE_ID, CONSUMO_TONELADAS);
				CellSelectorGroup porcentajeConsumoGroup = new CellSelectorGroup(ComparePercentFormulaEvaluator.DIVIDENDO,
						consumoTmSelector, ComparePercentFormulaEvaluator.DIVISOR, todoConsumoSelector);
				row.putCell(CONSUMO_PORCENTAJE, new SmartGWTCell(porcentajeConsumoGroup, new ComparePercentFormulaEvaluator(),
						record, CONSUMO_PORCENTAJE, colNumbers.get(CONSUMO_PORCENTAJE), rowNum, this));
			}

			// consumo_real_tm = [sum(ccpt.consumo_real_tm) where
			// componente=este]
			CellSelector consumoRealSelector = new RowFilterCellSelector(new RowFilter(CODIGO_COMPONENTE,
					record.getAttribute(CODIGO_COMPONENTE)), GRAPH_SOURCE_ID, CONSUMOREAL_TONELADAS);
			CellSelectorGroup consumoRealGroup = new CellSelectorGroup("uno", consumoRealSelector);
			row.putCell(CONSUMOREAL_TONELADAS, new SmartGWTCell(consumoRealGroup, new SumAllFormulaEvaluator(), record,
					CONSUMOREAL_TONELADAS, colNumbers.get(CONSUMOREAL_TONELADAS), rowNum, this));

			// porcentajereal_consumo = consumoreal_tm / [consumoreal_tm sin
			// where] * 100
			CellSelector consumoRealTmSelector = new FixedCellSelector(GROUPED_GRAPH_SOURCE_ID, CONSUMOREAL_TONELADAS, row);
			CellSelector todoConsumoRealSelector = new RowFilterCellSelector(new RowFilter(), GROUPED_GRAPH_SOURCE_ID,
					CONSUMOREAL_TONELADAS);
			CellSelectorGroup porcentajeRealConsumoGroup = new CellSelectorGroup(ComparePercentFormulaEvaluator.DIVIDENDO,
					consumoRealTmSelector, ComparePercentFormulaEvaluator.DIVISOR, todoConsumoRealSelector);
			row.putCell(CONSUMOREAL_PORCENTAJE, new SmartGWTCell(porcentajeRealConsumoGroup,
					new ComparePercentFormulaEvaluator(), record, CONSUMOREAL_PORCENTAJE, colNumbers.get(CONSUMOREAL_PORCENTAJE),
					rowNum, this));

			// diferencia_tm = this.consumoreal_tm - this.consumo_tm
			CellSelectorGroup diferenciaGroup = new CellSelectorGroup("uno", consumoRealTmSelector, "-dos", consumoTmSelector);
			row.putCell(DIFERENCIA, new SmartGWTCell(diferenciaGroup, new SumAllFormulaEvaluator(), record, DIFERENCIA,
					colNumbers.get(DIFERENCIA), rowNum, this));

			// diferencia_mas_ajmanual = diferencia_tm + [sum(mda.ajuste) where
			// codigo_producto = este]
			CellSelector diferenciaSelector = new FixedCellSelector(GROUPED_GRAPH_SOURCE_ID, DIFERENCIA, row);
			CellSelector ajustesManualesSelector = new RowFilterCellSelector(new RowFilter(
					GridMovimientosDeAjuste.COLUMNA_CODIGO_PRODUCTO_COMP, record.getAttribute(CODIGO_PRODUCTO_COMPONENTE),
					GridMovimientosDeAjuste.COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_MANUAL),
					GridMovimientosDeAjuste.GRAPH_SOURCE_ID, GridMovimientosDeAjuste.COLUMNA_AJUSTE_CONSUMO);
			CellSelectorGroup diferenciaMasAjManualGroup = new CellSelectorGroup("uno", ajustesManualesSelector, "dos",
					diferenciaSelector);
			row.putCell(DIFERENCIA_MAS_AJUSTE_MANUAL, new SmartGWTCell(diferenciaMasAjManualGroup, new SumAllFormulaEvaluator(),
					record, DIFERENCIA_MAS_AJUSTE_MANUAL, colNumbers.get(DIFERENCIA_MAS_AJUSTE_MANUAL), rowNum, this));

			rows.add(row);
			rowNum++;
		}

		return rows;
	}

	public void setGridConsumoPuestoTrabajo(GridConsumoComponentesPuestoTrabajo gridConsumoPuestoTrabajo) {
		this.gridConsumoPuestoTrabajo = gridConsumoPuestoTrabajo;
	}

	// Caso clinker, cal
	public void copiarDatosInicialesSiAjusta(SmartGWTCellManager cellManager, Integer etapaDesde, int etapaHasta) {
		for (Record record : getRecords()) {
			Row row = cellManager.getRow(record);
			Cell inicializadoCell = row.getCell(INICIALIZADO);
			Cell diferenciaPorcCell = row.getCell(DIFERENCIA_PORCENTAJE);

			if (diferenciaPorcCell.isFormula()) {
				// Saltar las filas en las cuales diferencia es una formula
				// (por regla de negocio son las de mayor indice inicial)
				continue;
			}

			if (!equalsOrBothNull(inicializadoCell.getValue(), etapaDesde)) {
				// Saltar filas que no estan en el estado esperado
				continue;
			}

			// Se asigna sin recalcular, la formula es obsoleta hasta un recalc
			Object value = null;
			switch (etapaHasta) {
			case ETAPA_CON_CERO:
				diferenciaPorcCell.setValue(0);
				break;
			case ETAPA_DE_INICIAL:
				value = record.getAttribute(DIFERENCIA_PORC_INICIAL);
				diferenciaPorcCell.setValue(value);
				break;
			case ETAPA_SUGERIR:
				value = record.getAttribute(CONSUMO_PORCENTAJE);
				diferenciaPorcCell.setValue(value);
				break;
			}
			inicializadoCell.setValue(etapaHasta);
		}
	}

	/**
	 * True si a.equals(b) o tanto a como b son null
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean equalsOrBothNull(Object a, Object b) {
		if (a == null && b == null) {
			return true;
		}
		// Uno de los dos no es null
		if (a != null) {
			return a.equals(b);
		} else {
			return b.equals(a);
		}
	}

	// TODO: cambiar si/no por polimorfismo?
	// Caso CRU, TP-I
	public void copiarDatosInicialesNoAjusta(SmartGWTCellManager cellManager, Integer etapaDesde, int etapaHasta) {
		for (Record record : getRecords()) {
			Row row = cellManager.getRow(record);
			Cell inicializadoCell = row.getCell(INICIALIZADO);
			Cell diferenciaCell = row.getCell(DIFERENCIA);

			if (!equalsOrBothNull(inicializadoCell.getValue(), etapaDesde)) {
				// Saltar filas que no estan en el estado esperado
				continue;
			}

			Object value = null;
			switch (etapaHasta) {
			case ETAPA_CON_CERO:
				// Asignar sin recalcular
				diferenciaCell.setValue(0);
				break;
			case ETAPA_DE_INICIAL:
				value = record.getAttribute(DIFERENCIA_INICIAL);
				cellManager.setCellValue(diferenciaCell, value);
				break;
			default:
				throw new IllegalArgumentException("etapaHasta " + etapaHasta + " desconocida");
			}
			inicializadoCell.setValue(etapaHasta);
		}
	}

	public void copiarDatosIniciales(SmartGWTCellManager cellManager, Integer etapaDesde, int etapaHasta) {
		if (gridPuestoTrabajoProduccion.isSeAjustaConsumo()) {
			copiarDatosInicialesSiAjusta(cellManager, etapaDesde, etapaHasta);
		} else {
			copiarDatosInicialesNoAjusta(cellManager, etapaDesde, etapaHasta);
		}
	}

	public void setCellManager(SmartGWTCellManager cellManager) {
		this.cellManager = cellManager;
	}

	public void agregarFormulasSegunProducto(List<Row> rows, Long componente) {
		if (gridPuestoTrabajoProduccion.isSeAjustaConsumo()) {
			agregarFormulasAdicionalesSegunProducto(rows, componente);
		}

	}

	private void agregarFormulasAdicionalesSegunProducto(List<Row> rows, Long componente) {
		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);
		// Conseguir las filas con el mayor consumo para su puesto de trabajo
		Collection<Row> maxRows = MaxRowSelector.selectByProducto(rows, CODIGO_PRODUCTO_COMPONENTE, componente);

		// Ciclar por esas filas, cambiando el SimpleCell en
		// DIFERENCIA_PORCENTAJE por una celda con la siguiente fórmula:
		// diferencia_porcentaje = 100 - [sum(diferencia_porc) otras filas del
		// mismo puesto]
		for (Row maxRow : maxRows) {
			CellSelector otrosPorcSelector = new ExcludeRowFilterCellSelector(new RowFilter(CODIGO_PUESTO_TRABAJO, maxRow
					.getCell(CODIGO_PUESTO_TRABAJO).getValue()), GRAPH_SOURCE_ID, DIFERENCIA_PORCENTAJE, maxRow);
			maxRow.putCell(FIJO_100, new SimpleCell(100));
			CellSelector cienSelector = new FixedCellSelector(GRAPH_SOURCE_ID, FIJO_100, maxRow);

			CellSelectorGroup diferenciaInicialGroup = new CellSelectorGroup("uno", cienSelector, "-dos", otrosPorcSelector);

			// TODO: arreglar este rowNum. Mientras no se hace refreshcell no es
			// un problema
			// el 0.
			int rowNum = 0;
			Record record = ((SmartGWTRow) maxRow).getRecord();
			maxRow.putCell(DIFERENCIA_PORCENTAJE, new SmartGWTCell(diferenciaInicialGroup, new SumAllFormulaEvaluator(), record,
					DIFERENCIA_PORCENTAJE, colNumbers.get(DIFERENCIA_PORCENTAJE), rowNum, this));
		}
	}

}