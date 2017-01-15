package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoPuestoTrabajoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.DetallePuestoTrabajoComponenteBean;

public class DetalleParteTecnicoPuestoTrabajoComponenteBeanImpl implements DetalleParteTecnicoPuestoTrabajoComponenteBean {

	private String tipoProducto;
	private int ordenProceso;
	private String proceso;
	private String linea;
	private String componente;
	private List<DetallePuestoTrabajoComponenteBean> detallePuestoTrabajo;

	// Agregado Por Fabian
	private Long codigoComponente;

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public int getOrdenProceso() {
		return ordenProceso;
	}

	public void setOrdenProceso(int ordenProceso) {
		this.ordenProceso = ordenProceso;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public List<DetallePuestoTrabajoComponenteBean> getDetallePuestoTrabajo() {
		return detallePuestoTrabajo;
	}

	public void setDetallePuetoTrabajo(List<DetallePuestoTrabajoComponenteBean> detallePuestoTrabajo) {
		this.detallePuestoTrabajo = detallePuestoTrabajo;
	}

	/**
	 * @return the codigoComponente
	 */
	public Long getCodigoComponente() {
		return codigoComponente;
	}

	/**
	 * @param codigoComponente the codigoComponente to set
	 */
	public void setCodigoComponente(Long codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

}
