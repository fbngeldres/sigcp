package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import com.dbaccess.cellmanager.CellGroups;
import com.dbaccess.cellmanager.FormulaEvaluator;
import com.dbaccess.cellmanager.FormulaUtil;

public class ReglaDeTresFormulaEvaluator implements FormulaEvaluator {
	public static final String DIVIDENDO = "dividendo";
	public static final String DIVISOR = "divisor";
	public static final String MULTIPLICADOR = "multiplicador";

	public Object evaluate(CellGroups sourceCellGroups) {
		double dividendo = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(DIVIDENDO));
		double divisor = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(DIVISOR));
		double multiplicador = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(MULTIPLICADOR));

		return dividendo / divisor * multiplicador;
	}
}
