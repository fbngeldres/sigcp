package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.bean.ComponenteRegistroOrdenBean;
import pe.com.pacasmayo.sgcp.bean.HojaRutaBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProdConsultaBean;
import pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean;
import pe.com.pacasmayo.sgcp.bean.PlanAnualBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionGlobalException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: OrdenProduccionLogicFacade.java
 * Modificado: Dec 14, 2010 10:28:46 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface OrdenProduccionLogicFacade {

	/**
	 * @param planAnualBeans
	 * @param usuario
	 * @throws LogicaException
	 */
	public abstract void generarOrdenProduccionAutomatica(PlanAnualBean planAnualBeans, UsuarioBean usuario)
			throws LogicaException;

	/**
	 * @param codigo
	 * @param usuario
	 * @throws LogicaException
	 */
	public abstract void aprobarOrdenProduccion(Long codigo, UsuarioBean usuario) throws LogicaException;

	/**
	 * @param codigo
	 * @throws LogicaException
	 */
	public abstract void eliminarOrdenProduccion(Long codigo) throws LogicaException;

	/**
	 * @param valorProceso
	 * @param valorProducto
	 * @param valorAnio
	 * @param valorMes
	 * @param mensajeError
	 * @return
	 * @throws LogicaException
	 */
	public abstract HojaRutaBean obtenerHojaActivaProducto(String valorProceso, String valorProducto, String valorAnio,
			String valorMes, String mensajeError) throws LogicaException;

	/**
	 * @param codigoOrdenProd
	 * @return
	 * @throws LogicaException
	 */
	public abstract OrdenProduccionBean obtenerOrdenProduccion(Long codigoOrdenProd) throws LogicaException;

	/**
	 * @param valorUnidad
	 * @param valorLineaNegocio
	 * @param valorProceso
	 * @param valorProducto
	 * @param annio
	 * @param mes
	 * @return
	 * @throws LogicaException
	 * @throws AplicacionException
	 * @throws AplicacionGlobalException
	 */
	public abstract List<OrdenProdConsultaBean> filtrarOrdenProduccion(String valorUnidad, String valorLineaNegocio,
			String valorProceso, String valorProducto, String annio, String mes, String valorEstado) throws LogicaException,
			AplicacionException, AplicacionGlobalException;

	/**
	 * @param valorDivision
	 * @return
	 * @throws LogicaException
	 */
	public abstract String cargarDivision(String valorDivision) throws LogicaException;

	/**
	 * @param valorSociedad
	 * @return
	 * @throws LogicaException
	 */
	public abstract String cargarSociedad(String valorSociedad) throws LogicaException;

	/**
	 * @param valorUnidad
	 * @return
	 * @throws LogicaException
	 */
	public abstract String cargarUnidad(String valorUnidad) throws LogicaException;

	/**
	 * @param valorLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract String cargarLinea(String valorLineaNegocio) throws LogicaException;

	/**
	 * @param valorProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract String cargarProceso(String valorProceso) throws LogicaException;

	/**
	 * @param valorProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract String cargarProducto(String valorProducto) throws LogicaException;

	/**
	 * @param valorProceso
	 * @param valorProducto
	 * @param valorAnio
	 * @param valorMes
	 * @param mensajeError
	 * @param usuario
	 * @param hojaBean
	 * @return
	 * @throws LogicaException
	 */
	public abstract OrdenProduccionBean ordenValida(String valorProceso, String valorProducto, String valorAnio, String valorMes,
			String mensajeError, UsuarioBean usuario, HojaRutaBean hojaBean) throws LogicaException;

	/**
	 * @param hojaBean
	 * @param ordenBean
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<OperacionBean> cargarOperaciones(HojaRutaBean hojaBean, OrdenProduccionBean ordenBean, Integer valorAnnio)
			throws LogicaException;

	/**
	 * Encargado de llenar la lista de Componentes para registro o generación de
	 * ordenes
	 * 
	 * @param ordenBean
	 * @param valorProducto
	 * @param hojaBean
	 * @param valorAnio
	 * @param valorMes
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ComponenteRegistroOrdenBean> cargarComponentes(OrdenProduccionBean ordenBean, String valorProducto,
			HojaRutaBean hojaBean, String valorAnio, String valorMes) throws LogicaException;

	/**
	 * @param hojaBean
	 * @param usuario
	 * @param valorAnio
	 * @param valorMes
	 * @param ordenbean
	 * @throws LogicaException
	 */
	public abstract void grabarOrden(HojaRutaBean hojaBean, UsuarioBean usuario, String valorAnio, String valorMes,
			OrdenProduccionBean ordenbean) throws LogicaException;

	/**
	 * @param codigoordenProdConsulta
	 * @return
	 * @throws LogicaException
	 */
	public abstract OrdenProdConsultaBean modificarOrden(Long codigoordenProdConsulta) throws LogicaException;

	/**
	 * @param propiedades
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<OrdenProduccionBean> obtenerOrdenesProduccionPorPropiedades(Map<?, ?> propiedades)
			throws LogicaException;

	/**
	 * Obtiene atributos de las órdenes de producción
	 * 
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * Obtiene el DTO de la orden de producción
	 * 
	 * @param codigoOrdenProd
	 * @return
	 * @throws LogicaException
	 */
	public abstract Ordenproduccion obtenerOrdenProduccionDataObject(Integer codigoOrdenProd) throws LogicaException;

	/**
	 * @param mesOrdenProduccion
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<OrdenProduccionBean> obtenerOrdenesProduccionPorMesDatosProcesoProducto(Short mesOrdenProduccion)
			throws LogicaException;

	/**
	 * @param mes
	 * @param codigoProceso
	 * @param annio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<OrdenProduccionDTO> getOrdenesByMesPlantillaLiberadas(Integer mes, Integer annio,
			Long codigoPlantillaReporte) throws LogicaException;

	/**
	 * Obtiene la orden de produccion dado el id y tranforma la oren de
	 * produccion en el DTO usado en lso servicios de GWT
	 * 
	 * @param codigoOrdenProd codigo de la orden de produccion a buscar
	 * @return OrdenProduccionDTO
	 * @throws LogicaException si orden no es encontrada en la BD
	 */
	public abstract OrdenProduccionDTO obtenerOrdenProduccionDTO(Integer codigoOrdenProd) throws LogicaException;

	public abstract void cambiarEstadoOrdenesProduccion(Integer valorAno, Short valorMes);

	public abstract List<OrdenProduccionDTO> getOrdenesByMesPlantillaHistoricas(Integer mes, Integer annio,
			Long codigoPlantillaReporte) throws LogicaException;

}
