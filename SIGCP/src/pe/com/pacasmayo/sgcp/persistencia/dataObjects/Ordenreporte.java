package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: Ordenreporte.java
 * Modificado: Aug 23, 2012 4:35:23 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class Ordenreporte implements java.io.Serializable {

	// Fields

	private Long pkCodigoOrdenreporte;
	private Proceso proceso;
	private Producto producto;
	private Long ordenReporte;
	private String ordenProcesoProducto;
	private String tipoOrdenReporte;

	/**
	 * @return the pkCodigoOrdenreporte
	 */
	public Long getPkCodigoOrdenreporte() {
		return pkCodigoOrdenreporte;
	}

	/**
	 * @param pkCodigoOrdenreporte the pkCodigoOrdenreporte to set
	 */
	public void setPkCodigoOrdenreporte(Long pkCodigoOrdenreporte) {
		this.pkCodigoOrdenreporte = pkCodigoOrdenreporte;
	}

	/**
	 * @return the proceso
	 */
	public Proceso getProceso() {
		return proceso;
	}

	/**
	 * @param proceso the proceso to set
	 */
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
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
