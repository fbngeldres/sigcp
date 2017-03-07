package pe.com.pacasmayo.sgcp.presentacion.gwt.util.servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.bean.CargoBean;
import pe.com.pacasmayo.sgcp.bean.DivisionBean;
import pe.com.pacasmayo.sgcp.bean.GrupoUsuarioBean;
import pe.com.pacasmayo.sgcp.bean.LineaNegocioBean;
import pe.com.pacasmayo.sgcp.bean.PlantillaProductoBean;
import pe.com.pacasmayo.sgcp.bean.UsuarioBean;
import pe.com.pacasmayo.sgcp.bean.impl.UsuarioBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.logica.facade.CubicacionProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.DivisionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoCubicacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoNotificacionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoOrdenProduccionFacade;
import pe.com.pacasmayo.sgcp.logica.facade.EstadoRegistroMedicionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.LineaNegocioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.MedioAlmacenamientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.OrdenProduccionLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PlantillaProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.ProcesoLogicFacade;

import pe.com.pacasmayo.sgcp.logica.facade.ProductoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.PuestoTrabajoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.RendimientoTermicoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.SociedadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TableroControlLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.TipoMedioAlmacenamientoLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UnidadLogicFacade;
import pe.com.pacasmayo.sgcp.logica.facade.UsuarioLogicFacade;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.DivisionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.EstadoOrdenProduccionLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.LineaNegocioLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.MedioAlmacenamientoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProcesoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.ProductoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.PuestoTrabajoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.RendimientoTermicoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.SociedadLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.TipoMedioAlmacenamientoLogic;
import pe.com.pacasmayo.sgcp.logica.manejoMaestros.UnidadLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.EstadoNotificacionLogic;
import pe.com.pacasmayo.sgcp.logica.notificacion.TableroControlLogic;
import pe.com.pacasmayo.sgcp.logica.planificacion.OrdenProduccionLogic;

import pe.com.pacasmayo.sgcp.logica.reporteECS.PlantillaProductoLogic;
import pe.com.pacasmayo.sgcp.logica.seguridad.UsuarioLogic;
import pe.com.pacasmayo.sgcp.logica.stock.CubicacionProductoLogic;
import pe.com.pacasmayo.sgcp.logica.stock.EstadoCubicacionLogic;
import pe.com.pacasmayo.sgcp.logica.stock.EstadoRegistroMedicionLogic;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocubicacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadonotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoregistromedicion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablerocontrol;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomedioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.querier.MedioAlmacenamientoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ParametroSistemaQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.ProductoQuerier;
import pe.com.pacasmayo.sgcp.persistencia.querier.PuestoTrabajoQuerier;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesAplicacionAction;
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
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ConstantesGWT;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Servicio;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ServicioGWTGlobalException;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/*
 * SGCP (Sistema de Gestion y Control de la Produccion) 
 * Archivo: ServicioImpl.java
 * Modificado: Mar 3, 2010 10:15:16 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

/**
 * Implementacion de servicios de carga de entidades hibernate y su
 * correspondiente transformacion en DTO para poder ser transmitidos por el
 * servicio GWT-RPC
 * 
 * @author hector.longarte
 */
@SuppressWarnings("serial")
public class ServicioImpl extends RemoteServiceServlet implements Servicio, ConstantesAplicacionAction {

	private static DivisionLogicFacade divisionFacade = new DivisionLogic();
	private static SociedadLogicFacade sociedadFacade = new SociedadLogic();
	private static UnidadLogicFacade unidadFacade = new UnidadLogic();
	private static LineaNegocioLogicFacade lineaNegocioFacade = new LineaNegocioLogic();
	private static ProcesoLogicFacade procesoFacade = new ProcesoLogic();
	private static EstadoOrdenProduccionFacade estadoOrdenProduccionFacade = new EstadoOrdenProduccionLogic();
	private static TipoMedioAlmacenamientoLogicFacade tiposMedioFacade = new TipoMedioAlmacenamientoLogic();
	private static MedioAlmacenamientoLogicFacade mediosFacade = new MedioAlmacenamientoLogic();
	private static PuestoTrabajoLogicFacade puestosFacade = new PuestoTrabajoLogic();
	private static EstadoRegistroMedicionLogicFacade estadoRegistroMedicionFacade = new EstadoRegistroMedicionLogic();
	private static UsuarioLogicFacade usuarioFacade = new UsuarioLogic();
	private static ProductoLogicFacade productoFacade = new ProductoLogic();
	private static EstadoCubicacionLogicFacade estadoCubicacionLogicFacade = new EstadoCubicacionLogic();
	private static CubicacionProductoLogicFacade cubicacionProductoLogicFacade = new CubicacionProductoLogic();
	private static TableroControlLogicFacade tableroControlFacade = new TableroControlLogic();
	private static EstadoNotificacionLogicFacade estadoNotificacionFacade = new EstadoNotificacionLogic();
	
	private static OrdenProduccionLogicFacade ordenFacade = new OrdenProduccionLogic();
	private static PlantillaProductoLogicFacade plantillaProductoLogicFacade = new PlantillaProductoLogic();
	private static RendimientoTermicoLogicFacade rendimientoTermicoLogicFacade = new RendimientoTermicoLogic();
	// private static NotificacionCambioProduccionLogicFacade
	// notificacionProduccionFacade = new NotificacionCambioProduccionLogic();

	private static Log logger = LogFactory.getLog(ServicioImpl.class);
	protected UsuarioBean usuario = new UsuarioBeanImpl();

