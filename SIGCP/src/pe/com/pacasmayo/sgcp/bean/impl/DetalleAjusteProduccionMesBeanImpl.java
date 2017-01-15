package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.DetalleAjusteProduccionMesBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DetalleAjusteProduccionMesBeanImpl.java
 * Modificado: Jul 29, 2010 4:07:43 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class DetalleAjusteProduccionMesBeanImpl implements DetalleAjusteProduccionMesBean {

	private Long codigoAjuste;
	private String sociedad;
	private String unidad;
	private String lineaNegocio;
	private String estado;
	private String mesAnio;
	private String usuarioAprueba;
	private String usuarioAjusta;
	private String fechaAprueba;
	private String division;
	private Boolean envioconsumo;
	private Boolean enviocombustible;

	public String getMensajeConsumo() {
		if (envioconsumo != null && envioconsumo) {
			return "Enviado";
		}
		return "Pendiente";
	}

	public String getMensajeCombustible() {
		if (enviocombustible != null && enviocombustible) {
			return "Enviado";
		}
		return "Pendiente";
	}

	/**
	 * @return the envioconsumo
	 */
	public Boolean getEnvioconsumo() {
		return envioconsumo;
	}

	/**
	 * @param envioconsumo the envioconsumo to set
	 */
	public void setEnvioconsumo(Boolean envioconsumo) {
		this.envioconsumo = envioconsumo;
	}

	/**
	 * @return the enviocobustible
	 */
	public Boolean getEnviocombustible() {
		return enviocombustible;
	}

	/**
	 * @param enviocobustible the enviocobustible to set
	 */
	public void setEnviocombustible(Boolean enviocombustible) {
		this.enviocombustible = enviocombustible;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * DetalleAjusteProduccionMes()
	 */
	public void DetalleAjusteProduccionMes() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * getCodigoAjuste()
	 */
	public Long getCodigoAjuste() {
		return codigoAjuste;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * setCodigoAjuste(java.lang.Long)
	 */
	public void setCodigoAjuste(Long codigoAjuste) {
		this.codigoAjuste = codigoAjuste;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#getSociedad
	 * ()
	 */
	public String getSociedad() {
		return sociedad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#setSociedad
	 * (java.lang.String)
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#getUnidad
	 * ()
	 */
	public String getUnidad() {
		return unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#setUnidad
	 * (java.lang.String)
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * getLineaNegocio()
	 */
	public String getLineaNegocio() {
		return lineaNegocio;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * setLineaNegocio(java.lang.String)
	 */
	public void setLineaNegocio(String lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#getEstado
	 * ()
	 */
	public String getEstado() {
		return estado;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#setEstado
	 * (java.lang.String)
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#getMesAnio
	 * ()
	 */
	public String getMesAnio() {
		return mesAnio;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#setMesAnio
	 * (java.lang.String)
	 */
	public void setMesAnio(String mesAnio) {
		this.mesAnio = mesAnio;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * getUsuarioAprueba()
	 */
	public String getUsuarioAprueba() {
		return usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * setUsuarioAprueba(java.lang.String)
	 */
	public void setUsuarioAprueba(String usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * getFechaAprueba()
	 */
	public String getFechaAprueba() {
		return fechaAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.DetalleAjusteProduccionMesBean#
	 * setFechaAprueba(java.lang.String)
	 */
	public void setFechaAprueba(String fechaAprueba) {
		this.fechaAprueba = fechaAprueba;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getUsuarioAjusta() {
		return usuarioAjusta;
	}

	public void setUsuarioAjusta(String usuarioAjusta) {
		this.usuarioAjusta = usuarioAjusta;
	}
}
