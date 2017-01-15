package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.CubicacionProductoBean;
import pe.com.pacasmayo.sgcp.bean.EstadoCubicacionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoCubicacionBeanImpl.java
 * Modificado: Jun 7, 2010 8:35:24 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class EstadoCubicacionBeanImpl extends EntidadBeanImpl implements EstadoCubicacionBean {

	private Long pkCodigoEstadocubicacion;
	private String nombreEstadocubicacion;
	private List<CubicacionProductoBean> cubicacionesProductos;

	public Long getPkCodigoEstadocubicacion() {
		return pkCodigoEstadocubicacion;
	}

	public void setPkCodigoEstadocubicacion(Long pkCodigoEstadocubicacion) {
		this.pkCodigoEstadocubicacion = pkCodigoEstadocubicacion;
	}

	public String getNombreEstadocubicacion() {
		return nombreEstadocubicacion;
	}

	public void setNombreEstadocubicacion(String nombreEstadocubicacion) {
		this.nombreEstadocubicacion = nombreEstadocubicacion;
	}

	public List<CubicacionProductoBean> getCubicacionesProductos() {
		return cubicacionesProductos;
	}

	public void setCubicacionesProductos(List<CubicacionProductoBean> cubicacionesProductos) {
		this.cubicacionesProductos = cubicacionesProductos;
	}

}
