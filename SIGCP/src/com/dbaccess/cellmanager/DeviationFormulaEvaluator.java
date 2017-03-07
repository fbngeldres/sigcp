package com.dbaccess.cellmanager;

public class DeviationFormulaEvaluator implements FormulaEvaluator {
	public static final String REAL = "real";
	public static final String EXPECTED = "expected";

	public Object evaluate(CellGroups sourceCellGroups) {
		double real = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(REAL));
		double expected = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(EXPECTED));

		if (expected == 0) {
			// HACK: inifinito es un valor mas correcto. pero da problemas
			return 0;
		}

		return ((expected - real) * 100) / expected;
	}

}
