package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import javax.servlet.http.HttpSession;

import pe.com.pacasmayo.sgcp.bean.MenuBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MenuLogicFacade.java
 * Modificado: Sep 28, 2011 3:12:41 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface MenuLogicFacade {

	public abstract void configurarMenu(HttpSession sesion, UsuarioBean usuario)
			throws LogicaException;

}
