package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Equivalenciasccvarcalidad;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorconsumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Produccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productogenerado;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorconsumopuestotrabajoQuerier.java
 * Modificado: May 14, 2011 10:21:56 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class FactorconsumopuestotrabajoQuerier extends Querier {

	private static Logger log = Logger.getLogger(FactorconsumopuestotrabajoQuerier.class);

	/**
	 * Obtiene el ultimo valor de variable de calidad para un producto en un
	 * puesto de trabajo determinado
	 * 
	 * @param consumopuestotrabajo Consumopuestotrabajo
	 * @param equivalenciasccvarcalidad
	 * @return double
	 */
	@SuppressWarnings("unchecked")
	public static double obtenerUltimoFactorConsumoPorConsumoComponente(Consumopuestotrabajo consumopuestotrabajo,
			Equivalenciasccvarcalidad equivalenciasccvarcalidad) throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = null;
		try {

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select f.valorFactorconsumopuestotrabajo FROM Factorconsumopuestotrabajo AS f  WHERE ");
			queryStr.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codProdGen ");
			queryStr.append(" AND f.fkCodigoConsumoPuestoTrabajo.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codComp");
			queryStr.append(" AND f.fkCodigoEquivalenciasccvarcalidad.descripVarCalidad = :descripVarCal");
			queryStr.append(" AND f.fkCodigoEquivalenciasccvarcalidad.puestotrabajo = :codPuesto");
			queryStr.append(" AND f.fkCodigoEquivalenciasccvarcalidad.componente.pkCodigoComponente = f.fkCodigoConsumoPuestoTrabajo.componente.pkCodigoComponente");
			queryStr.append(" AND f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :codPuesto");
			queryStr.append(" ORDER BY f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.fechaTablaoperacion desc");

			Query query = Querier.query(queryStr.toString());

			Productogenerado productogenerado = consumopuestotrabajo.getProductogenerado();
			Producto producto = productogenerado.getOrdenproduccion().getProduccion().getProducto();
			Producto componente = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente();

			query.setLong("codProdGen", producto.getPkCodigoProducto());
			query.setLong("codComp", componente.getPkCodigoProducto());
			query.setLong("codPuesto", productogenerado.getTablaoperacion().getProduccionpuestotrabajo().getPuestotrabajo()
					.getPkCodigoPuestotrabajo());
			query.setString("descripVarCal", equivalenciasccvarcalidad.getDescripVarCalidad());
			List<Double> valores = query.list();

			Double valor = 0d;

			if (valores != null && valores.size() > 0) {
				valor = valores.get(0);
			}

			return valor;

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
		} catch (NonUniqueResultException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Obtiene el valor de una variable de calidad para un producto en unpuesto
	 * de trabajo determinado
	 * 
	 * @param producto
	 * @param consumopuestotrabajo Consumopuestotrabajo
	 * @param descEquiVarCalidad String descripcion de la variable de calidad en
	 *            el scc
	 * @return double
	 */
	@SuppressWarnings("unchecked")
	public static double obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(Consumopuestotrabajo consumopuestotrabajo,
			String descEquiVarCalidad) throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = null;
		try {

			Produccion produccion = consumopuestotrabajo.getProductogenerado().getOrdenproduccion().getProduccion();
			Long codProceso = produccion.getProceso().getPkCodigoProceso();

			Long codPuestoTrab = consumopuestotrabajo.getProductogenerado().getTablaoperacion().getProduccionpuestotrabajo()
					.getPuestotrabajo().getPkCodigoPuestotrabajo();

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select f.valorFactorconsumopuestotrabajo FROM Factorconsumopuestotrabajo AS f  WHERE ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.proceso.pkCodigoProceso = :codProceso AND ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.componente.pkCodigoComponente = :codComponente AND ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.descripVarCalidad = :descripVarCalidad AND ");
			queryStr.append("f.fkCodigoConsumoPuestoTrabajo.pkCodigoConsumopuestotrabajo = :codigoConsumoPuesto");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setLong("codProceso", codProceso);
			query.setLong("codComponente", consumopuestotrabajo.getComponente().getPkCodigoComponente());
			query.setString("descripVarCalidad", descEquiVarCalidad);
			query.setLong("codigoConsumoPuesto", consumopuestotrabajo.getPkCodigoConsumopuestotrabajo());

			Double valorConsumo = (Double) query.uniqueResult();

			return valorConsumo == null ? 0d : valorConsumo;
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
		} catch (NonUniqueResultException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Obtiene el valor de una variable de calidad para un producto en unpuesto
	 * de trabajo determinado a partir del ultimo dia de produccion del Crudo
	 * Negro
	 * 
	 * @param producto
	 * @param consumopuestotrabajo Consumopuestotrabajo
	 * @param descEquiVarCalidad String descripcion de la variable de calidad en
	 *            el scc
	 * @return double
	 */
	@SuppressWarnings("unchecked")
	public static double obtenerFactorConsumoPorEquVarCalidadYConsumoComponente(Consumopuestotrabajo consumopuestotrabajo,
			String descEquiVarCalidad, Date ultimoDiaProduccion) throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = null;
		try {

			Produccion produccion = consumopuestotrabajo.getProductogenerado().getOrdenproduccion().getProduccion();
			Long codProceso = produccion.getProceso().getPkCodigoProceso();

			Long codPuestoTrab = consumopuestotrabajo.getProductogenerado().getTablaoperacion().getProduccionpuestotrabajo()
					.getPuestotrabajo().getPkCodigoPuestotrabajo();

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select f.valorFactorconsumopuestotrabajo FROM Factorconsumopuestotrabajo AS f  WHERE ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.proceso.pkCodigoProceso = :codProceso AND ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codComponente AND ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.descripVarCalidad = :descripVarCalidad AND ");
			queryStr.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.fechaTablaoperacion = :fechaProduccion");

			Query query = Querier.query(queryStr.toString());

			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setLong("codProceso", codProceso);
			query.setLong("codComponente", consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente()
					.getPkCodigoProducto());
			query.setString("descripVarCalidad", descEquiVarCalidad);
			query.setDate("fechaProduccion", ultimoDiaProduccion);

			Double valorConsumo = (Double) query.uniqueResult();

			return valorConsumo == null ? 0d : valorConsumo;
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
		} catch (NonUniqueResultException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static double obtenerPromedioFactorConsumoPorEquVarCalidadYProduccion(Consumopuestotrabajo consumopuestotrabajo,
			String descEquiVarCalidad, Long codPuestoTrab) throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = null;
		try {

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("Select DISTINCT(f.valorFactorconsumopuestotrabajo) FROM Factorconsumopuestotrabajo AS f  WHERE ");
			queryStr.append("f.fkCodigoEquivalenciasccvarcalidad.descripVarCalidad = :descripVarCalidad AND ");
			queryStr.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo != :codPuesto AND ");
			queryStr.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.fechaTablaoperacion = :fecha AND ");

			queryStr.append("f.fkCodigoConsumoPuestoTrabajo.productogenerado.ordenproduccion.pkCodigoOrdenproduccion = :codigoOrdenProd");

			Query query = Querier.query(queryStr.toString());

			Productogenerado productogenerado = consumopuestotrabajo.getProductogenerado();

			query.setString("descripVarCalidad", descEquiVarCalidad);
			query.setLong("codigoOrdenProd", productogenerado.getOrdenproduccion().getPkCodigoOrdenproduccion());
			query.setLong("codPuesto", codPuestoTrab);
			query.setDate("fecha", productogenerado.getTablaoperacion().getFechaTablaoperacion());

			List list = query.list();

			int cantElemetos = 0;
			Double total = 0d;
			for (Object object : list) {
				total += (Double) object;
				cantElemetos++;
			}

			if (cantElemetos > 0) {
				return total / cantElemetos;
			}

			return 0d;

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
		} catch (NonUniqueResultException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static void eliminarPorCodigodConsumoPuestoTrabajo(List<Long> codigosConsumoPuestosT) {
		StringBuilder querystr = new StringBuilder(
				"DELETE FROM Factorconsumopuestotrabajo c where c.fkCodigoConsumoPuestoTrabajo.pkCodigoConsumopuestotrabajo  in (:codigos) ");
		Query query = Querier.query(querystr.toString());
		query.setParameterList("codigos", codigosConsumoPuestosT);
		query.executeUpdate();

	}

	public static Factorconsumopuestotrabajo obtenerFactorConsumoPorCodigoConsumoPuestoTrabajo(Long pkCodigoConsumopuestotrabajo) {
		StringBuilder querystr = new StringBuilder(
				"FROM Factorconsumopuestotrabajo c where c.fkCodigoConsumoPuestoTrabajo.pkCodigoConsumopuestotrabajo  in (:codigo) ");
		Query query = Querier.query(querystr.toString());
		query.setLong("codigo", pkCodigoConsumopuestotrabajo);
		Factorconsumopuestotrabajo valor = (Factorconsumopuestotrabajo) query.uniqueResult();

		return valor;
	}

	public static Factorconsumopuestotrabajo obtenerFactorConsumoPorCodigoConsumoYCodigoProductoPuestoTrabajo(
			Long pkCodigoConsumopuestotrabajo, Long producto, Long componente, Date fecha) {
		StringBuilder querystr = new StringBuilder(
				" FROM Factorconsumopuestotrabajo c where c.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo  in (:puestoTrabajo) ");
		querystr.append(" AND c.fkCodigoConsumoPuestoTrabajo.componente.productoByFkCodigoProductoComponente.pkCodigoProducto=:componente ");
		querystr.append(" AND c.fkCodigoConsumoPuestoTrabajo.componente.productoByFkCodigoProducto.pkCodigoProducto=:producto ");
		querystr.append(" AND c.fkCodigoConsumoPuestoTrabajo.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto=:producto ");
		querystr.append(" AND c.fkCodigoConsumoPuestoTrabajo.productogenerado.tablaoperacion.fechaTablaoperacion=:fechaOperacion ");
		querystr.append(" AND c.fkCodigoEquivalenciasccvarcalidad.descripVarCalidad=:variableCalidad ");
		Query query = Querier.query(querystr.toString());

		query.setLong("puestoTrabajo", pkCodigoConsumopuestotrabajo);
		query.setLong("componente", componente);
		query.setLong("producto", producto);
		query.setDate("fechaOperacion", fecha);
		query.setString("variableCalidad", "Humedad");

		List<Factorconsumopuestotrabajo> lista = query.list();

		Factorconsumopuestotrabajo factor = null;
		if (lista != null && lista.size() > 0) {
			factor = lista.get(0);
		}

		return factor;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Map<Long, Double>> obtenerPromedioFactorVariableCalidadPorPeriodoContable(Long codigoLineaNegocio,
			Long codigoUnidad, Integer annio, Short mes, String variableCalidad) throws EntornoEjecucionException {
		String mensajeError = "";
		try {

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("SELECT fecha_tablaoperacion,pk_codigo_producto ,valor_factorconsumopuestotrabajo ");
			queryStr.append("  FROM vw_variablescalidad_consumo_producto");
			queryStr.append(" WHERE ");

			queryStr.append(" ano_periodocontable = :annio ");
			queryStr.append("AND mes_periodocontable = :mes ");
			queryStr.append("AND descrip_var_calidad LIKE :variableCalidad ");
			if (codigoUnidad != null) {
				queryStr.append("AND pk_codigo_unidad = :codigoUnidad ");
			}
			if (codigoLineaNegocio != null) {
				queryStr.append("AND pk_codigo_lineanegocio = :codigoLineaNegocio ");
			}

			Query query = Querier.getSession().createSQLQuery(queryStr.toString());

			if (codigoUnidad != null) {
				query.setLong("codigoUnidad", codigoUnidad);
			}

			query.setInteger("annio", annio);
			query.setShort("mes", mes);
			query.setString("variableCalidad",  "%" + variableCalidad + "%");

			if (codigoLineaNegocio != null) {
				query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			}

			List<Object[]> listaBd = query.list();

			Date fechaOperacion = null;
			Long codigoProducto = null;
			Double valorFactorPuestotrabajo = null;

			Map<String, Map<Long, Double>> factorPromedioCalidadDia = new HashMap<>();
			for (Object[] fila : listaBd) {
				fechaOperacion = (Date) fila[0];
				codigoProducto = NumberUtil.convertirObjectToLong(fila[1]);
				valorFactorPuestotrabajo = NumberUtil.convertirObjectToDouble(fila[2]);

				Map<Long, Double> factorPromedioProducto = factorPromedioCalidadDia.get(FechaUtil
						.convertirDateToString(fechaOperacion));

				if (factorPromedioProducto == null) {
					factorPromedioProducto = new HashMap<>();
				}

				factorPromedioProducto.put(codigoProducto, valorFactorPuestotrabajo);
				factorPromedioCalidadDia.put(FechaUtil.convertirDateToString(fechaOperacion), factorPromedioProducto);

			}

			return factorPromedioCalidadDia;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			e.printStackTrace();
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			e.printStackTrace();
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			e.printStackTrace();
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			e.printStackTrace();
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

}
