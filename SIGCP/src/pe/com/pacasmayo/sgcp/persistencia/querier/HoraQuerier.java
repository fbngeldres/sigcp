package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SociedadQuerier.java
 * Modificado: Jun 3, 2010 5:34:31 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hora;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class HoraQuerier implements ConstantesMensajeAplicacion {

	private static Logger log = Logger.getLogger(HoraQuerier.class);

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/
	private static final String CODIGO_TURNO = "turno.pkCodigoTurno";
	private static final String VALOR_HORA = "horaHora";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Hora
	 * 
	 * @return
	 */
	public static List<Hora> getAll() {

		return Querier.getAll(Hora.class);
	}

	/**
	 * Método para obtener la lista de objectos Hora, ordenadas por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Hora> getAllOrderBy(String order) {

		return Querier.getAll(Hora.class, order);
	}

	/**
	 * Método para obtener una Hora de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Hora getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Hora.class, codigo);
	}

	/**
	 * Método para obtener una lista de Horas por medio del turno.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Hora> findByCodigoTurno(Long codigoTurno) throws AplicacionException {

		try {
			return Querier.findByProperty(Hora.class, CODIGO_TURNO, codigoTurno);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de Horas por medio de la hora.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Hora> findByHora(Short hora) throws AplicacionException {

		try {
			return Querier.findByProperty(Hora.class, VALOR_HORA, hora);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar una Hora en la BD.
	 * 
	 * @param Hora
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Hora hora) throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {

		Querier.save(hora);
	}

	/**
	 * Método para modificar una Hora de la BD.
	 * 
	 * @param Hora
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Hora hora) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(hora);
	}

	/**
	 * Método para eliminar una Hora de la BD.
	 * 
	 * @param Hora
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Hora hora) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(hora);
	}

	@SuppressWarnings("unchecked")
	public static Short obtenerUltimaHoraDeUltimoTurno() throws ElementoNoEncontradoException {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select turno.horaFinTurno FROM Turno AS turno WHERE turno.pkCodigoTurno = 3");

			Query query = Querier.query(queryStr.toString());

			return (Short) query.uniqueResult();

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static Short obtenerHoraInicioPrimerTurno() throws ElementoNoEncontradoException {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select turno.horaInicioTurno FROM Turno AS turno WHERE turno.pkCodigoTurno = 1");

			Query query = Querier.query(queryStr.toString());

			return (Short) query.uniqueResult();

		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(mensajeError);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}
}
