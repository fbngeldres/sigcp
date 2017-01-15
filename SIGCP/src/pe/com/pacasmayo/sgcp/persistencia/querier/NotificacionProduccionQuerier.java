package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.bean.ResumenNotificacionItemRepBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillareporte;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestin y Control de la Producción) 
 * Archivo: NotificacionProduccionQuerier.java
 * Modificado: Jun 25, 2010 3:28:45 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class NotificacionProduccionQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static final String CODIGO_NOTIFICACIONDIARIA = "notificaciondiaria.pkCodigoNotificaciondiaria";
	private static Logger log = Logger.getLogger(NotificacionProduccionQuerier.class);

	/**
	 * Busca una lista de notificaciones por el codigo de la cabecera
	 * (notificacion diara) y opcionalmente por el puesto de trabajo y producto
	 * 
	 * @param codigoNotificacionDiaria
	 * @param codigoPuestoTrabajo
	 * @param codigoProducto
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Notificacionproduccion> obtenerPorNotificacionDiariaOrdenadoPuestoTurnoHora(Long codigoNotificacionDiaria,
			Long codigoPuestoTrabajo, Long codigoProducto) throws AplicacionException {
		List<Notificacionproduccion> notificacionesProduccion = null;
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder("FROM Notificacionproduccion np  WHERE");
			queryStr.append(" np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria ");

			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				queryStr.append(" AND np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo");

			if (codigoProducto != null && codigoProducto > 0)
				queryStr.append(" AND np.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");

			queryStr.append(" Order by np.fechaNotificacionproduccion,  np.puestotrabajo.nombrePuestotrabajo, np.ordenproduccion.produccion.producto.nombreProducto,  np.hora.pkCodigoHora");
			// queryStr.append(" Order by np.puestotrabajo.nombrePuestotrabajo,
			// np.hora.turno.horaInicioTurno, np.hora.horaHora");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);

			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			if (codigoProducto != null && codigoProducto > 0)
				query.setLong("codigoProducto", codigoProducto);

			notificacionesProduccion = query.list();

		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			log.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException);
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException);
		} catch (HibernateException hException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			log.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException);
		}
		return notificacionesProduccion;
	}

	/**
	 * Obtiene las Notificaciones de produccion segun el codigo de la
	 * notificacion diaria a la que pertenecen
	 * 
	 * @param codNotifDiaria codigo de la notificacion diaria
	 * @return List<Notificacionproduccion>
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Notificacionproduccion> findByCodigoNotificacion(Long codNotifDiaria)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		String mensajeError = null;
		try {
			return Querier.findByProperty(Notificacionproduccion.class, CODIGO_NOTIFICACIONDIARIA, codNotifDiaria);
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			log.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException);
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
		} catch (HibernateException hException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			log.error(mensajeError, hException);
			throw new EntornoEjecucionException(mensajeError, hException);
		}
	}

	/**
	 * Guarda en BD una notificacion de produccion
	 * 
	 * @param notificacionProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void save(Notificacionproduccion notificacionProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(notificacionProduccion);
	}

	/**
	 * Obtiene una notificacion de produccion segun si id
	 * 
	 * @param codigo id
	 * @return Notificacionproduccion
	 * @throws ElementoNoEncontradoException si elemento no existe en la BD
	 */
	public static Notificacionproduccion getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Notificacionproduccion.class, codigo);
	}

	/**
	 * Actualiza las propiedades de una notificacion de produccion
	 * 
	 * @param notificacionProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void update(Notificacionproduccion notificacionProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(notificacionProduccion);
	}

	/**
	 * Obtiene las notificaciones de produccion segun la notificacion diaria
	 * 
	 * @param codigoNotificacionDiaria Long
	 * @return List<Ordenproduccion>
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> obtenerOrdenesProduccionSegunNotifDiaria(Long codigoNotificacionDiaria)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		List<Ordenproduccion> ordenesProduccion = null;
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT np.ordenproduccion FROM Notificacionproduccion np WHERE ");
			queryStr.append("np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);

			ordenesProduccion = query.list();

		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			log.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException);
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException);
		} catch (HibernateException hException) {
			hException.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			log.error(mensajeError, hException);
			throw new EntornoEjecucionException(mensajeError, hException);
		}
		return ordenesProduccion;
	}

	/**
	 * Obtiene ordenes de produccion de las notificaciones de produccion segun
	 * la notificacion diaria y puesto de trabjo
	 * 
	 * @param codigoNotificacionDiaria Long
	 * @param codigoPuestoTrabajo Long
	 * @return List<Ordenproduccion>
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Ordenproduccion> obtenerOrdenesProduccionSegunNotifDiariayPuestoTrab(Long codigoNotificacionDiaria,
			Long codigoPuestoTrabajo) throws SesionVencidaException, EntornoEjecucionException {
		List<Ordenproduccion> ordenesProduccion = new ArrayList<Ordenproduccion>();
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT(np.ordenproduccion),np.ordenproduccion.produccion.proceso.ordenEjecucionProceso FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria AND ");
			queryStr.append("np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabjo ORDER BY np.ordenproduccion.produccion.proceso.ordenEjecucionProceso ASC");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);
			query.setLong("codigoPuestoTrabjo", codigoPuestoTrabajo);

			List<Object[]> lista = query.list();

			for (Object[] objects : lista) {
				ordenesProduccion.add((Ordenproduccion) objects[0]);
			}

			return ordenesProduccion;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);

		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Obtiene los puesto de trabajo de las notificaaciones de produccion segun
	 * la notificacion diaria
	 * 
	 * @param codigoNotificacionDiaria Long
	 * @return List<Puestotrabajo>
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Puestotrabajo> obtenerPuestosDeTrabajoSegunNotifDiaria(Long codigoNotificacionDiaria)
			throws SesionVencidaException, EntornoEjecucionException {
		List<Puestotrabajo> puestosDeTrabajo = null;
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT(np.puestotrabajo),np.ordenproduccion.produccion.proceso.ordenEjecucionProceso  FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria");
			queryStr.append(" ORDER BY np.ordenproduccion.produccion.proceso.ordenEjecucionProceso ASC ");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);
			puestosDeTrabajo = new ArrayList<Puestotrabajo>();
			List<Object[]> li = query.list();
			for (Object[] objects : li) {
				puestosDeTrabajo.add((Puestotrabajo) objects[0]);
			}

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);

		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
		return puestosDeTrabajo;
	}

	/**
	 * Obtiene una lista de notificaciones produccion segun la notificacion
	 * diaria, puesto de trabajo y orden de produccion
	 * 
	 * @param codigoNotificacionDiaria
	 * @param codigoPuestoTrabajo
	 * @param codigoOrdenProd
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Componentenotificacion> obtenerCompNotifSegunNotifPuestoYOrden(Long codigoNotificacionDiaria,
			Long codigoPuestoTrabajo, Long codigoOrdenProd) throws SesionVencidaException, EntornoEjecucionException {
		List<Componentenotificacion> notificacionesProduccion = null;
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT cn FROM Componentenotificacion cn  WHERE ");
			queryStr.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria AND");
			queryStr.append("cn.notificacionproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestotrabajo AND");
			queryStr.append("cn.notificacionproduccion.ordenproduccion.pkCodigoOrdenproduccion = :codigoOrdenproduccion");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);
			query.setLong("codigoPuestotrabajo", codigoPuestoTrabajo);
			query.setLong("codigoOrdenproduccion", codigoOrdenProd);

			notificacionesProduccion = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
		return notificacionesProduccion;
	}

	/**
	 * Obtiene una lista de notificaciones produccion segun la notificacion
	 * diaria, puesto de trabajo y orden de produccion
	 * 
	 * @param codigoNotificacionDiaria
	 * @param codigoPuestoTrabajo
	 * @param codigoOrdenProd
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Columnareporte> obtenerColsPlantillaReporteSegunDatosNotifYPuesto(Long codigoLineaNegocio,
			Long codigoTableroControl, Date fechaRegistro, Long codigoPuestoTrabajo) throws SesionVencidaException,
			EntornoEjecucionException {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select np.plantillaproducto.plantillareporte FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio AND ");
			queryStr.append("np.notificaciondiaria.tablerocontrol.pkCodigoTablerocontrol = :codigoTableroControl AND ");
			queryStr.append("np.notificaciondiaria.fechaNotificaciondiaria = :fecha  AND ");
			queryStr.append("np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestotrabajo AND ");
			queryStr.append("np.hora.horaHora = :hora");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setLong("codigoTableroControl", codigoTableroControl);
			query.setDate("fecha", fechaRegistro);
			query.setLong("codigoPuestotrabajo", codigoPuestoTrabajo);

			// el valor de la hora es indistinto me interes es obtener solo una
			// notificacion produccion del puesto de trabajo
			// para a traves de ella obtener la plantilla reporte con la que se
			// guardo en bd
			Integer hora = 8;
			query.setShort("hora", hora.shortValue());

			Plantillareporte plantilla = (Plantillareporte) query.uniqueResult();
			return new ArrayList<Columnareporte>(plantilla.getColumnareportes());

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerComponentesSegunNotificacionDiaria(Long codigoNotificacionDiaria, Session session)
			throws SesionVencidaException, EntornoEjecucionException {
		List<Producto> productos = null;
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT cn.componente.productoByFkCodigoProductoComponente FROM Componentenotificacion cn  WHERE ");
			queryStr.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotif AND ");
			queryStr.append("cn.componente.productoByFkCodigoProductoComponente.tipoproducto.pkCodigoTipoproducto != :codTipoProd");

			Query query = session.createQuery(queryStr.toString());
			query.setLong("codNotif", codigoNotificacionDiaria);
			query.setLong("codTipoProd", 1L);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
		return productos;
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerComponentesNoNotificados(Long codigoNotificacionDiaria, Session session, Long codLineaNeg)
			throws SesionVencidaException, EntornoEjecucionException {
		List<Producto> productos = null;
		String mensajeError = null;
		try {
			StringBuilder hql = new StringBuilder();
			
			
//			hql.append("SELECT DISTINCT(prod) FROM Producto prod, Hojaruta hr  WHERE ");
//			hql.append("prod.tipoproducto.pkCodigoTipoproducto != :codTipoProd AND ");
//			hql.append("hr.produccion.producto.pkCodigoProducto = prod.pkCodigoProducto AND ");
//			hql.append("hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
//			hql.append("prod.pkCodigoProducto NOT IN ");
//			hql.append("(SELECT DISTINCT cn.componente.productoByFkCodigoProductoComponente.pkCodigoProducto ");
//			hql.append("FROM Componentenotificacion cn  WHERE ");
//			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotif)");

			
			hql.append("SELECT hr.produccion.producto FROM Hojaruta hr  WHERE ");
			hql.append("hr.produccion.producto.tipoproducto.pkCodigoTipoproducto != :codTipoProd AND ");
			//hql.append("hr.produccion.producto.pkCodigoProducto = prod.pkCodigoProducto AND ");
			hql.append("  hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			hql.append("  hr.estadohojaruta.pkCodigoEstadohojaruta = :codigoHR AND ");
			hql.append(" hr.produccion.producto.pkCodigoProducto NOT IN ");
			hql.append("(SELECT DISTINCT cn.componente.productoByFkCodigoProductoComponente.pkCodigoProducto ");
			hql.append("FROM Componentenotificacion cn  WHERE ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotif)");

			
			
			Query query = session.createQuery(hql.toString());
			query.setLong("codNotif", codigoNotificacionDiaria);
			query.setLong("codTipoProd", 1L);
			query.setLong("codigoHR", 1L);
			query.setLong("codLineaNeg", codLineaNeg);

			productos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
		return productos;
	}

	/**
	 * Obtiene la cantidad de horas que opera un puesto de trabajo para una
	 * notificacion diaria y una orden de produccion
	 * 
	 * @param codigoNotificacionDiaria Long
	 * @param codigoPuestoTrabajo Long
	 * @param codigoOrdenProd Long
	 * @return Double
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Notificacionproduccion> obtenerPorNotifPuestoTrabYOrden(Long codigoNotificacionDiaria,
			Long codigoPuestoTrabajo, Long codigoOrdenProd) throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = null;
		List<Notificacionproduccion> notificacionesProduccion = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT(np) FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria AND ");
			queryStr.append("np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestotrabajo AND ");
			queryStr.append("np.ordenproduccion.pkCodigoOrdenproduccion = :codigoOrdenproduccion");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);
			query.setLong("codigoPuestotrabajo", codigoPuestoTrabajo);
			query.setLong("codigoOrdenproduccion", codigoOrdenProd);
			notificacionesProduccion = query.list();

			return notificacionesProduccion;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);

		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Notificacionproduccion> obtenerNotifProdDeHorasSiguientes(Notificacionproduccion notificacionHO)
			throws SesionVencidaException {
		String mensajeError = null;
		List<Notificacionproduccion> notificacionesProduccion = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotifDiaria AND ");
			queryStr.append("np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestotrabajo AND ");
			queryStr.append("np.hora.turno.pkCodigoTurno >= :codigoTurno");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotifDiaria", notificacionHO.getNotificaciondiaria().getPkCodigoNotificaciondiaria());
			query.setLong("codigoPuestotrabajo", notificacionHO.getPuestotrabajo().getPkCodigoPuestotrabajo());
			query.setLong("codigoTurno", notificacionHO.getHora().getTurno().getPkCodigoTurno());
			notificacionesProduccion = query.list();

			return notificacionesProduccion;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);

		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static Notificacionproduccion obtenerSegunCamposNotifDiariaFechaYHora(Long codigoLineaNegocio,
			Long codigoPuestoTrabajo, Long codigoTableroControl, Date fechaReg, Short horaInicioPrimerTurno)
			throws SesionVencidaException {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :codigolineaNeg AND ");
			queryStr.append("np.notificaciondiaria.tablerocontrol.pkCodigoTablerocontrol = :codigoTablero AND ");
			queryStr.append("np.notificaciondiaria.fechaNotificaciondiaria = :fecha AND ");
			queryStr.append("np.hora.horaHora = :codHora  AND ");
			queryStr.append("np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestotrabajo");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigolineaNeg", codigoLineaNegocio);
			query.setLong("codigoTablero", codigoTableroControl);
			query.setDate("fecha", fechaReg);
			query.setShort("codHora", horaInicioPrimerTurno);
			query.setLong("codigoPuestotrabajo", codigoPuestoTrabajo);

			return (Notificacionproduccion) query.uniqueResult();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);

		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Medioalmacenamiento> obtenerMediosAlmacPorProductoNotificado(NotificacionDiariaBean notificacionBean,
			Producto producto) {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT(np.medioalmacenamiento) FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotif AND ");
			queryStr.append("np.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProdcuto");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotif", notificacionBean.getCodigo());
			query.setLong("codigoProdcuto", producto.getPkCodigoProducto());

			return query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static Notificacionproduccion obtenerNotificacionHoraAnterior(Notificacionproduccion notificacionProduccion)
			throws LogicaException {
		String mensajeError = null;
		try {

			String horaCambioProduccion = notificacionProduccion.getHoraCambioproduccionNotificac();

			String[] horaCambioDividida = horaCambioProduccion.split(":");
			String horaCambio = horaCambioDividida[0];

			Short primeraHoraPrimerTurno = HoraQuerier.obtenerHoraInicioPrimerTurno();

			Notificacionproduccion notificacionprodAnterior = null;

			if (horaCambio.equals(primeraHoraPrimerTurno.toString())) {
				Date fecha = FechaUtil.obtenerFechaDiaAnterior(notificacionProduccion.getFechaNotificacionproduccion());
				Short ultimaHoraUltimoTurno = HoraQuerier.obtenerUltimaHoraDeUltimoTurno();
				Long codPuestoTrabajo = notificacionProduccion.getPuestotrabajo().getPkCodigoPuestotrabajo();

				StringBuilder hql = new StringBuilder("From Notificacionproduccion as np WHERE ");
				hql.append("np.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrabajo  AND ");
				hql.append("np.hora.horaHora = :codHora  AND ");
				hql.append("np.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :codLineaNeg  AND ");
				hql.append("np.fechaNotificacionproduccion = :fecha");

				Query query = Querier.query(hql.toString());
				query.setLong("codPuestoTrabajo", codPuestoTrabajo);
				query.setShort("codHora", ultimaHoraUltimoTurno);
				query.setLong("codPuestoTrabajo", notificacionProduccion.getNotificaciondiaria().getLineanegocio()
						.getPkCodigoLineanegocio());
				query.setDate("fecha", fecha);

				notificacionprodAnterior = (Notificacionproduccion) query.uniqueResult();
			} else {
				notificacionprodAnterior = Querier.getById(Notificacionproduccion.class,
						notificacionProduccion.getPkCodigoNotificacionproduccio() - 1);
			}

			return notificacionprodAnterior;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		} catch (ElementoNoEncontradoException e) {
			throw new LogicaException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<ResumenNotificacionItemRepBean> obtenerResumenItemsNotificacion(Long codigoPuestoTrabajo,
			Long codigoProducto, String fechaInicio, String fechaFin) {

		// Lista de Items de ResumenNotificacionItemRepBean
		List<ResumenNotificacionItemRepBean> listaItems = new ArrayList<ResumenNotificacionItemRepBean>();
		// Lista Auxiliar
		List<Object[]> listaAuxiliar = null;
		// Mensaje de Error
		String mensajeError = null;

		try {
			// Query String
			StringBuilder queryStr = new StringBuilder();
			// Se construye el Query String
			queryStr.append("SELECT np.fechaNotificacionproduccion, p.nombreProducto, "
					+ "SUM(cn.valorComponentenotificacion), SUM(np.horaEcsNotificacionproduccion), "
					+ "np.ordenproduccion.produccion.producto.nombreProducto ");
			queryStr.append("FROM Notificacionproduccion np, Hora h, Plantillaproducto pp, Componentenotificacion cn, "
					+ "Componente c, Producto p, Puestotrabajo pt WHERE ");
			queryStr.append("h.pkCodigoHora = np.hora.pkCodigoHora AND ");
			queryStr.append("pp.pkCodigoPlantillaproducto = np.plantillaproducto.pkCodigoPlantillaproducto AND ");
			queryStr.append("cn.notificacionproduccion.pkCodigoNotificacionproduccio = np.pkCodigoNotificacionproduccio AND ");
			queryStr.append("cn.componente.pkCodigoComponente = c.pkCodigoComponente AND ");
			queryStr.append("p.pkCodigoProducto = c.productoByFkCodigoProductoComponente.pkCodigoProducto AND ");
			queryStr.append("pt.pkCodigoPuestotrabajo = np.puestotrabajo.pkCodigoPuestotrabajo AND ");
			// Se verifica si hay o no un producto seleccionado para agregarlo
			// al Query
			if (codigoProducto != null) {
				queryStr.append("pp.producto.pkCodigoProducto = :codigoProducto AND ");
			}
			queryStr.append("pt.pkCodigoPuestotrabajo = :codigoPuestoTrabajo AND ");
			queryStr.append("np.fechaNotificacionproduccion >=:fechaInicio AND ");
			queryStr.append("np.fechaNotificacionproduccion <=:fechaFin ");
			queryStr.append("GROUP BY np.fechaNotificacionproduccion, p.nombreProducto, np.ordenproduccion.produccion.producto.nombreProducto ");
			queryStr.append("ORDER BY np.fechaNotificacionproduccion, np.ordenproduccion.produccion.producto.nombreProducto, p.nombreProducto ");

			Query query = Querier.query(queryStr.toString());

			if (codigoProducto != null) {
				query.setLong("codigoProducto", codigoProducto);
			}
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			query.setDate("fechaInicio", FechaUtil.convertirAFecha(fechaInicio, FechaUtil.PATRON_FECHA_BD, null));
			query.setDate("fechaFin", FechaUtil.convertirAFecha(fechaFin, FechaUtil.PATRON_FECHA_BD, null));

			// Obtenemos la Lista de Items
			listaAuxiliar = query.list();

			// Se recorre la lista para armar una nueva lista de
			// ResumenNotificacionItemRepBean y devolverla
			for (Iterator<Object[]> it = listaAuxiliar.iterator(); it.hasNext();) {
				ResumenNotificacionItemRepBean itemResumen = new ResumenNotificacionItemRepBean();
				Object[] item = it.next();
				Date fecha = (Date) item[0];
				String nombreComponente = (String) item[1];
				String cantidad = ((Double) item[2]).toString();
				String horasProduccion = ((Double) item[3]).toString();
				String nombreProducto = (String) item[4];
				// Se asigna la Fecha
				itemResumen.setFecha(FechaUtil.convertirDateToString(fecha, FechaUtil.PATRON_FECHA_APLICACION));
				// Se asigna el nombre del Producto
				itemResumen.setNombreProducto(nombreProducto);
				// Se asigna el Nombre del Componente
				itemResumen.setNombreComponente(nombreComponente);
				// Se asigna la Cantidad Producida del Componente
				itemResumen.setCantidad(cantidad);
				// Se asigna la Cantidad de Horas de Produccion del Componente
				// en este Puesto de Trabajo
				itemResumen.setHorasProduccion(horasProduccion);
				listaItems.add(itemResumen);
			}

			return listaItems;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (LogicaException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static boolean validarPlantillaEnNotif(Long plantillaReporteCodigo) throws SesionVencidaException {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT COUNT(np.pkCodigoNotificacionproduccio) FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.plantillaproducto.pkCodigoPlantillaproducto = :codigoPlantilla");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoPlantilla", plantillaReporteCodigo);

			return ((Long) query.uniqueResult()) > 0;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static Long obtenerNumeroNotifSegunPlantillaReporte(Long codigoPlantillaReporte) {
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT COUNT(np.pkCodigoNotificacionproduccio) FROM Notificacionproduccion np  WHERE ");
			queryStr.append("np.plantillaproducto.plantillareporte.pkCodigoPlantillareporte = :codigoPlantilla");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoPlantilla", codigoPlantillaReporte);

			return ((Long) query.uniqueResult());

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Busca una lista de notificaciones por el codigo de la cabecera
	 * (notificacion diara) y opcionalmente por el puesto de trabajo y producto
	 * 
	 * @param codigoNotificacionDiaria
	 * @param codigoPuestoTrabajo
	 * @param codigoProducto
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerPorNotificacionProduccionPorFiltros(Long lineaNegocio, Long codigoEstadoNotificacion,
			Long codigoPuestoTrabajo, Date fechaInicio, Date fechaFin) throws AplicacionException {
		List<Object[]> lista = null;
		String mensajeError = null;

		try {
			StringBuilder queryStr = new StringBuilder(
					"SELECT DISTINCT(np.puestotrabajo),np.notificaciondiaria FROM Notificacionproduccion np ");
			queryStr.append(" WHERE ");
			queryStr.append(" np.fechaNotificacionproduccion between :fechaInicio AND :fechaFin");
			queryStr.append(" AND np.notificaciondiaria.lineanegocio.pkCodigoLineanegocio = :lineaNegocio");

			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				queryStr.append(" AND np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo");
			if (codigoEstadoNotificacion != null && codigoEstadoNotificacion > 0)
				queryStr.append(" AND np.notificaciondiaria.estadonotificacion.pkCodigoEstadonotificacion = :codigoestado");

			Query query = Querier.query(queryStr.toString());

			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);
			query.setLong("lineaNegocio", lineaNegocio);
			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);

			if (codigoEstadoNotificacion != null && codigoEstadoNotificacion > 0)
				query.setLong("codigoestado", codigoEstadoNotificacion);

			lista = query.list();

			return lista;
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			log.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException);
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException);
		} catch (HibernateException hException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			log.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException);
		}

	}

	/**
	 * @param codigoNotificacionDiaria
	 * @param codigoPuestoTrabajo
	 * @param codigoOrdenProd
	 * @return
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Notificacionproduccion> obtenerPorNotifPuestoTrab(Long codigoNotificacionDiaria, Long codigoPuestoTrabajo)
			throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = null;
		List<Notificacionproduccion> notificacionesProduccion = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("FROM Notificacionproduccion np  WHERE ");
			queryStr.append(" np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria ");
			queryStr.append(" AND np.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestotrabajo ");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);
			query.setLong("codigoPuestotrabajo", codigoPuestoTrabajo);

			notificacionesProduccion = query.list();

			return notificacionesProduccion;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e);

		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Obtiene una lista de objetos que contendra la orden de produccion[0] y el
	 * puesto de trabajo[1]
	 * 
	 * @param codigoNotificacionDiaria Long
	 * @return List<Ordenproduccion>
	 * @throws ElementoNoEncontradoException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerOrdenesProduccionPuestoTrabajoByCodigoNotifDiaria(Long codigoNotificacionDiaria)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		List<Object[]> ordenesProduccionPuestoTrabajo = null;
		String mensajeError = null;
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT DISTINCT np.ordenproduccion,np.puestotrabajo FROM Notificacionproduccion np WHERE ");
			queryStr.append("np.notificaciondiaria.pkCodigoNotificaciondiaria = :codigoNotificacionDiaria");
			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoNotificacionDiaria", codigoNotificacionDiaria);

			ordenesProduccionPuestoTrabajo = query.list();

		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			log.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException);
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException);
		} catch (HibernateException hException) {
			hException.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			log.error(mensajeError, hException);
			throw new EntornoEjecucionException(mensajeError, hException);
		}
		return ordenesProduccionPuestoTrabajo;
	}

}
