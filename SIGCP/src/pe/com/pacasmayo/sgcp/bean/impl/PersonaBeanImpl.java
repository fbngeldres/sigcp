package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PersonaBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.CargoBean;
import pe.com.pacasmayo.sgcp.bean.PersonaBean;

public class PersonaBeanImpl implements PersonaBean {

	protected String apellido;
	private Long codigo;
	protected String correo;
	protected String extension;
	protected String idDocumento;
	protected String nombre;
	protected String telefono;
	protected CargoBean cargo;

	public PersonaBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getApellido()
	 */
	public String getApellido() {
		return apellido;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setApellido(java.lang.String)
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getCorreo()
	 */
	public String getCorreo() {
		return correo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setCorreo(java.lang.String)
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getExtension()
	 */
	public String getExtension() {
		return extension;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setExtension(java.lang.String
	 * )
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getIdDocumento()
	 */
	public String getIdDocumento() {
		return idDocumento;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setIdDocumento(java.lang.
	 * String)
	 */
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getTelefono()
	 */
	public String getTelefono() {
		return telefono;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setTelefono(java.lang.String)
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#getCargo()
	 */
	public CargoBean getCargo() {
		return cargo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.PersonaBean#setCargo(pe.com.pacasmayo
	 * .sgcp.bean.impl.CargoBeanImpl)
	 */
	public void setCargo(CargoBean cargo) {
		this.cargo = cargo;
	}

}