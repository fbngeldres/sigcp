package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ConsumoComponentePuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.EstadoMovimientoBean;
import pe.com.pacasmayo.sgcp.bean.MovimientoAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.TipoMovimientoBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: MovimientoAjusteBeanImpl.java
 * Modificado: May 27, 2010 10:10:45 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class MovimientoAjusteBeanImpl extends EntidadBeanImpl implements MovimientoAjusteBean {

	private Long pkCodigoMovimientoajuste;
	private ConsumoComponentePuestoTrabajoBean consumocomponentepuestotrabajo;
	private ProductoBean producto;
	private EstadoMovimientoBean estadomovimiento;
	private PuestoTrabajoBean puestotrabajo;
	private TipoMovimientoBean tipomovimiento;
	private Double cantidadMovimientoajuste;
	private String descripcionMovimientoajuste;

	public Long getPkCodigoMovimientoajuste() {
		return pkCodigoMovimientoajuste;
	}

	public void setPkCodigoMovimientoajuste(Long pkCodigoMovimientoajuste) {
		this.pkCodigoMovimientoajuste = pkCodigoMovimientoajuste;
	}

	public ConsumoComponentePuestoTrabajoBean getConsumocomponentepuestotrabajo() {
		return consumocomponentepuestotrabajo;
	}

	public void setConsumocomponentepuestotrabajo(ConsumoComponentePuestoTrabajoBean consumocomponentepuestotrabajo) {
		this.consumocomponentepuestotrabajo = consumocomponentepuestotrabajo;
	}

	public ProductoBean getProducto() {
		return producto;
	}

	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	public EstadoMovimientoBean getEstadomovimiento() {
		return estadomovimiento;
	}

	public void setEstadomovimiento(EstadoMovimientoBean estadomovimiento) {
		this.estadomovimiento = estadomovimiento;
	}

	public PuestoTrabajoBean getPuestotrabajo() {
		return puestotrabajo;
	}

	public void setPuestotrabajo(PuestoTrabajoBean puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public TipoMovimientoBean getTipomovimiento() {
		return tipomovimiento;
	}

	public void setTipomovimiento(TipoMovimientoBean tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public Double getCantidadMovimientoajuste() {
		return cantidadMovimientoajuste;
	}

	public void setCantidadMovimientoajuste(Double cantidadMovimientoajuste) {
		this.cantidadMovimientoajuste = cantidadMovimientoajuste;
	}

	public String getDescripcionMovimientoajuste() {
		return descripcionMovimientoajuste;
	}

	public void setDescripcionMovimientoajuste(String descripcionMovimientoajuste) {
		this.descripcionMovimientoajuste = descripcionMovimientoajuste;
	}

}
