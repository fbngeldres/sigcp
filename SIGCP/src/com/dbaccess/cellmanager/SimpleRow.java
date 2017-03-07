package com.dbaccess.cellmanager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleRow implements Row {

	private Map<String, Cell> celdas;

	public SimpleRow() {
		celdas = new HashMap<String, Cell>();
	}

	public Cell getCell(String colName) {
		return celdas.get(colName);
	}

	public void putCell(String colName, Cell cell) {
		celdas.put(colName, cell);
	}

	public List<Cell> getCells() {
		List<Cell> result = new LinkedList<Cell>();
		result.addAll(celdas.values());
		return result;
	}

	public List<String> getColNames() {
		List<String> result = new LinkedList<String>();
		result.addAll(celdas.keySet());
		return result;
	}

}
