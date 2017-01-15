package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstimacionComercialBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.EstimacionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plancomercializacion;

public class EstimacionRegistroMensualBeanImpl implements EstimacionRegistroMensualBean {

	private Long codigo;
	private Plancomercializacion plancomercializacion;
	private Short mesEstimacionregistromensual;
	private Integer annoEstimacionregistromensual;
	private Double cantidadEstimacionregistromens;
	private String cantidadEstimacion;

	public EstimacionRegistroMensualBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstimacionComercialBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstimacionComercialBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the plancomercializacion
	 */
	public Plancomercializacion getPlancomercializacion() {
		return plancomercializacion;
	}

	/**
	 * @param plancomercializacion the plancomercializacion to set
	 */
	public void setPlancomercializacion(Plancomercializacion plancomercializacion) {
		this.plancomercializacion = plancomercializacion;
	}

	/**
	 * @return the mesEstimacionregistromensual
	 */
	public Short getMesEstimacionregistromensual() {
		return mesEstimacionregistromensual;
	}

	/**
	 * @param mesEstimacionregistromensual the mesEstimacionregistromensual to
	 *            set
	 */
	public void setMesEstimacionregistromensual(Short mesEstimacionregistromensual) {
		this.mesEstimacionregistromensual = mesEstimacionregistromensual;
	}

	/**
	 * @return the annoEstimacionregistromensual
	 */
	public Integer getAnnoEstimacionregistromensual() {
		return annoEstimacionregistromensual;
	}

	/**
	 * @param annoEstimacionregistromensual the annoEstimacionregistromensual to
	 *            set
	 */
	public void setAnnoEstimacionregistromensual(Integer annoEstimacionregistromensual) {
		this.annoEstimacionregistromensual = annoEstimacionregistromensual;
	}

	/**
	 * @return the cantidadEstimacionregistromens
	 */
	public Double getCantidadEstimacionregistromens() {
		return cantidadEstimacionregistromens;
	}

	/**
	 * @param cantidadEstimacionregistromens the cantidadEstimacionregistromens
	 *            to set
	 */
	public void setCantidadEstimacionregistromens(Double cantidadEstimacionregistromens) {
		this.cantidadEstimacionregistromens = cantidadEstimacionregistromens;
	}

	public String getCantidadEstimacion() {
		return cantidadEstimacion;
	}

	public void setCantidadEstimacion(String cantidadEstimacion) {
		this.cantidadEstimacion = cantidadEstimacion;
	}

}