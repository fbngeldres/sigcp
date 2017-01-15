package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.OrdenReporteBean;
import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: OrdenReporteBeanImpl.java
 * Modificado: Aug 23, 2012 6:38:08 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class OrdenReporteBeanImpl implements OrdenReporteBean {
	private Long codigo;
	private ProcesoBean proceso;
	private ProductoBean producto;
	private Long ordenReporte;
	private String ordenProcesoProducto;
	private String tipoOrdenReporte;

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
	 * @return the proceso
	 */
	public ProcesoBean getProceso() {
		return proceso;
	}

	/**
	 * @param proceso the proceso to set
	 */
	public void setProceso(ProcesoBean proceso) {
		this.proceso = proceso;
	}

	/**
	 * @return the producto
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	/**
	 * @return the ordenReporte
	 */
	public Long getOrdenReporte() {
		return ordenReporte;
	}

	/**
	 * @param ordenReporte the ordenReporte to set
	 */
	public void setOrdenReporte(Long ordenReporte) {
		this.ordenReporte = ordenReporte;
	}

	/**
	 * @return the ordenProcesoProducto
	 */
	public String getOrdenProcesoProducto() {
		return ordenProcesoProducto;
	}

	/**
	 * @param ordenProcesoProducto the ordenProcesoProducto to set
	 */
	public void setOrdenProcesoProducto(String ordenProcesoProducto) {
		this.ordenProcesoProducto = ordenProcesoProducto;
	}

	/**
	 * @return the tipoOrdenReporte
	 */
	public String getTipoOrdenReporte() {
		return tipoOrdenReporte;
	}

	/**
	 * @param tipoOrdenReporte the tipoOrdenReporte to set
	 */
	public void setTipoOrdenReporte(String tipoOrdenReporte) {
		this.tipoOrdenReporte = tipoOrdenReporte;
	}
}
