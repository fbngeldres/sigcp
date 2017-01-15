package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProductoVariableCalidadQuerier.java
 * Modificado: Mar 29, 2011 4:00:18 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ProductoVariableCalidadQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/
	private static String mensajeError;
	private static Logger log = Logger.getLogger(ProductoVariableCalidadQuerier.class);

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objetos Productovariablecalidad
	 * 
	 * @return
	 */
	public static List<Productovariablecalidad> getAll() throws AplicacionException {

		return Querier.getAll(Productovariablecalidad.class);
	}

	/**
	 * Método para obtener la lista de objetos Productovariablecalidad, ordenada
	 * por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Productovariablecalidad> getAllOrderBy(String order) throws AplicacionException {

		return Querier.getAll(Productovariablecalidad.class, order);
	}

	/**
	 * Método para obtener un Productovariablecalidad de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Productovariablecalidad getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Productovariablecalidad.class, codigo);
	}

	/**
	 * Método para obtener una lista de Productovariablecalidad de la BD, por
	 * medio de la hoja de ruta.
	 * 
	 * @param codigoHojaRutaComponente
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Productovariablecalidad> findByCodigoHojaRuta(Long codigoHojaRutaComponente) throws AplicacionException {

		try {
			Query query = query("from Productovariablecalidad pvc where pvc.hojaruta.pkCodigoHojaruta = ? ");

			query.setLong(0, codigoHojaRutaComponente);

			return query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de Productovariablecalidad de la BD, por
	 * medio del código de la variable de calidad.
	 * 
	 * @param codigoVariableCalidad
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Productovariablecalidad> findByCodigoVariableCalidad(Long codigoVariableCalidad)
			throws AplicacionException {

		try {
			Query query = query("from Productovariablecalidad pvc where pvc.variablecalidad.pkCodigoVariablecalidad = ? ");

			query.setLong(0, codigoVariableCalidad);

			return query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static Productovariablecalidad obtenerProductoVariableCalidad(Long codProducto, Long codigoVariableCalidad) {
		try {
			StringBuilder hql = new StringBuilder("FROM Productovariablecalidad pvc WHERE ");
			hql.append("pvc.hojaruta.produccion.producto.pkCodigoProducto = :producto AND ");
			hql.append("pvc.variablecalidad.pkCodigoVariablecalidad = :codigoVariableCal");

			Query query = query(hql.toString());
			query.setLong("producto", codProducto);
			query.setLong("codigoVariableCal", codigoVariableCalidad);

			return (Productovariablecalidad) query.uniqueResult();
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
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar un Productovariablecalidad en la BD.
	 * 
	 * @param productovariablecalidad
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Productovariablecalidad productovariablecalidad) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(productovariablecalidad);
	}

	/**
	 * Método para modificar un Productovariablecalidad de la BD.
	 * 
	 * @param productovariablecalidad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Productovariablecalidad productovariablecalidad) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(productovariablecalidad);
	}

	/**
	 * Método para eliminar un Productovariablecalidad de la BD.
	 * 
	 * @param productovariablecalidad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Productovariablecalidad productovariablecalidad) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(productovariablecalidad);
	}

	/**
	 * Este método elimina todos los productos variables calidad de una hoja de
	 * ruta
	 * 
	 * @param codigoHojaRuta
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void deleteByCodigoHojaRuta(Long codigoHojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete Productovariablecalidad pvc where pvc.hojaruta.pkCodigoHojaruta = ?  ");
		query.setLong(0, codigoHojaRuta);

		query.executeUpdate();

	}

	public static Produccion obtenerProductoVariableCalidadPorProducto(Long codigoProducto) {
		try {
			StringBuilder hql = new StringBuilder("SELECT  pvc.hojaruta.produccion " + "FROM Productovariablecalidad pvc "
					+ " WHERE pvc.hojaruta.produccion.producto.pkCodigoProducto = :codigoProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codigoProducto", codigoProducto);
			query.setMaxResults(1);
			return (Produccion) query.uniqueResult();
		} catch (EntornoEjecucionException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), e);
		}
	}

	public static List<Productovariablecalidad> obtenerProductoVariableCalidadByUnidad(Long valorUnidad) {
		try {
			StringBuilder hql = new StringBuilder("FROM Productovariablecalidad pvc WHERE 1=1 ");
			if (valorUnidad != null) {
				hql.append(" AND pvc.hojaruta.produccion.proceso.lineanegocio.unidad.pkCodigoUnidad = :valorUnidad ");
			}
			hql.append(" ORDER BY pvc.hojaruta.produccion.producto.nombreProducto");

			Query query = query(hql.toString());
			if (valorUnidad != null) {
				query.setLong("valorUnidad", valorUnidad);
			}

			return query.list();
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
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}
}
