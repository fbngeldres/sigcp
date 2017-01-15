package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HojaRutaQuerier.java
 * Modificado: Dec 3, 2009 4:13:19 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Collections;
import java.util.List;

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojaruta;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;


public class HojaRutaQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final Long CODIGO_HOJARUTA_ACTIVA = new Long(1);
	private static final Long CODIGO_HOJARUTA_INACTIVA = new Long(2);
	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_HOJA_RUTA = "nombreHojaruta";
	private static final String CODIGO_SCC = "codigoSccHojaruta";
	private static final String CODIGO_ESTADO = "estadohojaruta.pkCodigoEstadohojaruta";
	private static final String CODIGO_PRODUCCION = "produccion.pkProduccion";
	private static String mensajeError;

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Hojaruta
	 * 
	 * @return
	 */
	public static List<Hojaruta> getAll() throws AplicacionException {

		List lista = Querier.getAll(Hojaruta.class);

	
		return lista;
	}

	/**
	 * Método para obtener la lista de objectos Hojaruta, ordenados por un
	 * atributo
	 * 
	 * @param order
	 * @return Lista de Objetos de Hojaruta ordenados
	 */
	public static List<Hojaruta> getAllOrderBy(String order) {

		return Querier.getAll(Hojaruta.class, order);
	}

	/**
	 * Método para obtener la lista de objectos Hojaruta, que no contienen
	 * factores de dosificación
	 * 
	 * @param order
	 * @return Lista de Objetos de Hojaruta
	 */
	public static List<Hojaruta> getHojaRutaSinFactorDosificacion() throws AplicacionException {

		String queryString = "from Hojaruta hr where hr.pkCodigoHojaruta in (select fac.hojaruta.pkCodigoHojaruta from Factordosificacion fac where fac.factordosificacionregistromensus.size <= 0)";
		try {
			return getSession().createQuery(queryString).list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener la lista de objectos Hojaruta, que contienen factores
	 * de dosificación
	 * 
	 * @param order
	 * @return Lista de Objetos de Hojaruta
	 */
	public static List<Hojaruta> getHojaRutaConFactorDosificacion() throws AplicacionException {

		String queryString = "from Hojaruta hr where  hr.pkCodigoHojaruta in (select fac.hojaruta.pkCodigoHojaruta from Factordosificacion fac where fac.factordosificacionregistromensus.size > 0) order by hr.nombreHojaruta asc ";
		try {
			return getSession().createQuery(queryString).list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Hojaruta de la BD por código.
	 * 
	 * @param codigo
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Hojaruta getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return (Hojaruta) Querier.getById(Hojaruta.class, codigo);
	}

	/**
	 * Método para obtener una lista de Hojaruta de la BD por nombre.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> findByCodigoSCC(Long codigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Hojaruta.class, CODIGO_SCC, codigoSCC);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Hojaruta> findByCodigoProduccion(Long codigoProduccion) throws AplicacionException {

		try {
			return Querier.findByProperty(Hojaruta.class, CODIGO_PRODUCCION, codigoProduccion);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de Hojaruta de la BD por nombre.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> findByNombre(String value) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Hojaruta.class, NOMBRE_HOJA_RUTA, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de Hojaruta de la BD por unidad.
	 * 
	 * @param value
	 * @return
	 */
	public static List<Hojaruta> findByCodigoEstado(Long value) throws AplicacionException {

		try {
			return Querier.findByPropertyAndOrderBy(Hojaruta.class, CODIGO_ESTADO, value, NOMBRE_HOJA_RUTA);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * @param codigoLineaNegocio
	 * @return Lista de Hojas de ruta asociadas a una linea de negocio
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerPlanAnualHojasRuta(Long codigoLineaNegocio) throws AplicacionException {
		try {
			Query query = query("select object(h) from Hojaruta h "
					+ "where h.produccion.proceso.lineanegocio.pkCodigoLineanegocio = ? "
					+ "and  h.estadohojaruta.pkCodigoEstadohojaruta = ? "
					+ "order by h.produccion.producto.tipoproducto.siglasTipoproducto desc, "
					+ "h.produccion.proceso.ordenEjecucionProceso desc");
			query.setLong(0, codigoLineaNegocio);
			query.setLong(1, CODIGO_HOJARUTA_ACTIVA);
			return (List<Hojaruta>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener las hoja de ruta por medio de el proceso de la
	 * producción
	 * 
	 * @param codigoProceso
	 * @return Lista de Hojas de ruta asociadas a una linea de negocio
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaPorCodigoProceso(Long codigoProceso) throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.proceso.pkCodigoProceso = ?");
		query.setLong(0, codigoProceso);
		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta por medio de la Línea de Negocio del
	 * proceso de la producción
	 * 
	 * @param codigoLineaNegocio
	 * @return Lista de Hojas de ruta asociadas a una linea de negocio
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaPorCodigoLineaNegocio(Long codigoLineaNegocio) throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio = ?");
		query.setLong(0, codigoLineaNegocio);
		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta por medio de la Unidad de la Línea
	 * de Negocio del proceso de la producción
	 * 
	 * @param codigoUnidad
	 * @return Lista de Hojas de ruta asociadas a una linea de negocio
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaPorCodigoUnidad(Long codigoUnidad) throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.proceso.lineanegocio.unidad.pkCodigoUnidad = ?");
		query.setLong(0, codigoUnidad);
		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta ACTIVAS de un producto
	 * 
	 * @param codigoProducto
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaActivaPorProducto(Long codigoProducto) throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.producto.pkCodigoProducto = ? and hr.estadohojaruta.pkCodigoEstadohojaruta = ?");
		query.setLong(0, codigoProducto);
		query.setLong(1, CODIGO_HOJARUTA_ACTIVA);

		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta ACTIVAS de un producto y Linea de
	 * Negocio
	 * 
	 * @param codigoProducto
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaActivaPorProductoYLineaNegocio(Long codigoProducto, Long lineaNegocio)
			throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.producto.pkCodigoProducto = ? and hr.estadohojaruta.pkCodigoEstadohojaruta = ? and hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio=?");
		query.setLong(0, codigoProducto);
		query.setLong(1, CODIGO_HOJARUTA_ACTIVA);
		query.setLong(2, lineaNegocio);
		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta ACTIVA de un producto (OJO no
	 * funciona para clinker que es caso atipico)
	 * 
	 * @param codigoProducto
	 * @throws ElementoNoEncontradoException, EntornoEjecucionException
	 * @throws AplicacionException
	 */
	public static Hojaruta obtenerHojaRutaActivaPorProducto(Long codigoProducto) throws ElementoNoEncontradoException,
			EntornoEjecucionException {
		try {
			Query query = query("from Hojaruta hr where hr.produccion.producto.pkCodigoProducto = ? and hr.estadohojaruta.pkCodigoEstadohojaruta = ?");
			query.setLong(0, codigoProducto);
			query.setLong(1, CODIGO_HOJARUTA_ACTIVA);
			return (Hojaruta) query.uniqueResult();
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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

	/**
	 * Método para obtener las hoja de ruta ACTIVAS de un producto
	 * 
	 * @param codigoProducto
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaActivaPorProductoYProceso(Long codigoProducto, Long codigoProceso)
			throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.producto.pkCodigoProducto = ? and hr.produccion.proceso.pkCodigoProceso = ? and hr.estadohojaruta.pkCodigoEstadohojaruta = ?");
		query.setLong(0, codigoProducto);
		query.setLong(1, codigoProceso);
		query.setLong(2, CODIGO_HOJARUTA_ACTIVA);

		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta ACTIVAS de una produccion
	 * 
	 * @param codigoProducto
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaActivaPorProduccion(Long codigoProduccion) throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.pkProduccion = ? and hr.estadohojaruta.pkCodigoEstadohojaruta = ?");
		query.setLong(0, codigoProduccion);
		query.setLong(1, CODIGO_HOJARUTA_ACTIVA);

		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta por la produccion
	 * 
	 * @param codigoProducto
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaPorProduccion(Long codigoProducto) throws AplicacionException {

		// Query query = query("from Hojaruta hr where
		// hr.produccion.pkProduccion = ? and
		// hr.estadohojaruta.pkCodigoEstadohojaruta = ?");
		Query query = query("from Hojaruta hr where hr.produccion.pkProduccion = ?");
		query.setLong(0, codigoProducto);
		// query.setLong(1, CODIGO_HOJARUTA_ACTIVA);

		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta por la produccion
	 * 
	 * @param codigoProduccion
	 * @throws AplicacionException
	 */
	public static Hojaruta obtenerHojasRutaActivasPorProduccion(Long codigoProduccion) throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.produccion.pkProduccion = ? and hr.estadohojaruta.pkCodigoEstadohojaruta = ? ");
		query.setLong(0, codigoProduccion);
		query.setLong(1, CODIGO_HOJARUTA_ACTIVA);

		return (Hojaruta) query.uniqueResult();
	}

	/**
	 * Método para obtener las hoja de ruta por la produccion
	 * 
	 * @param codigoProduccion
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaNoInactivasPorNombre(String nombreHojaRuta) throws AplicacionException {

		String nombre = nombreHojaRuta.toLowerCase();

		Query query = query("from Hojaruta hr where hr.estadohojaruta.pkCodigoEstadohojaruta <> ? and lower(hr.nombreHojaruta) = ? ");
		query.setLong(0, CODIGO_HOJARUTA_INACTIVA);
		query.setString(1, nombre);

		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta por la produccion
	 * 
	 * @param codigoProduccion
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaNoInactivasPorProducto(Long codigoProducto) throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.estadohojaruta.pkCodigoEstadohojaruta <> ? and hr.produccion.producto.pkCodigoProducto = ? ");
		query.setLong(0, CODIGO_HOJARUTA_INACTIVA);
		query.setLong(1, codigoProducto);

		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta por nombre y la produccion
	 * 
	 * @param codigoProduccion
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaNoInactivasPorNombreYProduccion(String nombreHojaRuta, Long codigoProduccion)
			throws AplicacionException {

		String nombre = nombreHojaRuta.toLowerCase();

		Query query = query("from Hojaruta hr where hr.estadohojaruta.pkCodigoEstadohojaruta <> ? and lower(hr.nombreHojaruta) = ? and hr.produccion.pkProduccion = ? ");
		query.setLong(0, CODIGO_HOJARUTA_INACTIVA);
		query.setString(1, nombre);
		query.setLong(2, codigoProduccion);

		return (List<Hojaruta>) query.list();
	}

	/**
	 * Método para obtener las hoja de ruta por la produccion
	 * 
	 * @param codigoProduccion
	 * @throws AplicacionException
	 */
	public static List<Hojaruta> obtenerHojasRutaConFactoresDosificacion() throws AplicacionException {

		Query query = query("from Hojaruta hr where hr.factordosificacions.size > 0 ");

		return (List<Hojaruta>) query.list();
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar una Hojaruta en la BD.
	 * 
	 * @param hojaruta
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Hojaruta hojaruta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(hojaruta);
	}

	/**
	 * Metodo para modificar una Hojaruta de la BD.
	 * 
	 * @param hojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Hojaruta hojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(hojaRuta);
	}

	/**
	 * Método para eliminar una Hojarutao de la BD.
	 * 
	 * @param hojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Hojaruta hojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(hojaRuta);
	}

	// public static Proceso getProcesoSegunHojaRuta(Long codProducto) throws
	// ElementoNoEncontradoException {
	// try {
	// Query query = query("SELECT hr.produccion.proceso FROM Hojaruta hr WHERE
	// hr.estadohojaruta.pkCodigoEstadohojaruta = :estadoActiva AND
	// hr.produccion.producto.pkCodigoProducto = :codProducto");
	// query.setLong("estadoActiva", CODIGO_HOJARUTA_ACTIVA);
	// query.setLong("codProducto", codProducto);
	//
	// return (Proceso) query.uniqueResult();
	// } catch (ObjectNotFoundException e) {
	// mensajeError =
	// ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
	// logger.error(e);
	// throw new ElementoNoEncontradoException(mensajeError, e);
	// } catch (QueryException e) {
	// mensajeError =
	// ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
	// logger.error(e);
	// throw new EntornoEjecucionException(mensajeError, e);
	// } catch (SessionException e) {
	// mensajeError =
	// ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
	// logger.error(e);
	// throw new SesionVencidaException(mensajeError, e);
	// } catch (JDBCConnectionException e) {
	// mensajeError =
	// ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
	// logger.error(e);
	// throw new EntornoEjecucionException(mensajeError, e);
	// } catch (RuntimeException e) {
	// mensajeError =
	// ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
	// logger.error(e);
	// throw new EntornoEjecucionException(mensajeError, e);
	// }
	//
	// }

	public static Proceso getProcesoSegunHojaRuta(Long codProducto, Long codigoLinea) throws ElementoNoEncontradoException {
		try {
			Query query = query("SELECT hr.produccion.proceso FROM Hojaruta hr WHERE hr.estadohojaruta.pkCodigoEstadohojaruta = :estadoActiva AND hr.produccion.producto.pkCodigoProducto = :codProducto AND hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codLinea");
			query.setLong("estadoActiva", CODIGO_HOJARUTA_ACTIVA);
			query.setLong("codProducto", codProducto);
			query.setLong("codLinea", codigoLinea);

			return (Proceso) query.uniqueResult();
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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

	/**
	 * Retorna la linea de negocio a la que pertenece una producto segun su hoja
	 * de ruta, si el pruducto esta presente en mas de una linea, se devuele la
	 * linea de negocio cemento, de igual forma si el producto no posee hoja de
	 * ruta tambien se devuelve la linea de negocio cemento
	 * 
	 * @param codProducto Long
	 * @return Lineanegocio
	 * @throws ElementoNoEncontradoException si la consulta falla
	 */
	@SuppressWarnings("unchecked")
	public static Lineanegocio getLineaNegocioSegunHojaRuta(Long codProducto) throws ElementoNoEncontradoException {
		try {
			StringBuilder hqlBld = new StringBuilder("SELECT hr.produccion.proceso.lineanegocio FROM Hojaruta hr WHERE ");
			hqlBld.append("hr.estadohojaruta.pkCodigoEstadohojaruta = :estadoActiva");
			hqlBld.append(" AND hr.produccion.producto.pkCodigoProducto = :codProducto");

			Query query = query(hqlBld.toString());
			query.setLong("estadoActiva", CODIGO_HOJARUTA_ACTIVA);
			query.setLong("codProducto", codProducto);

			List<Lineanegocio> lineas = query.list();

			Long LINEA_NEGOCIO_CEMENTO = new Long(
					ManejadorPropiedades.obtenerPropiedadPorClave(ConstantesLogicaNegocio.LINEA_NEGOCIO_CEMENTO));

			// Si el producto tiene mas de una hoja de ruta porq esta presente
			// en dos lineas o mas lineas de negocio o
			// si no posee hoja de ruta, se retorna la linea de negocio por
			// defecto: Cemento
			if (lineas == null || lineas.size() == 0 || lineas.size() > 1) {
				return LineaNegocioQuerier.getById(LINEA_NEGOCIO_CEMENTO);
			}

			return lineas.get(0);
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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

	public static Long getNumeroDeHojasActivas(Producto producto) throws ElementoNoEncontradoException {
		try {
			Query query = query("SELECT COUNT (hr) FROM Hojaruta hr WHERE hr.produccion.producto.pkCodigoProducto = ? AND hr.estadohojaruta.pkCodigoEstadohojaruta = ?");
			query.setLong(0, producto.getPkCodigoProducto());
			query.setLong(1, CODIGO_HOJARUTA_ACTIVA);

			Long cantidad = (Long) query.uniqueResult();

			if (cantidad == null) {
				return 0L;
			}

			return cantidad;

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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

	public static List<Hojaruta> getHojasRutasByCodigoPlanAnual(Long codigoPlanAnual) {

		StringBuilder queryStr = new StringBuilder("SELECT hr FROM Hojaruta hr JOIN hr.plannecesidads pn ");
		queryStr.append(" WHERE ");
		queryStr.append(" pn.plananual.pkCodigoPlananual = :codigoPlanAnual ");

		Query query = query(queryStr.toString());
		query.setLong("codigoPlanAnual", codigoPlanAnual);

		return query.list();

	}

	public static Integer CambiarHojaRuta(Long codProceso, Long codProducto, Long codProduccion, Long mes, Long anio) {

		Query query = getSession().createSQLQuery(
				"select fnCambiarHojaRuta(:p_codProceso,:p_codProducto,:p_codProduccion,:p_mes,:p_anio)");

		query.setInteger("p_codProceso", codProceso.intValue());
		query.setInteger("p_codProducto", codProducto.intValue());
		query.setInteger("p_codProduccion", codProduccion.intValue());
		query.setInteger("p_mes", mes.intValue());
		query.setInteger("p_anio", anio.intValue());

		return (Integer) query.uniqueResult();
	}
}