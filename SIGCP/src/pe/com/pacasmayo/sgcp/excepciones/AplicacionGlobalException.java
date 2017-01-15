package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AplicacionGlobalException.java
 * Modificado: Dec 22, 2009 3:26:58 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AplicacionGlobalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AplicacionGlobalException(String mensaje) {
		super(mensaje);
	}

	public AplicacionGlobalException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public AplicacionGlobalException(Throwable causa) {
		super(causa);
	}

	public String getClassName() {
		return this.getClass().getName();
	}

	public String getMensaje() {
		return super.getMessage();
	}
}
