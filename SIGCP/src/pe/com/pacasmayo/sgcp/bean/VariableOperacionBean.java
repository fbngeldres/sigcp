package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: VariableOperacionBean.java
 * Modificado: Dec 21, 2009 10:24:13 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface VariableOperacionBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the registroMesAnual
	 */
	public abstract RegistroMesAnualBean getRegistroMesAnual();

	/**
	 * @param registroMesAnual the registroMesAnual to set
	 */
	public abstract void setRegistroMesAnual(RegistroMesAnualBean registroMesAnual);

}