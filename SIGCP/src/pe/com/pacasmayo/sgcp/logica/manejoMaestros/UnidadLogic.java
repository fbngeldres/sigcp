package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadLogic.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
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

import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.UnidadBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class UnidadLogic implements ConstantesMensajePresentacion,
		ConstantesMensajeAplicacion, ConstantesFiltros, UnidadLogicFacade {

	final static String NOMBRE_UNIDAD = "nombreUnidad";

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeandozerFactory beanFactory;

	public UnidadLogic() {

		UnidadLogic.beanFactory = BeandozerFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#obtenerUnidades
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#obtenerUnidades
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#obtenerUnidades
	 * ()
	 */
	public List<UnidadBean> obtenerUnidades() throws LogicaException {

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
			return (List<UnidadBean>) beanFactory.tranformarLista(
					UnidadQuerier.getAllOrderBy(NOMBRE_UNIDAD),
					UnidadBeanImpl.class);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidad.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#
	 * obtenerUnidadesPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#
	 * obtenerUnidadesPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#
	 * obtenerUnidadesPorNombre(java.lang.String)
	 */
	public List<UnidadBean> obtenerUnidadesPorNombre(String nombreUnidad)
			throws LogicaException {

		String mensajeError = "";
		List<UnidadBean> listaUnidadBean = null;

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
			listaUnidadBean = (List<UnidadBean>) beanFactory.tranformarLista(
					UnidadQuerier.findByNombre(nombreUnidad),
					UnidadBeanImpl.class);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidad.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaUnidadBean;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#
	 * obtenerUnidadesPorCodigoSociedad(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#
	 * obtenerUnidadesPorCodigoSociedad(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogicFacade#
	 * obtenerUnidadesPorCodigoSociedad(java.lang.Long)
	 */
	public List<UnidadBean> obtenerUnidadesPorCodigoSociedad(Long codigoSociedad)
			throws LogicaException {

		String mensajeError = "";

		List<UnidadBean> listaUnidadBean = null;

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

			listaUnidadBean = (List<UnidadBean>) beanFactory.tranformarLista(
					UnidadQuerier.findByCodigoSociedad(codigoSociedad),
					UnidadBeanImpl.class);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Unidad.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaUnidadBean;
	}

}