package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridAjustePuestoTrabajo.java
 * Modificado: Sep 20, 2010 2:28:22 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.ListGridTesteable;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Validaciones;

import com.dbaccess.cellmanager.CellSelector;
import com.dbaccess.cellmanager.CellSelectorGroup;
import com.dbaccess.cellmanager.DivisionFormulaEvaluator;
import com.dbaccess.cellmanager.FixedCellSelector;
import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.SimpleRow;
import com.dbaccess.cellmanager.SumAllFormulaEvaluator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.validator.RequiredIfFunction;
import com.smartgwt.client.widgets.form.validator.RequiredIfValidator;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HeaderSpan;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;

// Este grid se llama en la pantalla "Ajuste Puesto Trabajo"
public class GridAjustePuestoTrabajo extends ListGridTesteable implements ClienteServicioGwt {

	static final String GRAPH_SOURCE_ID = "gridAjustePuestoTrabajo";

	public static final int TAMANO_ESTANDAR = 680;
	public static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);

	public static final String CODIGO_PUESTO_TRABAJO = "codigoPuesto";
	public static final String NOMBRE_PUESTO_TRABAJO = "nombrePuestoTrabajo";
	public static final String CODIGO_PRODUCTO = "codigoProducto";
	public static final String NOMBRE_PRODUCTO = "nombreProducto";
	public static final String COLUMNA_PRODUCCION_TM = "produccionToneladas";
	public static final String COLUMNA_CONSUMO_COMBUSTIBLE = "consumoCombustible";
	public static final String COLUMNA_FACTOR_CONVERSION_M3 = "factorConversionm3";
	public static final String COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE = "consumoDiferenciaCombustible";
	public static final String COLUMNA_CONSUMOREAL_COMBUSTIBLE = "consumoRealCombustible";
	public static final String COLUMNA_CONSUMO_COMBUSTIBLE_M3 = "consumoM3";
	public static final String COLUMNA_FACTOR_COMBUSTIBLE = "factor";
	public static final String COLUMNA_FACTOR_COMBUSTIBLE_MES_ANTERIOR = "factorAnterior";
	public static final String COLUMNA_MERMA_COMBUSTIBLE = "mermaCombustible";
	public static final String COLUMNA_CODIGO_COMPONENTE = "codigoComponente";
	// public static final String COLUMNA_STOCKFINAL_OCULTO =
	// "stockFinalOculto";
	// public static final String COLUMNA_MERMA_OCULTO = "mermaOculta";

	public static final String DIV_GRIDS_AJUSTES = "gridsAjustes";

	public GridAjustePuestoTrabajo() {
		super();
		this.setID(GRAPH_SOURCE_ID);
		this.setWidth(TAMANO_ESTANDAR);

		RequiredIfValidator ifValidator = new RequiredIfValidator();
		ifValidator.setErrorMessage(CONSTANTES.campoRequerido());
		ifValidator.setExpression(new RequiredIfFunction() {
			public boolean execute(FormItem formItem, Object value) {
				return true;
			}
		});

		ListGridField codigoPuesto = new ListGridField(CODIGO_PUESTO_TRABAJO);
		codigoPuesto.setType(ListGridFieldType.INTEGER);
		codigoPuesto.setHidden(true);

		ListGridField nombrePuestoTrabajo = new ListGridField(NOMBRE_PUESTO_TRABAJO, CONSTANTES.tituloColPuestoTrabajo());
		nombrePuestoTrabajo.setType(ListGridFieldType.TEXT);
		nombrePuestoTrabajo.setCanEdit(false);
		nombrePuestoTrabajo.setAlign(Alignment.CENTER);
		nombrePuestoTrabajo.setIncludeInRecordSummary(false);
		nombrePuestoTrabajo.setShowGridSummary(true);
		nombrePuestoTrabajo.setWidth(80);
		nombrePuestoTrabajo.setSummaryFunction(new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				return "Total";
			}
		});

		ListGridField codigoComponente = new ListGridField(COLUMNA_CODIGO_COMPONENTE);
		codigoComponente.setType(ListGridFieldType.INTEGER);
		codigoComponente.setHidden(true);

		ListGridField codigoProducto = new ListGridField(CODIGO_PRODUCTO);
		codigoProducto.setType(ListGridFieldType.INTEGER);
		codigoProducto.setHidden(true);

		ListGridField nombreProducto = new ListGridField(NOMBRE_PRODUCTO, CONSTANTES.tituloColProducto());
		nombreProducto.setType(ListGridFieldType.TEXT);
		nombreProducto.setCanEdit(false);
		nombreProducto.setAlign(Alignment.CENTER);
		nombreProducto.setIncludeInRecordSummary(false);

		ListGridField produccionToneladas = new ListGridField(COLUMNA_PRODUCCION_TM, CONSTANTES.tituloColTM());
		produccionToneladas.setType(ListGridFieldType.FLOAT);
		produccionToneladas.setCanEdit(false);
		produccionToneladas.setAlign(Alignment.CENTER);
		produccionToneladas.setSummaryFunction(SummaryFunctionType.SUM);
		asignarFormato(produccionToneladas, ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

		ListGridField consumoToneladas = new ListGridField(COLUMNA_CONSUMO_COMBUSTIBLE, CONSTANTES.tituloColGalones());
		consumoToneladas.setType(ListGridFieldType.FLOAT);
		consumoToneladas.setCanEdit(false);
		consumoToneladas.setAlign(Alignment.CENTER);
		consumoToneladas.setIncludeInRecordSummary(false);
		consumoToneladas.setShowGridSummary(true);
		consumoToneladas.setIncludeInRecordSummary(false);
		asignarFormato(consumoToneladas, ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

		ListGridField consumoToneladasDiferencias = new ListGridField(COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE,
				CONSTANTES.tituloColGalones());
		consumoToneladasDiferencias.setType(ListGridFieldType.FLOAT);
		consumoToneladasDiferencias.setSummaryFunction(SummaryFunctionType.SUM);
		consumoToneladasDiferencias.setAlign(Alignment.CENTER);
		consumoToneladasDiferencias.setValidators(ifValidator);
		consumoToneladasDiferencias.setIncludeInRecordSummary(false);
		consumoToneladasDiferencias.setShowGridSummary(true);
		asignarFormato(consumoToneladasDiferencias, ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
		addAjusteCellEditorHandler(consumoToneladasDiferencias);

		ListGridField consumoToneladasReal = new ListGridField(COLUMNA_CONSUMOREAL_COMBUSTIBLE, CONSTANTES.tituloColGalones());
		consumoToneladasReal.setType(ListGridFieldType.FLOAT);
		consumoToneladasReal.setCanEdit(false);
		consumoToneladasReal.setAlign(Alignment.CENTER);
		consumoToneladasReal.setIncludeInRecordSummary(false);
		consumoToneladasReal.setShowGridSummary(true);
		asignarFormato(consumoToneladasReal, ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

		ListGridField factorConversionm3 = new ListGridField(COLUMNA_FACTOR_CONVERSION_M3);
		factorConversionm3.setType(ListGridFieldType.FLOAT);
		factorConversionm3.setHidden(true);

		ListGridField consumoM3 = new ListGridField(COLUMNA_CONSUMO_COMBUSTIBLE_M3, CONSTANTES.tituloColMetrosCubicos());
		consumoM3.setType(ListGridFieldType.FLOAT);
		consumoM3.setCanEdit(false);
		consumoM3.setAlign(Alignment.CENTER);
		consumoM3.setIncludeInRecordSummary(false);
		consumoM3.setShowGridSummary(true);
		asignarFormato(consumoM3, ConstantesGWT.FORMATO_NUMERO_TRES_DECIMALES);

		ListGridField factor = new ListGridField(COLUMNA_FACTOR_COMBUSTIBLE, CONSTANTES.titulocolGalonesToneladas());
		factor.setType(ListGridFieldType.FLOAT);
		factor.setCanEdit(false);
		factor.setAlign(Alignment.CENTER);
		factor.setIncludeInRecordSummary(false);
		factor.setShowGridSummary(true);
		asignarFormato(factor, ConstantesGWT.FORMATO_NUMERO_TRES_DECIMALES);

		ListGridField factorMesAterior = new ListGridField(COLUMNA_FACTOR_COMBUSTIBLE_MES_ANTERIOR,
				CONSTANTES.titulocolGalonesToneladas());
		factorMesAterior.setType(ListGridFieldType.FLOAT);
		factorMesAterior.setCanEdit(false);
		factorMesAterior.setAlign(Alignment.CENTER);
		factorMesAterior.setIncludeInRecordSummary(false);
		factorMesAterior.setShowGridSummary(true);
		asignarFormato(factorMesAterior, ConstantesGWT.FORMATO_NUMERO_TRES_DECIMALES);

		ListGridField mermaCombustible = new ListGridField(COLUMNA_MERMA_COMBUSTIBLE, CONSTANTES.tituloColGalones());
		mermaCombustible.setType(ListGridFieldType.FLOAT);
		mermaCombustible.setAlign(Alignment.CENTER);
		mermaCombustible.setSummaryFunction(SummaryFunctionType.SUM);
		mermaCombustible.setIncludeInRecordSummary(false);
		mermaCombustible.setShowGridSummary(true);
		asignarFormato(mermaCombustible, ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
		addAjusteCellEditorHandler(mermaCombustible);

		// ListGridField columnaStockFinalOculto = new
		// ListGridField(COLUMNA_STOCKFINAL_OCULTO, "STOCK");
		// columnaStockFinalOculto.setType(ListGridFieldType.FLOAT);
		// columnaStockFinalOculto.setCanEdit(false);
		// columnaStockFinalOculto.setHidden(true);
		// asignarFormato(columnaStockFinalOculto,
		// ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
		//
		// ListGridField columnaMermaOculto = new
		// ListGridField(COLUMNA_MERMA_OCULTO, "M.O");
		// columnaMermaOculto.setType(ListGridFieldType.FLOAT);
		// columnaMermaOculto.setDefaultValue(0);
		// columnaMermaOculto.setCanEdit(false);
		// columnaMermaOculto.setHidden(true);
		// asignarFormato(columnaMermaOculto,
		// ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);

		HeaderSpan headerPuestoTrabajo = new HeaderSpan("", new String[] { NOMBRE_PUESTO_TRABAJO });
		HeaderSpan headerProducto = new HeaderSpan("", new String[] { NOMBRE_PRODUCTO });
		HeaderSpan headerProduccion = new HeaderSpan(CONSTANTES.tituloHeaderProduccion(), new String[] { COLUMNA_PRODUCCION_TM });
		HeaderSpan headerConsumo = new HeaderSpan(CONSTANTES.tituloColConsumo(), new String[] { COLUMNA_CONSUMO_COMBUSTIBLE });
		HeaderSpan headerDiferencias = new HeaderSpan(CONSTANTES.titulocolDiferencia(),
				new String[] { COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE });
		HeaderSpan headerConsumoReal = new HeaderSpan(CONSTANTES.tituloColConsumoReal(), new String[] {
				COLUMNA_CONSUMOREAL_COMBUSTIBLE, COLUMNA_CONSUMO_COMBUSTIBLE_M3 });
		HeaderSpan headerFactor = new HeaderSpan(CONSTANTES.titulocolFactor(), new String[] { COLUMNA_FACTOR_COMBUSTIBLE });
		HeaderSpan headerFactorAnterior = new HeaderSpan(CONSTANTES.tituloHeaderMesAnt(),
				new String[] { COLUMNA_FACTOR_COMBUSTIBLE_MES_ANTERIOR });
		HeaderSpan headerMermaCombustible = new HeaderSpan(CONSTANTES.tituloHeaderMermaCombustible(),
				new String[] { COLUMNA_MERMA_COMBUSTIBLE });

		setHeaderSpans(headerPuestoTrabajo, headerProducto, headerProduccion, headerConsumo, headerDiferencias,
				headerConsumoReal, headerFactor, headerFactorAnterior, headerMermaCombustible);

		setHeaderHeight(50);
		setHeight(250);

		setCellHeight(20);

		// Se definen caracteristicas generales del grig
		// setShowAllRecords(true);
		setCanEdit(true);
		// setEditEvent(ListGridEditEvent.CLICK);
		// setEditByCell(true);
		setShowGridSummary(true);

		setFields(codigoPuesto, nombrePuestoTrabajo, codigoComponente, codigoProducto, nombreProducto, produccionToneladas,
				consumoToneladas, consumoToneladasDiferencias, consumoToneladasReal, factorConversionm3, consumoM3, factor,
				factorMesAterior, mermaCombustible);

	}

	private void asignarFormato(ListGridField columna, final String formato) {
		columna.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat(formato);
					return nf.format((Double.valueOf(value.toString())).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}
			}
		});
	}

	/**
	 * Método que redefine el evento ante un cambio de valor en las celdas
	 * 
	 * @param ajuste
	 */
	private void addAjusteCellEditorHandler(ListGridField ajuste) {
		ajuste.addCellSavedHandler(new CellSavedHandler() {

			public void onCellSaved(CellSavedEvent event) {
				String celda = event.getNewValue().toString();
				if (!Validaciones.isDouble(celda)) {
					com.google.gwt.user.client.Window.alert(CONSTANTES.validacionAjusteDebeSerUnMontoNumerico());
					cancelEditing();
				}
			}
		});
	}

	/**
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			removeData(listGridRecord);
		}
	}

	public void cargarGridAjusteCombustible(List<RegistroTablaConsumosPuestoTrabajoDTO> registrosDTO) {
		for (RegistroTablaConsumosPuestoTrabajoDTO registroDTO : registrosDTO) {

			ListGridRecord registro = new ListGridRecord();

			registro.setAttribute(CODIGO_PUESTO_TRABAJO, registroDTO.getCodigoPuestoTrabajo());
			registro.setAttribute(CODIGO_PRODUCTO, registroDTO.getCodigoProductoComponente());
			registro.setAttribute(COLUMNA_CODIGO_COMPONENTE, registroDTO.getCodigoComponente());

			registro.setAttribute(NOMBRE_PUESTO_TRABAJO, registroDTO.getNombrePuestoTrabajo());
			registro.setAttribute(NOMBRE_PRODUCTO, registroDTO.getNombreComponente());

			registro.setAttribute(COLUMNA_PRODUCCION_TM, registroDTO.getProduccionRealTM());
			registro.setAttribute(COLUMNA_FACTOR_CONVERSION_M3, 264.172D);

			registro.setAttribute(COLUMNA_CONSUMO_COMBUSTIBLE, registroDTO.getMontoConsumido());

			registro.setAttribute(COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE, registroDTO.getAjusteTM());

			registro.setAttribute(COLUMNA_FACTOR_COMBUSTIBLE_MES_ANTERIOR, registroDTO.getDosificacion());
			registro.setAttribute(COLUMNA_MERMA_COMBUSTIBLE, registroDTO.getPorcetanjeCarbones());

			this.addData(registro);
		}

	}

	public List<Row> getGraphRows() {
		final String[] columnasFuente = { COLUMNA_CONSUMO_COMBUSTIBLE, COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE,
				COLUMNA_CONSUMOREAL_COMBUSTIBLE, COLUMNA_FACTOR_CONVERSION_M3, COLUMNA_PRODUCCION_TM,
				COLUMNA_CONSUMO_COMBUSTIBLE_M3, COLUMNA_MERMA_COMBUSTIBLE };
		List<Row> rows = new LinkedList<Row>();

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		// 1m3=264.172 galones
		int rowNum = 0;

		for (Record record : this.getRecords()) {
			SimpleRow row = CellGraphUtil.convertToRow(this, record, columnasFuente, rowNum, colNumbers);

			// prod_real_tm = produccion_tm + ajuste_tm
			CellSelector consumoCombustible = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_CONSUMO_COMBUSTIBLE, row);
			CellSelector ajusteCombustible = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE, row);
			CellSelectorGroup ajusteGroup = new CellSelectorGroup("uno", consumoCombustible, "dos", ajusteCombustible);
			row.putCell(COLUMNA_CONSUMOREAL_COMBUSTIBLE, new SmartGWTCell(ajusteGroup, new SumAllFormulaEvaluator(), record,
					COLUMNA_CONSUMOREAL_COMBUSTIBLE, colNumbers.get(COLUMNA_CONSUMOREAL_COMBUSTIBLE), rowNum, this));

			CellSelector consumoRealCombustible = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_CONSUMOREAL_COMBUSTIBLE, row);
			CellSelector factorConversionM3 = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_FACTOR_CONVERSION_M3, row);
			CellSelectorGroup tmphGroup = new CellSelectorGroup(DivisionFormulaEvaluator.DIVIDENDO, consumoRealCombustible,
					DivisionFormulaEvaluator.DIVISOR, factorConversionM3);
			row.putCell(COLUMNA_CONSUMO_COMBUSTIBLE_M3, new SmartGWTCell(tmphGroup, new DivisionFormulaEvaluator(), record,
					COLUMNA_CONSUMO_COMBUSTIBLE_M3, colNumbers.get(COLUMNA_CONSUMO_COMBUSTIBLE_M3), rowNum, this));

			CellSelector produccionProductoTM = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_PRODUCCION_TM, row);
			// CellSelector consumoRealCombustible = new
			// FixedCellSelector(GRAPH_SOURCE_ID,
			// COLUMNA_CONSUMOREAL_COMBUSTIBLE, row);
			CellSelectorGroup factorCombustible = new CellSelectorGroup(DivisionFormulaEvaluator.DIVIDENDO,
					consumoRealCombustible, DivisionFormulaEvaluator.DIVISOR, produccionProductoTM);
			row.putCell(COLUMNA_FACTOR_COMBUSTIBLE, new SmartGWTCell(factorCombustible, new DivisionFormulaEvaluator(), record,
					COLUMNA_FACTOR_COMBUSTIBLE, colNumbers.get(COLUMNA_FACTOR_COMBUSTIBLE), rowNum, this));

			rows.add(row);
			rowNum++;
		}

		return rows;
	}

	public ArrayList<RegistroTablaAjusteDTO> obtenerMovimientoDeAjuste() {
		ArrayList<RegistroTablaAjusteDTO> registrosAjuste = new ArrayList<RegistroTablaAjusteDTO>();

		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			Double diferencia = Double.valueOf(listGridRecord.getAttribute(COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE));
			Double tnProd = listGridRecord.getAttributeAsDouble(COLUMNA_CONSUMO_COMBUSTIBLE);
			Double tnReal = listGridRecord.getAttributeAsDouble(COLUMNA_CONSUMOREAL_COMBUSTIBLE);

			String nombrePuesto = listGridRecord.getAttribute(NOMBRE_PUESTO_TRABAJO);
			String nombreComponente = listGridRecord.getAttribute(NOMBRE_PRODUCTO);
			String codigoPuesto = listGridRecord.getAttribute(CODIGO_PUESTO_TRABAJO);
			Long codigoComponente = new Long(listGridRecord.getAttribute(COLUMNA_CODIGO_COMPONENTE));

			Double ingreso = 0D;
			Double consumo = 0D;

			consumo = diferencia;

			if (diferencia.doubleValue() != 0d) {
				RegistroTablaAjusteDTO registroAjuste = crearRegistroAjuste(nombrePuesto, nombreComponente,
						codigoComponente.toString(), "Hoja de Ruta", codigoPuesto.toString(), tnProd, ingreso, consumo, tnReal);

				registrosAjuste.add(registroAjuste);
			}
		}

		// merma
		for (ListGridRecord listGridRecord : records) {
			Double diferencia = Double.valueOf(listGridRecord.getAttribute(COLUMNA_MERMA_COMBUSTIBLE));
			Double tnProd = listGridRecord.getAttributeAsDouble(COLUMNA_CONSUMO_COMBUSTIBLE);

			Double tnReal = listGridRecord.getAttributeAsDouble(COLUMNA_CONSUMOREAL_COMBUSTIBLE);

			String nombrePuesto = listGridRecord.getAttribute(NOMBRE_PUESTO_TRABAJO);
			String nombreComponente = listGridRecord.getAttribute(NOMBRE_PRODUCTO);
			String codigoPuesto = listGridRecord.getAttribute(CODIGO_PUESTO_TRABAJO);
			Long codigoComponente = new Long(listGridRecord.getAttribute(COLUMNA_CODIGO_COMPONENTE));

			Double ingreso = 0D;
			Double consumo = 0D;

			consumo = diferencia;

			if (diferencia.doubleValue() != 0d) {
				RegistroTablaAjusteDTO registroAjuste = crearRegistroAjuste(nombrePuesto, nombreComponente,
						codigoComponente.toString(), "Merma", codigoPuesto.toString(), tnProd, ingreso, consumo, tnReal);

				registrosAjuste.add(registroAjuste);
			}
		}

		return registrosAjuste;
	}

	private RegistroTablaAjusteDTO crearRegistroAjuste(String nombrePuestoTrabajo, String nombreComponente,
			String codigoComponente, String tipoComponente, String codigoPuesto, Double tnProd, Double ingreso, Double consumo,
			Double saldoFinal) {
		RegistroTablaAjusteDTO registroAjuste = new RegistroTablaAjusteDTO();
		registroAjuste.setCodigoComponente(new Long(codigoComponente));
		registroAjuste.setTipoComponente(tipoComponente);
		registroAjuste.setNombreComponente(nombreComponente);
		registroAjuste.setCodigoPuesto(new Long(codigoPuesto));
		registroAjuste.setNombrePuesto(nombrePuestoTrabajo);
		registroAjuste.setConsumo(consumo);
		registroAjuste.setIngreso(ingreso);
		registroAjuste.setSaldoInicial(0D);
		registroAjuste.setSaldoFinal(0D);
		return registroAjuste;
	}

	public List<RegistroTablaConsumosPuestoTrabajoDTO> exportarGridAjusteCombustible() {
		List<RegistroTablaConsumosPuestoTrabajoDTO> listaDtos = new ArrayList<RegistroTablaConsumosPuestoTrabajoDTO>();
		ListGridRecord[] records = this.getRecords();
		RegistroTablaConsumosPuestoTrabajoDTO dto;
		for (ListGridRecord record : records) {
			dto = new RegistroTablaConsumosPuestoTrabajoDTO();

			dto.setCodigoPuestoTrabajo(Long.valueOf(record.getAttribute(CODIGO_PUESTO_TRABAJO)));
			dto.setCodigoComponente(Long.valueOf(record.getAttribute(COLUMNA_CODIGO_COMPONENTE)));
			dto.setMontoConsumido(Double.valueOf(record.getAttribute(COLUMNA_CONSUMO_COMBUSTIBLE)));
			dto.setAjusteTM(Double.valueOf(record.getAttribute(COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE)));
			dto.setProduccionRealTM(Double.valueOf(record.getAttribute(COLUMNA_PRODUCCION_TM)));

			// Merma
			dto.setPorcetanjeCarbones(Double.valueOf(record.getAttribute(COLUMNA_MERMA_COMBUSTIBLE)));

			// Nulos
			dto.setProduccionRealPorcentaje(0D);
			dto.setProduccionPorcentaje(0D);
			// dto.setPorcetanjeCarbones(0D);
			dto.setEditadoManual(true);

			listaDtos.add(dto);
		}

		return listaDtos;
	}

	public List<RegistroTablaConsumosPuestoTrabajoDTO> exportarGridAjusteCombustibleMermaAutomatica() {
		List<RegistroTablaConsumosPuestoTrabajoDTO> listaDtos = new ArrayList<RegistroTablaConsumosPuestoTrabajoDTO>();
		try {

			ListGridRecord[] records = this.getRecords();
			RegistroTablaConsumosPuestoTrabajoDTO dto;

			Long puestoTrabajo;
			Long componente;
			Double consumoCombustible;
			Double diferenciaCombustible;
			Double produccionTM;
			Long producto;
			Double factor;
			Double consumoMermaCombustible;

			for (ListGridRecord record : records) {
				dto = new RegistroTablaConsumosPuestoTrabajoDTO();

				puestoTrabajo = record.getAttribute(CODIGO_PUESTO_TRABAJO) != null ? Long.valueOf(record
						.getAttribute(CODIGO_PUESTO_TRABAJO)) : 0L;
				componente = record.getAttribute(COLUMNA_CODIGO_COMPONENTE) != null ? Long.valueOf(record
						.getAttribute(COLUMNA_CODIGO_COMPONENTE)) : 0L;
				consumoCombustible = record.getAttribute(COLUMNA_CONSUMO_COMBUSTIBLE) != null ? record
						.getAttributeAsDouble(COLUMNA_CONSUMO_COMBUSTIBLE) : 0d;
				diferenciaCombustible = record.getAttribute(COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE) != null ? record
						.getAttributeAsDouble(COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE) : 0d;
				produccionTM = record.getAttribute(COLUMNA_PRODUCCION_TM) != null ? record
						.getAttributeAsDouble(COLUMNA_PRODUCCION_TM) : 0d;
				producto = record.getAttribute(CODIGO_PRODUCTO) != null ? Long.valueOf(record.getAttribute(CODIGO_PRODUCTO)) : 0L;
				factor = record.getAttribute(COLUMNA_FACTOR_COMBUSTIBLE_MES_ANTERIOR) != null ? record
						.getAttributeAsDouble(COLUMNA_FACTOR_COMBUSTIBLE_MES_ANTERIOR) : 0d;
				consumoMermaCombustible = record.getAttribute(COLUMNA_MERMA_COMBUSTIBLE) != null ? record
						.getAttributeAsDouble(COLUMNA_MERMA_COMBUSTIBLE) : 0d;

				dto.setCodigoPuestoTrabajo(puestoTrabajo);
				dto.setCodigoComponente(componente);
				dto.setMontoConsumido(consumoCombustible);
				dto.setAjusteTM(diferenciaCombustible);
				dto.setProduccionRealTM(produccionTM);

				dto.setCodigoProductoComponente(producto);
				dto.setNombrePuestoTrabajo(record.getAttribute(NOMBRE_PUESTO_TRABAJO));
				dto.setNombreComponente(record.getAttribute(NOMBRE_PRODUCTO));
				// dto.setFactorConversionm3(Double.valueOf(record.getAttribute(COLUMNA_FACTOR_CONVERSION_M3)));
				// dto.setAjusteTM(Double.valueOf(record.getAttribute(COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE)));
				dto.setDosificacion(factor);

				// Merma
				dto.setPorcetanjeCarbones(consumoMermaCombustible);

				listaDtos.add(dto);
			}
		} catch (Exception e) {
			Window.alert("4." + e.getMessage());
		}
		return listaDtos;
	}
}
