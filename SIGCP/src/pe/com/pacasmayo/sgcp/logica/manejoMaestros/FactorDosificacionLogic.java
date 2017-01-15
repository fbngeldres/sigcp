package pe.com.pacasmayo.sgcp.logica.manejoMaestros;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: FactorDosificacionLogic.java
 * Modificado: Feb 10, 2010 10:11:13 PM
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacionregistromensu;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojaruta;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadmedida;
import pe.com.pacasmayo.sgcp.persistencia.querier.ComponenteQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorDosificacionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.FactorDosificacionRegistroMensualQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.HojaRutaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.UnidadMedidaQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesFiltros;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class FactorDosificacionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion,
		FactorDosificacionLogicFacade, ConstantesFiltros {

	private Log logger = LogFactory.getLog(this.getClass());

	public static final String TAMANO_LISTA_COMPONENTES = "tamanoListaHojaRutaComponentes";
	public static final String CODIGO_COMPONENTE = "codigoComponente";
	public static final String CODIGO_HOJA_RUTA = "codigoHojaRuta";
	public static final String CODIGO_UNIDAD_MEDIDA = "codigoUnidadMedida";
	public static final String CODIGO_FACTOR_DOSIFICACION = "codigoFactorDosificacion";

	private static BeanFactory beanFactory;

	public FactorDosificacionLogic() {

		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #obtenerFactorDosificacion(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #obtenerFactorDosificacion(java.lang.Long)
	 */
	public FactorDosificacionBean obtenerFactorDosificacion(Long codigoFactorDosificacion) throws LogicaException {

		FactorDosificacionBean factorDosificacionBean = null;
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
			factorDosificacionBean = beanFactory.transformarFactordosificacion(FactorDosificacionQuerier
					.getById(codigoFactorDosificacion));
		} catch (ElementoNoEncontradoException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_NO_ENCONTRADO);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return factorDosificacionBean;
	}

	public List<FactorDosificacionBean> obtenerFactorDosificacionPorHojaRuta(Long codigoHojaRuta) throws LogicaException {

		String mensajeError = "";
		List<FactorDosificacionBean> listaFactorDosificacionBean = null;

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
			listaFactorDosificacionBean = beanFactory.transformarListaFactordosificacion(FactorDosificacionQuerier
					.findByHojaRuta(codigoHojaRuta));

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Factordosificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return listaFactorDosificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #obtenerFactorDosificaciones()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #obtenerFactorDosificaciones()
	 */
	public List<FactorDosificacionBean> obtenerFactorDosificaciones() throws LogicaException {

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
			return beanFactory.transformarListaFactordosificacion(FactorDosificacionQuerier.getAll());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Factordosificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Este método obtiene la lista de factores de dosificacion de acuerdo al
	 * año del registro mensual
	 */
	public List<FactorDosificacionBean> obtenerFactorDosificacionesPorAnnio(Integer annio) throws LogicaException {

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
			return beanFactory.transformarListaFactordosificacion(FactorDosificacionQuerier.findByAnnio(annio));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Factordosificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Este método obtiene la lista de factores de dosificacion de acuerdo al
	 * nombre de la hoja de ruta
	 */
	public List<FactorDosificacionBean> obtenerFactorDosificacionesPorNombreHojaRuta(String nombreHojaRuta)
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
			return beanFactory.transformarListaFactordosificacion(FactorDosificacionQuerier.findByNombreHojaRuta(nombreHojaRuta));
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Factordosificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<FactorDosificacionBean> obtenerFactorDosificacionesConRegistrosMensuales() throws LogicaException {

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
			return beanFactory.transformarListaFactordosificacion(FactorDosificacionQuerier.findWithRegistroMensual());
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Factordosificacion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public List<UtilBean> obtenerAtributos() {

		List<UtilBean> filtros = new ArrayList<UtilBean>();

		UtilBean filtro = new UtilBeanImpl();
		filtro.setCodigo(1);
		filtro.setValor(CODIGO);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(2);
		filtro.setValor(HOJA_RUTA);
		filtros.add(filtro);

		filtro = new UtilBeanImpl();
		filtro.setCodigo(3);
		filtro.setValor(ANNIO);
		filtros.add(filtro);

		return filtros;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #
	 * insertarFactorDosificacion(pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean
	 * )
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #
	 * insertarFactorDosificacion(pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean
	 * )
	 */
	public void insertarFactorDosificacion(FactorDosificacionBean factorDosificacionBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Factordosificacion factorDosificacion = new Factordosificacion();

			Hojaruta hojaRuta = HojaRutaQuerier.getById(factorDosificacionBean.getHojaRuta().getCodigo());
			factorDosificacion.setHojaruta(hojaRuta);

			Unidadmedida unidadMedida = new Unidadmedida();
			unidadMedida.setPkCodigoUnidadMedida(factorDosificacionBean.getUnidad().getCodigo());

			factorDosificacion.setUnidadmedida(unidadMedida);

			FactorDosificacionQuerier.save(factorDosificacion);

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

	public void insertarFactorDosificacions(Map parameters, Integer anno) throws LogicaException {

		String[] cantidadComponentesString = (String[]) parameters.get(TAMANO_LISTA_COMPONENTES);

		int cantidadComponentes = Integer.parseInt(cantidadComponentesString[0]);

		String[] codigoHojaRutaString = (String[]) parameters.get(CODIGO_HOJA_RUTA);
		String[] codigoComponenteString = (String[]) parameters.get(CODIGO_COMPONENTE);
		String[] codigoUnidadMedidaString = (String[]) parameters.get(CODIGO_UNIDAD_MEDIDA);

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			// Obtenemos la Hoja de ruta
			Hojaruta hojaRuta = HojaRutaQuerier.getById(Long.parseLong(codigoHojaRutaString[0]));

			for (int i = 0; i < cantidadComponentes; i++) {

				String componenteId = codigoComponenteString[i];

				Factordosificacion factorDosificacion = asignarFactorDosificacion(hojaRuta, anno, componenteId);

				// seteamos la Hojaruta
				factorDosificacion.setHojaruta(hojaRuta);

				// seteamos el Componente
				Componente componente = ComponenteQuerier.getById(Long.parseLong(componenteId));
				factorDosificacion.setComponente(componente);

				// seteamos la Unidadmedida
				Unidadmedida unidadMedida = UnidadMedidaQuerier.getById(Long.parseLong(codigoUnidadMedidaString[i]));

				factorDosificacion.setUnidadmedida(unidadMedida);

				FactorDosificacionQuerier.saveOrUpdate(factorDosificacion);

				eliminarFactoresDosificacionRegistroMensu(factorDosificacion);

				Set<Factordosificacionregistromensu> listaFactDosRegMen = new HashSet<Factordosificacionregistromensu>();

				for (short j = 1; j < 13; j++) {
					Factordosificacionregistromensu factorDosificacionRegistroMensu = new Factordosificacionregistromensu();

					// se registra el factor de dosificación
					factorDosificacionRegistroMensu.setFactordosificacion(factorDosificacion);

					// se registra el mes
					factorDosificacionRegistroMensu.setMesFactordosificacionregistrom(j);

					// se registra el año
					factorDosificacionRegistroMensu.setAnnoFactordosificacionregistro(anno);

					// se registra la cantidad
					double cantidadRegistroMes = obtenerCantidadRegMensual(i, j, parameters);

					factorDosificacionRegistroMensu.setCantidadFactordosificacionregi(cantidadRegistroMes);

					FactorDosificacionRegistroMensualQuerier.save(factorDosificacionRegistroMensu);
				}

				factorDosificacion.setFactordosificacionregistromensus(listaFactDosRegMen);

				FactorDosificacionQuerier.saveOrUpdate(factorDosificacion);
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
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_RELACIONADO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} catch (org.hibernate.HibernateException e) {
			logger.error(e.getMessage());
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(COMUNICACION_BD_FALLO);

			if (tx != null)
				tx.rollback();

			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Método que consulta los factores de dosificacion una hoja de ruta
	 * verificando si existe uno para el anno y componente espeficicados. En
	 * caso de no existir retorna un nuevo factor de dosificacion.
	 * 
	 * @param hojaRuta Hoja de Ruta
	 * @param anno Año
	 * @param componenteId codigo del componente
	 * @return Factor de dosificacion nuevo en caso de no existir
	 */
	private Factordosificacion asignarFactorDosificacion(Hojaruta hojaRuta, Integer anno, String componenteId) {

		Set<Factordosificacion> factoresDosificacion = hojaRuta.getFactordosificacions();

		for (Factordosificacion factordosificacion : factoresDosificacion) {

			if (componenteId.equals(factordosificacion.getComponente().getPkCodigoComponente().toString())) {

				Set<Factordosificacionregistromensu> factoresMensuales = factordosificacion.getFactordosificacionregistromensus();

				for (Factordosificacionregistromensu factordosificacionregistromensu : factoresMensuales) {

					if (anno.equals(factordosificacionregistromensu.getAnnoFactordosificacionregistro())) {

						return factordosificacion;
					}
				}
			}
		}

		return new Factordosificacion();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #actualizarFactorDosificacion(pe.com.pacasmayo.sgcp.bean.
	 * FactorDosificacionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #actualizarFactorDosificacion(pe.com.pacasmayo.sgcp.bean.
	 * FactorDosificacionBean)
	 */
	public void actualizarFactorDosificacion(FactorDosificacionBean factorDosificacionBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Factordosificacion factorDosificacion = FactorDosificacionQuerier.getById(factorDosificacionBean.getCodigo());

			Hojaruta hojaRuta = HojaRutaQuerier.getById(factorDosificacionBean.getHojaRuta().getCodigo());
			factorDosificacion.setHojaruta(hojaRuta);

			Unidadmedida unidadMedida = UnidadMedidaQuerier.getById(factorDosificacionBean.getUnidad().getCodigo());
			factorDosificacion.setUnidadmedida(unidadMedida);

			FactorDosificacionQuerier.update(factorDosificacion);

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

	/**
	 * Método para actualizar el valor de los factores de dosificacion de una
	 * hoja de ruta.
	 */
	public void actualizarFactorDosificacion(Map parameters, Integer anno) throws LogicaException {
		insertarFactorDosificacions(parameters, anno);
	}

	private void eliminarFactoresDosificacionRegistroMensu(Factordosificacion factorDosificacion)
			throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {

		for (Iterator<Factordosificacionregistromensu> iterator = factorDosificacion.getFactordosificacionregistromensus()
				.iterator(); iterator.hasNext();) {

			Factordosificacionregistromensu factorDosificacionRegMenEliminar = (Factordosificacionregistromensu) iterator.next();

			FactorDosificacionRegistroMensualQuerier.delete(factorDosificacionRegMenEliminar);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #eliminarFactorDosificacion(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.manejoMaestros.FactorDosificacionLogicFacade
	 * #
	 * eliminarFactorDosificacion(pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean
	 * )
	 */
	public void eliminarFactorDosificacion(FactorDosificacionBean factorDosificacionBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			Factordosificacion factorDosificacion = FactorDosificacionQuerier.getById(factorDosificacionBean.getCodigo());

			FactorDosificacionQuerier.delete(factorDosificacion);

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

	public void eliminarFactoresDosificacion(List<HojaRutaComponenteBean> listaHojaRutaComponenteBean) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			for (Iterator<HojaRutaComponenteBean> iterator = listaHojaRutaComponenteBean.iterator(); iterator.hasNext();) {

				HojaRutaComponenteBean hojaRutaComponenteBean = (HojaRutaComponenteBean) iterator.next();

				if (hojaRutaComponenteBean.getComponente().getFactorDosificacion() != null) {

					for (Iterator<FactorDosificacionBean> iterator2 = hojaRutaComponenteBean.getComponente()
							.getFactorDosificacion().iterator(); iterator2.hasNext();) {

						FactorDosificacionBean factorDosificacion = (FactorDosificacionBean) iterator2.next();
						Factordosificacion factorDosificacionEliminar = FactorDosificacionQuerier.getById(factorDosificacion
								.getCodigo());

						FactorDosificacionQuerier.delete(factorDosificacionEliminar);
					}
				}
			}

			tx.commit();

		} catch (ParametroInvalidoException e) {
			logger.error(e.getMessage());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_CAMPO_OBJETO_INVALIDO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoExistenteException e) {
			logger.error(e.getMessage());
			if (tx != null)
				tx.rollback();

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(MAESTROS_REGISTRO_DUPLICADO);
			throw new LogicaException(mensajeError, e);
		} catch (ElementoEliminadoException e) {
			logger.error(e.getMessage());
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

	public void eliminarFactoresDosificacionPorHojaRutaYAnnio(Long codigoHojaRuta, Integer annio) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			FactorDosificacionQuerier.deleteByCodigoHojaRutaYAnnio(codigoHojaRuta, annio);

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

	private Double obtenerCantidadRegMensual(int fila, int mes, Map parameter) {

		String key = "" + fila + "_factdos_mes" + mes;
		String[] value = (String[]) parameter.get(key);
		Double cantidad = 0.0;
		if (value != null && value[0] != null && value[0].length() > 0)
			cantidad = Double.parseDouble(value[0]);

		return cantidad;
	}

	/**
	 * Método para seleccionar el factor de dosificación del año especificado
	 * 
	 * @param factorDosificacion Lista de factores de dosificación
	 * @param anno Año
	 * @return Lista solo con el factor de dosifiación para el año.
	 */
	public List<FactorDosificacionBean> filtrarFactorDosificacion(List<FactorDosificacionBean> factorDosificacion, Integer anno) {

		List<FactorDosificacionBean> listaFactorDosificacion = new ArrayList<FactorDosificacionBean>();

		for (FactorDosificacionBean factorDosificacionBean : factorDosificacion) {

			FactorDosificacionRegistroMensualBean[] factorMensual = factorDosificacionBean.getFactorDosificacionRegistroMensual();

			for (int i = 0; i < factorMensual.length; i++) {

				FactorDosificacionRegistroMensualBean factorDosificacionRegistroMensualBean = factorMensual[i];

				if (factorDosificacionRegistroMensualBean == null) {
					continue;
				}

				if (!(anno.equals(factorDosificacionRegistroMensualBean.getAnno()))) {
					break;
				}

				listaFactorDosificacion.add(factorDosificacionBean);

				return listaFactorDosificacion;

			}
		}

		return listaFactorDosificacion;
	}

}
