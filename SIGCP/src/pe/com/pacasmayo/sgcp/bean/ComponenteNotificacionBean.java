package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ComponenteNotificacionBean.java
 * Modificado: Jan 21, 2010 11:42:17 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ComponenteNotificacionBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long codigo);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract NotificacionProduccionBean getNotificacionProduccion();

	public abstract void setNotificacionProduccion(NotificacionProduccionBean notificacionProduccion);

	public abstract Double getValorComponenteNotificacion();

	public abstract void setValorComponenteNotificacion(Double valorComponenteNotificacion);

	public ComponenteBean getComponente();

	public void setComponente(ComponenteBean componente);

}