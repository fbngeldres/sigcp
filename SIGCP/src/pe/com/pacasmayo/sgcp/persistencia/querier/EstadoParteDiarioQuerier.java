package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoParteDiarioQuerier.java
 * Modificado: Feb 5, 2010 9:26:39 AM 
 * Autor: gustavo.gonzalez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadopartediario;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class EstadoParteDiarioQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_ESTADO_PARTE_DIARIO = "pkCodigoEstadopartediario";

	private static String mensajeError;
	/** Logger Instance */
	protected static Logger log = Logger.getLogger(EstadoParteDiarioQuerier.class);

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Obtiene la lista de objectos Estadopartediario
	 * 
	 * @return
	 */
	public static List<Estadopartediario> getAll() throws AplicacionException {

		return Querier.getAll(Estadopartediario.class);
	}

	/**
	 * Obtiene un Estadopartediario de la BD segun su id
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException {
	 */
	public static Estadopartediario getById(Long codigo) throws ElementoNoEncontradoException {
		return Querier.getById(Estadopartediario.class, codigo);
	}

	/**
	 * Obtiene un Estadopartediario de la BD segun el nombre del estado
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException {
	 */
	public static Estadopartediario getByNombre(String nombre) throws ElementoNoEncontradoException {

		try {
			String hql = "FROM  Estadopartediario AS epd WHERE LOWER(epd.nombreEstadopartediario) = LOWER(:nombreEstado)";
			Query query = Querier.query(hql);
			query.setParameter("nombreEstado", nombre);

			return (Estadopartediario) query.uniqueResult();
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
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
}
