package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMovimientoBeanImpl.java
 * Modificado: Feb 22, 2010 10:49:00 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ClasificacionTipoMovimientoBean;
import pe.com.pacasmayo.sgcp.bean.TipoMovimientoBean;

public class TipoMovimientoBeanImpl extends EntidadBeanImpl implements TipoMovimientoBean {

	private String codigoSAP;
	private ClasificacionTipoMovimientoBean clasificacionTipoMovimiento;

	public TipoMovimientoBeanImpl() {
		clasificacionTipoMovimiento = new ClasificacionTipoMovimientoBeanImpl();
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	public ClasificacionTipoMovimientoBean getClasificacionTipoMovimiento() {
		return clasificacionTipoMovimiento;
	}

	public void setClasificacionTipoMovimiento(ClasificacionTipoMovimientoBean clasificacionTipoMovimiento) {
		this.clasificacionTipoMovimiento = clasificacionTipoMovimiento;
	}

}
