package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.TipoCategoriaProductoBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoCategoriaProductoBeanImpl.java
 * Modificado: Jan 20, 2011 4:53:46 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class TipoCategoriaProductoBeanImpl implements TipoCategoriaProductoBean {

	private Long codigo;
	private String nombre;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCategoriaProductoBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCategoriaProductoBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCategoriaProductoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCategoriaProductoBean#setNombre(java
	 * .lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
