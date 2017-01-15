package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: LineaNegocioBean.java
 * Modificado: Dec 29, 2009 5:19:33 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

public interface LineaNegocioBean extends EntidadBean {

	/**
	 * @return the unidad
	 */
	public abstract UnidadBean getUnidad();

	/**
	 * @return the unidad
	 */
	public abstract void setUnidad(UnidadBean unidadBean);

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
}