package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.JDBCConnectionException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.QueryException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.SessionException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class AjusteProductoQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static Logger log = Logger.getLogger(AjusteProductoQuerier.class);

	public static String CODIGO_LINEA_NEGOCIO_AJUSTE_PRODUCTO = "ajusteproduccion.lineanegocio.pkCodigoLineanegocio";
	public static String ANNO_CONTABLE_AJUSTE_PRODUCTO = "ajusteproduccion.periodocontable.anoPeriodocontable";
	public static String MES_CONTABLE_AJUSTE_PRODUCTO = "ajusteproduccion.periodocontable.mesPeriodocontable";
	public static String CODIGO_ESTADO_AJUSTE_PRODUCTO = "ajusteproduccion.estadoajusteproduccion.pkCodigoEstadoajusteproduccio";

	/**
	 * Método para obtener la lista de objectos ajuste de produccion
	 * 
	 * @return
	 */
	public static List<Ajusteproducto> getAll() {

		return Querier.getAll(Ajusteproducto.class);
	}

	/**
	 * Método para obtener un ajuste de produccion de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Ajusteproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Ajusteproducto.class, codigo);
	}

	public static Ajusteproducto getByMesAnioProductoProceso(Long codigoLineaNegocio, Long codigoProceso, Long codigoProducto,
			String estadoAjusteProduccion, String estadoAjusteProducto, Integer anio, Long mes) throws EntornoEjecucionException,
			SesionVencidaException {

		String mensajeError = "";
		Ajusteproducto ajusteProducto = null;
		try {

			String consulta = "select ap from Ajusteproducto ap where "
					+ " ap.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio "
					+ " and ap.ajusteproduccion.periodocontable.anoPeriodocontable = :anoContable "
					+ " and ap.ajusteproduccion.periodocontable.mesPeriodocontable = :mesContable ";

			if (codigoProceso > 0)
				consulta += " and ap.producto.pkCodigoProducto in (select hr.produccion.producto.pkCodigoProducto from Hojaruta hr where hr.estadohojaruta.pkCodigoEstadohojaruta = 1 and hr.produccion.proceso.pkCodigoProceso = :codigoProceso and hr.produccion.producto.pkCodigoProducto = :codigoProducto ) ";
			if (codigoProducto > 0)
				consulta += " and ap.producto.pkCodigoProducto = :codigoProducto ";

			Query query = Querier.query(consulta);
			query.setLong("codigoLineaNegocio", codigoLineaNegocio);

			query.setInteger("anoContable", anio);
			query.setShort("mesContable", mes.shortValue());
			if (codigoProceso > 0)
				query.setLong("codigoProceso", codigoProceso);
			if (codigoProducto > 0)
				query.setLong("codigoProducto", codigoProducto);

			ajusteProducto = (Ajusteproducto) query.uniqueResult();

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
		return ajusteProducto;
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un grupo de producto ajustado en la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Ajusteproducto ajusteproducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(ajusteproducto);
	}

	/**
	 * Metodo para modificar un producto de ajustado en la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Ajusteproducto ajusteproducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(ajusteproducto);
	}

	/**
	 * Método para eliminar un producto ajustado en la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Ajusteproducto ajusteproducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(ajusteproducto);
	}

	public static Ajusteproducto obtenerPorProductoLineaNegPeriodoContYGrupo(Long codigoProducto, Long codigoLinea, Short mes,
			Integer anio) throws ElementoNoEncontradoException, EntornoEjecucionException, SesionVencidaException {
		String mensajeError = "";
		try {
			StringBuilder queryStr = new StringBuilder("FROM Ajusteproducto ap WHERE ");
			queryStr.append("ap.producto.pkCodigoProducto = :codProducto");
			queryStr.append(" AND ap.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			queryStr.append(" AND ap.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			queryStr.append(" AND ap.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");

			Query query = getSession().createQuery(queryStr.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setShort("mes", mes);
			query.setInteger("anio", anio);

			Ajusteproducto ajusteproducto = (Ajusteproducto) query.uniqueResult();

			return ajusteproducto;

		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (QueryException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntornoEjecucionException(e.getMessage(), e);
		}

	}

	public static List<Puestotrabajoproduccion> obtenerHorasAjustePorLineaNegPeriodoCont(Long codigoPuesto, Long codigoLinea,
			Short mes, Integer anio) throws ElementoNoEncontradoException, EntornoEjecucionException, SesionVencidaException {
		String mensajeError = "";

		try {
			StringBuilder queryStr = new StringBuilder("FROM Puestotrabajoproduccion ptp WHERE ");
			queryStr.append(" ptp.puestotrabajo = :codigoPuesto ");
			queryStr.append(" AND ptp.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			queryStr.append(" AND ptp.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			queryStr.append(" AND ptp.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");

			Query query = getSession().createQuery(queryStr.toString());
			query.setLong("codigoPuesto", codigoPuesto);
			query.setLong("codigoLinea", codigoLinea);
			query.setShort("mes", mes);
			query.setInteger("anio", anio);

			List<Puestotrabajoproduccion> puestotrabajoprods = query.list();

			return puestotrabajoprods;

		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (QueryException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntornoEjecucionException(e.getMessage(), e);
		}

	}

	public static void cambiarAEstadoAprobadoAjustesProducto(Long codigoAjusteproduccion, Session session)
			throws EntornoEjecucionException {

		StringBuilder queryStr = new StringBuilder("UPDATE Ajusteproducto ap SET ap.estadoajusteproducto = :estado WHERE ");
		queryStr.append("ap.ajusteproduccion.pkCodigoAjusteproduccion = :codAjusteProduccion");

		try {

			Query query = session.createQuery(queryStr.toString());

			Estadoajusteproducto estadoajusteproducto = new Estadoajusteproducto();
			estadoajusteproducto.setPkCodigoEstadoajusteproducto(8L);

			query.setParameter("estado", estadoajusteproducto);
			query.setParameter("codAjusteProduccion", codigoAjusteproduccion);

			query.executeUpdate();

		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException("Ocurrio  un error actualizando el estado de los ajustes", e);
		}
	}

	public static List<Ajusteproducto> obtenerPorPropiedades(Map<String, Object> propiedades) throws EntornoEjecucionException {

		try {
			return findByProperties(Ajusteproducto.class, propiedades);
		} catch (HibernateException e) {
			log.error(e);
			throw new EntornoEjecucionException("Ocurrio  un error actualizando el estado de los ajustes", e);
		}
	}

	public static Ajusteproducto obtenerPorCodigo(Long codigo) throws ElementoNoEncontradoException {
		// TODO Auto-generated method stub
		return Querier.getById(Ajusteproducto.class, codigo);
	}
}
