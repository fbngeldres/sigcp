package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ElementoNoEncontradoException.java
 * Modificado: Dec 22, 2009 2:53:56 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ElementoNoEncontradoException extends AplicacionException {

	public ElementoNoEncontradoException(String mensaje) {
		super(mensaje);
	}

	public ElementoNoEncontradoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ElementoNoEncontradoException(Throwable causa) {
		super(causa);
	}

}
