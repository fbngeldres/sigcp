package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProcesoBeanImpl.java
 * Modificado: Nov 23, 2009 9:44:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.TipoProductoBean;

public class ProcesoBeanImpl implements ProcesoBean {

	private Long codigo;

	private LineaNegocioBean lineaNegocio = new LineaNegocioBeanImpl();
	private String nombre;
	private String descripcion;
	private Short ordenEjecucion;
	private String codigoSAP;
	private Long codigoSCC;
	private String siglas;
	private TipoProductoBean tipoProducto = new TipoProductoBeanImpl();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LineaNegocioBean getLineaNegocio() {
		return lineaNegocio;
	}

	public void setLineaNegocio(LineaNegocioBean lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Short getOrdenEjecucion() {
		return ordenEjecucion;
	}

	public void setOrdenEjecucion(Short ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	public Long getCodigoSCC() {
		return codigoSCC;
	}

	public void setCodigoSCC(Long codigoSCC) {
		this.codigoSCC = codigoSCC;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public TipoProductoBean getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoBean tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

}