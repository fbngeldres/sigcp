package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n)
 * Archivo: DocumentoMaterialBean.java
 * Modificado: May 25, 2010 10:42:30 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
import java.util.Date;
import java.util.List;

public interface DocumentoMaterialBean extends EntidadBean {

	public abstract Long getPkCodigoDocumentomaterial();

	public abstract void setPkCodigoDocumentomaterial(Long pkCodigoDocumentomaterial);

	public abstract UsuarioBean getUsuario();

	public abstract void setUsuario(UsuarioBean usuario);

	public abstract Boolean getOrigenSapMovimiento();

	public abstract void setOrigenSapMovimiento(Boolean origenSapMovimiento);

	public abstract TipoDocumentoMaterialBean getTipodocumentomaterial();

	public abstract void setTipodocumentomaterial(TipoDocumentoMaterialBean tipodocumentomaterial);

	public abstract SociedadBean getSociedad();

	public abstract void setSociedad(SociedadBean sociedad);

	public abstract PeriodoContableBean getPeriodocontable();

	public abstract void setPeriodocontable(PeriodoContableBean periodocontable);

	public abstract Date getFechaDocumentomaterial();

	public abstract void setFechaDocumentomaterial(Date fechaDocumentomaterial);

	public abstract List<MovimientoBean> getMovimientos();

	public abstract void setMovimientos(List<MovimientoBean> movimientos);

	/**
	 * @return the ticket
	 */
	public abstract String getTicket();

	/**
	 * @param ticket the ticket to set
	 */
	public abstract void setTicket(String ticket);

	/**
	 * @return the notaEntrega
	 */
	public abstract String getNotaEntrega();

	/**
	 * @param notaEntrega the notaEntrega to set
	 */
	public abstract void setNotaEntrega(String notaEntrega);

	/**
	 * @return the observacion
	 */
	public abstract String getObservacion();

	/**
	 * @param observacion the observacion to set
	 */
	public abstract void setObservacion(String observacion);

	/**
	 * @return the placa
	 */
	public abstract String getPlaca();

	/**
	 * @param placa the placa to set
	 */
	public abstract void setPlaca(String placa);

}