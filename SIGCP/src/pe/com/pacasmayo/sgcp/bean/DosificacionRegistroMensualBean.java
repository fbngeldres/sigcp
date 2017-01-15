package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DosificacionRegistroMensualBean.java
 * Modificado: Feb 25, 2010 11:37:15 AM 
 * Autor: daniel.loreto
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

public interface DosificacionRegistroMensualBean {

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
	 * @return the dosificacion
	 */
	public abstract DosificacionBean getDosificacion();

	/**
	 * @param dosificacion the dosificacion to set
	 */
	public abstract void setDosificacion(DosificacionBean dosificacion);

	/**
	 * @return the mesDosificacionRegistroMensual
	 */
	public abstract Short getMesDosificacionRegistroMensual();

	/**
	 * @param mesDosificacionRegistroMensual the mesDosificacionRegistroMensual
	 *            to set
	 */
	public abstract void setMesDosificacionRegistroMensual(Short mesDosificacionRegistroMensual);

	/**
	 * @return the annoDosificacionRegistroMensua
	 */
	public abstract Integer getAnnoDosificacionRegistroMensua();

	/**
	 * @param annoDosificacionRegistroMensua the annoDosificacionRegistroMensua
	 *            to set
	 */
	public abstract void setAnnoDosificacionRegistroMensua(Integer annoDosificacionRegistroMensua);

	/**
	 * @return the cantidadDosificacionRegistroMe
	 */
	public abstract Double getCantidadDosificacionRegistroMe();

	/**
	 * @param cantidadDosificacionRegistroMe the cantidadDosificacionRegistroMe
	 *            to set
	 */
	public abstract void setCantidadDosificacionRegistroMe(Double cantidadDosificacionRegistroMe);

	/**
	 * @return the consumoComponentePlans
	 */
	public abstract Set<?> getConsumoComponentePlans();

	/**
	 * @param consumoComponentePlans the consumoComponentePlans to set
	 */
	public abstract void setConsumoComponentePlans(Set<?> consumoComponentePlans);

}