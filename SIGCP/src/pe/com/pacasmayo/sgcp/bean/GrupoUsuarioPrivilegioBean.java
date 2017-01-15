package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GrupoUsuarioPrivilegioBean.java
 * Modificado: Mar 25, 2010 2:00:27 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface GrupoUsuarioPrivilegioBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the privilegioBean
	 */
	public abstract PrivilegioBean getPrivilegioBean();

	/**
	 * @param privilegioBean the privilegioBean to set
	 */
	public abstract void setPrivilegioBean(PrivilegioBean privilegioBean);

	/**
	 * @return the grupoUsuarioBean
	 */
	public abstract GrupoUsuarioBean getGrupoUsuarioBean();

	/**
	 * @param grupoUsuarioBean the grupoUsuarioBean to set
	 */
	public abstract void setGrupoUsuarioBean(GrupoUsuarioBean grupoUsuarioBean);

}