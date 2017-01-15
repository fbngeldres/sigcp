package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ProcesoBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProcesoLogicFacade.java
 * Modificado: Dec 14, 2010 11:16:04 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ProcesoLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesos() throws LogicaException;

	/**
	 * @param ordenEjecucion
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorOrdenEjecucion(
			Short ordenEjecucion) throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosBasicosPorCodigoLineaNegocio(
			Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @param orden
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProcesoBean> obtenerProcesosPorOrdenLineaNegocio(
			short orden, Long codigoLineaNegocio) throws LogicaException;

	public abstract List<ProcesoBean> obtenerProcesosPorCodigoLineaNegocio(
			Long codigoLineaNegocio) throws LogicaException;
}
