package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ElementoEliminadoException.java
 * Modificado: Dec 22, 2009 2:52:21 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ElementoEliminadoException extends AplicacionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementoEliminadoException(String mensaje) {
		super(mensaje);
	}

	public ElementoEliminadoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ElementoEliminadoException(Throwable causa) {
		super(causa);
	}

}
