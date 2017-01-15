package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: UnidadMedidaLogicFacade.java
 * Modificado: Jan 25, 2010 8:36:44 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface UnidadMedidaLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UnidadMedidaBean> obtenerUnidadMedidas() throws LogicaException;

	public abstract List<UnidadMedidaBean> obtenerUnidadMedidasOrdenAlfabetico() throws LogicaException;

	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public UnidadMedidaBean obtenerUnidadMedidaPorNombre(String nombre) throws LogicaException;

	/**
	 * @param codigoUnidadMedida
	 * @return
	 * @throws LogicaException
	 */
	public UnidadMedidaBean obtenerUnidadMedidaPorCodigo(Long codigoUnidadMedida) throws LogicaException;

	/**
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public UnidadMedidaBean obtenerUnidadMedidaPorPuestoTrabajo(Long codigoPuestoTrabajo) throws LogicaException;
}
