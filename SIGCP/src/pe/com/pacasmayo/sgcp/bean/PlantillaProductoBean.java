package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlantillaProductoBean.java
 * Modificado: May 24, 2010 10:23:40 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlantillaProductoBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract PlantillaReporteBean getPlantillaReporte();

	public abstract void setPlantillaReporte(PlantillaReporteBean plantillaReporte);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract List<ColumnaPlantillaProductoBean> getColumnas();

	public abstract void setColumnas(List<ColumnaPlantillaProductoBean> columnas);

	public abstract void setFecha(Date fecha);

	public abstract Date getFecha();

	public abstract void setVersion(Integer version);

	public abstract Integer getVersion();
}
