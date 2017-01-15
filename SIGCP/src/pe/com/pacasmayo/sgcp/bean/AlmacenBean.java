package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AlmacenBean.java
 * Modificado: Dec 22, 2009 1:23:52 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface AlmacenBean extends EntidadBean {

	public abstract String getCodigoSAP();

	public abstract void setCodigoSAP(String codigoSAP);

	public abstract UbicacionBean getUbicacion();

	public abstract void setUbicacion(UbicacionBean ubicacion);

	public abstract UnidadBean getUnidad();

	public abstract void setUnidad(UnidadBean unidad);
}