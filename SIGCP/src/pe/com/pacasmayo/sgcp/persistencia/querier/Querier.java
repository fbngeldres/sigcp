package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PuestoTrabajoQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.RangoFechas;

public class Querier implements ConstantesMensajeAplicacion {
	private static final String DELIMITADOR_HIBERNATE = "\\.";
	public static final String ASC = " asc";
	public static final String DESC = " desc";

	/** Logger Instance */
	protected static Logger logger = Logger.getLogger(Querier.class);

	protected static Session getSession() {
		return PersistenciaFactory.currentSession();
	}

	/**
	 * Metodo para obtener todos los registros de una entidad persistente
	 * 
	 * @param <E> Tipo de la entidad persistente
	 * @param cl class de la entiedad persistente
	 * @return Lista con los objetos obtenidos en la consulta
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAll(Class<E> cl) throws EntornoEjecucionException, SesionVencidaException {
		Query query = query("from " + cl.getName());
		return query.list();
	}

	/**
	 * Método para obtener todos los registros de una entidad persistente de
	 * acuerdo al sqlString recibido
	 * 
	 * @param cl
	 * @param sqlOrderBy
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> getAll(Class<E> cl, String sqlOrderBy) throws HibernateException {
		return query("from " + cl.getName() + " o Order By " + sqlOrderBy).list();
	}

	/**
	 * Obtiene todos los registros de una entidad en orden ascendente
	 * 
	 * @param <E> Tipo de la entiedad persistente
	 * @param cl class de la entiedad persistente
	 * @param sqlOrderBy
	 * @return Lista con los objetos obtenidos en la consulta ordenados de forma
	 *         ascendente
	 */
	@SuppressWarnings("unchecked")
	protected static <E> List<E> getAllOrderByAscendent(Class<E> cl, String sqlOrderBy) throws SesionVencidaException,
			EntornoEjecucionException {
		return query("from " + cl.getName() + " o Order By " + sqlOrderBy + ASC).list();
	}

