package com.dbaccess.cellmanager;

import java.util.List;
import java.util.Map;

public class SumAllFormulaEvaluator implements FormulaEvaluator {

	public Object evaluate(CellGroups sourceCellGroups) {
		double result = 0;
		for (Map.Entry<String, List<Cell>> entry : sourceCellGroups.entrySet()) {
			String key = entry.getKey();
			// HACK: cambiar algun dia por evaluacion real de formulas
			if (key.startsWith("-")) {
				result -= FormulaUtil.sumatoriaCeldas(entry.getValue());
			} else {
				result += FormulaUtil.sumatoriaCeldas(entry.getValue());
			}
		}

		return result;
	}
}
