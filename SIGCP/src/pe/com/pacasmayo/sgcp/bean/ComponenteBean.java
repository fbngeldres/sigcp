package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ComponenteBean.java
 * Modificado: Feb 22, 2010 2:50:47 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

public interface ComponenteBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract ProductoBean getProductoComponente();

	public abstract void setProductoComponente(ProductoBean productoComponente);

	public abstract List<FactorDosificacionBean> getFactorDosificacion();

	public abstract void setFactorDosificacion(List<FactorDosificacionBean> factorDosificacionBean);

}
