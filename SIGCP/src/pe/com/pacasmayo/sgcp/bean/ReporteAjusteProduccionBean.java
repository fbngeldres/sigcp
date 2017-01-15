package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ReporteAjusteProduccionBean.java
 * Modificado: Jun 4, 2012 8:17:41 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ReporteAjusteProduccionBean {

	private String tituloSubReporte;
	private String tituloReporteCombustible;
	private List<SubReporteAjusteProduccionBean> listaSubReporte;
	private List<SubReporteAjusteProduccionBean> listaSubReporteCombustibles;

	public List<SubReporteAjusteProduccionBean> getListaSubReporte() {
		return listaSubReporte;
	}

	public void setListaSubReporte(List<SubReporteAjusteProduccionBean> listaSubReporte) {
		this.listaSubReporte = listaSubReporte;
	}

	/**
	 * @return the listaSubReporteCombustibles
	 */
	public List<SubReporteAjusteProduccionBean> getListaSubReporteCombustibles() {
		return listaSubReporteCombustibles;
	}

	/**
	 * @param listaSubReporteCombustibles the listaSubReporteCombustibles to set
	 */
	public void setListaSubReporteCombustibles(List<SubReporteAjusteProduccionBean> listaSubReporteCombustibles) {
		this.listaSubReporteCombustibles = listaSubReporteCombustibles;
	}

	/**
	 * @return the tituloSubReporte
	 */
	public String getTituloSubReporte() {
		return tituloSubReporte;
	}

	/**
	 * @param tituloSubReporte the tituloSubReporte to set
	 */
	public void setTituloSubReporte(String tituloSubReporte) {
		this.tituloSubReporte = tituloSubReporte;
	}

	/**
	 * @return the tituloReporteCombustible
	 */
	public String getTituloReporteCombustible() {
		return tituloReporteCombustible;
	}

	/**
	 * @param tituloReporteCombustible the tituloReporteCombustible to set
	 */
	public void setTituloReporteCombustible(String tituloReporteCombustible) {
		this.tituloReporteCombustible = tituloReporteCombustible;
	}

}
