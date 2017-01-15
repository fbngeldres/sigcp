package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HojaRutaComponenteBeanImpl.java
 * Modificado: Feb 6, 2010 8:19:35 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.bean.TipoComponenteBean;

public class HojaRutaComponenteBeanImpl implements HojaRutaComponenteBean {

	private Long codigoHojaRutaComponente;
	private ComponenteBean componente;
	private HojaRutaBean hojaRuta;
	private TipoComponenteBean tipoComponente;

	public HojaRutaComponenteBeanImpl() {
		componente = new ComponenteBeanImpl();
		hojaRuta = new HojaRutaBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaComponenteBean#
	 * getCodigoHojaRutaComponente()
	 */
	public Long getCodigoHojaRutaComponente() {
		return codigoHojaRutaComponente;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaComponenteBean#
	 * setCodigoHojaRutaComponente(java.lang.Long)
	 */
	public void setCodigoHojaRutaComponente(Long codigoHojaRutaComponente) {
		this.codigoHojaRutaComponente = codigoHojaRutaComponente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaComponenteBean#getComponente()
	 */
	public ComponenteBean getComponente() {
		return componente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaComponenteBean#setComponente(
	 * pe.com.pacasmayo.sgcp.bean.ComponenteBean)
	 */
	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaComponenteBean#getHojaRuta()
	 */
	public HojaRutaBean getHojaRuta() {
		return hojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaComponenteBean#setHojaRuta(pe
	 * .com.pacasmayo.sgcp.bean.HojaRutaBean)
	 */
	public void setHojaRuta(HojaRutaBean hojaRuta) {
		this.hojaRuta = hojaRuta;
	}

	public TipoComponenteBean getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(TipoComponenteBean tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

}
