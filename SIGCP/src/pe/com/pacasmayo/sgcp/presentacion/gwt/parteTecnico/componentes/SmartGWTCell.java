package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import com.dbaccess.cellmanager.CellSelectorGroup;
import com.dbaccess.cellmanager.FormulaEvaluator;
import com.dbaccess.cellmanager.SimpleCell;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;

public class SmartGWTCell extends SimpleCell {
	public static int INVISIBLE_COL = -1;
	private Record record;
	private String property;
	private int colNum;
	private int rowNum;
	private ListGrid grid;

	public SmartGWTCell(CellSelectorGroup selectorGroup, FormulaEvaluator formulaEvaluator, Record record, String property,
			int colNum, int rowNum, ListGrid grid) {
		super(selectorGroup, formulaEvaluator);
		this.record = record;
		this.property = property;
		this.colNum = colNum;
		this.rowNum = rowNum;
		this.grid = grid;
	}

	public SmartGWTCell(Record record, String property, int colNum, int rowNum, ListGrid grid) {
		super(record.getAttribute(property));
		this.record = record;
		this.property = property;
		this.colNum = colNum;
		this.rowNum = rowNum;
		this.grid = grid;
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		this.record.setAttribute(property, value);
		// nota: rowNum parece ser siempre el del recordlist, independientemente
		// de los sorts en pantalla
		// if (colNum != INVISIBLE_COL) {
		// this.grid.refreshCell(rowNum, colNum);
		// }
	}

	@Override
	public void recalc(String phase) {
		super.recalc(phase);
		this.record.setAttribute(property, this.getValue());
	}

	@Override
	public void recalc() {
		recalc(null);
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public Record getRecord() {
		return record;
	}

	public String getProperty() {
		return property;
	}

	public int getColNum() {
		return colNum;
	}

	public ListGrid getGrid() {
		return grid;
	}

	@Override
	public String toString() {
		return grid.getID() + "[" + this.rowNum + "]." + this.property + "=" + this.value;
	}

}
