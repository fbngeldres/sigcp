package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HoraBean.java
 * Modificado: Dec 30, 2009 10:28:45 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface HoraBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigoTurno();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigoTurno(Long codigoTurno);

	/**
	 * @return the hora
	 */
	public abstract short getHora();

	/**
	 * @param hora the hora to set
	 */
	public abstract void setHora(short hora);


}