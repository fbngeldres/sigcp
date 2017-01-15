package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;
import java.util.Set;

import org.hibernate.Transaction;

import pe.com.pacasmayo.sgcp.bean.ColumnaReporteBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillareporte;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ColumnareporteDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ColumnaReporteLogicFacade.java
 * Modificado: Jun 10, 2010 3:18:52 AM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ColumnaReporteLogicFacade {

	/**
	 * Método para almacenar un listado de columnas de acuerdo con una plantilla
	 * 
	 * @param columnasBean
	 * @param plantilla
	 * @param tx
	 * @throws LogicaException
	 * @throws AplicacionException
	 */
	public abstract void insertarColumnasReporte(List<ColumnaReporteBean> columnasBean, Plantillareporte plantilla, Transaction tx)
			throws LogicaException, AplicacionException;

	/**
	 * Método para eliminar un listado de columnas de una plantilla
	 * 
	 * @param columnas
	 * @param tx
	 * @throws LogicaException
	 */
	public abstract void eliminarColumnasReporte(Set<Columnareporte> columnas, Transaction tx) throws LogicaException;

	/**
	 * Método para actualizar un listado de columnas de una plantilla
	 * 
	 * @param columnasBean
	 * @param plantilla
	 * @param tx
	 * @throws LogicaException
	 */
	public abstract void actualizarColumnasReporte(List<ColumnaReporteBean> columnasBean, Plantillareporte plantilla,
			Transaction tx) throws LogicaException;

	/**
	 * @param codigoPlantilla
	 * @param estado
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ColumnaReporteBean> obtenerColumnasPorPlantillaYEstado(Long codigoPlantilla, String estado)
			throws LogicaException;

	

	/**
	 * @param codigoPuestoTrabajo
	 * @param codigoProceso
	 * @param estadoColumna
	 * @param estadoPlantilla
	 * @param nombreArchivo
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ColumnareporteDTO> obtenerColumnasDTO(Long codigoPuestoTrabajo, Long codigoProceso,
			String estadoColumna, String estadoPlantilla) throws LogicaException;


}
