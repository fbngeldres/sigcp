package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanCapacidadRegistroMensualBeanImpl.java
 * Modificado: Abr 07, 2010 02:35:52 PM 
 * Autor: Ana Rosa
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.PlanCapacidadBean;
import pe.com.pacasmayo.sgcp.bean.PlanCapacidadRegistroMensualBean;

public class PlanCapacidadRegistroMensualBeanImpl implements PlanCapacidadRegistroMensualBean {

	private Long codigo;
	private PlanCapacidadBean plancapacidad;
	private Short mesPlancapregmensual;
	private Integer annoPlancapregmensual;
	private Double cantidadPlancapregmensual;

	/**
	 * @return the plancapacidad
	 */
	public PlanCapacidadBean getPlancapacidad() {
		return plancapacidad;
	}

	/**
	 * @param plancapacidad the plancapacidad to set
	 */
	public void setPlancapacidad(PlanCapacidadBean plancapacidad) {
		this.plancapacidad = plancapacidad;
	}

	/**
	 * @return the mesPlancapregmensual
	 */
	public Short getMesPlancapregmensual() {
		return mesPlancapregmensual;
	}

	/**
	 * @param mesPlancapregmensual the mesPlancapregmensual to set
	 */
	public void setMesPlancapregmensual(Short mesPlancapregmensual) {
		this.mesPlancapregmensual = mesPlancapregmensual;
	}

	/**
	 * @return the annoPlancapregmensual
	 */
	public Integer getAnnoPlancapregmensual() {
		return annoPlancapregmensual;
	}

	/**
	 * @param annoPlancapregmensual the annoPlancapregmensual to set
	 */
	public void setAnnoPlancapregmensual(Integer annoPlancapregmensual) {
		this.annoPlancapregmensual = annoPlancapregmensual;
	}

	/**
	 * @return the cantidadPlancapregmensual
	 */
	public Double getCantidadPlancapregmensual() {
		return cantidadPlancapregmensual;
	}

	/**
	 * @param cantidadPlancapregmensual the cantidadPlancapregmensual to set
	 */
	public void setCantidadPlancapregmensual(Double cantidadPlancapregmensual) {
		this.cantidadPlancapregmensual = cantidadPlancapregmensual;
	}

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

}