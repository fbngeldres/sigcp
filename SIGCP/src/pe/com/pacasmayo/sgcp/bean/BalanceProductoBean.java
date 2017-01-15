package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: BalanceProductoBean.java
 * Modificado: May 26, 2010 4:24:49 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface BalanceProductoBean extends EntidadBean {

	public abstract Long getPkCodigoBalanceproducto();

	public abstract void setPkCodigoBalanceproducto(Long pkCodigoBalanceproducto);

	public abstract AjusteProductoBean getAjusteproducto();

	public abstract void setAjusteproducto(AjusteProductoBean ajusteproducto);

	public abstract ConceptoBean getConcepto();

	public abstract void setConcepto(ConceptoBean concepto);

	public abstract TipoBalanceBean getTipobalance();

	public abstract void setTipobalance(TipoBalanceBean tipobalance);

	public abstract Double getMontoBalanceproducto();

	public abstract void setMontoBalanceproducto(Double montoBalanceproducto);

}