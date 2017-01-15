package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productogenerado;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ProductoGeneradoQuerier extends Querier {
	private static String mensajeError;
	private static Logger log = Logger.getLogger(ProductoGeneradoQuerier.class);
	private static final String NOMBRE_CLASE_PRODUCTO_GENERADO = Productogenerado.class.getSimpleName();
	private static final String TABLA_OPERACION = "tablaoperacion.pkCodigoTablaoperacion";

	@SuppressWarnings("unchecked")
	public static List<Productogenerado> obtenerProductoGeneradoPorVarioFiltros(Long codigoPuestoTrabajo, Long codigoProceso,
			Long codigoProducto, Integer anno, Integer mes, Date fechaLimite, Date fechaInicial) throws AplicacionException {
		List<Productogenerado> listaProductoGenerado = null;
		try {
			StringBuilder queryStr = new StringBuilder("FROM Productogenerado pg  WHERE ");
			// queryStr
			// .append("
			// pg.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio
			// = :codigoLineaNegocio ");
			queryStr.append("  pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anno ");
			queryStr.append(" AND pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes ");

			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				queryStr.append(" AND pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo");

			if (codigoProducto != null && codigoProducto > 0)
				queryStr.append(" AND pg.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");

			if (codigoProceso != null && codigoProceso > 0)
				queryStr.append(" AND pg.ordenproduccion.produccion.proceso.pkCodigoProceso = :codigoProceso");

			if (fechaInicial != null)
				queryStr.append(" AND pg.tablaoperacion.fechaTablaoperacion >= :fechaInicial");
			if (fechaLimite != null)
				queryStr.append(" AND pg.tablaoperacion.fechaTablaoperacion <= :fechaLimite");

			queryStr.append(" Order by  pg.ordenproduccion.produccion.proceso.lineanegocio.pkCodigoLineanegocio ASC, pg.ordenproduccion.produccion.proceso.ordenEjecucionProceso ASC, pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo ASC,pg.ordenproduccion.produccion.producto.nombreProducto ASC  "
					+ "	pg.ordenproduccion.produccion.producto.nombreProducto, " + "	pg.tablaoperacion.fechaTablaoperacion ");

			Query query = Querier.query(queryStr.toString());

			// query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setInteger("anno", anno);
			mes++;
			query.setShort("mes", mes.shortValue());

			if (codigoPuestoTrabajo != null && codigoPuestoTrabajo > 0)
				query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);

			if (codigoProceso != null && codigoProceso > 0)
				query.setLong("codigoProceso", codigoProceso);

			if (codigoProducto != null && codigoProducto > 0)
				query.setLong("codigoProducto", codigoProducto);

			if (fechaInicial != null)
				query.setDate("fechaInicial", fechaInicial);

			if (fechaLimite != null)
				query.setDate("fechaLimite", fechaLimite);

			listaProductoGenerado = query.list();
			return listaProductoGenerado;
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			log.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			log.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (HibernateException hException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			log.error(mensajeError, hException);
			throw new ElementoNoEncontradoException(mensajeError, hException.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Productogenerado> obtenerPorTablaOperacion(Long codigoTablaOperacion) throws SesionVencidaException,
			EntornoEjecucionException {
		String consulta = "FROM {0} AS pg where pg.{1} = :codigoTablaOperacion ";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_PRODUCTO_GENERADO, TABLA_OPERACION });

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoTablaOperacion", codigoTablaOperacion);

			List<Productogenerado> productoGenerado = query.list();
			return productoGenerado;

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

	public static double obtenerCantidadProductoPorFechaYOrdenProd(Ordenproduccion orden, Date fechaNotif)
			throws SesionVencidaException, EntornoEjecucionException {
		StringBuilder queryStr = new StringBuilder("SELECT SUM(pg.produccionTmProductogenerado) FROM Productogenerado pg WHERE ");
		queryStr.append("pg.ordenproduccion.pkCodigoOrdenproduccion = :codigoOrden AND ");
		queryStr.append("pg.tablaoperacion.fechaTablaoperacion = :fecha");

		try {
			Query query = Querier.query(queryStr.toString());
			query.setLong("codigoOrden", orden.getPkCodigoOrdenproduccion());
			query.setDate("fecha", fechaNotif);

			Double result = (Double) query.uniqueResult();
			return result == null ? 0d : result;

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

	public static double obtenerCantidadProductoPorFechaOrdenProdYPuest(Ordenproduccion orden, Date fechaNotif,
			Long codigoPuestoTrabajo) throws SesionVencidaException, EntornoEjecucionException {

		StringBuilder queryStr = new StringBuilder("SELECT SUM(pg.produccionTmProductogenerado) FROM Productogenerado pg WHERE ");
		queryStr.append("pg.ordenproduccion.pkCodigoOrdenproduccion = :codigoOrden AND ");
		queryStr.append("pg.tablaoperacion.fechaTablaoperacion = :fecha AND ");
		queryStr.append("pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo ");

		try {
			Query query = Querier.query(queryStr.toString());
			query.setLong("codigoOrden", orden.getPkCodigoOrdenproduccion());
			query.setDate("fecha", fechaNotif);
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			Double result = (Double) query.uniqueResult();
			return result == null ? 0d : result;

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

	public static void eliminarPorFechaLineaNeg(List<Long> codigos) {

		StringBuilder querystr = new StringBuilder("DELETE  FROM  Productogenerado pg ");
		querystr.append(" WHERE pg.pkCodigoProductogenerado in(:listaCodigos) ");
		Query query = Querier.query(querystr.toString());
		query.setParameterList("listaCodigos", codigos);

		query.executeUpdate();

	}

	public static List<Long> obtenerCodigosPorLineNegFecha(Long lineaNegocio, Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);

		StringBuilder querystr = new StringBuilder("SELECT pg.pkCodigoProductogenerado FROM  Productogenerado pg ");
		querystr.append(" WHERE pg.tablaoperacion.fechaTablaoperacion = :fechaTablaOperacion  ");
		querystr.append("AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mesPartediario  ");
		querystr.append("AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anoPartediario  ");
		querystr.append("AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineNegParteDiario  ");

		Query query = Querier.query(querystr.toString());
		query.setDate("fechaTablaOperacion", fecha);
		query.setShort("mesPartediario", mes.shortValue());
		query.setInteger("anoPartediario", anio);
		query.setLong("lineNegParteDiario", lineaNegocio);

		return query.list();

	}

	public static List<Object[]> obtenerCalententamiento(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable, Boolean valor) {
		String mensajeError = "";
		List<Object[]> consumos = new ArrayList<Object[]>();

		try {
			StringBuilder hql = new StringBuilder("SELECT");
			hql.append(" pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo,");
			if (valor) { // Obtner solido
				hql.append(" SUM(pg.descuentoAdicionalSolido)");
			} else { // Obtiene liquido
				hql.append(" SUM(pg.descuentoAdicionalLiquido)");
			}
			hql.append(" FROM Productogenerado AS pg WHERE");
			hql.append(" pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes ");
			hql.append(" AND pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio ");
			hql.append(" AND pg.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
			hql.append(" AND pg.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");

			hql.append(" GROUP BY");
			hql.append(" pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo");
			hql.append(" ORDER BY 1 ASC");

			Query query = query(hql.toString());

			query.setLong("lineaNeg", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codigoProducto", codigoProducto);
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

	public static Map<Long, Double[]> obtenerCalentamientoConsumoComponentes(Integer anno, Short mes, Long codigoLinea,
			Long codigoProducto, Long codigoProceso, Long... codigoProductoComponente) throws SesionVencidaException,
			EntornoEjecucionException {
		try {

			String queryString = " SELECT "
					+ " cpt.productogenerado.pkCodigoProductogenerado, "
					+ " cpt.productogenerado.descuentoAdicionalSolido, "
					+ " SUM(cpt.cantidadConsumopuestotrabajo)"

					+ " FROM Consumopuestotrabajo AS cpt WHERE "
					+ " cpt.productogenerado.ordenproduccion.mesOrdenproduccion = :mes"
					+ " AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codigoLinea"
					+ " AND cpt.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto"
					+ " AND cpt.productogenerado.ordenproduccion.produccion.proceso.pkCodigoProceso = :codigoProceso"
					+ " AND cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto  in (:codigoProductoComponente)"
					+ " AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes"
					+ " AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio"
					+ " GROUP BY cpt.productogenerado.pkCodigoProductogenerado,cpt.productogenerado.descuentoAdicionalSolido ";

			Query query = Querier.query(queryString);
			query.setShort("mes", mes);
			query.setInteger("anio", anno);
			query.setParameterList("codigoProductoComponente", codigoProductoComponente);
			query.setLong("codigoLinea", codigoLinea);
			query.setLong("codigoProducto", codigoProducto);
			query.setLong("codigoProceso", codigoProceso);

			List<Object[]> calentamientoConsumos = query.list();
			Map<Long, Double[]> mapaProductoGenerado = new HashMap<Long, Double[]>();
			Long pkProductoGenerado = 0L;
			Double descuentoSolido = 0d;
			Double cantidadConsumo = 0d;
			for (Object[] object : calentamientoConsumos) {
				descuentoSolido = 0d;
				cantidadConsumo = 0d;
				if (object.length == 3) {
					if (object[0] != null) {
						pkProductoGenerado = Long.valueOf(object[0].toString());
						if (object[1] != null) {
							descuentoSolido = Double.valueOf(object[1].toString());
						}
						if (object[2] != null) {
							cantidadConsumo = Double.valueOf(object[2].toString());
						}
						mapaProductoGenerado.put(pkProductoGenerado, new Double[] { descuentoSolido, cantidadConsumo });
					}

				}

			}
			return mapaProductoGenerado;

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
}
