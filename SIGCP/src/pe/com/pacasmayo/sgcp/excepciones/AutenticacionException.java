package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AutenticacionException.java
 * Modificado: Dec 22, 2009 2:50:02 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AutenticacionException extends AplicacionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutenticacionException(String mensaje) {
		super(mensaje);
	}

	public AutenticacionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public AutenticacionException(Throwable causa) {
		super(causa);
	}

}
