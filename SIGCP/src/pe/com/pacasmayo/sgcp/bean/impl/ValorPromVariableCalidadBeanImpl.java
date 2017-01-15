package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ProductoVariableCalidadBean;
import pe.com.pacasmayo.sgcp.bean.TablaKardexBean;
import pe.com.pacasmayo.sgcp.bean.ValorPromVariableCalidadBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ValorPromVariableCalidadBeanImpl.java
 * Modificado: May 27, 2010 11:15:20 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ValorPromVariableCalidadBeanImpl extends EntidadBeanImpl implements ValorPromVariableCalidadBean {

	private Long pkCodigoValorpromvariblecalid;
	private TablaKardexBean tablakardex;
	private ProductoVariableCalidadBean productovariablecalidad;
	private Double valorValorpromvariblecalidad;

	public Long getPkCodigoValorpromvariblecalid() {
		return pkCodigoValorpromvariblecalid;
	}

	public void setPkCodigoValorpromvariblecalid(Long pkCodigoValorpromvariblecalid) {
		this.pkCodigoValorpromvariblecalid = pkCodigoValorpromvariblecalid;
	}

	public TablaKardexBean getTablakardex() {
		return tablakardex;
	}

	public void setTablakardex(TablaKardexBean tablakardex) {
		this.tablakardex = tablakardex;
	}

	public ProductoVariableCalidadBean getProductovariablecalidad() {
		return productovariablecalidad;
	}

	public void setProductovariablecalidad(ProductoVariableCalidadBean productovariablecalidad) {
		this.productovariablecalidad = productovariablecalidad;
	}

	public Double getValorValorpromvariblecalidad() {
		return valorValorpromvariblecalidad;
	}

	public void setValorValorpromvariblecalidad(Double valorValorpromvariblecalidad) {
		this.valorValorpromvariblecalidad = valorValorpromvariblecalidad;
	}

}
