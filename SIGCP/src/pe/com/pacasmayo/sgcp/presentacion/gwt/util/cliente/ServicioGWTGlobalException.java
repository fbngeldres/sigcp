package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import java.io.Serializable;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ServidorGWTGlobalException.java
 * Modificado: Jan 26, 2011 10:26:54 AM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ServicioGWTGlobalException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7282110224128778230L;

	public ServicioGWTGlobalException() {
		super();
	}

	public ServicioGWTGlobalException(String mensaje) {
		super(mensaje);
	}

	public ServicioGWTGlobalException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public ServicioGWTGlobalException(Throwable causa) {
		super(causa);
	}

	public String getClassName() {
		return this.getClass().getName();
	}

	public String getMensaje() {
		return super.getMessage();
	}
}
