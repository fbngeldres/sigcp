package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: VariableCalidadBeanImpl.java
 * Modificado: Nov 23, 2009 9:44:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ProductoVariableCalidadBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;
import pe.com.pacasmayo.sgcp.bean.VariableCalidadBean;

public class VariableCalidadBeanImpl extends EntidadBeanImpl implements VariableCalidadBean {

	private UnidadMedidaBean unidadmedida;
	private List<ProductoVariableCalidadBean> productovariablecalidads = new ArrayList<ProductoVariableCalidadBean>();

	public UnidadMedidaBean getUnidadmedida() {
		return unidadmedida;
	}

	public void setUnidadmedida(UnidadMedidaBean unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public List<ProductoVariableCalidadBean> getProductoVariableCalidad() {
		return productovariablecalidads;
	}

	public void setProductoVariableCalidad(List<ProductoVariableCalidadBean> productovariablecalidads) {
		this.productovariablecalidads = productovariablecalidads;
	}

}