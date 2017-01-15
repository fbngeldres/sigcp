package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.RendimientoTermicoBean;

public class RendimientoTermicoBeanImpl extends EntidadBeanImpl implements RendimientoTermicoBean {

	private Long pkCodigoRendimientoTermico;
	private String nombrePuestoTrabajo;
	private String nombreProducto;
	private Long valorkCal1;
	private Long valorkCal2;
	private Long codigoPuestoTrabajo;
	private Long codigoProducto;

	public Long getPkCodigoRendimientoTermico() {
		return pkCodigoRendimientoTermico;
	}

	public void setPkCodigoRendimientoTermico(Long pkCodigoRendimientoTermico) {
		this.pkCodigoRendimientoTermico = pkCodigoRendimientoTermico;
	}

	public String getNombrePuestoTrabajo() {
		return nombrePuestoTrabajo;
	}

	public void setNombrePuestoTrabajo(String nombrePuestoTrabajo) {
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Long getValorkCal1() {
		return valorkCal1;
	}

	public void setValorkCal1(Long valorkCal1) {
		this.valorkCal1 = valorkCal1;
	}

	public Long getValorkCal2() {
		return valorkCal2;
	}

	public void setValorkCal2(Long valorkCal2) {
		this.valorkCal2 = valorkCal2;
	}

	public Long getCodigoPuestoTrabajo() {
		return codigoPuestoTrabajo;
	}

	public void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo) {
		this.codigoPuestoTrabajo = codigoPuestoTrabajo;
	}

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

}
