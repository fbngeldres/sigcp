package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.Map;

public class RegistroKardexProductoComponentesDTO extends RegistroKardexParteDiarioDTO {

	private static final long serialVersionUID = -8390636777228853949L;

	protected Map<String, Double> consumoComponentes = null;
	protected Map<String, Double> variablesCalidad = null;
	protected Map<String, Double> erroresBalanza = null;
	protected Double fisico = null;

	protected Double variacion = null;

	// HUMEDAD
	protected Map<String, Double> consumoComponentesSemiSeco = null;
	protected Map<String, Double> factorConsumoComponentesSemiSeco = null;

	protected Map<String, Double> consumoComponentesHumedo = null;

	public Map<String, Double> getConsumoComponentes() {
		return consumoComponentes;
	}

	public void setConsumoComponentes(Map<String, Double> consumoComponentes) {
		this.consumoComponentes = consumoComponentes;
	}

	/**
	 * @return the variablesVariacion
	 */
	public Map<String, Double> getVariablesCalidad() {
		return variablesCalidad;
	}

	/**
	 * @param variablesVariacion the variablesVariacion to set
	 */
	public void setVariablesCalidad(Map<String, Double> variablesCalidad) {
		this.variablesCalidad = variablesCalidad;
	}

	public Map<String, Double> getErroresBalanza() {
		return erroresBalanza;
	}

	public void setErroresBalanza(Map<String, Double> erroresBalanza) {
		this.erroresBalanza = erroresBalanza;
	}

	public Double getLibros() {
		return saldoInicial + ingreso + ingresoHV - consumo;
	}

	public Double getFisico() {
		return fisico;
	}

	public void setFisico(Double fisico) {
		this.fisico = fisico;
	}

	public Double getVariacion() {
		return variacion;
	}

	public void setVariacion(Double variacion) {
		this.variacion = variacion;
	}

	public Map<String, Double> getConsumoComponentesSemiSeco() {
		return consumoComponentesSemiSeco;
	}

	public void setConsumoComponentesSemiSeco(Map<String, Double> consumoComponentesSemiSeco) {
		this.consumoComponentesSemiSeco = consumoComponentesSemiSeco;
	}

	public Map<String, Double> getFactorConsumoComponentesSemiSeco() {
		return factorConsumoComponentesSemiSeco;
	}

	public void setFactorConsumoComponentesSemiSeco(Map<String, Double> factorConsumoComponentesSemiSeco) {
		this.factorConsumoComponentesSemiSeco = factorConsumoComponentesSemiSeco;
	}

	public Map<String, Double> getConsumoComponentesHumedo() {
		return consumoComponentesHumedo;
	}

	public void setConsumoComponentesHumedo(Map<String, Double> consumoComponentesHumedo) {
		this.consumoComponentesHumedo = consumoComponentesHumedo;
	}

}
