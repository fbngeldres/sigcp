package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: NotificacionProduccionBean.java
 * Modificado: Jan 21, 2010 11:41:56 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.Set;

public interface NotificacionProduccionBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract HoraBean getHora();

	public abstract void setHora(HoraBean hora);

	public abstract RegistroReporteEcsBean getRegistroReporteEcs();

	public abstract void setRegistroReporteEcs(RegistroReporteEcsBean registroReporteEcs);

	public abstract PuestoTrabajoBean getPuestoTrabajo();

	public abstract void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo);

	public abstract OrdenProduccionBean getOrdenProduccion();

	public abstract void setOrdenProduccion(OrdenProduccionBean ordenProduccion);

	public abstract NotificacionDiariaBean getNotificacionDiaria();

	public abstract void setNotificacionDiaria(NotificacionDiariaBean notificacionDiaria);

	public abstract MedioAlmacenamientoBean getMedioAlmacenamiento();

	public abstract void setMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamiento);

	public abstract Date getHoraNoificacionNotificacionpr();

	public abstract void setHoraNoificacionNotificacionpr(Date horaNoificacionNotificacionpr);

	public abstract String getObservacionNotificacionProducc();

	public abstract void setObservacionNotificacionProducc(String observacionNotificacionProducc);

	public abstract Boolean getProduccionLavadoNotificacionpr();

	public abstract void setProduccionLavadoNotificacionpr(Boolean produccionLavadoNotificacionpr);

	public abstract Boolean getCambioProduccionNotificacionpr();

	public abstract void setCambioProduccionNotificacionpr(Boolean cambioProduccionNotificacionpr);

	public abstract String getHoraCambioproduccionNotificac();

	public abstract void setHoraCambioproduccionNotificac(String horaCambioproduccionNotificac);

	public abstract Set<ComponenteNotificacionBean> getComponentesNotificacion();

	public abstract void setComponentesNotificacion(Set<ComponenteNotificacionBean> componentesNotificacion);

	public abstract Date getFecha();

	public abstract void setFecha(Date fecha);

}