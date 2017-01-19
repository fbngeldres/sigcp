package pe.com.pacasmayo.sgcp.presentacion.action;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AplicacionAction.java
 * Modificado: Nov 26, 2009 9:40:34 AM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import pe.com.pacasmayo.sgcp.bean.CargoBean;
import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.impl.UsuarioBeanImpl;
import pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.MenuLogic.OPCIONES;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AplicacionAction extends ActionSupport implements
		ConstantesAplicacionAction, ConstantesMensajeAplicacion,
		ConstantesMensajePresentacion {

	protected UsuarioBean usuario = new UsuarioBeanImpl();
	// protected Map<String, List<OPCIONES>> mapaDeOpciones;
	//
	// protected static BloqueoTransaccionLogicFacade
	// bloqueoTransaccionLogicFacade = new BloqueoTransaccionLogic();

	private boolean funNuevoActivo = false;
	private boolean funModificarActivo = false;
	private boolean funEliminarActivo = false;
	private boolean funConsultarActivo = false;
	private boolean funAprobarActivo = false;
	private boolean funVersionarActivo = false;
	private boolean funCopiarActivo = false;
	private boolean funRevertirActivo = false;
	private boolean funGestionarActivo = false;
	private boolean funCerrarActivo = false;
	private boolean funLiberarActivo = false;
	private boolean funExportarActivo = false;
	private boolean funActivarActivo = false;
	private boolean funInactivarActivo = false;
	private boolean funVerActivo = false;
	private boolean funGenerarActivo = false;
	private boolean funCargaAutomaticActivo = false;
	private boolean funImportarActivo = false;
	private boolean funPreConsolidarActivo = false;

	private boolean funGuardarActivo = false;

	protected UsuarioBean getUsuarioSession() {
		HttpSession sesion = ServletActionContext.getRequest().getSession();
		return (UsuarioBean) sesion.getAttribute(USUARIO_SESION);
	}

	//
	// protected CargoBean getUsuarioCargo() {
	// return usuario.getPersona().getCargo();
	// }
	//
	// protected boolean esUsuarioAdmin() {
	// for (Iterator<GrupoUsuarioBean> itorGrupoUsuario =
	// usuario.getGrupoUsuarios().iterator(); itorGrupoUsuario.hasNext();) {
	// GrupoUsuarioBean grupoUsuario = itorGrupoUsuario.next();
	// if (grupoUsuario.getNombre().equals(GRUPO_USUARIO_ADMIN))
	// return true;
	// }
	// return false;
	// }
	//
	protected void asignaPrivilegios() {
		usuario = getUsuarioSession();
		asignarPrivilegios(this.getClass().getName(), usuario.getLogin());

	}
	

	private Map<String, List<OPCIONES>> getUsuarioMapaOpciones(String login) {
		HttpSession sesion = ServletActionContext.getRequest().getSession();
		return (Map<String, List<OPCIONES>>) sesion.getAttribute(MenuLogic.MAPA_OPCIONES + login);
	}
	private void asignarPrivilegios(String accion, String login) {

		Map<String, List<OPCIONES>> mapaDeOpciones = getUsuarioMapaOpciones(login);

	
		if (mapaDeOpciones.containsKey(accion)) {
			List<OPCIONES> opciones = mapaDeOpciones.get(accion);

			setFunConsultarActivo(opciones.contains(OPCIONES.CONSULTAR));
			setFunModificarActivo(opciones.contains(OPCIONES.MODIFICAR));
			setFunEliminarActivo(opciones.contains(OPCIONES.ELIMINAR));
			setFunVersionarActivo(opciones.contains(OPCIONES.VERSIONAR));
			setFunAprobarActivo(opciones.contains(OPCIONES.APROBAR));
			setFunCopiarActivo(opciones.contains(OPCIONES.COPIAR));
			setFunRevertirActivo(opciones.contains(OPCIONES.REVERTIR));
			setFunNuevoActivo(opciones.contains(OPCIONES.NUEVO));
			setFunGestionarActivo(opciones.contains(OPCIONES.GESTIONAR));
			setFunCerrarActivo(opciones.contains(OPCIONES.CERRAR));
			setFunLiberarActivo(opciones.contains(OPCIONES.LIBERAR));
			setFunExportarActivo(opciones.contains(OPCIONES.EXPORTAR));
			setFunActivarActivo(opciones.contains(OPCIONES.ACTIVAR));
			setFunInactivarActivo(opciones.contains(OPCIONES.INACTIVAR));
			setFunVerActivo(opciones.contains(OPCIONES.VER));
			setFunGenerarActivo(opciones.contains(OPCIONES.GENERAR));
			setFunCargaAutomaticActivo(opciones.contains(OPCIONES.CARGAAUTOMATICA));
			setFunImportarActivo(opciones.contains(OPCIONES.IMPORTAR));
			setFunPreConsolidarActivo(opciones.contains(OPCIONES.PRECONSOLIDAR));
			setFunGuardarActivo(opciones.contains(OPCIONES.GRABAR));
			


			/*
			 * setFunConsultarActivo(true); setFunModificarActivo(true);
			 * setFunEliminarActivo(true); setFunVersionarActivo(true);
			 * setFunAprobarActivo(true); setFunCopiarActivo(true);
			 * setFunRevertirActivo(true); setFunNuevoActivo(true);
			 * setFunGestionarActivo(true); setFunCerrarActivo(true);
			 * setFunLiberarActivo(true); setFunExportarActivo(true);
			 * setFunActivarActivo(true); setFunInactivarActivo(true);
			 * setFunVerActivo(true); setFunGenerarActivo(true);
			 * setFunCargaAutomaticActivo(true); setFunImportarActivo(true);
			 * setFunPreConsolidarActivo(true);
			 */

		}
	}

	//
	// private Map<String, List<OPCIONES>> getUsuarioMapaOpciones(String login)
	// {
	// HttpSession sesion = ServletActionContext.getRequest().getSession();
	// return (Map<String, List<OPCIONES>>)
	// sesion.getAttribute(MenuLogic.MAPA_OPCIONES + login);
	// }
	//
	// private void asignarPrivilegios(String accion, String login) {
	//
	// Map<String, List<OPCIONES>> mapaDeOpciones =
	// getUsuarioMapaOpciones(login);
	//
	//
	// if (mapaDeOpciones.containsKey(accion)) {
	// List<OPCIONES> opciones = mapaDeOpciones.get(accion);
	//
	// setFunConsultarActivo(opciones.contains(OPCIONES.CONSULTAR));
	// setFunModificarActivo(opciones.contains(OPCIONES.MODIFICAR));
	// setFunEliminarActivo(opciones.contains(OPCIONES.ELIMINAR));
	// setFunVersionarActivo(opciones.contains(OPCIONES.VERSIONAR));
	// setFunAprobarActivo(opciones.contains(OPCIONES.APROBAR));
	// setFunCopiarActivo(opciones.contains(OPCIONES.COPIAR));
	// setFunRevertirActivo(opciones.contains(OPCIONES.REVERTIR));
	// setFunNuevoActivo(opciones.contains(OPCIONES.NUEVO));
	// setFunGestionarActivo(opciones.contains(OPCIONES.GESTIONAR));
	// setFunCerrarActivo(opciones.contains(OPCIONES.CERRAR));
	// setFunLiberarActivo(opciones.contains(OPCIONES.LIBERAR));
	// setFunExportarActivo(opciones.contains(OPCIONES.EXPORTAR));
	// setFunActivarActivo(opciones.contains(OPCIONES.ACTIVAR));
	// setFunInactivarActivo(opciones.contains(OPCIONES.INACTIVAR));
	// setFunVerActivo(opciones.contains(OPCIONES.VER));
	// setFunGenerarActivo(opciones.contains(OPCIONES.GENERAR));
	// setFunCargaAutomaticActivo(opciones.contains(OPCIONES.CARGAAUTOMATICA));
	// setFunImportarActivo(opciones.contains(OPCIONES.IMPORTAR));
	// setFunPreConsolidarActivo(opciones.contains(OPCIONES.PRECONSOLIDAR));
	// setFunGuardarActivo(opciones.contains(OPCIONES.GRABAR));
	//
	//
	//
	// /*
	// * setFunConsultarActivo(true); setFunModificarActivo(true);
	// * setFunEliminarActivo(true); setFunVersionarActivo(true);
	// * setFunAprobarActivo(true); setFunCopiarActivo(true);
	// * setFunRevertirActivo(true); setFunNuevoActivo(true);
	// * setFunGestionarActivo(true); setFunCerrarActivo(true);
	// * setFunLiberarActivo(true); setFunExportarActivo(true);
	// * setFunActivarActivo(true); setFunInactivarActivo(true);
	// * setFunVerActivo(true); setFunGenerarActivo(true);
	// * setFunCargaAutomaticActivo(true); setFunImportarActivo(true);
	// * setFunPreConsolidarActivo(true);
	// */
	//
	// }
	// }
	//
	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public void limpiarMensajesyErrores() {
		Collection<Object> errorMessages = new ArrayList<Object>();
		Collection<Object> messages = new ArrayList<Object>();
		setActionErrors(errorMessages);
		setActionMessages(messages);
	}

	/**
	 * @return the funModificarActivo
	 */
	public boolean isFunModificarActivo() {
		return funModificarActivo;
	}

	/**
	 * @param funModificarActivo
	 *            the funModificarActivo to set
	 */
	public void setFunModificarActivo(boolean funModificarActivo) {
		this.funModificarActivo = funModificarActivo;
	}

	/**
	 * @return the funEliminarActivo
	 */
	public boolean isFunEliminarActivo() {
		return funEliminarActivo;
	}

	/**
	 * @param funEliminarActivo
	 *            the funEliminarActivo to set
	 */
	public void setFunEliminarActivo(boolean funEliminarActivo) {
		this.funEliminarActivo = funEliminarActivo;
	}

	/**
	 * @return the funConsultarActivo
	 */
	public boolean isFunConsultarActivo() {
		return funConsultarActivo;
	}

	/**
	 * @param funConsultarActivo
	 *            the funConsultarActivo to set
	 */
	public void setFunConsultarActivo(boolean funConsultarActivo) {
		this.funConsultarActivo = funConsultarActivo;
	}

	/**
	 * @return the funAprobarActivo
	 */
	public boolean isFunAprobarActivo() {
		return funAprobarActivo;
	}

	/**
	 * @param funAprobarActivo
	 *            the funAprobarActivo to set
	 */
	public void setFunAprobarActivo(boolean funAprobarActivo) {
		this.funAprobarActivo = funAprobarActivo;
	}

	/**
	 * @return the funVersionarActivo
	 */
	public boolean isFunVersionarActivo() {
		return funVersionarActivo;
	}

	/**
	 * @param funVersionarActivo
	 *            the funVersionarActivo to set
	 */
	public void setFunVersionarActivo(boolean funVersionarActivo) {
		this.funVersionarActivo = funVersionarActivo;
	}

	/**
	 * @return the funCopiarActivo
	 */
	public boolean isFunCopiarActivo() {
		return funCopiarActivo;
	}

	/**
	 * @param funCopiarActivo
	 *            the funCopiarActivo to set
	 */
	public void setFunCopiarActivo(boolean funCopiarActivo) {
		this.funCopiarActivo = funCopiarActivo;
	}

	/**
	 * Asigna el valor TRUE a la variable de sesión EXITO_OPERACION
	 */
	protected void setExitoOperacion() {

		Map<String, Object> session = ActionContext.getContext().getSession();

		session.put(EXITO_OPERACION, Boolean.TRUE);
	}

	/**
	 * Verifica si la operacion se realizó con exito. En caso de ser verdadero,
	 * agrega un mensaje de exito a los ActionMessages
	 */
	protected void verificarExitoOperacion() {

		Map<String, Object> session = ActionContext.getContext().getSession();

		if (Boolean.TRUE.equals(session.get(EXITO_OPERACION))) {

			addActionMessage(ManejadorPropiedades
					.obtenerPropiedadPorClave(EXITO_OPERACION_REALIZADA));

			session.remove(EXITO_OPERACION);
		}
	}
	
	protected boolean esUsuarioAdmin() {
//		for (Iterator<GrupoUsuarioBean> itorGrupoUsuario = usuario.getGrupoUsuarios().iterator(); itorGrupoUsuario.hasNext();) {
//			GrupoUsuarioBean grupoUsuario = itorGrupoUsuario.next();
//			if (grupoUsuario.getNombre().equals(GRUPO_USUARIO_ADMIN))
//				return true;
//		}
		return false;
	}

	protected CargoBean getUsuarioCargo() {
		return usuario.getPersona().getCargo();
	}

	// PUBLIC VOID VALIDARTRANSACCION(STRING TRANSACCION, LONG LINEANEGOCIO)
	// THROWS LOGICAEXCEPTION {
	// STRING MENSAJEERROR = "";
	// BLOQUEOTRANSACCIONBEAN BLOQUEOTRANSACCIONBEAN = NULL;
	// TRY {
	//
	// BLOQUEOTRANSACCIONBEAN =
	// BLOQUEOTRANSACCIONLOGICFACADE.OBTENERTRANSACCION(TRANSACCION,
	// LINEANEGOCIO);
	//
	// } CATCH (LOGICAEXCEPTION E1) {
	//
	// THROW NEW LOGICAEXCEPTION(E1.GETMENSAJE());
	// }
	//
	// IF (BLOQUEOTRANSACCIONBEAN == NULL) {
	// MENSAJEERROR = "NO SE ENCUENTRA NOMBRE DE LA TRANSACCI\U00F3N,
	// COMUNIQUESE CON SU ADMINISTRADOR";
	// THROW NEW LOGICAEXCEPTION(MENSAJEERROR);
	// }
	//
	// IF (!BLOQUEOTRANSACCIONBEAN.GETTRANSACCIONHABILITADA()) {
	// MENSAJEERROR = "LA TRANSACCI\U00F3N SE ENCUENTRA EN USO, POR FAVOR
	// ESPERE";
	// THROW NEW LOGICAEXCEPTION(MENSAJEERROR);
	// }
	//
	// TRY {
	// BLOQUEOTRANSACCIONBEAN.SETUSUARIOBEAN(GETUSUARIOSESSION());
	// BLOQUEOTRANSACCIONBEAN.SETFECHATRANSACCION(FECHAUTIL.GETCURRENTTIMESTAMP());
	// BLOQUEOTRANSACCIONBEAN.SETTRANSACCIONHABILITADA(FALSE);
	// BLOQUEOTRANSACCIONLOGICFACADE.MODIFICARTRANSACCION(BLOQUEOTRANSACCIONBEAN);
	// } CATCH (LOGICAEXCEPTION E1) {
	// MENSAJEERROR = "NO SE PUDO REALIZAR EL BLOQUEO DE LA TRANSACCI\U00F3N,
	// COMUNIQUESE CON EL ADMINISTRADOR";
	// THROW NEW LOGICAEXCEPTION(MENSAJEERROR);
	// }
	//
	// }
	//
	// PUBLIC VOID LIBERARTRANSACCION(STRING TRANSACCION, LONG LINEANEGOCIO)
	// THROWS APLICACIONGLOBALEXCEPTION {
	//
	// STRING MENSAJEERROR = "";
	// TRY {
	// BLOQUEOTRANSACCIONBEAN BLOQUEOTRANSACCIONBEAN = NULL;
	//
	// BLOQUEOTRANSACCIONBEAN =
	// BLOQUEOTRANSACCIONLOGICFACADE.OBTENERTRANSACCION(TRANSACCION,
	// LINEANEGOCIO);
	// BLOQUEOTRANSACCIONBEAN.SETUSUARIOBEAN(GETUSUARIOSESSION());
	// BLOQUEOTRANSACCIONBEAN.SETFECHATRANSACCION(FECHAUTIL.GETCURRENTTIMESTAMP());
	// BLOQUEOTRANSACCIONBEAN.SETTRANSACCIONHABILITADA(TRUE);
	// BLOQUEOTRANSACCIONLOGICFACADE.MODIFICARTRANSACCION(BLOQUEOTRANSACCIONBEAN);
	// } CATCH (LOGICAEXCEPTION E1) {
	//
	// MENSAJEERROR = "NO SE PUDO REALIZAR EL DESBLOQUEO DE LA TRANSACCI\U00F3N,
	// COMUNIQUESE CON EL ADMINISTRADOR";
	// ADDACTIONERROR(MENSAJEERROR);
	// THROW NEW APLICACIONGLOBALEXCEPTION(MENSAJEERROR);
	// }
	//
	// }

	public boolean isFunNuevoActivo() {
		return funNuevoActivo;
	}

	public void setFunNuevoActivo(boolean funNuevoActivo) {
		this.funNuevoActivo = funNuevoActivo;
	}

	public boolean isFunRevertirActivo() {
		return funRevertirActivo;
	}

	public void setFunRevertirActivo(boolean funRevertirActivo) {
		this.funRevertirActivo = funRevertirActivo;
	}

	public boolean isFunGestionarActivo() {
		return funGestionarActivo;
	}

	public void setFunGestionarActivo(boolean funGestionarActivo) {
		this.funGestionarActivo = funGestionarActivo;
	}

	public boolean isFunCerrarActivo() {
		return funCerrarActivo;
	}

	public void setFunCerrarActivo(boolean funCerrarActivo) {
		this.funCerrarActivo = funCerrarActivo;
	}

	public boolean isFunLiberarActivo() {
		return funLiberarActivo;
	}

	public void setFunLiberarActivo(boolean funLiberarActivo) {
		this.funLiberarActivo = funLiberarActivo;
	}

	public boolean isFunExportarActivo() {
		return funExportarActivo;
	}

	public void setFunExportarActivo(boolean funExportarActivo) {
		this.funExportarActivo = funExportarActivo;
	}

	public boolean isFunActivarActivo() {
		return funActivarActivo;
	}

	public void setFunActivarActivo(boolean funActivarActivo) {
		this.funActivarActivo = funActivarActivo;
	}

	public boolean isFunInactivarActivo() {
		return funInactivarActivo;
	}

	public void setFunInactivarActivo(boolean funInactivarActivo) {
		this.funInactivarActivo = funInactivarActivo;
	}

	public boolean isFunVerActivo() {
		return funVerActivo;
	}

	public void setFunVerActivo(boolean funVerActivo) {
		this.funVerActivo = funVerActivo;
	}

	public boolean isFunGenerarActivo() {
		return funGenerarActivo;
	}

	public void setFunGenerarActivo(boolean funGenerarActivo) {
		this.funGenerarActivo = funGenerarActivo;
	}

	public boolean isFunCargaAutomaticActivo() {
		return funCargaAutomaticActivo;
	}

	public void setFunCargaAutomaticActivo(boolean funCargaAutomaticActivo) {
		this.funCargaAutomaticActivo = funCargaAutomaticActivo;
	}

	public boolean isFunImportarActivo() {
		return funImportarActivo;
	}

	public void setFunImportarActivo(boolean funImportarActivo) {
		this.funImportarActivo = funImportarActivo;
	}

	public boolean isFunPreConsolidarActivo() {
		return funPreConsolidarActivo;
	}

	public void setFunPreConsolidarActivo(boolean funPreConsolidarActivo) {
		this.funPreConsolidarActivo = funPreConsolidarActivo;
	}

	public boolean isFunGuardarActivo() {
		return funGuardarActivo;
	}

	public void setFunGuardarActivo(boolean funGuardarActivo) {
		this.funGuardarActivo = funGuardarActivo;
	}

}
