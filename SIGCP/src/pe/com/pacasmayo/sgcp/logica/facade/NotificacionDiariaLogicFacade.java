package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: NotificacionDiariaLogicFacade.java
 * Modificado: Jun 25, 2010 3:28:45 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.NotificaciondiariaDTO;

public interface NotificacionDiariaLogicFacade {

	/**
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @param codigoPuestoTrabajo
	 * @param codigoEstado
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<NotificacionDiariaBean> obtenerNotificacionesDiariasPorFiltros(Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoEstado, Date fechaInicio, Date fechaFin) throws LogicaException;

	/**
	 * Agregado por Stephany Metodo que trae todas las notificaciones excepto
	 * las del estado Generada
	 */
	/**
	 * @param codigoSociedad
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @param codigoPuestoTrabajo
	 * @param codigoEstado
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<NotificacionDiariaBean> obtenerNotificacionesDiariasEstadoPorFiltros(Long codigoSociedad,
			Long codigoUnidad, Long codigoLineaNegocio, Long codigoEstado, Date fechaInicio, Date fechaFin)
			throws LogicaException;

	/**
	 * @param codigo
	 * @return
	 * @throws LogicaException
	 */
	public abstract NotificacionDiariaBean obtenerNotificacionDiaria(Long codigo) throws LogicaException;

	/**
	 * @param codigo
	 * @return
	 * @throws LogicaException
	 */
	public abstract Notificaciondiaria obtenerNotificacionDiariaDataObject(Long codigo) throws LogicaException;

	/**
	 * @param notificacionDiariaBean
	 * @throws LogicaException
	 */
	public abstract void actualizarEstadoNotificacion(NotificacionDiariaBean notificacionDiariaBean, Long codigoEstado)
			throws LogicaException;

	/**
	 * @param codigoUnidad
	 * @param codigoLineaNegocio
	 * @param codigoActividad
	 * @param codigoTableroControl
	 * @param codigoPuestoTrabajo
	 * @param codigoEstado
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<NotificacionDiariaBean> obtenerNotificacionesDiariasCantera(Long codigoUnidad, Long codigoLineaNegocio,
			Long codigoActividad, Long codigoTableroControl, Long codigoPuestoTrabajo, Long codigoEstado, Date fechaInicio,
			Date fechaFin) throws LogicaException;

	/**
	 * @param codigoUsuarioRegistra
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param codigoEstadoNotificacion
	 * @param fechaRegistro
	 * @param usuario 
	 * @param codigoNotifDiaria
	 * @throws LogicaException
	 */
	public abstract void insertarNotificacionPlanta(Long codigoUsuarioRegistra, Long codigoLineaNegocio,
			Long codigoTableroControl, Long codigoEstadoNotificacion, Date fechaRegistro, UsuarioBean usuario) throws LogicaException;


	/**
	 * @param codigoUsuarioRegistra
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param codigoEstado
	 * @param fechaRegistro
	 * @return
	 * @throws LogicaException
	 */
	public abstract NotificaciondiariaDTO obtenerNotificacionDiariaDTO(Long codigoLineaNegocio, Long codigoTableroControl,
			Date fechaRegistro) throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @param fechaRegistro
	 * @return
	 * @throws LogicaException
	 */
	public abstract NotificacionDiariaBean obtenerNotificacionDiariaCantera(Long codigoLineaNegocio, Date fechaRegistro)
			throws LogicaException;

	/**
	 * @param codigoNotificacionDiaria
	 * @return
	 * @throws LogicaException
	 */
	public abstract NotificacionDiariaBean obtenerNotificacionDiariaPorCodigo(Long codigoNotificacionDiaria)
			throws LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @param fechaRegistro
	 * @return
	 * @throws LogicaException
	 */
	public abstract NotificacionDiariaBean obtenerNotificacionDiariaCanteraBase(Long codigoLineaNegocio, Date fechaRegistro)
			throws LogicaException;

	/**
	 * Obtiene una notificacion diaria segun la linea de negocio, tableron de
	 * control y fecha
	 * 
	 * @param codigoLineaNegocio codigo de la Linea de Negocio
	 * @param codigoTableroControl codigo del Tablero de Control
	 * @param fechaRegistro fecha de registro
	 * @return Notificaciondiaria
	 * @throws LogicaException sila busqeuda falla
	 */
	public Notificaciondiaria obtenerNotificacionDiariaDO(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro)
			throws LogicaException;

	/**
	 * Elimina las notificacion de produccion de una produccion diaria, usuando
	 * el casca de hibernate tambien se eliminan componetenes notificacion
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoTableroControl
	 * @param fechaRegistro
	 * @param codigoPuestoTrabajo
	 * @param usuario
	 * @throws LogicaException si el query para elminar falla
	 */
	public abstract void eliminarNotificacionesProduccion(Long codigoLineaNegocio, Long codigoTableroControl, Date fechaRegistro,
			Long codigoNotifDiaria, Long codigoPuestoTrabajo, UsuarioBean usuario) throws LogicaException;

	/**
	 * Aprueba la notificacion diaria pasada como parametro
	 * 
	 * @param notificacion NotificacionDiariaBean
	 * @param usuario
	 * @throws LogicaException si ocurre un error en el proceso de aprobacion
	 */
	public NotificacionDiariaBean aprobarNotificacion(NotificacionDiariaBean notificacion, UsuarioBean usuario)
			throws LogicaException;

	/*
	 * Comentado por Jordan
	 */
	public NotificacionDiariaBean obtenerNotificacion(Long codigoLineaNegocio, Date fechaRegistro) throws LogicaException;

	/**
	 * Agregado por Fabian Metodo que se encarga de desaprobar una notificacion
	 * teniendo como parametro lineaNegocio y fechanotificacion
	 */
	// FIXME ELIMINAR ESTE METODO
	public abstract boolean desaprobarNotificacion(Long lineaNegocio, Date fecha);

	/*
	 * Agregado por Stephany
	 */
	public abstract ResultadoBeanImpl cerrarNotificacion(NotificacionDiariaBean notificacion, UsuarioBean usuario)
			throws LogicaException;

	public abstract boolean reprocesarNotificacion(NotificacionDiariaBean notificacion, UsuarioBean usuario)
			throws LogicaException;

	public abstract NotificacionDiariaBean obtenerUltimaNotificacionDiaria(NotificacionDiariaBean notificacion)
			throws LogicaException;

	public abstract NotificacionDiariaBean obtenerUltimaNotificacionDiariaAprobada(NotificacionDiariaBean notificacion)
			throws LogicaException;

	/**
	 * @param codigo
	 * @return
	 */
	public abstract boolean abrirNotificacionDiaria(Long codigo, UsuarioBean usuario) throws LogicaException;

}