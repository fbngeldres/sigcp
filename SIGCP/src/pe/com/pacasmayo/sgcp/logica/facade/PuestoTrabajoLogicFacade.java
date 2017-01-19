package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: PuestoTrabajoLogicFacade.java
 * Modificado: Jan 25, 2010 8:33:47 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PuestoTrabajoDTO;

public interface PuestoTrabajoLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajo() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Puestotrabajo> obtenerPuestosTrabajoDataObjects() throws LogicaException;

	/**
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Puestotrabajo> obtenerPuestosTrabajoDataObjectsPorProducto(Long codigoProducto) throws LogicaException;

	/**
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Puestotrabajo> obtenerPuestosTrabajoDTOPorProceso(Long codigoProceso) throws LogicaException;

	/**
	 * @param procesoCodigo
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorProceso(Long procesoCodigo) throws LogicaException;

	/**
	 * @param lineaCodigo
	 * @return
	 * @throws LogicaException
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorLineaNegoco(Long lineaCodigo) throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorUnidad(Long codigoUnidad) throws LogicaException;

	/**
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public abstract PuestoTrabajoBean obtenerPuestoTrabajo(Long codigoPuestoTrabajo) throws LogicaException;

	/**
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public abstract Puestotrabajo obtenerPuestoTrabajoDataObject(Long codigoPuestoTrabajo) throws LogicaException;

	/**
	 * @param codigoSCC
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoSCC(Long codigoSCC) throws LogicaException;

	/**
	 * @param codigoSAP
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoSAP(String codigoSAP) throws LogicaException;

	/**
	 * @param nombrePuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorNombre(String nombrePuestoTrabajo) throws LogicaException;

	/**
	 * @param siglas
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorSiglas(String siglas) throws LogicaException;

	/**
	 * @param codigoTipoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoTipoPuestoTrabajo(Long codigoTipoPuestoTrabajo)
			throws LogicaException;

	/**
	 * @param codigoCentroCostos
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoCentroCostos(Long codigoCentroCostos)
			throws LogicaException;

	/**
	 * @param codigoEstadoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorEstado(Long codigoEstadoPuestoTrabajo) throws LogicaException;

	/**
	 * @param codigoUnidadMedida
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoUnidadMedida(Long codigoUnidadMedida)
			throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param puestoTrabajoBean
	 * @throws LogicaException
	 */
	public abstract void insertarPuestoTrabajo(PuestoTrabajoBean puestoTrabajoBean) throws LogicaException;

	/**
	 * @param puestoTrabajoBean
	 * @throws LogicaException
	 */
	public abstract void actualizarPuestoTrabajo(PuestoTrabajoBean puestoTrabajoBean) throws LogicaException;

	/**
	 * @param puestoTrabajoBean
	 * @throws LogicaException
	 */
	public abstract void eliminarPuestoTrabajo(PuestoTrabajoBean puestoTrabajoBean) throws LogicaException;

	/**
	 * @param codigoTableroControl
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajoPorTableroControl(Long codigoTableroControl)
			throws LogicaException;

	/**
	 * @param codigoTableroControl
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Puestotrabajo> obtenerPuestosTrabajoDTOPorTableroControl(Long codigoTableroControl)
			throws LogicaException;

	/**
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public PuestoTrabajoDTO obtenerPuestoTrabajoDTO(Long codigoPuestoTrabajo) throws LogicaException;
}
