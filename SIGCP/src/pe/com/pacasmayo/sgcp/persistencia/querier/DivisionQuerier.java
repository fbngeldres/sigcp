package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DivisionQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
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

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Division;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class DivisionQuerier extends Querier implements ConstantesMensajeAplicacion {

	/** Logger Instance */
	protected static Logger loggerQ = Logger.getLogger(DivisionQuerier.class);

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	public static String NOMBRE_DIVISION = "nombreDivision";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Division
	 * 
	 * @return
	 */
	public static List<Division> getAll() throws AplicacionException {

		return Querier.getAll(Division.class);
	}

	/**
	 * Método para obtener la lista de objetos Division, ordenados por un campo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Division> getAllOrderBy(String order) throws AplicacionException {

		try {
			return Querier.getAll(Division.class, order);
		} catch (HibernateException e) {
			loggerQ.error(e);
			throw new AplicacionException(ERROR_HIBERNATE, e);
		}

	}

	/**
	 * Método para obtener una Division de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Division getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return (Division) Querier.getById(Division.class, codigo);
	}

	/**
	 * Método para obtener una lista de Division, por medio del nombre
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Division> findByNombre(String value) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Division.class, NOMBRE_DIVISION, value);
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
	 * Metodo para insertar una Division en la BD.
	 * 
	 * @param division
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Division division) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(division);
	}

	/**
	 * Metodo para modificar una Division de la BD.
	 * 
	 * @param division
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Division division) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(division);
	}

	/**
	 * Metodo para eliminar una Division de la BD.
	 * 
	 * @param division
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Division division) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.delete(division);
	}
}
