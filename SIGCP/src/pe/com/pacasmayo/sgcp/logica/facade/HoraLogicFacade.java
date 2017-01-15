package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.HoraBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hora;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HoraLogicFacade.java
 * Modificado: Dec 14, 2010 11:58:41 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface HoraLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HoraBean> obtenerHoras() throws LogicaException;

	/**
	 * @param codigoHora
	 * @return
	 * @throws LogicaException
	 */
	public abstract Hora obtenerHora(Long codigoHora) throws LogicaException;

	/**
	 * @param codigoHora
	 * @return
	 * @throws LogicaException
	 */
	public abstract HoraBean obtenerHoraBean(Long codigoHora) throws LogicaException;

	/**
	 * @param valorHora
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HoraBean> obtenerHoraPorHora(Short valorHora) throws LogicaException;

	/**
	 * @param codigoTurno
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<HoraBean> obtenerHoraPorTurno(Long codigoTurno) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param horaBean
	 * @throws LogicaException
	 */
	public abstract void insertarHora(HoraBean horaBean) throws LogicaException;

	/**
	 * @param horaBean
	 * @throws LogicaException
	 */
	public abstract void actualizarHora(HoraBean horaBean) throws LogicaException;

	/**
	 * @param horaBean
	 * @throws LogicaException
	 */
	public abstract void eliminarHora(HoraBean horaBean) throws LogicaException;

	/**
	 * @param codigoDivision
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<Hora> obtenerHorasDataObjects(Long codigoDivision) throws LogicaException;

}
