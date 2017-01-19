package pe.com.pacasmayo.sgcp.presentacion.action.seguridad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: CerrarSesionAction.java
 * Modificado: Nov 30, 2009 8:01:12 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;

import com.opensymphony.xwork2.Action;

public class CerrarSesionAction extends AplicacionAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(this.getClass());

	public String cerrarSesion() {
		HttpSession sesion = ServletActionContext.getRequest().getSession();
		sesion.setMaxInactiveInterval(10);
		UsuarioBean usuario = (UsuarioBean) sesion.getAttribute(USUARIO_SESION);
		sesion.removeAttribute(USUARIO_SESION);
		sesion.removeAttribute(MenuLogic.TOPMENUS + usuario.getLogin());
		sesion.removeAttribute(MenuLogic.MAPA_OPCIONES + usuario.getLogin());

		logger.debug("Se removieron los parametros de la sesion");

		return Action.SUCCESS;
	}

	public String salirSesion() {

		return Action.SUCCESS;
	}

}
