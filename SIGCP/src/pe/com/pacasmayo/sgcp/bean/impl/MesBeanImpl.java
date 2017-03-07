package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MesBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.MesBean;

public class MesBeanImpl implements MesBean {

	private Long codigo;
	private String mes;

	public MesBeanImpl() {

	}

	public MesBeanImpl(Long codigo, String mes) {

		this.codigo = codigo;
		this.mes = mes;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MesBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MesBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MesBean#getMes()
	 */
	public String getMes() {
		return mes;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MesBean#setMes(java.lang.String)
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

}