package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AlmacenQuerier.java
 * Modificado: Dec 22, 2009 2:36:53 PM 
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Almacen;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class AlmacenQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String CODIGO_TIPO_MOVIMIENTO = "tipoMovimieno.pkCodigoTipomovimiento";
	private static String CODIGO_UNIDAD = "unidad.pkCodigoUnidad";
	private static final String NOMBRE_ALMACEN = "nombreAlmacen";
	private static final String CODIGO_SAP = "codigoSapAlmacen";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos AlmacenBean
	 * 
	 * @return
	 */
	public static List<Almacen> getAll() throws AplicacionException {

		return Querier.getAll(Almacen.class);
	}

	/**
	 * Método para obtener todos los Almacenes, ordenadas por un atributo
	 * 
	 * @param order
	 * @return Lista de Objetos de AlmacenBean ordenados
	 */
	public static List<Almacen> getAllOrderBy(String order) {

		return Querier.getAll(Almacen.class, order);
	}

	/**
	 * Método para obtener un Almacén de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Almacen getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Almacen.class, codigo);
	}

	/**
	 * Método para obtener una lista de Almacenes por el codigo de la Unidad
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Almacen> findByCodigoUnidad(Long value) throws AplicacionException {

		try {
			return Querier.findByProperty(Almacen.class, CODIGO_UNIDAD, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de AlmacenBean por el codigo del tipo de
	 * movimiento
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Almacen> findByCodigoTipoMovimiento(Long value) throws AplicacionException {

		try {
			return Querier.findByProperty(Almacen.class, CODIGO_TIPO_MOVIMIENTO, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una sociedad por el codigo SAP
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Almacen findByCodigoSAP(String codigoSAP) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Almacen.class, CODIGO_SAP, codigoSAP);
	}

	/**
	 * Método para obtener las AlmacenBean de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Almacen> findByNombre(String nombre) throws ElementoNoEncontradoException, EntornoEjecucionException {
		try {
			return Querier.getByLikeStringPropertie(Almacen.class, NOMBRE_ALMACEN, nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new EntornoEjecucionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un Almacén en la BD.
	 * 
	 * @param almacen
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Almacen almacen) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(almacen);
	}

	/**
	 * Método para modificar un Almacén de la BD.
	 * 
	 * @param almacen
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Almacen almacen) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(almacen);
	}

	/**
	 * Método para Eliminar un Almacén de la BD.
	 * 
	 * @param almacen
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Almacen almacen) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(almacen);
	}
}
