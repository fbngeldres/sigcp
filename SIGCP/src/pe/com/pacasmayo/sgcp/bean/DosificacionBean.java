package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DosificacionBean.java
 * Modificado: Dec 22, 2009 1:14:27 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface DosificacionBean extends EntidadBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the factorDosificacion
	 */
	public abstract FactorDosificacionBean getFactorDosificacion();

	/**
	 * @param factorDosificacion the factorDosificacion to set
	 */
	public abstract void setFactorDosificacion(FactorDosificacionBean factorDosificacion);

	/**
	 * @return the unidadMedida
	 */
	public abstract UnidadMedidaBean getUnidadMedida();

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public abstract void setUnidadMedida(UnidadMedidaBean unidadMedida);

	/**
	 * @return the dosificacionRegistroMensualBeanList
	 */
	public abstract DosificacionRegistroMensualBean[] getDosificacionRegistroMensualBeanList();

	/**
	 * @param dosificacionRegistroMensualBeanList the
	 *            dosificacionRegistroMensualBeanList to set
	 */
	public abstract void setDosificacionRegistroMensualBeanList(
			DosificacionRegistroMensualBean[] dosificacionRegistroMensualBeanList);

}