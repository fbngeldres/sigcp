package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import com.dbaccess.cellmanager.CellGroups;
import com.dbaccess.cellmanager.FormulaEvaluator;
import com.dbaccess.cellmanager.FormulaUtil;

public class PercentMinusFormulaEvaluator implements FormulaEvaluator {
	public static final String PORCENTAJE = "porcentaje";
	public static final String VALOR = "valor";
	public static final String RESTA = "resta";

	public Object evaluate(CellGroups sourceCellGroups) {
		double porcentaje = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(PORCENTAJE));
		double valor = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(VALOR));
		double resta = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(RESTA));

		return (porcentaje * valor / 100) - resta;
	}
}
