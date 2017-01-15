package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: OrdenProduccionQuerier.java
 * Modificado: Feb 1, 2010 12:30:35 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;
import java.util.Map;

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccionmanual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class OrdenProduccionManualQuerier extends Querier {

	private static Log logger = LogFactory.getLog(OrdenProduccionManualQuerier.class);
	public static final String CODIGO_ESTATUS_ORDEN = "estadoordenproduccion.pkCodigoEstadoorden";
	public static final String CODIGO_PROCESO = "produccion.proceso.pkCodigoProceso";
	public static final String CODIGO_PRODUCTO = "producto.pkCodigoProducto";

	public static final String MES = "mesOrdenproduccion";
	public static final String CODIGO_PRODUCCION = "produccion.pkProduccion";
	public static final String NOMBRE_CLASE_ORDEN_PRODUCCION = Ordenproduccionmanual.class.getSimpleName();

	/**
	 * Metodo para obtener todas las Ordenproduccion
	 * 
	 * @return
	 */
	public static List<Ordenproduccionmanual> getAll() throws AplicacionException {

		return Querier.getAll(Ordenproduccionmanual.class);
	}

	/**
	 * Metodo para obtener todas las Ordenproduccion ordenadas por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Ordenproduccionmanual> getAllOrderBy(String order) {

		return Querier.getAll(Ordenproduccionmanual.class, order);
	}

	/**
	 * Metodo para obtener una Ordenproduccion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Ordenproduccionmanual getById(Long codigo) throws ElementoNoEncontradoException {
		return (Ordenproduccionmanual) Querier.getById(Ordenproduccionmanual.class, codigo);
	}

	public static Ordenproduccionmanual findByOrdenProduccion(Long codigoOrdenProduccion) throws ElementoNoEncontradoException,
			AplicacionException {
		String consulta = "from Ordenproduccionmanual orden " + "where orden.ordenproduccion.pkCodigoOrdenproduccion = ? ";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoOrdenProduccion);
			Ordenproduccionmanual orden = (Ordenproduccionmanual) query.uniqueResult();
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

	public static Ordenproduccionmanual findByOrdenProduccionYAnnio(Long codigoOrdenProduccion, Integer annio)
			throws ElementoNoEncontradoException, AplicacionException {

		String consulta = "from Ordenproduccionmanual orden where orden.ordenproduccion.pkCodigoOrdenproduccion = ? and orden.annoOrdenproduccionmanual = ? ";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoOrdenProduccion);
			query.setLong(1, annio);

			Ordenproduccionmanual orden = (Ordenproduccionmanual) query.uniqueResult();
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
	 * Metodo para Insertar una Orden de produccion en la BD.
	 * 
	 * @param ordenProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 * @throws ConstrainViolationException
	 */
	public static void save(Ordenproduccionmanual ordenProduccion) throws ParametroInvalidoException, ElementoExistenteException,
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
	public static void update(Ordenproduccionmanual ordenProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

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
	public static void delete(Ordenproduccionmanual ordenProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

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
	public static void saveOrUpdate(Ordenproduccionmanual ordenProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException, Exception {

		Querier.saveOrUpdate(ordenProduccion);
	}

	public static List findByProperties(Map propiedades) {
		return Querier.findByProperties(Ordenproduccionmanual.class, propiedades);
	}

}
