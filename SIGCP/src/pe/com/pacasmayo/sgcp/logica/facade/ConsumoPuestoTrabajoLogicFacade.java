package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroVarCalidadDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoPuestoTrabajoLogicFacade.java
 * Modificado: Aug 9, 2011 9:37:58 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConsumoPuestoTrabajoLogicFacade {

	/**
	 * @param codigoPuestoTrabajo Long
	 * @param codProducto Long
	 * @param codigoLineaNegocio Long
	 * @param anno Integer
	 * @param mes Short
	 * @return List<RegistroVarCalidadDTO>
	 * @throws LogicaException
	 */
	public abstract List<RegistroVarCalidadDTO> cargarDetalleVarablesCalidad(Long codigoPuestoTrabajo, Long codProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException;

	public abstract Double hallarPoderCalorficoCarbonPonderado(Long codigoPuestoTrabajo, Long codProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException;
	
	public abstract Double hallarPoderCalorficoCarbonPonderadoDAO(Long codigoPuestoTrabajo, Long codProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws LogicaException;

	public Map<Long, Double[]> hallarConsumoPuestoTrabajo(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable, Boolean valor) throws LogicaException;
}
