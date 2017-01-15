package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadousuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Persona;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuariogrupousuario;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class UsuarioQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static String LOGIN = "loginUsuario";
	private static String PASSWORD = "passwordUsuario";
	private static String CODIGO_ESTADO_USUARIO = "estadousuario.pkCodigoEstadousuario";
	private static String CODIGO_GRUPO_USUARIO = "grupousuario.pkCodigoGrupousuario";
	private static String PK_CODIGO_PERSONA = "persona.pkCodigoPersona";
	private static String PK_CODIGO_USUARIO = "pkCodigoUsuario";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Usuario
	 * 
	 * @return
	 */
	public static List<Usuario> getAll() throws AplicacionException {

		return Querier.getAll(Usuario.class);
	}

	/**
	 * Método para obtener una Usuario de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Usuario getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return Querier.getById(Usuario.class, codigo);
	}

	/**
	 * Método para obtener una Usuario de la BD por login
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static Usuario getByLogin(String login) throws ElementoNoEncontradoException, EntornoEjecucionException,
			SesionVencidaException {

		Usuario usuario = Querier.findByPropertyUniqueResult(Usuario.class, LOGIN, login);
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public static Usuario getByLoginPassword(String login, String password) throws ElementoNoEncontradoException {
		Usuario usuario = Querier.findByProperties(Usuario.class, LOGIN, login, PASSWORD, password);
		if (usuario == null) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			throw new ElementoNoEncontradoException(mensajeError, null);
		}

		return usuario;
	}

	/**
	 * Obtener Usuario dado un Nombre o Apellido
	 * 
	 * @param valor
	 * @param opcion
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Usuario> getByNombreoApellido(String valor, String opcion) throws ElementoNoEncontradoException {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Persona> listaPersonas = Querier.findByProperty(Persona.class, opcion, valor);
		for (Iterator<Persona> it = listaPersonas.iterator(); it.hasNext();) {
			Persona persona = (Persona) it.next();
			long codigoPersona = persona.getPkCodigoPersona();
			List<Usuario> usuario = Querier.findByProperty(Usuario.class, PK_CODIGO_PERSONA, codigoPersona);
			listaUsuarios.addAll(usuario);
		}
		if (listaUsuarios == null) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			throw new ElementoNoEncontradoException(mensajeError, null);
		}

		return listaUsuarios;
	}

	/**
	 * Obtener Usuario dado un Estado
	 * 
	 * @param valor
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Usuario> getByEstado(String valor) throws ElementoNoEncontradoException {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Estadousuario estado = EstadoUsuarioQuerier.getById(Long.parseLong(valor));
		long codigoEstado = estado.getPkCodigoEstadousuario();
		List<Usuario> usuario = Querier.findByProperty(Usuario.class, CODIGO_ESTADO_USUARIO, codigoEstado);
		listaUsuarios.addAll(usuario);

		if (listaUsuarios == null) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			throw new ElementoNoEncontradoException(mensajeError, null);
		}

		return listaUsuarios;
	}

	/**
	 * Obtener Usuario dado un Grupo
	 * 
	 * @param valor
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Usuario> getByGrupo(String valor) throws ElementoNoEncontradoException {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Grupousuario grupo = GrupoUsuarioQuerier.getById(Long.parseLong(valor));
		long codigoGrupo = grupo.getPkCodigoGrupousuario();
		List<Usuariogrupousuario> listaUsuarioGU = Querier.findByProperty(Usuariogrupousuario.class, CODIGO_GRUPO_USUARIO,
				codigoGrupo);
		for (Iterator<Usuariogrupousuario> it = listaUsuarioGU.iterator(); it.hasNext();) {
			Usuariogrupousuario usuarioGU = (Usuariogrupousuario) it.next();
			long codigoUsuario = usuarioGU.getUsuario().getPkCodigoUsuario();
			List<Usuario> usuario = Querier.findByProperty(Usuario.class, PK_CODIGO_USUARIO, codigoUsuario);
			listaUsuarios.addAll(usuario);
		}

		if (listaUsuarios == null) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			throw new ElementoNoEncontradoException(mensajeError, null);
		}

		return listaUsuarios;
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Usuario en la BD.
	 * 
	 * @param usuario
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Usuario usuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(usuario);
	}

	/**
	 * Metodo para modificar una Usuario de la BD.
	 * 
	 * @param usuario
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Usuario usuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(usuario);
	}

	/**
	 * Metodo para eliminar una Usuario de la BD.
	 * 
	 * @param usuario
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Usuario usuario) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(usuario);
	}
}