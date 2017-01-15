package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: CapacidadOperativaRegistroMensualBean.java
 * Modificado: Aug 7, 2010 12:31:24 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface CapacidadOperativaRegistroMensualBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the planAnual
	 */
	public abstract PlanAnualBean getPlanAnual();

	/**
	 * @param planAnual the planAnual to set
	 */
	public abstract void setPlanAnual(PlanAnualBean planAnual);

	/**
	 * @return the mesCapacidadOperativaregistromensual
	 */
	public abstract Short getMesCapacidadOperativaregistromensual();

	/**
	 * @param mesCapacidadOperativaregistromensual the
	 *            mesCapacidadOperativaregistromensual to set
	 */
	public abstract void setMesCapacidadOperativaregistromensual(Short mesCapacidadOperativaregistromensual);

	/**
	 * @return the annoCapacidadOperativaRegistroMensual
	 */
	public abstract Integer getAnnoCapacidadOperativaRegistroMensual();

	/**
	 * @param annoCapacidadOperativaRegistroMensual the
	 *            annoCapacidadOperativaRegistroMensual to set
	 */
	public abstract void setAnnoCapacidadOperativaRegistroMensual(Integer annoCapacidadOperativaRegistroMensual);

	/**
	 * @return the cantidadCapacidadOperativaRegistroMensual
	 */
	public abstract Double getCantidadCapacidadOperativaRegistroMensual();

	/**
	 * @param cantidadCapacidadOperativaRegistroMensual the
	 *            cantidadCapacidadOperativaRegistroMensual to set
	 */
	public abstract void setCantidadCapacidadOperativaRegistroMensual(Double cantidadCapacidadOperativaRegistroMensual);

	public abstract String getCantidadCapacidadOperativa();

	public abstract void setCantidadCapacidadOperativa(String cantidadCapacidadOperativa);
}
