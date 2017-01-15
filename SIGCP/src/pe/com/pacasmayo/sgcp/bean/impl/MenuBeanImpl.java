package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MenuBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.AccionBean;
import pe.com.pacasmayo.sgcp.bean.MenuBean;
import pe.com.pacasmayo.sgcp.bean.NivelMenuBean;
import pe.com.pacasmayo.sgcp.bean.PrivilegioBean;

public class MenuBeanImpl implements MenuBean {

	private Long codigo;
	private String nombre;
	private String descripcion;
	private Short ordenMenu;
	private Boolean estadoMenu;
	private PrivilegioBean privilegioBean;
	private NivelMenuBean nivelMenuBean;
	private MenuBean menuPadre;
	private List<MenuBean> menuBeanList = new ArrayList<MenuBean>();
	private List<AccionBean> accionBeanList = new ArrayList<AccionBean>();

	public MenuBeanImpl() {
		privilegioBean = new PrivilegioBeanImpl();
		nivelMenuBean = new NivelMenuBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getDescripcion()
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setDescripcion(java.lang.String)
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getOrdenMenu()
	 */
	public Short getOrdenMenu() {
		return ordenMenu;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setOrdenMenu(java.lang.Short)
	 */
	public void setOrdenMenu(Short ordenMenu) {
		this.ordenMenu = ordenMenu;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getPrivilegioBean()
	 */
	public PrivilegioBean getPrivilegioBean() {
		return privilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setPrivilegioBean(pe.com.pacasmayo
	 * .sgcp.bean.PrivilegioBean)
	 */
	public void setPrivilegioBean(PrivilegioBean privilegioBean) {
		this.privilegioBean = privilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getNivelMenuBean()
	 */
	public NivelMenuBean getNivelMenuBean() {
		return nivelMenuBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setNivelMenuBean(pe.com.pacasmayo
	 * .sgcp.bean.NivelMenuBean)
	 */
	public void setNivelMenuBean(NivelMenuBean nivelMenuBean) {
		this.nivelMenuBean = nivelMenuBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getMenuPadre()
	 */
	public MenuBean getMenuPadre() {
		return menuPadre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setMenuPadre(pe.com.pacasmayo
	 * .sgcp.bean.MenuBean)
	 */
	public void setMenuPadre(MenuBean menuPadre) {
		this.menuPadre = menuPadre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getMenuBeanList()
	 */
	public List<MenuBean> getMenuBeanList() {
		return menuBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setMenuBeanList(java.util.List)
	 */
	public void setMenuBeanList(List<MenuBean> menuBeanList) {
		this.menuBeanList = menuBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.MenuBean#getAccionBeanList()
	 */
	public List<AccionBean> getAccionBeanList() {
		return accionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.MenuBean#setAccionBeanList(java.util.
	 * List)
	 */
	public void setAccionBeanList(List<AccionBean> accionBeanList) {
		this.accionBeanList = accionBeanList;
	}

	/**
	 * @return the estadoMenu
	 */
	public Boolean getEstadoMenu() {
		return estadoMenu;
	}

	/**
	 * @param estadoMenu the estadoMenu to set
	 */
	public void setEstadoMenu(Boolean estadoMenu) {
		this.estadoMenu = estadoMenu;
	}

}