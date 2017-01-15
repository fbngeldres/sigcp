package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;
import java.util.Date;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoPorPuestoTrabajoDTO.java
 * Modificado: Jan 11, 2012 4:34:25 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ConsumoPorPuestoTrabajoDTO implements Serializable {

	private static final long serialVersionUID = -2938319737338632021L;

	private Date fecha;
	private Long codigoPuesto;
	private String nombrePuesto;
	private Double consumo;
	private Long consumopt;
	private Double consumoHumedo;

	// Variables Adicionales
	private Long productoGenerado;
	private Long componente;

	public ConsumoPorPuestoTrabajoDTO(Date fecha, Long codigoPuesto, String nombrePuesto, Double consumo, Long consumopt,
			Double consumoHumedo) {
		this.fecha = fecha;
		this.codigoPuesto = codigoPuesto;
		this.nombrePuesto = nombrePuesto;
		this.consumo = consumo;
		this.consumopt = consumopt;
		this.consumoHumedo = consumoHumedo;
	}

	public ConsumoPorPuestoTrabajoDTO(Date fecha, Long codigoPuesto, String nombrePuesto, Long productoGenerado, Long componente,
			Double consumoHumedo, Double consumo) {
		super();
		this.fecha = fecha;
		this.codigoPuesto = codigoPuesto;
		this.nombrePuesto = nombrePuesto;
		this.consumo = consumo;
		this.consumoHumedo = consumoHumedo;
		this.productoGenerado = productoGenerado;
		this.componente = componente;
	}

	/**
	 * @return the codigoPuesto
	 */
	public Long getCodigoPuesto() {
		return codigoPuesto;
	}

	/**
	 * @param codigoPuesto the codigoPuesto to set
	 */
	public void setCodigoPuesto(Long codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	/**
	 * @return the nombrePuesto
	 */
	public String getNombrePuesto() {
		return nombrePuesto;
	}

	/**
	 * @param nombrePuesto the nombrePuesto to set
	 */
	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

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
	 * @return the consumo
	 */
	public Double getConsumo() {
		return consumo;
	}

	/**
	 * @param consumo the consumo to set
	 */
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public Long getConsumopt() {
		return consumopt;
	}

	public void setConsumopt(Long consumopt) {
		this.consumopt = consumopt;
	}

	public Double getConsumoHumedo() {
		return consumoHumedo;
	}

	public void setConsumoHumedo(Double consumoHumedo) {
		this.consumoHumedo = consumoHumedo;
	}

	public Long getProductoGenerado() {
		return productoGenerado;
	}

	public void setProductoGenerado(Long productoGenerado) {
		this.productoGenerado = productoGenerado;
	}

	public Long getComponente() {
		return componente;
	}

	public void setComponente(Long componente) {
		this.componente = componente;
	}

}
