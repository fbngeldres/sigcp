package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DivisionBean.java
 * Modificado: Dec 22, 2009 1:14:52 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface DivisionBean extends EntidadBean {
	/**
	 * @return the codigoSAP
	 */
	public abstract String getCodigoSAP();

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public abstract void setCodigoSAP(String codigoSAP);

	/**
	 * @return the sociedad
	 */
	public abstract SociedadBean getSociedad();

	/**
	 * @param sociedad the sociedad to set
	 */
	public abstract void setSociedad(SociedadBean sociedad);

}