package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: SubReporteAjusteProduccionBean.java
 * Modificado: Jun 5, 2012 9:16:07 AM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class SubReporteAjusteProduccionBean {

	private String producto;
	private String componente;
	private String puestoTrabajo;
	private double ajuste;
	private double toneladaNotificado;
	private double toneladareal;
	private double porcentajeRealDNotificado;
	private double porcentajereal;
	private double costo;

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public double getAjuste() {
		return ajuste;
	}

	public void setAjuste(double ajuste) {
		this.ajuste = ajuste;
	}

	public double getToneladareal() {
		return toneladareal;
	}

	public void setToneladareal(double toneladareal) {
		this.toneladareal = toneladareal;
	}

	public double getPorcentajereal() {
		return porcentajereal;
	}

	public void setPorcentajereal(double porcentajereal) {
		this.porcentajereal = porcentajereal;
	}

	/**
	 * @return the toneladaNotificado
	 */
	public double getToneladaNotificado() {
		return toneladaNotificado;
	}

	/**
	 * @param toneladaNotificado the toneladaNotificado to set
	 */
	public void setToneladaNotificado(double toneladaNotificado) {
		this.toneladaNotificado = toneladaNotificado;
	}

	/**
	 * @return the porcentajeRealDNotificado
	 */
	public double getPorcentajeRealDNotificado() {
		return porcentajeRealDNotificado;
	}

	/**
	 * @param porcentajeRealDNotificado the porcentajeRealDNotificado to set
	 */
	public void setPorcentajeRealDNotificado(double porcentajeRealDNotificado) {
		this.porcentajeRealDNotificado = porcentajeRealDNotificado;
	}

	/**
	 * @return the costo
	 */
	public double getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return the puestoTrabajo
	 */
	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	/**
	 * @param puestoTrabajo the puestoTrabajo to set
	 */
	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

}
