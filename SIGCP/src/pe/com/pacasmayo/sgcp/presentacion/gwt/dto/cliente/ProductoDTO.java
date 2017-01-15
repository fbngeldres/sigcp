package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProcesoDTO.java
 * Modificado: Mar 9, 2010 6:52:29 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ProductoDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pkCodigoProducto;
	private String nombreProducto;
	private String descripcionProducto;
	private Long fkCodigoTipoProducto;
	private String grupoProducto;

	public Long getPkCodigoProducto() {
		return pkCodigoProducto;
	}

	public void setPkCodigoProducto(Long pkCodigoProducto) {
		this.pkCodigoProducto = pkCodigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public Long getFkCodigoTipoProducto() {
		return fkCodigoTipoProducto;
	}

	public void setFkCodigoTipoProducto(Long fkCodigoTipoProducto) {
		this.fkCodigoTipoProducto = fkCodigoTipoProducto;
	}

	public void setGrupoProducto(String grupoProducto) {
		this.grupoProducto = grupoProducto;
	}

	public String getGrupoProducto() {
		return grupoProducto;
	}
}
