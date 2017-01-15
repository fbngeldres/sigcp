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

public interface ConceptoBean extends EntidadBean {

	/**
	 * @return the unidadMedida
	 */
	public abstract UnidadMedidaBean getUnidadMedida();

	/**
	 * @param unidadMedida the unidad de medida to set
	 */
	public abstract void setUnidadMedida(UnidadMedidaBean unidadMedida);

	/**
	 * @return the unidadMedida
	 */
	public abstract ConceptoRegistroMensualBean[] getListaConceptoRegistroMensual();

	/**
	 * @param unidadMedida the unidad de medida to set
	 */
	public abstract void setListaConceptoRegistroMensual(ConceptoRegistroMensualBean[] listaConceptoRegistroMensual);

}