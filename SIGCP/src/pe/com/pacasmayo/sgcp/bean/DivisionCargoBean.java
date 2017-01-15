package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DivisionCargoBean.java
 * Modificado: Mar 24, 2010 12:36:04 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface DivisionCargoBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the divisionBean
	 */
	public abstract DivisionBean getDivisionBean();

	/**
	 * @param divisionBean the divisionBean to set
	 */
	public abstract void setDivisionBean(DivisionBean divisionBean);

}