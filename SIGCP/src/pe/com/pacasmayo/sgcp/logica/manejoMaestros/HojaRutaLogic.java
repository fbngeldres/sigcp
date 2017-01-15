package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: HojaRutaLogic.java
 * Modificado: Nov 27, 2009 3:40:38 PM
 * Autor: evelyn.santamaria
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.OperacionComponenteBean;
import pe.com.pacasmayo.sgcp.bean.OperacionRecursoBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
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
import pe.com.pacasmayo.sgcp.logica.facade.FactorDosificacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.HojaRutaLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Actividad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadohojaruta;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojaruta;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojarutacomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacioncomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacionrecurso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Recurso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadmedida;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.querier.ActividadQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorDosificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorDosificacionRegistroMensualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OperacionComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OperacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.OperacionRecursoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProcesoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.RecursoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoComponenteQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class HojaRutaLogic implements ConstantesFiltros, ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		HojaRutaLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public HojaRutaLogic() {

		HojaRutaLogic.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRuta(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRuta(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRuta(java.lang.Long)
	 */
	public HojaRutaBean obtenerHojaRuta(Long codigoHojaRuta) throws LogicaException {

		HojaRutaBean hojaRutaBean = null;
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
			hojaRutaBean = beanFactory.transformarHojaRuta(HojaRutaQuerier.getById(codigoHojaRuta));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return hojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoEstado(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoEstado(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoEstado(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojaRutaPorCodigoEstado(Long codigoEstado) throws LogicaException {

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

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

		try {
			listaHojaRutaBean = beanFactory
					.transformarListaHojaRutaParaConsulta(HojaRutaQuerier.findByCodigoEstado(codigoEstado));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoSCC(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoSCC(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoSCC(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojaRutaPorCodigoSCC(Long codigoSCC) throws LogicaException {

		String mensajeError = "";

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

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
			listaHojaRutaBean = beanFactory.transformarListaHojaRutaParaConsulta(HojaRutaQuerier.findByCodigoSCC(codigoSCC));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null) {
				PersistenciaFactory.closeSession(session);
			}
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorNombre(java.lang.String)
	 */
	public List<HojaRutaBean> obtenerHojaRutaPorNombre(String nombreHojaRuta) throws LogicaException {

		String mensajeError = "";

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

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
			listaHojaRutaBean = beanFactory.transformarListaHojaRutaParaConsulta(HojaRutaQuerier.findByNombre(nombreHojaRuta));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoProceso(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoProceso(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoProceso(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojaRutaPorCodigoProceso(Long codigoProceso) throws LogicaException {

		String mensajeError = "";

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

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

			listaHojaRutaBean = beanFactory.transformarListaHojaRutaParaConsulta(HojaRutaQuerier
					.obtenerHojasRutaPorCodigoProceso(codigoProceso));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoUnidad(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojaRutaPorCodigoUnidad(Long codigoUnidad) throws LogicaException {

		String mensajeError = "";

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

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
			listaHojaRutaBean = beanFactory.transformarListaHojaRutaParaConsulta(HojaRutaQuerier
					.obtenerHojasRutaPorCodigoUnidad(codigoUnidad));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorCodigoLineaNegocio(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojaRutaPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

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
			listaHojaRutaBean = beanFactory.transformarListaHojaRutaParaConsulta(HojaRutaQuerier
					.obtenerHojasRutaPorCodigoLineaNegocio(codigoLineaNegocio));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaSinFactorDosificacion()
	 */
	public List<HojaRutaBean> obtenerHojaRutaSinFactorDosificacion() throws LogicaException {

		String mensajeError = "";

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

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
			listaHojaRutaBean = beanFactory.transformarListaHojaRuta(HojaRutaQuerier.getHojaRutaSinFactorDosificacion());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaConFactorDosificacion()
	 */
	public List<HojaRutaBean> obtenerHojaRutaConFactorDosificacion() throws LogicaException {

		String mensajeError = "";

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

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
			listaHojaRutaBean = beanFactory.transformarListaHojaRutaConFactoresDosificacion(HojaRutaQuerier
					.getHojaRutaConFactorDosificacion());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRuta()
	 */
	public List<HojaRutaBean> obtenerHojasRuta() throws LogicaException {

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

			List<Hojaruta> hojas = HojaRutaQuerier.getAll();

			List<HojaRutaBean> hojasB = beanFactory.transformarListaHojaRuta(hojas);

			return hojasB;
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRutaParaConsulta()
	 */
	public List<HojaRutaBean> obtenerHojasRutaParaConsulta() throws LogicaException {

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
			return beanFactory.transformarListaHojaRutaParaConsulta(HojaRutaQuerier.getAll());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojaRutaPorAnno(java.lang.Long, java.lang.Integer)
	 */
	public HojaRutaBean obtenerHojaRutaPorAnno(Long codigoHojaRuta, Integer anno) throws LogicaException {

		HojaRutaBean hojaRutaBean = obtenerHojaRuta(codigoHojaRuta);

		return filtrarHojaRutaComponentesPorAnno(hojaRutaBean, anno);
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerAnnosHojaRuta(pe.com.pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	public List<UtilBean> obtenerAnnosHojaRuta(HojaRutaBean hojaRutaBean) {

		List<UtilBean> listaAnnos = new ArrayList<UtilBean>();

		Set<Integer> setAnnos = extraerAnnos(hojaRutaBean);

		for (Integer anno : setAnnos) {

			UtilBean utilBean = new UtilBeanImpl();

			utilBean.setCodigo(anno);
			utilBean.setValor(anno.toString());

			listaAnnos.add(utilBean);
		}

		return listaAnnos;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRutaPorProceso(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojasRutaPorProceso(Long codigoProceso) throws LogicaException {

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
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaPorCodigoProceso(codigoProceso));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRutaPorProduccion(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojasRutaPorProduccion(Long codigoProduccion) throws LogicaException {

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
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaPorProduccion(codigoProduccion));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRutaNoInactivasPorNombre(java.lang.String)
	 */
	public List<HojaRutaBean> obtenerHojasRutaNoInactivasPorNombre(String nombreHojaRuta) throws LogicaException {

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
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaNoInactivasPorNombre(nombreHojaRuta));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRutaNoInactivasPorNombreYProduccion(java.lang.String,
	 * java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojasRutaNoInactivasPorNombreYProduccion(String nombreHojaRuta, Long codigoProduccion)
			throws LogicaException {

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
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaNoInactivasPorNombreYProduccion(
					nombreHojaRuta, codigoProduccion));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRutaNoInactivasPorProducto(java.lang.Long)
	 */
	public List<HojaRutaBean> obtenerHojasRutaNoInactivasPorProducto(Long codigoProducto) throws LogicaException {

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
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaNoInactivasPorProducto(codigoProducto));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerHojasRutaConFactorDosificacion()
	 */
	public List<HojaRutaBean> obtenerHojasRutaConFactorDosificacion() throws LogicaException {

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
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaConFactoresDosificacion());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerAtributos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * obtenerAtributos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
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
		filtro.setValor(NOMBRE);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(3);
		filtro.setValor(ESTADO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(4);
		filtro.setValor(PROCESO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(5);
		filtro.setValor(UNIDAD);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(6);
		filtro.setValor(LINEA_NEGOCIO);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * insertarHojaRuta(pe.com.pacasmayo.sgcp.bean.HojaRutaBean,
	 * pe.com.pacasmayo.sgcp.bean.ProduccionBean, java.util.List,
	 * java.util.List)
	 */
	public void insertarHojaRuta(HojaRutaBean hojaRutaBean, ProduccionBean produccionBean,
			List<String> listaCodigoProductoComponentes, List<OperacionBean> operaciones, Long mes, Long anio)
			throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Transaction tx2 = null;
		Session session = null;
		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Hojaruta hojaRuta = new Hojaruta();
			hojaRuta.setNombreHojaruta(hojaRutaBean.getNombre());

			Estadohojaruta estadoHojaRuta = new Estadohojaruta();
			estadoHojaRuta.setPkCodigoEstadohojaruta(hojaRutaBean.getEstadoHojaRuta().getCodigo());

			hojaRuta.setEstadohojaruta(estadoHojaRuta);

			Proceso proceso = ProcesoQuerier.getById(produccionBean.getProceso().getCodigo());
			Producto producto = ProductoQuerier.getById(produccionBean.getProducto().getCodigo());

			Produccion produccion = ProduccionQuerier.getByProductoProceso(producto.getPkCodigoProducto(),
					proceso.getPkCodigoProceso());
			if (produccion == null) {
				produccion = new Produccion();

				produccion.setProceso(proceso);
				produccion.setProducto(producto);
				ProduccionQuerier.save(produccion);
			}

			// Se setea la producciï¿½n a la hoja de ruta
			hojaRuta.setProduccion(produccion);

			// se almacena la hoja de ruta
			HojaRutaQuerier.save(hojaRuta);

			// se almacenan los componente de la hoja de ruta y los componentes
			Set<Componente> componentes = new HashSet<Componente>();
			if (listaCodigoProductoComponentes != null && listaCodigoProductoComponentes.size() > 0) {
				componentes = almacenaHojaRutaComponente(listaCodigoProductoComponentes, hojaRuta, producto);
			}

			// se almacenan las operaciones
			if (operaciones != null && operaciones.size() > 0)
				almacenarOperaciones(operaciones, hojaRuta, componentes);

			almacenaFactorDosificacion(hojaRutaBean, hojaRuta);

			hojaRuta.setHojarutacomponentes(new TreeSet());

			tx.commit();

			// Proceso de Cambio de Hoja de Ruta--------- John Borrego Marca
			try {
				tx2 = session.beginTransaction();

				HojaRutaQuerier.CambiarHojaRuta(proceso.getPkCodigoProceso(), producto.getPkCodigoProducto(),
						produccion.getPkProduccion(), mes, anio);
				tx2.commit();
			} catch (Exception e) {
				tx2.rollback();
				mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MENSAJE_ERROR_FUNCION_HOJA_RUTA);
				logger.error(e.getMessage());
				throw new LogicaException(mensajeError, e);
			}
			// --------- John Borrego Marca

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
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_IGUAL);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (AplicacionException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FALLA_CONSULTA_LISTA);

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
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * actualizarHojaRuta(pe.com.pacasmayo.sgcp.bean.HojaRutaBean,
	 * java.util.List, java.util.List)
	 */
	public void actualizarHojaRuta(HojaRutaBean hojaRutaBean, List<String> listaCodigoProductoComponentes,
			List<OperacionBean> operaciones) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Hojaruta hojaRuta = HojaRutaQuerier.getById(hojaRutaBean.getCodigo());

			hojaRuta.setNombreHojaruta(hojaRutaBean.getNombre());

			Estadohojaruta estadoHojaRuta = new Estadohojaruta();
			estadoHojaRuta.setPkCodigoEstadohojaruta(hojaRutaBean.getEstadoHojaRuta().getCodigo());

			hojaRuta.setEstadohojaruta(estadoHojaRuta);

			Long codigoEstadoHRIncial = hojaRuta.getEstadohojaruta().getPkCodigoEstadohojaruta();

			// se almacena la hoja de ruta
			HojaRutaQuerier.update(hojaRuta);

			if (codigoEstadoHRIncial == Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_HOJA_RUTA_GENERADA))) {
				// Se eliminan los componentes de la hoja de ruta
				if (hojaRuta.getHojarutacomponentes() != null && hojaRuta.getHojarutacomponentes().size() > 0
						&& hojaRuta.getOperacions() != null && hojaRuta.getOperacions().size() > 0) {
					OperacionComponenteQuerier.deleteByCodigoHojaRuta(hojaRuta.getPkCodigoHojaruta());
					HojaRutaComponenteQuerier.deleteByCodigoHojaRuta(hojaRuta.getPkCodigoHojaruta());
					OperacionQuerier.deleteByCodigoHojaRuta(hojaRuta.getPkCodigoHojaruta());
				}
				// se almacenan los componente de la hoja de ruta y los
				// componentes
				Set<Componente> componentes = new HashSet<Componente>();
				if (listaCodigoProductoComponentes != null && listaCodigoProductoComponentes.size() > 0) {
					componentes = almacenaHojaRutaComponente(listaCodigoProductoComponentes, hojaRuta, hojaRuta.getProduccion()
							.getProducto());
				}

				// se almacenan las operaciones
				if (operaciones != null && operaciones.size() > 0)
					almacenarOperaciones(operaciones, hojaRuta, componentes);

				// almacenaFactorDosificacion(hojaRutaBean, hojaRuta);

				hojaRuta.setHojarutacomponentes(new TreeSet());
			}
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
		} catch (ConstraintViolationException e) {
			logger.error(e.getMessage());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MENSAJE_HOJA_RUTA_RELACIONADA_MODIFICAR);

			throw new LogicaException(mensajeError, e);

		} catch (HibernateException e) {
			logger.error(e.getMessage());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * eliminarHojaRuta(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * eliminarHojaRuta(pe.com.pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.HojaRutaLogicFacade#
	 * eliminarHojaRuta(pe.com.pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	public void eliminarHojaRuta(HojaRutaBean hojaRutaBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			FactorDosificacionRegistroMensualQuerier.deleteByCodigoHojaRuta(hojaRutaBean.getCodigo());

			FactorDosificacionQuerier.deleteByCodigoHojaRuta(hojaRutaBean.getCodigo());

			OperacionComponenteQuerier.deleteByCodigoHojaRuta(hojaRutaBean.getCodigo());

			OperacionRecursoQuerier.deleteByCodigoHojaRuta(hojaRutaBean.getCodigo());

			HojaRutaComponenteQuerier.deleteByCodigoHojaRuta(hojaRutaBean.getCodigo());

			OperacionQuerier.deleteByCodigoHojaRuta(hojaRutaBean.getCodigo());

			Hojaruta hojaRuta = HojaRutaQuerier.getById(hojaRutaBean.getCodigo());

			HojaRutaQuerier.delete(hojaRuta);

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
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MENSAJE_HOJA_RUTA_RELACIONADA_ELIMINAR);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Mï¿½todo para almacenar las operaciones en la hoja de ruta
	 * 
	 * @param operaciones
	 * @param hojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	private void actualizaOperacionesComponentesYRecursos(List<OperacionBean> operaciones, Hojaruta hojaRuta,
			List<String> listaCodigoProductoComponentes) throws ElementoNoEncontradoException, ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException {

		for (Iterator<OperacionBean> iterator = operaciones.iterator(); iterator.hasNext();) {

			OperacionBean operacionBean = (OperacionBean) iterator.next();

			// se actualizan los factores de dosificacion de los componentes
			for (int i = 0; i < operacionBean.getListaOperacionComponentes().size(); i++) {
				OperacionComponenteBean operacionComponenteBean = operacionBean.getListaOperacionComponentes().get(i);

				Operacioncomponente operacionComponente = new Operacioncomponente();
				if (operacionComponenteBean.getCodigo() == null) {
					Hojarutacomponente hojaRutaComponente = new Hojarutacomponente();

					Componente componente = new Componente();

					// se setea el producto
					componente.setProductoByFkCodigoProducto(hojaRuta.getProduccion().getProducto());

					Producto productoComponente = new Producto();
					productoComponente.setPkCodigoProducto(Long.parseLong(listaCodigoProductoComponentes.get(i)));

					// se seta el producto componente (material)
					componente.setProductoByFkCodigoProductoComponente(productoComponente);

					// se almacena el componente
					ComponenteQuerier.save(componente);

					hojaRutaComponente.setComponente(componente);
					hojaRutaComponente.setHojaruta(hojaRuta);

					// se busca el tipo de componente

					Tipocomponente tipoComponente = TipoComponenteQuerier.getById((long) 1);

					hojaRutaComponente.setTipocomponente(tipoComponente);

					HojaRutaComponenteQuerier.save(hojaRutaComponente);

				}

				operacionComponente = OperacionComponenteQuerier.getById(operacionComponenteBean.getCodigo());

				if (operacionComponente == null) {
					operacionComponente = new Operacioncomponente();

				}

				operacionComponente.setMaxFactorOperacioncomponente(operacionComponenteBean.getMaxFactor());
				operacionComponente.setMinFactorOperacioncomponente(operacionComponenteBean.getMinFactor());

				OperacionComponenteQuerier.saveOrUpdate(operacionComponente);
			}

			// se almacenan los recursos
			if (operacionBean.getListaOperacionRecursos() != null && operacionBean.getListaOperacionRecursos().size() > 0) {
				for (int i = 0; i < operacionBean.getListaOperacionRecursos().size(); i++) {

					OperacionRecursoBean operacionRecursoBean = operacionBean.getListaOperacionRecursos().get(i);

					Operacionrecurso operacionRecurso = new Operacionrecurso();

					if (operacionRecursoBean.getCodigo() != null) {
						operacionRecurso = OperacionRecursoQuerier.getById(operacionRecursoBean.getCodigo());
					} else {
						Operacion operacion = OperacionQuerier.getById(operacionBean.getCodigo());
						operacionRecurso.setOperacion(operacion);
					}

					Recurso recurso = RecursoQuerier.getById(operacionBean.getListaOperacionRecursos().get(i).getRecurso()
							.getCodigo());

					operacionRecurso.setRecurso(recurso);
					OperacionRecursoQuerier.saveOrUpdate(operacionRecurso);
				}
			}
		}
	}

	/**
	 * Mï¿½todo para almacenar las operaciones en la hoja de ruta
	 * 
	 * @param operaciones
	 * @param hojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	private void almacenarOperaciones(List<OperacionBean> operaciones, Hojaruta hojaRuta, Set<Componente> componentes)
			throws ElementoNoEncontradoException, ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException {

		for (Iterator<OperacionBean> iterator = operaciones.iterator(); iterator.hasNext();) {

			OperacionBean operacionBean = (OperacionBean) iterator.next();

			Actividad actividad = ActividadQuerier.getById(operacionBean.getActividad().getCodigo());
			Puestotrabajo puestoTrabajo = PuestoTrabajoQuerier.getById(operacionBean.getPuestoTrabajo().getCodigo());

			Operacion operacion = new Operacion();

			operacion.setActividad(actividad);
			operacion.setHojaruta(hojaRuta);
			operacion.setNombreOperacion(operacionBean.getNombre());

			// se almancena el puesto de trabajo
			operacion.setPuestotrabajo(puestoTrabajo);

			// TODO : eliminar esta propiedad, porque en el modelo actual ya no
			// exite este campo
			operacion.setOrdenEjecucionOperacion(operacionBean.getOrdenEjecucion());

			OperacionQuerier.save(operacion);

			// se almacenan los componentes que corresponden a las operaciones
			almacenaOperacionComponentes(operacionBean, operacion, hojaRuta);

			// se almacenan los recursos
			if (operacionBean.getListaOperacionRecursos() != null && operacionBean.getListaOperacionRecursos().size() > 0) {
				for (int i = 0; i < operacionBean.getListaOperacionRecursos().size(); i++) {
					Operacionrecurso operacionRecurso = new Operacionrecurso();
					operacionRecurso.setOperacion(operacion);
					Recurso recurso = RecursoQuerier.getById(operacionBean.getListaOperacionRecursos().get(i).getRecurso()
							.getCodigo());

					operacionRecurso.setRecurso(recurso);

					OperacionRecursoQuerier.save(operacionRecurso);
				}
			}
		}
	}

	/**
	 * Mï¿½todo para almacenar los componentes del producto de la hoja de ruta
	 * 
	 * @param listaCodigoProductoComponentes
	 * @param hojaRuta
	 * @param producto
	 * @return
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 * @throws ConstrainViolationException
	 */
	private Set<Componente> almacenaHojaRutaComponente(List<String> listaCodigoProductoComponentes, Hojaruta hojaRuta,
			Producto producto) throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {

		Set<Componente> componentes = new HashSet<Componente>();
		Set<Hojarutacomponente> hojaRutaComponentesDO = new HashSet<Hojarutacomponente>();

		if (listaCodigoProductoComponentes != null && listaCodigoProductoComponentes.size() > 0) {

			// se eliminan las hojas de ruta componentes asociadas anteriormente

			for (int i = 0; i < listaCodigoProductoComponentes.size(); i++) {

				Hojarutacomponente hojaRutaComponente = new Hojarutacomponente();

				Componente componente = new Componente();

				// se setea el producto
				componente.setProductoByFkCodigoProducto(producto);

				Producto productoComponente = new Producto();
				productoComponente.setPkCodigoProducto(Long.parseLong(listaCodigoProductoComponentes.get(i)));

				// se seta el producto componente (material)
				componente.setProductoByFkCodigoProductoComponente(productoComponente);

				// se almacena el componente
				ComponenteQuerier.save(componente);

				hojaRutaComponente.setComponente(componente);
				hojaRutaComponente.setHojaruta(hojaRuta);

				// se busca el tipo de componente

				Tipocomponente tipoComponente = TipoComponenteQuerier.getById((long) 1);

				hojaRutaComponente.setTipocomponente(tipoComponente);

				HojaRutaComponenteQuerier.save(hojaRutaComponente);

				hojaRutaComponentesDO.add(hojaRutaComponente);
			}
			if (hojaRuta.getHojarutacomponentes() == null)
				hojaRuta.setHojarutacomponentes(new HashSet<Hojarutacomponente>());
			hojaRuta.setHojarutacomponentes(hojaRutaComponentesDO);
		}
		return componentes;
	}

	/**
	 * Mï¿½todo para almacenar la producciï¿½n en la hoja de ruta
	 * 
	 * @param produccionBean
	 * @param producto
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws LogicaException
	 * @throws ConstrainViolationException
	 */
	private Produccion almacenaProduccion(ProduccionBean produccionBean, Producto producto) throws ElementoNoEncontradoException,
			ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException, LogicaException {

		Produccion produccion = null;
		String mensajeError = "";

		try {
			produccion = ProduccionQuerier.getByProductoProceso(produccionBean.getProducto().getCodigo(), produccionBean
					.getProceso().getCodigo());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}

		if (produccion == null) {
			produccion = new Produccion();
			Proceso proceso = ProcesoQuerier.getById(produccionBean.getProceso().getCodigo());

			produccion.setProducto(producto);
			produccion.setProceso(proceso);

			ProduccionQuerier.save(produccion);
		}

		return produccion;
	}

	/**
	 * Mï¿½todo para almacenar los componentes de las operaciones
	 * correpondientes a la hoja de ruta
	 * 
	 * @throws ConstrainViolationException
	 */
	private void almacenaOperacionComponentes(OperacionBean operacionBean, Operacion operacion, Hojaruta hojaRuta)
			throws ElementoNoEncontradoException, ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException {

		for (Iterator<OperacionComponenteBean> iterator = operacionBean.getListaOperacionComponentes().iterator(); iterator
				.hasNext();) {

			OperacionComponenteBean operacionComponenteBean = (OperacionComponenteBean) iterator.next();

			Operacioncomponente operacionComponente = new Operacioncomponente();

			operacionComponente.setHojarutacomponente(obtenerHojaRutaComponente(hojaRuta.getHojarutacomponentes(),
					operacionComponenteBean.getHojaRutaComponente().getComponente().getProductoComponente().getCodigo()));

			operacionComponente.setOperacion(operacion);
			operacionComponente.setMaxFactorOperacioncomponente(operacionComponenteBean.getMaxFactor());
			operacionComponente.setMinFactorOperacioncomponente(operacionComponenteBean.getMinFactor());
			OperacionComponenteQuerier.save(operacionComponente);
		}
	}

	/**
	 * Este mï¿½todo busca la hoja de ruta componente para una operaciï¿½n
	 * componente
	 * 
	 * @param hojaRutaComponentes
	 * @param codigoProductoComponente
	 * @return
	 */
	private Hojarutacomponente obtenerHojaRutaComponente(Set<Hojarutacomponente> hojaRutaComponentes,
			Long codigoProductoComponente) {

		for (Iterator<Hojarutacomponente> iterator = hojaRutaComponentes.iterator(); iterator.hasNext();) {

			Hojarutacomponente hojaRutaComponente = (Hojarutacomponente) iterator.next();

			if (hojaRutaComponente.getComponente().getProductoByFkCodigoProductoComponente().getPkCodigoProducto()
					.equals(codigoProductoComponente)) {
				return hojaRutaComponente;
			}
		}

		return null;
	}

	/**
	 * Este mï¿½todo almacena en el objeto DO los factores de dosificacion de la
	 * hoja de ruta componente
	 * 
	 * @param hojaRutaBean
	 * @param hojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 */
	private void almacenaFactorDosificacion(HojaRutaBean hojaRutaBean, Hojaruta hojaRuta) throws ElementoNoEncontradoException,
			ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException {

		int indice = 0;

		for (Iterator<Hojarutacomponente> iterator = hojaRuta.getHojarutacomponentes().iterator(); iterator.hasNext();) {

			Hojarutacomponente hojaRutaComponente = (Hojarutacomponente) iterator.next();

			Factordosificacion factorDosificacion = new Factordosificacion();
			factorDosificacion.setHojaruta(hojaRuta);

			// se busca el componente
			Componente componente = hojaRutaComponente.getComponente();

			factorDosificacion.setComponente(componente);

			Unidadmedida unidadMedida = hojaRuta.getProduccion().getProducto().getUnidadmedida();

			factorDosificacion.setUnidadmedida(unidadMedida);

			FactorDosificacionQuerier.save(factorDosificacion);

			indice++;
		}
	}

	/**
	 * Metodo que modifica la lista de factores de dosificacion para los
	 * componentes una hoja de ruta, dejando solo los pertenecientes al anno
	 * especificado.
	 * 
	 * @param hojaRutaBean
	 * @param anno
	 * @return
	 */
	private HojaRutaBean filtrarHojaRutaComponentesPorAnno(HojaRutaBean hojaRutaBean, Integer anno) {

		List<HojaRutaComponenteBean> hojaRutaComponentes = hojaRutaBean.getHojaRutaComponentes();
		FactorDosificacionLogicFacade factorDosificacionLogicFacade = new FactorDosificacionLogic();

		for (HojaRutaComponenteBean hojaRutaComponenteBean : hojaRutaComponentes) {

			ComponenteBean componenteBean = hojaRutaComponenteBean.getComponente();

			List<FactorDosificacionBean> factoresDosificacion = factorDosificacionLogicFacade.filtrarFactorDosificacion(
					componenteBean.getFactorDosificacion(), anno);

			componenteBean.setFactorDosificacion(factoresDosificacion);

			hojaRutaBean.setComponente(componenteBean);
		}

		hojaRutaBean.setHojaRutaComponentes(hojaRutaComponentes);

		return hojaRutaBean;
	}

	/**
	 * Metodo para obtener un set de los annos de una hoja de ruta
	 * 
	 * @param hojaRutaBean
	 * @return
	 */
	private Set<Integer> extraerAnnos(HojaRutaBean hojaRutaBean) {

		Set<Integer> setAnnos = new TreeSet<Integer>();
		List<HojaRutaComponenteBean> listaHojaRutaComponente = hojaRutaBean.getHojaRutaComponentes();

		for (HojaRutaComponenteBean hojaRutaComponenteBean : listaHojaRutaComponente) {

			List<FactorDosificacionBean> listaFactoresDosificacion = hojaRutaComponenteBean.getComponente()
					.getFactorDosificacion();

			for (FactorDosificacionBean factorDosificacionBean : listaFactoresDosificacion) {

				FactorDosificacionRegistroMensualBean[] factorMensual = factorDosificacionBean
						.getFactorDosificacionRegistroMensual();

				for (int i = 0; i < factorMensual.length; i++) {

					FactorDosificacionRegistroMensualBean factorDosificacionRegistroMensualBean = factorMensual[i];

					if (factorDosificacionRegistroMensualBean == null || factorDosificacionRegistroMensualBean.getAnno() == null) {
						continue;
					}

					setAnnos.add(factorDosificacionRegistroMensualBean.getAnno());

					break;
				}
			}
		}

		return setAnnos;
	}

	/**
	 * Obtiene Hojas de Ruta Activas por el codifo de Producto
	 * 
	 * @param codigoProducto
	 * @return List<HojaRutaBean>
	 * @throws LogicaException
	 */
	public List<HojaRutaBean> obtenerHojasRutaActivaPorProducto(Long codigoProducto) throws LogicaException {

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
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaActivaPorProducto(codigoProducto));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Obtiene Hojas de Ruta Activas por el codifo de Producto y Linea negocio
	 * 
	 * @param codigoProducto
	 * @return List<HojaRutaBean>
	 * @throws LogicaException
	 */
	public List<HojaRutaBean> obtenerHojaRutaActivaPorProcesoProductoDAO(Long codigoProceso, Long codigoProducto)
			throws LogicaException {

		String mensajeError = "";

		

		try {
			return beanFactory.transformarListaHojaRuta(HojaRutaQuerier.obtenerHojasRutaActivaPorProductoYProceso(
					codigoProducto, codigoProceso));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Hojaruta.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		}
	}

}
