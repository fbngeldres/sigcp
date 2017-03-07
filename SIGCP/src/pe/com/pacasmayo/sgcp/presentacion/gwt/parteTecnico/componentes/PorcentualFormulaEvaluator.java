package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import com.dbaccess.cellmanager.CellGroups;
import com.dbaccess.cellmanager.FormulaEvaluator;
import com.dbaccess.cellmanager.FormulaUtil;

public class PorcentualFormulaEvaluator implements FormulaEvaluator {
	public static final String SUMA_1 = "SUMA_1";
	public static final String SUMA_2 = "SUMA_2";
	public static final String DIVISOR = "DIVISOR";

	public Object evaluate(CellGroups sourceCellGroups) {
		double suma1 = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(SUMA_1));
		double suma2 = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(SUMA_2));
		double divisor = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(DIVISOR));

		if (divisor == 0) {
			// HACK
			return 0;
		}

		return (suma1 + suma2) / divisor * 100;
	}
}
