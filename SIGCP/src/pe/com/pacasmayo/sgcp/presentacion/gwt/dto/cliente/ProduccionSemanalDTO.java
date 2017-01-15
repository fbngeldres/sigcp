package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProduccionSemanalDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String usuarioRegistra;
	private Long codigoUsuarioRegistra;
	private ProcesoDTO proceso;
	private String usuarioAprueba;
	private Long codigoUsuarioAprueba;
	private EstadoProduccionSemanalDTO estadoProduccionSemanal;
	private Date fechaInicioSemana;
	private Date fechaFinSemana;
	private Date fechaRegistro;
	private Map<String, List<RegistroTablaProgramaDTO>> mapaProgramas;
	private DivisionDTO division;
	private SociedadDTO sociedad;
	private UnidadDTO unidad;
	private LineaNegocioDTO lineaNegocio;
	private List<ProductosSimultaneosDTO> productos;

	public ProduccionSemanalDTO() {

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getFechaFinSemana()
	 */
	/*
	 * (non-Javadoc) public Long getCodigo() { public Long getCodigo() { return
	 * codigo; } /* (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setCodigo(Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getFechaFinSemana()
	 */
	public Date getFechaFinSemana() {
		return fechaFinSemana;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setFechaFinSemana
	 * (java.util.Date)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setFechaFinSemana
	 * (java.util.Date)
	 */
	public void setFechaFinSemana(Date fechaFinSemana) {
		this.fechaFinSemana = fechaFinSemana;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getFechaInicioSemana
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getFechaInicioSemana
	 * ()
	 */
	public Date getFechaInicioSemana() {
		return fechaInicioSemana;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setFechaInicioSemana
	 * (java.util.Date)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setFechaInicioSemana
	 * (java.util.Date)
	 */
	public void setFechaInicioSemana(Date fechaInicioSemana) {
		this.fechaInicioSemana = fechaInicioSemana;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getFechaRegistro()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getFechaRegistro()
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setFechaRegistro
	 * (java.util.Date)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setFechaRegistro
	 * (java.util.Date)
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getProceso()
	 */
	public ProcesoDTO getProceso() {
		return proceso;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setProceso(pe.com
	 * .pacasmayo.sgcp.bean.ProcesoBean)
	 */
	public void setProceso(ProcesoDTO proceso) {
		this.proceso = proceso;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getUsuarioRegistra
	 * ()
	 */
	public String getUsuarioRegistra() {
		return usuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setUsuarioRegistra
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioRegistra(String usuarioRegistra) {
		this.usuarioRegistra = usuarioRegistra;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#getUsuarioAprueba()
	 */
	public String getUsuarioAprueba() {
		return usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#setUsuarioAprueba
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioAprueba(String usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#
	 * getEstadoProduccionSemanal()
	 */
	public EstadoProduccionSemanalDTO getEstadoProduccionSemanal() {
		return estadoProduccionSemanal;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProduccionSemanalBean#
	 * setEstadoProduccionSemanal
	 * (pe.com.pacasmayo.sgcp.bean.EstadoProduccionSemanalBean)
	 */
	public void setEstadoProduccionSemanal(EstadoProduccionSemanalDTO estadoProduccionSemanal) {
		this.estadoProduccionSemanal = estadoProduccionSemanal;
	}

	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @return the codigoUsuarioAprueba
	 */
	public Long getCodigoUsuarioAprueba() {
		return codigoUsuarioAprueba;
	}

	/**
	 * @param codigoUsuarioAprueba the codigoUsuarioAprueba to set
	 */
	public void setCodigoUsuarioAprueba(Long codigoUsuarioAprueba) {
		this.codigoUsuarioAprueba = codigoUsuarioAprueba;
	}

	/**
	 * @return the division
	 */
	public DivisionDTO getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(DivisionDTO division) {
		this.division = division;
	}

	/**
	 * @return the sociedad
	 */
	public SociedadDTO getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(SociedadDTO sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the unidad
	 */
	public UnidadDTO getUnidad() {
		return unidad;
	}

	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(UnidadDTO unidad) {
		this.unidad = unidad;
	}

	/**
	 * @return the lineaNegocio
	 */
	public LineaNegocioDTO getLineaNegocio() {
		return lineaNegocio;
	}

	/**
	 * @param lineaNegocio the lineaNegocio to set
	 */
	public void setLineaNegocio(LineaNegocioDTO lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	/**
	 * @return the codigoUsuarioRegistra
	 */
	public Long getCodigoUsuarioRegistra() {
		return codigoUsuarioRegistra;
	}

	/**
	 * @param codigoUsuarioRegistra the codigoUsuarioRegistra to set
	 */
	public void setCodigoUsuarioRegistra(Long codigoUsuarioRegistra) {
		this.codigoUsuarioRegistra = codigoUsuarioRegistra;
	}

	/**
	 * @return the mapaProgramas
	 */
	public Map<String, List<RegistroTablaProgramaDTO>> getMapaProgramas() {
		return mapaProgramas;
	}

	/**
	 * @param mapaProgramas the mapaProgramas to set
	 */
	public void setMapaProgramas(Map<String, List<RegistroTablaProgramaDTO>> mapaProgramas) {
		this.mapaProgramas = mapaProgramas;
	}

	public List<ProductosSimultaneosDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosSimultaneosDTO> productos) {
		this.productos = productos;
	}

}