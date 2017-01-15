package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ActividadQuerier.java
 * Modificado: Dec 22, 2009 2:36:53 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Actividad;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ActividadQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String CODIGO_SCC = "codigoSccActividad";
	public static final String NOMBRE_ACTIVIDAD = "nombreActividad";
	private static final String CODIGO_ESTADO_ACTIVIDAD = "estadoactividad.pkCodigoEstadoactividad";
	private static final String CODIGO_PROCESO = "proceso.pkCodigoProceso";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Actividad
	 * 
	 * @return
	 */
	public static List<Actividad> getAll() throws AplicacionException {

		return Querier.getAll(Actividad.class);
	}

	/**
	 * Método para obtener una Actividad de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Actividad getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Actividad.class, codigo);
	}

	/**
	 * Método para obtener las Actvidades de la BD por codigo SCC.
	 * 
	 * @param codigoSCC
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Actividad> findByCodigoSCC(Long codigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Actividad.class, CODIGO_SCC, codigoSCC);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener las Actvidades de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Actividad> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Actividad.class, NOMBRE_ACTIVIDAD, nombre);

		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Metodo para obtener una lista de Actividades por el codigo del estado de
	 * la actividad
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Actividad> findByCodigoEstadoActividad(Long value) throws AplicacionException {

		try {
			return Querier.findByProperty(Actividad.class, CODIGO_ESTADO_ACTIVIDAD, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Metodo para obtener una lista de Actividades por el codigo del Proceso
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Actividad> findByCodigoProceso(Long value) throws AplicacionException {

		try {
			return Querier.findByProperty(Actividad.class, CODIGO_PROCESO, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar una Actividad en la BD.
	 * 
	 * @param actividad
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Actividad actividad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(actividad);
	}

	/**
	 * Metodo para modificar una Actividad de la BD.
	 * 
	 * @param actividad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Actividad actividad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(actividad);
	}

	/**
	 * Método para eliminar una Actividad de la BD.
	 * 
	 * @param actividad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Actividad actividad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(actividad);
	}

	@SuppressWarnings("unchecked")
	public static List<Actividad> findByCodigoLineaNegocio(Long codigoLineaNegocio) throws AplicacionException {

		try {
			List<Actividad> lista = null;
			String queryString = "SELECT DISTINCT(act) from Actividad as act, Proceso as p, Lineanegocio ln"
					+ " where act.proceso.pkCodigoProceso = p.pkCodigoProceso"
					+ " and p.lineanegocio.pkCodigoLineanegocio = ln.pkCodigoLineanegocio" + " and ln.pkCodigoLineanegocio = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codigoLineaNegocio);
			lista = queryObject.list();

			return lista;
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Actividad> findByPropiedades(Map propiedades) {
		return Querier.findByProperties(Actividad.class, propiedades);
	}
}
