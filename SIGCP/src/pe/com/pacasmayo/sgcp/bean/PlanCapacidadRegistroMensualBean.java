package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanCapacidadRegistroMensualBean.java
 * Modificado: Abr 07, 2010 02:55:52 PM 
 * Autor: Ana Rosa
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlanCapacidadRegistroMensualBean {

	/**
	 * @return the codigo
	 */
	public Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo);

	/**
	 * @return the plancapacidad
	 */
	public abstract PlanCapacidadBean getPlancapacidad();

	/**
	 * @param plancapacidad the plancapacidad to set
	 */
	public abstract void setPlancapacidad(PlanCapacidadBean plancapacidad);

	/**
	 * @return the mesPlancapregmensual
	 */
	public abstract Short getMesPlancapregmensual();

	/**
	 * @param mesPlancapregmensual the mesPlancapregmensual to set
	 */
	public abstract void setMesPlancapregmensual(Short mesPlancapregmensual);

	/**
	 * @return the annoPlancapregmensual
	 */
	public abstract Integer getAnnoPlancapregmensual();

	/**
	 * @param annoPlancapregmensual the annoPlancapregmensual to set
	 */
	public abstract void setAnnoPlancapregmensual(Integer annoPlancapregmensual);

	/**
	 * @return the cantidadPlancapregmensual
	 */
	public abstract Double getCantidadPlancapregmensual();

	/**
	 * @param cantidadPlancapregmensual the cantidadPlancapregmensual to set
	 */
	public abstract void setCantidadPlancapregmensual(Double cantidadPlancapregmensual);

}