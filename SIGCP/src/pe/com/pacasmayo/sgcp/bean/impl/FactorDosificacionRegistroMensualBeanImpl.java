package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorDosificacionRegistroMensualBeanImpl.java
 * Modificado: Jan 11, 2010 10:22:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionRegistroMensualBean;

public class FactorDosificacionRegistroMensualBeanImpl extends EntidadBeanImpl implements FactorDosificacionRegistroMensualBean {

	private FactorDosificacionBean factorDosificacionBean;
	private Short mes;
	private Integer anno;
	private double cantidadRegistro;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #getFactorDosificacionBean()
	 */
	public FactorDosificacionBean getFactorDosificacionBean() {
		return factorDosificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #setFactorDosificacionBean
	 * (pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean)
	 */
	public void setFactorDosificacionBean(FactorDosificacionBean factorDosificacionBean) {
		this.factorDosificacionBean = factorDosificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #getMes()
	 */
	public Short getMes() {
		return mes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #setMes(java.lang.Integer)
	 */
	public void setMes(Short mes) {
		this.mes = mes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #getAnno()
	 */
	public Integer getAnno() {
		return anno;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #setAnno(java.lang.Integer)
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #getCantidadRegistro()
	 */
	public double getCantidadRegistro() {
		return cantidadRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionRegistroMensualBean
	 * #setCantidadRegistro(double)
	 */
	public void setCantidadRegistro(double cantidadRegistro) {
		this.cantidadRegistro = cantidadRegistro;
	}

}
