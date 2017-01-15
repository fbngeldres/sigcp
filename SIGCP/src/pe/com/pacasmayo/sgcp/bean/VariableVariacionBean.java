package pe.com.pacasmayo.sgcp.bean;

/**
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) Archivo:
 * VariableVariacionBean.java Modificado: Jan 10, 2012 11:29:29 AM Autor: Luis
 * Robles Copyright (C) DBAccess, 2010. All rights reserved. Developed by:
 * DBAccess. http://www.dbaccess.com
 */

public interface VariableVariacionBean extends EntidadBean {

	public abstract void setPkCodigoVariablevariacion(Long pkCodigoVariablevariacion);

	public abstract Long getPkCodigoVariablevariacion();

	public abstract void setNombreVariablevariacion(String nombreVariablevariacion);

	public abstract String getNombreVariablevariacion();

	/**
	 * @return the produccion
	 */
	public abstract boolean isProduccion();

	/**
	 * @param produccion the produccion to set
	 */
	public abstract void setProduccion(boolean produccion);

}
