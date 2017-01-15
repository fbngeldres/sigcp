package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medicion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedicionDiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.StockDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MedicionLogicFacade.java
 * Modificado: May 7, 2010 2:49:29 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface MedicionLogicFacade {

	/**
	 * @param medicion
	 * @throws LogicaException
	 */
	public abstract void insertarMedicion(Medicion medicion) throws LogicaException;

	/**
	 * @param medicion
	 * @throws LogicaException
	 */
	public abstract void actualizarMedicion(Medicion medicion) throws LogicaException;

	/**
	 * @param medicion
	 * @throws LogicaException
	 */
	public abstract void eliminarMedicion(Medicion medicion) throws LogicaException;

	/**
	 * @param codigoProceso
	 * @param anno
	 * @param mes
	 * @param codigoEstadoRegistroMedicion
	 * @param codigoTipoMedioAlamacenamiento
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<StockDTO> obtenerStockMensualProductosPorProceso(Long codigoProceso, Integer anno, Short mes,
			Long codigoEstadoRegistroMedicion, Long codigoTipoMedioAlamacenamiento) throws LogicaException;

	/**
	 * @param codigoProceso
	 * @param fecha
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(Long codigoregistroMedicion)
			throws LogicaException;

	/**
	 * @param codigoProceso
	 * @param codigoTipoMedio
	 * @param fecha
	 * @param codigoEstado
	 * @param lineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(Long codigoProceso, Long codigoTipoMedio,
			Date fecha, Long codigoEstado, Long lineaNegocio) throws LogicaException;

	public abstract List<MedicionDiariaDTO> obtenerDetalleRegistroMedicion(Long codigoProceso, Long codigoProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException;

	public abstract Double obtenerCantidadAlmacenada(Long codigoSilo, double promedioAlturas, Double[] alturas)
			throws LogicaException;

	public abstract List<MedicionDiariaDTO> obtenerDetalleRegistroMedicionDAO(Long codigoProceso, Long pkCodigoProducto,
			Long codigoLineaNegocio, Integer anio, Short mes) throws LogicaException;

}