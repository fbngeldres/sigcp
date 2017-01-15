package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PuestoTrabajoQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponenteajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajuste;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class PuestoTrabajoProduccionQuerier extends Querier implements ConstantesMensajeAplicacion, ConstantesLogicaNegocio {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	public static Puestotrabajoproduccion findByPuestoTrabajo(Long codigoPT) {
		String consulta = "from Puestotrabajoproduccion puestotrabajoproduccion "
				+ "where puestotrabajoproduccion.puestotrabajo = ? ";

		Query query = Querier.query(consulta);

		return (Puestotrabajoproduccion) query.uniqueResult();
	}

	/**
	 * Metodo para obtener la lista de objectos Puestotrabajoproduccion
	 * 
	 * @return
	 */
	public static List<Puestotrabajoproduccion> getAll() throws AplicacionException {

		return Querier.getAll(Puestotrabajoproduccion.class);
	}

	/**
	 * Metodo para obtener la lista de objectos Puestotrabajoproduccion,
	 * ordenados por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Puestotrabajoproduccion> getAllOrderBy(String order) {

		return Querier.getAll(Puestotrabajoproduccion.class, order);
	}

	/**
	 * Metodo para obtener un Puestotrabajo de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Puestotrabajoproduccion getById(Long codigo) throws ElementoNoEncontradoException {

		return (Puestotrabajoproduccion) Querier.getById(Puestotrabajoproduccion.class, codigo);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar un Puesto de Trabajo en la BD.
	 * 
	 * @param puestoTrabajo
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Puestotrabajoproduccion puestoTrabajoProd) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(puestoTrabajoProd);
	}

	/**
	 * Método para modificar un Puesto de Trabajo de la BD.
	 * 
	 * @param puestoTrabajo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Puestotrabajoproduccion puestotrabajoproduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(puestotrabajoproduccion);
	}

	/**
	 * Método para eliminar un Puesto de Trabajo de la BD.
	 * 
	 * @param puestoTrabajo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Puestotrabajoproduccion puestotrabajoproduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(puestotrabajoproduccion);
	}

	/**
	 * Método para obtener los puestos de trabajo utilizados por los componentes
	 * del producto
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @return
	 */
	public static List<Puestotrabajoproduccion> obtenerPuestosTrabajoProducto(Long codigoDivision, Long codigoSociedad,
			Long codigoUnidad, Long codigoProducto, Long codigoLineaNegocio, Short mes, Integer anio) {
		String mensajeError = "";
		List<Puestotrabajoproduccion> puestos = new ArrayList<Puestotrabajoproduccion>();

		try {

			String consultarUnidad = "and ap.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = ? ";
			String consultarLineaNegocio = "and ap.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = ? ";

			String consulta = "select ptp " + " from Ajusteproducto ap, Puestotrabajoproduccion ptp " + "where "
					+ "ap.estadoajusteproducto.nombreEstadoajusteproducto = 'Aprobado' and "
					+ "ap.ajusteproduccion.periodocontable.mesPeriodocontable = ? and "
					+ "ap.ajusteproduccion.periodocontable.anoPeriodocontable = ? and "
					+ "ap.ajusteproduccion.periodocontable.cerradoPeridocontable = ? and "
					+ "ap.producto.pkCodigoProducto = ? and "
					+ "ap.ajusteproduccion.lineanegocio.unidad.sociedad.division.pkCodigoDivision = ? and "
					+ "ap.ajusteproduccion.lineanegocio.unidad.sociedad.pkCodigoSociedad = ? "
					+ (codigoUnidad.equals(new Long(-1)) ? "" : consultarUnidad)
					+ (codigoLineaNegocio.equals(new Long(-1)) ? "" : consultarLineaNegocio) + "and ptp.ajusteproducto = ap";

			Query query = Querier.query(consulta);

			query.setShort(0, mes);
			query.setInteger(1, anio);
			query.setBoolean(2, false);
			query.setLong(3, codigoProducto);
			query.setLong(4, codigoDivision);
			query.setLong(5, codigoSociedad);

			if (!codigoUnidad.equals(new Long(-1)) && !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(6, codigoUnidad);
				query.setLong(7, codigoLineaNegocio);
			}

			if (codigoUnidad.equals(new Long(-1)) && !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(6, codigoLineaNegocio);
			}

			if (!codigoUnidad.equals(new Long(-1)) && codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(6, codigoUnidad);
			}

			puestos = query.list();

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

		return puestos;
	}

	@SuppressWarnings("unchecked")
	public static List<Puestotrabajoproduccion> obtenerPorCodigoAjusteProducto(Long codigoAjusteProducto)
			throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = "";
		try {

			String consulta = "FROM Puestotrabajoproduccion ptp WHERE ptp.ajusteproducto.pkCodigoAjusteproducto = :codAjusteProd";

			Query query = Querier.query(consulta);
			query.setLong("codAjusteProd", codigoAjusteProducto);

			List<Puestotrabajoproduccion> puestos = query.list();
			return puestos;
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

	@SuppressWarnings("unchecked")
	public static List<Consumocomponenteajuste> obtenerConsumoComponentesPorCodigoAjusteProducto(Long codigoAjusteProducto)
			throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = "";
		try {

			String consulta = "FROM Consumocomponenteajuste c WHERE c.puestotrabajoproduccion.ajusteproducto.pkCodigoAjusteproducto = :codAjusteProd";

			Query query = Querier.query(consulta);
			query.setLong("codAjusteProd", codigoAjusteProducto);

			List<Consumocomponenteajuste> consumos = query.list();
			return consumos;
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

	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerComponentesConConsumo(Long codigoAjusteProducto) throws SesionVencidaException,
			EntornoEjecucionException, ElementoNoEncontradoException {
		String mensajeError = "";
		try {

			String consulta = "select c.componente.pkCodigoComponente, c.componente.productoByFkCodigoProductoComponente FROM Consumocomponenteajuste c WHERE c.puestotrabajoproduccion.ajusteproducto.pkCodigoAjusteproducto = :codAjusteProd";

			Query query = Querier.query(consulta);
			query.setLong("codAjusteProd", codigoAjusteProducto);

			List<Object[]> productos = query.list();

			return productos;
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static List<Movimientoajuste> obtenerMovimientoAjustesPorCodigoAjusteProducto(Long codigoAjusteProducto) {
		String mensajeError = "";
		try {

			String consulta = "FROM Movimientoajuste m WHERE m.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.pkCodigoAjusteproducto = :codAjusteProd";

			Query query = Querier.query(consulta);
			query.setLong("codAjusteProd", codigoAjusteProducto);

			List<Movimientoajuste> movimientos = query.list();
			return movimientos;
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

	public static List<Movimientoajusteproducto> obtenerMovimientoAjustesProductoPorCodigoAjusteProducto(Long codigoAjusteProducto) {
		String mensajeError = "";
		try {

			String consulta = "FROM Movimientoajusteproducto m WHERE m.puestotrabajoproduccion.ajusteproducto.pkCodigoAjusteproducto = :codAjusteProd";

			Query query = Querier.query(consulta);
			query.setLong("codAjusteProd", codigoAjusteProducto);

			List<Movimientoajusteproducto> movimientos = query.list();
			return movimientos;
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

	@SuppressWarnings("unchecked")
	public static List<Puestotrabajoproduccion> obtenerPorCodigoAjusteProduccion(Long codigoAjusteProduccion)
			throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = "";
		try {

			String consulta = "FROM Puestotrabajoproduccion ptp WHERE "
					+ "ptp.ajusteproducto.ajusteproduccion.pkCodigoAjusteproduccion = :codAjusteProd";

			Query query = Querier.query(consulta);
			query.setLong("codAjusteProd", codigoAjusteProduccion);

			List<Puestotrabajoproduccion> puestosProduccion = query.list();
			return puestosProduccion;
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
	 * Agregado por Fabian Método para obtener los puestos de trabajo utilizados
	 * por los componentes del producto
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @return
	 */
	public static List<Puestotrabajoproduccion> obtenerPuestosTrabajoProducto(Long codigoUnidad, Long codigoProducto,
			Long codigoLineaNegocio, Short mes, Integer anio, Long codigoEstado) {
		logger.debug("SE EJECUTO EL METODO obtenerPuestosTrabajoProducto");
		String mensajeError = "";
		List<Puestotrabajoproduccion> puestos = new ArrayList<Puestotrabajoproduccion>();

		try {

			String consultarUnidad = " and ap.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :unidad ";
			String consultarLineaNegocio = " and ap.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :lineaNeg ";

			String consulta = "select ptp " + " from Ajusteproducto ap, Puestotrabajoproduccion ptp " + "where "
					+ "ap.ajusteproduccion.periodocontable.mesPeriodocontable = :mes and "
					+ "ap.ajusteproduccion.periodocontable.anoPeriodocontable = :anno and "
					+ "ap.ajusteproduccion.periodocontable.cerradoPeridocontable = :cerrado and "
					+ "ap.producto.pkCodigoProducto = :producto AND ptp.ajusteproducto = ap "
					+ (codigoUnidad.equals(new Long(-1)) ? "" : consultarUnidad)
					+ (codigoLineaNegocio.equals(new Long(-1)) ? "" : consultarLineaNegocio);

			if (codigoEstado != null) {
				consulta += " AND ap.estadoajusteproducto.nombreEstadoajusteproducto = :nombreAjuste ";
			}

			Query query = Querier.query(consulta);

			query.setShort("mes", mes);
			query.setInteger("anno", anio);
			query.setBoolean("cerrado", false);
			query.setLong("producto", codigoProducto);

			if (codigoEstado != null) {
				query.setString("nombreAjuste", EstadoAjusteProductoQuerier.getById(codigoEstado).getNombreEstadoajusteproducto());
			}

			if (!codigoUnidad.equals(new Long(-1)) && !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong("unidad", codigoUnidad);
				query.setLong("lineaNeg", codigoLineaNegocio);
			}

			if (codigoUnidad.equals(new Long(-1)) && !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong("lineaNeg", codigoLineaNegocio);
			}

			if (!codigoUnidad.equals(new Long(-1)) && codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong("unidad", codigoUnidad);
			}

			puestos = query.list();

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
		} catch (ElementoNoEncontradoException e) {
			mensajeError = e.getMensaje();
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return puestos;
	}

	/**
	 * Agregado por Fabian
	 */

	@SuppressWarnings("unchecked")
	public static Double obtenerAjustePTrabajoProducnPorIdAjusteProducto(Long producto, Long lineaNegocio, Short mes, Integer anio)
			throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = "";
		try {
			StringBuilder consulta = new StringBuilder(
					"SELECT SUM(ptp.tmAjustePuestotrabajoproducci) FROM Puestotrabajoproduccion ptp ");
			consulta.append(" WHERE ");
			consulta.append("  ptp.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");
			if (mes != null) {
				consulta.append(" AND ptp.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes ");
			}

			consulta.append(" AND ptp.ajusteproducto.producto.pkCodigoProducto = :codigoProducto ");
			if (lineaNegocio != null && lineaNegocio.longValue() != -1L) {
				consulta.append(" AND ptp.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :lineanegocio ");
			}

			Query query = Querier.query(consulta.toString());
			query.setLong("codigoProducto", producto);
			if (lineaNegocio != null && lineaNegocio.longValue() != -1L) {

				query.setLong("lineanegocio", lineaNegocio);
			}
			if (mes != null) {
				query.setShort("mes", mes);
			}
			query.setInteger("anio", anio);

			return (query.uniqueResult() != null) ? (Double) query.uniqueResult() : 0.0d;
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

	public static Double obtenerAjusteAcumuladoPTrabajo(Long producto, Long lineaNegocio, Short mes, Integer anio)
			throws SesionVencidaException, EntornoEjecucionException {
		String mensajeError = "";
		try {
			StringBuilder consulta = new StringBuilder(
					"SELECT SUM(ptp.tmAjustePuestotrabajoproducci) FROM Puestotrabajoproduccion ptp ");
			consulta.append(" WHERE ");
			consulta.append("  ptp.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");
			if (mes != null) {
				consulta.append(" AND ptp.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes ");
			}

			consulta.append(" AND ptp.ajusteproducto.producto.pkCodigoProducto = :codigoProducto ");
			if (lineaNegocio != null && lineaNegocio.longValue() != -1L) {
				consulta.append(" AND ptp.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :lineanegocio ");
			}

			Query query = Querier.query(consulta.toString());
			query.setLong("codigoProducto", producto);
			if (lineaNegocio != null && lineaNegocio.longValue() != -1L) {

				query.setLong("lineanegocio", lineaNegocio);
			}
			if (mes != null) {
				query.setShort("mes", mes);
			}
			query.setInteger("anio", anio);

			return (query.uniqueResult() != null) ? (Double) query.uniqueResult() : 0.0d;
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

	// Registro Distribuible - Carga Automatica

	public static Double getTmRealPuestoTrabajoProduccion(Short mes, Integer anio, Long producto, Long puestoTrabajo) {

		String consulta = "SELECT SUM(ppt.tmRealPuestotrabajoproduccion+tmAjustePuestotrabajoproducci) FROM "
				+ " Puestotrabajoproduccion ppt" + " where ppt.puestotrabajo.pkCodigoPuestotrabajo = :puestoTrabajo"
				+ " AND ppt.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND ppt.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND ppt.ajusteproducto.producto.pkCodigoProducto= :producto";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setLong("producto", producto);
		query.setLong("puestoTrabajo", puestoTrabajo);
		Double valor = (Double) query.uniqueResult();
		if (valor == null) {
			valor = 0.0;
		}
		return valor;
	}

	public static Double getTmRealPuestoTrabajoProduccionporProducto(Short mes, Integer anio, Long producto) {

		String consulta = "SELECT SUM(ppt.tmRealPuestotrabajoproduccion+tmAjustePuestotrabajoproducci) FROM "
				+ " Puestotrabajoproduccion ppt"
				+ " where ppt.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND ppt.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND ppt.ajusteproducto.producto.pkCodigoProducto= :producto";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setLong("producto", producto);
		Double valor = (Double) query.uniqueResult();
		if (valor == null) {
			valor = 0.0;
		}
		return valor;
	}

	public static Puestotrabajoproduccion obtenerPorPeriodoContableProductoPuestoTrabajo(Long codigoLineaNegocio,
			Long codigoProducto, Long codigoPuestoTrabajo, Short mes, Integer anio) {
		String mensajeError = "";
		Puestotrabajoproduccion puestos = null;

		try {

			StringBuilder consulta = new StringBuilder("FROM Puestotrabajoproduccion ptp");
			consulta.append(" WHERE");

			consulta.append(" ptp.ajusteproducto.estadoajusteproducto.nombreEstadoajusteproducto = 'Aprobado'");
			consulta.append(" AND ptp.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable < :mes");
			consulta.append(" AND ptp.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable <= :anno");
			consulta.append(" AND ptp.ajusteproducto.producto.pkCodigoProducto= :producto");
			consulta.append(" AND ptp.puestotrabajo.pkCodigoPuestotrabajo= :puestotrabajo");
			consulta.append(" AND ptp.tmphRealPuestotrabajoproducci > 0");
			if (!codigoLineaNegocio.equals(Long.valueOf(-1))) {
				consulta.append(" AND ptp.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :lineanegocio ");
			}
			consulta.append(" ORDER BY ptp.ajusteproducto.ajusteproduccion.periodocontable.pkCodigoPeridocontable  DESC");

			Query query = Querier.query(consulta.toString());

			query.setMaxResults(1);

			query.setInteger("anno", anio);
			query.setInteger("mes", mes);
			query.setLong("producto", codigoProducto);
			query.setLong("puestotrabajo", codigoPuestoTrabajo);
			if (!codigoLineaNegocio.equals(Long.valueOf(-1))) {
				query.setLong("lineanegocio", codigoLineaNegocio);
			}

			puestos = (Puestotrabajoproduccion) query.uniqueResult();

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

		return puestos;
	}
}
