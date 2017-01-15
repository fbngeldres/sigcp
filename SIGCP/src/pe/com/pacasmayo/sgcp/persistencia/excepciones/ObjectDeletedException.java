package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ObjectDeletedException.java
 * Modificado: Dec 22, 2009 2:37:57 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ObjectDeletedException extends UnresolvableObjectException {

	public ObjectDeletedException(String s) {
		super(s);

	}

	public ObjectDeletedException(String string, Throwable root) {
		super(string, root);

	}

	public ObjectDeletedException(Throwable root) {
		super(root);

	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}