package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RangoInvalidoException.java
 * Modificado: Dec 23, 2009 7:57:12 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class RangoInvalidoException extends ParametroInvalidoException {

	public RangoInvalidoException(String mensaje) {
		super(mensaje);

	}

	public RangoInvalidoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public RangoInvalidoException(Throwable causa) {
		super(causa);
	}

}
