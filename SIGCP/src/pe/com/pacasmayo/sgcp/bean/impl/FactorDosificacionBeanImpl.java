package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorDosificacionBeanImpl.java
 * Modificado: Jan 11, 2010 10:22:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.DosificacionBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class FactorDosificacionBeanImpl extends EntidadBeanImpl implements FactorDosificacionBean {

	private ComponenteBean componente;
	private UnidadMedidaBean unidad;
	private HojaRutaBean hojaRuta;
	private List<DosificacionBean> dosificaciones;
	private FactorDosificacionRegistroMensualBean[] factorDosificacionRegistroMensual;
	private String proyeccion;

	public FactorDosificacionBeanImpl() {
		factorDosificacionRegistroMensual = new FactorDosificacionRegistroMensualBeanImpl[12];
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#getComponente()
	 */
	public ComponenteBean getComponente() {
		return componente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#setComponente(
	 * pe.com.pacasmayo.sgcp.bean.ComponenteBean)
	 */
	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#getUnidad()
	 */
	public UnidadMedidaBean getUnidad() {
		return unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#setUnidad(pe.com
	 * .pacasmayo.sgcp.bean.UnidadMedidaBean)
	 */
	public void setUnidad(UnidadMedidaBean unidad) {
		this.unidad = unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#getHojaRuta()
	 */
	public HojaRutaBean getHojaRuta() {
		return hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#setHojaRuta(pe
	 * .com.pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	public void setHojaRuta(HojaRutaBean hojaRuta) {
		this.hojaRuta = hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#getDosificaciones
	 * ()
	 */
	public List<DosificacionBean> getDosificaciones() {
		return dosificaciones;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#setDosificaciones
	 * (java.util.Set)
	 */
	public void setDosificaciones(List<DosificacionBean> dosificaciones) {
		this.dosificaciones = dosificaciones;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#
	 * getFactorDosificacionRegistroMensual()
	 */
	public FactorDosificacionRegistroMensualBean[] getFactorDosificacionRegistroMensual() {
		return factorDosificacionRegistroMensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.FactorDosificacionBean#
	 * setFactorDosificacionRegistroMensual
	 * (pe.com.pacasmayo.sgcp.bean.FactorDosificacionRegistroMensualBean[])
	 */
	public void setFactorDosificacionRegistroMensual(FactorDosificacionRegistroMensualBean[] factorDosificacionRegistroMensual) {
		this.factorDosificacionRegistroMensual = factorDosificacionRegistroMensual;
	}

	public String getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(String proyeccion) {
		this.proyeccion = proyeccion;
	}

}