package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ValorPromVariableCalidadBean.java
 * Modificado: May 27, 2010 11:10:46 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ValorPromVariableCalidadBean extends EntidadBean {

	public abstract Long getPkCodigoValorpromvariblecalid();

	public abstract void setPkCodigoValorpromvariblecalid(Long pkCodigoValorpromvariblecalid);

	public abstract TablaKardexBean getTablakardex();

	public abstract void setTablakardex(TablaKardexBean tablakardex);

	public abstract ProductoVariableCalidadBean getProductovariablecalidad();

	public abstract void setProductovariablecalidad(ProductoVariableCalidadBean productovariablecalidad);

	public abstract Double getValorValorpromvariblecalidad();

	public abstract void setValorValorpromvariblecalidad(Double valorValorpromvariblecalidad);

}