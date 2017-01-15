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

import pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;

public class CapacidadOperativaRegistroMensualBeanImpl implements CapacidadOperativaRegistroMensualBean {

	private Long codigo;
	private PlanAnualBean planAnual;
	private Short mesCapacidadOperativaregistromensual;
	private Integer annoCapacidadOperativaRegistroMensual;
	private Double cantidadCapacidadOperativaRegistroMensual;
	private String cantidadCapacidadOperativa;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #getPlanAnual()
	 */
	public PlanAnualBean getPlanAnual() {
		return planAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #setPlanAnual(pe.com.pacasmayo.sgcp.bean.PlanAnualBean)
	 */
	public void setPlanAnual(PlanAnualBean planAnual) {
		this.planAnual = planAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #getMesCapacidadOperativaregistromensual()
	 */
	public Short getMesCapacidadOperativaregistromensual() {
		return mesCapacidadOperativaregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #setMesCapacidadOperativaregistromensual(java.lang.Short)
	 */
	public void setMesCapacidadOperativaregistromensual(Short mesCapacidadOperativaregistromensual) {
		this.mesCapacidadOperativaregistromensual = mesCapacidadOperativaregistromensual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #getAnnoCapacidadOperativaRegistroMensual()
	 */
	public Integer getAnnoCapacidadOperativaRegistroMensual() {
		return annoCapacidadOperativaRegistroMensual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #setAnnoCapacidadOperativaRegistroMensual(java.lang.Integer)
	 */
	public void setAnnoCapacidadOperativaRegistroMensual(Integer annoCapacidadOperativaRegistroMensual) {
		this.annoCapacidadOperativaRegistroMensual = annoCapacidadOperativaRegistroMensual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #getCantidadCapacidadOperativaRegistroMensual()
	 */
	public Double getCantidadCapacidadOperativaRegistroMensual() {
		return cantidadCapacidadOperativaRegistroMensual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaRegistroMensualBean
	 * #setCantidadCapacidadOperativaRegistroMensual(java.lang.Double)
	 */
	public void setCantidadCapacidadOperativaRegistroMensual(Double cantidadCapacidadOperativaRegistroMensual) {
		this.cantidadCapacidadOperativaRegistroMensual = cantidadCapacidadOperativaRegistroMensual;
	}

	public String getCantidadCapacidadOperativa() {
		return cantidadCapacidadOperativa;
	}

	public void setCantidadCapacidadOperativa(String cantidadCapacidadOperativa) {
		this.cantidadCapacidadOperativa = cantidadCapacidadOperativa;
	}

}
