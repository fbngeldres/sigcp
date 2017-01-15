package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HojaRutaComponenteQuerier.java
 * Modificado: Dec 3, 2009 4:13:19 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojarutacomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class HojaRutaComponenteQuerier extends Querier implements ConstantesMensajeAplicacion {

	/** Logger Instance */
	private static Logger log = Logger.getLogger(HojaRutaComponenteQuerier.class);

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String CODIGO_HOJA_RUTA = "hojaruta.pkCodigoHojaruta";
	private static final Long CODIGO_HOJARUTA_ACTIVA = new Long(1);
	private static String mensajeError;

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Hojarutacomponente
	 * 
	 * @return
	 */
	public static List<Hojarutacomponente> getAll() throws AplicacionException {

		return Querier.getAll(Hojarutacomponente.class);
	}

	/**
	 * Método para obtener la lista de objectos Hojarutacomponente, ordenados
	 * por un atributo
	 * 
	 * @param order
	 * @return Lista de Objetos de Hojarutacomponente ordenados
	 */
	public static List<Hojarutacomponente> getAllOrderBy(String order) {

		return Querier.getAll(Hojarutacomponente.class, order);
	}

	/**
	 * Método para obtener una Hojarutacomponente de la BD por código.
	 * 
	 * @param codigo
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Hojarutacomponente getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Hojarutacomponente.class, codigo);
	}

	/**
	 * Método para obtener la lista de objectos Hojarutacomponente, por medio
	 * del codigo de la hoja de ruta
	 * 
	 * @param order
	 * @return Lista de Objetos de Hojarutacomponente
	 */
	public static List<Hojarutacomponente> findByCodigoHojaRuta(Long value) throws AplicacionException {

		try {
			return Querier.findByProperty(Hojarutacomponente.class, CODIGO_HOJA_RUTA, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Hojarutacomponente por medio del código de la
	 * hoja de ruta y del código del producto componente
	 * 
	 * @param codigoHojaRuta
	 * @param codigoProductoComponente
	 */
	public static Hojarutacomponente getByHojaRutaProductoComponente(Long codigoHojaRuta, Long codigoProductoComponente) {

		String consulta = "from Hojarutacomponente hrc " + "where hrc.hojaruta.pkCodigoHojaruta = ? "
				+ "and hrc.componente.pkCodigoComponente = ?  ";

		Query query = Querier.query(consulta);
		query.setLong(0, codigoHojaRuta);
		query.setLong(1, codigoProductoComponente);

		return (Hojarutacomponente) query.uniqueResult();
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar una Hojarutacomponente en la BD.
	 * 
	 * @param hojaRutaComponente
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Hojarutacomponente hojaRutaComponente) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(hojaRutaComponente);
	}

	/**
	 * Metodo para modificar una Hojarutacomponente de la BD.
	 * 
	 * @param hojaRutaComponente
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Hojarutacomponente hojaRutaComponente) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(hojaRutaComponente);
	}

	/**
	 * Método para eliminar una Hojarutacomponenteo de la BD.
	 * 
	 * @param hojaRutaComponente
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Hojarutacomponente hojaRutaComponente) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(hojaRutaComponente);
	}

	/**
	 * Método para eliminar una lista de Hojarutacomponente de la BD, por medio
	 * del código de la hoja de ruta.
	 * 
	 * @param hojaRutaComponente
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void deleteByCodigoHojaRuta(Long codigoHojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete Hojarutacomponente hrc where hrc.hojaruta.pkCodigoHojaruta = :codigo";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoHojaRuta).executeUpdate();

	}

	/**
	 * Método para los componentes de un producto, a traves de su hoja de ruta
	 * activa
	 * 
	 * @param codigoProducto
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Componente> obtenerComponentesPorProductoSegunHojaRuta(Long codigoProducto)
			throws EntornoEjecucionException, SesionVencidaException {
		try {
			Query query = query("SELECT DISTINCT(hrc.componente) FROM Hojarutacomponente hrc WHERE hrc.hojaruta.produccion.producto.pkCodigoProducto = ? AND hrc.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = ?");
			query.setLong(0, codigoProducto);
			query.setLong(1, CODIGO_HOJARUTA_ACTIVA);

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

	public static boolean poseeCompEnHojaRuta(Long codProducto) throws EntornoEjecucionException, SesionVencidaException {
		try {

			StringBuilder queryBld = new StringBuilder(
					"SELECT COUNT(hrc.pkCodigoHojarutacomponente) FROM Hojarutacomponente hrc WHERE ");
			queryBld.append("hrc.hojaruta.produccion.producto.pkCodigoProducto = ?");
			queryBld.append(" AND hrc.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = ?");
			Query query = query(queryBld.toString());

			query.setLong(0, codProducto);
			query.setLong(1, CODIGO_HOJARUTA_ACTIVA);
			Long cantComp = (Long) query.uniqueResult();
			if (cantComp == null) {
				return false;
			}
			return cantComp > 0L;
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

	/**
	 * Obtiene los objetos de tipo Produccion de las hojas de ruta que tiene
	 * como componente al producto pasado como parametro
	 * 
	 * @param producto Producto
	 * @return List<Produccion>
	 */
	@SuppressWarnings("unchecked")
	public static List<Produccion> obtenerProduccionesDePP(Producto producto) throws EntornoEjecucionException,
			SesionVencidaException {
		try {
			StringBuilder queryBld = new StringBuilder(
					"SELECT DISTINCT(hrc.hojaruta.produccion) FROM Hojarutacomponente hrc WHERE ");
			queryBld.append("hrc.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = ?");
			queryBld.append(" AND hrc.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = ?");
			Query query = query(queryBld.toString());
			query.setLong(0, producto.getPkCodigoProducto());
			query.setLong(1, CODIGO_HOJARUTA_ACTIVA);

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

	// AGREGADO POR FABIAN
	/**
	 * @param codigoProducto
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @return
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Componente> getCombustiblesPorProductoHojaRutaActiva(Long codigoLineaNegocio, Long codigoProducto)
			throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = "";
		try {
			StringBuilder consulta = new StringBuilder("SELECT hrc.componente FROM Hojarutacomponente hrc ");
			consulta.append(" WHERE ");
			consulta.append(" hrc.hojaruta.estadohojaruta.nombreEstadohojaruta = :estado ");

			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() != -1L)
				consulta.append(" AND hrc.hojaruta.produccion.proceso.lineanegocio = :lineaNegocio ");

			consulta.append(" AND hrc.componente.productoByFkCodigoProducto.pkCodigoProducto = :producto ");
			consulta.append(" AND hrc.componente.productoByFkCodigoProductoComponente.tipocategoriaproducto.nombreTipocategoriaproducto = :tipoCombustible ");
			consulta.append(" order by hrc.componente.productoByFkCodigoProductoComponente.nombreProducto ASC ");

			Query query = Querier.query(consulta.toString());
			query.setString("estado", "ACTIVA");
			query.setLong("producto", codigoProducto);
			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() != -1L)
				query.setLong("lineaNegocio", codigoLineaNegocio);
			query.setString("tipoCombustible", "combustible");
			return query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}
}
