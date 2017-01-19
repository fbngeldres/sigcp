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
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
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
import pe.com.pacasmayo.sgcp.logica.facade.PuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadmedida;
import pe.com.pacasmayo.sgcp.persistencia.querier.EstadoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoPuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadMedidaQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class PuestoTrabajoLogic implements ConstantesFiltros, ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		PuestoTrabajoLogicFacade {

	final static String NOMBRE_PUESTO_TRABAJO = "nombrePuestotrabajo";

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public PuestoTrabajoLogic() {

		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorProceso(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorProceso(Long procesoCodigo) throws LogicaException {
		List<PuestoTrabajoBean> puestos = new ArrayList<PuestoTrabajoBean>();

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
			puestos = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findByProcess(procesoCodigo));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Sociedad.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return puestos;

	}

	/**
	 * Metodo para obtener los PuestoTrabajoDTO
	 * 
	 * @param procesoCodigo
	 * @return
	 * @throws LogicaException
	 */
	public List<Puestotrabajo> obtenerPuestosTrabajoDTOPorProceso(Long procesoCodigo) throws LogicaException {

		List<Puestotrabajo> puestos = new ArrayList<Puestotrabajo>();

		Session session = null;

		String mensajeError = "";

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
			puestos = PuestoTrabajoQuerier.obtenerPuestosTrabajoPorCodigoProceso(procesoCodigo);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
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
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorLineaNegocio(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorLineaNegoco(Long lineaCodigo) throws LogicaException {
		List<PuestoTrabajoBean> puestos = new ArrayList<PuestoTrabajoBean>();
		Session session = null;

		String mensajeError = "";

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
			puestos = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findByBussinesLine(lineaCodigo));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
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
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorLineaNegocio(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorUnidad(Long codigoUnidad) throws LogicaException {
		List<PuestoTrabajoBean> puestos = new ArrayList<PuestoTrabajoBean>();
		Session session = null;

		String mensajeError = "";

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
			puestos = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findByBussinesUnidad(codigoUnidad));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
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
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajo()
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajo() throws LogicaException {

		Session session = null;

		String mensajeError = "";

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
			return beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.getAllOrderBy(NOMBRE_PUESTO_TRABAJO));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<Puestotrabajo> obtenerPuestosTrabajoDataObjects() throws LogicaException {

		Session session = null;

		String mensajeError = "";

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
			return PuestoTrabajoQuerier.getAllOrderByNombre();
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<Puestotrabajo> obtenerPuestosTrabajoDataObjectsPorProducto(Long codigoProducto) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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
			return PuestoTrabajoQuerier.findByCodigoProductoOrderByNombre(codigoProducto);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestoTrabajo(java.lang.Long)
	 */
	public PuestoTrabajoBean obtenerPuestoTrabajo(Long codigoPuestoTrabajo) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		PuestoTrabajoBean puestoTrabajoBean = null;

		try {
			puestoTrabajoBean = beanFactory.transformarPuestoTrabajo(PuestoTrabajoQuerier.getById(codigoPuestoTrabajo));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoSCC(int)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoSCC(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoSCC(Long codigoSCC) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findByCodigoSCC(codigoSCC));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoSAP(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoSAP(java.lang.String)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoSAP(String codigoSAP) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findByCodigoSAP(codigoSAP));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorNombre(java.lang.String)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorNombre(String nombrePuestoTrabajo) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory
					.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findByNombre(nombrePuestoTrabajo));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorSiglas(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorSiglas(java.lang.String)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorSiglas(String siglas) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findBySiglas(siglas));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoTipoPuestoTrabajo(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoTipoPuestoTrabajo(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoTipoPuestoTrabajo(Long codigoTipoPuestoTrabajo)
			throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier
					.findByCodigoTipoPuestoTrabajo(codigoTipoPuestoTrabajo));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoCentroCostos(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorCodigoCentroCostos(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoCentroCostos(Long codigoCentroCostos) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier
					.findByCodigoCentroCostos(codigoCentroCostos));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorCodigoUnidadMedida(Long codigoUnidadMedida) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier
					.findByCodigoUnidadMedida(codigoUnidadMedida));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	public Puestotrabajo obtenerPuestoTrabajoDataObject(Long codigoPuestoTrabajo) throws LogicaException {
		Session session = null;

		String mensajeError = "";

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

		Puestotrabajo puestoTrabajo = null;

		try {
			puestoTrabajo = PuestoTrabajoQuerier.getById(codigoPuestoTrabajo);
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajo;
	}

	public PuestoTrabajoDTO obtenerPuestoTrabajoDTO(Long codigoPuestoTrabajo) throws LogicaException {
		Puestotrabajo puestoTrabajo = obtenerPuestoTrabajoDataObject(codigoPuestoTrabajo);

		PuestoTrabajoDTO puestoTrabajoDTO = new PuestoTrabajoDTO();
		puestoTrabajoDTO.setPkCodigoPuestotrabajo(puestoTrabajo.getPkCodigoPuestotrabajo());
		puestoTrabajoDTO.setCodigoSapPuestotrabajo(puestoTrabajo.getCodigoSapPuestotrabajo());
		puestoTrabajoDTO.setCodSccPuestotrabajo(puestoTrabajo.getCodSccPuestotrabajo());
		puestoTrabajoDTO.setDescripcionPuestotrabajo(puestoTrabajo.getDescripcionPuestotrabajo());
		puestoTrabajoDTO.setNombrePuestotrabajo(puestoTrabajo.getNombrePuestotrabajo());
		puestoTrabajoDTO.setSiglasPuestotrabajo(puestoTrabajo.getSiglasPuestotrabajo());

		return puestoTrabajoDTO;
	}

	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorTableroControl(Long codigoTableroControl) throws LogicaException {
		List<PuestoTrabajoBean> puestos = new ArrayList<PuestoTrabajoBean>();

		Session session = null;

		String mensajeError = "";

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
			puestos = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier.findByTableroControl(codigoTableroControl));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestos;
	}

	public List<Puestotrabajo> obtenerPuestosTrabajoDTOPorTableroControl(Long codigoTableroControl) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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
			return PuestoTrabajoQuerier.findByTableroControl(codigoTableroControl);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorEstado(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerPuestosTrabajoPorEstado(java.lang.Long)
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorEstado(Long codigoEstadoPuestoTrabajo) throws LogicaException {

		Session session = null;

		String mensajeError = "";

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

		List<PuestoTrabajoBean> puestoTrabajoBeans = null;

		try {
			puestoTrabajoBeans = beanFactory.transformarListaPuestoTrabajo(PuestoTrabajoQuerier
					.findByCodigoEstadoPuestoTrabajo(codigoEstadoPuestoTrabajo));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Puestotrabajo.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return puestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * obtenerAtributos()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
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
		filtro.setValor(CODIGO_SAP);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(4);
		filtro.setValor(NOMBRE);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(5);
		filtro.setValor(SIGLAS);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(6);
		filtro.setValor(TIPO_PUESTO_TRABAJO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(7);
		filtro.setValor(ESTADO);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * insertarPuestoTrabajo(pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * insertarPuestoTrabajo(pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public void insertarPuestoTrabajo(PuestoTrabajoBean puestoTrabajoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Puestotrabajo puestoTrabajo = new Puestotrabajo();

			puestoTrabajo.setCodigoSapPuestotrabajo(puestoTrabajoBean.getCodigoSAP());
			puestoTrabajo.setCodSccPuestotrabajo(puestoTrabajoBean.getCodigoSCC());
			puestoTrabajo.setDescripcionPuestotrabajo(puestoTrabajoBean.getDescripcion());
			puestoTrabajo.setNombrePuestotrabajo(puestoTrabajoBean.getNombre());
			puestoTrabajo.setSiglasPuestotrabajo(puestoTrabajoBean.getSiglas());

			Estadopuestotrabajo estadoPuestoTrabajo = new Estadopuestotrabajo();
			estadoPuestoTrabajo.setPkCodigoEstadopuestotrabajo(puestoTrabajoBean.getEstadoPuestoTrabajo().getCodigo());

			puestoTrabajo.setEstadopuestotrabajo(estadoPuestoTrabajo);

			Tipopuestotrabajo tipoPuestoTrabajo = new Tipopuestotrabajo();
			tipoPuestoTrabajo.setPkCodigoTipopuestotrabajo(puestoTrabajoBean.getTipoPuestoTrabajo().getCodigo());

			puestoTrabajo.setTipopuestotrabajo(tipoPuestoTrabajo);

			Unidadmedida unidadMedida = new Unidadmedida();
			unidadMedida.setPkCodigoUnidadMedida(puestoTrabajoBean.getUnidadMedida().getCodigo());

			puestoTrabajo.setUnidadmedida(unidadMedida);

			PuestoTrabajoQuerier.save(puestoTrabajo);

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
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * actualizarPuestoTrabajo(pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * actualizarPuestoTrabajo(pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public void actualizarPuestoTrabajo(PuestoTrabajoBean puestoTrabajoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Puestotrabajo puestoTrabajo = PuestoTrabajoQuerier.getById(puestoTrabajoBean.getCodigo());

			puestoTrabajo.setCodigoSapPuestotrabajo(puestoTrabajoBean.getCodigoSAP());
			puestoTrabajo.setCodSccPuestotrabajo(puestoTrabajoBean.getCodigoSCC());
			puestoTrabajo.setDescripcionPuestotrabajo(puestoTrabajoBean.getDescripcion());
			puestoTrabajo.setNombrePuestotrabajo(puestoTrabajoBean.getNombre());
			puestoTrabajo.setSiglasPuestotrabajo(puestoTrabajoBean.getSiglas());

			Estadopuestotrabajo estadoPuestoTrabajo = EstadoPuestoTrabajoQuerier.getById(puestoTrabajoBean
					.getEstadoPuestoTrabajo().getCodigo());

			puestoTrabajo.setEstadopuestotrabajo(estadoPuestoTrabajo);

			Tipopuestotrabajo tipoPuestoTrabajo = TipoPuestoTrabajoQuerier.getById(puestoTrabajoBean.getTipoPuestoTrabajo()
					.getCodigo());

			puestoTrabajo.setTipopuestotrabajo(tipoPuestoTrabajo);

			Unidadmedida unidadMedida = UnidadMedidaQuerier.getById(puestoTrabajoBean.getUnidadMedida().getCodigo());

			puestoTrabajo.setUnidadmedida(unidadMedida);

			PuestoTrabajoQuerier.update(puestoTrabajo);

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
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * eliminarPuestoTrabajo(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogicFacade#
	 * eliminarPuestoTrabajo(pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public void eliminarPuestoTrabajo(PuestoTrabajoBean puestoTrabajoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Puestotrabajo puestoTrabajo = PuestoTrabajoQuerier.getById(puestoTrabajoBean.getCodigo());

			PuestoTrabajoQuerier.delete(puestoTrabajo);

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

}
