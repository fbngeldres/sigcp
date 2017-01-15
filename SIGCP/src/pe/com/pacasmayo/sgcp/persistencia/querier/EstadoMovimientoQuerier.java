package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoNotificacionQuerier.java
 * Modificado: Dec 3, 2009 4:13:19 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadomovimiento;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class EstadoMovimientoQuerier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_ESTADO_MOVIMIENTO = "pkCodigoEstadomovimiento";
	private static String NOMBRE_ESTADO_MOVIMIENTO = "nombreEstadomovimiento";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Estadomovimiento
	 * 
	 * @return
	 */
	public static List<Estadomovimiento> getAll() throws AplicacionException {

		return Querier.getAll(Estadomovimiento.class);
	}

	/**
	 * Método para obtener una Estadomovimiento de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException {
	 */
	public static Estadomovimiento getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadomovimiento) Querier.getById(Estadomovimiento.class, codigo);
	}

	/**
	 * Método para obtener un tipo de documento material de la BD por el nombre.
	 * 
	 * @param nombre
	 * @throws ElementoNoEncontradoException
	 * @throws Exception
	 */
	public static Estadomovimiento findByNombreUniqueResult(String nombre) throws ElementoNoEncontradoException {

		try {
			return (Estadomovimiento) Querier
					.findByPropertyUniqueResult(Estadomovimiento.class, NOMBRE_ESTADO_MOVIMIENTO, nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, uOException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Estadomovimiento en la BD.
	 * 
	 * @param estadoMovimiento
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Estadomovimiento estadoMovimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(estadoMovimiento);
	}

	/**
	 * Metodo para modificar una Estadomovimiento de la BD.
	 * 
	 * @param estadoMovimiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Estadomovimiento estadoMovimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(estadoMovimiento);
	}

	/**
	 * Metodo para eliminar una Estadomovimiento de la BD.
	 * 
	 * @param estadoMovimiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Estadomovimiento estadoMovimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(estadoMovimiento);
	}

}
