package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProduccionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoAjusteProduccionBeanImpl.java
 * Modificado: May 27, 2010 9:53:24 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class EstadoAjusteProduccionBeanImpl extends EntidadBeanImpl implements EstadoAjusteProduccionBean {

	private Long pkCodigoEstadoajusteproduccio;
	private String nombreEstadoajusteproduccion;
	private List<AjusteProduccionBean> ajusteproduccions = new ArrayList<AjusteProduccionBean>();

	public Long getPkCodigoEstadoajusteproduccio() {
		return pkCodigoEstadoajusteproduccio;
	}

	public void setPkCodigoEstadoajusteproduccio(Long pkCodigoEstadoajusteproduccio) {
		this.pkCodigoEstadoajusteproduccio = pkCodigoEstadoajusteproduccio;
	}

	public String getNombreEstadoajusteproduccion() {
		return nombreEstadoajusteproduccion;
	}

	public void setNombreEstadoajusteproduccion(String nombreEstadoajusteproduccion) {
		this.nombreEstadoajusteproduccion = nombreEstadoajusteproduccion;
	}

	public List<AjusteProduccionBean> getAjusteproduccions() {
		return ajusteproduccions;
	}

	public void setAjusteproduccions(List<AjusteProduccionBean> ajusteproduccions) {
		this.ajusteproduccions = ajusteproduccions;
	}
}
