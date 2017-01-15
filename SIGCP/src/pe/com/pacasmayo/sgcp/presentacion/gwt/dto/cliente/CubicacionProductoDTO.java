package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: CubicacionProductoDTO.java
 * Modificado: Jun 16, 2010 10:02:37 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class CubicacionProductoDTO implements Serializable {

	private Long pkCodigoCubicacionproducto;
	private Long codigoUsuarioByFkCodigoUsuarioRegistra;
	private Long codigoUsuarioByFkCodigoUsuario;
	private Long codigoUsuarioByFkCodigoUsuarioAprueba;
	private ProduccionDTO produccion;
	private List<TablaCubicacionDTO> cubicaciones;
	private Date fechaCubicacionproducto;
	private Double stockFisicoCubicacionproducto;
	private Short mesCubicacionproducto;
	private Integer anoCubicacionproducto;
	private Double toneladasCubicacionproducto;

	private Long codigoDivision;
	private Long codigoSociedad;
	private Long codigoUnidad;
	private Long codigoLineanegocio;
	private Long codigoProceso;
	private Long codigoProducto;
	private Long codigoEstadocubicacion;

	private String nombreDivision;
	private String nombreSociedad;
	private String nombreUnidad;
	private String nombreLineaNegocio;
	private String nombreProceso;
	private String nombreProducto;
	private String nombreEstado;

	private boolean aprobadoAnulado = true;

	public Long getPkCodigoCubicacionproducto() {
		return pkCodigoCubicacionproducto;
	}

	public void setPkCodigoCubicacionproducto(Long pkCodigoCubicacionproducto) {
		this.pkCodigoCubicacionproducto = pkCodigoCubicacionproducto;
	}

	public Long getCodigoUsuarioByFkCodigoUsuarioRegistra() {
		return codigoUsuarioByFkCodigoUsuarioRegistra;
	}

	public void setCodigoUsuarioByFkCodigoUsuarioRegistra(Long codigoUsuarioByFkCodigoUsuarioRegistra) {
		this.codigoUsuarioByFkCodigoUsuarioRegistra = codigoUsuarioByFkCodigoUsuarioRegistra;
	}

	public Long getCodigoUsuarioByFkCodigoUsuario() {
		return codigoUsuarioByFkCodigoUsuario;
	}

	public void setCodigoUsuarioByFkCodigoUsuario(Long codigoUsuarioByFkCodigoUsuario) {
		this.codigoUsuarioByFkCodigoUsuario = codigoUsuarioByFkCodigoUsuario;
	}

	public Long getCodigoEstadocubicacion() {
		return codigoEstadocubicacion;
	}

	public void setCodigoEstadocubicacion(Long codigoEstadocubicacion) {
		this.codigoEstadocubicacion = codigoEstadocubicacion;
	}

	public Long getCodigoLineanegocio() {
		return codigoLineanegocio;
	}

	public void setCodigoLineanegocio(Long codigoLineanegocio) {
		this.codigoLineanegocio = codigoLineanegocio;
	}

	public Long getCodigoUsuarioByFkCodigoUsuarioAprueba() {
		return codigoUsuarioByFkCodigoUsuarioAprueba;
	}

	public void setCodigoUsuarioByFkCodigoUsuarioAprueba(Long codigoUsuarioByFkCodigoUsuarioAprueba) {
		this.codigoUsuarioByFkCodigoUsuarioAprueba = codigoUsuarioByFkCodigoUsuarioAprueba;
	}

	public ProduccionDTO getProduccion() {
		return produccion;
	}

	public void setProduccion(ProduccionDTO produccionDTO) {
		this.produccion = produccionDTO;
	}

	public Date getFechaCubicacionproducto() {
		return fechaCubicacionproducto;
	}

	public void setFechaCubicacionproducto(Date fechaCubicacionproducto) {
		this.fechaCubicacionproducto = fechaCubicacionproducto;
	}

	public Double getStockFisicoCubicacionproducto() {
		return stockFisicoCubicacionproducto;
	}

	public void setStockFisicoCubicacionproducto(Double stockFisicoCubicacionproducto) {
		this.stockFisicoCubicacionproducto = stockFisicoCubicacionproducto;
	}

	public Short getMesCubicacionproducto() {
		return mesCubicacionproducto;
	}

	public void setMesCubicacionproducto(Short mesCubicacionproducto) {
		this.mesCubicacionproducto = mesCubicacionproducto;
	}

	public Integer getAnoCubicacionproducto() {
		return anoCubicacionproducto;
	}

	public void setAnoCubicacionproducto(Integer anoCubicacionproducto) {
		this.anoCubicacionproducto = anoCubicacionproducto;
	}

	public Double getToneladasCubicacionproducto() {
		return toneladasCubicacionproducto;
	}

	public void setToneladasCubicacionproducto(Double toneladasCubicacionproducto) {
		this.toneladasCubicacionproducto = toneladasCubicacionproducto;
	}

	public String getNombreDivision() {
		return nombreDivision;
	}

	public void setNombreDivision(String nombreDivision) {
		this.nombreDivision = nombreDivision;
	}

	public String getNombreSociedad() {
		return nombreSociedad;
	}

	public void setNombreSociedad(String nombreSociedad) {
		this.nombreSociedad = nombreSociedad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public String getNombreLineaNegocio() {
		return nombreLineaNegocio;
	}

	public void setNombreLineaNegocio(String nombreLineaNegocio) {
		this.nombreLineaNegocio = nombreLineaNegocio;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Long getCodigoDivision() {
		return codigoDivision;
	}

	public void setCodigoDivision(Long codigoDivision) {
		this.codigoDivision = codigoDivision;
	}

	public Long getCodigoSociedad() {
		return codigoSociedad;
	}

	public void setCodigoSociedad(Long codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	public Long getCodigoUnidad() {
		return codigoUnidad;
	}

	public void setCodigoUnidad(Long codigoUnidad) {
		this.codigoUnidad = codigoUnidad;
	}

	public Long getCodigoProceso() {
		return codigoProceso;
	}

	public void setCodigoProceso(Long codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public List<TablaCubicacionDTO> getCubicaciones() {
		return cubicaciones;
	}

	public void setCubicaciones(List<TablaCubicacionDTO> cubicaciones) {
		this.cubicaciones = cubicaciones;
	}

	public boolean isAprobadoAnulado() {
		return aprobadoAnulado;
	}

	public void setAprobadoAnulado(boolean aprobadoAnulado) {
		this.aprobadoAnulado = aprobadoAnulado;
	}

}
