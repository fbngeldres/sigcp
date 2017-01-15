package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.PeriodoContableBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: AjusteProduccionBeanImpl.java
 * Modificado: May 27, 2010 8:09:17 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class AjusteProduccionBeanImpl extends EntidadBeanImpl implements AjusteProduccionBean {

	private Long pkCodigoAjusteproduccion;
	private EstadoAjusteProduccionBean estadoajusteproduccion;
	private UsuarioBean usuarioByFkCodigoUsuarioRegistra;
	private UsuarioBean usuarioByFkCodigoUsuario;
	private PeriodoContableBean periodocontable;
	private LineaNegocioBean lineanegocio;
	private String fechaAprobacionAjusteproduccion;
	private List<AjusteProductoBean> ajueProductos = new ArrayList<AjusteProductoBean>();
	private Boolean consumoEnviadoSap;
	private Boolean combutibleEnviadoSap;

	/**
	 * @return the consumoEnviadoSap
	 */
	public Boolean getConsumoEnviadoSap() {
		return consumoEnviadoSap;
	}

	/**
	 * @param consumoEnviadoSap the consumoEnviadoSap to set
	 */
	public void setConsumoEnviadoSap(Boolean consumoEnviadoSap) {
		this.consumoEnviadoSap = consumoEnviadoSap;
	}

	/**
	 * @return the combutibleEnviadoSap
	 */
	public Boolean getCombutibleEnviadoSap() {
		return combutibleEnviadoSap;
	}

	/**
	 * @param combutibleEnviadoSap the combutibleEnviadoSap to set
	 */
	public void setCombutibleEnviadoSap(Boolean combutibleEnviadoSap) {
		this.combutibleEnviadoSap = combutibleEnviadoSap;
	}

	public Long getPkCodigoAjusteproduccion() {
		return pkCodigoAjusteproduccion;
	}

	public void setPkCodigoAjusteproduccion(Long pkCodigoAjusteproduccion) {
		this.pkCodigoAjusteproduccion = pkCodigoAjusteproduccion;
	}

	public EstadoAjusteProduccionBean getEstadoajusteproduccion() {
		return estadoajusteproduccion;
	}

	public void setEstadoajusteproduccion(EstadoAjusteProduccionBean estadoajusteproduccion) {
		this.estadoajusteproduccion = estadoajusteproduccion;
	}

	public UsuarioBean getUsuarioByFkCodigoUsuarioRegistra() {
		return usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(UsuarioBean usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public UsuarioBean getUsuarioByFkCodigoUsuario() {
		return usuarioByFkCodigoUsuario;
	}

	public void setUsuarioByFkCodigoUsuario(UsuarioBean usuarioByFkCodigoUsuario) {
		this.usuarioByFkCodigoUsuario = usuarioByFkCodigoUsuario;
	}

	public PeriodoContableBean getPeriodocontable() {
		return periodocontable;
	}

	public void setPeriodocontable(PeriodoContableBean periodocontable) {
		this.periodocontable = periodocontable;
	}

	public LineaNegocioBean getLineanegocio() {
		return lineanegocio;
	}

	public void setLineanegocio(LineaNegocioBean lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public String getFechaAprobacionAjusteproduccion() {
		return fechaAprobacionAjusteproduccion;
	}

	public void setFechaAprobacionAjusteproduccion(String fechaAprobacionAjusteproduccion) {
		this.fechaAprobacionAjusteproduccion = fechaAprobacionAjusteproduccion;
	}

	/**
	 * @return the ajueProductos
	 */
	public List<AjusteProductoBean> getAjueProductos() {
		return ajueProductos;
	}

	/**
	 * @param ajueProductos the ajueProductos to set
	 */
	public void setAjueProductos(List<AjusteProductoBean> ajueProductos) {
		this.ajueProductos = ajueProductos;
	}
}
