package pe.com.pacasmayo.sgcp.logica.facade;

import java.io.ByteArrayOutputStream;

import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

import com.itextpdf.text.DocumentException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ParteDiarioLogicFacade.java
 * Modificado: Jun 1, 2010 4:45:21 PM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface ParteDiarioLogicFacade {

	

	/**
	 * Realiza el registro de parte diario, esto no es mas que tener un resumen
	 * del ingreso, produccion, consumo y salida, por PRODUCTO y PUESTO DE
	 * TRABAJO
	 * 
	 * @param notificacionBean
	 * @param usuario
	 */
	public abstract void registrarPartediario(NotificacionDiariaBean notificacionBean, UsuarioBean usuario)
			throws LogicaException;

	/**
	 * Agregado Por Fabian Interface para generar el Reporte Parte diario en PDF
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param anio
	 * @param mes
	 * @param division
	 * @param sociedad
	 * @param unidad
	 * @param rutaImg
	 * @return
	 * @throws LogicaException
	 * @throws DocumentException
	 */
	public ByteArrayOutputStream generarReporteDetalleParteDiario(Long codigoProceso, Long codigoProducto, Integer anio,
			Short mes, Long division, Long sociedad, Long unidad, String rutaImg) throws LogicaException, DocumentException;

	/**
	 * Agregado Por Jordan Interface para generar el Reporte Parte diario en XLS
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param anio
	 * @param mes
	 * @param division
	 * @param sociedad
	 * @param unidad
	 * @param rutaImg
	 * @return
	 * @throws LogicaException
	 * @throws DocumentException
	 */
	public ByteArrayOutputStream generarReporteDetalleParteDiarioEXCEL(Long codigoProceso, Long codigoProducto, Integer anio,
			Short mes, Long division, Long sociedad, Long unidad, String rutaImg) throws LogicaException, DocumentException;



	


}
