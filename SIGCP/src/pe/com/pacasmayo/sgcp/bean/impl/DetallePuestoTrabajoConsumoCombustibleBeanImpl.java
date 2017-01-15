package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.DetallePuestoTrabajoConsumoCombustibleBean;

public class DetallePuestoTrabajoConsumoCombustibleBeanImpl implements DetallePuestoTrabajoConsumoCombustibleBean {

	public String nombreComponenteCombustible;
	public double consumoComponenteCombustible;
	public Long codigoConsumo;
	public String unidadMedida;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.
	 * DetalleParteTecnicoPuestoTrabajoConsumoCombustibleBe
	 * #getNombreComponenteCombustible()
	 */
	public String getNombreComponenteCombustible() {
		return nombreComponenteCombustible;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.
	 * DetalleParteTecnicoPuestoTrabajoConsumoCombustibleBe
	 * #setNombreComponenteCombustible(java.lang.String)
	 */
	public void setNombreComponenteCombustible(String nombreComponenteCombustible) {
		this.nombreComponenteCombustible = nombreComponenteCombustible;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.
	 * DetalleParteTecnicoPuestoTrabajoConsumoCombustibleBe
	 * #getConsumoComponenteCombustible()
	 */
	public double getConsumoComponenteCombustible() {
		return consumoComponenteCombustible;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.
	 * DetalleParteTecnicoPuestoTrabajoConsumoCombustibleBe
	 * #setConsumoComponenteCombustible(double)
	 */
	public void setConsumoComponenteCombustible(double consumoComponenteCombustible) {
		this.consumoComponenteCombustible = consumoComponenteCombustible;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.
	 * DetalleParteTecnicoPuestoTrabajoConsumoCombustibleBe#getCodigoConsumo()
	 */
	public Long getCodigoConsumo() {
		return codigoConsumo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.
	 * DetalleParteTecnicoPuestoTrabajoConsumoCombustibleBe
	 * #setCodigoConsumo(java.lang.Long)
	 */
	public void setCodigoConsumo(Long codigoConsumo) {
		this.codigoConsumo = codigoConsumo;
	}

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

}
