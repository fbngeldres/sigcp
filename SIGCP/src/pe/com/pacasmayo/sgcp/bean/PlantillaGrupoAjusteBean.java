package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PlantillaGrupoAjusteBean.java
 * Modificado: May 26, 2010 4:17:04 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface PlantillaGrupoAjusteBean extends EntidadBean {

	public abstract LineaNegocioBean getLineanegocio();

	public abstract void setLineanegocio(LineaNegocioBean lineanegocio);

	public abstract Short getOrdenPlantillagrupoajuste();

	public abstract void setOrdenPlantillagrupoajuste(Short ordenPlantillagrupoajuste);

	public abstract String getNombrePlantillagrupoajuste();

	public void setNombrePlantillagrupoajuste(String nombrePlantillagrupoajuste);

	public abstract List<PlantillaAjusteProductoBean> getPlantillaajusteproductos();

	public abstract void setPlantillaajusteproductos(List<PlantillaAjusteProductoBean> plantillaajusteproductos);

	public abstract List<AjusteProductoBean> getAjusteproductos();

	public abstract void setAjusteproductos(List<AjusteProductoBean> ajusteproductos);

}