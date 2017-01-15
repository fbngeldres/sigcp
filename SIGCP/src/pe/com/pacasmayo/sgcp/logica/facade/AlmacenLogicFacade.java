package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AlmacenLogicFacade.java
 * Modificado: Jan 25, 2010 8:26:27 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AlmacenBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface AlmacenLogicFacade {

	/**
	 * @param codigoAlmacen
	 * @return
	 * @throws LogicaException
	 */
	public abstract AlmacenBean obtenerAlmacen(Long codigoAlmacen) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<AlmacenBean> obtenerAlmacenes() throws LogicaException;

	/**
	 * @param codigoSAP
	 * @return
	 * @throws LogicaException
	 */
	public abstract AlmacenBean obtenerAlmacenPorCodigoSAP(String codigoSAP) throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<AlmacenBean> obtenerAlmacenPorCodigoUnidad(Long codigoUnidad) throws LogicaException;

	/**
	 * @param codigoTipoMovimiento
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<AlmacenBean> obtenerAlmacenPorCodigoTipoMovimiento(Long codigoTipoMovimiento) throws LogicaException;

	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<AlmacenBean> obtenerAlmacenPorNombre(String nombre) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param almacenBean
	 * @throws LogicaException
	 */
	public abstract void insertarAlmacen(AlmacenBean almacenBean) throws LogicaException;

	/**
	 * @param almacenBean
	 * @throws LogicaException
	 */
	public abstract void actualizarAlmacen(AlmacenBean almacenBean) throws LogicaException;

	/**
	 * @param almacenBean
	 * @throws LogicaException
	 */
	public abstract void eliminarAlmacen(AlmacenBean almacenBean) throws LogicaException;

}
