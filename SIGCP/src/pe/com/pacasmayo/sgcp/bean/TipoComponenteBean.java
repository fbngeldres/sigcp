package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoComponenteBean.java
 * Modificado: Apr 28, 2010 9:10:36 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TipoComponenteBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

}
