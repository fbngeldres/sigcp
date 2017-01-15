package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: NotificacionProduccionBeanImpl.java
 * Modificado: Jan 21, 2010 11:47:31 AM 
 * Autor: gustavo.gonzalez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.ComponenteNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.HoraBean;
import pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.RegistroReporteEcsBean;

public class NotificacionProduccionBeanImpl implements NotificacionProduccionBean {

	private Long codigo;
	private HoraBean hora;
	private RegistroReporteEcsBean registroReporteEcs;
	private PuestoTrabajoBean puestoTrabajo;
	private OrdenProduccionBean ordenProduccion;
	private NotificacionDiariaBean notificacionDiaria;
	private MedioAlmacenamientoBean medioAlmacenamiento;
	private Date horaNoificacionNotificacionpr;
	private String observacionNotificacionProducc;
	private Boolean produccionLavadoNotificacionpr;
	private Boolean cambioProduccionNotificacionpr;
	private String horaCambioproduccionNotificac;
	private Set<ComponenteNotificacionBean> componentesNotificacion = new HashSet<ComponenteNotificacionBean>(0);

	private Date fecha;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#setCodigo(
	 * java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#getHora()
	 */
	public HoraBean getHora() {
		return hora;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#setHora(pe
	 * .com.pacasmayo.sgcp.bean.HoraBean)
	 */
	public void setHora(HoraBean hora) {
		this.hora = hora;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getRegistroReporteEcs()
	 */
	public RegistroReporteEcsBean getRegistroReporteEcs() {
		return registroReporteEcs;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setRegistroReporteEcs
	 * (pe.com.pacasmayo.sgcp.bean.impl.RegistroReporteEcsBean)
	 */
	public void setRegistroReporteEcs(RegistroReporteEcsBean registroReporteEcs) {
		this.registroReporteEcs = registroReporteEcs;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#getPuestoTrabajo
	 * ()
	 */
	public PuestoTrabajoBean getPuestoTrabajo() {
		return puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#setPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#getOrdenProduccion
	 * ()
	 */
	public OrdenProduccionBean getOrdenProduccion() {
		return ordenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#setOrdenProduccion
	 * (pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	public void setOrdenProduccion(OrdenProduccionBean ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getNotificacionDiaria()
	 */
	public NotificacionDiariaBean getNotificacionDiaria() {
		return notificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setNotificacionDiaria
	 * (pe.com.pacasmayo.sgcp.bean.impl.NotificacionDiariaBean)
	 */
	public void setNotificacionDiaria(NotificacionDiariaBean notificacionDiaria) {
		this.notificacionDiaria = notificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getMedioAlmacenamiento()
	 */
	public MedioAlmacenamientoBean getMedioAlmacenamiento() {
		return medioAlmacenamiento;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setMedioAlmacenamiento
	 * (pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean)
	 */
	public void setMedioAlmacenamiento(MedioAlmacenamientoBean medioAlmacenamiento) {
		this.medioAlmacenamiento = medioAlmacenamiento;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getHoraNoificacionNotificacionpr()
	 */
	public Date getHoraNoificacionNotificacionpr() {
		return horaNoificacionNotificacionpr;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setHoraNoificacionNotificacionpr(java.util.Date)
	 */
	public void setHoraNoificacionNotificacionpr(Date horaNoificacionNotificacionpr) {
		this.horaNoificacionNotificacionpr = horaNoificacionNotificacionpr;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getObservacionNotificacionProducc()
	 */
	public String getObservacionNotificacionProducc() {
		return observacionNotificacionProducc;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setObservacionNotificacionProducc(java.lang.String)
	 */
	public void setObservacionNotificacionProducc(String observacionNotificacionProducc) {
		this.observacionNotificacionProducc = observacionNotificacionProducc;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getProduccionLavadoNotificacionpr()
	 */
	public Boolean getProduccionLavadoNotificacionpr() {
		return produccionLavadoNotificacionpr;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setProduccionLavadoNotificacionpr(java.lang.Byte)
	 */
	public void setProduccionLavadoNotificacionpr(Boolean produccionLavadoNotificacionpr) {
		this.produccionLavadoNotificacionpr = produccionLavadoNotificacionpr;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getCambioProduccionNotificacionpr()
	 */
	public Boolean getCambioProduccionNotificacionpr() {
		return cambioProduccionNotificacionpr;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setCambioProduccionNotificacionpr(java.lang.Byte)
	 */
	public void setCambioProduccionNotificacionpr(Boolean cambioProduccionNotificacionpr) {
		this.cambioProduccionNotificacionpr = cambioProduccionNotificacionpr;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * getHoraCambioproduccionNotificac()
	 */
	public String getHoraCambioproduccionNotificac() {
		return horaCambioproduccionNotificac;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean#
	 * setHoraCambioproduccionNotificac(java.lang.String)
	 */
	public void setHoraCambioproduccionNotificac(String horaCambioproduccionNotificac) {
		this.horaCambioproduccionNotificac = horaCambioproduccionNotificac;
	}

	public Set<ComponenteNotificacionBean> getComponentesNotificacion() {
		return componentesNotificacion;
	}

	public void setComponentesNotificacion(Set<ComponenteNotificacionBean> componentesNotificacion) {
		this.componentesNotificacion = componentesNotificacion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
