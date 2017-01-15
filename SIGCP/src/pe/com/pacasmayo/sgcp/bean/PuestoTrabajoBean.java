package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PuestoTrabajoBean.java
 * Modificado: Dec 30, 2009 10:38:16 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

public interface PuestoTrabajoBean extends EntidadBean {

	/**
	 * @return the codigoSAP
	 */
	public abstract String getCodigoSAP();

	/**
	 * @param codigoSAP
	 *            the codigoSAP to set
	 */
	public abstract void setCodigoSAP(String codigoSAP);

	/**
	 * @return the codigoSCC
	 */
	public abstract Long getCodigoSCC();

	/**
	 * @param codigoSCC
	 *            the codigoSCC to set
	 */
	public abstract void setCodigoSCC(Long codigoSCC);

	/**
	 * @return the siglas
	 */
	public abstract String getSiglas();

	/**
	 * @param siglas
	 *            the siglas to set
	 */
	public abstract void setSiglas(String siglas);

	/**
	 * @return the estadoPuestoTrabajo
	 */
	public abstract EstadoPuestoTrabajoBean getEstadoPuestoTrabajo();

	/**
	 * @param estadoPuestoTrabajo
	 *            the estadoPuestoTrabajo to set
	 */
	public abstract void setEstadoPuestoTrabajo(
			EstadoPuestoTrabajoBean estadoPuestoTrabajo);

	/**
	 * @return the planCapacidad
	 */
	public abstract PlanCapacidadBean getPlanCapacidad();

	/**
	 * @param planCapacidad
	 *            the planCapacidad to set
	 */
	public abstract void setPlanCapacidad(PlanCapacidadBean planCapacidad);

	/**
	 * @return the tipoPuestoTrabajo
	 */
	public abstract TipoPuestoTrabajoBean getTipoPuestoTrabajo();

	/**
	 * @param tipoPuestoTrabajo
	 *            the tipoPuestoTrabajo to set
	 */
	public abstract void setTipoPuestoTrabajo(
			TipoPuestoTrabajoBean tipoPuestoTrabajo);

	/**
	 * @return the unidadMedida
	 */
	public abstract UnidadMedidaBean getUnidadMedida();

	/**
	 * @param produccionSemanal
	 *            the unidadMedida to set
	 */
	public abstract void setUnidadMedida(UnidadMedidaBean unidadMedida);
}