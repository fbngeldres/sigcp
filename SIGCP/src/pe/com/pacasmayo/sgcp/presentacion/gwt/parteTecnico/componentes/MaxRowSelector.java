package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dbaccess.cellmanager.Row;

public class MaxRowSelector {
	public static Collection<Row> select(List<Row> inputRows, String valueField, String groupByField) {
		// En este mapa:
		// key = groupByField
		// value = la fila con el maximo valor para ese groupByField
		Map<String, Row> maxMap = new HashMap<String, Row>();
		for (Row row : inputRows) {
			String key = (String) row.getCell(groupByField).getValue();

			Row maxRow = maxMap.get(key);
			if (maxRow == null) {
				// Si ninguna fila es el máximo, poner esta
				maxMap.put(key, row);
				continue;
			}

			double currentMax = maxRow.getCell(valueField).getValueAsDouble();
			double currentValue = row.getCell(valueField).getValueAsDouble();
			if (currentValue > currentMax) {
				// Si el valor en esta fila es mayor que el máximo, poner esta
				maxMap.put(key, row);
			}
		}

		return maxMap.values();
	}

	/**
	 * Este metodo sirve para obtener una lista de filas por productos
	 * 
	 * @param inputRows
	 * @param groupByField
	 * @param componente
	 * @return
	 */
	public static Collection<Row> selectByProducto(List<Row> inputRows, String groupByField, Long componente) {
		// En este mapa:
		// key = groupByField
		// value = la fila con el maximo valor para ese groupByField
		Map<Integer, Row> maxMap = new HashMap<Integer, Row>();
		int i = 0;
		for (Row row : inputRows) {
			String key = (String) row.getCell(groupByField).getValue();

			if (key.equals(componente.toString())) {
				i++;
				maxMap.put(i, row);

			}

		}
		return maxMap.values();
	}
}
