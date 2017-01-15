package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TablaOperacionBean.java
 * Modificado: May 26, 2010 5:09:31 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface TablaOperacionBean extends EntidadBean {

	public abstract Long getPkCodigoTablaoperacion();

	public abstract void setPkCodigoTablaoperacion(Long pkCodigoTablaoperacion);

	public abstract ProduccionPuestoTrabajoBean getProduccionpuestotrabajo();

	public abstract void setProduccionpuestotrabajo(ProduccionPuestoTrabajoBean produccionpuestotrabajo);

	public abstract Date getFechaTablaoperacion();

	public abstract void setFechaTablaoperacion(Date fechaTablaoperacion);

	public abstract Double getTotalTmTablaoperacion();

	public abstract void setTotalTmTablaoperacion(Double totalTmTablaoperacion);

	public abstract Double getTotalHoraTablaoperacion();

	public abstract void setTotalHoraTablaoperacion(Double totalHoraTablaoperacion);

	public abstract List<ProductoGeneradoBean> getProductogenerados();

	public abstract void setProductogenerados(List<ProductoGeneradoBean> productogenerados);

}