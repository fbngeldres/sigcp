package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: CubicacionProductoQuerier.java
 * Modificado: Jun 8, 2010 11:23:49 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class CubicacionProductoQuerier extends Querier implements ConstantesLogicaNegocio {

	public static final String CODIGO_LINEA_NEGOCIO = "lineanegocio.pkCodigoLineanegocio";
	public static final String CODIGO_PROCESO = "produccion.proceso.pkCodigoProceso";
	public static final String CODIGO_PRODUCTO = "produccion.producto.pkCodigoProducto";
	public static final String CODIGO_ESTADO_CUBICACION = "estadocubicacion.pkCodigoEstadocubicacion";
	public static final String NOMBRE_ESTADO_CUBICACION = "estadocubicacion.nombreEstadocubicacion";
	public static final String ANIO = "anoCubicacionproducto";
	public static final String MES = "mesCubicacionproducto";

	/**
	 * Metodo que permite filtrar la lista de CubicacionProducto segun un
	 * conjunto de propiedades simultáneamente
	 * 
	 * @param propiedades
	 * @return
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 * @throws AplicacionException
	 */
	public static List<Cubicacionproducto> buscarPorPropiedades(Map<String, Object> propiedades)
			throws ElementoNoEncontradoException, SesionVencidaException, EntornoEjecucionException, AplicacionException {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" FROM Cubicacionproducto As cub WHERE ");

		String caracterPuntoRegex = "\\.";
		String cadenaVacia = "";

		for (Iterator<String> iterator = propiedades.keySet().iterator(); iterator.hasNext();) {
			String clausulaWhere = " cub.{0} = :{1} {2} ";
			String clave = iterator.next();
			String clausulaAnd = iterator.hasNext() ? " AND " : cadenaVacia;
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
		} catch (UnresolvableObjectException uOException) {
			logger.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(ERROR_HIBERNATE, hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

	}

	public static Cubicacionproducto getById(Long codigoCubicacionProducto) throws ElementoNoEncontradoException,
			SesionVencidaException, EntornoEjecucionException {
		return (Cubicacionproducto) Querier.getById(Cubicacionproducto.class, codigoCubicacionProducto);
	}

	public static void save(Cubicacionproducto cubicacionproducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(cubicacionproducto);
	}

	public static Cubicacionproducto obtenerPorPropiedadesYEdoDistintoDeAnulado(long codigoLineaNegocio, long codigoProducto,
			long codigoProceso, Date fechaCubicacion) throws ElementoNoEncontradoException, AplicacionException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaCubicacion);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" FROM Cubicacionproducto As cub WHERE ");
		queryStr.append("cub.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio AND ");
		queryStr.append("cub.produccion.producto.pkCodigoProducto = :codigoProducto AND ");
		queryStr.append("cub.produccion.proceso.pkCodigoProceso = :codigoProceso AND ");
		queryStr.append("cub.mesCubicacionproducto = :mes AND ");
		// Agrego la validadcion del anio
		queryStr.append("cub.anoCubicacionproducto = :anio AND ");
		queryStr.append("lower(cub.estadocubicacion.nombreEstadocubicacion) != :estadoAnulado ");

		try {
			String consulta = queryStr.toString();
			Session session = getSession();
			Query query = session.createQuery(consulta);
			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setLong("codigoProducto", codigoProducto);
			query.setLong("codigoProceso", codigoProceso);
			query.setShort("mes", mes.shortValue());
			// Agrego la validacion del Año
			query.setInteger("anio", anio);
			query.setString("estadoAnulado",
					ManejadorPropiedades.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_ANULADO).toLowerCase());

			return (Cubicacionproducto) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			logger.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(ERROR_HIBERNATE, hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Metodo que permite consultar la cubicacion de producto que no se
	 * encuentran en medios de almacenamiento particular (ejemplo Silo) y para
	 * un estado definido (Generado/Aprobado), para productos no terminados
	 * 
	 * @param codigoProduccion
	 * @param mesContable
	 * @param anioContable
	 * @param estadoCubicacionProducto
	 * @param tipoAlmacenamiento
	 */
	@SuppressWarnings("unchecked")
	public static List<Cubicacion> obtenerCubicacionProducto(Long codigoProducto, Short mesContable, Integer anioContable,
			String estadoCubicacionProducto) {
		String mensajeError = "";
		List<Cubicacion> cubicaciones = new ArrayList<Cubicacion>();
		// 297_47637
		try {

			StringBuilder queryBld = new StringBuilder("FROM Cubicacion cb WHERE ");
			queryBld.append("cb.cubicacionproducto.mesCubicacionproducto = :mes");
			queryBld.append(" AND cb.cubicacionproducto.anoCubicacionproducto = :anno");
			if (estadoCubicacionProducto != null) {
				queryBld.append(" AND cb.cubicacionproducto.estadocubicacion.nombreEstadocubicacion = :estado");
			} else {
				queryBld.append(" AND cb.cubicacionproducto.estadocubicacion.nombreEstadocubicacion <> :estado");
			}
			if (codigoProducto != null) {
				queryBld.append(" AND cb.cubicacionproducto.produccion.producto.pkCodigoProducto = :producto");
			}
			queryBld.append(" order by cb.cubicacionproducto.produccion.producto.nombreProducto asc, cb.medioalmacenamiento.numeroMedioalmacenamiento asc");

			Query query = Querier.query(queryBld.toString());

			query.setShort("mes", mesContable);
			query.setInteger("anno", anioContable);
			if (estadoCubicacionProducto != null) {
				query.setString("estado", estadoCubicacionProducto);
			} else {
				String estadoAnulado = ManejadorPropiedades.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_ANULADO);
				query.setString("estado", estadoAnulado);
			}
			if (codigoProducto != null) {
				query.setLong("producto", codigoProducto);
			}

			cubicaciones = query.list();

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

		return cubicaciones;
	}

	/**
	 * Metodo que permite consultar la cubicacion de producto que no se
	 * encuentran en medios de almacenamiento particular (ejemplo Silo) y para
	 * un estado definido (Generado/Aprobado), para productos no terminados
	 * 
	 * @param codigoProduccion
	 * @param mesContable
	 * @param anioContable
	 * @param estadoCubicacionProducto
	 * @param tipoAlmacenamiento
	 */
	@SuppressWarnings("unchecked")
	public static double obtenerStockFisicoProductoCubicado(Long codigoProducto, Short mesContable, Integer anioContable,
			String estadoCubicacionProducto) {
		double stockFisico = 0d;

		List<Cubicacion> cubicaciones = CubicacionProductoQuerier.obtenerCubicacionProducto(codigoProducto, mesContable,
				anioContable, estadoCubicacionProducto);

		for (int indice = 0; indice < cubicaciones.size(); indice++) {
			Cubicacion cubicacion = cubicaciones.get(indice);
			Medioalmacenamiento medioalmacenamiento = cubicacion.getMedioalmacenamiento();
			Double densidad = medioalmacenamiento.getDensidadMedioalmacenamiento();
			densidad = densidad == null ? 1D : densidad;
			double valorTm = cubicacion.getVolumenM3Cubicacion() * densidad;
			stockFisico += valorTm;
		}

		return stockFisico;
	}

	/**
	 * Metodo que te permite obtener las cubicaciones de una mes y año
	 * determinado
	 * 
	 * @param estadoCubicacion
	 */
	public static List<Cubicacion> getCubicaciones(Integer anio, Short mes, Long estadoCubicacion) {

		StringBuilder sql = new StringBuilder("FROM Cubicacion c ");
		sql.append(" WHERE ");
		sql.append(" c.cubicacionproducto.mesCubicacionproducto= :mes AND ");
		sql.append(" c.cubicacionproducto.anoCubicacionproducto =:anio AND ");
		if (estadoCubicacion != null) {
			sql.append(" c.cubicacionproducto.estadocubicacion.pkCodigoEstadocubicacion=:estado ");
		} else {
			sql.append(" c.cubicacionproducto.estadocubicacion.pkCodigoEstadocubicacion<>:estado ");
		}
		sql.append(" ORDER BY  c.cubicacionproducto.produccion.producto.nombreProducto,");
		sql.append(" c.medioalmacenamiento.nombreMedioalmacenamiento");
		Query query = query(sql.toString());
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		if (estadoCubicacion != null) {
			query.setLong("estado", estadoCubicacion);
		} else {
			query.setLong("estado", Long.valueOf(3));
		}

		return query.list();
	}
}
