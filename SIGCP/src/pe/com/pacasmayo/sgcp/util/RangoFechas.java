package pe.com.pacasmayo.sgcp.util;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RangoFechas.java
 * Modificado: Feb 23, 2010 4:56:05 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;

/**
 * Clase utilitaria para encapsular un rango de fechas Date en un solo objeto
 * 
 * @author hector.longarte
 */
public class RangoFechas {

	public static final String RANGO_FECHAS = "RANGO_FECHAS";

	private Date fechaInicio;
	private Date fechaFin;
	private String nombreCampo;

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

}
