package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: DivisionLogic.java
 * Modificado: Dec 1, 2009 10:11:13 PM
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

import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeandozerFactoryImpl;
import pe.com.pacasmayo.sgcp.bean.impl.DivisionBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.DivisionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Division;
import pe.com.pacasmayo.sgcp.persistencia.querier.DivisionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class DivisionLogic implements ConstantesMensajePresentacion,
		ConstantesMensajeAplicacion, DivisionLogicFacade, ConstantesFiltros {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeandozerFactory beanFactory;

	public DivisionLogic() {

		this.beanFactory = BeandozerFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogicFacade#
	 * obtenerDivision(java.lang.Long)
	 */
	public DivisionBean obtenerDivision(Long codigoDivision)
			throws LogicaException {

		DivisionBean divisionBean = null;
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

			divisionBean = (DivisionBean) beanFactory.transformarBean(
					DivisionQuerier.getById(codigoDivision),
					DivisionBeanImpl.class);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return divisionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogicFacade#
	 * obtenerDivisiones()
	 */
	public List<DivisionBean> obtenerDivisiones() throws LogicaException {

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
			return (List<DivisionBean>) beanFactory.tranformarLista(
					DivisionQuerier
							.getAllOrderBy(DivisionQuerier.NOMBRE_DIVISION),
					DivisionBeanImpl.class);
		} catch (AplicacionException e) {
			e.printStackTrace();
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Division.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<Division> obtenerDivisionesDataObject() throws LogicaException {

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
			return DivisionQuerier.getAll();
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Division.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogicFacade#
	 * obtenerDivisionesPorNombre(java.lang.String)
	 */
	public List<DivisionBean> obtenerDivisionesPorNombre(String nombreDivision)
			throws LogicaException {

		String mensajeError = "";
		List<DivisionBean> listaDivisionBean = null;

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
			listaDivisionBean = (List<DivisionBean>) beanFactory
					.tranformarLista(
							DivisionQuerier.findByNombre(nombreDivision),
							DivisionBeanImpl.class);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Division.class.getName().toString();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaDivisionBean;

	}
}