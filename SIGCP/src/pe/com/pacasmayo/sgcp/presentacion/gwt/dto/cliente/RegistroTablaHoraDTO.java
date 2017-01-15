package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TablaHoraDTO.java
 * Modificado: Mar 12, 2010 5:51:11 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class RegistroTablaHoraDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigoUbicacion;
	private Integer pkCodigoProducto;
	private Integer codigoPuestoTrabajo;
	private Integer codigoSilo;
	private String dia;
	private String nombreProducto;
	private String nombrePuestoTrabajo;
	private String nombreSilo;
	private String horaInicial;
	private Double capacidadsilo;
	private Double stockAcual;

	private Integer pkCodigoOrdenProduccion;

	private boolean[] arregloHoras = null;

	public Integer getPkCodigoProducto() {
		return pkCodigoProducto;
	}

	public void setPkCodigoProducto(Integer pkCodigoProducto) {
		this.pkCodigoProducto = pkCodigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombrePuestoTrabajo() {
		return nombrePuestoTrabajo;
	}

	public void setNombrePuestoTrabajo(String nombrePuestoTrabajo) {
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
	}

	public Integer getCodigoPuestoTrabajo() {
		return codigoPuestoTrabajo;
	}

	public void setCodigoPuestoTrabajo(Integer codigoPuestoTrabajo) {
		this.codigoPuestoTrabajo = codigoPuestoTrabajo;
	}

	public String getNombreSilo() {
		return nombreSilo;
	}

	public void setNombreSilo(String nombreSilo) {
		this.nombreSilo = nombreSilo;
	}

	public Integer getCodigoSilo() {
		return codigoSilo;
	}

	public void setCodigoSilo(Integer codigoSilo) {
		this.codigoSilo = codigoSilo;
	}

	public boolean[] getArregloHoras() {
		return arregloHoras;
	}

	public void setArregloHoras(boolean[] arregloHoras) {
		this.arregloHoras = arregloHoras;
	}

	public Integer getPkCodigoOrdenProduccion() {
		return pkCodigoOrdenProduccion;
	}

	public void setPkCodigoOrdenProduccion(Integer pkCodigoOrdenProduccion) {
		this.pkCodigoOrdenProduccion = pkCodigoOrdenProduccion;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * @return the horaInicial
	 */
	public String getHoraInicial() {
		return horaInicial;
	}

	/**
	 * @param horaInicial the horaInicial to set
	 */
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	/**
	 * @return the codigoUbicacion
	 */
	public Integer getCodigoUbicacion() {
		return codigoUbicacion;
	}

	/**
	 * @param codigoUbicacion the codigoUbicacion to set
	 */
	public void setCodigoUbicacion(Integer codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}

	public Double getCapacidadsilo() {
		return capacidadsilo;
	}

	public void setCapacidadsilo(Double capacidadsilo) {
		this.capacidadsilo = capacidadsilo;
	}

	public Double getStockAcual() {
		return stockAcual;
	}

	public void setStockAcual(Double stockAcual) {
		this.stockAcual = stockAcual;
	}
}