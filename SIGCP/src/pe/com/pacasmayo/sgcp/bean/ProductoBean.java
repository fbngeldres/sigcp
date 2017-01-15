package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ProductoBean.java
 * Modificado: Dec 30, 2009 10:35:51 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ProductoBean extends EntidadBean {

	public abstract String getCodigoSAP();

	public abstract void setCodigoSAP(String codigoSAP);

	public abstract Long getCodigoSCC();

	public abstract void setCodigoSCC(Long codigoSCC);

	public abstract double getCosto();

	public abstract void setCosto(double costo);

	public abstract String getSiglas();

	public abstract void setSiglas(String siglas);

	public abstract double getStockMaximo();

	public abstract void setStockMaximo(double stockMaximo);

	public abstract double getStockMinimo();

	public abstract void setStockMinimo(double stockMinimo);

	public abstract EstadoProductoBean getEstadoProducto();

	public abstract void setEstadoProducto(EstadoProductoBean estadoProducto);

	public abstract TipoProductoBean getTipoProducto();

	public abstract void setTipoProducto(TipoProductoBean tipoProducto);

	public abstract UnidadMedidaBean getUnidadMedida();

	public abstract void setUnidadMedida(UnidadMedidaBean unidadMedida);

	public abstract TipoCategoriaProductoBean getTipoCategoriaProductoBean();

	public abstract void setTipoCategoriaProductoBean(TipoCategoriaProductoBean tipoCategoriaProductoBean);

	public abstract String getGrupoProducto();

	public abstract void setGrupoProducto(String grupoProducto);

	public TipoConsumoBean getTipoConsumo();

	public void setTipoConsumo(TipoConsumoBean tipoConsumo);

}