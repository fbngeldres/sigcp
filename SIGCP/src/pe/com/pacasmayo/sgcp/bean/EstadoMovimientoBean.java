package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoMovimientoBean.java
 * Modificado: May 25, 2010 10:42:30 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoMovimientoBean extends EntidadBean {

	public abstract Long getPkCodigoEstadomovimiento();

	public abstract void setPkCodigoEstadomovimiento(Long pkCodigoEstadomovimiento);

	public abstract String getNombreEstadomovimiento();

	public abstract void setNombreEstadomovimiento(String nombreEstadomovimiento);

}