package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoProductoBean.java
 * Modificado: Dec 22, 2009 1:10:32 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoProductoBean extends EntidadBean {

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