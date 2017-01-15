package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadMedidaBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.RegistroBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class UnidadMedidaBeanImpl extends EntidadBeanImpl implements UnidadMedidaBean {

	private RegistroBean registro;

	public UnidadMedidaBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UnidadMedidaBean#getRegistro()
	 */
	public RegistroBean getRegistro() {
		return registro;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UnidadMedidaBean#setRegistro(pe.com.pacasmayo
	 * .sgcp.bean.RegistroBean)
	 */
	public void setRegistro(RegistroBean registro) {
		this.registro = registro;
	}

}