package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProduccionBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.bean.ProduccionBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;

public class ProduccionBeanImpl implements ProduccionBean {

	private Long codigo;
	private ProcesoBean proceso;
	private ProductoBean producto;

	public ProduccionBeanImpl() {
		proceso = new ProcesoBeanImpl();
		producto = new ProductoBeanImpl();
	}

	/**
	 * @return the proceso
	 */
	public ProcesoBean getProceso() {
		return proceso;
	}

	/**
	 * @param proceso the proceso to set
	 */
	public void setProceso(ProcesoBean proceso) {
		this.proceso = proceso;
	}

	/**
	 * @return the producto
	 */
	public ProductoBean getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProduccionBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ProduccionBean#setCodigo(Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}