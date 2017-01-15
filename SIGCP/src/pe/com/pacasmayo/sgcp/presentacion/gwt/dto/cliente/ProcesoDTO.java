package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProcesoDTO.java
 * Modificado: Mar 9, 2010 6:52:29 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ProcesoDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCodigoProceso;
	private String nombreProceso;
	private String descripcionProceso;
	private Short ordenEjecucionProceso;
	private LineaNegocioDTO lineaNegocio;
	private Long codigoSccProceso;
	private String siglasProceso;

	public Long getPkCodigoProceso() {
		return pkCodigoProceso;
	}

	public void setPkCodigoProceso(Long pkCodigoProceso) {
		this.pkCodigoProceso = pkCodigoProceso;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getDescripcionProceso() {
		return descripcionProceso;
	}

	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}

	public Short getOrdenEjecucionProceso() {
		return ordenEjecucionProceso;
	}

	public void setOrdenEjecucionProceso(Short ordenEjecucionProceso) {
		this.ordenEjecucionProceso = ordenEjecucionProceso;
	}

	public Long getCodigoSccProceso() {
		return codigoSccProceso;
	}

	public void setCodigoSccProceso(Long codigoSccProceso) {
		this.codigoSccProceso = codigoSccProceso;
	}

	/**
	 * @return the siglasProceso
	 */
	public String getSiglasProceso() {
		return siglasProceso;
	}

	/**
	 * @param siglasProceso the siglasProceso to set
	 */
	public void setSiglasProceso(String siglasProceso) {
		this.siglasProceso = siglasProceso;
	}

	public LineaNegocioDTO getLineaNegocio() {
		return lineaNegocio;
	}

	public void setLineaNegocio(LineaNegocioDTO lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

}
