package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoProductoQuerier.java
 * Modificado: Jan 9, 2010 09:58:11 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoproducto;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class EstadoProductoQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos EstadoProductoBean
	 * 
	 * @return
	 */
	public static List<Estadoproducto> getAll() throws AplicacionException {

		return Querier.getAll(Estadoproducto.class);
	}

	/**
	 * Método para obtener un Estadoproducto de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadoproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadoproducto) Querier.getById(Estadoproducto.class, codigo);
	}
}
