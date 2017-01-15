package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: CargoBean.java
 * Modificado: Apr 27, 2010 4:01:55 PM 
 * Autor: Ignacio
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Nivelcargo;

public interface CargoBean {

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

	/**
	 * @return the divisionBeanList
	 */
	public abstract List<DivisionBean> getDivisionBeanList();

	/**
	 * @param divisionBeanList the divisionBeanList to set
	 */
	public abstract void setDivisionBeanList(List<DivisionBean> divisionBeanList);

	/**
	 * @return the sociedadBeanList
	 */
	public abstract List<SociedadBean> getSociedadBeanList();

	/**
	 * @param sociedadBeanList the sociedadBeanList to set
	 */
	public abstract void setSociedadBeanList(List<SociedadBean> sociedadBeanList);

	/**
	 * @return the unidadBeanList
	 */
	public abstract List<UnidadBean> getUnidadBeanList();

	/**
	 * @param unidadBeanList the unidadBeanList to set
	 */
	public abstract void setUnidadBeanList(List<UnidadBean> unidadBeanList);



	public NivelCargoBean getNivelCargoBean();

	public void setNivelCargoBean(NivelCargoBean nivelCargoBean);

	public abstract Nivelcargo getNivelCargo();

	public abstract void setNivelCargo(Nivelcargo nivelCargo);

	public abstract DivisionCargoBean getDivisionCargoBean();

	public abstract void setDivisionCargoBean(DivisionCargoBean divisionCargoBean);

	public abstract SociedadCargoBean getSociedadCargoBean();

	public abstract void setSociedadCargoBean(SociedadCargoBean sociedadCargoBean);

	public abstract UnidadCargoBean getUnidadCargoBean();

	public abstract void setUnidadCargoBean(UnidadCargoBean unidadCargoBean);
}
