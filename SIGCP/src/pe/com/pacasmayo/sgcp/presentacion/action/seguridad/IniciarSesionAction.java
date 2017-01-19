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

import java.rmi.RemoteException;

import javax.servlet.http.HttpSession;
//import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.MenuLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UsuarioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.UsuarioLogic;
import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

//import com.hochschild.security.ws.client.SecurityService;
//import com.hochschild.security.ws.client.SecurityServiceServiceLocator;
//import com.hochschild.security.ws.client.User;

public class IniciarSesionAction extends AplicacionAction {

	private static final long serialVersionUID = 12121223123123L;

	private Log logger = LogFactory.getLog(this.getClass());

	private UsuarioLogicFacade usuarioLogic = new UsuarioLogic();
	private MenuLogicFacade menuLogic = new MenuLogic();

	private String login;
	private String password;
	private String mensajeError;

	public String doIniciarSesion() {
		try {
			
			setUsuario(usuarioLogic.obtenerUsuarioPorLogin(getLogin()));

			if (getUsuario() == null) {
				addActionError(getText(ERROR_USUARIO_INVALIDO));
				return ERROR;
			}

			

			HttpSession sesion = ServletActionContext.getRequest().getSession();
			sesion.setAttribute(USUARIO_SESION, getUsuario());
			
			menuLogic.configurarMenu(sesion, getUsuario());

//			// llamada al webservice
//			String address = getText(WEB_SERVICE);
//			SecurityServiceServiceLocator locator = new SecurityServiceServiceLocator(address);
//			SecurityService service = locator.getSecurityService();
//			User user = service.authenticate(getUsuario().getPersona().getCorreo(), getPassword());
//
//			if (user == null) {
//				addActionError(getText(ERROR_USUARIO_INVALIDO));
//				sesion.removeAttribute(USUARIO_SESION);
//				sesion.removeAttribute(MenuLogic.TOPMENUS);
//				sesion.invalidate();
//				return ERROR;
//			}

		} catch (LogicaException e) {
			logger.error(e);
			if (e.getMensaje().equals(ManejadorPropiedades.obtenerPropiedadPorClave(SEGURIDAD_REGISTRO_NO_ENCONTRADO)))
				addActionError(getText(ERROR_USUARIO_INVALIDO));
			return ERROR;
		} catch (RuntimeException e) {
			logger.error(e);
			if (e instanceof SesionVencidaException) {
				mensajeError = getText(SESION_VENCIDA);
				addActionError(mensajeError);
			} else if (e instanceof EntornoEjecucionException) {
				mensajeError = getText(COMUNICACION_BD_FALLO);
				addActionError(mensajeError);
			} else {
				mensajeError = getText(ERROR_FATAL_FALLO);
				addActionError(mensajeError);
			}
			return ERROR;
		}

//		catch (ServiceException e) {
//			logger.error(e);
//			logger.error(e.getMessage());
//			mensajeError = getText(ERROR_COMUNICACION_WEB_SERVICE_FALLIDA);
//			addActionError(mensajeError);
//			return ERROR;
//		} catch (RemoteException e) {
//			logger.error(e);
//			logger.error(e.getMessage());
//			mensajeError = getText(ERROR_COMUNICACION_WEB_SERVICE_FALLIDA);
//			addActionError(mensajeError);
//			return ERROR;
//		}

		return SUCCESS;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}
