package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.io.Serializable;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MovimientoajusteproductoDTO.java
 * Modificado: Feb 24, 2012 10:41:27 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class MovimientoajusteproductoDTO implements Serializable {

	private static final long serialVersionUID = 5770491361633785025L;

	private Long pkCodigomvimientoajusteproducto;
	private Long codigoPuestotrabajoProduccion;
	private Long codigoProducto;
	private Double cantidadMovimientoajusteproducto;

	public MovimientoajusteproductoDTO() {
		// empty
	}

	/**
	 * @return the pkCodigomvimientoajusteproducto
	 */
	public Long getPkCodigomvimientoajusteproducto() {
		return pkCodigomvimientoajusteproducto;
	}

	/**
	 * @param pkCodigomvimientoajusteproducto the
	 *            pkCodigomvimientoajusteproducto to set
	 */
	public void setPkCodigomvimientoajusteproducto(Long pkCodigomvimientoajusteproducto) {
		this.pkCodigomvimientoajusteproducto = pkCodigomvimientoajusteproducto;
	}

	/**
	 * @return the codigoPuestotrabajoProduccion
	 */
	public Long getCodigoPuestotrabajoProduccion() {
		return codigoPuestotrabajoProduccion;
	}

	/**
	 * @param codigoPuestotrabajoProduccion the codigoPuestotrabajoProduccion to
	 *            set
	 */
	public void setCodigoPuestotrabajoProduccion(Long codigoPuestotrabajoProduccion) {
		this.codigoPuestotrabajoProduccion = codigoPuestotrabajoProduccion;
	}

	/**
	 * @return the codigoProducto
	 */
	public Long getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the cantidadMovimientoajusteproducto
	 */
	public Double getCantidadMovimientoajusteproducto() {
		return cantidadMovimientoajusteproducto;
	}

	/**
	 * @param cantidadMovimientoajusteproducto the
	 *            cantidadMovimientoajusteproducto to set
	 */
	public void setCantidadMovimientoajusteproducto(Double cantidadMovimientoajusteproducto) {
		this.cantidadMovimientoajusteproducto = cantidadMovimientoajusteproducto;
	}

}
