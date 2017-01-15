package pe.com.pacasmayo.sgcp.logica.stock;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.PeriodoContableBean;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactory;
import pe.com.pacasmayo.sgcp.bean.factory.BeanFactoryImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.PeriodoContableLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable;
import pe.com.pacasmayo.sgcp.persistencia.querier.PeriodoContableQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PeriodoContableLogic.java
 * Modificado: Jun 1, 2010 4:07:26 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class PeriodoContableLogic implements ConstantesMensajeAplicacion, ConstantesLogicaNegocio, ConstantesAplicacionAction,
		PeriodoContableLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	private BeanFactory beanFactory;

	public PeriodoContableLogic() {
		this.beanFactory = BeanFactoryImpl.getInstance();
	}

	public PeriodoContableBean getPeriodoContablePorFecha(short mes, int ano) throws LogicaException {
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
		PeriodoContableBean periodoContableBean = null;

		try {
			periodoContableBean = beanFactory.transformarPeriodoContable(PeriodoContableQuerier.getByMesYAno(mes, ano));
		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return periodoContableBean;
	}

	/**
	 * Método para listar los años del periodo contable abierto
	 * 
	 * @return
	 */
	public List<Integer> obtenerAnosPorPeriodoAbierto() throws LogicaException {

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
			List<Integer> anios = PeriodoContableQuerier.getAnosPorPeriodoAbierto();
			return anios;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * Método para listar los años del periodo contable abierto
	 * 
	 * @return
	 */
	public List<Integer> obtenerAnosPorPeriodo() throws LogicaException {

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
			List<Integer> anios = PeriodoContableQuerier.getAnosPorPeriodo();
			return anios;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * Método para listar los meses de periodo contable
	 * 
	 * @return
	 */
	public List<String> obtenerMesesPorPeriodoAbierto() throws LogicaException {

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
			List<Short> meses = PeriodoContableQuerier.getMesesPorPeriodoAbierto();
			List<String> nombresMes = new ArrayList<String>();

			for (int i = 0; i < meses.size(); i++) {
				nombresMes.add(FechaUtil.numeroMesANombreMes(meses.get(i)));
			}

			return nombresMes;
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * Método para registrar el nuevo periodo contable
	 */
	public void registrarNuevoPeriodoContable(int year, Short month) throws LogicaException {
		try {
			if (month == 12) {
				year++;
				month = 1;
			} else {
				month++;
			}
			Periodocontable periodoContableNew = PeriodoContableQuerier.getByMesYAno(month, year);
			if (periodoContableNew == null) {
				Periodocontable periodoContable = new Periodocontable();
				periodoContable.setAnoPeriodocontable(year);
				periodoContable.setMesPeriodocontable(month);
				periodoContable.setCerradoPeridocontable(false);

				PeriodoContableQuerier.save(periodoContable);
			}
		} catch (ParametroInvalidoException e) {
			e.printStackTrace();
		} catch (ElementoExistenteException e) {
			e.printStackTrace();
		} catch (ElementoEliminadoException e) {
			e.printStackTrace();
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
		}
	}

}
