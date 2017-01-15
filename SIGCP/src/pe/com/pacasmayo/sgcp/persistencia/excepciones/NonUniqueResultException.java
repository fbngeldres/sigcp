package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NonUniqueResultException.java
 * Modificado: Dec 22, 2009 2:29:30 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import org.hibernate.HibernateException;

public class NonUniqueResultException extends HibernateException {

	public NonUniqueResultException(String mensaje) {
		super(mensaje);

	}

	public NonUniqueResultException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public NonUniqueResultException(Throwable causa) {
		super(causa);
	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}
