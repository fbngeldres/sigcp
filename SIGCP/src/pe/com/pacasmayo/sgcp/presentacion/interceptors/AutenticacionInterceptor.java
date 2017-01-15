package pe.com.pacasmayo.sgcp.presentacion.interceptors;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: AutenticacionInterceptor.java
 * Modificado: Jan 29, 2010 8:43:21 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AutenticacionInterceptor extends AbstractInterceptor /*
																 * implements
																 * Interceptor,
																 * ConstantesAplicacionAction
																 * ,
																 * ConstantesMensajeAplicacion
																 */{

	/**
	 * 
	 */
	private Log logger = LogFactory.getLog(this.getClass());
	private static final long serialVersionUID = -3358854616445009914L;
	private static final String LOGIN_USER = "login";
	private static final String PASS_USER = "password";

	/**
	 * 
	 */
	public void destroy() {

	}

	/**
	 * 
	 */
	public void init() {

	}

	/**
	 * Implementaci�n del m�todo intercept de la interfase Interceptor para la
	 * autenticaci�n y autorizacion del ususario en la aplicaci�n
	 * 
	 * @param accionInvocada
	 * @return resultado de verificaci�n de ususario
	 */
	public String intercept(ActionInvocation accionInvocada) throws Exception {
		// 1. Eliminar mensajes y errores existentes
		// ((AplicacionAction)
		// accionInvocada.getAction()).limpiarMensajesyErrores();

		// 2. Obtiene el contexto de acci�n desde la invocaci�n y accede
		// a los objetos HttpServletRequest y HttpSession.
		// final ActionContext contexto = accionInvocada.getInvocationContext();
		// HttpServletRequest solicitud = (HttpServletRequest)
		// contexto.get(HTTP_REQUEST);
		// HttpSession sesion = solicitud.getSession(true);
		// String loginUser = solicitud.getParameter(LOGIN_USER);
		// String passUser = solicitud.getParameter(PASS_USER);
		//
		// if (!usuarioInicioSesion(sesion)) {
		//
		// logger.info("El usuario no se encuentra en sesion" +
		// accionInvocada.getInvocationContext().getName());
		//
		// if (StringUtils.isBlank(loginUser) || StringUtils.isBlank(passUser))
		// {
		// // addActionError(accionInvocada, ManejadorPropiedades
		// // .obtenerPropiedadPorClave(ERROR_LOGIN_PASSWORD_REQUERIDO));
		// return REDIRECCION_GLOBAL_LOGIN;
		// }
		//
		// }
		//
		// logger.info("Se redirecciona al action solicitado  " +
		// accionInvocada.getInvocationContext().getName());
		// return accionInvocada.invoke();
		return accionInvocada.invoke();
	}

	/**
	 * M�todo que permite establcer si el ususario ha iniciado o no una sesion
	 * 
	 * @param sesion
	 * @return
	 */

	public boolean usuarioInicioSesion(HttpSession sesion) {
		// UsuarioBean usuario = (UsuarioBean)
		// sesion.getAttribute(USUARIO_SESION);
		//
		// if (usuario == null) {
		// return false;
		// }

		return true;
	}

	/**
	 * @param invocation
	 * @param message
	 */
	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if (action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}

}
