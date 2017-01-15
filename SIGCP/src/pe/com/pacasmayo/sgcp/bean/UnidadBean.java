package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadBean.java
 * Modificado: Dec 30, 2009 10:43:32 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */


public interface UnidadBean extends EntidadBean {

	/**
	 * @return the codigoSCC
	 */
	public abstract Long getCodigoSCC();

	/**
	 * @param codigoSCC
	 *            the codigoSCC to set
	 */
	public abstract void setCodigoSCC(Long codigoSCC);

	/**
	 * @return the codigoSAP
	 */
	public abstract String getCodigoSAP();

	/**
	 * @param codigoSAP
	 *            the codigoSAP to set
	 */
	public abstract void setCodigoSAP(String codigoSAP);

	

	/**
	 * @return the sociedad
	 */
	public abstract SociedadBean getSociedad();

	/**
	 * @param sociedad
	 *            the sociedad to set
	 */
	public abstract void setSociedad(SociedadBean sociedad);

}