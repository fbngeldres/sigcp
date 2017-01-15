package pe.com.pacasmayo.sgcp.bean;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PersonaBean.java
 * Modificado: Dec 22, 2009 12:58:39 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PersonaBean {

	/**
	 * @return the apellido
	 */
	public abstract String getApellido();

	/**
	 * @param apellido the apellido to set
	 */
	public abstract void setApellido(String apellido);

	/**
	 * @return the codigo
	 */
	public abstract Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	public abstract void setCodigo(Long codigo);

	/**
	 * @return the correo
	 */
	public abstract String getCorreo();

	/**
	 * @param correo the correo to set
	 */
	public abstract void setCorreo(String correo);

	/**
	 * @return the extension
	 */
	public abstract String getExtension();

	/**
	 * @param extension the extension to set
	 */
	public abstract void setExtension(String extension);

	/**
	 * @return the idDocumento
	 */
	public abstract String getIdDocumento();

	/**
	 * @param idDocumento the idDocumento to set
	 */
	public abstract void setIdDocumento(String idDocumento);

	/**
	 * @return the nombre
	 */
	public abstract String getNombre();

	/**
	 * @param nombre the nombre to set
	 */
	public abstract void setNombre(String nombre);

	/**
	 * @return the telefono
	 */
	public abstract String getTelefono();

	/**
	 * @param telefono the telefono to set
	 */
	public abstract void setTelefono(String telefono);

	/**
	 * @return the cargo
	 */
	public abstract CargoBean getCargo();

	/**
	 * @param cargo the cargo to set
	 */
	public abstract void setCargo(CargoBean cargo);

}