	/**
	 * Metodo que carga los estados de registros de medicion
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<EstadoRegistroMedicionDTO> cargarEstadosRegistroMedicion() throws ServicioGWTGlobalException {
		List<Estadoregistromedicion> estados = new ArrayList<Estadoregistromedicion>();
		List<EstadoRegistroMedicionDTO> estadosDTO = new ArrayList<EstadoRegistroMedicionDTO>();

		try {
			estados = estadoRegistroMedicionFacade.obtenerEstadosRegistroMedicionDataObjects();
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Estadoregistromedicion> iterator = estados.iterator(); iterator.hasNext();) {

			Estadoregistromedicion estado = iterator.next();
			EstadoRegistroMedicionDTO estadoDTO = new EstadoRegistroMedicionDTO();

			estadoDTO.setPkCodigoEstadoregistromedicio(estado.getPkCodigoEstadoregistromedicio());
			estadoDTO.setNombreEstadoregistromedicion(estado.getNombreEstadoregistromedicion());
			estadoDTO.setDescripcionEstadoregistromedic(estado.getDescripcionEstadoregistromedic());

			estadosDTO.add(estadoDTO);
		}
		return estadosDTO;
	}

	/**
	 * Metodo que carga los estados de notificacion
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<EstadoNotificacionDTO> cargarEstadosNotificacion() throws ServicioGWTGlobalException {
		List<Estadonotificacion> estados = null;
		List<EstadoNotificacionDTO> estadosDTO = new ArrayList<EstadoNotificacionDTO>();

		try {
			estados = estadoNotificacionFacade.obtenerEstadoNotificacionDataObjects();
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}

		for (Estadonotificacion estado : estados) {

			EstadoNotificacionDTO estadoDTO = new EstadoNotificacionDTO();
			estadoDTO.setPkCodigoEstadonotificacion(estado.getPkCodigoEstadonotificacion());
			estadoDTO.setNombreEstadonotificacion(estado.getNombreEstadonotificacion());

			estadosDTO.add(estadoDTO);
		}
		return estadosDTO;
	}

	/**
	 * Metodo que carga los medios de almacenamiento
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamiento() throws ServicioGWTGlobalException {

		List<Medioalmacenamiento> medios = null;
		List<MedioAlmacenamientoDTO> mediosDTO = new ArrayList<MedioAlmacenamientoDTO>();

		try {
			medios = mediosFacade.obtenerMediosAlmacenamientoDataObjects();
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Medioalmacenamiento> iterator = medios.iterator(); iterator.hasNext();) {
			Medioalmacenamiento medio = iterator.next();
			MedioAlmacenamientoDTO medioDTO = new MedioAlmacenamientoDTO();
			medioDTO.setPkCodigoMedioalmacenamiento(medio.getPkCodigoMedioalmacenamiento());

			medioDTO.setNombreMedioalmacenamiento(medio.getNombreMedioalmacenamiento());
			medioDTO.setNumeroMedioalmacenamiento(medio.getNumeroMedioalmacenamiento());

			mediosDTO.add(medioDTO);
		}
		return mediosDTO;
	}

	/**
	 * Metodo que carga los puestos de trabajo
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<PuestoTrabajoDTO> cargarPuestosTrabajo() throws ServicioGWTGlobalException {

		List<Puestotrabajo> puestos = null;
		List<PuestoTrabajoDTO> puestosDTO = new ArrayList<PuestoTrabajoDTO>();

		try {
			puestos = puestosFacade.obtenerPuestosTrabajoDataObjects();
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Puestotrabajo> iterator = puestos.iterator(); iterator.hasNext();) {
			Puestotrabajo puesto = iterator.next();
			PuestoTrabajoDTO puestoDTO = new PuestoTrabajoDTO();
			puestoDTO.setPkCodigoPuestotrabajo(puesto.getPkCodigoPuestotrabajo());
			puestoDTO.setNombrePuestotrabajo(puesto.getNombrePuestotrabajo());
			puestosDTO.add(puestoDTO);
		}
		return puestosDTO;
	}

	/**
	 * Metodo que carga los puestos de trabajo asociados a un codigo de producto
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<PuestoTrabajoDTO> cargarPuestosTrabajoPorProducto(Long codigoProducto) throws ServicioGWTGlobalException {

		List<Puestotrabajo> puestos = null;
		List<PuestoTrabajoDTO> puestosDTO = new ArrayList<PuestoTrabajoDTO>();

		try {
			puestos = puestosFacade.obtenerPuestosTrabajoDataObjectsPorProducto(codigoProducto);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Puestotrabajo> iterator = puestos.iterator(); iterator.hasNext();) {
			Puestotrabajo puesto = iterator.next();
			PuestoTrabajoDTO puestoDTO = new PuestoTrabajoDTO();
			puestoDTO.setPkCodigoPuestotrabajo(puesto.getPkCodigoPuestotrabajo());
			puestoDTO.setNombrePuestotrabajo(puesto.getNombrePuestotrabajo());
			puestosDTO.add(puestoDTO);
		}
		return puestosDTO;
	}

	/**
	 * @throws ServicioGWTGlobalException
	 */
	public List<PuestoTrabajoDTO> cargarPuestosTrabajoDTOPorProceso(Long codigoProceso) throws ServicioGWTGlobalException {

		List<Puestotrabajo> puestos = new ArrayList<Puestotrabajo>();
		List<PuestoTrabajoDTO> puestosDTO = new ArrayList<PuestoTrabajoDTO>();

		try {
			puestos = PuestoTrabajoQuerier.obtenerPuestosTrabajoPorCodigoProceso(codigoProceso);

			for (Iterator<Puestotrabajo> iterator = puestos.iterator(); iterator.hasNext();) {
				Puestotrabajo puesto = iterator.next();
				PuestoTrabajoDTO puestoDTO = new PuestoTrabajoDTO();

				puestoDTO.setPkCodigoPuestotrabajo(puesto.getPkCodigoPuestotrabajo());
				puestoDTO.setNombrePuestotrabajo(puesto.getNombrePuestotrabajo());
				puestosDTO.add(puestoDTO);
			}
		} catch (AplicacionException e) {
			throw new ServicioGWTGlobalException(e);
		}

		return puestosDTO;
	}

