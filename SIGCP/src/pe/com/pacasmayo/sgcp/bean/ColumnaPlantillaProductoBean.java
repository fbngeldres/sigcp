package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ColumnaPlantillaProductoBean.java
 * Modificado: Jun 1, 2010 8:36:55 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ColumnaPlantillaProductoBean {

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
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getVariableOperacion()
	 */
	public abstract VariableOperacionBean getVariableOperacion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setVariableOperacion
	 * (pe.com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	public abstract void setVariableOperacion(VariableOperacionBean variableOperacion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * getPlantillaProductoReporte()
	 */
	public abstract PlantillaProductoBean getPlantillaProductoReporte();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * setPlantillaProductoReporte
	 * (pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean)
	 */
	public abstract void setPlantillaProductoReporte(PlantillaProductoBean plantillaProductoReporte);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getComponenteProducto
	 * ()
	 */
	public abstract ComponenteBean getComponenteProducto();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setComponenteProducto
	 * (pe.com.pacasmayo.sgcp.bean.ComponenteBean)
	 */
	public abstract void setComponenteProducto(ComponenteBean componenteProducto);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * getProporcionColumnaReporte()
	 */
	public abstract Double getProporcionColumnaReporte();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * setProporcionColumnaReporte(java.lang.Integer)
	 */
	public abstract void setProporcionColumnaReporte(Double proporcionColumnaReporte);

	public abstract ColumnaReporteBean getColumnaPlantillaReporte();

	public abstract void setColumnaPlantillaReporte(ColumnaReporteBean columnaPlantillaReporte);
}
