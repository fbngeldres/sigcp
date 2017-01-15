package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;
import java.util.Date;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: CubicacionDTO.java
 * Modificado: Jun 16, 2010 10:02:06 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class TablaCubicacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoMedioalmacenamiento;
	private Integer codigoLineaNegocio;
	private Integer codigoProducto;
	private Integer codigoProceso;
	private Integer codigoEstado;

	private String medioAlmacenamiento;
	private String tipoMedioAlmacenamiento;
	private Double volumen;
	private Double densidadMedioAlmc;
	private Double areaCresta;
	private Double areaPie;
	private Double diferenciaNivel;
	private String observacion;
	private Double areaOcupada;
	private Double relacion;
	private Double alturaLadoCarbon;
	private Double alturaCentral;
	private Double alturaLadoClinker;
	private Double alturaLibrePromedio;
	private Double unidad;
	private Date fecha;
	private Double humedadPonderada;

	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	public Double getAreaCresta() {
		return areaCresta;
	}

	public void setAreaCresta(Double areaCresta) {
		this.areaCresta = areaCresta;
	}

	public Double getAreaPie() {
		return areaPie;
	}

	public void setAreaPie(Double areaPie) {
		this.areaPie = areaPie;
	}

	public Double getDiferenciaNivel() {
		return diferenciaNivel;
	}

	public void setDiferenciaNivel(Double diferenciaNivel) {
		this.diferenciaNivel = diferenciaNivel;
	}

	public String getObservacionCubicacion() {
		return observacion;
	}

	public void setObservacionCubicacion(String observacionCubicacion) {
		this.observacion = observacionCubicacion;
	}

	public Double getAreaOcupada() {
		return areaOcupada;
	}

	public void setAreaOcupada(Double areaOcupada) {
		this.areaOcupada = areaOcupada;
	}

	public Double getRelacionCubicacion() {
		return relacion;
	}

	public void setRelacionCubicacion(Double relacionCubicacion) {
		this.relacion = relacionCubicacion;
	}

	public Double getAlturaLadoCarboni() {
		return alturaLadoCarbon;
	}

	public void setAlturaLadoCarboni(Double alturaLadoCarboni) {
		this.alturaLadoCarbon = alturaLadoCarboni;
	}

	public Double getAlturaCentral() {
		return alturaCentral;
	}

	public void setAlturaCentral(Double alturaCentral) {
		this.alturaCentral = alturaCentral;
	}

	public Double getAlturaLadoClinker() {
		return alturaLadoClinker;
	}

	public void setAlturaLadoClinker(Double alturaLadoClinker) {
		this.alturaLadoClinker = alturaLadoClinker;
	}

	public Double getAlturaLibrePromedio() {
		return alturaLibrePromedio;
	}

	public void setAlturaLibrePromedio(Double alturaLibrePromedio) {
		this.alturaLibrePromedio = alturaLibrePromedio;
	}

	public Double getUnidad() {
		return unidad;
	}

	public void setUnidad(Double unidad) {
		this.unidad = unidad;
	}

	public String getMedioAlmacenamiento() {
		return medioAlmacenamiento;
	}

	public void setMedioAlmacenamiento(String medioAlmacenamiento) {
		this.medioAlmacenamiento = medioAlmacenamiento;
	}

	public String getTipoMedioAlmacenamiento() {
		return tipoMedioAlmacenamiento;
	}

	public void setTipoMedioAlmacenamiento(String tipoMedioAlmacenamiento) {
		this.tipoMedioAlmacenamiento = tipoMedioAlmacenamiento;
	}

	public Integer getCodigoLineaNegocio() {
		return codigoLineaNegocio;
	}

	public void setCodigoLineaNegocio(Integer codigoLineaNegocio) {
		this.codigoLineaNegocio = codigoLineaNegocio;
	}

	public Integer getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCodigoProceso() {
		return codigoProceso;
	}

	public void setCodigoProceso(Integer codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Integer getCodigoMedioalmacenamiento() {
		return codigoMedioalmacenamiento;
	}

	public void setCodigoMedioalmacenamiento(Integer codigoMedioalmacenamiento) {
		this.codigoMedioalmacenamiento = codigoMedioalmacenamiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the densidadMedioAlmc
	 */
	public Double getDensidadMedioAlmc() {
		return densidadMedioAlmc;
	}

	/**
	 * @param densidadMedioAlmc the densidadMedioAlmc to set
	 */
	public void setDensidadMedioAlmc(Double densidadMedioAlmc) {
		this.densidadMedioAlmc = densidadMedioAlmc;
	}

	/**
	 * @return the cantTM
	 */
	public Double getCantTM() {
		if (densidadMedioAlmc != null) {
			return volumen * densidadMedioAlmc;
		}

		return 0d;
	}

	/**
	 * @return the humedadPonderada
	 */
	public Double getHumedadPonderada() {
		return humedadPonderada;
	}

	/**
	 * @param humedadPonderada the humedadPonderada to set
	 */
	public void setHumedadPonderada(Double humedadPonderada) {
		this.humedadPonderada = humedadPonderada;
	}

}
