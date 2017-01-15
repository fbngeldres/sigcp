package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ProcesoBean.java
 * Modificado: Jan 20, 2010 5:54:26 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Set;

public interface ProcesoBean {

	public abstract TipoProductoBean getTipoProducto();

	public abstract void setTipoProducto(TipoProductoBean tipoProducto);

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getCodigo()
	 */
	public abstract Long getCodigo();

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setCodigo(Long)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setCodigo(java.lang.Long)
	 */
	public abstract void setCodigo(Long codigo);

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getCodigoSCC()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getCodigoSCC()
	 */
	public abstract Long getCodigoSCC();

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setCodigoSCC(Long)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setCodigoSCC(java.lang.Long)
	 */
	public abstract void setCodigoSCC(Long codigoSCC);

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getDescripcion()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getDescripcion()
	 */
	public abstract String getDescripcion();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setDescripcion(java.lang.
	 * String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setDescripcion(java.lang.
	 * String)
	 */
	public abstract void setDescripcion(String descripcion);

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getNombre()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getNombre()
	 */
	public abstract String getNombre();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setNombre(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setNombre(java.lang.String)
	 */
	public abstract void setNombre(String nombre);

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getOrdenEjecucion()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getOrdenEjecucion()
	 */
	public abstract Short getOrdenEjecucion();

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setOrdenEjecucion(int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setOrdenEjecucion(java.lang
	 * .Short)
	 */
	public abstract void setOrdenEjecucion(Short ordenEjecucion);

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#getLineaNegocio()
	 */
	public abstract LineaNegocioBean getLineaNegocio();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProcesoBean#setLineaNegocio(pe.com.pacasmayo
	 * .sgcp.bean.LineaNegocioBean)
	 */
	public abstract void setLineaNegocio(LineaNegocioBean lineaNegocio);

	public abstract String getCodigoSAP();

	public abstract void setCodigoSAP(String codigoSAP);

	public abstract String getSiglas();

	public abstract void setSiglas(String siglas);
}