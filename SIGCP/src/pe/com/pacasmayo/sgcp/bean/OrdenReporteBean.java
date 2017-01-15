package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: OrdenReporteBean.java
 * Modificado: Aug 23, 2012 6:25:12 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface OrdenReporteBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract ProcesoBean getProceso();

	public abstract void setProceso(ProcesoBean proceso);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract Long getOrdenReporte();

	public abstract void setOrdenReporte(Long ordenReporte);

	public abstract String getOrdenProcesoProducto();

	public abstract void setOrdenProcesoProducto(String ordenProcesoProducto);

	public abstract String getTipoOrdenReporte();

	public abstract void setTipoOrdenReporte(String tipoOrdenReporte);

}
