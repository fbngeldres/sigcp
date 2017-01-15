package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionOperacionBeanImpl.java
 * Modificado: Jan 21, 2010 11:47:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.HashSet;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionOperacionBean;
import pe.com.pacasmayo.sgcp.bean.RegistroReporteEcsBean;
import pe.com.pacasmayo.sgcp.bean.VariableValorNotificacionBean;

public class NotificacionOperacionBeanImpl implements NotificacionOperacionBean {

	private Long codigo;
	private RegistroReporteEcsBean registroReporteEcs;
	private NotificacionDiariaBean notificacionDiaria;
	private Set<VariableValorNotificacionBean> variablesValorNotificacion = new HashSet<VariableValorNotificacionBean>(0);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionOperacionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionOperacionBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionOperacionBean#
	 * getRegistroReporteEcs()
	 */
	public RegistroReporteEcsBean getRegistroReporteEcs() {
		return registroReporteEcs;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionOperacionBean#
	 * setRegistroReporteEcs
	 * (pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean)
	 */
	public void setRegistroReporteEcs(RegistroReporteEcsBean registroReporteEcs) {
		this.registroReporteEcs = registroReporteEcs;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionOperacionBean#
	 * getNotificacionDiaria()
	 */
	public NotificacionDiariaBean getNotificacionDiaria() {
		return notificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionOperacionBean#
	 * setNotificacionDiaria
	 * (pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean)
	 */
	public void setNotificacionDiaria(NotificacionDiariaBean notificacionDiaria) {
		this.notificacionDiaria = notificacionDiaria;
	}

	public Set<VariableValorNotificacionBean> getVariablesValorNotificacion() {
		return variablesValorNotificacion;
	}

	public void setVariablesValorNotificacion(Set<VariableValorNotificacionBean> variablesValorNotificacion) {
		this.variablesValorNotificacion = variablesValorNotificacion;
	}

}
