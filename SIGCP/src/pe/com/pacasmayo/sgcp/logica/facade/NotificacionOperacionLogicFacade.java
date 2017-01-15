package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.Date;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionOperacionLogicFacade.java
 * Modificado: Aug 17, 2010 7:30:07 PM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface NotificacionOperacionLogicFacade {

	/**
	 * @param codigoRegistroReporteECS
	 * @param codigoNotificacionDiaria
	 * @param codigoHora
	 * @param codigoPuestoTrabajo
	 * @param fechaRegistro
	 * @throws LogicaException
	 */
	public abstract void insertarNotificacionOperacion(Long codigoRegistroReporteECS, Long codigoNotificacionDiaria,
			Long codigoHora, Long codigoPuestoTrabajo, Date fechaRegistro) throws LogicaException;

}