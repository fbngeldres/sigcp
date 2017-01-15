package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccionPlanQuerier.java
 * Modificado: Feb 1, 2010 11:39:09 AM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojaruta;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccionplan;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plananual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class OrdenProduccionPlanQuerier extends Querier implements ConstantesLogicaNegocio {

	private static Log logger = LogFactory.getLog(OrdenProduccionPlanQuerier.class);

	private static final String CODIGO_PLANPRODUCCION = "plananual.pkCodigoPlananual";

	/**
	 * Metodo para obtener todas las OrdenProduccionPlan
	 * 
	 * @return
	 */
	public static List<Ordenproduccionplan> getAll() throws AplicacionException {

		return Querier.getAll(Ordenproduccionplan.class);
	}

	/**
	 * Metodo para obtener todas las OrdenProduccionPlan ordenadas por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List getAllOrderBy(String order) {

		return Querier.getAll(Ordenproduccionplan.class, order);
	}

	/**
	 * Método para obtener un Ordenproduccionplan de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Ordenproduccionplan getById(Long codigo) throws ElementoNoEncontradoException {

		return (Ordenproduccionplan) Querier.getById(Ordenproduccionplan.class, codigo);
	}

	/**
	 * Metodo para obtener un OrdenProduccionPlan de la BD por medio del codigo
	 * del plan de produccion.
	 * 
	 * @param codigoPlanProduccion
	 * @throws AplicacionException
	 */
	public static List<Ordenproduccionplan> findByPlanProduccion(Long codigoPlanProduccion) throws AplicacionException {

		try {
			return Querier.findByProperty(Ordenproduccionplan.class, CODIGO_PLANPRODUCCION, codigoPlanProduccion);
		} catch (UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, hException);
			throw new AplicacionException(mensajeError, hException.getCause());
		}
	}

	/**
	 * Metodo para obtener un OrdenProduccionPlan de la BD por medio del codigo
	 * de la orden del plan de produccion.
	 * 
	 * @param codigoOrdenProduccion
	 * @throws AplicacionException
	 */
	public static Ordenproduccionplan findByOrdenProduccion(Long codigoOrdenProduccion) throws ElementoNoEncontradoException,
			AplicacionException {
		String consulta = "from Ordenproduccionplan ordenplan " + "where ordenplan.ordenproduccion.pkCodigoOrdenproduccion = ? ";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoOrdenProduccion);
			Ordenproduccionplan orden = (Ordenproduccionplan) query.uniqueResult();

			return orden;
		} catch (UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, hException);
			throw new AplicacionException(mensajeError, hException.getCause());
		}
	}

	/**
	 * Metodo para Insertar un Ordenproduccionplan en la BD.
	 * 
	 * @param operacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Ordenproduccionplan ordenproduccionplan) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(ordenproduccionplan);
	}

	/**
	 * Metodo para modificar una Ordenproduccionplan en la BD.
	 * 
	 * @param ordenproduccionplan
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Ordenproduccionplan ordenproduccionplan) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(ordenproduccionplan);
	}

	/**
	 * Metodo para eliminar una Ordenproduccionplan en la BD.
	 * 
	 * @param ordenproduccionplan
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Ordenproduccionplan ordenproduccionplan) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(ordenproduccionplan);

	}

	/**
	 * Este método elimina una Orden de Produccion Plan, por medio del mes de
	 * corte, el año y el codigo de la producción del plan anual
	 * 
	 * @param mesCorteVersionPlananual
	 * @param annoPlananual
	 * @param codigoProduccionPlananual
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void deleteByAnnioMesProduccion(short mesCorteVersionPlananual, int annoPlananual,
			Long codigoProduccionPlananual) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete Ordenproduccionplan opp where opp.plananual.mesCorteVersionPlananual >= ? and opp.plananual.annoPlananual = ? and opp.plananual.pkCodigoPlananual = ?");

		query.setShort(0, mesCorteVersionPlananual);
		query.setInteger(1, annoPlananual);
		query.setLong(2, codigoProduccionPlananual);

		query.executeUpdate();
	}

	/**
	 * Metodo para insertar o actualizar si ya existe un Ordenproduccionplan en
	 * la BD.
	 * 
	 * @param ordenproduccionplan
	 * @throws Exception
	 */
	public static void saveOrUpdate(Ordenproduccionplan ordenproduccionplan) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException, Exception {

		Querier.saveOrUpdate(ordenproduccionplan);
	}

	public static void deleteByAnnioMesProduccion(Short mesCorteVersionPlananual, Integer annoPlananual, Long codigoPlananual)
			throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {

		Query query = query("delete from Ordenproduccionplan opp where opp.plananual.pkCodigoPlananual in (select pa.pkCodigoPlananual from Plananual pa where pa.annoPlananual = ? and pa.pkCodigoPlananual = ?) and opp.ordenproduccion.pkCodigoOrdenproduccion in (select op.pkCodigoOrdenproduccion from Ordenproduccion op where op.mesOrdenproduccion > ? )");

		query.setInteger(0, annoPlananual);
		query.setLong(1, codigoPlananual);
		query.setShort(2, mesCorteVersionPlananual);

		query.executeUpdate();
	}

	public static void deleteByAnnioMesProduccion(Plananual planAnual, Hojaruta hojaRuta, Short mesCorteVersionPlananual)
			throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {

		Query query = query("delete from Ordenproduccionplan opp where opp.plananual.pkCodigoPlananual in (select pa.pkCodigoPlananual from Plananual pa where pa.annoPlananual = ? and pa.pkCodigoPlananual = ?) and opp.ordenproduccion.pkCodigoOrdenproduccion in (select op.pkCodigoOrdenproduccion from Ordenproduccion op where op.mesOrdenproduccion > ? and op.hojaruta.pkCodigoHojaruta = ? and op.produccion.pkProduccion = ?)");

		query.setInteger(0, planAnual.getAnnoPlananual());
		query.setLong(1, planAnual.getPkCodigoPlananual());
		query.setShort(2, mesCorteVersionPlananual);
		query.setLong(3, hojaRuta.getPkCodigoHojaruta());
		query.setLong(4, hojaRuta.getProduccion().getPkProduccion());

		query.executeUpdate();
	}

	/**
	 * Metodo para obtener las OrdenProduccion liberadas de la BD por medio del
	 * Anño y mes
	 * 
	 * @param codigoOrdenProduccion
	 * @throws AplicacionException
	 */
	public static List<Ordenproduccion> findOrdenProduccionByAnoMes(Short mes, Integer anio)
			throws ElementoNoEncontradoException, AplicacionException {

		Long estadoPlanAnual = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(PLANIFICACION_CODIGO_ESTADO_PLAN_APROBADO));
		Long estadoHojaRutaActiva = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(ESTADO_HOJA_RUTA_ACTIVA));
		Long codigoEstadoLiberado = Long.parseLong(ManejadorPropiedades
				.obtenerPropiedadPorClave(ConstantesLogicaNegocio.CODIGO_ESTADO_ORDEN_PRODUCCION_LIBERADA));

		StringBuilder consulta = new StringBuilder("SELECT op.ordenproduccion FROM Ordenproduccionplan op ");
		consulta.append(" WHERE op.ordenproduccion.mesOrdenproduccion = :mes");
		consulta.append(" AND   op.ordenproduccion.estadoordenproduccion.pkCodigoEstadoorden = :estadoOrden");
		consulta.append(" AND   op.plananual.annoPlananual = :anio");
		consulta.append(" AND   op.plananual.estadoplananual.pkCodigoEstadoplananual = :estadoPlan");
		consulta.append(" AND   op.ordenproduccion.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :codigoEstadoHR");
		try {
			Query query = Querier.query(consulta.toString());
			query.setLong("mes", mes);
			query.setLong("estadoOrden", codigoEstadoLiberado);
			query.setLong("anio", anio);
			query.setLong("estadoPlan", estadoPlanAnual);
			query.setLong("codigoEstadoHR", estadoHojaRutaActiva);

			return query.list();
		} catch (UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (HibernateException hException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, hException);
			throw new AplicacionException(mensajeError, hException.getCause());
		}
	}

}
