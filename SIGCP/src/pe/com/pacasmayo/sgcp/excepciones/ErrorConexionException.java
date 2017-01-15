package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ErrorConexionException.java
 * Modificado: Dec 22, 2009 3:06:53 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ErrorConexionException extends EntornoEjecucionException {

	public ErrorConexionException(String mensaje) {
		super(mensaje);
	}

	public ErrorConexionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ErrorConexionException(Throwable causa) {
		super(causa);
	}

}
