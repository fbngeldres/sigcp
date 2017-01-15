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
import pe.com.pacasmayo.sgcp.bean.ConceptoRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class ConceptoBeanImpl extends EntidadBeanImpl implements ConceptoBean {

	private UnidadMedidaBean unidadMedida;
	private ConceptoRegistroMensualBean[] listaConceptoRegistroMensual = new ConceptoRegistroMensualBeanImpl[12];

	public ConceptoBeanImpl() {

	}

	/**
	 * @return the listaConceptoRegistroMensual
	 */
	public ConceptoRegistroMensualBean[] getListaConceptoRegistroMensual() {
		return listaConceptoRegistroMensual;
	}

	/**
	 * @param listaConceptoRegistroMensual the listaConceptoRegistroMensual to
	 *            set
	 */
	public void setListaConceptoRegistroMensual(ConceptoRegistroMensualBean[] listaConceptoRegistroMensual) {
		this.listaConceptoRegistroMensual = listaConceptoRegistroMensual;
	}

	/**
	 * @return the unidadMedida
	 */
	public UnidadMedidaBean getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(UnidadMedidaBean unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

}