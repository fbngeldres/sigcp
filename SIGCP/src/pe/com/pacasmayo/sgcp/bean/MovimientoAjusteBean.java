package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: MovimientoAjusteBean.java
 * Modificado: May 26, 2010 3:09:09 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface MovimientoAjusteBean extends EntidadBean {

	public abstract Long getPkCodigoMovimientoajuste();

	public abstract void setPkCodigoMovimientoajuste(Long pkCodigoMovimientoajuste);

	public abstract ConsumoComponentePuestoTrabajoBean getConsumocomponentepuestotrabajo();

	public abstract void setConsumocomponentepuestotrabajo(ConsumoComponentePuestoTrabajoBean consumocomponentepuestotrabajo);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract EstadoMovimientoBean getEstadomovimiento();

	public abstract void setEstadomovimiento(EstadoMovimientoBean estadomovimiento);

	public abstract PuestoTrabajoBean getPuestotrabajo();

	public abstract void setPuestotrabajo(PuestoTrabajoBean puestotrabajo);

	public abstract TipoMovimientoBean getTipomovimiento();

	public abstract void setTipomovimiento(TipoMovimientoBean tipomovimiento);

	public abstract Double getCantidadMovimientoajuste();

	public abstract void setCantidadMovimientoajuste(Double cantidadMovimientoajuste);

	public abstract String getDescripcionMovimientoajuste();

	public abstract void setDescripcionMovimientoajuste(String descripcionMovimientoajuste);

}