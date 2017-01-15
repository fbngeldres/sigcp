package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ActividadBean;
import pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.OperacionComponenteBean;
import pe.com.pacasmayo.sgcp.bean.OperacionRecursoBean;
import pe.com.pacasmayo.sgcp.bean.PlanCapacidadRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.TasaRealProduccionBean;
import pe.com.pacasmayo.sgcp.bean.TasaRealProduccionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.VariableOperacionBean;

public class OperacionBeanImpl implements OperacionBean {

	private Long codigo;
	private String nombre;
	private Long ordenEjecucion;
	private Double consumoEjecutado;
	private Double consumoPlanificado;
	private VariableOperacionBean variableOperacion;
	private ActividadBean actividad;
	private PuestoTrabajoBean puestoTrabajo;
	private HojaRutaBean hojaRuta;
	private Long porcentaje;
	private TasaRealProduccionRegistroMensualBean[] listaTasaRealProduccionRM;
	private CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRMDias;
	private CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRMTon;
	private PlanCapacidadRegistroMensualBean[] listaPlanCapacidadRMDias;
	private PlanCapacidadRegistroMensualBean[] listaPlanCapacidadRMHoras;
	private TasaRealProduccionBean tasaRealProduccion;
	private List<OperacionComponenteBean> listaOperacionComponentes;
	private List<OperacionRecursoBean> listaOperacionRecursos;

