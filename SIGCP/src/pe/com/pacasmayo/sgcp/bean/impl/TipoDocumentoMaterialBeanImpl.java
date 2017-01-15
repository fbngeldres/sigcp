package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.TipoDocumentoMaterialBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TipoDocumentoMateriaBeanImpl.java
 * Modificado: May 25, 2010 11:08:08 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class TipoDocumentoMaterialBeanImpl implements TipoDocumentoMaterialBean {

	private Long codigo;
	private String nombre;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#setCodigo(java
	 * .lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#getNombre()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#setNombre(java
	 * .lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoDocumentoMaterialBean#setNombre(java
	 * .lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
