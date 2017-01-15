package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: QueryException.java
 * Modificado: Dec 22, 2009 2:29:30 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import org.hibernate.HibernateException;

public class QueryException extends HibernateException {

	public QueryException(String mensaje) {
		super(mensaje);

	}

	public QueryException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public QueryException(Throwable causa) {
		super(causa);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.persistencia.excepciones.QueryParameterException
	 * #getMensaje()
	 */
	public String getMensaje() {
		return this.getClass().getName();
	}
}
