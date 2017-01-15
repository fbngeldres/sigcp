package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoPrivilegioBeanImpl.java
 * Modificado: Mar 23, 2010 4:30:57 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.EstadoPrivilegioBean;

public class EstadoPrivilegioBeanImpl extends EntidadBeanImpl implements EstadoPrivilegioBean {

	private Long codigo;
	private String nombre;
	private String descripcion;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoPrivilegioBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoPrivilegioBean#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoPrivilegioBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoPrivilegioBean#setNombre(java.lang
	 * .String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoPrivilegioBean#getDescripcion()
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoPrivilegioBean#setDescripcion(java
	 * .lang.String)
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}