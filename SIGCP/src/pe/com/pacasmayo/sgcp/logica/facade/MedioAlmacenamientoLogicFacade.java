package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MedioAlmacenamientoLogicFacade.java
 * Modificado: Jan 25, 2010 8:31:28 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;

public interface MedioAlmacenamientoLogicFacade {

	/**
	 * @param codigoMedioAlmacenamiento
	 * @return
	 * @throws LogicaException
	 */
	public abstract MedioAlmacenamientoBean obtenerMedioAlmacenamiento(Long codigoMedioAlmacenamiento) throws LogicaException;

	/**
	 * @param codigoMedioAlmacenamiento
	 * @return
	 * @throws LogicaException
	 */
	public abstract Medioalmacenamiento obtenerMedioAlmacenamientoDataObject(Long codigoMedioAlmacenamiento)
			throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MedioAlmacenamientoBean> obtenerMediosAlmacenamiento() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Medioalmacenamiento> obtenerMediosAlmacenamientoDataObjects() throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorNombre(String nombre) throws LogicaException;

	/**
	 * @param codigoTipoMedioAlmac
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorTipoMedioAlmacenamiento(Long codigoTipoMedioAlmac)
			throws LogicaException;

	/**
	 * @param propiedades
	 * @return
	 * @throws LogicaException
	 */
	@SuppressWarnings("unchecked")
	public abstract List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientosPorPropiedades(Map propiedades)
			throws LogicaException;

	/**
	 * @param numero
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorNumero(Short numero) throws LogicaException;

	/**
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorPuestoTabajo(Long codigoPuestoTrabajo)
			throws LogicaException;

	/**
	 * @param codigoUbicacion
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorUbicacion(Long codigoUbicacion)
			throws LogicaException;

	/**
	 * @param medioAlmacenamientoBean
	 * @throws LogicaException
	 */
	public abstract void insertarMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamientoBean) throws LogicaException;

	/**
	 * @param medioAlmacenamientoBean
	 * @throws LogicaException
	 */
	public abstract void actualizarMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamientoBean) throws LogicaException;

	/**
	 * @param medioAlmacenamientoBean
	 * @throws LogicaException
	 */
	public abstract void eliminarMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamientoBean) throws LogicaException;

	/**
	 * @param codigoTipoMedioAlmac
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Medioalmacenamiento> obtenerMedioAlmacenamientoDTOPorTipoMedioYProceso(Long codigoTipoMedioAlmac,
			Long codigoProceso) throws LogicaException;

	public List<Medioalmacenamiento> obtenerMedioAlmacenamientoDTOPorTipoMedioYProcesoYProducto(Long codigoTipoMedioAlmac,
			Long codigoProceso, Long codigoProducto) throws LogicaException;

	/**
	 * Obtiene una lista de medios de almacenamiento segun el proceso al que
	 * pertenecen
	 * 
	 * @param codigoProceso
	 * @return List<Medioalmacenamiento>
	 * @throws LogicaException
	 */
	public abstract List<Medioalmacenamiento> obtenerMedioAlmacenamientoPorProceso(Long codigoProceso) throws LogicaException;
}