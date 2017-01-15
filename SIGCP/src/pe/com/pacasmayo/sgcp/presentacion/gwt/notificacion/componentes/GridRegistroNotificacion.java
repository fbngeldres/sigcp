package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.componentes;

import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class GridRegistroNotificacion extends ListGrid implements ClienteServicioGwt {

	private static final int TAMANO_COLUMNA_VARIABLE = 20;

	private static final String COLUMNA_HORA = "Hora";
	private static final String COLUMNA_PRODUCTO = "Producto";
	private static final String COLUMNA_OBSERVACION = "Observacion";
	public static final String COLUMNA_VARIABLE_UNO = "variableUno";
	public static final String COLUMNA_VARIABLE_DOS = "variableDos";
	public static final String COLUMNA_VARIABLE_TRES = "variableTres";
	public static final String COLUMNA_VARIABLE_CUATRO = "variableCuatro";
	public static final String COLUMNA_VARIABLE_CINCO = "variableCinco";
	public static final String COLUMNA_VARIABLE_SEIS = "variableSeis";
	public static final String COLUMNA_VARIABLE_SIETE = "variableSiete";
	public static final String COLUMNA_VARIABLE_OCHO = "variableOcho";
	public static final String COLUMNA_VARIABLE_NUEVE = "variableNueve";
	public static final String COLUMNA_VARIABLE_DIEZ = "variableDiez";
	public static final String COLUMNA_VARIABLE_ONCE = "variableOnce";
	public static final String COLUMNA_VARIABLE_DOCE = "variableDoce";
	public static final String COLUMNA_VARIABLE_TRECE = "variableTrece";
	public static final String COLUMNA_VARIABLE_CATORCE = "variableCatorce";
	public static final String COLUMNA_VARIABLE_QUINCE = "variableQuince";

	public static final String COLUMNA_CODIGO_PRODUCTO = "codigoProducto";

	public static final String COLUMNA_SILO = "Silo";
	public static final String COLUMNA_CODIGO_SILO = "codigoSilo";
	public static final String COLUMNA_CODIGO_ORDEN = "codigoOrden";
	public static final String COLUMNA_CHECKBOX = "seleccionCheckbox";

	/**
	 * Constructor del grid de programas con su estructura de columnas
	 */
	public GridRegistroNotificacion() {

		ListGridField seleccionCheckBox = new ListGridField(COLUMNA_CHECKBOX);
		seleccionCheckBox.setType(ListGridFieldType.BOOLEAN);
		seleccionCheckBox.setHidden(false);
		seleccionCheckBox.setCanEdit(true);

		ListGridField hora = new ListGridField(COLUMNA_HORA);
		hora.setType(ListGridFieldType.TEXT);
		hora.setHidden(false);
		hora.setCanEdit(false);

		ListGridField codigoOrdenProduccion = new ListGridField(COLUMNA_CODIGO_ORDEN);
		codigoOrdenProduccion.setType(ListGridFieldType.INTEGER);
		codigoOrdenProduccion.setHidden(false);
		codigoOrdenProduccion.setCanEdit(false);

		ListGridField producto = new ListGridField(COLUMNA_PRODUCTO);
		producto.setWidth(65);
		producto.setCanEdit(false);
		producto.setType(ListGridFieldType.TEXT);
		producto.setValueMap("", "", "", "");

		ListGridField silo = new ListGridField(COLUMNA_SILO);
		silo.setWidth(40);
		silo.setCanEdit(false);
		silo.setHidden(false);
		silo.setValueMap("", "", "", "");

		ListGridField observacion = new ListGridField(COLUMNA_OBSERVACION);
		observacion.setWidth(40);
		observacion.setCanEdit(false);
		observacion.setHidden(false);

		final ListGridField variableUno = new ListGridField(COLUMNA_VARIABLE_UNO, "1", TAMANO_COLUMNA_VARIABLE);
		variableUno.setType(ListGridFieldType.BOOLEAN);
		variableUno.setCanEdit(true);
		variableUno.setAlign(Alignment.CENTER);

		final ListGridField variableDos = new ListGridField(COLUMNA_VARIABLE_DOS, "2", TAMANO_COLUMNA_VARIABLE);
		variableDos.setType(ListGridFieldType.BOOLEAN);
		variableDos.setCanEdit(true);
		variableDos.setAlign(Alignment.CENTER);

		final ListGridField variableTres = new ListGridField(COLUMNA_VARIABLE_TRES, "3", TAMANO_COLUMNA_VARIABLE);
		variableTres.setType(ListGridFieldType.BOOLEAN);
		variableTres.setCanEdit(true);
		variableTres.setAlign(Alignment.CENTER);

		final ListGridField variableCuatro = new ListGridField(COLUMNA_VARIABLE_CUATRO, "4", TAMANO_COLUMNA_VARIABLE);
		variableCuatro.setType(ListGridFieldType.BOOLEAN);
		variableCuatro.setCanEdit(true);
		variableCuatro.setAlign(Alignment.CENTER);

		final ListGridField variableCinco = new ListGridField(COLUMNA_VARIABLE_CINCO, "5", TAMANO_COLUMNA_VARIABLE);
		variableCinco.setType(ListGridFieldType.BOOLEAN);
		variableCinco.setCanEdit(true);
		variableCinco.setAlign(Alignment.CENTER);

		final ListGridField variableSeis = new ListGridField(COLUMNA_VARIABLE_SEIS, "6", TAMANO_COLUMNA_VARIABLE);
		variableSeis.setType(ListGridFieldType.BOOLEAN);
		variableSeis.setCanEdit(true);
		variableSeis.setAlign(Alignment.CENTER);

		final ListGridField variableSiete = new ListGridField(COLUMNA_VARIABLE_SIETE, "7", TAMANO_COLUMNA_VARIABLE);
		variableSiete.setType(ListGridFieldType.BOOLEAN);
		variableSiete.setCanEdit(true);
		variableSiete.setAlign(Alignment.CENTER);

		final ListGridField variableOcho = new ListGridField(COLUMNA_VARIABLE_OCHO, "8", TAMANO_COLUMNA_VARIABLE);
		variableOcho.setType(ListGridFieldType.BOOLEAN);
		variableOcho.setCanEdit(true);
		variableOcho.setAlign(Alignment.CENTER);

		final ListGridField variableNueve = new ListGridField(COLUMNA_VARIABLE_NUEVE, "9", TAMANO_COLUMNA_VARIABLE);
		variableNueve.setType(ListGridFieldType.BOOLEAN);
		variableNueve.setCanEdit(true);
		variableNueve.setAlign(Alignment.CENTER);

		final ListGridField variableDiez = new ListGridField(COLUMNA_VARIABLE_DIEZ, "10", TAMANO_COLUMNA_VARIABLE);
		variableDiez.setType(ListGridFieldType.BOOLEAN);
		variableDiez.setCanEdit(true);
		variableDiez.setAlign(Alignment.CENTER);

		final ListGridField variableOnce = new ListGridField(COLUMNA_VARIABLE_ONCE, "11", TAMANO_COLUMNA_VARIABLE);
		variableOnce.setType(ListGridFieldType.BOOLEAN);
		variableOnce.setCanEdit(true);
		variableOnce.setAlign(Alignment.CENTER);

		final ListGridField variableDoce = new ListGridField(COLUMNA_VARIABLE_DOCE, "12", TAMANO_COLUMNA_VARIABLE);
		variableDoce.setType(ListGridFieldType.BOOLEAN);
		variableDoce.setCanEdit(true);
		variableDoce.setAlign(Alignment.CENTER);

		final ListGridField variableTrece = new ListGridField(COLUMNA_VARIABLE_TRECE, "1", TAMANO_COLUMNA_VARIABLE);
		variableTrece.setType(ListGridFieldType.BOOLEAN);
		variableTrece.setCanEdit(true);
		variableTrece.setAlign(Alignment.CENTER);

		final ListGridField variableCatorce = new ListGridField(COLUMNA_VARIABLE_CATORCE, "2", TAMANO_COLUMNA_VARIABLE);
		variableCatorce.setType(ListGridFieldType.BOOLEAN);
		variableCatorce.setCanEdit(true);
		variableCatorce.setAlign(Alignment.CENTER);

		final ListGridField variableQuince = new ListGridField(COLUMNA_VARIABLE_QUINCE, "3", TAMANO_COLUMNA_VARIABLE);
		variableQuince.setType(ListGridFieldType.BOOLEAN);
		variableQuince.setCanEdit(true);
		variableQuince.setAlign(Alignment.CENTER);

		setEditEvent(ListGridEditEvent.CLICK);
		setWidth100();
		setHeaderHeight(50);
		setHeight(150);

		setCanRemoveRecords(false);
		setSaveLocally(true);// Permite cambiar los checkbox
		setCanEdit(true);
		setModalEditing(false);

		setFields(seleccionCheckBox, hora, variableUno, variableDos, variableTres, variableCuatro, variableCinco, variableSeis,
				variableSiete, variableOcho, variableNueve, variableDiez, variableOnce, variableDoce, variableTrece,
				variableCatorce, variableQuince, codigoOrdenProduccion, producto, silo, observacion);

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
}
