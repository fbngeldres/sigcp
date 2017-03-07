package com.dbaccess.cellmanager;

import java.util.Set;

public class SimpleCell implements Cell {
	public Object value;
	private FormulaEvaluator formulaEvaluator;
	private CellSelectorGroup selectorGroup;
	private CellGroups cellGroups;
	private Set<String> phasesToRecalcOn;
	private boolean closed;

	public SimpleCell(CellSelectorGroup selectorGroup, FormulaEvaluator formulaEvaluator, boolean recalcOnlyOnce) {
		this.selectorGroup = selectorGroup;
		this.formulaEvaluator = formulaEvaluator;
	}

	public SimpleCell(CellSelectorGroup selectorGroup, FormulaEvaluator formulaEvaluator, Set<String> phasesToRecalcOn) {
		this.selectorGroup = selectorGroup;
		this.formulaEvaluator = formulaEvaluator;
		this.phasesToRecalcOn = phasesToRecalcOn;
	}

	public SimpleCell(CellSelectorGroup selectorGroup, FormulaEvaluator formulaEvaluator) {
		this.selectorGroup = selectorGroup;
		this.formulaEvaluator = formulaEvaluator;
	}

	public SimpleCell(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public boolean isFormula() {
		return this.formulaEvaluator != null;
	}

	public void setValue(Object value) {
		if (isFormula()) {
			throw new IllegalArgumentException("Cannot set value on formula cell");
		}
		this.value = value;
	}

	public void recalc() {
		// recalc without specifying a phase
		recalc(null);
	}

	public void recalc(String phase) {
		if (phasesToRecalcOn != null && !phasesToRecalcOn.contains(phase)) {
			// skip recalc if current phase is not a phase to recalc on.
			// ignore phases if they are not configured
			return;
		}
		if (!isFormula()) {
			throw new IllegalArgumentException("Cannot recalc non-formula cell");
		}
		if (this.cellGroups == null) {
			throw new IllegalArgumentException("Cannot recalc, cellGroups is null. Call refreshCellGroups first.");
		}
		if (isClosed()) {
			System.out.println("    skipping recalc");
			// skip recalc if already calc and set to calc once
			return;
		}
		this.value = formulaEvaluator.evaluate(this.cellGroups);
	}

	public double getValueAsDouble() {
		if (value instanceof Double) {
			return (Double) value;
		} else if (value instanceof Number) {
			return ((Number) value).doubleValue();
		} else if (value instanceof String) {
			return Double.valueOf((String) value);
		} else {
			throw new IllegalArgumentException("Cannot convert value " + value + " to double");
		}
	}

	@Override
	public String toString() {
		if (value == null) {
			return "#null#";
		}
		return value.toString();
	}

	public CellSelectorGroup getSelectorGroup() {
		return selectorGroup;
	}

	public CellGroups refreshCellGroups(RowSources sources) {
		if (!isFormula()) {
			throw new IllegalArgumentException("Cannot refresh groups on  non-formula cell");
		}
		this.cellGroups = this.selectorGroup.getCellGroups(sources);
		return this.cellGroups;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean isClosed() {
		return closed;
	}

}
