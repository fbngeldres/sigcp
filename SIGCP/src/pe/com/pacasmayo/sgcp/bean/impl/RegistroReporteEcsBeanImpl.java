package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroReporteEcsBeanImpl.java
 * Modificado: Nov 23, 2009 9:44:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.DatoReporteBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionOperacionBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.RegistroReporteEcsBean;

public class RegistroReporteEcsBeanImpl implements RegistroReporteEcsBean {

	private Long codigo;
	private Date fechaRegistroReporteEcs;
	private String nombreRegistroReportEecs;
	private Set<NotificacionProduccionBean> notificacionesProduccion = new HashSet<NotificacionProduccionBean>(0);
	private Set<NotificacionOperacionBean> notificacionesOperacion = new HashSet<NotificacionOperacionBean>(0);
	private List<DatoReporteBean> datosReporte;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean#
	 * getFechaRegistroReporteEcs()
	 */
	public Date getFechaRegistroReporteEcs() {
		return fechaRegistroReporteEcs;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean#
	 * setFechaRegistroReporteEcs(java.lang.Long)
	 */
	public void setFechaRegistroReporteEcs(Date fechaRegistroReporteEcs) {
		this.fechaRegistroReporteEcs = fechaRegistroReporteEcs;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean#
	 * getNombreRegistroReportEecs()
	 */
	public String getNombreRegistroReportEecs() {
		return nombreRegistroReportEecs;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean#
	 * setNombreRegistroReportEecs(java.lang.Long)
	 */
	public void setNombreRegistroReportEecs(String nombreRegistroReportEecs) {
		this.nombreRegistroReportEecs = nombreRegistroReportEecs;
	}

	public Set<NotificacionProduccionBean> getNotificacionesProduccion() {
		return notificacionesProduccion;
	}

	public void setNotificacionesProduccion(Set<NotificacionProduccionBean> notificacionesProduccion) {
		this.notificacionesProduccion = notificacionesProduccion;
	}

	public Set<NotificacionOperacionBean> getNotificacionesOperacion() {
		return notificacionesOperacion;
	}

	public void setNotificacionesOperacion(Set<NotificacionOperacionBean> notificacionesOperacion) {
		this.notificacionesOperacion = notificacionesOperacion;
	}

	public List<DatoReporteBean> getDatosReporte() {
		return datosReporte;
	}

	public void setDatosReporte(List<DatoReporteBean> datosReporte) {
		this.datosReporte = datosReporte;
	}

}
