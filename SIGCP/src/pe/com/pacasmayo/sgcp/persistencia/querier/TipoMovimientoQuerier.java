package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: TipoMovimientoQuerier.java
 * Modificado: Jan 7, 2010 06:10:31 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomovimiento;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class TipoMovimientoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String CODIGO_SAP = "codigoSapTipomovimiento";
	private static String NOMBRE_TIPO_MOVIMIENTO = "nombreTipomovimiento";
	private static String CODIGO_CLASIFICACION = "clasificaciontipomovimiento.pkCodigoClasificaciontipomovi";
	private static String NOMBRE_CLASIFICACION = "clasificaciontipomovimiento.nombreClasificaciontipomovimie";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Tipomovimiento
	 * 
	 * @return
	 */
	public static List<Tipomovimiento> getAll() throws AplicacionException {

		return Querier.getAll(Tipomovimiento.class);
	}

	/**
	 * Método para obtener la lista de objectos Tipomovimiento, ordenada por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Tipomovimiento> getAllOrderBy(String order) {

		return Querier.getAll(Tipomovimiento.class, order);
	}

	/**
	 * Método para obtener un Tipo de Movimiento de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipomovimiento getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipomovimiento.class, codigo);
	}

	/**
	 * Método para obtener una sociedad por el código SAP
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipomovimiento findByCodigoSAP(String codigoSAP) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Tipomovimiento.class, CODIGO_SAP, codigoSAP);
	}

	/**
	 * Método para obtener un Tipo de Movimiento de la BD por el nombre.
	 * 
	 * @param nombre
	 * @throws ElementoNoEncontradoException
	 * @throws AplicacionException
	 * @throws Exception
	 */
	public static List<Tipomovimiento> findByNombre(String nombre) throws ElementoNoEncontradoException {

		try {
			return Querier.getByLikeStringPropertie(Tipomovimiento.class, NOMBRE_TIPO_MOVIMIENTO, nombre);
		} catch (UnresolvableObjectException uOException) {
			logger.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(ERROR_HIBERNATE, hException);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener un Tipo de Movimiento de la BD por medio del código
	 * de la Clasificación.
	 * 
	 * @param nombre
	 * @throws AplicacionException
	 * @throws Exception
	 */
	public static List<Tipomovimiento> findByClasificacionTipoMovimiento(Long codigoClasificacionTipoMovimiento)
			throws AplicacionException {

		try {
			return Querier.findByProperty(Tipomovimiento.class, CODIGO_CLASIFICACION, codigoClasificacionTipoMovimiento);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener un Tipo de Movimiento de la BD por medio del nombre
	 * de la Clasificación.
	 * 
	 * @param nombre
	 * @throws AplicacionException
	 * @throws Exception
	 */
	public static List<Tipomovimiento> findByClasificacionTipoMovimientoNombre(String nombreClasificacionTipoMovimiento)
			throws AplicacionException {

		try {
			return Querier.findByProperty(Tipomovimiento.class, NOMBRE_CLASIFICACION, nombreClasificacionTipoMovimiento);
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
	 * Método para insertar un Tipo de movimiento en la BD.
	 * 
	 * @param tipoMovimiento
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Tipomovimiento tipoMovimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(tipoMovimiento);
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
	public static void update(Tipomovimiento tipoMovimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(tipoMovimiento);
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
	public static void delete(Tipomovimiento tipoMovimiento) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(tipoMovimiento);
	}
}
