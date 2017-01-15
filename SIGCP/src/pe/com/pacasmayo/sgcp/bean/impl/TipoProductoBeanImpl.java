package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoProductoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.TipoProductoBean;

public class TipoProductoBeanImpl implements TipoProductoBean {

	private Long codigo;
	private String nombre;
	private String Siglas;

	public String getSiglas() {
		return Siglas;
	}

	public void setSiglas(String siglas) {
		Siglas = siglas;
	}

	public TipoProductoBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoProductoBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoProductoBean#setCodigo(Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoProductoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoProductoBean#setNombre(java.lang.
	 * String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}