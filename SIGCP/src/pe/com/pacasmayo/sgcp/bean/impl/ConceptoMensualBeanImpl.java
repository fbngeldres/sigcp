package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConceptoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ConceptoBean;
import pe.com.pacasmayo.sgcp.bean.ConceptoMensualBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;

public class ConceptoMensualBeanImpl extends EntidadBeanImpl implements ConceptoMensualBean {

	private ConceptoBean concepto;
	private ProduccionBean produccion;
	private Integer annoConceptoMensual;

	public ConceptoMensualBeanImpl() {
	}

	/**
	 * @return the concepto
	 */
	public ConceptoBean getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(ConceptoBean concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the produccion
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/**
	 * @param produccion the produccion to set
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	/**
	 * @return the annoConceptoMensual
	 */
	public Integer getAnnoConceptoMensual() {
		return annoConceptoMensual;
	}

	/**
	 * @param annoConceptoMensual the annoConceptoMensual to set
	 */
	public void setAnnoConceptoMensual(Integer annoConceptoMensual) {
		this.annoConceptoMensual = annoConceptoMensual;
	}

}