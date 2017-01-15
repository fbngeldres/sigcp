package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: EntidadBean.java
 * Modificado: Dec 29, 2009 2:18:20 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EntidadBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EntidadBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EntidadBean#setCodigo(int)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EntidadBean#getDescripcion()
	 */
	public abstract String getDescripcion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EntidadBean#setDescripcion(java.lang.
	 * String)
	 */
	public abstract void setDescripcion(String descripcion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EntidadBean#getNombre()
	 */
	public abstract String getNombre();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EntidadBean#setNombre(java.lang.String)
	 */
	public abstract void setNombre(String nombre);

}