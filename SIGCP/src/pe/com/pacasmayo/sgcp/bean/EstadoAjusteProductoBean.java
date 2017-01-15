package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoAjusteProductoBean.java
 * Modificado: May 26, 2010 3:43:49 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface EstadoAjusteProductoBean extends EntidadBean {

	public abstract Long getPkCodigoEstadoajusteproducto();

	public abstract void setPkCodigoEstadoajusteproducto(Long pkCodigoEstadoajusteproducto);

	public abstract String getNombreEstadoajusteproducto();

	public abstract void setNombreEstadoajusteproducto(String nombreEstadoajusteproducto);

	public abstract List<AjusteProductoBean> getAjusteproductos();

	public abstract void setAjusteproductos(List<AjusteProductoBean> ajusteproductos);

}