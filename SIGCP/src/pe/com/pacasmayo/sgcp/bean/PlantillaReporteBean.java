package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: PlantillaReporteBean.java
 * Modificado: Jun 1, 2010 8:13:37 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlantillaReporteBean {

	public abstract List<ColumnaReporteBean> getColumnasReporte();

	public abstract void setColumnasReporte(List<ColumnaReporteBean> columnasReporte);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setCodigo(java.lang
	 * .Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getPuestoTrabajo()
	 */
	public abstract PuestoTrabajoBean getPuestoTrabajo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean)
	 */
	public abstract void setPuestoTrabajo(PuestoTrabajoBean puestoTrabajo);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#
	 * getNombrePlantillaReporte()
	 */
	public abstract String getNombrePlantillaReporte();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#
	 * setNombrePlantillaReporte(java.lang.String)
	 */
	public abstract void setNombrePlantillaReporte(String nombrePlantillaReporte);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getFechaRegistro()
	 */
	public abstract String getFechaRegistro();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setFechaRegistro
	 * (java.lang.String)
	 */
	public abstract void setFechaRegistro(String fechaRegistro);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getNumeroColumnas()
	 */
	public abstract Short getNumeroColumnas();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getEstadoPlantilla()
	 */
	public abstract EstadoPlantillaReporteBean getEstadoPlantilla();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setEstadoPlantilla
	 * (pe.com.pacasmayo.sgcp.bean.EstadoPlantillaReporteBean)
	 */
	public abstract void setEstadoPlantilla(EstadoPlantillaReporteBean estadoPlantilla);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getPlantillasProducto
	 * ()
	 */
	public abstract List<PlantillaProductoBean> getPlantillasProducto();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setPlantillasProducto
	 * (java.util.List)
	 */
	public abstract void setPlantillasProducto(List<PlantillaProductoBean> plantillasProducto);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getRegistroreporteecses
	 * ()
	 */
	public abstract List<RegistroReporteEcsBean> getRegistroreporteecses();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setRegistroreporteecses
	 * (java.util.List)
	 */
	public abstract void setRegistroreporteecses(List<RegistroReporteEcsBean> registroreporteecses);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setNumeroColumnas
	 * (java.lang.Short)
	 */
	public abstract void setNumeroColumnas(Short numeroColumnas);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#getProceso()
	 */
	public abstract ProcesoBean getProceso();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PlantillaReporteBean#setProceso(pe.com
	 * .pacasmayo.sgcp.bean.ProcesoBean)
	 */
	public abstract void setProceso(ProcesoBean proceso);

}
