package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ConsumoPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ProductoGeneradoBean;
import pe.com.pacasmayo.sgcp.bean.TablaOperacionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProductoGeneradoBeanImpl.java
 * Modificado: May 31, 2010 10:25:50 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ProductoGeneradoBeanImpl extends EntidadBeanImpl implements ProductoGeneradoBean {

	private Long pkCodigoProductogenerado;
	private TablaOperacionBean tablaoperacion;
	private OrdenProduccionBean ordenproduccion;
	private Double produccionTmProductogenerado;
	private Double horasProductogenerado;
	private List<ConsumoPuestoTrabajoBean> consumopuestotrabajos = new ArrayList<ConsumoPuestoTrabajoBean>();

	public Long getPkCodigoProductogenerado() {
		return pkCodigoProductogenerado;
	}

	public void setPkCodigoProductogenerado(Long pkCodigoProductogenerado) {
		this.pkCodigoProductogenerado = pkCodigoProductogenerado;
	}

	public TablaOperacionBean getTablaoperacion() {
		return tablaoperacion;
	}

	public void setTablaoperacion(TablaOperacionBean tablaoperacion) {
		this.tablaoperacion = tablaoperacion;
	}

	public OrdenProduccionBean getOrdenproduccion() {
		return ordenproduccion;
	}

	public void setOrdenproduccion(OrdenProduccionBean ordenproduccion) {
		this.ordenproduccion = ordenproduccion;
	}

	public Double getProduccionTmProductogenerado() {
		return produccionTmProductogenerado;
	}

	public void setProduccionTmProductogenerado(Double produccionTmProductogenerado) {
		this.produccionTmProductogenerado = produccionTmProductogenerado;
	}

	public Double getHorasProductogenerado() {
		return horasProductogenerado;
	}

	public void setHorasProductogenerado(Double horasProductogenerado) {
		this.horasProductogenerado = horasProductogenerado;
	}

	public List<ConsumoPuestoTrabajoBean> getConsumopuestotrabajos() {
		return consumopuestotrabajos;
	}

	public void setConsumopuestotrabajos(List<ConsumoPuestoTrabajoBean> consumopuestotrabajos) {
		this.consumopuestotrabajos = consumopuestotrabajos;
	}

}
