package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionComponenteBeanImpl.java
 * Modificado: Apr 27, 2010 1:14:34 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.OperacionComponenteBean;

public class OperacionComponenteBeanImpl implements OperacionComponenteBean {

	private Long codigo;
	private OperacionBean operacion;
	private HojaRutaComponenteBean hojaRutaComponente;
	private Double maxFactor;
	private Double minFactor;

	public OperacionComponenteBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionComponenteBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionComponenteBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionComponenteBean#getOperacion()
	 */
	public OperacionBean getOperacion() {
		return operacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionComponenteBean#setOperacion(
	 * pe.com.pacasmayo.sgcp.bean.OperacionBean)
	 */
	public void setOperacion(OperacionBean operacion) {
		this.operacion = operacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionComponenteBean#getHojaRutaComponente
	 * ()
	 */
	public HojaRutaComponenteBean getHojaRutaComponente() {
		return hojaRutaComponente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionComponenteBean#setHojaRutaComponente
	 * (pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean)
	 */
	public void setHojaRutaComponente(HojaRutaComponenteBean hojaRutaComponente) {
		this.hojaRutaComponente = hojaRutaComponente;
	}

	/**
	 * @return the maxFactor
	 */
	public Double getMaxFactor() {
		return maxFactor;
	}

	/**
	 * @param maxFactor the maxFactor to set
	 */
	public void setMaxFactor(Double maxFactor) {
		this.maxFactor = maxFactor;
	}

	/**
	 * @return the minFactor
	 */
	public Double getMinFactor() {
		return minFactor;
	}

	/**
	 * @param minFactor the minFactor to set
	 */
	public void setMinFactor(Double minFactor) {
		this.minFactor = minFactor;
	}

}
