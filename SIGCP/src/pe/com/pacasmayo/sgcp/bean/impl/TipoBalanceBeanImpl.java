package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.BalanceProductoBean;
import pe.com.pacasmayo.sgcp.bean.TipoBalanceBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TipoBalanceBeanImpl.java
 * Modificado: May 28, 2010 3:16:58 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class TipoBalanceBeanImpl extends EntidadBeanImpl implements TipoBalanceBean {

	private Long pkCodigoTipobalance;
	private String nombreTipobalance;
	private List<BalanceProductoBean> balanceproductos = new ArrayList<BalanceProductoBean>();

	public Long getPkCodigoTipobalance() {
		return pkCodigoTipobalance;
	}

	public void setPkCodigoTipobalance(Long pkCodigoTipobalance) {
		this.pkCodigoTipobalance = pkCodigoTipobalance;
	}

	public String getNombreTipobalance() {
		return nombreTipobalance;
	}

	public void setNombreTipobalance(String nombreTipobalance) {
		this.nombreTipobalance = nombreTipobalance;
	}

	public List<BalanceProductoBean> getBalanceproductos() {
		return balanceproductos;
	}

	public void setBalanceproductos(List<BalanceProductoBean> balanceproductos) {
		this.balanceproductos = balanceproductos;
	}

}
