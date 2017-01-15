package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMovimientoQuerier.java
 * Modificado: Jan 7, 2010 06:10:31 PM 
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
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocapacidadoperativa;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class TipoCapacidadOperativaQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Tipomovimiento
	 * 
	 * @return
	 */
	public static List<Tipocapacidadoperativa> getAll() throws AplicacionException {

		return Querier.getAll(Tipocapacidadoperativa.class);
	}

	/**
	 * Método para obtener la lista de objectos Tipomovimiento, ordenada por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Tipocapacidadoperativa> getAllOrderBy(String order) {

		return Querier.getAll(Tipocapacidadoperativa.class, order);
	}

	/**
	 * Método para obtener un Tipo de Movimiento de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipocapacidadoperativa getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return Querier.getById(Tipocapacidadoperativa.class, codigo);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar un Tipo de movimiento en la BD.
	 * 
	 * @param tipoMovimiento
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Tipocapacidadoperativa tipocapacidadoperativa) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(tipocapacidadoperativa);
	}

	/**
	 * Método para modificar un tipo de movimiento de la BD.
	 * 
	 * @param tipoMovimiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Tipocapacidadoperativa tipocapacidadoperativa) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(tipocapacidadoperativa);
	}

	/**
	 * Método para eliminar un tipo de movimiento de la BD.
	 * 
	 * @param tipoMovimiento
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Tipocapacidadoperativa tipocapacidadoperativa) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(tipocapacidadoperativa);
	}
}
