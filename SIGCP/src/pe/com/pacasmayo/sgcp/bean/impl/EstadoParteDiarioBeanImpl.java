package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.ParteDiarioBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoParteDiarioBeanImpl.java
 * Modificado: May 27, 2010 10:01:26 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class EstadoParteDiarioBeanImpl extends EntidadBeanImpl implements EstadoParteDiarioBean {

	private Long pkCodigoEstadopartediario;
	private String nombreEstadopartediario;
	private String descripcionEstadopartediario;
	private List<ParteDiarioBean> partediarios = new ArrayList<ParteDiarioBean>();

	public Long getPkCodigoEstadopartediario() {
		return pkCodigoEstadopartediario;
	}

	public void setPkCodigoEstadopartediario(Long pkCodigoEstadopartediario) {
		this.pkCodigoEstadopartediario = pkCodigoEstadopartediario;
	}

	public String getNombreEstadopartediario() {
		return nombreEstadopartediario;
	}

	public void setNombreEstadopartediario(String nombreEstadopartediario) {
		this.nombreEstadopartediario = nombreEstadopartediario;
	}

	public String getDescripcionEstadopartediario() {
		return descripcionEstadopartediario;
	}

	public void setDescripcionEstadopartediario(String descripcionEstadopartediario) {
		this.descripcionEstadopartediario = descripcionEstadopartediario;
	}

	public List<ParteDiarioBean> getPartediarios() {
		return partediarios;
	}

	public void setPartediarios(List<ParteDiarioBean> partediarios) {
		this.partediarios = partediarios;
	}

}
