package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.UnidadBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadLogicFacade.java
 * Modificado: Dec 14, 2010 11:18:40 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface UnidadLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadBean> obtenerUnidades() throws LogicaException;



	/**
	 * @param codigoSociedad
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadBean> obtenerUnidadesPorCodigoSociedad(
			Long codigoSociedad) throws LogicaException;

}
