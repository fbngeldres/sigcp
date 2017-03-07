package pe.com.pacasmayo.sgcp.presentacion.gwt.stock.componentes;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaStockDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.StockDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class GridStock extends ListGrid {

	public static final String COLUMNA_NOMBRE_ESTADO = "Estado";
	public static final String COLUMNA_CODIGO_ESTADO = "codigoEstado";
	private static final String COLUMNA_DIA = "D\u00eda";
	public static final String COLUMNA_CODIGO_REGISTRO = "codigoRegistro";

	// Este mapa representa las columnas del grid, y tiene como clave el num
	// columna, y como valor a cada uno de los objetos ListGridField
	private Map<String, ListGridField> mapaColumnas = new LinkedHashMap<String, ListGridField>();

	/**
	 * Constructor del grid de totales de stock de productos por dia
	 * 
	 * @param listaRegistros: Esta lista contiene los RegistroTablaStockDTO que
	 *            permitiran construir las columnas.
	 */
	public GridStock(List<RegistroTablaStockDTO> listaRegistros) {

		setEmptyMessage("No existen datos para la fecha seleccionada");

		// Columna del codigo del registro de medicion.
		ListGridField codigoRegistro = new ListGridField();
		codigoRegistro.setType(ListGridFieldType.INTEGER);
		codigoRegistro.setCanEdit(false);
		codigoRegistro.setHidden(true);
		codigoRegistro.setName(COLUMNA_CODIGO_REGISTRO);
		codigoRegistro.setAlign(Alignment.CENTER);

		// Columna del dia.
		ListGridField dia = new ListGridField();
		dia.setType(ListGridFieldType.TEXT);
		dia.setCanEdit(false);
		dia.setName(COLUMNA_DIA);
		dia.setWidth(70);
		dia.setAlign(Alignment.CENTER);

		// Columna del nombre del estado de registro de medicion.
		ListGridField nombreEstado = new ListGridField();
		nombreEstado.setType(ListGridFieldType.TEXT);
		nombreEstado.setCanEdit(false);
		nombreEstado.setName(COLUMNA_NOMBRE_ESTADO);
		nombreEstado.setWidth(70);
		nombreEstado.setAlign(Alignment.CENTER);

		// Columna de codigo de estado del registro de medicion. Columna oculta.
		ListGridField codigoEstado = new ListGridField();
		codigoEstado.setType(ListGridFieldType.INTEGER);
		codigoEstado.setCanEdit(false);
		codigoEstado.setName(COLUMNA_CODIGO_ESTADO);
		codigoEstado.setAlign(Alignment.CENTER);
		codigoEstado.setHidden(true);

		mapaColumnas.put(COLUMNA_CODIGO_REGISTRO, codigoRegistro);
		mapaColumnas.put(COLUMNA_DIA, dia);
		mapaColumnas.put(COLUMNA_NOMBRE_ESTADO, nombreEstado);
		mapaColumnas.put(COLUMNA_CODIGO_ESTADO, codigoEstado);

		GWT.log("numero de elementos: " + listaRegistros.size());

		// Este for itera sobre los registros de la lista recibida para detectar
		// cuales son los productos medianet el mapa de control, y crear las
		// columnas dinamicamente
		for (Iterator<RegistroTablaStockDTO> iterator = listaRegistros.iterator(); iterator.hasNext();) {

			RegistroTablaStockDTO registro = (RegistroTablaStockDTO) iterator.next();
			List<StockDTO> lista = registro.getColumnas();
			for (Iterator<StockDTO> iterator2 = lista.iterator(); iterator2.hasNext();) {
				StockDTO stockDTO = (StockDTO) iterator2.next();

				// Este if es para validar si la columna ya fue creada
				if (!mapaColumnas.containsKey(stockDTO.getNombreProducto())) {

					ListGridField columnaProducto = new ListGridField(stockDTO.getNombreProducto());
					columnaProducto.setAlign(Alignment.CENTER);
					mapaColumnas.put(stockDTO.getNombreProducto(), columnaProducto);

				}
			}
		}

		// Llenamos este arreglo que recibe el metodo setFields para setear las
		// columnas: (No recibe collecciones)
		ListGridField[] arregloColumnas = new ListGridField[mapaColumnas.size()];
		int indiceMapa = 0;
		for (Iterator<String> iterator = mapaColumnas.keySet().iterator(); iterator.hasNext();) {
			String clave = iterator.next();
			ListGridField field = mapaColumnas.get(clave);

			arregloColumnas[indiceMapa] = field;
			indiceMapa++;
		}

		setFields(arregloColumnas);
		setCanEdit(false);
		setWidth100();
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

	/**
	 * Metodo que agrega al grid los registros creando las columnas
	 * dinamicamente
	 * 
	 * @param listaRegistros
	 */
	public void cargarGridStock(List<RegistroTablaStockDTO> listaRegistros) {

		// Recorremos la lista de registros recibida
		for (Iterator<RegistroTablaStockDTO> iterator = listaRegistros.iterator(); iterator.hasNext();) {

			// Por cada registro de la lista recibida agregamos la columna fecha
			// y creamos un record que sera agregado al grid
			RegistroTablaStockDTO registro = (RegistroTablaStockDTO) iterator.next();
			List<StockDTO> listaColumnas = registro.getColumnas();
			ListGridRecord record = new ListGridRecord();

			record.setAttribute(COLUMNA_CODIGO_REGISTRO, registro.getCodigoRegistro());
			record.setAttribute(COLUMNA_DIA, registro.getFecha());
			record.setAttribute(COLUMNA_NOMBRE_ESTADO, registro.getNombreEstado());
			record.setAttribute(COLUMNA_CODIGO_ESTADO, registro.getCodigoEstado());

			// Recorremos esa lista y agregamos las columnas dinamicamente, para
			// finalmente agregar el registro al grid
			for (Iterator<StockDTO> iterator2 = listaColumnas.iterator(); iterator2.hasNext();) {
				StockDTO stockDTO = (StockDTO) iterator2.next();

				record.setAttribute(stockDTO.getNombreProducto(), stockDTO.getStock());
			}

			addData(record);
		}
	}

	public Map<String, ListGridField> getMapaColumnas() {
		return mapaColumnas;
	}

	public void setMapaColumnas(Map<String, ListGridField> mapaColumnas) {
		this.mapaColumnas = mapaColumnas;
	}

	public static String getNOMBRE_COLUMNA_DIA() {
		return COLUMNA_DIA;
	}
}