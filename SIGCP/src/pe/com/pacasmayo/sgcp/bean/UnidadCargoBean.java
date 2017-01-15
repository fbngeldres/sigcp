package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadCargoBean.java
 * Modificado: Mar 24, 2010 11:51:54 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface UnidadCargoBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the unidadBean
	 */
	public abstract UnidadBean getUnidadBean();

	/**
	 * @param unidadBean the unidadBean to set
	 */
	public abstract void setUnidadBean(UnidadBean unidadBean);

}