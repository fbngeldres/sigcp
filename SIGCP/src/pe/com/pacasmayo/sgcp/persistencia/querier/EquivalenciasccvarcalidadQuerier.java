package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Equivalenciasccvarcalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EquivalenciasccvarcalidadQuerier.java
 * Modificado: May 17, 2011 4:11:47 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EquivalenciasccvarcalidadQuerier extends Querier {

	private static Logger log = Logger.getLogger(EquivalenciasccvarcalidadQuerier.class);
	private static String mensajeError;

	/**
	 * Método para obtener un Producto por medio del código
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static Equivalenciasccvarcalidad getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Equivalenciasccvarcalidad.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public static List<String> obtenerNombresSgcpVarCalidad() throws SesionVencidaException, EntornoEjecucionException {
		try {

			Query query = Querier.query("SELECT DISTINCT(eqv.descripSgcpVarCalidad) FROM Equivalenciasccvarcalidad AS eqv");

			return query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Equivalenciasccvarcalidad> obtenerVariablesCalidadSegunPuestoYProduccion(
			Consumopuestotrabajo consumopuestotrabajo) {
		try {
			Long codPuestoTrab = consumopuestotrabajo.getProductogenerado().getTablaoperacion().getProduccionpuestotrabajo()
					.getPuestotrabajo().getPkCodigoPuestotrabajo();

			Produccion produccion = consumopuestotrabajo.getProductogenerado().getOrdenproduccion().getProduccion();

			Long codProceso = produccion.getProceso().getPkCodigoProceso();
			Long codComponente = consumopuestotrabajo.getComponente().getPkCodigoComponente();

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("FROM Equivalenciasccvarcalidad eq  WHERE ");
			queryStr.append("eq.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
			queryStr.append("eq.proceso.pkCodigoProceso = :codProceso AND ");
			queryStr.append("eq.componente.pkCodigoComponente = :codComponente ORDER BY eq.puestotrabajo ASC");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setLong("codProceso", codProceso);
			query.setLong("codComponente", codComponente);

			return query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static boolean verificarSiPoseeHumedad(Long codigoProducto) {
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select COUNT(eq.pkCodigoEquiscccalidadscc) FROM Equivalenciasccvarcalidad eq  WHERE ");
			queryStr.append("eq.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto AND ");
			queryStr.append("eq.descripSgcpVarCalidad = :descripVarcal");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codProducto", codigoProducto);
			query.setString("descripVarcal", "Humedad");

			Long count = (Long) query.uniqueResult();
			return count.longValue() > 0;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Jordan "Reporte de Variables de Calidad"
	 */

	public static List<Equivalenciasccvarcalidad> obtenerVariablesCalidadPuestoYProceso(Long codigoPuestotrabajo,
			Long codigoProceso) {
		try {

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("FROM Equivalenciasccvarcalidad eq  WHERE 1=1 ");
			if (codigoPuestotrabajo != null) {
				queryStr.append("AND eq.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab ");
			}
			if (codigoProceso != null) {
				queryStr.append("AND eq.proceso.pkCodigoProceso = :codProceso ");
			}
			queryStr.append("ORDER BY eq.componente.productoByFkCodigoProducto.nombreProducto ");
			Query query = Querier.query(queryStr.toString());

			if (codigoPuestotrabajo != null) {
				query.setLong("codPuestoTrab", codigoPuestotrabajo);
			}
			if (codigoProceso != null) {
				query.setLong("codProceso", codigoProceso);
			}
			return query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			e.printStackTrace();
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			e.printStackTrace();
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			e.printStackTrace();
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			e.printStackTrace();
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}
}
