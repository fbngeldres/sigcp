package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionOperacionBean.java
 * Modificado: Jan 21, 2010 11:50:50 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

public interface NotificacionOperacionBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract RegistroReporteEcsBean getRegistroReporteEcs();

	public abstract void setRegistroReporteEcs(RegistroReporteEcsBean registroReporteEcs);

	public abstract NotificacionDiariaBean getNotificacionDiaria();

	public abstract void setNotificacionDiaria(NotificacionDiariaBean notificacionDiaria);

	public abstract Set<VariableValorNotificacionBean> getVariablesValorNotificacion();

	public abstract void setVariablesValorNotificacion(Set<VariableValorNotificacionBean> variablesValorNotificacion);

}