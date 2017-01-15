package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoOrdenProduccionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean;

public class EstadoOrdenProduccionBeanImpl implements EstadoOrdenProduccionBean {

	private Long codigo;
	private String nombre;

	public EstadoOrdenProduccionBeanImpl() {

	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

}