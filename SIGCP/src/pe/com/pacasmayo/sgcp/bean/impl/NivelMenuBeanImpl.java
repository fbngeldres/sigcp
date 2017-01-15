package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.NivelMenuBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NivelMenuBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class NivelMenuBeanImpl implements NivelMenuBean {
	private Long codigo;
	private Short numeroNivelMenu;
	private String nombre;

	public NivelMenuBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NivelMenuBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NivelMenuBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NivelMenuBean#getNumeroNivelMenu()
	 */
	public Short getNumeroNivelMenu() {
		return numeroNivelMenu;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NivelMenuBean#setNumeroNivelMenu(java
	 * .lang.Short)
	 */
	public void setNumeroNivelMenu(Short numeroNivelMenu) {
		this.numeroNivelMenu = numeroNivelMenu;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NivelMenuBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NivelMenuBean#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
