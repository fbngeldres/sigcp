package pe.com.pacasmayo.sgcp.logica.notificacion;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: EstadoNotificacionLogic.java
 * Modificado: Jun 22, 2010 5:04:54 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.Session;

import pe.com.pacasmayo.sgcp.bean.EstadoNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoNotificacionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadonotificacion;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoNotificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class EstadoNotificacionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		ConstantesLogicaNegocio, EstadoNotificacionLogicFacade {

	private static BeanFactory beanFactory;

	private String mensajeError = "";

	public EstadoNotificacionLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.produccion.EstadoProduccionSemanalLogicFacade
	 * #obtenerEstadosProduccionSemanal()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.EstadoNotificacionLogicFacade
	 * #obtenerEstadoNotificacion()
	 */
	public List<EstadoNotificacionBean> obtenerEstadoNotificacion() throws LogicaException {

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			return beanFactory.transformarListaEstadoNotificacion(EstadoNotificacionQuerier.getAll());
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadonotificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<Estadonotificacion> obtenerEstadoNotificacionDataObjects() throws LogicaException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			return EstadoNotificacionQuerier.getAll();
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadonotificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.EstadoNotificacionLogicFacade
	 * #obtenerEstadoNotificacion(java.lang.Long)
	 */
	public EstadoNotificacionBean obtenerEstadoNotificacion(Long codigo) throws LogicaException {

		EstadoNotificacionBean estadoNotificacionBean = null;

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			estadoNotificacionBean = beanFactory.transformarEstadoNotificacion(EstadoNotificacionQuerier.getById(codigo));
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return estadoNotificacionBean;
	}

	public Estadonotificacion obtenerEstadoNotificacionPorNombreDO(String nombre) throws LogicaException {

		Estadonotificacion estadonotificacion = null;

		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			estadonotificacion = EstadoNotificacionQuerier.findByNombreUniqueResult(nombre);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return estadonotificacion;
	}

	public List<EstadoNotificacionBean> obtenerEstadoNotificacionGestionar() throws LogicaException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			List<Estadonotificacion> lista = EstadoNotificacionQuerier.getEstadosGestionar();
			for (Estadonotificacion notificacionDiariaBean : lista) {
				if (notificacionDiariaBean.getNombreEstadonotificacion().equals(EstadoNotificacionQuerier.ESTADO_APROBADO)) {
					notificacionDiariaBean.setNombreEstadonotificacion(EstadoNotificacionQuerier.ESTADO_ABIERTO);
				}
			}
			return beanFactory.transformarListaEstadoNotificacion(lista);
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Estadonotificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

}