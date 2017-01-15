package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoOrdenProduccionDTO.java
 * Modificado: Mar 10, 2010 10:47:30 AM 
 * Autor: daniel.loreto
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EstadoParteDiarioDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCodigoEstadoParteDiario;
	private String nombreEstadoParteDiario;

	public Long getPkCodigoEstadoParteDiario() {
		return pkCodigoEstadoParteDiario;
	}

	public void setPkCodigoEstadoParteDiario(Long pkCodigoEstadoParteDiario) {
		this.pkCodigoEstadoParteDiario = pkCodigoEstadoParteDiario;
	}

	public String getNombreEstadoParteDiario() {
		return nombreEstadoParteDiario;
	}

	public void setNombreEstadoParteDiario(String nombreEstadoParteDiario) {
		this.nombreEstadoParteDiario = nombreEstadoParteDiario;
	}

}
