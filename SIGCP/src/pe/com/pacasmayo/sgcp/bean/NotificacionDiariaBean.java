package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;
import java.util.Set;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: NotificacionDiariaBean.java
 * Modificado: Jun 23, 2010 2:44:03 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface NotificacionDiariaBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the usuarioRegistra
	 */
	public abstract UsuarioBean getUsuarioRegistra();

	/**
	 * @param usuarioRegistra
	 *            the usuarioRegistra to set
	 */
	public abstract void setUsuarioRegistra(UsuarioBean usuarioRegistra);

	/**
	 * @return the usuarioAprueba
	 */
	public abstract UsuarioBean getUsuarioAprueba();

	/**
	 * @param usuarioAprueba
	 *            the usuarioAprueba to set
	 */
	public abstract void setUsuarioAprueba(UsuarioBean usuarioAprueba);

	/**
	 * @return the lineaNegocio
	 */
	public abstract LineaNegocioBean getLineaNegocio();

	/**
	 * @param lineaNegocio
	 *            the lineaNegocio to set
	 */
	public abstract void setLineaNegocio(LineaNegocioBean lineaNegocio);

	/**
	 * @return the estadoNotificacion
	 */
	public abstract EstadoNotificacionBean getEstadoNotificacion();

	/**
	 * @param estadoNotificacion
	 *            the estadoNotificacion to set
	 */
	public abstract void setEstadoNotificacion(
			EstadoNotificacionBean estadoNotificacion);

	/**
	 * @return the fechaNotificacionDiaria
	 */
	public abstract Date getFechaNotificacionDiaria();

	/**
	 * @param fechaNotificacionDiaria
	 *            the fechaNotificacionDiaria to set
	 */
	public abstract void setFechaNotificacionDiaria(Date fechaNotificacionDiaria);

	/**
	 * @return the observacionNotificacionDiaria
	 */
	public abstract String getObservacionNotificacionDiaria();

	/**
	 * @param observacionNotificacionDiaria
	 *            the observacionNotificacionDiaria to set
	 */
	public abstract void setObservacionNotificacionDiaria(
			String observacionNotificacionDiaria);

	/**
	 * @return the tableroControl
	 */
	public abstract TableroControlBean getTableroControl();

	/**
	 * @param tableroControl
	 *            the tableroControl to set
	 */
	public abstract void setTableroControl(TableroControlBean tableroControl);

	/**
	 * @return the notificacionProducciones
	 */
	public abstract Set<NotificacionProduccionBean> getNotificacionProducciones();

	/**
	 * @param notificacionProducciones
	 *            the notificacionProducciones to set
	 */
	public abstract void setNotificacionProducciones(
			Set<NotificacionProduccionBean> notificacionProducciones);

	/**
	 * @return the notificacionOperaciones
	 */
	public abstract Set<NotificacionOperacionBean> getNotificacionOperaciones();

	/**
	 * @param notificacionOperaciones
	 *            the notificacionOperaciones to set
	 */
	public abstract void setNotificacionOperaciones(
			Set<NotificacionOperacionBean> notificacionOperaciones);

	public abstract UsuarioBean getUsuarioCierra();

	public abstract void setUsuarioCierra(UsuarioBean usuarioCierra);

}