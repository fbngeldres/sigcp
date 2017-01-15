package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ComponenteNotificacionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteNotificacionBean;
import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;

public class ComponenteNotificacionBeanImpl implements ComponenteNotificacionBean {

	private Long codigo;
	private ProductoBean producto;
	private NotificacionProduccionBean notificacionProduccion;
	private Double valorComponenteNotificacion;
	private ComponenteBean componente;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#setCodigo(
	 * java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#getProducto()
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#setProducto
	 * (pe.com.pacasmayo.sgcp.bean.ProductoBean)
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#
	 * getNotificacionProduccion()
	 */
	public NotificacionProduccionBean getNotificacionProduccion() {
		return notificacionProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#
	 * setNotificacionProduccion
	 * (pe.com.pacasmayo.sgcp.bean.impl.NotificacionProduccionBean)
	 */
	public void setNotificacionProduccion(NotificacionProduccionBean notificacionProduccion) {
		this.notificacionProduccion = notificacionProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#
	 * getValorComponenteNotificacion()
	 */
	public Double getValorComponenteNotificacion() {
		return valorComponenteNotificacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteNotificacionBean#
	 * setValorComponenteNotificacion(java.lang.Double)
	 */
	public void setValorComponenteNotificacion(Double valorComponenteNotificacion) {
		this.valorComponenteNotificacion = valorComponenteNotificacion;
	}

	public ComponenteBean getComponente() {
		return componente;
	}

	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

}
