package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TasaRealProduccionRegistroMensualBean.java
 * Modificado: Abr 07, 2010 05:55:12 PM 
 * Autor: Ana Rosa
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TasaRealProduccionRegistroMensualBean {
	/**
	 * @return the codigo
	 */
	public Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo);

	/**
	 * @return the tasarealproduccion
	 */
	public TasaRealProduccionBean getTasarealproduccion();

	/**
	 * @param tasarealproduccion the tasarealproduccion to set
	 */
	public void setTasarealproduccion(TasaRealProduccionBean tasarealproduccion);

	/**
	 * @return the mesTasarealprodregmensual
	 */
	public Short getMesTasarealprodregmensual();

	/**
	 * @param mesTasarealprodregmensual the mesTasarealprodregmensual to set
	 */
	public void setMesTasarealprodregmensual(Short mesTasarealprodregmensual);

	/**
	 * @return the annoTasarealprodregmensual
	 */
	public Integer getAnnoTasarealprodregmensual();

	/**
	 * @param annoTasarealprodregmensual the annoTasarealprodregmensual to set
	 */
	public void setAnnoTasarealprodregmensual(Integer annoTasarealprodregmensual);

	/**
	 * @return the cantidadTasarealprodregmensual
	 */
	public Double getCantidadTasarealprodregmensual();

	/**
	 * @param cantidadTasarealprodregmensual the cantidadTasarealprodregmensual
	 *            to set
	 */
	public void setCantidadTasarealprodregmensual(Double cantidadTasarealprodregmensual);
}