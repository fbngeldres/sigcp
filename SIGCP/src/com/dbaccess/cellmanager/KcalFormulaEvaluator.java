package com.dbaccess.cellmanager;

import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.PantallaAjusteProduccion;

public class KcalFormulaEvaluator implements FormulaEvaluator {
	// nombres para el filtrado de la formula
	public static final String NOMBRE_PUESTOTRABAJO = "nombrePuestoTrabajo";
	public static final String NOMBRE_PRODUCTO = "nombreProducto";

	public static final String CONSUMO_CARBON_SECO = "consumoCarbonSeco";
	public static final String PODER_CALORIFICO_CARBON_PONDERADO = "poderCalorificoCarbon";
	public static final String CONSUMO_PETROLEO_BUNKER = "consumoPetroleoBunker";
	public static final String PRODUCCION_REAL_PT = "produccionTotalRealPT";

	public static final Double PORC_HUMEDAD_CRUDO_NEGRO = 0d; // Por petici�n
	// del usuario
	public static final Double CAL_RECHAZADA = 0d; // Por petici�n del usuario
	private final double FACTOR_GALONES = 3.785412d;

	public static final Double DENSIDAD_PETROLEO = Double.parseDouble(PantallaAjusteProduccion.DENSIDAD_PETROLEO_VALOR);
	public static final Double DENSIDAD_PETROLEO_CAL = Double.parseDouble(PantallaAjusteProduccion.DENSIDAD_PETROLEO_CAL_VALOR);
	public static final Double PODER_CALORIFICO_PETROLEO = Double
			.parseDouble(PantallaAjusteProduccion.PODER_CALORIFICO_PETROLEO_VALOR);
	public static final Double PODER_CALORIFICO_PETROLEO_CAL = Double
			.parseDouble(PantallaAjusteProduccion.PODER_CALORIFICO_PETROLEO_CAL_VALOR);
	public static final Double PROGRESION_CARBON_KCAL = Double.parseDouble(PantallaAjusteProduccion.PROGRESION_CARBON_KCAL_VALOR);
	public static final Double PROGRESION_BUNKER_KCAL = Double.parseDouble(PantallaAjusteProduccion.PROGRESION_BUNKER_KCAL_VALOR);

	// validacion
	public static final String VALOR_MAX = "valorMax";

