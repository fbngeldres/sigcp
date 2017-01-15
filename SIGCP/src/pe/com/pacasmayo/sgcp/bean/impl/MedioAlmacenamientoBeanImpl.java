package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MedioAlmacenamientoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.TipoMedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.UbicacionBean;

public class MedioAlmacenamientoBeanImpl implements MedioAlmacenamientoBean {

	private Long codigo;
	private UbicacionBean ubicacion;
	private TipoMedioAlmacenamientoBean tipoMedioAlmacenamiento;
	private PuestoTrabajoBean puestoTrabajo;
	private ProduccionBean produccion;
	private String nombre;
	private Short numero;
	private Double capacidadMaxima;
	private Double capacidadMinima;
	private Double densidad;
	private Long numeroAlturas;
	private Double alturaEspecifica;
	private Double factorMetrosCubicos;
	private Double stockSeguridad;

	public MedioAlmacenamientoBeanImpl() {
		tipoMedioAlmacenamiento = new TipoMedioAlmacenamientoBeanImpl();
		ubicacion = new UbicacionBeanImpl();
		puestoTrabajo = new PuestoTrabajoBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getUbicacion()
	 */
	public UbicacionBean getUbicacion() {
		return ubicacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setUbicacion(
	 * pe.com.pacasmayo.sgcp.bean.UbicacionBean)
	 */
	public void setUbicacion(UbicacionBean ubicacion) {
		this.ubicacion = ubicacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#
	 * getTipoMedioAlmacenamiento()
	 */
	public TipoMedioAlmacenamientoBean getTipoMedioAlmacenamiento() {
		return tipoMedioAlmacenamiento;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#
	 * setTipoMedioAlmacenamiento
	 * (pe.com.pacasmayo.sgcp.bean.TipoMedioAlmacenamientoBean)
	 */
	public void setTipoMedioAlmacenamiento(TipoMedioAlmacenamientoBean tipoMedioAlmacenamiento) {
		this.tipoMedioAlmacenamiento = tipoMedioAlmacenamiento;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getPuestoTrabajo
	 * ()
	 */
	public PuestoTrabajoBean getPuestoTrabajo() {
		return puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getProduccion()
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setProduccion
	 * (pe.com.pacasmayo.sgcp.bean.ProduccionBean)
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setNombre(java
	 * .lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getNumero()
	 */
	public Short getNumero() {
		return numero;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setNumero(java
	 * .lang.Short)
	 */
	public void setNumero(Short numero) {
		this.numero = numero;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getCapacidadMaxima
	 * ()
	 */
	public Double getCapacidadMaxima() {
		return capacidadMaxima;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setCapacidadMaxima
	 * (java.lang.Double)
	 */
	public void setCapacidadMaxima(Double capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getCapacidadMinima
	 * ()
	 */
	public Double getCapacidadMinima() {
		return capacidadMinima;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setCapacidadMinima
	 * (java.lang.Double)
	 */
	public void setCapacidadMinima(Double capacidadMinima) {
		this.capacidadMinima = capacidadMinima;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getDensidad()
	 */
	public Double getDensidad() {
		return densidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setDensidad(java
	 * .lang.Double)
	 */
	public void setDensidad(Double densidad) {
		this.densidad = densidad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getNumeroAlturas
	 * ()
	 */
	public Long getNumeroAlturas() {
		return numeroAlturas;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setNumeroAlturas
	 * (java.lang.Long)
	 */
	public void setNumeroAlturas(Long numeroAlturas) {
		this.numeroAlturas = numeroAlturas;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getAlturaEspecifica
	 * ()
	 */
	public Double getAlturaEspecifica() {
		return alturaEspecifica;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setAlturaEspecifica
	 * (java.lang.Double)
	 */
	public void setAlturaEspecifica(Double alturaEspecifica) {
		this.alturaEspecifica = alturaEspecifica;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#
	 * getFactorMetrosCubicos()
	 */
	public Double getFactorMetrosCubicos() {
		return factorMetrosCubicos;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#
	 * setFactorMetrosCubicos(java.lang.Double)
	 */
	public void setFactorMetrosCubicos(Double factorMetrosCubicos) {
		this.factorMetrosCubicos = factorMetrosCubicos;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#getStockSeguridad
	 * ()
	 */
	public Double getStockSeguridad() {
		return stockSeguridad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MedioAlmacenamientoBean#setStockSeguridad
	 * (java.lang.Double)
	 */
	public void setStockSeguridad(Double stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}

}