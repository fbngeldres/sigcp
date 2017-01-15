package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TipoBalanceBean.java
 * Modificado: May 26, 2010 4:26:14 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface TipoBalanceBean extends EntidadBean {

	public abstract Long getPkCodigoTipobalance();

	public abstract void setPkCodigoTipobalance(Long pkCodigoTipobalance);

	public abstract String getNombreTipobalance();

	public abstract void setNombreTipobalance(String nombreTipobalance);

	public abstract List<BalanceProductoBean> getBalanceproductos();

	public abstract void setBalanceproductos(List<BalanceProductoBean> balanceproductos);

}