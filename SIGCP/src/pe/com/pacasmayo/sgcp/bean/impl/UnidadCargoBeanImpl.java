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

import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UnidadCargoBean;

public class UnidadCargoBeanImpl implements UnidadCargoBean {
	private Long Codigo;
	private UnidadBean unidadBean;

	public UnidadCargoBeanImpl() {
		unidadBean = new UnidadBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UnidadCargoBean#getCodigo()
	 */
	public Long getCodigo() {
		return Codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UnidadCargoBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		Codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UnidadCargoBean#getUnidadBean()
	 */
	public UnidadBean getUnidadBean() {
		return unidadBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UnidadCargoBean#setUnidadBean(pe.com.
	 * pacasmayo.sgcp.bean.UnidadBean)
	 */
	public void setUnidadBean(UnidadBean unidadBean) {
		this.unidadBean = unidadBean;
	}
}