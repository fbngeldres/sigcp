package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: CapacidadOperativaBean.java
 * Modificado: Oct 25, 2010 3:32:09 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface CapacidadOperativaBean {

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setCodigo(java
	 * .lang.Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getTasaRealProduccion
	 * ()
	 */
	public abstract TasaRealProduccionBean getTasaRealProduccion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setTasaRealProduccion
	 * (pe.com.pacasmayo.sgcp.bean.TasaRealProduccionBean)
	 */
	public abstract void setTasaRealProduccion(TasaRealProduccionBean tasaRealProduccion);

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * getTipoCapacidadOperativa()
	 */
	public abstract TipoCapacidadOperativaBean getTipoCapacidadOperativa();

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * setTipoCapacidadOperativa
	 * (pe.com.pacasmayo.sgcp.bean.TipoCapacidadOperativaBean)
	 */
	public abstract void setTipoCapacidadOperativa(TipoCapacidadOperativaBean tipoCapacidadOperativa);

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getOperacion()
	 */
	public abstract OperacionBean getOperacion();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setOperacion(pe
	 * .com.pacasmayo.sgcp.bean.OperacionBean)
	 */
	public abstract void setOperacion(OperacionBean operacion);

	public abstract CapacidadOperativaRegistroMensualBean[] getListaCapacidadOperativaRegistroMensual();

	public abstract void setListaCapacidadOperativaRegistroMensual(
			CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRegistroMensual);

}
