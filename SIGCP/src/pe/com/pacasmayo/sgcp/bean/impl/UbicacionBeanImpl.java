package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UbicacionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.AlmacenBean;
import pe.com.pacasmayo.sgcp.bean.UbicacionBean;

public class UbicacionBeanImpl extends EntidadBeanImpl implements UbicacionBean {

	private AlmacenBean almacen;

	public UbicacionBeanImpl() {
		almacen = new AlmacenBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UbicacionBean#getAlmacen()
	 */
	public AlmacenBean getAlmacen() {
		return almacen;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UbicacionBean#setAlmacen(pe.com.pacasmayo
	 * .sgcp.bean.AlmacenBean)
	 */
	public void setAlmacen(AlmacenBean almacen) {
		this.almacen = almacen;
	}

}