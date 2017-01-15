package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ActividadBeanImpl.java
 * Modificado: Nov 23, 2009 9:44:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ActividadBean;
import pe.com.pacasmayo.sgcp.bean.EstadoActividadBean;
import pe.com.pacasmayo.sgcp.bean.ProcesoBean;

public class ActividadBeanImpl extends EntidadBeanImpl implements ActividadBean {

	private Long codigoSCC;
	private String nombre;
	private ProcesoBean proceso;
	private EstadoActividadBean estadoActividad;
	private Double metrosPerforacion;

	public ActividadBeanImpl() {
		proceso = new ProcesoBeanImpl();
		estadoActividad = new EstadoActividadBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ActividadBean#getCodigoSCC()
	 */
	public Long getCodigoSCC() {
		return codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ActividadBean#setCodigoSCC(int)
	 */
	public void setCodigoSCC(Long codigoSCC) {
		this.codigoSCC = codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ActividadBean#getProceso()
	 */
	public ProcesoBean getProceso() {
		return proceso;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ActividadBean#setProceso(pe.com.pacasmayo
	 * .sgcp.bean.ProcesoBean)
	 */
	public void setProceso(ProcesoBean proceso) {
		this.proceso = proceso;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ActividadBean#getEstadoActividad()
	 */
	public EstadoActividadBean getEstadoActividad() {
		return estadoActividad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ActividadBean#setEstadoActividad(pe.com
	 * .pacasmayo.sgcp.bean.EstadoActividadBean)
	 */
	public void setEstadoActividad(EstadoActividadBean estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public Double getMetrosPerforacion() {
		return metrosPerforacion;
	}

	public void setMetrosPerforacion(Double metrosPerforacion) {
		this.metrosPerforacion = metrosPerforacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}