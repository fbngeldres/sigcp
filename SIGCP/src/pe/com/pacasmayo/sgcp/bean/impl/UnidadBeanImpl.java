package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadBeanImpl.java
 * Modificado: Dic 22, 2009 2:44:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;

public class UnidadBeanImpl extends EntidadBeanImpl implements UnidadBean {

	private String codigoSAP;
	private Long codigoSCC;
	private SociedadBean sociedad = new SociedadBeanImpl();

	public UnidadBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UnidadBean#getCodigoSCC()
	 */
	public Long getCodigoSCC() {
		return codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UnidadBean#setCodigoSCC(java.lang.Integer
	 * )
	 */
	public void setCodigoSCC(Long codigoSCC) {
		this.codigoSCC = codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UnidadBean#getSociedad()
	 */
	public SociedadBean getSociedad() {
		return sociedad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UnidadBean#setSociedad(pe.com.pacasmayo
	 * .sgcp.bean.SociedadBean)
	 */
	public void setSociedad(SociedadBean sociedad) {
		this.sociedad = sociedad;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

}