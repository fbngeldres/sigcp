package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: EstadoHojaRutaBean.java
 * Modificado: Dec 22, 2009 1:12:37 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoHojaRutaBean extends EntidadBean {

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