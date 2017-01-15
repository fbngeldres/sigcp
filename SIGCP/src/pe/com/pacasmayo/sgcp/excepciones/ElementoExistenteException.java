package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ElementoExistenteException.java
 * Modificado: Dec 22, 2009 2:51:13 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ElementoExistenteException extends AplicacionException {

	public ElementoExistenteException(String mensaje) {
		super(mensaje);
	}

	public ElementoExistenteException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ElementoExistenteException(Throwable causa) {
		super(causa);
	}

}
