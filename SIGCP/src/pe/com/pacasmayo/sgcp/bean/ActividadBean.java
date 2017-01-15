package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ActividadBean.java
 * Modificado: Dec 30, 2009 9:46:39 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ActividadBean extends EntidadBean {

	/**
	 * @return the metrosPerforacion
	 */
	public abstract Double getMetrosPerforacion();

	/**
	 * @param metrosPerforacion the metrosPerforacion to set
	 */
	public abstract void setMetrosPerforacion(Double metrosPerforacion);

	/**
	 * @return the codigoSCC
	 */
	public abstract Long getCodigoSCC();

	/**
	 * @param codigoSCC the codigoSCC to set
	 */
	public abstract void setCodigoSCC(Long codigoSCC);

	/**
	 * @return the proceso
	 */
	public abstract ProcesoBean getProceso();

	/**
	 * @param proceso the proceso to set
	 */
	public abstract void setProceso(ProcesoBean proceso);

	/**
	 * @return the estadoActividad
	 */
	public abstract EstadoActividadBean getEstadoActividad();

	/**
	 * @param estadoActividad the estadoActividad to set
	 */
	public abstract void setEstadoActividad(EstadoActividadBean estadoActividad);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

}