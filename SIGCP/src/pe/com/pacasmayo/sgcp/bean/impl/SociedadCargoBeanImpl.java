package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: CargoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.SociedadCargoBean;

public class SociedadCargoBeanImpl extends EntidadBeanImpl implements SociedadCargoBean {
	private Long Codigo;
	private SociedadBean sociedadBean;

	public SociedadCargoBeanImpl() {
		sociedadBean = new SociedadBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.SociedadCargoBean#getCodigo()
	 */
	public Long getCodigo() {
		return Codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.SociedadCargoBean#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		Codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.SociedadCargoBean#getSociedadBean()
	 */
	public SociedadBean getSociedadBean() {
		return sociedadBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.SociedadCargoBean#setSociedadBean(pe.
	 * com.pacasmayo.sgcp.bean.SociedadBean)
	 */
	public void setSociedadBean(SociedadBean sociedadBean) {
		this.sociedadBean = sociedadBean;
	}
}