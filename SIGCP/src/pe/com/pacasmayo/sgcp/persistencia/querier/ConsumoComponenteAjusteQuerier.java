package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.bean.AprobarAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponenteajuste;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ConsumoComponenteAjusteQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static Log loggerQ = LogFactory.getLog(ConsumoComponenteAjusteQuerier.class);

	/**
	 * Método para obtener la lista de objectos Consumocomponenteajuste
	 * 
	 * @return
	 */
	public static List<Consumocomponenteajuste> getAll() {

		return Querier.getAll(Consumocomponenteajuste.class);
	}

	/**
	 * Método para obtener una Consumocomponenteajuste de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Consumocomponenteajuste getById(Long codigo) throws ElementoNoEncontradoException {

		return (Consumocomponenteajuste) Querier.getById(Consumocomponenteajuste.class, codigo);
	}

	public static List<Consumocomponenteajuste> obtenerPorVarios(Long codigoLineaNegocio, Long codigoUnidad, Long codigoSociedad,
			Long codigoDivision, Long codigoProducto, Integer anio, Short mes, String nombreGrupo, String estAjusteProducto,
			String estAjuProduccion) throws EntornoEjecucionException, AplicacionException {
		String queryString = construirQueryNotificacionesCantera(codigoLineaNegocio, codigoUnidad);

		Query query = null;
		try {
			query = Querier.query(queryString);
		} catch (EntornoEjecucionException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), e);
		}
		try {
			query.setString("estAjusteProducto", estAjusteProducto);
			query.setString("estAjuProduccion", estAjuProduccion);
			query.setShort("mes", mes);
			query.setInteger("anio", anio);
			query.setBoolean("periodoCerrado", false);
			query.setLong("codigoSociedad", codigoSociedad);
			query.setLong("codigoDivision", codigoDivision);
			query.setString("nombreGrupo", nombreGrupo);
			query.setLong("codigoProducto", codigoProducto);

			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				query.setLong("codigoLineaNegocio", codigoLineaNegocio);

			if (codigoUnidad != null && codigoUnidad.longValue() > 0)
				query.setLong("codigoUnidad", codigoUnidad);

			return (List<Consumocomponenteajuste>) query.list();
		} catch (UnresolvableObjectException uOException) {
			loggerQ.error(uOException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO), uOException);
		} catch (ObjectNotFoundException oNFException) {
			loggerQ.error(oNFException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO), oNFException);
		} catch (NonUniqueResultException hException) {
			loggerQ.error(hException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT),
					hException);
		} catch (HibernateException hException) {
			loggerQ.error(hException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException);
		}

	}

	private static String construirQueryNotificacionesCantera(Long codigoLineaNegocio, Long codigoUnidad) {

		StringBuilder query = new StringBuilder();

		query.append("select cca"
				+ " from Consumocomponenteajuste cca"
				+ " where lower(cca.puestotrabajoproduccion.ajusteproducto.estadoajusteproducto.nombreEstadoajusteproducto) = lower(:estAjusteProducto)"
				+ " and lower(cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.estadoajusteproduccion.nombreEstadoajusteproduccion) = lower(:estAjuProduccion)"
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.cerradoPeridocontable = :periodoCerrado"
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.sociedad.pkCodigoSociedad = :codigoSociedad"
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :codigoDivision"
				+ " and lower(cca.puestotrabajoproduccion.ajusteproducto.plantillagrupoajuste.nombrePlantillagrupoajuste) = lower(:nombreGrupo)"
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");
		// FIXME : puestotrabajoproduccion.ajusteproducto. Actualmente
		// referencia a producto no a orden produccion
		if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
			query.append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");

		if (codigoUnidad != null && codigoUnidad.longValue() > 0)
			query.append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");

		return query.toString();
	}

	/**
	 * Este método obtiene los consumos asociados a los productos de un Grupo
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoUnidad
	 * @param codigoSociedad
	 * @param codigoDivision
	 * @param codigoProducto
	 * @param anio
	 * @param mes
	 * @param nombreGrupo
	 * @param estAjusteProducto
	 * @param estAjuProduccion
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws AplicacionException
	 */
	public static List<Consumocomponenteajuste> obtenerConsumosPorNombreGrupo(Long codigoLineaNegocio, Long codigoUnidad,
			Long codigoSociedad, Long codigoDivision, List<Long> codigoProducto, Integer anio, Short mes, String nombreGrupo)
			throws EntornoEjecucionException, AplicacionException {

		String queryString = construirQueryNotificacionesCanteraProductos(codigoLineaNegocio, codigoUnidad, codigoProducto);

		Query query = null;
		try {
			query = Querier.query(queryString);
		} catch (EntornoEjecucionException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), e);
		}
		try {

			query.setShort("mes", mes);
			query.setInteger("anio", anio);
			query.setBoolean("periodoCerrado", false);
			query.setLong("codigoSociedad", codigoSociedad);
			query.setLong("codigoDivision", codigoDivision);
			query.setString("nombreGrupo", nombreGrupo);

			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				query.setLong("codigoLineaNegocio", codigoLineaNegocio);

			if (codigoUnidad != null && codigoUnidad.longValue() > 0)
				query.setLong("codigoUnidad", codigoUnidad);

			return query.list();
		} catch (UnresolvableObjectException uOException) {
			loggerQ.error(uOException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO), uOException);
		} catch (ObjectNotFoundException oNFException) {
			loggerQ.error(oNFException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO), oNFException);
		} catch (NonUniqueResultException hException) {
			loggerQ.error(hException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT),
					hException);
		} catch (HibernateException hException) {
			loggerQ.error(hException);
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException);
		}

	}

	/**
	 * Este método devuelve el Query HQL que se usará para obtener los consumos
	 * de los materiales de un Grupo
	 * 
	 * @param codigoLineaNegocio
	 * @param codigoUnidad
	 * @param codigosProductos
	 * @return
	 */
	private static String construirQueryNotificacionesCanteraProductos(Long codigoLineaNegocio, Long codigoUnidad,
			List<Long> codigosProductos) {

		StringBuilder query = new StringBuilder();

		query.append("select cca"
				+ " from Consumocomponenteajuste cca"
				+ " where "
				+ "  cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes "
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio "
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.cerradoPeridocontable = :periodoCerrado "
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.sociedad.pkCodigoSociedad = :codigoSociedad "
				+ " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :codigoDivision ");
		query.append(" and 	 lower(cca.puestotrabajoproduccion.ajusteproducto.plantillagrupoajuste.nombrePlantillagrupoajuste)	 = lower(:nombreGrupo) ");

		if (codigosProductos.size() > 1) {
			String codigos = "";
			for (int i = 0; i < codigosProductos.size(); i++) {
				codigos += codigosProductos.get(i).toString() + " , ";
			}

			codigos = codigos.substring(0, codigos.length() - 3);

			/*
			 * Fabian Geldres ajusteproducto.ordenproduccion.procuccion Elimine
			 * esa referencia debido a que Actualmente en la BD esa relacion no
			 * existe
			 */
			query.append(" and cca.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto in (" + codigos + ")");
		} else if (codigosProductos.size() == 1) {
			query.append(" and cca.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto = :codigoProducto");
		}
		if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
			query.append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");

		if (codigoUnidad != null && codigoUnidad.longValue() > 0)
			query.append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");

		query.append(" order by  cca.componente.productoByFkCodigoProductoComponente.nombreProducto asc");

		return query.toString();
	}

	// public static Double obtenerConsumosCombustiblesAcumulado(Long
	// codigoLineaNegocio, Long codigoUnidad, Long codigoSociedad,
	// Long codigoDivision, Long codigoProducto, Short mes, Integer anio,
	// Boolean esCalentamiento)
	// throws EntornoEjecucionException, AplicacionException {
	//
	// String queryString =
	// construirQueryNotificacionesCanteraProductosCombustiblesAcumulado(codigoLineaNegocio,
	// codigoUnidad,
	// codigoProducto);
	//
	// Query query = null;
	// try {
	// query = Querier.query(queryString);
	// } catch (EntornoEjecucionException e) {
	// throw e;
	// } catch (RuntimeException e) {
	// throw new
	// EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO),
	// e);
	// }
	// try {
	// query.setShort("mes", mes);
	// query.setInteger("anio", anio);
	// query.setLong("codigoProducto", codigoProducto);
	// query.setBoolean("esCalentamiento", esCalentamiento);
	//
	// if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
	// query.setLong("codigoLineaNegocio", codigoLineaNegocio);
	//
	// if (codigoUnidad != null && codigoUnidad.longValue() > 0)
	// query.setLong("codigoUnidad", codigoUnidad);
	//
	// return (Double) query.uniqueResult();
	// } catch (UnresolvableObjectException uOException) {
	// loggerQ.error(uOException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
	// uOException);
	// } catch (ObjectNotFoundException oNFException) {
	// loggerQ.error(oNFException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
	// oNFException);
	// } catch (NonUniqueResultException hException) {
	// loggerQ.error(hException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT),
	// hException);
	// } catch (HibernateException hException) {
	// loggerQ.error(hException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE),
	// hException);
	// }
	// }

	/**
	 * @param codigoLineaNegocio
	 * @param codigoUnidad
	 * @param codigoSociedad
	 * @param codigoDivision
	 * @param codigoProducto
	 * @param mes
	 * @param anio
	 * @param estAjusteProducto
	 * @param estAjuProduccion
	 * @param esCalentamiento
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws AplicacionException
	 */

	// public static List<Consumocomponenteajuste>
	// obtenerConsumosCombustibles(Long codigoLineaNegocio, Long codigoUnidad,
	// Long codigoSociedad, Long codigoDivision, Long codigoProducto, Short mes,
	// Integer anio, Boolean esCalentamiento)
	// throws EntornoEjecucionException, AplicacionException {
	//
	// // String queryString =
	// construirQueryNotificacionesCanteraProductosCombustibles(codigoLineaNegocio,
	// codigoUnidad,
	// // codigoProducto);
	//
	// Query query = null;
	// try {
	// // query = Querier.query(queryString);
	// } catch (EntornoEjecucionException e) {
	// throw e;
	// } catch (RuntimeException e) {
	// throw new
	// EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO),
	// e);
	// }
	// try {
	//
	// query.setShort("mes", mes);
	// query.setInteger("anio", anio);
	// query.setLong("codigoProducto", codigoProducto);
	// query.setBoolean("esCalentamiento", esCalentamiento);
	//
	// if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
	// query.setLong("codigoLineaNegocio", codigoLineaNegocio);
	//
	// if (codigoUnidad != null && codigoUnidad.longValue() > 0)
	// query.setLong("codigoUnidad", codigoUnidad);
	//
	// return query.list();
	// } catch (UnresolvableObjectException uOException) {
	// loggerQ.error(uOException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
	// uOException);
	// } catch (ObjectNotFoundException oNFException) {
	// loggerQ.error(oNFException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
	// oNFException);
	// } catch (NonUniqueResultException hException) {
	// loggerQ.error(hException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT),
	// hException);
	// } catch (HibernateException hException) {
	// loggerQ.error(hException);
	// throw new
	// AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE),
	// hException);
	// }
	// }

	// private static String
	// construirQueryNotificacionesCanteraProductosCombustibles(Long
	// codigoLineaNegocio, Long codigoUnidad,
	// Long codigoProducto) {
	//
	// StringBuilder query = new StringBuilder();
	//
	// query
	// .append("select cca"
	// + " from Consumocomponenteajuste cca "
	// +
	// " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes "
	// +
	// " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio "
	// +
	// " and cca.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo in (select npro.puestotrabajo.pkCodigoPuestotrabajo from Notificacionproduccion npro where npro.escalentamientoNotificacionpr = :esCalentamiento ) ");
	//
	// query.append(" and cca.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codigoProducto");
	//
	// if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
	// query
	// .append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
	//
	// if (codigoUnidad != null && codigoUnidad.longValue() > 0)
	// query
	// .append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");
	//
	// query.append(" order by cca.puestotrabajoproduccion.puestotrabajo.siglasPuestotrabajo asc");
	//
	// return query.toString();
	// }

	// private static String
	// construirQueryNotificacionesCanteraProductosCombustiblesAcumulado(Long
	// codigoLineaNegocio,
	// Long codigoUnidad, Long codigoProducto) {
	//
	// StringBuilder query = new StringBuilder();
	//
	// query
	// .append("select SUM(cca.tmRealConsumocomponenteajuste) "
	// + " from Consumocomponenteajuste cca "
	// +
	// " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes "
	// +
	// " and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio "
	// +
	// " and cca.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo in (select npro.puestotrabajo.pkCodigoPuestotrabajo from Notificacionproduccion npro where npro.escalentamientoNotificacionpr = :esCalentamiento  ) ");
	//
	// query.append(" and cca.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codigoProducto");
	//
	// if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
	// query
	// .append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
	//
	// if (codigoUnidad != null && codigoUnidad.longValue() > 0)
	// query
	// .append(" and cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");
	//
	// return query.toString();
	// }

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar una Consumocomponenteajuste en la BD.
	 * 
	 * @param consumocomponenteajuste
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Consumocomponenteajuste consumocomponenteajuste) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(consumocomponenteajuste);
	}

	/**
	 * Metodo para modificar una Consumocomponenteajuste de la BD.
	 * 
	 * @param consumocomponenteajuste
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Consumocomponenteajuste consumocomponenteajuste) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(consumocomponenteajuste);
	}

	/**
	 * Método para eliminar una Consumocomponenteajuste de la BD.
	 * 
	 * @param consumocomponenteajuste
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Consumocomponenteajuste consumocomponenteajuste) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(consumocomponenteajuste);
	}

	/**
	 * Agregado Por Fabian
	 */

	public static HashMap<Long, Double> obtenerConsumoComponenteProducto(List<Long> codigosComponentes, Long codigoProducto,
			Integer anio, Short mes) {

		HashMap<Long, Double> mapa = new HashMap<Long, Double>();
		StringBuilder queryString = new StringBuilder(
				"SELECT c.componente.productoByFkCodigoProductoComponente.pkCodigoProducto,SUM(c.tmRealConsumocomponenteajuste) FROM Consumocomponenteajuste c WHERE c.componente.productoByFkCodigoProductoComponente.pkCodigoProducto in (:codigosComponentes)");
		queryString
				.append(" AND c.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
		queryString
				.append(" AND c.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
		queryString.append(" AND c.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto = :codigoProducto");
		queryString.append(" GROUP BY c.componente.productoByFkCodigoProductoComponente.pkCodigoProducto");
		Query query = Querier.query(queryString.toString());
		query.setParameterList("codigosComponentes", codigosComponentes);
		query.setLong("codigoProducto", codigoProducto);
		query.setInteger("anio", anio);
		query.setShort("mes", mes);
		List<Object[]> lista = query.list();

		if (lista != null) {
			for (Object[] objects : lista) {
				mapa.put((Long) objects[0], (Double) objects[1]);
			}
		}
		return mapa;
	}

	// Registro Distribuible - Carga Automatica
	public static Double getSumaConsumoComponentexPuestoTrabajo(Short mes, Integer anio, List<Long> componentes,
			Long puestoTrabajo) {

		String consulta = "SELECT cca.tmRealConsumocomponenteajuste FROM " + "Consumocomponenteajuste cca"
				+ " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND cca.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo = :puestoTrabajo"
				+ " AND cca.componente.pkCodigoComponente in (:componente)";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setParameterList("componente", componentes);
		query.setLong("puestoTrabajo", puestoTrabajo);
		Double cantidad = (Double) query.uniqueResult();
		if (cantidad == null) {
			cantidad = 0.0;
		}
		return cantidad;
	}

	public static Double getTmRealConsumoComponenteAjuste(Short mes, Integer anio, Long producto, Long puestoTrabajo) {

		String consulta = "SELECT SUM(cca.tmRealConsumocomponenteajuste) FROM " + "Consumocomponenteajuste cca"
				+ " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND cca.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo = :puestoTrabajo"
				+ " AND cca.componente.productoByFkCodigoProducto.pkCodigoProducto in (:producto)";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setLong("producto", producto);
		query.setLong("puestoTrabajo", puestoTrabajo);
		Double cantidad = (Double) query.uniqueResult();
		if (cantidad == null) {
			cantidad = 0.0;
		}
		return cantidad;
	}

	public static Double getSumaConsumoComponentes(Short mes, Integer anio, List<Long> componentes) {

		String consulta = "SELECT SUM(cca.tmRealConsumocomponenteajuste) FROM " + "Consumocomponenteajuste cca"
				+ " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND cca.componente.pkCodigoComponente in (:componente)";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setParameterList("componente", componentes);
		Double cantidad = (Double) query.uniqueResult();
		if (cantidad == null) {
			cantidad = 0.0;
		}
		return cantidad;
	}

	// Tabla Valor
	/**
	 * Obtengo la suma de TM Real Consumo de un producto
	 * 
	 * @param producto
	 * @return
	 */
	public static List<Object[]> getConsumoComponentePorProducto(Short mes, Integer anio, Long producto) {
		String consulta = "SELECT cca.componente.productoByFkCodigoProductoComponente.pkCodigoProducto  ,SUM(cca.tmRealConsumocomponenteajuste) FROM Consumocomponenteajuste cca "
				+ " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND cca.componente.productoByFkCodigoProducto.pkCodigoProducto in (:producto)  "
				+ " GROUP BY cca.componente.productoByFkCodigoProductoComponente.pkCodigoProducto ";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setLong("producto", producto);
		List<Object[]> objetos = query.list();

		return objetos;
	}

	public static List<Object[]> getConsumoProductoComponentePorProducto(Short mes, Integer anio, Long producto) {

		String consulta = "SELECT cca.componente.productoByFkCodigoProducto.pkCodigoProducto ,SUM(cca.tmRealConsumocomponenteajuste) "
				+ "FROM Consumocomponenteajuste cca "
				+ " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND cca.componente.productoByFkCodigoProductoComponente.pkCodigoProducto in (:producto)  "
				+ " GROUP BY cca.componente.productoByFkCodigoProducto.pkCodigoProducto ";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setLong("producto", producto);
		List<Object[]> objetos = query.list();

		return objetos;
	}

	public static List<AprobarAjusteProduccionBean> obtenerPorAjusteProduccion(Long codigoAjusteProduccion)
			throws EntornoEjecucionException, AplicacionException {
		try {
			StringBuffer queryStr = new StringBuffer(
					"SELECT new pe.com.pacasmayo.sgcp.bean.impl.AprobarAjusteProduccionBeanImpl(");
			queryStr.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto,");
			queryStr.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.producto.nombreProducto,");
			queryStr.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.producto.codigoSapProducto,");
			queryStr.append(" ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto,");
			queryStr.append(" ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.nombreProducto,");
			queryStr.append(" ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.codigoSapProducto,");
			queryStr.append(" sum(ma.cantidadMovimientoajuste))");
			queryStr.append(" FROM  Movimientoajuste ma ");
			queryStr.append(" WHERE");

			queryStr.append("  ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.pkCodigoAjusteproduccion = :codAjusteProduccion");
			queryStr.append(" GROUP BY ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto,");
			queryStr.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.producto.nombreProducto,");
			queryStr.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.producto.codigoSapProducto,");
			queryStr.append(" ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto, ");
			queryStr.append(" ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.nombreProducto,");
			queryStr.append(" ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.codigoSapProducto");
			queryStr.append(" ORDER BY 1");

			Query query = Querier.query(queryStr.toString());
			query.setLong("codAjusteProduccion", codigoAjusteProduccion);

			return (List<AprobarAjusteProduccionBean>) query.list();
		} catch (EntornoEjecucionException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), e);
		}

	}

	public static List<AprobarAjusteProduccionBean> obtenerConsumoAjuste(Long codigoAjusteProduccion, String codProductoSap)
			throws EntornoEjecucionException, AplicacionException {

		try {
			StringBuffer queryStr = new StringBuffer(
					"SELECT new pe.com.pacasmayo.sgcp.bean.impl.AprobarAjusteProduccionBeanImpl(");
			queryStr.append(" cca.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo,");
			queryStr.append(" cca.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo,");
			queryStr.append(" cca.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto,");
			queryStr.append(" cca.puestotrabajoproduccion.ajusteproducto.producto.nombreProducto,");
			queryStr.append(" cca.puestotrabajoproduccion.ajusteproducto.producto.codigoSapProducto,");
			queryStr.append(" sum(cca.tmProdConsumocomponenteajus+diferenciaConsumocomponenteaju),");
			queryStr.append(" sum(cca.porcentCarbonConsucompajuste))");
			queryStr.append(" FROM  Consumocomponenteajuste cca");
			queryStr.append(" WHERE");
			queryStr.append(" cca.puestotrabajoproduccion.ajusteproducto.producto.codigoSapProducto like :codProSap");
			queryStr.append(" AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.pkCodigoAjusteproduccion = :codAjusteProduccion");
			queryStr.append(" GROUP BY ");
			queryStr.append(" cca.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo,");
			queryStr.append(" cca.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo,");
			queryStr.append(" cca.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto,");
			queryStr.append(" cca.puestotrabajoproduccion.ajusteproducto.producto.nombreProducto,");
			queryStr.append(" cca.puestotrabajoproduccion.ajusteproducto.producto.codigoSapProducto");
			queryStr.append(" ORDER BY 1");

			Query query = Querier.query(queryStr.toString());
			query.setLong("codAjusteProduccion", codigoAjusteProduccion);
			query.setString("codProSap", codProductoSap + "%");

			return (List<AprobarAjusteProduccionBean>) query.list();
		} catch (EntornoEjecucionException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), e);
		}

	}

	public static List<Consumocomponenteajuste> obtenerPorAjusteProducto(Long codigoProducto, Long codigoLinea, int anio,
			Short mes) throws EntornoEjecucionException, AplicacionException {

		try {

			StringBuilder sql = new StringBuilder(" FROM Consumocomponenteajuste ccp ");
			sql.append(" WHERE");
			sql.append(" ccp.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto= :codigoProducto");
			sql.append(" AND  ccp.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable= :anio");
			sql.append(" AND  ccp.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable= :mes ");
			sql.append(" AND  ccp.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio= :linea ");
			sql.append(" ORDER BY ccp.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo ASC ");

			Query query = Querier.query(sql.toString());
			query.setLong("codigoProducto", codigoProducto);
			query.setInteger("anio", anio);
			query.setShort("mes", mes);
			query.setLong("linea", codigoLinea);

			return (List<Consumocomponenteajuste>) query.list();

		} catch (EntornoEjecucionException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), e);
		}

	}

	public static List<Consumocomponenteajuste> obtenerByProductoPuestoTrabajoComponente(Long codigoProductoAjuste,
			Long codigoLinea, int anio, Short mes, Long codigoProductoComponente, Long codigoPuestoTrabajo)
			throws EntornoEjecucionException, AplicacionException {

		try {

			StringBuilder sql = new StringBuilder(" FROM Consumocomponenteajuste ccp ");
			sql.append(" WHERE");
			sql.append(" ccp.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto= :codigoProducto ");
			sql.append("AND ccp.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable=:anio ");
			sql.append("AND ccp.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable=:mes ");
			sql.append("AND ccp.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio=:linea ");
			sql.append("AND ccp.componente.productoByFkCodigoProducto.pkCodigoProducto = :codigoProductoComponente ");
			sql.append("AND ccp.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo ");
			Query query = Querier.query(sql.toString());
			query.setLong("codigoProducto", codigoProductoAjuste);
			query.setInteger("anio", anio);
			query.setShort("mes", mes);
			query.setLong("linea", codigoLinea);
			query.setLong("codigoProductoComponente", codigoProductoComponente);
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);

			return (List<Consumocomponenteajuste>) query.list();

		} catch (EntornoEjecucionException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new EntornoEjecucionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO), e);
		}

	}

}
