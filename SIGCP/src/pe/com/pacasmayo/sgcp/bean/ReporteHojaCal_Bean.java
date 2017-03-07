package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.impl.ReporteConsumoVentasCal_List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ReporteHojaCal_Bean.java
 * Modificado: Oct 11, 2012 11:25:31 AM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ReporteHojaCal_Bean {

	// Lista Producto Terminado
	private List<ReporteProductoTerminado_Bean> subReporteProductoterminadoCal;

	private String tituloReporte;

	// Consumo_Ventas_Cal
	private List<ReporteConsumoVentasCal_List> reporteConsumosVentasCal;

	/**
	 * @return the subReporteProductoterminadoCal
	 */
	public List<ReporteProductoTerminado_Bean> getSubReporteProductoterminadoCal() {
		return subReporteProductoterminadoCal;
	}

	/**
	 * @param subReporteProductoterminadoCal the subReporteProductoterminadoCal
	 *            to set
	 */
	public void setSubReporteProductoterminadoCal(List<ReporteProductoTerminado_Bean> subReporteProductoterminadoCal) {
		this.subReporteProductoterminadoCal = subReporteProductoterminadoCal;
	}

	/**
	 * @return the tituloReporte
	 */
	public String getTituloReporte() {
		return tituloReporte;
	}

	/**
	 * @param tituloReporte the tituloReporte to set
	 */
	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}

	/**
	 * @return the reporteConsumosVentasCal
	 */
	public List<ReporteConsumoVentasCal_List> getReporteConsumosVentasCal() {
		return reporteConsumosVentasCal;
	}

	/**
	 * @param listaVentasConsumoCal the reporteConsumosVentasCal to set
	 */
	public void setReporteConsumosVentasCal(List<ReporteConsumoVentasCal_List> listaVentasConsumoCal) {
		this.reporteConsumosVentasCal = listaVentasConsumoCal;
	}

}
