package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccion.java
 * Modificado: Nov 23, 2009 9:44:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ComponenteRegistroOrdenBean;
import pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.RecursoBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

public class OrdenProduccionBeanImpl implements OrdenProduccionBean {

	private Long codigo;
	private EstadoOrdenProduccionBean estadoOrdenProduccion;
	private UsuarioBean usuarioRegistro;
	private UsuarioBean usuarioAprueba;
	private ProduccionBean produccion;
	private HojaRutaBean hojaRuta;
	private boolean esManual;
	private int mes;
	private String numeroOrden;
	private String numeroDocumento;
	private Double produccionEstimada;
	private Double produccionEjecutada;
	private Date fechaAprobacion;
	private Date fechaRegistro;
	private List<OperacionBean> listaOperacionOrdenProduccionBean = new ArrayList<OperacionBean>();
	private List<ComponenteRegistroOrdenBean> listaComponenteOrdenProduccion = new ArrayList<ComponenteRegistroOrdenBean>();
	private List<RecursoBean> listaRecursoOrdenProduccion = new ArrayList<RecursoBean>();

	public OrdenProduccionBeanImpl() {
		estadoOrdenProduccion = new EstadoOrdenProduccionBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getEstadoOrdenProduccion
	 * ()
	 */
	public EstadoOrdenProduccionBean getEstadoOrdenProduccion() {
		return estadoOrdenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setEstadoOrdenProduccion
	 * (pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean)
	 */
	public void setEstadoOrdenProduccion(EstadoOrdenProduccionBean estadoOrdenProduccion) {
		this.estadoOrdenProduccion = estadoOrdenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getUsuarioRegistro()
	 */
	public UsuarioBean getUsuarioRegistro() {
		return usuarioRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setUsuarioRegistro
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioRegistro(UsuarioBean usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getUsuarioAprueba()
	 */
	public UsuarioBean getUsuarioAprueba() {
		return usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setUsuarioAprueba
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioAprueba(UsuarioBean usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProduccion()
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProduccion(pe.
	 * com.pacasmayo.sgcp.bean.ProduccionBean)
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getHojaRuta()
	 */
	public HojaRutaBean getHojaRuta() {
		return hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setHojaRuta(pe.com
	 * .pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	public void setHojaRuta(HojaRutaBean hojaRuta) {
		this.hojaRuta = hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#isEsManual()
	 */
	public boolean isEsManual() {
		return esManual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setEsManual(boolean)
	 */
	public void setEsManual(boolean esManual) {
		this.esManual = esManual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getMes()
	 */
	public int getMes() {
		return mes;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setMes(int)
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getNumeroOrden()
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setNumeroOrden(java
	 * .lang.String)
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getNumeroDocumento()
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setNumeroDocumento
	 * (java.lang.String)
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProduccionEstimada
	 * ()
	 */
	public Double getProduccionEstimada() {
		return produccionEstimada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProduccionEstimada
	 * (java.lang.Double)
	 */
	public void setProduccionEstimada(Double produccionEstimada) {
		this.produccionEstimada = produccionEstimada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProduccionEjecutada
	 * ()
	 */
	public Double getProduccionEjecutada() {
		return produccionEjecutada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProduccionEjecutada
	 * (java.lang.Double)
	 */
	public void setProduccionEjecutada(Double produccionEjecutada) {
		this.produccionEjecutada = produccionEjecutada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getFechaAprobacion()
	 */
	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setFechaAprobacion
	 * (java.util.Date)
	 */
	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getFechaRegistro()
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setFechaRegistro(
	 * java.util.Date)
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#
	 * getListaOperacionOrdenProduccionBean()
	 */
	public List<OperacionBean> getListaOperacionOrdenProduccionBean() {
		return listaOperacionOrdenProduccionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#
	 * setListaOperacionOrdenProduccionBean(java.util.List)
	 */
	public void setListaOperacionOrdenProduccionBean(List<OperacionBean> listaOperacionOrdenProduccionBean) {
		this.listaOperacionOrdenProduccionBean = listaOperacionOrdenProduccionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#
	 * getListaComponenteOrdenProduccion()
	 */
	public List<ComponenteRegistroOrdenBean> getListaComponenteOrdenProduccion() {
		return listaComponenteOrdenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#
	 * setListaComponenteOrdenProduccion(java.util.List)
	 */
	public void setListaComponenteOrdenProduccion(List<ComponenteRegistroOrdenBean> listaComponenteOrdenProduccion) {
		this.listaComponenteOrdenProduccion = listaComponenteOrdenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#
	 * getListaRecursoOrdenProduccion()
	 */
	public List<RecursoBean> getListaRecursoOrdenProduccion() {
		return listaRecursoOrdenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#
	 * setListaRecursoOrdenProduccion(java.util.List)
	 */
	public void setListaRecursoOrdenProduccion(List<RecursoBean> listaRecursoOrdenProduccion) {
		this.listaRecursoOrdenProduccion = listaRecursoOrdenProduccion;
	}

}