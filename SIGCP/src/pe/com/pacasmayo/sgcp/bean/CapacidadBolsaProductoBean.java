package pe.com.pacasmayo.sgcp.bean;

/*
 * Backlog 
 * Archivo: CapacidadBolsaProductoBean.java
 * Modificado: Mar 18, 2014 5:44:48 PM
 * Autor: ginteldos
 *
 * Copyright (C) Gintelligence, 2012. All rights reserved.
 *
 * Developed by: Gintelligence. http://www.gintelligence.net
 */
public interface CapacidadBolsaProductoBean extends EntidadBean {

	public String getCodigoSapProducto();

	public void setCodigoSapProducto(String codigoSapProducto);

	public Double getCapacidadNetaBolsa();

	public void setCapacidadNetaBolsa(Double capacidadNetaBolsa);

	public Double getCapacidadBrutaBolsa();

	public void setCapacidadBrutaBolsa(Double capacidadBrutaBolsa);
}