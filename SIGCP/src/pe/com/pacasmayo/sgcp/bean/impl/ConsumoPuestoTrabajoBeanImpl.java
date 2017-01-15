package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ComponenteBean;
import pe.com.pacasmayo.sgcp.bean.ConsumoPuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoGeneradoBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ConsumoPuestoTrabajoBeanImpl.java
 * Modificado: May 27, 2010 9:48:58 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ConsumoPuestoTrabajoBeanImpl extends EntidadBeanImpl implements ConsumoPuestoTrabajoBean {

	private Long pkCodigoConsumopuestotrabajo;
	private ProductoGeneradoBean productogenerado;
	private ComponenteBean componente;
	private Double cantidadConsumopuestotrabajo;

	public Long getPkCodigoConsumopuestotrabajo() {
		return pkCodigoConsumopuestotrabajo;
	}

	public void setPkCodigoConsumopuestotrabajo(Long pkCodigoConsumopuestotrabajo) {
		this.pkCodigoConsumopuestotrabajo = pkCodigoConsumopuestotrabajo;
	}

	public ProductoGeneradoBean getProductogenerado() {
		return productogenerado;
	}

	public void setProductogenerado(ProductoGeneradoBean productogenerado) {
		this.productogenerado = productogenerado;
	}

	public ComponenteBean getComponente() {
		return componente;
	}

	public void setComponente(ComponenteBean componente) {
		this.componente = componente;
	}

	public Double getCantidadConsumopuestotrabajo() {
		return cantidadConsumopuestotrabajo;
	}

	public void setCantidadConsumopuestotrabajo(Double cantidadConsumopuestotrabajo) {
		this.cantidadConsumopuestotrabajo = cantidadConsumopuestotrabajo;
	}

}
