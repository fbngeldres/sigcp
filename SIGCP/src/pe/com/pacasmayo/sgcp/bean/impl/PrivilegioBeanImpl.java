package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PrivilegioBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoPrivilegioBean;
import pe.com.pacasmayo.sgcp.bean.MenuBean;
import pe.com.pacasmayo.sgcp.bean.PrivilegioBean;

public class PrivilegioBeanImpl implements PrivilegioBean {

	private Long codigo;
	private String nombre;
	private String descripcion;
	private EstadoPrivilegioBean estadoPrivilegioBean;
	private List<MenuBean> menuList;

	public PrivilegioBeanImpl() {
		estadoPrivilegioBean = new EstadoPrivilegioBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#setCodigo(java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#setNombre(java.lang.String
	 * )
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#getDescripcion()
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#setDescripcion(java.lang
	 * .String)
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#getEstadoPrivilegioBean()
	 */
	public EstadoPrivilegioBean getEstadoPrivilegioBean() {
		return estadoPrivilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#setEstadoPrivilegioBean
	 * (pe.com.pacasmayo.sgcp.bean.EstadoPrivilegioBean)
	 */
	public void setEstadoPrivilegioBean(EstadoPrivilegioBean estadoPrivilegioBean) {
		this.estadoPrivilegioBean = estadoPrivilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#getMenuList()
	 */
	public List<MenuBean> getMenuList() {
		return menuList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PrivilegioBean#setMenuList(java.util.
	 * List)
	 */
	public void setMenuList(List<MenuBean> menuList) {
		this.menuList = menuList;
	}
}