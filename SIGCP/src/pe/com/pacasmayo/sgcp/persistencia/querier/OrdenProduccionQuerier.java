package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccionQuerier.java
 * Modificado: Feb 1, 2010 12:30:35 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class OrdenProduccionQuerier extends Querier implements ConstantesLogicaNegocio {

	public static final String CODIGO_ESTATUS_ORDEN = "estadoordenproduccion.pkCodigoEstadoorden";
	public static final String CODIGO_PROCESO = "produccion.proceso.pkCodigoProceso";
	public static final String CODIGO_PRODUCTO = "producto.pkCodigoProducto";

	public static final String MES = "mesOrdenproduccion";
	public static final String CODIGO_PRODUCCION = "produccion.pkProduccion";
	public static final String NOMBRE_CLASE_ORDEN_PRODUCCION = Ordenproduccion.class.getSimpleName();
	private static String mensajeError;

	/**
	 * Metodo para obtener todas las Ordenproduccion
	 * 
	 * @return
	 */
	public static List<Ordenproduccion> getAll() throws EntornoEjecucionException, SesionVencidaException {

		return Querier.getAll(Ordenproduccion.class);
	}

	/**
	 * Metodo para obtener todas las Ordenproduccion ordenadas por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Ordenproduccion> getAllOrderBy(String order) {

		return Querier.getAll(Ordenproduccion.class, order);
	}

	/**
	 * Metodo para obtener una Ordenproduccion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Ordenproduccion getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {
		return Querier.getById(Ordenproduccion.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> getByProduccion(Long codigoProduccion) throws EntornoEjecucionException,
			SesionVencidaException, RuntimeException {

		String consulta = "from Ordenproduccion ordenprod " + "where ordenprod.produccion.pkProduccion = ? ";

		Query query = Querier.query(consulta);
		query.setLong(0, codigoProduccion);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> getByProduccionYMes(Long codigoProduccion, Short mes) throws AplicacionException {

		try {
			String consulta = "from Ordenproduccion ordenprod "
					+ "where ordenprod.produccion.pkProduccion = ? and ordenprod.mesOrdenproduccion = ? ";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoProduccion);
			query.setShort(1, mes);

			return query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Ordenproduccion> findByCodigoProducto(Long codigoProd) throws AplicacionException {
		try {
			return Querier.findByProperty(Ordenproduccion.class, CODIGO_PRODUCTO, codigoProd);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Metodo para Insertar una Orden de produccion en la BD.
	 * 
	 * @param ordenProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 * @throws ConstrainViolationException
	 */
	public static void save(Ordenproduccion ordenProduccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.save(ordenProduccion);
	}

	/**
	 * Metodo para modificar una Orden de produccion en la BD.
	 * 
	 * @param ordenProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Ordenproduccion ordenProduccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(ordenProduccion);
	}

	/**
	 * Metodo para eliminar una Orden de produccion en la BD.
	 * 
	 * @param ordenProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Ordenproduccion ordenProduccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.delete(ordenProduccion);
	}

	/**
	 * Metodo para insertar o actualizar si ya existe un Ordenproduccionplan en
	 * la BD.
	 * 
	 * @param ordenProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 * @throws Exception
	 */
	public static void saveOrUpdate(Ordenproduccion ordenProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException, Exception {

		Querier.saveOrUpdate(ordenProduccion);
	}

	public static List<Ordenproduccion> findByProperties(Map<?, ?> propiedades) {
		return Querier.findByProperties(Ordenproduccion.class, propiedades);
	}

	public static Ordenproduccion getByMesYProduccion(short mes, Long codigoProduccion) throws AplicacionException {

		String consulta = "from {0} As op where op.{1} = :mes AND op.{2} = :codProduccion "
				+ "AND op.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :estaHojaruta";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_ORDEN_PRODUCCION, MES, CODIGO_PRODUCCION });

		try {
			Query query = Querier.query(consulta);
			query.setShort("mes", mes);
			query.setLong("codProduccion", codigoProduccion);
			query.setLong("estaHojaruta", new Long(1)); // Estado Activa

			return (Ordenproduccion) query.uniqueResult();

		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Ordenproduccion> findByMes(Short mesOrdenProduccion) throws AplicacionException {
		try {
			return Querier.findByProperty(Ordenproduccion.class, MES, mesOrdenProduccion);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Busca una orden con estado liberado, para el mes indicado en el anio
	 * actual, ya sea uan orden automatica o manual. Para las ordenes
	 * automaticas busca que el estado del plan sea aprobado
	 * 
	 * @param mes
	 * @param codigoProducto
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> getByMesYProductoAnioActualEstadoPlan(short mes, Long codigoProducto)
			throws AplicacionException {

		Calendar cal = Calendar.getInstance();
		Integer anio = cal.get(Calendar.YEAR);
		Long codigoEstadoLiberado = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(ConstantesLogicaNegocio.CODIGO_ESTADO_ORDEN_PRODUCCION_LIBERADA));

		String nombreEstadoLiberadoPlan = (ManejadorPropiedades
				.obtenerPropiedadPorClave(ConstantesMensajeAplicacion.ESTADO_PLAN_ANUAL_APROBADO)).trim();

		String consulta = "from Ordenproduccion op where op.mesOrdenproduccion = :mes"
				+ " and op.produccion.producto.pkCodigoProducto = :codProducto"
				+ " and op.estadoordenproduccion.pkCodigoEstadoorden = :codigoEstadoLiberado"
				+ " and op.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :codigoEstadoHR"

				+ " OR (op.pkCodigoOrdenproduccion in (select opp.ordenproduccion.pkCodigoOrdenproduccion"
				+ " from Ordenproduccionplan opp where opp.plananual.annoPlananual = :anioactual"
				+ " and lower(opp.plananual.estadoplananual.nombreEstadoplan) = lower(:nombreEstadoLiberadoPlan))"

				+ " OR op.pkCodigoOrdenproduccion in (select opm.ordenproduccion.pkCodigoOrdenproduccion"
				+ " from Ordenproduccionmanual opm where opm.annoOrdenproduccionmanual = :anioactualM))";

		try {
			Query query = Querier.query(consulta);
			query.setShort("mes", mes);
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoEstadoLiberado", codigoEstadoLiberado);
			query.setInteger("anioactual", anio);
			query.setString("nombreEstadoLiberadoPlan", nombreEstadoLiberadoPlan);
			query.setInteger("anioactualM", anio);

			query.setLong("codigoEstadoHR", 1L); // se incluyó el estado de
			// hoja de ruta activa

			// TODO: De unique result se cambio a lista. Esto viene dado porque
			// el clinker ahora puede pertenecer a dos Ordenes de Produccion
			// Se devolverá una lista de ordenes de producción y el
			// desarrollador deberá elegir la primera hasta que sepan que curso
			// de acción tomar.
			return query.list();

		} catch (UnresolvableObjectException uOException) {
			logger.error(uOException);
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Busca una orden con estado liberado, para el mes indicado en el anio
	 * actual, ya sea uan orden automatica o manual
	 * 
	 * @param mes
	 * @param codigoProducto
	 * @return
	 * @throws AplicacionException
	 */
	public static Ordenproduccion getByMesProductoAnioActual(short mes, Long codigoProducto) throws AplicacionException {

		Calendar cal = Calendar.getInstance();
		Integer anio = cal.get(Calendar.YEAR);
		Long codigoEstadoLiberado = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(ConstantesLogicaNegocio.CODIGO_ESTADO_ORDEN_PRODUCCION_LIBERADA));

		Long estadoPlanAnual = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PLANIFICACION_CODIGO_ESTADO_PLAN_APROBADO));

		String consulta = "from Ordenproduccion op where op.mesOrdenproduccion = :mes"
				+ " and op.produccion.producto.pkCodigoProducto = :codProducto"
				+ " and op.estadoordenproduccion.pkCodigoEstadoorden = :codigoEstadoLiberado"
				+ " and op.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :codigoEstadoHR"
				+ " and (op.pkCodigoOrdenproduccion in (select opp.ordenproduccion.pkCodigoOrdenproduccion"
				+ " from Ordenproduccionplan opp where opp.plananual.annoPlananual = :anioactual AND opp.plananual.estadoplananual.pkCodigoEstadoplananual = :edoPlanAnual)"
				+ " or op.pkCodigoOrdenproduccion in (select opm.ordenproduccion.pkCodigoOrdenproduccion"
				+ " from Ordenproduccionmanual opm where opm.annoOrdenproduccionmanual = :anioactualM))";

		try {
			Query query = Querier.query(consulta);
			query.setShort("mes", mes);
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoEstadoLiberado", codigoEstadoLiberado);
			query.setInteger("anioactual", anio);
			query.setInteger("anioactualM", anio);
			query.setLong("edoPlanAnual", estadoPlanAnual);

			query.setLong("codigoEstadoHR", 1L); // se incluyó el estado de
			// hoja de ruta activa

			return (Ordenproduccion) query.uniqueResult();

		} catch (UnresolvableObjectException uOException) {
			logger.error(uOException);
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> getByMesAnioYProduccion(Short mes, Long codigoProduccion, Integer annio)
			throws EntornoEjecucionException, SesionVencidaException {

		try {
			Query query = query("from Ordenproduccion op " + "where " + "op.mesOrdenproduccion = ? and "
					+ "op.produccion.pkProduccion = ? and " + "op.pkCodigoOrdenproduccion in "
					+ "(select opp.ordenproduccion.pkCodigoOrdenproduccion " + "from Ordenproduccionplan opp " + "where "
					+ "opp.plananual.annoPlananual = ? and " + "opp.plananual.estadoplananual.pkCodigoEstadoplananual = 2 ) "
					+ "or op.pkCodigoOrdenproduccion in " + "(select opm.ordenproduccion.pkCodigoOrdenproduccion " + "from "
					+ "Ordenproduccionmanual opm where opm.annoOrdenproduccionmanual = ?) ");

			query.setInteger(0, mes);
			query.setLong(1, codigoProduccion);
			query.setInteger(2, annio);
			query.setInteger(3, annio);
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
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static void deleteByAnnioMesProduccion(Short mesCorteVersionPlananual, Integer annoPlananual, Long codigoPlananual)
			throws ElementoEliminadoException {
		try {
			Query query = query("delete Ordenproduccion op where op.pkCodigoOrdenproduccion in (select opp.ordenproduccion.pkCodigoOrdenproduccion from Ordenproduccionplan opp where opp.plananual.annoPlananual = ? and opp.plananual.pkCodigoPlananual = ?) and op.mesOrdenproduccion > ?");

			query.setInteger(0, annoPlananual);
			query.setLong(1, codigoPlananual);
			query.setShort(2, mesCorteVersionPlananual);

			query.executeUpdate();
		} catch (HibernateException e) {
			logger.error(e);
			throw new ElementoEliminadoException("Ocurrió  un error intentando eliminar  la orden de producción");
		}

	}

	public static void deleteByOrdenProduccionPlan(Long codigoOrdenProduccionPlan) throws ElementoEliminadoException {

		try {
			Query query = query("delete Ordenproduccion op where op.pkCodigoOrdenproduccion in (select opp.ordenproduccion.pkCodigoOrdenproduccion from Ordenproduccionplan opp where opp.ordenproduccion.pkCodigoOrdenproduccion = ?)");
			query.setLong(0, codigoOrdenProduccionPlan);

			query.executeUpdate();
		} catch (HibernateException e) {
			logger.error(e);
			throw new ElementoEliminadoException("Ocurrió  un error intentando eliminar  la orden de producción");
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> getByMesLiberadaPlantilla(Integer mes, Integer annio, Long codigoPlantillaReporte,
			Long estadoPlanAnual) throws EntornoEjecucionException, SesionVencidaException {

		Long codigoEstadoLiberado = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(ConstantesLogicaNegocio.CODIGO_ESTADO_ORDEN_PRODUCCION_LIBERADA));

		try {

			StringBuilder queryBld = new StringBuilder(
					"SELECT DISTINCT(op) FROM Ordenproduccion op, Columnaplantillaproducto cpp");
			queryBld.append(" WHERE op.mesOrdenproduccion = :mes ");
			queryBld.append(" AND op.estadoordenproduccion.pkCodigoEstadoorden = :estadoOrden");
			queryBld.append(" AND op.produccion.producto.pkCodigoProducto = cpp.plantillaproducto.producto.pkCodigoProducto");
			queryBld.append(" AND cpp.plantillaproducto.plantillareporte.pkCodigoPlantillareporte = :codPlantReport");
			queryBld.append(" AND (");
			queryBld.append("op.pkCodigoOrdenproduccion IN (");
			queryBld.append(" SELECT opp.ordenproduccion.pkCodigoOrdenproduccion FROM Ordenproduccionplan opp");
			queryBld.append(" WHERE opp.plananual.annoPlananual = :anio");
			queryBld.append(" AND opp.plananual.estadoplananual.pkCodigoEstadoplananual = :edoPlanAnual)");
			queryBld.append(" OR op.pkCodigoOrdenproduccion IN (");
			queryBld.append(" SELECT opm.ordenproduccion.pkCodigoOrdenproduccion FROM Ordenproduccionmanual opm");
			queryBld.append(" WHERE opm.annoOrdenproduccionmanual = :anio)");
			queryBld.append(")");

			Query query = query(queryBld.toString());
			query.setInteger("mes", mes);
			query.setLong("estadoOrden", codigoEstadoLiberado);
			query.setLong("codPlantReport", codigoPlantillaReporte);
			query.setInteger("anio", annio);
			query.setLong("edoPlanAnual", estadoPlanAnual);

			List<Ordenproduccion> list = query.list();
			return list;

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
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	public static Ordenproduccion obtenerPorProductoMesYEstado(Integer mes, Long codigoProducto, Long codgoEstado,
			Long codigoLineaNeg, Integer anio) {
		String mensajeError = null;
		try {
			Long estadoHojaRutaActiva = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(ESTADO_HOJA_RUTA_ACTIVA));
			Long estadoPlanAnual = Long.parseLong(ManejadorPropiedades
					.obtenerPropiedadPorClave(PLANIFICACION_CODIGO_ESTADO_PLAN_APROBADO));

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select DISTINCT(op) FROM Ordenproduccion op, Ordenproduccionplan opp  WHERE ");
			queryStr.append("op.estadoordenproduccion.pkCodigoEstadoorden = :estadoLib AND ");
			queryStr.append("op.mesOrdenproduccion = :mes AND ");
			queryStr.append("op.produccion.producto.pkCodigoProducto = :codigoProdcuto AND ");
			queryStr.append("op.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio ");
			queryStr.append(" AND op.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :codigoEstadoHR");

			// Que se encuentre en el plan anul o sea manual
			queryStr.append(" AND op.pkCodigoOrdenproduccion = opp.ordenproduccion.pkCodigoOrdenproduccion");

			// Que el año del plan sea correponda con el año de la notificacion
			queryStr.append(" AND opp.plananual.annoPlananual = :anio");

			// Que el estado del plan anual sea probado
			queryStr.append(" AND opp.plananual.estadoplananual.pkCodigoEstadoplananual = :edoPlanAnual");

			Query query = Querier.query(queryStr.toString());

			query.setLong("estadoLib", codgoEstado);
			query.setShort("mes", mes.shortValue());
			query.setLong("codigoProdcuto", codigoProducto);
			query.setLong("codigoEstadoHR", estadoHojaRutaActiva);
			query.setLong("codigoLineaNegocio", codigoLineaNeg);
			query.setInteger("anio", anio);

			query.setLong("edoPlanAnual", estadoPlanAnual);

			Ordenproduccion ordenProduccion = (Ordenproduccion) query.uniqueResult();

			if (ordenProduccion != null) {
				return ordenProduccion;
			}

			queryStr = new StringBuilder();
			queryStr.append("Select DISTINCT(op) FROM Ordenproduccion op, Ordenproduccionmanual opm   WHERE");

			queryStr.append(" op.estadoordenproduccion.pkCodigoEstadoorden = :estadoLib");
			queryStr.append(" AND op.produccion.producto.pkCodigoProducto = :codigoProdcuto  ");
			queryStr.append(" AND op.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :codigoEstadoHR");
			queryStr.append(" AND op.mesOrdenproduccion = :mes");
			queryStr.append(" AND op.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
			queryStr.append(" AND op.pkCodigoOrdenproduccion = opm.ordenproduccion.pkCodigoOrdenproduccion");
			queryStr.append(" AND opm.annoOrdenproduccionmanual = :anio");

			query = Querier.query(queryStr.toString());

			query.setLong("estadoLib", codgoEstado);
			query.setShort("mes", mes.shortValue());
			query.setLong("codigoLineaNegocio", codigoLineaNeg);
			query.setLong("codigoProdcuto", codigoProducto);
			query.setLong("codigoEstadoHR", estadoHojaRutaActiva);
			query.setInteger("anio", anio);

			ordenProduccion = (Ordenproduccion) query.uniqueResult();
			return ordenProduccion;

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

	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> obtenerPorLineaNegocioMesYEstadoNoNotif(Integer mes, Integer anio,
			Long codigoLineaNegocio, Long codgoEstado, Long codigoNotificacionDiaria) {
		String mensajeError = null;

		Long estadoPlanAnual = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PLANIFICACION_CODIGO_ESTADO_PLAN_APROBADO));
		Long estadoHojaRutaActiva = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(ESTADO_HOJA_RUTA_ACTIVA));
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select DISTINCT(op) FROM Ordenproduccion op, Ordenproduccionplan opp WHERE");

			// estado de la orden liberada
			queryStr.append(" op.estadoordenproduccion.pkCodigoEstadoorden = :estadoLib");

			// Hoja de ruta activa
			queryStr.append(" AND op.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :codigoEstadoHR");

			queryStr.append(" AND op.mesOrdenproduccion = :mes");
			queryStr.append(" AND op.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");

			// Que se encuentre en el plan anul o sea manual
			queryStr.append(" AND op.pkCodigoOrdenproduccion = opp.ordenproduccion.pkCodigoOrdenproduccion");

			// Que el año del plan sea correponda con el año de la notificacion
			queryStr.append(" AND opp.plananual.annoPlananual = :anio");

			// Que el estado del plan anual sea probado
			queryStr.append(" AND opp.plananual.estadoplananual.pkCodigoEstadoplananual = :edoPlanAnual");

			// Que no se enceuntre en las notificaciones del dia
			queryStr.append(" AND op.pkCodigoOrdenproduccion NOT IN");
			queryStr.append(" (SELECT DISTINCT np.ordenproduccion.pkCodigoOrdenproduccion FROM  Notificacionproduccion np");
			queryStr.append("  WHERE np.notificaciondiaria.pkCodigoNotificaciondiaria =	 :codigoNotificacionDiaria)");
			Query query = Querier.query(queryStr.toString());

			query.setLong("estadoLib", codgoEstado);
			query.setShort("mes", mes.shortValue());
			query.setLong("edoPlanAnual", estadoPlanAnual);
			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);
			query.setLong("codigoEstadoHR", estadoHojaRutaActiva);
			query.setInteger("anio", anio);

			List<Ordenproduccion> listaOrdenesAutomaticas = query.list();

			queryStr = new StringBuilder();
			queryStr.append("Select DISTINCT(op) FROM Ordenproduccion op, Ordenproduccionmanual opm   WHERE");

			// estado de la orden liberada
			queryStr.append(" op.estadoordenproduccion.pkCodigoEstadoorden = :estadoLib");

			// Hoja de ruta activa
			queryStr.append(" AND op.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :codigoEstadoHR");

			queryStr.append(" AND op.mesOrdenproduccion = :mes");
			queryStr.append(" AND op.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");

			// Que se encuentre en el plan anul o sea manual
			queryStr.append(" AND op.pkCodigoOrdenproduccion = opm.ordenproduccion.pkCodigoOrdenproduccion");

			// Que el año del plan sea correponda con el año de la notificacion
			queryStr.append(" AND opm.annoOrdenproduccionmanual = :anio");

			// Que no se enceuntre en las notificaciones del dia
			queryStr.append(" AND op.pkCodigoOrdenproduccion NOT IN");
			queryStr.append(" (SELECT DISTINCT np.ordenproduccion.pkCodigoOrdenproduccion FROM  Notificacionproduccion np");
			queryStr.append("  WHERE np.notificaciondiaria.pkCodigoNotificaciondiaria =	 :codigoNotificacionDiaria)");
			query = Querier.query(queryStr.toString());

			query.setLong("estadoLib", codgoEstado);
			query.setShort("mes", mes.shortValue());
			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);
			query.setLong("codigoEstadoHR", estadoHojaRutaActiva);
			query.setInteger("anio", anio);

			listaOrdenesAutomaticas.addAll(query.list());
			return listaOrdenesAutomaticas;

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

	// Registro Diatribuible - Carga Automatica (Buscar OrdenProduccion por
	// Producto / CodigoProceso/ Mes / Annio)
	@SuppressWarnings("unchecked")
	public static Ordenproduccion getOrdenProduccionByProductoYMesYProceso(Long codigoProducto, Long codigoProceso, int mes,
			Integer annio) throws EntornoEjecucionException, SesionVencidaException {
		try {
			Query query = query("from Ordenproduccion op where op.produccion.producto.pkCodigoProducto = :codProducto"
					+ " and op.produccion.proceso.pkCodigoProceso = :codProceso "
					+ " and op.mesOrdenproduccion = :mes "
					+ " and op.pkCodigoOrdenproduccion in (select opp.ordenproduccion.pkCodigoOrdenproduccion from Ordenproduccionplan opp where opp.plananual.annoPlananual = :annio) ");

			query.setLong("codProducto", codigoProducto);
			query.setLong("codProceso", codigoProceso);
			query.setInteger("mes", mes);
			query.setInteger("annio", annio);
			query.setMaxResults(1);

			return (Ordenproduccion) query.uniqueResult();

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
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

}
