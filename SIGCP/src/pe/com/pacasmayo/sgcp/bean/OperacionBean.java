package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: OperacionBean.java
 * Modificado: May 13, 2010 3:22:52 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OperacionBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * getListaCapacidadOperativaRMDias()
	 */
	public abstract CapacidadOperativaRegistroMensualBean[] getListaCapacidadOperativaRMDias();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#
	 * setListaCapacidadOperativaRMDias
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	public abstract void setListaCapacidadOperativaRMDias(CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRMDias);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaCapacidadOperativaRMTon
	 * ()
	 */
	public abstract CapacidadOperativaRegistroMensualBean[] getListaCapacidadOperativaRMTon();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaCapacidadOperativaRMTon
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	public abstract void setListaCapacidadOperativaRMTon(CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRMTon);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setCodigo(java.lang.Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getNombre()
	 */
	public abstract String getNombre();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setNombre(java.lang.String)
	 */
	public abstract void setNombre(String nombre);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getOrdenEjecucion()
	 */
	public abstract Long getOrdenEjecucion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setOrdenEjecucion(java.
	 * lang.Long)
	 */
	public abstract void setOrdenEjecucion(Long ordenEjecucion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getVariableOperacion()
	 */
	public abstract VariableOperacionBean getVariableOperacion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setVariableOperacion(pe
	 * .com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	public abstract void setVariableOperacion(VariableOperacionBean variableOperacion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getActividad()
	 */
	public abstract ActividadBean getActividad();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setActividad(pe.com.pacasmayo
	 * .sgcp.bean.ActividadBean)
	 */
	public abstract void setActividad(ActividadBean actividad);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getPuestoTrabajo()
	 */
	public abstract PuestoTrabajoBean getPuestoTrabajo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setPuestoTrabajo(pe.com
	 * .pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public abstract void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo);

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
	public abstract HojaRutaBean getHojaRuta();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setHojaRuta(pe.com.pacasmayo
	 * .sgcp.bean.HojaRutaBean)
	 */
	public abstract void setHojaRuta(HojaRutaBean hojaRuta);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaTasaRealProduccionRM
	 * ()
	 */
	public abstract TasaRealProduccionRegistroMensualBean[] getListaTasaRealProduccionRM();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaTasaRealProduccionRM
	 * (pe.com.pacasmayo.sgcp.bean.TasaRealProduccionRegistroMensualBean[])
	 */
	public abstract void setListaTasaRealProduccionRM(TasaRealProduccionRegistroMensualBean[] listaTasaRealProduccionRM);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaPlanCapacidadRM()
	 */
	public abstract PlanCapacidadRegistroMensualBean[] getListaPlanCapacidadRMDias();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaPlanCapacidadRM
	 * (pe.com.pacasmayo.sgcp.bean.PlanCapacidadRegistroMensualBean[])
	 */
	public abstract void setListaPlanCapacidadRMDias(PlanCapacidadRegistroMensualBean[] listaPlanCapacidadRMDias);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaPlanCapacidadRM()
	 */
	public abstract PlanCapacidadRegistroMensualBean[] getListaPlanCapacidadRMHoras();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaPlanCapacidadRM
	 * (pe.com.pacasmayo.sgcp.bean.PlanCapacidadRegistroMensualBean[])
	 */
	public abstract void setListaPlanCapacidadRMHoras(PlanCapacidadRegistroMensualBean[] listaPlanCapacidadRMHoras);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getTasaRealProduccion()
	 */
	public abstract TasaRealProduccionBean getTasaRealProduccion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setTasaRealProduccion(pe
	 * .com.pacasmayo.sgcp.bean.TasaRealProduccionBean)
	 */
	public abstract void setTasaRealProduccion(TasaRealProduccionBean tasaRealProduccion);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#getListaOperacionComponentes
	 * ()
	 */
	public abstract List<OperacionComponenteBean> getListaOperacionComponentes();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.OperacionBean#setListaOperacionComponentes
	 * (java.util.List)
	 */
	public abstract void setListaOperacionComponentes(List<OperacionComponenteBean> listaOperacionComponentes);

	public abstract List<OperacionRecursoBean> getListaOperacionRecursos();

	public abstract void setListaOperacionRecursos(List<OperacionRecursoBean> listaOperacionRecursos);

	/**
	 * @return the porcentaje
	 */
	public abstract Long getPorcentaje();

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public abstract void setPorcentaje(Long porcentaje);

	public Double getConsumoEjecutado();

	/**
	 * @param consumoEjecutado the consumoEjecutado to set
	 */
	public void setConsumoEjecutado(Double consumoEjecutado);

	/**
	 * @return the consumoPlanificado
	 */
	public Double getConsumoPlanificado();

	/**
	 * @param consumoPlanificado the consumoPlanificado to set
	 */
	public void setConsumoPlanificado(Double consumoPlanificado);
}
