package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioPrivilegioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GrupoUsuarioPrivilegioLogicFacade.java
 * Modificado: Apr 19, 2010 6:06:41 PM 
 * Autor: Ignacio
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface GrupoUsuarioPrivilegioLogicFacade {

	/**
	 * @param codigoGrupoUsuario
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<GrupoUsuarioPrivilegioBean> getByGrupoUsuario(Long codigoGrupoUsuario) throws LogicaException;

}
