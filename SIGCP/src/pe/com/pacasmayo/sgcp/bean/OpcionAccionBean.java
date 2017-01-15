package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OpcionAccionBean.java
 * Modificado: Apr 29, 2010 7:20:15 AM 
 * Autor: Ignacio
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OpcionAccionBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the opcionBean
	 */
	public abstract OpcionBean getOpcionBean();

	/**
	 * @param opcionBean the opcionBean to set
	 */
	public abstract void setOpcionBean(OpcionBean opcionBean);

	/**
	 * @return the accionBean
	 */
	public abstract AccionBean getAccionBean();

	/**
	 * @param accionBean the accionBean to set
	 */
	public abstract void setAccionBean(AccionBean accionBean);

}
