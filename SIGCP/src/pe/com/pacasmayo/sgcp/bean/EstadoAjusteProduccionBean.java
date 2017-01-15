package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoajusteProduccionBean.java
 * Modificado: May 26, 2010 4:10:14 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface EstadoAjusteProduccionBean extends EntidadBean {

	public abstract Long getPkCodigoEstadoajusteproduccio();

	public abstract void setPkCodigoEstadoajusteproduccio(Long pkCodigoEstadoajusteproduccio);

	public abstract String getNombreEstadoajusteproduccion();

	public abstract void setNombreEstadoajusteproduccion(String nombreEstadoajusteproduccion);

	public abstract List<AjusteProduccionBean> getAjusteproduccions();

	public abstract void setAjusteproduccions(List<AjusteProduccionBean> ajusteproduccions);

}