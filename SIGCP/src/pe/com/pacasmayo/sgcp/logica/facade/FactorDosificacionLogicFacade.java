package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorDosificacionLogicFacade.java
 * Modificado: Jan 25, 2010 8:30:29 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface FactorDosificacionLogicFacade {

	/**
	 * @param codigoFactorDosificacion
	 * @return
	 * @throws LogicaException
	 */
	public abstract FactorDosificacionBean obtenerFactorDosificacion(Long codigoFactorDosificacion) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<FactorDosificacionBean> obtenerFactorDosificaciones() throws LogicaException;

	/**
	 * @param codigoHojaRuta
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<FactorDosificacionBean> obtenerFactorDosificacionPorHojaRuta(Long codigoHojaRuta) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<FactorDosificacionBean> obtenerFactorDosificacionesConRegistrosMensuales() throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param annio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<FactorDosificacionBean> obtenerFactorDosificacionesPorAnnio(Integer annio) throws LogicaException;

	public abstract List<FactorDosificacionBean> obtenerFactorDosificacionesPorNombreHojaRuta(String nombreHojaRuta)
			throws LogicaException;

	/**
	 * @param factorDosificacionBean
	 * @throws LogicaException
	 */
	public abstract void insertarFactorDosificacion(FactorDosificacionBean factorDosificacionBean) throws LogicaException;

	/**
	 * @param parameters
	 * @param anno
	 * @throws LogicaException
	 */
	public abstract void insertarFactorDosificacions(Map parameters, Integer anno) throws LogicaException;

	/**
	 * @param factorDosificacionBean
	 * @throws LogicaException
	 */
	public abstract void actualizarFactorDosificacion(FactorDosificacionBean factorDosificacionBean) throws LogicaException;

	/**
	 * @param parameters
	 * @param anno
	 * @throws LogicaException
	 */
	public abstract void actualizarFactorDosificacion(Map parameters, Integer anno) throws LogicaException;

	/**
	 * @param factorDosificacionBean
	 * @throws LogicaException
	 */
	public abstract void eliminarFactorDosificacion(FactorDosificacionBean factorDosificacionBean) throws LogicaException;

	/**
	 * @param listaHojaRutaComponenteBean
	 * @throws LogicaException
	 */
	public abstract void eliminarFactoresDosificacion(List<HojaRutaComponenteBean> listaHojaRutaComponenteBean)
			throws LogicaException;

	/**
	 * @param codigoHojaRuta
	 * @param annio
	 * @throws LogicaException
	 */
	public abstract void eliminarFactoresDosificacionPorHojaRutaYAnnio(Long codigoHojaRuta, Integer annio) throws LogicaException;

	/**
	 * @param factorDosificacion
	 * @param anno
	 * @return
	 */
	public abstract List<FactorDosificacionBean> filtrarFactorDosificacion(List<FactorDosificacionBean> factorDosificacion,
			Integer anno);

}
