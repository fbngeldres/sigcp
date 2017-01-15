package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProductoGeneradoBean.java
 * Modificado: May 26, 2010 5:10:22 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ProductoGeneradoBean extends EntidadBean {

	public abstract Long getPkCodigoProductogenerado();

	public abstract void setPkCodigoProductogenerado(Long pkCodigoProductogenerado);

	public abstract TablaOperacionBean getTablaoperacion();

	public abstract void setTablaoperacion(TablaOperacionBean tablaoperacion);

	public abstract OrdenProduccionBean getOrdenproduccion();

	public abstract void setOrdenproduccion(OrdenProduccionBean ordenproduccion);

	public abstract Double getProduccionTmProductogenerado();

	public abstract void setProduccionTmProductogenerado(Double produccionTmProductogenerado);

	public abstract Double getHorasProductogenerado();

	public abstract void setHorasProductogenerado(Double horasProductogenerado);

	public abstract List<ConsumoPuestoTrabajoBean> getConsumopuestotrabajos();

	public abstract void setConsumopuestotrabajos(List<ConsumoPuestoTrabajoBean> consumopuestotrabajos);

}