	/**
	 * Metodo que carga los puestos de trabajo
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<PuestoTrabajoDTO> cargarPuestosTrabajoPorTableroControl(Long codigoTableroControl)
			throws ServicioGWTGlobalException {

		List<Puestotrabajo> puestos = null;
		List<PuestoTrabajoDTO> puestosDTO = new ArrayList<PuestoTrabajoDTO>();

		try {
			puestos = puestosFacade.obtenerPuestosTrabajoDTOPorTableroControl(codigoTableroControl);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Puestotrabajo> iterator = puestos.iterator(); iterator.hasNext();) {
			Puestotrabajo puesto = iterator.next();
			PuestoTrabajoDTO puestoDTO = new PuestoTrabajoDTO();
			puestoDTO.setPkCodigoPuestotrabajo(puesto.getPkCodigoPuestotrabajo());
			puestoDTO.setNombrePuestotrabajo(puesto.getNombrePuestotrabajo());
			puestosDTO.add(puestoDTO);
		}
		return puestosDTO;
	}

	/**
	 * Metodo que carga los tipos de medio de almacenamiento
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<TipoMedioAlmacenamientoDTO> cargarTiposMediosAlmacenamiento() throws ServicioGWTGlobalException {

		List<Tipomedioalmacenamiento> tiposMedio = new ArrayList<Tipomedioalmacenamiento>();
		List<TipoMedioAlmacenamientoDTO> tiposMedioDTO = new ArrayList<TipoMedioAlmacenamientoDTO>();

		try {
			tiposMedio = tiposMedioFacade.obtenerTiposMedioAlmacenamientoDataObject();

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Tipomedioalmacenamiento> iterator = tiposMedio.iterator(); iterator.hasNext();) {
			Tipomedioalmacenamiento tipoMedio = iterator.next();
			TipoMedioAlmacenamientoDTO tipoMedioDTO = new TipoMedioAlmacenamientoDTO();
			tipoMedioDTO.setPkCodigoTipomedioalmacenamien(tipoMedio.getPkCodigoTipomedioalmacenamien());
			tipoMedioDTO.setNombreTipomedioalmacenamiento(tipoMedio.getNombreTipomedioalmacenamiento());
			tiposMedioDTO.add(tipoMedioDTO);
		}
		return tiposMedioDTO;
	}

	/**
	 * Método para listar las lineas de negocio asociadas al usuario
	 * 
	 * @return
	 * @throws ServicioGWTGlobalException
	 */
	public List<LineaNegocioDTO> cargarLineaNegocioUsuario() throws ServicioGWTGlobalException {

		List<LineaNegocioBean> lineas = new ArrayList<LineaNegocioBean>();
		List<LineaNegocioDTO> lineasDTO = new ArrayList<LineaNegocioDTO>();

		usuario = getUsuarioSession();

		try {

			lineas = lineaNegocioFacade.obtenerLineaNegocioPorUsuario(usuario);

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<LineaNegocioBean> iterator = lineas.iterator(); iterator.hasNext();) {
			LineaNegocioBean linea = iterator.next();
			LineaNegocioDTO lineaDTO = new LineaNegocioDTO();

			lineaDTO.setCodigoSccLineanegocio(linea.getCodigoSCC());
			lineaDTO.setDescripcionLineanegocio(linea.getDescripcion());
			lineaDTO.setNombreLineanegocio(linea.getNombre());
			lineaDTO.setPkCodigoLineanegocio(linea.getCodigo());

			lineasDTO.add(lineaDTO);
		}
		return lineasDTO;
	}

