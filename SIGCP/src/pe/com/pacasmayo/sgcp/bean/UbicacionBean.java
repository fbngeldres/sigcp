package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UbicacionBean.java
 * Modificado: Jan 27, 2010 1:24:07 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface UbicacionBean extends EntidadBean {

	public abstract AlmacenBean getAlmacen();

	public abstract void setAlmacen(AlmacenBean almacen);

}
