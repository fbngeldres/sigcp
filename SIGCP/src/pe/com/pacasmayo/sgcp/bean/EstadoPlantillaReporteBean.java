package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoPlantillaReporteBean.java
 * Modificado: May 25, 2010 2:05:12 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoPlantillaReporteBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract String getNombreEstadoPlantillaReporte();

	public abstract void setNombreEstadoPlantillaReporte(String nombreEstadoPlantillaReporte);

	public abstract List<PlantillaReporteBean> getPlantillasReporte();

	public abstract void setPlantillasReporte(List<PlantillaReporteBean> plantillasReporte);

}
