package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AlmacenBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.FactorKardexBean;
import pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.TablaKardexBean;
import pe.com.pacasmayo.sgcp.bean.UbicacionBean;
import pe.com.pacasmayo.sgcp.bean.ValorPromVariableCalidadBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TablaKardexBeanImpl.java
 * Modificado: May 27, 2010 11:20:11 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class TablaKardexBeanImpl extends EntidadBeanImpl implements TablaKardexBean {

	private Long pkCodigoTablakardex;
	private ProduccionDiariaBean producciondiaria;
	private AlmacenBean almacen;
	private UbicacionBean ubicacionByFkCodigoUbicacionDestino;
	private UbicacionBean ubicacionByFkCodigoUbicacionOrigen;
	private MedioAlmacenamientoBean medioalmacenamiento;
	private Date fechaTablakardex;
	private String observacionTablakardex;
	private Double ingresoTablakardex;
	private Double consumoTablakardex;
	private Double saldoInicialTablakardex;
	private Double stockFisicoTablakardex;
	private Double stockFinalTablakardex;
	private Double variacionTablakardex;
	private Double ingresoHumedadTablakardex;
	private Double consumoHumedadTablakardex;
	private List<ValorPromVariableCalidadBean> valorpromvariablecalidads = new ArrayList<ValorPromVariableCalidadBean>();
	private List<FactorKardexBean> factorkardexes = new ArrayList<FactorKardexBean>();
	private List<ConsumoComponenteBean> consumocomponentes = new ArrayList<ConsumoComponenteBean>();

	public Long getPkCodigoTablakardex() {
		return pkCodigoTablakardex;
	}

	public void setPkCodigoTablakardex(Long pkCodigoTablakardex) {
		this.pkCodigoTablakardex = pkCodigoTablakardex;
	}

	public ProduccionDiariaBean getProducciondiaria() {
		return producciondiaria;
	}

	public void setProducciondiaria(ProduccionDiariaBean producciondiaria) {
		this.producciondiaria = producciondiaria;
	}

	public AlmacenBean getAlmacen() {
		return almacen;
	}

	public void setAlmacen(AlmacenBean almacen) {
		this.almacen = almacen;
	}

	public UbicacionBean getUbicacionByFkCodigoUbicacionDestino() {
		return ubicacionByFkCodigoUbicacionDestino;
	}

	public void setUbicacionByFkCodigoUbicacionDestino(UbicacionBean ubicacionByFkCodigoUbicacionDestino) {
		this.ubicacionByFkCodigoUbicacionDestino = ubicacionByFkCodigoUbicacionDestino;
	}

	public UbicacionBean getUbicacionByFkCodigoUbicacionOrigen() {
		return ubicacionByFkCodigoUbicacionOrigen;
	}

	public void setUbicacionByFkCodigoUbicacionOrigen(UbicacionBean ubicacionByFkCodigoUbicacionOrigen) {
		this.ubicacionByFkCodigoUbicacionOrigen = ubicacionByFkCodigoUbicacionOrigen;
	}

	public MedioAlmacenamientoBean getMedioalmacenamiento() {
		return medioalmacenamiento;
	}

	public void setMedioalmacenamiento(MedioAlmacenamientoBean medioalmacenamiento) {
		this.medioalmacenamiento = medioalmacenamiento;
	}

	public Date getFechaTablakardex() {
		return fechaTablakardex;
	}

	public void setFechaTablakardex(Date fechaTablakardex) {
		this.fechaTablakardex = fechaTablakardex;
	}

	public String getObservacionTablakardex() {
		return observacionTablakardex;
	}

	public void setObservacionTablakardex(String observacionTablakardex) {
		this.observacionTablakardex = observacionTablakardex;
	}

	public Double getIngresoTablakardex() {
		return ingresoTablakardex;
	}

	public void setIngresoTablakardex(Double ingresoTablakardex) {
		this.ingresoTablakardex = ingresoTablakardex;
	}

	public Double getConsumoTablakardex() {
		return consumoTablakardex;
	}

	public void setConsumoTablakardex(Double consumoTablakardex) {
		this.consumoTablakardex = consumoTablakardex;
	}

	public Double getSaldoInicialTablakardex() {
		return saldoInicialTablakardex;
	}

	public void setSaldoInicialTablakardex(Double saldoInicialTablakardex) {
		this.saldoInicialTablakardex = saldoInicialTablakardex;
	}

	public Double getStockFisicoTablakardex() {
		return stockFisicoTablakardex;
	}

	public void setStockFisicoTablakardex(Double stockFisicoTablakardex) {
		this.stockFisicoTablakardex = stockFisicoTablakardex;
	}

	public Double getStockFinalTablakardex() {
		return stockFinalTablakardex;
	}

	public void setStockFinalTablakardex(Double stockFinalTablakardex) {
		this.stockFinalTablakardex = stockFinalTablakardex;
	}

	public Double getVariacionTablakardex() {
		return variacionTablakardex;
	}

	public void setVariacionTablakardex(Double variacionTablakardex) {
		this.variacionTablakardex = variacionTablakardex;
	}

	public Double getIngresoHumedadTablakardex() {
		return ingresoHumedadTablakardex;
	}

	public void setIngresoHumedadTablakardex(Double ingresoHumedadTablakardex) {
		this.ingresoHumedadTablakardex = ingresoHumedadTablakardex;
	}

	public Double getConsumoHumedadTablakardex() {
		return consumoHumedadTablakardex;
	}

	public void setConsumoHumedadTablakardex(Double consumoHumedadTablakardex) {
		this.consumoHumedadTablakardex = consumoHumedadTablakardex;
	}

	public List<ValorPromVariableCalidadBean> getValorpromvariablecalidads() {
		return valorpromvariablecalidads;
	}

	public void setValorpromvariablecalidads(List<ValorPromVariableCalidadBean> valorpromvariablecalidads) {
		this.valorpromvariablecalidads = valorpromvariablecalidads;
	}

	public List<FactorKardexBean> getFactorkardexes() {
		return factorkardexes;
	}

	public void setFactorkardexes(List<FactorKardexBean> factorkardexes) {
		this.factorkardexes = factorkardexes;
	}

	public List<ConsumoComponenteBean> getConsumocomponentes() {
		return consumocomponentes;
	}

	public void setConsumocomponentes(List<ConsumoComponenteBean> consumocomponentes) {
		this.consumocomponentes = consumocomponentes;
	}
}
