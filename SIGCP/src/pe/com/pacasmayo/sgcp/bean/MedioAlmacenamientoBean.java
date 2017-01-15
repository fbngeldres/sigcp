package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MedioAlmacenamientoBean.java
 * Modificado: Feb 22, 2010 11:03:14 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface MedioAlmacenamientoBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract UbicacionBean getUbicacion();

	public abstract void setUbicacion(UbicacionBean ubicacion);

	public abstract TipoMedioAlmacenamientoBean getTipoMedioAlmacenamiento();

	public abstract void setTipoMedioAlmacenamiento(TipoMedioAlmacenamientoBean tipoMedioAlmacenamiento);

	public abstract PuestoTrabajoBean getPuestoTrabajo();

	public abstract void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo);

	public abstract ProduccionBean getProduccion();

	public abstract void setProduccion(ProduccionBean produccion);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

	public abstract Short getNumero();

	public abstract void setNumero(Short numero);

	public abstract Double getCapacidadMaxima();

	public abstract void setCapacidadMaxima(Double capacidadMaxima);

	public abstract Double getCapacidadMinima();

	public abstract void setCapacidadMinima(Double capacidadMinima);

	public abstract Double getDensidad();

	public abstract void setDensidad(Double densidad);

	public abstract Long getNumeroAlturas();

	public abstract void setNumeroAlturas(Long numeroAlturas);

	public abstract Double getAlturaEspecifica();

	public abstract void setAlturaEspecifica(Double alturaEspecifica);

	public abstract Double getFactorMetrosCubicos();

	public abstract void setFactorMetrosCubicos(Double factorMetrosCubicos);

	public abstract Double getStockSeguridad();

	public abstract void setStockSeguridad(Double stockSeguridad);

}