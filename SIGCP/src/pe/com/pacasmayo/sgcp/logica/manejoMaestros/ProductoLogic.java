package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProductoLogic.java
 * Modificado: Dec 8, 2009 4:00:29 PM
 * Autor: evelyn.santamaria
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UtilBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipoconsumo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipoproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadmedida;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoCategoriaProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadMedidaQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ProductoLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, ConstantesFiltros,
		ProductoLogicFacade, ConstantesLogicaNegocio {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	@SuppressWarnings("static-access")
	public ProductoLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosDTO()
	 */
	public List<ProductoDTO> obtenerProductosDTO() throws LogicaException {

		List<Producto> productos = null;
		List<ProductoDTO> productosDTO = null;
		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();

			productos = ProductoQuerier.getAll();

			productosDTO = new ArrayList<ProductoDTO>();
			for (Iterator<Producto> iterator = productos.iterator(); iterator.hasNext();) {
				Producto producto = iterator.next();
				ProductoDTO productoDTO = new ProductoDTO();

				productoDTO.setPkCodigoProducto(producto.getPkCodigoProducto());
				productoDTO.setDescripcionProducto(producto.getDescripcionProducto());
				productoDTO.setFkCodigoTipoProducto(producto.getTipoproducto().getPkCodigoTipoproducto());
				productoDTO.setNombreProducto(producto.getNombreProducto());
				productoDTO.setGrupoProducto(producto.getGrupoProducto());
				productosDTO.add(productoDTO);
			}
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
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBTENER_ORDEN_PRODUCCION);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return productosDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductos()
	 */
	public List<ProductoBean> obtenerProductos() throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			return beanFactory.transformarListaProductos(ProductoQuerier.getAll());
		} catch (SesionVencidaException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosParaCombo()
	 */
	public List<ProductoBean> obtenerProductosParaCombo() throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			return beanFactory.transformarListaProductoParaCombos(ProductoQuerier.getAll());
		} catch (SesionVencidaException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosOrdenAlfabetico()
	 */
	public List<ProductoBean> obtenerProductosOrdenAlfabetico() throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			return beanFactory.transformarListaProductos(ProductoQuerier.getAllOrderByNombre());
		} catch (HibernateException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorPuestoTrabajoProceso(java.lang.Long, java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorPuestoTrabajoProceso(java.lang.Long, java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductoPorPuestoTrabajoProceso(Long codigoPuestoTrabajo, Long codigoProceso)
			throws LogicaException {

		List<ProductoBean> productosBean = new ArrayList<ProductoBean>();
		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			List<Producto> productos = ProductoQuerier.findByMachineAndProcess(codigoPuestoTrabajo, codigoProceso);

			for (int indice = 0; indice < productos.size(); indice++) {
				Producto producto = productos.get(indice);
				productosBean.add(beanFactory.transformarProducto(producto));
			}
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
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return productosBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProducto(java.lang.Long)
	 */
	public ProductoBean obtenerProducto(Long codigoProducto) throws LogicaException {

		String mensajeError = "";
		ProductoBean productoBean = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBean = beanFactory.transformarProducto(ProductoQuerier.getById(codigoProducto));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosPorNombre(java.lang.String)
	 */
	public List<ProductoBean> obtenerProductosPorNombre(String nombreProducto) throws LogicaException {

		String mensajeError = "";

		List<ProductoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBeans = beanFactory.transformarListaProductos(ProductoQuerier.findByNombre(nombreProducto));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorCodigoSAP(java.lang.String)
	 */
	public ProductoBean obtenerProductoPorCodigoSAP(String codigoSAP) throws LogicaException {

		String mensajeError = "";
		List<ProductoBean> productos = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productos = beanFactory.transformarListaProductos(ProductoQuerier.findByCodigoSAP(codigoSAP));

			if (productos != null && productos.size() > 0)
				return productos.get(0);
		} catch (SesionVencidaException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorCodigoSAP(java.lang.String)
	 */
	public ProductoBean obtenerProductoPorCodigoSAPUnidadMedida(String codigoSAP, Long unidadMedida) throws LogicaException {

		String mensajeError = "";
		ProductoBean productos = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {

			productos = beanFactory.transformarProducto(ProductoQuerier
					.findByProductoCodigoSAPTipoUnidad(codigoSAP, unidadMedida));

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productos;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorCodigoSAP(java.lang.String)
	 */
	public ProductoBean obtenerProductoPorCodigoSAPBolsa(String codigoSAPBolsa) throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			ProductoBean productoBean = beanFactory.transformarProducto(ProductoQuerier.findByCodigoSAPBolsa(codigoSAPBolsa));

			return productoBean;
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
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorCodigoSAP(java.lang.String)
	 */
	public ProductoBean obtenerProductoPorCodigoSAPBigBag(String codigoSAPBigBag) throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			ProductoBean productoBean = beanFactory.transformarProducto(ProductoQuerier.findByCodigoSAPBigBag(codigoSAPBigBag));

			return productoBean;
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
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public ProductoBean obtenerProductoPorCodigoSAPEspecial1(String codigoSAPEspecial1) throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			ProductoBean productoBean = beanFactory.transformarProducto(ProductoQuerier
					.findByCodigoSAPEspecial1(codigoSAPEspecial1));

			return productoBean;
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
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public ProductoBean obtenerProductoPorCodigoSAPEspecial2(String codigoSAPEspecial2) throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			ProductoBean productoBean = beanFactory.transformarProducto(ProductoQuerier
					.findByCodigoSAPEspecial2(codigoSAPEspecial2));

			return productoBean;
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
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public ProductoBean obtenerProductoPorCodigoSAPEspecial3(String codigoSAPEspecial3) throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			ProductoBean productoBean = beanFactory.transformarProducto(ProductoQuerier
					.findByCodigoSAPEspecial3(codigoSAPEspecial3));

			return productoBean;
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
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public ProductoBean obtenerProductoPorCodigoSAPEspecial4(String codigoSAPEspecial4, Long tipoUnidadMedida)
			throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			ProductoBean productoBean = beanFactory.transformarProducto(ProductoQuerier.findByCodigoSAPEspecial4(
					codigoSAPEspecial4, tipoUnidadMedida));

			return productoBean;
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosPorCodigoSCC(java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductosPorCodigoSCC(Long codigoSCC) throws LogicaException {

		String mensajeError = "";

		List<ProductoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBeans = beanFactory.transformarListaProductos(ProductoQuerier.findByCodigoSCC(codigoSCC));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosPorSiglas(java.lang.String)
	 */
	public List<ProductoBean> obtenerProductosPorSiglas(String siglasProducto) throws LogicaException {

		String mensajeError = "";

		List<ProductoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBeans = beanFactory.transformarListaProductos(ProductoQuerier.findBySiglas(siglasProducto));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosPorTipoProducto(java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductosPorTipoProducto(Long codigoTipoProducto) throws LogicaException {

		String mensajeError = "";

		List<ProductoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			/*
			 * productoBeans =
			 * beanFactory.transformarListaProductos(ProductoQuerier
			 * .findByCodigoEstadoProductoOrdenNombre(codigoTipoProducto));
			 */
			productoBeans = beanFactory.transformarListaProductos(ProductoQuerier.findByCodigoTipoProducto(codigoTipoProducto));

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosPorEstadoProducto(java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductosPorEstadoProducto(Long codigoEstadoProducto) throws LogicaException {

		String mensajeError = "";

		List<ProductoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBeans = beanFactory.transformarListaProductos(ProductoQuerier
					.findByCodigoEstadoProducto(codigoEstadoProducto));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductosPorPropiedades(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<ProductoBean> obtenerProductosPorPropiedades(Map propiedades) {

		return beanFactory.transformarListaProductos(ProductoQuerier.findByProperties(propiedades));
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorOrdenProduccion(java.lang.Long)
	 */
	public ProductoBean obtenerProductoPorOrdenProduccion(Long codigoOrdenProduccion) throws EntornoEjecucionException,
			LogicaException {

		String mensajeError = "";
		ProductoBean productoBean = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBean = beanFactory.transformarProducto(ProductoQuerier.findByOrdenProduccion(codigoOrdenProduccion));
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBean;

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoBasicoPorOrdenProduccion(java.lang.Long)
	 */
	public ProductoBean obtenerProductoBasicoPorOrdenProduccion(Long codigoOrdenProduccion) throws EntornoEjecucionException,
			LogicaException {

		String mensajeError = "";
		ProductoBean productoBean = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBean = beanFactory.transformarProductoBasico(ProductoQuerier.findByOrdenProduccion(codigoOrdenProduccion));
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBean;

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorProceso(java.lang.Long)
	 */
	public List<Producto> obtenerProductoPorProceso(Long codigoProceso) throws EntornoEjecucionException, LogicaException {

		String mensajeError = "";
		List<Producto> productos = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productos = ProductoQuerier.findByCodigoProceso(codigoProceso);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productos;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorCodigoLineaNegocio(java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductoPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException {
		String mensajeError = "";
		List<ProductoBean> productos = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productos = beanFactory.transformarListaProductos(ProductoQuerier.findByCodigoLineaNegocio(codigoLineaNegocio));
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productos;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerAtributos()
	 */
	public List<UtilBean> obtenerAtributos() {

		List<UtilBean> filtros = new ArrayList<UtilBean>();

		UtilBean filtro = new UtilBeanImpl();
		filtro.setCodigo(1);
		filtro.setValor(CODIGO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(2);
		filtro.setValor(CODIGO_SAP);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(3);
		filtro.setValor(CODIGO_SCC);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(4);
		filtro.setValor(TIPO_MATERIAL);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(5);
		filtro.setValor(ESTADO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(6);
		filtro.setValor(NOMBRE);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(7);
		filtro.setValor(SIGLAS);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * insertarProductoProduccion(pe.com.pacasmayo.sgcp.bean.ProductoBean,
	 * java.util.List)
	 */
	public void insertarProductoProduccion(ProductoBean productoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Producto producto = new Producto();
			if (productoBean.getCodigoSAP() != null)
				producto.setCodigoSapProducto(productoBean.getCodigoSAP());
			producto.setCodigoSccProducto(productoBean.getCodigoSCC());
			producto.setCostoProducto(productoBean.getCosto());
			producto.setDescripcionProducto(productoBean.getDescripcion());
			producto.setNombreProducto(productoBean.getNombre());
			producto.setSiglasProducto(productoBean.getSiglas());
			producto.setStockMaximoProducto(productoBean.getStockMaximo());
			producto.setStockMinimoProducto(productoBean.getStockMinimo());

			if (productoBean.getTipoCategoriaProductoBean().getCodigo() != null) {
				Tipocategoriaproducto tipocategoriaproducto = new Tipocategoriaproducto();
				tipocategoriaproducto.setPkCodigoTipocategoriaproducto(productoBean.getTipoCategoriaProductoBean().getCodigo());

				producto.setTipocategoriaproducto(tipocategoriaproducto);
			}

			if (productoBean.getTipoConsumo().getCodigo() != null) {
				Tipoconsumo Tipoconsumo = new Tipoconsumo();
				Tipoconsumo.setPkCodigoTipoconsumo(productoBean.getTipoConsumo().getCodigo());

				producto.setTipoconsumo(Tipoconsumo);
			}

			Estadoproducto estadoProducto = new Estadoproducto();
			estadoProducto.setPkCodigoEstadoproducto(productoBean.getEstadoProducto().getCodigo());

			producto.setEstadoproducto(estadoProducto);

			Tipoproducto tipoProducto = new Tipoproducto();
			tipoProducto.setPkCodigoTipoproducto(productoBean.getTipoProducto().getCodigo());

			producto.setTipoproducto(tipoProducto);

			Unidadmedida unidadMedida = new Unidadmedida();
			unidadMedida.setPkCodigoUnidadMedida(productoBean.getUnidadMedida().getCodigo());

			producto.setUnidadmedida(unidadMedida);

			ProductoQuerier.save(producto);

			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * actualizarProductoProduccion(pe.com.pacasmayo.sgcp.bean.ProductoBean,
	 * java.util.List)
	 */
	public void actualizarProductoProduccion(ProductoBean productoBean) throws LogicaException {
		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Producto producto = ProductoQuerier.getById(productoBean.getCodigo());

			if (productoBean.getCodigoSAP() != null)
				producto.setCodigoSapProducto(productoBean.getCodigoSAP());

			producto.setCodigoSccProducto(productoBean.getCodigoSCC());
			producto.setCostoProducto(productoBean.getCosto());
			producto.setDescripcionProducto(productoBean.getDescripcion());
			producto.setNombreProducto(productoBean.getNombre());
			producto.setSiglasProducto(productoBean.getSiglas());
			producto.setStockMaximoProducto(productoBean.getStockMaximo());
			producto.setStockMinimoProducto(productoBean.getStockMinimo());

			if (productoBean.getTipoCategoriaProductoBean().getCodigo() != null) {
				Tipocategoriaproducto tipoCategoriaProducto = TipoCategoriaProductoQuerier.getById(productoBean
						.getTipoCategoriaProductoBean().getCodigo());
				producto.setTipocategoriaproducto(tipoCategoriaProducto);
			} else {
				// Dado que se puede tener vacio el Tipo de Categoria del
				// Producto, si el usuario a modificar un producto,
				// blanquea el combo Tipo de Categoria, se setea null en BD para
				// dicho Campo en la tabla Productos
				producto.setTipocategoriaproducto(null);
			}

			if (productoBean.getTipoConsumo().getCodigo() != null) {
				Tipoconsumo tipoconsumo = new Tipoconsumo();
				tipoconsumo.setPkCodigoTipoconsumo(productoBean.getTipoConsumo().getCodigo());

				producto.setTipoconsumo(tipoconsumo);
			} else {

				producto.setTipoconsumo(null);
			}

			Estadoproducto estadoProducto = EstadoProductoQuerier.getById(productoBean.getEstadoProducto().getCodigo());

			producto.setEstadoproducto(estadoProducto);

			Tipoproducto tipoProducto = TipoProductoQuerier.getById(productoBean.getTipoProducto().getCodigo());

			producto.setTipoproducto(tipoProducto);

			Unidadmedida unidadMedida = UnidadMedidaQuerier.getById(productoBean.getUnidadMedida().getCodigo());

			producto.setUnidadmedida(unidadMedida);

			ProductoQuerier.update(producto);

			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * eliminarProducto(pe.com.pacasmayo.sgcp.bean.ProductoBean)
	 */
	public void eliminarProducto(ProductoBean productoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			// se eliminan los productos componentes
			ComponenteQuerier.deleteByCodigoProducto(productoBean.getCodigo());

			Producto producto = ProductoQuerier.getById(productoBean.getCodigo());

			ProductoQuerier.delete(producto);

			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogicFacade#
	 * obtenerProductoPorProceso(java.lang.Long)
	 */
	public List<ProductoBean> obtenerProductosPorProceso(Long codigoProceso) throws EntornoEjecucionException, LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			return beanFactory.transformarListaProductos(ProductoQuerier.findByCodigoProceso(codigoProceso));
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public List<ProductoBean> obtenerProductosPorUnidad(Long codigoUnidad) throws EntornoEjecucionException, LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			return beanFactory.transformarListaProductos(ProductoQuerier.findByCodigoUnidad(codigoUnidad));
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public List<ProductoBean> obtenerProductosFiltrandoPorProductoYTipoTipoProducto(Long codigoProducto, Long codigoTipoProducto)
			throws LogicaException {
		String mensajeError = "";

		List<ProductoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			productoBeans = beanFactory.transformarListaProductos(ProductoQuerier
					.obtenerProductosFiltrandoPorProductoYTipoTipoProducto(codigoProducto, codigoTipoProducto));

			return productoBeans;

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
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade#esMateriaPrima
	 * (java.lang.Long)
	 */
	public boolean esMateriaPrima(Long codigoProducto) throws LogicaException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			Producto producto = ProductoQuerier.getById(codigoProducto);

			long tipoProd = producto.getTipoproducto().getPkCodigoTipoproducto().longValue();

			Long codigoPP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_EN_PROCESO));
			Long codigoMP = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PRODUCTO_MATERIA_PRIMA));

			boolean poseeCompEnHojaRuta = HojaRutaComponenteQuerier.poseeCompEnHojaRuta(producto.getPkCodigoProducto());
			boolean productoProcesoSinComp = (tipoProd == codigoPP && !poseeCompEnHojaRuta);

			boolean esMateriaPrima = tipoProd == codigoMP || productoProcesoSinComp;
			return esMateriaPrima;
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	public ProductoBean obtenerProductoPorNombre(String nombreProducto) throws LogicaException {

		String mensajeError = "";

		ProductoBean productoBean = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productoBean = beanFactory.transformarProducto(ProductoQuerier.findByNombre_(nombreProducto));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBean;
	}

	public List<ProductoBean> obtenerProductoPorPuestoTrabajo(Long codigoPuestoTrabajo) throws LogicaException {

		List<ProductoBean> productosBean = new ArrayList<ProductoBean>();
		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			List<Producto> productos = ProductoQuerier.obtenerProductosPorPuestoTrabajo(codigoPuestoTrabajo);

			for (int indice = 0; indice < productos.size(); indice++) {
				Producto producto = productos.get(indice);
				productosBean.add(beanFactory.transformarProductoBasico(producto));
			}
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
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

		return productosBean;
	}

	public ProductoBean obtenerProductoPorCodigoSAPDiferenteTipoConsumo(String codigoSAPProducto, String tipoConsumo)
			throws LogicaException {
		String mensajeError = "";
		List<ProductoBean> productos = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
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
		}

		try {
			productos = beanFactory.transformarListaProductos(ProductoQuerier.findByCodigoSAPNDiferenteTipoConsumo(
					codigoSAPProducto, tipoConsumo));

			if (productos != null && productos.size() > 0)
				return productos.get(0);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Producto.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return null;
	}

}