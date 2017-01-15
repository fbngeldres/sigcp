package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnresolvableObjectException.java
 * Modificado: Dec 22, 2009 2:36:53 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import org.hibernate.HibernateException;

public class UnresolvableObjectException extends HibernateException {

	public UnresolvableObjectException(String s) {
		super(s);

	}

	public UnresolvableObjectException(String string, Throwable root) {
		super(string, root);

	}

	public UnresolvableObjectException(Throwable root) {
		super(root);

	}

	public String getMensaje() {
		return this.getClass().getName();
	}
}
