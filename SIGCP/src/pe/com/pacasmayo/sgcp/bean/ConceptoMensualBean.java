package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConceptoBean.java
 * Modificado: Dec 30, 2009 10:07:42 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConceptoMensualBean extends EntidadBean {

	/**
	 * @return the produccion
	 */
	public abstract ProduccionBean getProduccion();

	/**
	 * @param produccion the produccion to set
	 */
	public abstract void setProduccion(ProduccionBean produccion);

	/**
	 * @return the unidadMedida
	 */
	public abstract ConceptoBean getConcepto();

	/**
	 * @param unidadMedida the unidad de medida to set
	 */
	public abstract void setConcepto(ConceptoBean concepto);

	/**
	 * @return the unidadMedida
	 */
	public abstract Integer getAnnoConceptoMensual();

	/**
	 * @param unidadMedida the unidad de medida to set
	 */
	public abstract void setAnnoConceptoMensual(Integer anno);
}