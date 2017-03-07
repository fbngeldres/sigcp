package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.HashMap;
import java.util.Map;

import com.dbaccess.cellmanager.SimpleCell;
import com.dbaccess.cellmanager.SimpleRow;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CellGraphUtil {

	public static Map<String, Integer> getColNumbersMap(ListGrid grid) {
		Map<String, Integer> colNumbers = new HashMap<String, Integer>();
		ListGridField[] fields = grid.getFields();
		int colNum = 0;
		for (ListGridField listGridField : fields) {
			String name = listGridField.getName();
			colNumbers.put(name, colNum);
			colNum++;
		}
		return colNumbers;
	}

	public static SimpleRow convertToRow(ListGrid grid, Record record, String[] properties, int rowNum,
			Map<String, Integer> colNumbers) {
		SmartGWTRow row = new SmartGWTRow(record);
		for (String property : properties) {
			Integer colNumber = colNumbers.get(property);
			if (colNumber == null) {
				// hidden attribute, use simplecell (which does not refresh
				// grid)
				row.putCell(property, new SimpleCell(record.getAttribute(property)));
			} else {
				// visible attribute, use SmartGWTCell (which DOES refresh grid)
				row.putCell(property, new SmartGWTCell(record, property, colNumber, rowNum, grid));
			}
		}
		return row;
	}

}
