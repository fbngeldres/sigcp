package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.GestionStockGraficoBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Kardexmedioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TablaKardexQuerier.java
 * Modificado: Apr 1, 2011 4:54:16 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class TablaKardexQuerier extends Querier {

	private static String mensajeError = "";
	private static Logger log = Logger.getLogger(TablaKardexQuerier.class);
	private static final String NOMBRE_CLASE_TABLA_KARDEX = Tablakardex.class.getSimpleName();
	private static final String PRODUCCION_DIARIA = "producciondiaria.pkCodigoProducciondiaria";

	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerPorProduccionDiaria(Long codigoProduccionDiaria) throws EntornoEjecucionException {
		String consulta = "FROM {0} AS tk where tk.{1} = :codigoProduccionDiaria ";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_TABLA_KARDEX, PRODUCCION_DIARIA });

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoProduccionDiaria", codigoProduccionDiaria);

			List<Tablakardex> tablaKardex = query.list();
			return tablaKardex;

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
	private static List<Tablakardex> obtenerTablaKardexPpYPt(Long codigoProceso, Long codigoProducto, Long codigoLineaNegocio,
			Long codigoEstadoParteDiario, Integer anno, Short mes, Date fecha) throws EntornoEjecucionException,
			SesionVencidaException {

		try {

			StringBuilder queryStr = new StringBuilder("FROM Tablakardex AS tk WHERE ");
			queryStr.append("tk.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso = :codigoProceso AND ");
			if (codigoProducto != null) {
				queryStr.append("tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :codProducto AND ");
			} else {
				queryStr.append("tk.producciondiaria.ordenproduccion.pkCodigoOrdenproduccion > 0 AND ");
			}

			queryStr.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");

			if (codigoEstadoParteDiario != null) {
				queryStr.append("tk.producciondiaria.partediario.estadopartediario.pkCodigoEstadopartediario = :codEstPD AND ");
			}

			if (fecha != null) {
				queryStr.append("tk.fechaTablakardex = :fecha AND ");
			}

			queryStr.append("tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = :anio AND ");
			queryStr.append("tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes ORDER BY fechaTablakardex ASC");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoProceso", codigoProceso);
			if (codigoProducto != null) {
				query.setLong("codProducto", codigoProducto);
			}
			query.setLong("codLineaNeg", codigoLineaNegocio);
			if (codigoEstadoParteDiario != null) {
				query.setLong("codEstPD", codigoEstadoParteDiario);
			}

			if (fecha != null) {
				query.setDate("fecha", fecha);
			}

			query.setInteger("anio", anno);
			query.setShort("mes", mes);

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

	@SuppressWarnings("unchecked")
	private static List<Tablakardex> obtenerTablaKardex(Long codigoProceso, Long codigoProducto, Long codigoLineaNegocio,
			Long codigoEstadoParteDiario, Integer anno, Short mes, Date fecha) throws EntornoEjecucionException,
			SesionVencidaException {

		try {

			StringBuilder queryStr = new StringBuilder("FROM Tablakardex AS tk WHERE ");

			if (codigoEstadoParteDiario != null) {
				queryStr.append("tk.producciondiaria.partediario.estadopartediario.pkCodigoEstadopartediario = :codEstPD AND ");
			}

			if (fecha != null) {
				queryStr.append("tk.fechaTablakardex = :fecha AND ");
			}

			queryStr.append("tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = :anio AND ");
			queryStr.append("tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes ");
			queryStr.append(" ORDER BY fechaTablakardex ASC ");

			Query query = Querier.query(queryStr.toString());

			if (codigoEstadoParteDiario != null) {
				query.setLong("codEstPD", codigoEstadoParteDiario);
			}

			if (fecha != null) {
				query.setDate("fecha", fecha);
			}

			query.setInteger("anio", anno);
			query.setShort("mes", mes);

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

	/**
	 * Obtiene los kardex del mes de un pp o pt
	 * 
	 * @param codigoProceso Long
	 * @param codigoProducto Long
	 * @param codigoLineaNegocio Long
	 * @param codigoEstadoParteDiario Long
	 * @param anno Integer
	 * @param mes Short
	 * @return Tablakardex
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	public static List<Tablakardex> obtenerTablaKardexPpYPtDelMes(Long codigoProceso, Long codigoProducto,
			Long codigoLineaNegocio, Long codigoEstadoParteDiario, Integer anno, Short mes) throws EntornoEjecucionException,
			SesionVencidaException {
		List<Tablakardex> kardexs = obtenerTablaKardexPpYPt(codigoProceso, codigoProducto, codigoLineaNegocio,
				codigoEstadoParteDiario, anno, mes, null);

		return kardexs;
	}

	/**
	 * Obtiene el valor del kardex de un pp o pt para una fecha especifica
	 * 
	 * @param codigoProceso Long
	 * @param codigoProducto Long
	 * @param codigoLineaNegocio Long
	 * @param codigoEstadoParteDiario Long
	 * @param anno Integer
	 * @param mes Short
	 * @param fecha Date
	 * @return Tablakardex
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	public static Tablakardex obtenerTablaKardexPpYPtPorFecha(Long codigoProceso, Long codigoProducto, Long codigoLineaNegocio,
			Long codigoEstadoParteDiario, Integer anno, Short mes, Date fecha) throws EntornoEjecucionException,
			SesionVencidaException {
		List<Tablakardex> kardexs = obtenerTablaKardexPpYPt(codigoProceso, codigoProducto, codigoLineaNegocio,
				codigoEstadoParteDiario, anno, mes, fecha);

		if (kardexs != null && kardexs.size() != 0) {
			Tablakardex tablakardex = kardexs.get(0);
			return tablakardex;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Consumocomponente> obtenerConsumoComponentes(Long codigoProceso, Long codigoProducto,
			Long codigoLineaNegocio, Integer anno, Short mes) throws EntornoEjecucionException, SesionVencidaException {

		try {

			StringBuilder queryStr = new StringBuilder("SELECT comp FROM Consumocomponente AS comp WHERE ");
			queryStr.append("comp.tablakardex.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso = :codigoProceso AND ");
			if (codigoProducto != null) {
				queryStr.append("comp.tablakardex.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :codProducto AND ");
			} else {
				queryStr.append("comp.tablakardex.producciondiaria.ordenproduccion.pkCodigoOrdenproduccion > 0 AND ");
			}
			queryStr.append("comp.tablakardex.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			queryStr.append("comp.tablakardex.producciondiaria.partediario.periodocontable.anoPeriodocontable = :anio AND ");
			queryStr.append("comp.tablakardex.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes ");
			// queryStr
			// .append("GROUP BY comp.tablakardex.fechaTablakardex,
			// comp.pkCodigoConsumocomponente,comp.componente.pkCodigoComponente,
			// ");
			// queryStr.append("comp.tablakardex.pkCodigoTablakardex,
			// comp.consumoConsumocomponente ");
			queryStr.append("ORDER BY comp.tablakardex.fechaTablakardex ASC");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codigoProceso", codigoProceso);
			if (codigoProducto != null) {
				query.setLong("codProducto", codigoProducto);
			}
			query.setLong("codLineaNeg", codigoLineaNegocio);
			query.setInteger("anio", anno);
			query.setShort("mes", mes);

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

	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerTablaKardexMp(Long codigoProducto, Long codigoLineaNegocio,
			Long codigoEstadoParteDiario, Integer anno, Short mes) throws SesionVencidaException, EntornoEjecucionException {

		try {

			StringBuilder queryStr = new StringBuilder("FROM Tablakardex AS tk WHERE ");
			if (codigoProducto != null) {
				queryStr.append("tk.producciondiaria.producto.pkCodigoProducto = :codProducto AND ");
			} else {
				queryStr.append("tk.producciondiaria.producto.pkCodigoProducto > 0 AND ");
			}
			queryStr.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			if (codigoEstadoParteDiario != null) {
				queryStr.append("tk.producciondiaria.partediario.estadopartediario.pkCodigoEstadopartediario = :codEstPD AND ");
			}
			queryStr.append("tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = :anio AND ");
			queryStr.append("tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes ORDER BY fechaTablakardex ASC");

			Query query = Querier.query(queryStr.toString());

			if (codigoProducto != null) {
				query.setLong("codProducto", codigoProducto);
			}
			query.setLong("codLineaNeg", codigoLineaNegocio);
			if (codigoEstadoParteDiario != null) {
				query.setLong("codEstPD", codigoEstadoParteDiario);
			}
			query.setInteger("anio", anno);
			query.setShort("mes", mes);

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

	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerTablaKardexMpPorProceso(Long codigoProducto, Long codigoProceso,
			Long codigoLineaNegocio, Long codigoEstadoParteDiario, Integer anno, Short mes) throws SesionVencidaException,
			EntornoEjecucionException {
		try {

			StringBuilder queryStr = new StringBuilder("FROM Tablakardex AS tk WHERE ");
			if (codigoProducto != null) {
				queryStr.append("tk.producciondiaria.producto.pkCodigoProducto = :codProducto AND ");
			} else {
				queryStr.append("tk.producciondiaria.producto.pkCodigoProducto IN");
				queryStr.append("(Select DISTINCT(hrc.componente.productoByFkCodigoProductoComponente.pkCodigoProducto) FROM Hojarutacomponente hrc WHERE ");
				queryStr.append("hrc.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :estadoHR AND ");
				queryStr.append("hrc.hojaruta.produccion.proceso.pkCodigoProceso = :codProceso) AND ");
			}
			// queryStr.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio
			// = :codLineaNeg AND ");
			if (codigoEstadoParteDiario != null) {
				queryStr.append("tk.producciondiaria.partediario.estadopartediario.pkCodigoEstadopartediario = :codEstPD AND ");
			}
			queryStr.append("tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = :anio AND ");
			queryStr.append("tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes ORDER BY fechaTablakardex ASC");

			Query query = Querier.query(queryStr.toString());

			if (codigoProducto != null) {
				query.setLong("codProducto", codigoProducto);
			}
			if (codigoProceso != null) {
				query.setLong("codProceso", codigoProceso);
			}

			// query.setLong("codLineaNeg", codigoLineaNegocio);
			if (codigoEstadoParteDiario != null) {
				query.setLong("codEstPD", codigoEstadoParteDiario);
			}

			query.setLong("estadoHR", HojaRutaQuerier.CODIGO_HOJARUTA_ACTIVA);

			query.setInteger("anio", anno);
			query.setShort("mes", mes);

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

	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerRegistroDiario(Date fechaTablakardex, Long codigoTipoProducto, Long codigoAlmacen)
			throws AplicacionException {
		try {
			String queryString = "SELECT tk FROM Tablakardex as tk "
					+ "tk.producciondiaria.ordenproduccion.produccion.producto.tipoproducto.pkCodigoTipoproducto = :pkCodigoTipoproducto"
					+ "WHERE tk.almacen.pkCodigoAlmacen = :pkCodigoAlmacen AND " + "tk.fechaTablakardex = :fechaTablakardex";

			Query query = Querier.query(queryString);
			query.setLong("pkCodigoTipoproducto", codigoTipoProducto);
			query.setLong("pkCodigoAlmacen", codigoAlmacen);
			query.setDate("fechaTablakardex", fechaTablakardex);

			return query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new AplicacionException(ERROR_QUERY_FALLO, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new AplicacionException(ERROR_USO_SESION_INAPROPIADA, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new AplicacionException(ERROR_COMUNICACION_FALLO, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static List<Tablakardex> obtenerRegistrosPorParteDiario(Long codigoProducto, Long codigoProceso,
			Long codigoLineaNegocio, Long anio, Long mes, Long dia) throws EntornoEjecucionException, SesionVencidaException,
			RuntimeException {
		try {
			String queryString = "SELECT tk FROM Tablakardex as tk "
					+ "WHERE tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = ?"
					+ "AND tk.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso = ?"
					+ "AND tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = ?"
					+ "AND tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = ? " // integer
					+ "AND tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = ? "// short
					+ "AND tk.fechaTablakardex = ?";

			Calendar fechaRegistro = Calendar.getInstance();
			fechaRegistro.set(anio.intValue(), mes.intValue() - 1, dia.intValue());
			Query query = Querier.query(queryString);
			query.setLong(0, codigoProducto);
			query.setLong(1, codigoProceso);
			query.setLong(2, codigoLineaNegocio);
			query.setInteger(3, anio.intValue());
			query.setShort(4, mes.shortValue());
			query.setDate(5, fechaRegistro.getTime());

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
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public static double obtenerStockFinalDiaAnterior(Producciondiaria producciondiaria) {
		try {
			StringBuilder hql = new StringBuilder("FROM Tablakardex as tk WHERE ");
			hql.append("tk.producciondiaria.pkCodigoProducciondiaria = :codProdDiaria ORDER BY tk.fechaTablakardex ASC ");

			Query query = Querier.query(hql.toString());
			query.setParameter("codProdDiaria", producciondiaria.getPkCodigoProducciondiaria());

			List<Tablakardex> tablasKardex = query.list();

			return obtenerStockFinalUltimoDiaRegistrado(tablasKardex);

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

	private static double obtenerStockFinalUltimoDiaRegistrado(List<Tablakardex> tablasKardex) {
		if (tablasKardex == null || tablasKardex.size() == 0) {
			return 0d;
		}

		Tablakardex tablakardex = tablasKardex.get(tablasKardex.size() - 1);

		return tablakardex.getStockFinalTablakardex();
	}

	@SuppressWarnings("unchecked")
	public static Tablakardex obtenerUltimodiaMes(Producto producto, Ajusteproduccion ajusteProduccion)
			throws SesionVencidaException, EntornoEjecucionException, ElementoNoEncontradoException {
		try {
			boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(producto.getPkCodigoProducto());

			StringBuilder hql = new StringBuilder("FROM Tablakardex as tk WHERE ");
			hql.append("tk.producciondiaria.partediario.periodocontable.pkCodigoPeridocontable = :codPeriodoCont  AND ");
			hql.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg  AND ");

			if (esMateriaPrima) {
				hql.append("tk.producciondiaria.producto.pkCodigoProducto = :codProducto");
			} else {
				hql.append("tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :codProducto");
			}
			hql.append(" ORDER BY tk.fechaTablakardex DESC");
			Query query = Querier.query(hql.toString());
			query.setParameter("codPeriodoCont", ajusteProduccion.getPeriodocontable().getPkCodigoPeridocontable());
			query.setParameter("codLineaNeg", ajusteProduccion.getLineanegocio().getPkCodigoLineanegocio());
			query.setParameter("codProducto", producto.getPkCodigoProducto());

			List<Tablakardex> tablasKardex = query.list();

			if (tablasKardex == null || tablasKardex.size() == 0) {
				return null;
			}

			Tablakardex tablakardex = tablasKardex.get(0);

			return tablakardex;

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

	@SuppressWarnings("unchecked")
	public static Tablakardex obtenerPorFechaProductoYLineaNegocio(Date fecha, Long producto, Long linea)
			throws SesionVencidaException, EntornoEjecucionException, ElementoNoEncontradoException {
		try {
			boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(producto);

			StringBuilder hql = new StringBuilder("FROM Tablakardex as tk WHERE ");
			hql.append("tk.fechaTablakardex = :fecha  AND ");
			hql.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :linea  AND ");

			if (esMateriaPrima) {
				hql.append("tk.producciondiaria.producto.pkCodigoProducto = :producto");
			} else {
				hql.append("tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :producto");
			}
			hql.append(" ORDER BY tk.fechaTablakardex DESC");
			Query query = Querier.query(hql.toString());
			query.setDate("fecha", fecha);
			query.setParameter("linea", linea);
			query.setParameter("producto", producto);

			List<Tablakardex> tablasKardex = query.list();

			if (tablasKardex == null || tablasKardex.size() == 0) {
				return null;
			}

			Tablakardex tablakardex = tablasKardex.get(0);

			return tablakardex;

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

	@SuppressWarnings("unchecked")
	public static Double obtenerTMTotalKardex(Date fecha, Long producto, Long linea) throws SesionVencidaException,
			EntornoEjecucionException, ElementoNoEncontradoException {
		try {
			boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(producto);

			StringBuilder hql = new StringBuilder(
					"select sum(tk.ingresoTablakardex )  - (sum(tk.consumoTablakardex) / 2 ) + (sum(tk.ajustelogisticoTablakardex) / 2 )FROM Tablakardex as tk WHERE ");
			hql.append("tk.fechaTablakardex <= :fecha  AND tk.fechaTablakardex >= :fechaCotaInferior AND ");
			hql.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :linea  AND ");
			if (esMateriaPrima) {
				hql.append("tk.producciondiaria.producto.pkCodigoProducto = :producto");
			} else {
				hql.append("tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :producto");
			}
			Query query = Querier.query(hql.toString());
			query.setDate("fecha", fecha);
			query.setDate("fechaCotaInferior", DateUtils.truncate(fecha, Calendar.MONTH));
			query.setParameter("linea", linea);
			query.setParameter("producto", producto);

			Double result = (Double) query.uniqueResult();
			if (result == null)
				return 0D;

			return result;

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

	@SuppressWarnings("unchecked")
	public static double obtenerStockFinalDiaAnteriorPPyPTNoProducidos(Producto producto, Integer mesParteDiario,
			Lineanegocio lineanegocio) {
		try {
			StringBuilder hql = new StringBuilder("FROM Tablakardex as tk WHERE ");
			hql.append("tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :codProducto AND ");
			hql.append("tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes AND ");
			hql.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNegocio ");
			hql.append("ORDER BY tk.fechaTablakardex ASC ");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", producto.getPkCodigoProducto());
			query.setShort("mes", mesParteDiario.shortValue());
			query.setLong("codLineaNegocio", lineanegocio.getPkCodigoLineanegocio());

			List<Tablakardex> tablasKardex = query.list();

			return obtenerStockFinalUltimoDiaRegistrado(tablasKardex);

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

	@SuppressWarnings("unchecked")
	public static double obtenerStockFinalDiaAnteriorMpNoProducida(Producto producto, Integer mesParteDiario,
			Lineanegocio lineanegocio) {
		try {
			StringBuilder hql = new StringBuilder("FROM Tablakardex as tk WHERE ");
			hql.append("tk.producciondiaria.producto.pkCodigoProducto = :codProducto AND ");
			hql.append("tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes AND ");
			hql.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNegocio");
			hql.append("ORDER BY tk.fechaTablakardex ASC ");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", producto.getPkCodigoProducto());
			query.setShort("mes", mesParteDiario.shortValue());

			List<Tablakardex> tablasKardex = query.list();

			return obtenerStockFinalUltimoDiaRegistrado(tablasKardex);

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

	@SuppressWarnings("unchecked")
	public static Map<Long, Double> obtenerListaStockSilosDiaAnterior(Producciondiaria producciondiaria) {
		try {
			StringBuilder hql = new StringBuilder("FROM Tablakardex as tk WHERE ");
			hql.append("tk.producciondiaria.pkCodigoProducciondiaria = :codProdDiaria ORDER BY tk.fechaTablakardex ASC ");

			Query query = Querier.query(hql.toString());
			query.setParameter("codProdDiaria", producciondiaria.getPkCodigoProducciondiaria());

			List<Tablakardex> tablasKardex = query.list();

			if (tablasKardex == null || tablasKardex.size() == 0) {
				return null;
			}

			Tablakardex tablakardex = tablasKardex.get(tablasKardex.size() - 1);

			Map<Long, Double> stockSilosAnterior = new HashMap<Long, Double>();
			Set<Kardexmedioalmacenamiento> kardexmedioalmacenamientos = tablakardex.getKardexmedioalmacenamientos();

			for (Iterator<Kardexmedioalmacenamiento> iterator = kardexmedioalmacenamientos.iterator(); iterator.hasNext();) {
				Kardexmedioalmacenamiento item = iterator.next();

				stockSilosAnterior.put(item.getFkCodigoMedioalmacenamiento().getPkCodigoMedioalmacenamiento(),
						item.getStockFinallTablakardexmedioalmac());
			}

			return stockSilosAnterior;

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

	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerPorProductoFechaLineaMP(Date fechaInicio, Date fechaFin, Long codigoProducto,
			Long codigoLineaNeg, Long codigoMediAlmacena, Long codigoAlamcen) {
		try {

			StringBuilder queryStr = new StringBuilder("SELECT tk FROM Tablakardex AS tk ");
			if (codigoMediAlmacena != null) {
				queryStr.append("JOIN tk.kardexmedioalmacenamientos km ");
			}
			queryStr.append(" WHERE ");
			queryStr.append("tk.producciondiaria.producto.pkCodigoProducto = :codProducto AND ");
			if (codigoMediAlmacena != null) {
				queryStr.append("km.fkCodigoMedioalmacenamiento.pkCodigoMedioalmacenamiento = :codigoMedAlm AND ");
			}
			if (codigoAlamcen != null) {
				queryStr.append("tk.almacen.pkCodigoAlmacen = :codigoAlamcen AND ");
			}
			queryStr.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			queryStr.append("tk.fechaTablakardex between :fechaInicio and :fechaFin");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codProducto", codigoProducto);
			query.setLong("codLineaNeg", codigoLineaNeg);
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);

			if (codigoMediAlmacena != null) {
				query.setLong("codigoMedAlm", codigoMediAlmacena);
			}
			if (codigoAlamcen != null) {
				query.setLong("codigoAlamcen", codigoAlamcen);
			}
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

	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerPorProductoFechaLineaPpYPt(Date fechaInicio, Date fechaFin, Long codigoProducto,
			Long codigoProceso, Long codigoLineaNeg, Long codigoMediAlmacena, Long codigoAlamcen) {
		try {
			StringBuilder queryStr = new StringBuilder("SELECT tk FROM Tablakardex AS tk ");
			if (codigoMediAlmacena != null) {
				queryStr.append("JOIN tk.kardexmedioalmacenamientos km ");
			}
			queryStr.append(" WHERE ");
			queryStr.append("tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :produc AND ");
			if (codigoMediAlmacena != null) {
				queryStr.append("km.fkCodigoMedioalmacenamiento.pkCodigoMedioalmacenamiento = :codigoMedAlm AND ");
			}
			if (codigoAlamcen != null) {
				queryStr.append("tk.almacen.pkCodigoAlmacen = :codigoAlamcen AND ");
			}
			queryStr.append("tk.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso = :proceso AND ");
			queryStr.append("tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			queryStr.append("tk.fechaTablakardex between :fechaInicio AND :fechaFin ");
			queryStr.append("ORDER BY tk.fechaTablakardex ");
			Query query = Querier.query(queryStr.toString());

			query.setLong("produc", codigoProducto);
			query.setLong("proceso", codigoProceso);
			query.setLong("codLineaNeg", codigoLineaNeg);
			query.setDate("fechaInicio", fechaInicio);
			query.setDate("fechaFin", fechaFin);
			if (codigoMediAlmacena != null) {
				query.setLong("codigoMedAlm", codigoMediAlmacena);
			}
			if (codigoAlamcen != null) {
				query.setLong("codigoAlamcen", codigoAlamcen);
			}
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

	public static Double obtenerSaldoIncialSegunOrdenYFecha(Ordenproduccion ordenproduccion, Date fecha)
			throws ElementoNoEncontradoException {
		String mensajeError = "";

		try {
			StringBuilder queryStr = new StringBuilder("SELECT tk.saldoInicialTablakardex FROM Tablakardex AS tk WHERE ");
			queryStr.append("tk.producciondiaria.ordenproduccion.pkCodigoOrdenproduccion = :orden AND ");
			queryStr.append("tk.fechaTablakardex = :fecha");

			Query query = query(queryStr.toString());
			query.setLong("orden", ordenproduccion.getPkCodigoOrdenproduccion());
			query.setDate("fecha", fecha);

			Double saldoInicial = (Double) query.uniqueResult();

			return saldoInicial == null ? 0d : saldoInicial;
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
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

	/**
	 * Obtiene los kardex del mes de un pp o pt
	 * 
	 * @param codigoProceso Long
	 * @param codigoProducto Long
	 * @param codigoLineaNegocio Long
	 * @param codigoEstadoParteDiario Long
	 * @param anno Integer
	 * @param mes Short
	 * @param codigoLineaNegocio
	 * @return Tablakardex
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	public static List<Tablakardex> obtenerTablaKardexPorProcesoProducto(Long codigoProceso, Long codigoProducto, Integer anno,
			Short mes, boolean esMateriaPrima) throws EntornoEjecucionException, SesionVencidaException {

		StringBuilder queryStr = new StringBuilder("FROM Tablakardex AS tk WHERE ");

		if (esMateriaPrima) {
			if (codigoProducto != null) {
				queryStr.append("tk.producciondiaria.producto.pkCodigoProducto = :producto AND ");
				queryStr.append("tk.producciondiaria.producto.estadoproducto.pkCodigoEstadoproducto = :estadoProducto AND ");
			}
		} else {
			if (codigoProceso != null && codigoProducto != null) {
				queryStr.append("tk.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso = :proceso AND ");
				queryStr.append("tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = :producto AND ");
				queryStr.append("tk.producciondiaria.ordenproduccion.produccion.producto.estadoproducto.pkCodigoEstadoproducto = :estadoProducto AND ");
			}

		}
		queryStr.append("tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = :anio AND ");
		queryStr.append("tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes ");

		queryStr.append(" ORDER BY fechaTablakardex ASC ");

		if (esMateriaPrima) {
			if (codigoProducto != null) {
				queryStr.append(",tk.producciondiaria.producto.nombreProducto ASC");
			}
		} else {
			if (codigoProceso != null && codigoProducto != null) {
				queryStr.append(",tk.producciondiaria.ordenproduccion.produccion.proceso.ordenEjecucionProceso ASC,tk.producciondiaria.ordenproduccion.produccion.proceso.lineanegocio.pkCodigoLineanegocio ASC ");
				queryStr.append(",tk.producciondiaria.ordenproduccion.produccion.producto.nombreProducto ASC ");
			}

		}

		Query query = Querier.query(queryStr.toString());

		if (esMateriaPrima) {
			if (codigoProducto != null) {
				query.setLong("producto", codigoProducto);
				query.setLong("estadoProducto", Long.valueOf(1));
			}
		} else {
			if (codigoProceso != null && codigoProducto != null) {
				query.setLong("proceso", codigoProceso);
				query.setLong("producto", codigoProducto);
				query.setLong("estadoProducto", Long.valueOf(1));
			}

		}

		query.setInteger("anio", anno);
		query.setShort("mes", mes);

		return query.list();

	}

	public static List<Tablakardex> obtenerTablaKardexPorPeriodoParteDiario(Long lineaNegocio, Date fecha) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);
		StringBuilder queryStr = new StringBuilder("FROM Tablakardex tk");
		queryStr.append(" WHERE tk.producciondiaria.partediario.periodocontable.mesPeriodocontable= :mes ");
		queryStr.append(" AND tk.producciondiaria.partediario.periodocontable.anoPeriodocontable= :anio ");
		queryStr.append(" AND tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio= :lineaNegocio ");
		queryStr.append(" AND tk.fechaTablakardex= :fecha ");
		queryStr.append(" AND tk.observacionTablakardex is null ");
		Query query = Querier.query(queryStr.toString());
		query.setShort("mes", mes.shortValue());
		query.setInteger("anio", anio);
		query.setLong("lineaNegocio", lineaNegocio);
		query.setDate("fecha", fecha);

		return query.list();
	}

	public static void eliminarKardexAlmacenamientoPorCodigosTablaKardex(List<Long> codigosKardex) {
		StringBuilder queryStr = new StringBuilder(
				" DELETE FROM Kardexmedioalmacenamiento km where km.fkCodigoTablakardex.pkCodigoTablakardex in (:codigosTablaKardex)");
		Query query = Querier.query(queryStr.toString());
		query.setParameterList("codigosTablaKardex", codigosKardex);
		query.executeUpdate();

	}

	public static void eliminarPorcodigos(List<Long> codigosKardex) {
		StringBuilder queryStr = new StringBuilder(
				" DELETE FROM Tablakardex tk where tk.pkCodigoTablakardex in (:codigosTablaKardex)");
		Query query = Querier.query(queryStr.toString());
		query.setParameterList("codigosTablaKardex", codigosKardex);
		query.executeUpdate();

	}

	public static List<Object[]> obtenerConsumoProductoToFecha(Producto producto, Date time, Date time2) {
		StringBuilder sql = new StringBuilder("SELECT consumoTablakardex , consumoHumedadTablakardex  FROM Tablakardex tk ");
		sql.append(" WHERE ");
		sql.append("  tk.producciondiaria.producto.pkCodigoProducto= :producto  ");
		sql.append("  AND tk.fechaTablakardex >= :fechaInicio AND  tk.fechaTablakardex < :fechaFin ");
		Query query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());
		query.setDate("fechaInicio", time);
		query.setDate("fechaFin", time2);
		List<Object[]> suma = (List<Object[]>) query.list();

		if (suma != null && suma.size() > 0) {
			return suma;

		}
		sql = new StringBuilder("SELECT consumoTablakardex , consumoHumedadTablakardex  FROM Tablakardex tk ");
		sql.append(" WHERE ");
		sql.append("  tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto= :producto  ");
		sql.append("  AND tk.fechaTablakardex >= :fechaInicio AND  tk.fechaTablakardex < :fechaFin ");
		query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());
		query.setDate("fechaInicio", time);
		query.setDate("fechaFin", time2);
		suma = (List<Object[]>) query.list();
		return suma;
	}

	public static List<Object[]> obtenerIngresoProductoToFecha(Producto producto, Date time, Date time2) {
		StringBuilder sql = new StringBuilder("SELECT ingresoTablakardex , ingresoHumedadTablakardex  FROM Tablakardex tk ");
		sql.append(" WHERE ");
		sql.append("  tk.producciondiaria.producto.pkCodigoProducto= :producto  ");
		sql.append("  AND tk.fechaTablakardex >= :fechaInicio AND  tk.fechaTablakardex < :fechaFin ");
		Query query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());
		query.setDate("fechaInicio", time);
		query.setDate("fechaFin", time2);
		List<Object[]> suma = (List<Object[]>) query.list();

		if (suma != null && suma.size() > 0) {
			return suma;

		}
		sql = new StringBuilder("SELECT ingresoTablakardex , ingresoHumedadTablakardex  FROM Tablakardex tk ");
		sql.append(" WHERE ");
		sql.append("  tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto= :producto  ");
		sql.append("  AND tk.fechaTablakardex >= :fechaInicio AND  tk.fechaTablakardex < :fechaFin ");
		query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());
		query.setDate("fechaInicio", time);
		query.setDate("fechaFin", time2);
		suma = (List<Object[]>) query.list();
		return suma;
	}

	public static List<GestionStockGraficoBean> generarReporteStock(Date fechaInicio, Date fechaFin, Long proceso,
			String nombreReporte, String tituloEjeY, List<Long> listProducto) {

		StringBuilder sql = new StringBuilder("SELECT  ");
		sql.append(" new pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.GestionStockGraficoBean( ");
		sql.append(" day(tk.fechaTablakardex)");
		sql.append(" ,tk.stockFinalTablakardex ");
		if (listProducto != null) {
			sql.append(" ,tk.producciondiaria.producto.nombreProducto ");
		} else {
			sql.append(" ,tk.producciondiaria.ordenproduccion.produccion.producto.nombreProducto ");
		}
		sql.append(" ,tk.saldoInicialTablakardex ");
		sql.append(" ,tk.ingresoTablakardex ");
		sql.append(" ,tk.consumoTablakardex ");
		sql.append(" ,'" + nombreReporte + "'");
		sql.append(" ,'" + tituloEjeY + "')");
		sql.append(" FROM Tablakardex tk  ");
		sql.append(" WHERE ");
		sql.append("  	  tk.fechaTablakardex >= :fechaInicio ");
		sql.append("  AND tk.fechaTablakardex <= :fechaFin ");
		// sql.append(" AND tk.stockFinalTablakardex > 0 ");
		if (listProducto != null) {
			sql.append("  AND tk.producciondiaria.producto.pkCodigoProducto in (:producto) ");
		} else {
			sql.append("  AND tk.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso in (:proceso) ");
		}
		sql.append(" ORDER BY tk.fechaTablakardex");
		if (listProducto != null) {
			sql.append(",tk.producciondiaria.producto.nombreProducto");
		} else {
			sql.append(",tk.producciondiaria.ordenproduccion.produccion.producto.nombreProducto");
		}

		Query query = query(sql.toString());
		query.setDate("fechaInicio", fechaInicio);
		query.setDate("fechaFin", fechaFin);
		if (listProducto != null) {
			query.setParameterList("producto", listProducto);
		} else {
			query.setLong("proceso", proceso);
		}
		List<GestionStockGraficoBean> lista = query.list();

		return lista;
	}

	public static List<GestionStockGraficoBean> generarReporteStockTotalSaldoFinal(Date fechaInicio, Date fechaFin, Long proceso,
			String titulo, String nombreEjeY, String nombreEjeY2) {
		StringBuilder sql = new StringBuilder("SELECT  ");
		sql.append(" new pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.gestionStock.GestionStockGraficoBean( ");
		sql.append(" day(tk.fechaTablakardex)");
		sql.append(" ,SUM(tk.stockFinalTablakardex) ");
		sql.append(" ,'" + titulo + "'");
		sql.append(" ,'" + nombreEjeY2 + "'");
		sql.append(" ,'" + nombreEjeY2 + "'");
		sql.append(" ,'" + nombreEjeY + "')");
		sql.append(" FROM Tablakardex tk  ");
		sql.append(" WHERE ");
		sql.append("  	  tk.fechaTablakardex >= :fechaInicio ");
		sql.append("  AND tk.fechaTablakardex <= :fechaFin ");
		// sql.append(" AND tk.stockFinalTablakardex > 0 ");
		sql.append("  AND tk.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso in (:proceso) ");
		sql.append(" GROUP BY tk.fechaTablakardex ");
		sql.append(" ORDER BY tk.fechaTablakardex ");
		Query query = query(sql.toString());
		query.setDate("fechaInicio", fechaInicio);
		query.setDate("fechaFin", fechaFin);
		query.setLong("proceso", proceso);

		List<GestionStockGraficoBean> lista = query.list();

		return lista;
	}

	public static List<Tablakardex> obtenerInformeFactores(Long codigoUnidad, Integer anio, Short mes, Long proceso) {
		StringBuilder sql = new StringBuilder(" FROM Tablakardex tk ");
		sql.append(" WHERE ");
		sql.append(" tk.producciondiaria.partediario.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad ");
		sql.append(" AND tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = :anio ");
		sql.append(" AND tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = :mes ");
		sql.append(" AND tk.producciondiaria.ordenproduccion.produccion.proceso.pkCodigoProceso in (:proceso) ");

		Query query = query(sql.toString());
		query.setLong("codigoUnidad", codigoUnidad);
		query.setInteger("anio", anio);
		query.setShort("mes", mes);
		query.setLong("proceso", proceso);

		return query.list();
	}

	public static Tablakardex obtenerKardexUltimoConsumo(Producto producto) {
		StringBuilder sql = new StringBuilder("FROM Tablakardex tk ");
		sql.append(" WHERE ");
		sql.append("  tk.producciondiaria.producto.pkCodigoProducto= :producto  ");
		sql.append(" AND tk.consumoTablakardex > 0");

		sql.append(" ORDER BY tk.fechaTablakardex DESC ");

		Query query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());
		query.setMaxResults(1);
		Tablakardex suma = (Tablakardex) query.uniqueResult();

		if (suma != null) {
			return suma;

		}
		sql = new StringBuilder("FROM Tablakardex tk ");
		sql.append(" WHERE ");
		sql.append("  tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto= :producto  ");
		sql.append(" AND tk.consumoTablakardex > 0");
		sql.append(" ORDER BY tk.fechaTablakardex DESC ");
		query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());
		query.setMaxResults(1);
		suma = (Tablakardex) query.uniqueResult();
		return suma;
	}

	/**
	 * Metodo para obtener una produccion diaria por codigo de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException {
	 */
	public static Tablakardex getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tablakardex.class, codigo);
	}

}
