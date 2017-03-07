package pe.com.pacasmayo.sgcp.logica.facade;

import pe.com.pacasmayo.sgcp.bean.RegistroReporteEcsBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: RegistroReporteECSLogicFacade.java
 * Modificado: Jul 1, 2010 9:59:08 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface RegistroReporteECSLogicFacade {

	/**
	 * M�todo para almacenar en la bd el regitro del reporte con las lecturas
	 * del dia
	 * 
	 * @param registroReporteECSBean
	 * @throws LogicaException
	 */
	public abstract void insertarRegistroReporteECS(RegistroReporteEcsBean registroReporteECSBean) throws LogicaException;

	

}
