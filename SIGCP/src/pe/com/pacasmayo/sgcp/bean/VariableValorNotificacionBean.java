package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: VariableValorNotificacionBean.java
 * Modificado: Jan 21, 2010 11:51:11 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface VariableValorNotificacionBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract VariableOperacionBean getVariableOperacion();

	public abstract void setVariableOperacion(VariableOperacionBean variableOperacion);

	public abstract NotificacionOperacionBean getNotificacionOperacion();

	public abstract void setNotificacionOperacion(NotificacionOperacionBean notificacionOperacion);

}