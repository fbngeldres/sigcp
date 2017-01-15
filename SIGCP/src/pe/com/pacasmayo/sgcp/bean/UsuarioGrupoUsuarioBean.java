package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioGrupoUsuarioBean.java
 * Modificado: Mar 25, 2010 10:31:30 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface UsuarioGrupoUsuarioBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the usuarioBean
	 */
	public abstract UsuarioBean getUsuarioBean();

	/**
	 * @param usuarioBean the usuarioBean to set
	 */
	public abstract void setUsuarioBean(UsuarioBean usuarioBean);

	/**
	 * @return the grupoUsuarioBean
	 */
	public abstract GrupoUsuarioBean getGrupoUsuarioBean();

	/**
	 * @param grupoUsuarioBean the grupoUsuarioBean to set
	 */
	public abstract void setGrupoUsuarioBean(GrupoUsuarioBean grupoUsuarioBean);

}