package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ClasificacionTipoMovimientoBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ClasificacionTipoMovimientoBeanImpl.java
 * Modificado: May 3, 2010 7:47:51 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ClasificacionTipoMovimientoBeanImpl implements ClasificacionTipoMovimientoBean {

	private Long codigo;
	private String nombre;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ClasificacionTipoMovimientoBea#getCodigo
	 * ()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ClasificacionTipoMovimientoBea#setCodigo
	 * (java.lang.String)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ClasificacionTipoMovimientoBea#getNombre
	 * ()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ClasificacionTipoMovimientoBea#setNombre
	 * (java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
