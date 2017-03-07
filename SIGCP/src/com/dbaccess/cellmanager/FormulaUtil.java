package com.dbaccess.cellmanager;

import java.util.List;

public class FormulaUtil {
	public static double sumatoriaCeldas(List<Cell> cells) {
		double sumatoria = 0;
		for (Cell cell : cells) {
			sumatoria += cell.getValueAsDouble();
		}
		return sumatoria;
	}

	public static Double kcalHHClinker(double consumoCarbonSeco, double poderCalorificoCarbonPonderado,
			double consumoPetroleoBunker, double produccionTotalRealClinker, double FACTOR_GALONES, double DENSIDAD_PETROLEO,
			double PODER_CALORIFICO_PETROLEO) {

		return ((consumoCarbonSeco * poderCalorificoCarbonPonderado) + (((consumoPetroleoBunker * FACTOR_GALONES) / 1000.0)
				* DENSIDAD_PETROLEO * PODER_CALORIFICO_PETROLEO))
				/ produccionTotalRealClinker;
	}

	public static Double kcalHVClinker(double consumoCarbonSeco, double poderCalorificoCarbonPonderado,
			double produccionTotalRealClinker, double PORC_HUMEDAD_CRUDO_NEGRO) {

		return ((consumoCarbonSeco * poderCalorificoCarbonPonderado) + (PORC_HUMEDAD_CRUDO_NEGRO * 100.0))
				/ produccionTotalRealClinker;
	}

	public static Double kcalHorno1Cal(double consumoCarbonSeco, double poderCalorificoCarbonPonderado, double consumoPetroleo,
			double produccionTotalCal, double DENSIDAD_PETROLEO_CAL, double FACTOR_GALONES, double PODER_CALORIFICO_PETROLEO_CAL) {

		return ((consumoCarbonSeco * poderCalorificoCarbonPonderado) + (DENSIDAD_PETROLEO_CAL
				* ((consumoPetroleo * FACTOR_GALONES) / 1000.0) * PODER_CALORIFICO_PETROLEO_CAL))
				/ produccionTotalCal;
	}

	public static Double kcalHcalCal(double consumoPetroleo, double consumoCarbonSeco, double poderCalorificoCarbonPonderado,
			double produccionCalTM, double FACTOR_GALONES, double DENSIDAD_PETROLEO_CAL, double PODER_CALORIFICO_PETROLEO_CAL,
			double CAL_RECHAZADA) {

		return ((((consumoPetroleo * FACTOR_GALONES) / 1000.0) * DENSIDAD_PETROLEO_CAL * PODER_CALORIFICO_PETROLEO_CAL) + (consumoCarbonSeco * poderCalorificoCarbonPonderado))
				/ ((produccionCalTM + CAL_RECHAZADA));
	}
}
