package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanAnualBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.EstadoPlanBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;
import pe.com.pacasmayo.sgcp.bean.PlanComercializacionBean;
import pe.com.pacasmayo.sgcp.bean.PlanNecesidadBean;
import pe.com.pacasmayo.sgcp.bean.PlanParadaBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

public class PlanAnualBeanImpl implements PlanAnualBean {

	private int anno;
	private Long codigo;
	private String comentario;
	private String fechaAprueba;
	private String fechaRegistra;
	private Short mesCorteVersion;
	private Double necesidadComercial;
	private Double produccionAnual;
	private String version;
	private LineaNegocioBean lineaNegocio = new LineaNegocioBeanImpl();
	private OrdenProduccionBean ordenProduccion;
	private PlanParadaBean planParada;
	private PlanNecesidadBean planNecesidad;
	private List<PlanComercializacionBean> listaPlanComercializacion = new ArrayList<PlanComercializacionBean>();
	private UsuarioBean usuarioRegistra = new UsuarioBeanImpl();
	private UsuarioBean usuarioAprueba = new UsuarioBeanImpl();
	private EstadoPlanBean estadoPlan = new EstadoPlanBeanImpl();
	private List<CapacidadOperativaRegistroMensualBean> listaCapacidadOperRegMen = new ArrayList<CapacidadOperativaRegistroMensualBean>();
	private String usuarioApruebaConFecha;
	private String usuarioRegistraConFecha;

