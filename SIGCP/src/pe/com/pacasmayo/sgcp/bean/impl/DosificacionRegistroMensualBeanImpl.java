package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DosificacionRegistroMensualBean.java
 * Modificado: Feb 25, 2010 11:37:15 AM 
 * Autor: daniel.loreto
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.HashSet;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.DosificacionBean;
import pe.com.pacasmayo.sgcp.bean.DosificacionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;

public class DosificacionRegistroMensualBeanImpl implements DosificacionRegistroMensualBean {

	private Long codigo;
	private PlanAnualBean planAnual;
	private DosificacionBean dosificacion;
	private Short mesDosificacionRegistroMensual;
	private Integer annoDosificacionRegistroMensua;
	private Double cantidadDosificacionRegistroMe;
	private Set consumoComponentePlans = new HashSet(0);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#getCodigo
	 * ()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#setCodigo
	 * (java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#getPlanAnual
	 * ()
	 */
	public PlanAnualBean getPlanAnual() {
		return planAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#setPlanAnual
	 * (pe.com.pacasmayo.sgcp.bean.PlanAnualBean)
	 */
	public void setPlanAnual(PlanAnualBean planAnual) {
		this.planAnual = planAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * getDosificacion()
	 */
	public DosificacionBean getDosificacion() {
		return dosificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * setDosificacion(pe.com.pacasmayo.sgcp.bean.DosificacionBean)
	 */
	public void setDosificacion(DosificacionBean dosificacion) {
		this.dosificacion = dosificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * getMesDosificacionRegistroMensual()
	 */
	public Short getMesDosificacionRegistroMensual() {
		return mesDosificacionRegistroMensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * setMesDosificacionRegistroMensual(java.lang.Short)
	 */
	public void setMesDosificacionRegistroMensual(Short mesDosificacionRegistroMensual) {
		this.mesDosificacionRegistroMensual = mesDosificacionRegistroMensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * getAnnoDosificacionRegistroMensua()
	 */
	public Integer getAnnoDosificacionRegistroMensua() {
		return annoDosificacionRegistroMensua;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * setAnnoDosificacionRegistroMensua(java.lang.Integer)
	 */
	public void setAnnoDosificacionRegistroMensua(Integer annoDosificacionRegistroMensua) {
		this.annoDosificacionRegistroMensua = annoDosificacionRegistroMensua;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * getCantidadDosificacionRegistroMe()
	 */
	public Double getCantidadDosificacionRegistroMe() {
		return cantidadDosificacionRegistroMe;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * setCantidadDosificacionRegistroMe(java.lang.Double)
	 */
	public void setCantidadDosificacionRegistroMe(Double cantidadDosificacionRegistroMe) {
		this.cantidadDosificacionRegistroMe = cantidadDosificacionRegistroMe;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * getConsumoComponentePlans()
	 */
	public Set getConsumoComponentePlans() {
		return consumoComponentePlans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DosificacionRegistroMensualBean#
	 * setConsumoComponentePlans(java.util.Set)
	 */
	public void setConsumoComponentePlans(Set consumoComponentePlans) {
		this.consumoComponentePlans = consumoComponentePlans;
	}

}
