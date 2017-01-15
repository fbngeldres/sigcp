package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PrivilegioBean.java
 * Modificado: Mar 24, 2010 9:02:56 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

public interface PrivilegioBean {

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
	 * @return the descripcion
	 */
	public abstract String getDescripcion();

	/**
	 * @param descripcion the descripcion to set
	 */
	public abstract void setDescripcion(String descripcion);

	/**
	 * @return the estadoPrivilegioBean
	 */
	public abstract EstadoPrivilegioBean getEstadoPrivilegioBean();

	/**
	 * @param estadoPrivilegioBean the estadoPrivilegioBean to set
	 */
	public abstract void setEstadoPrivilegioBean(EstadoPrivilegioBean estadoPrivilegioBean);

	/**
	 * @return the menuList
	 */
	public abstract List<MenuBean> getMenuList();

	/**
	 * @param menuList the menuList to set
	 */
	public abstract void setMenuList(List<MenuBean> menuList);

}