package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: GrupoUsuarioBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioPrivilegioBean;
import pe.com.pacasmayo.sgcp.bean.PrivilegioBean;

public class GrupoUsuarioPrivilegioBeanImpl extends EntidadBeanImpl implements GrupoUsuarioPrivilegioBean {

	private Long codigo;
	private PrivilegioBean privilegioBean;
	private GrupoUsuarioBean grupoUsuarioBean;

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioPrivilegioBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioPrivilegioBean#setCodigo(
	 * java.lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioPrivilegioBean#getPrivilegioBean
	 * ()
	 */
	public PrivilegioBean getPrivilegioBean() {
		return privilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioPrivilegioBean#setPrivilegioBean
	 * (pe.com.pacasmayo.sgcp.bean.PrivilegioBean)
	 */
	public void setPrivilegioBean(PrivilegioBean privilegioBean) {
		this.privilegioBean = privilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioPrivilegioBean#
	 * getGrupoUsuarioBean()
	 */
	public GrupoUsuarioBean getGrupoUsuarioBean() {
		return grupoUsuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.GrupoUsuarioPrivilegioBean#
	 * setGrupoUsuarioBean(pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean)
	 */
	public void setGrupoUsuarioBean(GrupoUsuarioBean grupoUsuarioBean) {
		this.grupoUsuarioBean = grupoUsuarioBean;
	}

	public GrupoUsuarioPrivilegioBeanImpl() {

	}
}