package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GridTablaBalance.java
 * Modificado: Sep 20, 2010 2:28:22 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;

public class GridTablaBalance extends ListGrid implements ClienteServicioGwt {

	private static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);

	public GridTablaBalance(ListGrid gridDependiente) {

		super();

		ListGridField conceptoTablaBalanceField = new ListGridField(CONSTANTES.tituloColConcepto());
		conceptoTablaBalanceField.setType(ListGridFieldType.TEXT);
		conceptoTablaBalanceField.setCanEdit(false);
		conceptoTablaBalanceField.setAlign(Alignment.RIGHT);
		conceptoTablaBalanceField.setIncludeInRecordSummary(false);
		conceptoTablaBalanceField.setShowGridSummary(true);
		conceptoTablaBalanceField.setSummaryFunction(new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				return " Total";
			}
		});

		ListGridField saldoInicialTablaBalanceField = new ListGridField(CONSTANTES.tituloColSaldoInicial());
		saldoInicialTablaBalanceField.setType(ListGridFieldType.FLOAT);
		saldoInicialTablaBalanceField.setCanEdit(false);
		saldoInicialTablaBalanceField.setShowGridSummary(true);

		ListGridField ingresoTablaBalanceField = new ListGridField(CONSTANTES.tituloColIngreso(), CONSTANTES.tituloColIngreso());
		ingresoTablaBalanceField.setType(ListGridFieldType.FLOAT);
		ingresoTablaBalanceField.setShowGridSummary(true);
		ingresoTablaBalanceField.setAlign(Alignment.RIGHT);
		ingresoTablaBalanceField.setSummaryFunction(SummaryFunctionType.SUM);
		// Se define evento
		addAjusteCellEditorHandler(ingresoTablaBalanceField, gridDependiente);

		ListGridField consumoTablaBalanceField = new ListGridField(CONSTANTES.tituloColConsumo());
		consumoTablaBalanceField.setType(ListGridFieldType.FLOAT);
		consumoTablaBalanceField.setShowGridSummary(true);
		consumoTablaBalanceField.setAlign(Alignment.RIGHT);
		consumoTablaBalanceField.setSummaryFunction(SummaryFunctionType.SUM);
		// Se define evento
		addAjusteCellEditorHandler(consumoTablaBalanceField, gridDependiente);

		ListGridField saldoFinalTablaBalanceField = new ListGridField(CONSTANTES.tituloStockFinal());
		saldoFinalTablaBalanceField.setType(ListGridFieldType.FLOAT);
		saldoFinalTablaBalanceField.setShowGridSummary(true);
		saldoFinalTablaBalanceField.setAlign(Alignment.RIGHT);
		saldoFinalTablaBalanceField.setSummaryFunction(SummaryFunctionType.SUM);

		// Se aplica formato a la columna Saldo Final
		saldoFinalTablaBalanceField.setCellFormatter(new CellFormatter() {
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

		// Se define tamano a todo el grid
		setWidth(580);
		setHeight(120);
		setCellHeight(20);

		// Se definen caracteristicas generales del grig
		setShowAllRecords(true);
		setCanEdit(true);
		setEditEvent(ListGridEditEvent.CLICK);
		setEditByCell(true);
		setShowGridSummary(true);

		// Se establecen los valores de las columnas del grid
		setFields(conceptoTablaBalanceField, saldoInicialTablaBalanceField, ingresoTablaBalanceField, consumoTablaBalanceField,
				saldoFinalTablaBalanceField);
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

	/**
	 * Método que redefine el evento ante un cambio de valor en las celdas
	 * 
	 * @param ajuste
	 */
	private void addAjusteCellEditorHandler(ListGridField ajuste, final ListGrid gridDependiente) {
		CellSavedHandler handlerAjuste = new CellSavedHandler() {

			public void onCellSaved(CellSavedEvent event) {

				Record record = event.getRecord();
				double newValue = Double.parseDouble(event.getNewValue().toString());
				double oldValue = Double.parseDouble(event.getOldValue().toString());

				int colNum = event.getColNum();
				ListGridField field = getField(colNum);
				String fieldName = getFieldName(colNum);
				String fieldTitle = field.getTitle();

				com.google.gwt.user.client.Window.alert("Clicked <b>" + fieldTitle + ":" + record.getAttribute(fieldName)
						+ "</b> (Ajuste:" + record.getAttribute(CONSTANTES.tituloColIngreso()) + ")");

				if (newValue < 0) {

					com.google.gwt.user.client.Window.alert(CONSTANTES.validacionAjusteDebeSerUnMontoNumerico());

					record.setAttribute(CONSTANTES.tituloColIngreso(), oldValue);

					return;
				}

				RecordList listaRecord = gridDependiente.getRecordList();

				for (int i = 0; i < listaRecord.getLength(); i++)
					if (i == event.getRowNum()) {
						ListGridRecord registro = (ListGridRecord) listaRecord.get(i);
						System.err.println(" ---> " + Double.valueOf(registro.getAttribute(CONSTANTES.tituloColIngreso())));

						registro.setAttribute(CONSTANTES.tituloColIngreso(), record.getAttribute(CONSTANTES.tituloColIngreso()));

						System.err.println(" ***> " + Double.valueOf(registro.getAttribute(CONSTANTES.tituloColIngreso())));

						gridDependiente.refreshFields();
						gridDependiente.saveAllEdits();
					}
			}
		};

		ajuste.addCellSavedHandler(handlerAjuste);

	}

	/**
	 * Método para cargar los datos al grid de TablaBalance
	 * 
	 * @param registrosTablaBalanceDTO
	 */
	public void cargaGridTablaBalance(List<RegistroTablaBalanceDTO> registrosTablaBalanceDTO) {

		// variable de control para colocar el ingreso y el consumo
		// editables pero solo la primera fila de balance
		for (RegistroTablaBalanceDTO registroTablaBalance : registrosTablaBalanceDTO) {
			ListGridRecord registro = new ListGridRecord();

			registro.setAttribute(CONSTANTES.tituloColConcepto(), registroTablaBalance.getTipoConcepto());
			registro.setAttribute(CONSTANTES.tituloColSaldoInicial(), registroTablaBalance.getSaldoInicial());
			registro.setAttribute(CONSTANTES.tituloStockFinal(), registroTablaBalance.getSaldoFinal());
			registro.setAttribute(CONSTANTES.tituloColIngreso(), registroTablaBalance.getIngreso());
			registro.setAttribute(CONSTANTES.tituloColConsumo(), registroTablaBalance.getConsumo());

			this.addData(registro);

		}

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

			registrosTablaBalanceDTO.add(registroTablaBalance);
		}

		return registrosTablaBalanceDTO;
	}
}
