package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionDiariaLogic.java
 * Modificado: Jun 23, 2010 3:23:06 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class NotificacionDiariaQuerier extends Querier {

	/** Logger Instance */
	protected static Logger loggerQ = Logger.getLogger(NotificacionDiariaQuerier.class);

	private static String CODIGO_LINEANEGOCIO = "lineanegocio.pkCodigoLineanegocio";
	private static String CODIGO_UNIDAD_LINEANEGOCIO = "lineanegocio.unidad.pkCodigoUnidad";
	private static String CODIGO_PUESTOTRABAJO_CANTERA = "puestotrabajoByFkCodigoPuestotrabajo";
	private static String CODIGO_PUESTOTRABAJO_PRODUCCION = "puestotrabajo";
	private static String FECHAMOVIMIENTO = "fechaNotificaciondiaria";
	private static String CODIGO_ESTADO = "estadonotificacion.pkCodigoEstadonotificacion";
	private static String CODIGO_TABLEROCONTROL = "tablerocontrol.pkCodigoTablerocontrol";
	private static String CODIGO_ACTIVIDAD = "actividad.pkCodigoActividad";
	private static String ESTADO_NO_MUESTRA = "Generada";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * M�todo para obtener la lista de objectos Estadonotificacion
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Notificaciondiaria> getAll() throws AplicacionException {

		return Querier.getAll(Notificaciondiaria.class);
	}

	/**
	 * M�todo para obtener una Estadonotificacion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException {
	 */
	public static Notificaciondiaria getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Notificaciondiaria.class, codigo);
	}

	/**
	 * Metodo que obtiene las notificaiones diarias, filtrando por linea de
	 * negocio, estado y rango de fechas
	 * 
	 * @param codigoLineaNegocio linea de negocio
	 * @param codigoEstado estado de la notificacion
	 * @param fechaInicio fecha de inicio
	 * @param fechaFin fecha fin
	 * @return List<Notificaciondiaria>
	 * @throws AplicacionException Si falla la conexion con la BD
	 * @throws SesionVencidaException si se hace uso incorrecto de las sesiones
	 *             de hibernate
	 * @throws EntornoEjecucionException si ocurre un error en tiempo de
	 *             ejecucion
	 */
	@SuppressWarnings("unchecked")
	public static List<Notificaciondiaria> obtenerNotificacionesDiariasPorFiltros(Long codigoLineaNegocio, Long codigoEstado,
			Date fechaInicio, Date fechaFin) throws AplicacionException, SesionVencidaException, EntornoEjecucionException {
		List<Notificaciondiaria> notificaciondiariaList = new ArrayList<Notificaciondiaria>();

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("FROM Notificaciondiaria AS item ");
		queryBuilder.append("WHERE item.lineanegocio.pkCodigoLineanegocio = :ln AND ");
		if (codigoEstado != null) {
			queryBuilder.append("item.estadonotificacion.pkCodigoEstadonotificacion = :en AND ");
		}
		queryBuilder.append("item.fechaNotificaciondiaria >=:fechaInicio AND ");
		queryBuilder.append("item.fechaNotificaciondiaria <=:fechaFin ORDER BY item.fechaNotificaciondiaria asc");

		String queryStr = queryBuilder.toString();

		String mensajeError = null;
		try {
			Query query = Querier.query(queryStr);

			query.setLong("ln", codigoLineaNegocio);
			if (codigoEstado != null) {
				query.setLong("en", codigoEstado);
			}
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);

			notificaciondiariaList = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError, e);
			throw new AplicacionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerQ.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return notificaciondiariaList;
	}

	/*
	 * Metodo para el filtro para mostrar todas las notificaciones menos el
	 * estado Generado
	 */

	public static List<Notificaciondiaria> obtenerNotificacionesDiariasEstadoPorFiltros(Long codigoLineaNegocio,
			Long codigoEstado, Date fechaInicio, Date fechaFin) throws AplicacionException, SesionVencidaException,
			EntornoEjecucionException {
		List<Notificaciondiaria> notificaciondiariaList = new ArrayList<Notificaciondiaria>();

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("FROM Notificaciondiaria AS item ");
		queryBuilder.append("WHERE item.lineanegocio.pkCodigoLineanegocio = :ln AND ");
		if (codigoEstado != null) {
			queryBuilder.append("item.estadonotificacion.pkCodigoEstadonotificacion = :en AND ");
		}
		queryBuilder.append("item.estadonotificacion.nombreEstadonotificacion <> :estadoGenerado AND ");
		queryBuilder.append("item.fechaNotificaciondiaria >=:fechaInicio AND ");
		queryBuilder.append("item.fechaNotificaciondiaria <=:fechaFin ORDER BY item.fechaNotificaciondiaria asc");

		String queryStr = queryBuilder.toString();

		String mensajeError = null;
		try {
			Query query = Querier.query(queryStr);

			query.setLong("ln", codigoLineaNegocio);
			if (codigoEstado != null) {
				query.setLong("en", codigoEstado);
			}
			query.setString("estadoGenerado", ESTADO_NO_MUESTRA);
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);

			notificaciondiariaList = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError, e);
			throw new AplicacionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerQ.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return notificaciondiariaList;
	}

	/* */

	@SuppressWarnings("unchecked")
	public static List<Notificaciondiaria> obtenerNotificacionesDiariasCantera(Long codigoUnidad, Long codigoLineaNegocio,
			Long codigoActividad, Long codigoPuestoTrabajo, Long codigoTableroControl, Long codigoEstado, Date fechaInicio,
			Date fechaFin) throws AplicacionException {

		List<Notificaciondiaria> notificaciondiariaList = null;
		String mensajeError = null;

		try {
			Query query = Querier.query(construirQueryNotificacionesCantera(codigoLineaNegocio, codigoActividad,
					codigoTableroControl, codigoPuestoTrabajo, codigoEstado, fechaInicio, fechaFin));

			query.setLong("codigoUnidad", codigoUnidad);

			if (codigoLineaNegocio != null)
				query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			if (codigoTableroControl != null)
				query.setLong("codigoTableroControl", codigoTableroControl);
			if (codigoActividad != null)
				query.setLong("codigoActividad", codigoActividad);
			if (codigoPuestoTrabajo != null)
				query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			if (codigoEstado != null)
				query.setLong("codigoEstado", codigoEstado);
			if ((fechaInicio != null) && (fechaFin != null)) {
				query.setDate("fechaInicio", fechaInicio);
				query.setDate("fechaFin", fechaFin);
			}

			notificaciondiariaList = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError);
			throw new AplicacionException(ERROR_QUERY_FALLO, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError);
			throw new AplicacionException(ERROR_USO_SESION_INAPROPIADA, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError);
			throw new AplicacionException(ERROR_COMUNICACION_FALLO, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerQ.error(mensajeError);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}

		return notificaciondiariaList;
	}

	@SuppressWarnings("unchecked")
	public static List<Notificaciondiaria> obtenerNotificacionesDiariasProduccion(Long codigoUnidad, Long codigoLineaNegocio,
			Long codigoPuestoTrabajo, Long codigoEstado, Date fechaInicio, Date fechaFin) throws AplicacionException {

		List<Notificaciondiaria> notificaciondiariaList = null;
		String mensajeError = null;

		try {
			Query query = Querier.query(construirQueryNotificacionesProduccion(codigoLineaNegocio, codigoPuestoTrabajo,
					codigoEstado, fechaInicio, fechaFin));

			query.setLong("codigoUnidad", codigoUnidad);

			if (codigoLineaNegocio != null)
				query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			if (codigoPuestoTrabajo != null)
				query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			if (codigoEstado != null)
				query.setLong("codigoEstado", codigoEstado);
			if ((fechaInicio != null) && (fechaFin != null)) {
				query.setDate("fechaInicio", fechaInicio);
				query.setDate("fechaFin", fechaFin);
			}

			notificaciondiariaList = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerQ.error(mensajeError);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}

		return notificaciondiariaList;
	}

	private static String construirQueryNotificacionesCantera(Long codigoLineaNegocio, Long codigoActividad,
			Long codigoTableroControl, Long codigoPuestoTrabajo, Long codigoEstado, Date fechaInicio, Date fechaFin) {

		StringBuilder queryCantera = new StringBuilder();

		queryCantera.append("FROM Notificaciondiaria nd" + " WHERE nd.pkCodigoNotificaciondiaria in"
				+ " (SELECT nc.notificaciondiaria.pkCodigoNotificaciondiaria" + " FROM Notificacioncantera nc");

		queryCantera.append(" WHERE nc.notificaciondiaria." + CODIGO_UNIDAD_LINEANEGOCIO + "=:codigoUnidad");

		if (codigoLineaNegocio != null)
			queryCantera.append(" and nc.notificaciondiaria." + CODIGO_LINEANEGOCIO + "=:codigoLineaNegocio");

		if (codigoTableroControl != null)
			queryCantera.append(" and nc.notificaciondiaria." + CODIGO_TABLEROCONTROL + "=:codigoTableroControl");

		if (codigoActividad != null)
			queryCantera.append(" and nc." + CODIGO_ACTIVIDAD + "=:codigoActividad");

		if (codigoPuestoTrabajo != null)
			queryCantera.append(" and nc." + CODIGO_PUESTOTRABAJO_CANTERA + "=:codigoPuestoTrabajo");

		if (codigoEstado != null)
			queryCantera.append(" and nc.notificaciondiaria." + CODIGO_ESTADO + "=:codigoEstado");

		if ((fechaInicio != null) && (fechaFin != null))
			queryCantera.append(" and nc.notificaciondiaria." + FECHAMOVIMIENTO + ">=:fechaInicio and nc.notificaciondiaria."
					+ FECHAMOVIMIENTO + "<=:fechaFin");

		if (codigoActividad == null && codigoPuestoTrabajo == null) {
			queryCantera.append(") OR nd.pkCodigoNotificaciondiaria in"
					+ " (SELECT dc.notificaciondiaria.pkCodigoNotificaciondiaria" + " FROM Despachocantera dc");

			queryCantera.append(" WHERE dc.notificaciondiaria." + CODIGO_UNIDAD_LINEANEGOCIO + "=:codigoUnidad");

			if (codigoLineaNegocio != null)
				queryCantera.append(" and dc.notificaciondiaria." + CODIGO_LINEANEGOCIO + "=:codigoLineaNegocio");

			if (codigoTableroControl != null)
				queryCantera.append(" and dc.notificaciondiaria." + CODIGO_TABLEROCONTROL + "=:codigoTableroControl");

			if (codigoEstado != null)
				queryCantera.append(" and dc.notificaciondiaria." + CODIGO_ESTADO + "=:codigoEstado");

			if ((fechaInicio != null) && (fechaFin != null))
				queryCantera.append(" and dc.notificaciondiaria." + FECHAMOVIMIENTO + ">=:fechaInicio and dc.notificaciondiaria."
						+ FECHAMOVIMIENTO + "<=:fechaFin");

		}

		queryCantera.append(")");

		return queryCantera.toString();
	}

	private static String construirQueryNotificacionesProduccion(Long codigoLineaNegocio, Long codigoPuestoTrabajo,
			Long codigoEstado, Date fechaInicio, Date fechaFin) {

		StringBuilder queryCantera = new StringBuilder();

		queryCantera.append("FROM Notificaciondiaria nd" + " WHERE nd.pkCodigoNotificaciondiaria in"
				+ " (SELECT np.notificaciondiaria.pkCodigoNotificaciondiaria" + " FROM Notificacionproduccion np");

		queryCantera.append(" WHERE np.notificaciondiaria." + CODIGO_UNIDAD_LINEANEGOCIO + "=:codigoUnidad");

		if (codigoLineaNegocio != null)
			queryCantera.append(" and np.notificaciondiaria." + CODIGO_LINEANEGOCIO + "=:codigoLineaNegocio");

		if (codigoPuestoTrabajo != null)
			queryCantera.append(" and np." + CODIGO_PUESTOTRABAJO_PRODUCCION + "=:codigoPuestoTrabajo");

		if (codigoEstado != null)
			queryCantera.append(" and np.notificaciondiaria." + CODIGO_ESTADO + "=:codigoEstado");

		if ((fechaInicio != null) && (fechaFin != null))
			queryCantera.append(" and np.notificaciondiaria." + FECHAMOVIMIENTO + ">=:fechaInicio and np.notificaciondiaria."
					+ FECHAMOVIMIENTO + "<=:fechaFin");

		queryCantera.append(") order by nd.fechaNotificaciondiaria asc");

		return queryCantera.toString();
	}

	public static Notificaciondiaria obtenerNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl,
			Date fechaRegistro) throws AplicacionException {
		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "SELECT nd FROM Notificaciondiaria as nd WHERE "
					+ "nd.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio AND "
					+ "nd.tablerocontrol.pkCodigoTablerocontrol = :codigoTableroControl AND "
					+ "nd.fechaNotificaciondiaria = :fecha";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setLong("codigoTableroControl", codigoTableroControl);
			query.setDate("fecha", fechaRegistro);

			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	/*
	 * Metodo agregado por Stephany para obtener la ultima notificacion
	 * registrada
	 */

	public static Notificaciondiaria obtenerUltimaNotificacionDiaria(Long codigoLineaNegocio, Date fechaInicio, Date fechaFin)
			throws AplicacionException {
		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "SELECT nd FROM Notificaciondiaria as nd WHERE "
					+ " nd.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio AND "
					+ " estadonotificacion.pkCodigoEstadonotificacion = :estado AND "
					+ " nd.fechaNotificaciondiaria >= :fechaInicio AND nd.fechaNotificaciondiaria < :fechaFin "
					+ " ORDER BY nd.fechaNotificaciondiaria DESC ";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);
			query.setLong("estado", Long.valueOf(4));

			query.setMaxResults(1);
			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	/* Fin de metodo */

	@SuppressWarnings("unchecked")
	public static List<Notificaciondiaria> obtenerNotificacionDiaria(Long codigoLineaNegocio, Long codigoTableroControl,
			Long codigoEstado, Date fechaRegistro) throws AplicacionException {
		List<Notificaciondiaria> notificaciones = null;
		try {
			String queryStr = "SELECT nd FROM Notificaciondiaria as nd WHERE "
					+ "nd.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio AND "
					+ "nd.tablerocontrol.pkCodigoTablerocontrol = :codigoTableroControl AND "
					+ "nd.estadonotificacion.pkCodigoEstadonotificacion = :codigoEstadoNotificacion AND "
					+ "nd.fechaNotificaciondiaria = :fecha";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setLong("codigoTableroControl", codigoTableroControl);
			query.setLong("codigoEstadoNotificacion", codigoEstado);
			query.setDate("fecha", fechaRegistro);

			notificaciones = query.list();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciones;
	}

	public static Notificaciondiaria obtenerNotificacionDiariaCanterasDespachos(Long codigoLineaNegocio, Date fechaRegistro)
			throws ElementoNoEncontradoException {

		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "FROM Notificaciondiaria nd" + " WHERE nd.pkCodigoNotificaciondiaria in"
					+ " (SELECT nc.notificaciondiaria.pkCodigoNotificaciondiaria" + " FROM Notificacioncantera nc"
					+ " WHERE nc.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio"
					+ " AND nc.notificaciondiaria.fechaNotificaciondiaria = :fecha)" + " OR nd.pkCodigoNotificaciondiaria in"
					+ " (SELECT dc.notificaciondiaria.pkCodigoNotificaciondiaria" + " FROM Despachocantera dc"
					+ " WHERE dc.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio"
					+ " AND dc.notificaciondiaria.fechaNotificaciondiaria = :fecha)";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setDate("fecha", fechaRegistro);

			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	public static Notificaciondiaria obtenerNotificacionDiariaDespachos(Long codigoLineaNegocio, Date fechaRegistro)
			throws ElementoNoEncontradoException {

		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "SELECT DISTINCT(dc.notificaciondiaria)" + " FROM Despachocantera dc"
					+ " WHERE dc.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio"
					+ " AND dc.notificaciondiaria.fechaNotificaciondiaria = :fecha";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setDate("fecha", fechaRegistro);

			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	public static Notificaciondiaria obtenerNotificacionDiariaCantera(Long codigoLineaNegocio, Date fechaRegistro)
			throws ElementoNoEncontradoException {

		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "SELECT DISTINCT(nc.notificaciondiaria)" + " FROM Notificacioncantera nc"
					+ " WHERE nc.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio"
					+ " AND nc.notificaciondiaria.fechaNotificaciondiaria = :fecha";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setDate("fecha", fechaRegistro);

			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	public static Notificaciondiaria obtenerPorCodigoNotificacionCantera(Long codigoNotificacionCantera)
			throws ElementoNoEncontradoException {

		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "SELECT DISTINCT(nd) " + "FROM Notificaciondiaria as nd, " + "Notificacioncantera AS nc, "
					+ "WHERE nc.pkCodigoNotificacioncantera = :codigoNotificacionCantera AND "
					+ "nd.pkCodigoNotificaciondiaria = nc.notificaciondiaria.pkCodigoNotificaciondiaria ";

			Query query = Querier.query(queryStr);

			query.setLong("codigoNotificacionCantera", codigoNotificacionCantera);

			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	/**
	 * Busca las cabeceras de las notificaciones de produccion por un rango de
	 * fecha y opcional: unidad, sociedad,divicion, puesto trabajo y producto
	 * 
	 * @param codigoUnidad
	 * @param codigoSociedad
	 * @param codigoDivision
	 * @param fechaInicio
	 * @param fechaFin
	 * @param codigoPuestoTrabajo
	 * @param codigoProducto
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Notificaciondiaria> obtenerPorFechaYVariosFiltros(Long codigoUnidad, Long codigoSociedad,
			Long codigoDivision, Date fechaInicio, Date fechaFin, Long codigoPuestoTrabajo, Long codigoProducto)
			throws AplicacionException {
		List<Notificaciondiaria> notificacionesDiaria = null;
		try {
			StringBuilder queryStr = new StringBuilder(
					"SELECT DISTINCT(np.notificaciondiaria) FROM Notificacionproduccion np"
							+ " WHERE (np.notificaciondiaria.fechaNotificaciondiaria >= :fechaInicio AND np.notificaciondiaria.fechaNotificaciondiaria <= :fechaFin)");
			if (codigoUnidad != null && codigoUnidad > 0)
				queryStr.append(" AND np.notificaciondiaria.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");
			if (codigoSociedad != null && codigoSociedad > 0)
				queryStr.append(" AND np.notificaciondiaria.lineanegocio.unidad.sociedad.pkCodigoSociedad = :codigoSociedad");
			if (codigoDivision != null && codigoDivision > 0)
				queryStr.append(" AND np.notificaciondiaria.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :codigoDivision");
			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				queryStr.append(" AND np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo");
			if (codigoProducto != null && codigoProducto > 0)
				queryStr.append(" AND np.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");

			queryStr.append(" Order by np.notificaciondiaria.fechaNotificaciondiaria");

			Query query = Querier.query(queryStr.toString());

			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);

			if (codigoUnidad != null && codigoUnidad > 0)
				query.setLong("codigoUnidad", codigoUnidad);
			if (codigoSociedad != null && codigoSociedad > 0)
				query.setLong("codigoSociedad", codigoSociedad);
			if (codigoDivision != null && codigoDivision > 0)
				query.setLong("codigoDivision", codigoDivision);
			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			if (codigoProducto != null && codigoProducto > 0)
				query.setLong("codigoProducto", codigoProducto);

			notificacionesDiaria = query.list();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}
		return notificacionesDiaria;
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Estadonotificacion en la BD.
	 * 
	 * @param notificaciondiaria
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Notificaciondiaria notificaciondiaria) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(notificaciondiaria);
	}

	/**
	 * Metodo para modificar una Estadonotificacion de la BD.
	 * 
	 * @param notificaciondiaria
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Notificaciondiaria notificaciondiaria) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(notificaciondiaria);
	}

	/**
	 * Metodo para eliminar una Estadonotificacion de la BD.
	 * 
	 * @param estadonotificacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Notificaciondiaria notificaciondiaria) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(notificaciondiaria);
	}

	/**
	 * Elimina una notificacion diaria de la BD, dados su linea de negocio,
	 * tablero de control y fcha de registro
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param fechaRegistro
	 * @param codigoNotifDiaria
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException, EntornoEjecucionException
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static void eliminarNotificacionesProduccion(Long codigoNotifDiaria, Long codigoPuestoTrabajo)
			throws ElementoEliminadoException, ElementoNoEncontradoException, EntornoEjecucionException {
		String mensajeError = null;
		try {
			Notificaciondiaria notificaciondiaria = getById(codigoNotifDiaria);
			Set<Notificacionproduccion> notificacionproduccions = notificaciondiaria.getNotificacionproduccions();

			for (Iterator<Notificacionproduccion> itt = notificacionproduccions.iterator(); itt.hasNext();) {
				Notificacionproduccion notificacionproduccion = itt.next();
				if (notificacionproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo().longValue() == codigoPuestoTrabajo) {
					delete(notificacionproduccion);
				}
			}

		} catch (ParametroInvalidoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_PARAMETRO_INVALIDO);
			loggerQ.error(mensajeError, e);
			throw new ElementoEliminadoException(mensajeError, e.getCause());
		} catch (ElementoExistenteException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ELIMINADO);
			loggerQ.error(mensajeError, e);
			throw new ElementoEliminadoException(mensajeError, e.getCause());
		}
	}

	public static boolean verificarSiHayAprobadasEnLaFecha(Date fechaNotificacionDiaria) throws AplicacionException {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder("SELECT COUNT(nd.pkCodigoNotificaciondiaria) FROM Notificaciondiaria nd");
			queryStr.append(" WHERE nd.fechaNotificaciondiaria = :fecha AND ");
			queryStr.append("nd.estadonotificacion.pkCodigoEstadonotificacion = :estado");

			Query query = Querier.query(queryStr.toString());

			query.setDate("fecha", fechaNotificacionDiaria);
			query.setLong("estado", 3L);

			Long count = (Long) query.uniqueResult();

			return count > 0;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(e);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(e);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(e);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerQ.error(e);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}
	}

	/*
	 * Comentado por Jordan
	 */
	public static Notificaciondiaria obtenerNotificacion(Long codigoLineaNegocio, Date fechaRegistro)
			throws ElementoNoEncontradoException {

		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "FROM Notificaciondiaria nd" + " WHERE"
					+ " nd.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio" + " AND nd.fechaNotificaciondiaria = :fecha)";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setDate("fecha", fechaRegistro);

			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	/* Metodo agregado por Stephany para obtener la ultima notificacion Aprobada */
	public static Notificaciondiaria obtenerUltimaNotificacionDiariaAprobada(Long codigoLineaNegocio, Date fechaInicio,
			Date fechaFin) throws AplicacionException {
		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "SELECT nd FROM Notificaciondiaria as nd WHERE "
					+ " nd.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio AND "
					+ " estadonotificacion.pkCodigoEstadonotificacion = :estado AND "
					+ " nd.fechaNotificaciondiaria >= :fechaInicio AND nd.fechaNotificaciondiaria <= :fechaFin "
					+ " ORDER BY nd.fechaNotificaciondiaria DESC ";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);
			query.setLong("estado", Long.valueOf(3));

			query.setMaxResults(1);
			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	/*
	 * Metodo agregado por Stephany para obtener la primera notificacion
	 * Aprobada
	 */
	public static Notificaciondiaria obtenerPrimeraNotificacionDiariaAprobada(Long codigoLineaNegocio, Date fechaInicio,
			Date fechaFin) throws AplicacionException {
		Notificaciondiaria notificaciondiaria = null;
		try {
			String queryStr = "SELECT nd FROM Notificaciondiaria as nd WHERE "
					+ " nd.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio AND "
					+ " estadonotificacion.pkCodigoEstadonotificacion = :estado AND "
					+ " nd.fechaNotificaciondiaria >= :fechaInicio AND nd.fechaNotificaciondiaria < :fechaFin "
					+ " ORDER BY nd.fechaNotificaciondiaria ASC ";

			Query query = Querier.query(queryStr);

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);
			query.setLong("estado", Long.valueOf(3));

			query.setMaxResults(1);
			notificaciondiaria = (Notificaciondiaria) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}

		return notificaciondiaria;
	}

	public static List<Puestotrabajo> obtenerPuestostrabajosPorFechaYVariosFiltros(Long codigoUnidad, Long codigoSociedad,
			Long codigoDivision, Date fechaInicio, Date fechaFin, Long codigoPuestoTrabajo, Long codigoProducto)
			throws AplicacionException {
		List<Puestotrabajo> puestosTrabajo = null;
		try {
			StringBuilder queryStr = new StringBuilder(
					"SELECT DISTINCT(np.puestotrabajo) FROM Notificacionproduccion np"
							+ " WHERE (np.notificaciondiaria.fechaNotificaciondiaria >= :fechaInicio AND np.notificaciondiaria.fechaNotificaciondiaria <= :fechaFin)");
			if (codigoUnidad != null && codigoUnidad > 0)
				queryStr.append(" AND np.notificaciondiaria.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");
			if (codigoSociedad != null && codigoSociedad > 0)
				queryStr.append(" AND np.notificaciondiaria.lineanegocio.unidad.sociedad.pkCodigoSociedad = :codigoSociedad");
			if (codigoDivision != null && codigoDivision > 0)
				queryStr.append(" AND np.notificaciondiaria.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :codigoDivision");
			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				queryStr.append(" AND np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo");
			if (codigoProducto != null && codigoProducto > 0)
				queryStr.append(" AND np.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");

			queryStr.append(" Order by np.puestotrabajo.nombrePuestotrabajo");

			Query query = Querier.query(queryStr.toString());

			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);

			if (codigoUnidad != null && codigoUnidad > 0)
				query.setLong("codigoUnidad", codigoUnidad);
			if (codigoSociedad != null && codigoSociedad > 0)
				query.setLong("codigoSociedad", codigoSociedad);
			if (codigoDivision != null && codigoDivision > 0)
				query.setLong("codigoDivision", codigoDivision);
			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			if (codigoProducto != null && codigoProducto > 0)
				query.setLong("codigoProducto", codigoProducto);

			puestosTrabajo = query.list();

		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			loggerQ.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			loggerQ.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}
		return puestosTrabajo;
	}

}
