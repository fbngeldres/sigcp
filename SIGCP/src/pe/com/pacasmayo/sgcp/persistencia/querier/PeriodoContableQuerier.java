package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PeriodoContableQuerier.java
 * Modificado: Jun 1, 2010 4:16:39 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class PeriodoContableQuerier extends Querier {

	private static final String NOMBRE_CLASE_PERIODO_CONTABLE = Periodocontable.class.getSimpleName();
	private static final String MES = "mesPeriodocontable";
	private static final String ANO = "anoPeriodocontable";

	public static Periodocontable getByMesYAno(short mes, int ano) throws ElementoNoEncontradoException {
		String consulta = "FROM {0} AS pc where pc.{1} = :mes AND pc.{2} = :ano";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_PERIODO_CONTABLE, MES, ANO });

		try {
			Query query = Querier.query(consulta);
			query.setShort("mes", mes);
			query.setInteger("ano", ano);

			Periodocontable periodocontable = (Periodocontable) query.uniqueResult();
			return periodocontable;

		} catch (UnresolvableObjectException uOException) {
			logger.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(ERROR_HIBERNATE, hException);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de periodos contables
	 * 
	 * @return
	 */
	public static List<Periodocontable> getAll() {

		return Querier.getAll(Periodocontable.class);
	}

	/**
	 * Método para consultar de base de datos los Años de los diferentes
	 * periodos contables abiertos
	 * 
	 * @param
	 * @return
	 */
	public static List<Integer> getAnosPorPeriodoAbierto() {

		List<Integer> anios = new ArrayList<Integer>();
		String mensajeError = "";

		try {

			String consulta = "select distinct pc.anoPeriodocontable "
					+ "from Periodocontable pc where pc.cerradoPeridocontable = ? ORDER BY pc.anoPeriodocontable DESC";

			Query query = Querier.query(consulta);
			query.setBoolean(0, false);

			anios = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return anios;
	}

	/**
	 * Método para consultar de base de datos los Años de los diferentes
	 * periodos contables abiertos
	 * 
	 * @param
	 * @return
	 */
	public static List<Integer> getAnosPorPeriodo() {

		List<Integer> anios = new ArrayList<Integer>();
		String mensajeError = "";

		try {

			String consulta = "select distinct pc.anoPeriodocontable FROM Periodocontable pc"
					+ " ORDER BY pc.anoPeriodocontable ASC";

			Query query = Querier.query(consulta);

			anios = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return anios;
	}

	/**
	 * Método para consultar de base de datos los Meses de los diferentes
	 * periodos contables abiertos
	 * 
	 * @param
	 * @return
	 */
	public static List<Short> getMesesPorPeriodoAbierto() {

		List<Short> meses = new ArrayList<Short>();
		String mensajeError = "";

		try {

			String consulta = "select distinct pc.mesPeriodocontable "
					+ "from Periodocontable pc where pc.cerradoPeridocontable = ? ";

			Query query = Querier.query(consulta);
			query.setBoolean(0, false);

			meses = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return meses;
	}
}
