package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PlantillaAjusteProductoBean.java
 * Modificado: May 26, 2010 4:20:37 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface PlantillaAjusteProductoBean extends EntidadBean {

	public abstract PlantillaGrupoAjusteBean getPlantillagrupoajuste();

	public abstract void setPlantillagrupoajuste(PlantillaGrupoAjusteBean plantillagrupoajuste);

	public abstract ProduccionBean getProduccion();

	public abstract void setProduccion(ProduccionBean produccion);

	public abstract Short getOrdenPlantillaajusteproducto();

	public abstract void setOrdenPlantillaajusteproducto(Short ordenPlantillaajusteproducto);

}