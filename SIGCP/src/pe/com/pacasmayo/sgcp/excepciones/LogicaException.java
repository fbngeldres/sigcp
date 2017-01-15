package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: LogicaException.java
 * Modificado: Dec 22, 2009 3:13:41 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class LogicaException extends AplicacionException {

	public LogicaException(Throwable causa) {
		super(causa);

	}

	public LogicaException(String mensaje) {
		super(mensaje);

	}

	public LogicaException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

}
