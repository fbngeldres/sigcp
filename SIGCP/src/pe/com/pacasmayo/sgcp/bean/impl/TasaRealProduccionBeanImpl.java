package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TasaRealProduccionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.TasaRealProduccionBean;
import pe.com.pacasmayo.sgcp.bean.TasaRealProduccionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class TasaRealProduccionBeanImpl extends VariableOperacionBeanImpl implements TasaRealProduccionBean {

	private Long codigo;
	private UnidadMedidaBean unidadmedida;
	private PuestoTrabajoBean puestotrabajo;
	private ProduccionBean produccion;
	private double maximo;
	private double minimo;
	private TasaRealProduccionRegistroMensualBean[] tasaRealProduccionRegistroMensual;
	private String proyeccion;

	public TasaRealProduccionBeanImpl() {
		tasaRealProduccionRegistroMensual = new TasaRealProduccionRegistroMensualBeanImpl[12];
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TasaRealProduccionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TasaRealProduccionBean#setCodigo(Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the unidadmedida
	 */
	public UnidadMedidaBean getUnidadmedida() {
		return unidadmedida;
	}

	/**
	 * @param unidadmedida the unidadmedida to set
	 */
	public void setUnidadmedida(UnidadMedidaBean unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	/**
	 * @return the puestotrabajo
	 */
	public PuestoTrabajoBean getPuestotrabajo() {
		return puestotrabajo;
	}

	/**
	 * @param puestotrabajo the puestotrabajo to set
	 */
	public void setPuestotrabajo(PuestoTrabajoBean puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	/**
	 * @return the produccion
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/**
	 * @param produccion the produccion to set
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	public double getMaximo() {
		return maximo;
	}

	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}

	public double getMinimo() {
		return minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public TasaRealProduccionRegistroMensualBean[] getTasaRealProduccionRegistroMensual() {
		return tasaRealProduccionRegistroMensual;
	}

	public void setTasaRealProduccionRegistroMensual(TasaRealProduccionRegistroMensualBean[] tasaRealProduccionRegistroMensual) {
		this.tasaRealProduccionRegistroMensual = tasaRealProduccionRegistroMensual;
	}

	public String getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(String proyeccion) {
		this.proyeccion = proyeccion;
	}

}