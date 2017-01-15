package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionVariablesProduccionBean.java
 * Modificado: Nov 30, 2010 4:55:09 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 * Clase qu contiene la cabecera de la notificacion de planta diaria de variables de produccion
 */

public class NotDiariaProduccionRepBean {

	private String division; // division sociedad unidad
	private String sociedad;
	private String unidad;
	private String puestoTrabajo;
	private String productoA;
	private String rango;
	private List<NotVarProduccionPuestoTrabajoRepBean> notPuestoTrabajos = new ArrayList<NotVarProduccionPuestoTrabajoRepBean>();

	public NotDiariaProduccionRepBean() {

	}

	/**
	 * @param division
	 * @param sociedad
	 * @param unidad
	 * @param puestoTrabajo
	 * @param productoA
	 * @param rango
	 * @param notPuestoTrabajos
	 */
	public NotDiariaProduccionRepBean(String division, String sociedad, String unidad, String puestoTrabajo, String productoA,
			String rango, List<NotVarProduccionPuestoTrabajoRepBean> notPuestoTrabajos) {
		super();
		this.division = division;
		this.sociedad = sociedad;
		this.unidad = unidad;
		this.puestoTrabajo = puestoTrabajo;
		this.productoA = productoA;
		this.rango = rango;
		this.notPuestoTrabajos = notPuestoTrabajos;
	}

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

	public String getProductoA() {
		return productoA;
	}

	public void setProductoA(String productoA) {
		this.productoA = productoA;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public List<NotVarProduccionPuestoTrabajoRepBean> getNotPuestoTrabajos() {
		return notPuestoTrabajos;
	}

	public void setNotPuestoTrabajos(List<NotVarProduccionPuestoTrabajoRepBean> notPuestoTrabajos) {
		this.notPuestoTrabajos = notPuestoTrabajos;
	}

}