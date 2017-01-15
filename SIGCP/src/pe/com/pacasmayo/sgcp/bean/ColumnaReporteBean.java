package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ColumnaReporteBean.java
 * Modificado: Jun 1, 2010 8:31:53 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ColumnaReporteBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setCodigo(java.lang
	 * .Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getNombreColumnaReporte
	 * ()
	 */
	public abstract String getNombreColumnaReporte();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setNombreColumnaReporte
	 * (java.lang.String)
	 */
	public abstract void setNombreColumnaReporte(String nombreColumnaReporte);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * getPlantillaProductoReporte()
	 */
	public abstract PlantillaReporteBean getPlantillaReporte();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * setPlantillaProductoReporte
	 * (pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean)
	 */
	public abstract void setPlantillaReporte(PlantillaReporteBean plantillaReporte);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getPosicionColumnaReporte
	 * ()
	 */
	public abstract Short getPosicionColumnaReporte();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setPosicionColumnaReporte
	 * (java.lang.Integer)
	 */
	public abstract void setPosicionColumnaReporte(Short posicionColumnaReporte);

	public abstract EstadoColumnaReporteBean getEstadoColumnaReporte();

	public abstract void setEstadoColumnaReporte(EstadoColumnaReporteBean estadoColumnaReporte);
}
