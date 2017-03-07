package com.dbaccess.cellmanager;

import java.util.HashMap;

public class RowFilter extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7420461073016291625L;

	/**
	 * Se asume que vienen impares = clave, pares = valor.
	 * 
	 * @param objects
	 * @return
	 */
	public RowFilter(Object... objects) {
		if (objects.length % 2 != 0) {
			throw new IllegalArgumentException("Numero de argumentos debe ser par.");
		}
		int total = objects.length;
		for (int i = 0; i < total; i += 2) {
			this.put((String) objects[i], objects[i + 1]);
		}
	}
}
