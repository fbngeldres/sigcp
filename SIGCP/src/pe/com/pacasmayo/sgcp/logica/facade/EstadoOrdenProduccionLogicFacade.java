package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoOrdenProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoordenproduccion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoOrdenProduccionLogicFacade.java
 * Modificado: Mar 22, 2011 2:07:41 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoOrdenProduccionLogicFacade {

	public abstract List<Estadoordenproduccion> obtenerEstadosOrdenProduccion() throws LogicaException;

	public abstract List<EstadoOrdenProduccionBean> obtenerEstadosOrdenProduccionBean() throws LogicaException;

}
