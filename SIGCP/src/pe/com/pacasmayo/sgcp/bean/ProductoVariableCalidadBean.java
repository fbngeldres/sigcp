package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProductoVariableCalidadBean.java
 * Modificado: May 27, 2010 11:13:13 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ProductoVariableCalidadBean extends EntidadBean {

	public abstract Long getPkCodigoProductovariablecalid();

	public abstract void setPkCodigoProductovariablecalid(Long pkCodigoProductovariablecalid);

	public abstract HojaRutaBean getHojaruta();

	public abstract void setHojaruta(HojaRutaBean hojaruta);

	public abstract VariableCalidadBean getVariablecalidad();

	public abstract void setVariablecalidad(VariableCalidadBean variablecalidad);

	public abstract Long getCodigoMatrixScc();

	public abstract void setCodigoMatrixScc(Long codigoMatrixScc);

	public Long getCodigoProcesoScc();

	public void setCodigoProcesoScc(Long codigoProcesoScc);

	public abstract List<ValorPromVariableCalidadBean> getValorpromvariablecalidads();

	public abstract void setValorpromvariablecalidads(List<ValorPromVariableCalidadBean> valorpromvariablecalidads);

	public abstract List<VariableCalidadBean> getListaVariablesCalidad();

	public abstract void setListaVariablesCalidad(List<VariableCalidadBean> listaVariablesCalidad);
}