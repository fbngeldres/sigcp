package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoNotificacionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.HashSet;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.EstadoNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;

public class EstadoNotificacionBeanImpl implements EstadoNotificacionBean {

	private Long codigo;
	private String nombreEstadoNotificacion;
	private Set<NotificacionDiariaBean> notificacionesDiarias = new HashSet<NotificacionDiariaBean>(0);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoNotificacionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoNotificacionBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoNotificacionBean#
	 * getNombreEstadoNotificacion()
	 */
	public String getNombreEstadoNotificacion() {
		return nombreEstadoNotificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoNotificacionBean#
	 * setNombreEstadoNotificacion(java.lang.String)
	 */
	public void setNombreEstadoNotificacion(String nombreEstadoNotificacion) {
		this.nombreEstadoNotificacion = nombreEstadoNotificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoNotificacionBean#getNotificaciondiarias
	 * ()
	 */
	public Set<NotificacionDiariaBean> getNotificaciondiarias() {
		return notificacionesDiarias;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoNotificacionBean#setNotificaciondiarias
	 * (java.util.Set)
	 */
	public void setNotificaciondiarias(Set<NotificacionDiariaBean> notificaciondiarias) {
		this.notificacionesDiarias = notificaciondiarias;
	}

}
