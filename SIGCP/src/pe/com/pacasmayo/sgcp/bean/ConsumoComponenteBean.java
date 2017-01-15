package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoComponenteBean.java
 * Modificado: May 26, 2010 5:04:33 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ConsumoComponenteBean extends EntidadBean {

	public abstract Long getPkCodigoConsumocomponente();

	public abstract void setPkCodigoConsumocomponente(Long pkCodigoConsumocomponente);

	public abstract ComponenteBean getComponente();

	public abstract void setComponente(ComponenteBean componente);

	public abstract TablaKardexBean getTablakardex();

	public abstract void setTablakardex(TablaKardexBean tablakardex);

	public abstract Double getConsumoConsumocomponente();

	public abstract void setConsumoConsumocomponente(Double consumoConsumocomponente);

}