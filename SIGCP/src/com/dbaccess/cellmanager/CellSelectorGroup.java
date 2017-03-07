package com.dbaccess.cellmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CellSelectorGroup extends HashMap<String, CellSelector> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3271386233097246533L;

	/**
	 * Se asume que vienen impares = clave, pares = valor.
	 * 
	 * @param objects
	 * @return
	 */
	public CellSelectorGroup(Object... objects) {
		if (objects.length % 2 != 0) {
			throw new IllegalArgumentException("Numero de argumentos debe ser par.");
		}
		int total = objects.length;
		for (int i = 0; i < total; i += 2) {
			this.put((String) objects[i], (CellSelector) objects[i + 1]);
		}
	}

	public CellGroups getCellGroups(RowSources sources) {
		CellGroups groups = new CellGroups();
		for (Map.Entry<String, CellSelector> entry : this.entrySet()) {
			CellSelector selector = entry.getValue();
			List<Row> source = sources.get(selector.getRowSourceName());
			groups.put(entry.getKey(), selector.select(source));
		}
		return groups;
	}

}
