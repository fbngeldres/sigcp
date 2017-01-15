package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SociedadCargoBean.java
 * Modificado: Mar 24, 2010 12:54:30 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface SociedadCargoBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the sociedadBean
	 */
	public abstract SociedadBean getSociedadBean();

	/**
	 * @param sociedadBean the sociedadBean to set
	 */
	public abstract void setSociedadBean(SociedadBean sociedadBean);

}