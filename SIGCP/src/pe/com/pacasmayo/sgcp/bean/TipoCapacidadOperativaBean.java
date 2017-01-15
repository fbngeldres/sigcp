package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: TipoCapacidadOperativaBean.java
 * Modificado: Dec 14, 2010 10:25:25 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TipoCapacidadOperativaBean extends EntidadBean {

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCapacidadOperativaBean#getUnidadMedida
	 * ()
	 */
	public abstract UnidadMedidaBean getUnidadMedida();

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCapacidadOperativaBean#setUnidadMedida
	 * (pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean)
	 */
	public abstract void setUnidadMedida(UnidadMedidaBean unidadMedida);

}