	public OperacionBeanImpl() {
		actividad = new ActividadBeanImpl();
		puestoTrabajo = new PuestoTrabajoBeanImpl();
		hojaRuta = new HojaRutaBeanImpl();
		listaOperacionComponentes = new ArrayList<OperacionComponenteBean>();
		listaOperacionRecursos = new ArrayList<OperacionRecursoBean>();
		listaTasaRealProduccionRM = new TasaRealProduccionRegistroMensualBeanImpl[12];
		listaCapacidadOperativaRMDias = new CapacidadOperativaRegistroMensualBeanImpl[12];
		listaCapacidadOperativaRMTon = new CapacidadOperativaRegistroMensualBeanImpl[12];
		listaPlanCapacidadRMDias = new PlanCapacidadRegistroMensualBeanImpl[12];
		listaPlanCapacidadRMHoras = new PlanCapacidadRegistroMensualBeanImpl[12];
		listaOperacionComponentes = new ArrayList<OperacionComponenteBean>();
		listaOperacionRecursos = new ArrayList<OperacionRecursoBean>();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * getListaCapacidadOperativaRMDias()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * getListaCapacidadOperativaRMDias()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * getListaCapacidadOperativaRMDias()
	 */
	public CapacidadOperativaRegistroMensualBean[] getListaCapacidadOperativaRMDias() {
		return listaCapacidadOperativaRMDias;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * setListaCapacidadOperativaRMDias
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * setListaCapacidadOperativaRMDias
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * setListaCapacidadOperativaRMDias
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	public void setListaCapacidadOperativaRMDias(CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRMDias) {
		this.listaCapacidadOperativaRMDias = listaCapacidadOperativaRMDias;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaCapacidadOperativaRMTon
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaCapacidadOperativaRMTon
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaCapacidadOperativaRMTon
	 * ()
	 */
	public CapacidadOperativaRegistroMensualBean[] getListaCapacidadOperativaRMTon() {
		return listaCapacidadOperativaRMTon;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaCapacidadOperativaRMTon
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaCapacidadOperativaRMTon
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaCapacidadOperativaRMTon
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	public void setListaCapacidadOperativaRMTon(CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRMTon) {
		this.listaCapacidadOperativaRMTon = listaCapacidadOperativaRMTon;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setCodigo(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setCodigo(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setCodigo(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getNombre()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getNombre()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getNombre()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getOrdenEjecucion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getOrdenEjecucion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getOrdenEjecucion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getOrdenEjecucion()
	 */
	public Long getOrdenEjecucion() {
		return ordenEjecucion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setOrdenEjecucion(java.
	 * lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setOrdenEjecucion(java.
	 * lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setOrdenEjecucion(java.
	 * lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setOrdenEjecucion(java.
	 * lang.Long)
	 */
	public void setOrdenEjecucion(Long ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getVariableOperacion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getVariableOperacion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getVariableOperacion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getVariableOperacion()
	 */
	public VariableOperacionBean getVariableOperacion() {
		return variableOperacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setVariableOperacion(pe
	 * .com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setVariableOperacion(pe
	 * .com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setVariableOperacion(pe
	 * .com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setVariableOperacion(pe
	 * .com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	public void setVariableOperacion(VariableOperacionBean variableOperacion) {
		this.variableOperacion = variableOperacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getActividad()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getActividad()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getActividad()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getActividad()
	 */
	public ActividadBean getActividad() {
		return actividad;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setActividad(pe.com.pacasmayo
	 * .sgcp.bean.ActividadBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setActividad(pe.com.pacasmayo
	 * .sgcp.bean.ActividadBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setActividad(pe.com.pacasmayo
	 * .sgcp.bean.ActividadBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setActividad(pe.com.pacasmayo
	 * .sgcp.bean.ActividadBean)
	 */
	public void setActividad(ActividadBean actividad) {
		this.actividad = actividad;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getPuestoTrabajo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getPuestoTrabajo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getPuestoTrabajo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getPuestoTrabajo()
	 */
	public PuestoTrabajoBean getPuestoTrabajo() {
		return puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setPuestoTrabajo(pe.com
	 * .pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setPuestoTrabajo(pe.com
	 * .pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setPuestoTrabajo(pe.com
	 * .pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setPuestoTrabajo(pe.com
	 * .pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getHojaRuta()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getHojaRuta()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getHojaRuta()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getHojaRuta()
	 */
	public HojaRutaBean getHojaRuta() {
		return hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setHojaRuta(pe.com.pacasmayo
	 * .sgcp.bean.HojaRutaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setHojaRuta(pe.com.pacasmayo
	 * .sgcp.bean.HojaRutaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setHojaRuta(pe.com.pacasmayo
	 * .sgcp.bean.HojaRutaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setHojaRuta(pe.com.pacasmayo
	 * .sgcp.bean.HojaRutaBean)
	 */
	public void setHojaRuta(HojaRutaBean hojaRuta) {
		this.hojaRuta = hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaTasaRealProduccionRM
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaTasaRealProduccionRM
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaTasaRealProduccionRM
	 * ()
	 */
	public TasaRealProduccionRegistroMensualBean[] getListaTasaRealProduccionRM() {
		return listaTasaRealProduccionRM;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaTasaRealProduccionRM
	 * (pe.com.pacasmayo.sgcp.bean.TasaRealProduccionRegistroMensualBean[])
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaTasaRealProduccionRM
	 * (pe.com.pacasmayo.sgcp.bean.TasaRealProduccionRegistroMensualBean[])
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaTasaRealProduccionRM
	 * (pe.com.pacasmayo.sgcp.bean.TasaRealProduccionRegistroMensualBean[])
	 */
	public void setListaTasaRealProduccionRM(TasaRealProduccionRegistroMensualBean[] listaTasaRealProduccionRM) {
		this.listaTasaRealProduccionRM = listaTasaRealProduccionRM;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaPlanCapacidadRM()
	 */
	public PlanCapacidadRegistroMensualBean[] getListaPlanCapacidadRMHoras() {
		return listaPlanCapacidadRMHoras;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaPlanCapacidadRM()
	 */
	public PlanCapacidadRegistroMensualBean[] getListaPlanCapacidadRMDias() {
		return listaPlanCapacidadRMDias;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaPlanCapacidadRM
	 * (pe.com.pacasmayo.sgcp.bean.PlanCapacidadRegistroMensualBean[])
	 */
	public void setListaPlanCapacidadRMHoras(PlanCapacidadRegistroMensualBean[] listaPlanCapacidadRMHoras) {
		this.listaPlanCapacidadRMHoras = listaPlanCapacidadRMHoras;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaPlanCapacidadRM
	 * (pe.com.pacasmayo.sgcp.bean.PlanCapacidadRegistroMensualBean[])
	 */
	public void setListaPlanCapacidadRMDias(PlanCapacidadRegistroMensualBean[] listaPlanCapacidadRMDias) {
		this.listaPlanCapacidadRMDias = listaPlanCapacidadRMDias;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getTasaRealProduccion()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getTasaRealProduccion()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getTasaRealProduccion()
	 */
	public TasaRealProduccionBean getTasaRealProduccion() {
		return tasaRealProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setTasaRealProduccion(pe
	 * .com.pacasmayo.sgcp.bean.TasaRealProduccionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setTasaRealProduccion(pe
	 * .com.pacasmayo.sgcp.bean.TasaRealProduccionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setTasaRealProduccion(pe
	 * .com.pacasmayo.sgcp.bean.TasaRealProduccionBean)
	 */
	public void setTasaRealProduccion(TasaRealProduccionBean tasaRealProduccion) {
		this.tasaRealProduccion = tasaRealProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaOperacionComponentes
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaOperacionComponentes
	 * ()
	 */
	public List<OperacionComponenteBean> getListaOperacionComponentes() {
		return listaOperacionComponentes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaOperacionComponentes
	 * (java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaOperacionComponentes
	 * (java.util.List)
	 */
	public void setListaOperacionComponentes(List<OperacionComponenteBean> listaOperacionComponentes) {
		this.listaOperacionComponentes = listaOperacionComponentes;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaOperacionRecursos()
	 */
	public List<OperacionRecursoBean> getListaOperacionRecursos() {
		return listaOperacionRecursos;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaOperacionRecursos
	 * (java.util.List)
	 */
	public void setListaOperacionRecursos(List<OperacionRecursoBean> listaOperacionRecursos) {
		this.listaOperacionRecursos = listaOperacionRecursos;
	}

	/**
	 * @return the porcentaje
	 */
	public Long getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(Long porcentaje) {
		this.porcentaje = porcentaje;
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