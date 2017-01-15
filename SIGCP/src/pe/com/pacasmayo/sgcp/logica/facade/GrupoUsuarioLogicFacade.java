package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuario;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GrupoUsuarioLogicFacade.java
 * Modificado: Aug 11, 2011 1:22:35 PM 
 * Autor: Andrey Bottoni
 *
 * Copyright (C) DBAccess, 2011. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface GrupoUsuarioLogicFacade {

	/**
	 * @param codigoGrupoUsuario
	 * @return
	 * @throws LogicaException
	 */
	public GrupoUsuarioBean obtenerGrupoUsuario(Long codigoGrupoUsuario) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public List<GrupoUsuarioBean> obtenerGrupos() throws LogicaException;

	/**
	 * @param grupoBean
	 * @return
	 */
	public Grupousuario obtenerGrupoUsuarioDTO(GrupoUsuarioBean grupoBean);

}
