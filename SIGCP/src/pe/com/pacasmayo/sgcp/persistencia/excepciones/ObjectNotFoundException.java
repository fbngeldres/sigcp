package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ObjectNotFoundException.java
 * Modificado: Dec 22, 2009 2:38:20 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ObjectNotFoundException extends UnresolvableObjectException {

	public ObjectNotFoundException(String mensaje) {
		super(mensaje);

	}

	public ObjectNotFoundException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ObjectNotFoundException(Throwable causa) {
		super(causa);
	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}