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

import pe.com.pacasmayo.sgcp.bean.ColumnaPlantillaProductoBean;
import pe.com.pacasmayo.sgcp.bean.ColumnaReporteBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaProductoBean;
import pe.com.pacasmayo.sgcp.bean.VariableOperacionBean;

public class ColumnaPlantillaProductoBeanImpl implements ColumnaPlantillaProductoBean {

	private Long codigo;
	private VariableOperacionBean variableOperacion;
	private PlantillaProductoBean plantillaProductoReporte;
	private ComponenteBean componenteProducto;
	private Double proporcionColumnaReporte;
	private ColumnaReporteBean columnaPlantillaReporte;

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#getCodigo()
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
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#setCodigo
	 * (java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getVariableOperacion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * getVariableOperacion()
	 */
	public VariableOperacionBean getVariableOperacion() {
		return variableOperacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setVariableOperacion
	 * (pe.com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * setVariableOperacion(pe.com.pacasmayo.sgcp.bean.VariableOperacionBean)
	 */
	public void setVariableOperacion(VariableOperacionBean variableOperacion) {
		this.variableOperacion = variableOperacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * getPlantillaProductoReporte()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * getPlantillaProductoReporte()
	 */
	public PlantillaProductoBean getPlantillaProductoReporte() {
		return plantillaProductoReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * setPlantillaProductoReporte
	 * (pe.com.pacasmayo.sgcp.bean.impl.PlantillaProductoReporteBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * setPlantillaProductoReporte
	 * (pe.com.pacasmayo.sgcp.bean.PlantillaProductoBean)
	 */
	public void setPlantillaProductoReporte(PlantillaProductoBean plantillaProductoReporte) {
		this.plantillaProductoReporte = plantillaProductoReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#getComponenteProducto
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * getComponenteProducto()
	 */
	public ComponenteBean getComponenteProducto() {
		return componenteProducto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#setComponenteProducto
	 * (pe.com.pacasmayo.sgcp.bean.ComponenteBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * setComponenteProducto(pe.com.pacasmayo.sgcp.bean.ComponenteBean)
	 */
	public void setComponenteProducto(ComponenteBean componenteProducto) {
		this.componenteProducto = componenteProducto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * getProporcionColumnaReporte()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * getProporcionColumnaReporte()
	 */
	public Double getProporcionColumnaReporte() {
		return proporcionColumnaReporte;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaReporteBean#
	 * setProporcionColumnaReporte(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ColumnaPlantillaProductoBean#
	 * setProporcionColumnaReporte(java.lang.Double)
	 */
	public void setProporcionColumnaReporte(Double proporcionColumnaReporte) {
		this.proporcionColumnaReporte = proporcionColumnaReporte;
	}

	public ColumnaReporteBean getColumnaPlantillaReporte() {
		return columnaPlantillaReporte;
	}

	public void setColumnaPlantillaReporte(ColumnaReporteBean columnaPlantillaReporte) {
		this.columnaPlantillaReporte = columnaPlantillaReporte;
	}
}
