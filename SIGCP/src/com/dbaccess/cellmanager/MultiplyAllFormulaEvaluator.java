package com.dbaccess.cellmanager;

import java.util.List;
import java.util.Map;

public class MultiplyAllFormulaEvaluator implements FormulaEvaluator {

	public Object evaluate(CellGroups sourceCellGroups) {
		double result = 0;
		for (Map.Entry<String, List<Cell>> entry : sourceCellGroups.entrySet()) {
			// HACK: cambiar algun dia por evaluacion real de formulas
			result *= FormulaUtil.sumatoriaCeldas(entry.getValue());
		}

		return result;
	}
}
