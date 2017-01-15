package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UsuarioBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioGrupoUsuarioBean;

public class UsuarioGrupoUsuarioBeanImpl extends EntidadBeanImpl implements UsuarioGrupoUsuarioBean {

	private Long codigo;
	private UsuarioBean usuarioBean;
	private GrupoUsuarioBean grupoUsuarioBean;

	public UsuarioGrupoUsuarioBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.UsuarioGrupoUsuarioBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioGrupoUsuarioBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioGrupoUsuarioBean#getUsuarioBean()
	 */
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioGrupoUsuarioBean#setUsuarioBean
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioGrupoUsuarioBean#getGrupoUsuarioBean
	 * ()
	 */
	public GrupoUsuarioBean getGrupoUsuarioBean() {
		return grupoUsuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.UsuarioGrupoUsuarioBean#setGrupoUsuarioBean
	 * (pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean)
	 */
	public void setGrupoUsuarioBean(GrupoUsuarioBean grupoUsuarioBean) {
		this.grupoUsuarioBean = grupoUsuarioBean;
	}
}