package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMedioAlmacenamientoBeanImpl.java
 * Modificado: Feb 22, 2010 10:49:00 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.HashSet;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.TipoMedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class TipoMedioAlmacenamientoBeanImpl implements TipoMedioAlmacenamientoBean {

	private Long codigo;
	private UnidadMedidaBean unidada;
	private String nombre;
	private String descripcion;
	private Set<?> mediosAlmacenamiento = new HashSet(0);
	private Set registroMedicioness = new HashSet(0);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#setCodigo
	 * (java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#getUnidada()
	 */
	public UnidadMedidaBean getUnidada() {
		return unidada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#setUnidada
	 * (pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean)
	 */
	public void setUnidada(UnidadMedidaBean unidada) {
		this.unidada = unidada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#setNombre
	 * (java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#getDescripcion
	 * ()
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#setDescripcion
	 * (java.lang.String)
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#
	 * getMediosAlmacenamiento()
	 */
	public Set getMediosAlmacenamiento() {
		return mediosAlmacenamiento;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#
	 * setMediosAlmacenamiento(java.util.Set)
	 */
	public void setMediosAlmacenamiento(Set mediosAlmacenamiento) {
		this.mediosAlmacenamiento = mediosAlmacenamiento;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#
	 * getRegistroMedicioness()
	 */
	public Set getRegistroMedicioness() {
		return registroMedicioness;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.TipoMedioAlmacenamientoBean#
	 * setRegistroMedicioness(java.util.Set)
	 */
	public void setRegistroMedicioness(Set registroMedicioness) {
		this.registroMedicioness = registroMedicioness;
	}

}
