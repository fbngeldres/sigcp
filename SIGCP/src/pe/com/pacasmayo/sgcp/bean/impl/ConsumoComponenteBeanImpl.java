package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteBean;
import pe.com.pacasmayo.sgcp.bean.TablaKardexBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoComponenteBeanImpl.java
 * Modificado: May 27, 2010 9:43:56 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ConsumoComponenteBeanImpl extends EntidadBeanImpl implements ConsumoComponenteBean {

	private Long pkCodigoConsumocomponente;
	private ComponenteBean componente;
	private TablaKardexBean tablakardex;
	private Double consumoConsumocomponente;

	public Long getPkCodigoConsumocomponente() {
		return pkCodigoConsumocomponente;
	}

	public void setPkCodigoConsumocomponente(Long pkCodigoConsumocomponente) {
		this.pkCodigoConsumocomponente = pkCodigoConsumocomponente;
	}

	public ComponenteBean getComponente() {
		return componente;
	}

	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

	public TablaKardexBean getTablakardex() {
		return tablakardex;
	}

	public void setTablakardex(TablaKardexBean tablakardex) {
		this.tablakardex = tablakardex;
	}

	public Double getConsumoConsumocomponente() {
		return consumoConsumocomponente;
	}

	public void setConsumoConsumocomponente(Double consumoConsumocomponente) {
		this.consumoConsumocomponente = consumoConsumocomponente;
	}
}
