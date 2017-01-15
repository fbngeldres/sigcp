package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TableroControlBean.java
 * Modificado: Jun 23, 2010 2:40:52 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TableroControlBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

	/**
	 * @return the descripcion
	 */
	public abstract String getDescripcion();

	/**
	 * @param descripcion the descripcion to set
	 */
	public abstract void setDescripcion(String descripcion);

	/**
	 * @return the unidad
	 */
	public abstract UnidadBean getUnidad();

	/**
	 * @param unidad the unidad to set
	 */
	public abstract void setUnidad(UnidadBean unidad);

}