package pe.com.pacasmayo.sgcp.logica.stock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.MedicionLogicFacade;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medicion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registromedicion;
import pe.com.pacasmayo.sgcp.persistencia.querier.MedicionQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PersistenciaFactory;
import pe.com.pacasmayo.sgcp.persistencia.querier.RegistroMedicionQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedicionDiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.StockDTO;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroMedicionLogic.java
 * Modificado: May 7, 2010 9:49:17 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class MedicionLogic implements ConstantesMensajePresentacion, ConstantesMensajeAplicacion, MedicionLogicFacade {

	private Log logger = LogFactory.getLog(this.getClass());

	public void insertarMedicion(Medicion medicion) throws LogicaException, HibernateException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			MedicionQuerier.save(medicion);
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
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public void actualizarMedicion(Medicion medicion) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();
			MedicionQuerier.update(medicion);
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
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	public void eliminarMedicion(Medicion medicion) throws LogicaException {

		String mensajeError = "";
		Transaction tx = null;
		Session session = null;

		try {

			session = PersistenciaFactory.currentSession();
			tx = session.beginTransaction();

			MedicionQuerier.delete(medicion);
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
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
	}

	/**
	 * Metodo que retorna la lista de StockDTO en la consulta de stock mensual
	 * por productos asociados a un proceso
	 */
	public List<StockDTO> obtenerStockMensualProductosPorProceso(Long codigoProceso, Integer anno, Short mes,
			Long codigoEstadoRegistroMedicion, Long codigoTipoMedioAlamacenamiento) throws LogicaException {

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

			return MedicionQuerier.obtenerStockMensualProductosPorProceso(codigoProceso, anno, mes, codigoEstadoRegistroMedicion,
					codigoTipoMedioAlamacenamiento);
		} catch (AplicacionException e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medicion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * Metodo que retorna la lista de RegistroTablaMedicionDiaDTO en la consulta
	 * de detalle del silo
	 */
	public List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(Long codigoregistroMedicion)
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

			List<RegistroTablaMedicionDiaDTO> listaRegistroDetallesStock = MedicionQuerier
					.obtenerListaRegistroDetallesStock(codigoregistroMedicion);

			return listaRegistroDetallesStock;

		} catch (AplicacionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medicion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

	}

	/**
	 * Metodo para cargar la lista de RegistroTablaMedicionDiaDTO para la
	 * ventana de registro de medicion
	 */
	@SuppressWarnings("unchecked")
	public List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(Long codigoProceso, Long codigoTipoMedio,
			Date fecha, Long codigoEstado, Long lineaNegocio) throws LogicaException {
		String mensajeError = "";
		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();

			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);

			Registromedicion registrosExistentes = RegistroMedicionQuerier.obtenerRegistroMedicionPorVarios(codigoProceso,
					new Integer(cal.get(Calendar.YEAR)), new Short(new Integer(cal.get(Calendar.MONTH) + 1).shortValue()),
					codigoTipoMedio, lineaNegocio, fecha);

			List<RegistroTablaMedicionDiaDTO> listaRegistroDetallesStock = new ArrayList<RegistroTablaMedicionDiaDTO>();
			if (registrosExistentes != null) {
				// No puede existir dos registros de Medición Diaria
				for (Iterator<Medicion> iterator = registrosExistentes.getMedicions().iterator(); iterator.hasNext();) {
					Medicion medicion = iterator.next();

					//listaRegistroDetallesStock.add(ConvertidorHibernateDTO.convertirMedicionAMedicionDTO(medicion));
				}

				Collections.sort(listaRegistroDetallesStock, new Comparator<RegistroTablaMedicionDiaDTO>() {
					public int compare(RegistroTablaMedicionDiaDTO o1, RegistroTablaMedicionDiaDTO o2) {
						return o1.getNombreSilo().compareTo(o2.getNombreSilo());
					}

				});
			} else {
				listaRegistroDetallesStock = MedicionQuerier.obtenerListaRegistroDetallesStock(codigoProceso, codigoTipoMedio);
			}

			return listaRegistroDetallesStock;

		} catch (AplicacionException e) {
			logger.error(e);
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA)
					+ Medicion.class.getName().toString();
			throw new LogicaException(mensajeError, e);
		} finally {
			PersistenciaFactory.closeSession(session);
		}

	}

	public List<MedicionDiariaDTO> obtenerDetalleRegistroMedicion(Long codigoProceso, Long codigoProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException {

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

		List<MedicionDiariaDTO> mediciones = null;
		try {

			mediciones = obtenerDetalleRegistroMedicionDAO(codigoProceso, codigoProducto, codigoLineaNegocio, anno, mes);
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}
		return mediciones;
	}

	public List<MedicionDiariaDTO> obtenerDetalleRegistroMedicionDAO(Long codigoProceso, Long codigoProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException {

		List<MedicionDiariaDTO> mediciones = null;
		try {
			Date fechainicio = FechaUtil.FormaterFecha("01" + FechaUtil.SEPARADOR_CAMPOS_FECHA_APP + mes
					+ FechaUtil.SEPARADOR_CAMPOS_FECHA_APP + anno);
			Date fechaFin = FechaUtil.obtenerFechaDiaSiguiente(FechaUtil.getUltimoDiaMes(--mes, anno).getTime());

			mediciones = MedicionQuerier.obtenerDetalleRegistroMedicion(codigoProceso, codigoProducto, codigoLineaNegocio,
					fechainicio, fechaFin);

		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
		}
		return mediciones;
	}

	public Double obtenerCantidadAlmacenada(Long codigoSilo, double promedioAlturas, Double[] alturas) throws LogicaException {

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
		Double cantidadAlmacenada = 0d;
		try {
			cantidadAlmacenada = MedicionQuerier.obtenerCantidadAlmacenada(codigoSilo, promedioAlturas, alturas).doubleValue();
		} catch (Exception e) {

			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CONSULTA_LISTA);
			logger.error(mensajeError, e);
			throw new LogicaException(mensajeError, e);
		} finally {
			if (session != null)
				PersistenciaFactory.closeSession(session);
		}

		return cantidadAlmacenada;
	}
}