	/**
	 * Metodo que carga las divisiones
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<DivisionDTO> cargarDivisiones() throws ServicioGWTGlobalException {

		List<DivisionBean> divisiones = new ArrayList<DivisionBean>();
		List<DivisionDTO> divisionesDTO = new ArrayList<DivisionDTO>();

		usuario = getUsuarioSession();
System.out.println("--> " +usuario );
		try {

			if (!esUsuarioAdmin()) {
				divisiones = getUsuarioCargo().getDivisionBeanList();
			} else {
				divisiones = divisionFacade.obtenerDivisiones();
			}
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<DivisionBean> iterator = divisiones.iterator(); iterator.hasNext();) {
			DivisionBean division = iterator.next();
			DivisionDTO divisionDTO = new DivisionDTO();
			divisionDTO.setPkCodigoDivision(division.getCodigo());
			divisionDTO.setNombreDivision(division.getNombre());
			divisionDTO.setDescripcionDivision(division.getDescripcion());

			divisionesDTO.add(divisionDTO);
		}
		return divisionesDTO;
	}

	/**
	 * Metodo que carga las sociedades por codigo de division
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<SociedadDTO> cargarSociedadesPorDivision(Long codigoDivision) throws ServicioGWTGlobalException {

		List<Sociedad> sociedades = null;
		List<SociedadDTO> sociedadesDTO = new ArrayList<SociedadDTO>();

		try {
			sociedades = sociedadFacade.obtenerSociedadesDataObjects(codigoDivision);

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Sociedad> iterator = sociedades.iterator(); iterator.hasNext();) {
			Sociedad sociedad = iterator.next();
			SociedadDTO sociedadDTO = new SociedadDTO();
			sociedadDTO.setPkCodigoSociedad(sociedad.getPkCodigoSociedad());
			sociedadDTO.setNombreSociedad(sociedad.getNombreSociedad());
			sociedadDTO.setDescripcionSociedad(sociedad.getDescripcionSociedad());
			sociedadDTO.setCodigoSapSociedad(sociedad.getCodigoSapSociedad());
			
			sociedadDTO.setSiglasSociedad(sociedad.getSiglasSociedad());
			sociedadesDTO.add(sociedadDTO);
		}
		return sociedadesDTO;
	}

	/**
	 * Metodo que carga las unidades por codigo de sociedad
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<UnidadDTO> cargarUnidadesPorSociedad(Long codigoSociedad) throws ServicioGWTGlobalException {

		List<Unidad> unidades = null;
		List<UnidadDTO> unidadesDTO = new ArrayList<UnidadDTO>();

		try {
			unidades = unidadFacade.obtenerUnidadesDataObjects(codigoSociedad);

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Unidad> iterator = unidades.iterator(); iterator.hasNext();) {
			Unidad unidad = iterator.next();
			UnidadDTO unidadDTO = new UnidadDTO();
			unidadDTO.setPkCodigoUnidad(unidad.getPkCodigoUnidad());
			unidadDTO.setNombreUnidad(unidad.getNombreUnidad());
			unidadDTO.setCodigoSapUnidad(unidad.getCodigoSapUnidad());
			
			unidadDTO.setDescripcionUnidad(unidad.getDescripcionUnidad());
			unidadesDTO.add(unidadDTO);
		}
		return unidadesDTO;
	}

	/**
	 * Metodo que carga las lineas de negocio por codigo de unidad
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<LineaNegocioDTO> cargarLineasNegocioPorUnidad(Long codigoUnidad) throws ServicioGWTGlobalException {

		List<Lineanegocio> lineasNeogcio = null;
		List<LineaNegocioDTO> lineasNeogcioDTO = new ArrayList<LineaNegocioDTO>();

		try {
			lineasNeogcio = lineaNegocioFacade.obtenerLineasNegocioDataObjects(codigoUnidad);

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Lineanegocio> iterator = lineasNeogcio.iterator(); iterator.hasNext();) {
			Lineanegocio lineaNegocio = iterator.next();
			LineaNegocioDTO lineaNegocioDTO = new LineaNegocioDTO();
			lineaNegocioDTO.setPkCodigoLineanegocio(lineaNegocio.getPkCodigoLineanegocio());
			lineaNegocioDTO.setNombreLineanegocio(lineaNegocio.getNombreLineanegocio());
			
			lineaNegocioDTO.setDescripcionLineanegocio(lineaNegocio.getDescripcionLineanegocio());
			lineasNeogcioDTO.add(lineaNegocioDTO);
		}
		return lineasNeogcioDTO;
	}

	/**
	 * Metodo que carga los procesos por codigo de linea de negocio
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<ProcesoDTO> cargarProcesosPorLineaNegocio(Long codigoLineaNegocio) throws ServicioGWTGlobalException {

		List<Proceso> procesos = null;
		List<ProcesoDTO> procesosDTO = new ArrayList<ProcesoDTO>();

		try {
			procesos = procesoFacade.obtenerProcesosDataObjects(codigoLineaNegocio);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Proceso> iterator = procesos.iterator(); iterator.hasNext();) {
			Proceso proceso = iterator.next();
			ProcesoDTO procesoDTO = new ProcesoDTO();
			procesoDTO.setPkCodigoProceso(proceso.getPkCodigoProceso());
			procesoDTO.setNombreProceso(proceso.getNombreProceso());
			procesoDTO.setCodigoSccProceso(proceso.getCodigoSccProceso());
			procesoDTO.setDescripcionProceso(proceso.getDescripcionProceso());
			procesosDTO.add(procesoDTO);

		}
		return procesosDTO;
	}

	/**
	 * Metodo que carga los stados de ordenes de produccion
	 * 
	 * @throws ServicioGWTGlobalException
	 */
	public List<EstadoProduccionSemanalDTO> cargarEstadosOrdenProduccion() throws ServicioGWTGlobalException {

		List<Estadoordenproduccion> estados = null;
		List<EstadoProduccionSemanalDTO> estadosOrdenProduccionDTO = new ArrayList<EstadoProduccionSemanalDTO>();

		try {
			estados = estadoOrdenProduccionFacade.obtenerEstadosOrdenProduccion();

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Estadoordenproduccion> iterator = estados.iterator(); iterator.hasNext();) {
			Estadoordenproduccion estado = iterator.next();
			EstadoProduccionSemanalDTO estadoOrdenProduccionDTO = new EstadoProduccionSemanalDTO();
			estadoOrdenProduccionDTO.setPkCodigoEstadoProduccionSemanal(estado.getPkCodigoEstadoorden());
			estadoOrdenProduccionDTO.setNombreEstadoProduccionSemanal(estado.getNombreEstadoorden());
			estadosOrdenProduccionDTO.add(estadoOrdenProduccionDTO);
		}
		return estadosOrdenProduccionDTO;
	}

	/**
	 * Metodo que dada una Clave, devuelve el valor de la propiedad dada la
	 * clave señalada
	 * 
	 * @param listaClavesPropiedades
	 * @return
	 */
	public String obtenerPropiedadPorClave(String clavePropiedad) {
		return ManejadorPropiedades.obtenerPropiedadPorClave(clavePropiedad);
	}

