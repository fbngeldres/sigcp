package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n)
 * Archivo: ReporteProductoTerminado_Bean.java
 * Modificado: Jul 25, 2012 4:29:56 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ReporteProductoTerminado_Bean {

	// private Long producto;
	private List<ReporteParteTecnicoSub_A_Bean> subReportePtComponentes;

	private List<ReporteParteTecnicoOperacionesSub_B_Bean> subReportePTOperacionesComponentes;

	private String nombreproducto;

	public List<ReporteParteTecnicoSub_A_Bean> getSubReportePtComponentes() {
		return subReportePtComponentes;
	}

	public void setSubReportePtComponentes(List<ReporteParteTecnicoSub_A_Bean> subReportePtComponentes) {
		this.subReportePtComponentes = subReportePtComponentes;
	}

	public String getNombreproducto() {
		return nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}

	public List<ReporteParteTecnicoOperacionesSub_B_Bean> getSubReportePTOperacionesComponentes() {
		return subReportePTOperacionesComponentes;
	}

	public void setSubReportePTOperacionesComponentes(
			List<ReporteParteTecnicoOperacionesSub_B_Bean> subReportePTOperacionesComponentes) {
		this.subReportePTOperacionesComponentes = subReportePTOperacionesComponentes;
	}

}
