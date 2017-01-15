package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ResumenNotificacionItemRepBean.java
 * Modificado: Aug 5, 2011 8:10:57 AM 
 * Autor: Andrey Bottoni
 *
 * Copyright (C) DBAccess, 2011. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ResumenNotificacionItemRepBean {

	private String fecha;
	private String nombreProducto;
	private String nombreComponente;
	private String cantidad;
	private String horasProduccion;

	public ResumenNotificacionItemRepBean() {

	}

	public ResumenNotificacionItemRepBean(String fecha, String nombreProducto, String nombreComponente, String cantidad,
			String horasProduccion) {
		super();
		this.fecha = fecha;
		this.nombreProducto = nombreProducto;
		this.nombreComponente = nombreComponente;
		this.cantidad = cantidad;
		this.horasProduccion = horasProduccion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreComponente() {
		return nombreComponente;
	}

	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getHorasProduccion() {
		return horasProduccion;
	}

	public void setHorasProduccion(String horasProduccion) {
		this.horasProduccion = horasProduccion;
	}

	public String toString() {
		return "Fecha: " + fecha + "\tProducto: " + nombreProducto + "\tComponente: " + nombreComponente + "\tCantidad: "
				+ cantidad + "\tHoras: " + horasProduccion;
	}
}
