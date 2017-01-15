package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: VariableCalidadBean.java
 * Modificado: Dec 22, 2009 12:29:44 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface VariableCalidadBean extends EntidadBean {
	public abstract UnidadMedidaBean getUnidadmedida();

	public abstract void setUnidadmedida(UnidadMedidaBean unidadmedida);

	public abstract List<ProductoVariableCalidadBean> getProductoVariableCalidad();

	public abstract void setProductoVariableCalidad(List<ProductoVariableCalidadBean> productosVariableCalidad);
}