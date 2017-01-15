package pe.com.pacasmayo.sgcp.logica.facade;

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.bean.ProductoBean;
import pe.com.pacasmayo.sgcp.bean.UtilBean;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ProductoLogicFacade.java
 * Modificado: Dec 14, 2010 11:03:26 PM 
 * Autor: Andy
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ProductoLogicFacade {

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductos() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoDTO> obtenerProductosDTO() throws LogicaException;

	/**
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosParaCombo() throws LogicaException;

	/**
	 * Obtiene los productos en orden alfabético
	 * 
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosOrdenAlfabetico() throws LogicaException;

	/**
	 * @param codigoPuestoTrabajo
	 * @param codigoProceso
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductoPorPuestoTrabajoProceso(Long codigoPuestoTrabajo, Long codigoProceso)
			throws LogicaException;

	/**
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract ProductoBean obtenerProducto(Long codigoProducto) throws LogicaException;

	/**
	 * @param nombreProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosPorNombre(String nombreProducto) throws LogicaException;

	/**
	 * @param codigoSAP
	 * @return
	 * @throws LogicaException
	 */
	public abstract ProductoBean obtenerProductoPorCodigoSAP(String codigoSAP) throws LogicaException;

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * bolsas de 42 Kg
	 * 
	 * @param codigoSAP
	 * @return
	 * @throws LogicaException
	 */
	public abstract ProductoBean obtenerProductoPorCodigoSAPBolsa(String codigoSAPBolsa) throws LogicaException;

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * bolsas de 1 TM (BIGBAG)
	 * 
	 * @param codigoSAPBigBag
	 * @return
	 * @throws LogicaException
	 */
	public ProductoBean obtenerProductoPorCodigoSAPBigBag(String codigoSAPBigBag) throws LogicaException;

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * especial 1
	 * 
	 * @param codigoSAPBigBag
	 * @return
	 * @throws LogicaException
	 */
	public ProductoBean obtenerProductoPorCodigoSAPEspecial1(String codigoSAPEspecial1) throws LogicaException;

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * especial 2
	 * 
	 * @param codigoSAPBigBag
	 * @return
	 * @throws LogicaException
	 */
	public ProductoBean obtenerProductoPorCodigoSAPEspecial2(String codigoSAPEspecial2) throws LogicaException;

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * especial 3
	 * 
	 * @param codigoSAPBigBag
	 * @return
	 * @throws LogicaException
	 */
	public ProductoBean obtenerProductoPorCodigoSAPEspecial3(String codigoSAPEspecial3) throws LogicaException;

	/**
	 * Método para obtener un producto por el codigo SAP cuando este viene en
	 * especial 4
	 * @param codigoUnidadMedida 
	 * 
	 * @param codigoSAPBigBag
	 * @return
	 * @throws LogicaException
	 */
	public ProductoBean obtenerProductoPorCodigoSAPEspecial4(String codigoSAPEspecial4, Long codigoUnidadMedida) throws LogicaException;

	/**
	 * @param codigoSCC
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosPorCodigoSCC(Long codigoSCC) throws LogicaException;

	/**
	 * @param siglasProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosPorSiglas(String siglasProducto) throws LogicaException;

	/**
	 * @param codigoTipoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosPorTipoProducto(Long codigoTipoProducto) throws LogicaException;

	/**
	 * @param codigoTipoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosFiltrandoPorProductoYTipoTipoProducto(Long codigoProducto,
			Long codigoTipoProducto) throws LogicaException;

	/**
	 * @param codigoEstadoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosPorEstadoProducto(Long codigoEstadoProducto) throws LogicaException;

	/**
	 * @param propiedades
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List<ProductoBean> obtenerProductosPorPropiedades(Map propiedades);

	/**
	 * @param codigoOrdenProduccion
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws LogicaException
	 */
	public abstract ProductoBean obtenerProductoPorOrdenProduccion(Long codigoOrdenProduccion) throws EntornoEjecucionException,
			LogicaException;

	/**
	 * @param codigoOrdenProduccion
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws LogicaException
	 */
	public abstract ProductoBean obtenerProductoBasicoPorOrdenProduccion(Long codigoOrdenProduccion)
			throws EntornoEjecucionException, LogicaException;

	/**
	 * @param codigoProceso
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws LogicaException
	 */
	public abstract List<Producto> obtenerProductoPorProceso(Long codigoProceso) throws EntornoEjecucionException,
			LogicaException;

	/**
	 * @param codigoLineaNegocio
	 * @return
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductoPorCodigoLineaNegocio(Long codigoLineaNegocio) throws LogicaException;

	/**
	 * @return
	 */
	public abstract List<UtilBean> obtenerAtributos();

	/**
	 * @param productoBean
	 * @param procesos
	 * @throws LogicaException
	 */
	public abstract void insertarProductoProduccion(ProductoBean productoBean) throws LogicaException;

	/**
	 * @param productoBean
	 * @param procesos
	 * @throws LogicaException
	 */
	public abstract void actualizarProductoProduccion(ProductoBean productoBean) throws LogicaException;

	/**
	 * @param productoBean
	 * @throws LogicaException
	 */
	public abstract void eliminarProducto(ProductoBean productoBean) throws LogicaException;

	/**
	 * @param codigoProceso
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosPorProceso(Long codigoProceso) throws EntornoEjecucionException,
			LogicaException;

	/**
	 * @param codigoUnidad
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws LogicaException
	 */
	public abstract List<ProductoBean> obtenerProductosPorUnidad(Long codigoUnidad) throws EntornoEjecucionException,
			LogicaException;

	/**
	 * Verifica si un producto es materia prima o no, considerando si posee
	 * componentes en su hoja de ruta
	 * 
	 * @param codigoProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract boolean esMateriaPrima(Long codigoProducto) throws LogicaException;

	/**
	 * Obtiene el producto por un nombre
	 * 
	 * @param nombreProducto
	 * @return
	 * @throws LogicaException
	 */
	public abstract ProductoBean obtenerProductoPorNombre(String nombreProducto) throws LogicaException;

	/**
	 * Obtiene el producto por un puesto trbaajo
	 * 
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws LogicaException
	 */
	public List<ProductoBean> obtenerProductoPorPuestoTrabajo(Long codigoPuestoTrabajo) throws LogicaException;

	public abstract ProductoBean obtenerProductoPorCodigoSAPDiferenteTipoConsumo(String codigoSAPProducto, String valor)
			throws LogicaException;

	public abstract ProductoBean obtenerProductoPorCodigoSAPUnidadMedida(String codigoSAPProducto, Long codigoUnidad) throws LogicaException;

}
