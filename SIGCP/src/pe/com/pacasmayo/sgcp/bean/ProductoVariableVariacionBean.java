package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ProductoVariableVariacionBean.java
 * Modificado: Jan 17, 2012 12:00:05 PM 
 * Autor: dracvs
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ProductoVariableVariacionBean extends EntidadBean {

	public abstract long getpkCodigoProductovariablevar();

	public abstract void setpkCodigoProductovariablevar(long codigoProdVarVab);

	public abstract double getValorProductoVariableVar();

	public abstract void setValorProductoVariableVar(double valorProdVariableVar);

	public abstract Date getFechaVariableVariacion();

	public abstract void setFechaVariableVariacion(Date fechaVariableVar);

	public abstract String getFechaVariableVariacionStr();

	public abstract void setFechaVariableVariacionStr(String fechaVariableVarStr);

	public abstract VariableVariacionBean getVariableVariacion();

	public abstract void setVariableVariacion(VariableVariacionBean varVab);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract ProcesoBean getProceso();

	public abstract void setProceso(ProcesoBean proceso);

	public abstract PuestoTrabajoBean getPuestoTrabajo();

	public abstract void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo);

	// PRUEBA

}
