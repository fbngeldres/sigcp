package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroDetalleCarbonesDTO.java
 * Modificado: Dec 23, 2011 7:56:38 AM 
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

public class RegistroDetalleCarbonesDTO implements Serializable {

	private static final long serialVersionUID = -9062798871485838957L;

	private Map<String, String> componentesMix1 = new HashMap<String, String>();
	private Map<String, String> componentesMix2 = new HashMap<String, String>();

	private Map<String, Double[]> puestoTrabajoMix1 = new HashMap<String, Double[]>();
	private Map<String, Double[]> puestoTrabajoMix2 = new HashMap<String, Double[]>();

	private Date fecha;

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the componentesMix1
	 */
	public Map<String, String> getComponentesMix1() {
		return componentesMix1;
	}

	/**
	 * @param componentesMix1 the componentesMix1 to set
	 */
	public void setComponentesMix1(Map<String, String> componentesMix1) {
		this.componentesMix1 = componentesMix1;
	}

	/**
	 * @return the componentesMix2
	 */
	public Map<String, String> getComponentesMix2() {
		return componentesMix2;
	}

	/**
	 * @param componentesMix2 the componentesMix2 to set
	 */
	public void setComponentesMix2(Map<String, String> componentesMix2) {
		this.componentesMix2 = componentesMix2;
	}

	public Map<String, Double[]> getPuestoTrabajoMix1() {
		return puestoTrabajoMix1;
	}

	public void setPuestoTrabajoMix1(Map<String, Double[]> puestoTrabajoMix1) {
		this.puestoTrabajoMix1 = puestoTrabajoMix1;
	}

	public Map<String, Double[]> getPuestoTrabajoMix2() {
		return puestoTrabajoMix2;
	}

	public void setPuestoTrabajoMix2(Map<String, Double[]> puestoTrabajoMix2) {
		this.puestoTrabajoMix2 = puestoTrabajoMix2;
	}

}
