package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PuestoTrabajoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.ActividadBean;
import pe.com.pacasmayo.sgcp.bean.EstadoPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.PlanCapacidadBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.TipoPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class PuestoTrabajoBeanImpl extends EntidadBeanImpl implements
		PuestoTrabajoBean {

	private String codigoSAP;
	private Long codigoSCC;
	private String siglas;
	private EstadoPuestoTrabajoBean estadoPuestoTrabajo = new EstadoPuestoTrabajoBeanImpl();

	private Set<ActividadBean> actividades;
	private PlanCapacidadBean planCapacidad = new PlanCapacidadBeanImpl();
	private TipoPuestoTrabajoBean tipoPuestoTrabajo = new TipoPuestoTrabajoBeanImpl();

	private UnidadMedidaBean unidadMedida = new UnidadMedidaBeanImpl();

	public PuestoTrabajoBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#getCodigoSAP()
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#setCodigoSAP(java.lang
	 * .String)
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#getCodigoSCC()
	 */
	public Long getCodigoSCC() {
		return codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#setCodigoSCC(java.lang
	 * .Integer)
	 */
	public void setCodigoSCC(Long codigoSCC) {
		this.codigoSCC = codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#getSiglas()
	 */
	public String getSiglas() {
		return siglas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#setSiglas(java.lang
	 * .String)
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#getEstadoPuestoTrabajo
	 * ()
	 */
	public EstadoPuestoTrabajoBean getEstadoPuestoTrabajo() {
		return estadoPuestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#setEstadoPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.bean.EstadoPuestoTrabajoBean)
	 */
	public void setEstadoPuestoTrabajo(
			EstadoPuestoTrabajoBean estadoPuestoTrabajo) {
		this.estadoPuestoTrabajo = estadoPuestoTrabajo;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#getPlanCapacidad()
	 */
	public PlanCapacidadBean getPlanCapacidad() {
		return planCapacidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#setPlanCapacidad(pe
	 * .com.pacasmayo.sgcp.bean.PlanCapacidadBean)
	 */
	public void setPlanCapacidad(PlanCapacidadBean planCapacidad) {
		this.planCapacidad = planCapacidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#getTipoPuestoTrabajo()
	 */
	public TipoPuestoTrabajoBean getTipoPuestoTrabajo() {
		return tipoPuestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#setTipoPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.bean.TipoPuestoTrabajoBean)
	 */
	public void setTipoPuestoTrabajo(TipoPuestoTrabajoBean tipoPuestoTrabajo) {
		this.tipoPuestoTrabajo = tipoPuestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBean#getUnidadMedida()
	 */
	public UnidadMedidaBean getUnidadMedida() {
		return unidadMedida;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UnidadMedidaBean#setUnidadMedida(pe.com
	 * .pacasmayo.sgcp.bean.UnidadMedidaBean)
	 */
	public void setUnidadMedida(UnidadMedidaBean unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

}