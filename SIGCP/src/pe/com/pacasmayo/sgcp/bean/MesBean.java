package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MesBean.java
 * Modificado: Dec 22, 2009 1:03:08 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface MesBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the mes
	 */
	public abstract String getMes();

	/**
	 * @param mes the mes to set
	 */
	public abstract void setMes(String mes);

}