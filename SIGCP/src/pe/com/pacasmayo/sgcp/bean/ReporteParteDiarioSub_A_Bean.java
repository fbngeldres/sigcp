package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ReporteParteDiarioSub_A_Bean.java
 * Modificado: May 9, 2011 3:54:49 PM 
 * Autor: Saul Hernandez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ReporteParteDiarioSub_A_Bean {
	private String mes;
	private String anno;
	private List<ReporteParteDiarioSub_A_A_Bean> registroReporteDiarioMes = new ArrayList<ReporteParteDiarioSub_A_A_Bean>();

	/**
	 * 
	 */
	public ReporteParteDiarioSub_A_Bean() {
		super();
	}

	/**
	 * @param mes
	 * @param anno
	 * @param registroReporteDiarioMes
	 */
	public ReporteParteDiarioSub_A_Bean(String mes, String anno, List<ReporteParteDiarioSub_A_A_Bean> registroReporteDiarioMes) {
		super();
		this.mes = mes;
		this.anno = anno;
		this.registroReporteDiarioMes = registroReporteDiarioMes;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public List<ReporteParteDiarioSub_A_A_Bean> getRegistroReporteDiarioMes() {
		return registroReporteDiarioMes;
	}

	public void setRegistroReporteDiarioMes(List<ReporteParteDiarioSub_A_A_Bean> registroReporteDiarioMes) {
		this.registroReporteDiarioMes = registroReporteDiarioMes;
	}

}
