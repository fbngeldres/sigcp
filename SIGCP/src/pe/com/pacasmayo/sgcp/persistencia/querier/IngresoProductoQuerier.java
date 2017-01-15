package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ingresoproducto;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: IngresoProductoQuerier.java
 * Modificado: Dec 23, 2010 11:25:03 AM 
 * Autor: ricardo.marquez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class IngresoProductoQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_LINEA_NEGOCIO = "lineanegocio.pkCodigoLineanegocio";
	public static final String ANIO = "anoIngresoproducto";
	public static final String MES = "mesIngresoproducto";

	public static void save(Ingresoproducto ingresoProducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(ingresoProducto);
	}

	public static Ingresoproducto getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return Querier.getById(Ingresoproducto.class, codigo);
	}

	/**
	 * Metodo que permite filtrar la lista de IngresoProducto segun un conjunto
	 * de propiedades
	 * 
	 * @param propiedades
	 * @return
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 * @throws AplicacionException
	 */
	public static List<Ingresoproducto> buscarPorPropiedades(Map<String, Object> propiedades) throws SesionVencidaException,
			EntornoEjecucionException {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append("FROM Ingresoproducto As iprod WHERE");

		String caracterPuntoRegex = "\\.";
		String cadenaVacia = "";
		String mensajeError = "";

		for (Iterator<String> iterator = propiedades.keySet().iterator(); iterator.hasNext();) {
			String clausulaWhere = " iprod.{0} = :{1} {2}";
			String clave = iterator.next();
			String clausulaAnd = iterator.hasNext() ? "AND" : cadenaVacia;
			String appendWhere = MessageFormat.format(clausulaWhere,
					new Object[] { clave, clave.replaceAll(caracterPuntoRegex, cadenaVacia), clausulaAnd });
			queryStr.append(appendWhere);
		}

		try {
			String consulta = queryStr.toString();
			Session session = getSession();
			Query query = session.createQuery(consulta);

			for (Iterator<String> iterator = propiedades.keySet().iterator(); iterator.hasNext();) {
				String clave = iterator.next();
				query.setParameter(clave.replaceAll(caracterPuntoRegex, cadenaVacia), propiedades.get(clave));
			}

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
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw e;
		}

	}

	/**
	 * Obtiene el Ingresoproducto segun la linea de negocio ya la fecha del
	 * proceso
	 * 
	 * @param codigoLineaNegocio Long
	 * @param fecha Date
	 * @return Ingresoproducto
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	public static Ingresoproducto obtenerPorFechaYLineaNegocio(Long codigoLineaNegocio, Date fecha)
			throws SesionVencidaException, EntornoEjecucionException {

		String mensajeError = "";

		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("FROM Ingresoproducto As iprod WHERE ");
			queryStr.append("iprod.lineanegocio.pkCodigoLineanegocio = :codLinea AND ");
			queryStr.append("iprod.fechaIngresoproducto = :fecha");

			Session session = getSession();
			Query query = session.createQuery(queryStr.toString());

			query.setLong("codLinea", codigoLineaNegocio);
			query.setDate("fecha", fecha);

			return (Ingresoproducto) query.uniqueResult();
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
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw e;
		}
	}

}
