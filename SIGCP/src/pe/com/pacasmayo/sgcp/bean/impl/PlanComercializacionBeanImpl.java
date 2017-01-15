package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanComercializacionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.EstimacionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;
import pe.com.pacasmayo.sgcp.bean.PlanComercializacionBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;

public class PlanComercializacionBeanImpl implements PlanComercializacionBean {

	private Long codigo;
	private PlanAnualBean planAnual;
	private ProduccionBean produccion;
	private EstimacionRegistroMensualBean[] estimacionRegistroMensualBeanList = new EstimacionRegistroMensualBeanImpl[12];

	public PlanComercializacionBeanImpl() {

	}

	/**
	 * @return the estimacionRegistroMensualBeanList
	 */
	public EstimacionRegistroMensualBean[] getEstimacionRegistroMensualBeanList() {
		return estimacionRegistroMensualBeanList;
	}

	/**
	 * @param estimacionRegistroMensualBeanList the
	 *            estimacionRegistroMensualBeanList to set
	 */
	public void setEstimacionRegistroMensualBeanList(EstimacionRegistroMensualBean[] estimacionRegistroMensualBeanList) {
		this.estimacionRegistroMensualBeanList = estimacionRegistroMensualBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanComercializacionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanComercializacionBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the planAnual
	 */
	public PlanAnualBean getPlanAnual() {
		return planAnual;
	}

	/**
	 * @param planAnual the planAnual to set
	 */
	public void setPlanAnual(PlanAnualBean planAnual) {
		this.planAnual = planAnual;
	}

	/**
	 * @return the produccion
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/**
	 * @param produccion the produccion to set
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	public int compareTo(PlanComercializacionBean plan) {
		if (this.produccion != null && plan.getProduccion() != null) {
			if (this.produccion.getProducto() != null && plan.getProduccion().getProducto() != null) {
				if (this.produccion.getProducto().getNombre() != null && plan.getProduccion().getProducto().getNombre() != null) {
					String original = this.produccion.getProducto().getNombre().toUpperCase();
					String comparador = plan.getProduccion().getProducto().getNombre().toUpperCase();
					return original.compareTo(comparador);
				}
			}
		}
		return this.codigo.compareTo(plan.getCodigo());
	}

}