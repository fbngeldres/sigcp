package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Clasificaciontipomovimiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: MovimientoQuerier.java
 * Modificado: May 26, 2010 8:32:07 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class MovimientoQuerier extends Querier implements ConstantesLogicaNegocio {

	private static final String NOMBRE_CLASE_MOVIMIENTO = Movimiento.class.getSimpleName();
	private static final String ATRB_PK_CODIGO_PRODUCTO = "pkCodigoProducto";
	private static final String ATRB_FECHA_MOVIMIENTO = "fechaMovimiento";
	public static final String CODIGO_TIPO_MOVIMIENTO = "tipomovimiento.pkCodigoTipomovimiento";

	private static Logger log = Logger.getLogger(MovimientoQuerier.class);
	private static String mensajeError;

	public static Double obtenerMovimientoPorProductoXFechaXClasificacion(Long codigoClasificacion, Long codigoProducto,
			Date fecha) throws AplicacionException {
		Double ValorMovimientos = null;
		String mensajeError = null;
		StringBuilder queryStr = new StringBuilder("SELECT COALESCE(sum(m.cantidadMovimiento),0) FROM Movimiento m WHERE ");
		queryStr.append("m.tipomovimiento.clasificaciontipomovimiento.pkCodigoClasificaciontipomovi = :codClasificacion AND ");
		queryStr.append("m.producto.pkCodigoProducto = :codProducto AND ");
		queryStr.append("m.fechaMovimiento = :fecha");

		String queryStrTemp = queryStr.toString();
		try {
			Query query = Querier.query(queryStrTemp);
			query.setLong("codClasificacion", codigoClasificacion);
			query.setLong("codProducto", codigoProducto);
			query.setDate("fecha", fecha);

			ValorMovimientos = (Double) query.uniqueResult();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_QUERY_FALLO, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_USO_SESION_INAPROPIADA, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_COMUNICACION_FALLO, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}

		return ValorMovimientos;
	}

	public static Double obtenerMovimientoPorProductoPorRangoFechaXClasificacion(Long codigoClasificacion, Long codigoProducto,
			int anio, Short mes) throws AplicacionException {
		Double ValorMovimientos = null;
		String mensajeError = null;
		StringBuilder queryStr = new StringBuilder("SELECT COALESCE(sum(m.cantidadMovimiento),0) FROM Movimiento m WHERE ");
		queryStr.append("m.tipomovimiento.clasificaciontipomovimiento.pkCodigoClasificaciontipomovi = :codClasificacion AND ");
		queryStr.append("m.producto.pkCodigoProducto = :codProducto AND ");
		queryStr.append("date_part('year',m.fechaMovimiento) = :anio AND ");
		queryStr.append("date_part('month',m.fechaMovimiento) = :mes ");

		String queryStrTemp = queryStr.toString();
		try {
			Query query = Querier.query(queryStrTemp);
			query.setLong("codClasificacion", codigoClasificacion);
			query.setLong("codProducto", codigoProducto);
			query.setInteger("anio", anio);
			query.setShort("mes", mes);

			ValorMovimientos = (Double) query.uniqueResult();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_QUERY_FALLO, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_USO_SESION_INAPROPIADA, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_COMUNICACION_FALLO, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}

		return ValorMovimientos;
	}

	@SuppressWarnings("unchecked")
	public static List<Movimiento> obtenerMovimientoPorProductoYRangoFechas(Long codigoProducto, Date fechaIni, Date fechafin,
			Long codigoMedAlman, Long codigoAlmacen) throws AplicacionException {
		List<Movimiento> movimientos = null;
		String mensajeError = null;
		StringBuilder queryStr = new StringBuilder("FROM {0} AS m WHERE ");
		queryStr.append("m.producto.{1} = :codProducto AND ");
		queryStr.append("m.{2} >= :fechaIni AND m.{2} <= :fechaFin ");
		if (codigoAlmacen != null) {
			queryStr.append(" AND m.medioalmacenamiento.ubicacion.almacen.pkCodigoAlmacen = :codAlman  ");
		}
		if (codigoMedAlman != null) {
			queryStr.append(" AND m.medioalmacenamiento.pkCodigoMedioalmacenamiento = :codMedAlman  ");
		}
		queryStr.append(" Order By m.{2} {3} ");
		// String queryStr = "FROM {0} AS m WHERE m.producto.{1} = :codProducto
		// AND m.{2} >= :fechaIni AND m.{2} <= :fechaFin Order By m.{2} {3}";
		String queryStrTemp = queryStr.toString();
		queryStrTemp = MessageFormat.format(queryStrTemp, new Object[] { NOMBRE_CLASE_MOVIMIENTO, ATRB_PK_CODIGO_PRODUCTO,
				ATRB_FECHA_MOVIMIENTO, ASC });
		try {
			Query query = Querier.query(queryStrTemp);
			query.setLong("codProducto", codigoProducto);
			query.setDate("fechaIni", fechaIni);
			query.setDate("fechaFin", fechafin);
			if (codigoMedAlman != null) {
				query.setLong("codMedAlman", codigoMedAlman);
			}
			if (codigoAlmacen != null) {
				query.setLong("codAlman", codigoAlmacen);
			}
			movimientos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_QUERY_FALLO, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_USO_SESION_INAPROPIADA, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(ERROR_COMUNICACION_FALLO, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}

		return movimientos;
	}

	public static List<Movimiento> obtenerMovimientosPorFiltros(Map<String, Object> propiedades) throws AplicacionException {
		try {
			return Querier.findByProperties(Movimiento.class, propiedades);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Movimiento> obtenerMovimientosPorVarios(Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio,
			Long codigoTipoDocumento, Long codigoAlmacen, Long codigoUbicacion, Long codigoProducto, Date fechaInicio,
			Date fechaFin) throws EntornoEjecucionException {

		try {
			StringBuilder queryString = new StringBuilder("select m" + " from Movimiento m"
					+ " where m.documentomaterial.sociedad = :codigoSociedad"
					+ " and m.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");

			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				queryString.append(" and m.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
			if (codigoAlmacen != null && codigoAlmacen.longValue() > 0)
				queryString.append(" and m.ubicacionByFkCodigoUbicacionOrigen.almacen.pkCodigoAlmacen = :codigoAlmacen");
			if (codigoUbicacion != null && codigoUbicacion.longValue() > 0)
				queryString.append(" and m.ubicacionByFkCodigoUbicacionOrigen.pkCodigoUbicacion = :codigoUbicacion");
			if (codigoProducto != null && codigoProducto.longValue() > 0)
				queryString.append(" and m.producto.pkCodigoProducto = :codigoProducto");
			if (codigoTipoDocumento != null && codigoTipoDocumento.longValue() > 0)
				queryString
						.append(" and m.documentomaterial.tipodocumentomaterial.pkCodigoTipodocumentomaterial = :codigoTipoDocumento");
			if (fechaInicio != null && fechaFin != null) {
				queryString.append(" and m.fechaMovimiento >= :fechaInicio" + " and m.fechaMovimiento <= :fechaFin");
			}

			Query query = Querier.query(queryString.toString());
			query.setLong("codigoSociedad", codigoSociedad);
			query.setLong("codigoUnidad", codigoUnidad);
			if (fechaInicio != null && fechaFin != null) {
				query.setDate("fechaInicio", fechaInicio);
				query.setDate("fechaFin", fechaFin);
			}
			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			if (codigoAlmacen != null && codigoAlmacen.longValue() > 0)
				query.setLong("codigoAlmacen", codigoAlmacen);
			if (codigoUbicacion != null && codigoUbicacion.longValue() > 0)
				query.setLong("codigoUbicacion", codigoUbicacion);
			if (codigoProducto != null && codigoProducto.longValue() > 0)
				query.setLong("codigoProducto", codigoProducto);
			if (codigoTipoDocumento != null && codigoTipoDocumento.longValue() > 0)
				query.setLong("codigoTipoDocumento", codigoTipoDocumento);

			return query.list();
		} catch (HibernateException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Movimiento> obtenerMovimientosPorVariosTransferencia(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoTipoDocumento, Long codigoAlmacen, Long codigoUbicacion, Long codigoProducto,
			Date fechaInicio, Date fechaFin) throws EntornoEjecucionException {

		try {
			StringBuilder queryString = new StringBuilder("select m" + " from Movimiento m"
					+ " where m.documentomaterial.sociedad = :codigoSociedad"
					+ " and m.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");

			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				queryString.append(" and m.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
			if (codigoAlmacen != null && codigoAlmacen.longValue() > 0)
				queryString.append(" and m.ubicacionByFkCodigoUbicacionOrigen.almacen.pkCodigoAlmacen = :codigoAlmacen");
			if (codigoUbicacion != null && codigoUbicacion.longValue() > 0)
				queryString.append(" and m.ubicacionByFkCodigoUbicacionOrigen.pkCodigoUbicacion = :codigoUbicacion");
			if (codigoProducto != null && codigoProducto.longValue() > 0)
				queryString.append(" and m.producto.pkCodigoProducto = :codigoProducto");
			if (codigoTipoDocumento != null && codigoTipoDocumento.longValue() > 0)
				queryString
						.append(" and m.documentomaterial.tipodocumentomaterial.pkCodigoTipodocumentomaterial = :codigoTipoDocumento");
			if (fechaInicio != null && fechaFin != null) {
				queryString.append(" and m.fechaMovimiento >= :fechaInicio" + " and m.fechaMovimiento <= :fechaFin");
			}

			Query query = Querier.query(queryString.toString());
			query.setLong("codigoSociedad", codigoSociedad);
			query.setLong("codigoUnidad", codigoUnidad);
			if (fechaInicio != null && fechaFin != null) {
				query.setDate("fechaInicio", fechaInicio);
				query.setDate("fechaFin", fechaFin);
			}
			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			if (codigoAlmacen != null && codigoAlmacen.longValue() > 0)
				query.setLong("codigoAlmacen", codigoAlmacen);
			if (codigoUbicacion != null && codigoUbicacion.longValue() > 0)
				query.setLong("codigoUbicacion", codigoUbicacion);
			if (codigoProducto != null && codigoProducto.longValue() > 0)
				query.setLong("codigoProducto", codigoProducto);
			if (codigoTipoDocumento != null && codigoTipoDocumento.longValue() > 0)
				query.setLong("codigoTipoDocumento", codigoTipoDocumento);

			return query.list();
		} catch (HibernateException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	// Inicio Query Tabla Valor
	@SuppressWarnings("unchecked")
	public static List<Movimiento> obtenerMovimientosPorVariosTransferenciaTV(Long codigoSociedad, Long codigoDivision,
			String codigoSapProducto, Long codigoTipoDocumento, Short mes, Integer anio, boolean productoSinDiferenciar)
			throws EntornoEjecucionException {
		System.out.println("---> " + productoSinDiferenciar + " __ " + codigoSapProducto);
		try {
			StringBuilder queryString = new StringBuilder("select m" + " from Movimiento m"
					+ " where m.documentomaterial.sociedad.pkCodigoSociedad = :codigoSociedad"
					+ " and m.documentomaterial.sociedad.division.pkCodigoDivision = :codigoDivision");

			if (!productoSinDiferenciar) {
				queryString.append(" and m.codigoSapproductoMovimiento = :codigoSapProducto");
			} else {
				queryString.append(" and m.producto.codigoSapProducto = :codigoSapProducto");
			}

			queryString
					.append(" and m.documentomaterial.tipodocumentomaterial.pkCodigoTipodocumentomaterial = :codigoTipoDocumento");
			queryString.append(" AND date_part('month',m.fechaMovimiento)= :mes");
			queryString.append(" AND date_part('year',m.fechaMovimiento)= :anio");

			Query query = Querier.query(queryString.toString());

			query.setLong("codigoSociedad", codigoSociedad);
			query.setLong("codigoDivision", codigoDivision);
			query.setString("codigoSapProducto", codigoSapProducto);
			query.setLong("codigoTipoDocumento", codigoTipoDocumento);
			query.setShort("mes", mes);
			query.setInteger("anio", anio);

			return query.list();

		} catch (HibernateException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	// Fin Query Tabla Valor
	/**
	 * Método para insertar un movimiento en la BD.
	 * 
	 * @param movimiento
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Movimiento movimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(movimiento);
	}

	/**
	 * Método para modificar un movimiento de la BD.
	 * 
	 * @param movimiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Movimiento movimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(movimiento);
	}

	/**
	 * Método para eliminar un movimiento de la BD.
	 * 
	 * @param movimiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Movimiento movimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(movimiento);
	}

	@SuppressWarnings("unchecked")
	public static List<Movimiento> obtenerCantidadSegunLineaNegProductoFecha(Lineanegocio lineanegocio, Producto producto,
			Date fechaNotf) throws EntornoEjecucionException, SesionVencidaException {
		StringBuilder hql = new StringBuilder("FROM Movimiento AS m WHERE ");
		hql.append("m.producto.pkCodigoProducto = :codProducto AND ");
		hql.append("m.fechaMovimiento = :fecha AND ");
		hql.append("m.lineanegocio.pkCodigoLineanegocio = :lineanegocio");

		try {
			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", producto.getPkCodigoProducto());
			query.setLong("lineanegocio", lineanegocio.getPkCodigoLineanegocio());
			query.setDate("fecha", fechaNotf);

			return query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	public static double obtenerCantidadSegunLineaNegProductoFechaYTipo(Producto producto,
			Date fechaNotf, Long clasifMov, String[] codigosTraslado) throws ElementoNoEncontradoException,
			EntornoEjecucionException {
		StringBuilder hql = new StringBuilder("SELECT sum(m.cantidadMovimiento) FROM Movimiento AS m WHERE ");
		hql.append("m.producto.pkCodigoProducto = :codProducto AND m.fechaMovimiento = :fecha AND ");
		hql.append("m.tipomovimiento.clasificaciontipomovimiento.pkCodigoClasificaciontipomovi = :clasifMov");
		hql.append(" AND m.tipomovimiento.codigoSapTipomovimiento NOT IN (:codigoTraslado) ");

		try {
			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", producto.getPkCodigoProducto());
			query.setDate("fecha", fechaNotf);
			query.setLong("clasifMov", clasifMov);
			query.setParameterList("codigoTraslado", codigosTraslado);

			Double movDesp = (Double) query.uniqueResult();
			return movDesp == null ? 0d : movDesp;
		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO), e);
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO), e);
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), e);
		}

	}

	@SuppressWarnings("unchecked")
	public static Double obtenerMovProProductoFechaYMedioAlmc(Lineanegocio lineanegocio, Producto producto,
			Medioalmacenamiento medioAlmac, Date fechaNotif) throws ElementoNoEncontradoException, EntornoEjecucionException {
		StringBuilder hql = new StringBuilder("FROM Movimiento AS m WHERE ");
		hql.append("m.lineanegocio.pkCodigoLineanegocio = :lineaNeg AND ");
		hql.append("m.medioalmacenamiento.pkCodigoMedioalmacenamiento = :medioAlmc AND ");
		hql.append("m.producto.pkCodigoProducto = :codProducto AND m.fechaMovimiento = :fecha");

		try {
			Query query = Querier.query(hql.toString());
			query.setParameter("lineaNeg", lineanegocio.getPkCodigoLineanegocio());
			query.setParameter("medioAlmc", medioAlmac.getPkCodigoMedioalmacenamiento());
			query.setParameter("codProducto", producto.getPkCodigoProducto());
			query.setDate("fecha", fechaNotif);

			List<Movimiento> movimientos = query.list();

			Double cantMov = 0d;

			String clasifMovIngreso = ManejadorPropiedades.obtenerPropiedadPorClave(NOMBRE_CLASIFICACION_MOVIMIENTO_INGRESO)
					.toLowerCase();

			for (Movimiento movimiento : movimientos) {
				Clasificaciontipomovimiento clasificaciontipomovimiento = movimiento.getTipomovimiento()
						.getClasificaciontipomovimiento();
				String clasificacionLower = clasificaciontipomovimiento.getNombreClasificaciontipomovimie().toLowerCase();

				if (clasificacionLower.equals(clasifMovIngreso)) {
					cantMov += movimiento.getCantidadMovimiento().doubleValue();
				} else {
					cantMov -= movimiento.getCantidadMovimiento().doubleValue();
				}
			}

			return cantMov;
		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO), e);
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO), e);
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static boolean verificarMovimientosAutomaticosEnFecha(Date fechaNotif) throws ParametroInvalidoException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(m.pkCodigoMovimiento) FROM Movimiento AS m WHERE ");
		queryBuilder.append("m.documentomaterial.fechaDocumentomaterial = :fecha AND ");
		queryBuilder.append("m.documentomaterial.origenSapMovimiento = :origenSapMovimiento");

		Session session = null;

		String mensajeError;
		try {
			session = PersistenciaFactory.currentSession();

			Query query = query(queryBuilder.toString());

			query.setDate("fecha", fechaNotif);
			query.setBoolean("origenSapMovimiento", new Boolean(true));

			Long result = (Long) query.uniqueResult();

			return result.longValue() > 0;

		} catch (PropertyValueException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, e);
			throw new ParametroInvalidoException(mensajeError, e);
		} catch (ObjectDeletedException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ELIMINADO);
			logger.error(mensajeError, e);
			throw new ElementoEliminadoException(mensajeError, e);
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	public static void eliminarPorFechaYMovimientoSAP(Date fecha, boolean movimientoSAP) {
		StringBuilder querystr = new StringBuilder("DELETE FROM Movimiento m ");
		querystr.append(" WHERE m.fechaMovimiento= :fecha  AND m.documentomaterial in ( SELECT dm FROM Documentomaterial dm WHERE dm.fechaDocumentomaterial=:fecha");
		querystr.append(" AND dm.origenSapMovimiento=:condicion ) ");
		Query query = Querier.query(querystr.toString());
		query.setDate("fecha", fecha);
		query.setBoolean("condicion", movimientoSAP);
		query.executeUpdate();

	}

	public static List<Movimiento> obtenerMovimientoPorRangoFechasProductoTipoMovimiento(Long codigoLineaNegocio, Integer annio,
			Short mes, String[] codigosSapTipoMovimiento, Long codProducto, String[] codigoSapProductoMovimiento)
			throws ParametroInvalidoException, ElementoEliminadoException, ElementoNoEncontradoException {
		List<Movimiento> movimientos = null;
		StringBuilder queryBuilder = new StringBuilder("FROM Movimiento AS m WHERE ");

		queryBuilder.append(" m.producto.pkCodigoProducto = :codProducto ");
		queryBuilder.append(" AND m.tipomovimiento.codigoSapTipomovimiento IN (:codigosSapTipoMovimiento)");
		queryBuilder.append(" AND m.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
		queryBuilder.append(" AND m.documentomaterial.periodocontable.anoPeriodocontable= :annio");
		queryBuilder.append(" AND m.documentomaterial.periodocontable.mesPeriodocontable= :mes");

		if (codigoSapProductoMovimiento != null) {
			queryBuilder.append(" AND TRIM(m.codigosapproductoMovimiento) IN (:codigoSapProductoMovimiento)");
		}

		String mensajeError;
		try {

			Query query = query(queryBuilder.toString());

			query.setLong("codProducto", codProducto);
			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setParameterList("codigosSapTipoMovimiento", codigosSapTipoMovimiento);
			query.setShort("mes", mes);
			query.setInteger("annio", annio);

			if (codigoSapProductoMovimiento != null) {
				query.setParameterList("codigoSapProductoMovimiento", codigoSapProductoMovimiento);
			}

			movimientos = query.list();

			return movimientos;

		} catch (PropertyValueException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, e);
			throw new ParametroInvalidoException(mensajeError, e);
		} catch (ObjectDeletedException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ELIMINADO);
			logger.error(mensajeError, e);
			throw new ElementoEliminadoException(mensajeError, e);
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	public static List<Movimiento> obtenerMovimientoPorDiaProductoTipoMovimiento(Long codigoLineaNegocio, Date fechaMovimiento,
			Long codProducto, String[] codigosSapTipoMovimiento) throws ParametroInvalidoException, ElementoEliminadoException,
			ElementoNoEncontradoException {
		List<Movimiento> movimientos = null;
		StringBuilder queryBuilder = new StringBuilder("FROM Movimiento AS m WHERE ");

		queryBuilder.append(" m.producto.pkCodigoProducto = :codProducto ");
		queryBuilder.append(" AND m.tipomovimiento.codigoSapTipomovimiento IN (:codigosSapTipoMovimiento)");
		queryBuilder.append(" AND m.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
		queryBuilder.append(" AND m.fechaMovimiento = :fechaMovimiento");

		String mensajeError;
		try {

			Query query = query(queryBuilder.toString());

			query.setLong("codProducto", codProducto);
			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setParameterList("codigosSapTipoMovimiento", codigosSapTipoMovimiento);
			query.setDate("fechaMovimiento", fechaMovimiento);

			movimientos = query.list();

			return movimientos;

		} catch (PropertyValueException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, e);
			throw new ParametroInvalidoException(mensajeError, e);
		} catch (ObjectDeletedException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ELIMINADO);
			logger.error(mensajeError, e);
			throw new ElementoEliminadoException(mensajeError, e);
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}
}
