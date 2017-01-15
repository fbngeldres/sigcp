package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioGrupoUsuarioQuerier.java
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

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuariogrupousuario;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class UsuarioGrupoUsuarioQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Cargo
	 * 
	 * @return
	 */
	public static List<Usuariogrupousuario> getAll() throws AplicacionException {
		return Querier.getAll(Usuariogrupousuario.class);
	}

	/**
	 * Método para obtener la lista de objectos Cargo, ordenadas por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Usuariogrupousuario> getAllOrderBy(String order) {
		return Querier.getAll(Usuariogrupousuario.class, order);
	}

	/**
	 * Método para obtener un UsuarioGrupoUsuario de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Usuariogrupousuario getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {
		return (Usuariogrupousuario) Querier.getById(Usuariogrupousuario.class, codigo);
	}

	/**
	 * Método para obtener una UsuarioGrupoUsuario de la BD por el Codigo de
	 * Usuario
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static Usuariogrupousuario getByUsuario(Usuario usuario) throws AplicacionException {

		try {
			List<Usuariogrupousuario> listaUsuarioGrupo = Querier.findByProperty(Usuariogrupousuario.class, "usuario", usuario);
			if (listaUsuarioGrupo.isEmpty())
				return null;
			return (listaUsuarioGrupo.get(0));
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
	 * Método para insertar un Cargo en la BD.
	 * 
	 * @param usuarioGrupo
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Usuariogrupousuario usuarioGrupo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(usuarioGrupo);
	}

	/**
	 * Método para modificar un Cargo de la BD.
	 * 
	 * @param usuarioGrupo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Usuariogrupousuario usuarioGrupo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(usuarioGrupo);
	}

	/**
	 * Método para eliminar un Cargo de la BD.
	 * 
	 * @param usuarioGrupo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Usuariogrupousuario usuarioGrupo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(usuarioGrupo);
	}
}
