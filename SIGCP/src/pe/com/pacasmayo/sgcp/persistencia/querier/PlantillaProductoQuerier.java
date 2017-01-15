package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaproducto;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlantillaProductoQuerier.java
 * Modificado: Feb 9, 2011 3:10:12 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class PlantillaProductoQuerier extends Querier implements ConstantesLogicaNegocio {

	public static final Long CODIGO_HR_ACTIVA = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(ESTADO_HOJA_RUTA_ACTIVA));

	@SuppressWarnings({ "unchecked", "cast" })
	public static List<Plantillaproducto> obtenerPorPlantillaReporteYProducto(Long codigoPlantillaReporte, Long codigoProducto) {
		String consulta = "SELECT DISTINCT(pp) FROM Plantillaproducto pp, Hojaruta hr, Columnaplantillaproducto cpp, Hojarutacomponente hrc   "
				+ "WHERE pp.plantillareporte.pkCodigoPlantillareporte = ? "
				+ "AND pp.producto.pkCodigoProducto = hr.produccion.producto.pkCodigoProducto "
				+ "AND hr.estadohojaruta.pkCodigoEstadohojaruta = ? "
				+ "AND hrc.hojaruta.pkCodigoHojaruta = hr.pkCodigoHojaruta "
				+ "AND hrc.componente.pkCodigoComponente = cpp.componente.pkCodigoComponente "
				+ "AND cpp.plantillaproducto.pkCodigoPlantillaproducto = pp.pkCodigoPlantillaproducto "
				+ "AND pp.producto.pkCodigoProducto  = ? order by pp.versionPlantillaproducto DESC";
		String mensajeError = "";
		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoPlantillaReporte);
			query.setLong(1, CODIGO_HR_ACTIVA);
			query.setLong(2, codigoProducto);

			return (List<Plantillaproducto>) query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings({ "unchecked", "cast" })
	public static Plantillaproducto obtenerActualPorPlantillaReporteYProducto(Long codigoPlantillaReporte, Long codigoProducto)
			throws EntornoEjecucionException, SesionVencidaException {
		String consulta = "SELECT DISTINCT(pp) FROM Plantillaproducto pp, Hojaruta hr, Columnaplantillaproducto cpp, Hojarutacomponente hrc "
				+ "WHERE pp.plantillareporte.pkCodigoPlantillareporte = ? "
				+ "AND pp.producto.pkCodigoProducto = hr.produccion.producto.pkCodigoProducto "
				+ "AND hr.estadohojaruta.pkCodigoEstadohojaruta = ?"
				+ "AND hrc.hojaruta.pkCodigoHojaruta = hr.pkCodigoHojaruta "
				+ "AND hrc.componente.pkCodigoComponente = cpp.componente.pkCodigoComponente "
				+ "AND cpp.plantillaproducto.pkCodigoPlantillaproducto = pp.pkCodigoPlantillaproducto "
				+ "AND pp.producto.pkCodigoProducto  = ? order by pp.pkCodigoPlantillaproducto DESC";
		String mensajeError = "";
		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoPlantillaReporte);
			query.setLong(1, CODIGO_HR_ACTIVA);
			query.setLong(2, codigoProducto);

			List<Plantillaproducto> lista = (List<Plantillaproducto>) query.list();

			if (lista == null || lista.size() == 0) {
				return null;
			}

			return lista.get(0);
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static List<Plantillaproducto> obtenerPlantillasByCodigos(List<Long> codigos) throws EntornoEjecucionException,
			SesionVencidaException {
		String consulta = "FROM Plantillaproducto pp WHERE pp.pkCodigoPlantillaproducto in (:codigos)";

		String mensajeError = "";
		try {
			Query query = Querier.query(consulta);
			query.setParameterList("codigos", codigos);
			List<Plantillaproducto> lista = (List<Plantillaproducto>) query.list();

			return query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}
}