	/**
	 * Obtiene todos los registros de una entidad en orden descendente
	 * 
	 * @param <E> Tipo de la entidad persistente
	 * @param cl class de la entidad persistente
	 * @param sqlOrderBy
	 * @return Lista con los objetos obtenidos en la consulta ordenados de forma
	 *         descendente
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	protected static <E> List<E> getAllOrderByDescendent(Class<E> cl, String sqlOrderBy) throws SesionVencidaException,
			EntornoEjecucionException {

		List lista = query("from " + cl.getName() + " o Order By " + sqlOrderBy + DESC).list();

		return lista;
	}

	/**
	 * @param <E>
	 * @param cl
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> getByLikeStringPropertie(Class<E> cl, String propertyName, String propertyValue)
			throws HibernateException {

		return query("from " + cl.getName() + " where upper(" + propertyName + ") like upper('%" + propertyValue + "%')").list();
	}

	/**
	 * Metodo para obtener un objeto persistente a traves de su id
	 * 
	 * @param cl class del objeto persitente que se desea consultar
	 * @param id identificador del objeto
	 * @return Objeto peristente
	 * @throws ElementoNoEncontradoException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static <E> E getById(Class<E> cl, Long id) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		Session sesion = null;
		E object = null;
		String mensajeError = "";

		try {
			sesion = getSession();
			object = (E) sesion.get(cl, id);

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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

		return object;
	}

	/**
	 * Obtiene una lista de objetos de la bd consultados a traves de la
	 * propiedad pasada como parametro
	 * 
	 * @param cl class del objeto que se desea consultar
	 * @param propertyName nombre de la propiedad por la que se desea consultar
	 * @param value valor de la propiedad a consultar
	 * @return lista de objetos obtenidos a traves de la propiedad que se pasa
	 *         como parametro
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> findByProperty(Class<E> cl, String propertyName, Object value) throws EntornoEjecucionException,
			SesionVencidaException {

		String mensajeError = "";
		List<E> lista = null;
		try {
			String queryString = "from " + cl.getName() + " as model where model." + propertyName + "= ?";

			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			lista = queryObject.list();

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

		return lista;
	}

	public static <E> List<E> findByPropertyAndOrderBy(Class<E> cl, String propertyName, Object value, String sqlOrderBy) {

		String mensajeError = "";
		List<E> lista = null;
		try {
			String queryString = "from " + cl.getName() + " as model where model." + propertyName + " = ? " + " Order By model."
					+ sqlOrderBy + ASC;

			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			lista = queryObject.list();

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

		return lista;
	}

	/**
	 * Obtiene un objeto de la bd consultado a traves de la propiedad pasada
	 * como parametro
	 * 
	 * @param cl class del objeto que se desea consultar
	 * @param propertyName nombre de la propiedad por la que se desea consultar
	 * @param value valor de la propiedad a consultar
	 * @return objeto obtenido a traves de la propiedad que se pasa como
	 *         parametro
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaExceptio
	 */
	@SuppressWarnings("unchecked")
	public static <E> E findByPropertyUniqueResult(Class<E> cl, String propertyName, Object value)
			throws ElementoNoEncontradoException, EntornoEjecucionException, SesionVencidaException {

		String mensajeError = "";
		E object = null;
		try {
			String queryString = "from " + cl.getName() + " as model where model." + propertyName + "= ?";

			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			object = (E) queryObject.uniqueResult();

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (NonUniqueResultException e) {
			logger.error(e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			throw new EntornoEjecucionException(mensajeError, e);
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

		return object;
	}

	/**
	 * Consulta un objeto segun dos de sus propiedades
	 * 
	 * @param cl class del objeto que se desea consultar
	 * @param propertyName1 propiedad por la que se desea consultar
	 * @param value1 valor de la primera propiedad por la que se desea consultar
	 * @param propertyName2 segunda propiedad por la que se desea consultar
	 * @param value2 valor de la segunda propiedad por la que se desea consultar
	 * @return objeto obtenido segun las dos propiedades pasadas como parametro
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	@SuppressWarnings("unchecked")
	public static <E> E findByProperties(Class<E> cl, String propertyName1, Object value1, String propertyName2, Object value2)
			throws ElementoNoEncontradoException, EntornoEjecucionException, SesionVencidaException {

		String mensajeError = "";
		E object = null;
		try {
			String queryString = "from " + cl.getName() + " as model where model." + propertyName1 + "= ? and model."
					+ propertyName2 + "= ?";

			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value1);
			queryObject.setParameter(1, value2);
			object = (E) queryObject.uniqueResult();

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (NonUniqueResultException e) {
			logger.error(e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
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

		return object;
	}

	protected static Query query(String qstr) throws EntornoEjecucionException, SesionVencidaException, RuntimeException {

		String mensajeError = "";

		Query query = null;

		try {
			query = getSession().createQuery(qstr);

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

		return query;
	}
	
	protected static Query querySQL(String qstr) throws EntornoEjecucionException, SesionVencidaException, RuntimeException {

		String mensajeError = "";

		Query query = null;

		try {
			query = getSession().createSQLQuery(qstr);

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

		return query;
	}

	/**
	 * Guarda un objeto en BD
	 * 
	 * @param object objeto que se desea guardar
	 * @return identificador del objeto guardado
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static Object save(Object object) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Object objeto = null;
		objeto = manejaOperacion(object, "save");

		return objeto;
	}

	/**
	 * Actualiza un objeto en bd
	 * 
	 * @param object objeto que se desea actualizar
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void update(Object object) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		manejaOperacion(object, "update");
	}

	/**
	 * Guarda o actualiza un objeto en BD
	 * 
	 * @param object objeto que se desea guardar o actualizar
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void saveOrUpdate(Object object) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		manejaOperacion(object, "saveOrUpdate");
	}

	/**
	 * Elimina un objeto de BD
	 * 
	 * @param object objeto que se desea eliminar
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void delete(Object object) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		manejaOperacion(object, "delete");
	}

	/**
	 * Método para ejecutar la administración de la entidad Area.
	 * 
	 * @param object
	 * @param type
	 * @return
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 * @throws ConstrainViolationException
	 * @throws ConstrainViolationException
	 */
	private static final Object manejaOperacion(Object object, String type) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Object id = null;
		Session sesion = null;
		String mensajeError;

		try {
			sesion = getSession();

			if (type.equals("save")) {
				id = sesion.save(object);
			} else if (type.equals("update")) {
				sesion.update(object);
			} else if (type.equals("saveOrUpdate")) {
				sesion.saveOrUpdate(object);
			} else if (type.equals("delete")) {
				sesion.delete(object);
			}

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
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return id;
	}

	/**
	 * Metodo que consulta una lista de entidades, segun un conjunto de
	 * criterios: codigos de filtrado de claves foraneas
	 * 
	 * @param cl
	 * @param propiedades
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> findByProperties(Class<E> cl, Map propiedades) throws EntornoEjecucionException,
			SesionVencidaException {

		List<E> lista = null;
		Session sesion = null;
		String mensajeError = "";

		try {

			sesion = getSession();
			Criteria criteria = sesion.createCriteria(cl.getName());
			Map mapaAlias = new HashMap();
			// Este ciclo toma cada String para la navegacion de objetos en
			// hibernate para
			// crear alias que se agregaran como restricciones en el criterio de
			// busqueda,
			// con su respectivo valor, y asi poder ejecutar consultas con
			// varios
			// criterios de filtrado pior codigos

			for (Iterator iterator = propiedades.keySet().iterator(); iterator.hasNext();) {

				String clave = (String) iterator.next();
				String[] arregloAliasHibernate = clave.split(DELIMITADOR_HIBERNATE);
				String alias = "";
				int ultimoAlias = arregloAliasHibernate.length - 1;

				// Ciclo hasta el penúltimo elemento para crear los alias
				for (int i = 0; i < ultimoAlias; i++) {

					alias += arregloAliasHibernate[i];

					if (mapaAlias.get(alias) == null) {
						criteria.createAlias(alias, alias.replace(".", ""));
						mapaAlias.put(alias, alias.replace(".", ""));
					}

					alias = mapaAlias.get(alias) + ".";
				}
				// Por este if si la propiedad que se paso tiene valor nulo,
				// se usa para buscar valores que sean distintos a null
				if (propiedades.get(clave) == null) {
					criteria.add(Restrictions.isNotNull(alias + arregloAliasHibernate[ultimoAlias]));
				} else {
					// Por este if pasa un filtro de String
					if (propiedades.get(clave).getClass() == String.class) {
						criteria.add(Restrictions.like(alias + arregloAliasHibernate[ultimoAlias], propiedades.get(clave)));
					}
					// Por este if pasa un filtro de rango de fecha
					else if (propiedades.get(clave).getClass() == RangoFechas.class) {
						RangoFechas rango = (RangoFechas) propiedades.get(clave);
						criteria.add(Restrictions.between(rango.getNombreCampo(), rango.getFechaInicio(), rango.getFechaFin()));
					} else {
						criteria.add(Restrictions.eq(alias + arregloAliasHibernate[ultimoAlias], propiedades.get(clave)));
					}
				}
			}
			lista = (List<E>) criteria.list();

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
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
		return lista;
	}

	/**
	 * Método que se encarga de la ejecución del query de acuerdo a un conjunto
	 * de parametros recibidos
	 * 
	 * @param queryString corresponde a query que se desea ejecutar
	 * @param parameters los parametros a utilizar por el query
	 * @return lista de resultados obtenidos una vez ejecutado el query
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws RuntimeException
	 */
	@SuppressWarnings("unchecked")
	protected static <E> List<E> executeQuery(String queryString, Map<String, Object> parameters)
			throws EntornoEjecucionException, SesionVencidaException, RuntimeException {

		String mensajeError = "";
		Query query = null;
		List<E> result = null;

		try {

			query = getSession().createQuery(queryString);

			for (Iterator<String> it = parameters.keySet().iterator(); it.hasNext();) {

				String key = (String) it.next();
				Object valor = parameters.get(key);

				query.setParameter(key, valor);
			}

			result = query.list();
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

		return result;
	}

	/**
	 * Metodo que consulta una lista de entidades, segun un conjunto de
	 * criterios: codigos de filtrado de claves foraneas Ademas contiene las
	 * variables para poder realizar la paginacion
	 * 
	 * @param cl
	 * @param propiedades
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> findByPropertiesAndPagination(Class<E> cl, Map propiedades, Integer maxResult, Integer firstResult)
			throws EntornoEjecucionException, SesionVencidaException {

		List<E> lista = null;
		Session sesion = null;
		String mensajeError = "";

		try {

			sesion = getSession();
			Criteria criteria = sesion.createCriteria(cl.getName());
			Map mapaAlias = new HashMap();
			// Este ciclo toma cada String para la navegacion de objetos en
			// hibernate para
			// crear alias que se agregaran como restricciones en el criterio de
			// busqueda,
			// con su respectivo valor, y asi poder ejecutar consultas con
			// varios
			// criterios de filtrado pior codigos

			for (Iterator iterator = propiedades.keySet().iterator(); iterator.hasNext();) {

				String clave = (String) iterator.next();
				String[] arregloAliasHibernate = clave.split(DELIMITADOR_HIBERNATE);
				String alias = "";
				int ultimoAlias = arregloAliasHibernate.length - 1;

				// Ciclo hasta el penúltimo elemento para crear los alias
				for (int i = 0; i < ultimoAlias; i++) {

					alias += arregloAliasHibernate[i];

					if (mapaAlias.get(alias) == null) {
						criteria.createAlias(alias, alias.replace(".", ""));
						mapaAlias.put(alias, alias.replace(".", ""));
					}

					alias = mapaAlias.get(alias) + ".";
				}
				// Por este if si la propiedad que se paso tiene valor nulo,
				// se usa para buscar valores que sean distintos a null
				if (propiedades.get(clave) == null) {
					criteria.add(Restrictions.isNotNull(alias + arregloAliasHibernate[ultimoAlias]));
				} else {
					// Por este if pasa un filtro de String
					if (propiedades.get(clave).getClass() == String.class) {
						criteria.add(Restrictions.like(alias + arregloAliasHibernate[ultimoAlias], propiedades.get(clave)));
					}
					// Por este if pasa un filtro de rango de fecha
					else if (propiedades.get(clave).getClass() == RangoFechas.class) {
						RangoFechas rango = (RangoFechas) propiedades.get(clave);
						criteria.add(Restrictions.between(rango.getNombreCampo(), rango.getFechaInicio(), rango.getFechaFin()));
					} else {
						criteria.add(Restrictions.eq(alias + arregloAliasHibernate[ultimoAlias], propiedades.get(clave)));
					}
				}
			}
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResult);
			lista = (List<E>) criteria.list();

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
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
		return lista;
	}
}