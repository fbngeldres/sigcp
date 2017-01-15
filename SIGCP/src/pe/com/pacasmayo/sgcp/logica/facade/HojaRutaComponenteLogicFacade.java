package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HojaRutaComponenteLogicFacade.java
 * Modificado: Feb 6, 2010 9:56:39 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface HojaRutaComponenteLogicFacade {

	/**
	 * @param codigoHojaRutaComponente
	 * @return
	 * @throws LogicaException
	 */
	public abstract HojaRutaComponenteBean obtenerHojaRutaComponente(Long codigoHojaRutaComponente) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaComponenteBean> obtenerHojasRutaComponentes() throws LogicaException;

	/**
	 * @param codigoHojaRuta
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaComponenteBean> obtenerHojasRutaComponentesPorCodigoHojaRuta(Long codigoHojaRuta)
			throws LogicaException;

	/**
	 * @param hojaRutaComponenteBean
	 * @throws LogicaException
	 */
	public abstract void insertarHojaRutaComponente(HojaRutaComponenteBean hojaRutaComponenteBean) throws LogicaException;

	/**
	 * @param hojaRutaComponenteListBean
	 * @throws LogicaException
	 */
	public abstract void insertarHojasRutaComponentes(List<HojaRutaComponenteBean> hojaRutaComponenteListBean)
			throws LogicaException;

	/**
	 * @param hojaRutaComponenteBean
	 * @throws LogicaException
	 */
	public abstract void eliminarHojaRutaComponente(HojaRutaComponenteBean hojaRutaComponenteBean) throws LogicaException;

	/**
	 * @param codigoHojaRuta
	 * @throws LogicaException
	 */
	public abstract void eliminarHojaRutaComponentePorCodigoHojaRuta(Long codigoHojaRuta) throws LogicaException;
}
