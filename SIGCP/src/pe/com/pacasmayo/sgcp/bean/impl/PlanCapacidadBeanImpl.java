package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanCapacidadBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.EstadoPlanCapacidadBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.PlanCapacidadBean;
import pe.com.pacasmayo.sgcp.bean.PlanCapacidadRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.RegistroMesAnualBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class PlanCapacidadBeanImpl implements PlanCapacidadBean {

	private Long codigo;
	private PuestoTrabajoBean puestoTrabajo;
	private RegistroMesAnualBean registroMesAnual;
	private UnidadMedidaBean unidadMedida;
	private PlanCapacidadRegistroMensualBean[] plancapacidadregistromensual;
	private String versionPlancapacidad;
	private Integer annoPlancapacidad;
	private EstadoPlanCapacidadBean estadoPlanCapacidad;
	private LineaNegocioBean lineaNegocio;

	public PlanCapacidadBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanCapacidadBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanCapacidadBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanCapacidadBean#getPuestoTrabajo()
	 */
	public PuestoTrabajoBean getPuestoTrabajo() {
		return puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanCapacidadBean#setPuestoTrabajo(pe
	 * .com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanCapacidadBean#getRegistroMesAnual()
	 */
	public RegistroMesAnualBean getRegistroMesAnual() {
		return registroMesAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanCapacidadBean#setRegistroMesAnual
	 * (pe.com.pacasmayo.sgcp.bean.RegistroMesAnualBean)
	 */
	public void setRegistroMesAnual(RegistroMesAnualBean registroMesAnual) {
		this.registroMesAnual = registroMesAnual;
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

	/**
	 * @return the plancapacidadregistromensual
	 */
	public PlanCapacidadRegistroMensualBean[] getPlancapacidadregistromensual() {
		return plancapacidadregistromensual;
	}

	/**
	 * @param plancapacidadregistromensual the plancapacidadregistromensual to
	 *            set
	 */
	public void setPlancapacidadregistromensual(PlanCapacidadRegistroMensualBean[] plancapacidadregistromensual) {
		this.plancapacidadregistromensual = plancapacidadregistromensual;
	}

	/**
	 * @return the versionPlancapacidad
	 */
	public String getVersionPlancapacidad() {
		return versionPlancapacidad;
	}

	/**
	 * @param versionPlancapacidad the versionPlancapacidad to set
	 */
	public void setVersionPlancapacidad(String versionPlancapacidad) {
		this.versionPlancapacidad = versionPlancapacidad;
	}

	/**
	 * @return the annoPlancapacidad
	 */
	public Integer getAnnoPlancapacidad() {
		return annoPlancapacidad;
	}

	/**
	 * @param annoPlancapacidad the annoPlancapacidad to set
	 */
	public void setAnnoPlancapacidad(Integer annoPlancapacidad) {
		this.annoPlancapacidad = annoPlancapacidad;
	}

	/**
	 * @return the estadoPlanCapacidad
	 */
	public EstadoPlanCapacidadBean getEstadoPlanCapacidad() {
		return estadoPlanCapacidad;
	}

	/**
	 * @param estadoPlanCapacidad the estadoPlanCapacidad to set
	 */
	public void setEstadoPlanCapacidad(EstadoPlanCapacidadBean estadoPlanCapacidad) {
		this.estadoPlanCapacidad = estadoPlanCapacidad;
	}

	/**
	 * @return the lineaNegocioBean
	 */
	public LineaNegocioBean getLineaNegocio() {
		return lineaNegocio;
	}

	/**
	 * @param lineaNegocioBean the lineaNegocioBean to set
	 */
	public void setLineaNegocio(LineaNegocioBean lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

}