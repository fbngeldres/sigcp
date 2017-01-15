package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UtilBeanImpl.java
 * Modificado: Nov 23, 2009 9:44:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.UtilBean;

public class UtilBeanImpl implements UtilBean {

	private Integer codigo;
	private String valor;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#getCodigo()
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#setCodigo(Integer)
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#getValor()
	 */
	public String getValor() {
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UtilBean#setValor(java.lang.String)
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

}