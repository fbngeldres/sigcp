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

public interface ConceptoRegistroMensualBean {

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
	public abstract Short getMesConceptoregistromensual();

	/**
	 * @param MesEstimacionregistromensual the mes to set
	 */
	public abstract void setMesConceptoregistromensual(Short mesConceptoregistromensual);

	/**
	 * @return the AnnoConceptoregistromensual
	 */
	public abstract Double getCantidadConceptoregistromens();

	/**
	 * @param AnnoConceptoregistromensual the anno to set
	 */
	public abstract void setCantidadConceptoregistromens(Double cantidadConceptoregistromens);

	/**
	 * @return the codigo
	 */
	public abstract ConceptoMensualBean getConceptoMensual();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setConceptoMensual(ConceptoMensualBean conceptoMensual);
}