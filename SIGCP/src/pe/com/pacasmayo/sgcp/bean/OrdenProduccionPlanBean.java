package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccionPlanBean.java
 * Modificado: Feb 1, 2010 4:45:27 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OrdenProduccionPlanBean {

	public abstract OrdenProduccionBean getOrdenProduccionBean();

	public abstract void setOrdenProduccionBean(OrdenProduccionBean ordenProduccion);

	public abstract PlanAnualBean getPlanAnualBean();

	public abstract void setPlanAnualBean(PlanAnualBean planAnual);

	public abstract void setCodigoOrdenPlanProduccion(Long codigoOrdenPlanProduccion);

	public abstract Long getCodigoOrdenPlanProduccion();
}
