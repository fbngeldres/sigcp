package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dbaccess.cellmanager.Cell;
import com.dbaccess.cellmanager.CellManager;
import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.RowSources;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SmartGWTCellManager extends CellManager {
	private Map<Record, Row> mapaRecordARow;
	private Map<Record, Map<Integer, Cell>> mapaRecordACell;
	private Set<ListGridField> fieldsMonitoreados;
	private ListGrid[] gridsToRefresh;

	public SmartGWTCellManager(RowSources sources) {
		super(sources);
	}

	@Override
	public void rebuildGraphAndDependencies() {
		super.rebuildGraphAndDependencies();
		recalcMapaRecordARow();
		attachCellSaveHandlers(getSources());
	}

	private void recalcMapaRecordARow() {
		mapaRecordARow = new HashMap<Record, Row>();
		for (Map.Entry<String, List<Row>> source : getSources().entrySet()) {
			for (Row row : source.getValue()) {
				if (!(row instanceof SmartGWTRow)) {
					// Solo interesan los SmartGWTRow
					continue;
				}
				SmartGWTRow sRow = (SmartGWTRow) row;
				mapaRecordARow.put(sRow.getRecord(), sRow);
			}
		}
	}

	public Row getRow(Record record) {
		return mapaRecordARow.get(record);
	}

	public Cell getCell(Record record, int colNum) {
		return mapaRecordACell.get(record).get(colNum);
	}

	public void attachCellSaveHandlers(RowSources sources) {
		mapaRecordACell = new HashMap<Record, Map<Integer, Cell>>();
		fieldsMonitoreados = new HashSet<ListGridField>();

		for (Map.Entry<String, List<Row>> source : sources.entrySet()) {
			for (Row row : source.getValue()) {
				for (Cell cell : row.getCells()) {
					if (!(cell instanceof SmartGWTCell)) {
						// Solo interesan los SmartGWTCell
						continue;
					}
					SmartGWTCell sCell = (SmartGWTCell) cell;
					if (sCell.isFormula()) {
						// Solo interesan los campos no-formula (que son los
						// editables)
						continue;
					}

					// Construir mapa de record+colNum -> Cell
					Map<Integer, Cell> mapaColCell = mapaRecordACell.get(sCell.getRecord());
					if (mapaColCell == null) {
						mapaColCell = new HashMap<Integer, Cell>();
						mapaRecordACell.put(sCell.getRecord(), mapaColCell);
					}
					mapaColCell.put(sCell.getColNum(), sCell);

					// Attachear handler a la columna (listgridfield)
					ListGridField field = sCell.getGrid().getField(sCell.getProperty());
					if (field == null) {
						// Saltar campos invisibles. El grid los reporta en null
						continue;
					}
					if (fieldsMonitoreados.contains(field)) {
						// Saltar campos ya monitoreados
						continue;
					}
					field.addCellSavedHandler(new CellManagerCellSaveHandler(this));
					fieldsMonitoreados.add(field);
				}
			}
		}
	}

	public ListGrid[] getGridsToRefresh() {
		return gridsToRefresh;
	}

	public void setGridsToRefresh(ListGrid[] gridsToRefresh) {
		this.gridsToRefresh = gridsToRefresh;
	}

	@Override
	public void afterMassRecalc() {
		for (ListGrid grid : gridsToRefresh) {
			// HACK: esta no es la forma mas limpia ni optima de lograr el
			// refresh.
			// La mejor forma es refreshCell (que se probó y se sabe que sirve)
			// + recalculateSummaries (que esta disponible solo en versiones mas
			// nuevas de smartgwt).
			// Con solo refreshCell las celdas cambian pero el !#@ summary no.
			// Este truco de add y remove hace que cambie la celda y el summary.
			Record record = new Record();
			grid.addData(record);
			grid.removeData(record);
		}
	}

}
