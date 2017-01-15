package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n)
 * Archivo: FactorKardexBean.java
 * Modificado: May 26, 2010 4:59:13 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface FactorKardexBean extends EntidadBean {

	public abstract Long getPkCodigoFactorkardex();

	public abstract void setPkCodigoFactorkardex(Long pkCodigoFactorkardex);

	public abstract ProductoFactorCorreccionBean getProductofactorcorreccion();

	public abstract void setProductofactorcorreccion(ProductoFactorCorreccionBean productofactorcorreccion);

	public abstract TablaKardexBean getTablakardex();

	public abstract void setTablakardex(TablaKardexBean tablakardex);

}