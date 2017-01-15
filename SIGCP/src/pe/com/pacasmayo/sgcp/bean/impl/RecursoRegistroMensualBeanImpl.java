package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RecursoRegistroMensualBeanImpl.java
 * Modificado: Feb 25, 2010 8:04:13 PM 
 * Autor: Ana Rosa
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;
import pe.com.pacasmayo.sgcp.bean.RecursoBean;
import pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean;

public class RecursoRegistroMensualBeanImpl implements RecursoRegistroMensualBean {

	private Long codigo;
	private PlanAnualBean plananual;
	private RecursoBean recurso;
	private OperacionBean operacion;
	private Short mesRecursoregistromensual;
	private Integer annoRecursoregistromensual;
	private Double cantidadRecursoregistromensual;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#
	 * getAnnoRecursoregistromensual()
	 */
	public Integer getAnnoRecursoregistromensual() {
		return annoRecursoregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#
	 * getCantidadRecursoregistromensual()
	 */
	public Double getCantidadRecursoregistromensual() {
		return cantidadRecursoregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#
	 * getMesRecursoregistromensual()
	 */
	public Short getMesRecursoregistromensual() {
		return mesRecursoregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#getOperacionBean()
	 */
	public OperacionBean getOperacionBean() {
		return operacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#getPlanAnual()
	 */
	public PlanAnualBean getPlanAnual() {
		return plananual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#getRecurso()
	 */
	public RecursoBean getRecurso() {
		return recurso;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#
	 * setAnnoRecursoregistromensual(java.lang.Integer)
	 */
	public void setAnnoRecursoregistromensual(Integer annoRecursoregistromensual) {
		this.annoRecursoregistromensual = annoRecursoregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#
	 * setCantidadRecursoregistromensual(java.lang.Double)
	 */
	public void setCantidadRecursoregistromensual(Double cantidadRecursoregistromensual) {
		this.cantidadRecursoregistromensual = cantidadRecursoregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#
	 * setMesRecursoregistromensual(java.lang.Short)
	 */
	public void setMesRecursoregistromensual(Short mesrecursoregistromensual) {
		this.mesRecursoregistromensual = mesrecursoregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#setOperacionBean
	 * (pe.com.pacasmayo.sgcp.bean.OperacionBean)
	 */
	public void setOperacionBean(OperacionBean operacionbean) {
		this.operacion = operacionbean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#setPlanAnualBean
	 * (pe.com.pacasmayo.sgcp.bean.PlanAnualBean)
	 */
	public void setPlanAnualBean(PlanAnualBean plananualbean) {
		this.plananual = plananualbean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean#setRecursoBean(
	 * pe.com.pacasmayo.sgcp.bean.RecursoBean)
	 */
	public void setRecursoBean(RecursoBean recursobean) {
		this.recurso = recursobean;
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
