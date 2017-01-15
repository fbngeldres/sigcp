package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProductoQuerier.java
 * Modificado: Dec 10, 2009 2:29:57 PM
 * Autor: evelyn.santamaria
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ProductoQuerier extends Querier implements ConstantesMensajeAplicacion, ConstantesLogicaNegocio {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	public static final String NOMBRE_PRODUCTO = "nombreProducto";
	private static final String SIGLAS_PRODUCTO = "siglasProducto";
	private static final String CODIGO_SAP_PRODUCTO = "codigoSapProducto";
	private static final String CODIGO_SAP_BOLSA_PRODUCTO = "codigoSapBolsaProducto";
	private static final String CODIGO_SAP_BIGBAG_PRODUCTO = "codigoSapBigBagProducto";
	private static final String CODIGO_SAP_ESPECIAL1_PRODUCTO = "codigoSapEspecial1Producto";
	private static final String CODIGO_SAP_ESPECIAL2_PRODUCTO = "codigoSapEspecial2Producto";
	private static final String CODIGO_SAP_ESPECIAL3_PRODUCTO = "codigoSapEspecial3Producto";
	private static final String CODIGO_SAP_ESPECIAL4_PRODUCTO = "codigoSapEspecial4Producto";
	private static final String CODIGO_SCC_PRODUCTO = "codigoSccProducto";
	private static final String TIPO_PRODUCTO = "tipoproducto.pkCodigoTipoproducto";
	private static final String ESTADO_PRODUCTO = "estadoproducto.pkCodigoEstadoproducto";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Metodo para obtener la lista de objectos Producto
	 * 
	 * @return
	 */
	public static List<Producto> getAll() {

		return Querier.getAll(Producto.class);
	}

	/**
	 * Método para obtener todos Productos, ordenados por un atributo
	 * 
	 * @param order
	 * @return Lista de Objetos de Producto
	 */
	public static List<Producto> getAllOrderBy(String order) throws HibernateException {

		return getAll(Producto.class, order);
	}

	/**
	 * Método para obtener todos Productos, ordenados por un atributo
	 * 
	 * @param order
	 * @return Lista de Objetos de Producto
	 */
	public static List<Producto> getAllOrderByNombre() throws HibernateException {

		return getAllOrderBy(NOMBRE_PRODUCTO);
	}

	/**
	 * Método para obtener un Producto por medio del código
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static Producto getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Producto.class, codigo);
	}

	/**
	 * Método para obtener los Productos de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Producto> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Producto.class, NOMBRE_PRODUCTO, nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Productos de la BD por siglas.
	 * 
	 * @param siglas
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Producto> findBySiglas(String siglas) throws AplicacionException {

		try {
			return Querier.findByProperty(Producto.class, SIGLAS_PRODUCTO, siglas);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Productos de la BD por el codigo SCC.
	 * 
	 * @param codigoSCC
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Producto> findByCodigoSCC(Long codigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Producto.class, CODIGO_SCC_PRODUCTO, codigoSCC);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener un producto por el codigo SAP
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Producto> findByCodigoSAP(String codigoSAP) throws SesionVencidaException, EntornoEjecucionException {
		return Querier.findByProperty(Producto.class, CODIGO_SAP_PRODUCTO, codigoSAP);
	}

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * bolsas
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Producto findByCodigoSAPBolsa(String codigoSAPBolsa) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Producto.class, CODIGO_SAP_BOLSA_PRODUCTO, codigoSAPBolsa);
	}

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * bolsas de 1 TM (BIGBAG)
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Producto findByCodigoSAPBigBag(String codigoSAPBigBag) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Producto.class, CODIGO_SAP_BIGBAG_PRODUCTO, codigoSAPBigBag);
	}

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * Especial 1
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Producto findByCodigoSAPEspecial1(String codigoSAPEspecial1) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Producto.class, CODIGO_SAP_ESPECIAL1_PRODUCTO, codigoSAPEspecial1);
	}

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * Especial 2
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Producto findByCodigoSAPEspecial2(String codigoSAPEspecial2) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Producto.class, CODIGO_SAP_ESPECIAL2_PRODUCTO, codigoSAPEspecial2);
	}

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * Especial 3
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Producto findByCodigoSAPEspecial3(String codigoSAPEspecial3) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Producto.class, CODIGO_SAP_ESPECIAL3_PRODUCTO, codigoSAPEspecial3);
	}

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * Especial 4
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static Producto findByCodigoSAPEspecial4(String codigoSAPEspecial4, Long tipoUnidad) throws AplicacionException {

		return findByCodigoSAPTipoUnidad(codigoSAPEspecial4, tipoUnidad, CODIGO_SAP_ESPECIAL4_PRODUCTO);
		// return Querier.findByPropertyUniqueResult(Producto.class,
		// CODIGO_SAP_ESPECIAL4_PRODUCTO, codigoSAPEspecial4);
	}

	/**
	 * Método para obtener los Productos de la BD por el tipo de producto.
	 * 
	 * @param codTipoProd
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Producto> findByCodigoTipoProducto(Long codTipoProd) throws AplicacionException {

		try {

			return Querier.findByProperty(Producto.class, TIPO_PRODUCTO, codTipoProd);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Productos de la BD por el tipo de producto.
	 * 
	 * @param codTipoProd
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Producto> findByCodigoEstadoProductoOrdenNombre(Long codigoEstadoProducto) throws AplicacionException {

		try {
			Query query = query("from Producto where estadoproducto.pkCodigoEstadoproducto = ? order by nombreProducto ");
			query.setLong(0, codigoEstadoProducto);

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
	 * Método para obtener los Productos de la BD por el tipo de producto.
	 * 
	 * @param codEstadoProd
	 * @return
	 */
	public static List<Producto> findByCodigoEstadoProducto(Long codEstadoProd) throws AplicacionException {

		try {
			return Querier.findByProperty(Producto.class, ESTADO_PRODUCTO, codEstadoProd);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener el correlativo del código del producto, según el tipo
	 * de producto
	 * 
	 * @param codigoTipoProducto
	 * @return
	 * @throws AplicacionException
	 */
	public static Long obtenerCorrelativo(Long codigoTipoProducto) throws AplicacionException {

		try {
			Query query = query("Select max(cam.campaignYear) From producto Where fk_codigo_tipoProducto = ? ");
			query.setLong(0, codigoTipoProducto);
			return (Long) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener el listado de Productos a partir del puesto de
	 * trabajo y del proceso
	 * 
	 * @param codigoPuestoTrabajo
	 * @param codigoProceso
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Producto> findByMachineAndProcess(Long codigoPuestoTrabajo, Long codigoProceso) {
		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {
			String consulta = "select distinct pro "
					+ "from Puestotrabajo pt, Operacion op, Hojaruta hr, Produccion producc, Producto pro " + "where "
					+ "pt.pkCodigoPuestotrabajo = ? and " + "pt = op.puestotrabajo and " + "hr = op.hojaruta and "
					+ "producc = hr.produccion and " + "pro = producc.producto and " + "producc.proceso.pkCodigoProceso = ?";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoPuestoTrabajo);
			query.setLong(1, codigoProceso);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productos;
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> findByCodigoLineaNegocio(Long codigoLineaNegocio) {

		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {
			String consulta = "from Producto producto where producto.pkCodigoProducto IN (select produccion.producto.pkCodigoProducto from Produccion produccion where produccion.proceso.lineanegocio.pkCodigoLineanegocio = ? )";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoLineaNegocio);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productos;
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un Producto en la BD.
	 * 
	 * @param producto
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoExceptions
	 * @return Código del Producto
	 * @throws ConstrainViolationException
	 */
	public static Long save(Producto producto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		return (Long) Querier.save(producto);
	}

	/**
	 * Metodo para modificar un Producto de la BD.
	 * 
	 * @param producto
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Producto producto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(producto);
	}

	/**
	 * Método para eliminar un Producto de la BD.
	 * 
	 * @param producto
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Producto producto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(producto);
	}

	@SuppressWarnings("unchecked")
	public static List findByProperties(Map propiedades) {
		return Querier.findByProperties(Producto.class, propiedades);
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> findByCodigoProceso(Long codigoProceso) throws EntornoEjecucionException {

		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {
			String consulta = "select distinct(producto) " + "from Produccion produccion, Producto producto "
					+ "where producto.pkCodigoProducto = produccion.producto.pkCodigoProducto and "
					+ "produccion.proceso.pkCodigoProceso = :pkCodigoProceso " + "ORDER BY producto.nombreProducto";

			Query query = Querier.query(consulta);

			query.setParameter("pkCodigoProceso", codigoProceso);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productos;
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> findByCodigoUnidad(Long codigoUnidad) throws EntornoEjecucionException {

		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {
			String consulta = "select distinct(producto) " + "from Produccion produccion, Producto producto "
					+ "where producto.pkCodigoProducto = produccion.producto.pkCodigoProducto and "
					+ "produccion.proceso.lineanegocio.unidad.pkCodigoUnidad = :pkCodigoUnidad "
					+ "ORDER BY producto.nombreProducto";

			Query query = Querier.query(consulta);

			query.setParameter("pkCodigoUnidad", codigoUnidad);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productos;
	}

	/**
	 * Método para obtener los prodcutos combustibles
	 * 
	 * @param codigoDivision
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @return
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Producto> findCombustibles(Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio) throws EntornoEjecucionException {

		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {

			String consultarUnidad = "and pro.proceso.lineanegocio.unidad.pkCodigoUnidad = ? ";
			String consultarLineaNegocio = "and pro.proceso.lineanegocio.pkCodigoLineanegocio = ? ";

			String consulta = "select pro.producto " + "from Produccion pro " + "where "
					+ "pro.producto.tipocategoriaproducto.nombreTipocategoriaproducto = ? and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = ? and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = ? "
					+ (codigoUnidad.equals(new Long(-1)) ? "" : consultarUnidad)
					+ (codigoLineaNegocio.equals(new Long(-1)) ? "" : consultarLineaNegocio)
					+ "ORDER BY pro.producto.nombreProducto asc";

			Query query = Querier.query(consulta);

			query.setString(0, "combustible");
			query.setLong(1, codigoDivision);
			query.setLong(2, codigoSociedad);

			if (!codigoUnidad.equals(new Long(-1)) && !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(3, codigoUnidad);
				query.setLong(4, codigoLineaNegocio);
			}

			if (codigoUnidad.equals(new Long(-1)) && !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(3, codigoLineaNegocio);
			}

			if (!codigoUnidad.equals(new Long(-1)) && codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(3, codigoUnidad);
			}

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productos;
	}

	/**
	 * Método para obtener el Producto correspondiente a una orden de produccion
	 * a partir del codigo del puesto de trabajo
	 * 
	 * @param codigoOrdenProduccion
	 * @return
	 */
	public static Producto findByOrdenProduccion(Long codigoOrdenProduccion) {
		Producto producto = null;
		String mensajeError = "";

		try {
			String consulta = "select distinct pro " + "from Ordenproduccion op, Produccion p, Producto pro "
					+ "where op.pkCodigoOrdenproduccion = ? and " + "p.pkProduccion = op.produccion.pkProduccion and "
					+ "p.producto.pkCodigoProducto = pro.pkCodigoProducto";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoOrdenProduccion);

			producto = (Producto) query.uniqueResult();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return producto;
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductosFiltrandoPorProductoYTipoTipoProducto(Long codigoProducto, Long codigoTipo)
			throws EntornoEjecucionException {

		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {
			String consulta = "select distinct(producto) " + "from Producto producto "
					+ "where producto.tipoproducto.pkCodigoTipoproducto != :codigoTipo AND "
					+ "producto.pkCodigoProducto != :codigoProducto " + "ORDER BY producto.nombreProducto";

			Query query = Querier.query(consulta);

			query.setLong("codigoTipo", codigoTipo);
			query.setLong("codigoProducto", codigoProducto);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productos;
	}

	/**
	 * Verifica si un producto es materia, esto debido a que hay productos en
	 * proceso que no tienen componentes en su hoja de ruta y deben ser tratados
	 * como materias primas
	 * 
	 * @param componente Producto
	 * @return boolean, verdadero si es materia prima, falso en caso contrario
	 */
	public static boolean verificarSiEsMateriaPrima(Producto componente) {
		String mensajeError = "";

		try {
			Long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));
			Long codigoMP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_MATERIA_PRIMA));
			Long codigoTipoproducto = componente.getTipoproducto().getPkCodigoTipoproducto();

			boolean esProductoProceso = codigoTipoproducto.longValue() == codigoPP.longValue();
			boolean esMateriaPrima = codigoTipoproducto.longValue() == codigoMP.longValue();
			boolean poseeCompEnHojaRuta = HojaRutaComponenteQuerier.poseeCompEnHojaRuta(componente.getPkCodigoProducto());

			return esMateriaPrima || (esProductoProceso && !poseeCompEnHojaRuta);

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Verifica si un producto es materia, esto debido a que hay productos en
	 * proceso que no tienen componentes en su hoja de ruta y deben ser tratados
	 * como maeterias primas
	 * 
	 * @param codigoCmponente Long codigo del producto
	 * @return boolean, verdadero si es materia prima, falso en caso contrario
	 */
	public static boolean verificarSiEsMateriaPrima(Long codigoCmponente) throws SesionVencidaException,
			ElementoNoEncontradoException, EntornoEjecucionException {
		Producto producto = Querier.getById(Producto.class, codigoCmponente);
		return verificarSiEsMateriaPrima(producto);
	}

	public static boolean verificarSiEsProductoTerminado(Long codigoProducto) throws SesionVencidaException,
			ElementoNoEncontradoException, EntornoEjecucionException {
		String mensajeError = null;
		Long codigoPT = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_TERMINADO));
		try {
			String consulta = "SELECT p.tipoproducto.pkCodigoTipoproducto FROM Producto p WHERE p.pkCodigoProducto = :pk";

			Query query = Querier.query(consulta);
			query.setLong("pk", codigoProducto);

			Long codigoTipoProducto = (Long) query.uniqueResult();
			return codigoTipoProducto.longValue() == codigoPT.longValue();
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
	}

	/**
	 * Método para obtener los Productos de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static Producto findByNombre_(String nombre) throws SesionVencidaException, ElementoNoEncontradoException,
			EntornoEjecucionException {
		String mensajeError = null;
		try {
			StringBuilder buString = new StringBuilder("FROM Producto p WHERE p.nombreProducto = :producto ");
			Query query = query(buString.toString());
			query.setString("producto", nombre);
			query.setMaxResults(1);

			return (Producto) query.uniqueResult();
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
	}

	public static List<Producto> obtenerProductosPorPuestoTrabajo(Long codigoPuestoTrabajo) {
		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {
			String consulta = "select distinct pro "
					+ "from Puestotrabajo pt, Operacion op, Hojaruta hr, Produccion producc, Producto pro " + "where "
					+ "pt.pkCodigoPuestotrabajo = ? and " + "pt = op.puestotrabajo and " + "hr = op.hojaruta and "
					+ "producc = hr.produccion and " + "pro = producc.producto ";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoPuestoTrabajo);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productos;
	}

	public static List<Producto> findByCodigoSAPNDiferenteTipoConsumo(String codigoSAPProducto, String tipoConsumo)
			throws AplicacionException {
		String mensajeError = null;
		try {
			StringBuilder buString = new StringBuilder(
					"FROM Producto p WHERE p.codigoSapProducto = :codigoSAP AND p.tipoconsumo.nombreTipoconsumo <> :tipoconsumo ");
			Query query = query(buString.toString());
			query.setString("codigoSAP", codigoSAPProducto);
			query.setString("tipoconsumo", tipoConsumo);

			return query.list();
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
	}

	public static void main(String[] args) {
		try {
			Producto ss = ProductoQuerier.findByCodigoSAPTipoUnidad("004-00113", 2L, "codigoSapProducto");
			System.out.println(ss);
		} catch (AplicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Producto findByProductoCodigoSAPTipoUnidad(String codigoSAPProducto, Long tipoUnidad)
			throws AplicacionException {
		String mensajeError = null;
		Producto producto = null;
		try {

			producto = findByCodigoSAPTipoUnidad(codigoSAPProducto, tipoUnidad, CODIGO_SAP_PRODUCTO);

			if (producto == null) {
				producto = findByCodigoSAPTipoUnidad(codigoSAPProducto, tipoUnidad, CODIGO_SAP_BIGBAG_PRODUCTO);

				if (producto == null) {
					producto = findByCodigoSAPTipoUnidad(codigoSAPProducto, tipoUnidad, CODIGO_SAP_BOLSA_PRODUCTO);
					if (producto == null) {
						producto = findByCodigoSAPTipoUnidad(codigoSAPProducto, tipoUnidad, CODIGO_SAP_ESPECIAL1_PRODUCTO);
						if (producto == null) {
							producto = findByCodigoSAPTipoUnidad(codigoSAPProducto, tipoUnidad, CODIGO_SAP_ESPECIAL2_PRODUCTO);
							if (producto == null) {
								producto = findByCodigoSAPTipoUnidad(codigoSAPProducto, tipoUnidad, CODIGO_SAP_ESPECIAL3_PRODUCTO);
							}
						}
					}
				}
			}

		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
		return producto;
	}

	public static Producto findByCodigoSAPTipoUnidad(String codigoSAPProducto, Long tipoUnidad, String columna)
			throws AplicacionException {
		String mensajeError = null;
		try {
			StringBuilder buString = new StringBuilder(" FROM Producto ");
			buString.append(" WHERE ");
			buString.append(columna + " = :codigoSAP ");
			buString.append(" AND unidadmedida.pkCodigoUnidadMedida = :tipoconsumo ");
			Query query = query(buString.toString());
			query.setString("codigoSAP", codigoSAPProducto);
			query.setLong("tipoconsumo", tipoUnidad);
			query.setMaxResults(1);
			return (Producto) query.uniqueResult();
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
	}
}
