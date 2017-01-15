package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import java.util.Date;
import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ComponenteDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DivisionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoNotificacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoProduccionSemanalDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoRegistroMedicionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadocubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.OrdenProduccionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PlantillaProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProductoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.PuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.SociedadDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TablaCubicacionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TableroControlDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.TipoMedioAlmacenamientoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.UnidadDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * SGCP (Sistema de Gestion y Control de la Produccion) 
 * Archivo: Validaciones.java
 * Modificado: Apr 9, 2010 5:01:57 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ServicioAsync {

	public void cargarEstadosRegistroMedicion(AsyncCallback<List<EstadoRegistroMedicionDTO>> callback)
			throws IllegalArgumentException;

	public void cargarMediosAlmacenamiento(AsyncCallback<List<MedioAlmacenamientoDTO>> callback) throws IllegalArgumentException;

	public void cargarTiposMediosAlmacenamiento(AsyncCallback<List<TipoMedioAlmacenamientoDTO>> callback)
			throws IllegalArgumentException;

	public void cargarLineaNegocioUsuario(AsyncCallback<List<LineaNegocioDTO>> callback) throws IllegalArgumentException;

	public void cargarDivisiones(AsyncCallback<List<DivisionDTO>> callback) throws IllegalArgumentException;

	public void cargarSociedadesPorDivision(Long codigoDivision, AsyncCallback<List<SociedadDTO>> callback)
			throws IllegalArgumentException;

	public void cargarUnidadesPorSociedad(Long codigoSociedad, AsyncCallback<List<UnidadDTO>> callback)
			throws IllegalArgumentException;

	public void cargarLineasNegocioPorUnidad(Long codigoUnidad, AsyncCallback<List<LineaNegocioDTO>> callback)
			throws IllegalArgumentException;

	public void cargarProcesosPorLineaNegocio(Long codigoLineaNegocio, AsyncCallback<List<ProcesoDTO>> callback)
			throws IllegalArgumentException;

	public void cargarEstadosOrdenProduccion(AsyncCallback<List<EstadoProduccionSemanalDTO>> callback)
			throws IllegalArgumentException;

	public void obtenerPropiedadPorClave(String clavePropiedad, AsyncCallback<String> callback) throws IllegalArgumentException;

	public void obtenerMapaPropiedadesPorListaClaves(List<String> listaClavesPropiedades,
			AsyncCallback<Map<String, String>> callback) throws IllegalArgumentException;

	public void obtenerSemana(Date fechaInicio, AsyncCallback<String[]> callback) throws IllegalArgumentException;

	public void obtenerFechaActual(AsyncCallback<Date> callback) throws IllegalArgumentException;

	public void cargarProductoPorProceso(Long codigoProceso, AsyncCallback<List<ProductoDTO>> callback);

	public void cargarMediosAlmacenamiento(Long codigoTipoMedioAlmacenamiento, Long codigoProceso,
			AsyncCallback<List<MedioAlmacenamientoDTO>> asyncCallback);

	public void cargarMediosAlmacenamiento(Long codigoTipoMedioAlmacenamiento, Long codigoProceso, Long codigoProducto,
			AsyncCallback<List<MedioAlmacenamientoDTO>> asyncCallback);

	public void cargarMediosAlmacenamientoSegunProceso(Long codigoProceso,
			AsyncCallback<List<MedioAlmacenamientoDTO>> asyncCallback);

	public void cargarEstadosCubicacion(AsyncCallback<List<EstadocubicacionDTO>> asyncCallback);

	public void registrarCubicacion(List<TablaCubicacionDTO> listaCubicaciones, AsyncCallback<String> asyncCallback);

	public void cargarPuestosTrabajo(AsyncCallback<List<PuestoTrabajoDTO>> callback) throws IllegalArgumentException;

	public void cargarPuestosTrabajoPorProducto(Long codigoProducto, AsyncCallback<List<PuestoTrabajoDTO>> callback)
			throws IllegalArgumentException;

	public void cargarPuestosTrabajoDTOPorProceso(Long codigoProceso, AsyncCallback<List<PuestoTrabajoDTO>> callback)
			throws IllegalArgumentException;

	public void obtenerOrdenProduccionDTO(Long codigoOrden, AsyncCallback<OrdenProduccionDTO> callback)
			throws IllegalArgumentException;

	public void cargarPuestosTrabajoPorTableroControl(Long codigoTableroControl, AsyncCallback<List<PuestoTrabajoDTO>> callback)
			throws IllegalArgumentException;

	public void cargarTablerosControlPorUnidad(Long codigoUnidad, AsyncCallback<List<TableroControlDTO>> callback)
			throws IllegalArgumentException;

	public void cargarTablerosControlPorPuestoTrabajoUnidad(Long codigoPuestoTrabajo, Long codigoUnidad,
			AsyncCallback<List<TableroControlDTO>> callback) throws IllegalArgumentException;

	public void obtenerProceso(long codigoProceso, AsyncCallback<ProcesoDTO> callback) throws IllegalArgumentException;

	public void obtenerPuestoTrabajo(long codigoPuestoTrabajo, AsyncCallback<PuestoTrabajoDTO> callback)
			throws IllegalArgumentException;

	public void cargarEstadosNotificacion(AsyncCallback<List<EstadoNotificacionDTO>> asyncCallback);

	public void obtenerOrdenesProduccionDTOByProceso(Long codigoProceso, AsyncCallback<List<OrdenProduccionDTO>> callback);

	public void colocarObjetoEnSesion(String nombre, Integer objeto, AsyncCallback<Boolean> callback);

	public void cargarPlantillas(Long codigoPlantillaReporte, Long codigoProducto,
			AsyncCallback<List<PlantillaProductoDTO>> asyncCallback);

	public void cargarPlantillaMasReciente(Long codigoPlantillaReporte, Long codigoProducto,
			AsyncCallback<PlantillaProductoDTO> asyncCallback);

	public void validarSiExisteCubicacionProducto(long codigoLineaNegocio, long codigoProducto, long codigoProceso,
			Date fechaCubicacion, AsyncCallback<Boolean> asyncCallback);

	public void valorPorDefectoDivision(AsyncCallback<String> asyncCallback);

	public void valorPorDefectoSociedad(AsyncCallback<String> asyncCallback);

	public void valorPorDefectoUnidad(AsyncCallback<String> asyncCallback);

	public void verificarSiEsProductoTerminado(Long codigoProducto, AsyncCallback<Boolean> asyncCallback);

	void getUserSessionTimeout(AsyncCallback<Integer> callback);

	public void getComponentesDeHRActivas(AsyncCallback<List<ComponenteDTO>> asyncCallback);

	/**
	 * AGREGADO POR JORDAN
	 * 
	 * @param codigoMedioAlmacenamiento
	 * @param callback
	 */
	public void cargarDensidadMedioAlmacenamiento(long codigoMedioAlmacenamiento, AsyncCallback<Double> callback);

	public void obtenerCodigoProductoRendimientoTermico(AsyncCallback<List<Long>> asyncCallback);

	public void obtenerMapaParametroSistema(AsyncCallback<Map<String, String>> asyncCallback);

}