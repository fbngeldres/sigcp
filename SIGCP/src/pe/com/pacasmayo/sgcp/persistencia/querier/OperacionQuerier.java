package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OperacionQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class OperacionQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_OPERACION = "nombreOperacion";
	private static final String ORDEN_OPERACION = "ordenEjecucionOperacion";
	private static final String CODIGO_ACTIVIDAD = "actividad.pkCodigoActividad";
	private static final String CODIGO_RECURSO = "recurso.pkCodigoRecurso";
	private static final String CODIGO_PUESTO_TRABAJO = "puestotrabajo.pkCodigoPuestotrabajo";
	private static final String CODIGO_HOJA_RUTA = "hojaruta.pkCodigoHojaruta";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener todas las Operaciones
	 * 
	 * @return
	 */
	public static List<Operacion> getAll() throws AplicacionException {

		return Querier.getAll(Operacion.class);
	}

	/**
	 * Método para obtener todas las Operaciones, ordenadas por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List getAllOrderBy(String order) {

		return Querier.getAll(Operacion.class, order);
	}

	/**
	 * Método para obtener una Operacion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Operacion getById(Long codigo) throws ElementoNoEncontradoException {

		return (Operacion) Querier.getById(Operacion.class, codigo);
	}

	/**
	 * Método para obtener una Operacion de la BD por medio del orden de
	 * ejecución.
	 * 
	 * @param orden
	 * @throws AplicacionException
	 */
	public static List<Operacion> findByOrdenEjecucion(Long orden) throws ElementoNoEncontradoException, AplicacionException {
		String mensajeError = "";
		try {
			return Querier.findByProperty(Operacion.class, ORDEN_OPERACION, orden);
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (HibernateException hException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, hException);
			throw new AplicacionException(mensajeError, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Operacion de la BD por medio del código de la
	 * actividad asociada.
	 * 
	 * @param codigoActividad
	 * @throws AplicacionException
	 */
	public static List<Operacion> findByCodigoActividad(Long codigoActividad) throws AplicacionException {

		try {
			return Querier.findByProperty(Operacion.class, CODIGO_ACTIVIDAD, codigoActividad);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Operacion de la BD por medio del nombre.
	 * 
	 * @param nombre
	 * @throws AplicacionException
	 */
	public static List<Operacion> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Operacion.class, NOMBRE_OPERACION, nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Operacion de la BD por medio del codigo del
	 * Recurso.
	 * 
	 * @param codigoRecurso
	 * @throws AplicacionException
	 */
	public static List<Operacion> findByCodigoRecurso(Long codigoRecurso) throws AplicacionException {

		try {
			return Querier.findByProperty(Operacion.class, CODIGO_RECURSO, codigoRecurso);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Operacion de la BD por medio del codigo del
	 * Puesto de Trabajo.
	 * 
	 * @param codigoPuestoTrabajo
	 * @throws AplicacionException
	 */
	public static List<Operacion> findByCodigoPuestoTrabajo(Long codigoPuestoTrabajo) throws AplicacionException {

		try {
			return Querier.findByProperty(Operacion.class, CODIGO_PUESTO_TRABAJO, codigoPuestoTrabajo);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Operacion de la BD por medio del codigo de la
	 * Hoja de Ruta.
	 * 
	 * @param codigoHojaRuta
	 * @throws AplicacionException
	 */
	public static List<Operacion> findByCodigoHojaRuta(Long codigoHojaRuta) throws ElementoNoEncontradoException,
			AplicacionException {
		String mensajeError = "";
		try {
			return Querier.findByProperty(Operacion.class, CODIGO_HOJA_RUTA, codigoHojaRuta);
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (HibernateException hException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError, hException);
			throw new AplicacionException(mensajeError, hException.getCause());
		}
	}

	public static List<Operacion> getPorHojaRuta(Long codigoHojaRuta) throws AplicacionException {

		Query query = query("from Operacion op where op.hojaruta.pkCodigoHojaruta = ? order by op.puestotrabajo.nombrePuestotrabajo asc ");
		query.setLong(0, codigoHojaRuta);

		return (List<Operacion>) query.list();
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar una Operacion en la BD.
	 * 
	 * @param operacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Operacion operacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(operacion);
	}

	/**
	 * Método para modificar una Operacion en la BD.
	 * 
	 * @param operacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Operacion operacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(operacion);
	}

	/**
	 * Método para eliminar una Operacion en la BD.
	 * 
	 * @param operacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Operacion operacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(operacion);
	}

	/**
	 * Método para eliminar una lista de Operaciones de la BD, por medio del
	 * código de la hoja de ruta.
	 * 
	 * @param codigoHojaRuta
	 * @throws AplicacionException
	 */
	public static void deleteByCodigoHojaRuta(Long codigoHojaRuta) throws HibernateException {

		String hqlDelete = "delete Operacion oper where oper.hojaruta.pkCodigoHojaruta = :codigo";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoHojaRuta).executeUpdate();
	}
}
