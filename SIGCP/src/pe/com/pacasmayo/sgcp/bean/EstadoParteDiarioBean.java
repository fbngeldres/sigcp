package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoParteDiarioBean.java
 * Modificado: May 26, 2010 4:42:04 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface EstadoParteDiarioBean extends EntidadBean {

	public abstract Long getPkCodigoEstadopartediario();

	public abstract void setPkCodigoEstadopartediario(Long pkCodigoEstadopartediario);

	public abstract String getNombreEstadopartediario();

	public abstract void setNombreEstadopartediario(String nombreEstadopartediario);

	public abstract String getDescripcionEstadopartediario();

	public abstract void setDescripcionEstadopartediario(String descripcionEstadopartediario);

	public abstract List<ParteDiarioBean> getPartediarios();

	public abstract void setPartediarios(List<ParteDiarioBean> partediarios);

}