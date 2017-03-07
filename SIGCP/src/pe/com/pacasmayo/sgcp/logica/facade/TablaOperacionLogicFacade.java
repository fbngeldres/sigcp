package pe.com.pacasmayo.sgcp.logica.facade;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TablaOperacionLogicFacade.java
 * Modificado:
 * Autor:
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.ReportePuestoTrabajoProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroPuestoTrabajoDTO;

public interface TablaOperacionLogicFacade {

	
	/**
	 * Este metodo es utilizado para obtener la lista de bean's para el reporte
	 * de Produccion Puesto Trabajo
	 * 
	 * @param mes
	 * @param anio
	 * @return
	 * @throws LogicaException
	 */
	public abstract ReportePuestoTrabajoProduccionBean obtenerProduccionPuestotrabajoReporte(Short mes, Integer anio,
			Long lineaNegocio) throws LogicaException;

	/**
	 * @param mes
	 * @param anio
	 * @param unidad
	 * @param direccionReporte
	 * @throws LogicaException
	 */
	public void enviarCorreo(Short mes, Integer anio, Long unidad, String direccionReporte) throws LogicaException;

}
