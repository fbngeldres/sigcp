package com.dbaccess.cellmanager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RowFilterCellSelector implements CellSelector {
	Map<String, Object> filter;
	private String rowSourceName;
	private String colName;

	/**
	 * Crea un nuevo selector de celdas basado en filtrar filas.
	 * 
	 * @param filter Filtro usado para filtrar las filas
	 * @param rowSourceName Fuente de filas
	 * @param colName Columna a extraer. Las celdas son de esta columna.
	 */
	public RowFilterCellSelector(RowFilter filter, String rowSourceName, String colName) {
		this.filter = filter;
		this.rowSourceName = rowSourceName;
		this.colName = colName;
	}

	public String getColName() {
		return this.colName;
	}

	public List<Cell> select(List<Row> rowSource) {
		List<Cell> result = new LinkedList<Cell>();

		for (Row row : rowSource) {
			if (matchesFilter(row)) {
				result.add(row.getCell(colName));
			}
		}
		return result;
	}

	protected boolean matchesFilter(Row row) {
		for (Map.Entry<String, Object> filterItem : filter.entrySet()) {
			String filterCol = filterItem.getKey();
			Cell cell = row.getCell(filterCol);
			Object expectedValue = filterItem.getValue();
			if (expectedValue == null) {
				throw new IllegalArgumentException("expected value cannot be null in row filter");
			}
			if (cell == null) {
				return false;
			}
			if (!expectedValue.equals(cell.getValue())) {
				return false;
			}
		}

		return true;
	}

	public String getRowSourceName() {
		return rowSourceName;
	}

}
