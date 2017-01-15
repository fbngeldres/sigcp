package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroBean.java
 * Modificado: Dec 22, 2009 12:45:52 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RegistroBean {

	/**
	 * @return the cantidad
	 */
	public abstract double getCantidad();

	/**
	 * @param cantidad the cantidad to set
	 */
	public abstract void setCantidad(double cantidad);

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

}