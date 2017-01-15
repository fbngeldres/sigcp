package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroTablaMedicionDia.java
 * Modificado: Apr 20, 2010 3:32:11 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class RegistroTablaMedicionDiaDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Long codigoSilo;
	private String nombreSilo;
	private Double alturaSilo;
	private Long codigoProducto;
	private String nombreProducto;
	private Long codigoProduccion;

	// Mapa para guardar las alturas medidas del silo: codigoAltura, valor
	private List<Double> listaAlturas;
	private Double cantidad;
	private Double capacidad;
	private Double porcentajeUso;
	private Long numeroAlturas;
	private Double factorMetrosCubicos;
	private Double stockSeguridad;
	private Long codigoEstado;

	public List<Double> getListaAlturas() {
		return listaAlturas;
	}

	public void setListaAlturas(List<Double> listaAlturas) {
		this.listaAlturas = listaAlturas;
	}

	public Long getNumeroAlturas() {
		return numeroAlturas;
	}

	public void setNumeroAlturas(Long numeroAlturas) {
		this.numeroAlturas = numeroAlturas;
	}

	public Long getCodigoSilo() {
		return codigoSilo;
	}

	public void setCodigoSilo(Long codigoSilo) {
		this.codigoSilo = codigoSilo;
	}

	public String getNombreSilo() {
		return nombreSilo;
	}

	public void setNombreSilo(String nombreSilo) {
		this.nombreSilo = nombreSilo;
	}

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}

	public Double getPorcentajeUso() {
		return porcentajeUso;
	}

	public void setPorcentajeUso(Double porcentajeUso) {
		this.porcentajeUso = porcentajeUso;
	}

	public Double getAlturaSilo() {
		return alturaSilo;
	}

	public void setAlturaSilo(Double alturaSilo) {
		this.alturaSilo = alturaSilo;
	}

	public Double getFactorMetrosCubicos() {
		return factorMetrosCubicos;
	}

	public void setFactorMetrosCubicos(Double factorMetrosCubicos) {
		this.factorMetrosCubicos = factorMetrosCubicos;
	}

	public Double getStockSeguridad() {
		return stockSeguridad;
	}

	public void setStockSeguridad(Double stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}

	public Long getCodigoProduccion() {
		return codigoProduccion;
	}

	public void setCodigoProduccion(Long codigoProduccion) {
		this.codigoProduccion = codigoProduccion;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

}
