package pe.com.pacasmayo.sgcp.persistencia.querier;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadbolsaproducto;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AccionQuerier.java
 * Modificado: Sep 29, 2011 11:08:22 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class CapacidadBolsaProductoQuerier extends Querier {
	private static final String CODIGO_SAP_PRODUCTO = "codigoSapProducto";

	/**
	 * Método para obtener la capacidad dependiendo del codigo SAP
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */

	public static Capacidadbolsaproducto obtenerByCodigoSap(String codigoSap) throws ElementoNoEncontradoException {
		return Querier.findByPropertyUniqueResult(Capacidadbolsaproducto.class, CODIGO_SAP_PRODUCTO, codigoSap);
	}

}
