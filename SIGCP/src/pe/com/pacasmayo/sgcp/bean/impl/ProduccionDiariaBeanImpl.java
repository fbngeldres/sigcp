package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.TablaKardexBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProduccionDiariaBeanImpl.java
 * Modificado: May 27, 2010 10:24:59 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ProduccionDiariaBeanImpl extends EntidadBeanImpl implements ProduccionDiariaBean {

	private Long pkCodigoProducciondiaria;
	private ParteDiarioBean partediario;
	private OrdenProduccionBean ordenproduccion;
	private Double saldoInicialProducciondiaria;
	private Double ingresoProduccionProducciondi;
	private Double consumoProducciondiaria;
	private Double saldoFinalProducciondiaria;
	private List<TablaKardexBean> tablakardexes = new ArrayList<TablaKardexBean>();

	public Long getPkCodigoProducciondiaria() {
		return pkCodigoProducciondiaria;
	}

	public void setPkCodigoProducciondiaria(Long pkCodigoProducciondiaria) {
		this.pkCodigoProducciondiaria = pkCodigoProducciondiaria;
	}

	public ParteDiarioBean getPartediario() {
		return partediario;
	}

	public void setPartediario(ParteDiarioBean partediario) {
		this.partediario = partediario;
	}

	public OrdenProduccionBean getOrdenproduccion() {
		return ordenproduccion;
	}

	public void setOrdenproduccion(OrdenProduccionBean ordenproduccion) {
		this.ordenproduccion = ordenproduccion;
	}

	public Double getSaldoInicialProducciondiaria() {
		return saldoInicialProducciondiaria;
	}

	public void setSaldoInicialProducciondiaria(Double saldoInicialProducciondiaria) {
		this.saldoInicialProducciondiaria = saldoInicialProducciondiaria;
	}

	public Double getIngresoProduccionProducciondi() {
		return ingresoProduccionProducciondi;
	}

	public void setIngresoProduccionProducciondi(Double ingresoProduccionProducciondi) {
		this.ingresoProduccionProducciondi = ingresoProduccionProducciondi;
	}

	public Double getConsumoProducciondiaria() {
		return consumoProducciondiaria;
	}

	public void setConsumoProducciondiaria(Double consumoProducciondiaria) {
		this.consumoProducciondiaria = consumoProducciondiaria;
	}

	public Double getSaldoFinalProducciondiaria() {
		return saldoFinalProducciondiaria;
	}

	public void setSaldoFinalProducciondiaria(Double saldoFinalProducciondiaria) {
		this.saldoFinalProducciondiaria = saldoFinalProducciondiaria;
	}

	public List<TablaKardexBean> getTablakardexes() {
		return tablakardexes;
	}

	public void setTablakardexes(List<TablaKardexBean> tablakardexes) {
		this.tablakardexes = tablakardexes;
	}
}
