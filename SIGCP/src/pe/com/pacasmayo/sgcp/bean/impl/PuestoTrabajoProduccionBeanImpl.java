package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoComponentePuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoProduccionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PuestoTrabajoProduccionBeanImpl.java
 * Modificado: May 27, 2010 10:32:38 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class PuestoTrabajoProduccionBeanImpl extends EntidadBeanImpl implements PuestoTrabajoProduccionBean {

	private Long pkCodigoPuestotrabajoproducci;
	private AjusteProductoBean ajusteproducto;
	private PuestoTrabajoBean puestotrabajo;
	private Double tmPuestotrabajoproduccion;
	private Double hrPuestotrabajoproduccion;
	private Double tmphPuestotrabajoproduccion;
	private Double hrAjustePuestotrabajoproducci;
	private Double tmAjustePuestotrabajoproducci;
	private Double tmRealPuestotrabajoproduccion;
	private Double hrRealPuestotrabajoproduccion;
	private Double tmphRealPuestotrabajoproducci;
	private Double kcalPuestotrabajoproduccion;
	private Double kcalRealPuestotrabajoproducci;

	private Double carprodPuestotrabajoproduccion;
	private Double carcalentPuestotrabajoproduccion;
	private Double carprodRealPuestotrabajoproduccion;
	private Double carcalentRealPuestotrabajoproduccion;

	private Double bunkprodPuestotrabajoproduccion;
	private Double bunkcalentPuestotrabajoproduccion;
	private Double bunkprodRealPuestotrabajoproduccion;
	private Double bunkcalentRealPuestotrabajoproduccion;

	private List<ConsumoComponentePuestoTrabajoBean> consumocomponentepuestotrabajos = new ArrayList<ConsumoComponentePuestoTrabajoBean>();

	public Long getPkCodigoPuestotrabajoproducci() {
		return pkCodigoPuestotrabajoproducci;
	}

	public void setPkCodigoPuestotrabajoproducci(Long pkCodigoPuestotrabajoproducci) {
		this.pkCodigoPuestotrabajoproducci = pkCodigoPuestotrabajoproducci;
	}

	public AjusteProductoBean getAjusteproducto() {
		return ajusteproducto;
	}

	public void setAjusteproducto(AjusteProductoBean ajusteproducto) {
		this.ajusteproducto = ajusteproducto;
	}

	public PuestoTrabajoBean getPuestotrabajo() {
		return puestotrabajo;
	}

	public void setPuestotrabajo(PuestoTrabajoBean puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Double getTmPuestotrabajoproduccion() {
		return tmPuestotrabajoproduccion;
	}

	public void setTmPuestotrabajoproduccion(Double tmPuestotrabajoproduccion) {
		this.tmPuestotrabajoproduccion = tmPuestotrabajoproduccion;
	}

	public Double getHrPuestotrabajoproduccion() {
		return hrPuestotrabajoproduccion;
	}

	public void setHrPuestotrabajoproduccion(Double hrPuestotrabajoproduccion) {
		this.hrPuestotrabajoproduccion = hrPuestotrabajoproduccion;
	}

	public Double getTmphPuestotrabajoproduccion() {
		return tmphPuestotrabajoproduccion;
	}

	public void setTmphPuestotrabajoproduccion(Double tmphPuestotrabajoproduccion) {
		this.tmphPuestotrabajoproduccion = tmphPuestotrabajoproduccion;
	}

	public Double getHrAjustePuestotrabajoproducci() {
		return hrAjustePuestotrabajoproducci;
	}

	public void setHrAjustePuestotrabajoproducci(Double hrAjustePuestotrabajoproducci) {
		this.hrAjustePuestotrabajoproducci = hrAjustePuestotrabajoproducci;
	}

	public Double getTmAjustePuestotrabajoproducci() {
		return tmAjustePuestotrabajoproducci;
	}

	public void setTmAjustePuestotrabajoproducci(Double tmAjustePuestotrabajoproducci) {
		this.tmAjustePuestotrabajoproducci = tmAjustePuestotrabajoproducci;
	}

	public Double getTmRealPuestotrabajoproduccion() {
		return tmRealPuestotrabajoproduccion;
	}

	public void setTmRealPuestotrabajoproduccion(Double tmRealPuestotrabajoproduccion) {
		this.tmRealPuestotrabajoproduccion = tmRealPuestotrabajoproduccion;
	}

	public Double getHrRealPuestotrabajoproduccion() {
		return hrRealPuestotrabajoproduccion;
	}

	public void setHrRealPuestotrabajoproduccion(Double hrRealPuestotrabajoproduccion) {
		this.hrRealPuestotrabajoproduccion = hrRealPuestotrabajoproduccion;
	}

	public Double getTmphRealPuestotrabajoproducci() {
		return tmphRealPuestotrabajoproducci;
	}

	public void setTmphRealPuestotrabajoproducci(Double tmphRealPuestotrabajoproducci) {
		this.tmphRealPuestotrabajoproducci = tmphRealPuestotrabajoproducci;
	}

	public Double getKcalPuestotrabajoproduccion() {
		return kcalPuestotrabajoproduccion;
	}

	public void setKcalPuestotrabajoproduccion(Double kcalPuestotrabajoproduccion) {
		this.kcalPuestotrabajoproduccion = kcalPuestotrabajoproduccion;
	}

	public Double getKcalRealPuestotrabajoproducci() {
		return kcalRealPuestotrabajoproducci;
	}

	public void setKcalRealPuestotrabajoproducci(Double kcalRealPuestotrabajoproducci) {
		this.kcalRealPuestotrabajoproducci = kcalRealPuestotrabajoproducci;
	}

	public List<ConsumoComponentePuestoTrabajoBean> getConsumocomponentepuestotrabajos() {
		return consumocomponentepuestotrabajos;
	}

	public void setConsumocomponentepuestotrabajos(List<ConsumoComponentePuestoTrabajoBean> consumocomponentepuestotrabajos) {
		this.consumocomponentepuestotrabajos = consumocomponentepuestotrabajos;
	}

	public Double getCarProdPuestotrabajoproduccion() {
		return carprodPuestotrabajoproduccion;
	}

	public void setCarProdPuestotrabajoproduccion(Double carProdPuestotrabajoproduccion) {
		this.carprodPuestotrabajoproduccion = carProdPuestotrabajoproduccion;
	}

	public Double getCarCalentPuestotrabajoproduccion() {
		return carcalentPuestotrabajoproduccion;
	}

	public void setCarCalentPuestotrabajoproduccion(Double carCalentPuestotrabajoproduccion) {
		this.carcalentPuestotrabajoproduccion = carCalentPuestotrabajoproduccion;
	}

	public Double getCarProdRealPuestotrabajoproduccion() {
		return carprodRealPuestotrabajoproduccion;
	}

	public void setCarProdRealPuestotrabajoproduccion(Double carProdRealPuestotrabajoproduccion) {
		this.carprodRealPuestotrabajoproduccion = carProdRealPuestotrabajoproduccion;
	}

	public Double getCarCalentRealPuestotrabajoproduccion() {
		return carcalentRealPuestotrabajoproduccion;
	}

	public void setCarCalentRealPuestotrabajoproduccion(Double carCalentRealPuestotrabajoproduccion) {
		this.carcalentRealPuestotrabajoproduccion = carCalentRealPuestotrabajoproduccion;
	}

	public Double getBunkCalentPuestotrabajoproduccion() {
		return bunkcalentPuestotrabajoproduccion;
	}

	public Double getBunkCalentRealPuestotrabajoproduccion() {
		return bunkcalentRealPuestotrabajoproduccion;
	}

	public Double getBunkProdPuestotrabajoproduccion() {
		return bunkprodPuestotrabajoproduccion;
	}

	public Double getBunkProdRealPuestotrabajoproduccion() {
		return bunkprodRealPuestotrabajoproduccion;
	}

	public void setBunkCalentPuestotrabajoproduccion(Double bunkCalentPuestotrabajoproduccion) {
		this.bunkcalentPuestotrabajoproduccion = bunkCalentPuestotrabajoproduccion;
	}

	public void setBunkCalentRealPuestotrabajoproduccion(Double bunkCalentRealPuestotrabajoproduccion) {
		this.bunkcalentRealPuestotrabajoproduccion = bunkCalentRealPuestotrabajoproduccion;
	}

	public void setBunkProdPuestotrabajoproduccion(Double bunkProdPuestotrabajoproduccion) {
		this.bunkprodPuestotrabajoproduccion = bunkProdPuestotrabajoproduccion;
	}

	public void setBunkProdRealPuestotrabajoproduccion(Double bunkProdRealPuestotrabajoproduccion) {
		this.bunkprodRealPuestotrabajoproduccion = bunkProdRealPuestotrabajoproduccion;
	}
}
