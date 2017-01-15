package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ProduccionDiariaQuerier.java
 * Modificado: Jun 2, 2010 8:08:19 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ProduccionDiariaQuerier extends Querier implements ConstantesLogicaNegocio {

	private static Logger log = Logger.getLogger(ProduccionDiariaQuerier.class);

	public static Producciondiaria getByPartediarioYOrdenProduccion(Long codigoParteDiario, Long codigoOrdenProduccion)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		String consulta = "FROM Producciondiaria AS pdia WHERE pdia.partediario.pkCodigoPartediario = :codigoParteDiario AND "
				+ "pdia.ordenproduccion.pkCodigoOrdenproduccion = :codigoOrdenProduccion";

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoParteDiario", codigoParteDiario);
			query.setLong("codigoOrdenProduccion", codigoOrdenProduccion);

			Producciondiaria producciondiaria = (Producciondiaria) query.uniqueResult();
			return producciondiaria;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Producciondiaria getByPartediarioYProductoOrden(Long lineaNeg, Integer anio, Short mes,
			Ordenproduccion ordenproduccion) throws ElementoNoEncontradoException, EntornoEjecucionException {

		StringBuilder hql = new StringBuilder("Select DISTINCT(pdia) ");
		hql.append("FROM Producciondiaria pdia, Ordenproduccionplan opp, Ordenproduccionmanual opm WHERE");
		hql.append(" pdia.partediario.periodocontable.anoPeriodocontable = :anio");
		hql.append(" AND pdia.partediario.periodocontable.mesPeriodocontable = :mes ");
		hql.append(" AND pdia.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");

		hql.append(" AND pdia.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");
		hql.append(" AND pdia.ordenproduccion.produccion.proceso.pkCodigoProceso = :codigoProceso");
		hql.append(" AND pdia.ordenproduccion.mesOrdenproduccion = :mes");
		hql.append(" AND pdia.ordenproduccion.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :lineaNeg");

		try {
			Query query = Querier.query(hql.toString());
			query.setInteger("anio", anio);
			query.setShort("mes", mes);
			query.setLong("lineaNeg", lineaNeg);
			query.setLong("codigoProducto", ordenproduccion.getProduccion().getProducto().getPkCodigoProducto());
			query.setLong("codigoProceso", ordenproduccion.getProduccion().getProceso().getPkCodigoProceso());

			Producciondiaria producciondiaria = (Producciondiaria) query.uniqueResult();
			return producciondiaria;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Producciondiaria getByPartediarioYComponenteAnt(Long lineaNeg, Integer anio, Short mes, Producto producto)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		StringBuilder queryBld = new StringBuilder("FROM Producciondiaria AS pdia WHERE ");
		queryBld.append(" pdia.partediario.periodocontable.anoPeriodocontable = :anio");
		queryBld.append(" AND pdia.partediario.periodocontable.mesPeriodocontable = :mes ");
		queryBld.append(" AND pdia.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
		queryBld.append(" AND pdia.producto.pkCodigoProducto = :codigoProdComp");

		try {
			Query query = Querier.query(queryBld.toString());
			query.setInteger("anio", anio);
			query.setShort("mes", mes);
			query.setLong("lineaNeg", lineaNeg);
			query.setLong("codigoProdComp", producto.getPkCodigoProducto());

			Producciondiaria producciondiaria = (Producciondiaria) query.uniqueResult();
			return producciondiaria;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Producciondiaria> getByPartediario(Long codigoParteDiario) throws ElementoNoEncontradoException,
			EntornoEjecucionException {
		String consulta = "FROM Producciondiaria AS pdia where pdia.partediario.pkCodigoPartediario = :codigoParteDiario ";

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoParteDiario", codigoParteDiario);

			return query.list();

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Producciondiaria obtenerPorFechaYOrdenProduccion(Date fechaNotif, Ordenproduccion ordenproduccion)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		String consulta = "FROM Producciondiaria AS pd where pd.partediario.periodocontable.mesPeriodocontable = :mes AND "
				+ "pd.partediario.periodocontable.anoPeriodocontable = :anio AND pd.ordenproduccion.pkCodigoOrdenproduccion = :codigoOrdenProduccion";

		try {
			Query query = Querier.query(consulta);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaNotif);
			int mes = cal.get(Calendar.MONTH) + 1;
			int anio = cal.get(Calendar.YEAR);
			query.setShort("mes", (short) mes);
			query.setInteger("anio", anio);
			query.setLong("codigoOrdenProduccion", ordenproduccion.getPkCodigoOrdenproduccion());

			Producciondiaria producciondiaria = (Producciondiaria) query.uniqueResult();
			return producciondiaria;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Producciondiaria obtenerPorFechaYComponente(Date fechaNotif, Producto producto)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		String consulta = "FROM Producciondiaria AS pd where pd.partediario.periodocontable.mesPeriodocontable = :mes AND "
				+ "pd.partediario.periodocontable.anoPeriodocontable = :anio AND pd.producto.pkCodigoProducto = :codigoProducto";

		try {
			Query query = Querier.query(consulta);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaNotif);
			int mes = cal.get(Calendar.MONTH) + 1;
			int anio = cal.get(Calendar.YEAR);
			query.setShort("mes", (short) mes);
			query.setInteger("anio", anio);
			query.setLong("codigoProducto", producto.getPkCodigoProducto());

			Producciondiaria producciondiaria = (Producciondiaria) query.uniqueResult();
			return producciondiaria;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Producciondiaria getByPartediarioYComponente(Long codigoParteDiario, Producto producto)
			throws ElementoNoEncontradoException {
		String consulta = "FROM Producciondiaria AS pdia where pdia.partediario.pkCodigoPartediario = :codigoParteDiario AND "
				+ "pdia.producto.pkCodigoProducto = :codigoProducto";

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoParteDiario", codigoParteDiario);
			query.setLong("codigoProducto", producto.getPkCodigoProducto());

			Producciondiaria producciondiaria = (Producciondiaria) query.uniqueResult();
			return producciondiaria;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	private static Object[] obtenerStocksMensualesProducto(Long codigoProducto, Long codigoLinea, Short mes, Integer anio,
			boolean porOrdenProduccion) throws ElementoNoEncontradoException, EntornoEjecucionException {
		StringBuilder queryBuilder = new StringBuilder(
				"SELECT SUM(pd.saldoInicialProducciondiaria),SUM(pd.ingresoProduccionProducciondi),SUM(pd.consumoProducciondiaria),SUM(pd.ajustelogisticoProducciondiaria) FROM Producciondiaria AS pd WHERE ");
		queryBuilder.append("pd.partediario.periodocontable.mesPeriodocontable = :mes ");
		queryBuilder.append("AND pd.partediario.periodocontable.anoPeriodocontable = :anio ");
		if (porOrdenProduccion) {
			queryBuilder.append("AND pd.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProd ");
		} else {
			queryBuilder.append("AND pd.producto.pkCodigoProducto = :codigoProd ");
		}

		if (codigoLinea != null && codigoLinea.longValue() != -1L) {
			queryBuilder.append("AND pd.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
		}

		try {
			Query query = Querier.query(queryBuilder.toString());
			query.setShort("mes", mes);
			query.setInteger("anio", anio);
			query.setLong("codigoProd", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("lineaNeg", codigoLinea);
			}
			Object[] arryaStocks = (Object[]) query.uniqueResult();
			return arryaStocks;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Double obtenerSaldoInicialClinker(Long codigoProducto, Long codigoLinea, Short mes, Integer anio)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		StringBuilder queryBuilder = new StringBuilder(
				"SELECT pd.saldoInicialProducciondiaria FROM Producciondiaria AS pd WHERE ");
		queryBuilder.append("pd.partediario.periodocontable.mesPeriodocontable = :mes ");
		queryBuilder.append("AND pd.partediario.periodocontable.anoPeriodocontable = :anio ");
		queryBuilder.append("AND pd.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProd ");
		queryBuilder.append("AND pd.ordenproduccion.produccion.proceso.pkCodigoProceso = :codigoProceso ");
		queryBuilder.append("AND pd.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");

		try {
			Query query = Querier.query(queryBuilder.toString());
			query.setShort("mes", mes);
			query.setInteger("anio", anio);
			query.setLong("codigoProd", codigoProducto);
			Long codigoProcesoClkHH = new Long(ManejadorPropiedades.obtenerPropiedadPorClave(CODIGO_PROCESO_CLINKERIZACION_HH));
			query.setLong("codigoProceso", codigoProcesoClkHH);
			query.setLong("lineaNeg", codigoLinea);

			Double saldoInicial = (Double) query.uniqueResult();
			return saldoInicial == null ? 0d : saldoInicial;
		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Object[] obtenerStocksMensualesPorOrdenProd(Long codigoProducto, Long codigoLinea, Short mes, Integer anio)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		return obtenerStocksMensualesProducto(codigoProducto, codigoLinea, mes, anio, true);
	}

	public static Object[] obtenerStocksMensualesPorProducto(Long codigoProducto, Long codigoLinea, Short mes, Integer anio)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		return obtenerStocksMensualesProducto(codigoProducto, codigoLinea, mes, anio, false);
	}

	public static void actualizarProduccionDiarioConValoresKardex(List<Tablakardex> tablakardexes)
			throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {
		for (Tablakardex tablakardex : tablakardexes) {
			Producciondiaria produccionDiaria = tablakardex.getProducciondiaria();

			Double saldinicial = produccionDiaria.getSaldoInicialProducciondiaria();
			Double ingreso = produccionDiaria.getIngresoProduccionProducciondi();
			Double consumo = produccionDiaria.getConsumoProducciondiaria();
			Double ajusteLogistico = produccionDiaria.getAjustelogisticoProducciondiaria();

			ingreso += (-tablakardex.getIngresoTablakardex());
			consumo += (-tablakardex.getConsumoTablakardex());
			ajusteLogistico += (-tablakardex.getAjustelogisticoTablakardex());
			
			
			Double saldoFinal = (saldinicial + ingreso) - consumo;
			saldoFinal += ajusteLogistico;

			produccionDiaria.setSaldoFinalProducciondiaria(saldoFinal);
			produccionDiaria.setIngresoProduccionProducciondi(ingreso);
			produccionDiaria.setConsumoProducciondiaria(consumo);
			produccionDiaria.setAjustelogisticoProducciondiaria(ajusteLogistico);
			
			update(produccionDiaria);
		}

	}

	public static void eliminarSegunFechaLineaNeg(Long lineaNegocio, Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);

		StringBuilder querystr = new StringBuilder("DELETE FROM Producciondiaria pd ");
		querystr.append(" WHERE pd.partediario IN (SELECT pt FROM Partediario pt WHERE pt.periodocontable.anoPeriodocontable=:anio");
		querystr.append(" AND  pt.periodocontable.mesPeriodocontable=:mes");
		querystr.append(" AND  pt.lineanegocio.pkCodigoLineanegocio=:lineaNegocio)");

		Query query = Querier.query(querystr.toString());
		query.setShort("mes", mes.shortValue());
		query.setInteger("anio", anio);
		query.setLong("lineaNegocio", lineaNegocio);
		query.executeUpdate();
	}

	/**
	 * Agregado por Fabian
	 */

	public static Object[] obtenerStocksAnualProducto(Long codigoProducto, Long codigoLinea, Short mes, Integer anio,
			boolean porOrdenProduccion) throws ElementoNoEncontradoException, EntornoEjecucionException {

		StringBuilder queryBuilder = new StringBuilder(
				"SELECT SUM(pd.saldoInicialProducciondiaria),SUM(pd.ingresoProduccionProducciondi),SUM(pd.consumoProducciondiaria) FROM Producciondiaria AS pd WHERE ");
		queryBuilder.append(" pd.partediario.periodocontable.anoPeriodocontable = :anio ");
		if (mes != null) {
			queryBuilder.append(" AND pd.partediario.periodocontable.mesPeriodocontable <= :mes ");
		}

		if (porOrdenProduccion) {
			queryBuilder.append("AND pd.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProd ");
		} else {
			queryBuilder.append("AND pd.producto.pkCodigoProducto = :codigoProd ");
		}
		if (codigoLinea != null && codigoLinea.longValue() != -1L) {
			queryBuilder.append("AND pd.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
		}

		try {
			Query query = Querier.query(queryBuilder.toString());

			query.setInteger("anio", anio);
			query.setLong("codigoProd", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("lineaNeg", codigoLinea);
			}
			if (mes != null) {
				query.setShort("mes", mes);
			}
			Object[] arryaStocks = (Object[]) query.uniqueResult();
			return arryaStocks;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	public static Object[] obtenerStocksMensualProducto(Long codigoProducto, Long codigoLinea, Short mes, Integer anio,
			boolean porOrdenProduccion) throws ElementoNoEncontradoException, EntornoEjecucionException {

		StringBuilder queryBuilder = new StringBuilder(
				"SELECT SUM(pd.saldoInicialProducciondiaria),SUM(pd.ingresoProduccionProducciondi),SUM(pd.consumoProducciondiaria),SUM(ajustelogisticoProducciondiaria) FROM Producciondiaria AS pd WHERE ");
		queryBuilder.append(" pd.partediario.periodocontable.anoPeriodocontable = :anio ");
		if (mes != null) {
			queryBuilder.append(" AND pd.partediario.periodocontable.mesPeriodocontable = :mes ");
		}

		if (porOrdenProduccion) {
			queryBuilder.append("AND pd.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProd ");
		} else {
			queryBuilder.append("AND pd.producto.pkCodigoProducto = :codigoProd ");
		}
		if (codigoLinea != null && codigoLinea.longValue() != -1L) {
			queryBuilder.append("AND pd.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
		}

		try {
			Query query = Querier.query(queryBuilder.toString());

			query.setInteger("anio", anio);
			query.setLong("codigoProd", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("lineaNeg", codigoLinea);
			}
			if (mes != null) {
				query.setShort("mes", mes);
			}
			Object[] arryaStocks = (Object[]) query.uniqueResult();
			return arryaStocks;

		} catch (UnresolvableObjectException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, e.getCause());
		} catch (ObjectNotFoundException e) {
			log.error(e);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, e.getCause());
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	// Registro Distribuible - Carga Automatica (Materia Prima)
	public static Double getProduccionDiariaporProducto(Short mes, Integer anio, Long producto, Ordenproduccion ordenproduccion) {
		StringBuilder sql = new StringBuilder("SELECT pd.ingresoProduccionProducciondi FROM Producciondiaria pd ");
		sql.append(" WHERE ");
		sql.append(" pd.partediario.periodocontable.mesPeriodocontable = :mes");
		sql.append(" AND pd.partediario.periodocontable.anoPeriodocontable = :anio");
		if (producto != null) {
			sql.append(" AND pd.producto.pkCodigoProducto in (:producto) ");
		}
		if (ordenproduccion != null) {
			sql.append(" AND pd.ordenproduccion.pkCodigoOrdenproduccion in (:ordenproduccion) ");
		}

		Query query = Querier.query(sql.toString());
		if (producto != null)
			query.setLong("producto", producto);
		if (ordenproduccion != null)
			query.setLong("ordenproduccion", ordenproduccion.getPkCodigoOrdenproduccion());

		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		Double cantidad = (Double) query.uniqueResult();
		if (cantidad == null) {
			cantidad = 0.0;
		}
		return cantidad;
	}

	/**
	 * Metodo para obtener una produccion diaria por codigo de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException {
	 */
	public static Producciondiaria getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Producciondiaria.class, codigo);
	}

}
