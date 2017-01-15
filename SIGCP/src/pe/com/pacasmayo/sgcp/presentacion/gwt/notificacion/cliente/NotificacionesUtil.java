package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente;

import java.io.Serializable;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.componentes.GridVariablesProduccion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Servicio;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioAsync;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: NotificacionesUtil.java
 * Modificado: Apr 13, 2011 10:44:02 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class NotificacionesUtil implements Serializable {

	private static final long serialVersionUID = 8740717414267483093L;

	ServicioAsync servicioComunicacion = GWT.create(Servicio.class);

	/**
	 * Metodo que cambia las ordenes de produccion en los registros siguientes
	 * de mismo turno y de turnos siguientes
	 * 
	 * @param gridVarProd
	 * @param record
	 * @param codigoPlantillaReporte
	 */
	public static void cambiarOrdenProduccionEnHorasSiguientes(GridVariablesProduccion gridVarProd, ListGridRecord record) {
		try {
			ListGridRecord[] records = gridVarProd.getRecords();
			String codigoHoraStr = record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA);
			if (codigoHoraStr == null) {
				return;
			}
			GWT.log("hora str: " + codigoHoraStr);
			short valorHoraElementoCambioProd = Short.parseShort(codigoHoraStr);
			GWT.log("hora de cambio  de produccion: " + valorHoraElementoCambioProd);
			long codigoTurnoElementoCambioProd = 0;
			String codigoTurnoRecord = record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);
			if (codigoTurnoRecord != null && !codigoTurnoRecord.equals("")) {
				codigoTurnoElementoCambioProd = Long.parseLong(record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO));
			}
			GWT.log("turno de cambio  de produccion: " + codigoTurnoElementoCambioProd);
			for (int i = 0; i < records.length; i++) {
				short valorHora = Short.parseShort(records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA));
				long codigoTurno = 0;
				String codigoTurnoRecords = records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);
				if (codigoTurnoRecords != null && !codigoTurnoRecords.equals("")) {
					codigoTurno = Long.parseLong(records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO));
				}

				if (codigoTurno == codigoTurnoElementoCambioProd && valorHora > valorHoraElementoCambioProd) {
					asinarOrdenProduccionActualDesdeRecord(records[i], record);
					gridVarProd.refreshRow(i);
				}
				if (codigoTurno > codigoTurnoElementoCambioProd) {
					asinarOrdenProduccionActualDesdeRecord(records[i], record);
					gridVarProd.refreshRow(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Asigna los datos del registro actual al registro siguiente
	 * 
	 * @param registroSiguiente
	 * @param registroActual
	 * @param codigoPlantillaReporteF
	 */
	private static void asinarOrdenProduccionActualDesdeRecord(ListGridRecord registroSiguiente, ListGridRecord registroActual) {
		String valorColOrden = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_ORDEN);
		String valorColCodOrden = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN);
		String valorColCodProducto = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PRODUCTO);
		String valorColCodLineaNeg = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_LINEA_NEGOCIO);
		String valorColNombLineaNeg = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_NOMBRE_LINEA_NEGOCIO);

		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_ORDEN, valorColOrden);
		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN, valorColCodOrden);
		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PRODUCTO, valorColCodProducto);
		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_LINEA_NEGOCIO, valorColCodLineaNeg);
		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_NOMBRE_LINEA_NEGOCIO, valorColNombLineaNeg);
	}

	/**
	 * Metodo que cambia las ordenes de produccion en los registros siguientes
	 * de mismo turno y de turnos siguientes
	 * 
	 * @param gridVarProd
	 * @param record
	 */
	public static void cambiarSiloEnHorasSiguientes(GridVariablesProduccion gridVarProd, ListGridRecord record) {
		ListGridRecord[] records = gridVarProd.getRecords();

		short valorHoraElementoCambioProd = Short.parseShort(record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA));
		GWT.log("hora de cambio  de produccion: " + valorHoraElementoCambioProd);

		long codigoTurnoElementoCambioProd = 0;
		String codigoTurnoRecord = record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);
		if (codigoTurnoRecord != null && !codigoTurnoRecord.equals("")) {
			codigoTurnoElementoCambioProd = Long.parseLong(record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO));
		}
		GWT.log("turno de cambio  de produccion: " + codigoTurnoElementoCambioProd);

		String codigoOrdenProduccion = record.getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN);

		for (int i = 0; i < records.length; i++) {

			if (codigoOrdenProduccion.equals(records[i].getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN))) {

				short valorHora = Short.parseShort(records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA));
				long codigoTurno = 0;
				String codigoTurnoRecords = records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);
				if (codigoTurnoRecords != null && !codigoTurnoRecords.equals("")) {
					codigoTurno = Long.parseLong(records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO));
				}

				if (codigoTurno == codigoTurnoElementoCambioProd && valorHora > valorHoraElementoCambioProd) {
					asinarSiloActual(records[i], record);
				}
				if (codigoTurno > codigoTurnoElementoCambioProd) {
					asinarSiloActual(records[i], record);
				}
			}
		}
	}

	/**
	 * Asigna los datos del registro actual al registro siguiente
	 * 
	 * @param registroSiguiente
	 * @param registroActual
	 */
	private static void asinarSiloActual(ListGridRecord registroSiguiente, ListGridRecord registroActual) {
		String nombreSilo = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_SILO);
		String codigoSilo = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_SILO);

		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_SILO, nombreSilo);
		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_SILO, codigoSilo);
	}

	/**
	 * @param ordenDTO
	 * @return
	 */
	public static ListGridRecord asinarOrdenDesdeOrdenDTO(OrdenProduccionDTO ordenDTO, GridVariablesProduccion gridVarProd) {
		LineaNegocioDTO lineaNegocioDTO = ordenDTO.getProduccion().getProceso().getLineaNegocio();

		ListGridRecord record = gridVarProd.getSelectedRecord();

		GWT.log("Obtuvo registro dle grid");

		record.setAttribute(GridVariablesProduccion.COLUMNA_ORDEN, ordenDTO.getProduccion().getProducto().getNombreProducto()
				+ " " + ordenDTO.getProduccion().getProceso().getSiglasProceso());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_ORDEN, ordenDTO.getPkCodigoOrdenproduccion());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PRODUCTO, ordenDTO.getProduccion().getProducto()
				.getPkCodigoProducto().toString());
		record.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_LINEA_NEGOCIO, lineaNegocioDTO.getPkCodigoLineanegocio());
		record.setAttribute(GridVariablesProduccion.COLUMNA_NOMBRE_LINEA_NEGOCIO, lineaNegocioDTO.getNombreLineanegocio());
		return record;
	}

	/**
	 * Metodo que cambia las ordenes de produccion en los registros siguientes
	 * de mismo turno y de turnos siguientes
	 * 
	 * @param gridVarProd
	 * @param record
	 */
	public static void cambiarPlantillaEnHorasSiguientes(GridVariablesProduccion gridVarProd, ListGridRecord record) {
		try {
			ListGridRecord[] records = gridVarProd.getRecords();

			short valorHoraElementoCambioProd = Short
					.parseShort(record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA));
			GWT.log("hora de cambio  de produccion: " + valorHoraElementoCambioProd);

			long codigoTurnoElementoCambioProd = 0;
			String codigoTurnoRecord = record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);
			if (codigoTurnoRecord != null && !codigoTurnoRecord.equals("")) {
				codigoTurnoElementoCambioProd = Long.parseLong(record.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO));
			}
			GWT.log("turno de cambio  de produccion: " + codigoTurnoElementoCambioProd);

			String codigoOrdenProduccion = record.getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN);

			for (int i = 0; i < records.length; i++) {
				if (codigoOrdenProduccion.equals(records[i].getAttribute(gridVarProd.COLUMNA_CODIGO_ORDEN))) {

					short valorHora = Short.parseShort(records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_HORA));

					long codigoTurno = 0;
					String codigoTurnoRecords = records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO);
					if (codigoTurnoRecords != null && !codigoTurnoRecords.equals("")) {
						codigoTurno = Long.parseLong(records[i].getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_TURNO));
					}

					if (codigoTurno == codigoTurnoElementoCambioProd && valorHora > valorHoraElementoCambioProd) {
						asinarPlantillaActual(records[i], record);
						gridVarProd.refreshRow(i);
					}
					if (codigoTurno > codigoTurnoElementoCambioProd) {
						asinarPlantillaActual(records[i], record);
						gridVarProd.refreshRow(i);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Asigna los datos del registro actual al registro siguiente
	 * 
	 * @param registroSiguiente
	 * @param registroActual
	 */
	private static void asinarPlantillaActual(ListGridRecord registroSiguiente, ListGridRecord registroActual) {
		String nombrePlantilla = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_PLANTILLA);
		String codigoPalntilla = registroActual.getAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PLANTILLA);

		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_PLANTILLA, nombrePlantilla);
		registroSiguiente.setAttribute(GridVariablesProduccion.COLUMNA_CODIGO_PLANTILLA, codigoPalntilla);
	}

}
