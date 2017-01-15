package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SociedadBean.java
 * Modificado: Dec 22, 2009 12:42:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface SociedadBean extends EntidadBean {

	/**
	 * @return the codigoSCC
	 */
	public abstract Long getCodigoSCC();

	/**
	 * @param codigoSCC the codigoSCC to set
	 */
	public abstract void setCodigoSCC(Long codigoSCC);

	/**
	 * @return the codigoSAP
	 */
	public abstract String getCodigoSAP();

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public abstract void setCodigoSAP(String codigoSAP);

	/**
	 * @return the siglas
	 */
	public abstract String getSiglasSociedad();

	/**
	 * @param siglas the siglas to set
	 */
	public abstract void setSiglasSociedad(String siglasSociedad);

	public abstract DivisionBean getDivision();

	public abstract void setDivision(DivisionBean divisionBean);

}