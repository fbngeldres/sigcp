package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n)
 * Archivo: ReporteCombustibles_Bean.java
 * Modificado: Jul 25, 2012 6:30:11 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ReporteCombustibles_Bean {
	private String titulo;
	private List<SubReporteCombustibleBean> subReporteCombustibleSolido;
	private List<SubReporteCombustibleBean> subReporteCombustibleLiquido;

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the subReporteCombustibleSolido
	 */
	public List<SubReporteCombustibleBean> getSubReporteCombustibleSolido() {
		return subReporteCombustibleSolido;
	}

	/**
	 * @param subReporteCombustibleSolido the subReporteCombustibleSolido to set
	 */
	public void setSubReporteCombustibleSolido(List<SubReporteCombustibleBean> subReporteCombustibleSolido) {
		this.subReporteCombustibleSolido = subReporteCombustibleSolido;
	}

	/**
	 * @return the subReporteCombustibleLiquido
	 */
	public List<SubReporteCombustibleBean> getSubReporteCombustibleLiquido() {
		return subReporteCombustibleLiquido;
	}

	/**
	 * @param subReporteCombustibleLiquido the subReporteCombustibleLiquido to
	 *            set
	 */
	public void setSubReporteCombustibleLiquido(List<SubReporteCombustibleBean> subReporteCombustibleLiquido) {
		this.subReporteCombustibleLiquido = subReporteCombustibleLiquido;
	}

}
