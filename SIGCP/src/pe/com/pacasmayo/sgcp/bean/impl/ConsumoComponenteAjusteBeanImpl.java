package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponentePuestoTrabajoBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoComponenteAjusteBeanImpl.java
 * Modificado: May 27, 2010 8:57:45 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ConsumoComponenteAjusteBeanImpl extends EntidadBeanImpl implements ConsumoComponenteAjusteBean {

	private Long pkCodigoConsumocomponenteajus;
	private ComponenteBean componente;
	private Double tmConsumocomponenteajuste;
	private Double porcentajeConsumocomponenteaju;
	private Double tmRealConsumocomponenteajuste;
	private Double porcentajeRealConsumocomponen;
	private Double diferenciaConsumocomponenteaju;
	private List<ConsumoComponentePuestoTrabajoBean> consumocomponentepuestotrabajos = new ArrayList<ConsumoComponentePuestoTrabajoBean>();

	public Long getPkCodigoConsumocomponenteajus() {
		return pkCodigoConsumocomponenteajus;
	}

	public void setPkCodigoConsumocomponenteajus(Long pkCodigoConsumocomponenteajus) {
		this.pkCodigoConsumocomponenteajus = pkCodigoConsumocomponenteajus;
	}

	public ComponenteBean getComponente() {
		return componente;
	}

	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

	public Double getTmConsumocomponenteajuste() {
		return tmConsumocomponenteajuste;
	}

	public void setTmConsumocomponenteajuste(Double tmConsumocomponenteajuste) {
		this.tmConsumocomponenteajuste = tmConsumocomponenteajuste;
	}

	public Double getPorcentajeConsumocomponenteaju() {
		return porcentajeConsumocomponenteaju;
	}

	public void setPorcentajeConsumocomponenteaju(Double porcentajeConsumocomponenteaju) {
		this.porcentajeConsumocomponenteaju = porcentajeConsumocomponenteaju;
	}

	public Double getTmRealConsumocomponenteajuste() {
		return tmRealConsumocomponenteajuste;
	}

	public void setTmRealConsumocomponenteajuste(Double tmRealConsumocomponenteajuste) {
		this.tmRealConsumocomponenteajuste = tmRealConsumocomponenteajuste;
	}

	public Double getPorcentajeRealConsumocomponen() {
		return porcentajeRealConsumocomponen;
	}

	public void setPorcentajeRealConsumocomponen(Double porcentajeRealConsumocomponen) {
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
	}

	public Double getDiferenciaConsumocomponenteaju() {
		return diferenciaConsumocomponenteaju;
	}

	public void setDiferenciaConsumocomponenteaju(Double diferenciaConsumocomponenteaju) {
		this.diferenciaConsumocomponenteaju = diferenciaConsumocomponenteaju;
	}

	public List<ConsumoComponentePuestoTrabajoBean> getConsumocomponentepuestotrabajos() {
		return consumocomponentepuestotrabajos;
	}

	public void setConsumocomponentepuestotrabajos(List<ConsumoComponentePuestoTrabajoBean> consumocomponentepuestotrabajos) {
		this.consumocomponentepuestotrabajos = consumocomponentepuestotrabajos;
	}
}
