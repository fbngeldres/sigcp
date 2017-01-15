package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.io.Serializable;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AjusteProductoDTO.java
 * Modificado: Feb 9, 2012 1:58:00 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AjusteProductoDTO implements Serializable {

	private static final long serialVersionUID = -7415410823492205918L;

	private Long codigo;
	private String estado;
	private String observacion;
	private Double ajuste;

	public AjusteProductoDTO() {

	}

	public AjusteProductoDTO(Long codigo, String estado, String observacion, Double ajuste) {
		this.codigo = codigo;
		this.estado = estado;
		this.observacion = observacion;
		this.ajuste = ajuste;
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the ajuste
	 */
	public Double getAjuste() {
		return ajuste;
	}

	/**
	 * @param ajuste the ajuste to set
	 */
	public void setAjuste(Double ajuste) {
		this.ajuste = ajuste;
	}

}
