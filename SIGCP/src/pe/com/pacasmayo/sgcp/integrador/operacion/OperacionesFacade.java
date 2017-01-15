package pe.com.pacasmayo.sgcp.integrador.operacion;

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.IntegracionException;
import pe.com.pacasmayo.sgcp.integrador.excepciones.IntegradorExcepcion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionesFacade.java
 * Modificado: Mar 11, 2011 2:56:19 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OperacionesFacade {

	/**
	 * Obtiene variales de calidad registradas en el sistema SAC de los ingresos
	 * de materias primas
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idProceso
	 * @param idProducto
	 * @return double
	 * @throws IntegradorExcepcion si la consulta falla
	 */
	public abstract Double obtenerVariableCalidadMpSac(Date fechaInicio, Long idProceso, Long idProducto, String variableCalDesc)
			throws IntegradorExcepcion;

	/**
	 * Obtiene variales de calidad registradas en el sistema SAC por puesto de
	 * trabajo
	 * 
	 * @param fecha
	 * @param codigoProcesoScc
	 * @param codigoProductoScc
	 * @param codigoPuestotrabajo
	 * @param variableCalDesc
	 * @return
	 * @throws IntegracionException
	 */
	public abstract double obtenerVariableCalidadSacPorPuestoTrabajo(Date fecha, Long codigoProcesoScc, Long codigoProductoScc,
			Long codigoPuestotrabajo, String variableCalDesc) throws IntegracionException;

	// Agregado por John Vara

	public abstract List getCantidadEnsayos(String GrupoEnsayo, Integer anio, Short mes, Long miarea, Long idProducto,
			Long idProceso, Long idPuestoTrabajo) throws IntegradorExcepcion;

	/**
	 * Obtiene el promedio de los vaslores de una variable de calidad del sac
	 * entre dos fechas
	 * 
	 * @param fechaFinal Date
	 * @param codigoProcesoScc Long
	 * @param codigoProductoScc Long
	 * @param variableCalDesc String
	 * @return Double
	 * @throws IntegradorExcepcion
	 */
	public Double obtenerPromedioVarCalidadMes(Date fechaFinal, Long codigoProcesoScc, Long codigoProductoScc,
			String variableCalDesc) throws IntegradorExcepcion;

	/**
	 * Jordan Torres "Reporte de Datos de Calidad"
	 * 
	 * @param fechaFinal Date
	 * @param fechaInicial Date
	 * @param codigoProcesoScc Long
	 * @param codigoPuestoTrabajoScc Long
	 * @param codigoProductoScc Long
	 * @param variableCalDesc String
	 * @return List<Object[]>
	 * @throws IntegradorExcepcion
	 * @throws ParseException
	 */

	public List<Object[]> obtenerPromedioVarCalidadRangoFechasReporte(Date fechaInicial, Date fechaFinal, Long codigoProcesoScc,
			Long codigoPuestoTrabajoScc, Long codigoProductoScc, String variableCalDesc) throws IntegradorExcepcion;

	/**
	 * Jordan Torres "Reporte de Datos de Calidad"
	 * 
	 * @param fechaFinal Date
	 * @param fechaInicial Date
	 * @param codigoProcesoScc Long
	 * @param codigoMatrixScc Long
	 * @param variableCalDesc String
	 * @return List<Object[]>
	 * @throws IntegradorExcepcion
	 * @throws ParseException
	 */
	public List<Object[]> obtenerVarCalidadReportePorProductovarcalidad(Date fechaInicial, Date fechaFinal,
			Long codigoProcesoScc, Long codigoMatrixScc, String variableCalDesc) throws IntegradorExcepcion;

}
