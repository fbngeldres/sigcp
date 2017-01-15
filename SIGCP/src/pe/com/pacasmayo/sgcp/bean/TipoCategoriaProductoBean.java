package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoCategoriaProductoBean.java
 * Modificado: Jan 20, 2011 4:56:00 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TipoCategoriaProductoBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

}
