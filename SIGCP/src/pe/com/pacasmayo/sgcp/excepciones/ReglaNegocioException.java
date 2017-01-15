package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: ReglaNegocioException.java
 * Modificado: Dec 22, 2009 3:02:01 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ReglaNegocioException extends AplicacionException {

	public ReglaNegocioException(String mensaje) {
		super(mensaje);

	}

	public ReglaNegocioException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ReglaNegocioException(Throwable causa) {
		super(causa);
	}

}
