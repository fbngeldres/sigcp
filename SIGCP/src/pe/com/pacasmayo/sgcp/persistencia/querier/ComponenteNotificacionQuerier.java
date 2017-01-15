package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.NotificacionDiariaBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componentenotificacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Notificacionproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ComponenteNotificacionQuerier.java
 * Modificado: Oct 5, 2010 1:36:37 PM 
 * Autor: ricardo.marquez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ComponenteNotificacionQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String NOTIFICACION_PRODUCCION = "notificacionproduccion.pkCodigoNotificacionproduccio";
	private static String mensajeError;
	/** Logger Instance */
	private static Logger log = Logger.getLogger(ComponenteNotificacionQuerier.class);

	public static List<Componentenotificacion> getByNotificacionProduccion(Long codigo) throws AplicacionException {

		try {
			return Querier.findByProperty(Componentenotificacion.class, NOTIFICACION_PRODUCCION, codigo);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static void save(Componentenotificacion componenteNotificacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(componenteNotificacion);
	}

	@SuppressWarnings("unchecked")
	public static List<Componentenotificacion> findByProperties(Map propiedades) {
		return Querier.findByProperties(Componentenotificacion.class, propiedades);

	}

	public static void update(Componentenotificacion componenteNotificacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(componenteNotificacion);
	}

	@SuppressWarnings("unchecked")
	public static List<Componentenotificacion> getByNotifYProducto(NotificacionDiariaBean notificacionBean, Producto producto)
			throws EntornoEjecucionException, SesionVencidaException {
		try {
			StringBuilder hql = new StringBuilder("From Componentenotificacion as cn WHERE ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotifDiaria  AND ");
			hql.append("cn.notificacionproduccion.ordenproduccion.produccion.producto.pkCodigoProducto = :codProd");

			Query query = Querier.query(hql.toString());
			query.setLong("codNotifDiaria", notificacionBean.getCodigo());
			query.setLong("codProd", producto.getPkCodigoProducto());

			return query.list();
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
		}
	}

	/**
	 * Consulta el total que se ha consumido de un producto cuando este se
	 * comportan como un componete (aplica para materias primas y productos en
	 * proceso)
	 * 
	 * @param notificacionBean
	 * @param componente
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	public static Double getConsumoProductoComponente(NotificacionDiariaBean notificacionBean, Producto producto)
			throws EntornoEjecucionException, SesionVencidaException {
		try {
			//
			StringBuilder hql = new StringBuilder(
					"Select sum(cn.valorComponentenotificacion) FROM Componentenotificacion AS cn WHERE ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotifDiaria  AND ");
			hql.append("cn.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codNotifDiaria", notificacionBean.getCodigo());
			query.setLong("codProducto", producto.getPkCodigoProducto());

			Double consumo = (Double) query.uniqueResult();

			if (consumo == null) {
				return 0d;
			}

			return consumo;
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
		}
	}

	/**
	 * Obtiene una lista de objetos componentes notificacion segun la
	 * notificacion diaria a la que pertencen sus notificaciones de produccion
	 * 
	 * @param notificacionBean NotificacionDiariaBean
	 * @return List<Componentenotificacion>
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static double getConsumoComponente(Componente componente, Long codigoNotifDiaria) throws EntornoEjecucionException,
			SesionVencidaException {
		try {
			StringBuilder hql = new StringBuilder(
					"Select sum(cn.valorComponentenotificacion) From Componentenotificacion as cn WHERE ");
			hql.append("cn.componente.pkCodigoComponente = :codComp AND ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotif");

			Query query = Querier.query(hql.toString());
			query.setLong("codComp", componente.getPkCodigoComponente());
			query.setLong("codNotif", codigoNotifDiaria);

			Double sumaConsumoCompoente = (Double) query.uniqueResult();
			return sumaConsumoCompoente == null ? 0d : sumaConsumoCompoente;
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
		}
	}

	/**
	 * Obtiene una lista de objetos componentes notificacion segun la
	 * notificacion diaria a la que pertencen sus notificaciones de produccion
	 * 
	 * @param notificacionBean NotificacionDiariaBean
	 * @return List<Componentenotificacion>
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static double getConsumoComponentePorSilo(Componente componente, Long codigoNotifDiaria, Medioalmacenamiento medioALmac)
			throws EntornoEjecucionException, SesionVencidaException {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT SUM(cn.valorComponentenotificacion) FROM Componentenotificacion as cn WHERE ");
			hql.append("cn.componente.pkCodigoComponente = :codComp AND ");
			hql.append("cn.notificacionproduccion.medioalmacenamiento.pkCodigoMedioalmacenamiento = :codMedio AND ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotif");

			Query query = Querier.query(hql.toString());
			query.setLong("codComp", componente.getPkCodigoComponente());
			query.setLong("codMedio", medioALmac.getPkCodigoMedioalmacenamiento());
			query.setLong("codNotif", codigoNotifDiaria);

			Double sumaConsumoCompoente = (Double) query.uniqueResult();
			return sumaConsumoCompoente == null ? 0d : sumaConsumoCompoente;
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
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Componentenotificacion> obtenerComponentesDeUltimaNotifDelDiaAnterior(
			Notificacionproduccion notificacionProduccion) throws LogicaException {
		try {
			Date fecha = FechaUtil.obtenerFechaDiaAnterior(notificacionProduccion.getFechaNotificacionproduccion());
			Short ultimaHoraUltimoTurno = HoraQuerier.obtenerUltimaHoraDeUltimoTurno();
			Long codPuestoTrabajo = notificacionProduccion.getPuestotrabajo().getPkCodigoPuestotrabajo();

			StringBuilder hql = new StringBuilder("From Componentenotificacion as cn WHERE ");
			hql.append("cn.notificacionproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrabajo  AND ");
			hql.append("cn.notificacionproduccion.hora.horaHora = :codHora  AND ");
			hql.append("cn.notificacionproduccion.fechaNotificacionproduccion = :fecha");

			Query query = Querier.query(hql.toString());
			query.setLong("codPuestoTrabajo", codPuestoTrabajo);
			query.setShort("codHora", ultimaHoraUltimoTurno);
			query.setDate("fecha", fecha);

			return query.list();

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

	public static double getConsumoProductoComponentePP(NotificacionDiariaBean notificacionBean, Producto producto) {
		try {
			StringBuilder hql = new StringBuilder(
					"Select sum(cn.valorComponentenotificacion) FROM Componentenotificacion AS cn WHERE ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :codNotifDiaria  AND ");
			hql.append("cn.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codNotifDiaria", notificacionBean.getCodigo());
			query.setLong("codProducto", producto.getPkCodigoProducto());

			Double d = (Double) query.uniqueResult();

			return d == null ? 0d : d;
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
		}
	}

	public static List<Componentenotificacion> obtenerSegunOrdenProducionYNotif(Ordenproduccion ordenproduccion, Long codigoNotif) {
		try {
			StringBuilder hql = new StringBuilder("From Componentenotificacion as cn WHERE ");
			hql.append("cn.notificacionproduccion.ordenproduccion.pkCodigoOrdenproduccion = :orden  AND ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :notif");

			Query query = Querier.query(hql.toString());
			query.setLong("orden", ordenproduccion.getPkCodigoOrdenproduccion());
			query.setLong("notif", codigoNotif);

			return query.list();

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
		}
	}

	public static List<Componentenotificacion> obtenerSegunOrdenProducionYNotifYPuesto(Ordenproduccion ordenproduccion,
			Long codigoNotif, Long puestoTrabajo) {
		try {
			StringBuilder hql = new StringBuilder("From Componentenotificacion as cn WHERE ");
			hql.append("cn.notificacionproduccion.ordenproduccion.pkCodigoOrdenproduccion = :orden  AND ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.pkCodigoNotificaciondiaria = :notif AND ");
			hql.append("cn.notificacionproduccion.puestotrabajo.pkCodigoPuestotrabajo = :puestoTrabajo");

			Query query = Querier.query(hql.toString());
			query.setLong("orden", ordenproduccion.getPkCodigoOrdenproduccion().longValue());
			query.setLong("notif", codigoNotif.longValue());
			query.setLong("puestoTrabajo", puestoTrabajo.longValue());

			return query.list();
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
		}
	}

	public static List<Object[]> obtenerNombresComponentesSegunProcesoProductoPuesto(Long proceso, Long producto,
			Long puestotrabajo, Date fechaInicio, Date fechaFin) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT distinct cn.componente.productoByFkCodigoProductoComponente.pkCodigoProducto, cn.componente.productoByFkCodigoProductoComponente.nombreProducto From Componentenotificacion as cn WHERE ");
			hql.append("cn.notificacionproduccion.ordenproduccion.produccion.proceso.pkCodigoProceso = :proceso  AND ");
			hql.append("cn.notificacionproduccion.ordenproduccion.produccion.producto.pkCodigoProducto = :producto  AND ");
			hql.append("cn.notificacionproduccion.puestotrabajo.pkCodigoPuestotrabajo = :puestotrabajo  AND ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.fechaNotificaciondiaria >= :fechainicio  AND ");
			hql.append("cn.notificacionproduccion.notificaciondiaria.fechaNotificaciondiaria <= :fechafin AND ");
			hql.append("valorComponentenotificacion > 0");
			hql.append(" ORDER BY cn.componente.productoByFkCodigoProductoComponente.nombreProducto ASC");

			Query query = Querier.query(hql.toString());
			query.setLong("proceso", proceso);
			query.setLong("producto", producto);
			query.setLong("puestotrabajo", puestotrabajo);
			query.setDate("fechainicio", fechaInicio);
			query.setDate("fechafin", fechaFin);

			return query.list();

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
		}
	}
	/*
	 * public static List<Object[]>
	 * obtenerComponentesSegunProcesoProductoPuesto(Long proceso, Long producto,
	 * Long puestotrabajo, Date fechaInicio) { try { StringBuilder hql = new
	 * StringBuilder(
	 * "SELECT cn.componente.productoByFkCodigoProductoComponente.pkCodigoProducto, cn.componente.productoByFkCodigoProductoComponente.nombreProducto,SUM(valorComponentenotificacion) From Componentenotificacion as cn WHERE "
	 * ); hql.append(
	 * "cn.notificacionproduccion.ordenproduccion.produccion.proceso.pkCodigoProceso = :proceso  AND "
	 * ); hql.append(
	 * "cn.notificacionproduccion.ordenproduccion.produccion.producto.pkCodigoProducto = :producto  AND "
	 * ); hql.append(
	 * "cn.notificacionproduccion.puestotrabajo.pkCodigoPuestotrabajo = :puestotrabajo  AND "
	 * ); hql.append(
	 * "cn.notificacionproduccion.notificaciondiaria.fechaNotificaciondiaria = :fechainicio  "
	 * ); hql.append(
	 * "GROUP BY cn.componente.productoByFkCodigoProductoComponente.pkCodigoProducto, cn.componente.productoByFkCodigoProductoComponente.nombreProducto "
	 * ); hql.append("HAVING  SUM(valorComponentenotificacion) > 0 "); Query
	 * query = Querier.query(hql.toString()); query.setLong("proceso", proceso);
	 * query.setLong("producto", producto); query.setLong("puestotrabajo",
	 * puestotrabajo); query.setDate("fechainicio", fechaInicio); return
	 * query.list(); } catch (QueryException e) { mensajeError =
	 * ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
	 * log.error(mensajeError, e); throw new
	 * EntornoEjecucionException(mensajeError, e); } catch (SessionException e)
	 * { mensajeError =
	 * ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA
	 * ); log.error(mensajeError, e); throw new
	 * SesionVencidaException(mensajeError, e); } catch (JDBCConnectionException
	 * e) { mensajeError =
	 * ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
	 * log.error(mensajeError, e); throw new
	 * EntornoEjecucionException(mensajeError, e); } catch (RuntimeException e)
	 * { mensajeError =
	 * ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
	 * log.error(mensajeError, e); throw new
	 * EntornoEjecucionException(ERROR_FATAL_FALLO, e); } }
	 */

}
