package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: LineaNegocioBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;

public class LineaNegocioBeanImpl extends EntidadBeanImpl implements LineaNegocioBean {

	private String codigoSAP;
	private Long codigoSCC;
	private UnidadBean unidad = new UnidadBeanImpl();
	

	public LineaNegocioBeanImpl() {

	}

	public UnidadBean getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadBean unidadBean) {
		this.unidad = unidadBean;
	}

	

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.LineaNegocioBean#getCodigoSCC()
	 */
	public Long getCodigoSCC() {
		return codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.LineaNegocioBean#setCodigoSCC(int)
	 */
	public void setCodigoSCC(Long codigoSCC) {
		this.codigoSCC = codigoSCC;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

}