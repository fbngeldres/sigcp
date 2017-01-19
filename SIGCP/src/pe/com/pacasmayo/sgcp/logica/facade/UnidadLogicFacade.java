package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadLogicFacade.java
 * Modificado: Dec 14, 2010 11:18:40 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface UnidadLogicFacade {

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract UnidadBean obtenerUnidad(Long codigoUnidad) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadBean> obtenerUnidades() throws LogicaException;

	public abstract List<UnidadBean> obtenerUnidadesBasico() throws LogicaException;

	public abstract List<UnidadBean> obtenerUnidadesParaCombo() throws LogicaException;

	/**
	 * @param nombreUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadBean> obtenerUnidadesPorNombre(String nombreUnidad) throws LogicaException;

	/**
	 * @param codigoSCC
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadBean> obtenerUnidadesPorCodigoSCC(Long codigoSCC) throws LogicaException;

	/**
	 * @param codigoSociedad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadBean> obtenerUnidadesPorCodigoSociedad(Long codigoSociedad) throws LogicaException;

	/**
	 * @param codigoSociedad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadBean> obtenerUnidadesPorCodigoSociedadBasico(Long codigoSociedad) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param unidadBean
	 * @throws LogicaException
	 */
	public abstract void insertarUnidad(UnidadBean unidadBean) throws LogicaException;

	/**
	 * @param unidadBean
	 * @throws LogicaException
	 */
	public abstract void actualizarUnidad(UnidadBean unidadBean) throws LogicaException;

	/**
	 * @param unidadBean
	 * @throws LogicaException
	 */
	public abstract void eliminarUnidad(UnidadBean unidadBean) throws LogicaException;

	/**
	 * @param codigoSociedad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Unidad> obtenerUnidadesDataObjects(Long codigoSociedad) throws LogicaException;

}
