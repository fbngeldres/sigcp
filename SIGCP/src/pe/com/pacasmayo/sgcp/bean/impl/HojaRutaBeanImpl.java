package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HojaRutaBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.EstadoHojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.FactorDosificacionBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaComponenteBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;

public class HojaRutaBeanImpl extends EntidadBeanImpl implements HojaRutaBean {

	private ComponenteBean componente;
	private EstadoHojaRutaBean estadoHojaRuta;
	private ProductoBean producto;
	private List<OperacionBean> operacions;
	private Integer numeroHojaruta;
	private ProduccionBean produccion;
	private List<FactorDosificacionBean> factorDosificacions;
	private List<HojaRutaComponenteBean> hojaRutaComponentes;

	public HojaRutaBeanImpl() {
		producto = new ProductoBeanImpl();
		produccion = new ProduccionBeanImpl();
		estadoHojaRuta = new EstadoHojaRutaBeanImpl();
		hojaRutaComponentes = new ArrayList<HojaRutaComponenteBean>();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getComponente()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getComponente()
	 */
	public ComponenteBean getComponente() {
		return componente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setComponente(pe.com.pacasmayo
	 * .sgcp.bean.ComponenteBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setComponente(pe.com.pacasmayo
	 * .sgcp.bean.ComponenteBean)
	 */
	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getEstadoHojaRuta()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getEstadoHojaRuta()
	 */
	public EstadoHojaRutaBean getEstadoHojaRuta() {
		return estadoHojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setEstadoHojaRuta(pe.com
	 * .pacasmayo.sgcp.bean.EstadoHojaRutaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setEstadoHojaRuta(pe.com
	 * .pacasmayo.sgcp.bean.EstadoHojaRutaBean)
	 */
	public void setEstadoHojaRuta(EstadoHojaRutaBean estadoHojaRuta) {
		this.estadoHojaRuta = estadoHojaRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getProducto()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getProducto()
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setProducto(pe.com.pacasmayo
	 * .sgcp.bean.ProductoBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setProducto(pe.com.pacasmayo
	 * .sgcp.bean.ProductoBean)
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getOperacion()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getOperacion()
	 */
	public List<OperacionBean> getOperacions() {
		return operacions;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setOperacion(pe.com.pacasmayo
	 * .sgcp.bean.OperacionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setOperacion(pe.com.pacasmayo
	 * .sgcp.bean.OperacionBean)
	 */
	public void setOperacion(List<OperacionBean> operacions) {
		this.operacions = operacions;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getNumeroHojaruta()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getNumeroHojaruta()
	 */
	public Integer getNumeroHojaruta() {
		return numeroHojaruta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setNumeroHojaruta(Java.Lang
	 * .Integer)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setNumeroHojaruta(java.lang
	 * .Integer)
	 */
	public void setNumeroHojaruta(Integer numeroHojaruta) {
		this.numeroHojaruta = numeroHojaruta;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#getProduccion()
	 */
	public ProduccionBean getProduccion() {
		return produccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.HojaRutaBean#setProduccion(pe.com.pacasmayo
	 * .sgcp.bean.ProduccionBean)
	 */
	public void setProduccion(ProduccionBean produccion) {
		this.produccion = produccion;
	}

	public List<FactorDosificacionBean> getFactorDosificacions() {
		return factorDosificacions;
	}

	public void setFactorDosificacions(List<FactorDosificacionBean> factorDosificacions) {
		this.factorDosificacions = factorDosificacions;
	}

	public List<HojaRutaComponenteBean> getHojaRutaComponentes() {
		return hojaRutaComponentes;
	}

	public void setHojaRutaComponentes(List<HojaRutaComponenteBean> hojaRutaComponentes) {
		this.hojaRutaComponentes = hojaRutaComponentes;
	}
}