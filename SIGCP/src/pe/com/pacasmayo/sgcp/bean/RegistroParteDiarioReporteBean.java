package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroParteDiarioReporteBean.java
 * Modificado: Jan 31, 2011 6:12:44 PM 
 * Autor: usiclon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class RegistroParteDiarioReporteBean {
	private String Fecha;
	private List<SubRegistroParteDiarioReporteBean> registrosReporteParteDiario = new ArrayList<SubRegistroParteDiarioReporteBean>();

	/**
	 * @param fecha
	 * @param registrosReporteParteDiario
	 */
	public RegistroParteDiarioReporteBean(String fecha, List<SubRegistroParteDiarioReporteBean> registrosReporteParteDiario) {
		super();
		Fecha = fecha;
		this.registrosReporteParteDiario = registrosReporteParteDiario;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return Fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	/**
	 * @return the registrosReporteParteDiario
	 */
	public List<SubRegistroParteDiarioReporteBean> getRegistrosReporteParteDiario() {
		return registrosReporteParteDiario;
	}

	/**
	 * @param registrosReporteParteDiario the registrosReporteParteDiario to set
	 */
	public void setRegistrosReporteParteDiario(List<SubRegistroParteDiarioReporteBean> registrosReporteParteDiario) {
		this.registrosReporteParteDiario = registrosReporteParteDiario;
	}

}
