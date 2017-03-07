package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;

import com.dbaccess.cellmanager.CellGroups;
import com.dbaccess.cellmanager.FormulaEvaluator;
import com.dbaccess.cellmanager.FormulaUtil;

public class PorcentajeCarbonEvaluator implements FormulaEvaluator {
	public static final String CARBON_PUESTO = "carbonPuesto";
	public static final String GRUPO = "grupo";
	public static final String CONSUMOREAL_TM = "consumoRealTm";

	public Object evaluate(CellGroups sourceCellGroups) {
		double carbonPuesto = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(CARBON_PUESTO));
		String grupo = (String) sourceCellGroups.get(GRUPO).get(0).getValue();
		double consumoReal = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(CONSUMOREAL_TM));

		if (ConstantesGWT.GRUPO_CARBON_MIX.equals(grupo)) {
			if (carbonPuesto == 0) {
				// HACK: inifinito es un valor mas correcto. pero da problemas
				return 0;
			}

			return consumoReal / carbonPuesto * 100;
		} else {
			return 100;
		}
	}

}
