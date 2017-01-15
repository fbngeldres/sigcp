package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PrivilegioQuerier.java
 * Modificado: Sep 27, 2011 03:15:44 PM
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
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Privilegio;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class PrivilegioQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	public static final String NOMBRE_PRIVILEGIO = "nombrePrivilegio";
	private static String CODIGO_ESTADO_PRIVILEGIO = "estadoprivilegio.pkCodigoEstadoprivilegio";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos privilegio
	 * 
	 * @return
	 */
	public static List<Privilegio> getAll() throws AplicacionException {

		return Querier.getAll(Privilegio.class);
	}

	/**
	 * Método para obtener un privilegio de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Privilegio getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Privilegio.class, codigo);
	}

	/**
	 * Método para obtener una lista de Privilegios de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Privilegio> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Privilegio.class, NOMBRE_PRIVILEGIO, nombre);

		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Metodo para obtener una lista de privilegios por el codigo del estado del
	 * privilegio
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Privilegio> findByCodigoEstadoPrivilegio(Long value) throws AplicacionException {

		try {
			return Querier.findByProperty(Privilegio.class, CODIGO_ESTADO_PRIVILEGIO, value);
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
	 * Método para Insertar un privilegio en la BD.
	 * 
	 * @param privilegio
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Privilegio privilegio) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(privilegio);
	}

	/**
	 * Metodo para modificar un privilegio de la BD.
	 * 
	 * @param privilegio
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Privilegio privilegio) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(privilegio);
	}

	/**
	 * Método para eliminar un privilegio de la BD.
	 * 
	 * @param privilegio
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Privilegio privilegio) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(privilegio);
	}
}
