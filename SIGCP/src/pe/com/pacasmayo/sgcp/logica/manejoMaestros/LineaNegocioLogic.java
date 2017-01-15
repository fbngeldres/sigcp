package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: LineaNegocioLogic.java
 * Modificado: Nov 27, 2009 3:40:38 PM
 * Autor: evelyn.santamaria
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
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
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.LineaNegocioQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class LineaNegocioLogic implements ConstantesFiltros, ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		LineaNegocioLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory;

	public LineaNegocioLogic() {

		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineasNegocio()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineasNegocio()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineasNegocio()
	 */
	public List<LineaNegocioBean> obtenerLineasNegocio() throws LogicaException {

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
			return beanFactory.transformarListaLineaNegocio(LineaNegocioQuerier.getAll());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineasNegocioBasico()
	 */
	public List<LineaNegocioBean> obtenerLineasNegocioBasico() throws LogicaException {

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
			return beanFactory.transformarListaLineaNegocioBasico(LineaNegocioQuerier.getAll());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineasNegocioParaCombo()
	 */
	public List<LineaNegocioBean> obtenerLineasNegocioParaCombo() throws LogicaException {

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
			return beanFactory.transformarListaLineaNegocioParaCombos(LineaNegocioQuerier.getAll());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocio(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocio(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocio(java.lang.Long)
	 */
	public LineaNegocioBean obtenerLineaNegocio(Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";
		LineaNegocioBean lineaNegocioBean = null;

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
			lineaNegocioBean = beanFactory.transformarLineaNegocio(LineaNegocioQuerier.getById(codigoLineaNegocio));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return lineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioBasico(java.lang.Long)
	 */
	public LineaNegocioBean obtenerLineaNegocioBasico(Long codigoLineaNegocio) throws LogicaException {

		String mensajeError = "";
		LineaNegocioBean lineaNegocioBean = null;

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
			lineaNegocioBean = beanFactory.transformarLineaNegocioBasico(LineaNegocioQuerier.getById(codigoLineaNegocio));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return lineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorCodigoUnidad(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorCodigoUnidad(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorCodigoUnidad(java.lang.Long)
	 */
	public List<LineaNegocioBean> obtenerLineaNegocioPorCodigoUnidad(Long codigoUnidad) throws LogicaException {

		String mensajeError = "";

		List<LineaNegocioBean> listaLineaNegocioBean = new ArrayList<LineaNegocioBean>();

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
			listaLineaNegocioBean = beanFactory
					.transformarListaLineaNegocio(LineaNegocioQuerier.findByCodigoUnidad(codigoUnidad));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaLineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioBasicoPorCodigoUnidad(java.lang.Long)
	 */
	public List<LineaNegocioBean> obtenerLineaNegocioBasicoPorCodigoUnidad(Long codigoUnidad) throws LogicaException {

		String mensajeError = "";

		List<LineaNegocioBean> listaLineaNegocioBean = new ArrayList<LineaNegocioBean>();

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
			listaLineaNegocioBean = beanFactory.transformarListaLineaNegocioBasico(LineaNegocioQuerier
					.findByCodigoUnidad(codigoUnidad));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaLineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorCodigoSCC(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorCodigoSCC(java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorCodigoSCC(java.lang.Long)
	 */
	public List<LineaNegocioBean> obtenerLineaNegocioPorCodigoSCC(Long codigoSCC) throws LogicaException {

		String mensajeError = "";

		List<LineaNegocioBean> listaLineaNegocioBean = new ArrayList<LineaNegocioBean>();

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
			listaLineaNegocioBean = beanFactory.transformarListaLineaNegocio(LineaNegocioQuerier.findByCodigoSCC(codigoSCC));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaLineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineasNegocioDataObjects(java.lang.Long)
	 */
	public List<Lineanegocio> obtenerLineasNegocioDataObjects(Long codigoUnidad) throws LogicaException {

		String mensajeError = "";

		List<Lineanegocio> lineas = null;

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
			lineas = LineaNegocioQuerier.findByCodigoUnidad(codigoUnidad);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return lineas;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorNombre(java.lang.String)
	 */
	public List<LineaNegocioBean> obtenerLineaNegocioPorNombre(String nombreLineaNegocio) throws LogicaException {

		String mensajeError = "";

		List<LineaNegocioBean> listaLineaNegocioBean = new ArrayList<LineaNegocioBean>();

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
			listaLineaNegocioBean = beanFactory
					.transformarListaLineaNegocio(LineaNegocioQuerier.findByNombre(nombreLineaNegocio));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Lineanegocio.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaLineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineaNegocioPorUsuario(pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public List<LineaNegocioBean> obtenerLineaNegocioPorUsuario(UsuarioBean usuario) throws LogicaException {
		Map<Long, LineaNegocioBean> lineasNegocioMap = new HashMap<Long, LineaNegocioBean>();
		List<UnidadBean> unidades = usuario.getPersona().getCargo().getUnidadBeanList();

		List<LineaNegocioBean> lineas = new ArrayList<LineaNegocioBean>();

		for (int i = 0; i < unidades.size(); i++) {
			UnidadBean unidad = unidades.get(i);
			List<LineaNegocioBean> lineasAux = obtenerLineaNegocioPorCodigoUnidad(unidad.getCodigo());

			Iterator<LineaNegocioBean> lineaAuxIter = lineasAux.iterator();

			while (lineaAuxIter.hasNext()) {
				LineaNegocioBean linea = lineaAuxIter.next();

				if (!lineasNegocioMap.containsKey(linea.getCodigo()))
					lineasNegocioMap.put(linea.getCodigo(), linea);

			}
		}

		Collection<LineaNegocioBean> coleccion = lineasNegocioMap.values();
		Iterator<LineaNegocioBean> coleccionIter = coleccion.iterator();

		while (coleccionIter.hasNext()) {
			lineas.add(coleccionIter.next());
		}

		return lineas;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerAtributos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerAtributos()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
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
		filtro.setValor(UNIDAD);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * insertarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * insertarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * insertarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	public void insertarLineaNegocio(LineaNegocioBean lineaNegocioBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Lineanegocio lineaNegocio = new Lineanegocio();

			lineaNegocio.setCodigoSapLineanegocio(lineaNegocioBean.getCodigoSAP());
			//lineaNegocio.setCodigoSccLineanegocio(lineaNegocioBean.getCodigoSCC());
			lineaNegocio.setDescripcionLineanegocio(lineaNegocioBean.getDescripcion());
			lineaNegocio.setNombreLineanegocio(lineaNegocioBean.getNombre());

			Unidad unidad = new Unidad();
			unidad.setPkCodigoUnidad(lineaNegocioBean.getUnidad().getCodigo());

			lineaNegocio.setUnidad(unidad);

			LineaNegocioQuerier.save(lineaNegocio);

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
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * actualizarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * actualizarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * actualizarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	public void actualizarLineaNegocio(LineaNegocioBean lineaNegocioBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Lineanegocio lineaNegocio = LineaNegocioQuerier.getById(lineaNegocioBean.getCodigo());

			lineaNegocio.setCodigoSapLineanegocio(lineaNegocioBean.getCodigoSAP());
			//lineaNegocio.setCodigoSccLineanegocio(lineaNegocioBean.getCodigoSCC());
			lineaNegocio.setDescripcionLineanegocio(lineaNegocioBean.getDescripcion());
			lineaNegocio.setNombreLineanegocio(lineaNegocioBean.getNombre());

			Unidad unidad = UnidadQuerier.getById(lineaNegocioBean.getUnidad().getCodigo());
			lineaNegocio.setUnidad(unidad);

			LineaNegocioQuerier.update(lineaNegocio);

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
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * eliminarLineaNegocio(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * eliminarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * eliminarLineaNegocio(pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	public void eliminarLineaNegocio(LineaNegocioBean lineaNegocioBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Lineanegocio lineaNegocio = LineaNegocioQuerier.getById(lineaNegocioBean.getCodigo());

			LineaNegocioQuerier.delete(lineaNegocio);

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
	 * @see pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogicFacade#
	 * obtenerLineasNegocioBasico()
	 */
	public LineaNegocioBean obtenerLineasNegocioSegunHojaruta(Long codigoProducto) throws LogicaException {

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
			Lineanegocio lineaNegocio = null;
			lineaNegocio = HojaRutaQuerier.getLineaNegocioSegunHojaRuta(codigoProducto);
			LineaNegocioBean lineaNegocioBean = beanFactory.transformarLineaNegocio(lineaNegocio);
			return lineaNegocioBean;
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_EXCEPTION_ELEMENTO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}
}
