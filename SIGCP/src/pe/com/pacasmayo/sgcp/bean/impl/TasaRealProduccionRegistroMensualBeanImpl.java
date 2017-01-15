package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TasaRealProduccionRegistroMensualBeanImpl.java
 * Modificado: Abr 07, 2010 05:55:12 PM 
 * Autor: Ana Rosa
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.TasaRealProduccionBean;
import pe.com.pacasmayo.sgcp.bean.TasaRealProduccionRegistroMensualBean;

public class TasaRealProduccionRegistroMensualBeanImpl implements TasaRealProduccionRegistroMensualBean {

	private Long codigo;
	private TasaRealProduccionBean tasarealproduccion;
	private Short mesTasarealprodregmensual;
	private Integer annoTasarealprodregmensual;
	private Double cantidadTasarealprodregmensual;

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the tasarealproduccion
	 */
	public TasaRealProduccionBean getTasarealproduccion() {
		return tasarealproduccion;
	}

	/**
	 * @param tasarealproduccion the tasarealproduccion to set
	 */
	public void setTasarealproduccion(TasaRealProduccionBean tasarealproduccion) {
		this.tasarealproduccion = tasarealproduccion;
	}

	/**
	 * @return the mesTasarealprodregmensual
	 */
	public Short getMesTasarealprodregmensual() {
		return mesTasarealprodregmensual;
	}

	/**
	 * @param mesTasarealprodregmensual the mesTasarealprodregmensual to set
	 */
	public void setMesTasarealprodregmensual(Short mesTasarealprodregmensual) {
		this.mesTasarealprodregmensual = mesTasarealprodregmensual;
	}

	/**
	 * @return the annoTasarealprodregmensual
	 */
	public Integer getAnnoTasarealprodregmensual() {
		return annoTasarealprodregmensual;
	}

	/**
	 * @param annoTasarealprodregmensual the annoTasarealprodregmensual to set
	 */
	public void setAnnoTasarealprodregmensual(Integer annoTasarealprodregmensual) {
		this.annoTasarealprodregmensual = annoTasarealprodregmensual;
	}

	/**
	 * @return the cantidadTasarealprodregmensual
	 */
	public Double getCantidadTasarealprodregmensual() {
		return cantidadTasarealprodregmensual;
	}

	/**
	 * @param cantidadTasarealprodregmensual the cantidadTasarealprodregmensual
	 *            to set
	 */
	public void setCantidadTasarealprodregmensual(Double cantidadTasarealprodregmensual) {
		this.cantidadTasarealprodregmensual = cantidadTasarealprodregmensual;
	}

}