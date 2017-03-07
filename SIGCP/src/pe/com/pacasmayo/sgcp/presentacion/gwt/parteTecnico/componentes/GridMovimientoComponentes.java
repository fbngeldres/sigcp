package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente.ConstantesModuloParteTecnico;
import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.ListGridTesteable;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Servicio;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioAsync;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Validaciones;

import com.dbaccess.cellmanager.CellManager;
import com.dbaccess.cellmanager.CellSelector;
import com.dbaccess.cellmanager.CellSelectorGroup;
import com.dbaccess.cellmanager.DeviationFormulaEvaluator;
import com.dbaccess.cellmanager.FixedCellSelector;
import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.RowFilter;
import com.dbaccess.cellmanager.RowFilterCellSelector;
import com.dbaccess.cellmanager.SimpleRow;
import com.dbaccess.cellmanager.SumAllFormulaEvaluator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.SummaryFunction;
import com.smartgwt.client.widgets.grid.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.events.ChangedHandler;

public class GridMovimientoComponentes extends ListGridTesteable implements ClienteServicioGwt {

	ConstantesModuloParteTecnico CONSTANTES = GWT.create(ConstantesModuloParteTecnico.class);
	ServicioAsync servicioUtil = GWT.create(Servicio.class);

	double porcentajeDesvValido = 0d;

	// private HashMap<String, Double> mapaConsumosParteDiario;
	private final String nombreBunker;
	private PantallaAjusteProduccion pantallaAjusteProduccion;
	private GridMovimientosDeAjuste gridMovimientosDeAjuste;
	private GridConsumoComponentesPuestoTrabajo gridConsumoPuestoTrabajo;
	private CellManager cellManager;
	private String COLUMNA_AJUSTE_LOGISTICO = "ajusteLogistico";

	public static final String COL_CONSUMO_GLOBAL = "consumoPartediario";
	public static final String GRAPH_SOURCE_ID = "gridMovimientoComponentes";

