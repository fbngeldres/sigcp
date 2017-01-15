package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: JDBCConnectionException.java
 * Modificado: Dec 22, 2009 2:29:30 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.sql.SQLException;

import org.hibernate.JDBCException;

public class JDBCConnectionException extends JDBCException {

	public JDBCConnectionException(String string, SQLException root) {
		super(string, root);
	}

	public JDBCConnectionException(String string, SQLException root, String sql) {
		super(string, root, sql);

	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}