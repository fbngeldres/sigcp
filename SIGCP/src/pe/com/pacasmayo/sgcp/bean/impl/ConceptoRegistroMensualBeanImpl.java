package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConceptoComercialBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ConceptoMensualBean;
import pe.com.pacasmayo.sgcp.bean.ConceptoRegistroMensualBean;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plancomercializacion;

public class ConceptoRegistroMensualBeanImpl implements ConceptoRegistroMensualBean {

	private Long codigo;
	private Plancomercializacion plancomercializacion;
	private Short mesConceptoregistromensual;
	private Double cantidadConceptoregistromens;
	private ConceptoMensualBean conceptoMensual;

	public ConceptoRegistroMensualBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ConceptoComercialBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ConceptoComercialBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the plancomercializacion
	 */
	public Plancomercializacion getPlancomercializacion() {
		return plancomercializacion;
	}

	/**
	 * @param plancomercializacion the plancomercializacion to set
	 */
	public void setPlancomercializacion(Plancomercializacion plancomercializacion) {
		this.plancomercializacion = plancomercializacion;
	}

	/**
	 * @return the mesConceptoregistromensual
	 */
	public Short getMesConceptoregistromensual() {
		return mesConceptoregistromensual;
	}

	/**
	 * @param mesConceptoregistromensual the mesConceptoregistromensual to set
	 */
	public void setMesConceptoregistromensual(Short mesConceptoregistromensual) {
		this.mesConceptoregistromensual = mesConceptoregistromensual;
	}

	/**
	 * @return the cantidadConceptoregistromens
	 */
	public Double getCantidadConceptoregistromens() {
		return cantidadConceptoregistromens;
	}

	/**
	 * @param cantidadConceptoregistromens the cantidadConceptoregistromens to
	 *            set
	 */
	public void setCantidadConceptoregistromens(Double cantidadConceptoregistromens) {
		this.cantidadConceptoregistromens = cantidadConceptoregistromens;
	}

	/**
	 * @return the conceptoMensual
	 */
	public ConceptoMensualBean getConceptoMensual() {
		return conceptoMensual;
	}

	/**
	 * @param conceptoMensual the conceptoMensual to set
	 */
	public void setConceptoMensual(ConceptoMensualBean conceptoMensual) {
		this.conceptoMensual = conceptoMensual;
	}

}