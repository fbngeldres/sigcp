package pe.com.pacasmayo.sgcp.persistencia.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SQLGrammarException.java
 * Modificado: Dec 22, 2009 2:32:40 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.sql.SQLException;

import org.hibernate.JDBCException;

public class SQLGrammarException extends JDBCException {

	public SQLGrammarException(String string, SQLException root) {
		super(string, root);

	}

	public SQLGrammarException(String string, SQLException root, String sql) {
		super(string, root, sql);

	}

	public String getMensaje() {
		return this.getClass().getName();
	}

}