package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: UbicacionLogicFacade.java
 * Modificado: Jan 27, 2010 10:26:26 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.UbicacionBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public interface UbicacionLogicFacade {

	/**
	 * M�todo para ibtener una Ubicacion por medio del codigo
	 * 
	 * @param codigoUbicacion
	 * @return
	 * @throws LogicaException
	 */
	public abstract UbicacionBean obtenerUbicacion(Long codigoUbicacion) throws LogicaException;

	/**
	 * M�todo para ontener todas las ubicaciones
	 * 
	 * @return
	 */
	public abstract List<UbicacionBean> obtenerUbicaciones() throws LogicaException;

	/**
	 * M�todo para obtener las ubicaciones por medio del nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UbicacionBean> obtenerUbicacionesPorNombre(String nombre) throws LogicaException;

	/**
	 * M�todo para obtener las ubicaciones por medio del c�digo del Almac�n
	 * 
	 * @param codigoAlmacen
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UbicacionBean> obtenerUbicacionesPorCodigoAlmacen(Long codigoAlmacen) throws LogicaException;

	/**
	 * M�todp para obtener los atributos del filtrado
	 * 
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * M�todo para insertar una Ubicacion en la BD.
	 * 
	 * @param ubicacionBean
	 * @throws LogicaException
	 */
	public abstract void insertarUbicacion(UbicacionBean ubicacionBean) throws LogicaException;

	/**
	 * M�todo para actualizar una Ubicacion en la BD.
	 * 
	 * @param ubicacionBean
	 * @throws LogicaException
	 */
	public abstract void actualizarUbicacion(UbicacionBean ubicacionBean) throws LogicaException;

	/**
	 * M�todo para eliminar una Ubicacion de la BD
	 * 
	 * @param ubicacionBean
	 * @throws LogicaException
	 */
	public abstract void eliminarUbicacion(UbicacionBean ubicacionBean) throws LogicaException;

}
