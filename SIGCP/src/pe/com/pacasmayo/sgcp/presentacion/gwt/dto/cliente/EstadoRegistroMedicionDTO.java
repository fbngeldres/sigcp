package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoRegistroMedicionDTO.java
 * Modificado: Apr 22, 2010 5:41:27 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EstadoRegistroMedicionDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long pkCodigoEstadoregistromedicio;
	private String nombreEstadoregistromedicion;
	private String descripcionEstadoregistromedic;

	public Long getPkCodigoEstadoregistromedicio() {
		return pkCodigoEstadoregistromedicio;
	}

	public void setPkCodigoEstadoregistromedicio(Long pkCodigoEstadoregistromedicio) {
		this.pkCodigoEstadoregistromedicio = pkCodigoEstadoregistromedicio;
	}

	public String getNombreEstadoregistromedicion() {
		return nombreEstadoregistromedicion;
	}

	public void setNombreEstadoregistromedicion(String nombreEstadoregistromedicion) {
		this.nombreEstadoregistromedicion = nombreEstadoregistromedicion;
	}

	public String getDescripcionEstadoregistromedic() {
		return descripcionEstadoregistromedic;
	}

	public void setDescripcionEstadoregistromedic(String descripcionEstadoregistromedic) {
		this.descripcionEstadoregistromedic = descripcionEstadoregistromedic;
	}

}
