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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/*
 * SGCP (Sistema de Gestion y Control de la Produccion) 
 * Archivo: Servicio.java
 * Modificado: Mar 3, 2010 10:02:47 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
@RemoteServiceRelativePath("servicio")
public interface Servicio extends RemoteService {

	public List<EstadoRegistroMedicionDTO> cargarEstadosRegistroMedicion() throws ServicioGWTGlobalException;

	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamiento() throws ServicioGWTGlobalException;

	public List<TipoMedioAlmacenamientoDTO> cargarTiposMediosAlmacenamiento() throws ServicioGWTGlobalException;

	public List<LineaNegocioDTO> cargarLineaNegocioUsuario() throws ServicioGWTGlobalException;

	public List<DivisionDTO> cargarDivisiones() throws ServicioGWTGlobalException;

	public List<SociedadDTO> cargarSociedadesPorDivision(Long codigoDivision) throws ServicioGWTGlobalException;

	public List<UnidadDTO> cargarUnidadesPorSociedad(Long codigoSociedad) throws ServicioGWTGlobalException;

	public List<LineaNegocioDTO> cargarLineasNegocioPorUnidad(Long codigoUnidad) throws ServicioGWTGlobalException;

	public List<ProcesoDTO> cargarProcesosPorLineaNegocio(Long codigoLineaNegocio) throws ServicioGWTGlobalException;

	public List<EstadoProduccionSemanalDTO> cargarEstadosOrdenProduccion() throws ServicioGWTGlobalException;

	public String obtenerPropiedadPorClave(String clavePropiedad);

	public Map<String, String> obtenerMapaPropiedadesPorListaClaves(List<String> listaClavesPropiedades);

	public String[] obtenerSemana(Date fechaInicio);

	public Date obtenerFechaActual();

	public List<ProductoDTO> cargarProductoPorProceso(Long codigoProceso) throws ServicioGWTGlobalException;

	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamiento(Long codigoTipoMedioAlmacenamiento, Long codigoProceso)
			throws ServicioGWTGlobalException;

	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamiento(Long codigoTipoMedioAlmacenamiento, Long codigoProceso,
			Long codigoProducto) throws ServicioGWTGlobalException;

	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamientoSegunProceso(Long codigoProceso)
			throws ServicioGWTGlobalException;

	public List<EstadocubicacionDTO> cargarEstadosCubicacion() throws ServicioGWTGlobalException;

	public String registrarCubicacion(List<TablaCubicacionDTO> listaCubicaciones) throws ServicioGWTGlobalException;

	public List<PuestoTrabajoDTO> cargarPuestosTrabajo() throws ServicioGWTGlobalException;

	public List<PuestoTrabajoDTO> cargarPuestosTrabajoPorProducto(Long codigoProducto) throws ServicioGWTGlobalException;

	public List<PuestoTrabajoDTO> cargarPuestosTrabajoDTOPorProceso(Long codigoProceso) throws ServicioGWTGlobalException;

	public List<PuestoTrabajoDTO> cargarPuestosTrabajoPorTableroControl(Long codigoTableroControl)
			throws ServicioGWTGlobalException;

	public List<TableroControlDTO> cargarTablerosControlPorUnidad(Long codigoUnidad);

	public List<TableroControlDTO> cargarTablerosControlPorPuestoTrabajoUnidad(Long codigoPuestoTrabajo, Long codigoUnidad);

	public ProcesoDTO obtenerProceso(long codigoProceso) throws ServicioGWTGlobalException;

	public PuestoTrabajoDTO obtenerPuestoTrabajo(long codigoPuestoTrabajo);

	public List<EstadoNotificacionDTO> cargarEstadosNotificacion() throws ServicioGWTGlobalException;

	public List<OrdenProduccionDTO> obtenerOrdenesProduccionDTOByProceso(Long codigoProceso);

	public OrdenProduccionDTO obtenerOrdenProduccionDTO(Long codigoOrden) throws ServicioGWTGlobalException;

	public Boolean colocarObjetoEnSesion(String nombre, Integer objeto);

	public List<PlantillaProductoDTO> cargarPlantillas(Long codigoPlantillaReporte, Long codigoProducto)
			throws ServicioGWTGlobalException;

	public PlantillaProductoDTO cargarPlantillaMasReciente(Long codigoPlantillaReporte, Long codigoProducto)
			throws ServicioGWTGlobalException;

	public Boolean validarSiExisteCubicacionProducto(long codigoLineaNegocio, long codigoProducto, long codigoProceso,
			Date fechaCubicacion) throws ServicioGWTGlobalException;

	public String valorPorDefectoDivision();

	public String valorPorDefectoSociedad();

	public String valorPorDefectoUnidad();

	public Boolean verificarSiEsProductoTerminado(Long codigoProducto) throws ServicioGWTGlobalException;

	public Integer getUserSessionTimeout() throws ServicioGWTGlobalException;

	public List<ComponenteDTO> getComponentesDeHRActivas() throws ServicioGWTGlobalException;

	/**
	 * AGREGADO POR JORDAN
	 * 
	 * @param codigoMedioAlmacenamiento
	 * @throws ServicioGWTGlobalException
	 */
	public Double cargarDensidadMedioAlmacenamiento(long codigoMedioAlmacenamiento) throws ServicioGWTGlobalException;

	public List<Long> obtenerCodigoProductoRendimientoTermico() throws ServicioGWTGlobalException;

	public Map<String, String> obtenerMapaParametroSistema();

}