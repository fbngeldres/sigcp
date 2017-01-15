package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadMedidaBean.java
 * Modificado: Dec 30, 2009 10:45:04 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface UnidadMedidaBean extends EntidadBean {

	/**
	 * @return the registro
	 */
	public abstract RegistroBean getRegistro();

	/**
	 * @param registro the registro to set
	 */
	public abstract void setRegistro(RegistroBean registro);

}