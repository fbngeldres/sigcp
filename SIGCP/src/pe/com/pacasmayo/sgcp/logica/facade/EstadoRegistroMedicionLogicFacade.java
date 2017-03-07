package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoregistromedicion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoRegistroMedicionLogicFacade.java
 * Modificado: Apr 22, 2010 6:00:02 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoRegistroMedicionLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Estadoregistromedicion> obtenerEstadosRegistroMedicionDataObjects() throws LogicaException;

	/**
	 * @param codigoEstado
	 * @return
	 * @throws LogicaException
	 */
	public abstract Estadoregistromedicion obtenerEstadoRegistroMedicionDataObject(Long codigoEstado) throws LogicaException;
}