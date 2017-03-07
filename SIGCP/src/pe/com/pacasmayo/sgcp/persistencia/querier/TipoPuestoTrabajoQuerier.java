package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: TipoPuestoTrabajoQuerier.java
 * Modificado: Jan 9, 2010 10:38:51 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipopuestotrabajo;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class TipoPuestoTrabajoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * M�todo para obtener la lista de objectos Tipopuestotrabajo
	 * 
	 * @return
	 */
	public static List<Tipopuestotrabajo> getAll() throws AplicacionException {

		return Querier.getAll(Tipopuestotrabajo.class);
	}

	/**
	 * M�todo para obtener la lista de objectos Tipopuestotrabajo, ordenado por
	 * un atributo
	 * 
	 * @return
	 */
	public static List<Tipopuestotrabajo> getAllOrderBy(String order) {

		return Querier.getAll(Tipopuestotrabajo.class, order);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * M�todo para obtener una EstadoPuestoTrabajo de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipopuestotrabajo getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipopuestotrabajo.class, codigo);
	}
}
