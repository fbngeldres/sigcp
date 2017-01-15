package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProduccionPuestoTrabajoBean.java
 * Modificado: May 26, 2010 5:08:38 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ProduccionPuestoTrabajoBean extends EntidadBean {

	public abstract Long getPkCodigoProduccionpuestotraba();

	public abstract void setPkCodigoProduccionpuestotraba(Long pkCodigoProduccionpuestotraba);

	public abstract PuestoTrabajoBean getPuestotrabajo();

	public abstract void setPuestotrabajo(PuestoTrabajoBean puestotrabajo);

	public abstract ParteDiarioBean getPartediario();

	public abstract void setPartediario(ParteDiarioBean partediario);

	public abstract List<TablaOperacionBean> getTablaoperacions();

	public abstract void setTablaoperacions(List<TablaOperacionBean> tablaoperacions);

}