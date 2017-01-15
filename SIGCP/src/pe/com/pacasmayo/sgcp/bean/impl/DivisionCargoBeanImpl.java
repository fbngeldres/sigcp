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

import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.DivisionCargoBean;

public class DivisionCargoBeanImpl extends EntidadBeanImpl implements DivisionCargoBean {
	private Long Codigo;
	private DivisionBean divisionBean;

	public DivisionCargoBeanImpl() {
		divisionBean = new DivisionBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DivisionCargoBean#getCodigo()
	 */
	public Long getCodigo() {
		return Codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DivisionCargoBean#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		Codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DivisionCargoBean#getDivisionBean()
	 */
	public DivisionBean getDivisionBean() {
		return divisionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DivisionCargoBean#setDivisionBean(pe.
	 * com.pacasmayo.sgcp.bean.DivisionBean)
	 */
	public void setDivisionBean(DivisionBean divisionBean) {
		this.divisionBean = divisionBean;
	}
}