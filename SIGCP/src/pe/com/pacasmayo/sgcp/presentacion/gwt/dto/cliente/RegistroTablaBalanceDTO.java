package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroTablaBalanceDTO.java
 * Modificado: Sep 27, 2010 10:00:44 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class RegistroTablaBalanceDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoConcepto; // Balance, Ajuste, Total
	private double saldoInicial;
	private double saldoFinal;
	private double ingreso;
	private double consumo;
	private double consumoPorAjuste;

	public String getTipoConcepto() {
		return tipoConcepto;
	}

	public void setTipoConcepto(String tipoConcepto) {
		this.tipoConcepto = tipoConcepto;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public double getIngreso() {
		return ingreso;
	}

	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public double getConsumoPorAjuste() {
		return consumoPorAjuste;
	}

	public void setConsumoPorAjuste(double consumoPorAjuste) {
		this.consumoPorAjuste = consumoPorAjuste;
	}

	public Double getLibros() {
		return saldoInicial + ingreso - consumo;
	}

}
