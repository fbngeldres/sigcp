package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoPuestoTrabajoQuerier.java
 * Modificado: Dec 3, 2009 4:13:19 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadopuestotrabajo;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class EstadoPuestoTrabajoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener una lista de Estados de Puestos de Trabajo
	 * 
	 * @return
	 */
	public static List<Estadopuestotrabajo> getAll() throws AplicacionException {

		return Querier.getAll(Estadopuestotrabajo.class);
	}

	/**
	 * Método para obtener una lista de objetos Estadopuestotrabajo, ordenadas
	 * por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Estadopuestotrabajo> getAllOrderBy(String order) {

		return Querier.getAll(Estadopuestotrabajo.class, order);
	}

	/**
	 * Método para obtener un Estadopuestotrabajo de la BD.
	 * 
	 * @param EstadoPuestoTrabajoImplBean
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadopuestotrabajo getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadopuestotrabajo) Querier.getById(Estadopuestotrabajo.class, codigo);
	}
}