package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoOrdenProduccionDTO.java
 * Modificado: Mar 10, 2010 10:47:30 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EstadoProduccionSemanalDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCodigoEstadoProduccionSemanal;
	private String nombreEstadoProduccionSemanal;

	public Long getPkCodigoEstadoProduccionSemanal() {
		return pkCodigoEstadoProduccionSemanal;
	}

	public void setPkCodigoEstadoProduccionSemanal(Long pkCodigoEstadoProduccionSemanal) {
		this.pkCodigoEstadoProduccionSemanal = pkCodigoEstadoProduccionSemanal;
	}

	public String getNombreEstadoProduccionSemanal() {
		return nombreEstadoProduccionSemanal;
	}

	public void setNombreEstadoProduccionSemanal(String nombreEstadoorden) {
		this.nombreEstadoProduccionSemanal = nombreEstadoorden;
	}
}
