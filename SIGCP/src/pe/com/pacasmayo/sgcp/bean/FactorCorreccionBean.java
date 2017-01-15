package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: FactorCorreccionBean.java
 * Modificado: May 26, 2010 5:01:43 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface FactorCorreccionBean extends EntidadBean {

	public abstract Long getPkCodigoFactorcorreccion();

	public abstract void setPkCodigoFactorcorreccion(Long pkCodigoFactorcorreccion);

	public abstract String getNombreFactorcorreccion();

	public abstract void setNombreFactorcorreccion(String nombreFactorcorreccion);

	public abstract List<ProductoFactorCorreccionBean> getProductofactorcorreccions();

	public abstract void setProductofactorcorreccions(List<ProductoFactorCorreccionBean> productofactorcorreccions);

}