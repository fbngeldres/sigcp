package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.LinkedList;
import java.util.List;

import com.dbaccess.cellmanager.Cell;
import com.dbaccess.cellmanager.Row;
import com.dbaccess.cellmanager.RowFilter;
import com.dbaccess.cellmanager.RowFilterCellSelector;

public class ExcludeRowFilterCellSelector extends RowFilterCellSelector {

	private Row excludedRow;

	public ExcludeRowFilterCellSelector(RowFilter filter, String rowSourceName, String colName, Row excludedRow) {
		super(filter, rowSourceName, colName);
		this.excludedRow = excludedRow;
	}

	@Override
	public List<Cell> select(List<Row> rowSource) {
		List<Cell> result = new LinkedList<Cell>();

		for (Row row : rowSource) {
			if (matchesFilter(row) && !row.equals(excludedRow)) {
				result.add(row.getCell(getColName()));
			}
		}
		return result;
	}

}
