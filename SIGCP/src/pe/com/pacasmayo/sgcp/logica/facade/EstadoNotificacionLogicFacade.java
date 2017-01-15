package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.EstadoNotificacionBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadonotificacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoNotificacionLogicFacade.java
 * Modificado: Jun 22, 2010 5:36:25 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface EstadoNotificacionLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<EstadoNotificacionBean> obtenerEstadoNotificacion() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Estadonotificacion> obtenerEstadoNotificacionDataObjects() throws LogicaException;

	/**
	 * @param codigo
	 * @return
	 * @throws LogicaException
	 */
	public abstract EstadoNotificacionBean obtenerEstadoNotificacion(Long codigo) throws LogicaException;

	/**
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract Estadonotificacion obtenerEstadoNotificacionPorNombreDO(String nombre) throws LogicaException;

	public abstract List<EstadoNotificacionBean> obtenerEstadoNotificacionGestionar() throws LogicaException;

}