package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.RendimientoTermico;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoPuestoTrabajoQuerier.java
 * Modificado: May 9, 2011 3:23:52 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class RendimientoTermicoQuerier extends Querier {

	private static Logger log = Logger.getLogger(RendimientoTermicoQuerier.class);
	private static String mensajeError;

	@SuppressWarnings("unchecked")
	public static RendimientoTermico obtenerRendimientoTermico(Long codPuestoTrabajo, Long codProducto) {

		try {
			StringBuilder hql = new StringBuilder("FROM RendimientoTermico AS rt WHERE ");
			hql.append("rt.puestoTrabajo.pkCodigoPuestotrabajo = :puestoTrabajo AND ");
			hql.append("rt.producto.pkCodigoProducto = :producto");

			Query query = Querier.query(hql.toString());

			query.setLong("puestoTrabajo", codPuestoTrabajo);
			query.setLong("producto", codProducto);

			return (RendimientoTermico) query.uniqueResult();
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

	public static List<RendimientoTermico> getAll() {
		return Querier.getAll(RendimientoTermico.class);
	}

	public static RendimientoTermico getById(long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {
		return Querier.getById(RendimientoTermico.class, codigo);
	}

	/**
	 * @param variableVariacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void save(RendimientoTermico rendimientoTermico) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.save(rendimientoTermico);
	}

	/**
	 * @param variableVariacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void update(RendimientoTermico rendimientoTermico) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(rendimientoTermico);
	}

	/**
	 * @param variableVariacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void delete(RendimientoTermico rendimientoTermico) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.delete(rendimientoTermico);
	}

	public static List<Long> getCodigoProductoRendimientoTermico() {
		try {
			StringBuilder hql = new StringBuilder("SELECT  distinct producto.pkCodigoProducto FROM RendimientoTermico ");

			Query query = Querier.query(hql.toString());

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

	public static List<Long> getCodigoPuestoTrabajoRendimientoTermico() {
		try {
			StringBuilder hql = new StringBuilder("SELECT  distinct puestoTrabajo.pkCodigoPuestotrabajo FROM RendimientoTermico ");

			Query query = Querier.query(hql.toString());

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

}
