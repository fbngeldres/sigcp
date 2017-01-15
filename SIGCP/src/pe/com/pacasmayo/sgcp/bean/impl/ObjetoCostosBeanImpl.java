package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ObjetoCostoImpl.java
 * Modificado: May 25, 2010 1:00:13 PM 
 * Autor: Ricardo Marquez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;


import pe.com.pacasmayo.sgcp.bean.EstadoObjetoCostosBean;
import pe.com.pacasmayo.sgcp.bean.ObjetoCostosBean;
import pe.com.pacasmayo.sgcp.bean.TipoObjetoCostosBean;

public class ObjetoCostosBeanImpl implements ObjetoCostosBean {

	private Long codigo;
	private String descripcion;
	private String abreviatura;
	private Long valor_estadistico;
	private String codigoSap;
	private Long area;
	private Long estado;
	private Long tipo;
	private Date fechaIni;
	private Date fechaFin;

	private TipoObjetoCostosBean tipoBean;
	
	private EstadoObjetoCostosBean estadoBean;

	private Boolean flagparticionadObjetocosto;

	public ObjetoCostosBeanImpl() {

		codigo = null;
		descripcion = null;
		abreviatura = null;
		valor_estadistico = null;
		codigoSap = null;
		estado = null;
		fechaIni = null;
		fechaFin = null;

		tipoBean = null;
		
		estadoBean = null;
		flagparticionadObjetocosto = null;
	}

	public Boolean getFlagparticionadObjetocosto() {
		return flagparticionadObjetocosto;
	}

	public void setFlagparticionadObjetocosto(Boolean flagparticionadObjetocosto) {
		this.flagparticionadObjetocosto = flagparticionadObjetocosto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getCodigo()
	 */
	public Long getCodigo() {

		return this.codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setCodigo(java.lang.
	 * Long)
	 */
	public void setCodigo(Long cod) {

		this.codigo = cod;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getDescripcion()
	 */
	public String getDescripcion() {

		return this.descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setDescripcion(java.
	 * lang.String)
	 */
	public void setDescripcion(String des) {

		this.descripcion = des;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getAbreviatura()
	 */
	public String getAbreviatura() {

		return this.abreviatura;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setAbreviatura(java.
	 * lang.String)
	 */
	public void setAbreviatura(String abr) {

		this.abreviatura = abr;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getValor_estadistico()
	 */
	public Long getValor_estadistico() {

		return this.valor_estadistico;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setValor_estadistico
	 * (java.lang.Long)
	 */
	public void setValor_estadistico(Long val) {

		this.valor_estadistico = val;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getEstado()
	 */
	public Long getEstado() {

		return this.estado;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setEstado(java.lang.
	 * Long)
	 */
	public void setEstado(Long est) {

		this.estado = est;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getTipo()
	 */
	public Long getTipo() {

		return this.tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setTipo(java.lang.Long)
	 */
	public void setTipo(Long tipo) {

		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getFecha_ini()
	 */
	public Date getFechaIni() {

		return this.fechaIni;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setFecha_ini(java.util
	 * .Date)
	 */
	public void setFechaIni(Date ini) {

		this.fechaIni = ini;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getFecha_fin()
	 */
	public Date getFechaFin() {

		return this.fechaFin;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setFecha_fin(java.util
	 * .Date)
	 */
	public void setFechaFin(Date fin) {

		this.fechaFin = fin;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#getArea()
	 */
	public Long getArea() {
		return area;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ObjetoCostosBean#setArea(java.lang.Long)
	 */
	public void setArea(Long area) {
		this.area = area;
	}

	public String getCodigoSap() {
		return codigoSap;
	}

	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	public TipoObjetoCostosBean getTipoBean() {
		return tipoBean;
	}

	public void setTipoBean(TipoObjetoCostosBean tipoBean) {
		this.tipoBean = tipoBean;
	}



	public EstadoObjetoCostosBean getEstadoBean() {
		return estadoBean;
	}

	public void setEstadoBean(EstadoObjetoCostosBean estadoBean) {
		this.estadoBean = estadoBean;
	}

	public String getDescripcionCodigoSap() {
		return codigoSap + " - " + descripcion;

	}

}
