package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.ProductoVariableCalidadBean;
import pe.com.pacasmayo.sgcp.bean.ValorPromVariableCalidadBean;
import pe.com.pacasmayo.sgcp.bean.VariableCalidadBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProductoVariableCalidadBeanImpl.java
 * Modificado: May 27, 2010 11:17:13 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ProductoVariableCalidadBeanImpl extends EntidadBeanImpl implements ProductoVariableCalidadBean {

	private Long pkCodigoProductovariablecalid;
	private HojaRutaBean hojaruta;
	private VariableCalidadBean variablecalidad;
	private Long codigoMatrixScc;
	private Long codigoProcesoScc;
	private List<ValorPromVariableCalidadBean> valorpromvariablecalidads;
	private List<VariableCalidadBean> listaVariablesCalidad;

	public ProductoVariableCalidadBeanImpl() {
		hojaruta = new HojaRutaBeanImpl();
		valorpromvariablecalidads = new ArrayList<ValorPromVariableCalidadBean>();
		listaVariablesCalidad = new ArrayList<VariableCalidadBean>();
	}

	public Long getPkCodigoProductovariablecalid() {
		return pkCodigoProductovariablecalid;
	}

	public void setPkCodigoProductovariablecalid(Long pkCodigoProductovariablecalid) {
		this.pkCodigoProductovariablecalid = pkCodigoProductovariablecalid;
	}

	public HojaRutaBean getHojaruta() {
		return hojaruta;
	}

	public void setHojaruta(HojaRutaBean hojaruta) {
		this.hojaruta = hojaruta;
	}

	public VariableCalidadBean getVariablecalidad() {
		return variablecalidad;
	}

	public void setVariablecalidad(VariableCalidadBean variablecalidad) {
		this.variablecalidad = variablecalidad;
	}

	public Long getCodigoMatrixScc() {
		return codigoMatrixScc;
	}

	public void setCodigoMatrixScc(Long codigoMatrixScc) {
		this.codigoMatrixScc = codigoMatrixScc;
	}

	public List<ValorPromVariableCalidadBean> getValorpromvariablecalidads() {
		return valorpromvariablecalidads;
	}

	public void setValorpromvariablecalidads(List<ValorPromVariableCalidadBean> valorpromvariablecalidads) {
		this.valorpromvariablecalidads = valorpromvariablecalidads;
	}

	public List<VariableCalidadBean> getListaVariablesCalidad() {
		return listaVariablesCalidad;
	}

	public void setListaVariablesCalidad(List<VariableCalidadBean> listaVariablesCalidad) {
		this.listaVariablesCalidad = listaVariablesCalidad;
	}

	/**
	 * @return the codigoProcesoScc
	 */
	public Long getCodigoProcesoScc() {
		return codigoProcesoScc;
	}

	/**
	 * @param codigoProcesoScc the codigoProcesoScc to set
	 */
	public void setCodigoProcesoScc(Long codigoProcesoScc) {
		this.codigoProcesoScc = codigoProcesoScc;
	}

}
