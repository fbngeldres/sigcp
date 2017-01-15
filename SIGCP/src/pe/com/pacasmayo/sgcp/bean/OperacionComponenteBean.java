package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionComponenteBean.java
 * Modificado: May 8, 2010 10:49:12 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OperacionComponenteBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract OperacionBean getOperacion();

	public abstract void setOperacion(OperacionBean operacion);

	public abstract HojaRutaComponenteBean getHojaRutaComponente();

	public abstract void setHojaRutaComponente(HojaRutaComponenteBean hojaRutaComponente);

	public abstract Double getMaxFactor();

	public void setMaxFactor(Double maxFactor);

	public abstract Double getMinFactor();

	public abstract void setMinFactor(Double minFactor);
}
