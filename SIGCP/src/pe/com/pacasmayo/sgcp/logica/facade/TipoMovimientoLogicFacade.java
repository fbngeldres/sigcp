package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMovimientoLogicFacade.java
 * Modificado: Jan 25, 2010 8:34:30 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.TipoMovimientoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface TipoMovimientoLogicFacade {

	/**
	 * @param codigo
	 * @return
	 * @throws LogicaException
	 */
	public abstract TipoMovimientoBean obtenerTipoMovimiento(Long codigo) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TipoMovimientoBean> obtenerTipoMovimientos() throws LogicaException;

	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TipoMovimientoBean> obtenerTipoMovimientoPorNombre(String nombre) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param tipoMovimientoBean
	 * @throws LogicaException
	 */
	public abstract void insertarTipoMovimiento(TipoMovimientoBean tipoMovimientoBean) throws LogicaException;

	/**
	 * @param codigoSAP
	 * @return
	 * @throws LogicaException
	 */
	public abstract TipoMovimientoBean obtenerTipoMovimientoPorCodigoSAP(String codigoSAP) throws LogicaException;

	/**
	 * @param codigoClasificacionTipoMovimiento
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TipoMovimientoBean> obtenerTipoMovimientoPorCodigoClasificacionTipoMovimiento(
			Long codigoClasificacionTipoMovimiento) throws LogicaException;

	/**
	 * @param nombreClasificacionTipoMovimiento
	 * @return
	 * @throws LogicaException
	 */
	public List<TipoMovimientoBean> obtenerTipoMovimientoPorNombreClasificacionTipoMovimiento(
			String nombreClasificacionTipoMovimiento) throws LogicaException;

	/**
	 * @param tipoMovimientoBean
	 * @throws LogicaException
	 */
	public abstract void actualizarTipoMovimiento(TipoMovimientoBean tipoMovimientoBean) throws LogicaException;

	/**
	 * @param tipoMovimientoBean
	 * @throws LogicaException
	 */
	public abstract void eliminarTipoMovimiento(TipoMovimientoBean tipoMovimientoBean) throws LogicaException;

	public abstract TipoMovimientoBean obtenerTipoMovimientoDAO(Long codigo) throws LogicaException;

}
