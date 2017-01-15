package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoPlanBean.java
 * Modificado: Dec 22, 2009 1:11:47 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoPlanBean {

	/**
	 * @param codigo
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param nombre
	 */
	public abstract void setNombre(String nombre);

	/**
	 * @return nombre
	 */
	public abstract String getNombre();
}