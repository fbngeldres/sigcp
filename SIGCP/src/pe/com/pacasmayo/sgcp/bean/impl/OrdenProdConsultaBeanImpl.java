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

import java.util.Date;

import pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProdConsultaBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

public class OrdenProdConsultaBeanImpl implements OrdenProdConsultaBean {

	private Long codigo;
	private EstadoOrdenProduccionBean estadoOrdenProduccion;
	private UsuarioBean usuarioRegistro;
	private UsuarioBean usuarioAprueba;
	private ProduccionBean produccion;
	private HojaRutaBean hojaRuta;
	private boolean esManual;
	private Date fechaAprobacion;
	private Date fechaRegistro;
	private int mes;
	private String numeroDocumento;
	private String numeroOrden;
	private Double produccionEjecutada;
	private Double produccionEstimada;
	private String proceso;
	private String usuarioR;
	private String usuarioA;

	private String anio_mes;
	private String unidad;
	private String lineaneg;
	private String producto;
	private Long codigoProducto;
	private Double porcentaje;
	private String division;
	private String sociedad;
	private String anio;
	private char tipo;

	public OrdenProdConsultaBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setCodigo(int)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setCodigo(java.
	 * lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#isEsManual()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#isEsManual()
	 */
	public boolean isEsManual() {
		return esManual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setEsManual(boolean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setEsManual(boolean
	 * )
	 */
	public void setEsManual(boolean esManual) {
		this.esManual = esManual;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getFechaAprobacion()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getFechaAprobacion
	 * ()
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
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setFechaAprobacion
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
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getFechaRegistro()
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
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setFechaRegistro
	 * (java.util.Date)
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getMes()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getMes()
	 */
	public int getMes() {
		return mes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setMes(java.lang.
	 * String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setMes(int)
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getNumeroDocumento()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getNumeroDocumento
	 * ()
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setNumeroDocumento
	 * (int)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setNumeroDocumento
	 * (java.lang.String)
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getNumeroOrden()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getNumeroOrden()
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
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setNumeroOrden(
	 * java.lang.String)
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProduccionEjecutada
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getProduccionEjecutada
	 * ()
	 */
	public Double getProduccionEjecutada() {
		return produccionEjecutada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProduccionEjecutada
	 * (int)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setProduccionEjecutada
	 * (java.lang.Double)
	 */
	public void setProduccionEjecutada(Double produccionEjecutada) {
		this.produccionEjecutada = produccionEjecutada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProduccionEstimada
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getProduccionEstimada
	 * ()
	 */
	public Double getProduccionEstimada() {
		return produccionEstimada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProduccionEstimada
	 * (double)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setProduccionEstimada
	 * (java.lang.Double)
	 */
	public void setProduccionEstimada(Double produccionEstimada) {
		this.produccionEstimada = produccionEstimada;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#getProceso()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getProceso()
	 */
	public String getProceso() {
		return proceso;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProduccionBean#setProceso(pe.com
	 * .pacasmayo.sgcp.bean.ProcesoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setProceso(java
	 * .lang.String)
	 */
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#
	 * getEstadoOrdenProduccion()
	 */
	public EstadoOrdenProduccionBean getEstadoOrdenProduccion() {
		return estadoOrdenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getHojaRuta()
	 */
	public HojaRutaBean getHojaRuta() {
		return hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getProduccion()
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getUsuarioAprueba()
	 */
	public UsuarioBean getUsuarioAprueba() {
		return usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getUsuarioRegistro
	 * ()
	 */
	public UsuarioBean getUsuarioRegistro() {
		return usuarioRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#
	 * setEstadoOrdenProduccion
	 * (pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean)
	 */
	public void setEstadoOrdenProduccion(EstadoOrdenProduccionBean estadoOrdenProduccion) {
		this.estadoOrdenProduccion = estadoOrdenProduccion;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setHojaRuta(pe.
	 * com.pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	public void setHojaRuta(HojaRutaBean hojaRuta) {
		this.hojaRuta = hojaRuta;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setProduccion(pe
	 * .com.pacasmayo.sgcp.bean.ProduccionBean)
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setUsuarioAprueba
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioAprueba(UsuarioBean usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setUsuarioRegistro
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioRegistro(UsuarioBean usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getAnio_mes()
	 */
	public String getAnio_mes() {
		return anio_mes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setAnio_mes(java
	 * .lang.String)
	 */
	public void setAnio_mes(String anio_mes) {
		this.anio_mes = anio_mes;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getUnidad()
	 */
	public String getUnidad() {
		return unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setUnidad(java.
	 * lang.String)
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getLineaneg()
	 */
	public String getLineaneg() {
		return lineaneg;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setLineaneg(java
	 * .lang.String)
	 */
	public void setLineaneg(String lineaneg) {
		this.lineaneg = lineaneg;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getProducto()
	 */
	public String getProducto() {
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setProducto(java
	 * .lang.String)
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getPorcentaje()
	 */
	public Double getPorcentaje() {
		return porcentaje;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setPorcentaje(java
	 * .lang.Double)
	 */
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getDivision()
	 */
	public String getDivision() {
		return division;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setDivision(java
	 * .lang.String)
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getSociedad()
	 */
	public String getSociedad() {
		return sociedad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setSociedad(java
	 * .lang.String)
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getAnio()
	 */
	public String getAnio() {
		return anio;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setAnio(java.lang
	 * .String)
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getUsuarioR()
	 */
	public String getUsuarioR() {
		return usuarioR;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setUsuarioR(java
	 * .lang.String)
	 */
	public void setUsuarioR(String usuarioR) {
		this.usuarioR = usuarioR;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getUsuarioA()
	 */
	public String getUsuarioA() {
		return usuarioA;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setUsuarioA(java
	 * .lang.String)
	 */
	public void setUsuarioA(String usuarioA) {
		this.usuarioA = usuarioA;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getTipo()
	 */
	public char getTipo() {
		return tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setTipo(char)
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#getCodigoProducto()
	 */
	public Long getCodigoProducto() {
		return codigoProducto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OrdenProdConsultaBean#setCodigoProducto
	 * (java.lang.Long)
	 */
	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

}