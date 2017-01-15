package pe.com.pacasmayo.sgcp.util;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ManejadorPropiedades.java
 * Modificado: Jan 14, 2010 5:25:59 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ResourceBundle;

/**
 * <pre>
 * Esta clase propociona un manejador que permite el la configuración del proyecto, de acuerdo a lo
 * establecido en un archivo de propiedades
 * </pre>
 */
public class ManejadorPropiedades {

	/** ruta del archivo de propiedades de configuracion */
	private static final String DIRECCION_ARCHIVO_PROPIEDADES = "resources";

	/** Instancia del archivo de configuracion */
	private static ResourceBundle recurso;

	static {
		recurso = ResourceBundle.getBundle(DIRECCION_ARCHIVO_PROPIEDADES);
	}

	/**
	 * Crea una instancia de la clase y establece las propiedades del objeto con
	 * los dados como parametros.
	 */
	private ManejadorPropiedades() {
	}

	/**
	 * Obtiene el mensaje que se corresponde con la clave asignada en el archivo
	 * de propiedades
	 * 
	 * @param clave Clave asociada aun mensaje definida en el archivo de
	 *            propiedades
	 * @return resultado
	 */
	public static String obtenerPropiedadPorClave(String clave) {
		String resultado = "";

		if (clave != null) {
			resultado = recurso.getString(clave);
		}

		return resultado;
	}
}
