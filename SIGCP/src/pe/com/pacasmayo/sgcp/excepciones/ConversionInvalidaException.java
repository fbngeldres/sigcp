package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConversionInvalidaException.java
 * Modificado: Dec 23, 2009 7:54:10 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ConversionInvalidaException extends ParametroInvalidoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConversionInvalidaException(String mensaje) {
		super(mensaje);
	}

	public ConversionInvalidaException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ConversionInvalidaException(Throwable causa) {
		super(causa);
	}

}
