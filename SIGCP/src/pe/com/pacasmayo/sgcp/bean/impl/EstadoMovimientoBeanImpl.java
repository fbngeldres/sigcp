package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoMovimientoBeanImpl.java
 * Modificado: May 25, 2010 10:42:30 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
import pe.com.pacasmayo.sgcp.bean.EstadoMovimientoBean;

public class EstadoMovimientoBeanImpl extends EntidadBeanImpl implements EstadoMovimientoBean {

	private Long pkCodigoEstadomovimiento;
	private String nombreEstadomovimiento;

	public String getNombreEstadomovimiento() {
		return nombreEstadomovimiento;
	}

	public Long getPkCodigoEstadomovimiento() {
		return pkCodigoEstadomovimiento;
	}

	public void setNombreEstadomovimiento(String nombreEstadomovimiento) {
		this.nombreEstadomovimiento = nombreEstadomovimiento;
	}

	public void setPkCodigoEstadomovimiento(Long pkCodigoEstadomovimiento) {
		this.pkCodigoEstadomovimiento = pkCodigoEstadomovimiento;
	}

}
