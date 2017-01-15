package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TablaPrograma.java
 * Modificado: Feb 15, 2010 9:23:55 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

/**
 * Clase que representa un registro de la tabla de programas
 */
public class UbicacionEspecificaDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String puesto;
	private Integer codigoPuesto;
	private String silo;
	private Integer codigoSilo;
	private List<HoraUbicacionDTO> horas;

	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return the codigoPuesto
	 */
	public Integer getCodigoPuesto() {
		return codigoPuesto;
	}

	/**
	 * @param codigoPuesto the codigoPuesto to set
	 */
	public void setCodigoPuesto(Integer codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	/**
	 * @return the silo
	 */
	public String getSilo() {
		return silo;
	}

	/**
	 * @param silo the silo to set
	 */
	public void setSilo(String silo) {
		this.silo = silo;
	}

	/**
	 * @return the codigoSilo
	 */
	public Integer getCodigoSilo() {
		return codigoSilo;
	}

	/**
	 * @param codigoSilo the codigoSilo to set
	 */
	public void setCodigoSilo(Integer codigoSilo) {
		this.codigoSilo = codigoSilo;
	}

	/**
	 * @return the horas
	 */
	public List<HoraUbicacionDTO> getHoras() {
		return horas;
	}

	/**
	 * @param horas the horas to set
	 */
	public void setHoras(List<HoraUbicacionDTO> horas) {
		this.horas = horas;
	}

}