package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProcesoLogic.java
 * Modificado: Jan 16, 2010 8:19:29 PM
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
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
import pe.com.pacasmayo.sgcp.logica.facade.ProcesoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipoproducto;
import pe.com.pacasmayo.sgcp.persistencia.querier.LineaNegocioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProcesoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoProductoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ProcesoLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, ConstantesFiltros,
		ProcesoLogicFacade {

	final static String NOMBRE_PROCESO = "nombreProceso";

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public ProcesoLogic() {

		ProcesoLogic.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesos()
	 */
	public List<ProcesoBean> obtenerProcesos() throws LogicaException {

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
			return beanFactory.transformarListaProceso(ProcesoQuerier.getAllOrderBy(NOMBRE_PROCESO));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosBasico()
	 */
	public List<ProcesoBean> obtenerProcesosBasico() throws LogicaException {

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
			return beanFactory.transformarListaProcesoBasico(ProcesoQuerier.getAllOrderBy(NOMBRE_PROCESO));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosParaCombo()
	 */
	public List<ProcesoBean> obtenerProcesosParaCombo() throws LogicaException {

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
			return beanFactory.transformarListaProcesoParaCombos(ProcesoQuerier.getAllOrderBy(NOMBRE_PROCESO));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#obtenerProceso
	 * (java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#obtenerProceso
	 * (java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#obtenerProceso
	 * (java.lang.Long)
	 */
	public ProcesoBean obtenerProceso(Long codigoProceso) throws LogicaException {

		String mensajeError = "";

		ProcesoBean procesoBean = null;

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
			procesoBean = beanFactory.transformarProceso(ProcesoQuerier.getById(codigoProceso));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return procesoBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorCodigoSCC(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorCodigoSCC(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorCodigoSCC(java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosPorCodigoSCC(Long codigoSCC) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

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
			productoBeans = beanFactory.transformarListaProceso(ProcesoQuerier.findByCodigoSCC(codigoSCC));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorNombre(java.lang.String)
	 */
	public List<ProcesoBean> obtenerProcesosPorNombre(String nombreProceso) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> procesoBeans = null;

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
			procesoBeans = beanFactory.transformarListaProceso(ProcesoQuerier.findByNombre(nombreProceso));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return procesoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorOrdenEjecucion(java.lang.Short)
	 */
	public List<ProcesoBean> obtenerProcesosPorOrdenEjecucion(Short ordenEjecucion) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

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
			productoBeans = beanFactory.transformarListaProceso(ProcesoQuerier.findByOrdenEjecucion(ordenEjecucion));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorCodigoLineaNegocio(java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

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
			productoBeans = beanFactory.transformarListaProceso(ProcesoQuerier.findByCodigoLineaNegocio(codigoLineaNegocio));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosBasicosPorCodigoLineaNegocio(java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosBasicosPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

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
			productoBeans = beanFactory
					.transformarListaProcesoBasico(ProcesoQuerier.findByCodigoLineaNegocio(codigoLineaNegocio));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorCodigoTipoProducto(java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosPorCodigoTipoProducto(Long codigoTipoProducto) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

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
			productoBeans = beanFactory.transformarListaProceso(ProcesoQuerier.findByCodigoTipoProducto(codigoTipoProducto));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorOrdenLineaNegocio(short, java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosPorOrdenLineaNegocio(short orden, Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> procesos = null;

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
			procesos = beanFactory.transformarListaProceso(ProcesoQuerier.getProcesosPorOrdenLineaNegocio(orden,
					codigoLineaNegocio));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return procesos;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesoDataObject(java.lang.Long)
	 */
	public Proceso obtenerProcesoDataObject(Long codigoProceso) throws LogicaException {

		String mensajeError = "";

		Proceso proceso = null;

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
			proceso = ProcesoQuerier.getById(codigoProceso);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return proceso;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosDataObjects(java.lang.Long)
	 */
	public List<Proceso> obtenerProcesosDataObjects(Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<Proceso> procesos = null;
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
			procesos = ProcesoQuerier.findByCodigoLineaNegocio(codigoLineaNegocio);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return procesos;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesoDTO(java.lang.Long)
	 */
	public ProcesoDTO obtenerProcesoDTO(Long codigoProceso) throws LogicaException {

		ProcesoDTO procesoDTO = new ProcesoDTO();
		Proceso proceso = obtenerProcesoDataObject(codigoProceso);

		procesoDTO.setCodigoSccProceso(proceso.getCodigoSccProceso());
		procesoDTO.setDescripcionProceso(proceso.getDescripcionProceso());
		procesoDTO.setNombreProceso(proceso.getNombreProceso());
		procesoDTO.setOrdenEjecucionProceso(proceso.getOrdenEjecucionProceso());
		procesoDTO.setPkCodigoProceso(proceso.getPkCodigoProceso());
		procesoDTO.setSiglasProceso(proceso.getSiglasProceso());

		return procesoDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
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
		filtro.setValor(CODIGO_SCC);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(3);
		filtro.setValor(NOMBRE);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(4);
		filtro.setValor(ORDEN_EJECUCION);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(5);
		filtro.setValor(LINEA_NEGOCIO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(6);
		filtro.setValor(TIPO_PRODUCTO);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * insertarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * insertarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * insertarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	public void insertarProceso(ProcesoBean procesoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Proceso proceso = new Proceso();

			proceso.setCodigoSapProceso(procesoBean.getCodigoSAP());
			proceso.setCodigoSccProceso(procesoBean.getCodigoSCC());
			proceso.setDescripcionProceso(procesoBean.getDescripcion());

			Lineanegocio lineaNegocio = new Lineanegocio();
			lineaNegocio.setPkCodigoLineanegocio(procesoBean.getLineaNegocio().getCodigo());

			proceso.setLineanegocio(lineaNegocio);

			proceso.setNombreProceso(procesoBean.getNombre());
			proceso.setSiglasProceso(procesoBean.getSiglas());
			proceso.setOrdenEjecucionProceso(procesoBean.getOrdenEjecucion());

			Tipoproducto tipoProducto = new Tipoproducto();
			tipoProducto.setPkCodigoTipoproducto(procesoBean.getTipoProducto().getCodigo());

			proceso.setTipoproducto(tipoProducto);

			ProcesoQuerier.save(proceso);

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
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * actualizarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * actualizarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * actualizarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	public void actualizarProceso(ProcesoBean procesoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Proceso proceso = ProcesoQuerier.getById(procesoBean.getCodigo());

			proceso.setCodigoSapProceso(procesoBean.getCodigoSAP());
			proceso.setCodigoSccProceso(procesoBean.getCodigoSCC());
			proceso.setDescripcionProceso(procesoBean.getDescripcion());

			Lineanegocio lineaNegocio = LineaNegocioQuerier.getById(procesoBean.getLineaNegocio().getCodigo());

			proceso.setLineanegocio(lineaNegocio);

			proceso.setNombreProceso(procesoBean.getNombre());
			proceso.setSiglasProceso(procesoBean.getSiglas());
			proceso.setOrdenEjecucionProceso(procesoBean.getOrdenEjecucion());

			Tipoproducto tipoProducto = TipoProductoQuerier.getById(procesoBean.getTipoProducto().getCodigo());

			proceso.setTipoproducto(tipoProducto);

			ProcesoQuerier.update(proceso);

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
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * eliminarProceso(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * eliminarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * eliminarProceso(pe.com.pacasmayo.sgcp.bean.ProcesoBean)
	 */
	public void eliminarProceso(ProcesoBean procesoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Proceso proceso = ProcesoQuerier.getById(procesoBean.getCodigo());

			ProcesoQuerier.delete(proceso);

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
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesoPorcodigoLineaYCodigoProducto(java.lang.Long,
	 * java.lang.Long)
	 */
	public Proceso obtenerProcesoPorcodigoLineaYCodigoProducto(Long codigoLinea, Long codigoProducto) throws LogicaException {

		String mensajeError = "";
		Proceso proceso = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();

			proceso = ProcesoQuerier.obtenerProcesoPorcodigoLineaYCodigoProducto(codigoLinea, codigoProducto);
		} catch (ParametroInvalidoException e) {
			logger.error(e.getMensaje());

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMensaje());

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return proceso;
	}

	public List<ProcesoBean> obtenerProcesosPorCodigoUnidad(Long valorUnidad) throws LogicaException {
		String mensajeError = "";

		List<ProcesoBean> procesosBeans = null;

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
			procesosBeans = beanFactory.transformarListaProceso(ProcesoQuerier.findByCodigoLineaNegocio(valorUnidad));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return procesosBeans;
	}
}