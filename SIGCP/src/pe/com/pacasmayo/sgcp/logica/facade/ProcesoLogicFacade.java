package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProcesoLogicFacade.java
 * Modificado: Dec 14, 2010 11:16:04 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ProcesoLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesos() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosBasico() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosParaCombo() throws LogicaException;

	/**
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract ProcesoBean obtenerProceso(Long codigoProceso) throws LogicaException;

	/**
	 * @param codigoSCC
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorCodigoSCC(Long codigoSCC) throws LogicaException;

	/**
	 * @param nombreProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorNombre(String nombreProceso) throws LogicaException;

	/**
	 * @param ordenEjecucion
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorOrdenEjecucion(Short ordenEjecucion) throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosBasicosPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @param codigoTipoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorCodigoTipoProducto(Long codigoTipoProducto) throws LogicaException;

	/**
	 * @param orden
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorOrdenLineaNegocio(short orden, Long codigoLineaNegocio)
			throws LogicaException;

	/**
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract Proceso obtenerProcesoDataObject(Long codigoProceso) throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Proceso> obtenerProcesosDataObjects(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract ProcesoDTO obtenerProcesoDTO(Long codigoProceso) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param procesoBean
	 * @throws LogicaException
	 */
	public abstract void insertarProceso(ProcesoBean procesoBean) throws LogicaException;

	/**
	 * @param procesoBean
	 * @throws LogicaException
	 */
	public abstract void actualizarProceso(ProcesoBean procesoBean) throws LogicaException;

	/**
	 * @param procesoBean
	 * @throws LogicaException
	 */
	public abstract void eliminarProceso(ProcesoBean procesoBean) throws LogicaException;

	/**
	 * @param codigoLinea
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract Proceso obtenerProcesoPorcodigoLineaYCodigoProducto(Long codigoLinea, Long codigoProducto)
			throws LogicaException;

	public abstract List<ProcesoBean> obtenerProcesosPorCodigoUnidad(Long valorUnidad) throws LogicaException;

}
