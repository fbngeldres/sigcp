package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: MoviminetoBeanImpl.java
 * Modificado: May 25, 2010 10:42:30 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;

import pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean;
import pe.com.pacasmayo.sgcp.bean.EstadoMovimientoBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.MedioAlmacenamientoBean;
import pe.com.pacasmayo.sgcp.bean.MovimientoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.TipoMovimientoBean;
import pe.com.pacasmayo.sgcp.bean.UbicacionBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class MovimientoBeanImpl extends EntidadBeanImpl implements MovimientoBean {

	private static final String TRANSFERENCIA = "transferencia";
	private Long pkCodigoMovimiento;
	private Double cantidadMovimiento;
	private Date fechaMovimiento;
	private Double factorVolqueteMovimiento;
	private Long numeroViajesMovimiento;
	private String origenMovimiento;

	private UnidadMedidaBean unidadmedida;
	private ProductoBean producto;
	private EstadoMovimientoBean estadomovimiento;
	private UbicacionBean ubicacionByFkCodigoUbicacionDestino;
	private UbicacionBean ubicacionByFkCodigoUbicacionOrigen;
	private LineaNegocioBean lineanegocio;
	private TipoMovimientoBean tipomovimiento;
	private MedioAlmacenamientoBean medioalmacenamiento;
	private DocumentoMaterialBean documentomaterial;
	private Double factorHumedad = 0d;
	private Double cantidadMovimientoHumedad;
	private String codigoSapproducto;
	
	
	private Boolean isMovimientoLogistico = false;

	public MovimientoBeanImpl() {
		unidadmedida = new UnidadMedidaBeanImpl();
		producto = new ProductoBeanImpl();
		estadomovimiento = new EstadoMovimientoBeanImpl();
		ubicacionByFkCodigoUbicacionDestino = new UbicacionBeanImpl();
		ubicacionByFkCodigoUbicacionOrigen = new UbicacionBeanImpl();
		lineanegocio = new LineaNegocioBeanImpl();
		tipomovimiento = new TipoMovimientoBeanImpl();
		medioalmacenamiento = new MedioAlmacenamientoBeanImpl();
		documentomaterial = new DocumentoMaterialBeanImpl();
	}

	public Double getCantidadMovimiento() {
		return cantidadMovimiento;
	}

	public DocumentoMaterialBean getDocumentomaterial() {
		return documentomaterial;
	}

	public EstadoMovimientoBean getEstadomovimiento() {
		return estadomovimiento;
	}

	public Double getFactorVolqueteMovimiento() {
		return factorVolqueteMovimiento;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public LineaNegocioBean getLineanegocio() {
		return lineanegocio;
	}

	public MedioAlmacenamientoBean getMedioalmacenamiento() {
		return medioalmacenamiento;
	}

	public Long getNumeroViajesMovimiento() {
		return numeroViajesMovimiento;
	}

	public String getOrigenMovimiento() {
		return origenMovimiento;
	}

	public Long getPkCodigoMovimiento() {
		return pkCodigoMovimiento;
	}

	public ProductoBean getProducto() {
		return producto;
	}

	public TipoMovimientoBean getTipomovimiento() {
		return tipomovimiento;
	}

	public UbicacionBean getUbicacionByFkCodigoUbicacionDestino() {
		return ubicacionByFkCodigoUbicacionDestino;
	}

	public UbicacionBean getUbicacionByFkCodigoUbicacionOrigen() {
		return ubicacionByFkCodigoUbicacionOrigen;
	}

	public UnidadMedidaBean getUnidadmedida() {
		return unidadmedida;
	}

	public void setCantidadMovimiento(Double cantidadMovimiento) {
		this.cantidadMovimiento = cantidadMovimiento;
	}

	public void setDocumentomaterial(DocumentoMaterialBean documentomaterial) {
		this.documentomaterial = documentomaterial;
	}

	public void setEstadomovimiento(EstadoMovimientoBean estadomovimiento) {
		this.estadomovimiento = estadomovimiento;
	}

	public void setFactorVolqueteMovimiento(Double factorVolqueteMovimiento) {
		this.factorVolqueteMovimiento = factorVolqueteMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public void setLineanegocio(LineaNegocioBean lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public void setMedioalmacenamiento(MedioAlmacenamientoBean medioalmacenamiento) {
		this.medioalmacenamiento = medioalmacenamiento;
	}

	public void setNumeroViajesMovimiento(Long numeroViajesMovimiento) {
		this.numeroViajesMovimiento = numeroViajesMovimiento;
	}

	public void setOrigenMovimiento(String origenMovimiento) {
		this.origenMovimiento = origenMovimiento;
	}

	public void setPkCodigoMovimiento(Long pkCodigoMovimiento) {
		this.pkCodigoMovimiento = pkCodigoMovimiento;
	}

	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	public void setTipomovimiento(TipoMovimientoBean tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public void setUbicacionByFkCodigoUbicacionDestino(UbicacionBean ubicacionByFkCodigoUbicacionDestino) {
		this.ubicacionByFkCodigoUbicacionDestino = ubicacionByFkCodigoUbicacionDestino;
	}

	public void setUbicacionByFkCodigoUbicacionOrigen(UbicacionBean ubicacionByFkCodigoUbicacionOrigen) {
		this.ubicacionByFkCodigoUbicacionOrigen = ubicacionByFkCodigoUbicacionOrigen;
	}

	public void setUnidadmedida(UnidadMedidaBean unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public String getNombreTipoDocumentoMaterialDetallado() {

		String nombreTipoDocumentoMaterialDetallado = documentomaterial.getTipodocumentomaterial().getNombre();

		if (nombreTipoDocumentoMaterialDetallado.compareToIgnoreCase(TRANSFERENCIA) == 0
				&& tipomovimiento.getClasificacionTipoMovimiento().getCodigo().intValue() == 1) {
			nombreTipoDocumentoMaterialDetallado += " - ";
			nombreTipoDocumentoMaterialDetallado += "I";
		} else if (nombreTipoDocumentoMaterialDetallado.compareToIgnoreCase(TRANSFERENCIA) == 0
				&& tipomovimiento.getClasificacionTipoMovimiento().getCodigo().intValue() == 2) {
			nombreTipoDocumentoMaterialDetallado += " - ";
			nombreTipoDocumentoMaterialDetallado += "S";
		}

		return nombreTipoDocumentoMaterialDetallado;
	}

	public void setNombreTipoDocumentoMaterialDetallado(String nombreTipoDocumentoMaterialDetallado) {

	}

	public String getNombreAlmacen() {
		String nombreTipoDocumentoMaterialDetallado = documentomaterial.getTipodocumentomaterial().getNombre();
		if (nombreTipoDocumentoMaterialDetallado.compareToIgnoreCase(TRANSFERENCIA) == 0) {
			if (ubicacionByFkCodigoUbicacionDestino != null) {
				return ubicacionByFkCodigoUbicacionDestino.getAlmacen().getNombre();
			}

		}
		if (ubicacionByFkCodigoUbicacionOrigen != null && ubicacionByFkCodigoUbicacionOrigen.getAlmacen() != null) {
			return ubicacionByFkCodigoUbicacionOrigen.getAlmacen().getNombre();
		}
		return "";
	}

	public String getNombreUbicacion() {
		String nombreTipoDocumentoMaterialDetallado = documentomaterial.getTipodocumentomaterial().getNombre();
		if (nombreTipoDocumentoMaterialDetallado.compareToIgnoreCase(TRANSFERENCIA) == 0) {
			if (ubicacionByFkCodigoUbicacionDestino != null) {
				return ubicacionByFkCodigoUbicacionDestino.getNombre();
			}

		}
		if (ubicacionByFkCodigoUbicacionOrigen != null) {
			return ubicacionByFkCodigoUbicacionOrigen.getNombre();
		}
		return "";
	}

	public void setNombreAlmacen(String nombreAlmacen) {
	}

	public void setNombreUbicacion(String nombreUbicacion) {
	}

	public boolean isTipoMovimientoSalida() {
		if (tipomovimiento.getClasificacionTipoMovimiento().getNombre().compareToIgnoreCase("Salida") == 0) {
			return true;
		}

		return false;
	}

	public String getTipoMovimientoSalidaCSS() {

		if (isTipoMovimientoSalida()) {
			return "light_gray_background";
		}
		return null;
	}

	public void setTipoMovimientoSalidaCSS(String tipoMovimientoSalidaCSS) {
	}

	public Double getCantidadMovimientoHumedad() {
		return cantidadMovimientoHumedad;
	}

	public void setCantidadMovimientoHumedad(Double cantidadMovimientoHumedad) {
		this.cantidadMovimientoHumedad = cantidadMovimientoHumedad;
	}

	public Double getFactorHumedad() {
		return factorHumedad;
	}

	public void setFactorHumedad(Double factorHumedad) {
		this.factorHumedad = factorHumedad;
	}

	public Boolean getIsMovimientoLogistico() {
		return isMovimientoLogistico;
	}

	public void setIsMovimientoLogistico(Boolean isMovimientoLogistico) {
		this.isMovimientoLogistico = isMovimientoLogistico;
	}

	public String getCodigoSapproducto() {
		return codigoSapproducto;
	}

	public void setCodigoSapproducto(String codigoSapproducto) {
		this.codigoSapproducto = codigoSapproducto;
	}
}
