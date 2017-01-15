package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadocubicacionBean.java
 * Modificado: Jun 8, 2010 8:29:07 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface EstadoCubicacionBean extends EntidadBean {

	public abstract Long getPkCodigoEstadocubicacion();

	public abstract void setPkCodigoEstadocubicacion(Long pkCodigoEstadocubicacion);

	public abstract String getNombreEstadocubicacion();

	public abstract void setNombreEstadocubicacion(String nombreEstadocubicacion);

	public abstract List<CubicacionProductoBean> getCubicacionesProductos();

	public abstract void setCubicacionesProductos(List<CubicacionProductoBean> cubicacionesProductos);
}