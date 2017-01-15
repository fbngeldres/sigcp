package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: IntegracionException.java
 * Modificado: Dec 22, 2009 3:04:22 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class IntegracionException extends EntornoEjecucionException {

	public IntegracionException(String mensaje) {
		super(mensaje);
	}

	public IntegracionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public IntegracionException(Throwable causa) {
		super(causa);
	}

}
