package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class UnidadQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_UNIDAD = "nombreUnidad";
	private static String CODIGO_SCC_UNIDAD = "codigoSccUnidad";
	private static String CODIGO_SOCIEDAD = "sociedad.pkCodigoSociedad";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objetos Unidad
	 * 
	 * @return
	 */
	public static List<Unidad> getAll() throws AplicacionException {

		return Querier.getAll(Unidad.class);
	}

	/**
	 * Método para obtener la lista de objetos Unidad, ordenada por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Unidad> getAllOrderBy(String order) throws AplicacionException {

		return Querier.getAll(Unidad.class, order);
	}

	/**
	 * Método para obtener una Unidad de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Unidad getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Unidad.class, codigo);
	}

	/**
	 * Método para obtener una Unidad de la BD, por medio del nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Unidad> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Unidad.class, NOMBRE_UNIDAD, nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Unidad de la BD, por medio del codigoSCC.
	 * 
	 * @param codigoSCC
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Unidad> findByCodigoSCC(Long codigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Unidad.class, CODIGO_SCC_UNIDAD, codigoSCC);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Unidad de la BD, por medio del codigo de la
	 * sociedad.
	 * 
	 * @param codSociedad
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Unidad> findByCodigoSociedad(Long codSociedad) throws AplicacionException {

		try {
			return Querier.findByPropertyAndOrderBy(Unidad.class, CODIGO_SOCIEDAD, codSociedad, NOMBRE_UNIDAD);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		} catch (RuntimeException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), hException);
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar una Unidad en la BD.
	 * 
	 * @param unidad
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Unidad unidad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(unidad);
	}

	/**
	 * Método para modificar una Unidad de la BD.
	 * 
	 * @param unidad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Unidad unidad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(unidad);
	}

	/**
	 * Método para eliminar una Unidad de la BD.
	 * 
	 * @param unidad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Unidad unidad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(unidad);
	}
}
