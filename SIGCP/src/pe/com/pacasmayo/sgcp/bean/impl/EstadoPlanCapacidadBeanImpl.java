package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.EstadoPlanCapacidadBean;

public class EstadoPlanCapacidadBeanImpl implements EstadoPlanCapacidadBean {

	private Long codigo;
	private String descripcion;
	private String nombre;

	public Long getCodigo() {
		// TODO Auto-generated method stub
		return codigo;
	}

	public String getDescripcion() {
		// TODO Auto-generated method stub
		return descripcion;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;

	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

}
