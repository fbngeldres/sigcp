package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridTablaBalance2.java
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

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.ListGridTesteable;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Validaciones;

import com.dbaccess.cellmanager.CellSelector;
import com.dbaccess.cellmanager.CellSelectorGroup;
import com.dbaccess.cellmanager.FixedCellSelector;
import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.RowFilter;
import com.dbaccess.cellmanager.RowFilterCellSelector;
import com.dbaccess.cellmanager.SimpleRow;
import com.dbaccess.cellmanager.SumAllFormulaEvaluator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.events.ChangedHandler;

public class GridTablaBalance2 extends ListGridTesteable implements ClienteServicioGwt {

	private Double saldoInicialLibroBalance = 0D;
	private Double produccionLibroBalance = 0D;
	private static Double saldoFinalLibroBalance = 0D;
	private Double consumoLibroBalance = 0D;

	private Double consumoAjusteBalance = 0D;
	private Double produccionAjusteBalance = 0D;

	private static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);
	public static final String GRAPH_SOURCE_ID = "tablaBalance2";
	public static String COLUMNA_INGRESO = CONSTANTES.tituloColIngreso();

	public GridTablaBalance2() {

		super();
		this.setID(GRAPH_SOURCE_ID);

		ListGridField conceptoTablaBalanceField = new ListGridField(CONSTANTES.tituloColConcepto());
		conceptoTablaBalanceField.setType(ListGridFieldType.TEXT);
		conceptoTablaBalanceField.setCanEdit(false);
		conceptoTablaBalanceField.setAlign(Alignment.CENTER);
		conceptoTablaBalanceField.setIncludeInRecordSummary(false);
		conceptoTablaBalanceField.setShowGridSummary(true);
		conceptoTablaBalanceField.setSummaryFunction(new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				return " Totales";
			}
		});

		ListGridField saldoInicial = new ListGridField(CONSTANTES.tituloColSaldoInicial());
		saldoInicial.setType(ListGridFieldType.FLOAT);
		saldoInicial.setCanEdit(false);
		saldoInicial.setShowGridSummary(true);
		saldoInicial.setSummaryFunction(SummaryFunctionType.SUM);
		saldoInicial.setAlign(Alignment.CENTER);
		asignarFormato(saldoInicial);

		ListGridField ingreso = new ListGridField(CONSTANTES.tituloColIngreso(), CONSTANTES.tituloColIngreso());
		ingreso.setType(ListGridFieldType.FLOAT);
		ingreso.setCanEdit(false);
		ingreso.setShowGridSummary(true);
		ingreso.setAlign(Alignment.CENTER);
		ingreso.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(ingreso);
		asignarFormato(ingreso);

		ListGridField consumo = new ListGridField(CONSTANTES.tituloColConsumo());
		consumo.setType(ListGridFieldType.FLOAT);
		consumo.setCanEdit(false);
		consumo.setShowGridSummary(true);
		consumo.setAlign(Alignment.CENTER);
		consumo.setSummaryFunction(SummaryFunctionType.SUM);
		addAjusteCellEditorHandler(consumo);
		asignarFormato(consumo);

		ListGridField saldoFinal = new ListGridField(CONSTANTES.tituloStockFinal());
		saldoFinal.setType(ListGridFieldType.FLOAT);
		saldoFinal.setCanEdit(false);
		saldoFinal.setShowGridSummary(true);
		saldoFinal.setAlign(Alignment.CENTER);
		// saldoFinal.setSummaryFunction(obtenerSumaColumnas(CONSTANTES.tituloStockFinal()));
		// addAjusteCellEditorHandler(saldoFinal);
		asignarFormato(saldoFinal);

		// Se define tamano a todo el grid
		setWidth100();
		setCellHeight(20);
		setHeight(100);

		// Se definen caracteristicas generales del grig
		setShowAllRecords(true);
		setCanEdit(true);
		setEditEvent(ListGridEditEvent.CLICK);
		setEditByCell(true);
		setShowGridSummary(true);

		// Se establecen los valores de las columnas del grid
		setFields(conceptoTablaBalanceField, saldoInicial, ingreso, consumo, saldoFinal);

	}

	@Override
	protected String getGridSummary(ListGridField field) {
		String valueStr = super.getGridSummary(field);
		try {

			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			// se redondea para obtener numeros reales
			// ya que estaba obteniendo numero indefinidos
			Double d = nf.parse(reducirAdosDecimales(Double.valueOf(valueStr)) + "");
			String format = nf.format(d);
			return format;
		} catch (Exception e) {
			return valueStr;
		}
	}

	/**
	 * se cogio del NumberUtil como ayuda para que se arregle el problema de la
	 * suma en la columna saldo Final
	 * 
	 * @param dblnum
	 * @return
	 */
	private double reducirAdosDecimales(double dblnum) {

		double numero = dblnum * 100;
		numero = Math.round(numero);
		dblnum = numero / 100;

		return dblnum;

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
	 * Método que redefine el evento ante un cambio de valor en las celdas
	 * 
	 * @param ajuste
	 */
	private void addAjusteCellEditorHandler(ListGridField ajuste) {
		ajuste.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {
				String celda = (String) event.getValue();

				if (!Validaciones.isDouble(celda)) {
					com.google.gwt.user.client.Window.alert(CONSTANTES.validacionAjusteDebeSerUnMontoNumerico());
					cancelEditing();
				}
			}
		});
	}

	/**
	 * Método para cargar los datos al grid de TablaBalance
	 * 
	 * @param registrosTablaBalanceDTO
	 */
	public void cargarGridTablaBalance(RegistroTablaBalanceDTO dto, Double valorAjuste) {
		limpiarGrid();

		ListGridRecord registroBalance = new ListGridRecord();
		produccionAjusteBalance = valorAjuste;

		registroBalance.setAttribute(CONSTANTES.tituloColConcepto(), "Balance");
		saldoInicialLibroBalance = dto.getSaldoInicial();
		saldoFinalLibroBalance = dto.getSaldoFinal();
		consumoLibroBalance = dto.getConsumo();
		produccionLibroBalance = dto.getIngreso();
		consumoAjusteBalance = dto.getConsumoPorAjuste();
		registroBalance.setAttribute(CONSTANTES.tituloColSaldoInicial(), saldoInicialLibroBalance);
		registroBalance.setAttribute(CONSTANTES.tituloStockFinal(), saldoFinalLibroBalance);
		registroBalance.setAttribute(CONSTANTES.tituloColIngreso(), produccionLibroBalance);
		registroBalance.setAttribute(CONSTANTES.tituloColConsumo(), consumoLibroBalance);
		addData(registroBalance);

		ListGridRecord registroAjuste = new ListGridRecord();

		registroAjuste.setAttribute(CONSTANTES.tituloColConcepto(), "Ajuste");
		registroAjuste.setAttribute(CONSTANTES.tituloColSaldoInicial(), 0);
		registroAjuste.setAttribute(CONSTANTES.tituloStockFinal(), 0);
		// TODO: poner en null
		registroAjuste.setAttribute(CONSTANTES.tituloColIngreso(), 0);

		registroAjuste.setAttribute(CONSTANTES.tituloColConsumo(), consumoAjusteBalance);

		this.addData(registroAjuste);

	}

	/**
	 * Modifica la Columna Ingreso del Concepto :Balance
	 * 
	 * @param montoConsignacion
	 */
	public void modificarIngresoBalance(Double montoConsignacion) {
		RecordList listaRecord = this.getRecordList();

		if (listaRecord.getLength() > 0) {
			Double consumo = listaRecord.get(0).getAttributeAsDouble(CONSTANTES.tituloColConsumo());

			listaRecord.get(0).setAttribute(CONSTANTES.tituloColIngreso(), montoConsignacion);
			listaRecord.get(0).setAttribute(CONSTANTES.tituloStockFinal(), montoConsignacion - consumo);
			setProduccionLibroBalance(montoConsignacion);
			this.refreshFields();
		}
	}

	public Double obtenerStockFinalBalance() {
		RecordList listaRecord = this.getRecordList();
		Double stockFinal = 0d;
		if (listaRecord.getLength() > 0) {
			stockFinal = listaRecord.get(0).getAttributeAsDouble(CONSTANTES.tituloStockFinal());
		}

		return stockFinal;
	}

	/**
	 * Método para exportar datos del grid
	 * 
	 * @return
	 */
	public List<RegistroTablaBalanceDTO> exportarGridTablaBalance() {
		List<RegistroTablaBalanceDTO> registrosTablaBalanceDTO = new ArrayList<RegistroTablaBalanceDTO>();
		RecordList listaRecord = this.getRecordList();

		for (int i = 0; i < listaRecord.getLength(); i++) {

			RegistroTablaBalanceDTO registroTablaBalance = new RegistroTablaBalanceDTO();
			registroTablaBalance.setTipoConcepto(listaRecord.get(i).getAttribute(CONSTANTES.tituloColConcepto()));
			registroTablaBalance.setSaldoInicial(Double.valueOf(listaRecord.get(i).getAttribute(
					CONSTANTES.tituloColSaldoInicial())));
			registroTablaBalance.setIngreso(Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloColIngreso())));
			registroTablaBalance.setConsumo(Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloColConsumo())));
			registroTablaBalance.setSaldoFinal(Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloStockFinal())));

			// Si el registro es el de la primera fila del grid, es la fila de
			// Balance
			if (i == 0) {
				saldoInicialLibroBalance = Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloColSaldoInicial()));
				produccionLibroBalance = Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloColIngreso()));
				consumoLibroBalance = Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloColConsumo()));
				saldoFinalLibroBalance = Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloStockFinal()));
			}

			// El registro es el de la segunda fila del grid, es la fila de
			// Ajuste
			if (i == 1) {
				consumoAjusteBalance = Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloColConsumo()));
				produccionAjusteBalance = Double.valueOf(listaRecord.get(i).getAttribute(CONSTANTES.tituloColIngreso()));
			}

			registrosTablaBalanceDTO.add(registroTablaBalance);
		}

		return registrosTablaBalanceDTO;
	}

	/**
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			removeData(listGridRecord);
		}

		saldoInicialLibroBalance = 0D;
		produccionLibroBalance = 0D;
		saldoFinalLibroBalance = 0D;
		consumoLibroBalance = 0D;
		consumoAjusteBalance = 0D;
		produccionAjusteBalance = 0D;
	}

	public Double getSaldoInicialLibroBalance() {
		return saldoInicialLibroBalance;
	}

	public void setSaldoInicialLibroBalance(Double saldoInicialLibroBalance) {
		this.saldoInicialLibroBalance = saldoInicialLibroBalance;
	}

	public Double getProduccionLibroBalance() {
		return produccionLibroBalance;
	}

	public void setProduccionLibroBalance(Double produccionLibroBalance) {
		this.produccionLibroBalance = produccionLibroBalance;
	}

	public Double getSaldoFinalLibroBalance() {
		return saldoFinalLibroBalance;
	}

	public void setSaldoFinalLibroBalance(Double saldoFinalLibroBalance) {
		this.saldoFinalLibroBalance = saldoFinalLibroBalance;
	}

	public Double getConsumoLibroBalance() {
		return consumoLibroBalance;
	}

	public void setConsumoLibroBalance(Double consumoLibroBalance) {
		this.consumoLibroBalance = consumoLibroBalance;
	}

	public Double getConsumoAjusteBalance() {
		return consumoAjusteBalance;
	}

	public void setConsumoAjusteBalance(Double consumoAjusteBalance) {
		this.consumoAjusteBalance = consumoAjusteBalance;
	}

	public Double getProduccionAjusteBalance() {
		return produccionAjusteBalance;
	}

	public void setProduccionAjusteBalance(Double produccionAjusteBalance) {
		this.produccionAjusteBalance = produccionAjusteBalance;
	}

	public List<Row> getGraphRows() {
		final String[] columnasFuente = { CONSTANTES.tituloColSaldoInicial(), CONSTANTES.tituloColConsumo() };
		List<Row> rows = new LinkedList<Row>();

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		int rowNum = 1;
		Record record = getRecord(rowNum);
		SimpleRow row = CellGraphUtil.convertToRow(this, record, columnasFuente, rowNum, colNumbers);

		// row1.ingreso = sum(ptp.ajuste_tm)
		CellSelector ajustesSelector = new RowFilterCellSelector(new RowFilter(), GridPuestoTrabajoProduccion.GRAPH_SOURCE_ID,
				GridPuestoTrabajoProduccion.COLUMNA_AJUSTE_TM);
		CellSelectorGroup ajusteGroup = new CellSelectorGroup("uno", ajustesSelector);
		row.putCell(
				CONSTANTES.tituloColIngreso(),
				new SmartGWTCell(ajusteGroup, new SumAllFormulaEvaluator(), record, CONSTANTES.tituloColIngreso(), colNumbers
						.get(CONSTANTES.tituloColIngreso()), rowNum, this));

		// row1.stockfinal = row1.saldoinicial + row1.ajuste - row1.consumo
		CellSelector saldoInicialSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.tituloColSaldoInicial(), row);
		CellSelector ajusteThisSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.tituloColIngreso(), row);
		CellSelector consumoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.tituloColConsumo(), row);
		CellSelectorGroup stockFinalGroup = new CellSelectorGroup("uno", saldoInicialSelector, "dos", ajusteThisSelector,
				"-tres", consumoSelector);
		row.putCell(CONSTANTES.tituloStockFinal(), new SmartGWTCell(stockFinalGroup, new SumAllFormulaEvaluator(), record,
				CONSTANTES.tituloStockFinal(), colNumbers.get(CONSTANTES.tituloStockFinal()), rowNum, this));

		rows.add(row);
		return rows;
	}

	public List<Row> getGraphRowsCombustible() {
		final String[] columnasFuente = { CONSTANTES.tituloColSaldoInicial(), CONSTANTES.tituloColConsumo(),
				CONSTANTES.tituloColIngreso(), CONSTANTES.tituloStockFinal() };
		List<Row> rows = new LinkedList<Row>();

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		int rowNum = 1;

		Record record = getRecord(rowNum);

		SimpleRow row = CellGraphUtil.convertToRow(this, record, columnasFuente, rowNum, colNumbers);

		// row1.ingreso = sum(ptp.ajuste_tm)
		CellSelector ajustesSelector = new RowFilterCellSelector(new RowFilter(), GridAjustePuestoTrabajo.GRAPH_SOURCE_ID,
				GridAjustePuestoTrabajo.COLUMNA_CONSUMO_DIFERENCIA_COMBUSTIBLE);

		CellSelector mermaselector = new RowFilterCellSelector(new RowFilter(), GridAjustePuestoTrabajo.GRAPH_SOURCE_ID,
				GridAjustePuestoTrabajo.COLUMNA_MERMA_COMBUSTIBLE);

		CellSelectorGroup ajusteGroup = new CellSelectorGroup("uno", ajustesSelector, "dos", mermaselector);
		row.putCell(
				CONSTANTES.tituloColConsumo(),
				new SmartGWTCell(ajusteGroup, new SumAllFormulaEvaluator(), record, CONSTANTES.tituloColConsumo(), colNumbers
						.get(CONSTANTES.tituloColConsumo()), rowNum, this));

		// row1.stockfinal = row1.saldoinicial + row1.ajuste - row1.consumo
		CellSelector saldoInicialSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.tituloColSaldoInicial(), row);
		CellSelector ajusteThisSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.tituloColIngreso(), row);
		CellSelector consumoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.tituloColConsumo(), row);
		CellSelectorGroup stockFinalGroup = new CellSelectorGroup("uno", saldoInicialSelector, "dos", ajusteThisSelector,
				"-tres", consumoSelector);
		row.putCell(CONSTANTES.tituloStockFinal(), new SmartGWTCell(stockFinalGroup, new SumAllFormulaEvaluator(), record,
				CONSTANTES.tituloStockFinal(), colNumbers.get(CONSTANTES.tituloStockFinal()), rowNum, this));

		rows.add(row);
		return rows;
	}

}
