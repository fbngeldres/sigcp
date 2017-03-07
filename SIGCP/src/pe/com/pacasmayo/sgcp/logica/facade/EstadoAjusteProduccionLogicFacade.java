package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: EstadoAjusteProduccionLogicFacade.java
 * Modificado: Aug 2, 2010 3:32:08 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoAjusteProduccionLogicFacade {

	/**
	 * M�todo para obtener la lista de estados de los ajustes de produccion
	 * 
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<EstadoAjusteProduccionBean> obtenerEstadosAjusteProduccion() throws LogicaException;

}
