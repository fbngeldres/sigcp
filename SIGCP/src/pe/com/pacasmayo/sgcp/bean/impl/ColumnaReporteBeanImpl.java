package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ColumnaReporteBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ColumnaReporteBean;
import pe.com.pacasmayo.sgcp.bean.EstadoColumnaReporteBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaReporteBean;

public class ColumnaReporteBeanImpl implements ColumnaReporteBean {

	private Long codigo;
	private PlantillaReporteBean plantillaReporte;
	private String nombreColumnaReporte;
	private Short posicionColumnaReporte;
	private EstadoColumnaReporteBean estadoColumnaReporte;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setCodigo(java.lang
	 * .Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getNombreColumnaReporte
	 * ()
	 */
	public String getNombreColumnaReporte() {
		return nombreColumnaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setNombreColumnaReporte
	 * (java.lang.String)
	 */
	public void setNombreColumnaReporte(String nombreColumnaReporte) {
		this.nombreColumnaReporte = nombreColumnaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * getPlantillaProductoReporte()
	 */
	public PlantillaReporteBean getPlantillaReporte() {
		return plantillaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * setPlantillaProductoReporte
	 * (pe.com.pacasmayo.sgcp.bean.PlantillaReporteBean)
	 */
	public void setPlantillaReporte(PlantillaReporteBean plantillaReporte) {
		this.plantillaReporte = plantillaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getPosicionColumnaReporte
	 * ()
	 */
	public Short getPosicionColumnaReporte() {
		return posicionColumnaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setPosicionColumnaReporte
	 * (java.lang.Short)
	 */
	public void setPosicionColumnaReporte(Short posicionColumnaReporte) {
		this.posicionColumnaReporte = posicionColumnaReporte;
	}

	public EstadoColumnaReporteBean getEstadoColumnaReporte() {
		return estadoColumnaReporte;
	}

	public void setEstadoColumnaReporte(EstadoColumnaReporteBean estadoColumnaReporte) {
		this.estadoColumnaReporte = estadoColumnaReporte;
	}
}
