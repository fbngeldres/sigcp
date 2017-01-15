package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HojaRutaLogicFacade.java
 * Modificado: Dec 14, 2010 11:11:42 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface HojaRutaLogicFacade {

	/**
	 * @param codigoHojaRuta
	 * @return
	 * @throws LogicaException
	 */
	public abstract HojaRutaBean obtenerHojaRuta(Long codigoHojaRuta) throws LogicaException;

	/**
	 * @param codigoEstado
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaPorCodigoEstado(Long codigoEstado) throws LogicaException;

	/**
	 * @param codigoSCC
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaPorCodigoSCC(Long codigoSCC) throws LogicaException;

	/**
	 * @param nombreHojaRuta
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaPorNombre(String nombreHojaRuta) throws LogicaException;

	/**
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaPorCodigoProceso(Long codigoProceso) throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaPorCodigoUnidad(Long codigoUnidad) throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaSinFactorDosificacion() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaConFactorDosificacion() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRuta() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaParaConsulta() throws LogicaException;

	/**
	 * @param codigoHojaRuta
	 * @param anno
	 * @return
	 * @throws LogicaException
	 */
	public abstract HojaRutaBean obtenerHojaRutaPorAnno(Long codigoHojaRuta, Integer anno) throws LogicaException;

	/**
	 * Metodo para obtener una lista de UtilBean con los annos de una hoja de
	 * ruta
	 * 
	 * @param hojaRutaBean Hoja de Ruta
	 * @return lista de UtilBean con los annos de una hoja de ruta
	 */
	public abstract List<UtilBean> obtenerAnnosHojaRuta(HojaRutaBean hojaRutaBean);

	/**
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaPorProceso(Long codigoProceso) throws LogicaException;

	/**
	 * @param codigoProduccion
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaPorProduccion(Long codigoProduccion) throws LogicaException;

	/**
	 * @param nombreHojaRuta
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaNoInactivasPorNombre(String nombreHojaRuta) throws LogicaException;

	/**
	 * @param nombreHojaRuta
	 * @param codigoProduccion
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaNoInactivasPorNombreYProduccion(String nombreHojaRuta,
			Long codigoProduccion) throws LogicaException;

	/**
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaNoInactivasPorProducto(Long codigoProducto) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaConFactorDosificacion() throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * Método para insertar una hoja de ruta
	 */
	public abstract void insertarHojaRuta(HojaRutaBean hojaRutaBean, ProduccionBean produccionBean,
			List<String> listaCodigoProductoComponentes, List<OperacionBean> operaciones, Long mes, Long anio)
			throws LogicaException;

	/**
	 * @param hojaRutaBean
	 * @param listaCodigoProductoComponentes
	 * @param operaciones
	 * @throws LogicaException
	 */
	public abstract void actualizarHojaRuta(HojaRutaBean hojaRutaBean, List<String> listaCodigoProductoComponentes,
			List<OperacionBean> operaciones) throws LogicaException;

	/**
	 * @param hojaRutaBean
	 * @throws LogicaException
	 */
	public abstract void eliminarHojaRuta(HojaRutaBean hojaRutaBean) throws LogicaException;

	/**
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojasRutaActivaPorProducto(Long codigoProducto) throws LogicaException;

	/**
	 * @param lineaNegocio
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HojaRutaBean> obtenerHojaRutaActivaPorProcesoProductoDAO(Long lineaNegocio, Long codigoProducto)
			throws LogicaException;
}
