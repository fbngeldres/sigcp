package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RecursoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.RecursoBean;
import pe.com.pacasmayo.sgcp.bean.RecursoRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.TipoPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class RecursoBeanImpl extends VariableOperacionBeanImpl implements RecursoBean {

	private Long codigo;
	private String nombre;
	private UnidadMedidaBean unidadmedida;
	private TipoPuestoTrabajoBean tipopuesto;
	private PuestoTrabajoBean puestotrabajo;
	private Double consumoEjecutado;
	private Double consumoPlanificado;
	private RecursoRegistroMensualBean[] recursoRegistroMensual;

	public RecursoBeanImpl() {
		recursoRegistroMensual = new RecursoRegistroMensualBean[12];
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RecursoBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RecursoBean#setCodigo(Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RecursoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.RecursoBean#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UnidadMedidaBean getUnidadMedida() {
		return unidadmedida;
	}

	public void setUnidadMedida(UnidadMedidaBean unidadmed) {
		this.unidadmedida = unidadmed;
	}

	public PuestoTrabajoBean getPuestoTrabajo() {
		return puestotrabajo;
	}

	public void setPuestoTrabajo(PuestoTrabajoBean puestotrabajobean) {
		this.puestotrabajo = puestotrabajobean;
	}

	/**
	 * @return the recursoRegistroMensual
	 */
	public RecursoRegistroMensualBean[] getRecursoRegistroMensual() {
		return recursoRegistroMensual;
	}

	/**
	 * @param recursoRegistroMensual the recursoRegistroMensual to set
	 */
	public void setRecursoRegistroMensual(RecursoRegistroMensualBean[] recursoRegistroMensual) {
		this.recursoRegistroMensual = recursoRegistroMensual;
	}

	/**
	 * @return the tipopuesto
	 */
	public TipoPuestoTrabajoBean getTipopuesto() {
		return tipopuesto;
	}

	/**
	 * @param tipopuesto the tipopuesto to set
	 */
	public void setTipopuesto(TipoPuestoTrabajoBean tipopuesto) {
		this.tipopuesto = tipopuesto;
	}

	/**
	 * @return the consumoEjecutado
	 */
	public Double getConsumoEjecutado() {
		return consumoEjecutado;
	}

	/**
	 * @param consumoEjecutado the consumoEjecutado to set
	 */
	public void setConsumoEjecutado(Double consumoEjecutado) {
		this.consumoEjecutado = consumoEjecutado;
	}

	/**
	 * @return the consumoPlanificado
	 */
	public Double getConsumoPlanificado() {
		return consumoPlanificado;
	}

	/**
	 * @param consumoPlanificado the consumoPlanificado to set
	 */
	public void setConsumoPlanificado(Double consumoPlanificado) {
		this.consumoPlanificado = consumoPlanificado;
	}
}