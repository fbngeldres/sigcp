package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.EstadoObjetoCostosBean;

public class EstadoObjetoCostosBeanImpl implements EstadoObjetoCostosBean {

	/*
	 * SGCP (Sistema de Gestión y Control de la Producción) Archivo:
	 * ObjetoCostoImpl.java Modificado: Jun 2, 2010 1:00:13 PM Autor: Ricardo
	 * Marquez Copyright (C) DBAccess, 2010. All rights reserved. Developed by:
	 * DBAccess. http://www.dbaccess.com
	 */

	private Long pkCodigo;
	private String nombre;

	public EstadoObjetoCostosBeanImpl() {

		this.pkCodigo = null;
		this.nombre = null;

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoObjectoCostoBean#getpkCodigo()
	 */
	public Long getpkCodigo() {
		return pkCodigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoObjectoCostoBean#setpkCodigo(java
	 * .lang.Long)
	 */
	public void setpkCodigo(Long pkCodigo) {
		this.pkCodigo = pkCodigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoObjectoCostoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoObjectoCostoBean#setNombre(java
	 * .lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
