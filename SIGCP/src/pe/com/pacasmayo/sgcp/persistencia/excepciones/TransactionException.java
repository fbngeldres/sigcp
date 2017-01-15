package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TransactionException.java
 * Modificado: Dec 22, 2009 2:34:28 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import org.hibernate.HibernateException;

public class TransactionException extends HibernateException {

	public TransactionException(String mensaje) {
		super(mensaje);

	}

	public TransactionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public TransactionException(Throwable causa) {
		super(causa);
	}

	public String getMensaje() {
		return this.getClass().getName();
	}
}
