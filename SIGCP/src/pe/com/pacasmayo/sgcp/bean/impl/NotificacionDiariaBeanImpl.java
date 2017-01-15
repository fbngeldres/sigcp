package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionDiariaBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import pe.com.pacasmayo.sgcp.bean.EstadoNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;

import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionOperacionBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.TableroControlBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

public class NotificacionDiariaBeanImpl implements NotificacionDiariaBean {

	private Long codigo;
	private UsuarioBean usuarioRegistra;
	private UsuarioBean usuarioAprueba;
	private UsuarioBean usuarioCierra;
	private LineaNegocioBean lineaNegocio;
	private EstadoNotificacionBean estadoNotificacion;
	private Date fechaNotificacionDiaria;
	private String observacionNotificacionDiaria;
	private TableroControlBean tableroControl;
	private Set<NotificacionProduccionBean> notificacionProducciones = new HashSet<NotificacionProduccionBean>(0);
	private Set<NotificacionOperacionBean> notificacionOperaciones = new HashSet<NotificacionOperacionBean>(0);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#getUsuarioRegistra
	 * ()
	 */
	public UsuarioBean getUsuarioRegistra() {
		return usuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#setUsuarioRegistra
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioRegistra(UsuarioBean usuarioRegistra) {
		this.usuarioRegistra = usuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#getUsuarioAprueba
	 * ()
	 */
	public UsuarioBean getUsuarioAprueba() {
		return usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#setUsuarioAprueba
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioAprueba(UsuarioBean usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#getTipoNotificacion
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#getLineaNegocio()
	 */
	public LineaNegocioBean getLineaNegocio() {
		return lineaNegocio;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#setLineaNegocio
	 * (pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	public void setLineaNegocio(LineaNegocioBean lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#getEstadoNotificacion
	 * ()
	 */
	public EstadoNotificacionBean getEstadoNotificacion() {
		return estadoNotificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#setEstadoNotificacion
	 * (pe.com.pacasmayo.sgcp.bean.EstadoNotificacionBean)
	 */
	public void setEstadoNotificacion(EstadoNotificacionBean estadoNotificacion) {
		this.estadoNotificacion = estadoNotificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * getFechaNotificacionDiaria()
	 */
	public Date getFechaNotificacionDiaria() {
		return fechaNotificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * setFechaNotificacionDiaria(java.util.Date)
	 */
	public void setFechaNotificacionDiaria(Date fechaNotificacionDiaria) {
		this.fechaNotificacionDiaria = fechaNotificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * getObservacionNotificacionDiaria()
	 */
	public String getObservacionNotificacionDiaria() {
		return observacionNotificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * setObservacionNotificacionDiaria(java.lang.String)
	 */
	public void setObservacionNotificacionDiaria(String observacionNotificacionDiaria) {
		this.observacionNotificacionDiaria = observacionNotificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#getTableroControl
	 * ()
	 */
	public TableroControlBean getTableroControl() {
		return tableroControl;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#setTableroControl
	 * (pe.com.pacasmayo.sgcp.bean.TableroControlBean)
	 */
	public void setTableroControl(TableroControlBean tableroControl) {
		this.tableroControl = tableroControl;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * getNotificacionProducciones()
	 */
	public Set<NotificacionProduccionBean> getNotificacionProducciones() {
		return notificacionProducciones;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * setNotificacionProducciones(java.util.Set)
	 */
	public void setNotificacionProducciones(Set<NotificacionProduccionBean> notificacionProducciones) {
		this.notificacionProducciones = notificacionProducciones;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * getNotificacionOperaciones()
	 */
	public Set<NotificacionOperacionBean> getNotificacionOperaciones() {
		return notificacionOperaciones;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean#
	 * setNotificacionOperaciones(java.util.Set)
	 */
	public void setNotificacionOperaciones(Set<NotificacionOperacionBean> notificacionOperaciones) {
		this.notificacionOperaciones = notificacionOperaciones;
	}

	

	public UsuarioBean getUsuarioCierra() {
		return usuarioCierra;
	}

	public void setUsuarioCierra(UsuarioBean usuarioCierra) {
		this.usuarioCierra = usuarioCierra;
	}

}
