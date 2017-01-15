package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ResumenNotificacionRepBean.java
 * Modificado: Aug 5, 2011 8:09:08 AM 
 * Autor: Andrey Bottoni
 *
 * Copyright (C) DBAccess, 2011. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ResumenNotificacionRepBean {

	private String division;
	private String sociedad;
	private String unidad;
	private String puestoTrabajo;
	private String producto;
	private String rango;
	private List<ResumenNotificacionItemRepBean> reporteItems = new ArrayList<ResumenNotificacionItemRepBean>();

	public ResumenNotificacionRepBean() {

	}

	public ResumenNotificacionRepBean(String division, String sociedad, String unidad, String puestoTrabajo, String producto,
			String rango, List<ResumenNotificacionItemRepBean> reporteItems) {
		super();
		this.division = division;
		this.sociedad = sociedad;
		this.unidad = unidad;
		this.puestoTrabajo = puestoTrabajo;
		this.producto = producto;
		this.rango = rango;
		this.reporteItems = reporteItems;
	}

	/** GETTERS Y SETTERS * */

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSociedad() {
		return sociedad;
	}

	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public List<ResumenNotificacionItemRepBean> getReporteItems() {
		return reporteItems;
	}

	public void setReporteItems(List<ResumenNotificacionItemRepBean> reporteItems) {
		this.reporteItems = reporteItems;
	}

	/*
	 * public String toString() { return "Division: " + division +
	 * "\tSociedad: " + sociedad + "\tUnidad: " + unidad + "\tPuestoTrabajo: " +
	 * puestoTrabajo + "\tProducto: " + producto + "\tRango: " + rango; }
	 */
}
