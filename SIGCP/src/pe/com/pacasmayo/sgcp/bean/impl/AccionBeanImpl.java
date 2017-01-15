package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AccionBeanImpl.java
 * Modificado: Dec 4, 2009 2:44:13 AM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AccionBean;
import pe.com.pacasmayo.sgcp.bean.OpcionAccionBean;
import pe.com.pacasmayo.sgcp.bean.OpcionBean;

public class AccionBeanImpl extends EntidadBeanImpl implements AccionBean {

	private Long codigo;
	private String nombre;
	private String presentacionAccion;
	private String urlAccion;
	private List<OpcionBean> opcionesList;
	private List<OpcionAccionBean> opcionAccionesList;

	public AccionBeanImpl() {
		opcionesList = new ArrayList<OpcionBean>();
		opcionAccionesList = new ArrayList<OpcionAccionBean>();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getPresentacionAccion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getPresentacionAccion()
	 */
	public String getPresentacionAccion() {
		return presentacionAccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setPresentacionAccion(java
	 * .lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setPresentacionAccion(java
	 * .lang.String)
	 */
	public void setPresentacionAccion(String presentacionAccion) {
		this.presentacionAccion = presentacionAccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getUrlAccion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getUrlAccion()
	 */
	public String getUrlAccion() {
		return urlAccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setUrlAccion(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setUrlAccion(java.lang.String)
	 */
	public void setUrlAccion(String urlAccion) {
		this.urlAccion = urlAccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getOpcionesList()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.AccionBean#getOpcionesList()
	 */
	public List<OpcionBean> getOpcionesList() {
		return opcionesList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setOpcionesList(java.util.
	 * List)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.AccionBean#setOpcionesList(java.util.
	 * List)
	 */
	public void setOpcionesList(List<OpcionBean> opcionesList) {
		this.opcionesList = opcionesList;
	}

	public List<OpcionAccionBean> getOpcionAccionesList() {
		return opcionAccionesList;
	}

	public void setOpcionAccionesList(List<OpcionAccionBean> opcionAccionesList) {
		this.opcionAccionesList = opcionAccionesList;
	}

}