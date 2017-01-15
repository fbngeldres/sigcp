package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoColumnaReporteBean.java
 * Modificado: Jun 1, 2010 8:30:49 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoColumnaReporteBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract String getNombreEstadoColumnaReporte();

	public abstract void setNombreEstadoColumnaReporte(String nombreEstadoColumnaReporte);

	public abstract ColumnaReporteBean getColumnaReporte();

	public abstract void setColumnaReporte(ColumnaReporteBean columnaReporte);

}
