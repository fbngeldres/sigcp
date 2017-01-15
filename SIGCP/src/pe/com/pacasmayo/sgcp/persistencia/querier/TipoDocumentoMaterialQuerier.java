package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoDocumentoMaterialQuerier.java
 * Modificado: Jan 7, 2010 06:10:31 PM 
 * Autor: jesus.becerra
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipodocumentomaterial;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class TipoDocumentoMaterialQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_TIPO_DOCUMENTO = "nombreTipodocumentomaterial";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Tipodocumentomaterial
	 * 
	 * @return
	 */
	public static List<Tipodocumentomaterial> getAll() throws AplicacionException {

		return Querier.getAll(Tipodocumentomaterial.class);
	}

	/**
	 * Método para obtener la lista de objectos Tipodocumentomaterial, ordenada
	 * por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Tipodocumentomaterial> getAllOrderBy(String order) {

		return Querier.getAll(Tipodocumentomaterial.class, order);
	}

	/**
	 * Método para obtener un tipo de documento material de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipodocumentomaterial getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipodocumentomaterial.class, codigo);
	}

	/**
	 * Método para obtener un tipo de documento material de la BD por el nombre.
	 * 
	 * @param nombre
	 * @throws AplicacionException
	 * @throws Exception
	 */
	public static List<Tipodocumentomaterial> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Tipodocumentomaterial.class, NOMBRE_TIPO_DOCUMENTO, nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener un tipo de documento material de la BD por el nombre.
	 * 
	 * @param nombre
	 * @throws ElementoNoEncontradoException
	 * @throws AplicacionException
	 * @throws Exception
	 */
	public static Tipodocumentomaterial findByNombreUniqueResult(String nombre) throws ElementoNoEncontradoException {

		try {
			return Querier.findByPropertyUniqueResult(Tipodocumentomaterial.class, NOMBRE_TIPO_DOCUMENTO, nombre);
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

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar un tipo de documento material en la BD.
	 * 
	 * @param tipodocumentomaterial
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Tipodocumentomaterial tipodocumentomaterial) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(tipodocumentomaterial);
	}

	/**
	 * Método para modificar un tipo de documento material de la BD.
	 * 
	 * @param tipodocumentomaterial
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Tipodocumentomaterial tipodocumentomaterial) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(tipodocumentomaterial);
	}

	/**
	 * Método para eliminar un tipo de documento material de la BD.
	 * 
	 * @param tipodocumentomaterial
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Tipodocumentomaterial tipodocumentomaterial) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(tipodocumentomaterial);
	}
}
