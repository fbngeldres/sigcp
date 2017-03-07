package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente;

import java.util.List;
import java.util.Map;
import java.util.Set;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoAjusteProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.GrupoAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaAjusteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaBalanceDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.AjusteProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroPuestoTrabajoProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumoComponentesDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.RegistroTablaConsumosPuestoTrabajoDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ParteTecnicoServiceAsync.java
 * Modificado: Sep 20, 2010 2:26:37 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ParteTecnicoServiceAsync {

	public void cargarEstadosAjusteProduccion(AsyncCallback<List<EstadoAjusteProduccionDTO>> callback)
			throws IllegalArgumentException;

	public void cargarMeses(AsyncCallback<List<String>> callback) throws IllegalArgumentException;

	public void cargarAnios(AsyncCallback<List<Integer>> callback) throws IllegalArgumentException;

	public void cargarGrupoAjuste(Long codigoLineaNegocio, AsyncCallback<List<GrupoAjusteDTO>> callback)
			throws IllegalArgumentException;

	public void cargarProductosporCodigoGrupoAjuste(Long codigoGrupoAjuste, AsyncCallback<List<ProductoDTO>> callback)
			throws IllegalArgumentException;

	/**
	 * Obtiene la cantidad a ajustar F-L
	 * 
	 * @param codigoLinea Long
	 * @param codigoProducto Long
	 * @param mesContable String
	 * @param anioContable Integer
	 * @param callback AsyncCallback<Double>
	 * @throws IllegalArgumentException
	 */
	public void obtenerAjuste(Long codigoLinea, Long codigoProducto, String mesContable, Integer anioContable,
			AsyncCallback<Double> callback) throws IllegalArgumentException;

	/**
	 * Obtiene los datos de la tabla que muestra la produccion por puesto de
	 * trabajo y ajustes cuando no se ha guardado el ajuste
	 * 
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param mesContable String
	 * @param anioContable Integer
	 * @param callback AsyncCallback<List<RegistroPuestoTrabajoProduccionDTO>>
	 * @throws IllegalArgumentException
	 */
	public void obtenerProduccionPuestoTrabajoMes(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable, AsyncCallback<List<RegistroPuestoTrabajoProduccionDTO>> callback)
			throws IllegalArgumentException;

	/**
	 * Obtiene los datos para la tabla movimientos de componentes cuando no se
	 * ha guardado el ajuste
	 * 
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param mesContable String
	 * @param anioContable Integer
	 * @param otrosProductosAjuste
	 * @param callback AsyncCallback<List<RegistroTablaConsumoComponentesDTO>>
	 */
	public void obtenerConceptosComponentesProducto(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable, Set<String> otrosProductosAjuste,
			AsyncCallback<List<RegistroTablaConsumoComponentesDTO>> callback);

	/**
	 * Obtiene los datos de la tabla que muestra los consumos de los componentes
	 * clasificados por puesto de trabajo cuando no se ha guardado el ajuste
	 * 
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param mesContable String
	 * @param anioContable Integer
	 * @param callback AsyncCallback<Map<String,
	 *            List<RegistroTablaConsumosPuestoTrabajoDTO>>>
	 */
	public void obtenerConsumoComponentesPuestoTrabajo(Long codigoProducto, Long codigoLinea, String mesContable,
			Integer anioContable, AsyncCallback<Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>> callback);

	/**
	 * Obtiene los datos para llenar la tabla balance cuando no se ha guardado
	 * el ajuste
	 * 
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param mesContable String
	 * @param anioContable Integer
	 * @param callback AsyncCallback<RegistroTablaBalanceDTO>
	 */
	public void obtenerConceptosLibro(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable,
			AsyncCallback<RegistroTablaBalanceDTO> callback);

	/**
	 * Obtiene el stock fisico de un producto
	 * 
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param mesContable String
	 * @param anioContable Integer
	 * @param callback AsyncCallback<Double>
	 */
	public void obtenerStockFisico(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable,
			AsyncCallback<Double> callback);

	/**
	 * Funcion para guardar los ajustes realizados sobre un producto
	 * 
	 * @param ajuste
	 * @param codigoLineaNegocio Long
	 * @param anio Integer
	 * @param mes String
	 * @param codigoPlantillaGrupoAjuste Long
	 * @param codigoProducto Long
	 * @param saldoInicialLibroBalance double
	 * @param produccionLibroBalance double
	 * @param saldoFinalLibroBalance double
	 * @param consumoLibroBalance double
	 * @param consumoAjusteBalance double
	 * @param produccionAjusteBalance double
	 * @param detallesProduccionPuestoTrabajo
	 *            List<RegistroPuestoTrabajoProduccionDTO>
	 * @param detallesConsumoComponenteAjuste
	 *            List<RegistroTablaConsumosPuestoTrabajoDTO>
	 * @param detallesMovimientoAjuste List<RegistroTablaAjusteDTO>
	 * @param callback AsyncCallback<Boolean>
	 */
	public void registrarParteTecnico(Double ajuste, Long codigoLineaNegocio, Integer anio, String mes,
			Long codigoPlantillaGrupoAjuste, Long codigoProducto, double saldoInicialLibroBalance, double produccionLibroBalance,
			double saldoFinalLibroBalance, double consumoLibroBalance, double consumoAjusteBalance,
			double produccionAjusteBalance, List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String observaciones, AsyncCallback<Boolean> callback);

	/**
	 * Obtiene el id del ajuste prodcuto para la linea de negocio, mes y anio
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param asyncCallback
	 */
	public void verificarSiExisteAjusteBd(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable,
			AsyncCallback<AjusteProductoDTO> asyncCallback);

	/**
	 * Obtiene una lista de registros de ajuste de la entidad
	 * Produccionpuestotrabajo
	 * 
	 * @param codigoAjusteProducto
	 * @param callBack
	 */
	public void obtenerProduccionPuestoTrabajoMesBD(Long codigoAjusteProducto,
			AsyncCallback<List<RegistroPuestoTrabajoProduccionDTO>> callBack);

	/**
	 * Obtiene los datos de la tabla de los consumo de los componenets por
	 * puesto de trabajo cuando el ajuste ya existe en bd
	 * 
	 * @param codigoAjusteProducto Long
	 * @param callback AsyncCallback<Map<String,
	 *            List<RegistroTablaConsumosPuestoTrabajoDTO>>>
	 */
	public void obtenerConsumoComponentesPuestoTrabajoBD(Long codigoAjusteProducto,
			AsyncCallback<Map<String, List<RegistroTablaConsumosPuestoTrabajoDTO>>> callback);

	/**
	 * Obtiene los datos para la tabla movimientos de ajuste cuando el ajuste ya
	 * existe en bd
	 * 
	 * @param codigoAjusteProducto Long
	 * @param callback AsyncCallback<List<RegistroTablaAjusteDTO>>
	 */
	public void obtenerDatosGridMovimientoAjustesBD(Long codigoAjusteProducto,
			AsyncCallback<List<RegistroTablaAjusteDTO>> callback);

	/**
	 * Obtiene los datos de la tabla movimientos de compoenetes cuadno el ajuste
	 * existe en bd
	 * 
	 * @param codigoAjusteProducto Long
	 * @param codigoProducto Long
	 * @param codigoLinea Long
	 * @param mesContable String
	 * @param anioContable Integer
	 * @param otrosProductosAjuste
	 * @param callback AsyncCallback<List<RegistroTablaConsumoComponentesDTO>>
	 */
	public void obtenerDatosGridConsumoComponentesBD(Long codigoAjusteProducto, Long codigoProducto, Long codigoLinea,
			String mesContable, Integer anioContable, Set<String> otrosProductosAjuste,
			AsyncCallback<List<RegistroTablaConsumoComponentesDTO>> callback);

	/**
	 * Valida si existe cubicaciones aprobadas para los datos pasados como
	 * parametro
	 * 
	 * @param anio String
	 * @param mes String
	 * @param linea String
	 * @param asyncCallback AsyncCallback<Boolean> true si existen, false en
	 *            caso contrario
	 */
	public void validarSiexistencubicaciones(String codigoLineaNegocio, String anio, String mes,
			AsyncCallback<Boolean> asyncCallback);

	/**
	 * Obtiene todos los productos registrados en el sistema
	 * 
	 * @param callback
	 * @throws IllegalArgumentException
	 */
	public void getProductos(AsyncCallback<List<ProductoDTO>> callback) throws IllegalArgumentException;

	/**
	 * Méotod para obtener la desviacion de horas de ajuste
	 * 
	 * @param detallesProduccionPuestoTrabajo
	 * @param codigoLineaNegocio
	 * @param mesContable
	 * @param anioContable
	 * @return
	 */
	public void obtenerDesviacionHoras(Long codigoProducto,
			List<RegistroPuestoTrabajoProduccionDTO> detallesProduccionPuestoTrabajo, Long codigoLineaNegocio,
			String mesContable, Integer anioContable, AsyncCallback<Map<String, Double>> callback);

	/**
	 * Metodo para cargar el
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return
	 */
	public void obtenerDatosCombustible(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable,
			AsyncCallback<List<RegistroTablaConsumosPuestoTrabajoDTO>> callback);

	public void registrarParteTecnicoCombustible(Double mermaIngresada, Double ajuste, Long codigoLineaNegocio, Integer anio,
			String mes, Long codigoPlantillaGrupoAjuste, Long codigoProducto, double saldoInicialLibroBalance,
			double produccionLibroBalance, double saldoFinalLibroBalance, double consumoLibroBalance,
			double consumoAjusteBalance, double produccionAjusteBalance,
			List<RegistroTablaConsumosPuestoTrabajoDTO> detallesConsumoComponenteAjuste,
			List<RegistroTablaAjusteDTO> detallesMovimientoAjuste, String fechaFactura, String cantidadFactura,
			String cantidadRestante, String observaciones, AsyncCallback<Boolean> callback);

	public void obtenerConsumoDesdeFecha(Long codigoLineaNegocio, Long codigoProducto, String fecha,
			AsyncCallback<String[]> callback);

	public void obtenerDatosComprobante(Long codigoProducto, Long codigoLinea, String mesContable, Integer anioContable,
			AsyncCallback<String[]> callbakDatosFactura);

	/**
	 * Carga los movimientoAjuste del Bunker
	 * 
	 * @param codigo
	 * @param callback
	 */
	public void obtenerDatosGridMovimientoAjustesCombustibleBD(Long codigo, AsyncCallback<List<RegistroTablaAjusteDTO>> callback);

	/**
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param asyncCallback
	 */
	public void validarParteDiarioCerrado(Long codigoLinea, String mesContable, Integer anioContable,
			AsyncCallback<Boolean> asyncCallback);

	public void validarSiExisteAjustes(String linea, String anno, String mes, AsyncCallback<Boolean> asyncCallback);
}