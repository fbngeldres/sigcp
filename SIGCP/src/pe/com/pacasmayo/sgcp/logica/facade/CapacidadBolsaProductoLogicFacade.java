package pe.com.pacasmayo.sgcp.logica.facade;

import pe.com.pacasmayo.sgcp.bean.CapacidadBolsaProductoBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * Backlog 
 * Archivo: CapacidadBolsaProductoLogicFacade.java
 * Modificado: Mar 18, 2014 5:42:26 PM
 * Autor: ginteldos
 *
 * Copyright (C) Gintelligence, 2012. All rights reserved.
 *
 * Developed by: Gintelligence. http://www.gintelligence.net
 */
public interface CapacidadBolsaProductoLogicFacade {

	public CapacidadBolsaProductoBean obtenerPorCodigoSap(String codigoSap) throws LogicaException;

}
