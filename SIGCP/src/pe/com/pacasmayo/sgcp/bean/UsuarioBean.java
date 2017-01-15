package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioBean.java
 * Modificado: Jan 21, 2010 1:34:47 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBeanImpl;

public interface UsuarioBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract EstadoUsuarioBean getEstadoUsuario();

	public abstract void setEstadoUsuario(EstadoUsuarioBean estadoUsuario);

	public abstract PersonaBean getPersona();

	public abstract void setPersona(PersonaBean persona);

	public abstract String getLogin();

	public abstract void setLogin(String login);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract Set<OrdenProduccionBean> getOrdenProduccionUsuarioRegistro();

	public abstract void setOrdenProduccionUsuarioRegistro(Set<OrdenProduccionBean> ordenProduccionUsuarioRegistro);

	public abstract Set<OrdenProduccionBean> getOrdenProduccionUsuarioAprueba();

	public abstract void setOrdenProduccionUsuarioAprueba(Set<OrdenProduccionBean> ordenProduccionUsuarioAprueba);

	

	public abstract Set<PlanAnualBean> getPlanesAnualesUsuarioRegistra();

	public abstract void setPlanesAnualesUsuarioRegistra(Set<PlanAnualBean> planesAnualesUsuarioRegistra);

	public abstract Set<PlanAnualBean> getPlanesAnualesUsuarioAprueba();

	public abstract void setPlanesAnualesUsuarioAprueba(Set<PlanAnualBean> planesAnualesUsuarioAprueba);

	public abstract Set<NotificacionDiariaBeanImpl> getNotificacionesDiarias();

	public abstract void setNotificacionesDiarias(Set<NotificacionDiariaBeanImpl> notificacionesDiarias);

	public abstract Set<GrupoUsuarioBean> getGrupoUsuarios();

	public abstract void setGrupoUsuarios(Set<GrupoUsuarioBean> grupoUsuarios);

	public abstract String getNombreCompleto();

	public abstract GrupoUsuarioBean getGrupoUsuario();

	public abstract void setGrupoUsuario(GrupoUsuarioBean grupoUsuario);

}