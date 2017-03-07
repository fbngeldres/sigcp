package com.dbaccess.cellmanager;

public interface Cell {
	public boolean isFormula();

	public Object getValue();

	public double getValueAsDouble();

	public void setValue(Object value);

	/**
	 * Recalcula el valor de la celda. Toma los valores de los que depende de
	 * los cellGroups internos, y con eso llama a la formula interna.
	 * 
	 * @param sourceCellGroups
	 */
	public void recalc();

	/**
	 * @param sources
	 * @return
	 */
	public CellGroups refreshCellGroups(RowSources sources);

	public CellSelectorGroup getSelectorGroup();
}
