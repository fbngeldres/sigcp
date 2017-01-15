package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadocubicacionDTO.java
 * Modificado: Jun 20, 2010 3:31:25 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class EstadocubicacionDTO implements Serializable {

	private Long pkCodigoEstadocubicacion;
	private String nombreEstadocubicacion;

	public Long getPkCodigoEstadocubicacion() {
		return pkCodigoEstadocubicacion;
	}

	public void setPkCodigoEstadocubicacion(Long pkCodigoEstadocubicacion) {
		this.pkCodigoEstadocubicacion = pkCodigoEstadocubicacion;
	}

	public String getNombreEstadocubicacion() {
		return nombreEstadocubicacion;
	}

	public void setNombreEstadocubicacion(String nombreEstadocubicacion) {
		this.nombreEstadocubicacion = nombreEstadocubicacion;
	}

}
