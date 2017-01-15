package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.DetallePuestoTrabajoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.DetallePuestoTrabajoConsumoCombustibleBean;

public class DetallePuestoTrabajoComponenteBeanImpl implements DetallePuestoTrabajoComponenteBean {

	private String puestoTrabajo;
	private double produccion;
	private double tiempoProduccion;
	private Long codigoPuestoTrabajo;
	private List<DetallePuestoTrabajoConsumoCombustibleBean> detalleConsumoCombustible;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * getCodigoPuestoTrabajo()
	 */
	public Long getCodigoPuestoTrabajo() {
		return codigoPuestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * setCodigoPuestoTrabajo(java.lang.Long)
	 */
	public void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo) {
		this.codigoPuestoTrabajo = codigoPuestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * getPuestoTrabajo()
	 */
	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * setPuestoTrabajo(java.lang.String)
	 */
	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * getProduccion()
	 */
	public double getProduccion() {
		return produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * setProduccion(double)
	 */
	public void setProduccion(double produccion) {
		this.produccion = produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * getTiempoProduccion()
	 */
	public double getTiempoProduccion() {
		return tiempoProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetallePuestoTrabajoComponenteBean#
	 * setTiempoProduccion(double)
	 */
	public void setTiempoProduccion(double tiempoProduccion) {
		this.tiempoProduccion = tiempoProduccion;
	}

	public List<DetallePuestoTrabajoConsumoCombustibleBean> getDetalleConsumoCombustible() {
		return detalleConsumoCombustible;
	}

	public void setDetalleConsumoCombustible(List<DetallePuestoTrabajoConsumoCombustibleBean> detalleConsumoCombustible) {
		this.detalleConsumoCombustible = detalleConsumoCombustible;
	}

}
