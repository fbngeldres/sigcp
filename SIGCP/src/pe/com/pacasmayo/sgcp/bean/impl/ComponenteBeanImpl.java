package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ComponenteBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;

public class ComponenteBeanImpl implements ComponenteBean {

	private Long codigo;
	private ProductoBean producto;
	private ProductoBean productoComponente;
	private List<FactorDosificacionBean> factorDosificacion;

	public ComponenteBeanImpl() {
		factorDosificacion = new ArrayList<FactorDosificacionBean>();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#getProducto()
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#setProducto(pe.com.pacasmayo
	 * .sgcp.bean.ProductoBean)
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#getProductoComponente()
	 */
	public ProductoBean getProductoComponente() {
		return productoComponente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#setProductoComponente(
	 * pe.com.pacasmayo.sgcp.bean.ProductoBean)
	 */
	public void setProductoComponente(ProductoBean productoComponente) {
		this.productoComponente = productoComponente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#getFactorDosificacionBean
	 * ()
	 */
	public List<FactorDosificacionBean> getFactorDosificacion() {
		return factorDosificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteBean#setFactorDosificacionBean
	 * (java.util.List)
	 */
	public void setFactorDosificacion(List<FactorDosificacionBean> factorDosificacionBean) {
		this.factorDosificacion = factorDosificacionBean;
	}

}