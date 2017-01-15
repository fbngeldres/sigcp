package pe.com.pacasmayo.sgcp.integrador.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: IntegradorExcepcion.java
 * Modificado: Mar 11, 2011 3:08:12 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class IntegradorExcepcion extends Exception {

	private static final long serialVersionUID = 1L;

	public IntegradorExcepcion(String mensaje) {
		super(mensaje);
	}

	public IntegradorExcepcion(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public IntegradorExcepcion(Throwable causa) {
		super(causa);
	}

	public String getClassName() {
		return this.getClass().getName();
	}

	public String getMensaje() {
		return super.getMessage();
	}
}
