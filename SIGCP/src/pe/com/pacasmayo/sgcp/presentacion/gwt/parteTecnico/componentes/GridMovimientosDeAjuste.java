package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.ArrayList;
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

import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.SimpleRow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

// Este grid se llama en la pantalla "Movimientos de Ajuste"
public class GridMovimientosDeAjuste extends ListGridTesteable implements ClienteServicioGwt {

	private Double totalIngresos = 0D;
	private Double totalConsumos = 0D;

	private boolean movimientosGenerados;

	public static final ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);
	public static final String CODIGO_COMPONENTE = "codigoComponente";
	public static final String CODIGO_PUESTO_TRABAJO = "codigoPuestoTrabajo";
	public static final String COLUMNA_PUESTO_TRABAJO = "columnaPuestoTrabajo";
	public static final String COLUMNA_COMPONENTES = "colComponentes";
	public static final String COLUMNA_TIPO_COMPONENTE = "colTipoComponente";
	public static final String COLUMNA_AJUSTE_CONSUMO = "colConsumo";
	public static final String COLUMNA_OBSERVACION = "colObservacion";
	public static final String COLUMNA_TIPO_MOV = "movManual";
	public static final String COLUMNA_ELIMINAR = "eliminar";
	public static final String COLUMNA_CODIGO_PRODUCTO_COMP = "codProducto";
	public static final String GRAPH_SOURCE_ID = "gridMovimientosDeAjuste";

	private static final String[] COLUMNAS_FUENTE_GRAPH = { CODIGO_PUESTO_TRABAJO, COLUMNA_CODIGO_PRODUCTO_COMP,
			COLUMNA_AJUSTE_CONSUMO, COLUMNA_TIPO_MOV };

	private PantallaAjusteProduccion pantallaAjusteProduccion;

	private Map<PuestoYComponente, Double> ajustesManualesComponentesPorPuesto;
	private Map<String, Double> ajustesManualesProductos;
	private SmartGWTCellManager cellManager;
	private ListGridRecord recordPendienteInsercion;

	public GridMovimientosDeAjuste(PantallaAjusteProduccion pantallaAjusteProduccion) {

		super();
		this.setID(GRAPH_SOURCE_ID);
		this.pantallaAjusteProduccion = pantallaAjusteProduccion;
		ListGridField codigoPuestoTrabajo = new ListGridField(CODIGO_PUESTO_TRABAJO);
		codigoPuestoTrabajo.setType(ListGridFieldType.INTEGER);
		codigoPuestoTrabajo.setHidden(true);

		ListGridField columnaPuestoTrabajo = new ListGridField(COLUMNA_PUESTO_TRABAJO, CONSTANTES.tituloColPuestoTrabajo());
		columnaPuestoTrabajo.setType(ListGridFieldType.TEXT);
		columnaPuestoTrabajo.setCanEdit(false);
		columnaPuestoTrabajo.setAlign(Alignment.CENTER);
		columnaPuestoTrabajo.setIncludeInRecordSummary(false);
		columnaPuestoTrabajo.setShowGridSummary(true);
		columnaPuestoTrabajo.setWidth(120);
		columnaPuestoTrabajo.setSummaryFunction(new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				return "Total";
			}
		});

		ListGridField codigoComponente = new ListGridField(CODIGO_COMPONENTE);
		codigoComponente.setType(ListGridFieldType.INTEGER);
		codigoComponente.setHidden(true);

		ListGridField tipoComponenteField = new ListGridField(COLUMNA_TIPO_COMPONENTE, CONSTANTES.tipoComponente());
		tipoComponenteField.setType(ListGridFieldType.TEXT);
		tipoComponenteField.setCanEdit(false);
		tipoComponenteField.setAlign(Alignment.CENTER);
		tipoComponenteField.setIncludeInRecordSummary(false);
		tipoComponenteField.setShowGridSummary(true);

		ListGridField conceptoTablaBalanceField = new ListGridField(COLUMNA_COMPONENTES, CONSTANTES.tituloColComponentes());
		conceptoTablaBalanceField.setType(ListGridFieldType.TEXT);
		conceptoTablaBalanceField.setCanEdit(false);
		conceptoTablaBalanceField.setAlign(Alignment.CENTER);
		conceptoTablaBalanceField.setIncludeInRecordSummary(false);
		conceptoTablaBalanceField.setShowGridSummary(true);

		ListGridField consumo = new ListGridField(COLUMNA_AJUSTE_CONSUMO, CONSTANTES.titleHeaderAjustes());
		consumo.setType(ListGridFieldType.FLOAT);
		consumo.setCanEdit(false);
		consumo.setShowGridSummary(true);
		consumo.setAlign(Alignment.CENTER);
		consumo.setSummaryFunction(calcularSumatoriaColumnaConFormato());
		addAjusteCellEditorHandler(consumo);
		asignarFormato(consumo);

		ListGridField observacion = new ListGridField(COLUMNA_OBSERVACION, CONSTANTES.tituloColObservacion());
		observacion.setType(ListGridFieldType.TEXT);
		observacion.setCanEdit(false);
		observacion.setShowGridSummary(false);
		observacion.setAlign(Alignment.CENTER);
		observacion.setShowHover(true);

		ListGridField regEditado = new ListGridField(COLUMNA_TIPO_MOV, "Tipo Movimiento", 40);
		regEditado.setAlign(Alignment.CENTER);
		regEditado.setType(ListGridFieldType.IMAGE);
		regEditado.setImageURLPrefix("../../images/");
		regEditado.setCanEdit(false);
		regEditado.setImageURLSuffix(".png");
		regEditado.setWidth(80);
		regEditado.setShowHover(true);
		regEditado.setHoverCustomizer(new HoverCustomizer() {
			public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value.toString() == null) {
					return "";
				}

				if (value.toString().indexOf(ConstantesGWT.EDITADO_MANUAL) > 0) {
					return CONSTANTES.hoverMovManual();
				}

				return CONSTANTES.hoverMovAutomatico();
			}
		});

		ListGridField eliminar = new ListGridField(COLUMNA_ELIMINAR, "Eliminar", 40);
		eliminar.setAlign(Alignment.CENTER);
		eliminar.setType(ListGridFieldType.IMAGE);
		eliminar.setImageURLPrefix("../../images/");
		eliminar.setCanEdit(false);
		eliminar.setImageURLSuffix(".png");
		eliminar.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				eliminarRegistro(event.getRecord());
			}
		});

		setWidth100();
		setCellHeight(20);

		setShowAllRecords(true);
		setCanEdit(true);
		// setCanRemoveRecords(true);
		setEditEvent(ListGridEditEvent.CLICK);
		setEditByCell(true);
		setShowGridSummary(true);
		setHeight(400);
		setHoverWidth(200);

		setFields(codigoPuestoTrabajo, columnaPuestoTrabajo, codigoComponente, tipoComponenteField, conceptoTablaBalanceField,
				consumo, observacion, regEditado, eliminar);
	}

	public GridMovimientosDeAjuste(PantallaAjusteProduccion pantallaAjusteProduccion, String id) {
		super();
		this.setID(id);
		this.pantallaAjusteProduccion = pantallaAjusteProduccion;
		ListGridField codigoPuestoTrabajo = new ListGridField(CODIGO_PUESTO_TRABAJO);
		codigoPuestoTrabajo.setType(ListGridFieldType.INTEGER);
		codigoPuestoTrabajo.setHidden(true);

		ListGridField columnaPuestoTrabajo = new ListGridField(COLUMNA_PUESTO_TRABAJO, CONSTANTES.tituloColPuestoTrabajo());
		columnaPuestoTrabajo.setType(ListGridFieldType.TEXT);
		columnaPuestoTrabajo.setCanEdit(false);
		columnaPuestoTrabajo.setAlign(Alignment.CENTER);
		columnaPuestoTrabajo.setIncludeInRecordSummary(false);
		columnaPuestoTrabajo.setShowGridSummary(true);
		columnaPuestoTrabajo.setWidth(120);
		columnaPuestoTrabajo.setSummaryFunction(new SummaryFunction() {
			public Object getSummaryValue(Record[] records, ListGridField field) {
				return "Total";
			}
		});

		ListGridField codigoComponente = new ListGridField(CODIGO_COMPONENTE);
		codigoComponente.setType(ListGridFieldType.INTEGER);
		codigoComponente.setHidden(true);

		ListGridField tipoComponenteField = new ListGridField(COLUMNA_TIPO_COMPONENTE, CONSTANTES.tipoComponente());
		tipoComponenteField.setType(ListGridFieldType.TEXT);
		tipoComponenteField.setCanEdit(false);
		tipoComponenteField.setAlign(Alignment.CENTER);
		tipoComponenteField.setIncludeInRecordSummary(false);
		tipoComponenteField.setShowGridSummary(true);

		ListGridField conceptoTablaBalanceField = new ListGridField(COLUMNA_COMPONENTES, CONSTANTES.tituloColComponentes());
		conceptoTablaBalanceField.setType(ListGridFieldType.TEXT);
		conceptoTablaBalanceField.setCanEdit(false);
		conceptoTablaBalanceField.setAlign(Alignment.CENTER);
		conceptoTablaBalanceField.setIncludeInRecordSummary(false);
		conceptoTablaBalanceField.setShowGridSummary(true);

		ListGridField consumo = new ListGridField(COLUMNA_AJUSTE_CONSUMO, CONSTANTES.titleHeaderAjustes());
		consumo.setType(ListGridFieldType.FLOAT);
		consumo.setCanEdit(false);
		consumo.setShowGridSummary(true);
		consumo.setAlign(Alignment.CENTER);
		consumo.setSummaryFunction(calcularSumatoriaColumnaConFormato());
		addAjusteCellEditorHandler(consumo);
		asignarFormato(consumo);

		ListGridField observacion = new ListGridField(COLUMNA_OBSERVACION, CONSTANTES.tituloColObservacion());
		observacion.setType(ListGridFieldType.TEXT);
		observacion.setCanEdit(false);
		observacion.setShowGridSummary(false);
		observacion.setAlign(Alignment.CENTER);
		observacion.setShowHover(true);

		ListGridField regEditado = new ListGridField(COLUMNA_TIPO_MOV, "Tipo Movimiento", 40);
		regEditado.setAlign(Alignment.CENTER);
		regEditado.setType(ListGridFieldType.IMAGE);
		regEditado.setImageURLPrefix("../../images/");
		regEditado.setCanEdit(false);
		regEditado.setImageURLSuffix(".png");
		regEditado.setWidth(80);
		regEditado.setShowHover(true);
		regEditado.setHoverCustomizer(new HoverCustomizer() {
			public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) {
				if (value.toString() == null) {
					return "";
				}

				if (value.toString().indexOf(ConstantesGWT.EDITADO_MANUAL) > 0) {
					return CONSTANTES.hoverMovManual();
				}

				return CONSTANTES.hoverMovAutomatico();
			}
		});

		ListGridField eliminar = new ListGridField(COLUMNA_ELIMINAR, "Eliminar", 40);
		eliminar.setAlign(Alignment.CENTER);
		eliminar.setType(ListGridFieldType.IMAGE);
		eliminar.setImageURLPrefix("../../images/");
		eliminar.setCanEdit(false);
		eliminar.setImageURLSuffix(".png");
		eliminar.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				eliminarRegistro(event.getRecord());
			}
		});

		setWidth100();
		setCellHeight(20);

		setShowAllRecords(true);
		setCanEdit(true);
		// setCanRemoveRecords(true);
		setEditEvent(ListGridEditEvent.CLICK);
		setEditByCell(true);
		setShowGridSummary(true);
		setHeight(400);
		setHoverWidth(200);

		setFields(codigoPuestoTrabajo, columnaPuestoTrabajo, codigoComponente, tipoComponenteField, conceptoTablaBalanceField,
				consumo, observacion, regEditado, eliminar);
	}

	protected void eliminarRegistro(Record record) {
		try {
			this.removeData(record);
			Row row = cellManager.getRow(record);
			cellManager.deleteRow(GRAPH_SOURCE_ID, row);
			Window.alert("registro eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			GWT.log(e.getMessage());
			Window.alert("ocurrio una falla. contacte al administrador");
		}
	}

	/**
	 * Método que redefine el evento ante un cambio de valor en las celdas
	 * 
	 * @param ajuste
	 */
	// TODO: quitar esto, columna no es editable nunca!
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
	 * Método para cargar los datos al grid de TablaBalance
	 * 
	 * @param registrosTablaBalanceDTO
	 */
	public void cargaGridMovimientosAjuste(List<RegistroTablaAjusteDTO> registrosTablaAjusteDTO) {

		totalIngresos = 0D;
		totalConsumos = 0D;

		String nombrePuestoAux = null;
		String baseStyle = null;

		for (RegistroTablaAjusteDTO registroTablaAjusteDTO : registrosTablaAjusteDTO) {

			ListGridRecord registro = new ListGridRecord();

			// Esto es para distinguir en la gui los registros por puesto de
			// trabajo
			String nombrePuesto = registroTablaAjusteDTO.getNombrePuesto();
			if (!nombrePuesto.equals(nombrePuestoAux) && nombrePuestoAux != null) {
				if (baseStyle == null) {
					baseStyle = ConstantesGWT.CSS_LIGHT_GRAY_BG;
				} else {
					baseStyle = null;
				}
			}
			nombrePuestoAux = nombrePuesto;
			registro.set_baseStyle(baseStyle);

			registro.setAttribute(CODIGO_PUESTO_TRABAJO, registroTablaAjusteDTO.getCodigoPuesto());
			registro.setAttribute(COLUMNA_PUESTO_TRABAJO, nombrePuesto);
			registro.setAttribute(CODIGO_COMPONENTE, registroTablaAjusteDTO.getCodigoComponente());
			registro.setAttribute(COLUMNA_TIPO_COMPONENTE, registroTablaAjusteDTO.getTipoComponente());
			registro.setAttribute(COLUMNA_COMPONENTES, registroTablaAjusteDTO.getNombreComponente());
			registro.setAttribute(COLUMNA_AJUSTE_CONSUMO, registroTablaAjusteDTO.getConsumo());
			registro.setAttribute(COLUMNA_CODIGO_PRODUCTO_COMP, registroTablaAjusteDTO.getCodigoProductoComponente());

			if (registroTablaAjusteDTO.isMovimientoManual()) {
				registro.setAttribute(COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_MANUAL);
			} else {
				registro.setAttribute(COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_AUTOMATICO);
			}

			registro.setAttribute(COLUMNA_ELIMINAR, ConstantesGWT.NOMBRE_IMAGEN_ELIMINAR);

			totalIngresos += registroTablaAjusteDTO.getIngreso();
			totalConsumos += registroTablaAjusteDTO.getConsumo();

			addData(registro);
		}
		recalcularMapas();
		this.movimientosGenerados = true;
	}

	/**
	 * Método para exportar datos del grid
	 * 
	 * @return
	 */
	public List<RegistroTablaAjusteDTO> exportarGridMovimientosAjuste() {
		List<RegistroTablaAjusteDTO> listaDtos = new ArrayList<RegistroTablaAjusteDTO>();
		ListGridRecord[] records = this.getRecords();

		for (ListGridRecord record : records) {
			RegistroTablaAjusteDTO dto = new RegistroTablaAjusteDTO();

			String codigoPuesto = record.getAttribute(CODIGO_PUESTO_TRABAJO);
			dto.setCodigoPuesto(codigoPuesto == null ? null : Long.valueOf(codigoPuesto));

			String codigoProductoComponente = record.getAttribute(COLUMNA_CODIGO_PRODUCTO_COMP);
			dto.setCodigoProductoComponente(codigoProductoComponente == null ? null : Long.valueOf(codigoProductoComponente));

			dto.setNombrePuesto(record.getAttribute(COLUMNA_PUESTO_TRABAJO));

			String tipoComponente = record.getAttribute(COLUMNA_TIPO_COMPONENTE);
			dto.setTipoComponente(tipoComponente);

			String codigoComponente = record.getAttribute(CODIGO_COMPONENTE);
			dto.setCodigoComponente(codigoComponente == null ? null : Long.valueOf(codigoComponente));

			dto.setNombreComponente(record.getAttribute(COLUMNA_COMPONENTES));
			dto.setConsumo(Double.valueOf(record.getAttribute(COLUMNA_AJUSTE_CONSUMO)));

			String valorColMovManual = record.getAttribute(COLUMNA_TIPO_MOV).toString();
			boolean movimientoManual = valorColMovManual.equals(ConstantesGWT.EDITADO_MANUAL);
			dto.setMovimientoManual(movimientoManual);

			listaDtos.add(dto);
		}

		return listaDtos;
	}

	/**
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			removeData(listGridRecord);
		}
		this.movimientosGenerados = false;
	}

	public void limpiarMovimientosAutomaticos() {

		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			String tipoMovimiento = listGridRecord.getAttribute(COLUMNA_TIPO_MOV);
			if (tipoMovimiento.equals(ConstantesGWT.EDITADO_AUTOMATICO)) {
				removeData(listGridRecord);
			}
		}
		this.movimientosGenerados = false;
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

	private SummaryFunction calcularSumatoriaColumnaConFormato() {
		return new SummaryFunction() {

			public Object getSummaryValue(Record[] records, ListGridField field) {
				Double total = 0D;
				for (Record record : records) {
					Double rowValue = record.getAttributeAsDouble(field.getName());
					if (rowValue != null) {
						total += rowValue;
					}
				}

				try {
					NumberFormat nf = NumberFormat.getFormat(ConstantesGWT.FORMATO_NUMERO_DOS_DECIMALES);
					return nf.format(total);
				} catch (Exception e) {
					return total;
				}
			}

		};
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

	public boolean isMovimientosGenerados() {
		return movimientosGenerados;
	}

	public void setMovimientosGenerados(boolean movimientosGenerados) {
		this.movimientosGenerados = movimientosGenerados;
	}

	public Set<String> getCodProductoCompDeMovimientoAjusteProducto() {
		Set<String> codProductoComps = new HashSet<String>();

		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			String tipoComponente = listGridRecord.getAttribute(COLUMNA_TIPO_COMPONENTE);
			String codProductoComp = listGridRecord.getAttribute(COLUMNA_CODIGO_PRODUCTO_COMP);
			if (tipoComponente.equals(ConstantesGWT.TIPO_COMPONENTE_PRODUCTOS)) {
				codProductoComps.add(codProductoComp);
			}
		}

		return codProductoComps;
	}

	public void agregarRegistroManual(ListGridRecord record) {
		addData(record);

		boolean esProducto = ConstantesGWT.TIPO_COMPONENTE_PRODUCTOS.equals(record.getAttribute(COLUMNA_TIPO_COMPONENTE));
		this.recordPendienteInsercion = record;
		if (esProducto) {
			// Si el ajuste es de un producto arbitrario (y no hoja de ruta),
			// cargar de BD el grid de nuevo, con el nuevo producto
			// (este metodo sin parametros llama a los records del grid
			// intermante).
			// TODO: optimizar recargando solo si el producto es realmente nuevo
			// Esta llamada es asíncrona, y termina con completarInsert
			pantallaAjusteProduccion.cargarTablaMovimientoComponentes();
		} else {
			// Con hoja de ruta no hay recarga de grid asíncrona,
			// y se termina la inserción en el momento.
			completarInsert();
		}

	}

	public void completarInsert() {
		if (this.recordPendienteInsercion == null) {
			return;
		}

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);
		// Nota: este rowNum puede estar mal. Como no se le hace refresh
		// a estas celdas no debería causar problemas.
		SimpleRow row = getGraphRow(colNumbers, this.getRecords().length, this.recordPendienteInsercion);
		cellManager.insertRow(GRAPH_SOURCE_ID, row);
		this.recordPendienteInsercion = null;
		Window.alert(CONSTANTES.movimientoManualExitoso());

		pantallaAjusteProduccion.getDialogoCrearMovManual().hide();
	}

	private void actualizarGridMovimientoComponentes() {
		pantallaAjusteProduccion.cargarTablaMovimientoComponentes();
	}

	public Map<PuestoYComponente, Double> obtenerAjustesManualesComponentesPorPuesto() {
		return this.ajustesManualesComponentesPorPuesto;
	}

	public void recalcularAjustesManualesComponentesPorPuesto() {
		ajustesManualesComponentesPorPuesto = new HashMap<PuestoYComponente, Double>();

		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			boolean esManual = ConstantesGWT.EDITADO_MANUAL.equals(listGridRecord.getAttribute(COLUMNA_TIPO_MOV));

			// Saltar ajustes no manuales
			if (!esManual) {
				continue;
			}

			// Saltar ajustes que no sean de hoja ruta (componente)
			if (!ConstantesGWT.TIPO_COMPONENTE_HOJA_RUTA.equals(listGridRecord.getAttribute(COLUMNA_TIPO_COMPONENTE))) {
				continue;
			}

			// acumular el ajuste en el mapa de ajustes
			PuestoYComponente puestoYComp = new PuestoYComponente(listGridRecord.getAttribute(CODIGO_PUESTO_TRABAJO),
					listGridRecord.getAttribute(CODIGO_COMPONENTE));
			Double ajuste = listGridRecord.getAttributeAsDouble(COLUMNA_AJUSTE_CONSUMO);
			acumular(ajustesManualesComponentesPorPuesto, puestoYComp, ajuste);
		}
	}

	public Map<String, Double> obtenerAjustesManualesProductos() {
		return this.ajustesManualesProductos;
	}

	public void recalcularMapas() {
		recalcularAjustesManualesComponentesPorPuesto();
		recalcularAjustesManualesProductos();
	}

	public void recalcularAjustesManualesProductos() {
		ajustesManualesProductos = new HashMap<String, Double>();

		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			boolean esManual = ConstantesGWT.EDITADO_MANUAL.equals(listGridRecord.getAttribute(COLUMNA_TIPO_MOV));

			// Saltar ajustes no manuales
			if (!esManual) {
				continue;
			}

			// Saltar ajustes que no sean de hoja ruta (componente)
			if (!ConstantesGWT.TIPO_COMPONENTE_PRODUCTOS.equals(listGridRecord.getAttribute(COLUMNA_TIPO_COMPONENTE))) {
				continue;
			}

			// acumular el ajuste en el mapa de ajustes
			String codigoComp = listGridRecord.getAttribute(COLUMNA_CODIGO_PRODUCTO_COMP);
			Double ajuste = listGridRecord.getAttributeAsDouble(COLUMNA_AJUSTE_CONSUMO);
			acumular(ajustesManualesProductos, codigoComp, ajuste);
		}
	}

	/**
	 * Si no hay valor para la clave, asigna el valor. Si hay valor para la
	 * clave, suma el valor al que ya existía.
	 * 
	 * @param mapa
	 * @param clave
	 * @param valor
	 */
	public void acumular(Map<PuestoYComponente, Double> mapa, PuestoYComponente clave, Double valor) {
		Double valorActual = mapa.get(clave);
		if (valorActual == null) {
			mapa.put(clave, valor);
		} else {
			mapa.put(clave, valorActual + valor);
		}
	}

	/**
	 * Si no hay valor para la clave, asigna el valor. Si hay valor para la
	 * clave, suma el valor al que ya existía.
	 * 
	 * @param mapa
	 * @param clave
	 * @param valor
	 */
	public void acumular(Map<String, Double> mapa, String clave, Double valor) {
		Double valorActual = mapa.get(clave);
		if (valorActual == null) {
			mapa.put(clave, valor);
		} else {
			mapa.put(clave, valorActual + valor);
		}
	}

	public List<Row> getGraphRows() {
		List<Row> rows = new LinkedList<Row>();

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		int rowNum = 0;
		for (Record record : this.getRecords()) {
			SimpleRow row = getGraphRow(colNumbers, rowNum, record);
			rows.add(row);
			rowNum++;
		}
		return rows;
	}

	private SimpleRow getGraphRow(Map<String, Integer> colNumbers, int rowNum, Record record) {
		SimpleRow row = CellGraphUtil.convertToRow(this, record, COLUMNAS_FUENTE_GRAPH, rowNum, colNumbers);
		return row;
	}

	public void setCellManager(SmartGWTCellManager cellManager) {
		this.cellManager = cellManager;
	}

}
