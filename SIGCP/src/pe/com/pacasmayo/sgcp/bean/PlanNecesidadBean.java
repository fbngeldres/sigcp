package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanNecesidadBean.java
 * Modificado: Dec 22, 2009 12:54:12 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlanNecesidadBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the hojaRuta
	 */
	public abstract HojaRutaBean getHojaRuta();

	/**
	 * @param hojaRuta the hojaRuta to set
	 */
	public abstract void setHojaRuta(HojaRutaBean hojaRuta);

	/**
	 * @return the necesidadOperativa
	 */
	public abstract ProduccionBean getProduccion();

	/**
	 * @param necesidadOperativa the necesidadOperativa to set
	 */
	public abstract void setProduccion(ProduccionBean produccionBean);

	/**
	 * @return the lista de conceptos
	 */
	public ConceptoBean[] getListaConceptos();

	/**
	 * @param listaConceptos the listaConceptos to set
	 */
	public void setListaConceptos(ConceptoBean[] listaConceptos);

}