package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AplicacionException.java
 * Modificado: Dec 22, 2009 2:47:53 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AplicacionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AplicacionException(String mensaje) {
		super(mensaje);
	}

	public AplicacionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public AplicacionException(Throwable causa) {
		super(causa);
	}

	public String getClassName() {
		return this.getClass().getName();
	}

	public String getMensaje() {
		return super.getMessage();
	}

}
