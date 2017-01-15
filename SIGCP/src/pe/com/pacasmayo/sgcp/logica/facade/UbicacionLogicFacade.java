package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
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
	 * Método para ibtener una Ubicacion por medio del codigo
	 * 
	 * @param codigoUbicacion
	 * @return
	 * @throws LogicaException
	 */
	public abstract UbicacionBean obtenerUbicacion(Long codigoUbicacion) throws LogicaException;

	/**
	 * Método para ontener todas las ubicaciones
	 * 
	 * @return
	 */
	public abstract List<UbicacionBean> obtenerUbicaciones() throws LogicaException;

	/**
	 * Método para obtener las ubicaciones por medio del nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UbicacionBean> obtenerUbicacionesPorNombre(String nombre) throws LogicaException;

	/**
	 * Método para obtener las ubicaciones por medio del código del Almacén
	 * 
	 * @param codigoAlmacen
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<UbicacionBean> obtenerUbicacionesPorCodigoAlmacen(Long codigoAlmacen) throws LogicaException;

	/**
	 * Métodp para obtener los atributos del filtrado
	 * 
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * Método para insertar una Ubicacion en la BD.
	 * 
	 * @param ubicacionBean
	 * @throws LogicaException
	 */
	public abstract void insertarUbicacion(UbicacionBean ubicacionBean) throws LogicaException;

	/**
	 * Método para actualizar una Ubicacion en la BD.
	 * 
	 * @param ubicacionBean
	 * @throws LogicaException
	 */
	public abstract void actualizarUbicacion(UbicacionBean ubicacionBean) throws LogicaException;

	/**
	 * Método para eliminar una Ubicacion de la BD
	 * 
	 * @param ubicacionBean
	 * @throws LogicaException
	 */
	public abstract void eliminarUbicacion(UbicacionBean ubicacionBean) throws LogicaException;

}
