package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Datoreporte;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: DatoReporteQuerier.java
 * Modificado: Jul 1, 2010 9:23:37 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class DatoReporteQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * M�todo para insertar un dato del reporte en la BD.
	 * 
	 * @param dato
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Datoreporte dato) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(dato);
	}

	/**
	 * M�todo para modificar un dato del reporte en la BD.
	 * 
	 * @param dato
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Datoreporte dato) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(dato);
	}

	/**
	 * M�todo para eliminar un dato del reporte en la BD.
	 * 
	 * @param dato
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Datoreporte dato) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(dato);
	}

	/**
	 * M�todo para obtener una Datoreporte de la BD por el c�digo.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Datoreporte getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Datoreporte.class, codigo);
	}

	/**
	 * Metodo para cargar las filas de variables por hora usado para cargar
	 * datos en el grid de registro de VO.
	 * 
	 * @param fecha
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Datoreporte> obtenerFilasVariables(Date fecha, String nombre) throws AplicacionException {
		List<Datoreporte> filasVariables = new ArrayList<Datoreporte>();

		logger.debug("<- metodo obtenerFilasVariables ");
		logger.debug("fecha recibida " + fecha);

		String consulta = "SELECT dr FROM Registroreporteecs as rre, Datoreporte as dr"
				+ " WHERE rre.fechaRegistroreporteecs = :fecha"
				+ " AND rre.pkCodigoRegistroreporteecs = dr.registroreporteecs.pkCodigoRegistroreporteecs"
				+ " AND upper(rre.nombreRegistroreporteecs) like :nombreReporte";

		String mensajeError = "";

		try {

			Query query = Querier.query(consulta);
			query.setDate("fecha", fecha);
			query.setString("nombreReporte", nombre);

			filasVariables = query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e);
		}

		return filasVariables;
	}

	@SuppressWarnings("unchecked")
	public static List<Datoreporte> obtenerFilasVariables(String nombre) throws AplicacionException {
		List<Datoreporte> filasVariables = new ArrayList<Datoreporte>();

		logger.debug("<- metodo obtenerFilasVariables ");
		logger.debug("nombre recibido " + nombre);

		String consulta = "FROM Datoreporte AS dr"
				+ " WHERE upper(dr.registroreporteecs.nombreRegistroreporteecs) = upper(:nombreReporte)"
				+ " ORDER BY dr.hora.horaHora asc";

		String mensajeError = "";

		try {

			Query query = Querier.query(consulta);
			query.setString("nombreReporte", nombre);

			filasVariables = query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e);
		}

		return filasVariables;
	}

	/**
	 * Metodo para cargar las filas de horas por variables usado para cargar
	 * datos en el grid de registro de VP.
	 * 
	 * @param fecha
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Datoreporte> obtenerFilasHoras(Date fecha, String nombre) throws AplicacionException {
		List<Datoreporte> filasVariables = new ArrayList<Datoreporte>();

		String consulta = "SELECT dr FROM Registroreporteecs as rre, Datoreporte as dr"
				+ " WHERE rre.fechaRegistroreporteecs = :fecha"
				+ " AND rre.pkCodigoRegistroreporteecs = dr.registroreporteecs.pkCodigoRegistroreporteecs"
				+ " AND upper(rre.nombreRegistroreporteecs) like :nombreReporte";

		String mensajeError = "";

		try {

			Query query = Querier.query(consulta);
			query.setDate("fecha", fecha);
			query.setString("nombreReporte", nombre);

			filasVariables = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e);
		}

		return filasVariables;
	}
}
