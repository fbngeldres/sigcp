package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RecursoBean.java
 * Modificado: Dec 22, 2009 12:46:33 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RecursoBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

	/**
	 * @return the unidadmedida
	 */
	public abstract UnidadMedidaBean getUnidadMedida();

	/**
	 * @param unidadmedida the unidadmedida to set
	 */
	public abstract void setUnidadMedida(UnidadMedidaBean unidadmedida);

	/**
	 * @return the tipopuesto
	 */
	public TipoPuestoTrabajoBean getTipopuesto();

	/**
	 * @param tipopuesto the tipopuesto to set
	 */
	public void setTipopuesto(TipoPuestoTrabajoBean tipopuesto);

	/**
	 * @return the puestotrabajo
	 */
	public PuestoTrabajoBean getPuestoTrabajo();

	/**
	 * @param puestoTrabajo the puestoTrabajo to set
	 */
	public void setPuestoTrabajo(PuestoTrabajoBean puestotrabajobean);

	/**
	 * @return the recursoregistromensual
	 */
	public RecursoRegistroMensualBean[] getRecursoRegistroMensual();

	/**
	 * @return the consumoEjecutado
	 */
	public Double getConsumoEjecutado();

	/**
	 * @param consumoEjecutado the consumoEjecutado to set
	 */
	public void setConsumoEjecutado(Double consumoEjecutado);

	/**
	 * @return the consumoEjecutado
	 */
	public Double getConsumoPlanificado();

	/**
	 * @param consumoEjecutado the consumoEjecutado to set
	 */
	public void setConsumoPlanificado(Double consumoPlanificado);

	public void setRecursoRegistroMensual(RecursoRegistroMensualBean[] recursoRegistroMensualBean);
}
