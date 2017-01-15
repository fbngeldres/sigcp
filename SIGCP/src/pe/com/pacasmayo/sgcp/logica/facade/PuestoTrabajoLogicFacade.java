package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: PuestoTrabajoLogicFacade.java
 * Modificado: Jan 25, 2010 8:33:47 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.PuestoTrabajoBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface PuestoTrabajoLogicFacade {

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public List<PuestoTrabajoBean> obtenerPuestosTrabajoPorUnidad(
			Long codigoUnidad) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<PuestoTrabajoBean> obtenerPuestosTrabajo()
			throws LogicaException;

}
