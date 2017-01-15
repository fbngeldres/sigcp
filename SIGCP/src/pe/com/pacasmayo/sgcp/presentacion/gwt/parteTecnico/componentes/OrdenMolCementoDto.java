package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenMolCementoDto.java
 * Modificado: Dec 27, 2011 4:32:12 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class OrdenMolCementoDto implements java.io.Serializable {

	private static final long serialVersionUID = 2063528456371329155L;

	private Integer orden = 10;
	private String nombreComponenteOrden;

	public OrdenMolCementoDto(String nombreComponenteOrden) {
		this.nombreComponenteOrden = nombreComponenteOrden;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the nombreComponenteOrden
	 */
	public String getNombreComponenteOrden() {
		return nombreComponenteOrden;
	}

	/**
	 * @param nombreComponenteOrden the nombreComponenteOrden to set
	 */
	public void setNombreComponenteOrden(String nombreComponenteOrden) {
		this.nombreComponenteOrden = nombreComponenteOrden;
	}

}