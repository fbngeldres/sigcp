package pe.com.pacasmayo.sgcp.logica.parteTecnico;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.OrdenReporteBean;
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
import pe.com.pacasmayo.sgcp.logica.facade.OrdenReporteLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenreporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.querier.OrdenReporteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: OrdenReporteLogic.java
 * Modificado: Aug 23, 2012 7:29:06 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class OrdenReporteLogic implements ConstantesMensajeAplicacion, ConstantesLogicaNegocio, ConstantesAplicacionAction,
		ConstantesMensajePresentacion, OrdenReporteLogicFacade, ConstantesFiltros {

	private BeanFactory beanFactory;
	private Log logger = LogFactory.getLog(this.getClass());

	public OrdenReporteLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	public List<OrdenReporteBean> obtenerOrdenResumenProducto(String tipoReporte) throws AplicacionException {

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
			List<OrdenReporteBean> list = beanFactory.transformarListaOrdenResumen(OrdenReporteQuerier.findByTipo(tipoReporte));

			return list;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public List<OrdenReporteBean> obtenerOrdenResumenProductoOrdenadoPorTipo(String tipoReporte) throws AplicacionException {

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

			List<OrdenReporteBean> list = obtenerOrdenResumenProductoOrdenadoPorTipoDAO(tipoReporte);

			return list;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public List<OrdenReporteBean> obtenerOrdenResumenProductoOrdenadoPorTipoDAO(String tipoReporte) throws LogicaException {

		String mensajeError = "";
		try {

			List<OrdenReporteBean> list = beanFactory.transformarListaOrdenResumen(OrdenReporteQuerier
					.findByTipoOrderByOrden(tipoReporte));

			return list;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		}

	}

	public Boolean eliminar(Long codigo) throws LogicaException {
		String mensajeError = "";
		Transaction tx = null;
		Session session = null;
		Boolean operacion = false;
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			Ordenreporte ordenReporte = OrdenReporteQuerier.getById(codigo);
			if (ordenReporte == null) {
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
				throw new LogicaException(mensajeError);
			}
			OrdenReporteQuerier.delete(ordenReporte);
			tx.commit();
			operacion = true;
		} catch (ParametroInvalidoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return operacion;
	}

	public Boolean ingresar(OrdenReporteBean ordenReporteBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;
		Boolean operacion = false;
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Ordenreporte ordenReporte = new Ordenreporte();

			ordenReporte.setOrdenProcesoProducto(ordenReporteBean.getOrdenProcesoProducto());
			ordenReporte.setOrdenReporte(ordenReporteBean.getOrdenReporte());
			ordenReporte.setTipoOrdenReporte(ordenReporteBean.getTipoOrdenReporte());

			if (ordenReporteBean.getProceso().getCodigo() != null) {
				Proceso proceso = new Proceso();
				proceso.setPkCodigoProceso(ordenReporteBean.getProceso().getCodigo());
				ordenReporte.setProceso(proceso);
			}

			if (ordenReporteBean.getProducto().getCodigo() != null) {
				Producto producto = new Producto();
				producto.setPkCodigoProducto(ordenReporteBean.getProducto().getCodigo());
				ordenReporte.setProducto(producto);
			}
			OrdenReporteQuerier.save(ordenReporte);
			tx.commit();
			operacion = true;
		} catch (ParametroInvalidoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return operacion;
	}

	public Boolean modificar(OrdenReporteBean ordenReporteBean) throws LogicaException {
		String mensajeError = "";
		Transaction tx = null;
		Session session = null;
		Boolean operacion = false;
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Ordenreporte ordenReporte = OrdenReporteQuerier.getById(ordenReporteBean.getCodigo());

			ordenReporte.setOrdenProcesoProducto(ordenReporteBean.getOrdenProcesoProducto());
			ordenReporte.setOrdenReporte(ordenReporteBean.getOrdenReporte());
			ordenReporte.setTipoOrdenReporte(ordenReporteBean.getTipoOrdenReporte());

			if (ordenReporteBean.getProceso().getCodigo() != null) {
				Proceso proceso = new Proceso();
				proceso.setPkCodigoProceso(ordenReporteBean.getProceso().getCodigo());
				ordenReporte.setProceso(proceso);
			} else {
				ordenReporte.setProceso(null);
			}
			if (ordenReporteBean.getProducto().getCodigo() != null) {
				Producto producto = new Producto();
				producto.setPkCodigoProducto(ordenReporteBean.getProducto().getCodigo());
				ordenReporte.setProducto(producto);
			} else {
				ordenReporte.setProducto(null);
			}

			OrdenReporteQuerier.update(ordenReporte);
			tx.commit();
			operacion = true;
		} catch (ParametroInvalidoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e);
			if (tx != null)
				tx.rollback();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return operacion;
	}

	public OrdenReporteBean obtenerPorcodigo(Long codigo) throws LogicaException {

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
		OrdenReporteBean ordenReporteBean = null;

		try {
			ordenReporteBean = beanFactory.transformarOrdenReporte(OrdenReporteQuerier.getById(codigo));
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return ordenReporteBean;
	}

	public List<String> obtenerTiposReporte() throws LogicaException {

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
			List<String> lista = OrdenReporteQuerier.getTipoReporte();
			return lista;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	public List<UtilBean> obtenerAtributos() {
		List<UtilBean> filtros = new ArrayList<UtilBean>();

		UtilBean filtro = new UtilBeanImpl();
		filtro.setCodigo(1);
		filtro.setValor(TIPO_REPORTE);
		filtros.add(filtro);

		return filtros;
	}

}