	public Object evaluate(CellGroups sourceCellGroups) {
		Double formula = 0d;

		String nombrePuestoTrabajo = sourceCellGroups.get(NOMBRE_PUESTOTRABAJO).get(0).getValue().toString();
		String nombreProducto = sourceCellGroups.get(NOMBRE_PRODUCTO).get(0).getValue().toString();
		double poderCalorificoCarbonPonderado = FormulaUtil.sumatoriaCeldas(sourceCellGroups
				.get(PODER_CALORIFICO_CARBON_PONDERADO));
		double consumoPetroleoBunker = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(CONSUMO_PETROLEO_BUNKER));
		double produccionReal = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(PRODUCCION_REAL_PT));

		Long valorMax = Long.valueOf(sourceCellGroups.get(VALOR_MAX).get(0).getValue().toString());

		double consumoCarbonSeco = FormulaUtil.sumatoriaCeldas(sourceCellGroups.get(CONSUMO_CARBON_SECO));

		if (nombrePuestoTrabajo != null && nombreProducto != null) {
			if (nombrePuestoTrabajo.startsWith("HORNO")) {
				if (nombreProducto.startsWith("CLK -")) {
					formula = kcalHHClinker(consumoCarbonSeco, poderCalorificoCarbonPonderado, consumoPetroleoBunker,
							produccionReal);

					double carbon = 0d;
					double calentBunker = 0d;
					if (valorMax != -1) {
						while (formula > valorMax && consumoPetroleoBunker >= PROGRESION_BUNKER_KCAL) {
							calentBunker += PROGRESION_BUNKER_KCAL;
							consumoPetroleoBunker -= PROGRESION_BUNKER_KCAL;

							formula = kcalHHClinker(consumoCarbonSeco, poderCalorificoCarbonPonderado, consumoPetroleoBunker,
									produccionReal);
						}

						while (formula > valorMax && consumoCarbonSeco >= PROGRESION_CARBON_KCAL) {
							carbon += PROGRESION_CARBON_KCAL;
							consumoCarbonSeco -= PROGRESION_CARBON_KCAL;

							formula = kcalHHClinker(consumoCarbonSeco, poderCalorificoCarbonPonderado, consumoPetroleoBunker,
									produccionReal);
						}
					}
				} else if (nombrePuestoTrabajo.startsWith("HORNO 1") && nombreProducto.startsWith("CAL- GRANULADA")) {
					formula = kcalHorno1Cal(consumoCarbonSeco, poderCalorificoCarbonPonderado, consumoPetroleoBunker,
							produccionReal);

					double carbon = 0d;
					double calentBunker = 0d;
					if (valorMax != -1) {
						while (formula > valorMax && consumoPetroleoBunker >= PROGRESION_BUNKER_KCAL) {
							calentBunker += PROGRESION_BUNKER_KCAL;
							consumoPetroleoBunker -= PROGRESION_BUNKER_KCAL;

							formula = kcalHorno1Cal(consumoCarbonSeco, poderCalorificoCarbonPonderado, consumoPetroleoBunker,
									produccionReal);
						}

						while (formula > valorMax && consumoCarbonSeco >= PROGRESION_CARBON_KCAL) {
							carbon += PROGRESION_CARBON_KCAL;
							consumoCarbonSeco -= PROGRESION_CARBON_KCAL;

							formula = kcalHorno1Cal(consumoCarbonSeco, poderCalorificoCarbonPonderado, consumoPetroleoBunker,
									produccionReal);
						}
					}
				}
			} else if (nombrePuestoTrabajo.startsWith("HV-")) {
				if (nombreProducto.startsWith("CLK -")) {
					formula = kcalHVClinker(consumoCarbonSeco, poderCalorificoCarbonPonderado, produccionReal);

					double carbon = 0d;
					double calentBunker = 0d;
					if (valorMax != -1) {
						while (formula > valorMax && consumoPetroleoBunker >= PROGRESION_BUNKER_KCAL) {
							calentBunker += PROGRESION_BUNKER_KCAL;
							consumoPetroleoBunker -= PROGRESION_BUNKER_KCAL;

							formula = kcalHVClinker(consumoCarbonSeco, poderCalorificoCarbonPonderado, produccionReal);
						}

						while (formula > valorMax && consumoCarbonSeco >= PROGRESION_CARBON_KCAL) {
							carbon += PROGRESION_CARBON_KCAL;
							consumoCarbonSeco -= PROGRESION_CARBON_KCAL;

							formula = kcalHVClinker(consumoCarbonSeco, poderCalorificoCarbonPonderado, produccionReal);
						}
					}
				}
			} else if (nombrePuestoTrabajo.startsWith("HCAL") && nombreProducto.startsWith("CAL- GRANULADA")) {
				formula = kcalHcalCal(consumoPetroleoBunker, consumoCarbonSeco, poderCalorificoCarbonPonderado, produccionReal);

				double carbon = 0d;
				double calentBunker = 0d;

				if (valorMax != -1) {
					while (formula > valorMax && consumoPetroleoBunker >= PROGRESION_BUNKER_KCAL) {
						calentBunker += PROGRESION_BUNKER_KCAL;
						consumoPetroleoBunker -= PROGRESION_BUNKER_KCAL;

						formula = kcalHcalCal(consumoPetroleoBunker, consumoCarbonSeco, poderCalorificoCarbonPonderado,
								produccionReal);
					}

					while (formula > valorMax && consumoCarbonSeco >= PROGRESION_CARBON_KCAL) {
						carbon += PROGRESION_CARBON_KCAL;
						consumoCarbonSeco -= PROGRESION_CARBON_KCAL;

						formula = kcalHcalCal(consumoPetroleoBunker, consumoCarbonSeco, poderCalorificoCarbonPonderado,
								produccionReal);
					}
				}
			}
		}

		return formula;
	}// evaluate

	private Double kcalHHClinker(double consumoCarbonSeco, double poderCalorificoCarbonPonderado, double consumoPetroleoBunker,
			double produccionTotalRealClinker) {
		Double divisor = produccionTotalRealClinker;
		Double formula = 0d;

		if (divisor != null && divisor > 0) {
			formula = ((consumoCarbonSeco * poderCalorificoCarbonPonderado) + (((consumoPetroleoBunker * FACTOR_GALONES) / 1000.0)
					* DENSIDAD_PETROLEO * PODER_CALORIFICO_PETROLEO))
					/ divisor;
		}
		return formula;
	}

	@SuppressWarnings("unused")
	private Double kcalHVClinker(double consumoCarbonSeco, double poderCalorificoCarbonPonderado,
			double produccionTotalRealClinker) {
		Double divisor = produccionTotalRealClinker;
		Double formula = 0d;
		if (divisor != null && divisor > 0) {
			formula = ((consumoCarbonSeco * poderCalorificoCarbonPonderado) + (PORC_HUMEDAD_CRUDO_NEGRO * 100.0)) / divisor;
		}

		return formula;
	}

	@SuppressWarnings("unused")
	private Double kcalHorno1Cal(double consumoCarbonSeco, double poderCalorificoCarbonPonderado, double consumoPetroleo,
			double produccionTotalCal) {
		Double divisor = produccionTotalCal;
		Double formula = 0d;
		if (divisor != null && divisor > 0) {
			formula = ((consumoCarbonSeco * poderCalorificoCarbonPonderado) + (DENSIDAD_PETROLEO_CAL
					* ((consumoPetroleo * FACTOR_GALONES) / 1000.0) * PODER_CALORIFICO_PETROLEO_CAL))
					/ divisor;
		}
		return formula;
	}

	@SuppressWarnings("unused")
	private Double kcalHcalCal(double consumoPetroleo, double consumoCarbonSeco, double poderCalorificoCarbonPonderado,
			double produccionCalTM) {
		Double divisor = ((produccionCalTM + CAL_RECHAZADA));
		Double formula = 0d;
		if (divisor != null && divisor > 0) {
			formula = ((((consumoPetroleo * FACTOR_GALONES) / 1000.0) * DENSIDAD_PETROLEO_CAL * PODER_CALORIFICO_PETROLEO_CAL) + (consumoCarbonSeco * poderCalorificoCarbonPonderado))
					/ divisor;
		}
		return formula;
	}
}
