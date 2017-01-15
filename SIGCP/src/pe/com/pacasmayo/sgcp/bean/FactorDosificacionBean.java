package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorDosificacionBean.java
 * Modificado: Feb 18, 2010 1:52:36 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

public interface FactorDosificacionBean extends EntidadBean {

	public abstract ComponenteBean getComponente();

	public abstract void setComponente(ComponenteBean componente);

	public abstract UnidadMedidaBean getUnidad();

	public abstract void setUnidad(UnidadMedidaBean unidad);

	public abstract HojaRutaBean getHojaRuta();

	public abstract void setHojaRuta(HojaRutaBean hojaRuta);

	public abstract List<DosificacionBean> getDosificaciones();

	public abstract void setDosificaciones(List<DosificacionBean> dosificaciones);

	public abstract FactorDosificacionRegistroMensualBean[] getFactorDosificacionRegistroMensual();

	public abstract void setFactorDosificacionRegistroMensual(
			FactorDosificacionRegistroMensualBean[] factorDosificacionRegistroMensual);

	public abstract String getProyeccion();

	public abstract void setProyeccion(String proyeccion);
}
