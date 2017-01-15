package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ActividadLogicFacade.java
 * Modificado: Jan 25, 2010 11:49:02 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ParametroSistemaBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface ParametroSistemaLogicFacade {

	/**
	 * @param nombreParametro
	 * @return
	 * @throws LogicaException
	 */
	public abstract ParametroSistemaBean obtenerParametroSistema(String nombreParametro) throws LogicaException;

	/**
	 * @param nombreParametro
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ParametroSistemaBean> obtenerParametrosSistema(String nombreParametro) throws LogicaException;

	public abstract ParametroSistemaBean obtenerParametroSistemaDAO(String nombreParametro) throws LogicaException;

}
