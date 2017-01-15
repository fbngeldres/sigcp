package pe.com.pacasmayo.sgcp.presentacion.action.seguridad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: IniciarSesionAction.java
 * Modificado: Nov 26, 2009 3:49:23 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;

public class PrincipalAction extends AplicacionAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws AplicacionGlobalException {
		return SUCCESS;
	}

}
