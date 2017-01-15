package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RfcPeMMIngresosDiarioLogicFacade.java
 * Modificado: Jul 6, 2010 9:27:58 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RfcPeMMIngresosDiarioLogicFacade {
	/**
	 * @param fechaContabilizacion
	 * @return
	 * @throws LogicaException
	 */
	public List<String> obtenerDatosTabla(String fechaContabilizacion) throws LogicaException;
}