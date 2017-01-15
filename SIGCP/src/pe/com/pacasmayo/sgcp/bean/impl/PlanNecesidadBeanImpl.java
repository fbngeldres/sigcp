package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanNecesidadBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ConceptoBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.PlanNecesidadBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;

public class PlanNecesidadBeanImpl implements PlanNecesidadBean {

	private Long codigo;
	private HojaRutaBean hojaRuta;
	private ProduccionBean produccion;
	private ConceptoBean[] listaConceptos = new ConceptoBeanImpl[4];

	public PlanNecesidadBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanNecesidadBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanNecesidadBean#setCodigo(Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanNecesidadBean#getHojaRuta()
	 */
	public HojaRutaBean getHojaRuta() {
		return hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanNecesidadBean#setHojaRuta(pe.com.
	 * pacasmayo.sgcp.bean.impl.HojaRutaBeanImpl)
	 */
	public void setHojaRuta(HojaRutaBean hojaRuta) {
		this.hojaRuta = hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanNecesidadBean#getProduccion()
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanNecesidadBean#setProduccion(pe.com
	 * .pacasmayo.sgcp.bean.impl.ProduccionBeanImpl)
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	/**
	 * @return the listaConceptos
	 */
	public ConceptoBean[] getListaConceptos() {
		return listaConceptos;
	}

	/**
	 * @param listaConceptos the listaConceptos to set
	 */
	public void setListaConceptos(ConceptoBean[] listaConceptos) {
		this.listaConceptos = listaConceptos;
	}

}