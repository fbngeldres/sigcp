package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.PlantillaProductoBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PlantillaProductoDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlantillaProductoLogicFacade.java
 * Modificado: Feb 9, 2011 3:07:58 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface PlantillaProductoLogicFacade {

	/**
	 * Obtiene una lista de objetos Columnaplantillaproducto segun los datos de
	 * la platilla producto
	 * 
	 * @param codigoPlantillaReporte
	 * @param codigoProducto
	 * @return List<Columnaplantillaproducto>
	 * @throws LogicaException
	 */
	public abstract List<PlantillaProductoBean> obtenerPorPlantillaReporteYProducto(Long codigoPlantillaReporte, Long codigoProducto)
			throws LogicaException;

	/**
	 * Obtiene un objeto Columnaplantillaproducto segun los datos de la platilla
	 * producto y la ultima version
	 * 
	 * @param codigoPlantillaReporte
	 * @param codigoProducto
	 * @return Columnaplantillaproducto
	 * @throws LogicaException
	 */
	public PlantillaProductoDTO obtenerActualPorPlantillaReporteYProducto(Long codigoPlantillaReporte, Long codigoProducto)
			throws LogicaException;

}
