package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoUsuarioQuerier.java
 * Modificado: Aug 11, 2011 1:22:35 PM 
 * Autor: Andrey Bottoni
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.UnresolvableObjectException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadousuario;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class EstadoUsuarioQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_ESTADO_USUARIO = "nombreEstadousuario";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Estado Usuario
	 * 
	 * @return
	 */
	public static List<Estadousuario> getAll() throws AplicacionException {
		return Querier.getAll(Estadousuario.class);
	}

	/**
	 * Método para obtener la lista de objectos Estado Usuario, ordenadas por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Estadousuario> getAllOrderBy(String order) {
		return Querier.getAll(Estadousuario.class, order);
	}

	/**
	 * Método para obtener un Estado Usuario de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadousuario getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {
		return (Estadousuario) Querier.getById(Estadousuario.class, codigo);
	}

	/**
	 * Método para obtener un Estado Usuario por el nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Estadousuario> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Estadousuario.class, NOMBRE_ESTADO_USUARIO, nombre);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar un Estado Usuario en la BD.
	 * 
	 * @param sociedad
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Estadousuario estadoUsuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(estadoUsuario);
	}

	/**
	 * Método para modificar un Estado Usuario de la BD.
	 * 
	 * @param sociedad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Estadousuario estadoUsuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(estadoUsuario);
	}

	/**
	 * Método para eliminar un Estado Usuario de la BD.
	 * 
	 * @param sociedad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Estadousuario estadoUsuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(estadoUsuario);
	}
}
