package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.List;

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

/**
 * Clase que representa un registro de la tabla de programas
 */
public class RegistroTablaProgramaDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer pkCodigoProducto;
	private String nombreProducto;
	private String nombreUnidadMedida;
	private Double saldoInicial;
	private Double montoProduccion;
	private Double montoConsumo;
	private Double saldoFinal;
	private String acumuladoPlanificacion;
	private Integer pkCodigoOrdenProduccion;
	private String ordenProduccion;
	private String puestoUno;
	private String siloUno;
	private String puestoDos;
	private String siloDos;
	private String puestoTres;
	private String siloTres;
	private String fecha;
	private Integer codigoPrograma;
	private List<RegistroTablaHoraDTO> ubicaciones;

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

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getMontoProduccion() {
		return montoProduccion;
	}

	public void setMontoProduccion(Double montoProduccion) {
		this.montoProduccion = montoProduccion;
	}

	public Double getMontoConsumo() {
		return montoConsumo;
	}

	public void setMontoConsumo(Double montoConsumo) {
		this.montoConsumo = montoConsumo;
	}

	public Double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(Double saldoFinal) {
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

	@Override
	public String toString() {
		return "\n   -Nombre producto: " + this.nombreProducto + "\n" + "   -Unidad medida: " + this.nombreUnidadMedida + "\n"
				+ "   -Saldo inicial: " + this.saldoInicial + "\n" + "   -Produccion: " + this.montoProduccion + "\n"
				+ "   -Consumo: " + this.montoConsumo + "\n" + "   -Saldo final: " + this.saldoFinal + "\n" + "   -Acumulado: "
				+ this.acumuladoPlanificacion + "\n" + "   -Orden produccion: " + this.ordenProduccion + "\n"
				+ "   -Puesto uno: " + this.puestoUno + "\n" + "   -Silo uno: " + this.siloUno + "\n" + "   -Puesto dos: "
				+ this.siloDos + "\n" + "   -Silo dos: " + this.siloDos + "\n" + "   -Puesto tres: " + this.puestoTres + "\n"
				+ "   -Silo tres: " + this.siloTres + "\n";
	}

	/**
	 * @return the pkCodigoProducto
	 */
	public Integer getPkCodigoProducto() {
		return pkCodigoProducto;
	}

	/**
	 * @param pkCodigoProducto the pkCodigoProducto to set
	 */
	public void setPkCodigoProducto(Integer pkCodigoProducto) {
		this.pkCodigoProducto = pkCodigoProducto;
	}

	public Integer getPkCodigoOrdenProduccion() {
		return pkCodigoOrdenProduccion;
	}

	public void setPkCodigoOrdenProduccion(Integer pkCodigoOrdenProduccion) {
		this.pkCodigoOrdenProduccion = pkCodigoOrdenProduccion;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the codigoPrograma
	 */
	public Integer getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(Integer codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the ubicaciones
	 */
	public List<RegistroTablaHoraDTO> getUbicaciones() {
		return ubicaciones;
	}

	/**
	 * @param ubicaciones the ubicaciones to set
	 */
	public void setUbicaciones(List<RegistroTablaHoraDTO> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
}