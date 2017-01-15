package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TablaKardexLogicFacade.java
 * Modificado:
 * Autor:
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.List;
import java.util.Map;


import pe.com.pacasmayo.sgcp.bean.TablaKardexBean;
import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.ReporteGestionStockProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ConsumoCarbonesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroDetalleCarbonesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroKardexParteDiarioDTO;

public interface TablaKardexLogicFacade {

	/**
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param codigoEstadoParteDiario
	 * @param codigoEstadoParteDiario2
	 * @param anno
	 * @param mes
	 * @return
	 * @throws LogicaException
	 */
	public abstract Map<String, List<RegistroKardexParteDiarioDTO>> obtenerRegistrosKardexParteDiario(Long codigoProceso,
			Long codigoProducto, Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException;

	/**
	 * Obtiene dos objetos tablaKardex pa un periodo de fechas, devuelve un
	 * arreglo de tamaño 2, donde el indice 0 contiene el kardex para la fecha
	 * de inicio del periodo y el indice 1 contiene el kardex para la fecha que
	 * finaliza el periodo
	 * 
	 * @param fechaInicioDate Date
	 * @param fechaFinDate Date
	 * @param codigoProducto Long
	 * @param codigoProceso Long
	 * @param codigoLineaNeg Long
	 * @return TablaKardexBean[]
	 * @throws LogicaException Si falla la operacion
	 */
	public abstract TablaKardexBean[] obtenerKardexMPporPeriodo(Date fechaInicioDate, Date fechaFinDate, Long codigoProducto,
			Long codigoProceso, Long codigoLineaNeg, Long codigoMedioAlmacen, Long codigoAlmacen) throws LogicaException;

	/**
	 * Obtiene una lista con el detalle del conssumo de los carbon que componen
	 * los mix en el clinker
	 * 
	 * @param consumoCarbonesDtos List<ConsumoCarbonesDTO>
	 * @param codigoLineaNegocio Long
	 * @param anno Integer
	 * @param mes Short
	 * @return List<RegistroDetalleCarbonesDTO>
	 */
	public abstract List<RegistroDetalleCarbonesDTO> obtenerDetalleConsumoCarbonEnClinker(
			List<ConsumoCarbonesDTO> consumoCarbonesDtos, Long codigoLineaNegocio, Integer anno, Short mes)
			throws LogicaException;

	/**
	 * Este metodo es utilizado para obtener la lista de bean's para el reporte
	 * de Gestion de Stock
	 * 
	 * @param mes
	 * @param anio
	 * @return
	 * @throws LogicaException
	 */
	public abstract ReporteGestionStockProduccionBean obtenerStockReporte(Short mes, Integer anio, Long unidad)
			throws LogicaException;

	public abstract void generarKardexDiarioSI(Long valorLineaNegocio, Integer valorAno, Short valorMes, Integer valorDia)
			throws LogicaException;


	public Tablakardex obtenerKrdexHVSegunFecha(List<Tablakardex> kardexsClkHV, Date fechaTablakardex) throws LogicaException;

}
