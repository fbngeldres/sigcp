package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HojaRutaComponenteBean.java
 * Modificado: Feb 6, 2010 8:26:15 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface HojaRutaComponenteBean {

	public abstract Long getCodigoHojaRutaComponente();

	public abstract void setCodigoHojaRutaComponente(Long codigoHojaRutaComponente);

	public abstract ComponenteBean getComponente();

	public abstract void setComponente(ComponenteBean componente);

	public abstract HojaRutaBean getHojaRuta();

	public abstract void setHojaRuta(HojaRutaBean hojaRuta);

	public abstract TipoComponenteBean getTipoComponente();

	public abstract void setTipoComponente(TipoComponenteBean tipoComponente);
}
