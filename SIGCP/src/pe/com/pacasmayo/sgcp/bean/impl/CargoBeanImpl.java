package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: CargoBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;


import pe.com.pacasmayo.sgcp.bean.CargoBean;
import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.DivisionCargoBean;
import pe.com.pacasmayo.sgcp.bean.NivelCargoBean;
import pe.com.pacasmayo.sgcp.bean.SociedadBean;
import pe.com.pacasmayo.sgcp.bean.SociedadCargoBean;
import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UnidadCargoBean;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Nivelcargo;

public class CargoBeanImpl extends EntidadBeanImpl implements CargoBean {

	private Long codigo;
	private String nombre;
	private Nivelcargo nivelCargo;
	private NivelCargoBean nivelCargoBean;
	private List<DivisionBean> divisionBeanList = new ArrayList<DivisionBean>();
	private List<SociedadBean> sociedadBeanList = new ArrayList<SociedadBean>();
	private List<UnidadBean> unidadBeanList = new ArrayList<UnidadBean>();
	
	private DivisionCargoBean divisionCargoBean;
	private SociedadCargoBean sociedadCargoBean;
	private UnidadCargoBean unidadCargoBean;

	public CargoBeanImpl() {
		nivelCargoBean = new NivelCargoBeanImpl();
		divisionCargoBean = new DivisionCargoBeanImpl();
		sociedadCargoBean = new SociedadCargoBeanImpl();
		unidadCargoBean = new UnidadCargoBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CargoBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CargoBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CargoBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CargoBean#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CargoBean#getDivisionBeanList()
	 */
	public List<DivisionBean> getDivisionBeanList() {
		return divisionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CargoBean#setDivisionBeanList(java.util
	 * .List)
	 */
	public void setDivisionBeanList(List<DivisionBean> divisionBeanList) {
		this.divisionBeanList = divisionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CargoBean#getSociedadBeanList()
	 */
	public List<SociedadBean> getSociedadBeanList() {
		return sociedadBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CargoBean#setSociedadBeanList(java.util
	 * .List)
	 */
	public void setSociedadBeanList(List<SociedadBean> sociedadBeanList) {
		this.sociedadBeanList = sociedadBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CargoBean#getUnidadBeanList()
	 */
	public List<UnidadBean> getUnidadBeanList() {
		return unidadBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CargoBean#setUnidadBeanList(java.util
	 * .List)
	 */
	public void setUnidadBeanList(List<UnidadBean> unidadBeanList) {
		this.unidadBeanList = unidadBeanList;
	}




	public NivelCargoBean getNivelCargoBean() {
		return nivelCargoBean;
	}

	public void setNivelCargoBean(NivelCargoBean nivelCargo) {
		this.nivelCargoBean = nivelCargo;
	}

	public Nivelcargo getNivelCargo() {
		return nivelCargo;
	}

	public void setNivelCargo(Nivelcargo nivelCargo) {
		this.nivelCargo = nivelCargo;
	}

	public DivisionCargoBean getDivisionCargoBean() {
		return divisionCargoBean;
	}

	public void setDivisionCargoBean(DivisionCargoBean divisionCargoBean) {
		this.divisionCargoBean = divisionCargoBean;
	}

	public SociedadCargoBean getSociedadCargoBean() {
		return sociedadCargoBean;
	}

	public void setSociedadCargoBean(SociedadCargoBean sociedadCargoBean) {
		this.sociedadCargoBean = sociedadCargoBean;
	}

	public UnidadCargoBean getUnidadCargoBean() {
		return unidadCargoBean;
	}

	public void setUnidadCargoBean(UnidadCargoBean unidadCargoBean) {
		this.unidadCargoBean = unidadCargoBean;
	}

}