package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.TableroControlBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablerocontrol;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TableroControlLogicFacade.java
 * Modificado: Aug 18, 2010 3:09:28 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface TableroControlLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TableroControlBean> obtenerTablerosControl() throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<TableroControlBean> obtenerTablerosControlPorUnidad(Long codigoUnidad) throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Tablerocontrol> obtenerTablerosControlDTOPorUnidad(Long codigoUnidad) throws LogicaException;


}