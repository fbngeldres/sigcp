package com.dbaccess.cellmanager;

public class PercentFormulaEvaluator implements FormulaEvaluator {
	public static final String PORCENTAJE = "porcentaje";
	public static final String VALOR = "valor";

	public Object evaluate(CellGroups sourceCellGroups) {
		double porcentaje = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(PORCENTAJE));
		double valor = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(VALOR));

		return porcentaje * valor / 100;
	}
}
