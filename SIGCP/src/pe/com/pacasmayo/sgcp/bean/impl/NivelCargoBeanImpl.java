package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NivelCargoBeanImpl.java
 * Modificado: Mar 23, 2010 4:30:57 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.NivelCargoBean;

public class NivelCargoBeanImpl extends EntidadBeanImpl implements NivelCargoBean {

	private Long codigo;
	private String nombre;
	private String descripcion;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NivelCargoBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NivelCargoBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NivelCargoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NivelCargoBean#setNombre(java.lang.String
	 * )
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NivelCargoBean#getDescripcion()
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NivelCargoBean#setDescripcion(java.lang
	 * .String)
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}