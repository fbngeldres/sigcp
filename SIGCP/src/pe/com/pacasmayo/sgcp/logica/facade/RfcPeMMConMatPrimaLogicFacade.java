package pe.com.pacasmayo.sgcp.logica.facade;

import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RfcPeMMConMatPrimaLogicFacade.java
 * Modificado: Jul 8, 2010 5:11:28 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RfcPeMMConMatPrimaLogicFacade {

	/**
	 * @param fechaDoc
	 * @param consumos
	 * @throws LogicaException
	 */
	public abstract ResultadoBeanImpl ingresarDatosPorLote(String fechaP_BUDAT, Long codigoNotif, String fechaBLDATT)
			throws LogicaException;

}