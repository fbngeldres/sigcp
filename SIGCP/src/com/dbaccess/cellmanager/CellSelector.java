package com.dbaccess.cellmanager;

import java.util.List;

/**
 * Un CellSelector retorna un conjunto de celdas, todas de la misma columna, a
 * partir de unas filas y un filtro.
 * 
 * @author Alfredo
 */
public interface CellSelector {
	public List<Cell> select(List<Row> rowSource);

	public String getRowSourceName();

	public String getColName();
}
