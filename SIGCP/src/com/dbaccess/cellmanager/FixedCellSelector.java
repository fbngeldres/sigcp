package com.dbaccess.cellmanager;

import java.util.LinkedList;
import java.util.List;

public class FixedCellSelector implements CellSelector {
	private String colName;
	private String rowSourceName;
	private Row fixedRow;

	/**
	 * Crea un nuevo selector de celdas que apunta a una fila predefinida.
	 * 
	 * @param rowSourceName nombre de la fuente de filas
	 * @param colName columna a extraer
	 * @param fixedRow fila predefinida
	 */
	public FixedCellSelector(String rowSourceName, String colName, Row fixedRow) {
		this.rowSourceName = rowSourceName;
		this.colName = colName;
		this.fixedRow = fixedRow;
	}

	public String getColName() {
		return this.colName;
	}

	public String getRowSourceName() {
		return this.rowSourceName;
	}

	public List<Cell> select(List<Row> rowSource) {
		List<Cell> result = new LinkedList<Cell>();

		// Nota: Es m�s �ptimo dejar el fixedCell en el constructor,
		// pero eso no permite switchear entre cells y reevaluar.
		// Por eso se calcula aqu� cada vez.
		Cell fixedCell = fixedRow.getCell(colName);
		if (fixedCell == null) {
			throw new IllegalArgumentException("No se encontr� cell para colName " + colName);
		}
		result.add(fixedCell);
		return result;
	}

}
