package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.OrdenReporteBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: OrdenReporteLogicFacade.java
 * Modificado: Aug 23, 2012 7:26:31 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface OrdenReporteLogicFacade {

	public abstract List<OrdenReporteBean> obtenerOrdenResumenProductoOrdenadoPorTipo(String tipoReporte)
			throws AplicacionException;

	public abstract List<OrdenReporteBean> obtenerOrdenResumenProducto(String tipoReporte) throws AplicacionException;

	public abstract Boolean modificar(OrdenReporteBean ordenReporteBean) throws LogicaException;

	public abstract OrdenReporteBean obtenerPorcodigo(Long codigo) throws LogicaException;

	public abstract Boolean eliminar(Long codigo) throws LogicaException;

	public abstract Boolean ingresar(OrdenReporteBean ordenReporteBean) throws LogicaException;

	public abstract List<String> obtenerTiposReporte() throws LogicaException;

	public abstract List<UtilBean> obtenerAtributos();

	public abstract List<OrdenReporteBean> obtenerOrdenResumenProductoOrdenadoPorTipoDAO(String listarProductosComponentesResumen)throws LogicaException;


}
