package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProductoFactorCorreccionBean.java
 * Modificado: May 26, 2010 5:00:17 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ProductoFactorCorreccionBean extends EntidadBean {

	public abstract Long getPkCodigoProductofactorcorrecc();

	public abstract void setPkCodigoProductofactorcorrecc(Long pkCodigoProductofactorcorrecc);

	public abstract FactorCorreccionBean getFactorcorreccion();

	public abstract void setFactorcorreccion(FactorCorreccionBean factorcorreccion);

	public abstract HojaRutaBean getHojaruta();

	public abstract void setHojaruta(HojaRutaBean hojaruta);

	public abstract Double getValorProductofactorcorreccion();

	public abstract void setValorProductofactorcorreccion(Double valorProductofactorcorreccion);

	public abstract List<FactorKardexBean> getFactorkardexes();

	public abstract void setFactorkardexes(List<FactorKardexBean> factorkardexes);

}