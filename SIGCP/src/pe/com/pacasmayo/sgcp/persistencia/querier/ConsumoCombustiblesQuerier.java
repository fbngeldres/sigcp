package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.bean.ConsumoCombustibleBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocombustibles;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ConsumoCombustiblesQuerier extends Querier {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/
	private static Log loggerQ = LogFactory.getLog(ConsumoCombustiblesQuerier.class);

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

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
	public static List<Consumocombustibles> getAllOrderBy(String order) {

		return Querier.getAll(Consumocombustibles.class, order);
	}

	/**
	 * Metodo para obtener un Puestotrabajo de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Consumocombustibles getById(Long codigo) throws ElementoNoEncontradoException {

		return (Consumocombustibles) Querier.getById(Consumocombustibles.class, codigo);
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
	public static void save(Consumocombustibles consumocombustibles) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(consumocombustibles);
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
	public static void update(Consumocombustibles consumocombustibles) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(consumocombustibles);
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
	public static void delete(Consumocombustibles consumocombustibles) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(consumocombustibles);
	}

	public static List<ConsumoCombustibleBean> obtenerConsumosCombustibles(Long codigoLineaNegocio, Long codigoUnidad,
			Long codigoSociedad, Short mes, Integer anio, Boolean estadoCombustible) throws EntornoEjecucionException,
			AplicacionException {

		StringBuilder query = new StringBuilder();

		query.append("SELECT new pe.com.pacasmayo.sgcp.bean.impl.ConsumoCombustibleBeanImpl( ");
		query.append("cc.componente.productoByFkCodigoProductoComponente.pkCodigoProducto,");
		query.append("cc.componente.productoByFkCodigoProductoComponente.nombreProducto,");
		query.append("cc.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo,");
		query.append("cc.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo,");
		query.append("SUM(cc.combustiblecalentRealConsumocombustibles),");
		query.append("SUM(cc.combustibleprodRealConsumocombustibles),");
		query.append("cc.componente.productoByFkCodigoProductoComponente.unidadmedida.nombreUnidadmedida)");
		query.append("FROM Consumocombustibles cc ");
		query.append("WHERE ");

		query.append(" cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable =:anio  ");

		if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0) {
			query.append(" AND cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
		}
		if (codigoUnidad != null && codigoUnidad.longValue() > 0) {
			query.append(" AND cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");
		}
		if (mes != null && mes.intValue() != -1) {
			query.append(" AND cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable =:mes  ");
		}

		query.append(" AND		 cc.componente.productoByFkCodigoProductoComponente.estadoFisicoSolidoProducto = :estadoCombustible");
		query.append(" GROUP BY cc.componente.productoByFkCodigoProductoComponente.pkCodigoProducto,");
		query.append(" cc.componente.productoByFkCodigoProductoComponente.nombreProducto,");
		query.append(" cc.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo,");
		query.append(" cc.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo,");
		query.append(" cc.componente.productoByFkCodigoProductoComponente.unidadmedida.nombreUnidadmedida");
		query.append(" ORDER BY cc.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo asc");

		Query query1 = Querier.query(query.toString());

		try {
			if (mes != null && mes.intValue() != -1) {
				query1.setShort("mes", mes);
			}

			query1.setBoolean("estadoCombustible", estadoCombustible);
			query1.setInteger("anio", anio);
			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				query1.setLong("codigoLineaNegocio", codigoLineaNegocio);

			if (codigoUnidad != null && codigoUnidad.longValue() > 0)
				query1.setLong("codigoUnidad", codigoUnidad);

			return query1.list();
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

	public static Double[] obtenerConsumosCombustiblesAcumulado(Long codigoLineaNegocio, Long codigoUnidad, Long codigoSociedad,
			Long codigoDivision, Short mes, Integer anio) throws EntornoEjecucionException, AplicacionException {
		String mensajeError = "";
		try {
			StringBuilder query = new StringBuilder();

			query.append("select SUM(cc.combustibleprodRealConsumocombustibles), SUM(cc.combustiblecalentRealConsumocombustibles) "
					+ " from Consumocombustibles cc "
					+ " where cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes "
					+ " and cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");

			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				query.append(" and cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");

			if (codigoUnidad != null && codigoUnidad.longValue() > 0)
				query.append(" and cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");

			Query query1 = Querier.query(query.toString());

			query1.setShort("mes", mes);
			query1.setInteger("anio", anio);

			if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() > 0)
				query1.setLong("codigoLineaNegocio", codigoLineaNegocio);

			if (codigoUnidad != null && codigoUnidad.longValue() > 0)
				query1.setLong("codigoUnidad", codigoUnidad);

			Object[] result = (Object[]) query1.uniqueResult();
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

	@SuppressWarnings("unchecked")
	public static List<Producto> findCombustiblesPorEstadoFisico(Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, boolean estadoFisicoSolido) throws EntornoEjecucionException {

		List<Producto> productos = new ArrayList<Producto>();
		String mensajeError = "";

		try {

			String consulta = "SELECT DISTINCT cc.componente.productoByFkCodigoProductoComponente "
					+ "FROM Consumocombustibles cc "
					+ "WHERE "
					+ "cc.componente.productoByFkCodigoProductoComponente.estadoFisicoSolidoProducto = ? AND "
					+ "cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.sociedad.division.pkCodigoDivision = ? AND "
					+ "cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.sociedad.pkCodigoSociedad = ? AND "
					+ "cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = ? AND "
					+ "cc.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = ? "

					+ "ORDER BY cc.componente.productoByFkCodigoProductoComponente.nombreProducto ASC";

			Query query = Querier.query(consulta);

			query.setBoolean(0, estadoFisicoSolido);
			query.setLong(1, codigoDivision);
			query.setLong(2, codigoSociedad);
			query.setLong(3, codigoUnidad);
			query.setLong(4, codigoLineaNegocio);

			if (!codigoUnidad.equals(new Long(-1)) && !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(3, codigoUnidad);
				query.setLong(4, codigoLineaNegocio);
			}

			productos = query.list();

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

		return productos;
	}

}