	/**
	 * Metodo que dada una Lista de Claves, devuelve en un Mapa, todos los
	 * valores de las propiedades dadas sus claves
	 * 
	 * @param listaClavesPropiedades
	 * @return
	 */
	public Map<String, String> obtenerMapaPropiedadesPorListaClaves(List<String> listaClavesPropiedades) {

		Map<String, String> mapaPropiedades = new HashMap<String, String>();
		for (Iterator<String> it = listaClavesPropiedades.iterator(); it.hasNext();) {
			String valorClave = it.next();
			String valorPropiedad = this.obtenerPropiedadPorClave(valorClave);
			mapaPropiedades.put(valorClave, valorPropiedad);
		}
		return mapaPropiedades;

	}

	/**
	 * Metodo que dada una fecha tipo Date carga una lista de los dias de esa
	 * semana
	 */
	public String[] obtenerSemana(Date fecha) {

		String[] semana = new String[7];

		DateFormat df = new SimpleDateFormat(FechaUtil.PATRON_FECHA_APLICACION);
		df.format(fecha);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);

		// Este for calcula los dias de la semana de sabado a viernes
		// y los agrega en la lista de dias
		for (Integer i = 0; i <= 6; i++) {
			// asignamos al objeto date
			fecha = cal.getTime();

			// agregamos en la lista el dia...
			semana[i] = df.format(fecha);
			// incrementamos el dia
			cal.add(Calendar.DATE, 1);
		}
		return semana;
	}

	/**
	 * Metodo que consulta la fecha de la ultiuma produccion semanal registrada
	 * en BD, para determinar la fecha de la produccion semanal actual
	 */
	public Date obtenerFechaActual() {

		Calendar cal = Calendar.getInstance();

		return cal.getTime();
	}

	/**
	 * Metodo que retorna la session
	 * 
	 * @return
	 */
	private HttpSession getSession() {
		return this.getThreadLocalRequest().getSession();
	}

	/**
	 * Metodo que retorna el usuario actual
	 * 
	 * @return
	 */
	protected UsuarioBean getUsuarioSession() {
		HttpSession sesion = getSession();
		return (UsuarioBean) sesion.getAttribute(USUARIO_SESION);
	}

	protected boolean esUsuarioAdmin() {
		for (Iterator<GrupoUsuarioBean> itorGrupoUsuario = usuario.getGrupoUsuarios().iterator(); itorGrupoUsuario.hasNext();) {
			GrupoUsuarioBean grupoUsuario = itorGrupoUsuario.next();
			if (grupoUsuario.getNombre().equals(GRUPO_USUARIO_ADMIN))
				return true;
		}
		return false;
	}

	protected CargoBean getUsuarioCargo() {
		return usuario.getPersona().getCargo();
	}

	/**
	 * Metodo para transformar el UsuarioBean que obtenermos de la sesion en un
	 * Usuario
	 * 
	 * @param usuarioBean
	 * @return
	 * @throws LogicaException
	 */
	public static Usuario obtenerUsuarioDTO(UsuarioBean usuarioBean) throws LogicaException {

		Usuario usuario = usuarioFacade.obtenerUsuarioDataObject(usuarioBean.getCodigo());

		return usuario;
	}

	public List<ProductoDTO> cargarProductoPorProceso(Long codigoProceso) throws ServicioGWTGlobalException {

		List<Producto> productos = null;
		List<ProductoDTO> productoDTOs = new ArrayList<ProductoDTO>();

		try {
			productos = productoFacade.obtenerProductoPorProceso(codigoProceso);

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}

		for (Producto producto : productos) {
			ProductoDTO productoDTO = new ProductoDTO();

			productoDTO.setPkCodigoProducto(producto.getPkCodigoProducto());
			productoDTO.setNombreProducto(producto.getNombreProducto());
			productoDTO.setDescripcionProducto(producto.getDescripcionProducto());
			productoDTO.setFkCodigoTipoProducto(producto.getTipoproducto().getPkCodigoTipoproducto());
			productoDTO.setGrupoProducto(producto.getGrupoProducto());

			productoDTOs.add(productoDTO);
		}

		return productoDTOs;
	}

	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamiento(Long codigoTipoMedioAlmacenamiento, Long codigoProceso)
			throws ServicioGWTGlobalException {
		List<Medioalmacenamiento> medios = null;
		List<MedioAlmacenamientoDTO> mediosDTO = new ArrayList<MedioAlmacenamientoDTO>();

		try {
			medios = mediosFacade.obtenerMedioAlmacenamientoDTOPorTipoMedioYProceso(codigoTipoMedioAlmacenamiento, codigoProceso);
			for (Iterator<Medioalmacenamiento> iterator = medios.iterator(); iterator.hasNext();) {
				Medioalmacenamiento medio = iterator.next();
				MedioAlmacenamientoDTO medioDTO = new MedioAlmacenamientoDTO();
				medioDTO.setPkCodigoMedioalmacenamiento(medio.getPkCodigoMedioalmacenamiento());

				medioDTO.setNombreMedioalmacenamiento(medio.getNombreMedioalmacenamiento());
				medioDTO.setNumeroMedioalmacenamiento(medio.getNumeroMedioalmacenamiento());
				mediosDTO.add(medioDTO);
			}
			return mediosDTO;
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}

	}

	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamiento(Long codigoTipoMedioAlmacenamiento, Long codigoProceso,
			Long codigoPoducto) throws ServicioGWTGlobalException {
		List<Medioalmacenamiento> medios = null;
		List<MedioAlmacenamientoDTO> mediosDTO = new ArrayList<MedioAlmacenamientoDTO>();

		try {
			medios = mediosFacade.obtenerMedioAlmacenamientoDTOPorTipoMedioYProcesoYProducto(codigoTipoMedioAlmacenamiento,
					codigoProceso, codigoPoducto);
			for (Iterator<Medioalmacenamiento> iterator = medios.iterator(); iterator.hasNext();) {
				Medioalmacenamiento medio = iterator.next();
				MedioAlmacenamientoDTO medioDTO = new MedioAlmacenamientoDTO();
				medioDTO.setPkCodigoMedioalmacenamiento(medio.getPkCodigoMedioalmacenamiento());

				medioDTO.setNombreMedioalmacenamiento(medio.getNombreMedioalmacenamiento());
				medioDTO.setNumeroMedioalmacenamiento(medio.getNumeroMedioalmacenamiento());
				mediosDTO.add(medioDTO);
			}
			return mediosDTO;
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}

	}

	public List<MedioAlmacenamientoDTO> cargarMediosAlmacenamientoSegunProceso(Long codigoProceso)
			throws ServicioGWTGlobalException {
		List<Medioalmacenamiento> medios = null;
		List<MedioAlmacenamientoDTO> mediosDTO = new ArrayList<MedioAlmacenamientoDTO>();

		try {
			medios = mediosFacade.obtenerMedioAlmacenamientoPorProceso(codigoProceso);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		for (Iterator<Medioalmacenamiento> iterator = medios.iterator(); iterator.hasNext();) {
			Medioalmacenamiento medio = iterator.next();
			MedioAlmacenamientoDTO medioDTO = new MedioAlmacenamientoDTO();
			medioDTO.setPkCodigoMedioalmacenamiento(medio.getPkCodigoMedioalmacenamiento());

			medioDTO.setNombreMedioalmacenamiento(medio.getNombreMedioalmacenamiento());
			medioDTO.setNumeroMedioalmacenamiento(medio.getNumeroMedioalmacenamiento());
			mediosDTO.add(medioDTO);
		}
		return mediosDTO;
	}

	public List<EstadocubicacionDTO> cargarEstadosCubicacion() throws ServicioGWTGlobalException {
		List<Estadocubicacion> estadosCubicacion = null;
		List<EstadocubicacionDTO> estadosCubicacionDTO = new ArrayList<EstadocubicacionDTO>();

		try {
			estadosCubicacion = estadoCubicacionLogicFacade.getEstadosCubicacionDTO();
			for (Estadocubicacion estadocubicacion : estadosCubicacion) {

				EstadocubicacionDTO estadocubicacionDTO = new EstadocubicacionDTO();

				estadocubicacionDTO.setPkCodigoEstadocubicacion(estadocubicacion.getPkCodigoEstadocubicacion());
				estadocubicacionDTO.setNombreEstadocubicacion(estadocubicacion.getNombreEstadocubicacion());

				estadosCubicacionDTO.add(estadocubicacionDTO);
			}

		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}

		return estadosCubicacionDTO;
	}

	public String registrarCubicacion(List<TablaCubicacionDTO> listaCubicaciones) throws ServicioGWTGlobalException {
		String MENSJ_EXITO = "";
		try {
			cubicacionProductoLogicFacade.registrarCubicacion(listaCubicaciones, getUsuarioSession().getCodigo());
			MENSJ_EXITO = ConstantesGWT.OPERACION_EXITOSA;
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
		return MENSJ_EXITO;
	}

	public List<TableroControlDTO> cargarTablerosControlPorUnidad(Long codigoUnidad) {
		List<TableroControlDTO> tablerosControlDTO = new ArrayList<TableroControlDTO>();
		List<Tablerocontrol> tablerosControl = null;

		try {
			tablerosControl = tableroControlFacade.obtenerTablerosControlDTOPorUnidad(codigoUnidad);
			for (Tablerocontrol tablerocontrol : tablerosControl) {
				TableroControlDTO tableroControlDTO = new TableroControlDTO();

				tableroControlDTO.setPkCodigoTableroControl(tablerocontrol.getPkCodigoTablerocontrol());
				tableroControlDTO.setNombreTableroControl(tablerocontrol.getNombreTablerocontrol());
				tableroControlDTO.setDescripcionTableroControl(tablerocontrol.getDescripcionTablerocontrol());
			}
		} catch (LogicaException e) {
			logger.error(e.getMensaje());
		}

		return tablerosControlDTO;
	}

	public ProcesoDTO obtenerProceso(long codigoProceso) throws ServicioGWTGlobalException {
		ProcesoDTO procesoDTO = null;

		try {
			procesoDTO = procesoFacade.obtenerProcesoDTO(codigoProceso);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}

		return procesoDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.Servicio#
	 * obtenerOrdenProduccionDTO(java.lang.Long)
	 */
	public OrdenProduccionDTO obtenerOrdenProduccionDTO(Long codigoOrden) throws ServicioGWTGlobalException {
		OrdenProduccionDTO ordenDTO = null;

		try {
			ordenDTO = ordenFacade.obtenerOrdenProduccionDTO(codigoOrden.intValue());
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}

		return ordenDTO;
	}

	public PuestoTrabajoDTO obtenerPuestoTrabajo(long codigoPuestoTrabajo) {
		PuestoTrabajoDTO puestoTrabajoDTO = null;
		try {
			puestoTrabajoDTO = puestosFacade.obtenerPuestoTrabajoDTO(codigoPuestoTrabajo);
		} catch (LogicaException e) {
			logger.error(e.getMessage(), e);
		}

		return puestoTrabajoDTO;
	}

	public List<TableroControlDTO> cargarTablerosControlPorPuestoTrabajoUnidad(Long codigoPuestoTrabajo, Long codigoUnidad) {
		List<TableroControlDTO> tablerosControlDTO = null;

		try {
			tablerosControlDTO = tableroControlFacade.obtenerTablerosControlDTO(codigoPuestoTrabajo, codigoUnidad);

		} catch (LogicaException e) {
			logger.error(e.getMensaje());
		}

		return tablerosControlDTO;
	}

	/**
	 * Cambia la produccion de manera normal
	 */
	public List<OrdenProduccionDTO> obtenerOrdenesProduccionDTOByProceso(Long codigoProceso) {

		List<OrdenProduccionDTO> ordenesDTO = new ArrayList<OrdenProduccionDTO>();

		List<Ordenproduccion> ordenes = null; //produccionSemanalFacade.obtenerOrdenesByMesActualAndProceso(codigoProceso);

		for (Iterator<Ordenproduccion> iterator = ordenes.iterator(); iterator.hasNext();) {
			Ordenproduccion orden = iterator.next();

			OrdenProduccionDTO ordenDTO = new OrdenProduccionDTO();
			ordenDTO.setPkCodigoOrdenproduccion(orden.getPkCodigoOrdenproduccion());
			ordenDTO.setFechaAprobacionOrdenproduccio(orden.getFechaAprobacionOrdenproduccio());
			ordenDTO.setFechaRegistroOrdenproduccion(orden.getFechaRegistroOrdenproduccion());
			ordenDTO.setMesOrdenproduccion(orden.getMesOrdenproduccion());
			ordenDTO.setNumeroDocumentoOrdenproduccio(orden.getNumeroDocumentoOrdenproduccio());
			ordenDTO.setNumeroOrdenOrdenproduccion(orden.getNumeroOrdenOrdenproduccion());

			ordenesDTO.add(ordenDTO);
		}

		return ordenesDTO;
	}

	// /**
	// * Cambia la produccion con lavado
	// */
	// public Boolean cambiarProduccionNormal(Long codigoNotificacionProduccion,
	// String minuto) {
	// Boolean retorno = false;
	// try {
	// notificacionProduccionFacade.modificarNotificacionProduccion(codigoNotificacionProduccion,
	// minuto);
	//
	// retorno = true;
	// } catch (LogicaException e) {
	// logger.error(e.getMessage(), e);
	// }
	//
	// return retorno;
	// }

	// /**
	// * Metodo que carga las ordenes de produccionDTO
	// */
	// public Boolean cambiarProduccionLavado(Long codigoNotificacionProduccion,
	// Long codigoOrden, Long codigoSilo) {
	// Boolean retorno = false;
	// try {
	// notificacionProduccionFacade.crearNotificacionProduccionLavado(codigoNotificacionProduccion,
	// codigoOrden,
	// codigoSilo);
	// retorno = true;
	// } catch (LogicaException e) {
	// logger.error(e.getMessage(), e);
	// }
	//
	// return retorno;
	//
	// }

	public Boolean colocarObjetoEnSesion(String nombre, Integer objeto) {
		HttpSession session = getSession();
		session.setAttribute(nombre, objeto);
		return true;
	}

	public List<PlantillaProductoDTO> cargarPlantillas(Long codigoPlantillaReporte, Long codigoProducto)
			throws ServicioGWTGlobalException {
		try {
			List<PlantillaProductoBean> plantillasProducto = plantillaProductoLogicFacade.obtenerPorPlantillaReporteYProducto(
					codigoPlantillaReporte, codigoProducto);
			List<PlantillaProductoDTO> plantillasProductoDTO = new ArrayList<PlantillaProductoDTO>();
			for (PlantillaProductoBean plantillaproducto : plantillasProducto) {
				PlantillaProductoDTO plantillaProductoDTO = new PlantillaProductoDTO();
				plantillaProductoDTO.setPkCodigoPlantillaProducto(plantillaproducto.getCodigo());

				Date fechaPlantillaproducto = plantillaproducto.getFecha();
				String fechaStr = FechaUtil.convertirDateToString(fechaPlantillaproducto);
				plantillaProductoDTO.setNombrePlantillaProducto(plantillaproducto.getProducto().getNombre() + " " + fechaStr
						+ " v" + plantillaproducto.getVersion());
				plantillasProductoDTO.add(plantillaProductoDTO);
			}
			return plantillasProductoDTO;
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	public PlantillaProductoDTO cargarPlantillaMasReciente(Long codigoPlantillaReporte, Long codigoProducto)
			throws ServicioGWTGlobalException {
		try {
			PlantillaProductoDTO plantillaProductoDTO = plantillaProductoLogicFacade.obtenerActualPorPlantillaReporteYProducto(
					codigoPlantillaReporte, codigoProducto);

			return plantillaProductoDTO;
		} catch (LogicaException e) {
			e.printStackTrace();
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	public Boolean validarSiExisteCubicacionProducto(long codigoLineaNegocio, long codigoProducto, long codigoProceso,
			Date fechaCubicacion) throws ServicioGWTGlobalException {
		try {
			return cubicacionProductoLogicFacade.validarSiExisteCubicacionProducto(codigoLineaNegocio, codigoProducto,
					codigoProceso, fechaCubicacion);
		} catch (LogicaException e) {
			throw new ServicioGWTGlobalException(e);
		}
	}

	/**
	 * Devuelve el valor por defecto asignado al combo de División
	 */
	public String valorPorDefectoDivision() {

		String valorPorDefecto = "";
		usuario = getUsuarioSession();
		if (esUsuarioAdmin()) {
			valorPorDefecto = obtenerPropiedadPorClave(ConstantesAplicacionAction.CODIGO_POR_DEFECTO_DIVISION);
		} else {
			if (usuario.getPersona().getCargo() != null && usuario.getPersona().getCargo().getDivisionCargoBean() != null) {
				valorPorDefecto = usuario.getPersona().getCargo().getDivisionCargoBean().getDivisionBean().getCodigo().toString();
			}
		}
		return valorPorDefecto;
	}

	/**
	 * Devuelve el valor por defecto asignado al combo de Sociedad
	 */
	public String valorPorDefectoSociedad() {

		String valorPorDefecto = "";
		usuario = getUsuarioSession();
		if (esUsuarioAdmin()) {
			valorPorDefecto = obtenerPropiedadPorClave(ConstantesAplicacionAction.CODIGO_POR_DEFECTO_SOCIEDAD);
		} else {
			if (usuario.getPersona().getCargo() != null && usuario.getPersona().getCargo().getSociedadCargoBean() != null) {
				valorPorDefecto = usuario.getPersona().getCargo().getSociedadCargoBean().getSociedadBean().getCodigo().toString();
			}
		}
		return valorPorDefecto;
	}

	/**
	 * Devuelve el valor por defecto asignado al combo de Unidad
	 */
	public String valorPorDefectoUnidad() {

		String valorPorDefecto = "";
		usuario = getUsuarioSession();
		if (esUsuarioAdmin()) {
			valorPorDefecto = obtenerPropiedadPorClave(ConstantesAplicacionAction.CODIGO_POR_DEFECTO_UNIDAD);
		} else {
			if (usuario.getPersona().getCargo() != null && usuario.getPersona().getCargo().getUnidadCargoBean() != null) {
				valorPorDefecto = usuario.getPersona().getCargo().getUnidadCargoBean().getUnidadBean().getCodigo().toString();
			}
		}
		return valorPorDefecto;
	}

	public Boolean verificarSiEsProductoTerminado(Long codigoProducto) throws ServicioGWTGlobalException {
		try {
			return ProductoQuerier.verificarSiEsProductoTerminado(codigoProducto);
		} catch (SesionVencidaException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (ElementoNoEncontradoException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		} catch (EntornoEjecucionException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}
	}

	public Integer getUserSessionTimeout() throws ServicioGWTGlobalException {
		HttpSession session = this.getThreadLocalRequest().getSession();
		UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute(USUARIO_SESION);
		if (usuarioBean == null) {
			return null;
		}

		return session.getMaxInactiveInterval() * 1000;
	}

	public List<ComponenteDTO> getComponentesDeHRActivas() throws ServicioGWTGlobalException {
		// ComponenteQuerier
		return null;
	}

	/**
	 * AGREGADO POR JORDAN
	 * 
	 * @param codigoMedioAlmacenamiento
	 * @throws ServicioGWTGlobalException
	 */
	public Double cargarDensidadMedioAlmacenamiento(long codigoMedioAlmacenamiento) throws ServicioGWTGlobalException {
		Double densidad;
		try {
			Medioalmacenamiento medioalmacenamiento = MedioAlmacenamientoQuerier.getById(codigoMedioAlmacenamiento);
			densidad = medioalmacenamiento.getDensidadMedioalmacenamiento();
			if (densidad == null) {
				densidad = 1.0;
			}
		} catch (ElementoNoEncontradoException e) {
			throw new ServicioGWTGlobalException(e.getMensaje(), e);
		}

		return densidad;
	}

	public List<Long> obtenerCodigoProductoRendimientoTermico() throws ServicioGWTGlobalException {
		try {
			return rendimientoTermicoLogicFacade.obtenerCodigosProductosRendimientosTermicos();
		} catch (LogicaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Long>();

	}

	public Map<String, String> obtenerMapaParametroSistema() {
		Map<String, String> mapaParametros = new HashMap<String, String>();

		ParametroSistema parametroDensidadPetroleoKCAL = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.DENSIDAD_PETROLEO_KCAL);
		ParametroSistema parametroDensidadPetroleoCalKCAL = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.DENSIDAD_PETROLEO_KCAL_CAL);
		ParametroSistema parametroPoderCalorificoPetroleo = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.RENDIMIENTO_TERMICO_PETROLEO_KCAL);
		ParametroSistema parametroPoderCalorificoPetroleoCal = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.RENDIMIENTO_TERMICO_PETROLEO_CAL_KCAL);
		ParametroSistema parametroProgresionCarbonKCAL = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.PROGRESION_CARBON_KCAL);
		ParametroSistema parametroProgresionBunkerKCAL = ParametroSistemaQuerier
				.obtenerParametroSistema(ConstantesParametro.PROGRESION_BUNKER_KCAL);

		mapaParametros.put(ConstantesParametro.DENSIDAD_PETROLEO_KCAL, parametroDensidadPetroleoKCAL.getValorParametro());
		mapaParametros.put(ConstantesParametro.DENSIDAD_PETROLEO_KCAL_CAL, parametroDensidadPetroleoCalKCAL.getValorParametro());
		mapaParametros.put(ConstantesParametro.RENDIMIENTO_TERMICO_PETROLEO_KCAL,
				parametroPoderCalorificoPetroleo.getValorParametro());
		mapaParametros.put(ConstantesParametro.RENDIMIENTO_TERMICO_PETROLEO_CAL_KCAL,
				parametroPoderCalorificoPetroleoCal.getValorParametro());
		mapaParametros.put(ConstantesParametro.PROGRESION_CARBON_KCAL, parametroProgresionCarbonKCAL.getValorParametro());
		mapaParametros.put(ConstantesParametro.PROGRESION_BUNKER_KCAL, parametroProgresionBunkerKCAL.getValorParametro());

		return mapaParametros;
	}

}