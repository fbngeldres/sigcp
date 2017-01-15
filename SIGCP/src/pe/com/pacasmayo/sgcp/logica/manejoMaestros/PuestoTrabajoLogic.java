package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PuestoTrabajologic.java
 * Modificado: Nov 26, 2009 11:03:19 AM
 * Autor: evelyn.santamaria
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
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.PuestoTrabajoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.PuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class PuestoTrabajoLogic implements ConstantesFiltros,
		ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		PuestoTrabajoLogicFacade {

	final static String NOMBRE_PUESTO_TRABAJO = "nombrePuestotrabajo";

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeandozerFactory beanFactory;

	public PuestoTrabajoLogic() {

		this.beanFactory = BeandozerFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorLineaNegocio(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorUnidad(
			Long codigoUnidad) throws LogicaException {
		List<PuestoTrabajoBean> puestos = new ArrayList<PuestoTrabajoBean>();
		Session session = null;

		String mensajeError = "";

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
			puestos = (List<PuestoTrabajoBean>) beanFactory.tranformarLista(
					PuestoTrabajoQuerier.findByBussinesUnidad(codigoUnidad),
					PuestoTrabajoBeanImpl.class);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Sociedad.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestos;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajo()
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajo()
			throws LogicaException {

		Session session = null;

		String mensajeError = "";

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
			return (List<PuestoTrabajoBean>) beanFactory.tranformarLista(
					PuestoTrabajoQuerier.getAllOrderBy(NOMBRE_PUESTO_TRABAJO),
					PuestoTrabajoBeanImpl.class);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}
}
