package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.CapacidadBolsaProductoBean;

/*
 * Backlog 
 * Archivo: CapacidadBolsaProductoBeanImpl.java
 * Modificado: Mar 18, 2014 5:49:06 PM
 * Autor: ginteldos
 *
 * Copyright (C) Gintelligence, 2012. All rights reserved.
 *
 * Developed by: Gintelligence. http://www.gintelligence.net
 */
public class CapacidadBolsaProductoBeanImpl implements CapacidadBolsaProductoBean {

	private Long codigo;
	private String codigoSapProducto;
	private Double capacidadNetaBolsa;
	private Double capacidadBrutaBolsa;

	public String getCodigoSapProducto() {
		return codigoSapProducto;
	}

	public void setCodigoSapProducto(String codigoSapProducto) {
		this.codigoSapProducto = codigoSapProducto;
	}

	public Double getCapacidadNetaBolsa() {
		return capacidadNetaBolsa;
	}

	public void setCapacidadNetaBolsa(Double capacidadNetaBolsa) {
		this.capacidadNetaBolsa = capacidadNetaBolsa;
	}

	public Double getCapacidadBrutaBolsa() {
		return capacidadBrutaBolsa;
	}

	public void setCapacidadBrutaBolsa(Double capacidadBrutaBolsa) {
		this.capacidadBrutaBolsa = capacidadBrutaBolsa;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return null;
	}

	public String getNombre() {
		return null;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;

	}

	public void setDescripcion(String descripcion) {
	}

	public void setNombre(String nombre) {
	}
}
