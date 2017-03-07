package com.dbaccess.cellmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowSources extends HashMap<String, List<Row>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5974314901203612129L;

	public String toSemiJson() {
		final String INDENT = "  ";
		final String DBLQUOTE = "\"";
		StringBuffer buffer = new StringBuffer();

		for (Map.Entry<String, List<Row>> source : this.entrySet()) {
			buffer.append("------" + source.getKey() + "\n");
			buffer.append("{");
			for (Row row : source.getValue()) {
				buffer.append("{\n");
				for (String name : row.getColNames()) {
					buffer.append(INDENT + DBLQUOTE + name + DBLQUOTE + ":" + row.getCell(name).getValue() + "\n");
				}
				buffer.append("}\n");
			}
		}

		return buffer.toString();
	}
}
