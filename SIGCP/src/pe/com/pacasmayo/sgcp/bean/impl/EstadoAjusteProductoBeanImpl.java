package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProductoBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoAjusteProductoBeanImpl.java
 * Modificado: May 27, 2010 9:56:51 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class EstadoAjusteProductoBeanImpl extends EntidadBeanImpl implements EstadoAjusteProductoBean {

	private Long pkCodigoEstadoajusteproducto;
	private String nombreEstadoajusteproducto;
	private List<AjusteProductoBean> ajusteproductos = new ArrayList<AjusteProductoBean>();

	public Long getPkCodigoEstadoajusteproducto() {
		return pkCodigoEstadoajusteproducto;
	}

	public void setPkCodigoEstadoajusteproducto(Long pkCodigoEstadoajusteproducto) {
		this.pkCodigoEstadoajusteproducto = pkCodigoEstadoajusteproducto;
	}

	public String getNombreEstadoajusteproducto() {
		return nombreEstadoajusteproducto;
	}

	public void setNombreEstadoajusteproducto(String nombreEstadoajusteproducto) {
		this.nombreEstadoajusteproducto = nombreEstadoajusteproducto;
	}

	public List<AjusteProductoBean> getAjusteproductos() {
		return ajusteproductos;
	}

	public void setAjusteproductos(List<AjusteProductoBean> ajusteproductos) {
		this.ajusteproductos = ajusteproductos;
	}

}
