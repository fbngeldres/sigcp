package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ProduccionPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoGeneradoBean;
import pe.com.pacasmayo.sgcp.bean.TablaOperacionBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: TablaOperacionBeanImpl.java
 * Modificado: May 31, 2010 10:15:27 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class TablaOperacionBeanImpl extends EntidadBeanImpl implements TablaOperacionBean {

	private Long pkCodigoTablaoperacion;
	private ProduccionPuestoTrabajoBean produccionpuestotrabajo;
	private Date fechaTablaoperacion;
	private Double totalTmTablaoperacion;
	private Double totalHoraTablaoperacion;
	private List<ProductoGeneradoBean> productogenerados = new ArrayList<ProductoGeneradoBean>();

	public Long getPkCodigoTablaoperacion() {
		return pkCodigoTablaoperacion;
	}

	public void setPkCodigoTablaoperacion(Long pkCodigoTablaoperacion) {
		this.pkCodigoTablaoperacion = pkCodigoTablaoperacion;
	}

	public ProduccionPuestoTrabajoBean getProduccionpuestotrabajo() {
		return produccionpuestotrabajo;
	}

	public void setProduccionpuestotrabajo(ProduccionPuestoTrabajoBean produccionpuestotrabajo) {
		this.produccionpuestotrabajo = produccionpuestotrabajo;
	}

	public Date getFechaTablaoperacion() {
		return fechaTablaoperacion;
	}

	public void setFechaTablaoperacion(Date fechaTablaoperacion) {
		this.fechaTablaoperacion = fechaTablaoperacion;
	}

	public Double getTotalTmTablaoperacion() {
		return totalTmTablaoperacion;
	}

	public void setTotalTmTablaoperacion(Double totalTmTablaoperacion) {
		this.totalTmTablaoperacion = totalTmTablaoperacion;
	}

	public Double getTotalHoraTablaoperacion() {
		return totalHoraTablaoperacion;
	}

	public void setTotalHoraTablaoperacion(Double totalHoraTablaoperacion) {
		this.totalHoraTablaoperacion = totalHoraTablaoperacion;
	}

	public List<ProductoGeneradoBean> getProductogenerados() {
		return productogenerados;
	}

	public void setProductogenerados(List<ProductoGeneradoBean> productogenerados) {
		this.productogenerados = productogenerados;
	}

}
