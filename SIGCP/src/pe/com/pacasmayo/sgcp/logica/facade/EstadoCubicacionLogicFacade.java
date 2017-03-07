package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoCubicacionBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocubicacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: EstadoCubicacionLogicFacade.java
 * Modificado: Jun 8, 2010 9:34:38 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoCubicacionLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<EstadoCubicacionBean> getEstadosCubicacion() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Estadocubicacion> getEstadosCubicacionDTO() throws LogicaException;
}
