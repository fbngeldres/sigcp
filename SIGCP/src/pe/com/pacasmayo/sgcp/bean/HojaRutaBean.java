package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: HojaRutaBean.java
 * Modificado: Feb 4, 2010 3:28:32 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

public interface HojaRutaBean extends EntidadBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getComponente()
	 */
	public abstract ComponenteBean getComponente();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setComponente(pe.com.pacasmayo
	 * .sgcp.bean.ComponenteBean)
	 */
	public abstract void setComponente(ComponenteBean componente);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getEstadoHojaRuta()
	 */
	public abstract EstadoHojaRutaBean getEstadoHojaRuta();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setEstadoHojaRuta(pe.com
	 * .pacasmayo.sgcp.bean.EstadoHojaRutaBean)
	 */
	public abstract void setEstadoHojaRuta(EstadoHojaRutaBean estadoHojaRuta);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getProducto()
	 */
	public abstract ProductoBean getProducto();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setProducto(pe.com.pacasmayo
	 * .sgcp.bean.ProductoBean)
	 */
	public abstract void setProducto(ProductoBean producto);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getOperacion()
	 */
	public abstract List<OperacionBean> getOperacions();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setOperacion(pe.com.pacasmayo
	 * .sgcp.bean.OperacionBean)
	 */
	public abstract void setOperacion(List<OperacionBean> operacions);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setNumeroHojaruta(Java.Lang
	 * .Integer)
	 */
	public abstract ProduccionBean getProduccion();

	public abstract void setProduccion(ProduccionBean produccion);

	public abstract List<FactorDosificacionBean> getFactorDosificacions();

	public abstract void setFactorDosificacions(List<FactorDosificacionBean> factorDosificacions);

	public abstract List<HojaRutaComponenteBean> getHojaRutaComponentes();

	public abstract void setHojaRutaComponentes(List<HojaRutaComponenteBean> hojaRutaComponentes);
}
