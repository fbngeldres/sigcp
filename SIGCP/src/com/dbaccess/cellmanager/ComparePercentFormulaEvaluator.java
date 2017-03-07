package com.dbaccess.cellmanager;

public class ComparePercentFormulaEvaluator implements FormulaEvaluator {
	public static final String DIVIDENDO = "dividendo";
	public static final String DIVISOR = "divisor";

	public Object evaluate(CellGroups sourceCellGroups) {
		double dividendo = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(DIVIDENDO));
		double divisor = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(DIVISOR));

		if (divisor == 0) {
			// HACK: inifinito es un valor mas correcto. pero da problemas
			return 0;
		}

		return dividendo / divisor * 100;
	}
}
