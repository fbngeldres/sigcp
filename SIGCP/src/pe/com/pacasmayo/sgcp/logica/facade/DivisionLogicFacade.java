package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DivisionLogicFacade.java
 * Modificado: Jan 25, 2010 8:28:14 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Division;

public interface DivisionLogicFacade {

	/**
	 * @param codigoDivision
	 * @return
	 * @throws LogicaException
	 */
	public abstract DivisionBean obtenerDivision(Long codigoDivision) throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<DivisionBean> obtenerDivisiones() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Division> obtenerDivisionesDataObject() throws LogicaException;

	/**
	 * @param nombreDivision
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<DivisionBean> obtenerDivisionesPorNombre(String nombreDivision) throws LogicaException;



}
