package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.componentes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.widgets.grid.HeaderSpan;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class GridVariablesOperacion extends ListGrid {

	private static final String[] nomColHor = { "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10", "h11", "h12", "h13",
			"h14", "h15", "h16", "h17", "h18", "h19", "h20", "h21", "h22", "h23", "h24" };
	private static final String[] valColHor = { "7am", "8am", "9am", "10am", "11am", "12m", "1pm", "2pm", "3pm", "4pm", "5pm",
			"6pm", "7pm", "8pm", "9pm", "10pm", "11pm", "12am", "1am", "2am", "am", "4am", "5am", "6am" };
	private static final String[] nomColVar = { "variables" };

	private static final short[] horas = { (short) 7, (short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13,
			(short) 14, (short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, (short) 22,
			(short) 23, (short) 24, (short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6 };

	private static final Map<Short, String> horasColumna = new HashMap<Short, String>();

	public GridVariablesOperacion() {
		ListGridField[] gridFields = new ListGridField[25];

		ListGridField listGridField = new ListGridField(nomColVar[0], "Variables");
		listGridField.setFrozen(true);
		listGridField.setWidth(100);
		gridFields[0] = listGridField;

		for (int i = 0; i < gridFields.length - 1; i++) {
			listGridField = new ListGridField(nomColHor[i], valColHor[i]);
			listGridField.setWidth(50);
			gridFields[i + 1] = listGridField;
		}

		setFields(gridFields);

		HeaderSpan variablesCabecera = new HeaderSpan("", nomColVar);
		HeaderSpan horasCabecera = new HeaderSpan("Horas", nomColHor);

		setHeaderSpans(variablesCabecera, horasCabecera);
		setResizeFieldsInRealTime(true);

		setWidth100();
		setHeaderHeight(50);
		setHeight(300);

		llenarHorasColumna();
	}

	private void llenarHorasColumna() {
		for (int i = 0; i < horas.length; i++) {
			horasColumna.put(horas[i], nomColHor[i]);
		}
	}

	@Override
	protected String getGridSummary(ListGridField field) {
		String valueStr = super.getGridSummary(field);
		try {
			NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
			Double d = nf.parse(valueStr);
			String format = nf.format(d);
			GWT.log(format);
			return format;
		} catch (Exception e) {
			return valueStr;
		}
	}

	private double obtenerTextoVariable(Short pos, DatoReporteDTO datoreporte) {

		switch (pos) {
		case 0:
			return datoreporte.getVariable1Datoreporte();
		case 1:
			return datoreporte.getVariable2Datoreporte();
		case 2:
			return datoreporte.getVariable3Datoreporte();
		case 3:
			return datoreporte.getVariable4Datoreporte();
		case 4:
			return datoreporte.getVariable5Datoreporte();
		case 5:
			return datoreporte.getVariable6Datoreporte();
		case 6:
			return datoreporte.getVariable7Datoreporte();
		case 7:
			return datoreporte.getVariable8Datoreporte();
		case 8:
			return datoreporte.getVariable9Datoreporte();
		case 9:
			return datoreporte.getVariable10Datoreporte();
		case 10:
			return datoreporte.getVariable11Datoreporte();
		case 11:
			return datoreporte.getVariable12Datoreporte();
		case 12:
			return datoreporte.getVariable13Datoreporte();
		case 13:
			return datoreporte.getVariable14Datoreporte();
		}

		return 0;
	}

	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			removeData(listGridRecord);
		}
	}

	/**
	 * Metodo que carga la lista de objetos DTO en el grid
	 * 
	 * @param columnas
	 * @param datos
	 */
	public void cargarVariablesOperacion(List<ColumnareporteDTO> columnas, List<DatoReporteDTO> datos) {
		limpiarGrid();

		if ((columnas.size() != 0) || (datos.size() != 0)) {
			for (ColumnareporteDTO columnareporteDTO : columnas) {
				ListGridRecord record = new ListGridRecord();
				record.setAttribute(nomColVar[0], columnareporteDTO.getNombreColumnareporte());

				short pos = columnareporteDTO.getPosicionColumnareporte();

				for (DatoReporteDTO datoreporteDTO : datos) {
					String nombreColumnaHora = horasColumna.get(datoreporteDTO.getHora());
					record.setAttribute(nombreColumnaHora, obtenerTextoVariable(pos, datoreporteDTO));
				}
				addData(record);
			}
		} else {
			com.google.gwt.user.client.Window
					.alert("No existe reporte ECS creado para el producto seleccionado en la fecha de hoy.");
		}
	}
}