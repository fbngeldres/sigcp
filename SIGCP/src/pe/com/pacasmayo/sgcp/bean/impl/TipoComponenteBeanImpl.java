package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.TipoComponenteBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoComponenteBeanImpl.java
 * Modificado: Apr 28, 2010 9:08:45 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class TipoComponenteBeanImpl implements TipoComponenteBean {

	private Long codigo;
	private String nombre;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoComponenteBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoComponenteBean#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoComponenteBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoComponenteBean#setNombre(java.lang
	 * .String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
