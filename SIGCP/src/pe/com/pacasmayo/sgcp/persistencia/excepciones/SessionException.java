package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SessionException.java
 * Modificado: Dec 22, 2009 2:40:29 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import org.hibernate.HibernateException;

public class SessionException extends HibernateException {

	public SessionException(String mensaje) {
		super(mensaje);

	}

	public SessionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public SessionException(Throwable causa) {
		super(causa);
	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}
