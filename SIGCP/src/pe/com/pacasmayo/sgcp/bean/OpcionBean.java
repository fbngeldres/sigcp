package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OpcionBean.java
 * Modificado: Apr 29, 2010 7:15:19 AM 
 * Autor: Ignacio
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OpcionBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

}
