package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaAjusteProductoBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaGrupoAjusteBean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: PlantillaGrupoAjusteBeanImpl.java
 * Modificado: May 27, 2010 10:21:08 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class PlantillaGrupoAjusteBeanImpl extends EntidadBeanImpl implements PlantillaGrupoAjusteBean {

	private Long pkCodigoPlantillagrupoajuste;
	private LineaNegocioBean lineanegocio;
	private Short ordenPlantillagrupoajuste;
	private String nombrePlantillagrupoajuste;
	private List<AjusteProductoBean> ajusteproductos = new ArrayList<AjusteProductoBean>();
	private List<PlantillaAjusteProductoBean> plantillaajusteproductos = new ArrayList<PlantillaAjusteProductoBean>();

	public Long getPkCodigoPlantillagrupoajuste() {
		return pkCodigoPlantillagrupoajuste;
	}

	public void setPkCodigoPlantillagrupoajuste(Long pkCodigoPlantillagrupoajuste) {
		this.pkCodigoPlantillagrupoajuste = pkCodigoPlantillagrupoajuste;
	}

	public LineaNegocioBean getLineanegocio() {
		return lineanegocio;
	}

	public void setLineanegocio(LineaNegocioBean lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Short getOrdenPlantillagrupoajuste() {
		return ordenPlantillagrupoajuste;
	}

	public void setOrdenPlantillagrupoajuste(Short ordenPlantillagrupoajuste) {
		this.ordenPlantillagrupoajuste = ordenPlantillagrupoajuste;
	}

	public String getNombrePlantillagrupoajuste() {
		return nombrePlantillagrupoajuste;
	}

	public void setNombrePlantillagrupoajuste(String nombrePlantillagrupoajuste) {
		this.nombrePlantillagrupoajuste = nombrePlantillagrupoajuste;
	}

	public List<PlantillaAjusteProductoBean> getPlantillaajusteproductos() {
		return plantillaajusteproductos;
	}

	public void setPlantillaajusteproductos(List<PlantillaAjusteProductoBean> plantillaajusteproductos) {
		this.plantillaajusteproductos = plantillaajusteproductos;
	}

	/**
	 * @return the ajusteproductos
	 */
	public List<AjusteProductoBean> getAjusteproductos() {
		return ajusteproductos;
	}

	/**
	 * @param ajusteproductos the ajusteproductos to set
	 */
	public void setAjusteproductos(List<AjusteProductoBean> ajusteproductos) {
		this.ajusteproductos = ajusteproductos;
	}

}
