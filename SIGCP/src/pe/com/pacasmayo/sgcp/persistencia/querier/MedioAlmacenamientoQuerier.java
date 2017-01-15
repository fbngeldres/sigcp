package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MedioAlmacenamientoQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class MedioAlmacenamientoQuerier implements ConstantesMensajeAplicacion {

	private static Log log = LogFactory.getLog(MedioAlmacenamientoQuerier.class);

	private static String mensajeError;

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/
	public static final String CODIGO_PRODUCTO = "produccion.producto.pkCodigoProducto";
	public static final String NOMBRE_MEDIO_ALMACENAMIENTO = "nombreMedioalmacenamiento";
	public static final String CODIGO_TIPO_MEDIO_ALMACENAMIENTO = "tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien";
	public static final String NUMERO_MEDIO_ALMACENAMIENTO = "numeroMedioalmacenamiento";
	public static final String CODIGO_PUESTO_TRABAJO = "puestotrabajo.pkCodigoPuestotrabajo";
	public static final String CODIGO_UBICACION = "ubicacion.pkCodigoUbicacion";
	public static final String NOMBRE_CLASE_MEDIO_ALMACENAMIENTO = "Medioalmacenamiento";
	public static final String CODIGO_MEDIO_ALMACENAMIENTO = "pkCodigoMedioalmacenamiento";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Medioalmacenamiento
	 * 
	 * @return
	 */
	public static List<Medioalmacenamiento> getAll() throws SesionVencidaException, EntornoEjecucionException {

		return Querier.getAll(Medioalmacenamiento.class);
	}

	/**
	 * Método para obtener la lista de objetos Medioalmacenamiento, ordenados
	 * por un campo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Medioalmacenamiento> getAllOrderBy(String order) throws AplicacionException {

		try {
			return Querier.getAll(Medioalmacenamiento.class, order);
		} catch (HibernateException e) {
			throw new AplicacionException(ERROR_HIBERNATE, e);
		}

	}

	/**
	 * Método para obtener una Medioalmacenamiento de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Medioalmacenamiento getById(Long codigo) throws ElementoNoEncontradoException {
		return Querier.getById(Medioalmacenamiento.class, codigo);
	}

	/**
	 * Método para obtener los Medioalmacenamiento de la BD por nombre.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Medioalmacenamiento> findByNombre(String value) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Medioalmacenamiento.class, NOMBRE_MEDIO_ALMACENAMIENTO, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Medioalmacenamiento de la BD por el número.
	 * 
	 * @param numero
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Medioalmacenamiento> findByNumero(Short numero) throws AplicacionException {

		try {
			return Querier.findByProperty(Medioalmacenamiento.class, NUMERO_MEDIO_ALMACENAMIENTO, numero);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static Medioalmacenamiento findSiloByNumeroYProduccion(Short numero, Long codProducto, Long codProceso)
			throws ElementoNoEncontradoException, EntornoEjecucionException, SesionVencidaException {
		StringBuilder queryBuilder = new StringBuilder(
				"FROM Medioalmacenamiento AS ma where ma.numeroMedioalmacenamiento = :num AND ");
		queryBuilder.append("ma.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien = 1 AND ");
		queryBuilder.append("ma.produccion.producto.pkCodigoProducto = :codProducto AND ");
		queryBuilder.append("ma.produccion.proceso.pkCodigoProceso = :codProceso");

		try {
			Query query = Querier.query(queryBuilder.toString());
			query.setShort("num", numero);
			query.setLong("codProducto", codProducto);
			query.setLong("codProceso", codProceso);

			return (Medioalmacenamiento) query.uniqueResult();
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Método para obtener los Medioalmacenamiento de la BD por nombre.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Medioalmacenamiento> findByCodigoTipoMedioAlmacenamiento(Long value) throws AplicacionException {

		try {
			return Querier.findByPropertyAndOrderBy(Medioalmacenamiento.class, CODIGO_TIPO_MEDIO_ALMACENAMIENTO, value,
					NOMBRE_MEDIO_ALMACENAMIENTO);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Medioalmacenamiento de la BD por el código del
	 * Puesto de Trabajo.
	 * 
	 * @param codPuestoTrabajo
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Medioalmacenamiento> findByCodigoPuestoTrabajo(Long codPuestoTrabajo) throws AplicacionException {

		try {
			return Querier.findByProperty(Medioalmacenamiento.class, CODIGO_PUESTO_TRABAJO, codPuestoTrabajo);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Medioalmacenamiento de la BD por el código de la
	 * Ubicación del Medio de Almacenamiento.
	 * 
	 * @param codigoUbicacion
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Medioalmacenamiento> findByCodigoUbicacion(Long codigoUbicacion) throws AplicacionException {

		try {
			return Querier.findByProperty(Medioalmacenamiento.class, CODIGO_UBICACION, codigoUbicacion);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	public static List findByProperties(Map propiedades) throws AplicacionException {

		try {
			return Querier.findByProperties(Medioalmacenamiento.class, propiedades);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Obtiene los medios de almacenamiento indicados en la lista
	 * 
	 * @param codigos
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Medioalmacenamiento> obtenerporCodigos(List<Long> codigos) throws AplicacionException {

		String consulta = "from {0} As ma where ma.{1} in (:codigos)";
		consulta = MessageFormat
				.format(consulta, new Object[] { NOMBRE_CLASE_MEDIO_ALMACENAMIENTO, CODIGO_MEDIO_ALMACENAMIENTO });
		try {
			Query query = Querier.query(consulta);
			query.setParameterList("codigos", codigos);

			return query.list();

		} catch (HibernateException e) {
			log.error(e);
			throw new AplicacionException(ERROR_HIBERNATE, e.getCause());
		}

	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Medioalmacenamiento en la BD.
	 * 
	 * @param medioAlmacenamiento
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Medioalmacenamiento medioAlmacenamiento) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(medioAlmacenamiento);
	}

	/**
	 * Metodo para modificar una Medioalmacenamiento de la BD.
	 * 
	 * @param medioAlmacenamiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Medioalmacenamiento medioAlmacenamiento) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(medioAlmacenamiento);
	}

	/**
	 * Metodo para eliminar una MedioAlmacenamiento de la BD.
	 * 
	 * @param medioAlmacenamiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Medioalmacenamiento medioAlmacenamiento) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(medioAlmacenamiento);
	}

	@SuppressWarnings("unchecked")
	public static List<Medioalmacenamiento> obtenerPorProceso(Long codigoProceso) throws SesionVencidaException,
			EntornoEjecucionException {
		StringBuilder queryBuilder = new StringBuilder("FROM Medioalmacenamiento AS ma WHERE ");
		queryBuilder.append("ma.produccion.proceso.pkCodigoProceso = :codProceso ");
		queryBuilder.append("ORDER BY ma.nombreMedioalmacenamiento");

		try {
			Query query = Querier.query(queryBuilder.toString());
			query.setLong("codProceso", codigoProceso);

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
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Medioalmacenamiento> obtenerPorTipoMedioProceso(Long tipoMedio, Long codigoProceso)
			throws SesionVencidaException, EntornoEjecucionException {
		StringBuilder queryBuilder = new StringBuilder("FROM Medioalmacenamiento AS ma WHERE ");
		queryBuilder.append(" ma.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien = :codTipoMedio ");
		queryBuilder.append(" AND ma.produccion.proceso.pkCodigoProceso = :codProceso ");
		queryBuilder.append("ORDER BY ma.nombreMedioalmacenamiento");

		try {
			Query query = Querier.query(queryBuilder.toString());
			query.setLong("codTipoMedio", tipoMedio);
			query.setLong("codProceso", codigoProceso);

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
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static List<Medioalmacenamiento> obtenerPorTipoMedioProcesoProducto(Long tipoMedio, Long codigoProceso,
			Long codigoProducto) throws SesionVencidaException, EntornoEjecucionException {

		StringBuilder queryBuilder = new StringBuilder("FROM Medioalmacenamiento AS ma WHERE ");
		queryBuilder.append(" ma.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien = :codTipoMedio ");
		queryBuilder.append(" AND ma.produccion.proceso.pkCodigoProceso = :codProceso ");
		queryBuilder.append(" AND ma.produccion.producto.pkCodigoProducto = :codProducto ");
		queryBuilder.append("ORDER BY ma.nombreMedioalmacenamiento");

		try {
			Query query = Querier.query(queryBuilder.toString());
			query.setLong("codTipoMedio", tipoMedio);
			query.setLong("codProceso", codigoProceso);
			query.setLong("codProducto", codigoProducto);

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
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}
}