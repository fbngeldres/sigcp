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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuario;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class GrupoUsuarioQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_GRUPO_USUARIO = "nombreGrupousuario";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Grupo Usuario
	 * 
	 * @return
	 */
	public static List<Grupousuario> getAll() throws AplicacionException {
		return Querier.getAll(Grupousuario.class);
	}

	/**
	 * Método para obtener la lista de objectos Grupo Usuario, ordenadas por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Grupousuario> getAllOrderBy(String order) {
		return Querier.getAll(Grupousuario.class, order);
	}

	/**
	 * Método para obtener un Grupo Usuario de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Grupousuario getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {
		return (Grupousuario) Querier.getById(Grupousuario.class, codigo);
	}

	/**
	 * Método para obtener un Estado Usuario por el nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Grupousuario> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Grupousuario.class, NOMBRE_GRUPO_USUARIO, nombre);
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
	public static void save(Grupousuario grupoUsuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(grupoUsuario);
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
	public static void update(Grupousuario grupoUsuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(grupoUsuario);
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
	public static void delete(Grupousuario grupoUsuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(grupoUsuario);
	}
}
