package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SesionVencidaExcepction.java
 * Modificado: Dec 22, 2009 3:05:40 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class SesionVencidaException extends EntornoEjecucionException {

	public SesionVencidaException(String mensaje) {
		super(mensaje);

	}

	public SesionVencidaException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public SesionVencidaException(Throwable causa) {
		super(causa);
	}

}
