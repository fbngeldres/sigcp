package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroReporteEcsBean.java
 * Modificado: Jan 21, 2010 11:39:44 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface RegistroReporteEcsBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract Date getFechaRegistroReporteEcs();

	public abstract void setFechaRegistroReporteEcs(Date fechaRegistroReporteEcs);

	public abstract String getNombreRegistroReportEecs();

	public abstract void setNombreRegistroReportEecs(String nombreRegistroReportEecs);

	public abstract Set<NotificacionProduccionBean> getNotificacionesProduccion();

	public abstract void setNotificacionesProduccion(Set<NotificacionProduccionBean> notificacionesProduccion);

	public abstract Set<NotificacionOperacionBean> getNotificacionesOperacion();

	public abstract void setNotificacionesOperacion(Set<NotificacionOperacionBean> notificacionesOperacion);

	public abstract List<DatoReporteBean> getDatosReporte();

	public abstract void setDatosReporte(List<DatoReporteBean> datosReporte);

}