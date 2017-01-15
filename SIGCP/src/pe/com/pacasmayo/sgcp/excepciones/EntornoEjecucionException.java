package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EntornoEjecucionException.java
 * Modificado: Dec 22, 2009 3:03:03 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EntornoEjecucionException extends RuntimeException {

	public EntornoEjecucionException(String mensaje) {
		super(mensaje);
	}

	public EntornoEjecucionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public EntornoEjecucionException(Throwable causa) {
		super(causa);
	}

	public String getClassName() {
		return this.getClass().getName();
	}

	public String getMensaje() {
		return super.getMessage();
	}
}
