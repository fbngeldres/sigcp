package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroMesAnualBean.java
 * Modificado: Dec 30, 2009 10:39:39 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RegistroMesAnualBean {

	/**
	 * @return the anno
	 */
	public abstract int getAnno();

	/**
	 * @param anno the anno to set
	 */
	public abstract void setAnno(int anno);

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the registro
	 */
	public abstract RegistroBean getRegistro();

	/**
	 * @param registro the registro to set
	 */
	public abstract void setRegistro(RegistroBean registro);

}