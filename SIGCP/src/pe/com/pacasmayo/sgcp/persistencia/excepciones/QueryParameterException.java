package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: QueryParameterException.java
 * Modificado: Dec 22, 2009 2:40:29 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class QueryParameterException extends QueryException {

	public QueryParameterException(String mensaje) {
		super(mensaje);

	}

	public QueryParameterException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public QueryParameterException(Throwable causa) {
		super(causa);
	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}