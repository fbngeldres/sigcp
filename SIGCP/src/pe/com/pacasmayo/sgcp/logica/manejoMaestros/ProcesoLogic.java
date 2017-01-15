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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.ProcesoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.ProcesoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProcesoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ProcesoLogic implements ConstantesMensajePresentacion,
		ConstantesMensajeAplicacion, ConstantesFiltros, ProcesoLogicFacade {

	final static String NOMBRE_PROCESO = "nombreProceso";

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeandozerFactory beanFactory;

	public ProcesoLogic() {

		ProcesoLogic.beanFactory = BeandozerFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesos()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesos()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesos()
	 */
	public List<ProcesoBean> obtenerProcesos() throws LogicaException {

		String mensajeError = "";

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {
			return (List<ProcesoBean>) beanFactory.tranformarLista(
					ProcesoQuerier.getAllOrderBy(NOMBRE_PROCESO),
					ProcesoBeanImpl.class);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorOrdenEjecucion(java.lang.Short)
	 */
	public List<ProcesoBean> obtenerProcesosPorOrdenEjecucion(
			Short ordenEjecucion) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {
			productoBeans = (List<ProcesoBean>) beanFactory.tranformarLista(
					ProcesoQuerier.findByOrdenEjecucion(ordenEjecucion),
					ProcesoBeanImpl.class);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
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
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosBasicosPorCodigoLineaNegocio(java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosBasicosPorCodigoLineaNegocio(
			Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {
			productoBeans = (List<ProcesoBean>) beanFactory
					.tranformarLista(ProcesoQuerier
							.findByCodigoLineaNegocio(codigoLineaNegocio),
							ProcesoBeanImpl.class);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
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
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorOrdenLineaNegocio(short, java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosPorOrdenLineaNegocio(short orden,
			Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> procesos = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {
			procesos = (List<ProcesoBean>) beanFactory.tranformarLista(
					ProcesoQuerier.getProcesosPorOrdenLineaNegocio(orden,
							codigoLineaNegocio), ProcesoBeanImpl.class);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
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
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogicFacade#
	 * obtenerProcesosPorCodigoLineaNegocio(java.lang.Long)
	 */
	public List<ProcesoBean> obtenerProcesosPorCodigoLineaNegocio(
			Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<ProcesoBean> productoBeans = null;

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		try {
			productoBeans = (List<ProcesoBean>) beanFactory
					.tranformarLista(ProcesoQuerier
							.findByCodigoLineaNegocio(codigoLineaNegocio),
							ProcesoBeanImpl.class);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Proceso.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return productoBeans;
	}

}