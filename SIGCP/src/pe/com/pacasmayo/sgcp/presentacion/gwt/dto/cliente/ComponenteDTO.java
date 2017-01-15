package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ComponenteDTO.java
 * Modificado: Apr 12, 2011 4:22:26 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ComponenteDTO implements java.io.Serializable {

	private static final long serialVersionUID = 358378846990785879L;

	private Long pkCodigoComponente;
	private String nombreProducto;

	public Long getPkCodigoComponente() {
		return pkCodigoComponente;
	}

	public void setPkCodigoComponente(Long pkCodigoComponente) {
		this.pkCodigoComponente = pkCodigoComponente;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

}
