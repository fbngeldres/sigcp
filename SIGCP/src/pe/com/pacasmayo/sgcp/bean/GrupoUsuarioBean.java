package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: GrupoUsuarioBean.java
 * Modificado: Apr 20, 2010 2:57:28 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

public interface GrupoUsuarioBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setCodigo(java.lang.
	 * Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#getNombre()
	 */
	public abstract String getNombre();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setNombre(java.lang.
	 * String)
	 */
	public abstract void setNombre(String nombre);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#getDescripcion()
	 */
	public abstract String getDescripcion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setDescripcion(java.
	 * lang.String)
	 */
	public abstract void setDescripcion(String descripcion);

	/**
	 * @return the grupoUsuarioPrivilegios
	 */
	public abstract List<GrupoUsuarioPrivilegioBean> getGrupoUsuarioPrivilegios();

	/**
	 * @param grupoUsuarioPrivilegios the grupoUsuarioPrivilegios to set
	 */
	public abstract void setGrupoUsuarioPrivilegios(List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegios);

}
