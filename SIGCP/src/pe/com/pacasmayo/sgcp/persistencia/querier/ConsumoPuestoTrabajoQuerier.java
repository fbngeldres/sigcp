package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ConsumoPorPuestoTrabajoDTO;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoPuestoTrabajoQuerier.java
 * Modificado: May 9, 2011 3:23:52 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ConsumoPuestoTrabajoQuerier extends Querier {

	private static Logger log = Logger.getLogger(ConsumoPuestoTrabajoQuerier.class);
	private static String mensajeError;
	private static final String NOMBRE_CLASE_CONSUMO_PUESTO_TRABAJO = Consumopuestotrabajo.class.getSimpleName();
	private static final String PRODUCTO_GENERADO = "productogenerado.pkCodigoProductogenerado";

	@SuppressWarnings("unchecked")
	public static List<Consumopuestotrabajo> obtenerListaConsumoComponentePorProductoProcesoLineaNegYFecha(Date fechaNotif,
			Producto producto, Proceso proceso, Long pkLineaNegocio) throws SesionVencidaException, EntornoEjecucionException {
		try {

			StringBuilder hql = new StringBuilder("FROM Consumopuestotrabajo AS ctp WHERE ");

			hql.append("ctp.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codProducto AND ");
			hql.append("ctp.productogenerado.ordenproduccion.produccion.proceso.pkCodigoProceso = :codProceso AND ");
			hql.append("ctp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");

			hql.append("ctp.productogenerado.tablaoperacion.fechaTablaoperacion = :fecha");

			Query query = Querier.query(hql.toString());

			query.setLong("codProducto", producto.getPkCodigoProducto());
			query.setLong("codProceso", proceso.getPkCodigoProceso());
			query.setLong("codLineaNeg", pkLineaNegocio);

			query.setDate("fecha", fechaNotif);

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

	public static List<Object[]> obtenerConsumoPuestoTrabajoMes(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable, Boolean valor) {
		String mensajeError = "";
		String combustible = "combustible";
		List<Object[]> consumos = new ArrayList<Object[]>();

		try {
			StringBuilder hql = new StringBuilder("SELECT");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo,");
			hql.append(" SUM(cpt.cantidadConsumopuestotrabajo),");
			hql.append(" SUM(cpt.cantidadCalentamientoConsumopuestotrabajo)");
			hql.append(" FROM Consumopuestotrabajo AS cpt WHERE");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
			hql.append(" AND cpt.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");
			hql.append(" AND cpt.componente.productoByFkCodigoProductoComponente.tipocategoriaproducto.nombreTipocategoriaproducto LIKE :combustible");
			hql.append(" AND cpt.componente.productoByFkCodigoProductoComponente.estadoFisicoSolidoProducto = :VALOR");
			hql.append(" GROUP BY");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo");
			hql.append(" ORDER BY 1 ASC");

			Query query = query(hql.toString());

			query.setLong("lineaNeg", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codigoProducto", codigoProducto);
			query.setString("combustible", "%" + combustible + "%");
			query.setBoolean("VALOR", valor);

			consumos = query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return consumos;
	}

	public static List<Object[]> obtenerConsumoPuestoTrabajoCarbon(Long codigoProducto, Long codigoLinea,
			Long codigoPuestoTrabajo, Short mesContable, Integer anioContable, Boolean valor) {
		String mensajeError = "";
		String combustible = "combustible";
		List<Object[]> consumos = new ArrayList<Object[]>();

		try {
			StringBuilder hql = new StringBuilder("SELECT");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo,");
			hql.append(" cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto,");
			hql.append(" cpt.componente.productoByFkCodigoProductoComponente.nombreProducto,");
			hql.append(" SUM(cpt.cantidadConsumopuestotrabajo)");
			hql.append(" FROM Consumopuestotrabajo AS cpt WHERE");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
			hql.append(" AND cpt.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");
			hql.append(" AND cpt.componente.productoByFkCodigoProductoComponente.tipocategoriaproducto.nombreTipocategoriaproducto LIKE :combustible");
			hql.append(" AND cpt.componente.productoByFkCodigoProductoComponente.estadoFisicoSolidoProducto = :VALOR");
			hql.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo");
			hql.append(" GROUP BY");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo,");
			hql.append(" cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto,");
			hql.append(" cpt.componente.productoByFkCodigoProductoComponente.nombreProducto");
			hql.append(" ORDER BY 1 ASC");

			Query query = query(hql.toString());

			query.setLong("lineaNeg", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codigoProducto", codigoProducto);
			query.setString("combustible", "%" + combustible + "%");
			query.setBoolean("VALOR", valor);
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);

			consumos = query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return consumos;
	}

	public static Double obtenerPromedioFactorConsumo(Tablakardex tablaKardex, String variableCalidad) {
		try {

			Ordenproduccion ordenproduccion = tablaKardex.getProducciondiaria().getOrdenproduccion();
			Date fecha = tablaKardex.getFechaTablakardex();
			Lineanegocio lineanegocio = tablaKardex.getProducciondiaria().getPartediario().getLineanegocio();

			StringBuilder hql = new StringBuilder(
					"SELECT AVG(f.valorFactorconsumopuestotrabajo) FROM Factorconsumopuestotrabajo AS f WHERE ");
			hql.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.ordenproduccion.pkCodigoOrdenproduccion = :codOrdenProd AND ");
			hql.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			hql.append("f.fkCodigoEquivalenciasccvarcalidad.descripSgcpVarCalidad = :variableCal AND ");
			hql.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.fechaTablaoperacion = :fecha");

			Query query = Querier.query(hql.toString());

			query.setLong("codOrdenProd", ordenproduccion.getPkCodigoOrdenproduccion());
			query.setLong("codLineaNeg", lineanegocio.getPkCodigoLineanegocio());
			query.setString("variableCal", variableCalidad);
			query.setDate("fecha", fecha);

			Double avgFactor = (Double) query.uniqueResult();
			return avgFactor == null ? 0d : avgFactor.doubleValue();
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

	public static Double obtenerPromedioFactorConsumoComponente(Tablakardex tablaKardex, String variableCalidad) {
		try {

			Date fecha = tablaKardex.getFechaTablakardex();
			Lineanegocio lineanegocio = tablaKardex.getProducciondiaria().getPartediario().getLineanegocio();

			StringBuilder hql = new StringBuilder(
					"SELECT AVG(f.valorFactorconsumopuestotrabajo) FROM Factorconsumopuestotrabajo AS f WHERE ");
			hql.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			hql.append("f.fkCodigoEquivalenciasccvarcalidad.descripSgcpVarCalidad = :variableCal AND ");
			hql.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.fechaTablaoperacion = :fecha");

			Query query = Querier.query(hql.toString());

			query.setLong("codLineaNeg", lineanegocio.getPkCodigoLineanegocio());
			query.setString("variableCal", variableCalidad);
			query.setDate("fecha", fecha);

			Double avgFactor = (Double) query.uniqueResult();
			return avgFactor == null ? 0d : avgFactor.doubleValue();
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
	public static List<Consumopuestotrabajo> obtenerConsumoComponentesPorFechaYProductoGenerado(Date fechaNotif,
			String nombreProducto) throws SesionVencidaException, EntornoEjecucionException {
		try {

			StringBuilder hql = new StringBuilder("FROM Consumopuestotrabajo AS ctp WHERE ");
			hql.append("lower(ctp.productogenerado.ordenproduccion.produccion.producto.nombreProducto) = :nombreProducto AND ");
			hql.append("ctp.productogenerado.tablaoperacion.fechaTablaoperacion = :fecha");

			Query query = Querier.query(hql.toString());
			query.setString("nombreProducto", nombreProducto.toLowerCase());
			query.setDate("fecha", fechaNotif);

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

	@SuppressWarnings("unchecked")
	public static List<Consumopuestotrabajo> obtenerPorProductoGenerado(Long codigoProductoGenerado)
			throws EntornoEjecucionException {
		String consulta = "FROM {0} AS cpt where cpt.{1} = :codigoProductoGenerado ";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_CONSUMO_PUESTO_TRABAJO, PRODUCTO_GENERADO });

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoProductoGenerado", codigoProductoGenerado);

			List<Consumopuestotrabajo> consumoPuestoTrabajo = query.list();
			return consumoPuestoTrabajo;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	@SuppressWarnings("unchecked")
	public static Double obtenerConsumoComponentesPorFecha(Date fechaNotif, Long codigoProductoComp)
			throws SesionVencidaException, EntornoEjecucionException {
		try {

			StringBuilder hql = new StringBuilder(
					"Select sum(ctp.cantidadConsumopuestotrabajo) FROM Consumopuestotrabajo AS ctp WHERE ");
			hql.append("ctp.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto AND ");
			hql.append("ctp.productogenerado.tablaoperacion.fechaTablaoperacion = :fecha");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProductoComp);

			query.setDate("fecha", fechaNotif);

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
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	/**
	 * Obtiene los consumos clasificados por puesto de trabajo de un mp o pp
	 * 
	 * @param fechaNotif
	 * @param codigoProductoComp Long
	 * @param lineaNegocio Long
	 * @return List<Object[]> tamano 4, pos 0 Long codigo puesto, pos 1 String
	 *         nombrePuesto, pos 2 Double consumo pos 3 Date fecha
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<ConsumoPorPuestoTrabajoDTO> obtenerConsumoComponentePorFechaEnPuestos(Long codigoProductoComp,
			Long lineaNegocio, Integer anio, Short mes) throws SesionVencidaException, EntornoEjecucionException {
		try {

			StringBuilder hql = new StringBuilder(
					"SELECT new pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ConsumoPorPuestoTrabajoDTO(");
			hql.append("ctp.productogenerado.tablaoperacion.fechaTablaoperacion, ");
			hql.append("ctp.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo, ");
			hql.append("ctp.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo, ");
			hql.append("ctp.cantidadConsumopuestotrabajo, ");
			hql.append("ctp.pkCodigoConsumopuestotrabajo, ");
			hql.append("ctp.cantidadHumedaConsumopuestotrabajo) ");
			hql.append("FROM Consumopuestotrabajo AS ctp WHERE ");
			hql.append("ctp.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");
			hql.append(" AND ctp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :linea");
			hql.append(" AND ctp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND ctp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes ");
			hql.append(" AND ctp.cantidadConsumopuestotrabajo >0");
			hql.append(" AND ctp.cantidadHumedaConsumopuestotrabajo >0");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProductoComp);
			query.setLong("linea", lineaNegocio);
			query.setInteger("anio", anio);
			query.setShort("mes", mes);

			List<ConsumoPorPuestoTrabajoDTO> lista = query.list();

			Collections.sort(lista, new Comparator<ConsumoPorPuestoTrabajoDTO>() {
				public int compare(ConsumoPorPuestoTrabajoDTO o1, ConsumoPorPuestoTrabajoDTO o2) {
					return o1.getFecha().compareTo(o2.getFecha());
				}
			});

			return lista;

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
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	/**
	 * Obtiene una lista de los consumos que se registraron en el puesto de
	 * trabajo en mes de un determinado año
	 * 
	 * @param codigoPuestoTrabajo Long
	 * @param codProducto Long
	 * @param codigoLineaNegocio Long
	 * @param anno Integer
	 * @param mes Short
	 * @return List<Consumopuestotrabajo>
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Consumopuestotrabajo> obtenerRegistrosPorLineaNegPuestoProductoAnnioYMes(Long codigoPuestoTrabajo,
			Long codProducto, Long codigoLineaNegocio, Integer anno, Short mes) throws SesionVencidaException,
			EntornoEjecucionException {
		try {

			StringBuilder hql = new StringBuilder(
					"Select c.productogenerado.tablaoperacion.fechaTablaoperacion,c FROM Consumopuestotrabajo AS c WHERE ");
			hql.append("c.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");

			if (codigoPuestoTrabajo != null) {
				hql.append("c.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :puestoTrab AND ");
			}

			hql.append("c.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio AND ");
			hql.append("c.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes AND ");

			hql.append("c.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codProducto ");
			hql.append("ORDER BY c.productogenerado.tablaoperacion.fechaTablaoperacion ASC");

			Query query = Querier.query(hql.toString());

			query.setLong("codLineaNeg", codigoLineaNegocio);
			if (codigoPuestoTrabajo != null) {
				query.setLong("puestoTrab", codigoPuestoTrabajo);
			}
			query.setInteger("anio", anno);
			query.setShort("mes", mes);
			query.setLong("codProducto", codProducto);

			List<Object[]> list = query.list();
			List<Consumopuestotrabajo> consumos = new ArrayList<Consumopuestotrabajo>();
			for (Object[] objects : list) {
				consumos.add((Consumopuestotrabajo) objects[1]);
			}
			return consumos;
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
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Obtiene una lista de los consumos que se registraron en el puesto de
	 * trabajo en mes de un determinado año
	 * 
	 * @param codigoPuestoTrabajo Long
	 * @param codProducto Long
	 * @param codigoLineaNegocio Long
	 * @param anno Integer
	 * @param mes Short
	 * @return List<Consumopuestotrabajo>
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Consumopuestotrabajo> obtenerRegistrosPorLineaNegAnnioYMes(Long codigoLineaNegocio, Integer anno, Short mes)
			throws SesionVencidaException, EntornoEjecucionException {
		try {

			StringBuilder hql = new StringBuilder("FROM Consumopuestotrabajo AS c WHERE ");
			hql.append("c.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			hql.append("c.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio AND ");
			hql.append("c.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes ");

			hql.append("ORDER BY c.productogenerado ASC");

			Query query = Querier.query(hql.toString());

			query.setLong("codLineaNeg", codigoLineaNegocio);
			query.setInteger("anio", anno);
			query.setShort("mes", mes);

			List<Consumopuestotrabajo> consumos = query.list();
			return consumos;
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
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static List<Long> obtenerCodigosPorLineaNegYFecha(Long lineaNegocio, Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);

		StringBuilder querystr = new StringBuilder(" SELECT cp.pkCodigoConsumopuestotrabajo FROM  Consumopuestotrabajo cp ");
		querystr.append(" WHERE  ");
		querystr.append(" cp.productogenerado.tablaoperacion.fechaTablaoperacion = :fechaTablaOperacion  ");
		querystr.append("AND  cp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mesPartediario  ");
		querystr.append("AND  cp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anoPartediario  ");
		querystr.append("AND  cp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineNegParteDiario  ");

		Query query = Querier.query(querystr.toString());
		query.setDate("fechaTablaOperacion", fecha);
		query.setShort("mesPartediario", mes.shortValue());
		query.setInteger("anoPartediario", anio);
		query.setLong("lineNegParteDiario", lineaNegocio);

		return query.list();
	}

	public static void eliminarPorFechaLineaNeg(List<Long> codigosConsumoPuestosT) {

		StringBuilder querystr = new StringBuilder("DELETE FROM  Consumopuestotrabajo  cp ");
		querystr.append(" WHERE ");
		querystr.append(" cp.pkCodigoConsumopuestotrabajo in (:fechaTablaOperacion)  ");

		Query query = Querier.query(querystr.toString());

		query.setParameterList("fechaTablaOperacion", codigosConsumoPuestosT);

		query.executeUpdate();

	}

	/**
	 * Obtiene una lista de ConsumoPuesto de trabajo por un codigo del producto
	 * componente, Año , Mes y linea de negocio seleccionado
	 * 
	 * @param productoComponente
	 * @param mes
	 * @param anio
	 * @return
	 */
	public static List<Object[]> obtenerByCodigoProductoComponente(Long productoComponente, Short mes, Integer anio,
			Long lineaNegocio) {

		StringBuilder querystr = new StringBuilder(
				"SELECT cp.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo"
						+ ",cp.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo"
						+ ",cp.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto"
						+ ",cp.productogenerado.ordenproduccion.produccion.producto.nombreProducto"
						+ ",cp.componente.pkCodigoComponente" + ",SUM(cp.productogenerado.produccionTmProductogenerado)"
						+ ",SUM(cp.cantidadConsumopuestotrabajo) FROM  Consumopuestotrabajo cp ");
		querystr.append(" WHERE  ");
		querystr.append(" cp.componente.productoByFkCodigoProductoComponente.pkCodigoProducto=:productoComponente  ");
		querystr.append("AND  cp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mesPartediario  ");
		querystr.append("AND  cp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anoPartediario  ");
		querystr.append("AND  cp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineNegParteDiario  ");
		querystr.append("GROUP BY cp.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo"
				+ ",cp.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo"
				+ ",cp.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto"
				+ ",cp.productogenerado.ordenproduccion.produccion.producto.nombreProducto" + ",cp.componente.pkCodigoComponente");
		querystr.append(" ORDER BY cp.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo,cp.productogenerado.ordenproduccion.produccion.producto.nombreProducto  ");

		Query query = Querier.query(querystr.toString());
		query.setLong("productoComponente", productoComponente);
		query.setShort("mesPartediario", mes.shortValue());
		query.setInteger("anoPartediario", anio);
		query.setLong("lineNegParteDiario", lineaNegocio);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public static Double obtenerConsumoComponentesPorFechaInicioFin(Date fechaInicio, Date fechaFin, Long codigoUnidad,
			Long codigoComponente) throws SesionVencidaException, EntornoEjecucionException {
		try {

			StringBuilder hql = new StringBuilder(
					"SELECT SUM(ctp.cantidadConsumopuestotrabajo) FROM Consumopuestotrabajo AS ctp WHERE ");
			hql.append(" ctp.componente.productoByFkCodigoProductoComponente.pkCodigoProducto  = :codigoComponente  ");
			hql.append(" AND ctp.productogenerado.tablaoperacion.fechaTablaoperacion > :fechaInicio");
			hql.append(" AND ctp.productogenerado.tablaoperacion.fechaTablaoperacion <= :fechaFin");
			hql.append(" AND ctp.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codigoLinea");

			Query query = Querier.query(hql.toString());
			query.setLong("codigoComponente", codigoComponente);
			query.setLong("codigoLinea", codigoUnidad);
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);

			return (Double) query.uniqueResult();

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

	public static List<ConsumoPorPuestoTrabajoDTO> obtenerDatosPorPuestoTrabajo(Integer anno, Short mes, Long codigoLinea,
			Long codigoProducto, Long codigoProceso, Long... codigoProductoComponente) throws SesionVencidaException,
			EntornoEjecucionException {
		try {

			String queryString = " SELECT new pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.ConsumoPorPuestoTrabajoDTO("
					+ " cpt.productogenerado.tablaoperacion.fechaTablaoperacion, "
					+ " cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo, "
					+ " cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo, "
					+ " cpt.productogenerado.pkCodigoProductogenerado, "
					+ " cpt.componente.pkCodigoComponente, "
					+ " cpt.cantidadConsumopuestotrabajo, "
					+ " cpt.cantidadCalentamientoConsumopuestotrabajo) "

					+ " FROM Consumopuestotrabajo AS cpt WHERE "
					+ " cpt.productogenerado.ordenproduccion.mesOrdenproduccion = :mes"
					+ " AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codigoLinea"
					+ " AND cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto  in (:codigoProductoComponente)"
					+ " AND cpt.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto"
					+ " AND cpt.productogenerado.ordenproduccion.produccion.proceso.pkCodigoProceso = :codigoProceso"
					+ " AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes"
					+ " AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio"
					+ " ORDER BY cpt.productogenerado.tablaoperacion.fechaTablaoperacion,cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo ASC";

			Query query = Querier.query(queryString);
			query.setShort("mes", mes);
			query.setInteger("anio", anno);
			query.setLong("codigoLinea", codigoLinea);
			query.setParameterList("codigoProductoComponente", codigoProductoComponente);
			query.setLong("codigoProducto", codigoProducto);
			query.setLong("codigoProceso", codigoProceso);

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

	public static List<Object[]> obtenerComponentesSegunProcesoProductoPuesto(Long proceso, Long producto, Long puestotrabajo,
			Date fechaInicio) {
		try {

			StringBuilder hql = new StringBuilder(
					"SELECT cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto, cpt.componente.productoByFkCodigoProductoComponente.nombreProducto,SUM(cpt.cantidadConsumopuestotrabajo) From Consumopuestotrabajo AS cpt WHERE ");
			hql.append("cpt.productogenerado.ordenproduccion.produccion.proceso.pkCodigoProceso = :proceso  AND ");
			hql.append("cpt.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :producto  AND ");
			hql.append("cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :puestotrabajo  AND ");
			hql.append("cpt.productogenerado.tablaoperacion.fechaTablaoperacion = :fechainicio  ");

			hql.append("GROUP BY cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto, cpt.componente.productoByFkCodigoProductoComponente.nombreProducto ");
			hql.append("HAVING  SUM(cpt.cantidadConsumopuestotrabajo) > 0 ");

			Query query = Querier.query(hql.toString());
			query.setLong("proceso", proceso);
			query.setLong("producto", producto);
			query.setLong("puestotrabajo", puestotrabajo);
			query.setDate("fechainicio", fechaInicio);

			return query.list();

		} catch (QueryException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

}
