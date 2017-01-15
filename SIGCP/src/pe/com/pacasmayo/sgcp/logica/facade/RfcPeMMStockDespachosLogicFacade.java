package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RfcPeMMStockDespachosLogicFacade.java
 * Modificado: Jul 9, 2010 3:44:52 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RfcPeMMStockDespachosLogicFacade {

	/**
	 * @param fecha
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<String> obtenerDatosTabla(String fecha) throws LogicaException;

	/**
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 * @throws LogicaException
	 */
	public List<String> obtenerDatosTablaPorRangoFechas(String fechaInicial, String fechaFinal) throws LogicaException;
}