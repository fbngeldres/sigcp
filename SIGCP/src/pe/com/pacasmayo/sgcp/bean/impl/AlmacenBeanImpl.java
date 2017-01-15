package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AlmacenBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.AlmacenBean;
import pe.com.pacasmayo.sgcp.bean.UbicacionBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;

public class AlmacenBeanImpl extends EntidadBeanImpl implements AlmacenBean {

	private String codigoSAP;
	private UbicacionBean ubicacion;
	private UnidadBean unidad;

	public AlmacenBeanImpl() {
		unidad = new UnidadBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AlmacenBean#getCodigoSAP()
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AlmacenBean#setCodigoSAP(java.lang.String
	 * )
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AlmacenBean#getUbicacion()
	 */
	public UbicacionBean getUbicacion() {
		return ubicacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AlmacenBean#setUbicacion(pe.com.pacasmayo
	 * .sgcp.bean.UbicacionBean)
	 */
	public void setUbicacion(UbicacionBean ubicacion) {
		this.ubicacion = ubicacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AlmacenBean#getUnidad()
	 */
	public UnidadBean getUnidad() {
		return unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AlmacenBean#setUnidad(pe.com.pacasmayo
	 * .sgcp.bean.UnidadBean)
	 */
	public void setUnidad(UnidadBean unidad) {
		this.unidad = unidad;
	}

}