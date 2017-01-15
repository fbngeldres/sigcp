package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: DocumentoMaterialBeanImpl.java
 * Modificado: May 25, 2010 10:42:30 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.bean.MovimientoBean;
import pe.com.pacasmayo.sgcp.bean.PeriodoContableBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.TipoDocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;

public class DocumentoMaterialBeanImpl extends EntidadBeanImpl implements DocumentoMaterialBean {

	private Long pkCodigoDocumentomaterial;
	private Date fechaDocumentomaterial;
	private Boolean origenSapMovimiento = false;
	private List<MovimientoBean> movimientos = new ArrayList<MovimientoBean>();
	private UsuarioBean usuario;
	private TipoDocumentoMaterialBean tipodocumentomaterial;
	private SociedadBean sociedad;
	private PeriodoContableBean periodocontable;

	/**
	 * Agregado por Jordan
	 */
	private String ticket;
	private String notaEntrega;
	private String observacion;
	private String placa;

	public DocumentoMaterialBeanImpl() {
		usuario = new UsuarioBeanImpl();
		tipodocumentomaterial = new TipoDocumentoMaterialBeanImpl();
		sociedad = new SociedadBeanImpl();
		periodocontable = new PeriodoContableBeanImpl();
	}

	public Date getFechaDocumentomaterial() {
		return fechaDocumentomaterial;
	}

	public List<MovimientoBean> getMovimientos() {
		return movimientos;
	}

	public PeriodoContableBean getPeriodocontable() {

		return periodocontable;
	}

	public Long getPkCodigoDocumentomaterial() {
		return pkCodigoDocumentomaterial;
	}

	public SociedadBean getSociedad() {
		return sociedad;
	}

	public TipoDocumentoMaterialBean getTipodocumentomaterial() {
		return tipodocumentomaterial;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setFechaDocumentomaterial(Date fechaDocumentomaterial) {
		this.fechaDocumentomaterial = fechaDocumentomaterial;
	}

	public void setMovimientos(List<MovimientoBean> movimientos) {
		this.movimientos = movimientos;
	}

	public void setPeriodocontable(PeriodoContableBean periodocontable) {
		this.periodocontable = periodocontable;
	}

	public void setPkCodigoDocumentomaterial(Long pkCodigoDocumentomaterial) {
		this.pkCodigoDocumentomaterial = pkCodigoDocumentomaterial;

	}

	public void setSociedad(SociedadBean sociedad) {
		this.sociedad = sociedad;
	}

	public void setTipodocumentomaterial(TipoDocumentoMaterialBean tipodocumentomaterial) {
		this.tipodocumentomaterial = tipodocumentomaterial;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public Boolean getOrigenSapMovimiento() {
		return origenSapMovimiento;
	}

	public void setOrigenSapMovimiento(Boolean origenSapMovimiento) {
		this.origenSapMovimiento = origenSapMovimiento;
	}

	/**
	 * Agregado por Jordan
	 */

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the notaEntrega
	 */
	public String getNotaEntrega() {
		return notaEntrega;
	}

	/**
	 * @param notaEntrega the notaEntrega to set
	 */
	public void setNotaEntrega(String notaEntrega) {
		this.notaEntrega = notaEntrega;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
