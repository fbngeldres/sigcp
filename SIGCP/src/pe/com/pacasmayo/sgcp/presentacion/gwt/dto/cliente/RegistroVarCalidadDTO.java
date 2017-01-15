package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroVarCalidadDTO.java
 * Modificado: Aug 9, 2011 9:03:21 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroVarCalidadDTO implements Serializable {

	private static final long serialVersionUID = 5771904575143654638L;

	private Date fecha;
	private Map<String, Map<String, Double>> variablesCalidad = new HashMap<String, Map<String, Double>>();
	private Map<String, Map<String, Double>> variablesVariacion = new HashMap<String, Map<String, Double>>();

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Map<String, Map<String, Double>> getVariablesCalidad() {
		return variablesCalidad;
	}

	public void setVariablesCalidad(Map<String, Map<String, Double>> variables) {
		this.variablesCalidad = variables;
	}

	public Map<String, Map<String, Double>> getVariablesVariacion() {
		return variablesVariacion;
	}

	public void setVariablesVariacion(Map<String, Map<String, Double>> variablesVariacion) {
		this.variablesVariacion = variablesVariacion;
	}

}
