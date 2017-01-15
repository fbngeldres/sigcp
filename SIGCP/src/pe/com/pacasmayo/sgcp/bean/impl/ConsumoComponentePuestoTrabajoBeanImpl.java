package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteAjusteBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponentePuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.MovimientoAjusteBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoProduccionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoComponentePuestoTrabajoBeanImpl.java
 * Modificado: May 27, 2010 9:45:27 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ConsumoComponentePuestoTrabajoBeanImpl extends EntidadBeanImpl implements ConsumoComponentePuestoTrabajoBean {

	private Long pkCodigoConsumocomponentepues;
	private ConsumoComponenteAjusteBean consumocomponenteajuste;
	private PuestoTrabajoProduccionBean puestotrabajoproduccion;
	private Double tmConsumocomponentepuestotraba;
	private Double porcentajeConsumocomponentepue;
	private Double tmRealConsumocomponentepuesto;
	private Double porcentajeRealConsumocomponen;
	private Double diferenciaConsumocomponentepue;
	private Double poderCalorificoConsumocompone;
	private String observacionConsumocomponentepu;
	private List<MovimientoAjusteBean> movimientoajustes = new ArrayList<MovimientoAjusteBean>();

	public Long getPkCodigoConsumocomponentepues() {
		return pkCodigoConsumocomponentepues;
	}

	public void setPkCodigoConsumocomponentepues(Long pkCodigoConsumocomponentepues) {
		this.pkCodigoConsumocomponentepues = pkCodigoConsumocomponentepues;
	}

	public ConsumoComponenteAjusteBean getConsumocomponenteajuste() {
		return consumocomponenteajuste;
	}

	public void setConsumocomponenteajuste(ConsumoComponenteAjusteBean consumocomponenteajuste) {
		this.consumocomponenteajuste = consumocomponenteajuste;
	}

	public PuestoTrabajoProduccionBean getPuestotrabajoproduccion() {
		return puestotrabajoproduccion;
	}

	public void setPuestotrabajoproduccion(PuestoTrabajoProduccionBean puestotrabajoproduccion) {
		this.puestotrabajoproduccion = puestotrabajoproduccion;
	}

	public Double getTmConsumocomponentepuestotraba() {
		return tmConsumocomponentepuestotraba;
	}

	public void setTmConsumocomponentepuestotraba(Double tmConsumocomponentepuestotraba) {
		this.tmConsumocomponentepuestotraba = tmConsumocomponentepuestotraba;
	}

	public Double getPorcentajeConsumocomponentepue() {
		return porcentajeConsumocomponentepue;
	}

	public void setPorcentajeConsumocomponentepue(Double porcentajeConsumocomponentepue) {
		this.porcentajeConsumocomponentepue = porcentajeConsumocomponentepue;
	}

	public Double getTmRealConsumocomponentepuesto() {
		return tmRealConsumocomponentepuesto;
	}

	public void setTmRealConsumocomponentepuesto(Double tmRealConsumocomponentepuesto) {
		this.tmRealConsumocomponentepuesto = tmRealConsumocomponentepuesto;
	}

	public Double getPorcentajeRealConsumocomponen() {
		return porcentajeRealConsumocomponen;
	}

	public void setPorcentajeRealConsumocomponen(Double porcentajeRealConsumocomponen) {
		this.porcentajeRealConsumocomponen = porcentajeRealConsumocomponen;
	}

	public Double getDiferenciaConsumocomponentepue() {
		return diferenciaConsumocomponentepue;
	}

	public void setDiferenciaConsumocomponentepue(Double diferenciaConsumocomponentepue) {
		this.diferenciaConsumocomponentepue = diferenciaConsumocomponentepue;
	}

	public Double getPoderCalorificoConsumocompone() {
		return poderCalorificoConsumocompone;
	}

	public void setPoderCalorificoConsumocompone(Double poderCalorificoConsumocompone) {
		this.poderCalorificoConsumocompone = poderCalorificoConsumocompone;
	}

	public String getObservacionConsumocomponentepu() {
		return observacionConsumocomponentepu;
	}

	public void setObservacionConsumocomponentepu(String observacionConsumocomponentepu) {
		this.observacionConsumocomponentepu = observacionConsumocomponentepu;
	}

	public List<MovimientoAjusteBean> getMovimientoajustes() {
		return movimientoajustes;
	}

	public void setMovimientoajustes(List<MovimientoAjusteBean> movimientoajustes) {
		this.movimientoajustes = movimientoajustes;
	}

}
