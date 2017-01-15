package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.CubicacionBean;
import pe.com.pacasmayo.sgcp.bean.CubicacionProductoBean;
import pe.com.pacasmayo.sgcp.bean.EstadoCubicacionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: CubicacionProductoBeanImpl.java
 * Modificado: Jun 7, 2010 8:58:50 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class CubicacionProductoBeanImpl extends EntidadBeanImpl implements CubicacionProductoBean {

	private Long pkCodigoCubicacionproducto;
	private UsuarioBean usuarioByFkCodigoUsuarioRegistra;
	private UsuarioBean usuarioByFkCodigoUsuario;
	private EstadoCubicacionBean estadocubicacion;
	private LineaNegocioBean lineanegocio;
	private UsuarioBean usuarioByFkCodigoUsuarioAprueba;
	private ProduccionBean produccion;
	private Date fechaCubicacionproducto;
	private Double stockFisicoCubicacionproducto;
	private Short mesCubicacionproducto;
	private Integer anoCubicacionproducto;
	private Double toneladasCubicacionproducto;
	private List<CubicacionBean> cubicaciones = new ArrayList<CubicacionBean>();

	public Long getPkCodigoCubicacionproducto() {
		return pkCodigoCubicacionproducto;
	}

	public void setPkCodigoCubicacionproducto(Long pkCodigoCubicacionproducto) {
		this.pkCodigoCubicacionproducto = pkCodigoCubicacionproducto;
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

	public EstadoCubicacionBean getEstadocubicacion() {
		return estadocubicacion;
	}

	public void setEstadocubicacion(EstadoCubicacionBean estadocubicacion) {
		this.estadocubicacion = estadocubicacion;
	}

	public LineaNegocioBean getLineanegocio() {
		return lineanegocio;
	}

	public void setLineanegocio(LineaNegocioBean lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public UsuarioBean getUsuarioByFkCodigoUsuarioAprueba() {
		return usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(UsuarioBean usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}

	public ProduccionBean getProduccion() {
		return produccion;
	}

	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	public Date getFechaCubicacionproducto() {
		return fechaCubicacionproducto;
	}

	public void setFechaCubicacionproducto(Date fechaCubicacionproducto) {
		this.fechaCubicacionproducto = fechaCubicacionproducto;
	}

	public Double getStockFisicoCubicacionproducto() {
		return stockFisicoCubicacionproducto;
	}

	public void setStockFisicoCubicacionproducto(Double stockFisicoCubicacionproducto) {
		this.stockFisicoCubicacionproducto = stockFisicoCubicacionproducto;
	}

	public Short getMesCubicacionproducto() {
		return mesCubicacionproducto;
	}

	public void setMesCubicacionproducto(Short mesCubicacionproducto) {
		this.mesCubicacionproducto = mesCubicacionproducto;
	}

	public Integer getAnoCubicacionproducto() {
		return anoCubicacionproducto;
	}

	public void setAnoCubicacionproducto(Integer anoCubicacionproducto) {
		this.anoCubicacionproducto = anoCubicacionproducto;
	}

	public Double getToneladasCubicacionproducto() {
		return toneladasCubicacionproducto;
	}

	public void setToneladasCubicacionproducto(Double toneladasCubicacionproducto) {
		this.toneladasCubicacionproducto = toneladasCubicacionproducto;
	}

	public List<CubicacionBean> getCubicaciones() {
		return cubicaciones;
	}

	public void setCubicaciones(List<CubicacionBean> cubicaciones) {
		this.cubicaciones = cubicaciones;
	}

}
