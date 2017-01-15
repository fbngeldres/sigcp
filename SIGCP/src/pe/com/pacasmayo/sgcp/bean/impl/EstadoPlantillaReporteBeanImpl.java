package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoPlantillaReporteBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaReporteBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoPlantillaReporteBeanImpl.java
 * Modificado: May 25, 2010 1:59:05 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EstadoPlantillaReporteBeanImpl implements EstadoPlantillaReporteBean {

	private Long codigo;
	private String nombreEstadoPlantillaReporte;
	private List<PlantillaReporteBean> plantillasReporte;

	public EstadoPlantillaReporteBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoPlantillaReporteBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.EstadoPlantillaReporteBean#setCodigo(
	 * java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoPlantillaReporteBean#
	 * getNombreEstadoPlantillaReporte()
	 */
	public String getNombreEstadoPlantillaReporte() {
		return nombreEstadoPlantillaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoPlantillaReporteBean#
	 * setNombreEstadoPlantillaReporte(java.lang.String)
	 */
	public void setNombreEstadoPlantillaReporte(String nombreEstadoPlantillaReporte) {
		this.nombreEstadoPlantillaReporte = nombreEstadoPlantillaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoPlantillaReporteBean#
	 * getPlantillasReporte()
	 */
	public List<PlantillaReporteBean> getPlantillasReporte() {
		return plantillasReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.EstadoPlantillaReporteBean#
	 * setPlantillasReporte(java.util.List)
	 */
	public void setPlantillasReporte(List<PlantillaReporteBean> plantillasReporte) {
		this.plantillasReporte = plantillasReporte;
	}
}
