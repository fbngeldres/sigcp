package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioLogicFacade.java
 * Modificado: Jan 25, 2010 8:40:41 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Persona;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuariogrupousuario;

public interface UsuarioLogicFacade {

	/**
	 * @param codigoUsuario
	 * @return
	 * @throws LogicaException
	 */
	public abstract UsuarioBean obtenerUsuario(Long codigoUsuario) throws LogicaException;

	/**
	 * @param codigoUsuario
	 * @return
	 * @throws LogicaException
	 */
	public abstract Usuario obtenerUsuarioDataObject(Long codigoUsuario) throws LogicaException;


	/**
	 * @param login
	 * @return
	 * @throws LogicaException
	 */
	public UsuarioBean obtenerUsuarioPorLogin(String login) throws LogicaException;

	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public List<UsuarioBean> obtenerUsuariosPorNombreoApellido(String nombre, String opcion) throws LogicaException;

	/**
	 * @param valor
	 * @return
	 * @throws LogicaException
	 */
	public List<UsuarioBean> obtenerUsuariosPorEstado(String valor) throws LogicaException;

	/**
	 * @param valor
	 * @return
	 * @throws LogicaException
	 */
	public List<UsuarioBean> obtenerUsuariosPorGrupo(String valor) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @return
	 * @throws LogicaException
	 */
	public List<UsuarioBean> obtenerUsuarios() throws LogicaException;

	/**
	 * @param usuario
	 * @throws LogicaException
	 */
	public void ingresarUsuario(Usuario usuario) throws LogicaException;

	/**
	 * @param usuario
	 * @param persona
	 * @param usuarioGrupo
	 * @param usuarioAuditoria
	 * @throws LogicaException
	 */
	public void ingresarDatosUsuario(Usuario usuario, Persona persona, Usuariogrupousuario usuarioGrupo,
			UsuarioBean usuarioAuditoria) throws LogicaException;

	/**
	 * @param usuarioBean
	 * @throws AplicacionException
	 */
	public void eliminarUsuario(UsuarioBean usuarioBean, UsuarioBean usuarioAuditoria) throws LogicaException;

	/**
	 * @param usuarioBean
	 * @param usuarioAuditoria
	 */
	public abstract void actualizar(UsuarioBean usuarioBean, UsuarioBean usuarioAuditoria) throws LogicaException;
}
