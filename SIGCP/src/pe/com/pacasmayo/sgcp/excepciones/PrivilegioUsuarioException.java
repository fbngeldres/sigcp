package pe.com.pacasmayo.sgcp.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PrivilegioUsuarioException.java
 * Modificado: Dec 22, 2009 3:09:34 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class PrivilegioUsuarioException extends AutenticacionException {

	public PrivilegioUsuarioException(String mensaje) {
		super(mensaje);

	}

	public PrivilegioUsuarioException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public PrivilegioUsuarioException(Throwable causa) {
		super(causa);
	}

}
