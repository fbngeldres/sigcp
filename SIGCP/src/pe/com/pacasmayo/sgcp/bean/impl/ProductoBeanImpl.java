package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProductoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.EstadoProductoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.TipoCategoriaProductoBean;
import pe.com.pacasmayo.sgcp.bean.TipoConsumoBean;
import pe.com.pacasmayo.sgcp.bean.TipoProductoBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class ProductoBeanImpl extends EntidadBeanImpl implements ProductoBean {

	private String codigoSAP;
	private Long codigoSCC;
	private double costo;
	private String siglas;
	private double stockMaximo;
	private double stockMinimo;
	public EstadoProductoBean estadoProducto;
	public TipoProductoBean tipoProducto;
	public UnidadMedidaBean unidadMedida;
	public TipoCategoriaProductoBean tipoCategoriaProductoBean;
	private String grupoProducto;
	private TipoConsumoBean tipoConsumo;

	public ProductoBeanImpl() {
		estadoProducto = new EstadoProductoBeanImpl();
		tipoProducto = new TipoProductoBeanImpl();
		unidadMedida = new UnidadMedidaBeanImpl();
		tipoCategoriaProductoBean = new TipoCategoriaProductoBeanImpl();
		tipoConsumo = new TipoConsumoBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getCodigoSAP()
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setCodigoSAP(java.lang.String
	 * )
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getCodigoSCC()
	 */
	public Long getCodigoSCC() {
		return codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setCodigoSCC(Long)
	 */
	public void setCodigoSCC(Long codigoSCC) {
		this.codigoSCC = codigoSCC;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getCosto()
	 */
	public double getCosto() {
		return costo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setCosto(double)
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getSiglas()
	 */
	public String getSiglas() {
		return siglas;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setSiglas(java.lang.String)
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getStockMaximo()
	 */
	public double getStockMaximo() {
		return stockMaximo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setStockMaximo(double)
	 */
	public void setStockMaximo(double stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getStockMinimo()
	 */
	public double getStockMinimo() {
		return stockMinimo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setStockMinimo(double)
	 */
	public void setStockMinimo(double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getEstadoProducto()
	 */
	public EstadoProductoBean getEstadoProducto() {
		return estadoProducto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setEstadoProducto(pe.com
	 * .pacasmayo.sgcp.bean.EstadoProductoBean)
	 */
	public void setEstadoProducto(EstadoProductoBean estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getTipoProducto()
	 */
	public TipoProductoBean getTipoProducto() {
		return tipoProducto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setTipoProducto(pe.com.pacasmayo
	 * .sgcp.bean.TipoProductoBean)
	 */
	public void setTipoProducto(TipoProductoBean tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#getUnidadMedida()
	 */
	public UnidadMedidaBean getUnidadMedida() {
		return unidadMedida;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ProductoBean#setUnidadMedida(pe.com.pacasmayo
	 * .sgcp.bean.UnidadMedidaBean)
	 */
	public void setUnidadMedida(UnidadMedidaBean unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public TipoCategoriaProductoBean getTipoCategoriaProductoBean() {
		return tipoCategoriaProductoBean;
	}

	public void setTipoCategoriaProductoBean(TipoCategoriaProductoBean tipoCategoriaProductoBean) {
		this.tipoCategoriaProductoBean = tipoCategoriaProductoBean;
	}

	public void setGrupoProducto(String grupoProducto) {
		this.grupoProducto = grupoProducto;
	}

	public String getGrupoProducto() {
		return grupoProducto;
	}

	public TipoConsumoBean getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(TipoConsumoBean tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
	
	

}