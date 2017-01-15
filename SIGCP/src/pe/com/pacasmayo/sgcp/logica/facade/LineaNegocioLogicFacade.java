package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: LineaNegocioLogicFacade.java
 * Modificado: Dec 14, 2010 11:21:15 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface LineaNegocioLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineasNegocio() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineasNegocioBasico() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineasNegocioParaCombo() throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract LineaNegocioBean obtenerLineaNegocio(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract LineaNegocioBean obtenerLineaNegocioBasico(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineaNegocioPorCodigoUnidad(Long codigoUnidad) throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineaNegocioBasicoPorCodigoUnidad(Long codigoUnidad) throws LogicaException;

	/**
	 * @param codigoSCC
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineaNegocioPorCodigoSCC(Long codigoSCC) throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Lineanegocio> obtenerLineasNegocioDataObjects(Long codigoUnidad) throws LogicaException;

	/**
	 * @param nombreLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineaNegocioPorNombre(String nombreLineaNegocio) throws LogicaException;

	/**
	 * Método para obtener las lineas de negocio disponibles para el usuario
	 * 
	 * @param usuario
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<LineaNegocioBean> obtenerLineaNegocioPorUsuario(UsuarioBean usuario) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param lineaNegocioBean
	 * @throws LogicaException
	 */
	public abstract void insertarLineaNegocio(LineaNegocioBean lineaNegocioBean) throws LogicaException;

	/**
	 * @param lineaNegocioBean
	 * @throws LogicaException
	 */
	public abstract void actualizarLineaNegocio(LineaNegocioBean lineaNegocioBean) throws LogicaException;

	/**
	 * @param lineaNegocioBean
	 * @throws LogicaException
	 */
	public abstract void eliminarLineaNegocio(LineaNegocioBean lineaNegocioBean) throws LogicaException;

	public abstract LineaNegocioBean obtenerLineasNegocioSegunHojaruta(Long codigo)  throws LogicaException;

	
}
