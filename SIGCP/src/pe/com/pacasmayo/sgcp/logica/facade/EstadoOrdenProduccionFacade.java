package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoOrdenProduccionFacade.java
 * Modificado: 
 * Autor:
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoordenproduccion;

public interface EstadoOrdenProduccionFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Estadoordenproduccion> obtenerEstadosOrdenProduccion() throws LogicaException;
}
