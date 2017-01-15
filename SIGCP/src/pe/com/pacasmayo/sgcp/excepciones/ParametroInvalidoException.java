package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ParametroInvalidoException.java
 * Modificado: Dec 22, 2009 2:56:42 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ParametroInvalidoException extends AplicacionException {

	public ParametroInvalidoException(String mensaje) {
		super(mensaje);

	}

	public ParametroInvalidoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ParametroInvalidoException(Throwable causa) {
		super(causa);
	}
}
