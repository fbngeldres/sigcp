package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProduccionBean.java
 * Modificado: Dec 22, 2009 12:50:34 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ProduccionBean {

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
	public abstract ProductoBean getProducto();

	/**
	 * @param producto the producto to set
	 */
	public abstract void setProducto(ProductoBean productoBean);

	/**
	 * @return the proceso
	 */
	public abstract ProcesoBean getProceso();

	/**
	 * @param proceso the proceso to set
	 */
	public abstract void setProceso(ProcesoBean procesoBean);

}