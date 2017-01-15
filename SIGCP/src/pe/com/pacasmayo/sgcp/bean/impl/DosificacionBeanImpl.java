package pe.com.pacasmayo.sgcp.bean.impl;

/* * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DosificacionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.DosificacionBean;
import pe.com.pacasmayo.sgcp.bean.DosificacionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class DosificacionBeanImpl extends EntidadBeanImpl implements DosificacionBean {

	private FactorDosificacionBean factorDosificacion;
	private UnidadMedidaBean unidadMedida;
	private DosificacionRegistroMensualBean[] dosificacionRegistroMensualBeanList = new DosificacionRegistroMensualBeanImpl[12];

	public DosificacionBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.DosificacionBean#getFactorDosificacion()
	 */
	public FactorDosificacionBean getFactorDosificacion() {
		return factorDosificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.DosificacionBean#setFactorDosificacion(pe.
	 * com.pacasmayo.sgcp.bean.FactorDosificacionBean)
	 */
	public void setFactorDosificacion(FactorDosificacionBean factorDosificacion) {
		this.factorDosificacion = factorDosificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.DosificacionBean#getUnidadMedida()
	 */
	public UnidadMedidaBean getUnidadMedida() {
		return unidadMedida;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.DosificacionBean#setUnidadMedida(pe.com.pacasmayo
	 * .sgcp.bean.UnidadMedidaBean)
	 */
	public void setUnidadMedida(UnidadMedidaBean unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * @return the dosificacionRegistroMensualBeanList
	 */
	public DosificacionRegistroMensualBean[] getDosificacionRegistroMensualBeanList() {
		return dosificacionRegistroMensualBeanList;
	}

	/**
	 * @param dosificacionRegistroMensualBeanList the
	 *            dosificacionRegistroMensualBeanList to set
	 */
	public void setDosificacionRegistroMensualBeanList(DosificacionRegistroMensualBean[] dosificacionRegistroMensualBeanList) {
		this.dosificacionRegistroMensualBeanList = dosificacionRegistroMensualBeanList;
	}
}