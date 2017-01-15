package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoajusteproduccion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoAjusteProduccionQuerier.java
 * Modificado: Aug 2, 2010 3:21:04 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EstadoAjusteProduccionQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener una lista de Estadoajusteproduccion
	 * 
	 * @return
	 */
	public static List<Estadoajusteproduccion> getAll() {

		return Querier.getAll(Estadoajusteproduccion.class);
	}

	/**
	 * Método para obtener una lista de Estadoajusteproduccion, ordenados por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Estadoajusteproduccion> getAllOrderBy(String order) {

		return Querier.getAll(Estadoajusteproduccion.class, order);
	}

	/**
	 * Método para obtener el estado por Nombre
	 * 
	 * @param name
	 * @return
	 */
	public static Estadoajusteproduccion findByName(String name) {

		List<Estadoajusteproduccion> estados = Querier.findByProperty(Estadoajusteproduccion.class,
				"nombreEstadoajusteproduccion", name);

		if (estados != null && estados.size() > 0)
			return estados.get(0);
		else
			return null;
	}

	/**
	 * Método para obtener un Estadoajusteproduccion de la BD por código.
	 * 
	 * @param codigo
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadoajusteproduccion getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadoajusteproduccion) Querier.getById(Estadoajusteproduccion.class, codigo);

	}

}
