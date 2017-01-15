package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.FactorKardexBean;
import pe.com.pacasmayo.sgcp.bean.ProductoFactorCorreccionBean;
import pe.com.pacasmayo.sgcp.bean.TablaKardexBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: FactorKardexBeanImpl.java
 * Modificado: May 27, 2010 10:06:26 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class FactorKardexBeanImpl extends EntidadBeanImpl implements FactorKardexBean {

	private Long pkCodigoFactorkardex;
	private ProductoFactorCorreccionBean productofactorcorreccion;
	private TablaKardexBean tablakardex;

	public Long getPkCodigoFactorkardex() {
		return pkCodigoFactorkardex;
	}

	public void setPkCodigoFactorkardex(Long pkCodigoFactorkardex) {
		this.pkCodigoFactorkardex = pkCodigoFactorkardex;
	}

	public ProductoFactorCorreccionBean getProductofactorcorreccion() {
		return productofactorcorreccion;
	}

	public void setProductofactorcorreccion(ProductoFactorCorreccionBean productofactorcorreccion) {
		this.productofactorcorreccion = productofactorcorreccion;
	}

	public TablaKardexBean getTablakardex() {
		return tablakardex;
	}

	public void setTablakardex(TablaKardexBean tablakardex) {
		this.tablakardex = tablakardex;
	}

}
