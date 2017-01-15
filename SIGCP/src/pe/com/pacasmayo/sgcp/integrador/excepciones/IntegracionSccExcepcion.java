package pe.com.pacasmayo.sgcp.integrador.excepciones;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: IntegracionSccExcepcion.java
 * Modificado: Mar 11, 2011 3:14:02 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class IntegracionSccExcepcion extends IntegradorExcepcion {

	private static final long serialVersionUID = 1L;

	public IntegracionSccExcepcion(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

	public IntegracionSccExcepcion(String mensaje) {
		super(mensaje);
	}

	public IntegracionSccExcepcion(Throwable causa) {
		super(causa);
	}

}
