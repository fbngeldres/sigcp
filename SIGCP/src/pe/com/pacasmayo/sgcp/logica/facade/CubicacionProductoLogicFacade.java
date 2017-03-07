package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: CubicacionProductoLogicFacade.java
 * Modificado:
 * Autor:
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.bean.CubicacionProductoBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.CubicacionProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;

public interface CubicacionProductoLogicFacade {

	/**
	 * Obtiene lista de Cubicaciones de Producto dado un mapa de propiedades.
	 * 
	 * @param propiedades
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<CubicacionProductoBean> obtenerCubicionesProductoPorPropiedades(Map<String, Object> propiedades)
			throws LogicaException;

	/**
	 * Obtiene una cubicacion de un producto para su consulta dado su id.
	 * 
	 * @param codigoCubicacionProducto Id de cubicación
	 * @return
	 * @throws LogicaException
	 */
	public abstract CubicacionProductoDTO obtenerCubicacionProducto(Long codigoCubicacionProducto) throws LogicaException;

	/**
	 * Aprueba una Cubicación dado su identificador
	 * 
	 * @param codigoCubicacionProducto
	 * @throws LogicaException
	 */
	public void aprobarCubicacion(Long codigoCubicacionProducto) throws LogicaException;

	/**
	 * Anula una Cubicación dado su identificador
	 * 
	 * @param codigoCubicacionProducto
	 * @throws LogicaException
	 */
	public void anularCubicacion(Long codigoCubicacionProducto) throws LogicaException;

	/**
	 * Registra una cubicación de producto
	 * 
	 * @param listaCubicaciones
	 * @param codigoUsuario
	 * @throws LogicaException
	 */
	public abstract void registrarCubicacion(List<TablaCubicacionDTO> listaCubicaciones, Long codigoUsuario)
			throws LogicaException;

	/**
	 * Modifica una cubicación de producto
	 * 
	 * @param listaCubicaciones
	 * @param codigoUsuario
	 * @throws LogicaException
	 */
	public abstract void modificarCubicacion(Long codigoCubicacionProducto, List<TablaCubicacionDTO> listaCubicaciones,
			Long codigoUsuario) throws LogicaException;

	/**
	 * Valida si existe un registro cubicacionproducto para los datos pasados
	 * como parametro
	 * 
	 * @param codigoLineaNegocio long
	 * @param codigoProducto long
	 * @param codigoProceso long
	 * @param fechaCubicacion Date
	 * @return true si el registro existe, false en caso contrario
	 * @throws LogicaException
	 */
	public abstract Boolean validarSiExisteCubicacionProducto(long codigoLineaNegocio, long codigoProducto, long codigoProceso,
			Date fechaCubicacion) throws LogicaException;

	/**
	 * Genera el Reporte de Cubicaciones en EXCEL - PDF
	 * 
	 * @throws LogicaException
	 */
	public abstract ByteArrayOutputStream generarReporteCubicacion(Integer anio, Short mes, Long division, Long sociedad,
			Long unidad, String ruta) throws LogicaException;

	/**
	 * revierte una Cubicación dado su identificador
	 * 
	 * @param codigoCubicacionProducto
	 * @throws LogicaException
	 */
	public abstract void revertirCubicacion(long parseLong) throws LogicaException;

	/**
	 * valida si una lista Cubicaciones se puede revertir
	 * 
	 * @param codigoCubicacionProducto
	 * @throws LogicaException
	 */
	public abstract Boolean validarCubicaciones(String[] codigosCubicaciones) throws LogicaException;
}
