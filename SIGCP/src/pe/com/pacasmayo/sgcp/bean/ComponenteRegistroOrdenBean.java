package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ComponenteRegistroOrdenBean.java
 * Modificado: Oct 26, 2010 4:23:15 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ComponenteRegistroOrdenBean {

	public abstract ComponenteBean getComponente();

	public abstract void setComponente(ComponenteBean componente);

	public abstract UnidadMedidaBean getUnidadmedida();

	public abstract void setUnidadmedida(UnidadMedidaBean unidadmedida);

	public abstract Double getDosificacionPlanificada();

	public abstract void setDosificacionPlanificada(Double dosificacionPlanificada);

	public abstract Double getDosificacionEjecutada();

	public abstract void setDosificacionEjecutada(Double dosificacionEjecutada);

	public abstract Double getPorcentaje();

	public abstract void setPorcentaje(Double porcentaje);

	public abstract Double getProduccionPlanificada();

	public abstract void setProduccionPlanificada(Double produccionPlanificada);

	public abstract Double getProduccionEjecutada();

	public abstract void setProduccionEjecutada(Double produccionEjecutada);

}
