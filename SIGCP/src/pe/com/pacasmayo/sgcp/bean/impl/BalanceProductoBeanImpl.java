package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.BalanceProductoBean;
import pe.com.pacasmayo.sgcp.bean.ConceptoBean;
import pe.com.pacasmayo.sgcp.bean.TipoBalanceBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: BalanceProductoBeanImpl.java
 * Modificado: May 27, 2010 8:51:57 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class BalanceProductoBeanImpl extends EntidadBeanImpl implements BalanceProductoBean {

	private Long pkCodigoBalanceproducto;
	private AjusteProductoBean ajusteproducto;
	private ConceptoBean concepto;
	private TipoBalanceBean tipobalance;
	private Double montoBalanceproducto;

	public Long getPkCodigoBalanceproducto() {
		return pkCodigoBalanceproducto;
	}

	public void setPkCodigoBalanceproducto(Long pkCodigoBalanceproducto) {
		this.pkCodigoBalanceproducto = pkCodigoBalanceproducto;
	}

	public AjusteProductoBean getAjusteproducto() {
		return ajusteproducto;
	}

	public void setAjusteproducto(AjusteProductoBean ajusteproducto) {
		this.ajusteproducto = ajusteproducto;
	}

	public ConceptoBean getConcepto() {
		return concepto;
	}

	public void setConcepto(ConceptoBean concepto) {
		this.concepto = concepto;
	}

	public TipoBalanceBean getTipobalance() {
		return tipobalance;
	}

	public void setTipobalance(TipoBalanceBean tipobalance) {
		this.tipobalance = tipobalance;
	}

	public Double getMontoBalanceproducto() {
		return montoBalanceproducto;
	}

	public void setMontoBalanceproducto(Double montoBalanceproducto) {
		this.montoBalanceproducto = montoBalanceproducto;
	}

}
