package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GrupoUsuarioBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioPrivilegioBean;

public class GrupoUsuarioBeanImpl extends EntidadBeanImpl implements GrupoUsuarioBean {

	private Long codigo;
	private String nombre;
	private String descripcion;
	private List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegios;

	public GrupoUsuarioBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setCodigo(java.lang.
	 * Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setNombre(java.lang.
	 * String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#getDescripcion()
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setDescripcion(java.
	 * lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setDescripcion(java.
	 * lang.String)
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#getGrupoUsuarioPrivilegios
	 * ()
	 */
	public List<GrupoUsuarioPrivilegioBean> getGrupoUsuarioPrivilegios() {
		return grupoUsuarioPrivilegios;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioBean#setGrupoUsuarioPrivilegios
	 * (java.util.Set)
	 */
	public void setGrupoUsuarioPrivilegios(List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegios) {
		this.grupoUsuarioPrivilegios = grupoUsuarioPrivilegios;
	}
}