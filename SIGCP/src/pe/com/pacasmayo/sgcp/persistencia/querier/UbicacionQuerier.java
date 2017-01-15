package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UbicacionQuerier.java
 * Modificado: Jan 9, 2010 10:38:51 AM 
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ubicacion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class UbicacionQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_UBICACION = "nombreUbicacion";
	private static final String CODIGO_ALMACEN = "almacen.pkCodigoAlmacen";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Ubicacion
	 * 
	 * @return
	 */
	public static List<Ubicacion> getAll() throws AplicacionException {

		return Querier.getAll(Ubicacion.class);
	}

	/**
	 * Método para obtener la lista de objectos Ubicacion
	 * 
	 * @return
	 */
	public static List<Ubicacion> getAllOrderBy(String order) {

		return Querier.getAll(Ubicacion.class, order);
	}

	/**
	 * Método para modificar una Ubicación de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 * @throws Exception
	 */
	public static Ubicacion getById(Long codigo) throws ElementoNoEncontradoException {

		Ubicacion ubicacion = Querier.getById(Ubicacion.class, codigo);
		return ubicacion;
	}

	/**
	 * Método para obtener las Ubicaciones de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws AplicacionException
	 */
	public static List<Ubicacion> findByNombre(String nombre) throws ElementoNoEncontradoException {

		try {
			return Querier.getByLikeStringPropertie(Ubicacion.class, NOMBRE_UBICACION, nombre);
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
	 * Metodo para obtener una lista de Ubicacion por el codigo del almacén
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Ubicacion> findByCodigoAlmacen(Long value) throws AplicacionException {

		try {
			return Querier.findByProperty(Ubicacion.class, CODIGO_ALMACEN, value);
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
	 * Método para persistir una entidad Ubicacion.
	 * 
	 * @param ubicacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Ubicacion ubicacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(ubicacion);

	}

	/**
	 * Método para modificar una Ubicacion de la BD.
	 * 
	 * @param ubicacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Ubicacion ubicacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(ubicacion);
	}

	/**
	 * Método para Eliminar una Ubicacion.
	 * 
	 * @param ubicacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Ubicacion ubicacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(ubicacion);
	}
}
