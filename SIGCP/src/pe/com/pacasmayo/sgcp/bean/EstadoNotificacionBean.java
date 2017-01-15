package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: EstadoNotificacionBean.java
 * Modificado: Jan 21, 2010 12:01:51 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

public interface EstadoNotificacionBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract String getNombreEstadoNotificacion();

	public abstract void setNombreEstadoNotificacion(String nombreEstadoNotificacion);

	public abstract Set<NotificacionDiariaBean> getNotificaciondiarias();

	public abstract void setNotificaciondiarias(Set<NotificacionDiariaBean> notificaciondiarias);

}