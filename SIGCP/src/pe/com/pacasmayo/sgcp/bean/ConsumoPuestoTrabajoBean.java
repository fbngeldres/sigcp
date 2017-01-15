package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoPuestoTrabajoBean.java
 * Modificado: May 26, 2010 5:11:56 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ConsumoPuestoTrabajoBean extends EntidadBean {

	public abstract Long getPkCodigoConsumopuestotrabajo();

	public abstract void setPkCodigoConsumopuestotrabajo(Long pkCodigoConsumopuestotrabajo);

	public abstract ProductoGeneradoBean getProductogenerado();

	public abstract void setProductogenerado(ProductoGeneradoBean productogenerado);

	public abstract ComponenteBean getComponente();

	public abstract void setComponente(ComponenteBean componente);

	public abstract Double getCantidadConsumopuestotrabajo();

	public abstract void setCantidadConsumopuestotrabajo(Double cantidadConsumopuestotrabajo);

}