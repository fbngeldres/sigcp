package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n)
 * Archivo: ReporteParteTecnicoAnexo_Bean.java
 * Modificado: Jul 25, 2012 6:24:49 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ReporteAnexo_Bean {
	private String titulo;
	private List<ReporteParteTecnicoSub_B_Bean> reporteClinker;
	private List<ReporteParteTecnicoSub_B_Bean> reporteCemento;
	private List<ReporteParteTecnicoSub_B_Bean> reporteCrudo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<ReporteParteTecnicoSub_B_Bean> getReporteClinker() {
		return reporteClinker;
	}

	public void setReporteClinker(List<ReporteParteTecnicoSub_B_Bean> reporteClinker) {
		this.reporteClinker = reporteClinker;
	}

	public List<ReporteParteTecnicoSub_B_Bean> getReporteCemento() {
		return reporteCemento;
	}

	public void setReporteCemento(List<ReporteParteTecnicoSub_B_Bean> reporteCemento) {
		this.reporteCemento = reporteCemento;
	}

	public List<ReporteParteTecnicoSub_B_Bean> getReporteCrudo() {
		return reporteCrudo;
	}

	public void setReporteCrudo(List<ReporteParteTecnicoSub_B_Bean> reporteCrudo) {
		this.reporteCrudo = reporteCrudo;
	}

}