	public GridMovimientoComponentes(PantallaAjusteProduccion pantallaAjusteProduccion, String nombreBunker) {
		this.setID(GRAPH_SOURCE_ID);
		this.pantallaAjusteProduccion = pantallaAjusteProduccion;
		// this.mapaConsumosParteDiario = new HashMap<String, Double>();
		this.nombreBunker = nombreBunker;
		servicioUtil.obtenerPropiedadPorClave(ConstantesGWT.PORCENTAJE_DESV_VALIDO, new AsyncCallback<String>() {

			public void onFailure(Throwable throwable) {
				if (throwable instanceof ServicioGWTGlobalException) {
					ServicioGWTGlobalException e = (ServicioGWTGlobalException) throwable;
					Window.alert(e.getMensaje());
				} else {
					Window.alert(CONSTANTES.falloRegistroAjusteProduccion());
				}
			}

			public void onSuccess(String porcentajeValido) {
				setPorcentajeDesvValido(new Double(porcentajeValido));

				// AA: esto de "CONSTANTES[punto]" es una mala práctica.
				// * Es buscar problemas con espacios y acentos usar name
				// internacionalizado.
				// * Es agregar confusion sobre cuales constantes pertenecen
				// a este grid, mezclandolas con todas las demas constantes.
				// * No permite conseguir las columnas con el autocomplete de
				// Eclipse.
				// * Es agregar trabajo con modificaciones innecesarias de
				// .properties
				// Cambiar algún dia a como están los demás grids, con sus
				// constantes
				// normales y corrientes.
				ListGridField codigoComponente = new ListGridField(CONSTANTES.codigoComponente());
				codigoComponente.setType(ListGridFieldType.INTEGER);
				codigoComponente.setHidden(true);

				ListGridField codigoProductoComponente = new ListGridField(CONSTANTES.codigoProductoComponente());
				codigoProductoComponente.setType(ListGridFieldType.INTEGER);
				codigoProductoComponente.setHidden(true);

				ListGridField componente = new ListGridField(CONSTANTES.colComponente());
				componente.setType(ListGridFieldType.TEXT);
				componente.setCanEdit(false);
				componente.setAlign(Alignment.CENTER);
				componente.setIncludeInRecordSummary(false);
				componente.setShowGridSummary(true);
				componente.setSortDirection(SortDirection.ASCENDING);
				componente.setSummaryFunction(new SummaryFunction() {
					public Object getSummaryValue(Record[] records, ListGridField field) {
						return CONSTANTES.totales();
					}
				});

				ListGridField saldoInicial = new ListGridField(CONSTANTES.colSaldoInicial(), CONSTANTES.tituloColSaldoInicial());
				saldoInicial.setType(ListGridFieldType.FLOAT);
				saldoInicial.setCanEdit(false);
				saldoInicial.setShowGridSummary(true);
				saldoInicial.setAlign(Alignment.CENTER);
				saldoInicial.setSummaryFunction(SummaryFunctionType.SUM);
				asignarFormato(saldoInicial);

				ListGridField ingreso = new ListGridField(CONSTANTES.colIngreso(), CONSTANTES.tituloColIngreso());
				ingreso.setType(ListGridFieldType.FLOAT);
				ingreso.setCanEdit(false);
				ingreso.setShowGridSummary(true);
				ingreso.setAlign(Alignment.CENTER);
				ingreso.setSummaryFunction(SummaryFunctionType.SUM);
				asignarFormato(ingreso);

				ListGridField consumoTM = new ListGridField(CONSTANTES.colConsumo(), CONSTANTES.tituloColConsumo());
				consumoTM.setType(ListGridFieldType.FLOAT);
				consumoTM.setCanEdit(false);
				consumoTM.setShowGridSummary(true);
				consumoTM.setAlign(Alignment.CENTER);
				consumoTM.setSummaryFunction(SummaryFunctionType.SUM);
				asignarFormato(consumoTM);

				ListGridField fisico = new ListGridField(CONSTANTES.colFisico(), CONSTANTES.tituloColFisico());
				fisico.setType(ListGridFieldType.FLOAT);
				fisico.setCanEdit(false);
				fisico.setShowGridSummary(true);
				fisico.setAlign(Alignment.CENTER);
				fisico.setSummaryFunction(SummaryFunctionType.SUM);
				asignarFormato(fisico);

				ListGridField libros = new ListGridField(CONSTANTES.colLibros(), CONSTANTES.tituloColLibros());
				libros.setType(ListGridFieldType.FLOAT);
				libros.setCanEdit(false);
				libros.setShowGridSummary(true);
				libros.setAlign(Alignment.CENTER);
				libros.setSummaryFunction(SummaryFunctionType.SUM);
				addAjusteCellEditorHandler(libros);
				asignarFormato(libros);

				ListGridField fisicoMenosLibros = new ListGridField(CONSTANTES.colFl(), CONSTANTES.tituloColFL());
				fisicoMenosLibros.setType(ListGridFieldType.FLOAT);
				fisicoMenosLibros.setCanEdit(false);
				fisicoMenosLibros.setShowGridSummary(true);
				fisicoMenosLibros.setAlign(Alignment.CENTER);
				fisicoMenosLibros.setSummaryFunction(SummaryFunctionType.SUM);
				asignarFormato(fisicoMenosLibros);

				ListGridField desviacion = new ListGridField(CONSTANTES.colDesviacion(), CONSTANTES.tituloColDesviacion());
				desviacion.setType(ListGridFieldType.FLOAT);
				desviacion.setCanEdit(false);
				desviacion.setShowGridSummary(true);
				desviacion.setAlign(Alignment.CENTER);
				asignarFormato(desviacion);

				ListGridField ajusteLogistico = new ListGridField(COLUMNA_AJUSTE_LOGISTICO);
				ajusteLogistico.setType(ListGridFieldType.FLOAT);
				ajusteLogistico.setHidden(true);

				setWidth100();
				setHeight(400);

				// Se definen caracteristicas generales del grig
				setShowAllRecords(true);
				setCanEdit(true);
				setEditEvent(ListGridEditEvent.CLICK);
				setEditByCell(true);
				setShowGridSummary(true);

				setFields(codigoComponente, codigoProductoComponente, componente, saldoInicial, ingreso, consumoTM, fisico,
						libros, fisicoMenosLibros, desviacion, ajusteLogistico);
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
	 * @param nombreBunker
	 * @param registrosTablaBalanceDTO
	 */
	public void cargaGridConsumoComponentes(List<RegistroTablaConsumoComponentesDTO> registroTablaConsumoComponentesDTO) {

		for (RegistroTablaConsumoComponentesDTO dto : registroTablaConsumoComponentesDTO) {

			if (dto.getNombreComponente().equals(nombreBunker)) {
				continue;
			}

			ListGridRecord registro = new ListGridRecord();

			registro.setAttribute(CONSTANTES.codigoComponente(), dto.getCodigoComponente());
			registro.setAttribute(CONSTANTES.codigoProductoComponente(), dto.getCodigoProductoComponente());
			registro.setAttribute(CONSTANTES.colComponente(), dto.getNombreComponente());

			registro.setAttribute(CONSTANTES.colSaldoInicial(), dto.getSaldoInicial());
			registro.setAttribute(CONSTANTES.colIngreso(), dto.getIngreso() + dto.getAjusteProducto());
			registro.setAttribute(COL_CONSUMO_GLOBAL, dto.getConsumoAjuste());
			registro.setAttribute(COLUMNA_AJUSTE_LOGISTICO, dto.getAjusteLogistico());
			// mapaConsumosParteDiario.put(dto.getCodigoProductoComponente().toString(),
			// );

			registro.setAttribute(CONSTANTES.colFisico(), dto.getFisico());

			addData(registro);
		}

		if (cellManager != null) {
			cellManager.updateSourceHack(GridMovimientoComponentes.GRAPH_SOURCE_ID, getGraphRows());
		}
	}

	private void verificarDesviacion(ListGridRecord recordFuente) {

		double desviacion = recordFuente.getAttributeAsDouble(CONSTANTES.colDesviacion());
		// AA: porque no poner != y listo?
		if (desviacion > porcentajeDesvValido || desviacion < porcentajeDesvValido) {
			recordFuente.set_baseStyle(ConstantesGWT.CSS_LIGHT_RED_BG);
		}
	}

	/**
	 * Método para exportar datos del grid
	 * 
	 * @return
	 */
	public List<RegistroTablaConsumoComponentesDTO> exportarGridConsumoComponentes() {
		List<RegistroTablaConsumoComponentesDTO> registrosDTO = new ArrayList<RegistroTablaConsumoComponentesDTO>();
		ListGridRecord[] records = this.getRecords();

		for (ListGridRecord registro : records) {

			RegistroTablaConsumoComponentesDTO registroDTO = new RegistroTablaConsumoComponentesDTO();

			registroDTO.setCodigoProductoComponente(new Long(registro.getAttribute(CONSTANTES.codigoComponente())));
			registroDTO.setNombreComponente(registro.getAttribute(CONSTANTES.colComponente()));

			registroDTO.setSaldoInicial(registro.getAttributeAsDouble(CONSTANTES.colSaldoInicial()));
			registroDTO.setIngreso(registro.getAttributeAsDouble(CONSTANTES.colIngreso()));

			registroDTO.setConsumo(registro.getAttributeAsDouble(CONSTANTES.colConsumo()));
			registroDTO.setFisico(registro.getAttributeAsDouble(CONSTANTES.colFisico()));

			registrosDTO.add(registroDTO);
		}

		return registrosDTO;
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
	 * Metodo para limpiar grids
	 */
	public void limpiarGrid() {
		ListGridRecord[] records = getRecords();
		if (records.length > 0) {
			for (ListGridRecord listGridRecord : records) {
				removeData(listGridRecord);
			}
		}
	}

	public double ceroSiNull(Double valor) {
		if (valor == null) {
			return 0;
		}
		return valor;
	}

	public LinkedHashMap<String, String> obtenerComponentes() {

		LinkedHashMap<String, String> mapaComponente = new LinkedHashMap<String, String>();
		ListGridRecord[] records = getRecords();
		for (ListGridRecord listGridRecord : records) {
			String codigoComponente = listGridRecord.getAttribute(CONSTANTES.codigoComponente());
			String nombreComponente = listGridRecord.getAttribute(CONSTANTES.colComponente());

			mapaComponente.put(codigoComponente, nombreComponente);
		}

		return mapaComponente;
	}

	/**
	 * @param porcentajeDesvValido the porcentajeDesvValido to set
	 */
	public void setPorcentajeDesvValido(double porcentajeDesvValido) {
		this.porcentajeDesvValido = porcentajeDesvValido;
	}

	public void setGridMovimientosDeAjuste(GridMovimientosDeAjuste gridMovimientosDeAjuste) {
		this.gridMovimientosDeAjuste = gridMovimientosDeAjuste;
	}

	public void setGridConsumoPuestoTrabajo(GridConsumoComponentesPuestoTrabajo gridConsumoPuestoTrabajo) {
		this.gridConsumoPuestoTrabajo = gridConsumoPuestoTrabajo;
	}

	public List<Row> getGraphRows() {
		final String[] columnasFuente = { CONSTANTES.colSaldoInicial(), CONSTANTES.colIngreso(), CONSTANTES.colFisico(),
				COL_CONSUMO_GLOBAL, COLUMNA_AJUSTE_LOGISTICO };
		List<Row> rows = new LinkedList<Row>();

		Map<String, Integer> colNumbers = CellGraphUtil.getColNumbersMap(this);

		int rowNum = 0;
		for (Record record : this.getRecords()) {
			SimpleRow row = CellGraphUtil.convertToRow(this, record, columnasFuente, rowNum, colNumbers);

			// consumoglobal_sin_este_prod = consumoglobal - cpt.consumo -
			// cpt.diferencia - mda.ajuste
			// (esto se calcula solo 1 vez, cuando carga la pantalla)
			CellSelector consumoGlobalSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COL_CONSUMO_GLOBAL, row);
			CellSelector diferenciaSelector = new RowFilterCellSelector(new RowFilter(
					GridConsumoComponentesPuestoTrabajo.CODIGO_PRODUCTO_COMPONENTE, record.getAttribute(CONSTANTES
							.codigoProductoComponente())), GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID,
					GridConsumoComponentesPuestoTrabajo.DIFERENCIA);
			CellSelector consumoCPTSelector = new RowFilterCellSelector(new RowFilter(
					GridConsumoComponentesPuestoTrabajo.CODIGO_PRODUCTO_COMPONENTE, record.getAttribute(CONSTANTES
							.codigoProductoComponente())), GridConsumoComponentesPuestoTrabajo.GRAPH_SOURCE_ID,
					GridConsumoComponentesPuestoTrabajo.CONSUMO_TONELADAS);
			CellSelector ajustesManualesSelector = new RowFilterCellSelector(
					new RowFilter(GridMovimientosDeAjuste.COLUMNA_CODIGO_PRODUCTO_COMP, record.getAttribute(CONSTANTES
							.codigoProductoComponente()), GridMovimientosDeAjuste.COLUMNA_TIPO_MOV, ConstantesGWT.EDITADO_MANUAL),
					GridMovimientosDeAjuste.GRAPH_SOURCE_ID, GridMovimientosDeAjuste.COLUMNA_AJUSTE_CONSUMO);
			// CellSelectorGroup consumoSinAjustesGroup = new CellSelectorGroup(
			// "uno", consumoGlobalSelector,
			// "-dos", consumoCPTSelector,
			// "-tres", diferenciaSelector,
			// "-cuatro", ajustesManualesSelector);
			// row.putCell(CONSTANTES.colConsumoSinAjustes(), new
			// SmartGWTCell(consumoSinAjustesGroup, new
			// SumAllFormulaEvaluator(),
			// record, CONSTANTES.colConsumoSinAjustes(),
			// SmartGWTCell.INVISIBLE_COL, rowNum, this));

			// consumoglobal = consumoglobal_sin_ajustes_este_prod +
			// cpt.diferencia + mda.ajuste
			CellSelectorGroup consumoGroup = new CellSelectorGroup("uno", consumoGlobalSelector, "tres", diferenciaSelector,
					"cuatro", ajustesManualesSelector);
			row.putCell(
					CONSTANTES.colConsumo(),
					new SmartGWTCell(consumoGroup, new SumAllFormulaEvaluator(), record, CONSTANTES.colConsumo(), colNumbers
							.get(CONSTANTES.colConsumo()), rowNum, this));

			// libros = saldoInicial + ingreso - consumo
			CellSelector consumoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.colConsumo(), row);
			CellSelector saldoInicialSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.colSaldoInicial(), row);
			CellSelector ingresoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.colIngreso(), row);
			CellSelector ajusteSelector = new FixedCellSelector(GRAPH_SOURCE_ID, COLUMNA_AJUSTE_LOGISTICO, row);

			CellSelectorGroup librosGroup = new CellSelectorGroup("uno", saldoInicialSelector, "dos", ingresoSelector, "-tres",
					consumoSelector, "cuatro", ajusteSelector);
			row.putCell(
					CONSTANTES.colLibros(),
					new SmartGWTCell(librosGroup, new SumAllFormulaEvaluator(), record, CONSTANTES.colLibros(), colNumbers
							.get(CONSTANTES.colLibros()), rowNum, this));

			// fisicoMenosLibros = fisico - libros
			CellSelector fisicoSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.colFisico(), row);
			CellSelector librosSelector = new FixedCellSelector(GRAPH_SOURCE_ID, CONSTANTES.colLibros(), row);
			CellSelectorGroup fMenosLGroup = new CellSelectorGroup("uno", fisicoSelector, "-dos", librosSelector);
			row.putCell(
					CONSTANTES.colFl(),
					new SmartGWTCell(fMenosLGroup, new SumAllFormulaEvaluator(), record, CONSTANTES.colFl(), colNumbers
							.get(CONSTANTES.colFl()), rowNum, this));

			// desviacion = fMenosL * 100 / fisico
			CellSelectorGroup desviacionGroup = new CellSelectorGroup(DeviationFormulaEvaluator.EXPECTED, fisicoSelector,
					DeviationFormulaEvaluator.REAL, librosSelector);
			row.putCell(CONSTANTES.colDesviacion(), new SmartGWTCell(desviacionGroup, new DeviationFormulaEvaluator(), record,
					CONSTANTES.colDesviacion(), colNumbers.get(CONSTANTES.colDesviacion()), rowNum, this));

			rows.add(row);
			rowNum++;
		}
		return rows;
	}

	public CellManager getCellManager() {
		return cellManager;
	}

	public void setCellManager(CellManager cellManager) {
		this.cellManager = cellManager;
	}

	public void forzarRecalc(SmartGWTCellManager cellManager) {
		// TODO Metodo vacio

	}

	public void cerrarIniciales(SmartGWTCellManager cellManager2) {
		// List<Row> rows = cellManager.getSources().get(GRAPH_SOURCE_ID);
		// for (Row row : rows) {
		// ((SimpleCell)
		// row.getCell(CONSTANTES.colConsumoSinAjustes())).setClosed(true);
		// }
	}

}