package pe.com.pacasmayo.sgcp.logica.facade;

import pe.com.pacasmayo.sgcp.bean.NotificacionProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionProduccionLogicFacade.java
 * Modificado: Sep 23, 2010 4:55:01 PM 
 * Autor: ricardo.marquez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface NotificacionCambioProduccionLogicFacade {

	/**
	 * @param datoreporteDTO
	 * @param notificacionPorduccionCodigo
	 * @param horaCambioProduccion
	 * @throws LogicaException
	 */
	public abstract void modificarNotificacionProduccion(Notificacionproduccion notificacionPorduccion,
			DatoReporteDTO datoreporteDTO) throws LogicaException;

	/**
	 * @param codigo
	 * @return
	 * @throws LogicaException
	 */
	public NotificacionProduccionBean obtenerNotificacionProduccionById(Long codigo) throws LogicaException;

	/**
	 * @param notificacionProduccionCodigo
	 * @param ordenProduccionCodigo
	 * @param medioAlmacenamientoCodigo
	 * @throws LogicaException
	 */
	public void crearNotificacionProduccionLavado(Notificacionproduccion notificacionproduccion) throws LogicaException;

}