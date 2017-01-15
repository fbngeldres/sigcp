package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMovimientoBean.java
 * Modificado: Feb 22, 2010 10:51:19 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TipoMovimientoBean extends EntidadBean {

	public abstract String getCodigoSAP();

	public abstract void setCodigoSAP(String codigoSAP);

	public abstract ClasificacionTipoMovimientoBean getClasificacionTipoMovimiento();

	public abstract void setClasificacionTipoMovimiento(ClasificacionTipoMovimientoBean clasificacionTipoMovimiento);
}