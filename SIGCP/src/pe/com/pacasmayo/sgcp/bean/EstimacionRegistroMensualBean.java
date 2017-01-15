package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstimacionComercialBean.java
 * Modificado: Dec 30, 2009 10:20:53 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstimacionRegistroMensualBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the MesEstimacionregistromensual
	 */
	public abstract Short getMesEstimacionregistromensual();

	/**
	 * @param MesEstimacionregistromensual the mes to set
	 */
	public abstract void setMesEstimacionregistromensual(Short mesEstimacionregistromensual);

	/**
	 * @return the AnnoEstimacionregistromensual
	 */
	public abstract Integer getAnnoEstimacionregistromensual();

	/**
	 * @param AnnoEstimacionregistromensual the anno to set
	 */
	public abstract void setAnnoEstimacionregistromensual(Integer annoEstimacionregistromensual);

	/**
	 * @return the AnnoEstimacionregistromensual
	 */
	public abstract Double getCantidadEstimacionregistromens();

	/**
	 * @param AnnoEstimacionregistromensual the anno to set
	 */
	public abstract void setCantidadEstimacionregistromens(Double cantidadEstimacionregistromens);

	public abstract String getCantidadEstimacion();

	public abstract void setCantidadEstimacion(String cantidadEstimacion);
}