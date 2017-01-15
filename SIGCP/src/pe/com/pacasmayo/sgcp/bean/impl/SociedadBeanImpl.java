package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SociedadBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;

public class SociedadBeanImpl extends EntidadBeanImpl implements SociedadBean {

	private Long codigoSCC;
	private String codigoSAP;
	private String siglasSociedad;
	private DivisionBean division = new DivisionBeanImpl();

	public SociedadBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.SociedadBean#getCodigoSCC()
	 */
	public Long getCodigoSCC() {
		return codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.SociedadBean#setCodigoSCC(Long)
	 */
	public void setCodigoSCC(Long codigoSCC) {
		this.codigoSCC = codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.SociedadBean#getSiglas()
	 */
	public String getSiglasSociedad() {
		return siglasSociedad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.SociedadBean#setSiglas(java.lang.String)
	 */
	public void setSiglasSociedad(String siglasSociedad) {
		this.siglasSociedad = siglasSociedad;
	}

	public DivisionBean getDivision() {
		return division;
	}

	public void setDivision(DivisionBean divisionBean) {
		this.division = divisionBean;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

}