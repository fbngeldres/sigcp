package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstrainVioleationException.java
 * Modificado: Dec 22, 2009 2:28:01 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;

public class ConstrainViolationException extends AplicacionException {

	public ConstrainViolationException(String mensaje) {
		super(mensaje);
	}

	public ConstrainViolationException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ConstrainViolationException(Throwable causa) {
		super(causa);
	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}