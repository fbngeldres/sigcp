package pe.com.pacasmayo.sgcp.bean;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: CubicacionBean.java
 * Modificado: Jun 8, 2010 8:43:40 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface CubicacionBean extends EntidadBean {

	public abstract Long getPkCodigoCubicacion();

	public abstract void setPkCodigoCubicacion(Long pkCodigoCubicacion);

	public abstract Cubicacionproducto getCubicacionproducto();

	public abstract void setCubicacionproducto(Cubicacionproducto cubicacionproducto);

	public abstract Medioalmacenamiento getMedioalmacenamiento();

	public abstract void setMedioalmacenamiento(Medioalmacenamiento medioalmacenamiento);

	public abstract Double getVolumenM3Cubicacion();

	public abstract void setVolumenM3Cubicacion(Double volumenM3Cubicacion);

	public abstract Double getAreaCrestaM2Cubicacion();

	public abstract void setAreaCrestaM2Cubicacion(Double areaCrestaM2Cubicacion);

	public abstract Double getAreaPieM2Cubicacion();

	public abstract void setAreaPieM2Cubicacion(Double areaPieM2Cubicacion);

	public abstract Double getDiferenciaNivelMtsCubicacion();

	public abstract void setDiferenciaNivelMtsCubicacion(Double diferenciaNivelMtsCubicacion);

	public abstract String getObservacionCubicacion();

	public abstract void setObservacionCubicacion(String observacionCubicacion);

	public abstract Double getAreaOcupadaM2Cubicacion();

	public abstract void setAreaOcupadaM2Cubicacion(Double areaOcupadaM2Cubicacion);

	public abstract Double getRelacionCubicacion();

	public abstract void setRelacionCubicacion(Double relacionCubicacion);

	public abstract Double getAlturaLadoCarbonMtsCubicaci();

	public abstract void setAlturaLadoCarbonMtsCubicaci(Double alturaLadoCarbonMtsCubicaci);

	public abstract Double getAlturaCentralMtsCubicacion();

	public abstract void setAlturaCentralMtsCubicacion(Double alturaCentralMtsCubicacion);

	public abstract Double getAlturaLadoClinkerMtsCubicac();

	public abstract void setAlturaLadoClinkerMtsCubicac(Double alturaLadoClinkerMtsCubicac);

	public abstract Double getAlturaLibrePromedioMtsCubic();

	public abstract void setAlturaLibrePromedioMtsCubic(Double alturaLibrePromedioMtsCubic);

	public abstract Double getUnidadKgCubicacion();

	public abstract void setUnidadKgCubicacion(Double unidadKgCubicacion);

}