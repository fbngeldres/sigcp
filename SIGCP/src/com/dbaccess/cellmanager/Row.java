package com.dbaccess.cellmanager;

import java.util.List;

public interface Row {
	public List<Cell> getCells();

	public Cell getCell(String colName);

	public void putCell(String colName, Cell cell);

	public List<String> getColNames();
}
