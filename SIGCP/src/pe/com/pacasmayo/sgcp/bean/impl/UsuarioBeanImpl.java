package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.EstadoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.PersonaBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;

import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

public class UsuarioBeanImpl extends PersonaBeanImpl implements UsuarioBean {

	private Long codigo;
	private EstadoUsuarioBean estadoUsuario;
	private String login;
	private String password;
	private Set<OrdenProduccionBean> ordenProduccionUsuarioRegistro;
	private Set<OrdenProduccionBean> ordenProduccionUsuarioAprueba;

	private Set<PlanAnualBean> planesAnualesUsuarioRegistra;
	private Set<PlanAnualBean> planesAnualesUsuarioAprueba;

	private Set<NotificacionDiariaBeanImpl> notificacionesDiarias;

	private Set<GrupoUsuarioBean> grupoUsuarios;
	private GrupoUsuarioBean grupoUsuario;

	public UsuarioBeanImpl() {
		estadoUsuario = new EstadoUsuarioBeanImpl();
		grupoUsuario = new GrupoUsuarioBeanImpl();
		this.cargo = new CargoBeanImpl();
	}

	public UsuarioBeanImpl(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getEstadoUsuario()
	 */
	public EstadoUsuarioBean getEstadoUsuario() {
		return estadoUsuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setEstadoUsuario(pe.com.pacasmayo
	 * .sgcp.bean.impl.EstadoUsuarioBean)
	 */
	public void setEstadoUsuario(EstadoUsuarioBean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getPersona()
	 */
	public PersonaBean getPersona() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setPersona(pe.com.pacasmayo
	 * .sgcp.bean.PersonaBean)
	 */
	public void setPersona(PersonaBean persona) {
		this.setNombre(persona.getNombre());
		this.setApellido(persona.getApellido());
		this.setCargo(persona.getCargo());
		this.setCorreo(persona.getCorreo());
		this.setExtension(persona.getExtension());
		this.setIdDocumento(persona.getIdDocumento());
		this.setTelefono(persona.getTelefono());
		this.setCargo(persona.getCargo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getLogin()
	 */
	public String getLogin() {
		return login;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setLogin(java.lang.String)
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setPassword(java.lang.String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getOrdenProduccionUsuarioRegistro
	 * ()
	 */
	public Set<OrdenProduccionBean> getOrdenProduccionUsuarioRegistro() {
		return ordenProduccionUsuarioRegistro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setOrdenProduccionUsuarioRegistro
	 * (java.util.Set)
	 */
	public void setOrdenProduccionUsuarioRegistro(
			Set<OrdenProduccionBean> ordenProduccionUsuarioRegistro) {
		this.ordenProduccionUsuarioRegistro = ordenProduccionUsuarioRegistro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getOrdenProduccionUsuarioAprueba
	 * ()
	 */
	public Set<OrdenProduccionBean> getOrdenProduccionUsuarioAprueba() {
		return ordenProduccionUsuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setOrdenProduccionUsuarioAprueba
	 * (java.util.Set)
	 */
	public void setOrdenProduccionUsuarioAprueba(
			Set<OrdenProduccionBean> ordenProduccionUsuarioAprueba) {
		this.ordenProduccionUsuarioAprueba = ordenProduccionUsuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getPlanesAnualesUsuarioRegistra
	 * ()
	 */
	public Set<PlanAnualBean> getPlanesAnualesUsuarioRegistra() {
		return planesAnualesUsuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setPlanesAnualesUsuarioRegistra
	 * (java.util.Set)
	 */
	public void setPlanesAnualesUsuarioRegistra(
			Set<PlanAnualBean> planesAnualesUsuarioRegistra) {
		this.planesAnualesUsuarioRegistra = planesAnualesUsuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getPlanesAnualesUsuarioAprueba
	 * ()
	 */
	public Set<PlanAnualBean> getPlanesAnualesUsuarioAprueba() {
		return planesAnualesUsuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setPlanesAnualesUsuarioAprueba
	 * (java.util.Set)
	 */
	public void setPlanesAnualesUsuarioAprueba(
			Set<PlanAnualBean> planesAnualesUsuarioAprueba) {
		this.planesAnualesUsuarioAprueba = planesAnualesUsuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getNotificacionesDiarias()
	 */
	public Set<NotificacionDiariaBeanImpl> getNotificacionesDiarias() {
		return notificacionesDiarias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setNotificacionesDiarias(
	 * java.util.Set)
	 */
	public void setNotificacionesDiarias(
			Set<NotificacionDiariaBeanImpl> notificacionesDiarias) {
		this.notificacionesDiarias = notificacionesDiarias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#getGrupoUsuarios()
	 */
	public Set<GrupoUsuarioBean> getGrupoUsuarios() {
		return grupoUsuarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioBean#setGrupoUsuarios(java.util
	 * .Set)
	 */
	public void setGrupoUsuarios(Set<GrupoUsuarioBean> grupoUsuarios) {
		this.grupoUsuarios = grupoUsuarios;
	}

	public String getNombreCompleto() {
		return getPersona().getApellido() + ", " + getPersona().getNombre();
	}

	public GrupoUsuarioBean getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuarioBean grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}
}