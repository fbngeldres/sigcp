package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OpcionAccionBean.java
 * Modificado: Apr 29, 2010 7:06:08 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.AccionBean;
import pe.com.pacasmayo.sgcp.bean.OpcionAccionBean;
import pe.com.pacasmayo.sgcp.bean.OpcionBean;

public class OpcionAccionBeanImpl implements OpcionAccionBean {
	private Long codigo;
	private OpcionBean opcionBean;
	private AccionBean accionBean;

	public OpcionAccionBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OpcionAccionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OpcionAccionBean#setCodigo(java.lang.
	 * Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OpcionAccionBean#getOpcionBean()
	 */
	public OpcionBean getOpcionBean() {
		return opcionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OpcionAccionBean#setOpcionBean(pe.com
	 * .pacasmayo.sgcp.bean.OpcionBean)
	 */
	public void setOpcionBean(OpcionBean opcionBean) {
		this.opcionBean = opcionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OpcionAccionBean#getAccionBean()
	 */
	public AccionBean getAccionBean() {
		return accionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OpcionAccionBean#setAccionBean(pe.com
	 * .pacasmayo.sgcp.bean.AccionBean)
	 */
	public void setAccionBean(AccionBean accionBean) {
		this.accionBean = accionBean;
	}
}
