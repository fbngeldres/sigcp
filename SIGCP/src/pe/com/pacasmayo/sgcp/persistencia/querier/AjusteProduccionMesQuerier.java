package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacionproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producciondiaria;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AjusteProduccionMesQuerier.java
 * Modificado: Jul 29, 2010 4:16:07 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AjusteProduccionMesQuerier extends Querier implements ConstantesMensajeAplicacion, ConstantesMensajePresentacion,
		ConstantesLogicaNegocio {

	/**
	 * Método para listar los ajustes del produccto durante el mes por lineas de
	 * negocio
	 * 
	 * @param lineaCodigo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Ajusteproduccion> findByBussinesLine(Long lineaCodigo, Integer anioContable, Short mesContable,
			Long estadoAjusteProduccionCodigo) {
		List<Ajusteproduccion> ajusteProduccion = new ArrayList<Ajusteproduccion>();
		String mensajeError = "";

		try {
			String consultaEstado = "and ap.estadoajusteproduccion.pkCodigoEstadoajusteproduccio = ? ";
			String consultaAnioContable = "and ap.periodocontable.anoPeriodocontable = ? ";
			String consultaMesContable = "and ap.periodocontable.mesPeriodocontable = ? ";
			String consulta = "select ap " + "from " + "Ajusteproduccion ap " + "where "
					+ "ap.lineanegocio.pkCodigoLineanegocio = ? " + ((anioContable != null) ? consultaAnioContable : "")
					+ ((mesContable != null) ? consultaMesContable : "")
					+ ((estadoAjusteProduccionCodigo != null) ? consultaEstado : "");

			Query query = Querier.query(consulta);
			query.setLong(0, lineaCodigo);

			if (anioContable != null && mesContable != null && estadoAjusteProduccionCodigo != null) {
				query.setInteger(1, anioContable);
				query.setShort(2, mesContable);
				query.setLong(3, estadoAjusteProduccionCodigo);
			}
			if (anioContable != null && mesContable == null && estadoAjusteProduccionCodigo == null) {
				query.setInteger(1, anioContable);
			}
			if (anioContable == null && mesContable != null && estadoAjusteProduccionCodigo == null) {
				query.setShort(1, mesContable);
			}
			if (anioContable == null && mesContable == null && estadoAjusteProduccionCodigo != null) {
				query.setLong(1, estadoAjusteProduccionCodigo);
			}
			if (anioContable != null && mesContable != null && estadoAjusteProduccionCodigo == null) {
				query.setInteger(1, anioContable);
				query.setShort(2, mesContable);
			}
			if (anioContable != null && mesContable == null && estadoAjusteProduccionCodigo != null) {
				query.setInteger(1, anioContable);
				query.setLong(2, estadoAjusteProduccionCodigo);
			}
			if (anioContable == null && mesContable != null && estadoAjusteProduccionCodigo != null) {
				query.setShort(1, mesContable);
				query.setLong(2, estadoAjusteProduccionCodigo);
			}

			ajusteProduccion = query.list();

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

		return ajusteProduccion;
	}

	/**
	 * Determina si un producto fue o no cubicado
	 * 
	 * @param codigoProduccion
	 * @param tipoAlmacenamiento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean esProductoCubicado(Long codigoProducto, Short mesContable, Integer anioContable,
			String estadoCubicacionProducto) {
		String mensajeError = "";
		List<Object> resultados = new ArrayList<Object>();
		boolean esCubicado = false;

		try {
			String consulta = "select cb from Cubicacionproducto cp, Cubicacion cb where "
					+ "cp.produccion.producto.pkCodigoProducto = ? and " + "cp.mesCubicacionproducto = ? and "
					+ "cp.anoCubicacionproducto = ? and " + "cp.estadocubicacion.nombreEstadocubicacion = ? and "
					+ "cb.cubicacionproducto = cp ";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoProducto);
			query.setShort(1, mesContable);
			query.setInteger(2, anioContable);
			query.setString(3, estadoCubicacionProducto);

			resultados = query.list();

			if (resultados != null && resultados.size() > 0)
				esCubicado = true;

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

		return esCubicado;
	}

	public static Date obtenerFechaCubicacionProducto(Long codigoProducto, Long lineaNegocio, Short mesContable,
			Integer anioContable, String estadoCubicacionProducto) {
		String mensajeError = "";
		try {
			StringBuffer consulta = new StringBuffer("FROM Cubicacionproducto cb WHERE ");
			consulta.append("cb.produccion.producto.pkCodigoProducto = :producto  ");
			consulta.append(" AND cb.mesCubicacionproducto = :mes AND " + "cb.anoCubicacionproducto = :anno  ");
			consulta.append(" AND cb.lineanegocio.pkCodigoLineanegocio = :linea  ");
			if (estadoCubicacionProducto != null) {
				consulta.append(" AND cb.estadocubicacion.nombreEstadocubicacion = :estado");
			} else {
				consulta.append(" AND cb.estadocubicacion.nombreEstadocubicacion <> :estado");
			}
			Query query = Querier.query(consulta.toString());

			query.setLong("producto", codigoProducto);
			query.setShort("mes", mesContable);
			query.setInteger("anno", anioContable);
			query.setLong("linea", lineaNegocio);
			if (estadoCubicacionProducto != null) {
				query.setString("estado", estadoCubicacionProducto);
			} else {
				String estadoAnulado = ManejadorPropiedades.obtenerPropiedadPorClave(STOCK_CUBICACIONPRODUCTO_ESTADO_ANULADO);
				query.setString("estado", estadoAnulado);
			}

			Cubicacionproducto cubicacionProd = (Cubicacionproducto) query.uniqueResult();
			if (cubicacionProd != null) {
				return cubicacionProd.getFechaCubicacionproducto();
			}
			return null;
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
		} catch (Exception e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			e.printStackTrace();
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Método para obtener la medida del mes de la produccion de un producto en
	 * un proceso
	 * 
	 * @param codigoProduccion
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws ElementoNoEncontradoException
	 * @throws SesionVencidaException
	 */
	@SuppressWarnings("unchecked")
	public static List<Producciondiaria> obtenerMedicionMesProductoCubicado(Long codigoProducto, Long codigoLinea,
			Short mesContable, Integer anioContable) throws SesionVencidaException, ElementoNoEncontradoException,
			EntornoEjecucionException {
		String mensajeError = "";
		List<Producciondiaria> medidasFisica = new ArrayList<Producciondiaria>();

		boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(codigoProducto);

		try {

			StringBuilder consulta = new StringBuilder("SELECT pd FROM Producciondiaria pd, Partediario ptd WHERE ");
			consulta.append("ptd.lineanegocio.pkCodigoLineanegocio = ? AND ");
			consulta.append("ptd.periodocontable.mesPeriodocontable = ? AND ");
			consulta.append("ptd.periodocontable.anoPeriodocontable = ? AND ");
			consulta.append("pd.partediario = ptd AND ");
			if (esMateriaPrima) {
				consulta.append("pd.producto.pkCodigoProducto = ?");
			} else {
				consulta.append("pd.ordenproduccion.produccion.producto.pkCodigoProducto = ?");
			}

			Query query = Querier.query(consulta.toString());

			query.setLong(0, codigoLinea);
			query.setShort(1, mesContable);
			query.setInteger(2, anioContable);
			query.setLong(3, codigoProducto);

			medidasFisica = query.list();

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

		return medidasFisica;
	}

	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerKardexMes(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable) throws SesionVencidaException, EntornoEjecucionException, ElementoNoEncontradoException {
		String mensajeError = "";

		try {

			boolean esMateriaPrima = ProductoQuerier.verificarSiEsMateriaPrima(codigoProducto);

			StringBuilder queryBld = new StringBuilder("FROM Tablakardex tk WHERE ");
			queryBld.append(" tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = ?  ");
			queryBld.append(" AND tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = ? ");
			queryBld.append(" AND tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = ? ");

			if (esMateriaPrima) {
				queryBld.append(" AND tk.producciondiaria.producto.pkCodigoProducto = ? ");
			} else {
				queryBld.append(" AND tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = ? ");
			}

			queryBld.append(" ORDER BY tk.fechaTablakardex DESC");

			Query queryOP = Querier.query(queryBld.toString());

			queryOP.setLong(0, codigoLinea);
			queryOP.setShort(1, mesContable);
			queryOP.setInteger(2, anioContable);
			queryOP.setLong(3, codigoProducto);

			List<Tablakardex> medidasFisica = queryOP.list();

			return medidasFisica;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Método para obtener la medida diaria de la produccion producto en un
	 * proceso
	 * 
	 * @param codigoProduccion
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fechaCubicacion
	 * @param fechaMesFinal
	 * @param tipoMedioAlmacenamiento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Double[] obtenerIngresosConsumosPostCubicacion(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable, Date fechaCubicacion, boolean esMP) throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = "";

		try {
			StringBuilder hqlBld = new StringBuilder("from Tablakardex tk WHERE ");
			hqlBld.append(" tk.producciondiaria.partediario.lineanegocio.pkCodigoLineanegocio = ?  ");
			hqlBld.append(" AND tk.producciondiaria.partediario.periodocontable.mesPeriodocontable = ?  ");
			hqlBld.append(" AND tk.producciondiaria.partediario.periodocontable.anoPeriodocontable = ?  ");
			if (esMP) {
				hqlBld.append(" AND tk.producciondiaria.producto.pkCodigoProducto = ? ");
			} else {
				hqlBld.append(" AND tk.producciondiaria.ordenproduccion.produccion.producto.pkCodigoProducto = ? ");
			}

			Query queryOP = Querier.query(hqlBld.toString());

			queryOP.setLong(0, codigoLinea);
			queryOP.setShort(1, mesContable);
			queryOP.setInteger(2, anioContable);
			queryOP.setLong(3, codigoProducto);

			Double[] ret = new Double[] { 0d, 0d };
			List<Tablakardex> list = queryOP.list();

			for (Tablakardex tablakardex : list) {
				if (fechaCubicacion.before(tablakardex.getFechaTablakardex())) {
					ret[0] += tablakardex.getIngresoTablakardex();
					ret[1] += tablakardex.getConsumoTablakardex();
				}
			}

			return ret;
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Método para obtener los valores de los conceptos para las componentes del
	 * producto en gestión
	 * 
	 * @param codigoProducto
	 * @param codigoProceso
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fechaMesInicial
	 * @param fechaMesFinal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerConceptosComponentesProducto(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable) {
		String mensajeError = "";

		List<Object[]> kardexConceptosComponentes = new ArrayList<Object[]>();

		try {
			// TODO: eliminar el sum. no se usa
			StringBuilder consultaBld = new StringBuilder(
					"SELECT cpt.componente.pkCodigoComponente, cpt.componente.productoByFkCodigoProductoComponente.nombreProducto,");
			consultaBld
					.append(" SUM(cpt.cantidadConsumopuestotrabajo), cpt.componente.productoByFkCodigoProductoComponente.grupoProducto, cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto ");
			consultaBld.append("FROM Consumopuestotrabajo cpt WHERE ");
			consultaBld
					.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :linea  ");
			consultaBld
					.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes  ");
			consultaBld
					.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio  ");
			consultaBld.append(" AND cpt.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codProducto  ");
			consultaBld.append(" AND cpt.productogenerado.ordenproduccion.mesOrdenproduccion = :mes");
			consultaBld
					.append(" GROUP BY  cpt.componente.pkCodigoComponente,cpt.componente.productoByFkCodigoProductoComponente.nombreProducto, cpt.componente.productoByFkCodigoProductoComponente.grupoProducto, ");
			consultaBld.append(" cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto ");
			consultaBld.append(" ORDER BY  cpt.componente.productoByFkCodigoProductoComponente.nombreProducto ASC");

			Query query = Querier.query(consultaBld.toString());

			query.setLong("linea", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codProducto", codigoProducto);

			kardexConceptosComponentes = query.list();

			// TODO: borrar. prueba dummy
			// kardexConceptosComponentes.add(new Object[] {null, "CATALIZADOR
			// DUMMY", 0, null, 158l} );

		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return kardexConceptosComponentes;
	}

	/**
	 * Método para obtener la produccion al final del mes indicado, por puesto
	 * de trabajo o del mes anterior si se establece de esta forma
	 * 
	 * @param codigoProduccion
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param mesAnterior
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerProduccionPuestoTrabajoMes(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable) {
		String mensajeError = "";
		List<Object[]> produccionPuestoTrabajo = new ArrayList<Object[]>();

		try {
			StringBuilder hql = new StringBuilder("SELECT ");
			hql.append("pgen.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo, ");
			hql.append("pgen.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo, ");
			hql.append("SUM(pgen.produccionTmProductogenerado), ");
			hql.append("SUM(pgen.horasProductogenerado), ");
			hql.append("trpm.tasarealproduccion.minimoTasarealproduccion, ");
			hql.append("trpm.tasarealproduccion.maximoTasarealproduccion, ");
			hql.append("trpm.tasarealproduccion.nominalTasarealproduccion ");
			hql.append("FROM Productogenerado pgen, Tasarealprodregistromensual trpm WHERE ");
			hql.append("pgen.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND pgen.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND pgen.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
			hql.append(" AND pgen.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProdcuto");

			hql.append(" AND trpm.mesTasarealprodregmensual = :mes");
			hql.append(" AND trpm.annoTasarealprodregmensual = :anio");
			hql.append(" AND trpm.tasarealproduccion.puestotrabajo.pkCodigoPuestotrabajo = pgen.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo");
			hql.append(" AND trpm.tasarealproduccion.produccion.pkProduccion = pgen.ordenproduccion.produccion.pkProduccion");

			hql.append(" GROUP BY ");
			hql.append("pgen.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo, ");
			hql.append("pgen.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo, ");
			hql.append("trpm.tasarealproduccion.minimoTasarealproduccion, ");
			hql.append("trpm.tasarealproduccion.maximoTasarealproduccion, ");
			hql.append("trpm.tasarealproduccion.nominalTasarealproduccion ");
			hql.append("ORDER BY pgen.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo");

			Query query = Querier.query(hql.toString());

			query.setLong("lineaNeg", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codigoProdcuto", codigoProducto);

			produccionPuestoTrabajo = query.list();

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

		return produccionPuestoTrabajo;
	}

	@SuppressWarnings("unchecked")
	public static Double obtenerPromedioKcalPuestoTrabMes(Long codigoProducto, Long codigoPuesto, Long codigoLinea,
			Short mesContable, Integer anioContable) {
		String mensajeError = "";
		try {
			StringBuilder hql = new StringBuilder("SELECT pgen.kcalProductogenerado FROM Productogenerado pgen WHERE ");
			hql.append("pgen.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND pgen.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND pgen.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
			hql.append(" AND pgen.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :codPuesto");
			hql.append(" AND pgen.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProdcuto");

			Query query = Querier.query(hql.toString());

			query.setLong("lineaNeg", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codigoProdcuto", codigoProducto);
			query.setLong("codPuesto", codigoPuesto);

			List<Object> list = query.list();
			int cantElem = 0;
			Double total = 0d;

			for (int i = 0; i < list.size(); i++) {
				Double valorKcal = (Double) list.get(i);
				if (valorKcal != null && valorKcal.doubleValue() != 0) {
					total += valorKcal;
					cantElem++;
				}
			}

			if (cantElem == 0) {
				return 0d;
			}

			return total / cantElem;

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
	}

	/**
	 * Obtiene los valores de valor nominal, maximo y minimo rendimiento de la
	 * tasa real de produccion
	 * 
	 * @param codigoProducto
	 * @param codigoPuesto
	 * @param mesContable
	 * @param anioContable
	 * @return Object[] en 0 minimo, en 1 maximo y en 2 valor nominal
	 * @throws EntornoEjecucionException
	 */
	public static Object[] obtenerTasasMaxMinYNominalporPuestoyProduccion(Long codigoProducto, Long codigoPuesto,
			Short mesContable, Integer anioContable) throws EntornoEjecucionException {
		String mensajeError = "";
		try {
			Object[] ret = new Object[3];

			StringBuilder hql = new StringBuilder("SELECT ");
			hql.append("trpm.tasarealproduccion.minimoTasarealproduccion, ");
			hql.append("trpm.tasarealproduccion.maximoTasarealproduccion, ");
			hql.append("trpm.tasarealproduccion.nominalTasarealproduccion ");
			hql.append("FROM Tasarealprodregistromensual trpm WHERE ");
			hql.append("trpm.mesTasarealprodregmensual = :mes");
			hql.append(" AND trpm.annoTasarealprodregmensual = :anio");
			hql.append(" AND trpm.tasarealproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codpuesto");
			hql.append(" AND trpm.tasarealproduccion.produccion.producto.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());

			query.setLong("codpuesto", codigoPuesto);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codProducto", codigoProducto);

			ret = (Object[]) query.uniqueResult();
			return ret;
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
	}

	public static Double[] obtenerProduccionyHoras(Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable, Long codigoPuesto) {
		String mensajeError = "";
		try {
			StringBuilder consultaBld = new StringBuilder("SELECT ");
			consultaBld.append("SUM(pgen.tmPuestotrabajoproduccion + pgen.tmAjustePuestotrabajoproducci), ");
			consultaBld.append("SUM(pgen.hrPuestotrabajoproduccion + pgen.hrAjustePuestotrabajoproducci) ");
			consultaBld.append("FROM Puestotrabajoproduccion pgen  WHERE ");
			consultaBld.append("pgen.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab");
			consultaBld.append(" AND pgen.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			consultaBld.append(" AND pgen.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			consultaBld.append(" AND pgen.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
			consultaBld.append(" AND pgen.ajusteproducto.producto.pkCodigoProducto = :codigoProdcuto");

			Query query = Querier.query(consultaBld.toString());

			query.setLong("codPuestoTrab", codigoPuesto);
			query.setLong("lineaNeg", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codigoProdcuto", codigoProducto);

			Object[] result = (Object[]) query.uniqueResult();

			Double[] array = new Double[] { (Double) result[0], (Double) result[1] };

			return array;

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
	}

	/**
	 * Consumos de componentes para la realización del producto por puesto de
	 * trabajo
	 * 
	 * @param codigoProduccion
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param mesAnterior
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerConsumoComponentePuestoTrabajoMes(Long codigoProducto, Long codigoLinea,
			Short mesContable, Integer anioContable, Date fechaInicial, Date fechaFinal) {
		String mensajeError = "";
		List<Object[]> produccionPuestoTrabajo = new ArrayList<Object[]>();

		try {
			String consulta = "select ppt.puestotrabajo.pkCodigoPuestotrabajo, ppt.puestotrabajo.nombrePuestotrabajo, "
					+ " cpt.componente.pkCodigoComponente, cpt.componente.productoByFkCodigoProductoComponente.nombreProducto,"
					+ " SUM(cpt.cantidadConsumopuestotrabajo), cpt.componente.productoByFkCodigoProductoComponente.grupoProducto, cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto "
					+ "from Produccionpuestotrabajo ppt, Partediario ptd, Tablaoperacion toper, Consumopuestotrabajo cpt, "
					+ "Productogenerado pgen "
					+ " where "
					+ "ptd.lineanegocio.pkCodigoLineanegocio = ?  "
					+ " and ptd.periodocontable.mesPeriodocontable = ?  "
					+ " and ptd.periodocontable.anoPeriodocontable = ?  "
					+ " and ppt.partediario = ptd   "
					+ " and toper.produccionpuestotrabajo = ppt  "
					+ " and pgen.ordenproduccion.produccion.producto.pkCodigoProducto = ?  "
					+ " and pgen.tablaoperacion = toper  "
					+ " and pgen = cpt.productogenerado"
					+ " GROUP BY ppt.puestotrabajo.pkCodigoPuestotrabajo, ppt.puestotrabajo.nombrePuestotrabajo, "
					+ " cpt.componente.pkCodigoComponente, cpt.componente.productoByFkCodigoProductoComponente.nombreProducto, cpt.componente.productoByFkCodigoProductoComponente.grupoProducto, "
					+ " cpt.componente.productoByFkCodigoProductoComponente.pkCodigoProducto "
					+ "ORDER BY cpt.componente.productoByFkCodigoProductoComponente.nombreProducto ASC";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoLinea);
			query.setShort(1, mesContable);
			query.setInteger(2, anioContable);
			query.setLong(3, codigoProducto);

			// TODO: averiguar porque un exception aquí no genera ninguna
			// informacion en logs
			produccionPuestoTrabajo = query.list();

		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		/*
		 * } catch (SessionException e) { mensajeError =
		 * ManejadorPropiedades.obtenerPropiedadPorClave
		 * (ERROR_USO_SESION_INAPROPIADA); logger.error(mensajeError); throw new
		 * EntornoEjecucionException(mensajeError, e); } catch
		 * (JDBCConnectionException e) { mensajeError =
		 * ManejadorPropiedades.obtenerPropiedadPorClave
		 * (ERROR_COMUNICACION_FALLO); logger.error(mensajeError); throw new
		 * EntornoEjecucionException(mensajeError, e); }
		 */

		return produccionPuestoTrabajo;
	}

	/**
	 * Método para obtener los compoenetes y los conceptos bases de sus
	 * movimientos
	 * 
	 * @param codigoProducto
	 * @param codigoProceso
	 * @param codigoLinea
	 * @param mesContable
	 * @param anioContable
	 * @param fecha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Tablakardex> obtenerMovimientosComponente(Long codigoProducto, Long codigoProceso, Long codigoLinea,
			Short mesContable, Integer anioContable, Date fecha) {
		String mensajeError = "";
		List<Tablakardex> movimientosComponente = new ArrayList<Tablakardex>();

		try {

			// ------------ MOVIMIENTO
			// Los componentes tambien son productos
			// Por tanto sus saldos se extraen de la mismas forma
			String consulta = "select tk from Tablakardex tk, Producciondiaria pd, Partediario ptd, Produccion pro, Componente comp "
					+ "where "
					+ "pro.producto.pkCodigoProducto = ? and "
					+ "pro.proceso.pkCodigoProceso = ? and "
					+ "pro.producto.pkCodigoProducto =  comp.productoByFkCodigoProducto.pkCodigoProducto and "
					+ "ptd.lineanegocio.pkCodigoLineanegocio = ? and "
					+ "ptd.periodocontable.mesPeriodocontable = ? and "
					+ "ptd.periodocontable.anoPeriodocontable = ? and "
					+ "pd.partediario = ptd and "
					+ "pd.ordenproduccion.produccion.producto.pkCodigoProducto = comp.productoByFkCodigoProductoComponente.pkCodigoProducto and "
					+ "tk.producciondiaria = pd and " + "tk.fechaTablakardex = ? ";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoProducto);
			query.setLong(1, codigoProceso);
			query.setLong(2, codigoLinea);
			query.setShort(3, mesContable);
			query.setInteger(5, anioContable);
			query.setDate(6, fecha);

			movimientosComponente = query.list();

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

		return movimientosComponente;
	}

	/**
	 * Método para obtener los ajustes de producto
	 * 
	 * @param codigoAjusteProduccion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Ajusteproducto> obtenerAjusteProducto(Long codigoAjusteProduccion) {
		String mensajeError = "";

		List<Ajusteproducto> ajustesProducto = new ArrayList<Ajusteproducto>();

		try {

			String consulta = "from Ajusteproducto aproducto " + "where aproducto.ajusteproduccion.pkCodigoAjusteproduccion = ? ";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoAjusteProduccion);

			ajustesProducto = query.list();

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

		return ajustesProducto;
	}

	/**
	 * @param codigoAjusteProduccion
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws ElementoNoEncontradoException
	 * @throws SesionVencidaException
	 */
	public static Ajusteproduccion obtenerAjusteProduccionPorCodigo(Long codigoAjusteProduccion) throws SesionVencidaException,
			ElementoNoEncontradoException, EntornoEjecucionException {
		Ajusteproduccion ajusteproduccion = null;
		ajusteproduccion = getById(Ajusteproduccion.class, codigoAjusteProduccion);
		return ajusteproduccion;

	}

	/**
	 * @param codigoAjusteProduccion
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void eliminarAjusteProduccionPorCodigo(Ajusteproduccion ajusteproduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(ajusteproduccion);

	}
}
