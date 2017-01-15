package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: UtilBean.java
 * Modificado: Dec 29, 2009 2:21:05 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface UtilBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#getCodigo()
	 */
	public abstract Integer getCodigo();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#setCodigo(int)
	 */
	public abstract void setCodigo(Integer codigo);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#getValor()
	 */
	public abstract String getValor();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#setValor(java.lang.String)
	 */
	public abstract void setValor(String valor);

}