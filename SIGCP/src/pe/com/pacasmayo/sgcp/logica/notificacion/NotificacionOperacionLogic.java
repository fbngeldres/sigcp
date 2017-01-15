package pe.com.pacasmayo.sgcp.logica.notificacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionOperacionLogic.java
 * Modificado: Aug 17, 2010 7:12:49 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.logica.facade.NotificacionOperacionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hora;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionoperacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs;
import pe.com.pacasmayo.sgcp.persistencia.querier.NotificacionOperacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class NotificacionOperacionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		ConstantesLogicaNegocio, NotificacionOperacionLogicFacade {

	private static BeanFactory beanFactory;

	private String mensajeError = "";

	public NotificacionOperacionLogic() {
		beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.NotificacionOperacionLogicFacade
	 * #insertarNotificacionOperacion(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date)
	 */
	public void insertarNotificacionOperacion(Long codigoRegistroReporteECS, Long codigoNotificacionDiaria, Long codigoHora,
			Long codigoPuestoTrabajo, Date fechaRegistro) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Notificacionoperacion notificacionoperacion = new Notificacionoperacion();

			// Notificacion Diaria
			Notificaciondiaria notificaciondiaria = new Notificaciondiaria();
			notificaciondiaria.setPkCodigoNotificaciondiaria(codigoNotificacionDiaria);

			notificacionoperacion.setNotificaciondiaria(notificaciondiaria);

			// RegistroReporte ECS
			Registroreporteecs registroreporteecs = new Registroreporteecs();
			registroreporteecs.setPkCodigoRegistroreporteecs(codigoRegistroReporteECS);

			notificacionoperacion.setRegistroreporteecs(registroreporteecs);

			// Puesto de Trabajo
			Puestotrabajo puestotrabajo = new Puestotrabajo();
			puestotrabajo.setPkCodigoPuestotrabajo(codigoPuestoTrabajo);

			notificacionoperacion.setPuestotrabajo(puestotrabajo);

			// Hora
			Hora hora = new Hora();
			hora.setPkCodigoHora(codigoHora);

			notificacionoperacion.setHora(hora);

			// Fecha
			notificacionoperacion.setFechaNotificacionoperacion(fechaRegistro);

			NotificacionOperacionQuerier.save(notificacionoperacion);

			tx.commit();

		} catch (ParametroInvalidoException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ELIMINADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}
}
