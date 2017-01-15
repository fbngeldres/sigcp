package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RecursoRegistroMensualBean.java
 * Modificado: Feb 25, 2010 7:50:01 PM 
 * Autor: Ana Rosa
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RecursoRegistroMensualBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract PlanAnualBean getPlanAnual();

	public abstract void setPlanAnualBean(PlanAnualBean plananualbean);

	public abstract RecursoBean getRecurso();

	public abstract void setRecursoBean(RecursoBean recursobean);

	public abstract OperacionBean getOperacionBean();

	public abstract void setOperacionBean(OperacionBean operacionbean);

	public abstract Short getMesRecursoregistromensual();

	public abstract void setMesRecursoregistromensual(Short mesrecursoregistromensual);

	public abstract Integer getAnnoRecursoregistromensual();

	public abstract void setAnnoRecursoregistromensual(Integer annoRecursoregistromensual);

	public abstract Double getCantidadRecursoregistromensual();

	public abstract void setCantidadRecursoregistromensual(Double cantidadRecursoregistromensual);
}
