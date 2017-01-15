package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.Date;

/*
 * Backlog 
 * Archivo: MedicionDiariaDTO.java
 * Modificado: Nov 22, 2012 10:26:46 AM
 * Autor: Stephany
 *
 * Copyright (C) Gintelligence, 2012. All rights reserved.
 *
 * Developed by: Gintelligence. http://www.gintelligence.net
 */
public class MedicionDiariaDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long codigoTipoMedioAlmacenamiento;
	public String nombreTipoMedioAlmacenamiento;
	public Integer annoRegistroMedicion;
	public Short mesRegistroMedicion;
	public Date fechaRegistroMedicion;
	public Double cantidadMedioAlmacenamiento;

	public MedicionDiariaDTO(Long codigoTipoMedioAlmacenamiento, String nombreTipoMedioAlmacenamiento,
			Integer annoRegistroMedicion, Short mesRegistroMedicion, Date fechaRegistroMedicion,
			Double cantidadMedioAlmacenamiento) {
		super();
		this.codigoTipoMedioAlmacenamiento = codigoTipoMedioAlmacenamiento;
		this.nombreTipoMedioAlmacenamiento = nombreTipoMedioAlmacenamiento;
		this.annoRegistroMedicion = annoRegistroMedicion;
		this.mesRegistroMedicion = mesRegistroMedicion;
		this.fechaRegistroMedicion = fechaRegistroMedicion;
		this.cantidadMedioAlmacenamiento = cantidadMedioAlmacenamiento;
	}

	public Long getCodigoTipoMedioAlmacenamiento() {
		return codigoTipoMedioAlmacenamiento;
	}

	public void setCodigoTipoMedioAlmacenamiento(Long codigoTipoMedioAlmacenamiento) {
		this.codigoTipoMedioAlmacenamiento = codigoTipoMedioAlmacenamiento;
	}

	public String getNombreTipoMedioAlmacenamiento() {
		return nombreTipoMedioAlmacenamiento;
	}

	public void setNombreTipoMedioAlmacenamiento(String nombreTipoMedioAlmacenamiento) {
		this.nombreTipoMedioAlmacenamiento = nombreTipoMedioAlmacenamiento;
	}

	public Integer getAnnoRegistroMedicion() {
		return annoRegistroMedicion;
	}

	public void setAnnoRegistroMedicion(Integer annoRegistroMedicion) {
		this.annoRegistroMedicion = annoRegistroMedicion;
	}

	public Short getMesRegistroMedicion() {
		return mesRegistroMedicion;
	}

	public void setMesRegistroMedicion(Short mesRegistroMedicion) {
		this.mesRegistroMedicion = mesRegistroMedicion;
	}

	public Date getFechaRegistroMedicion() {
		return fechaRegistroMedicion;
	}

	public void setFechaRegistroMedicion(Date fechaRegistroMedicion) {
		this.fechaRegistroMedicion = fechaRegistroMedicion;
	}

	public Double getCantidadMedioAlmacenamiento() {
		return cantidadMedioAlmacenamiento;
	}

	public void setCantidadMedioAlmacenamiento(Double cantidadMedioAlmacenamiento) {
		this.cantidadMedioAlmacenamiento = cantidadMedioAlmacenamiento;
	}

}
