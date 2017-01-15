package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Partediario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ParteDiarioQuerier.java
 * Modificado: Jun 1, 2010 4:53:41 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ParteDiarioQuerier extends Querier {

	private static Logger loggerPDQ = Logger.getLogger(ParteDiarioQuerier.class);

	private static String mensajeError;

	private static final String NOMBRE_CLASE_PARTE_DIARIO = Partediario.class.getSimpleName();
	private static final String PERIODO_CONTABLE = "periodocontable.pkCodigoPeridocontable";
	private static final String LINEA_NEGOCIO = "lineanegocio.pkCodigoLineanegocio";

	public static Partediario getByPeriodoContableYLineaNegocio(Long codigoPeriodoContable, Long codigoLineaNegocio)
			throws EntornoEjecucionException, SesionVencidaException, ElementoNoEncontradoException {
		String consulta = "FROM {0} AS pc where pc.{1} = :periodoCont AND pc.{2} = :lineaNeg";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_PARTE_DIARIO, PERIODO_CONTABLE, LINEA_NEGOCIO });

		try {
			Query query = Querier.query(consulta);
			query.setLong("periodoCont", codigoPeriodoContable);
			query.setLong("lineaNeg", codigoLineaNegocio);

			Partediario partediario = (Partediario) query.uniqueResult();
			return partediario;

		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerPDQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException);
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerPDQ.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException);
		} catch (NonUniqueResultException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT);
			loggerPDQ.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerPDQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerPDQ.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerPDQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerPDQ.error(mensajeError);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	public static Partediario getByPeriodoContableYLineaNegocio(int mes, int anio, Long codigoLineaNegocio)
			throws EntornoEjecucionException, SesionVencidaException, ElementoNoEncontradoException {
		StringBuilder queryBuilder = new StringBuilder("FROM Partediario AS pd WHERE ");
		queryBuilder.append("pd.periodocontable.anoPeriodocontable = :anio AND ");
		queryBuilder.append("pd.periodocontable.mesPeriodocontable = :mes AND ");
		queryBuilder.append("pd.lineanegocio.pkCodigoLineanegocio = :lineaNeg");

		try {
			Query query = Querier.query(queryBuilder.toString());
			query.setShort("mes", (short) mes);
			query.setInteger("anio", anio);
			query.setLong("lineaNeg", codigoLineaNegocio);

			Partediario partediario = (Partediario) query.uniqueResult();
			return partediario;

		} catch (NonUniqueResultException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT);
			loggerPDQ.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerPDQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException);
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerPDQ.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException);
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerPDQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerPDQ.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerPDQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerPDQ.error(mensajeError);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	/**
	 * Obtiene el parte diario segun el anio y mes de su periodo contable
	 * 
	 * @param mesPeriodoCont
	 * @param anioPeriodoCont
	 * @return Partediario
	 * @throws ElementoNoEncontradoException, SesionVencidaException
	 * @throws AplicacionException
	 */
	public static Partediario getByPeriodoContable(int mesPeriodoCont, int anioPeriodoCont) throws ElementoNoEncontradoException,
			SesionVencidaException, EntornoEjecucionException {
		String consulta = "FROM Partediario AS pd where pc.periodocontable.mesPeriodocontable = :mes AND pc.periodocontable.anoPeriodocontable = :anio";

		try {
			Query query = Querier.query(consulta);
			query.setLong("mes", mesPeriodoCont);
			query.setLong("anio", anioPeriodoCont);

			Partediario partediario = (Partediario) query.uniqueResult();
			return partediario;

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static List<Producciondiaria> obtenerRegistrosPorParteDiario(Long codigoProducto, Long codigoProceso, Long anio,
			Long mes) throws EntornoEjecucionException, SesionVencidaException, RuntimeException {
		logger.info("*****************registrosProduccionDiaria afuera");
		try {
			String queryString = "SELECT pd FROM Producciondiaria as pd "
					+ "WHERE pd.ordenproduccion.produccion.proceso.pkCodigoProceso = ? "
					+ "AND pd.partediario.periodocontable.anoPeriodocontable = ? " // integer
					+ "AND pd.partediario.periodocontable.mesPeriodocontable = ? ";

			if (codigoProducto != 0) {
				queryString = queryString + "AND pd.ordenproduccion.produccion.producto.pkCodigoProducto = ? ";
			}

			Query query = Querier.query(queryString);
			query.setLong(0, codigoProceso);
			query.setInteger(1, anio.intValue());
			query.setShort(2, mes.shortValue());

			if (codigoProducto != 0) {
				query.setLong(3, codigoProducto);
			}
			logger.info("********obtenerRegistrosPorParteDiario: " + query.list());
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
			logger.error(mensajeError);
			throw e;
		}
	}

	public static void eliminarFechaLineaNegocio(Long lineaNegocio, Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);

		StringBuilder querystr = new StringBuilder("DELETE FROM Partediario pd ");
		querystr.append(" WHERE pd.periodocontable IN (SELECT pc FROM Periodocontable pc WHERE pc.anoPeriodocontable=:anio");
		querystr.append(" AND  pc.mesPeriodocontable=:mes) AND pd.lineanegocio IN (SELECT ln FROM Lineanegocio ln WHERE ln.pkCodigoLineanegocio =:lineaNegocio) ");

		Query query = Querier.query(querystr.toString());
		query.setShort("mes", mes.shortValue());
		query.setInteger("anio", anio);
		query.setLong("lineaNegocio", lineaNegocio);
		query.executeUpdate();

	}

	public static Partediario getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Partediario.class, codigo);
	}
}
