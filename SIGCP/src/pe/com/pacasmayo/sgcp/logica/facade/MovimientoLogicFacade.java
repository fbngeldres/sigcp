package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.bean.MovimientoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: MovimientoLogicFacade.java
 * Modificado: May 26, 2010 8:08:03 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface MovimientoLogicFacade {

	/**
	 * @param codigoProducto
	 * @param fechaIni
	 * @param fechafin
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MovimientoBean> obtenerMovimientoPorProductoYRangoFechas(Long codigoProducto, Date fechaIni,
			Date fechafin, Long codigoMedAlman, Long codigoAlma) throws LogicaException;

	/**
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @param codigoTipoDocumento
	 * @param codigoAlmacen
	 * @param codigoUbicacion
	 * @param codigoProducto
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws LogicaException
	 */
	public List<MovimientoBean> obtenerMovimientosPorFiltros(Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio,
			Long codigoTipoDocumento, Long codigoAlmacen, Long codigoUbicacion, Long codigoProducto, Date fechaInicio,
			Date fechaFin) throws LogicaException;

	/**
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @param codigoTipoDocumento
	 * @param codigoAlmacenSalida
	 * @param codigoUbicacionSalida
	 * @param codigoProducto
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws LogicaException
	 */
	public List<MovimientoBean> obtenerMovimientosSalidaPorFiltros(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoTipoDocumento, Long codigoAlmacenSalida, Long codigoUbicacionSalida,
			Long codigoProducto, Date fechaInicio, Date fechaFin) throws LogicaException;

	public List<MovimientoBean> obtenerMovimientosSalidaPorFiltrosTV(Long codigoSociedad, Long codigoDivision,
			String codigoSAPProducto, Long codigoTipoDocumento, Short mes, Integer anio, boolean productoSinDiferenciar) throws LogicaException;

	/**
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @param codigoTipoDocumento
	 * @param codigoAlmacen
	 * @param codigoUbicacion
	 * @param codigoProducto
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws LogicaException
	 */
	public List<MovimientoBean> obtenerMovimientosTransferenciaPorFiltros(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoTipoDocumento, Long codigoAlmacen, Long codigoUbicacion, Long codigoProducto,
			Date fechaInicio, Date fechaFin) throws LogicaException;

	/**
	 * @param nombreTipoMovimiento
	 * @param fecha
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<MovimientoBean> obtenerMovimientosPorTipoFecha(String nombreTipoMovimiento, Date fecha)
			throws LogicaException;

	/**
	 * @param codigoLineNegocio
	 * @param codigoProducto
	 * @param fechaDiaria
	 * @return
	 * @throws LogicaException
	 */
	public Double obtenerMovimentoLogisticoProductoDiariaDAO(Long codigoLineNegocio, Long codigoProducto, Date fechaDiaria)
			throws LogicaException;

	/**
	 * @param codigoLineNegocio
	 * @param producto
	 * @param annio
	 * @param mes
	 * @return
	 * @throws LogicaException
	 */
	public Map<String, Double> obtenerMovimientoLogistico(Long codigoLineNegocio, ProductoBean producto, Integer annio, Short mes)
			throws LogicaException;
	
	
	/**
	 * 
	 * @param codigoLineNegocio
	 * @param producto
	 * @param annio
	 * @param mes
	 * @return
	 * @throws LogicaException
	 */
	public Double obtenerMovimentoLogisticoProductoDAO(Long codigoLineNegocio, ProductoBean producto, Integer annio, Short mes)
			throws LogicaException;

}
