package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.ParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.PeriodoContableBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ParteDiarioBeanImpl.java
 * Modificado: May 27, 2010 10:13:06 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ParteDiarioBeanImpl extends EntidadBeanImpl implements ParteDiarioBean {

	private Long pkCodigoPartediario;
	private UsuarioBean usuarioByFkCodigoUsuarioRegistra;
	private UsuarioBean usuarioByFkCodigoUsuarioCierra;
	private EstadoParteDiarioBean estadopartediario;
	private PeriodoContableBean periodocontable;
	private LineaNegocioBean lineanegocio;
	private List<ProduccionDiariaBean> producciondiarias = new ArrayList<ProduccionDiariaBean>();
	private List<ProduccionPuestoTrabajoBean> produccionpuestotrabajos = new ArrayList<ProduccionPuestoTrabajoBean>();

	public Long getPkCodigoPartediario() {
		return pkCodigoPartediario;
	}

	public void setPkCodigoPartediario(Long pkCodigoPartediario) {
		this.pkCodigoPartediario = pkCodigoPartediario;
	}

	public EstadoParteDiarioBean getEstadopartediario() {
		return estadopartediario;
	}

	public void setEstadopartediario(EstadoParteDiarioBean estadopartediario) {
		this.estadopartediario = estadopartediario;
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

	public List<ProduccionDiariaBean> getProducciondiarias() {
		return producciondiarias;
	}

	public void setProducciondiarias(List<ProduccionDiariaBean> producciondiarias) {
		this.producciondiarias = producciondiarias;
	}

	public List<ProduccionPuestoTrabajoBean> getProduccionpuestotrabajos() {
		return produccionpuestotrabajos;
	}

	public void setProduccionpuestotrabajos(List<ProduccionPuestoTrabajoBean> produccionpuestotrabajos) {
		this.produccionpuestotrabajos = produccionpuestotrabajos;
	}

	public UsuarioBean getUsuarioByFkCodigoUsuarioRegistra() {
		return usuarioByFkCodigoUsuarioRegistra;
	}

	public void setUsuarioByFkCodigoUsuarioRegistra(UsuarioBean usuarioByFkCodigoUsuarioRegistra) {
		this.usuarioByFkCodigoUsuarioRegistra = usuarioByFkCodigoUsuarioRegistra;
	}

	public UsuarioBean getUsuarioByFkCodigoUsuarioCierra() {
		return usuarioByFkCodigoUsuarioCierra;
	}

	public void setUsuarioByFkCodigoUsuarioCierra(UsuarioBean usuarioByFkCodigoUsuarioCierra) {
		this.usuarioByFkCodigoUsuarioCierra = usuarioByFkCodigoUsuarioCierra;
	}

}
