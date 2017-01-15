package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestiï¿½n y Control de la Producciï¿½n)
 * Archivo: ProduccionQuerier.java
 * Modificado: Dec 10, 2009 2:29:57 PM
 * Autor: evelyn.santamaria
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ProduccionQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String CODIGO_PROCESO = "proceso.pkCodigoProceso";
	private static final String CODIGO_PRODUCTO = "producto.pkCodigoProducto";
	static final Long CODIGO_ESTADO_PRODUCTO_ACTIVO = new Long(1);

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Metodo para obtener la lista de objectos Produccion
	 * 
	 * @return
	 */
	public static List<Produccion> getAll() throws AplicacionException {

		return Querier.getAll(Produccion.class);
	}

	/**
	 * Mï¿½todo para obtener todos Produccion, ordenados por un atributo
	 * 
	 * @param order
	 * @return Lista de Objetos de Produccion
	 */
	public static List<Produccion> getAllOrderBy(String order) {

		return Querier.getAll(Produccion.class, order);
	}

	/**
	 * Mï¿½todo para obtener un Producto por medio del cï¿½digo
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Produccion getById(Long codigo) throws ElementoNoEncontradoException {

		return (Produccion) Querier.getById(Produccion.class, codigo);
	}

	/**
	 * Mï¿½todo para obtener una produccion por medio del cï¿½digo del proceso
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static List<Produccion> getByProceso(Long codigo) throws AplicacionException {

		try {
			return Querier.findByPropertyAndOrderBy(Produccion.class, CODIGO_PROCESO, codigo, "producto.nombreProducto");
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Mï¿½todo para obtener una produccion por medio del cï¿½digo del producto
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static List<Produccion> getByProducto(Long codigo) throws AplicacionException {

		try {
			return Querier.findByProperty(Produccion.class, CODIGO_PRODUCTO, codigo);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Mï¿½todo para obtener una produccion por medio del cï¿½digo del producto
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static List<Produccion> getByProcesoYProductoActivo(Long codigo) throws AplicacionException {

		String consulta = "from Produccion prod " + "where prod.proceso.pkCodigoProceso = ? and "
				+ "prod.producto.estadoproducto.pkCodigoEstadoproducto = ? ";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigo);
			query.setLong(1, CODIGO_ESTADO_PRODUCTO_ACTIVO);

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
	 * Mï¿½todo para obtener una produccion por medio del cï¿½digo del producto
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static List<Producto> getByProductoActivo(Long lineanegocio) throws AplicacionException {

		String consulta = "Select distinct prod.producto from Produccion prod "
				+ " WHERE prod.producto.estadoproducto.pkCodigoEstadoproducto = ? AND "
				+ " prod.proceso.lineanegocio.pkCodigoLineanegocio = ? ";

		try {
			Query query = Querier.query(consulta);

			query.setLong(0, CODIGO_ESTADO_PRODUCTO_ACTIVO);
			query.setLong(1, lineanegocio);
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
	 * Mï¿½todo para obtener una produccion por medio del cï¿½digo del producto
	 * y del codigo del proceso
	 * 
	 * @param codigoProducto
	 * @param codigoProceso
	 * @throws AplicacionException
	 */
	public static Produccion getByProductoProceso(Long codigoProducto, Long codigoProceso) throws ElementoNoEncontradoException,
			AplicacionException {

		String consulta = "from Produccion prod " + "where prod.producto.pkCodigoProducto = ? "
				+ "and prod.proceso.pkCodigoProceso = ?  ";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoProducto);
			query.setLong(1, codigoProceso);

			return (Produccion) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
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
	 * Mï¿½todo para obtener una produccion por medio del cï¿½digo del producto
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static List<Produccion> getByProcesoYProduccionHojaRutaActiva(Long codigoProceso) throws AplicacionException {

		String consulta = "from Produccion prod "
				+ "where prod.proceso.pkCodigoProceso = ? and "
				+ "prod.pkProduccion in (select hr.produccion.pkProduccion from Hojaruta hr where hr.estadohojaruta.pkCodigoEstadohojaruta = 1) ";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoProceso);

			return query.list();
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
	 * Mï¿½todo para Insertar una produccion en la BD.
	 * 
	 * @param producto
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static Long save(Produccion produccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		return (Long) Querier.save(produccion);
	}

	/**
	 * Metodo para modificar una Produccion de la BD.
	 * 
	 * @param producto
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Produccion produccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(produccion);
	}

	/**
	 * Mï¿½todo para eliminar una Produccion de la BD.
	 * 
	 * @param produccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Produccion produccion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(produccion);
	}

	/**
	 * Mï¿½todo para eliminar una Produccion la BD, por medio del cï¿½digo del
	 * producto.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void deleteByCodigoHojaRuta(Long codigo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete Produccion pro where pro.producto.pkCodigoProducto = :codigo";
		getSession().createQuery(hqlDelete).setLong("codigo", codigo).executeUpdate();

	}

	/**
	 * Metodo para obtener la lista de producción por Proceso Y Producto
	 * 
	 * @param codigoLineaNegocio
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static List<Produccion> getByProcesoYProducto(Long codigoProceso, Long codigoProducto) throws AplicacionException {

		StringBuilder queryStr = new StringBuilder("SELECT produccion FROM Hojaruta hr ");
		queryStr.append(" WHERE hr.produccion.producto.estadoproducto.pkCodigoEstadoproducto = :estadoProducto  ");
		queryStr.append(" AND hr.estadohojaruta.pkCodigoEstadohojaruta = :estadoHojaruta  ");
		if (codigoProceso != null) {
			queryStr.append(" AND hr.produccion.proceso.pkCodigoProceso = :codigoProceso ");
		}
		if (codigoProducto != null) {
			queryStr.append(" AND hr.produccion.producto.pkCodigoProducto = :codigoProducto ");
		}
		queryStr.append(" ORDER BY hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio ASC, hr.produccion.proceso.ordenEjecucionProceso ASC, hr.produccion.producto.nombreProducto ASC ");

		try {
			Query query = Querier.query(queryStr.toString());

			if (codigoProceso != null) {
				query.setLong("codigoProceso", codigoProceso);
			}
			if (codigoProducto != null) {
				query.setLong("codigoProducto", codigoProducto);
			}
			query.setLong("estadoProducto", Long.valueOf(1));
			query.setLong("estadoHojaruta", Long.valueOf(1));
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
	 * Mï¿½todo para obtener una produccion por medio del cï¿½digo del producto
	 * y del codigo del proceso
	 * 
	 * @param codigoProducto
	 * @param codigoProceso
	 * @throws AplicacionException
	 */
	public static Produccion getByProductoActivoLineaNegocio(Long codigoProducto, Long lineaNegocio)
			throws ElementoNoEncontradoException, AplicacionException {

		String consulta = "from Produccion prod " + "where prod.producto.pkCodigoProducto = ? "
				+ "and prod.proceso.lineanegocio.pkCodigoLineanegocio = ? "
				+ "and lower(prod.producto.estadoproducto.nombreEstadoproducto) = lower('ACTIVO') ";

		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoProducto);
			query.setLong(1, lineaNegocio);

			return (Produccion) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
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
