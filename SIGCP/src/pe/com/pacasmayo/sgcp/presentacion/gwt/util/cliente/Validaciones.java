package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: Validaciones.java
 * Modificado: Apr 9, 2010 5:01:57 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

/**
 * Clase utilitaria de validaciones genericas
 */
public class Validaciones {

	public static boolean isInteger(String cadena) {

		try {
			Integer.parseInt(cadena);
			return true;

		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isDouble(String cadena) {

		try {
			Double.parseDouble(cadena);
			return true;

		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
