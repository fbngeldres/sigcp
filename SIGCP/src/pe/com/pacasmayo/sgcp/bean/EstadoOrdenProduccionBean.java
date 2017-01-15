package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoOrdenProduccionBean.java
 * Modificado: Dec 22, 2009 1:12:16 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoOrdenProduccionBean {

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);
}