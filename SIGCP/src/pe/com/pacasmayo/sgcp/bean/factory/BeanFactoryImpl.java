package pe.com.pacasmayo.sgcp.bean.factory;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: BeanFactoryImpl.java
 * Modificado: Nov 24, 2009 11:05:48 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.bean.*;
import pe.com.pacasmayo.sgcp.bean.impl.*;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.*;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Variablevariacion;
import pe.com.pacasmayo.sgcp.persistencia.querier.OrdenProduccionManualQuerier;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.DivisionDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.EstadoProduccionSemanalDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.LineaNegocioDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProcesoDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ProduccionSemanalDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.SociedadDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.UnidadDTO;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

public class BeanFactoryImpl implements BeanFactory {

	private NumberFormat nf = NumberFormat.getNumberInstance(new Locale("EN"));

	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	private static Logger logger = Logger.getLogger(BeanFactoryImpl.class);

	/** Singleton instance */
	private static BeanFactory singletton;

	private BeanFactoryImpl() {
		nf.setMaximumFractionDigits(3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaActividad
	 * (java.util.List)
	 */
	public static BeanFactory getInstance() {

		if (singletton == null) {
			singletton = new BeanFactoryImpl();
		}

		return singletton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaActividad
	 * (java.util.List)
	 */
	public List<ActividadBean> transformarListaActividad(
			List<Actividad> actividades) {

		List<ActividadBean> actividadBeans = new ArrayList<ActividadBean>();

		for (Iterator<Actividad> iterator = actividades.iterator(); iterator
				.hasNext();) {

			Actividad actividad = iterator.next();
			ActividadBean actividadBean = transformarActividad(actividad);
			if (actividadBean != null)
				actividadBeans.add(actividadBean);
		}

		return actividadBeans;
	}

	public List<ActividadBean> transformarListaActividadBasico(
			List<Actividad> actividades) {

		List<ActividadBean> actividadBeans = new ArrayList<ActividadBean>();

		for (Iterator<Actividad> iterator = actividades.iterator(); iterator
				.hasNext();) {

			Actividad actividad = iterator.next();
			ActividadBean actividadBean = transformarActividadBasico(actividad);
			if (actividadBean != null)
				actividadBeans.add(actividadBean);
		}

		return actividadBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarActividad(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Actividad)
	 */
	public ActividadBean transformarActividad(Actividad actividad) {

		if (actividad == null || actividad.getPkCodigoActividad() == null)
			return null;

		ActividadBean actividadBean = new ActividadBeanImpl();

		actividadBean.setCodigo(actividad.getPkCodigoActividad());
		actividadBean.setCodigoSCC(actividad.getCodigoSccActividad());
		actividadBean.setNombre(actividad.getNombreActividad());
		actividadBean.setDescripcion(actividad.getDescripcionActividad());
		actividadBean.setEstadoActividad(transformarEstadoActividad(actividad
				.getEstadoactividad()));
		actividadBean.setMetrosPerforacion(actividad
				.getMetrosPerforaActividad());

		actividadBean.setProceso(transformarProceso(actividad.getProceso()));

		return actividadBean;
	}

	private ActividadBean transformarActividadParaConsulta(Actividad actividad) {

		if (actividad == null || actividad.getPkCodigoActividad() == null)
			return null;

		ActividadBean actividadBean = new ActividadBeanImpl();

		actividadBean.setCodigo(actividad.getPkCodigoActividad());
		actividadBean.setNombre(actividad.getNombreActividad());

		return actividadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarActividadBasico
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Actividad)
	 */
	public ActividadBean transformarActividadBasico(Actividad actividad) {

		if (actividad == null || actividad.getPkCodigoActividad() == null)
			return null;

		ActividadBean actividadBean = new ActividadBeanImpl();

		actividadBean.setCodigo(actividad.getPkCodigoActividad());
		actividadBean.setCodigoSCC(actividad.getCodigoSccActividad());
		actividadBean.setNombre(actividad.getNombreActividad());
		actividadBean.setDescripcion(actividad.getDescripcionActividad());
		actividadBean.setMetrosPerforacion(actividad
				.getMetrosPerforaActividad());

		return actividadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarAlmacen(pe.
	 * com.pacasmayo.sgcp.persistencia.dataObjects.Almacen)
	 */
	public AlmacenBean transformarAlmacen(Almacen almacen) {

		if (almacen == null || almacen.getPkCodigoAlmacen() == null)
			return null;

		AlmacenBean almacenBean = new AlmacenBeanImpl();

		almacenBean.setCodigo(almacen.getPkCodigoAlmacen());
		if (almacen.getCodigoSapAlmacen() != null)
			almacenBean.setCodigoSAP(almacen.getCodigoSapAlmacen().trim());

		if (almacen.getDescripcionAlmacen() != null)
			almacenBean.setDescripcion(almacen.getDescripcionAlmacen().trim());

		if (almacen.getNombreAlmacen() != null)
			almacenBean.setNombre(almacen.getNombreAlmacen().trim());

		almacenBean.setUnidad(transformarUnidad(almacen.getUnidad()));

		return almacenBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaAlmacen
	 * (java.util.List)
	 */
	public List<AlmacenBean> transformarListaAlmacen(List<Almacen> almacenes) {

		List<AlmacenBean> almacenListaBean = new ArrayList<AlmacenBean>();

		for (Iterator<Almacen> iterator = almacenes.iterator(); iterator
				.hasNext();) {

			Almacen almacen = iterator.next();
			AlmacenBean almacenBean = transformarAlmacen(almacen);

			almacenListaBean.add(almacenBean);
		}

		return almacenListaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarClasificacionTipoMovimiento
	 * (pe.com.pacasmayo.sgcp.persistencia.
	 * dataObjects.Clasificaciontipomovimiento)
	 */
	public ClasificacionTipoMovimientoBean transformarClasificacionTipoMovimiento(
			Clasificaciontipomovimiento clasificacionTipoMovimiento) {

		if (clasificacionTipoMovimiento == null
				|| clasificacionTipoMovimiento
						.getPkCodigoClasificaciontipomovi() == null)
			return null;

		ClasificacionTipoMovimientoBean clasificacionTipoMovimientoBean = new ClasificacionTipoMovimientoBeanImpl();

		clasificacionTipoMovimientoBean.setCodigo(clasificacionTipoMovimiento
				.getPkCodigoClasificaciontipomovi());
		clasificacionTipoMovimientoBean.setNombre(clasificacionTipoMovimiento
				.getNombreClasificaciontipomovimie());

		return clasificacionTipoMovimientoBean;
	}

	public List<ClasificacionTipoMovimientoBean> transformarListaClasificacionTipoMovimiento(
			List<Clasificaciontipomovimiento> listaClasificacionTipoMovimiento) {

		List<ClasificacionTipoMovimientoBean> listaClasificacionTipoMovimientoBean = new ArrayList<ClasificacionTipoMovimientoBean>();

		for (Iterator<Clasificaciontipomovimiento> iterator = listaClasificacionTipoMovimiento
				.iterator(); iterator.hasNext();) {

			Clasificaciontipomovimiento clasificaciontipomovimiento = iterator
					.next();
			ClasificacionTipoMovimientoBean clasificacionTipoMovimientoBean = transformarClasificacionTipoMovimiento(clasificaciontipomovimiento);

			listaClasificacionTipoMovimientoBean
					.add(clasificacionTipoMovimientoBean);
		}

		return listaClasificacionTipoMovimientoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarComponente(
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente)
	 */
	public ComponenteBean transformarComponente(Componente componente) {

		ComponenteBean componenteBean = new ComponenteBeanImpl();

		componenteBean.setCodigo(componente.getPkCodigoComponente());
		componenteBean.setProducto(transformarProducto(componente
				.getProductoByFkCodigoProducto()));

		componenteBean.setProductoComponente(transformarProducto(componente
				.getProductoByFkCodigoProductoComponente()));

		if (componente.getFactordosificacions() != null)
			componenteBean
					.setFactorDosificacion(transformarListaFactordosificacion(componente
							.getFactordosificacions()));
		return componenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarComponenteSinFactorDosificacion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente)
	 */
	public ComponenteBean transformarComponenteSinFactorDosificacion(
			Componente componente) {

		ComponenteBean componenteBean = new ComponenteBeanImpl();

		componenteBean.setCodigo(componente.getPkCodigoComponente());
		componenteBean.setProducto(transformarProducto(componente
				.getProductoByFkCodigoProducto()));

		componenteBean.setProductoComponente(transformarProducto(componente
				.getProductoByFkCodigoProductoComponente()));

		return componenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaComponente
	 * (java.util.List)
	 */
	public List<ComponenteBean> transformarListaComponente(
			List<Componente> componenteLista) {

		List<ComponenteBean> listaComponenteBean = new ArrayList<ComponenteBean>();

		for (Iterator<Componente> iterator = componenteLista.iterator(); iterator
				.hasNext();) {

			Componente componente = iterator.next();
			ComponenteBean componenteBean = transformarComponente(componente);

			listaComponenteBean.add(componenteBean);
		}

		return listaComponenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarDivision(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Division)
	 */
	public DivisionBean transformarDivision(Division division) {

		if (division == null || division.getPkCodigoDivision() == null) {
			return null;
		}

		DivisionBean divisionBean = new DivisionBeanImpl();

		divisionBean.setCodigo(division.getPkCodigoDivision());

		if (division.getCodigoSapDivision() != null)
			divisionBean.setCodigoSAP(division.getCodigoSapDivision().trim());

		if (division.getNombreDivision() != null)
			divisionBean.setNombre(division.getNombreDivision().trim());

		if (division.getDescripcionDivision() != null)
			divisionBean.setDescripcion(division.getDescripcionDivision()
					.trim());

		return divisionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaDivision
	 * (java.util.List)
	 */
	public List<DivisionBean> transformarListaDivision(
			List<Division> divisionBeans) {

		List<DivisionBean> divisionImplBeans = new ArrayList<DivisionBean>();

		for (Iterator<Division> iterator = divisionBeans.iterator(); iterator
				.hasNext();) {

			Division division = iterator.next();
			DivisionBean divisionImplBean = transformarDivision(division);

			divisionImplBeans.add(divisionImplBean);
		}
		return divisionImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoActividad(java.util.List)
	 */
	public List<EstadoActividadBean> transformarListaEstadoActividad(
			List<Estadoactividad> listaEstadoActividad) {

		List<EstadoActividadBean> listaEstadoActividadBean = new ArrayList<EstadoActividadBean>();

		for (Iterator<Estadoactividad> iterator = listaEstadoActividad
				.iterator(); iterator.hasNext();) {

			Estadoactividad estadoActividad = iterator.next();
			EstadoActividadBean estadoActividadBean = transformarEstadoActividad(estadoActividad);

			listaEstadoActividadBean.add(estadoActividadBean);
		}

		return listaEstadoActividadBean;
	}

	public List<EstadoPrivilegioBean> transformarListaEstadoPrivilegio(
			List<Estadoprivilegio> listaEstadoPrivilegio) {

		List<EstadoPrivilegioBean> listaEstadoPrivilegioBean = new ArrayList<EstadoPrivilegioBean>();

		for (Iterator<Estadoprivilegio> iterator = listaEstadoPrivilegio
				.iterator(); iterator.hasNext();) {

			Estadoprivilegio estadoPrivilegio = iterator.next();
			EstadoPrivilegioBean estadoPrivilegioBean = transformarEstadoPrivilegio(estadoPrivilegio);

			listaEstadoPrivilegioBean.add(estadoPrivilegioBean);
		}

		return listaEstadoPrivilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoActividad
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoactividad)
	 */
	public EstadoNotificacionBean transformarEstadoNotificacion(
			Estadonotificacion estadoNotificacion) {

		EstadoNotificacionBean estadoNotificacionBean = new EstadoNotificacionBeanImpl();

		estadoNotificacionBean.setCodigo(estadoNotificacion
				.getPkCodigoEstadonotificacion());
		estadoNotificacionBean.setNombreEstadoNotificacion(estadoNotificacion
				.getNombreEstadonotificacion());

		return estadoNotificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoNotificacion(java.util.List)
	 */
	public List<EstadoNotificacionBean> transformarListaEstadoNotificacion(
			List<Estadonotificacion> listaEstadoNotificacion) {

		List<EstadoNotificacionBean> listaEstadoNotificacionBean = new ArrayList<EstadoNotificacionBean>();

		for (Iterator<Estadonotificacion> iterator = listaEstadoNotificacion
				.iterator(); iterator.hasNext();) {

			EstadoNotificacionBean estadoNotificacionBean = transformarEstadoNotificacion(iterator
					.next());

			listaEstadoNotificacionBean.add(estadoNotificacionBean);
		}

		return listaEstadoNotificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoActividad
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoactividad)
	 */
	public EstadoActividadBean transformarEstadoActividad(
			Estadoactividad estadoActividad) {

		EstadoActividadBean estadoActividadBean = new EstadoActividadBeanImpl();

		estadoActividadBean.setCodigo(estadoActividad
				.getPkCodigoEstadoactividad());
		estadoActividadBean.setNombre(estadoActividad
				.getNombreEstadoactividad());

		return estadoActividadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarNotificacionDiaria
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria)
	 */
	@SuppressWarnings("unchecked")
	public NotificacionDiariaBean transformarNotificacionDiaria(
			Notificaciondiaria notificaciondiaria) {
		if (notificaciondiaria == null)
			return null;
		NotificacionDiariaBean notificacionDiariaBean = new NotificacionDiariaBeanImpl();

		notificacionDiariaBean.setCodigo(notificaciondiaria
				.getPkCodigoNotificaciondiaria());
		notificacionDiariaBean
				.setUsuarioAprueba(transformarUsuario(notificaciondiaria
						.getUsuarioByFkCodigoUsuarioAprueba()));
		notificacionDiariaBean
				.setUsuarioRegistra(transformarUsuario(notificaciondiaria
						.getUsuarioByFkCodigoUsuarioRegistra()));
		notificacionDiariaBean
				.setEstadoNotificacion(transformarEstadoNotificacion(notificaciondiaria
						.getEstadonotificacion()));
		notificacionDiariaBean.setFechaNotificacionDiaria(notificaciondiaria
				.getFechaNotificaciondiaria());
		notificacionDiariaBean
				.setLineaNegocio(transformarLineaNegocio(notificaciondiaria
						.getLineanegocio()));
		notificacionDiariaBean
				.setTableroControl(transformarTableroControl(notificaciondiaria
						.getTablerocontrol()));
		notificacionDiariaBean
				.setNotificacionOperaciones(transformarListaNotificacionOperacion(notificaciondiaria
						.getNotificacionoperacions()));
		notificacionDiariaBean
				.setNotificacionProducciones(transformarListaNotificacionProduccion(notificaciondiaria
						.getNotificacionproduccions()));

		return notificacionDiariaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarNotificacionDiariaSimple
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificaciondiaria)
	 */
	public NotificacionDiariaBean transformarNotificacionDiariaSimple(
			Notificaciondiaria notificaciondiaria) {
		if (notificaciondiaria == null)
			return null;
		NotificacionDiariaBean notificacionDiariaBean = new NotificacionDiariaBeanImpl();

		notificacionDiariaBean.setCodigo(notificaciondiaria
				.getPkCodigoNotificaciondiaria());
		notificacionDiariaBean
				.setUsuarioAprueba(transformarUsuario(notificaciondiaria
						.getUsuarioByFkCodigoUsuarioAprueba()));
		notificacionDiariaBean
				.setUsuarioRegistra(transformarUsuario(notificaciondiaria
						.getUsuarioByFkCodigoUsuarioRegistra()));
		notificacionDiariaBean
				.setEstadoNotificacion(transformarEstadoNotificacion(notificaciondiaria
						.getEstadonotificacion()));
		notificacionDiariaBean.setFechaNotificacionDiaria(notificaciondiaria
				.getFechaNotificaciondiaria());
		notificacionDiariaBean
				.setLineaNegocio(transformarLineaNegocioBasico(notificaciondiaria
						.getLineanegocio()));
		notificacionDiariaBean
				.setObservacionNotificacionDiaria(notificaciondiaria
						.getObservacionNotificaciondiaria());
		notificacionDiariaBean
				.setUsuarioCierra(transformarUsuario(notificaciondiaria
						.getUsuarioByFkCodigoUsuarioCierra()));
		return notificacionDiariaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaNotificacionDiariaSimple(java.util.List)
	 */
	public List<NotificacionDiariaBean> transformarListaNotificacionDiariaSimple(
			List<Notificaciondiaria> notificacionesDiaria) {

		List<NotificacionDiariaBean> notificacionDiariaBeanList = new ArrayList<NotificacionDiariaBean>();

		for (Iterator<Notificaciondiaria> iterator = notificacionesDiaria
				.iterator(); iterator.hasNext();) {
			NotificacionDiariaBean notificacionDiariaBean = transformarNotificacionDiariaSimple(iterator
					.next());
			notificacionDiariaBeanList.add(notificacionDiariaBean);
		}
		return notificacionDiariaBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaNotificacionDiaria(java.util.List)
	 */
	public List<NotificacionDiariaBean> transformarListaNotificacionDiaria(
			List<Notificaciondiaria> notificacionesDiaria) {

		List<NotificacionDiariaBean> notificacionDiariaBeanList = new ArrayList<NotificacionDiariaBean>();

		for (Iterator<Notificaciondiaria> iterator = notificacionesDiaria
				.iterator(); iterator.hasNext();) {
			NotificacionDiariaBean notificacionDiariaBean = transformarNotificacionDiaria(iterator
					.next());
			notificacionDiariaBeanList.add(notificacionDiariaBean);
		}
		return notificacionDiariaBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTableroControl
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablerocontrol)
	 */
	public TableroControlBean transformarTableroControl(
			Tablerocontrol tablerocontrol) {
		if (tablerocontrol == null)
			return null;
		TableroControlBean tableroControlBean = new TableroControlBeanImpl();

		tableroControlBean
				.setCodigo(tablerocontrol.getPkCodigoTablerocontrol());
		tableroControlBean.setNombre(tablerocontrol.getNombreTablerocontrol());
		tableroControlBean.setDescripcion(tablerocontrol
				.getDescripcionTablerocontrol());
		tableroControlBean.setUnidad(transformarUnidad(tablerocontrol
				.getUnidad()));

		return tableroControlBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarNotificacionOperacion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionoperacion)
	 */
	public NotificacionOperacionBean transformarNotificacionOperacion(
			Notificacionoperacion notificacionoperacion) {
		NotificacionOperacionBean notificacionOperacionBean = new NotificacionOperacionBeanImpl();

		notificacionOperacionBean.setCodigo(notificacionoperacion
				.getPkCodigoNotificacionoperacion());

		return notificacionOperacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaNotificacionOperacion(java.util.Set)
	 */
	public Set<NotificacionOperacionBean> transformarListaNotificacionOperacion(
			Set<Notificacionoperacion> notificacionesOperacion) {

		Set<NotificacionOperacionBean> notificacionOperacionBeanList = new HashSet<NotificacionOperacionBean>();

		for (Iterator<Notificacionoperacion> iterator = notificacionesOperacion
				.iterator(); iterator.hasNext();) {
			NotificacionOperacionBean notificacionOperacionBean = transformarNotificacionOperacion(iterator
					.next());
			notificacionOperacionBeanList.add(notificacionOperacionBean);
		}
		return notificacionOperacionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#TransformarRegistroReporteEcs
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registroreporteecs)
	 */
	public RegistroReporteEcsBean TransformarRegistroReporteEcs(
			Registroreporteecs registroreporteecs) {
		RegistroReporteEcsBean registroReporteEcsBean = new RegistroReporteEcsBeanImpl();

		registroReporteEcsBean.setCodigo(registroreporteecs
				.getPkCodigoRegistroreporteecs());
		// TODO Ajustar al cambiar la BD
		// registroReporteEcsBean.setNombreRegistroReportEecs(registroreporteecs.getNombreRegistroreporteecs());
		// registroReporteEcsBean.setFechaRegistroReporteEcs(registroreporteecs.getFechaRegistroreporteecs());

		return registroReporteEcsBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarNotificacionProduccion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion)
	 */
	public NotificacionProduccionBean transformarNotificacionProduccion(
			Notificacionproduccion notificacionproduccion) {
		NotificacionProduccionBean notificacionProduccionBean = new NotificacionProduccionBeanImpl();

		notificacionProduccionBean.setCodigo(notificacionproduccion
				.getPkCodigoNotificacionproduccio());

		notificacionProduccionBean
				.setHora(transformarHora(notificacionproduccion.getHora()));

		return notificacionProduccionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaNotificacionProduccion(java.util.Set)
	 */
	public Set<NotificacionProduccionBean> transformarListaNotificacionProduccion(
			Set<Notificacionproduccion> notificacionesProduccion) {

		Set<NotificacionProduccionBean> notificacionProduccionBeanList = new HashSet<NotificacionProduccionBean>();

		for (Iterator<Notificacionproduccion> iterator = notificacionesProduccion
				.iterator(); iterator.hasNext();) {
			NotificacionProduccionBean notificacionProduccionBean = transformarNotificacionProduccion(iterator
					.next());
			notificacionProduccionBeanList.add(notificacionProduccionBean);
		}
		return notificacionProduccionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoHojaRuta
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadohojaruta)
	 */
	public EstadoHojaRutaBean transformarEstadoHojaRuta(
			Estadohojaruta estadoHohaRuta) {

		EstadoHojaRutaBean estadoActividadBean = new EstadoHojaRutaBeanImpl();

		estadoActividadBean.setCodigo(estadoHohaRuta
				.getPkCodigoEstadohojaruta());
		estadoActividadBean.setNombre(estadoHohaRuta.getNombreEstadohojaruta());

		return estadoActividadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaEstadoHojaRuta
	 * (java.util.List)
	 */
	public List<EstadoHojaRutaBean> transformarListaEstadoHojaRuta(
			List<Estadohojaruta> listaEstadosHojaRuta) {

		List<EstadoHojaRutaBean> listaHojaRutaBean = new ArrayList<EstadoHojaRutaBean>();

		for (Iterator<Estadohojaruta> iterator = listaEstadosHojaRuta
				.iterator(); iterator.hasNext();) {

			Estadohojaruta estadoHojaRuta = iterator.next();
			EstadoHojaRutaBean estadoHojaRutaBean = transformarEstadoHojaRuta(estadoHojaRuta);

			listaHojaRutaBean.add(estadoHojaRutaBean);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaEstadoProducto
	 * (java.util.List)
	 */
	public List<EstadoProductoBean> transformarListaEstadoProducto(
			List<Estadoproducto> estadoProductoBeans) {

		List<EstadoProductoBean> listaEstadoProducBean = new ArrayList<EstadoProductoBean>();

		for (Iterator<Estadoproducto> iterator = estadoProductoBeans.iterator(); iterator
				.hasNext();) {

			Estadoproducto estadoProd = iterator.next();
			EstadoProductoBean estadoProdBean = transformarEstadoProducto(estadoProd);

			listaEstadoProducBean.add(estadoProdBean);
		}
		return listaEstadoProducBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoproducto)
	 */
	public EstadoProductoBean transformarEstadoProducto(
			Estadoproducto estadoProducto) {
		if (estadoProducto == null
				|| estadoProducto.getPkCodigoEstadoproducto() == null) {
			return null;
		}

		EstadoProductoBean estadoProductoBean = new EstadoProductoBeanImpl();

		estadoProductoBean
				.setCodigo(estadoProducto.getPkCodigoEstadoproducto());
		estadoProductoBean.setNombre(estadoProducto.getNombreEstadoproducto());

		return estadoProductoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoUsuario
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadousuario)
	 */
	public EstadoUsuarioBean transformarEstadoUsuario(
			Estadousuario estadoUsuario) {
		if (estadoUsuario == null
				|| estadoUsuario.getPkCodigoEstadousuario() == null) {
			return null;
		}

		EstadoUsuarioBean estadoUsuarioBean = new EstadoUsuarioBeanImpl();
		estadoUsuarioBean.setCodigo(estadoUsuario.getPkCodigoEstadousuario());
		estadoUsuarioBean.setNombre(estadoUsuario.getNombreEstadousuario());
		estadoUsuarioBean.setDescripcion(estadoUsuario
				.getDescripcionEstadousuario());

		return estadoUsuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaEstados
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadousuario)
	 */
	public List<EstadoUsuarioBean> transformarListaEstados(
			List<Estadousuario> estados) {

		List<EstadoUsuarioBean> estadosBeans = new ArrayList<EstadoUsuarioBean>();
		for (Iterator<Estadousuario> iterator = estados.iterator(); iterator
				.hasNext();) {
			Estadousuario estado = iterator.next();
			EstadoUsuarioBean estadoBean = transformarEstadoUsuario(estado);
			if (estadoBean != null)
				estadosBeans.add(estadoBean);
		}

		return estadosBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoFuncionalidad
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadofuncionalidad)
	 */
	public EstadoPrivilegioBean transformarEstadoPrivilegio(
			Estadoprivilegio estadoPrivilegio) {
		if (estadoPrivilegio == null
				|| estadoPrivilegio.getPkCodigoEstadoprivilegio() == null) {
			return null;
		}

		EstadoPrivilegioBean estadoPrivilegioBean = new EstadoPrivilegioBeanImpl();

		estadoPrivilegioBean.setCodigo(estadoPrivilegio
				.getPkCodigoEstadoprivilegio());
		estadoPrivilegioBean.setNombre(estadoPrivilegio
				.getNombreEstadoprivilegio());
		estadoPrivilegioBean.setDescripcion(estadoPrivilegio
				.getDescripcionEstadoprivilegio());

		return estadoPrivilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarGrupoUsuario
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuario)
	 */
	@SuppressWarnings("unchecked")
	public GrupoUsuarioBean transformarGrupoUsuario(Grupousuario grupousuario,
			boolean agregarGrupoPrivilegios) {
		if (grupousuario == null
				|| grupousuario.getPkCodigoGrupousuario() == null) {
			return null;
		}

		GrupoUsuarioBean grupoUsuarioBean = new GrupoUsuarioBeanImpl();

		grupoUsuarioBean.setCodigo(grupousuario.getPkCodigoGrupousuario());
		grupoUsuarioBean.setNombre(grupousuario.getNombreGrupousuario());
		grupoUsuarioBean.setDescripcion(grupousuario
				.getDescripcionGrupousuario());

		if (agregarGrupoPrivilegios)
			grupoUsuarioBean
					.setGrupoUsuarioPrivilegios(transformarListaGrupoUsuarioPrivilegio(grupousuario
							.getGrupousuarioprivilegios()));

		return grupoUsuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarGrupoUsuario
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuario)
	 */
	public UsuarioGrupoUsuarioBean transformarUsuarioGrupoUsuario(
			Usuariogrupousuario usuariogrupousuario) {

		if (usuariogrupousuario == null
				|| usuariogrupousuario.getPkCodigoUsuariogrupousuario() == null) {
			return null;
		}

		UsuarioGrupoUsuarioBean usuarioGrupoUsuarioBean = new UsuarioGrupoUsuarioBeanImpl();

		usuarioGrupoUsuarioBean.setCodigo(usuariogrupousuario
				.getPkCodigoUsuariogrupousuario());
		usuarioGrupoUsuarioBean.setGrupoUsuarioBean(transformarGrupoUsuario(
				usuariogrupousuario.getGrupousuario(), false));

		return usuarioGrupoUsuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaUnidadCargo
	 * (java.util.Set)
	 */
	public List<UsuarioGrupoUsuarioBean> transformarListaUsuarioGrupoUsuario(
			Set<Usuariogrupousuario> usuarioGrupoUsuarioList) {

		List<UsuarioGrupoUsuarioBean> usuarioGrupoUsuarioBeanList = new ArrayList<UsuarioGrupoUsuarioBean>();

		for (Iterator<Usuariogrupousuario> iterator = usuarioGrupoUsuarioList
				.iterator(); iterator.hasNext();) {
			UsuarioGrupoUsuarioBean usuarioGrupoUsuarioBean = transformarUsuarioGrupoUsuario(iterator
					.next());
			usuarioGrupoUsuarioBeanList.add(usuarioGrupoUsuarioBean);
		}
		return usuarioGrupoUsuarioBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarNivelCargo(
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.NivelCargo)
	 */
	public NivelCargoBean transformarNivelCargo(Nivelcargo nivelCargo) {

		if (nivelCargo == null || nivelCargo.getPkCodigoNivelcargo() == null) {
			return null;
		}

		NivelCargoBean nivelCargoBean = new NivelCargoBeanImpl();

		nivelCargoBean.setCodigo(nivelCargo.getPkCodigoNivelcargo());
		nivelCargoBean.setNombre(nivelCargo.getNombreNivelcargo());
		nivelCargoBean.setDescripcion(nivelCargo.getDescripcionNivelcargo());

		return nivelCargoBean;
	}

	public List<NivelCargoBean> transformarListaNivelCargo(
			List<Nivelcargo> listaNivelCargo) {

		List<NivelCargoBean> nivelesCargo = new ArrayList<NivelCargoBean>();

		for (Iterator<Nivelcargo> iterator = listaNivelCargo.iterator(); iterator
				.hasNext();) {
			NivelCargoBean nivelCargoBean = transformarNivelCargo(iterator
					.next());
			nivelesCargo.add(nivelCargoBean);
		}
		return nivelesCargo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarMenuBean(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Menu)
	 */
	@SuppressWarnings("unchecked")
	public MenuBean transformarMenu(Menu menu, Short nivel) {

		if (menu == null || menu.getPkCodigoMenu() == null)
			return null;

		MenuBean menuBean = new MenuBeanImpl();

		menuBean.setCodigo(menu.getPkCodigoMenu());
		menuBean.setNombre(menu.getNombreMenu());
		menuBean.setDescripcion(menu.getDescripcionMenu());
		menuBean.setOrdenMenu(menu.getOrdenMenu());
		menuBean.setNivelMenuBean(transformarNivelMenu(menu.getNivelmenu()));
		menuBean.setPrivilegioBean(transformarPrivilegioBasico(menu
				.getPrivilegio()));
		menuBean.setEstadoMenu(menu.getEstadoMenu());

		menuBean.setAccionBeanList(transformarListaAccion(menu.getAccions()));

		if (nivel != 0) {
			if (nivel != 1)
				menuBean.setMenuPadre(transformarMenu(menu.getMenu(), nivel));

			menuBean.setMenuBeanList(transformarListaMenu(menu.getMenus(),
					++nivel));
		}
		return menuBean;
	}

	public MenuBean transformarMenuMantenimiento(Menu menu) {

		if (menu == null || menu.getPkCodigoMenu() == null)
			return null;

		MenuBean menuBean = new MenuBeanImpl();

		menuBean.setCodigo(menu.getPkCodigoMenu());
		menuBean.setNombre(menu.getNombreMenu());
		menuBean.setDescripcion(menu.getDescripcionMenu());
		menuBean.setOrdenMenu(menu.getOrdenMenu());
		menuBean.setNivelMenuBean(transformarNivelMenu(menu.getNivelmenu()));
		menuBean.setPrivilegioBean(transformarPrivilegioBasico(menu
				.getPrivilegio()));

		menuBean.setAccionBeanList(transformarListaAccion(menu.getAccions()));

		menuBean.setMenuPadre(transformarMenuParaCombo(menu.getMenu()));

		return menuBean;
	}

	private MenuBean transformarMenuParaCombo(Menu menu) {
		if (menu == null || menu.getPkCodigoMenu() == null)
			return null;

		MenuBean menuBean = new MenuBeanImpl();

		menuBean.setCodigo(menu.getPkCodigoMenu());
		menuBean.setNombre(menu.getNombreMenu());

		return menuBean;
	}

	public List<MenuBean> transformarListaMenu(List<Menu> menuList, Short nivel) {

		List<MenuBean> menuBeanList = new ArrayList<MenuBean>();

		for (Iterator<Menu> iterator = menuList.iterator(); iterator.hasNext();) {
			Menu menu = iterator.next();
			if (menu.getNivelmenu().getNumeroNivelmenu().equals(nivel)
					|| nivel.toString().equals("0")) {
				MenuBean menuBean = transformarMenu(menu, nivel);
				menuBeanList.add(menuBean);
			}
		}
		return menuBeanList;
	}

	private List<MenuBean> transformarListaMenu(Set<Menu> menuList, Short nivel) {

		List<MenuBean> menuBeanList = new ArrayList<MenuBean>();

		for (Iterator<Menu> iterator = menuList.iterator(); iterator.hasNext();) {
			Menu menu = iterator.next();
			if (menu.getNivelmenu().getNumeroNivelmenu().equals(nivel)
					|| nivel.toString().equals("0")) {
				MenuBean menuBean = transformarMenu(menu, nivel);
				menuBeanList.add(menuBean);
			}
		}
		return menuBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPrivilegio(
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.Privilegio)
	 */
	@SuppressWarnings("unchecked")
	public PrivilegioBean transformarPrivilegio(Privilegio privilegio) {

		if (privilegio == null || privilegio.getPkCodigoPrivilegio() == null)
			return null;

		PrivilegioBean privilegioBean = new PrivilegioBeanImpl();

		privilegioBean.setCodigo(privilegio.getPkCodigoPrivilegio());
		privilegioBean.setNombre(privilegio.getNombrePrivilegio());
		privilegioBean.setDescripcion(privilegio.getDescripcionPrivilegio());
		privilegioBean
				.setEstadoPrivilegioBean(transformarEstadoPrivilegio(privilegio
						.getEstadoprivilegio()));

		Short nivel = 1;
		privilegioBean.setMenuList(transformarListaMenu(privilegio.getMenus(),
				nivel));

		return privilegioBean;
	}

	private PrivilegioBean transformarPrivilegioBasico(Privilegio privilegio) {

		if (privilegio == null || privilegio.getPkCodigoPrivilegio() == null)
			return null;

		PrivilegioBean privilegioBean = new PrivilegioBeanImpl();

		privilegioBean.setCodigo(privilegio.getPkCodigoPrivilegio());
		privilegioBean.setNombre(privilegio.getNombrePrivilegio());
		privilegioBean.setDescripcion(privilegio.getDescripcionPrivilegio());
		privilegioBean
				.setEstadoPrivilegioBean(transformarEstadoPrivilegio(privilegio
						.getEstadoprivilegio()));
		return privilegioBean;
	}

	public List<PrivilegioBean> transformarListaPrivilegios(
			List<Privilegio> listaPrivilegio) {

		List<PrivilegioBean> privilegiosBean = new ArrayList<PrivilegioBean>();

		for (Iterator<Privilegio> iterator = listaPrivilegio.iterator(); iterator
				.hasNext();) {

			Privilegio privilegio = iterator.next();
			PrivilegioBean privilegioBean = transformarPrivilegio(privilegio);
			if (privilegioBean != null)
				privilegiosBean.add(privilegioBean);
		}

		return privilegiosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarMenuBean(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Menu)
	 */
	@SuppressWarnings("unchecked")
	public NivelMenuBean transformarNivelMenu(Nivelmenu nivelmenu) {

		if (nivelmenu == null || nivelmenu.getPkCodigoNivelmenu() == null)
			return null;

		NivelMenuBean nivelMenuBean = new NivelMenuBeanImpl();

		nivelMenuBean.setCodigo(nivelmenu.getPkCodigoNivelmenu());
		nivelMenuBean.setNombre(nivelmenu.getNombreNivelmenu());
		nivelMenuBean.setNumeroNivelMenu(nivelmenu.getNumeroNivelmenu());

		return nivelMenuBean;
	}

	public List<NivelMenuBean> transformarListaNivelMenu(
			List<Nivelmenu> nivelesMenu) {

		List<NivelMenuBean> nivelBeans = new ArrayList<NivelMenuBean>();

		for (Iterator<Nivelmenu> iterator = nivelesMenu.iterator(); iterator
				.hasNext();) {

			Nivelmenu nivelMenu = iterator.next();
			NivelMenuBean nivelMenuBean = transformarNivelMenu(nivelMenu);
			if (nivelMenuBean != null)
				nivelBeans.add(nivelMenuBean);
		}

		return nivelBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUnidadCargo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadcargo)
	 */
	public UnidadCargoBean transformarUnidadCargo(Unidadcargo unidadCargo) {

		if (unidadCargo == null || unidadCargo.getPkCodigoUnidadcargo() == null) {
			return null;
		}

		UnidadCargoBean unidadCargoBean = new UnidadCargoBeanImpl();

		unidadCargoBean.setCodigo(unidadCargo.getPkCodigoUnidadcargo());
		unidadCargoBean
				.setUnidadBean(transformarUnidad(unidadCargo.getUnidad()));

		return unidadCargoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaUnidadCargo
	 * (java.util.Set)
	 */
	public List<UnidadBean> transformarListaUnidadCargo(
			Set<Unidadcargo> unidadCargoList) {

		List<UnidadBean> unidadBeanList = new ArrayList<UnidadBean>();

		for (Iterator<Unidadcargo> iterator = unidadCargoList.iterator(); iterator
				.hasNext();) {
			UnidadBean unidadBean = transformarUnidad(iterator.next()
					.getUnidad());
			unidadBeanList.add(unidadBean);
		}
		return unidadBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarDivisionCargo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Divisioncargo)
	 */
	public DivisionCargoBean transformarDivisionCargo(
			Divisioncargo divisionCargo) {

		if (divisionCargo == null
				|| divisionCargo.getPkCodigoDivisioncargo() == null)
			return null;

		DivisionCargoBean divisionCargoBean = new DivisionCargoBeanImpl();

		divisionCargoBean.setCodigo(divisionCargo.getPkCodigoDivisioncargo());
		divisionCargoBean.setDivisionBean(transformarDivision(divisionCargo
				.getDivision()));

		return divisionCargoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaDivisionCargo
	 * (java.util.Set)
	 */
	public List<DivisionBean> transformarListaDivisionCargo(
			Set<Divisioncargo> divisionCargoList) {

		List<DivisionBean> divisionCargoBeanList = new ArrayList<DivisionBean>();

		for (Iterator<Divisioncargo> iterator = divisionCargoList.iterator(); iterator
				.hasNext();) {
			DivisionBean divisionBean = transformarDivision(iterator.next()
					.getDivision());
			divisionCargoBeanList.add(divisionBean);
		}
		return divisionCargoBeanList;
	}

	private List<DivisionCargoBean> transformarListaDivisionCargoSeguridad(
			Set<Divisioncargo> divisionCargoList) {

		List<DivisionCargoBean> divisionCargoBeanList = new ArrayList<DivisionCargoBean>();

		for (Iterator<Divisioncargo> iterator = divisionCargoList.iterator(); iterator
				.hasNext();) {
			DivisionCargoBean divisionCargoBean = transformarDivisionCargo(iterator
					.next());
			divisionCargoBeanList.add(divisionCargoBean);
		}
		return divisionCargoBeanList;
	}

	private List<SociedadCargoBean> transformarListaSociedadCargoSeguridad(
			Set<Sociedadcargo> sociedadCargoList) {

		List<SociedadCargoBean> sociedadCargoBeanList = new ArrayList<SociedadCargoBean>();

		for (Iterator<Sociedadcargo> iterator = sociedadCargoList.iterator(); iterator
				.hasNext();) {
			SociedadCargoBean sociedadCargoBean = transformarSociedadCargo(iterator
					.next());
			sociedadCargoBeanList.add(sociedadCargoBean);
		}
		return sociedadCargoBeanList;
	}

	private List<UnidadCargoBean> transformarListaUnidadCargoSeguridad(
			Set<Unidadcargo> unidadCargoList) {

		List<UnidadCargoBean> unidadCargoBeanList = new ArrayList<UnidadCargoBean>();

		for (Iterator<Unidadcargo> iterator = unidadCargoList.iterator(); iterator
				.hasNext();) {
			UnidadCargoBean unidadCargoBean = transformarUnidadCargo(iterator
					.next());
			unidadCargoBeanList.add(unidadCargoBean);
		}
		return unidadCargoBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarSociedadCargo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedadcargo)
	 */
	public SociedadCargoBean transformarSociedadCargo(
			Sociedadcargo sociedadCargo) {

		if (sociedadCargo == null
				|| sociedadCargo.getPkCodigoSociedadcargo() == null)
			return null;

		SociedadCargoBean sociedadCargoBean = new SociedadCargoBeanImpl();

		sociedadCargoBean.setCodigo(sociedadCargo.getPkCodigoSociedadcargo());
		sociedadCargoBean.setSociedadBean(transformarSociedad(sociedadCargo
				.getSociedad()));

		return sociedadCargoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaSociedadCargo
	 * (java.util.Set)
	 */
	public List<SociedadBean> transformarListaSociedadCargo(
			Set<Sociedadcargo> sociedadCargoList) {

		List<SociedadBean> sociedadBeanList = new ArrayList<SociedadBean>();

		for (Iterator<Sociedadcargo> iterator = sociedadCargoList.iterator(); iterator
				.hasNext();) {
			SociedadBean sociedadBean = transformarSociedad(iterator.next()
					.getSociedad());
			sociedadBeanList.add(sociedadBean);
		}
		return sociedadBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarCargoBean(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Cargo)
	 */
	@SuppressWarnings("unchecked")
	public CargoBean transformarCargo(Cargo cargo) {

		if (cargo == null || cargo.getPkCodigoCargo() == null) {
			return null;
		}

		CargoBean cargoBean = new CargoBeanImpl();

		cargoBean.setCodigo(cargo.getPkCodigoCargo());
		cargoBean.setNombre(cargo.getNombreCargo());
		cargoBean.setDivisionBeanList(transformarListaDivisionCargo(cargo
				.getDivisioncargos()));
		cargoBean.setSociedadBeanList(transformarListaSociedadCargo(cargo
				.getSociedadcargos()));
		cargoBean.setUnidadBeanList(transformarListaUnidadCargo(cargo
				.getUnidadcargos()));

		cargoBean
				.setNivelCargoBean(transformarNivelCargo(cargo.getNivelcargo()));

		if (cargo.getDivisioncargos() != null
				&& cargo.getDivisioncargos().size() > 0) {
			cargoBean
					.setDivisionCargoBean(transformarListaDivisionCargoSeguridad(
							cargo.getDivisioncargos()).get(0));
		}
		if (cargo.getSociedadcargos() != null
				&& cargo.getSociedadcargos().size() > 0) {
			cargoBean
					.setSociedadCargoBean(transformarListaSociedadCargoSeguridad(
							cargo.getSociedadcargos()).get(0));
		}
		if (cargo.getUnidadcargos() != null
				&& cargo.getUnidadcargos().size() > 0) {
			cargoBean.setUnidadCargoBean(transformarListaUnidadCargoSeguridad(
					cargo.getUnidadcargos()).get(0));
		}
		return cargoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOpcion(pe.com
	 * .pacasmayo.sgcp.persistencia.dataObjects.Opcion)
	 */
	public OpcionBean transformarOpcion(Opcion opcion) {

		if (opcion == null || opcion.getPkCodigoOpcion() == null) {
			return null;
		}

		OpcionBean opcionBean = new OpcionBeanImpl();

		opcionBean.setCodigo(opcion.getPkCodigoOpcion());
		opcionBean.setNombre(opcion.getNombreOpcion());

		return opcionBean;
	}

	public List<OpcionBean> transformarListaOpcionParaCombo(
			List<Opcion> opcionList) {

		List<OpcionBean> opcionBeanList = new ArrayList<OpcionBean>();

		for (Iterator<Opcion> iterator = opcionList.iterator(); iterator
				.hasNext();) {
			OpcionBean opcionBean = transformarOpcion(iterator.next());
			opcionBeanList.add(opcionBean);
		}

		return opcionBeanList;
	}

	public OpcionAccionBean transformarOpcionAccion(Opcionaccion opcionaccion) {

		if (opcionaccion == null
				|| opcionaccion.getPkCodigoOpcionaccion() == null) {
			return null;
		}

		OpcionAccionBean opcionAccionBean = new OpcionAccionBeanImpl();

		if (opcionaccion.getAccion() != null)
			opcionAccionBean.setAccionBean(transformarAccion(opcionaccion
					.getAccion()));

		opcionAccionBean.setCodigo(opcionaccion.getPkCodigoOpcionaccion());

		if (opcionaccion.getOpcion() != null)
			opcionAccionBean.setOpcionBean(transformarOpcion(opcionaccion
					.getOpcion()));

		return opcionAccionBean;
	}

	public List<OpcionAccionBean> transformarListaOpcionAccionParaCombo(
			List<Opcionaccion> opcionaccionList) {

		List<OpcionAccionBean> opcionAccionBeanList = new ArrayList<OpcionAccionBean>();

		for (Iterator<Opcionaccion> iterator = opcionaccionList.iterator(); iterator
				.hasNext();) {
			OpcionAccionBean opcionAccionBean = transformarOpcionAccion(iterator
					.next());
			opcionAccionBeanList.add(opcionAccionBean);
		}

		return opcionAccionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarCargoBean(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Cargo)
	 */
	public AccionBean transformarAccion(Accion accion) {

		if (accion == null || accion.getPkCodigoAccion() == null) {
			return null;
		}

		AccionBean accionBean = new AccionBeanImpl();

		accionBean.setCodigo(accion.getPkCodigoAccion());
		accionBean.setNombre(accion.getNombreAccion());
		accionBean.setPresentacionAccion(accion.getPresentacionAccion());
		accionBean.setUrlAccion(accion.getUrlAccion());

		List<OpcionBean> opcionBeanList = new ArrayList<OpcionBean>();

		Set<Opcionaccion> opcionAccionList = accion.getOpcionaccions();

		for (Iterator<Opcionaccion> iterator = opcionAccionList.iterator(); iterator
				.hasNext();) {
			OpcionBean opcionBean = transformarOpcion(iterator.next()
					.getOpcion());
			opcionBeanList.add(opcionBean);
		}

		accionBean.setOpcionesList(opcionBeanList);

		return accionBean;
	}

	private List<AccionBean> transformarListaAccion(Set<Accion> accionList) {

		List<AccionBean> accionBeanList = new ArrayList<AccionBean>();

		for (Iterator<Accion> iterator = accionList.iterator(); iterator
				.hasNext();) {
			AccionBean accionBean = transformarAccion(iterator.next());
			accionBeanList.add(accionBean);
		}
		return accionBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarCargoBean(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Cargo)
	 */
	public GrupoUsuarioPrivilegioBean transformarGrupoUsuarioPrivilegio(
			Grupousuarioprivilegio grupousuarioprivilegio) {

		if (grupousuarioprivilegio == null
				|| grupousuarioprivilegio.getPkCodigoGrupousuarioprivilegi() == null) {
			return null;
		}

		GrupoUsuarioPrivilegioBean grupoUsuarioPrivilegioBean = new GrupoUsuarioPrivilegioBeanImpl();

		grupoUsuarioPrivilegioBean.setCodigo(grupousuarioprivilegio
				.getPkCodigoGrupousuarioprivilegi());
		grupoUsuarioPrivilegioBean.setGrupoUsuarioBean(transformarGrupoUsuario(
				grupousuarioprivilegio.getGrupousuario(), false));
		grupoUsuarioPrivilegioBean
				.setPrivilegioBean(transformarPrivilegio(grupousuarioprivilegio
						.getPrivilegio()));

		return grupoUsuarioPrivilegioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarConjuntoGrupoUsuarioPrivilegioALista(java.util.Set)
	 */
	public List<GrupoUsuarioPrivilegioBean> transformarListaGrupoUsuarioPrivilegio(
			Set<Grupousuarioprivilegio> grupoUsuarioPrivilegioList) {

		List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegioBeanList = new ArrayList<GrupoUsuarioPrivilegioBean>();

		for (Iterator<Grupousuarioprivilegio> iterator = grupoUsuarioPrivilegioList
				.iterator(); iterator.hasNext();) {
			GrupoUsuarioPrivilegioBean grupoUsuarioPrivilegioBean = transformarGrupoUsuarioPrivilegio(iterator
					.next());
			grupoUsuarioPrivilegioBeanList.add(grupoUsuarioPrivilegioBean);
		}
		return grupoUsuarioPrivilegioBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaGrupoUsuarioPrivilegio(java.util.List)
	 */
	public List<GrupoUsuarioPrivilegioBean> transformarListaGrupoUsuarioPrivilegio(
			List<Grupousuarioprivilegio> grupoUsuarioPrivilegioList) {

		List<GrupoUsuarioPrivilegioBean> grupoUsuarioPrivilegioBeanList = new ArrayList<GrupoUsuarioPrivilegioBean>();

		for (Iterator<Grupousuarioprivilegio> iterator = grupoUsuarioPrivilegioList
				.iterator(); iterator.hasNext();) {
			Grupousuarioprivilegio grupousuarioprivilegio = iterator.next();
			grupoUsuarioPrivilegioBeanList
					.add(transformarGrupoUsuarioPrivilegio(grupousuarioprivilegio));
		}
		return grupoUsuarioPrivilegioBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUsuario(pe.
	 * com.pacasmayo.sgcp.persistencia.dataObjects.Usuario)
	 */
	@SuppressWarnings("unchecked")
	public UsuarioBean transformarUsuario(Usuario usuario) {

		if (usuario == null || usuario.getPkCodigoUsuario() == null)
			return null;

		UsuarioBean usuarioBean = new UsuarioBeanImpl();

		usuarioBean.setCodigo(usuario.getPkCodigoUsuario());
		usuarioBean.setLogin(usuario.getLoginUsuario());
		usuarioBean.setPassword(usuario.getPasswordUsuario());
		usuarioBean.setPersona(transformarPersona(usuario.getPersona()));
		usuarioBean.setEstadoUsuario(transformarEstadoUsuario(usuario
				.getEstadousuario()));

		Set<GrupoUsuarioBean> grupoUsuarioBeanList = new HashSet<GrupoUsuarioBean>();

		List<UsuarioGrupoUsuarioBean> usuarioGrupoUsuarioList = transformarListaUsuarioGrupoUsuario(usuario
				.getUsuariogrupousuarios());

		for (Iterator<UsuarioGrupoUsuarioBean> iterator = usuarioGrupoUsuarioList
				.iterator(); iterator.hasNext();) {
			UsuarioGrupoUsuarioBean grupo = (UsuarioGrupoUsuarioBean) iterator
					.next();
			grupoUsuarioBeanList.add(grupo.getGrupoUsuarioBean());
			usuarioBean.setGrupoUsuario(grupo.getGrupoUsuarioBean());
		}

		usuarioBean.setGrupoUsuarios(grupoUsuarioBeanList);

		return usuarioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoPuestoTrabajo(java.util.List)
	 */
	public List<EstadoPuestoTrabajoBean> transformarListaEstadoPuestoTrabajo(
			List<Estadopuestotrabajo> estadoPuestoTrabajos) {

		List<EstadoPuestoTrabajoBean> estadoPuestoTrabajoBeans = new ArrayList<EstadoPuestoTrabajoBean>();

		for (Iterator<Estadopuestotrabajo> iterator = estadoPuestoTrabajos
				.iterator(); iterator.hasNext();) {

			Estadopuestotrabajo estadoPuestoTrabajo = iterator.next();
			EstadoPuestoTrabajoBean estadoPuestoTrabajoImplBean = transformarEstadoPuestoTrabajo(estadoPuestoTrabajo);
			estadoPuestoTrabajoBeans.add(estadoPuestoTrabajoImplBean);
		}
		return estadoPuestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadopuestotrabajo)
	 */
	public EstadoPuestoTrabajoBean transformarEstadoPuestoTrabajo(
			Estadopuestotrabajo estadoPuestoTrabajo) {

		EstadoPuestoTrabajoBeanImpl estadoPuestoTrabajoBeanImpl = new EstadoPuestoTrabajoBeanImpl();

		estadoPuestoTrabajoBeanImpl.setCodigo(estadoPuestoTrabajo
				.getPkCodigoEstadopuestotrabajo());
		estadoPuestoTrabajoBeanImpl.setNombre(estadoPuestoTrabajo
				.getNombreEstadopuestotrabajo());

		return estadoPuestoTrabajoBeanImpl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarFactordosificacion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacion)
	 */
	@SuppressWarnings("unchecked")
	public FactorDosificacionBean transformarFactordosificacion(
			Factordosificacion factorDosificacion) {
		if (factorDosificacion == null
				|| factorDosificacion.getPkCodigoFactordosificacion() == null) {
			return null;
		}

		FactorDosificacionBean factorDosificacionBean = new FactorDosificacionBeanImpl();
		factorDosificacionBean.setCodigo(factorDosificacion
				.getPkCodigoFactordosificacion());

		if (factorDosificacion.getUnidadmedida() != null) {
			factorDosificacionBean
					.setUnidad(transformarUnidadMedida(factorDosificacion
							.getUnidadmedida()));
		}

		if (factorDosificacion.getFactordosificacionregistromensus() != null) {
			factorDosificacionBean
					.setFactorDosificacionRegistroMensual(transformarListaFactorDosificacionRegistroMensual(factorDosificacion
							.getFactordosificacionregistromensus()));
			if (factorDosificacionBean.getFactorDosificacionRegistroMensual()[0] != null) {
				Double proyeccion = new Double(0.00);
				for (int i = 0; i < factorDosificacionBean
						.getFactorDosificacionRegistroMensual().length; i++) {
					proyeccion = proyeccion
							+ factorDosificacionBean
									.getFactorDosificacionRegistroMensual()[i]
									.getCantidadRegistro();
				}
				proyeccion = proyeccion / 12;
				factorDosificacionBean.setProyeccion(nf.format(proyeccion));
			}
		}

		if (factorDosificacion.getDosificacions() != null) {
			factorDosificacionBean
					.setDosificaciones(transformarListaDosificacion(factorDosificacion
							.getDosificacions()));
		}
		if (factorDosificacion.getHojaruta() != null) {
			factorDosificacionBean
					.setHojaRuta(transformarHojaRutaSinComponentes(factorDosificacion
							.getHojaruta()));
		}

		if (factorDosificacion.getComponente() != null)
			factorDosificacionBean
					.setComponente(transformarComponenteSinFactorDosificacion(factorDosificacion
							.getComponente()));

		return factorDosificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaFactordosificacion(java.util.List)
	 */
	public List<FactorDosificacionBean> transformarListaFactordosificacion(
			List<Factordosificacion> factorDosificacionBeans) {

		List<FactorDosificacionBean> factorDosificacionImplBeans = new ArrayList<FactorDosificacionBean>();

		for (Iterator<Factordosificacion> iterator = factorDosificacionBeans
				.iterator(); iterator.hasNext();) {

			Factordosificacion factorDosificacion = iterator.next();
			FactorDosificacionBean factorDosificacionImplBean = transformarFactordosificacion(factorDosificacion);

			factorDosificacionImplBeans.add(factorDosificacionImplBean);
		}
		return factorDosificacionImplBeans;
	}

	private List<FactorDosificacionBean> transformarListaFactordosificacionSinHojaRuta(
			Set<Factordosificacion> factorDosificacionBeans) {

		List<FactorDosificacionBean> factorDosificacionImplBeans = new ArrayList<FactorDosificacionBean>();

		for (Iterator<Factordosificacion> iterator = factorDosificacionBeans
				.iterator(); iterator.hasNext();) {

			Factordosificacion factorDosificacion = iterator.next();
			FactorDosificacionBean factorDosificacionImplBean = transformarFactordosificacionSinHojaRuta(factorDosificacion);

			factorDosificacionImplBeans.add(factorDosificacionImplBean);
		}
		return factorDosificacionImplBeans;
	}

	private FactorDosificacionBean transformarFactordosificacionSinHojaRuta(
			Factordosificacion factorDosificacion) {
		if (factorDosificacion == null
				|| factorDosificacion.getPkCodigoFactordosificacion() == null) {
			return null;
		}

		FactorDosificacionBean factorDosificacionBean = new FactorDosificacionBeanImpl();
		factorDosificacionBean.setCodigo(factorDosificacion
				.getPkCodigoFactordosificacion());

		if (factorDosificacion.getUnidadmedida() != null) {
			factorDosificacionBean
					.setUnidad(transformarUnidadMedida(factorDosificacion
							.getUnidadmedida()));
		}

		if (factorDosificacion.getFactordosificacionregistromensus() != null) {
			factorDosificacionBean
					.setFactorDosificacionRegistroMensual(transformarListaFactorDosificacionRegistroMensual(factorDosificacion
							.getFactordosificacionregistromensus()));
			if (factorDosificacionBean.getFactorDosificacionRegistroMensual()[0] != null) {
				Double proyeccion = new Double(0.00);
				for (int i = 0; i < factorDosificacionBean
						.getFactorDosificacionRegistroMensual().length; i++) {
					proyeccion = proyeccion
							+ factorDosificacionBean
									.getFactorDosificacionRegistroMensual()[i]
									.getCantidadRegistro();
				}
				proyeccion = proyeccion / 12;
				factorDosificacionBean.setProyeccion(nf.format(proyeccion));
			}
		}

		if (factorDosificacion.getComponente() != null)
			factorDosificacionBean
					.setComponente(transformarComponenteSinFactorDosificacion(factorDosificacion
							.getComponente()));

		return factorDosificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaFactordosificacion(java.util.Set)
	 */
	public List<FactorDosificacionBean> transformarListaFactordosificacion(
			Set<Factordosificacion> factorDosificacionBeans) {

		List<FactorDosificacionBean> factorDosificacionImplBeans = new ArrayList<FactorDosificacionBean>();

		for (Iterator<Factordosificacion> iterator = factorDosificacionBeans
				.iterator(); iterator.hasNext();) {

			Factordosificacion factorDosificacion = iterator.next();
			FactorDosificacionBean factorDosificacionImplBean = transformarFactordosificacion(factorDosificacion);

			factorDosificacionImplBeans.add(factorDosificacionImplBean);
		}
		return factorDosificacionImplBeans;
	}

	private List<DosificacionBean> transformarListaDosificacion(
			Set<Dosificacion> dosificacionBeans) {

		List<DosificacionBean> dosificacionImplBeans = new ArrayList<DosificacionBean>();

		for (Iterator<Dosificacion> iterator = dosificacionBeans.iterator(); iterator
				.hasNext();) {

			Dosificacion dosificacion = iterator.next();
			DosificacionBean dosificacionImplBean = transformarDosificacion(dosificacion);

			dosificacionImplBeans.add(dosificacionImplBean);
		}
		return dosificacionImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarFactordosificacionregistromensu
	 * (pe.com.pacasmayo.sgcp.persistencia
	 * .dataObjects.Factordosificacionregistromensu)
	 */
	public FactorDosificacionRegistroMensualBean transformarFactordosificacionregistromensu(
			Factordosificacionregistromensu factorDosificacionRegistroMensual) {
		if (factorDosificacionRegistroMensual == null
				|| factorDosificacionRegistroMensual
						.getPkCodigoFactordosificacionreg() == null) {
			return null;
		}

		FactorDosificacionRegistroMensualBean factorDosificacionRegistroMensualBean = new FactorDosificacionRegistroMensualBeanImpl();

		factorDosificacionRegistroMensualBean
				.setCodigo(factorDosificacionRegistroMensual
						.getPkCodigoFactordosificacionreg());

		factorDosificacionRegistroMensualBean
				.setAnno(factorDosificacionRegistroMensual
						.getAnnoFactordosificacionregistro());

		factorDosificacionRegistroMensualBean
				.setCantidadRegistro(factorDosificacionRegistroMensual
						.getCantidadFactordosificacionregi());

		factorDosificacionRegistroMensualBean
				.setMes(factorDosificacionRegistroMensual
						.getMesFactordosificacionregistrom());

		return factorDosificacionRegistroMensualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaFactordosificacionregistromensu(java.util.List)
	 */
	public List<FactorDosificacionRegistroMensualBean> transformarListaFactordosificacionregistromensu(
			List<Factordosificacionregistromensu> factorDosificacionRegistroMensualBeans) {

		List<FactorDosificacionRegistroMensualBean> factorDosificacionRegistroMensualImplBeans = new ArrayList<FactorDosificacionRegistroMensualBean>();

		for (Iterator<Factordosificacionregistromensu> iterator = factorDosificacionRegistroMensualBeans
				.iterator(); iterator.hasNext();) {

			Factordosificacionregistromensu factorDosificacionRegistroMensual = iterator
					.next();
			FactorDosificacionRegistroMensualBean factorDosificacionRegistroMensualImplBean = transformarFactordosificacionregistromensu(factorDosificacionRegistroMensual);

			factorDosificacionRegistroMensualImplBeans
					.add(factorDosificacionRegistroMensualImplBean);
		}
		return factorDosificacionRegistroMensualImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaFactorDosificacionRegistroMensual(java.util.Set)
	 */
	public FactorDosificacionRegistroMensualBean[] transformarListaFactorDosificacionRegistroMensual(
			Set<Factordosificacionregistromensu> factorDosificacionRegistroMensualBeans) {

		FactorDosificacionRegistroMensualBean[] factorDosificacionRegistroMensualImplBeans = new FactorDosificacionRegistroMensualBean[12];

		for (Iterator<Factordosificacionregistromensu> iterator = factorDosificacionRegistroMensualBeans
				.iterator(); iterator.hasNext();) {

			Factordosificacionregistromensu factorDosificacionRegistroMensual = iterator
					.next();
			FactorDosificacionRegistroMensualBean factorDosificacionRegistroMensualBean = transformarFactordosificacionregistromensu(factorDosificacionRegistroMensual);

			factorDosificacionRegistroMensualImplBeans[factorDosificacionRegistroMensualBean
					.getMes() - 1] = factorDosificacionRegistroMensualBean;
		}
		return factorDosificacionRegistroMensualImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarHojaRuta(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Hojaruta)
	 */
	public HojaRutaBean transformarHojaRuta(Hojaruta hojaRuta) {
		if (hojaRuta == null || hojaRuta.getPkCodigoHojaruta() == null) {
			return null;
		}

		HojaRutaBean hojaRutaBean = new HojaRutaBeanImpl();
		hojaRutaBean.setCodigo(hojaRuta.getPkCodigoHojaruta());

		hojaRutaBean.setNombre(hojaRuta.getNombreHojaruta());

		hojaRutaBean.setEstadoHojaRuta(transformarEstadoHojaRuta(hojaRuta
				.getEstadohojaruta()));

		if (hojaRuta.getProduccion() != null)
			hojaRutaBean.setProduccion(transformarProduccion(hojaRuta
					.getProduccion()));

		if (hojaRuta.getProduccion() != null
				&& hojaRuta.getProduccion().getProducto() != null)
			hojaRutaBean.setProducto(transformarProducto(hojaRuta
					.getProduccion().getProducto()));
		if (hojaRuta.getProduccion() != null)
			hojaRutaBean.setProduccion(transformarProduccion(hojaRuta
					.getProduccion()));

		if (hojaRuta.getHojarutacomponentes() != null)
			hojaRutaBean
					.setHojaRutaComponentes(transformarListaHojaRutaComponente(hojaRuta
							.getHojarutacomponentes()));

		return hojaRutaBean;
	}

	public HojaRutaBean transformarHojaRutaParaConsultaOrdenProduccion(
			Hojaruta hojaRuta) {
		if (hojaRuta == null || hojaRuta.getPkCodigoHojaruta() == null) {
			return null;
		}

		HojaRutaBean hojaRutaBean = new HojaRutaBeanImpl();
		hojaRutaBean.setCodigo(hojaRuta.getPkCodigoHojaruta());
		hojaRutaBean.setNombre(hojaRuta.getNombreHojaruta());
		hojaRutaBean.setEstadoHojaRuta(transformarEstadoHojaRuta(hojaRuta
				.getEstadohojaruta()));

		if (hojaRuta.getProduccion() != null)
			hojaRutaBean.setProduccion(transformarProduccion(hojaRuta
					.getProduccion()));

		return hojaRutaBean;
	}

	private HojaRutaBean transformarHojaRutaSinComponentes(Hojaruta hojaRuta) {
		if (hojaRuta == null || hojaRuta.getPkCodigoHojaruta() == null) {
			return null;
		}

		HojaRutaBean hojaRutaBean = new HojaRutaBeanImpl();
		hojaRutaBean.setCodigo(hojaRuta.getPkCodigoHojaruta());

		hojaRutaBean.setNombre(hojaRuta.getNombreHojaruta());

		hojaRutaBean.setEstadoHojaRuta(transformarEstadoHojaRuta(hojaRuta
				.getEstadohojaruta()));

		if (hojaRuta.getProduccion() != null)
			hojaRutaBean.setProduccion(transformarProduccion(hojaRuta
					.getProduccion()));

		if (hojaRuta.getProduccion() != null
				&& hojaRuta.getProduccion().getProducto() != null)
			hojaRutaBean.setProducto(transformarProducto(hojaRuta
					.getProduccion().getProducto()));
		if (hojaRuta.getProduccion() != null)
			hojaRutaBean.setProduccion(transformarProduccion(hojaRuta
					.getProduccion()));

		return hojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaHojaRuta
	 * (java.util.List)
	 */
	public List<HojaRutaBean> transformarListaHojaRuta(
			List<Hojaruta> listaHojaRuta) {

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

		for (Iterator<Hojaruta> iterator = listaHojaRuta.iterator(); iterator
				.hasNext();) {

			Hojaruta hojaRuta = iterator.next();
			HojaRutaBean hojaRutaBean = transformarHojaRuta(hojaRuta);

			listaHojaRutaBean.add(hojaRutaBean);
		}

		return listaHojaRutaBean;
	}

	public List<HojaRutaBean> transformarListaHojaRutaParaCombo(
			List<Hojaruta> listaHojaRuta) {

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

		for (Iterator<Hojaruta> iterator = listaHojaRuta.iterator(); iterator
				.hasNext();) {

			Hojaruta hojaRuta = iterator.next();
			HojaRutaBean hojaRutaBean = transformarHojaRutaParaCombos(hojaRuta);

			listaHojaRutaBean.add(hojaRutaBean);
		}

		return listaHojaRutaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarHojaRutaComponente
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Hojarutacomponente)
	 */
	public HojaRutaComponenteBean transformarHojaRutaComponente(
			Hojarutacomponente hojaRutaComponente) {
		if (hojaRutaComponente == null
				|| hojaRutaComponente.getPkCodigoHojarutacomponente() == null) {
			return null;
		}

		HojaRutaComponenteBean hojaRutaComponenteBean = new HojaRutaComponenteBeanImpl();

		hojaRutaComponenteBean.setCodigoHojaRutaComponente(hojaRutaComponente
				.getPkCodigoHojarutacomponente());

		hojaRutaComponenteBean
				.setComponente(transformarComponente(hojaRutaComponente
						.getComponente()));

		hojaRutaComponenteBean
				.setTipoComponente(transformarTipoComponente(hojaRutaComponente
						.getTipocomponente()));

		return hojaRutaComponenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaHojaRutaComponente(java.util.List)
	 */
	public List<HojaRutaComponenteBean> transformarListaHojaRutaComponente(
			List<Hojarutacomponente> listaHojaRutaComponente) {

		List<HojaRutaComponenteBean> listaHojaRutaComponenteBean = new ArrayList<HojaRutaComponenteBean>();

		for (Iterator<Hojarutacomponente> iterator = listaHojaRutaComponente
				.iterator(); iterator.hasNext();) {

			Hojarutacomponente hojaRutaComponente = iterator.next();
			HojaRutaComponenteBean hojaRutaComponenteBean = transformarHojaRutaComponente(hojaRutaComponente);

			listaHojaRutaComponenteBean.add(hojaRutaComponenteBean);
		}

		return listaHojaRutaComponenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaHojaRutaComponente(java.util.Set)
	 */
	public List<HojaRutaComponenteBean> transformarListaHojaRutaComponente(
			Set<Hojarutacomponente> listaHojaRutaComponente) {

		List<HojaRutaComponenteBean> listaHojaRutaComponenteBean = new ArrayList<HojaRutaComponenteBean>();

		for (Iterator<Hojarutacomponente> iterator = listaHojaRutaComponente
				.iterator(); iterator.hasNext();) {

			Hojarutacomponente hojaRutaComponente = iterator.next();
			HojaRutaComponenteBean hojaRutaComponenteBean = transformarHojaRutaComponente(hojaRutaComponente);

			listaHojaRutaComponenteBean.add(hojaRutaComponenteBean);
		}

		return listaHojaRutaComponenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarLineaNegocio
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio)
	 */
	public LineaNegocioBean transformarLineaNegocio(Lineanegocio lineaNegocio) {
		if (lineaNegocio == null
				|| lineaNegocio.getPkCodigoLineanegocio() == null) {
			return null;
		}

		LineaNegocioBean lineaNegocioBean = new LineaNegocioBeanImpl();

		lineaNegocioBean.setCodigo(lineaNegocio.getPkCodigoLineanegocio());

		if (lineaNegocio.getCodigoSapLineanegocio() != null)
			lineaNegocioBean.setCodigoSAP(lineaNegocio
					.getCodigoSapLineanegocio().trim());

		if (lineaNegocio.getDescripcionLineanegocio() != null)
			lineaNegocioBean.setDescripcion(lineaNegocio
					.getDescripcionLineanegocio().trim());

		if (lineaNegocio.getNombreLineanegocio() != null)
			lineaNegocioBean.setNombre(lineaNegocio.getNombreLineanegocio()
					.trim());

		lineaNegocioBean.setUnidad(transformarUnidad(lineaNegocio.getUnidad()));

		return lineaNegocioBean;
	}

	public LineaNegocioBean transformarLineaNegocioBasico(
			Lineanegocio lineaNegocio) {
		if (lineaNegocio == null
				|| lineaNegocio.getPkCodigoLineanegocio() == null) {
			return null;
		}

		LineaNegocioBean lineaNegocioBean = new LineaNegocioBeanImpl();

		lineaNegocioBean.setCodigo(lineaNegocio.getPkCodigoLineanegocio());

		if (lineaNegocio.getCodigoSapLineanegocio() != null)
			lineaNegocioBean.setCodigoSAP(lineaNegocio
					.getCodigoSapLineanegocio().trim());

		if (lineaNegocio.getDescripcionLineanegocio() != null)
			lineaNegocioBean.setDescripcion(lineaNegocio
					.getDescripcionLineanegocio().trim());

		if (lineaNegocio.getNombreLineanegocio() != null)
			lineaNegocioBean.setNombre(lineaNegocio.getNombreLineanegocio()
					.trim());

		return lineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaLineaNegocio
	 * (java.util.List)
	 */
	public List<LineaNegocioBean> transformarListaLineaNegocio(
			List<Lineanegocio> listaLineaNegocio) {

		List<LineaNegocioBean> listaLineaNegocioBean = new ArrayList<LineaNegocioBean>();

		for (Iterator<Lineanegocio> iterator = listaLineaNegocio.iterator(); iterator
				.hasNext();) {

			Lineanegocio lineaNegocio = iterator.next();
			LineaNegocioBean lineaNegocioBean = transformarLineaNegocio(lineaNegocio);
			listaLineaNegocioBean.add(lineaNegocioBean);
		}

		return listaLineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaLineaNegocioBasico(java.util.List)
	 */
	public List<LineaNegocioBean> transformarListaLineaNegocioBasico(
			List<Lineanegocio> listaLineaNegocio) {

		List<LineaNegocioBean> listaLineaNegocioBean = new ArrayList<LineaNegocioBean>();

		for (Iterator<Lineanegocio> iterator = listaLineaNegocio.iterator(); iterator
				.hasNext();) {

			Lineanegocio lineaNegocio = iterator.next();
			LineaNegocioBean lineaNegocioBean = transformarLineaNegocioBasico(lineaNegocio);
			listaLineaNegocioBean.add(lineaNegocioBean);
		}

		return listaLineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOperacion(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Operacion)
	 */
	public OperacionBean transformarOperacion(Operacion operacion) {
		if (operacion == null || operacion.getPkCodigoOperacion() == null) {
			return null;
		}

		OperacionBean operacionBean = new OperacionBeanImpl();

		operacionBean.setActividad(transformarActividad(operacion
				.getActividad()));
		operacionBean.setCodigo(operacion.getPkCodigoOperacion());
		operacionBean.setHojaRuta(transformarHojaRuta(operacion.getHojaruta()));
		operacionBean.setNombre(operacion.getNombreOperacion());
		operacionBean.setOrdenEjecucion(operacion.getOrdenEjecucionOperacion());
		operacionBean.setPuestoTrabajo(transformarPuestoTrabajo(operacion
				.getPuestotrabajo()));

		operacionBean
				.setListaOperacionRecursos(transformarListaOperacionRecurso(operacion
						.getOperacionrecursos()));

		operacionBean
				.setListaOperacionComponentes(transformarListaOperacionComponente(operacion
						.getOperacioncomponentes()));

		return operacionBean;
	}

	public OperacionBean transformarOperacionParaPlanAnual(Operacion operacion) {
		if (operacion == null || operacion.getPkCodigoOperacion() == null) {
			return null;
		}

		OperacionBean operacionBean = new OperacionBeanImpl();

		operacionBean.setCodigo(operacion.getPkCodigoOperacion());
		operacionBean.setNombre(operacion.getNombreOperacion());
		operacionBean.setPuestoTrabajo(transformarPuestoTrabajo(operacion
				.getPuestotrabajo()));

		operacionBean.setActividad(transformarActividadParaConsulta(operacion
				.getActividad()));

		return operacionBean;
	}

	private OperacionBean transformarOperacion(Operacion operacion,
			boolean recurrente) {
		if (operacion == null || operacion.getPkCodigoOperacion() == null) {
			return null;
		}

		OperacionBean operacionBean = new OperacionBeanImpl();

		operacionBean.setActividad(transformarActividad(operacion
				.getActividad()));
		operacionBean.setCodigo(operacion.getPkCodigoOperacion());
		operacionBean.setHojaRuta(transformarHojaRuta(operacion.getHojaruta()));
		operacionBean.setNombre(operacion.getNombreOperacion());
		operacionBean.setOrdenEjecucion(operacion.getOrdenEjecucionOperacion());
		operacionBean.setPuestoTrabajo(transformarPuestoTrabajo(operacion
				.getPuestotrabajo()));

		if (recurrente) {
			operacionBean
					.setListaOperacionComponentes(transformarListaOperacionComponente(operacion
							.getOperacioncomponentes()));
		}
		return operacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaOperacion
	 * (java.util.List)
	 */
	public List<OperacionBean> transformarListaOperacion(
			List<Operacion> listaOperacion) {

		List<OperacionBean> listaOperacionBean = new ArrayList<OperacionBean>();

		for (Iterator<Operacion> iterator = listaOperacion.iterator(); iterator
				.hasNext();) {

			Operacion operacion = iterator.next();
			OperacionBean operacionBean = transformarOperacion(operacion);
			listaOperacionBean.add(operacionBean);
		}

		return listaOperacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaOperacion
	 * (java.util.List)
	 */
	public List<OperacionBean> transformarListaOperacionModificarHojaRuta(
			List<Operacion> listaOperacion) {

		List<OperacionBean> listaOperacionBean = new ArrayList<OperacionBean>();

		for (Iterator<Operacion> iterator = listaOperacion.iterator(); iterator
				.hasNext();) {

			Operacion operacion = iterator.next();
			OperacionBean operacionBean = transformarOperacionModificarHojaRuta(operacion);
			listaOperacionBean.add(operacionBean);
		}

		return listaOperacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOperacion(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Operacion)
	 */
	public OperacionBean transformarOperacionModificarHojaRuta(
			Operacion operacion) {
		if (operacion == null || operacion.getPkCodigoOperacion() == null) {
			return null;
		}

		OperacionBean operacionBean = new OperacionBeanImpl();

		operacionBean.setActividad(transformarActividad(operacion
				.getActividad()));
		operacionBean.setCodigo(operacion.getPkCodigoOperacion());
		operacionBean.setNombre(operacion.getNombreOperacion());
		operacionBean.setOrdenEjecucion(operacion.getOrdenEjecucionOperacion());
		operacionBean.setPuestoTrabajo(transformarPuestoTrabajo(operacion
				.getPuestotrabajo()));
		operacionBean.setHojaRuta(transformarHojaRutaParaCombos(operacion
				.getHojaruta()));

		operacionBean
				.setListaOperacionRecursos(transformarListaOperacionRecurso(operacion
						.getOperacionrecursos()));

		operacionBean
				.setListaOperacionComponentes(transformarListaOperacionComponente(operacion
						.getOperacioncomponentes()));

		return operacionBean;
	}

	public List<OperacionBean> transformarListaOperacionParaPlanAnual(
			List<Operacion> listaOperacion) {

		List<OperacionBean> listaOperacionBean = new ArrayList<OperacionBean>();

		for (Iterator<Operacion> iterator = listaOperacion.iterator(); iterator
				.hasNext();) {

			Operacion operacion = iterator.next();
			OperacionBean operacionBean = transformarOperacionParaPlanAnual(operacion);
			listaOperacionBean.add(operacionBean);
		}

		return listaOperacionBean;
	}

	public List<OperacionBean> transformarListaOperacionSet(
			Set<Operacion> listaOperacion) {

		List<OperacionBean> listaOperacionBean = new ArrayList<OperacionBean>();

		for (Iterator<Operacion> iterator = listaOperacion.iterator(); iterator
				.hasNext();) {

			Operacion operacion = iterator.next();
			OperacionBean operacionBean = transformarOperacion(operacion);
			listaOperacionBean.add(operacionBean);
		}

		return listaOperacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOperacionComponente
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacioncomponente)
	 */
	public OperacionComponenteBean transformarOperacionComponente(
			Operacioncomponente operacionComponente) {

		if (operacionComponente == null
				|| operacionComponente.getPkCodigoOperacioncomponente() == null)
			return null;

		OperacionComponenteBean operacionComponenteBean = new OperacionComponenteBeanImpl();

		operacionComponenteBean.setCodigo(operacionComponente
				.getPkCodigoOperacioncomponente());
		operacionComponenteBean
				.setHojaRutaComponente(transformarHojaRutaComponente(operacionComponente
						.getHojarutacomponente()));
		operacionComponenteBean.setMaxFactor(operacionComponente
				.getMaxFactorOperacioncomponente());
		operacionComponenteBean.setMinFactor(operacionComponente
				.getMinFactorOperacioncomponente());

		return operacionComponenteBean;
	}

	public List<OperacionComponenteBean> transformarListaOperacionComponente(
			Set<Operacioncomponente> listaOperacionComponente) {

		List<OperacionComponenteBean> listaOperacionComponenteBean = new ArrayList<OperacionComponenteBean>();

		for (Iterator<Operacioncomponente> iterator = listaOperacionComponente
				.iterator(); iterator.hasNext();) {

			Operacioncomponente operacionComponente = iterator.next();

			OperacionComponenteBean operacionBean = transformarOperacionComponente(operacionComponente);
			listaOperacionComponenteBean.add(operacionBean);
		}

		return listaOperacionComponenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOperacionRecurso
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Operacionrecurso)
	 */
	public OperacionRecursoBean transformarOperacionRecurso(
			Operacionrecurso operacionRecurso) {

		if (operacionRecurso == null
				|| operacionRecurso.getPkCodigoOperacionrecurso() == null)
			return null;

		OperacionRecursoBean operacionRecursoBean = new OperacionRecursoBeanImpl();

		operacionRecursoBean.setCodigo(operacionRecurso
				.getPkCodigoOperacionrecurso());
		operacionRecursoBean.setOperacion(transformarOperacion(
				operacionRecurso.getOperacion(), false));
		operacionRecursoBean.setRecurso(transformarRecurso(operacionRecurso
				.getRecurso()));

		return operacionRecursoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaOperacionRecurso(java.util.Set)
	 */
	public List<OperacionRecursoBean> transformarListaOperacionRecurso(
			Set<Operacionrecurso> listaOperacionComponente) {

		List<OperacionRecursoBean> listaOperacionRecursoBean = new ArrayList<OperacionRecursoBean>();

		for (Iterator<Operacionrecurso> iterator = listaOperacionComponente
				.iterator(); iterator.hasNext();) {

			Operacionrecurso operacionRecurso = iterator.next();

			OperacionRecursoBean operacionRecursoBean = transformarOperacionRecurso(operacionRecurso);

			listaOperacionRecursoBean.add(operacionRecursoBean);
		}

		return listaOperacionRecursoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarProceso(pe.
	 * com.pacasmayo.sgcp.persistencia.dataObjects.Proceso)
	 */
	public ProcesoBean transformarProceso(Proceso proceso) {

		if (proceso == null || proceso.getPkCodigoProceso() == null)
			return null;

		ProcesoBean procesoBean = new ProcesoBeanImpl();

		procesoBean.setCodigo(proceso.getPkCodigoProceso());

		procesoBean.setCodigoSAP(proceso.getCodigoSapProceso());
		procesoBean.setCodigoSCC(proceso.getCodigoSccProceso());
		procesoBean.setDescripcion(proceso.getDescripcionProceso());
		procesoBean.setNombre(proceso.getNombreProceso());
		procesoBean.setOrdenEjecucion(proceso.getOrdenEjecucionProceso());
		procesoBean.setLineaNegocio(transformarLineaNegocio(proceso
				.getLineanegocio()));
		procesoBean.setTipoProducto(transformarTipoProducto(proceso
				.getTipoproducto()));
		procesoBean.setSiglas(proceso.getSiglasProceso());

		return procesoBean;
	}

	/**
	 * @param proceso
	 * @return
	 */
	public ProcesoBean transformarProcesoBasico(Proceso proceso) {

		if (proceso == null || proceso.getPkCodigoProceso() == null)
			return null;

		ProcesoBean procesoBean = new ProcesoBeanImpl();

		procesoBean.setCodigo(proceso.getPkCodigoProceso());

		procesoBean.setCodigoSAP(proceso.getCodigoSapProceso());
		procesoBean.setCodigoSCC(proceso.getCodigoSccProceso());
		procesoBean.setDescripcion(proceso.getDescripcionProceso());
		procesoBean.setNombre(proceso.getNombreProceso());
		procesoBean.setOrdenEjecucion(proceso.getOrdenEjecucionProceso());
		procesoBean.setSiglas(proceso.getSiglasProceso());

		return procesoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaProceso
	 * (java.util.List)
	 */
	public List<ProcesoBean> transformarListaProceso(List<Proceso> procesos) {

		List<ProcesoBean> procesoBeans = new ArrayList<ProcesoBean>();
		Proceso proceso;
		for (Iterator<Proceso> iterator = procesos.iterator(); iterator
				.hasNext();) {

			proceso = new Proceso();
			proceso = iterator.next();
			ProcesoBean procesoBean = transformarProceso(proceso);

			procesoBeans.add(procesoBean);
		}

		return procesoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaProcesoBasico
	 * (java.util.List)
	 */
	public List<ProcesoBean> transformarListaProcesoBasico(
			List<Proceso> procesos) {

		List<ProcesoBean> procesoBeans = new ArrayList<ProcesoBean>();
		Proceso proceso;
		for (Iterator<Proceso> iterator = procesos.iterator(); iterator
				.hasNext();) {

			proceso = new Proceso();
			proceso = iterator.next();
			ProcesoBean procesoBean = transformarProcesoBasico(proceso);

			procesoBeans.add(procesoBean);
		}

		return procesoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarProduccion(
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion)
	 */
	public ProduccionBean transformarProduccion(Produccion produccion) {

		if (produccion == null || produccion.getPkProduccion() == null)
			return null;

		ProduccionBean produccionBean = new ProduccionBeanImpl();

		produccionBean.setCodigo(produccion.getPkProduccion());
		produccionBean.setProceso(transformarProceso(produccion.getProceso()));
		produccionBean
				.setProducto(transformarProducto(produccion.getProducto()));

		return produccionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaProduccion
	 * (java.util.List)
	 */
	public List<ProduccionBean> transformarListaProduccion(
			List<Produccion> produccionsBeans) {

		List<ProduccionBean> listaProduccionBean = new ArrayList<ProduccionBean>();

		for (Iterator<Produccion> iterator = produccionsBeans.iterator(); iterator
				.hasNext();) {

			Produccion produccion = iterator.next();
			ProduccionBean productoBean = transformarProduccion(produccion);

			listaProduccionBean.add(productoBean);
		}

		return listaProduccionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarProducto(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Producto)
	 */
	public ProductoBean transformarProducto(Producto producto) {

		if (producto == null || producto.getPkCodigoProducto() == null)
			return null;

		ProductoBean productoBean = new ProductoBeanImpl();

		productoBean.setCodigo(producto.getPkCodigoProducto());
		if (producto.getCodigoSapProducto() != null)
			productoBean.setCodigoSAP(producto.getCodigoSapProducto().trim());
		if (producto.getCodigoSccProducto() != null)
			productoBean.setCodigoSCC(producto.getCodigoSccProducto());
		if (producto.getCostoProducto() != null)
			productoBean.setCosto(producto.getCostoProducto());
		if (producto.getDescripcionProducto() != null)
			productoBean.setDescripcion(producto.getDescripcionProducto()
					.trim());
		productoBean.setEstadoProducto(transformarEstadoProducto(producto
				.getEstadoproducto()));
		productoBean.setNombre(producto.getNombreProducto());
		productoBean.setSiglas(producto.getSiglasProducto());
		if (producto.getStockMaximoProducto() != null)
			productoBean.setStockMaximo(producto.getStockMaximoProducto());
		if (producto.getStockMinimoProducto() != null)
			productoBean.setStockMinimo(producto.getStockMinimoProducto());
		productoBean.setTipoProducto(transformarTipoProducto(producto
				.getTipoproducto()));
		productoBean.setUnidadMedida(transformarUnidadMedida(producto
				.getUnidadmedida()));

		productoBean
				.setTipoCategoriaProductoBean(transformarTipoCategoriaProducto(producto
						.getTipocategoriaproducto()));
		productoBean.setGrupoProducto(producto.getGrupoProducto());
		productoBean.setTipoConsumo(this.tranformarTipoConsumo(producto
				.getTipoconsumo()));

		return productoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarProductoBasico
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto)
	 */
	public ProductoBean transformarProductoBasico(Producto producto) {

		if (producto == null || producto.getPkCodigoProducto() == null)
			return null;

		ProductoBean productoBean = new ProductoBeanImpl();

		productoBean.setCodigo(producto.getPkCodigoProducto());
		productoBean.setCodigoSAP(producto.getCodigoSapProducto());
		productoBean.setCodigoSCC(producto.getCodigoSccProducto());
		productoBean.setDescripcion(producto.getDescripcionProducto());
		productoBean.setNombre(producto.getNombreProducto());
		productoBean.setSiglas(producto.getSiglasProducto());

		return productoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaProductos
	 * (java.util.List)
	 */
	public List<ProductoBean> transformarListaProductos(
			List<Producto> productosBeans) {

		List<ProductoBean> productoBeans = new ArrayList<ProductoBean>();

		for (Iterator<Producto> iterator = productosBeans.iterator(); iterator
				.hasNext();) {

			Producto producto = iterator.next();
			ProductoBean productoBean = transformarProducto(producto);

			productoBeans.add(productoBean);
		}

		return productoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaProductos
	 * (java.util.List)
	 */
	public Set<ProduccionBean> transformarListaProducciones(
			Set<Produccion> produccionesBeans) {

		Set<ProduccionBean> produccionBeans = new HashSet<ProduccionBean>();

		for (Iterator<Produccion> iterator = produccionesBeans.iterator(); iterator
				.hasNext();) {

			Produccion produccion = iterator.next();
			ProduccionBean produccionBean = transformarProduccion(produccion);

			produccionBeans.add(produccionBean);
		}

		return produccionBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaPuestoTrabajo
	 * (java.util.List)
	 */
	public List<PuestoTrabajoBean> transformarListaPuestoTrabajo(
			List<Puestotrabajo> puestosTrabajo) {

		List<PuestoTrabajoBean> listaPuestoTrabajoBean = new ArrayList<PuestoTrabajoBean>();

		for (Iterator<Puestotrabajo> iterator = puestosTrabajo.iterator(); iterator
				.hasNext();) {

			Puestotrabajo puestoTrabajo = iterator.next();
			PuestoTrabajoBean puestoTrabajoBean = transformarPuestoTrabajo(puestoTrabajo);

			listaPuestoTrabajoBean.add(puestoTrabajoBean);
		}

		return listaPuestoTrabajoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo)
	 */
	public PuestoTrabajoBean transformarPuestoTrabajo(
			Puestotrabajo puestoTrabajo) {

		if (puestoTrabajo == null
				|| puestoTrabajo.getPkCodigoPuestotrabajo() == null) {
			return null;
		}

		PuestoTrabajoBean puestoTrabajoBean = new PuestoTrabajoBeanImpl();

		puestoTrabajoBean.setCodigo(puestoTrabajo.getPkCodigoPuestotrabajo());
		puestoTrabajoBean.setNombre(puestoTrabajo.getNombrePuestotrabajo());
		puestoTrabajoBean.setDescripcion(puestoTrabajo
				.getDescripcionPuestotrabajo());
		puestoTrabajoBean
				.setEstadoPuestoTrabajo(transformarEstadoPuestoTrabajo(puestoTrabajo
						.getEstadopuestotrabajo()));
		puestoTrabajoBean.setCodigoSAP(puestoTrabajo
				.getCodigoSapPuestotrabajo());
		puestoTrabajoBean.setCodigoSCC(puestoTrabajo.getCodSccPuestotrabajo());
		puestoTrabajoBean.setSiglas(puestoTrabajo.getSiglasPuestotrabajo());
		puestoTrabajoBean
				.setTipoPuestoTrabajo(transformarTipoPuestoTrabajo(puestoTrabajo
						.getTipopuestotrabajo()));

		puestoTrabajoBean.setUnidadMedida(transformarUnidadMedida(puestoTrabajo
				.getUnidadmedida()));

		return puestoTrabajoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarRecurso(pe.
	 * com.pacasmayo.sgcp.persistencia.dataObjects.Recurso)
	 */
	public RecursoBean transformarRecurso(Recurso recurso) {

		if (recurso == null || recurso.getPkCodigoRecurso() == null) {
			return null;
		}

		RecursoBean recursoBean = new RecursoBeanImpl();

		recursoBean.setCodigo(recurso.getPkCodigoRecurso());
		recursoBean.setNombre(recurso.getNombreRecurso());
		recursoBean.setUnidadMedida(transformarUnidadMedida(recurso
				.getUnidadmedida()));

		return recursoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaRecurso
	 * (java.util.List)
	 */
	public List<RecursoBean> transformarListaRecurso(List<Recurso> recursoLista) {

		List<RecursoBean> listaRecursoBean = new ArrayList<RecursoBean>();

		for (Iterator<Recurso> iterator = recursoLista.iterator(); iterator
				.hasNext();) {

			Recurso recurso = iterator.next();
			RecursoBean recursoBean = transformarRecurso(recurso);

			listaRecursoBean.add(recursoBean);
		}

		return listaRecursoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaRecursosRM
	 * (java.util.Set)
	 */
	public RecursoRegistroMensualBean[] transformarListaRecursosRM(
			Set<Recursoregistromensual> listaRecursosRM) {
		RecursoRegistroMensualBean[] recursosRM = new RecursoRegistroMensualBean[12];

		for (Iterator<Recursoregistromensual> iterator = listaRecursosRM
				.iterator(); iterator.hasNext();) {
			Recursoregistromensual recursoregistromensual = iterator.next();
			RecursoRegistroMensualBean recursoRegistroMensualBean = this
					.transformarRecursoRM(recursoregistromensual);
			recursosRM[recursoRegistroMensualBean
					.getMesRecursoregistromensual() - 1] = recursoRegistroMensualBean;

		}

		return recursosRM;
	}

	private RecursoRegistroMensualBean transformarRecursoRM(
			Recursoregistromensual recursoregistromensual) {
		RecursoRegistroMensualBean recursoRegistroMensualBean = new RecursoRegistroMensualBeanImpl();

		if (recursoregistromensual == null
				|| recursoregistromensual.getPkCodigoRecursoregistromensua() == null)
			return null;

		recursoRegistroMensualBean.setCodigo(recursoregistromensual
				.getPkCodigoRecursoregistromensua());
		recursoRegistroMensualBean
				.setAnnoRecursoregistromensual(recursoregistromensual
						.getAnnoRecursoregistromensual());
		recursoRegistroMensualBean
				.setCantidadRecursoregistromensual(recursoregistromensual
						.getCantidadRecursoregistromensual());
		recursoRegistroMensualBean
				.setMesRecursoregistromensual(recursoregistromensual
						.getMesRecursoregistromensual());

		return recursoRegistroMensualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarSociedad(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad)
	 */
	public SociedadBean transformarSociedad(Sociedad sociedad) {

		if (sociedad == null || sociedad.getPkCodigoSociedad() == null) {
			return null;
		}

		SociedadBean sociedadBean = new SociedadBeanImpl();

		sociedadBean.setCodigo(sociedad.getPkCodigoSociedad());

		if (sociedad.getCodigoSapSociedad() != null)
			sociedadBean.setCodigoSAP(sociedad.getCodigoSapSociedad().trim());

		if (sociedad.getNombreSociedad() != null)
			sociedadBean.setNombre(sociedad.getNombreSociedad().trim());

		if (sociedad.getDescripcionSociedad() != null)
			sociedadBean.setDescripcion(sociedad.getDescripcionSociedad()
					.trim());
		sociedadBean.setDivision(transformarDivision(sociedad.getDivision()));

		if (sociedad.getSiglasSociedad() != null)
			sociedadBean.setSiglasSociedad(sociedad.getSiglasSociedad().trim());

		return sociedadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaSociedad
	 * (java.util.List)
	 */
	public List<SociedadBean> transformarListaSociedad(
			List<Sociedad> sociedadBeans) {

		List<SociedadBean> sociedadImplBeans = new ArrayList<SociedadBean>();

		for (Iterator<Sociedad> iterator = sociedadBeans.iterator(); iterator
				.hasNext();) {

			Sociedad sociedad = iterator.next();
			SociedadBean sociedadImplBean = transformarSociedad(sociedad);
			sociedadImplBeans.add(sociedadImplBean);
		}
		return sociedadImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTipoComponente
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocomponente)
	 */
	public TipoComponenteBean transformarTipoComponente(
			Tipocomponente tipoComponente) {

		if (tipoComponente == null
				|| tipoComponente.getPkCodigoTipocomponente() == null)
			return null;

		TipoComponenteBean tipoComponenteBean = new TipoComponenteBeanImpl();

		tipoComponenteBean
				.setCodigo(tipoComponente.getPkCodigoTipocomponente());
		tipoComponenteBean.setNombre(tipoComponente.getNombreTipocomponente());

		return tipoComponenteBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTipoComponentes(java.util.List)
	 */
	public List<TipoComponenteBean> transformarListaTipoComponentes(
			List<Tipocomponente> listaTipoComponentes) {

		List<TipoComponenteBean> listaTipoMedioAlmacenamientoBeans = new ArrayList<TipoComponenteBean>();

		for (Iterator<Tipocomponente> iterator = listaTipoComponentes
				.iterator(); iterator.hasNext();) {

			Tipocomponente tipoComponente = iterator.next();

			TipoComponenteBean tipoComponenteBean = transformarTipoComponente(tipoComponente);
			listaTipoMedioAlmacenamientoBeans.add(tipoComponenteBean);
		}

		return listaTipoMedioAlmacenamientoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarTipoMedioAlmacenamiento
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomedioalmacenamiento)
	 */
	public TipoMedioAlmacenamientoBean transformarTipoMedioAlmacenamiento(
			Tipomedioalmacenamiento tipoMedioAlmacenamiento) {

		if (tipoMedioAlmacenamiento == null
				|| tipoMedioAlmacenamiento.getPkCodigoTipomedioalmacenamien() == null)
			return null;

		TipoMedioAlmacenamientoBean tipoMedioAlmacenamientoBean = new TipoMedioAlmacenamientoBeanImpl();

		tipoMedioAlmacenamientoBean.setCodigo(tipoMedioAlmacenamiento
				.getPkCodigoTipomedioalmacenamien());
		tipoMedioAlmacenamientoBean.setDescripcion(tipoMedioAlmacenamiento
				.getDescripcionTipomedioalmacenami());
		tipoMedioAlmacenamientoBean.setNombre(tipoMedioAlmacenamiento
				.getNombreTipomedioalmacenamiento());
		tipoMedioAlmacenamientoBean
				.setUnidada(transformarUnidadMedida(tipoMedioAlmacenamiento
						.getUnidadmedida()));

		return tipoMedioAlmacenamientoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTipoMedioAlmacenamiento(java.util.List)
	 */
	public List<TipoMedioAlmacenamientoBean> transformarListaTipoMedioAlmacenamiento(
			List<Tipomedioalmacenamiento> tipoMedioAlmacenamientoBeans) {

		List<TipoMedioAlmacenamientoBean> listaTipoMedioAlmacenamientoBeans = new ArrayList<TipoMedioAlmacenamientoBean>();

		for (Iterator<Tipomedioalmacenamiento> iterator = tipoMedioAlmacenamientoBeans
				.iterator(); iterator.hasNext();) {

			Tipomedioalmacenamiento tipomedioalmacenamiento = iterator.next();

			TipoMedioAlmacenamientoBean tipoMedioAlmacenamientoBean = transformarTipoMedioAlmacenamiento(tipomedioalmacenamiento);
			listaTipoMedioAlmacenamientoBeans.add(tipoMedioAlmacenamientoBean);
		}
		return listaTipoMedioAlmacenamientoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTipoMovimiento
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomovimiento)
	 */
	public TipoMovimientoBean transformarTipoMovimiento(
			Tipomovimiento tipoMovimiento) {

		if (tipoMovimiento == null
				|| tipoMovimiento.getPkCodigoTipomovimiento() == null)
			return null;

		TipoMovimientoBean tipoMovimientoBean = new TipoMovimientoBeanImpl();

		tipoMovimientoBean
				.setCodigo(tipoMovimiento.getPkCodigoTipomovimiento());
		if (tipoMovimiento.getDescripcionTipomovimiento() != null)
			tipoMovimientoBean.setDescripcion(tipoMovimiento
					.getDescripcionTipomovimiento().trim());
		tipoMovimientoBean.setNombre(tipoMovimiento.getNombreTipomovimiento()
				.trim());

		if (tipoMovimiento.getCodigoSapTipomovimiento() != null)
			tipoMovimientoBean.setCodigoSAP(tipoMovimiento
					.getCodigoSapTipomovimiento().trim());

		tipoMovimientoBean
				.setClasificacionTipoMovimiento(transformarClasificacionTipoMovimiento(tipoMovimiento
						.getClasificaciontipomovimiento()));

		return tipoMovimientoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaTipoMovimiento
	 * (java.util.List)
	 */
	public List<TipoMovimientoBean> transformarListaTipoMovimiento(
			List<Tipomovimiento> listaTipoMovimientoBeans) {

		List<TipoMovimientoBean> listaTipoMovimientoBean = new ArrayList<TipoMovimientoBean>();

		for (Iterator<Tipomovimiento> iterator = listaTipoMovimientoBeans
				.iterator(); iterator.hasNext();) {

			Tipomovimiento tipoMovimiento = iterator.next();
			TipoMovimientoBean tipoMovimientoBean = transformarTipoMovimiento(tipoMovimiento);

			listaTipoMovimientoBean.add(tipoMovimientoBean);
		}
		return listaTipoMovimientoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaTipoProducto
	 * (java.util.List)
	 */
	public List<TipoProductoBean> transformarListaTipoProducto(
			List<Tipoproducto> tipoProductoBeans) {

		List<TipoProductoBean> listaTipoProducBean = new ArrayList<TipoProductoBean>();

		for (Iterator<Tipoproducto> iterator = tipoProductoBeans.iterator(); iterator
				.hasNext();) {

			Tipoproducto tipoProd = iterator.next();
			TipoProductoBean tipoProdBean = transformarTipoProducto(tipoProd);

			listaTipoProducBean.add(tipoProdBean);
		}
		return listaTipoProducBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTipoProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipoproducto)
	 */
	public TipoProductoBean transformarTipoProducto(Tipoproducto tipoProducto) {

		if (tipoProducto == null
				|| tipoProducto.getPkCodigoTipoproducto() == null) {
			return null;
		}

		TipoProductoBean tipoProductoBean = new TipoProductoBeanImpl();

		tipoProductoBean.setCodigo(tipoProducto.getPkCodigoTipoproducto());
		tipoProductoBean.setNombre(tipoProducto.getNombreTipoproducto());
		tipoProductoBean.setSiglas(tipoProducto.getSiglasTipoproducto());

		return tipoProductoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTipoPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipopuestotrabajo)
	 */
	public TipoPuestoTrabajoBean transformarTipoPuestoTrabajo(
			Tipopuestotrabajo tipoPuestoTrabajo) {

		if (tipoPuestoTrabajo == null
				|| tipoPuestoTrabajo.getPkCodigoTipopuestotrabajo() == null)
			return null;

		TipoPuestoTrabajoBean tipoPuestoTrabajoBeanImpl = new TipoPuestoTrabajoBeanImpl();

		tipoPuestoTrabajoBeanImpl.setCodigo(tipoPuestoTrabajo
				.getPkCodigoTipopuestotrabajo());
		tipoPuestoTrabajoBeanImpl.setNombre(tipoPuestoTrabajo
				.getNombreTipopuestotrabajo());
		tipoPuestoTrabajoBeanImpl.setDescripcion(tipoPuestoTrabajo
				.getDescripcionTipopuestotrabajo());

		return tipoPuestoTrabajoBeanImpl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTipoPuestoTrabajo(java.util.List)
	 */
	public List<TipoPuestoTrabajoBean> transformarListaTipoPuestoTrabajo(
			List<Tipopuestotrabajo> puestosTrabajo) {

		List<TipoPuestoTrabajoBean> listaPuestoTrabajoBean = new ArrayList<TipoPuestoTrabajoBean>();

		for (Iterator<Tipopuestotrabajo> iterator = puestosTrabajo.iterator(); iterator
				.hasNext();) {

			Tipopuestotrabajo tipoPuestoTrabajo = iterator.next();
			TipoPuestoTrabajoBean tipoPuestoTrabajoBean = transformarTipoPuestoTrabajo(tipoPuestoTrabajo);

			listaPuestoTrabajoBean.add(tipoPuestoTrabajoBean);
		}

		return listaPuestoTrabajoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaUbicaciones
	 * (java.util.List)
	 */
	public List<UbicacionBean> transformarListaUbicaciones(
			List<Ubicacion> puestosTrabajo) {

		List<UbicacionBean> listaUbicacionBean = new ArrayList<UbicacionBean>();

		for (Iterator<Ubicacion> iterator = puestosTrabajo.iterator(); iterator
				.hasNext();) {

			Ubicacion ubicacion = iterator.next();
			UbicacionBean ubicacionBean = transformarUbicacion(ubicacion);

			listaUbicacionBean.add(ubicacionBean);
		}

		return listaUbicacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUbicacion(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Ubicacion)
	 */
	public UbicacionBean transformarUbicacion(Ubicacion ubicacion) {

		if (ubicacion == null || ubicacion.getPkCodigoUbicacion() == null) {
			return null;
		}

		UbicacionBean ubicacionBean = new UbicacionBeanImpl();

		ubicacionBean.setAlmacen(transformarAlmacen(ubicacion.getAlmacen()));

		ubicacionBean.setCodigo(ubicacion.getPkCodigoUbicacion());
		ubicacionBean.setDescripcion(ubicacion.getDescripcionUbicacion());
		ubicacionBean.setNombre(ubicacion.getNombreUbicacion());

		return ubicacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUnidad(pe.com
	 * .pacasmayo.sgcp.persistencia.dataObjects.Unidad)
	 */
	public UnidadBean transformarUnidad(Unidad unidad) {

		if (unidad == null || unidad.getPkCodigoUnidad() == null) {
			return null;
		}

		UnidadBean unidadBean = new UnidadBeanImpl();

		unidadBean.setCodigo(unidad.getPkCodigoUnidad());

		if (unidad.getNombreUnidad() != null)
			unidadBean.setNombre(unidad.getNombreUnidad().trim());

		if (unidad.getDescripcionUnidad() != null)
			unidadBean.setDescripcion(unidad.getDescripcionUnidad().trim());
		unidadBean.setSociedad(transformarSociedad(unidad.getSociedad()));

		if (unidad.getCodigoSapUnidad() != null)
			unidadBean.setCodigoSAP(unidad.getCodigoSapUnidad().trim());

		return unidadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUnidadBasico
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidad)
	 */
	public UnidadBean transformarUnidadBasico(Unidad unidad) {

		if (unidad == null || unidad.getPkCodigoUnidad() == null) {
			return null;
		}

		UnidadBean unidadBean = new UnidadBeanImpl();

		unidadBean.setCodigo(unidad.getPkCodigoUnidad());

		if (unidad.getNombreUnidad() != null)
			unidadBean.setNombre(unidad.getNombreUnidad().trim());

		if (unidad.getDescripcionUnidad() != null)
			unidadBean.setDescripcion(unidad.getDescripcionUnidad().trim());

		if (unidad.getCodigoSapUnidad() != null)
			unidadBean.setCodigoSAP(unidad.getCodigoSapUnidad().trim());

		return unidadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaUnidad
	 * (java.util.List)
	 */
	public List<UnidadBean> transformarListaUnidad(List<Unidad> unidadBeans) {

		List<UnidadBean> unidadImplBeans = new ArrayList<UnidadBean>();

		for (Iterator<Unidad> iterator = unidadBeans.iterator(); iterator
				.hasNext();) {

			Unidad unidad = iterator.next();
			UnidadBean unidadBean = transformarUnidad(unidad);

			unidadImplBeans.add(unidadBean);
		}
		return unidadImplBeans;
	}

	public List<UnidadBean> transformarListaUnidadBasico(
			List<Unidad> unidadBeans) {

		List<UnidadBean> unidadImplBeans = new ArrayList<UnidadBean>();

		for (Iterator<Unidad> iterator = unidadBeans.iterator(); iterator
				.hasNext();) {

			Unidad unidad = iterator.next();
			UnidadBean unidadBean = transformarUnidadBasico(unidad);

			unidadImplBeans.add(unidadBean);
		}
		return unidadImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaUnidadMedida
	 * (java.util.List)
	 */
	public List<UnidadMedidaBean> transformarListaUnidadMedida(
			List<Unidadmedida> unidadMedidaBeans) {

		List<UnidadMedidaBean> listaEstadoProducBean = new ArrayList<UnidadMedidaBean>();

		for (Iterator<Unidadmedida> iterator = unidadMedidaBeans.iterator(); iterator
				.hasNext();) {

			Unidadmedida estadoProd = iterator.next();
			UnidadMedidaBean estadoProdBean = transformarUnidadMedida(estadoProd);

			listaEstadoProducBean.add(estadoProdBean);
		}
		return listaEstadoProducBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUnidadMedida
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Unidadmedida)
	 */
	public UnidadMedidaBean transformarUnidadMedida(Unidadmedida unidadMedida) {

		if (unidadMedida == null
				|| unidadMedida.getPkCodigoUnidadMedida() == null) {
			return null;
		}

		UnidadMedidaBean unidadMedidaBean = new UnidadMedidaBeanImpl();

		unidadMedidaBean.setCodigo(unidadMedida.getPkCodigoUnidadMedida());

		if (unidadMedida.getDescripcionUnidadmedida() != null)
			unidadMedidaBean.setDescripcion(unidadMedida
					.getDescripcionUnidadmedida().trim());
		unidadMedidaBean.setNombre(unidadMedida.getNombreUnidadmedida().trim());

		return unidadMedidaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPersona(pe.
	 * com.pacasmayo.sgcp.persistencia.dataObjects.Persona)
	 */
	public PersonaBean transformarPersona(Persona persona) {

		if (persona == null || persona.getPkCodigoPersona() == null)
			return null;

		PersonaBean personaBean = new PersonaBeanImpl();

		personaBean.setCodigo(persona.getPkCodigoPersona());
		personaBean.setNombre(persona.getNombrePersona());
		personaBean.setApellido(persona.getApellidoPersona());
		personaBean.setTelefono(persona.getTelefonoPersona());
		personaBean.setExtension(persona.getExtensionPersona());
		personaBean.setCorreo(persona.getCorreoPersona());
		personaBean.setIdDocumento(persona.getIddocumentoPersona());
		personaBean.setCargo(transformarCargo(persona.getCargo()));

		return personaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaPersona
	 * (java.util.List)
	 */
	public List<PersonaBean> transformarListaPersona(List<Persona> personas) {
		List<PersonaBean> personasBean = new ArrayList<PersonaBean>();

		for (Iterator<Persona> iterator = personas.iterator(); iterator
				.hasNext();) {

			Persona persona = iterator.next();
			PersonaBean personaBean = transformarPersona(persona);
			personasBean.add(personaBean);
		}
		return personasBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaUsuario
	 * (java.util.List)
	 */
	public List<UsuarioBean> transformarListaUsuario(List<Usuario> usuarios) {
		List<UsuarioBean> usuariosBean = new ArrayList<UsuarioBean>();

		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator
				.hasNext();) {

			Usuario usuario = iterator.next();
			UsuarioBean usuarioBean = transformarUsuario(usuario);
			usuariosBean.add(usuarioBean);
		}
		return usuariosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOrdenProduccion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion)
	 */
	public OrdenProduccionBean transformarOrdenProduccion(
			Ordenproduccion ordenproduccion) {

		if (ordenproduccion == null
				|| ordenproduccion.getPkCodigoOrdenproduccion() == null)
			return null;

		OrdenProduccionBean ordenProduccionBean = new OrdenProduccionBeanImpl();

		ordenProduccionBean.setCodigo(ordenproduccion
				.getPkCodigoOrdenproduccion());
		try {
			Ordenproduccionmanual ordenMan = OrdenProduccionManualQuerier
					.findByOrdenProduccion(ordenproduccion
							.getPkCodigoOrdenproduccion());
			if (ordenMan == null) {
				ordenProduccionBean.setEsManual(false);
			} else {
				ordenProduccionBean.setEsManual(true);
			}
		} catch (AplicacionException e) {
			logger.error(e.getMensaje());
		}

		EstadoOrdenProduccionBean estadoOrdenProduccionBean = new EstadoOrdenProduccionBeanImpl();
		estadoOrdenProduccionBean.setCodigo(ordenproduccion
				.getEstadoordenproduccion().getPkCodigoEstadoorden());
		if (!StringUtils.isBlank(ordenproduccion.getEstadoordenproduccion()
				.getNombreEstadoorden()))
			estadoOrdenProduccionBean.setNombre(ordenproduccion
					.getEstadoordenproduccion().getNombreEstadoorden());
		ordenProduccionBean.setEstadoOrdenProduccion(estadoOrdenProduccionBean);
		ordenProduccionBean.setFechaAprobacion(ordenproduccion
				.getFechaAprobacionOrdenproduccio());
		ordenProduccionBean.setFechaRegistro(ordenproduccion
				.getFechaRegistroOrdenproduccion());

		HojaRutaBean hojaRutaBean = new HojaRutaBeanImpl();
		hojaRutaBean.setCodigo(ordenproduccion.getHojaruta()
				.getPkCodigoHojaruta());
		ordenProduccionBean.setHojaRuta(hojaRutaBean);

		ordenProduccionBean.setMes(ordenproduccion.getMesOrdenproduccion());
		ordenProduccionBean.setNumeroDocumento(ordenproduccion
				.getNumeroDocumentoOrdenproduccio());
		ordenProduccionBean.setNumeroOrden(ordenproduccion
				.getNumeroOrdenOrdenproduccion());
		ordenProduccionBean.setProduccionEjecutada(ordenproduccion
				.getProduccionEjecutadaOrdenprodu());
		ordenProduccionBean.setProduccionEstimada(ordenproduccion
				.getProduccionEstimadaOrdenproduc());

		UsuarioBean usuarioBeanAprueba = this
				.transformarUsuario(ordenproduccion
						.getUsuarioByFkCodigoUsuarioAprueba());
		ordenProduccionBean.setUsuarioAprueba(usuarioBeanAprueba);

		UsuarioBean usuarioBeanRegistro = this
				.transformarUsuario(ordenproduccion
						.getUsuarioByFkCodigoUsuarioRegistro());
		ordenProduccionBean.setUsuarioRegistro(usuarioBeanRegistro);

		ordenProduccionBean.setProduccion(transformarProduccion(ordenproduccion
				.getProduccion()));

		return ordenProduccionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarOrdenProduccionBasico
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion)
	 */
	public OrdenProduccionBean transformarOrdenProduccionBasico(
			Ordenproduccion ordenproduccion) {

		if (ordenproduccion == null
				|| ordenproduccion.getPkCodigoOrdenproduccion() == null)
			return null;

		OrdenProduccionBean ordenProduccionBean = new OrdenProduccionBeanImpl();

		ordenProduccionBean.setCodigo(ordenproduccion
				.getPkCodigoOrdenproduccion());

		ordenProduccionBean.setFechaAprobacion(ordenproduccion
				.getFechaAprobacionOrdenproduccio());
		ordenProduccionBean.setFechaRegistro(ordenproduccion
				.getFechaRegistroOrdenproduccion());

		ordenProduccionBean.setMes(ordenproduccion.getMesOrdenproduccion());
		ordenProduccionBean.setNumeroDocumento(ordenproduccion
				.getNumeroDocumentoOrdenproduccio());
		ordenProduccionBean.setNumeroOrden(ordenproduccion
				.getNumeroOrdenOrdenproduccion());
		ordenProduccionBean.setProduccionEjecutada(ordenproduccion
				.getProduccionEjecutadaOrdenprodu());
		ordenProduccionBean.setProduccionEstimada(ordenproduccion
				.getProduccionEstimadaOrdenproduc());
		ordenProduccionBean.setProduccion(transformarProduccion(ordenproduccion
				.getProduccion()));

		return ordenProduccionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOrdenProduccionBean
	 * (pe.com.pacasmayo.sgcp.bean.OrdenProduccionBean)
	 */
	public Ordenproduccion transformarOrdenProduccionBean(
			OrdenProduccionBean ordenProduccionBean) {

		if (ordenProduccionBean == null)
			return null;

		Ordenproduccion ordenproduccion = new Ordenproduccion();

		ordenproduccion.setPkCodigoOrdenproduccion(ordenProduccionBean
				.getCodigo());

		Estadoordenproduccion estadoOrdenProduccion = new Estadoordenproduccion();
		estadoOrdenProduccion.setPkCodigoEstadoorden(ordenProduccionBean
				.getEstadoOrdenProduccion().getCodigo());
		if (!StringUtils.isBlank(ordenProduccionBean.getEstadoOrdenProduccion()
				.getNombre()))
			estadoOrdenProduccion.setNombreEstadoorden(ordenProduccionBean
					.getEstadoOrdenProduccion().getNombre());
		ordenproduccion.setEstadoordenproduccion(estadoOrdenProduccion);
		ordenproduccion.setFechaAprobacionOrdenproduccio(ordenProduccionBean
				.getFechaAprobacion());
		ordenproduccion.setFechaRegistroOrdenproduccion(ordenProduccionBean
				.getFechaRegistro());

		Hojaruta hojaRuta = new Hojaruta();
		hojaRuta.setPkCodigoHojaruta(ordenProduccionBean.getHojaRuta()
				.getCodigo());
		ordenproduccion.setHojaruta(hojaRuta);
		ordenproduccion.setMesOrdenproduccion(Integer.valueOf(
				ordenProduccionBean.getMes()).shortValue());
		ordenproduccion.setNumeroDocumentoOrdenproduccio(ordenProduccionBean
				.getNumeroDocumento());
		ordenproduccion.setNumeroOrdenOrdenproduccion(ordenProduccionBean
				.getNumeroOrden());
		ordenproduccion.setProduccionEjecutadaOrdenprodu(ordenProduccionBean
				.getProduccionEjecutada());
		ordenproduccion.setProduccionEstimadaOrdenproduc(ordenProduccionBean
				.getProduccionEstimada());

		Usuario usuarioRegistro = new Usuario();
		usuarioRegistro.setPkCodigoUsuario(ordenProduccionBean
				.getUsuarioRegistro().getCodigo());
		ordenproduccion.setUsuarioByFkCodigoUsuarioRegistro(usuarioRegistro);

		Usuario usuarioAprueba = new Usuario();
		usuarioAprueba.setPkCodigoUsuario(ordenProduccionBean
				.getUsuarioAprueba().getCodigo());
		ordenproduccion.setUsuarioByFkCodigoUsuarioAprueba(usuarioAprueba);

		Produccion produccion = new Produccion();
		produccion.setPkProduccion(ordenProduccionBean.getProduccion()
				.getCodigo());
		ordenproduccion.setProduccion(produccion);

		return ordenproduccion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaOrdenProduccion(java.util.List)
	 */
	public List<OrdenProduccionBean> transformarListaOrdenProduccion(
			List<Ordenproduccion> listOrdenProduccion) {

		List<OrdenProduccionBean> OrdenProduccionImplBeans = new ArrayList<OrdenProduccionBean>();

		for (Iterator<Ordenproduccion> iterator = listOrdenProduccion
				.iterator(); iterator.hasNext();) {

			Ordenproduccion ordenproduccion = iterator.next();
			OrdenProduccionBean ordenProduccionBean = transformarOrdenProduccion(ordenproduccion);

			OrdenProduccionImplBeans.add(ordenProduccionBean);
		}
		return OrdenProduccionImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaOrdenProduccionBasico(java.util.List)
	 */
	public List<OrdenProduccionBean> transformarListaOrdenProduccionBasico(
			List<Ordenproduccion> listOrdenProduccion) {

		List<OrdenProduccionBean> OrdenProduccionImplBeans = new ArrayList<OrdenProduccionBean>();

		for (Iterator<Ordenproduccion> iterator = listOrdenProduccion
				.iterator(); iterator.hasNext();) {

			Ordenproduccion ordenproduccion = iterator.next();
			OrdenProduccionBean ordenProduccionBean = transformarOrdenProduccionBasico(ordenproduccion);

			OrdenProduccionImplBeans.add(ordenProduccionBean);
		}
		return OrdenProduccionImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarOrdenProduccionPlan
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccionplan)
	 */
	public OrdenProduccionPlanBean transformarOrdenProduccionPlan(
			Ordenproduccionplan ordenProduccionPlan) {

		if (ordenProduccionPlan == null
				|| ordenProduccionPlan.getPkCodigoOrdenproduccionplan() == null)
			return null;

		OrdenProduccionPlanBean ordenProduccionPlanBean = new OrdenProduccionPlanBeanImpl();
		ordenProduccionPlanBean
				.setCodigoOrdenPlanProduccion(ordenProduccionPlan
						.getPkCodigoOrdenproduccionplan());

		if (ordenProduccionPlan.getOrdenproduccion() != null) {
			OrdenProduccionBean ordenProduccion = new OrdenProduccionBeanImpl();
			ordenProduccion.setCodigo(ordenProduccionPlan.getOrdenproduccion()
					.getPkCodigoOrdenproduccion());
			ordenProduccionPlanBean.setOrdenProduccionBean(ordenProduccion);
		}

		if (ordenProduccionPlan.getPlananual() != null) {
			PlanAnualBean planAnual = new PlanAnualBeanImpl();
			planAnual.setCodigo(ordenProduccionPlan.getPlananual()
					.getPkCodigoPlananual());
			ordenProduccionPlanBean.setPlanAnualBean(planAnual);
		}

		return ordenProduccionPlanBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarOrdenProduccionPlanBean
	 * (pe.com.pacasmayo.sgcp.bean.OrdenProduccionPlanBean)
	 */
	public Ordenproduccionplan transformarOrdenProduccionPlanBean(
			OrdenProduccionPlanBean ordenProduccionPlanBean) {

		if (ordenProduccionPlanBean == null)
			return null;

		Ordenproduccionplan ordenproduccionplan = new Ordenproduccionplan();
		ordenproduccionplan
				.setPkCodigoOrdenproduccionplan(ordenProduccionPlanBean
						.getCodigoOrdenPlanProduccion());
		Ordenproduccion ordenproduccion = new Ordenproduccion();
		ordenproduccion.setPkCodigoOrdenproduccion(ordenProduccionPlanBean
				.getOrdenProduccionBean().getCodigo());
		ordenproduccionplan.setOrdenproduccion(ordenproduccion);
		Plananual plananual = new Plananual();
		plananual.setPkCodigoPlananual(ordenProduccionPlanBean
				.getPlanAnualBean().getCodigo());
		ordenproduccionplan.setPlananual(plananual);
		return ordenproduccionplan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPlanAnual(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Plananual)
	 */
	public PlanAnualBean transformarPlanAnual(Plananual plananual) {

		if (plananual == null || plananual.getPkCodigoPlananual() == null)
			return null;

		PlanAnualBean planAnualBean = new PlanAnualBeanImpl();

		planAnualBean.setAnno(plananual.getAnnoPlananual());
		planAnualBean.setCodigo(plananual.getPkCodigoPlananual());
		planAnualBean.setComentario(plananual.getObservacionPlananual());
		planAnualBean.setEstadoPlan(transformarEstadoPlan(plananual
				.getEstadoplananual()));

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
		if (plananual.getFechaAprobacionPlananual() != null) {
			planAnualBean.setFechaAprueba(dateFormat.format(plananual
					.getFechaAprobacionPlananual()));
		}

		planAnualBean.setFechaRegistra(dateFormat.format(plananual
				.getFechaRegistroPlananual()));

		planAnualBean.setLineaNegocio(transformarLineaNegocio(plananual
				.getLineanegocio()));

		if (plananual.getPlancomercializacions() != null
				&& plananual.getPlancomercializacions().size() > 0)
			planAnualBean
					.setListaPlanComercializacion(transformarListaPlanComercializacion(plananual
							.getPlancomercializacions()));

		if (plananual.getCapacidadoperativaregistromensus() != null
				&& plananual.getCapacidadoperativaregistromensus().size() > 0)
			planAnualBean
					.setListaCapacidadOperRegMen(transformarListaCapacidadOperativaRegistroMensual(plananual
							.getCapacidadoperativaregistromensus()));

		planAnualBean.setMesCorteVersion(plananual
				.getMesCorteVersionPlananual());

		planAnualBean.setNecesidadComercial(plananual
				.getNecesidadComercialPlananual());

		// planAnualBean.setOrdenProduccion(ordenProduccion)
		// planAnualBean.setPlanNecesidad();

		// planAnualBean.setPlanParada(planParada)

		planAnualBean.setProduccionAnual(plananual.getProduccionPlananual());
		planAnualBean.setUsuarioAprueba(transformarUsuario(plananual
				.getUsuarioByFkCodigoUsuarioAprueba()));
		planAnualBean.setUsuarioRegistra(transformarUsuario(plananual
				.getUsuarioByFkCodigoUsuarioRegistra()));
		planAnualBean.setVersion(plananual.getVersionPlananual());

		planAnualBean.setUsuarioRegistraConFecha(" ");
		planAnualBean.setUsuarioApruebaConFecha(" ");

		if (planAnualBean.getUsuarioRegistra() != null
				&& planAnualBean.getUsuarioRegistra().getCodigo() > 0
				&& planAnualBean.getFechaRegistra() != null)
			planAnualBean.setUsuarioRegistraConFecha(planAnualBean
					.getUsuarioRegistra().getLogin()
					+ " - "
					+ planAnualBean.getFechaRegistra().toString());

		if (planAnualBean.getUsuarioAprueba() != null
				&& planAnualBean.getUsuarioAprueba().getCodigo() > 0
				&& planAnualBean.getFechaAprueba() != null)
			planAnualBean.setUsuarioApruebaConFecha(planAnualBean
					.getUsuarioAprueba().getLogin()
					+ " - "
					+ planAnualBean.getFechaAprueba().toString());

		return planAnualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaPlanComercializacion(java.util.Set)
	 */

	@SuppressWarnings("unchecked")
	public List<PlanComercializacionBean> transformarListaPlanComercializacion(
			Set<Plancomercializacion> plancomercializacions) {
		List<PlanComercializacionBean> list = new ArrayList<PlanComercializacionBean>();
		for (Iterator<Plancomercializacion> iterator = plancomercializacions
				.iterator(); iterator.hasNext();) {
			PlanComercializacionBean planComercializacionBean = new PlanComercializacionBeanImpl();
			Plancomercializacion plancomercializacion = iterator.next();
			planComercializacionBean.setCodigo(plancomercializacion
					.getPkCodigoPlancomercializacion());
			planComercializacionBean
					.setProduccion(transformarProduccion(plancomercializacion
							.getProduccion()));
			planComercializacionBean
					.setEstimacionRegistroMensualBeanList(transformarListaEstimacionRegistroMensual(plancomercializacion
							.getEstimacionregistromensuals()));
			list.add(planComercializacionBean);
		}
		Collections.sort(list);
		return list;
	}

	private EstimacionRegistroMensualBean[] transformarListaEstimacionRegistroMensual(
			Set<Estimacionregistromensual> estimacionregistromensuals) {
		EstimacionRegistroMensualBean[] estimaciones = new EstimacionRegistroMensualBean[12];
		int i = 0;
		for (Iterator<Estimacionregistromensual> iterator = estimacionregistromensuals
				.iterator(); iterator.hasNext();) {

			EstimacionRegistroMensualBean bean = new EstimacionRegistroMensualBeanImpl();
			Estimacionregistromensual estimacionregistromensual = iterator
					.next();
			bean.setAnnoEstimacionregistromensual(estimacionregistromensual
					.getAnnoEstimacionregistromensual());
			bean.setCantidadEstimacionregistromens(estimacionregistromensual
					.getCantidadEstimacionregistromens());

			bean.setCantidadEstimacion(nf.format(estimacionregistromensual
					.getCantidadEstimacionregistromens()));

			bean.setCodigo(estimacionregistromensual
					.getPkCodigoEstimacionregistromen());
			bean.setMesEstimacionregistromensual(estimacionregistromensual
					.getMesEstimacionregistromensual());
			estimaciones[estimacionregistromensual
					.getMesEstimacionregistromensual() - 1] = bean;
			i++;
		}
		return estimaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaPlanAnual
	 * (java.util.List)
	 */
	public List<PlanAnualBean> transformarListaPlanAnual(
			List<Plananual> planAnualBeans) {

		List<PlanAnualBean> planAnualImplBeans = new ArrayList<PlanAnualBean>();

		for (Iterator<Plananual> iterator = planAnualBeans.iterator(); iterator
				.hasNext();) {

			Plananual plananual = iterator.next();
			PlanAnualBean planAnualImplBean = transformarPlanAnual(plananual);

			planAnualImplBeans.add(planAnualImplBean);
		}
		return planAnualImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoPlanAnual(java.util.List)
	 */
	public List<EstadoPlanBean> transformarListaEstadoPlanAnual(
			List<Estadoplananual> estadoPlanList) {
		List<EstadoPlanBean> estadoPlanImplBeans = new ArrayList<EstadoPlanBean>();

		for (Iterator<Estadoplananual> iterator = estadoPlanList.iterator(); iterator
				.hasNext();) {

			Estadoplananual estadoplananual = iterator.next();
			EstadoPlanBean estadoPlanImplBean = transformarEstadoPlan(estadoplananual);

			estadoPlanImplBeans.add(estadoPlanImplBean);
		}
		return estadoPlanImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoPlan(
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoplananual)
	 */
	public EstadoPlanBean transformarEstadoPlan(Estadoplananual estadoplananual) {

		if (estadoplananual == null
				|| estadoplananual.getPkCodigoEstadoplananual() == null)
			return null;

		EstadoPlanBean estadoPlanBean = new EstadoPlanBeanImpl();

		estadoPlanBean.setCodigo(estadoplananual.getPkCodigoEstadoplananual());
		estadoPlanBean.setNombre(estadoplananual.getNombreEstadoplan());

		return estadoPlanBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPlanAnualCompleto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plananual)
	 */
	@SuppressWarnings("unchecked")
	public PlanAnualBean transformarPlanAnualCompleto(Plananual plananual) {
		if (plananual == null) {
			return null;
		}
		PlanAnualBean planAnualBean = transformarPlanAnual(plananual);

		Set<Plancomercializacion> planComercializacionSet = plananual
				.getPlancomercializacions();
		if (planComercializacionSet != null
				&& !planComercializacionSet.isEmpty())
			planAnualBean
					.setListaPlanComercializacion(transformarListaPlanComercializacion(planComercializacionSet));
		return planAnualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarConcepto(pe
	 * .com.pacasmayo.sgcp.persistencia.dataObjects.Concepto)
	 */
	public ConceptoBean transformarConcepto(Concepto concepto) {

		if (concepto == null || concepto.getPkCodigoConcepto() == null)
			return null;

		ConceptoBean conceptoBean = new ConceptoBeanImpl();

		conceptoBean.setCodigo(concepto.getPkCodigoConcepto());
		conceptoBean.setDescripcion(concepto.getDescripcionConcepto());
		conceptoBean.setNombre(concepto.getNombreConcepto());
		conceptoBean.setUnidadMedida(transformarUnidadMedida(concepto
				.getUnidadmedida()));
		return conceptoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaConcepto
	 * (java.util.List)
	 */
	public List<ConceptoBean> transformarListaConcepto(
			List<Concepto> listaConcepto) {
		List<ConceptoBean> listaConceptoBean = new ArrayList<ConceptoBean>();
		for (Iterator<Concepto> iterator = listaConcepto.iterator(); iterator
				.hasNext();) {
			Concepto concepto = iterator.next();
			ConceptoBean conceptoBean = transformarConcepto(concepto);
			listaConceptoBean.add(conceptoBean);
		}
		return listaConceptoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarMedioAlmacenamiento
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento)
	 */
	public MedioAlmacenamientoBean transformarMedioAlmacenamiento(
			Medioalmacenamiento medioalmacenamiento) {

		if (medioalmacenamiento == null
				|| medioalmacenamiento.getPkCodigoMedioalmacenamiento() == null)
			return null;

		MedioAlmacenamientoBean medioAlmacenamientoBean = new MedioAlmacenamientoBeanImpl();

		medioAlmacenamientoBean.setCodigo(medioalmacenamiento
				.getPkCodigoMedioalmacenamiento());
		medioAlmacenamientoBean.setNombre(medioalmacenamiento
				.getNombreMedioalmacenamiento());
		medioAlmacenamientoBean.setNumero(medioalmacenamiento
				.getNumeroMedioalmacenamiento());
		medioAlmacenamientoBean.setAlturaEspecifica(medioalmacenamiento
				.getAlturaEspecificaMedioalmacena());
		medioAlmacenamientoBean.setCapacidadMaxima(medioalmacenamiento
				.getCapacidadMaximaMedioalmacenam());
		medioAlmacenamientoBean.setCapacidadMinima(medioalmacenamiento
				.getCapacidadMinimaMedioalmacenam());
		medioAlmacenamientoBean.setDensidad(medioalmacenamiento
				.getDensidadMedioalmacenamiento());
		medioAlmacenamientoBean.setFactorMetrosCubicos(medioalmacenamiento
				.getFactorMetrosCubicosMedioalma());
		medioAlmacenamientoBean.setStockSeguridad(medioalmacenamiento
				.getStockSeguridadMedioalmacenami());
		medioAlmacenamientoBean.setNumeroAlturas(medioalmacenamiento
				.getNumeroAlturasMedioalmacenamie());
		medioAlmacenamientoBean
				.setProduccion(transformarProduccion(medioalmacenamiento
						.getProduccion()));
		medioAlmacenamientoBean
				.setPuestoTrabajo(transformarPuestoTrabajo(medioalmacenamiento
						.getPuestotrabajo()));

		medioAlmacenamientoBean
				.setTipoMedioAlmacenamiento(transformarTipoMedioAlmacenamiento(medioalmacenamiento
						.getTipomedioalmacenamiento()));

		medioAlmacenamientoBean
				.setUbicacion(transformarUbicacion(medioalmacenamiento
						.getUbicacion()));

		return medioAlmacenamientoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaMedioAlmacenamiento(java.util.List)
	 */
	public List<MedioAlmacenamientoBean> transformarListaMedioAlmacenamiento(
			List<Medioalmacenamiento> mediosAlmacenamiento) {

		List<MedioAlmacenamientoBean> mediosAlmacenamientoBeans = new ArrayList<MedioAlmacenamientoBean>();

		for (Iterator<Medioalmacenamiento> iterator = mediosAlmacenamiento
				.iterator(); iterator.hasNext();) {

			Medioalmacenamiento medioAlmacenamiento = iterator.next();
			MedioAlmacenamientoBean medioAlmacenamientoBean = transformarMedioAlmacenamiento(medioAlmacenamiento);
			mediosAlmacenamientoBeans.add(medioAlmacenamientoBean);
		}
		return mediosAlmacenamientoBeans;
	}

	private DosificacionBean transformarDosificacion(Dosificacion dosificacion) {

		DosificacionBean dosificacionBean = new DosificacionBeanImpl();

		dosificacionBean.setCodigo(dosificacion.getPkCodigoDosificacion());

		if (dosificacion.getDosificacionregistromensuals() != null) {
			dosificacionBean
					.setDosificacionRegistroMensualBeanList(transformarListaDosificacionRegistroMensual(dosificacion
							.getDosificacionregistromensuals()));
		}

		return dosificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarDosificacionRegistroMensual
	 * (pe.com.pacasmayo.sgcp.persistencia.
	 * dataObjects.Dosificacionregistromensual)
	 */
	public DosificacionRegistroMensualBean transformarDosificacionRegistroMensual(
			Dosificacionregistromensual dosificacionRegistroMensual) {

		if (dosificacionRegistroMensual == null
				|| dosificacionRegistroMensual
						.getPkCodigoDosificacionregistrom() == null)
			return null;

		DosificacionRegistroMensualBean dosificacionRegistroMensualBean = new DosificacionRegistroMensualBeanImpl();

		dosificacionRegistroMensualBean
				.setAnnoDosificacionRegistroMensua(dosificacionRegistroMensual
						.getAnnoDosificacionregistromensua());
		dosificacionRegistroMensualBean
				.setCantidadDosificacionRegistroMe(dosificacionRegistroMensual
						.getCantidadDosificacionregistrome());
		dosificacionRegistroMensualBean.setCodigo(dosificacionRegistroMensual
				.getPkCodigoDosificacionregistrom());
		dosificacionRegistroMensualBean
				.setMesDosificacionRegistroMensual(dosificacionRegistroMensual
						.getMesDosificacionregistromensual());
		dosificacionRegistroMensualBean
				.setPlanAnual(transformarPlanAnual(dosificacionRegistroMensual
						.getPlananual()));

		return dosificacionRegistroMensualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaDosificacionRegistroMensual(java.util.Collection)
	 */
	public DosificacionRegistroMensualBean[] transformarListaDosificacionRegistroMensual(
			Collection<Dosificacionregistromensual> dosificacionRegistroMensuals) {

		DosificacionRegistroMensualBean[] dosificacionRegistroMensualBeans = new DosificacionRegistroMensualBean[12];

		for (Iterator<Dosificacionregistromensual> iterator = dosificacionRegistroMensuals
				.iterator(); iterator.hasNext();) {

			Dosificacionregistromensual dosificacionRegistroMensual = iterator
					.next();
			DosificacionRegistroMensualBean dosificacionRegistroMensualBean = transformarDosificacionRegistroMensual(dosificacionRegistroMensual);

			dosificacionRegistroMensualBeans[dosificacionRegistroMensualBean
					.getMesDosificacionRegistroMensual() - 1] = dosificacionRegistroMensualBean;
		}

		return dosificacionRegistroMensualBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaPlanNecesidad
	 * (java.util.List)
	 */
	public List<PlanNecesidadBean> transformarListaPlanNecesidad(
			List<Plannecesidad> listaTemporalPlan) {
		List<PlanNecesidadBean> listaPlanNecesidad = new ArrayList<PlanNecesidadBean>();

		for (Iterator<Plannecesidad> iterator = listaTemporalPlan.iterator(); iterator
				.hasNext();) {

			Plannecesidad plannecesidad = iterator.next();
			PlanNecesidadBean planNecesidadBean = transformarPlanNecesidad(plannecesidad);
			listaPlanNecesidad.add(planNecesidadBean);
		}
		return listaPlanNecesidad;
	}

	public List<PlanNecesidadBean> transformarListaPlanNecesidad(
			Set<Plannecesidad> listaTemporalPlan) {
		List<PlanNecesidadBean> listaPlanNecesidad = new ArrayList<PlanNecesidadBean>();

		for (Iterator<Plannecesidad> iterator = listaTemporalPlan.iterator(); iterator
				.hasNext();) {

			Plannecesidad plannecesidad = iterator.next();
			PlanNecesidadBean planNecesidadBean = transformarPlanNecesidad(plannecesidad);
			listaPlanNecesidad.add(planNecesidadBean);
		}
		return listaPlanNecesidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPlanNecesidad
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plannecesidad)
	 */
	public PlanNecesidadBean transformarPlanNecesidad(
			Plannecesidad plannecesidad) {
		PlanNecesidadBean planNecesidadBean = new PlanNecesidadBeanImpl();
		planNecesidadBean.setCodigo(plannecesidad.getPkCodigoPlannecesidad());
		planNecesidadBean.setHojaRuta(transformarHojaRuta(plannecesidad
				.getHojaruta()));

		return planNecesidadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaConceptoRegistroMensual(java.util.Collection)
	 */
	public ConceptoRegistroMensualBean[] transformarListaConceptoRegistroMensual(
			Collection<Conceptoregistromensual> listaConceptoRegistroMensual) {

		ConceptoRegistroMensualBean[] conceptoRegistroMensualBeans = new ConceptoRegistroMensualBean[12];

		for (Iterator<Conceptoregistromensual> iterator = listaConceptoRegistroMensual
				.iterator(); iterator.hasNext();) {

			Conceptoregistromensual conceptoregistromensual = iterator.next();
			ConceptoRegistroMensualBean conceptoRegistroMensualBean = transformarConceptoRegistroMensual(conceptoregistromensual);

			conceptoRegistroMensualBeans[conceptoRegistroMensualBean
					.getMesConceptoregistromensual() - 1] = conceptoRegistroMensualBean;
		}

		return conceptoRegistroMensualBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarConceptoRegistroMensual
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Conceptoregistromensual)
	 */
	public ConceptoRegistroMensualBean transformarConceptoRegistroMensual(
			Conceptoregistromensual conceptoRegistroMensual) {

		if (conceptoRegistroMensual == null
				|| conceptoRegistroMensual.getPkCodigoConceptoregistromensu() == null)
			return null;

		ConceptoRegistroMensualBean conceptoRegistroMensualBean = new ConceptoRegistroMensualBeanImpl();

		conceptoRegistroMensualBean
				.setCantidadConceptoregistromens(conceptoRegistroMensual
						.getCantidadConceptoregistromensua());
		conceptoRegistroMensualBean.setCodigo(conceptoRegistroMensual
				.getPkCodigoConceptoregistromensu());
		conceptoRegistroMensualBean
				.setMesConceptoregistromensual(conceptoRegistroMensual
						.getMesConceptoregistromensual());
		conceptoRegistroMensualBean
				.setConceptoMensual(transformarConceptoMensual(conceptoRegistroMensual
						.getConceptomensual()));

		return conceptoRegistroMensualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarConceptoMensual
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Conceptomensual)
	 */
	public ConceptoMensualBean transformarConceptoMensual(
			Conceptomensual conceptoMensual) {

		if (conceptoMensual == null
				|| conceptoMensual.getPkCodigoConceptomensual() == null)
			return null;

		ConceptoMensualBean conceptoMensualBean = new ConceptoMensualBeanImpl();
		conceptoMensualBean.setCodigo(conceptoMensual
				.getPkCodigoConceptomensual());
		conceptoMensualBean.setProduccion(transformarProduccion(conceptoMensual
				.getProduccion()));
		conceptoMensualBean.setConcepto(transformarConcepto(conceptoMensual
				.getConcepto()));

		return conceptoMensualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaPlanNecesidad
	 * (java.util.List)
	 */
	public List<PlanCapacidadBean> transformarListaPlanCapacidad(
			List<Plancapacidad> listaTemporalPlan) {
		List<PlanCapacidadBean> listaPlanCapacidad = new ArrayList<PlanCapacidadBean>();

		for (Iterator<Plancapacidad> iterator = listaTemporalPlan.iterator(); iterator
				.hasNext();) {

			Plancapacidad plancapacidad = iterator.next();
			PlanCapacidadBean plancapacidadbean = this
					.transformarPlancapacidad(plancapacidad);

			listaPlanCapacidad.add(plancapacidadbean);
		}
		return listaPlanCapacidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaPlanNecesidad
	 * (java.util.List)
	 */
	public List<PlanCapacidadRegistroMensualBean> transformarListaPlanCapacidadMensual(
			List<Plancapacidadregistromensual> listaTemporalPlan) {
		List<PlanCapacidadRegistroMensualBean> listaPlanCapacidad = new ArrayList<PlanCapacidadRegistroMensualBean>();

		for (Iterator<Plancapacidadregistromensual> iterator = listaTemporalPlan
				.iterator(); iterator.hasNext();) {

			Plancapacidadregistromensual plancapacidadregmen = iterator.next();
			PlanCapacidadRegistroMensualBean plancapacidadregmenbean = transformarPlancapacidadregistromensu(plancapacidadregmen);

			listaPlanCapacidad.add(plancapacidadregmenbean);
		}
		return listaPlanCapacidad;
	}

	/**
	 * @param plancapacidad
	 * @return
	 */
	public PlanCapacidadBean transformarPlancapacidad(
			Plancapacidad plancapacidad) {

		PlanCapacidadBean plancapacidadBean = new PlanCapacidadBeanImpl();

		if (plancapacidad == null
				|| plancapacidad.getPkCodigoPlancapacidad() == null)
			return null;

		plancapacidadBean.setCodigo(plancapacidad.getPkCodigoPlancapacidad());

		PuestoTrabajoBean puestoTrabajo = this
				.transformarPuestoTrabajo(plancapacidad.getPuestotrabajo());
		plancapacidadBean.setPuestoTrabajo(puestoTrabajo);
		UnidadMedidaBean unidadbean = this
				.transformarUnidadMedida(plancapacidad.getUnidadmedida());
		plancapacidadBean.setAnnoPlancapacidad(plancapacidad
				.getAnnoPlancapacidad());
		plancapacidadBean.setVersionPlancapacidad(plancapacidad
				.getVersionPlancapacidad());
		plancapacidadBean.setUnidadMedida(unidadbean);
		if (plancapacidad.getEstadoplancapacidad() != null) {
			plancapacidadBean.setEstadoPlanCapacidad(this
					.transformarEstadoPlanCapacidad(plancapacidad
							.getEstadoplancapacidad()));
		}
		return plancapacidadBean;
	}

	private EstadoPlanCapacidadBean transformarEstadoPlanCapacidad(
			Estadoplancapacidad estadoplancapacidad) {
		EstadoPlanCapacidadBean estadoPlanCapacidadBean = new EstadoPlanCapacidadBeanImpl();
		estadoPlanCapacidadBean.setCodigo(estadoplancapacidad
				.getPkCodigoEstadoplancapacidad());
		estadoPlanCapacidadBean.setNombre(estadoplancapacidad
				.getNombreEstadoplancapacidad());
		estadoPlanCapacidadBean.setDescripcion(estadoplancapacidad
				.getDescripcionEstadoplancapacidad());
		return estadoPlanCapacidadBean;
	}

	/**
	 * 
	 */
	public PlanCapacidadBean transformarPlancapacidadFull(
			Plancapacidad plancapacidad) {

		PlanCapacidadBean plancapacidadBean = new PlanCapacidadBeanImpl();

		if (plancapacidad == null
				|| plancapacidad.getPkCodigoPlancapacidad() == null)
			return null;

		plancapacidadBean.setCodigo(plancapacidad.getPkCodigoPlancapacidad());

		PuestoTrabajoBean puestoTrabajo = this
				.transformarPuestoTrabajo(plancapacidad.getPuestotrabajo());
		plancapacidadBean.setPuestoTrabajo(puestoTrabajo);
		UnidadMedidaBean unidadbean = this
				.transformarUnidadMedida(plancapacidad.getUnidadmedida());
		plancapacidadBean.setUnidadMedida(unidadbean);
		plancapacidadBean.setAnnoPlancapacidad(plancapacidad
				.getAnnoPlancapacidad());
		plancapacidadBean.setVersionPlancapacidad(plancapacidad
				.getVersionPlancapacidad());
		plancapacidadBean.setPlancapacidadregistromensual(this
				.transformarListaPlanCapRegMen(plancapacidad
						.getPlancapacidadregistromensuals()));
		if (plancapacidad.getEstadoplancapacidad() != null) {
			plancapacidadBean.setEstadoPlanCapacidad(this
					.transformarEstadoPlanCapacidad(plancapacidad
							.getEstadoplancapacidad()));
		}
		if (plancapacidad.getLineanegocio() != null) {
			plancapacidadBean.setLineaNegocio(this
					.transformarLineaNegocio(plancapacidad.getLineanegocio()));
		}
		return plancapacidadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarPlancapacidadregistromensu
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects
	 * .Plancapacidadregistromensual)
	 */
	public PlanCapacidadRegistroMensualBean transformarPlancapacidadregistromensu(
			Plancapacidadregistromensual plancapacidadregmen) {

		PlanCapacidadRegistroMensualBean plancapacidadregbean = new PlanCapacidadRegistroMensualBeanImpl();

		if (plancapacidadregmen == null
				|| plancapacidadregmen.getPkCodigoPlancapacidadregistro() == null)
			return null;

		plancapacidadregbean.setCodigo(plancapacidadregmen
				.getPkCodigoPlancapacidadregistro());
		plancapacidadregbean.setAnnoPlancapregmensual(plancapacidadregmen
				.getAnnoPlancapregmensual());
		plancapacidadregbean.setCantidadPlancapregmensual(plancapacidadregmen
				.getCantidadPlancapregmensual());
		plancapacidadregbean.setMesPlancapregmensual(plancapacidadregmen
				.getMesPlancapregmensual());
		PlanCapacidadBean plancap = this
				.transformarPlancapacidad(plancapacidadregmen
						.getPlancapacidad());
		plancapacidadregbean.setPlancapacidad(plancap);

		return plancapacidadregbean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaPlanCapRegMen
	 * (java.util.Set)
	 */
	public PlanCapacidadRegistroMensualBean[] transformarListaPlanCapRegMen(
			Set<Plancapacidadregistromensual> listaPlanCapRegMenBeans) {

		PlanCapacidadRegistroMensualBean[] planCapacidadImplBeans = new PlanCapacidadRegistroMensualBean[12];

		for (Iterator<Plancapacidadregistromensual> iterator = listaPlanCapRegMenBeans
				.iterator(); iterator.hasNext();) {

			Plancapacidadregistromensual plancapacidadregmen = iterator.next();
			PlanCapacidadRegistroMensualBean plancapacidadregmenbean = this
					.transformarPlancapacidadregistromensu(plancapacidadregmen);

			planCapacidadImplBeans[plancapacidadregmenbean
					.getMesPlancapregmensual() - 1] = plancapacidadregmenbean;
		}

		return planCapacidadImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarTasaRealProduccionRegMen(java.util.Set)
	 */
	public TasaRealProduccionRegistroMensualBean[] transformarTasaRealProduccionRegMen(
			Set<Tasarealprodregistromensual> tasarealproduccionregmen) {

		TasaRealProduccionRegistroMensualBean[] tasarealregmenbeans = new TasaRealProduccionRegistroMensualBeanImpl[12];

		for (Iterator<Tasarealprodregistromensual> iterator = tasarealproduccionregmen
				.iterator(); iterator.hasNext();) {

			Tasarealprodregistromensual tasaprodregmen = (Tasarealprodregistromensual) iterator
					.next();
			TasaRealProduccionRegistroMensualBean tasaprodregmenbean = transformarTasaRealProduccionRegistroMensual(tasaprodregmen);
			tasarealregmenbeans[tasaprodregmenbean
					.getMesTasarealprodregmensual() - 1] = tasaprodregmenbean;
		}

		return tasarealregmenbeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTasaRealProduccion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tasarealproduccion)
	 */
	public TasaRealProduccionBean transformarTasaRealProduccion(
			Tasarealproduccion tasarealprod) {

		TasaRealProduccionBean tasarealprodBean = new TasaRealProduccionBeanImpl();

		if (tasarealprod == null
				|| tasarealprod.getPkCodigoTasarealproduccion() == null)
			return null;

		tasarealprodBean
				.setCodigo(tasarealprod.getPkCodigoTasarealproduccion());
		ProduccionBean produccionbean = transformarProduccion(tasarealprod
				.getProduccion());
		tasarealprodBean.setProduccion(produccionbean);
		PuestoTrabajoBean puestoTrabajo = transformarPuestoTrabajo(tasarealprod
				.getPuestotrabajo());
		tasarealprodBean.setPuestotrabajo(puestoTrabajo);
		UnidadMedidaBean unidadbean = transformarUnidadMedida(tasarealprod
				.getUnidadmedida());
		tasarealprodBean.setUnidadmedida(unidadbean);

		if (tasarealprod.getTasarealprodregistromensuals() != null)
			tasarealprodBean
					.setTasaRealProduccionRegistroMensual(transformarTasaRealProduccionRegMen(tasarealprod
							.getTasarealprodregistromensuals()));

		if (tasarealprod.getMaximoTasarealproduccion() != null)
			tasarealprodBean.setMaximo(tasarealprod
					.getMaximoTasarealproduccion());

		if (tasarealprod.getMinimoTasarealproduccion() != null)
			tasarealprodBean.setMinimo(tasarealprod
					.getMinimoTasarealproduccion());

		return tasarealprodBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarTasaRealProduccionRegistroMensual
	 * (pe.com.pacasmayo.sgcp.persistencia
	 * .dataObjects.Tasarealprodregistromensual)
	 */
	public TasaRealProduccionRegistroMensualBean transformarTasaRealProduccionRegistroMensual(
			Tasarealprodregistromensual tasarealprodregmen) {

		TasaRealProduccionRegistroMensualBean tasarealprodregmenBean = new TasaRealProduccionRegistroMensualBeanImpl();

		tasarealprodregmenBean.setCodigo(tasarealprodregmen
				.getPkCodigoTasarealprodregistrom());
		tasarealprodregmenBean.setAnnoTasarealprodregmensual(tasarealprodregmen
				.getAnnoTasarealprodregmensual());
		tasarealprodregmenBean
				.setCantidadTasarealprodregmensual(tasarealprodregmen
						.getCantidadTasarealprodregmensual());
		tasarealprodregmenBean.setMesTasarealprodregmensual(tasarealprodregmen
				.getMesTasarealprodregmensual());

		return tasarealprodregmenBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTasaRealProduccion(java.util.List)
	 */
	public List<TasaRealProduccionBean> transformarListaTasaRealProduccion(
			List<Tasarealproduccion> listaTasaRealProduccion) {

		List<TasaRealProduccionBean> listaRetornar = new ArrayList<TasaRealProduccionBean>();
		for (Iterator<Tasarealproduccion> iterator = listaTasaRealProduccion
				.iterator(); iterator.hasNext();) {

			Tasarealproduccion tasaTealProduccion = iterator.next();

			listaRetornar
					.add(transformarTasaRealProduccion(tasaTealProduccion));
		}

		return listaRetornar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaCapacidadOperativaRM(java.util.Set)
	 */
	public CapacidadOperativaRegistroMensualBean[] transformarListaCapacidadOperativaRM(
			Set<Capacidadoperativaregistromensu> capacidadoperativaregistromensus) {
		CapacidadOperativaRegistroMensualBean[] capacidadesOperativasRM = new CapacidadOperativaRegistroMensualBean[12];

		for (Iterator<Capacidadoperativaregistromensu> iterator = capacidadoperativaregistromensus
				.iterator(); iterator.hasNext();) {
			Capacidadoperativaregistromensu capacidadoperativaregistromensu = iterator
					.next();
			CapacidadOperativaRegistroMensualBean capacidadOperativaRegistroMensualBean = this
					.transformarCapacidadOperativaRM(capacidadoperativaregistromensu);
			capacidadesOperativasRM[capacidadOperativaRegistroMensualBean
					.getMesCapacidadOperativaregistromensual() - 1] = capacidadOperativaRegistroMensualBean;

		}

		return capacidadesOperativasRM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaCapacidadOperativaRegistroMensual(java.util.Set)
	 */
	public List<CapacidadOperativaRegistroMensualBean> transformarListaCapacidadOperativaRegistroMensual(
			Set<Capacidadoperativaregistromensu> listaCapOperRegMen) {

		List<CapacidadOperativaRegistroMensualBean> listaRetornar = new ArrayList<CapacidadOperativaRegistroMensualBean>();
		for (Iterator<Capacidadoperativaregistromensu> iterator = listaCapOperRegMen
				.iterator(); iterator.hasNext();) {

			Capacidadoperativaregistromensu capacidadoperativaregistromensu = iterator
					.next();

			listaRetornar
					.add(transformarCapacidadOperativaRM(capacidadoperativaregistromensu));
		}

		return listaRetornar;
	}

	private CapacidadOperativaRegistroMensualBean transformarCapacidadOperativaRM(
			Capacidadoperativaregistromensu capacidadoperativaregistromensu) {
		CapacidadOperativaRegistroMensualBean capacidadOperativaRegistroMensualBean = new CapacidadOperativaRegistroMensualBeanImpl();

		if (capacidadoperativaregistromensu == null
				|| capacidadoperativaregistromensu
						.getPkCodigoCapacidadoperativareg() == null)
			return null;

		capacidadOperativaRegistroMensualBean
				.setCodigo(capacidadoperativaregistromensu
						.getPkCodigoCapacidadoperativareg());
		capacidadOperativaRegistroMensualBean
				.setAnnoCapacidadOperativaRegistroMensual(capacidadoperativaregistromensu
						.getAnnoCapacidadoperativaregistro());
		capacidadOperativaRegistroMensualBean
				.setCantidadCapacidadOperativaRegistroMensual(capacidadoperativaregistromensu
						.getCantidadRegistroCapacidadoper());

		capacidadOperativaRegistroMensualBean.setCantidadCapacidadOperativa(nf
				.format(capacidadoperativaregistromensu
						.getCantidadRegistroCapacidadoper()));

		capacidadOperativaRegistroMensualBean
				.setMesCapacidadOperativaregistromensual(capacidadoperativaregistromensu
						.getMesCapacidadoperativaregistrom());

		return capacidadOperativaRegistroMensualBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaMovimiento
	 * (java.util.List)
	 */
	public List<MovimientoBean> transformarListaMovimiento(
			List<Movimiento> movimientos) {
		List<MovimientoBean> movimientoBeans = new ArrayList<MovimientoBean>();

		for (Iterator<Movimiento> iterator = movimientos.iterator(); iterator
				.hasNext();) {
			Movimiento movimiento = iterator.next();
			MovimientoBean periodocontableBean = transformarMovimiento(movimiento);
			if (periodocontableBean != null) {
				movimientoBeans.add(periodocontableBean);
			}
		}

		return movimientoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarCapacidadOperativa
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadoperativa)
	 */
	public CapacidadOperativaBean transformarCapacidadOperativa(
			Capacidadoperativa capacidadOperativa) {

		if (capacidadOperativa == null
				|| capacidadOperativa.getPkCodigoCapacidadoperativa() == null)
			return null;

		CapacidadOperativaBean capacidadOperativaBean = new CapacidadOperativaBeanImpl();

		capacidadOperativaBean.setCodigo(capacidadOperativa
				.getPkCodigoCapacidadoperativa());

		if (capacidadOperativa.getOperacion() != null)
			capacidadOperativaBean
					.setOperacion(transformarOperacionParaPlanAnual(capacidadOperativa
							.getOperacion()));

		if (capacidadOperativa.getTasarealproduccion() != null)
			capacidadOperativaBean
					.setTasaRealProduccion(transformarTasaRealProduccion(capacidadOperativa
							.getTasarealproduccion()));
		if (capacidadOperativa.getTipocapacidadoperativa() != null)

			capacidadOperativaBean
					.setTipoCapacidadOperativa(transformarTipoCapacidadOperativa(capacidadOperativa
							.getTipocapacidadoperativa()));

		if (capacidadOperativa.getCapacidadoperativaregistromensus() != null)
			capacidadOperativaBean
					.setListaCapacidadOperativaRegistroMensual(transformarListaCapacidadOperativaRM(capacidadOperativa
							.getCapacidadoperativaregistromensus()));

		return capacidadOperativaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarTipoCapacidadOperativa
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocapacidadoperativa)
	 */
	public TipoCapacidadOperativaBean transformarTipoCapacidadOperativa(
			Tipocapacidadoperativa tipoCapacidadOperativa) {

		if (tipoCapacidadOperativa == null
				|| tipoCapacidadOperativa.getPkCodigoTipocapacidadoperativ() == null)
			return null;

		TipoCapacidadOperativaBean tipoCapacidadOperativaBean = new TipoCapacidadOperativaBeanImpl();

		tipoCapacidadOperativaBean.setCodigo(tipoCapacidadOperativa
				.getPkCodigoTipocapacidadoperativ());

		if (tipoCapacidadOperativa.getDescripcionTipocapacidad() != null)
			tipoCapacidadOperativaBean.setDescripcion(tipoCapacidadOperativa
					.getDescripcionTipocapacidad().trim());

		if (tipoCapacidadOperativa.getNombreTipocapacidad() != null)
			tipoCapacidadOperativaBean.setNombre(tipoCapacidadOperativa
					.getNombreTipocapacidad().trim());

		return tipoCapacidadOperativaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarMovimiento(
	 * pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimiento)
	 */
	public MovimientoBean transformarMovimiento(Movimiento movimiento) {

		if (movimiento == null || movimiento.getPkCodigoMovimiento() == null)
			return null;

		MovimientoBean movimientoBean = new MovimientoBeanImpl();

		movimientoBean
				.setCantidadMovimiento(movimiento.getCantidadMovimiento());
		movimientoBean.setCodigo(movimiento.getPkCodigoMovimiento());
		movimientoBean
				.setDocumentomaterial(transformarDocumentoMaterial(movimiento
						.getDocumentomaterial()));
		movimientoBean
				.setEstadomovimiento(transformarEstadoMovimiento(movimiento
						.getEstadomovimiento()));
		movimientoBean.setFactorVolqueteMovimiento(movimiento
				.getFactorVolqueteMovimiento());
		movimientoBean.setFechaMovimiento(movimiento.getFechaMovimiento());
		movimientoBean.setLineanegocio(transformarLineaNegocio(movimiento
				.getLineanegocio()));
		movimientoBean
				.setMedioalmacenamiento(transformarMedioAlmacenamiento(movimiento
						.getMedioalmacenamiento()));
		movimientoBean.setNumeroViajesMovimiento(movimiento
				.getNumeroViajesMovimiento());
		movimientoBean.setOrigenMovimiento(movimiento.getOrigenMovimiento());
		movimientoBean
				.setProducto(transformarProducto(movimiento.getProducto()));
		movimientoBean.setTipomovimiento(transformarTipoMovimiento(movimiento
				.getTipomovimiento()));
		movimientoBean
				.setUbicacionByFkCodigoUbicacionDestino(transformarUbicacion(movimiento
						.getUbicacionByFkCodigoUbicacionDestino()));
		movimientoBean
				.setUbicacionByFkCodigoUbicacionOrigen(transformarUbicacion(movimiento
						.getUbicacionByFkCodigoUbicacionOrigen()));
		movimientoBean.setUnidadmedida(transformarUnidadMedida(movimiento
				.getUnidadmedida()));
		movimientoBean.setFactorHumedad(movimiento.getFactorHumedad());
		movimientoBean.setCantidadMovimientoHumedad(movimiento
				.getCantidadMovimientoHumedad());
		return movimientoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoMovimiento(java.util.List)
	 */
	public List<EstadoMovimientoBean> transformarListaEstadoMovimiento(
			List<Estadomovimiento> estadoMovimientos) {
		List<EstadoMovimientoBean> estadoMovimientoBeans = new ArrayList<EstadoMovimientoBean>();

		for (Iterator<Estadomovimiento> iterator = estadoMovimientos.iterator(); iterator
				.hasNext();) {
			Estadomovimiento estadoMovimiento = iterator.next();
			EstadoMovimientoBean estadoMovBean = transformarEstadoMovimiento(estadoMovimiento);
			if (estadoMovBean != null) {
				estadoMovimientoBeans.add(estadoMovBean);
			}
		}

		return estadoMovimientoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoMovimiento
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadomovimiento)
	 */
	public EstadoMovimientoBean transformarEstadoMovimiento(
			Estadomovimiento estadomovimiento) {
		if (estadomovimiento == null
				|| estadomovimiento.getPkCodigoEstadomovimiento() == null) {
			return null;
		}

		EstadoMovimientoBean estadoMovimientoBean = new EstadoMovimientoBeanImpl();

		estadoMovimientoBean.setCodigo(estadomovimiento
				.getPkCodigoEstadomovimiento());
		estadoMovimientoBean.setNombreEstadomovimiento(estadomovimiento
				.getNombreEstadomovimiento());

		return estadoMovimientoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaDocumentoMaterial(java.util.List)
	 */
	public List<DocumentoMaterialBean> transformarListaDocumentoMaterial(
			List<Documentomaterial> documentomateriales) {
		List<DocumentoMaterialBean> documentoMaterialBeans = new ArrayList<DocumentoMaterialBean>();

		for (Iterator<Documentomaterial> iterator = documentomateriales
				.iterator(); iterator.hasNext();) {
			Documentomaterial documentomaterial = iterator.next();
			DocumentoMaterialBean documentoMaterialBean = transformarDocumentoMaterial(documentomaterial);
			if (documentoMaterialBean != null) {
				documentoMaterialBeans.add(documentoMaterialBean);
			}
		}
		return documentoMaterialBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarDocumentoMaterial
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Documentomaterial)
	 */
	public DocumentoMaterialBean transformarDocumentoMaterial(
			Documentomaterial documentomaterial) {

		if (documentomaterial == null
				|| documentomaterial.getPkCodigoDocumentomaterial() == null) {
			return null;
		}

		DocumentoMaterialBean documentoMaterialBean = new DocumentoMaterialBeanImpl();
		documentoMaterialBean.setOrigenSapMovimiento(documentomaterial
				.getOrigenSapMovimiento());
		documentoMaterialBean.setCodigo(documentomaterial
				.getPkCodigoDocumentomaterial());
		documentoMaterialBean.setUsuario(transformarUsuario(documentomaterial
				.getUsuario()));
		documentoMaterialBean
				.setTipodocumentomaterial(transformarTipoDocumentoMaterial(documentomaterial
						.getTipodocumentomaterial()));
		documentoMaterialBean.setSociedad(transformarSociedad(documentomaterial
				.getSociedad()));
		documentoMaterialBean
				.setPeriodocontable(transformarPeriodoContable(documentomaterial
						.getPeriodocontable()));
		documentoMaterialBean.setFechaDocumentomaterial(documentomaterial
				.getFechaDocumentomaterial());
		/**
		 * Agregado por JORDAN
		 */
		documentoMaterialBean.setTicket(documentomaterial.getTicket());
		documentoMaterialBean
				.setObservacion(documentomaterial.getObservacion());
		documentoMaterialBean
				.setNotaEntrega(documentomaterial.getNotaEntrega());
		documentoMaterialBean.setPlaca(documentomaterial.getPlaca());

		return documentoMaterialBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTipoDocumentoMaterial(java.util.List)
	 */
	public List<TipoDocumentoMaterialBean> transformarListaTipoDocumentoMaterial(
			List<Tipodocumentomaterial> tipoDocuentomateriales) {
		List<TipoDocumentoMaterialBean> tipoTocumentoMateriaBeans = new ArrayList<TipoDocumentoMaterialBean>();

		for (Iterator<Tipodocumentomaterial> iterator = tipoDocuentomateriales
				.iterator(); iterator.hasNext();) {
			Tipodocumentomaterial tipodocumentomaterial = iterator.next();
			TipoDocumentoMaterialBean tipoDocumentoMateriaBean = transformarTipoDocumentoMaterial(tipodocumentomaterial);
			if (tipoDocumentoMateriaBean != null) {
				tipoTocumentoMateriaBeans.add(tipoDocumentoMateriaBean);
			}
		}

		return tipoTocumentoMateriaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarTipoDocumentoMaterial
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipodocumentomaterial)
	 */
	public TipoDocumentoMaterialBean transformarTipoDocumentoMaterial(
			Tipodocumentomaterial tipodocumentomaterial) {
		if (tipodocumentomaterial == null
				|| tipodocumentomaterial.getPkCodigoTipodocumentomaterial() == null) {
			return null;
		}
		TipoDocumentoMaterialBean tipoDocumentoMateriaBean = new TipoDocumentoMaterialBeanImpl();
		tipoDocumentoMateriaBean.setCodigo(tipodocumentomaterial
				.getPkCodigoTipodocumentomaterial());
		tipoDocumentoMateriaBean.setNombre(tipodocumentomaterial
				.getNombreTipodocumentomaterial());

		return tipoDocumentoMateriaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaPeriodoContable(java.util.List)
	 */
	public List<PeriodoContableBean> transformarListaPeriodoContable(
			List<Periodocontable> periodosContables) {
		List<PeriodoContableBean> periodocontableBeans = new ArrayList<PeriodoContableBean>();
		for (Iterator<Periodocontable> iterator = periodosContables.iterator(); iterator
				.hasNext();) {
			Periodocontable periodocontable = iterator.next();
			PeriodoContableBean periodoContableBean = transformarPeriodoContable(periodocontable);
			if (periodoContableBean != null) {
				periodocontableBeans.add(periodoContableBean);
			}
		}
		return periodocontableBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPeriodoContable
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable)
	 */
	public PeriodoContableBean transformarPeriodoContable(
			Periodocontable periodoContable) {
		if (periodoContable == null
				|| periodoContable.getPkCodigoPeridocontable() == null) {
			return null;
		}
		PeriodoContableBean periodoContableBean = new PeriodoContableBeanImpl();
		periodoContableBean.setCodigo(periodoContable
				.getPkCodigoPeridocontable());
		periodoContableBean.setAnoPeriodocontable(periodoContable
				.getAnoPeriodocontable());
		periodoContableBean.setMesPeriodocontable(periodoContable
				.getMesPeriodocontable());

		periodoContableBean.setCerradoPeridocontable(periodoContable
				.getCerradoPeridocontable());

		return periodoContableBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaMovimientoAjuste(java.util.List)
	 */
	public List<MovimientoAjusteBean> transformarListaMovimientoAjuste(
			List<Movimientoajuste> movimientoAjustes) {
		List<MovimientoAjusteBean> beans = new ArrayList<MovimientoAjusteBean>();
		for (Iterator<Movimientoajuste> iterator = movimientoAjustes.iterator(); iterator
				.hasNext();) {
			Movimientoajuste movimientoajuste = iterator.next();
			MovimientoAjusteBean bean = transformarMovimientoAjuste(movimientoajuste);
			if (bean != null) {
				beans.add(bean);
			}
		}
		return beans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarMovimientoAjuste
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajuste)
	 */
	public MovimientoAjusteBean transformarMovimientoAjuste(
			Movimientoajuste movimientoajuste) {
		if (movimientoajuste == null
				|| movimientoajuste.getPkCodigoMovimientoajuste() == null) {
			return null;
		}

		MovimientoAjusteBean bean = new MovimientoAjusteBeanImpl();

		bean.setCodigo(movimientoajuste.getPkCodigoMovimientoajuste());
		bean.setCantidadMovimientoajuste(movimientoajuste
				.getCantidadMovimientoajuste());
		/*
		 * bean .setConsumocomponentepuestotrabajo(
		 * transformarConsumoComponentePuestoTrabajo(movimientoajuste.
		 * .getConsumocomponentepuestotrabajo()));
		 */
		bean.setDescripcionMovimientoajuste(movimientoajuste
				.getDescripcionMovimientoajuste());
		bean.setEstadomovimiento(transformarEstadoMovimiento(movimientoajuste
				.getEstadomovimiento()));
		bean.setProducto(transformarProducto(movimientoajuste.getProducto()));
		/*
		 * bean.setPuestotrabajo(transformarPuestoTrabajo(movimientoajuste
		 * .getPuestotrabajo()));
		 */
		bean.setTipomovimiento(transformarTipoMovimiento(movimientoajuste
				.getTipomovimiento()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaConsumoComponentePuestoTrabajo(java.util.List)
	 */
	public List<ConsumoComponentePuestoTrabajoBean> transformarListaConsumoComponentePuestoTrabajo(
			List<Consumocomponentepuestotrabajo> consumoComponentePuestoTrabajos) {
		List<ConsumoComponentePuestoTrabajoBean> beans = new ArrayList<ConsumoComponentePuestoTrabajoBean>();

		for (Iterator<Consumocomponentepuestotrabajo> iterator = consumoComponentePuestoTrabajos
				.iterator(); iterator.hasNext();) {
			Consumocomponentepuestotrabajo consumoComponentePuestoTrabajo = iterator
					.next();
			ConsumoComponentePuestoTrabajoBean bean = transformarConsumoComponentePuestoTrabajo(consumoComponentePuestoTrabajo);
			if (bean != null) {
				beans.add(bean);
			}
		}
		return beans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarConsumoComponentePuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.persistencia
	 * .dataObjects.Consumocomponentepuestotrabajo)
	 */
	public ConsumoComponentePuestoTrabajoBean transformarConsumoComponentePuestoTrabajo(
			Consumocomponentepuestotrabajo consumoComponentePuestoTrabajo) {
		if (consumoComponentePuestoTrabajo == null
				|| consumoComponentePuestoTrabajo
						.getPkCodigoConsumocomponentepues() == null) {
			return null;
		}
		ConsumoComponentePuestoTrabajoBean bean = new ConsumoComponentePuestoTrabajoBeanImpl();
		bean.setCodigo(consumoComponentePuestoTrabajo
				.getPkCodigoConsumocomponentepues());
		bean.setConsumocomponenteajuste(transformarConsumoComponenteAjuste(consumoComponentePuestoTrabajo
				.getConsumocomponenteajuste()));
		bean.setDiferenciaConsumocomponentepue(consumoComponentePuestoTrabajo
				.getDiferenciaConsumocomponentepue());

		bean.setObservacionConsumocomponentepu(consumoComponentePuestoTrabajo
				.getObservacionConsumocomponentepu());
		bean.setPoderCalorificoConsumocompone(consumoComponentePuestoTrabajo
				.getPoderCalorificoConsumocompone());
		bean.setPorcentajeConsumocomponentepue(consumoComponentePuestoTrabajo
				.getPorcentajeConsumocomponentepue());
		bean.setPorcentajeRealConsumocomponen(consumoComponentePuestoTrabajo
				.getPorcentajeRealConsumocomponen());
		bean.setPuestotrabajoproduccion(transformarPuestoTrabajoProduccion(consumoComponentePuestoTrabajo
				.getPuestotrabajoproduccion()));
		bean.setTmConsumocomponentepuestotraba(consumoComponentePuestoTrabajo
				.getTmConsumocomponentepuestotraba());
		bean.setTmRealConsumocomponentepuesto(consumoComponentePuestoTrabajo
				.getTmRealConsumocomponentepuesto());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaConsumoComponenteAjuste(java.util.List)
	 */
	public List<ConsumoComponenteAjusteBean> transformarListaConsumoComponenteAjuste(
			List<Consumocomponenteajuste> listaDataObjects) {
		List<ConsumoComponenteAjusteBean> listaBeans = new ArrayList<ConsumoComponenteAjusteBean>();

		for (Iterator<Consumocomponenteajuste> iterator = listaDataObjects
				.iterator(); iterator.hasNext();) {
			Consumocomponenteajuste dataObject = iterator.next();
			ConsumoComponenteAjusteBean bean = transformarConsumoComponenteAjuste(dataObject);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarConsumoComponenteAjuste
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponenteajuste)
	 */
	public ConsumoComponenteAjusteBean transformarConsumoComponenteAjuste(
			Consumocomponenteajuste dataObject) {
		if (dataObject == null
				|| dataObject.getPkCodigoConsumocomponenteajus() == null) {
			return null;
		}

		ConsumoComponenteAjusteBean bean = new ConsumoComponenteAjusteBeanImpl();
		bean.setCodigo(dataObject.getPkCodigoConsumocomponenteajus());
		bean.setComponente(transformarComponente(dataObject.getComponente()));

		bean.setDiferenciaConsumocomponenteaju(dataObject
				.getDiferenciaConsumocomponenteaju());
		bean.setPorcentajeConsumocomponenteaju(dataObject
				.getPorcentProduccConsucompajuste());
		bean.setPorcentajeRealConsumocomponen(dataObject
				.getPorcentajeRealConsumocomponen());
		bean.setTmConsumocomponenteajuste(dataObject
				.getTmProdConsumocomponenteajus());
		bean.setTmRealConsumocomponenteajuste(dataObject
				.getTmRealConsumocomponenteajuste());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaPuestoTrabajoProduccion(java.util.List)
	 */
	public List<PuestoTrabajoProduccionBean> transformarListaPuestoTrabajoProduccion(
			List<Puestotrabajoproduccion> listaDataObjects) {
		List<PuestoTrabajoProduccionBean> listaBeans = new ArrayList<PuestoTrabajoProduccionBean>();

		for (Iterator<Puestotrabajoproduccion> iterator = listaDataObjects
				.iterator(); iterator.hasNext();) {
			Puestotrabajoproduccion dataObject = iterator.next();
			PuestoTrabajoProduccionBean bean = transformarPuestoTrabajoProduccion(dataObject);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarPuestoTrabajoProduccion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion)
	 */
	public PuestoTrabajoProduccionBean transformarPuestoTrabajoProduccion(
			Puestotrabajoproduccion dataObject) {
		if (dataObject == null
				|| dataObject.getPkCodigoPuestotrabajoproducci() == null) {
			return null;
		}

		PuestoTrabajoProduccionBean bean = new PuestoTrabajoProduccionBeanImpl();
		bean.setCodigo(dataObject.getPkCodigoPuestotrabajoproducci());
		bean.setAjusteproducto(transformarAjusteProducto(dataObject
				.getAjusteproducto()));
		bean.setPuestotrabajo(transformarPuestoTrabajo(dataObject
				.getPuestotrabajo()));

		bean.setHrAjustePuestotrabajoproducci(dataObject
				.getHrAjustePuestotrabajoproducci());
		bean.setHrPuestotrabajoproduccion(dataObject
				.getHrPuestotrabajoproduccion());
		bean.setHrRealPuestotrabajoproduccion(dataObject
				.getHrRealPuestotrabajoproduccion());
		bean.setKcalPuestotrabajoproduccion(dataObject
				.getKcalPuestotrabajoproduccion());
		bean.setKcalRealPuestotrabajoproducci(dataObject
				.getKcalRealPuestotrabajoproducci());
		bean.setTmAjustePuestotrabajoproducci(dataObject
				.getTmAjustePuestotrabajoproducci());
		bean.setTmphPuestotrabajoproduccion(dataObject
				.getTmphPuestotrabajoproduccion());
		bean.setTmphRealPuestotrabajoproducci(dataObject
				.getTmphRealPuestotrabajoproducci());
		bean.setTmPuestotrabajoproduccion(dataObject
				.getTmPuestotrabajoproduccion());
		bean.setTmRealPuestotrabajoproduccion(dataObject
				.getTmRealPuestotrabajoproduccion());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaAjusteProducto
	 * (java.util.List)
	 */
	public List<AjusteProductoBean> transformarListaAjusteProducto(
			List<Ajusteproducto> ajusteProductos) {
		List<AjusteProductoBean> listaBeans = new ArrayList<AjusteProductoBean>();

		for (Iterator<Ajusteproducto> iterator = ajusteProductos.iterator(); iterator
				.hasNext();) {
			Ajusteproducto dataObject = iterator.next();
			AjusteProductoBean bean = transformarAjusteProducto(dataObject);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarAjusteProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto)
	 */
	public AjusteProductoBean transformarAjusteProducto(
			Ajusteproducto ajusteProducto) {
		if (ajusteProducto == null
				|| ajusteProducto.getPkCodigoAjusteproducto() == null) {
			return null;
		}

		AjusteProductoBean bean = new AjusteProductoBeanImpl();
		bean.setPlantillagrupoajuste(transformarPlantillaGrupoAjuste(ajusteProducto
				.getPlantillagrupoajuste()));
		bean.setAjusteproduccion(transformarAjusteProduccion(ajusteProducto
				.getAjusteproduccion()));

		bean.setCodigo(ajusteProducto.getPkCodigoAjusteproducto());
		bean.setEstadoajusteproducto(transformarEstadoAjusteProducto(ajusteProducto
				.getEstadoajusteproducto()));
		bean.setProducto(transformarProducto((ajusteProducto.getProducto())));

		bean.setUsuarioByFkCodigoUsuarioAjusta(transformarUsuario(ajusteProducto
				.getUsuarioByFkCodigoUsuarioAjusta()));
		bean.setUsuarioByFkCodigoUsuarioAprueba(transformarUsuario(ajusteProducto
				.getUsuarioByFkCodigoUsuarioAprueba()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoAjusteProducto(java.util.List)
	 */
	public List<EstadoAjusteProductoBean> transformarListaEstadoAjusteProducto(
			List<Estadoajusteproducto> estadoAjusteProductos) {
		List<EstadoAjusteProductoBean> listaBeans = new ArrayList<EstadoAjusteProductoBean>();

		for (Iterator<Estadoajusteproducto> iterator = estadoAjusteProductos
				.iterator(); iterator.hasNext();) {
			Estadoajusteproducto estadoajusteproducto = iterator.next();
			EstadoAjusteProductoBean bean = new EstadoAjusteProductoBeanImpl();
			bean = transformarEstadoAjusteProducto(estadoajusteproducto);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarEstadoAjusteProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoajusteproducto)
	 */
	public EstadoAjusteProductoBean transformarEstadoAjusteProducto(
			Estadoajusteproducto estadoAjusteProducto) {
		if (estadoAjusteProducto == null
				|| estadoAjusteProducto.getPkCodigoEstadoajusteproducto() == null) {
			return null;
		}
		EstadoAjusteProductoBean bean = new EstadoAjusteProductoBeanImpl();

		bean.setCodigo(estadoAjusteProducto.getPkCodigoEstadoajusteproducto());
		bean.setNombre(estadoAjusteProducto.getNombreEstadoajusteproducto());
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaBalanceProducto(java.util.List)
	 */
	public List<BalanceProductoBean> transformarListaBalanceProducto(
			List<Balanceproducto> balanceProductos) {
		List<BalanceProductoBean> listaBeans = new ArrayList<BalanceProductoBean>();

		for (Iterator<Balanceproducto> iterator = balanceProductos.iterator(); iterator
				.hasNext();) {
			Balanceproducto estadoajusteproducto = iterator.next();
			BalanceProductoBean bean = new BalanceProductoBeanImpl();
			bean = transformarBalanceProducto(estadoajusteproducto);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarBalanceProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Balanceproducto)
	 */
	public BalanceProductoBean transformarBalanceProducto(
			Balanceproducto balanceProducto) {
		if (balanceProducto == null
				|| balanceProducto.getPkCodigoBalanceproducto() == null) {
			return null;
		}

		BalanceProductoBean bean = new BalanceProductoBeanImpl();
		bean.setAjusteproducto(transformarAjusteProducto(balanceProducto
				.getAjusteproducto()));
		bean.setCodigo(balanceProducto.getPkCodigoBalanceproducto());
		bean.setConcepto(transformarConcepto(balanceProducto.getConcepto()));
		bean.setMontoBalanceproducto(balanceProducto.getMontoBalanceproducto());
		bean.setTipobalance(transformarTipoBalance(balanceProducto
				.getTipobalance()));
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaTipoBalance
	 * (java.util.List)
	 */
	public List<TipoBalanceBean> transformarListaTipoBalance(
			List<Tipobalance> tipoBalances) {
		List<TipoBalanceBean> listaBeans = new ArrayList<TipoBalanceBean>();

		for (Iterator<Tipobalance> iterator = tipoBalances.iterator(); iterator
				.hasNext();) {
			Tipobalance estadoajusteproducto = iterator.next();
			TipoBalanceBean bean = new TipoBalanceBeanImpl();
			bean = transformarTipoBalance(estadoajusteproducto);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTipoBalance
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipobalance)
	 */
	public TipoBalanceBean transformarTipoBalance(Tipobalance tipoBalance) {
		if (tipoBalance == null || tipoBalance.getPkCodigoTipobalance() == null) {
			return null;
		}

		TipoBalanceBean bean = new TipoBalanceBeanImpl();
		bean.setCodigo(tipoBalance.getPkCodigoTipobalance());
		bean.setNombre(tipoBalance.getNombreTipobalance());
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaPlantillaGrupoAjuste(java.util.List)
	 */
	public List<PlantillaGrupoAjusteBean> transformarListaPlantillaGrupoAjuste(
			List<Plantillagrupoajuste> plantillaGrupoAjustes) {
		List<PlantillaGrupoAjusteBean> listaBeans = new ArrayList<PlantillaGrupoAjusteBean>();

		for (Iterator<Plantillagrupoajuste> iterator = plantillaGrupoAjustes
				.iterator(); iterator.hasNext();) {
			Plantillagrupoajuste dataObj = iterator.next();
			PlantillaGrupoAjusteBean bean = new PlantillaGrupoAjusteBeanImpl();
			bean = transformarPlantillaGrupoAjuste(dataObj);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarPlantillaGrupoAjuste
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillagrupoajuste)
	 */
	public PlantillaGrupoAjusteBean transformarPlantillaGrupoAjuste(
			Plantillagrupoajuste plantillaGrupoAjuste) {
		if (plantillaGrupoAjuste == null
				|| plantillaGrupoAjuste.getPkCodigoPlantillagrupoajuste() == null) {
			return null;
		}

		PlantillaGrupoAjusteBean bean = new PlantillaGrupoAjusteBeanImpl();
		bean.setCodigo(plantillaGrupoAjuste.getPkCodigoPlantillagrupoajuste());
		bean.setLineanegocio(transformarLineaNegocio(plantillaGrupoAjuste
				.getLineanegocio()));
		bean.setOrdenPlantillagrupoajuste(plantillaGrupoAjuste
				.getOrdenPlantillagrupoajuste());
		bean.setNombrePlantillagrupoajuste(plantillaGrupoAjuste
				.getNombrePlantillagrupoajuste());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaPlantillaAjusteProducto(java.util.List)
	 */
	public List<PlantillaAjusteProductoBean> transformarListaPlantillaAjusteProducto(
			List<Plantillaajusteproducto> plantillaAjusteProductos) {
		List<PlantillaAjusteProductoBean> listaBeans = new ArrayList<PlantillaAjusteProductoBean>();

		for (Iterator<Plantillaajusteproducto> iterator = plantillaAjusteProductos
				.iterator(); iterator.hasNext();) {
			Plantillaajusteproducto dataObj = iterator.next();
			PlantillaAjusteProductoBean bean = new PlantillaAjusteProductoBeanImpl();
			bean = transformarPlantillaAjusteProducto(dataObj);
			if (bean != null) {
				listaBeans.add(bean);
			}
		}
		return listaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarPlantillaAjusteProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaajusteproducto)
	 */
	public PlantillaAjusteProductoBean transformarPlantillaAjusteProducto(
			Plantillaajusteproducto plantillaAjusteProducto) {
		if (plantillaAjusteProducto == null
				|| plantillaAjusteProducto.getPkCodigoPlantillaajusteproduc() == null) {
			return null;
		}

		PlantillaAjusteProductoBean bean = new PlantillaAjusteProductoBeanImpl();
		bean.setCodigo(plantillaAjusteProducto
				.getPkCodigoPlantillaajusteproduc());
		bean.setOrdenPlantillaajusteproducto(plantillaAjusteProducto
				.getOrdenPlantillaajusteproducto());
		bean.setPlantillagrupoajuste(transformarPlantillaGrupoAjuste(plantillaAjusteProducto
				.getPlantillagrupoajuste()));
		bean.setProduccion(transformarProduccion(plantillaAjusteProducto
				.getProduccion()));
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaParteDiario
	 * (java.util.List)
	 */
	public List<ParteDiarioBean> transformarListaParteDiario(
			List<Partediario> parteDiarios) {
		List<ParteDiarioBean> parteDiarioBeans = new ArrayList<ParteDiarioBean>();

		for (Iterator<Partediario> iterator = parteDiarios.iterator(); iterator
				.hasNext();) {
			Partediario parteDiario = iterator.next();
			ParteDiarioBean bean = transformarParteDiario(parteDiario);
			if (bean != null) {
				parteDiarioBeans.add(bean);
			}
		}
		return parteDiarioBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarParteDiario
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Partediario)
	 */
	public ParteDiarioBean transformarParteDiario(Partediario parteDiario) {
		if (parteDiario == null || parteDiario.getPkCodigoPartediario() == null) {
			return null;
		}

		ParteDiarioBean bean = new ParteDiarioBeanImpl();
		bean.setCodigo(parteDiario.getPkCodigoPartediario());
		bean.setEstadopartediario(transformarEstadoParteDiario(parteDiario
				.getEstadopartediario()));
		bean.setLineanegocio(transformarLineaNegocio(parteDiario
				.getLineanegocio()));
		bean.setPeriodocontable(transformarPeriodoContable(parteDiario
				.getPeriodocontable()));
		bean.setUsuarioByFkCodigoUsuarioCierra(transformarUsuario(parteDiario
				.getUsuarioByFkCodigoUsuarioCierra()));
		bean.setUsuarioByFkCodigoUsuarioRegistra(transformarUsuario(parteDiario
				.getUsuarioByFkCodigoUsuarioRegistra()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaProduccionPuestoTrabajos(java.util.ArrayList)
	 */
	public List<ProduccionPuestoTrabajoBean> transformarListaProduccionPuestoTrabajos(
			ArrayList<Produccionpuestotrabajo> produccionPuestoTrabajos) {

		List<ProduccionPuestoTrabajoBean> produccionPuestoTrabajoBeans = new ArrayList<ProduccionPuestoTrabajoBean>();

		for (Iterator<Produccionpuestotrabajo> iterator = produccionPuestoTrabajos
				.iterator(); iterator.hasNext();) {
			Produccionpuestotrabajo produccionPuestoTrabajo = iterator.next();
			ProduccionPuestoTrabajoBean bean = transformarProduccionPuestoTrabajo(produccionPuestoTrabajo);
			if (bean != null) {
				produccionPuestoTrabajoBeans.add(bean);
			}
		}

		return produccionPuestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarProduccionPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccionpuestotrabajo)
	 */
	public ProduccionPuestoTrabajoBean transformarProduccionPuestoTrabajo(
			Produccionpuestotrabajo produccionPuestoTrabajo) {
		if (produccionPuestoTrabajo == null
				|| produccionPuestoTrabajo.getPkCodigoProduccionpuestotraba() == null) {
			return null;
		}

		ProduccionPuestoTrabajoBean bean = new ProduccionPuestoTrabajoBeanImpl();
		bean.setCodigo(produccionPuestoTrabajo
				.getPkCodigoProduccionpuestotraba());
		bean.setPartediario(transformarParteDiario(produccionPuestoTrabajo
				.getPartediario()));
		bean.setPuestotrabajo(transformarPuestoTrabajo(produccionPuestoTrabajo
				.getPuestotrabajo()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTablaOperaciones(java.util.ArrayList)
	 */
	public List<TablaOperacionBean> transformarListaTablaOperaciones(
			ArrayList<Tablaoperacion> tablasOperaciones) {
		List<TablaOperacionBean> tablaOperacionBeans = new ArrayList<TablaOperacionBean>();

		for (Iterator<Tablaoperacion> iterator = tablasOperaciones.iterator(); iterator
				.hasNext();) {
			Tablaoperacion tablaOperacion = iterator.next();
			TablaOperacionBean bean = transformarTablaOperaciones(tablaOperacion);
			if (bean != null) {
				tablaOperacionBeans.add(bean);
			}
		}

		return tablaOperacionBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTablaOperaciones
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablaoperacion)
	 */
	public TablaOperacionBean transformarTablaOperaciones(
			Tablaoperacion tablaOperacion) {
		if (tablaOperacion == null
				|| tablaOperacion.getPkCodigoTablaoperacion() == null) {
			return null;
		}
		TablaOperacionBean bean = new TablaOperacionBeanImpl();
		bean.setCodigo(tablaOperacion.getPkCodigoTablaoperacion());
		bean.setFechaTablaoperacion(tablaOperacion.getFechaTablaoperacion());
		bean.setProduccionpuestotrabajo(transformarProduccionPuestoTrabajo(tablaOperacion
				.getProduccionpuestotrabajo()));
		bean.setTotalHoraTablaoperacion(tablaOperacion
				.getTotalHoraTablaoperacion());
		bean.setTotalTmTablaoperacion(tablaOperacion.getTotalTmTablaoperacion());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaProductosGenerados(java.util.ArrayList)
	 */
	public List<ProductoGeneradoBean> transformarListaProductosGenerados(
			ArrayList<Productogenerado> productosGenerados) {
		List<ProductoGeneradoBean> productoGeneradoBeans = new ArrayList<ProductoGeneradoBean>();

		for (Iterator<Productogenerado> iterator = productosGenerados
				.iterator(); iterator.hasNext();) {
			Productogenerado productoGenerado = iterator.next();
			ProductoGeneradoBean bean = transformarProductoGenerado(productoGenerado);
			if (bean != null) {
				productoGeneradoBeans.add(bean);
			}
		}

		return productoGeneradoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarProductoGenerado
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productogenerado)
	 */
	public ProductoGeneradoBean transformarProductoGenerado(
			Productogenerado productoGenerado) {
		if (productoGenerado == null
				|| productoGenerado.getPkCodigoProductogenerado() == null) {
			return null;
		}

		ProductoGeneradoBean bean = new ProductoGeneradoBeanImpl();
		bean.setCodigo(productoGenerado.getPkCodigoProductogenerado());
		bean.setHorasProductogenerado(productoGenerado
				.getHorasProductogenerado());
		bean.setOrdenproduccion(transformarOrdenProduccion(productoGenerado
				.getOrdenproduccion()));
		bean.setProduccionTmProductogenerado(productoGenerado
				.getProduccionTmProductogenerado());
		bean.setTablaoperacion(transformarTablaOperaciones(productoGenerado
				.getTablaoperacion()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaConsumoPuestoTrabajo(java.util.ArrayList)
	 */
	public List<ConsumoPuestoTrabajoBean> transformarListaConsumoPuestoTrabajo(
			ArrayList<Consumopuestotrabajo> consumoPuestoTrabajos) {
		List<ConsumoPuestoTrabajoBean> consumoPuestoTrabajoBeans = new ArrayList<ConsumoPuestoTrabajoBean>();

		for (Iterator<Consumopuestotrabajo> iterator = consumoPuestoTrabajos
				.iterator(); iterator.hasNext();) {
			Consumopuestotrabajo consumoPuestoTrabajo = iterator.next();
			ConsumoPuestoTrabajoBean bean = transformarConsumoPuestoTrabajo(consumoPuestoTrabajo);
			if (bean != null) {
				consumoPuestoTrabajoBeans.add(bean);
			}
		}

		return consumoPuestoTrabajoBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarConsumoPuestoTrabajo
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo)
	 */
	public ConsumoPuestoTrabajoBean transformarConsumoPuestoTrabajo(
			Consumopuestotrabajo consumoPuestoTrabajo) {
		if (consumoPuestoTrabajo == null
				|| consumoPuestoTrabajo.getPkCodigoConsumopuestotrabajo() == null) {
			return null;
		}

		ConsumoPuestoTrabajoBean bean = new ConsumoPuestoTrabajoBeanImpl();
		bean.setCantidadConsumopuestotrabajo(consumoPuestoTrabajo
				.getCantidadConsumopuestotrabajo());
		bean.setCodigo(consumoPuestoTrabajo.getPkCodigoConsumopuestotrabajo());
		bean.setComponente(transformarComponente(consumoPuestoTrabajo
				.getComponente()));
		bean.setProductogenerado(transformarProductoGenerado(consumoPuestoTrabajo
				.getProductogenerado()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarObjetoCostos
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Objetocostos)
	 */
	public ObjetoCostosBean transformarObjetoCostos(Objetocostos objetoCostos) {

		if (objetoCostos == null
				|| objetoCostos.getPkCodigoObjetocosto() == null) {
			return null;
		}

		ObjetoCostosBean objetoCostosBean = new ObjetoCostosBeanImpl();

		objetoCostosBean.setAbreviatura(objetoCostos
				.getAbreviaturaObjetocosto());
		objetoCostosBean.setCodigo(objetoCostos.getPkCodigoObjetocosto());
		objetoCostosBean.setDescripcion(objetoCostos
				.getDescripcionObjetocosto());
		objetoCostosBean.setEstado(objetoCostos.getEstadoobjetocostos()
				.getPkCodigoEstadoobjetocostos());
		objetoCostosBean.setTipo(objetoCostos.getTipoobjetocostos()
				.getPkCodigoTipoobjetocostos());
		objetoCostosBean.setCodigoSap(objetoCostos.getCodigoSapObjetocosto());
		objetoCostosBean.setFechaIni(objetoCostos.getFechaInicioObjetocosto());
		objetoCostosBean.setFechaFin(objetoCostos.getFechaFinObjetocosto());
		objetoCostosBean
				.setTipoBean(transformarTipoObjetoCostosBean(objetoCostos
						.getTipoobjetocostos()));
		// objetoCostosBean.setAreaBean(transformarArea(objetoCostos.getArea()));
		objetoCostosBean
				.setEstadoBean(transformarEstadoObjetoCostosBean(objetoCostos
						.getEstadoobjetocostos()));
		objetoCostosBean.setValor_estadistico(objetoCostos
				.getValorestadisticoObjetocosto());

		return objetoCostosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaObjetoCostos
	 * (java.util.List)
	 */
	public List<ObjetoCostosBean> transformarListaObjetoCostos(
			List<Objetocostos> listaObjetoCostos) {

		List<ObjetoCostosBean> listaObjetoCostosBean = new ArrayList<ObjetoCostosBean>();

		for (Iterator<Objetocostos> iterator = listaObjetoCostos.iterator(); iterator
				.hasNext();) {

			Objetocostos objetoCostosBean = iterator.next();
			listaObjetoCostosBean
					.add(transformarObjetoCostos(objetoCostosBean));
		}

		return listaObjetoCostosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarEstadoObjetoCostosBean
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoobjetocostos)
	 */
	public EstadoObjetoCostosBean transformarEstadoObjetoCostosBean(
			Estadoobjetocostos estadoObjetoCostos) {

		if (estadoObjetoCostos == null
				|| estadoObjetoCostos.getPkCodigoEstadoobjetocostos() == null) {
			return null;
		}

		EstadoObjetoCostosBean estadoObjetoCostosBean = new EstadoObjetoCostosBeanImpl();

		estadoObjetoCostosBean.setNombre(estadoObjetoCostos
				.getNombreEstadoobjetocostos());
		estadoObjetoCostosBean.setpkCodigo(estadoObjetoCostos
				.getPkCodigoEstadoobjetocostos());

		return estadoObjetoCostosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarEstadoOrdenProduccionBean
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoordenproduccion)
	 */
	public EstadoOrdenProduccionBean transformarEstadoOrdenProduccionBean(
			Estadoordenproduccion estadoOrdenProduccion) {
		if (estadoOrdenProduccion == null
				|| estadoOrdenProduccion.getPkCodigoEstadoorden() == null) {
			return null;
		}
		EstadoOrdenProduccionBean edoBean = new EstadoOrdenProduccionBeanImpl();
		edoBean.setCodigo(estadoOrdenProduccion.getPkCodigoEstadoorden());
		edoBean.setNombre(estadoOrdenProduccion.getNombreEstadoorden());
		return edoBean;
	}

	public List<EstadoOrdenProduccionBean> transformarListaEstadoOrdenProduccionBean(
			List<Estadoordenproduccion> listaEstadoordenProduccion) {
		List<EstadoOrdenProduccionBean> listaEstadoOrdenProduccionBean = new ArrayList<EstadoOrdenProduccionBean>();

		for (Iterator<Estadoordenproduccion> iterator = listaEstadoordenProduccion
				.iterator(); iterator.hasNext();) {

			Estadoordenproduccion estadoOrdenProduccionBean = iterator.next();
			listaEstadoOrdenProduccionBean
					.add(transformarEstadoOrdenProduccionBean(estadoOrdenProduccionBean));
		}

		return listaEstadoOrdenProduccionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoObjetoCostos(java.util.ArrayList)
	 */
	public List<EstadoObjetoCostosBean> transformarListaEstadoObjetoCostos(
			ArrayList<Estadoobjetocostos> listaEstadoObjetoCostos) {

		List<EstadoObjetoCostosBean> listaEstadoObjetoCostosBean = new ArrayList<EstadoObjetoCostosBean>();

		for (Iterator<Estadoobjetocostos> iterator = listaEstadoObjetoCostos
				.iterator(); iterator.hasNext();) {

			Estadoobjetocostos estadoobjetocostos = iterator.next();
			listaEstadoObjetoCostosBean
					.add(transformarEstadoObjetoCostosBean(estadoobjetocostos));
		}

		return listaEstadoObjetoCostosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarTipoObjetoCostosBean
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipoobjetocostos)
	 */
	public TipoObjetoCostosBean transformarTipoObjetoCostosBean(
			Tipoobjetocostos tipoObjetoCostos) {

		if (tipoObjetoCostos == null
				|| tipoObjetoCostos.getPkCodigoTipoobjetocostos() == null) {
			return null;
		}

		TipoObjetoCostosBean tipoObjetoCostosBean = new TipoObjetoCostosBeanImpl();

		tipoObjetoCostosBean.setNombre(tipoObjetoCostos
				.getNombreTipoobjetocostos());
		tipoObjetoCostosBean.setPkTipoObjetoCosto(tipoObjetoCostos
				.getPkCodigoTipoobjetocostos());

		return tipoObjetoCostosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTipoObjetoCostos(java.util.ArrayList)
	 */
	public List<TipoObjetoCostosBean> transformarListaTipoObjetoCostos(
			ArrayList<Tipoobjetocostos> listaTipoObjetoCostos) {

		List<TipoObjetoCostosBean> listaTipoObjetoCostosBean = new ArrayList<TipoObjetoCostosBean>();

		for (Iterator<Tipoobjetocostos> iterator = listaTipoObjetoCostos
				.iterator(); iterator.hasNext();) {

			Tipoobjetocostos tipoobjetocostos = iterator.next();
			listaTipoObjetoCostosBean
					.add(transformarTipoObjetoCostosBean(tipoobjetocostos));
		}

		return listaTipoObjetoCostosBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaProduccionDiaria(java.util.ArrayList)
	 */
	public List<ProduccionDiariaBean> transformarListaProduccionDiaria(
			ArrayList<Producciondiaria> produccionesDiarias) {
		List<ProduccionDiariaBean> produccionDiariaBeans = new ArrayList<ProduccionDiariaBean>();

		for (Iterator<Producciondiaria> iterator = produccionesDiarias
				.iterator(); iterator.hasNext();) {
			Producciondiaria produccionDiaria = iterator.next();
			ProduccionDiariaBean bean = transformarProduccionDiaria(produccionDiaria);
			if (bean != null) {
				produccionDiariaBeans.add(bean);
			}
		}

		return produccionDiariaBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarProduccionDiaria
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producciondiaria)
	 */
	public ProduccionDiariaBean transformarProduccionDiaria(
			Producciondiaria produccionDiaria) {
		if (produccionDiaria == null
				|| produccionDiaria.getPkCodigoProducciondiaria() == null) {
			return null;
		}

		ProduccionDiariaBean bean = new ProduccionDiariaBeanImpl();
		bean.setCodigo(produccionDiaria.getPkCodigoProducciondiaria());
		bean.setConsumoProducciondiaria(produccionDiaria
				.getConsumoProducciondiaria());
		bean.setIngresoProduccionProducciondi(produccionDiaria
				.getIngresoProduccionProducciondi());
		bean.setOrdenproduccion(transformarOrdenProduccion(produccionDiaria
				.getOrdenproduccion()));
		bean.setSaldoFinalProducciondiaria(produccionDiaria
				.getSaldoFinalProducciondiaria());
		bean.setSaldoInicialProducciondiaria(produccionDiaria
				.getSaldoInicialProducciondiaria());
		bean.setTablakardexes(transformarListaTablaKardex(new ArrayList<Tablakardex>(
				produccionDiaria.getTablakardexes())));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarProduccionDiaria
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producciondiaria)
	 */
	public ProduccionDiariaBean transformarProduccionDiariaBasico(
			Producciondiaria produccionDiaria) {
		if (produccionDiaria == null
				|| produccionDiaria.getPkCodigoProducciondiaria() == null) {
			return null;
		}

		ProduccionDiariaBean bean = new ProduccionDiariaBeanImpl();
		bean.setCodigo(produccionDiaria.getPkCodigoProducciondiaria());
		bean.setConsumoProducciondiaria(produccionDiaria
				.getConsumoProducciondiaria());
		bean.setIngresoProduccionProducciondi(produccionDiaria
				.getIngresoProduccionProducciondi());
		bean.setOrdenproduccion(transformarOrdenProduccion(produccionDiaria
				.getOrdenproduccion()));
		bean.setSaldoFinalProducciondiaria(produccionDiaria
				.getSaldoFinalProducciondiaria());
		bean.setSaldoInicialProducciondiaria(produccionDiaria
				.getSaldoInicialProducciondiaria());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaTablaKardex
	 * (java.util.List)
	 */
	public List<TablaKardexBean> transformarListaTablaKardex(
			List<Tablakardex> tablaKardexes) {
		List<TablaKardexBean> tablaKardexBeans = new ArrayList<TablaKardexBean>();

		for (Iterator<Tablakardex> iterator = tablaKardexes.iterator(); iterator
				.hasNext();) {
			Tablakardex tablaKardex = iterator.next();
			TablaKardexBean bean = transformarTablaKardex(tablaKardex);
			if (bean != null) {
				tablaKardexBeans.add(bean);
			}
		}
		return tablaKardexBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTablaKardex
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex)
	 */
	public TablaKardexBean transformarTablaKardex(Tablakardex tablaKardex) {
		if (tablaKardex == null || tablaKardex.getPkCodigoTablakardex() == null) {
			return null;
		}

		TablaKardexBean bean = new TablaKardexBeanImpl();
		bean.setAlmacen(transformarAlmacen(tablaKardex.getAlmacen()));
		bean.setCodigo(tablaKardex.getPkCodigoTablakardex());
		bean.setConsumoHumedadTablakardex(tablaKardex
				.getConsumoHumedadTablakardex());
		bean.setConsumoTablakardex(tablaKardex.getConsumoTablakardex());
		bean.setFechaTablakardex(tablaKardex.getFechaTablakardex());
		bean.setIngresoHumedadTablakardex(tablaKardex
				.getIngresoHumedadTablakardex());
		bean.setIngresoTablakardex(tablaKardex.getIngresoTablakardex());
		bean.setObservacionTablakardex(tablaKardex.getObservacionTablakardex());
		bean.setSaldoInicialTablakardex(tablaKardex
				.getSaldoInicialTablakardex());
		bean.setStockFinalTablakardex(tablaKardex.getStockFinalTablakardex());
		bean.setStockFisicoTablakardex(tablaKardex.getStockFisicoTablakardex());
		bean.setUbicacionByFkCodigoUbicacionDestino(transformarUbicacion(tablaKardex
				.getUbicacionByFkCodigoUbicacionDestino()));
		bean.setUbicacionByFkCodigoUbicacionOrigen(transformarUbicacion(tablaKardex
				.getUbicacionByFkCodigoUbicacionOrigen()));
		bean.setVariacionTablakardex(tablaKardex.getVariacionTablakardex());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaValorPromedioVarCalidad(java.util.ArrayList)
	 */
	public List<ValorPromVariableCalidadBean> transformarListaValorPromedioVarCalidad(
			ArrayList<Valorpromvariablecalidad> valoresPromVariablesCalidades) {
		List<ValorPromVariableCalidadBean> valorPromVariableCalidadBeans = new ArrayList<ValorPromVariableCalidadBean>();

		for (Iterator<Valorpromvariablecalidad> iterator = valoresPromVariablesCalidades
				.iterator(); iterator.hasNext();) {
			Valorpromvariablecalidad valorPromVariableCalidad = iterator.next();
			ValorPromVariableCalidadBean bean = transformarValorPromedioVarCalidad(valorPromVariableCalidad);
			if (bean != null) {
				valorPromVariableCalidadBeans.add(bean);
			}
		}

		return valorPromVariableCalidadBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarValorPromedioVarCalidad
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Valorpromvariablecalidad)
	 */
	public ValorPromVariableCalidadBean transformarValorPromedioVarCalidad(
			Valorpromvariablecalidad valorPromVariableCalidad) {
		if (valorPromVariableCalidad == null
				|| valorPromVariableCalidad.getPkCodigoValorpromvariblecalid() == null) {
			return null;
		}

		ValorPromVariableCalidadBean bean = new ValorPromVariableCalidadBeanImpl();
		bean.setCodigo(valorPromVariableCalidad
				.getPkCodigoValorpromvariblecalid());
		bean.setProductovariablecalidad(transformarProductoVariableCalidad(valorPromVariableCalidad
				.getProductovariablecalidad()));
		bean.setTablakardex(transformarTablaKardex(valorPromVariableCalidad
				.getTablakardex()));
		bean.setValorValorpromvariblecalidad(valorPromVariableCalidad
				.getValorValorpromvariblecalidad());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaProductoVariableCalidad(java.util.List)
	 */
	public List<ProductoVariableCalidadBean> transformarListaProductoVariableCalidad(
			List<Productovariablecalidad> productosVariableCalidad) {
		List<ProductoVariableCalidadBean> productoVariableCalidadBeans = new ArrayList<ProductoVariableCalidadBean>();

		for (Iterator<Productovariablecalidad> iterator = productosVariableCalidad
				.iterator(); iterator.hasNext();) {

			Productovariablecalidad productovariablecalidad = iterator.next();
			ProductoVariableCalidadBean bean = transformarProductoVariableCalidad(productovariablecalidad);
			if (bean != null) {
				productoVariableCalidadBeans.add(bean);
			}
		}

		return productoVariableCalidadBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarProductoVariableCalidad
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablecalidad)
	 */
	public ProductoVariableCalidadBean transformarProductoVariableCalidad(
			Productovariablecalidad productoVariableCalidad) {
		if (productoVariableCalidad == null
				|| productoVariableCalidad.getPkCodigoProductovariablecalid() == null) {
			return null;
		}

		ProductoVariableCalidadBean bean = new ProductoVariableCalidadBeanImpl();
		bean.setCodigo(productoVariableCalidad
				.getPkCodigoProductovariablecalid());
		bean.setCodigoMatrixScc(productoVariableCalidad.getCodigoMatrixScc());
		bean.setCodigoProcesoScc(productoVariableCalidad.getCodigoProcesoScc());
		bean.setHojaruta(transformarHojaRuta(productoVariableCalidad
				.getHojaruta()));
		bean.setVariablecalidad(transformarVariableCalidad(productoVariableCalidad
				.getVariablecalidad()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaVariableCalidad(java.util.List)
	 */
	public List<VariableCalidadBean> transformarListaVariableCalidad(
			List<Variablecalidad> variablesCalidad) {
		List<VariableCalidadBean> variableCalidadBeans = new ArrayList<VariableCalidadBean>();

		for (Iterator<Variablecalidad> iterator = variablesCalidad.iterator(); iterator
				.hasNext();) {
			Variablecalidad variableCalidad = iterator.next();
			VariableCalidadBean bean = transformarVariableCalidad(variableCalidad);
			if (bean != null) {
				variableCalidadBeans.add(bean);
			}
		}

		return variableCalidadBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarVariableCalidad
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Variablecalidad)
	 */
	public VariableCalidadBean transformarVariableCalidad(
			Variablecalidad variableCalidad) {
		if (variableCalidad == null
				|| variableCalidad.getPkCodigoVariablecalidad() == null) {
			return null;
		}

		VariableCalidadBean bean = new VariableCalidadBeanImpl();
		bean.setCodigo(variableCalidad.getPkCodigoVariablecalidad());
		bean.setNombre(variableCalidad.getNombreVariablecalidad());
		bean.setUnidadmedida(transformarUnidadMedida(variableCalidad
				.getUnidadmedida()));
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaFactorKardex
	 * (java.util.ArrayList)
	 */
	public List<FactorKardexBean> transformarListaFactorKardex(
			ArrayList<Factorvariacionpuestotrabajo> factorKardexes) {
		List<FactorKardexBean> factorKardexBeans = new ArrayList<FactorKardexBean>();

		for (Iterator<Factorvariacionpuestotrabajo> iterator = factorKardexes
				.iterator(); iterator.hasNext();) {
			Factorvariacionpuestotrabajo factorKardex = iterator.next();
			FactorKardexBean bean = transformarFactorKardex(factorKardex);
			if (bean != null) {
				factorKardexBeans.add(bean);
			}
		}

		return factorKardexBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarFactorKardex
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorkardex)
	 */
	public FactorKardexBean transformarFactorKardex(
			Factorvariacionpuestotrabajo factorKardex) {
		if (factorKardex == null
				|| factorKardex.getPkCodigoFactorvariacion() == null) {
			return null;
		}

		FactorKardexBean bean = new FactorKardexBeanImpl();
		bean.setCodigo(factorKardex.getPkCodigoFactorvariacion());

		// bean.setTablakardex(transformarTablaKardex(factorKardex.getTablakardex()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaConsumoComponente(java.util.List)
	 */
	public List<ConsumoComponenteBean> transformarListaConsumoComponente(
			List<Consumocomponente> consumoComponentes) {
		List<ConsumoComponenteBean> consumoComponenteBeans = new ArrayList<ConsumoComponenteBean>();

		for (Iterator<Consumocomponente> iterator = consumoComponentes
				.iterator(); iterator.hasNext();) {
			Consumocomponente consumoComponente = iterator.next();
			ConsumoComponenteBean bean = transformarConsumoComponente(consumoComponente);
			if (bean != null) {
				consumoComponenteBeans.add(bean);
			}
		}
		return consumoComponenteBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarConsumoComponente
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente)
	 */
	public ConsumoComponenteBean transformarConsumoComponente(
			Consumocomponente consumoComponente) {
		if (consumoComponente == null
				|| consumoComponente.getPkCodigoConsumocomponente() == null) {
			return null;
		}

		ConsumoComponenteBean bean = new ConsumoComponenteBeanImpl();
		bean.setCodigo(consumoComponente.getPkCodigoConsumocomponente());
		bean.setComponente(transformarComponente(consumoComponente
				.getComponente()));
		bean.setTablakardex(transformarTablaKardex(consumoComponente
				.getTablakardex()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoParteDiario(java.util.List)
	 */
	public List<EstadoParteDiarioBean> transformarListaEstadoParteDiario(
			List<Estadopartediario> estadopartediario) {
		List<EstadoParteDiarioBean> estadoParteDiarioBeans = new ArrayList<EstadoParteDiarioBean>();

		for (Iterator<Estadopartediario> iterator = estadopartediario
				.iterator(); iterator.hasNext();) {
			Estadopartediario estadoParteDiario = iterator.next();
			EstadoParteDiarioBean bean = transformarEstadoParteDiario(estadoParteDiario);
			if (bean != null) {
				estadoParteDiarioBeans.add(bean);
			}
		}

		return estadoParteDiarioBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoParteDiario
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadopartediario)
	 */
	public EstadoParteDiarioBean transformarEstadoParteDiario(
			Estadopartediario estadoParteDiario) {
		if (estadoParteDiario == null
				|| estadoParteDiario.getPkCodigoEstadopartediario() == null) {
			return null;
		}

		EstadoParteDiarioBean bean = new EstadoParteDiarioBeanImpl();
		bean.setCodigo(estadoParteDiario.getPkCodigoEstadopartediario());
		bean.setNombre(estadoParteDiario.getNombreEstadopartediario());
		bean.setDescripcion(estadoParteDiario.getDescripcionEstadopartediario());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaAjusteProduccion(java.util.List)
	 */
	public List<AjusteProduccionBean> transformarListaAjusteProduccion(
			List<Ajusteproduccion> ajusteProducciones) {
		List<AjusteProduccionBean> ajusteProduccionBeans = new ArrayList<AjusteProduccionBean>();

		for (Iterator<Ajusteproduccion> iterator = ajusteProducciones
				.iterator(); iterator.hasNext();) {
			Ajusteproduccion ajusteProduccion = iterator.next();
			AjusteProduccionBean bean = transformarAjusteProduccion(ajusteProduccion);
			if (bean != null) {
				ajusteProduccionBeans.add(bean);
			}
		}

		return ajusteProduccionBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarAjusteProduccion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion)
	 */
	public AjusteProduccionBean transformarAjusteProduccion(
			Ajusteproduccion ajusteProduccion) {
		if (ajusteProduccion == null
				|| ajusteProduccion.getPkCodigoAjusteproduccion() == null) {
			return null;
		}

		AjusteProduccionBean bean = new AjusteProduccionBeanImpl();
		bean.setCodigo(ajusteProduccion.getPkCodigoAjusteproduccion());
		bean.setEstadoajusteproduccion(transformarEstadoAjusteProduccion(ajusteProduccion
				.getEstadoajusteproduccion()));
		bean.setLineanegocio(transformarLineaNegocio(ajusteProduccion
				.getLineanegocio()));
		bean.setPeriodocontable(transformarPeriodoContable(ajusteProduccion
				.getPeriodocontable()));
		bean.setUsuarioByFkCodigoUsuario(transformarUsuario(ajusteProduccion
				.getUsuarioByFkCodigoUsuario()));
		bean.setUsuarioByFkCodigoUsuarioRegistra(transformarUsuario(ajusteProduccion
				.getUsuarioByFkCodigoUsuarioRegistra()));
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
		if (ajusteProduccion.getFechaAprobacionAjusteproducci() != null) {
			bean.setFechaAprobacionAjusteproduccion(dateFormat
					.format(ajusteProduccion.getFechaAprobacionAjusteproducci()));

		}
		bean.setConsumoEnviadoSap(ajusteProduccion.getConsumoEnviadoSap());
		bean.setCombutibleEnviadoSap(ajusteProduccion.getCombutibleEnviadoSap());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoAjusteProduccion(java.util.List)
	 */
	public List<EstadoAjusteProduccionBean> transformarListaEstadoAjusteProduccion(
			List<Estadoajusteproduccion> estadosAjusteProduccion) {
		List<EstadoAjusteProduccionBean> estadoAjusteProduccionBeans = new ArrayList<EstadoAjusteProduccionBean>();

		for (Iterator<Estadoajusteproduccion> iterator = estadosAjusteProduccion
				.iterator(); iterator.hasNext();) {
			Estadoajusteproduccion estadoAjusteProduccion = iterator.next();
			EstadoAjusteProduccionBean bean = transformarEstadoAjusteProduccion(estadoAjusteProduccion);
			if (bean != null) {
				estadoAjusteProduccionBeans.add(bean);
			}
		}

		return estadoAjusteProduccionBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarEstadoAjusteProduccion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoajusteproduccion)
	 */
	public EstadoAjusteProduccionBean transformarEstadoAjusteProduccion(
			Estadoajusteproduccion estadoAjusteProduccion) {
		if (estadoAjusteProduccion == null
				|| estadoAjusteProduccion.getPkCodigoEstadoajusteproduccio() == null) {
			return null;
		}

		EstadoAjusteProduccionBean bean = new EstadoAjusteProduccionBeanImpl();
		bean.setCodigo(estadoAjusteProduccion
				.getPkCodigoEstadoajusteproduccio());
		bean.setNombre(estadoAjusteProduccion.getNombreEstadoajusteproduccion());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarColumnaPlantillaReporte
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte)
	 */
	public ColumnaReporteBean transformarColumnaPlantillaReporte(
			Columnareporte columnaReporte) {
		if (columnaReporte == null
				|| columnaReporte.getPkCodigoColumnareporte() == null)
			return null;

		ColumnaReporteBean columna = new ColumnaReporteBeanImpl();

		columna.setCodigo(columnaReporte.getPkCodigoColumnareporte());
		columna.setNombreColumnaReporte(columnaReporte
				.getNombreColumnareporte());
		columna.setPosicionColumnaReporte(columnaReporte
				.getPosicionColumnareporte());
		columna.setEstadoColumnaReporte(transformarEstadoColumnaReporte(columnaReporte
				.getEstadocolumnareporte()));

		return columna;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaColumnaPlantillaReporte(java.util.Set)
	 */
	public List<ColumnaReporteBean> transformarListaColumnaPlantillaReporte(
			Set<Columnareporte> columnasReporte) {
		if (columnasReporte == null)
			return null;

		List<ColumnaReporteBean> columnas = new ArrayList<ColumnaReporteBean>();
		for (int i = 0; i < columnasReporte.size(); i++) {
			columnas.add(transformarColumnaPlantillaReporte((Columnareporte) columnasReporte
					.toArray()[i]));
		}
		return columnas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarEstadoPlantillaReporte
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoplantillareporte)
	 */
	public EstadoPlantillaReporteBean transformarEstadoPlantillaReporte(
			Estadoplantillareporte estadoPlantillaReporte) {
		if (estadoPlantillaReporte == null
				|| estadoPlantillaReporte.getPkCodigoEstadoplantillareport() == null)
			return null;

		EstadoPlantillaReporteBean estado = new EstadoPlantillaReporteBeanImpl();
		estado.setCodigo(estadoPlantillaReporte
				.getPkCodigoEstadoplantillareport());
		estado.setNombreEstadoPlantillaReporte(estadoPlantillaReporte
				.getNombreEstadoplantillareporte());

		return estado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoPlantillaReporte(java.util.List)
	 */
	public List<EstadoPlantillaReporteBean> transformarListaEstadoPlantillaReporte(
			List<Estadoplantillareporte> estadosPlantillaReporte) {
		if (estadosPlantillaReporte == null)
			return null;

		List<EstadoPlantillaReporteBean> estados = new ArrayList<EstadoPlantillaReporteBean>();

		for (int i = 0; i < estadosPlantillaReporte.size(); i++) {
			estados.add(transformarEstadoPlantillaReporte(estadosPlantillaReporte
					.get(i)));
		}
		return estados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPlantillaProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaproducto)
	 */
	public PlantillaProductoBean transformarPlantillaProductoBasico(
			Plantillaproducto plantillaProducto) {
		if (plantillaProducto == null
				|| plantillaProducto.getPkCodigoPlantillaproducto() == null)
			return null;

		PlantillaProductoBean plantilla = new PlantillaProductoBeanImpl();
		plantilla.setCodigo(plantillaProducto.getPkCodigoPlantillaproducto());
		// plantilla.setPlantillaReporte(transformarPlantillaReporte(plantillaProducto.getPlantillareporte()));
		plantilla.setProducto(transformarProductoBasico(plantillaProducto
				.getProducto()));
		plantilla.setFecha(plantillaProducto.getFechaPlantillaproducto());
		plantilla.setVersion(plantillaProducto.getVersionPlantillaproducto());
		// plantilla.setColumnas(transformarListaColumnaPlantillaProducto(plantillaProducto.getColumnaplantillaproductos()));

		return plantilla;
	}

	/**
	 * @param columna
	 * @return
	 */
	public ColumnaPlantillaProductoBean transformarColumnaPlantillaProducto(
			Columnaplantillaproducto columna) {
		if (columna == null
				|| columna.getPkCodigoColumnaplantillaprodu() == null)
			return null;

		ColumnaPlantillaProductoBean columnaBean = new ColumnaPlantillaProductoBeanImpl();
		columnaBean.setCodigo(columna.getPkCodigoColumnaplantillaprodu());
		columnaBean
				.setColumnaPlantillaReporte(transformarColumnaPlantillaReporte(columna
						.getColumnareporte()));
		columnaBean.setComponenteProducto(transformarComponente(columna
				.getComponente()));
		columnaBean.setProporcionColumnaReporte(columna
				.getProporcionColumnareporte());
		columnaBean.setVariableOperacion(transformarVariableOperacion(columna
				.getVariableoperacion()));

		return columnaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaPlantillaProducto(java.util.List)
	 */
	public List<PlantillaProductoBean> transformarListaPlantillaProducto(
			List<Plantillaproducto> plantillasProducto) {
		if (plantillasProducto == null)
			return null;

		List<PlantillaProductoBean> plantillas = new ArrayList<PlantillaProductoBean>();
		for (int i = 0; i < plantillasProducto.size(); i++) {
			plantillas
					.add(transformarPlantillaProductoBasico(plantillasProducto
							.get(i)));
		}

		return plantillas;
	}

	/**
	 * @param columnas
	 * @return
	 */
	public List<ColumnaPlantillaProductoBean> transformarListaColumnaPlantillaProducto(
			Set<Columnaplantillaproducto> columnas) {
		if (columnas == null)
			return null;

		List<ColumnaPlantillaProductoBean> columnasBean = new ArrayList<ColumnaPlantillaProductoBean>();
		for (int i = 0; i < columnas.size(); i++) {
			columnasBean
					.add(transformarColumnaPlantillaProducto((Columnaplantillaproducto) columnas
							.toArray()[i]));
		}
		return columnasBean;
	}

	/**
	 * @param variable
	 * @return
	 */
	public VariableOperacionBean transformarVariableOperacion(
			Variableoperacion variable) {
		if (variable == null || variable.getPkCodigoVariableoperacion() == null)
			return null;

		VariableOperacionBean variableBean = new VariableOperacionBeanImpl();
		variableBean.setCodigo(variable.getPkCodigoVariableoperacion());

		return variableBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoCubicacion(java.util.List)
	 */
	public List<EstadoCubicacionBean> transformarListaEstadoCubicacion(
			List<Estadocubicacion> estadosCubicacion) {
		List<EstadoCubicacionBean> estadosCubicacionBeans = new ArrayList<EstadoCubicacionBean>();

		for (Iterator<Estadocubicacion> iterator = estadosCubicacion.iterator(); iterator
				.hasNext();) {
			Estadocubicacion estadoCubicacion = iterator.next();
			EstadoCubicacionBean bean = transformarEstadoCubicacion(estadoCubicacion);
			if (bean != null) {
				estadosCubicacionBeans.add(bean);
			}
		}

		return estadosCubicacionBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarEstadoCubicacion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocubicacion)
	 */
	public EstadoCubicacionBean transformarEstadoCubicacion(
			Estadocubicacion estadoCubicacion) {
		if (estadoCubicacion == null
				|| estadoCubicacion.getPkCodigoEstadocubicacion() == null) {
			return null;
		}

		EstadoCubicacionBean bean = new EstadoCubicacionBeanImpl();
		bean.setCodigo(estadoCubicacion.getPkCodigoEstadocubicacion());
		bean.setNombre(estadoCubicacion.getNombreEstadocubicacion());

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaCubicacionProducto(java.util.List)
	 */
	public List<CubicacionProductoBean> transformarListaCubicacionProducto(
			List<Cubicacionproducto> cubicacionProductos) {
		List<CubicacionProductoBean> cubicacionProductosBeans = new ArrayList<CubicacionProductoBean>();

		for (Iterator<Cubicacionproducto> iterator = cubicacionProductos
				.iterator(); iterator.hasNext();) {
			Cubicacionproducto cubicacionProducto = iterator.next();
			CubicacionProductoBean bean = transformarCubicacionProducto(cubicacionProducto);
			if (bean != null) {
				cubicacionProductosBeans.add(bean);
			}
		}

		return cubicacionProductosBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarCubicacionProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto)
	 */
	public CubicacionProductoBean transformarCubicacionProducto(
			Cubicacionproducto cubicacionProducto) {
		if (cubicacionProducto == null
				|| cubicacionProducto.getPkCodigoCubicacionproducto() == null) {
			return null;
		}

		CubicacionProductoBean bean = new CubicacionProductoBeanImpl();
		bean.setAnoCubicacionproducto(cubicacionProducto
				.getAnoCubicacionproducto());
		bean.setCodigo(cubicacionProducto.getPkCodigoCubicacionproducto());
		bean.setEstadocubicacion(transformarEstadoCubicacion(cubicacionProducto
				.getEstadocubicacion()));
		bean.setFechaCubicacionproducto(cubicacionProducto
				.getFechaCubicacionproducto());
		bean.setLineanegocio(transformarLineaNegocio(cubicacionProducto
				.getLineanegocio()));
		bean.setMesCubicacionproducto(cubicacionProducto
				.getMesCubicacionproducto());
		bean.setProduccion(transformarProduccion(cubicacionProducto
				.getProduccion()));
		bean.setStockFisicoCubicacionproducto(cubicacionProducto
				.getStockFisicoCubicacionproducto());
		bean.setToneladasCubicacionproducto(cubicacionProducto
				.getToneladasCubicacionproducto());
		bean.setUsuarioByFkCodigoUsuario(transformarUsuario(cubicacionProducto
				.getUsuarioByFkCodigoUsuario()));
		bean.setUsuarioByFkCodigoUsuarioAprueba(transformarUsuario(cubicacionProducto
				.getUsuarioByFkCodigoUsuarioAprueba()));
		bean.setUsuarioByFkCodigoUsuarioRegistra(transformarUsuario(cubicacionProducto
				.getUsuarioByFkCodigoUsuarioRegistra()));

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarEstadoColumnaReporte
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocolumnareporte)
	 */
	public EstadoColumnaReporteBean transformarEstadoColumnaReporte(
			Estadocolumnareporte estado) {
		if (estado == null || estado.getPkCodigoEstadocolumnareporte() == null) {
			return null;
		}

		EstadoColumnaReporteBean estadoBean = new EstadoColumnaReporteBeanImpl();
		estadoBean.setCodigo(estado.getPkCodigoEstadocolumnareporte());
		estadoBean.setNombreEstadoColumnaReporte(estado
				.getNombreEstadocolumnareporte());

		return estadoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaEstadoColumnaReporte(java.util.List)
	 */
	public List<EstadoColumnaReporteBean> transformarListaEstadoColumnaReporte(
			List<Estadocolumnareporte> estados) {

		if (estados == null) {
			return null;
		}

		List<EstadoColumnaReporteBean> estadosBean = new ArrayList<EstadoColumnaReporteBean>();

		for (int i = 0; i < estados.size(); i++) {

			estadosBean.add(transformarEstadoColumnaReporte(estados.get(i)));
		}

		return estadosBean;
	}

	private ComponenteBean transformarComponenteDistrib(Componente componente) {
		if (componente == null) {
			return null;
		}
		ComponenteBean componenteBean = new ComponenteBeanImpl();

		componenteBean.setCodigo(componente.getPkCodigoComponente());
		componenteBean.setProducto(transformarProductoBasico(componente
				.getProductoByFkCodigoProducto()));

		componenteBean
				.setProductoComponente(transformarProductoBasico(componente
						.getProductoByFkCodigoProductoComponente()));

		return componenteBean;
	}

	private PuestoTrabajoBean transformarPuestoTrabajoDistrib(
			Puestotrabajo puestoTrabajo) {

		if (puestoTrabajo == null
				|| puestoTrabajo.getPkCodigoPuestotrabajo() == null) {
			return null;
		}

		PuestoTrabajoBean puestoTrabajoBean = new PuestoTrabajoBeanImpl();

		puestoTrabajoBean.setCodigo(puestoTrabajo.getPkCodigoPuestotrabajo());
		puestoTrabajoBean.setNombre(puestoTrabajo.getNombrePuestotrabajo());
		puestoTrabajoBean.setDescripcion(puestoTrabajo
				.getDescripcionPuestotrabajo());
		// puestoTrabajoBean.setEstadoPuestoTrabajo(transformarEstadoPuestoTrabajo(puestoTrabajo.getEstadopuestotrabajo()));
		// puestoTrabajoBean.setCodigoSAP(puestoTrabajo.getCodigoSapPuestotrabajo());
		// puestoTrabajoBean.setCodigoSCC(puestoTrabajo.getCodSccPuestotrabajo());
		// puestoTrabajoBean.setSiglas(puestoTrabajo.getSiglasPuestotrabajo());
		puestoTrabajoBean
				.setTipoPuestoTrabajo(transformarTipoPuestoTrabajo(puestoTrabajo
						.getTipopuestotrabajo()));

		// puestoTrabajoBean.setUnidadMedida(transformarUnidadMedida(puestoTrabajo.getUnidadmedida()));

		return puestoTrabajoBean;
	}

	private ActividadBean transformarActividadDistrib(Actividad actividad) {

		if (actividad == null || actividad.getPkCodigoActividad() == null)
			return null;

		ActividadBean actividadBean = new ActividadBeanImpl();

		actividadBean.setCodigo(actividad.getPkCodigoActividad());
		// actividadBean.setCodigoSCC(actividad.getCodigoSccActividad());
		actividadBean.setNombre(actividad.getNombreActividad());
		actividadBean.setDescripcion(actividad.getDescripcionActividad());
		// actividadBean.setEstadoActividad(transformarEstadoActividad(actividad.getEstadoactividad()));
		// actividadBean.setMetrosPerforacion(actividad.getMetrosPerforaActividad());

		// actividadBean.setProceso(transformarProcesoDistrib(actividad.getProceso()));

		return actividadBean;
	}

	private ProcesoBean transformarProcesoDistrib(Proceso proceso) {

		if (proceso == null || proceso.getPkCodigoProceso() == null)
			return null;

		ProcesoBean procesoBean = new ProcesoBeanImpl();

		procesoBean.setCodigo(proceso.getPkCodigoProceso());

		// procesoBean.setCodigoSAP(proceso.getCodigoSapProceso());
		// procesoBean.setCodigoSCC(proceso.getCodigoSccProceso());
		procesoBean.setDescripcion(proceso.getDescripcionProceso());
		procesoBean.setNombre(proceso.getNombreProceso());
		procesoBean.setOrdenEjecucion(proceso.getOrdenEjecucionProceso());
		procesoBean.setLineaNegocio(transformarLineaNegocioDistrib(proceso
				.getLineanegocio()));
		procesoBean.setTipoProducto(transformarTipoProducto(proceso
				.getTipoproducto()));
		// procesoBean.setSiglas(proceso.getSiglasProceso());

		return procesoBean;
	}

	public LineaNegocioBean transformarLineaNegocioDistrib(
			Lineanegocio lineaNegocio) {
		if (lineaNegocio == null
				|| lineaNegocio.getPkCodigoLineanegocio() == null) {
			return null;
		}

		LineaNegocioBean lineaNegocioBean = new LineaNegocioBeanImpl();

		lineaNegocioBean.setCodigo(lineaNegocio.getPkCodigoLineanegocio());

		// lineaNegocioBean.setCodigoSAP(lineaNegocio.getCodigoSapLineanegocio().trim());

		// lineaNegocioBean.setCodigoSCC(lineaNegocio.getCodigoSccLineanegocio());

		lineaNegocioBean.setDescripcion(lineaNegocio
				.getDescripcionLineanegocio());

		lineaNegocioBean.setNombre(lineaNegocio.getNombreLineanegocio());

		// lineaNegocioBean.setUnidad(transformarUnidad(lineaNegocio.getUnidad()));

		return lineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarHora(pe.com
	 * .pacasmayo.sgcp.persistencia.dataObjects.Hora)
	 */
	public HoraBean transformarHora(Hora hora) {

		if (hora == null || hora.getPkCodigoHora() == null)
			return null;

		HoraBean horaBean = new HoraBeanImpl();
		horaBean.setCodigo(hora.getPkCodigoHora());
		horaBean.setHora(hora.getHoraHora());
		horaBean.setCodigoTurno(hora.getTurno().getPkCodigoTurno());

		return horaBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaHora(java
	 * .util.List)
	 */
	public List<HoraBean> transformarListaHora(List<Hora> horaBeans) {
		List<HoraBean> listaHoraBean = new ArrayList<HoraBean>();

		for (Iterator<Hora> iterator = horaBeans.iterator(); iterator.hasNext();) {
			Hora hora = iterator.next();
			HoraBean bean = transformarHora(hora);
			if (bean != null) {
				listaHoraBean.add(bean);
			}
		}
		return listaHoraBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaTableroControl
	 * (java.util.List)
	 */
	public List<TableroControlBean> transformarListaTableroControl(
			List<Tablerocontrol> tablerosControl) {

		List<TableroControlBean> tablerosControlBean = new ArrayList<TableroControlBean>();

		for (Iterator<Tablerocontrol> iterator = tablerosControl.iterator(); iterator
				.hasNext();) {
			Tablerocontrol tableroControl = iterator.next();
			TableroControlBean bean = transformarTableroControl(tableroControl);
			if (bean != null) {
				tablerosControlBean.add(bean);
			}
		}
		return tablerosControlBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarComponenteNotificacion
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion)
	 */
	public ComponenteNotificacionBean transformarComponenteNotificacion(
			Componentenotificacion componenteNotificacionHO) {

		ComponenteNotificacionBean componenteNotificacion = new ComponenteNotificacionBeanImpl();

		componenteNotificacion.setCodigo(componenteNotificacionHO
				.getPkCodigoComponentenotificacio());
		componenteNotificacion.setNotificacionProduccion(this
				.transformarNotificacionProduccion(componenteNotificacionHO
						.getNotificacionproduccion()));
		componenteNotificacion
				.setValorComponenteNotificacion(componenteNotificacionHO
						.getValorComponentenotificacion());
		componenteNotificacion
				.setComponente(this
						.transformarComponente(componenteNotificacionHO
								.getComponente()));

		return componenteNotificacion;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * tranformarListaComponenteNotificacion(java.util.List)
	 */
	public List<ComponenteNotificacionBean> tranformarListaComponenteNotificacion(
			List<Componentenotificacion> componentesNotificacionHO) {

		List<ComponenteNotificacionBean> componentesNotificacion = new ArrayList<ComponenteNotificacionBean>();
		Iterator<Componentenotificacion> it = componentesNotificacionHO
				.iterator();

		while (it.hasNext()) {
			componentesNotificacion.add(this
					.transformarComponenteNotificacion(it.next()));
		}
		return componentesNotificacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarNotificacionDiariaBean
	 * (pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean)
	 */
	public Notificaciondiaria transformarNotificacionDiariaBean(
			NotificacionDiariaBean notificacionDiariaBean) {
		if (notificacionDiariaBean == null)
			return null;
		Notificaciondiaria notificacionDiaria = new Notificaciondiaria();

		if (notificacionDiariaBean.getCodigo() != null
				&& notificacionDiariaBean.getCodigo() > 0)
			notificacionDiaria
					.setPkCodigoNotificaciondiaria(notificacionDiariaBean
							.getCodigo());

		if (notificacionDiariaBean.getEstadoNotificacion() != null
				&& notificacionDiariaBean.getEstadoNotificacion().getCodigo() > 0) {
			Estadonotificacion estado = new Estadonotificacion();
			estado.setPkCodigoEstadonotificacion(notificacionDiariaBean
					.getEstadoNotificacion().getCodigo());
			if (notificacionDiariaBean.getEstadoNotificacion()
					.getNombreEstadoNotificacion() != null
					&& !notificacionDiariaBean.getEstadoNotificacion()
							.getNombreEstadoNotificacion().equals(""))
				estado.setNombreEstadonotificacion(notificacionDiariaBean
						.getEstadoNotificacion().getNombreEstadoNotificacion());
			notificacionDiaria.setEstadonotificacion(estado);
		}

		if (notificacionDiariaBean.getFechaNotificacionDiaria() != null)
			notificacionDiaria
					.setFechaNotificaciondiaria(notificacionDiariaBean
							.getFechaNotificacionDiaria());

		if (notificacionDiariaBean.getLineaNegocio() != null
				&& notificacionDiariaBean.getLineaNegocio().getCodigo() > 0)
			notificacionDiaria
					.setLineanegocio(transformarLineaNegocioBean(notificacionDiariaBean
							.getLineaNegocio()));

		if (notificacionDiariaBean.getObservacionNotificacionDiaria() != null
				&& !notificacionDiariaBean.getObservacionNotificacionDiaria()
						.equals(""))
			notificacionDiaria
					.setObservacionNotificaciondiaria(notificacionDiariaBean
							.getObservacionNotificacionDiaria());

		if (notificacionDiariaBean.getTableroControl() != null
				&& notificacionDiariaBean.getTableroControl().getCodigo() > 0)
			notificacionDiaria
					.setTablerocontrol(transformarTableroControlBean(notificacionDiariaBean
							.getTableroControl()));

		if (notificacionDiariaBean.getUsuarioRegistra() != null)
			notificacionDiaria
					.setUsuarioByFkCodigoUsuarioRegistra(transformarUsuarioBean(notificacionDiariaBean
							.getUsuarioRegistra()));
		if (notificacionDiariaBean.getUsuarioAprueba() != null)
			notificacionDiaria
					.setUsuarioByFkCodigoUsuarioAprueba(transformarUsuarioBean(notificacionDiariaBean
							.getUsuarioAprueba()));

		return notificacionDiaria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUsuarioBean
	 * (pe.com.pacasmayo.sgcp.bean.UsuarioBean)
	 */
	public Usuario transformarUsuarioBean(UsuarioBean usuarioBean) {
		if (usuarioBean == null)
			return null;

		Usuario usuario = new Usuario();

		if (usuarioBean.getCodigo() != null && usuarioBean.getCodigo() > 0)
			usuario.setPkCodigoUsuario(usuarioBean.getCodigo());

		if (usuarioBean.getLogin() != null
				&& !usuarioBean.getLogin().equals(""))
			usuario.setLoginUsuario(usuarioBean.getLogin());

		if (usuarioBean.getPersona() != null)
			usuario.setPersona(transformarPersonaBean(usuarioBean.getPersona()));

		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarPersonaBean
	 * (pe.com.pacasmayo.sgcp.bean.PersonaBean)
	 */
	public Persona transformarPersonaBean(PersonaBean personaBean) {
		if (personaBean == null)
			return null;

		Persona persona = new Persona();

		if (personaBean.getCodigo() != null && personaBean.getCodigo() > 0)
			persona.setPkCodigoPersona(personaBean.getCodigo());

		if (personaBean.getNombre() != null
				&& !personaBean.getNombre().equals(""))
			persona.setNombrePersona(personaBean.getNombre());

		if (personaBean.getApellido() != null
				&& !personaBean.getApellido().equals(""))
			persona.setApellidoPersona(personaBean.getApellido());

		if (personaBean.getTelefono() != null
				&& !personaBean.getTelefono().equals(""))
			persona.setTelefonoPersona(personaBean.getTelefono());

		if (personaBean.getIdDocumento() != null
				&& !personaBean.getIdDocumento().equals(""))
			persona.setIddocumentoPersona(personaBean.getIdDocumento());

		if (personaBean.getCorreo() != null
				&& !personaBean.getCorreo().equals(""))
			persona.setCorreoPersona(personaBean.getCorreo());

		if (personaBean.getExtension() != null
				&& !personaBean.getExtension().equals(""))
			persona.setExtensionPersona(personaBean.getExtension());

		return persona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarTableroControlBean
	 * (pe.com.pacasmayo.sgcp.bean.TableroControlBean)
	 */
	public Tablerocontrol transformarTableroControlBean(
			TableroControlBean tableroControlBean) {
		if (tableroControlBean == null)
			return null;

		Tablerocontrol tableroControl = new Tablerocontrol();

		if (tableroControlBean.getCodigo() != null
				&& tableroControlBean.getCodigo() > 0)
			tableroControl.setPkCodigoTablerocontrol(tableroControlBean
					.getCodigo());

		if (tableroControlBean.getDescripcion() != null
				&& !tableroControlBean.getDescripcion().equals(""))
			tableroControl.setDescripcionTablerocontrol(tableroControlBean
					.getDescripcion());

		if (tableroControlBean.getNombre() != null
				&& !tableroControlBean.getNombre().equals(""))
			tableroControl.setNombreTablerocontrol(tableroControlBean
					.getNombre());

		return tableroControl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarLineaNegocioBean
	 * (pe.com.pacasmayo.sgcp.bean.LineaNegocioBean)
	 */
	public Lineanegocio transformarLineaNegocioBean(
			LineaNegocioBean lineaNegocioBean) {
		if (lineaNegocioBean == null)
			return null;

		Lineanegocio lineaNegocio = new Lineanegocio();

		if (lineaNegocioBean.getCodigo() != null
				&& lineaNegocioBean.getCodigo() > 0)
			lineaNegocio.setPkCodigoLineanegocio(lineaNegocioBean.getCodigo());

		if (lineaNegocioBean.getCodigoSAP() != null
				&& !lineaNegocioBean.getCodigoSAP().equals(""))
			lineaNegocio.setCodigoSapLineanegocio(lineaNegocioBean
					.getCodigoSAP());

		if (lineaNegocioBean.getDescripcion() != null
				&& !lineaNegocioBean.getDescripcion().equals(""))
			lineaNegocio.setDescripcionLineanegocio(lineaNegocioBean
					.getDescripcion());

		if (lineaNegocioBean.getNombre() != null
				&& !lineaNegocioBean.getNombre().equals(""))
			lineaNegocio.setNombreLineanegocio(lineaNegocioBean.getNombre());

		if (lineaNegocioBean.getUnidad() != null)
			lineaNegocio.setUnidad(transformarUnidadBean(lineaNegocioBean
					.getUnidad()));

		return lineaNegocio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarUnidadBean(
	 * pe.com.pacasmayo.sgcp.bean.UnidadBean)
	 */
	public Unidad transformarUnidadBean(UnidadBean unidadBean) {
		if (unidadBean == null)
			return null;

		Unidad unidad = new Unidad();

		if (unidadBean.getCodigo() != null && unidadBean.getCodigo() > 0)
			unidad.setPkCodigoUnidad(unidadBean.getCodigo());

		if (unidadBean.getCodigoSAP() != null
				&& !unidadBean.getCodigoSAP().equals(""))
			unidad.setCodigoSapUnidad(unidadBean.getCodigoSAP());

		if (unidadBean.getDescripcion() != null
				&& !unidadBean.getDescripcion().equals(""))
			unidad.setDescripcionUnidad(unidadBean.getDescripcion());

		if (unidadBean.getNombre() != null
				&& !unidadBean.getNombre().equals(""))
			unidad.setNombreUnidad(unidadBean.getNombre());

		return unidad;
	}

	@SuppressWarnings("unchecked")
	private FactorDosificacionBean transformarFactordosificacionParaConsulta(
			Factordosificacion factorDosificacion) {

		if (factorDosificacion == null
				|| factorDosificacion.getPkCodigoFactordosificacion() == null) {
			return null;
		}

		FactorDosificacionBean factorDosificacionBean = new FactorDosificacionBeanImpl();
		factorDosificacionBean.setCodigo(factorDosificacion
				.getPkCodigoFactordosificacion());

		if (factorDosificacion.getFactordosificacionregistromensus().size() > 0) {
			factorDosificacionBean
					.setFactorDosificacionRegistroMensual(transformarListaFactorDosificacionRegistroMensualParaConsultaFactorDosificacion(factorDosificacion
							.getFactordosificacionregistromensus()));

		}

		if (factorDosificacion.getHojaruta() != null) {
			factorDosificacionBean
					.setHojaRuta(transformarHojaRutaParaCombos(factorDosificacion
							.getHojaruta()));
		}

		if (factorDosificacion.getComponente() != null)
			factorDosificacionBean
					.setComponente(transformarComponenteSinFactorDosificacion(factorDosificacion
							.getComponente()));

		return factorDosificacionBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaFactordosificacionParaConsulta(java.util.List)
	 */
	public List<FactorDosificacionBean> transformarListaFactordosificacionParaConsulta(
			List<Factordosificacion> listaFactorDosificacion) {

		List<FactorDosificacionBean> factorDosificacionImplBeans = new ArrayList<FactorDosificacionBean>();

		for (Iterator<Factordosificacion> iterator = listaFactorDosificacion
				.iterator(); iterator.hasNext();) {

			Factordosificacion factorDosificacion = iterator.next();
			FactorDosificacionBean factorDosificacionImplBean = transformarFactordosificacionParaConsulta(factorDosificacion);

			factorDosificacionImplBeans.add(factorDosificacionImplBean);
		}
		return factorDosificacionImplBeans;
	}

	private FactorDosificacionRegistroMensualBean transformarFactordosificacionregistromensualParaConsultaFactorDosificacion(
			Factordosificacionregistromensu factorDosificacionRegistroMensual) {
		if (factorDosificacionRegistroMensual == null
				|| factorDosificacionRegistroMensual
						.getPkCodigoFactordosificacionreg() == null) {
			return null;
		}

		FactorDosificacionRegistroMensualBean factorDosificacionRegistroMensualBean = new FactorDosificacionRegistroMensualBeanImpl();

		factorDosificacionRegistroMensualBean
				.setCodigo(factorDosificacionRegistroMensual
						.getPkCodigoFactordosificacionreg());

		factorDosificacionRegistroMensualBean
				.setAnno(factorDosificacionRegistroMensual
						.getAnnoFactordosificacionregistro());

		factorDosificacionRegistroMensualBean
				.setMes(factorDosificacionRegistroMensual
						.getMesFactordosificacionregistrom());

		return factorDosificacionRegistroMensualBean;
	}

	private FactorDosificacionRegistroMensualBean[] transformarListaFactorDosificacionRegistroMensualParaConsultaFactorDosificacion(
			Set<Factordosificacionregistromensu> factorDosificacionRegistroMensualBeans) {

		FactorDosificacionRegistroMensualBean[] factorDosificacionRegistroMensualImplBeans = new FactorDosificacionRegistroMensualBean[12];

		for (Iterator<Factordosificacionregistromensu> iterator = factorDosificacionRegistroMensualBeans
				.iterator(); iterator.hasNext();) {

			Factordosificacionregistromensu factorDosificacionRegistroMensual = iterator
					.next();
			FactorDosificacionRegistroMensualBean factorDosificacionRegistroMensualBean = transformarFactordosificacionregistromensualParaConsultaFactorDosificacion(factorDosificacionRegistroMensual);

			factorDosificacionRegistroMensualImplBeans[factorDosificacionRegistroMensualBean
					.getMes() - 1] = factorDosificacionRegistroMensualBean;
		}
		return factorDosificacionRegistroMensualImplBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaHojaRutaParaConsulta(java.util.List)
	 */
	public List<HojaRutaBean> transformarListaHojaRutaParaConsulta(
			List<Hojaruta> listaHojaruta) {

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

		for (Iterator<Hojaruta> iterator = listaHojaruta.iterator(); iterator
				.hasNext();) {

			Hojaruta hojaruta = iterator.next();
			listaHojaRutaBean.add(transformarHojaRutaParaConsulta(hojaruta));
		}
		return listaHojaRutaBean;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaHojaRutaConFactoresDosificacion(java.util.List)
	 */
	public List<HojaRutaBean> transformarListaHojaRutaConFactoresDosificacion(
			List<Hojaruta> listaHojaruta) {

		List<HojaRutaBean> listaHojaRutaBean = new ArrayList<HojaRutaBean>();

		for (Iterator<Hojaruta> iterator = listaHojaruta.iterator(); iterator
				.hasNext();) {

			Hojaruta hojaruta = iterator.next();
			listaHojaRutaBean
					.add(transformarHojaRutaConFactorDosificacion(hojaruta));
		}
		return listaHojaRutaBean;

	}

	private HojaRutaBean transformarHojaRutaParaConsulta(Hojaruta hojaRuta) {
		if (hojaRuta == null || hojaRuta.getPkCodigoHojaruta() == null) {
			return null;
		}

		HojaRutaBean hojaRutaBean = new HojaRutaBeanImpl();

		hojaRutaBean.setCodigo(hojaRuta.getPkCodigoHojaruta());
		hojaRutaBean.setNombre(hojaRuta.getNombreHojaruta());

		hojaRutaBean.setEstadoHojaRuta(transformarEstadoHojaRuta(hojaRuta
				.getEstadohojaruta()));
		hojaRutaBean.setProduccion(transformarProduccion(hojaRuta
				.getProduccion()));

		return hojaRutaBean;
	}

	private HojaRutaBean transformarHojaRutaConFactorDosificacion(
			Hojaruta hojaRuta) {
		if (hojaRuta == null || hojaRuta.getPkCodigoHojaruta() == null) {
			return null;
		}

		HojaRutaBean hojaRutaBean = new HojaRutaBeanImpl();

		hojaRutaBean.setCodigo(hojaRuta.getPkCodigoHojaruta());
		hojaRutaBean.setNombre(hojaRuta.getNombreHojaruta());

		hojaRutaBean.setEstadoHojaRuta(transformarEstadoHojaRuta(hojaRuta
				.getEstadohojaruta()));

		if (hojaRuta.getFactordosificacions() != null
				&& hojaRuta.getFactordosificacions().size() > 0)
			hojaRutaBean
					.setFactorDosificacions(transformarListaFactordosificacionSinHojaRuta(hojaRuta
							.getFactordosificacions()));

		return hojaRutaBean;
	}

	private HojaRutaBean transformarHojaRutaParaCombos(Hojaruta hojaRuta) {
		if (hojaRuta == null || hojaRuta.getPkCodigoHojaruta() == null) {
			return null;
		}

		HojaRutaBean hojaRutaBean = new HojaRutaBeanImpl();
		hojaRutaBean.setCodigo(hojaRuta.getPkCodigoHojaruta());

		hojaRutaBean.setNombre(hojaRuta.getNombreHojaruta());

		return hojaRutaBean;
	}

	private ProductoBean transformarProductoParaCombos(Producto producto) {
		if (producto == null || producto.getPkCodigoProducto() == null) {
			return null;
		}

		ProductoBean productoBean = new ProductoBeanImpl();
		productoBean.setCodigo(producto.getPkCodigoProducto());
		productoBean.setNombre(producto.getNombreProducto());

		return productoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaProductoParaCombos(java.util.List)
	 */
	public List<ProductoBean> transformarListaProductoParaCombos(
			List<Producto> productos) {

		List<ProductoBean> listaProductoBean = new ArrayList<ProductoBean>();

		for (Iterator<Producto> iterator = productos.iterator(); iterator
				.hasNext();) {

			Producto producto = iterator.next();
			listaProductoBean.add(transformarProductoParaCombos(producto));
		}

		return listaProductoBean;
	}

	private ProcesoBean transformarProcesoParaCombos(Proceso proceso) {
		if (proceso == null || proceso.getPkCodigoProceso() == null) {
			return null;
		}

		ProcesoBean procesoBean = new ProcesoBeanImpl();
		procesoBean.setCodigo(proceso.getPkCodigoProceso());
		procesoBean.setNombre(proceso.getNombreProceso());

		return procesoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaProcesoParaCombos(java.util.List)
	 */
	public List<ProcesoBean> transformarListaProcesoParaCombos(
			List<Proceso> procesos) {

		List<ProcesoBean> listaProcesoBean = new ArrayList<ProcesoBean>();

		for (Iterator<Proceso> iterator = procesos.iterator(); iterator
				.hasNext();) {

			Proceso proceso = iterator.next();
			listaProcesoBean.add(transformarProcesoParaCombos(proceso));
		}

		return listaProcesoBean;
	}

	private UnidadBean transformarUnidadParaCombos(Unidad unidad) {
		if (unidad == null || unidad.getPkCodigoUnidad() == null) {
			return null;
		}

		UnidadBean unidadBean = new UnidadBeanImpl();
		unidadBean.setCodigo(unidad.getPkCodigoUnidad());
		unidadBean.setNombre(unidad.getNombreUnidad());

		return unidadBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaUnidadParaCombos(java.util.List)
	 */
	public List<UnidadBean> transformarListaUnidadParaCombos(
			List<Unidad> unidades) {

		List<UnidadBean> listaUnidadBean = new ArrayList<UnidadBean>();

		for (Iterator<Unidad> iterator = unidades.iterator(); iterator
				.hasNext();) {

			Unidad unidad = iterator.next();
			listaUnidadBean.add(transformarUnidadParaCombos(unidad));
		}

		return listaUnidadBean;
	}

	private LineaNegocioBean transformarLineaNegocioParaCombos(
			Lineanegocio lineaNegocio) {
		if (lineaNegocio == null
				|| lineaNegocio.getPkCodigoLineanegocio() == null) {
			return null;
		}

		LineaNegocioBean lineaNegocioBean = new LineaNegocioBeanImpl();
		lineaNegocioBean.setCodigo(lineaNegocio.getPkCodigoLineanegocio());
		lineaNegocioBean.setNombre(lineaNegocio.getNombreLineanegocio());

		return lineaNegocioBean;
	}

	public List<LineaNegocioBean> transformarListaLineaNegocioParaCombos(
			List<Lineanegocio> lineaNegocios) {

		List<LineaNegocioBean> listaLineaNegocioBean = new ArrayList<LineaNegocioBean>();

		for (Iterator<Lineanegocio> iterator = lineaNegocios.iterator(); iterator
				.hasNext();) {

			Lineanegocio lineanegocio = iterator.next();
			listaLineaNegocioBean
					.add(transformarLineaNegocioParaCombos(lineanegocio));
		}

		return listaLineaNegocioBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarTipoCategoriaProducto
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipocategoriaproducto)
	 */
	public TipoCategoriaProductoBean transformarTipoCategoriaProducto(
			Tipocategoriaproducto tipoCategoriaProducto) {

		if (tipoCategoriaProducto == null
				|| tipoCategoriaProducto.getPkCodigoTipocategoriaproducto() == null) {
			return null;
		}

		TipoCategoriaProductoBean tipoCategoriaProductoBean = new TipoCategoriaProductoBeanImpl();
		tipoCategoriaProductoBean.setCodigo(tipoCategoriaProducto
				.getPkCodigoTipocategoriaproducto());
		tipoCategoriaProductoBean.setNombre(tipoCategoriaProducto
				.getNombreTipocategoriaproducto());

		return tipoCategoriaProductoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaTipoCategoriaProducto(java.util.List)
	 */
	public List<TipoCategoriaProductoBean> transformarListaTipoCategoriaProducto(
			List<Tipocategoriaproducto> listaTipoCategoriaProducto) {

		List<TipoCategoriaProductoBean> listaTipoCategoriaProductoBean = new ArrayList<TipoCategoriaProductoBean>();

		for (Iterator<Tipocategoriaproducto> iterator = listaTipoCategoriaProducto
				.iterator(); iterator.hasNext();) {
			Tipocategoriaproducto tipocategoriaproducto = iterator.next();
			listaTipoCategoriaProductoBean
					.add(transformarTipoCategoriaProducto(tipocategoriaproducto));
		}

		return listaTipoCategoriaProductoBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaGruposUsuario
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Grupousuario)
	 */
	public List<GrupoUsuarioBean> transformarListaGruposUsuario(
			List<Grupousuario> grupos) {

		List<GrupoUsuarioBean> gruposBeans = new ArrayList<GrupoUsuarioBean>();
		for (Iterator<Grupousuario> iterator = grupos.iterator(); iterator
				.hasNext();) {
			Grupousuario grupo = iterator.next();
			GrupoUsuarioBean grupoBean = transformarGrupoUsuario(grupo, true);
			if (grupoBean != null)
				gruposBeans.add(grupoBean);
		}

		return gruposBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#transformarListaCargos
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cargo)
	 */
	public List<CargoBean> transformarListaCargos(List<Cargo> cargos) {

		List<CargoBean> cargosBeans = new ArrayList<CargoBean>();
		for (Iterator<Cargo> iterator = cargos.iterator(); iterator.hasNext();) {
			Cargo cargo = iterator.next();
			CargoBean cargoBean = transformarCargo(cargo);
			if (cargoBean != null)
				cargosBeans.add(cargoBean);
		}

		return cargosBeans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.factory.BeanFactory#
	 * transformarListaUsuarioGruposUsuarios
	 * (pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuariogrupousuario)
	 */
	public List<UsuarioGrupoUsuarioBean> transformarListaUsuarioGruposUsuarios(
			List<Usuariogrupousuario> usuarioGrupoLista) {

		List<UsuarioGrupoUsuarioBean> usuarioGrupoUsuarioBeans = new ArrayList<UsuarioGrupoUsuarioBean>();
		for (Iterator<Usuariogrupousuario> iterator = usuarioGrupoLista
				.iterator(); iterator.hasNext();) {
			Usuariogrupousuario usuarioGrupo = iterator.next();
			UsuarioGrupoUsuarioBean usuarioGrupoUsuBean = transformarUsuarioGrupoUsuario(usuarioGrupo);
			if (usuarioGrupoUsuBean != null)
				usuarioGrupoUsuarioBeans.add(usuarioGrupoUsuBean);
		}

		return usuarioGrupoUsuarioBeans;
	}

	/**
	 * 
	 */
	public Estadousuario transformarEstadoUsuarioDTO(
			EstadoUsuarioBean estadoBean) {
		Estadousuario estado = new Estadousuario();
		estado.setPkCodigoEstadousuario(estadoBean.getCodigo());
		estado.setDescripcionEstadousuario(estadoBean.getDescripcion());
		estado.setNombreEstadousuario(estadoBean.getNombre());
		return estado;
	}

	/**
	 * @param grupoBean
	 * @return
	 */
	public Grupousuario transformarGrupoUsuarioDTO(GrupoUsuarioBean grupoBean) {
		Grupousuario grupo = new Grupousuario();
		grupo.setDescripcionGrupousuario(grupoBean.getDescripcion());
		grupo.setGrupousuarioprivilegios(new HashSet<GrupoUsuarioPrivilegioBean>(
				grupoBean.getGrupoUsuarioPrivilegios()));
		grupo.setNombreGrupousuario(grupoBean.getNombre());
		grupo.setPkCodigoGrupousuario(grupoBean.getCodigo());
		return grupo;
	}

	/**
	 * @param grupoBean
	 * @return
	 */
	public Usuariogrupousuario transformarUsuarioGrupoUsuarioDTO(
			UsuarioGrupoUsuarioBean grupoBean, Usuario usuario) {
		Usuariogrupousuario grupo = new Usuariogrupousuario();
		grupo.setGrupousuario(transformarGrupoUsuarioDTO(grupoBean
				.getGrupoUsuarioBean()));
		grupo.setPkCodigoUsuariogrupousuario(grupoBean.getCodigo());
		grupo.setUsuario(usuario);
		return grupo;
	}

	public PlanAnualBean transformarPlanAnualParaConsulta(Plananual plananual) {

		if (plananual == null || plananual.getPkCodigoPlananual() == null)
			return null;

		PlanAnualBean planAnualBean = new PlanAnualBeanImpl();

		planAnualBean.setAnno(plananual.getAnnoPlananual());
		planAnualBean.setCodigo(plananual.getPkCodigoPlananual());
		planAnualBean.setComentario(plananual.getObservacionPlananual());
		planAnualBean.setEstadoPlan(transformarEstadoPlan(plananual
				.getEstadoplananual()));

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
		if (plananual.getFechaAprobacionPlananual() != null) {
			planAnualBean.setFechaAprueba(dateFormat.format(plananual
					.getFechaAprobacionPlananual()));
		}

		planAnualBean.setFechaRegistra(dateFormat.format(plananual
				.getFechaRegistroPlananual()));

		planAnualBean.setLineaNegocio(transformarLineaNegocio(plananual
				.getLineanegocio()));

		planAnualBean.setMesCorteVersion(plananual
				.getMesCorteVersionPlananual());

		planAnualBean.setNecesidadComercial(plananual
				.getNecesidadComercialPlananual());

		planAnualBean.setProduccionAnual(plananual.getProduccionPlananual());
		planAnualBean.setUsuarioAprueba(transformarUsuario(plananual
				.getUsuarioByFkCodigoUsuarioAprueba()));
		planAnualBean.setUsuarioRegistra(transformarUsuario(plananual
				.getUsuarioByFkCodigoUsuarioRegistra()));
		planAnualBean.setVersion(plananual.getVersionPlananual());

		planAnualBean.setUsuarioRegistraConFecha("-");
		planAnualBean.setUsuarioApruebaConFecha("-");

		if (planAnualBean.getUsuarioRegistra() != null
				&& planAnualBean.getUsuarioRegistra().getCodigo() > 0
				&& planAnualBean.getFechaRegistra() != null)
			planAnualBean.setUsuarioRegistraConFecha(planAnualBean
					.getUsuarioRegistra().getLogin()
					+ " - "
					+ planAnualBean.getFechaRegistra().toString());

		if (planAnualBean.getUsuarioAprueba() != null
				&& planAnualBean.getUsuarioAprueba().getCodigo() > 0
				&& planAnualBean.getFechaAprueba() != null)
			planAnualBean.setUsuarioApruebaConFecha(planAnualBean
					.getUsuarioAprueba().getLogin()
					+ " - "
					+ planAnualBean.getFechaAprueba().toString());

		return planAnualBean;
	}

	public ParametroSistemaBean transformarParametroSistema(
			ParametroSistema parametro) {
		ParametroSistemaBean parametroBean = new ParametroSistemaBeanImpl();
		if (parametro == null) {
			return null;
		}
		parametroBean.setCodigo(parametro.getPkCodigoParametro());
		parametroBean.setNombre(parametro.getNombreParametro());
		parametroBean.setDescripcion(parametro.getDescripcionParametro());
		parametroBean.setValor(parametro.getValorParametro());
		return parametroBean;
	}

	public OrdenReporteBean transformarOrdenReporte(Ordenreporte ordenreporte) {
		if (ordenreporte == null
				|| ordenreporte.getPkCodigoOrdenreporte() == null)
			return null;

		OrdenReporteBean ordenReporteBean = new OrdenReporteBeanImpl();

		ordenReporteBean.setCodigo(ordenreporte.getPkCodigoOrdenreporte());
		ordenReporteBean.setProceso(this.transformarProcesoBasico(ordenreporte
				.getProceso()));
		ordenReporteBean.setProducto(this
				.transformarProductoBasico(ordenreporte.getProducto()));
		ordenReporteBean.setOrdenReporte(ordenreporte.getOrdenReporte());
		ordenReporteBean.setOrdenProcesoProducto(ordenreporte
				.getOrdenProcesoProducto());
		ordenReporteBean
				.setTipoOrdenReporte(ordenreporte.getTipoOrdenReporte());

		return ordenReporteBean;
	}

	public List<OrdenReporteBean> transformarListaOrdenResumen(
			List<Ordenreporte> listaOrdenesReporte) {
		if (listaOrdenesReporte != null) {
			List<OrdenReporteBean> listaReporteBean = new ArrayList<OrdenReporteBean>();
			for (Ordenreporte ordenreporte : listaOrdenesReporte) {
				listaReporteBean
						.add(this.transformarOrdenReporte(ordenreporte));
			}
			return listaReporteBean;
		}
		return null;
	}

	public List<ParametroSistemaBean> transformarParametrosSistema(
			List<ParametroSistema> parametros) {
		if (parametros == null) {
			return null;
		}
		List<ParametroSistemaBean> parametrosSistema = null;
		if (parametros != null && parametros.size() > 0) {
			parametrosSistema = new ArrayList<ParametroSistemaBean>();
			for (ParametroSistema parametroSistema : parametros) {
				parametrosSistema.add(this
						.transformarParametroSistema(parametroSistema));
			}
		}
		return parametrosSistema;
	}

	public List<RendimientoTermicoBean> transformarListaRendimientoTermico(
			List<RendimientoTermico> listRendimientoTermico)
			throws LogicaException {
		if (listRendimientoTermico != null) {
			List<RendimientoTermicoBean> listRendimientoTermicoBean = new ArrayList<RendimientoTermicoBean>();
			for (RendimientoTermico rendimientoTermicoBean : listRendimientoTermico) {
				listRendimientoTermicoBean.add(this
						.transformarRendimientoTermico(rendimientoTermicoBean));
			}
			return listRendimientoTermicoBean;
		} else {
			return null;
		}
	}

	public RendimientoTermicoBean transformarRendimientoTermico(
			RendimientoTermico rendimientoTermico) {
		RendimientoTermicoBean rendimientoTermicoBean = new RendimientoTermicoBeanImpl();

		rendimientoTermicoBean.setPkCodigoRendimientoTermico(rendimientoTermico
				.getPkCodigoRendimientoTermico());
		rendimientoTermicoBean.setNombrePuestoTrabajo(rendimientoTermico
				.getPuestoTrabajo().getNombrePuestotrabajo());
		rendimientoTermicoBean.setNombreProducto(rendimientoTermico
				.getProducto().getNombreProducto());
		rendimientoTermicoBean.setValorkCal1(rendimientoTermico
				.getValorKiloCalorias1());
		rendimientoTermicoBean.setValorkCal2(rendimientoTermico
				.getValorKiloCalorias2());
		rendimientoTermicoBean.setCodigoPuestoTrabajo(rendimientoTermico
				.getPuestoTrabajo().getPkCodigoPuestotrabajo());
		rendimientoTermicoBean.setCodigoProducto(rendimientoTermico
				.getProducto().getPkCodigoProducto());

		return rendimientoTermicoBean;
	}

	public CapacidadBolsaProductoBean transformarCapacidadBolsaProduccion(
			Capacidadbolsaproducto capacidadbolsaproducto) {
		CapacidadBolsaProductoBean capacidadBolsaProductoBean = null;

		if (capacidadbolsaproducto == null) {
			return capacidadBolsaProductoBean;
		}
		capacidadBolsaProductoBean = new CapacidadBolsaProductoBeanImpl();
		capacidadBolsaProductoBean.setCodigo(capacidadbolsaproducto
				.getPkCodigoCapacidadbolsaproducto());
		capacidadBolsaProductoBean.setCodigoSapProducto(capacidadbolsaproducto
				.getCodigoSapProducto());
		capacidadBolsaProductoBean.setCapacidadNetaBolsa(capacidadbolsaproducto
				.getCapacidadNetaBolsa());
		capacidadBolsaProductoBean
				.setCapacidadBrutaBolsa(capacidadbolsaproducto
						.getCapacidadBrutaBolsa());

		return capacidadBolsaProductoBean;
	}

	public List<TipoConsumoBean> transformarListaTipoConsumo(
			List<Tipoconsumo> listaTipoConsumo) {
		List<TipoConsumoBean> tiposConsumo = null;
		if (listaTipoConsumo == null) {
			return tiposConsumo;
		}
		tiposConsumo = new ArrayList<TipoConsumoBean>();
		for (Tipoconsumo tipoConsumo : listaTipoConsumo) {
			tiposConsumo.add(this.tranformarTipoConsumo(tipoConsumo));
		}
		return tiposConsumo;
	}

	public TipoConsumoBean tranformarTipoConsumo(Tipoconsumo tipoConsumo) {
		TipoConsumoBean tipoConsumoBean = null;

		if (tipoConsumo == null) {
			return tipoConsumoBean;
		}
		tipoConsumoBean = new TipoConsumoBeanImpl();
		tipoConsumoBean.setCodigo(tipoConsumo.getPkCodigoTipoconsumo());
		tipoConsumoBean.setNombre(tipoConsumo.getNombreTipoconsumo());

		return tipoConsumoBean;
	}

}