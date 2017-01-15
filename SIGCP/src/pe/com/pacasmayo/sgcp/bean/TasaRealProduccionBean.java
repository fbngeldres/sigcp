package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TasaRealProduccionBean.java
 * Modificado: Dec 22, 2009 12:41:12 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TasaRealProduccionBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the unidadmedida
	 */
	public UnidadMedidaBean getUnidadmedida();

	/**
	 * @param unidadmedida the unidadmedida to set
	 */
	public void setUnidadmedida(UnidadMedidaBean unidadmedida);

	/**
	 * @return the puestotrabajo
	 */
	public PuestoTrabajoBean getPuestotrabajo();

	/**
	 * @param puestotrabajo the puestotrabajo to set
	 */
	public void setPuestotrabajo(PuestoTrabajoBean puestotrabajo);

	/**
	 * @return the produccion
	 */
	public ProduccionBean getProduccion();

	/**
	 * @param produccion the produccion to set
	 */
	public void setProduccion(ProduccionBean produccion);

	public abstract double getMaximo();

	public abstract void setMaximo(double maximo);

	public abstract double getMinimo();

	public abstract void setMinimo(double minimo);

	public abstract TasaRealProduccionRegistroMensualBean[] getTasaRealProduccionRegistroMensual();

	public abstract void setTasaRealProduccionRegistroMensual(
			TasaRealProduccionRegistroMensualBean[] tasaRealProduccionRegistroMensual);

	public abstract String getProyeccion();

	public abstract void setProyeccion(String proyeccion);
}