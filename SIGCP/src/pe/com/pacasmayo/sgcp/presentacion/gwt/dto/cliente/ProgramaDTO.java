package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TablaPrograma.java
 * Modificado: Feb 15, 2010 9:23:55 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ProgramaDTO {

	private String nombreProducto;
	private String nombreUnidadMedida;
	private String saldoInicial;
	private String montoProduccion;
	private String montoConsumo;
	private String saldoFinal;
	private String acumuladoPlanificacion;
	private String ordenProduccion;
	private String puestoUno;
	private String siloUno;
	private String puestoDos;
	private String siloDos;
	private String puestoTres;
	private String siloTres;

	public String getPuestoUno() {
		return puestoUno;
	}

	public void setPuestoUno(String puestoUno) {
		this.puestoUno = puestoUno;
	}

	public String getSiloUno() {
		return siloUno;
	}

	public void setSiloUno(String siloUno) {
		this.siloUno = siloUno;
	}

	public String getPuestoDos() {
		return puestoDos;
	}

	public void setPuestoDos(String puestoDos) {
		this.puestoDos = puestoDos;
	}

	public String getSiloDos() {
		return siloDos;
	}

	public void setSiloDos(String siloDos) {
		this.siloDos = siloDos;
	}

	public String getPuestoTres() {
		return puestoTres;
	}

	public void setPuestoTres(String puestoTres) {
		this.puestoTres = puestoTres;
	}

	public String getSiloTres() {
		return siloTres;
	}

	public void setSiloTres(String siloTres) {
		this.siloTres = siloTres;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreUnidadMedida() {
		return nombreUnidadMedida;
	}

	public void setNombreUnidadMedida(String nombreUnidadMedida) {
		this.nombreUnidadMedida = nombreUnidadMedida;
	}

	public String getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(String saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getMontoProduccion() {
		return montoProduccion;
	}

	public void setMontoProduccion(String montoProduccion) {
		this.montoProduccion = montoProduccion;
	}

	public String getMontoConsumo() {
		return montoConsumo;
	}

	public void setMontoConsumo(String montoConsumo) {
		this.montoConsumo = montoConsumo;
	}

	public String getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(String saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public String getAcumuladoPlanificacion() {
		return acumuladoPlanificacion;
	}

	public void setAcumuladoPlanificacion(String acumuladoPlanificacion) {
		this.acumuladoPlanificacion = acumuladoPlanificacion;
	}

	public String getOrdenProduccion() {
		return ordenProduccion;
	}

	public void setOrdenProduccion(String ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}

	public String toString() {
		return "\n   -Nombre producto: " + this.nombreProducto + "\n" + "   -Unidad medida: " + this.nombreUnidadMedida + "\n"
				+ "   -Saldo inicial: " + this.saldoInicial + "\n" + "   -Produccion: " + this.montoProduccion + "\n"
				+ "   -Consumo: " + this.montoConsumo + "\n" + "   -Saldo final: " + this.saldoFinal + "\n" + "   -Acumulado: "
				+ this.acumuladoPlanificacion + "\n" + "   -Orden produccion: " + this.ordenProduccion + "\n"
				+ "   -Puesto uno: " + this.puestoUno + "\n" + "   -Silo uno: " + this.siloUno + "\n" + "   -Puesto dos: "
				+ this.siloDos + "\n" + "   -Silo dos: " + this.siloDos + "\n" + "   -Puesto tres: " + this.puestoTres + "\n"
				+ "   -Silo tres: " + this.siloTres + "\n";
	}
}