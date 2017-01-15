package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.DatoReporteBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Datoreporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DatoReporteDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DatoReporteLogicFacade.java
 * Modificado: Jul 1, 2010 9:53:23 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface DatoReporteLogicFacade {

	/**
	 * Metodo para insertar los datos del reporte de ECS en la BD
	 * 
	 * @param datosBean
	 * @param registroReporteECS
	 * @param tx
	 * @throws LogicaException
	 */
	public abstract void insertarDatosReporte(List<DatoReporteBean> datosBean, Registroreporteecs registroReporteECS,
			Transaction tx) throws LogicaException;

	/**
	 * Metodo para actualizar los datos del reporte de ECS en la BD
	 * 
	 * @param datosBean
	 * @param registroReporteECS
	 * @param tx
	 * @throws LogicaException
	 */
	public void actualizarDatosReporte(List<DatoReporteBean> datosBean, Registroreporteecs registroReporteECS, Transaction tx)
			throws LogicaException;

	/**
	 * @param datos
	 * @param registroReporteECS
	 * @param tx
	 * @throws LogicaException
	 */
	public void actualizarDatosReporteDataObject(List<Datoreporte> datos, Registroreporteecs registroReporteECS, Transaction tx)
			throws LogicaException;

	
	/**
	 * Metodo para obtener la lista de vairables por hora dada una fecha y un
	 * nombre de archivo ECS
	 * 
	 * @param fecha
	 * @param nombre
	 * @return List<DatoreporteDTO>
	 * @throws LogicaException
	 */
	public abstract List<DatoReporteDTO> cargarDatosVariablesPorHoraDTO(Date fecha, String nombre, String tipoVariable,
			Long codigoProceso) throws LogicaException;
}
