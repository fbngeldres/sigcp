package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccionPlanBeanImpl.java
 * Modificado: Feb 1, 2010 4:48:45 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionPlanBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;

public class OrdenProduccionPlanBeanImpl implements OrdenProduccionPlanBean {

	private OrdenProduccionBean ordenProduccion;
	private PlanAnualBean planAnual;
	private Long codigoOrdenPlanProduccion;

	public OrdenProduccionBean getOrdenProduccionBean() {
		return ordenProduccion;
	}

	public PlanAnualBean getPlanAnualBean() {
		return planAnual;
	}

	public Long getCodigoOrdenPlanProduccion() {
		return codigoOrdenPlanProduccion;
	}

	public void setOrdenProduccionBean(OrdenProduccionBean ordenProduccion) {
		this.ordenProduccion = ordenProduccion;

	}

	public void setPlanAnualBean(PlanAnualBean planAnual) {
		this.planAnual = planAnual;

	}

	public void setCodigoOrdenPlanProduccion(Long codigoOrdenPlanProduccion) {
		this.codigoOrdenPlanProduccion = codigoOrdenPlanProduccion;

	}

}
