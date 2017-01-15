package pe.com.pacasmayo.sgcp.bean;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Equivalenciasccvarcalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad;

public class ReporteEquivalenciasccvarcalidadBean {
	private Equivalenciasccvarcalidad datoCalidad;
	private String[] varCalDia = new String[31];
	private Productovariablecalidad variableProductoCalidad;

	/**
	 * @return the datoCalidad
	 */
	public Equivalenciasccvarcalidad getDatoCalidad() {
		return datoCalidad;
	}

	/**
	 * @param datoCalidad the datoCalidad to set
	 */
	public void setDatoCalidad(Equivalenciasccvarcalidad datoCalidad) {
		this.datoCalidad = datoCalidad;
	}

	/**
	 * @return the varCalDia
	 */
	public String[] getVarCalDia() {
		return varCalDia;
	}

	/**
	 * @param varCalDia the varCalDia to set
	 */
	public void setVarCalDia(String[] varCalDia) {
		this.varCalDia = varCalDia;
	}

	/**
	 * @return the variableProductoCalidad
	 */
	public Productovariablecalidad getVariableProductoCalidad() {
		return variableProductoCalidad;
	}

	/**
	 * @param variableProductoCalidad the variableProductoCalidad to set
	 */
	public void setVariableProductoCalidad(Productovariablecalidad variableProductoCalidad) {
		this.variableProductoCalidad = variableProductoCalidad;
	}

}
