package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ComponenteRegistroOrdenBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteRegistroOrdenBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class ComponenteRegistroOrdenBeanImpl implements ComponenteRegistroOrdenBean {

	private ComponenteBean componente;
	private UnidadMedidaBean unidadmedida;
	private Double dosificacionPlanificada;
	private Double dosificacionEjecutada;
	private Double porcentaje;
	private Double produccionPlanificada;
	private Double produccionEjecutada;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#getComponente()
	 */
	public ComponenteBean getComponente() {
		return componente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#setComponente
	 * (pe.com.pacasmayo.sgcp.bean.ComponenteBean)
	 */
	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#getUnidadmedida
	 * ()
	 */
	public UnidadMedidaBean getUnidadmedida() {
		return unidadmedida;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#setUnidadmedida
	 * (pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean)
	 */
	public void setUnidadmedida(UnidadMedidaBean unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * getDosificacionPlanificada()
	 */
	public Double getDosificacionPlanificada() {
		return dosificacionPlanificada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * setDosificacionPlanificada(java.lang.Double)
	 */
	public void setDosificacionPlanificada(Double dosificacionPlanificada) {
		this.dosificacionPlanificada = dosificacionPlanificada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * getDosificacionEjecutada()
	 */
	public Double getDosificacionEjecutada() {
		return dosificacionEjecutada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * setDosificacionEjecutada(java.lang.Double)
	 */
	public void setDosificacionEjecutada(Double dosificacionEjecutada) {
		this.dosificacionEjecutada = dosificacionEjecutada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#getPorcentaje()
	 */
	public Double getPorcentaje() {
		return porcentaje;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#setPorcentaje
	 * (java.lang.Double)
	 */
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * getProduccionPlanificada()
	 */
	public Double getProduccionPlanificada() {
		return produccionPlanificada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * setProduccionPlanificada(java.lang.Double)
	 */
	public void setProduccionPlanificada(Double produccionPlanificada) {
		this.produccionPlanificada = produccionPlanificada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * getProduccionEjecutada()
	 */
	public Double getProduccionEjecutada() {
		return produccionEjecutada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteRegistroOrdenBe#
	 * setProduccionEjecutada(java.lang.Double)
	 */
	public void setProduccionEjecutada(Double produccionEjecutada) {
		this.produccionEjecutada = produccionEjecutada;
	}

}