	public PlanAnualBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getAnno()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getAnno()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getAnno()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getAnno()
	 */
	public int getAnno() {
		return anno;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setAnno(int)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setAnno(int)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setAnno(int)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setAnno(int)
	 */
	public void setAnno(int anno) {
		this.anno = anno;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setCodigo(int)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setCodigo(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setCodigo(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getComentario()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getComentario()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getComentario()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getComentario()
	 */
	public String getComentario() {
		return comentario;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setComentario(java.lang
	 * .String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setComentario(java.lang
	 * .String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setComentario(java.lang
	 * .String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setComentario(java.lang
	 * .String)
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getMesCorteVersion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getMesCorteVersion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getMesCorteVersion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getMesCorteVersion()
	 */
	public Short getMesCorteVersion() {
		return mesCorteVersion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setMesCorteVersion(int)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setMesCorteVersion(java
	 * .lang.Short)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setMesCorteVersion(java
	 * .lang.Short)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setMesCorteVersion(java
	 * .lang.Short)
	 */
	public void setMesCorteVersion(Short mesCorteVersion) {
		this.mesCorteVersion = mesCorteVersion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getNecesidadComercial()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getNecesidadComercial()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getNecesidadComercial()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getNecesidadComercial()
	 */
	public Double getNecesidadComercial() {
		return necesidadComercial;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setNecesidadComercial(double
	 * )
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setNecesidadComercial(java
	 * .lang.Double)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setNecesidadComercial(java
	 * .lang.Double)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setNecesidadComercial(java
	 * .lang.Double)
	 */
	public void setNecesidadComercial(Double necesidadComercial) {
		this.necesidadComercial = necesidadComercial;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getProduccionAnual()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getProduccionAnual()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getProduccionAnual()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getProduccionAnual()
	 */
	public Double getProduccionAnual() {
		return produccionAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setProduccionAnual(double)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setProduccionAnual(java
	 * .lang.Double)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setProduccionAnual(java
	 * .lang.Double)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setProduccionAnual(java
	 * .lang.Double)
	 */
	public void setProduccionAnual(Double produccionAnual) {
		this.produccionAnual = produccionAnual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getVersion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getVersion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getVersion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getVersion()
	 */
	public String getVersion() {
		return version;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setVersion(java.lang.String
	 * )
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setVersion(java.lang.String
	 * )
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setVersion(java.lang.String
	 * )
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setVersion(java.lang.String
	 * )
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getLineaNegocio()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getLineaNegocio()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getLineaNegocio()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getLineaNegocio()
	 */
	public LineaNegocioBean getLineaNegocio() {
		return lineaNegocio;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setLineaNegocio(pe.com.
	 * pacasmayo.sgcp.bean.impl.LineaNegocioBeanImpl)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setLineaNegocio(pe.com.
	 * pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setLineaNegocio(pe.com.
	 * pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setLineaNegocio(pe.com.
	 * pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	public void setLineaNegocio(LineaNegocioBean lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getOrdenProduccion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getOrdenProduccion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getOrdenProduccion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getOrdenProduccion()
	 */
	public OrdenProduccionBean getOrdenProduccion() {
		return ordenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setOrdenProduccion(pe.com
	 * .pacasmayo.sgcp.bean.impl.OrdenProduccionBeanImpl)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setOrdenProduccion(pe.com
	 * .pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setOrdenProduccion(pe.com
	 * .pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setOrdenProduccion(pe.com
	 * .pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	public void setOrdenProduccion(OrdenProduccionBean ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanParada()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanParada()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanParada()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanParada()
	 */
	public PlanParadaBean getPlanParada() {
		return planParada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanParada(pe.com.pacasmayo
	 * .sgcp.bean.PlanParadaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanParada(pe.com.pacasmayo
	 * .sgcp.bean.PlanParadaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanParada(pe.com.pacasmayo
	 * .sgcp.bean.PlanParadaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanParada(pe.com.pacasmayo
	 * .sgcp.bean.PlanParadaBean)
	 */
	public void setPlanParada(PlanParadaBean planParada) {
		this.planParada = planParada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanNecesidad()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanNecesidad()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanNecesidad()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getPlanNecesidad()
	 */
	public PlanNecesidadBean getPlanNecesidad() {
		return planNecesidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanNecesidad(pe.com
	 * .pacasmayo.sgcp.bean.PlanNecesidadBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanNecesidad(pe.com
	 * .pacasmayo.sgcp.bean.PlanNecesidadBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanNecesidad(pe.com
	 * .pacasmayo.sgcp.bean.PlanNecesidadBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setPlanNecesidad(pe.com
	 * .pacasmayo.sgcp.bean.PlanNecesidadBean)
	 */
	public void setPlanNecesidad(PlanNecesidadBean planNecesidad) {
		this.planNecesidad = planNecesidad;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getEstadoPlan()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getEstadoPlan()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getEstadoPlan()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getEstadoPlan()
	 */
	public EstadoPlanBean getEstadoPlan() {
		return estadoPlan;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setEstadoPlan(pe.com.pacasmayo
	 * .sgcp.bean.EstadoPlanBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setEstadoPlan(pe.com.pacasmayo
	 * .sgcp.bean.EstadoPlanBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setEstadoPlan(pe.com.pacasmayo
	 * .sgcp.bean.EstadoPlanBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setEstadoPlan(pe.com.pacasmayo
	 * .sgcp.bean.EstadoPlanBean)
	 */
	public void setEstadoPlan(EstadoPlanBean estadoPlan) {
		this.estadoPlan = estadoPlan;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistra()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistra()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistra()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistra()
	 */
	public UsuarioBean getUsuarioRegistra() {
		return usuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioRegistra(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioRegistra(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioRegistra(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioRegistra(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioRegistra(UsuarioBean usuarioRegistra) {
		this.usuarioRegistra = usuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioAprueba()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioAprueba()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioAprueba()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioAprueba()
	 */
	public UsuarioBean getUsuarioAprueba() {
		return usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioAprueba(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioAprueba(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioAprueba(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioAprueba(pe.com
	 * .pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioAprueba(UsuarioBean usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getListaPlanComercializacion
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getListaPlanComercializacion
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getListaPlanComercializacion
	 * ()
	 */
	public List<PlanComercializacionBean> getListaPlanComercializacion() {
		return listaPlanComercializacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setListaPlanComercializacion
	 * (java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setListaPlanComercializacion
	 * (java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setListaPlanComercializacion
	 * (java.util.List)
	 */
	public void setListaPlanComercializacion(List<PlanComercializacionBean> listaPlanComercializacion) {
		this.listaPlanComercializacion = listaPlanComercializacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaAprueba()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaAprueba()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaAprueba()
	 */
	public String getFechaAprueba() {
		return fechaAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaAprueba(java.lang
	 * .String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaAprueba(java.lang
	 * .String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaAprueba(java.lang
	 * .String)
	 */
	public void setFechaAprueba(String fechaAprueba) {
		this.fechaAprueba = fechaAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaRegistra()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaRegistra()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getFechaRegistra()
	 */
	public String getFechaRegistra() {
		return fechaRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaRegistra(java.lang
	 * .String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaRegistra(java.lang
	 * .String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setFechaRegistra(java.lang
	 * .String)
	 */
	public void setFechaRegistra(String fechaRegistra) {
		this.fechaRegistra = fechaRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getListaCapacidadOperRegMen
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getListaCapacidadOperRegMen
	 * ()
	 */
	public List<CapacidadOperativaRegistroMensualBean> getListaCapacidadOperRegMen() {
		return listaCapacidadOperRegMen;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioApruebaConFecha()
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioApruebaConFecha()
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioApruebaConFecha()
	 * public String getUsuarioApruebaConFecha() { String valor = ""; if
	 * (usuarioAprueba != null) { valor = usuarioAprueba.getLogin(); if
	 * (fechaAprueba != null) valor = valor + " - " + fechaAprueba; } return
	 * valor; } (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistraConFecha
	 * () (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistraConFecha
	 * () (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistraConFecha
	 * () public String getUsuarioRegistraConFecha() { String valor = null; if
	 * (usuarioRegistra != null) { valor = usuarioRegistra.getLogin(); if
	 * (fechaRegistra != null) valor = valor + " - " + fechaRegistra; } return
	 * valor; }
	 */

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setListaCapacidadOperRegMen
	 * (java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setListaCapacidadOperRegMen
	 * (java.util.List)
	 */
	public void setListaCapacidadOperRegMen(List<CapacidadOperativaRegistroMensualBean> listaCapacidadOperRegMen) {
		this.listaCapacidadOperRegMen = listaCapacidadOperRegMen;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioApruebaConFecha
	 * (java.lang.String)
	 */
	public void setUsuarioApruebaConFecha(String usuarioApruebaConFecha) {
		this.usuarioApruebaConFecha = usuarioApruebaConFecha;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#setUsuarioRegistraConFecha
	 * (java.lang.String)
	 */
	public void setUsuarioRegistraConFecha(String usuarioRegistraConFecha) {
		this.usuarioRegistraConFecha = usuarioRegistraConFecha;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioApruebaConFecha()
	 */
	public String getUsuarioApruebaConFecha() {
		return usuarioApruebaConFecha;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlanAnualBean#getUsuarioRegistraConFecha
	 * ()
	 */
	public String getUsuarioRegistraConFecha() {
		return usuarioRegistraConFecha;
	}
}