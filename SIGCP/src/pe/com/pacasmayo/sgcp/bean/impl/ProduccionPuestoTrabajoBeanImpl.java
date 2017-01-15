package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ParteDiarioBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.TablaOperacionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProduccionPuestoTrabajoBeanImpl.java
 * Modificado: May 27, 2010 10:26:54 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ProduccionPuestoTrabajoBeanImpl extends EntidadBeanImpl implements ProduccionPuestoTrabajoBean {

	private Long pkCodigoProduccionpuestotraba;
	private PuestoTrabajoBean puestotrabajo;
	private ParteDiarioBean partediario;
	private List<TablaOperacionBean> tablaoperacions = new ArrayList<TablaOperacionBean>();

	public Long getPkCodigoProduccionpuestotraba() {
		return pkCodigoProduccionpuestotraba;
	}

	public void setPkCodigoProduccionpuestotraba(Long pkCodigoProduccionpuestotraba) {
		this.pkCodigoProduccionpuestotraba = pkCodigoProduccionpuestotraba;
	}

	public PuestoTrabajoBean getPuestotrabajo() {
		return puestotrabajo;
	}

	public void setPuestotrabajo(PuestoTrabajoBean puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public ParteDiarioBean getPartediario() {
		return partediario;
	}

	public void setPartediario(ParteDiarioBean partediario) {
		this.partediario = partediario;
	}

	public List<TablaOperacionBean> getTablaoperacions() {
		return tablaoperacions;
	}

	public void setTablaoperacions(List<TablaOperacionBean> tablaoperacions) {
		this.tablaoperacions = tablaoperacions;
	}

}
