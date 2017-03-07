package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProgramaDiarioLogic.java
 * Modificado: Dec 1, 2009 10:11:13 PM
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean;
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
import pe.com.pacasmayo.sgcp.logica.facade.MedioAlmacenamientoLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomedioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ubicacion;
import pe.com.pacasmayo.sgcp.persistencia.querier.MedioAlmacenamientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProduccionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.TipoMedioAlmacenamientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.UbicacionQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class MedioAlmacenamientoLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		MedioAlmacenamientoLogicFacade, ConstantesFiltros {

	private Log logger = LogFactory.getLog(this.getClass());

	private static BeanFactory beanFactory = BeanFactoryImpl.getInstance();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #obtenerMedioAlmacenamiento(java.lang.Long)
	 */
	public MedioAlmacenamientoBean obtenerMedioAlmacenamiento(Long codigoMedioAlmacenamiento) throws LogicaException {

		MedioAlmacenamientoBean MedioAlmacenamientoBean = null;
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

			MedioAlmacenamientoBean = beanFactory.transformarMedioAlmacenamiento(MedioAlmacenamientoQuerier
					.getById(codigoMedioAlmacenamiento));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return MedioAlmacenamientoBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #obtenerMedioAlmacenamientoes()
	 */
	public List<MedioAlmacenamientoBean> obtenerMediosAlmacenamiento() throws LogicaException {

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
			return beanFactory.transformarListaMedioAlmacenamiento(MedioAlmacenamientoQuerier.getAll());
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new LogicaException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}

	public List<Medioalmacenamiento> obtenerMediosAlmacenamientoDataObjects() throws LogicaException {

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
			return MedioAlmacenamientoQuerier.getAllOrderBy(MedioAlmacenamientoQuerier.NOMBRE_MEDIO_ALMACENAMIENTO);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorNombre(String nombre) throws LogicaException {

		String mensajeError = "";

		List<MedioAlmacenamientoBean> listaMedioAlmacenamientoBean = new ArrayList<MedioAlmacenamientoBean>();

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
			listaMedioAlmacenamientoBean = beanFactory.transformarListaMedioAlmacenamiento(MedioAlmacenamientoQuerier
					.findByNombre(nombre));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaMedioAlmacenamientoBean;
	}

	public List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorNumero(Short numero) throws LogicaException {

		String mensajeError = "";

		List<MedioAlmacenamientoBean> listaMedioAlmacenamientoBean = new ArrayList<MedioAlmacenamientoBean>();

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
			listaMedioAlmacenamientoBean = beanFactory.transformarListaMedioAlmacenamiento(MedioAlmacenamientoQuerier
					.findByNumero(numero));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaMedioAlmacenamientoBean;
	}

	public List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorTipoMedioAlmacenamiento(Long codigoTipoMedioAlmac)
			throws LogicaException {

		String mensajeError = "";

		List<MedioAlmacenamientoBean> listaMedioAlmacenamientoBean = new ArrayList<MedioAlmacenamientoBean>();

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
			listaMedioAlmacenamientoBean = beanFactory.transformarListaMedioAlmacenamiento(MedioAlmacenamientoQuerier
					.findByCodigoTipoMedioAlmacenamiento(codigoTipoMedioAlmac));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaMedioAlmacenamientoBean;
	}

	public List<Medioalmacenamiento> obtenerMedioAlmacenamientoDTOPorTipoMedioYProceso(Long codigoTipoMedioAlmac,
			Long codigoProceso) throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			return MedioAlmacenamientoQuerier.obtenerPorTipoMedioProceso(codigoTipoMedioAlmac, codigoProceso);
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
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	public List<Medioalmacenamiento> obtenerMedioAlmacenamientoDTOPorTipoMedioYProcesoYProducto(Long codigoTipoMedioAlmac,
			Long codigoProceso, Long codigoProducto) throws LogicaException {

		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			return MedioAlmacenamientoQuerier.obtenerPorTipoMedioProcesoProducto(codigoTipoMedioAlmac, codigoProceso,
					codigoProducto);
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
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	public List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorPuestoTabajo(Long codigoPuestoTrabajo)
			throws LogicaException {

		String mensajeError = "";

		List<MedioAlmacenamientoBean> listaMedioAlmacenamientoBean = new ArrayList<MedioAlmacenamientoBean>();

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
			listaMedioAlmacenamientoBean = beanFactory.transformarListaMedioAlmacenamiento(MedioAlmacenamientoQuerier
					.findByCodigoPuestoTrabajo(codigoPuestoTrabajo));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaMedioAlmacenamientoBean;
	}

	/**
	 * Metodo que obtiene una lista de producciones semanales filtrada por los
	 * criterios pasados en el Map de propiedades
	 * 
	 * @param propiedades
	 * @return
	 * @throws LogicaException
	 */
	@SuppressWarnings("unchecked")
	public List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientosPorPropiedades(Map propiedades) throws LogicaException {

		Session session = null;
		String mensajeError = "";
		List<MedioAlmacenamientoBean> lista = null;

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

			lista = beanFactory.transformarListaMedioAlmacenamiento(MedioAlmacenamientoQuerier.findByProperties(propiedades));

		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return lista;
	}

	public Medioalmacenamiento obtenerMedioAlmacenamientoDataObject(Long codigoMedioAlmacenamiento) throws LogicaException {
		Medioalmacenamiento medioAlmacenamiento = null;
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

			medioAlmacenamiento = MedioAlmacenamientoQuerier.getById(codigoMedioAlmacenamiento);
		} catch (ElementoNoEncontradoException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return medioAlmacenamiento;
	}

	public List<MedioAlmacenamientoBean> obtenerMedioAlmacenamientoPorUbicacion(Long codigoUbicacion) throws LogicaException {

		String mensajeError = "";

		List<MedioAlmacenamientoBean> listaMedioAlmacenamientoBean = new ArrayList<MedioAlmacenamientoBean>();

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
			listaMedioAlmacenamientoBean = beanFactory.transformarListaMedioAlmacenamiento(MedioAlmacenamientoQuerier
					.findByCodigoUbicacion(codigoUbicacion));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaMedioAlmacenamientoBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #obtenerAtributos()
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
		filtro.setValor(NUMERO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(4);
		filtro.setValor(TIPO_MEDIO_ALMACENAMIENTO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(5);
		filtro.setValor(PUESTO_TRABAJO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(6);
		filtro.setValor(UBICACION);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #insertarMedioAlmacenamiento(pe.com.pacasmayo.sgcp.bean.
	 * MedioAlmacenamientoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #insertarMedioAlmacenamiento(pe.com.pacasmayo.sgcp.bean.
	 * MedioAlmacenamientoBean)
	 */
	public void insertarMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamientoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Medioalmacenamiento medioAlmacenamiento = new Medioalmacenamiento();

			medioAlmacenamiento.setAlturaEspecificaMedioalmacena(medioAlmacenamientoBean.getAlturaEspecifica());

			medioAlmacenamiento.setCapacidadMaximaMedioalmacenam(medioAlmacenamientoBean.getCapacidadMaxima());

			medioAlmacenamiento.setCapacidadMinimaMedioalmacenam(medioAlmacenamientoBean.getCapacidadMinima());

			medioAlmacenamiento.setDensidadMedioalmacenamiento(medioAlmacenamientoBean.getDensidad());

			medioAlmacenamiento.setFactorMetrosCubicosMedioalma(medioAlmacenamientoBean.getFactorMetrosCubicos());

			medioAlmacenamiento.setNombreMedioalmacenamiento(medioAlmacenamientoBean.getNombre());

			medioAlmacenamiento.setNumeroAlturasMedioalmacenamie(medioAlmacenamientoBean.getNumeroAlturas());

			if (medioAlmacenamientoBean.getNumero() != null) {
				medioAlmacenamiento.setNumeroMedioalmacenamiento(medioAlmacenamientoBean.getNumero());
			}

			if (medioAlmacenamientoBean.getProduccion() != null) {

				Produccion produccion = ProduccionQuerier.getByProductoProceso(medioAlmacenamientoBean.getProduccion()
						.getProducto().getCodigo(), medioAlmacenamientoBean.getProduccion().getProceso().getCodigo());

				medioAlmacenamiento.setProduccion(produccion);
			}

			if (medioAlmacenamientoBean.getPuestoTrabajo() != null
					&& medioAlmacenamientoBean.getPuestoTrabajo().getCodigo() != null) {
				Puestotrabajo puestoTrabajo = new Puestotrabajo();
				puestoTrabajo.setPkCodigoPuestotrabajo(medioAlmacenamientoBean.getPuestoTrabajo().getCodigo());
				medioAlmacenamiento.setPuestotrabajo(puestoTrabajo);
			}

			medioAlmacenamiento.setStockSeguridadMedioalmacenami(medioAlmacenamientoBean.getStockSeguridad());

			Tipomedioalmacenamiento tipoMedioAlmacenamiento = new Tipomedioalmacenamiento();
			tipoMedioAlmacenamiento.setPkCodigoTipomedioalmacenamien(medioAlmacenamientoBean.getTipoMedioAlmacenamiento()
					.getCodigo());

			medioAlmacenamiento.setTipomedioalmacenamiento(tipoMedioAlmacenamiento);

			if (medioAlmacenamientoBean.getUbicacion() != null && medioAlmacenamientoBean.getUbicacion().getCodigo() != null) {

				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setPkCodigoUbicacion(medioAlmacenamientoBean.getUbicacion().getCodigo());

				medioAlmacenamiento.setUbicacion(ubicacion);
			}

			MedioAlmacenamientoQuerier.save(medioAlmacenamiento);

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
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #actualizarMedioAlmacenamiento(pe.com.pacasmayo.sgcp.bean.
	 * MedioAlmacenamientoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #actualizarMedioAlmacenamiento(pe.com.pacasmayo.sgcp.bean.
	 * MedioAlmacenamientoBean)
	 */
	public void actualizarMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamientoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Medioalmacenamiento medioAlmacenamiento = MedioAlmacenamientoQuerier.getById(medioAlmacenamientoBean.getCodigo());

			medioAlmacenamiento.setAlturaEspecificaMedioalmacena(medioAlmacenamientoBean.getAlturaEspecifica());

			medioAlmacenamiento.setCapacidadMaximaMedioalmacenam(medioAlmacenamientoBean.getCapacidadMaxima());

			medioAlmacenamiento.setCapacidadMinimaMedioalmacenam(medioAlmacenamientoBean.getCapacidadMinima());

			medioAlmacenamiento.setDensidadMedioalmacenamiento(medioAlmacenamientoBean.getDensidad());

			medioAlmacenamiento.setFactorMetrosCubicosMedioalma(medioAlmacenamientoBean.getFactorMetrosCubicos());

			medioAlmacenamiento.setNombreMedioalmacenamiento(medioAlmacenamientoBean.getNombre());

			medioAlmacenamiento.setNumeroAlturasMedioalmacenamie(medioAlmacenamientoBean.getNumeroAlturas());

			if (medioAlmacenamientoBean.getNumero() != null) {
				medioAlmacenamiento.setNumeroMedioalmacenamiento(medioAlmacenamientoBean.getNumero());
			} else {
				medioAlmacenamiento.setNumeroMedioalmacenamiento(null);
			}

			if (medioAlmacenamientoBean.getProduccion() != null) {

				Produccion produccion = ProduccionQuerier.getByProductoProceso(medioAlmacenamientoBean.getProduccion()
						.getProducto().getCodigo(), medioAlmacenamientoBean.getProduccion().getProceso().getCodigo());

				medioAlmacenamiento.setProduccion(produccion);
			} else {
				medioAlmacenamiento.setProduccion(null);
			}

			if (medioAlmacenamientoBean.getPuestoTrabajo() != null
					&& medioAlmacenamientoBean.getPuestoTrabajo().getCodigo() != null) {
				Puestotrabajo puestoTrabajo = PuestoTrabajoQuerier
						.getById(medioAlmacenamientoBean.getPuestoTrabajo().getCodigo());
				medioAlmacenamiento.setPuestotrabajo(puestoTrabajo);
			} else {
				medioAlmacenamiento.setPuestotrabajo(null);
			}

			medioAlmacenamiento.setStockSeguridadMedioalmacenami(medioAlmacenamientoBean.getStockSeguridad());
			if (medioAlmacenamientoBean.getTipoMedioAlmacenamiento() != null
					&& medioAlmacenamientoBean.getTipoMedioAlmacenamiento().getCodigo() != null) {
				Tipomedioalmacenamiento tipoMedioAlmacenamiento = TipoMedioAlmacenamientoQuerier.getById(medioAlmacenamientoBean
						.getTipoMedioAlmacenamiento().getCodigo());
				medioAlmacenamiento.setTipomedioalmacenamiento(tipoMedioAlmacenamiento);
			} else {
				medioAlmacenamiento.setTipomedioalmacenamiento(null);
			}

			if (medioAlmacenamientoBean.getUbicacion() != null && medioAlmacenamientoBean.getUbicacion().getCodigo() != null) {

				Ubicacion ubicacion = UbicacionQuerier.getById(medioAlmacenamientoBean.getUbicacion().getCodigo());

				medioAlmacenamiento.setUbicacion(ubicacion);
			} else {
				medioAlmacenamiento.setUbicacion(null);
			}

			MedioAlmacenamientoQuerier.update(medioAlmacenamiento);

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
		} catch (AplicacionException e) {
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medioalmacenamiento.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #eliminarMedioAlmacenamiento(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogicFacade
	 * #eliminarMedioAlmacenamiento(pe.com.pacasmayo.sgcp.bean.
	 * MedioAlmacenamientoBean)
	 */
	public void eliminarMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamientoBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Medioalmacenamiento medioAlmacenamiento = MedioAlmacenamientoQuerier.getById(medioAlmacenamientoBean.getCodigo());

			MedioAlmacenamientoQuerier.delete(medioAlmacenamiento);

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

	public List<Medioalmacenamiento> obtenerMedioAlmacenamientoPorProceso(Long codigoProceso) throws LogicaException {
		Session session = null;
		try {
			session = PersistenciaFactory.currentSession();
			return MedioAlmacenamientoQuerier.obtenerPorProceso(codigoProceso);
		} catch (SesionVencidaException e) {
			throw new LogicaException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new EntornoEjecucionException(e.getMensaje(), e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}
	}
}