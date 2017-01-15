package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ingresoproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ingresoproductoproceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: IngresoProductoProcesoQuerier.java
 * Modificado: Dec 23, 2010 11:25:03 AM 
 * Autor: ricardo.marquez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class IngresoProductoProcesoQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static Logger log = Logger.getLogger(IngresoProductoProcesoQuerier.class);
	private static String mensajeError = null;

	public static Ingresoproductoproceso getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return Querier.getById(Ingresoproductoproceso.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public static double obtenerIngresoProductoPorFechaYLineaNegocio(Producto producto, Long codigoLineaNegocio, Date fecha)
			throws EntornoEjecucionException, ElementoNoEncontradoException, SesionVencidaException {
		try {
			StringBuilder queryBuilder = new StringBuilder(
					"SELECT SUM(ipp.toneladasIngresadasIngresopro) FROM Ingresoproductoproceso AS ipp WHERE ");
			queryBuilder.append("ipp.ingresoproducto.lineanegocio.pkCodigoLineanegocio = :lineaNeg AND ");
			queryBuilder.append("ipp.ingresoproducto.fechaIngresoproducto = :fecha AND ");
			queryBuilder.append("ipp.producto.pkCodigoProducto = :producto");

			Query query = getSession().createQuery(queryBuilder.toString());
			query.setLong("lineaNeg", codigoLineaNegocio);
			query.setDate("fecha", fecha);
			query.setLong("producto", producto.getPkCodigoProducto());

			Double sum = (Double) query.uniqueResult();

			return sum == null ? 0d : sum;
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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
	 * Elimina los registros Ingresoproductoproceso pertenecientes al
	 * Ingresoproduco pasado como parametro
	 * 
	 * @param ingresoProducto Ingresoproducto
	 * @throws EntornoEjecucionException
	 * @throws ElementoNoEncontradoException
	 * @throws SesionVencidaException
	 */
	public static void eliminarSegunIngresoProducto(Ingresoproducto ingresoProducto) throws EntornoEjecucionException,
			ElementoNoEncontradoException, SesionVencidaException {
		try {
			StringBuilder queryBuilder = new StringBuilder("Delete FROM Ingresoproductoproceso ipp WHERE ");
			queryBuilder.append("ipp.ingresoproducto.pkCodigoIngresoproducto = :codigoIp");

			Query query = getSession().createQuery(queryBuilder.toString());
			query.setLong("codigoIp", ingresoProducto.getPkCodigoIngresoproducto());
			query.executeUpdate();

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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

}
