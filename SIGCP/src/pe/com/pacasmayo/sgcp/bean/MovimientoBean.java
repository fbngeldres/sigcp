package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: MovimientoBean.java
 * Modificado: May 25, 2010 10:42:30 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
import java.util.Date;

public interface MovimientoBean extends EntidadBean {

	public abstract Long getPkCodigoMovimiento();

	public abstract void setPkCodigoMovimiento(Long pkCodigoMovimiento);

	public abstract UnidadMedidaBean getUnidadmedida();

	public abstract void setUnidadmedida(UnidadMedidaBean unidadmedida);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract EstadoMovimientoBean getEstadomovimiento();

	public abstract void setEstadomovimiento(EstadoMovimientoBean estadomovimiento);

	public abstract UbicacionBean getUbicacionByFkCodigoUbicacionDestino();

	public abstract void setUbicacionByFkCodigoUbicacionDestino(UbicacionBean ubicacionByFkCodigoUbicacionDestino);

	public abstract LineaNegocioBean getLineanegocio();

	public abstract void setLineanegocio(LineaNegocioBean lineanegocio);

	public abstract UbicacionBean getUbicacionByFkCodigoUbicacionOrigen();

	public abstract void setUbicacionByFkCodigoUbicacionOrigen(UbicacionBean ubicacionByFkCodigoUbicacionOrigen);

	public abstract TipoMovimientoBean getTipomovimiento();

	public abstract void setTipomovimiento(TipoMovimientoBean tipomovimiento);

	public abstract MedioAlmacenamientoBean getMedioalmacenamiento();

	public abstract void setMedioalmacenamiento(MedioAlmacenamientoBean medioalmacenamiento);

	public abstract DocumentoMaterialBean getDocumentomaterial();

	public abstract void setDocumentomaterial(DocumentoMaterialBean documentomaterial);

	public abstract Double getCantidadMovimiento();

	public abstract void setCantidadMovimiento(Double cantidadMovimiento);

	public abstract Date getFechaMovimiento();

	public abstract void setFechaMovimiento(Date fechaMovimiento);

	public abstract Double getFactorVolqueteMovimiento();

	public abstract void setFactorVolqueteMovimiento(Double factorVolqueteMovimiento);

	public abstract Long getNumeroViajesMovimiento();

	public abstract void setNumeroViajesMovimiento(Long numeroViajesMovimiento);

	public abstract String getOrigenMovimiento();

	public abstract void setOrigenMovimiento(String origenMovimiento);

	public abstract String getNombreTipoDocumentoMaterialDetallado();

	public abstract void setNombreTipoDocumentoMaterialDetallado(String nombreTipoDocumentoMaterialDetallado);

	public abstract String getNombreAlmacen();

	public abstract void setNombreAlmacen(String nombreAlmacen);

	public abstract String getNombreUbicacion();

	public abstract void setNombreUbicacion(String nombreUbicacion);

	public abstract String getTipoMovimientoSalidaCSS();

	public abstract void setTipoMovimientoSalidaCSS(String tipoMovimientoSalidaCSS);

	public abstract Double getCantidadMovimientoHumedad();

	public abstract void setCantidadMovimientoHumedad(Double cantidadMovimientoHumedad);

	public abstract Double getFactorHumedad();

	public abstract void setFactorHumedad(Double factorHumedad);

	public Boolean getIsMovimientoLogistico();

	public void setIsMovimientoLogistico(Boolean isMovimientoLogistico);

	public String getCodigoSapproducto();

	public void setCodigoSapproducto(String codigoSapproducto);
}