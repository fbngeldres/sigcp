package pe.com.pacasmayo.sgcp.logica.stock;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.EstadoCubicacionBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoCubicacionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocubicacion;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoCubicacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoCubicacionLogic.java
 * Modificado: Jun 8, 2010 9:37:21 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class EstadoCubicacionLogic implements ConstantesFiltros, ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		EstadoCubicacionLogicFacade {

	private static BeanFactory beanFactory;

	private Log logger = LogFactory.getLog(this.getClass());

	@SuppressWarnings("static-access")
	public EstadoCubicacionLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	public List<EstadoCubicacionBean> getEstadosCubicacion() throws LogicaException {

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
			return beanFactory.transformarListaEstadoCubicacion(EstadoCubicacionQuerier.getAllByNombre());
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadocubicacion.class.getName().toString();
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<Estadocubicacion> getEstadosCubicacionDTO() throws LogicaException {

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
			return EstadoCubicacionQuerier.getAllByNombre();
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadocubicacion.class.getName().toString();
			logger.error(e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

}
