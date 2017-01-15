package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanComercializacionBean.java
 * Modificado: Dec 22, 2009 12:55:00 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlanComercializacionBean extends Comparable<PlanComercializacionBean> {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the producto
	 */
	public abstract ProduccionBean getProduccion();

	/**
	 * @param producto the producto to set
	 */
	public abstract void setProduccion(ProduccionBean produccion);

	/**
	 * @return the producto
	 */
	public abstract PlanAnualBean getPlanAnual();

	/**
	 * @param producto the producto to set
	 */
	public abstract void setPlanAnual(PlanAnualBean planAnual);

	public abstract EstimacionRegistroMensualBean[] getEstimacionRegistroMensualBeanList();

	public void setEstimacionRegistroMensualBeanList(EstimacionRegistroMensualBean[] estimacionRegistroMensualBeanList);

	public abstract int compareTo(PlanComercializacionBean plan);
}