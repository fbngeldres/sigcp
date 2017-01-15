package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: VariableOperacionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.RegistroMesAnualBean;
import pe.com.pacasmayo.sgcp.bean.VariableOperacionBean;

public class VariableOperacionBeanImpl implements VariableOperacionBean {

	private Long codigo;
	private RegistroMesAnualBean registroMesAnual;

	public VariableOperacionBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.VariableOperacionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.VariableOperacionBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.VariableOperacionBean#getRegistroMesAnual
	 * ()
	 */
	public RegistroMesAnualBean getRegistroMesAnual() {
		return registroMesAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.VariableOperacionBean#setRegistroMesAnual
	 * (pe.com.pacasmayo.sgcp.bean.impl.RegistroMesAnualBeanImpl)
	 */
	public void setRegistroMesAnual(RegistroMesAnualBean registroMesAnual) {
		this.registroMesAnual = registroMesAnual;
	}

}