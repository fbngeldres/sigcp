package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumopuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Productovariablevariacion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajo;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ProductoVariableVariacionQuerier extends Querier implements ConstantesLogicaNegocio {

	private static Logger log = Logger.getLogger(ProductoVariableVariacionQuerier.class);
	private static String mensajeError;

	/**
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Productovariablevariacion> getAll() throws AplicacionException {
		return Querier.getAll(Productovariablevariacion.class);
	}

	/**
	 * @param pkCodigoProductoVariable
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	public static Productovariablevariacion getById(long pkCodigoProductoVariable) throws ElementoNoEncontradoException,
			SesionVencidaException, EntornoEjecucionException {
		return Querier.getById(Productovariablevariacion.class, pkCodigoProductoVariable);
	}

	/**
	 * @param productoVariablevariacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void save(Productovariablevariacion productoVariablevariacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.save(productoVariablevariacion);
	}

	/**
	 * @param productoVariablevariacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void update(Productovariablevariacion productoVariablevariacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(productoVariablevariacion);
	}

	/**
	 * @param productoVariablevariacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void delete(Productovariablevariacion productoVariablevariacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.delete(productoVariablevariacion);
	}

	@SuppressWarnings("unchecked")
	public static List<Productovariablevariacion> searchByDate(Date fecha) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {
		String hql = "FROM Productovariablevariacion as pvv WHERE pvv.fechaVariablevariacion = :fecha";
		Query query = Querier.query(hql);
		query.setDate("fecha", fecha);
		return (List<Productovariablevariacion>) query.list();
	}

	/**
	 * Obtiene el productovariablevariacion(error de balanza) mas reciente para
	 * un producto en un proceso y puesto de trabajo
	 * 
	 * @param codigoProducto
	 * @param tablakardex
	 * @param session
	 * @return double Consumo afectado por las variables de variacion
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws AplicacionException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static Productovariablevariacion obtenerSegunConsumoPuestoTrab(Consumopuestotrabajo consumopuestotrabajo,
			Session session) throws EntornoEjecucionException, SesionVencidaException, SesionVencidaException,
			EntornoEjecucionException {

		Producto componente = consumopuestotrabajo.getComponente().getProductoByFkCodigoProductoComponente();
		Long codigoProducto = componente.getPkCodigoProducto();

		Proceso proceso = consumopuestotrabajo.getProductogenerado().getOrdenproduccion().getProduccion().getProceso();
		Puestotrabajo puestotrabajo = consumopuestotrabajo.getProductogenerado().getTablaoperacion().getProduccionpuestotrabajo()
				.getPuestotrabajo();

		try {
			StringBuilder hql = new StringBuilder("FROM Productovariablevariacion as pvv WHERE ");
			hql.append("pvv.producto.pkCodigoProducto = :codProducto");
			hql.append(" AND pvv.puestotrabajo.pkCodigoPuestotrabajo = :codPuestotrabajo");
			hql.append(" AND pvv.proceso.pkCodigoProceso = :codProceso");
			hql.append(" ORDER BY fechaVariablevariacion DESC");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codPuestotrabajo", puestotrabajo.getPkCodigoPuestotrabajo());
			query.setLong("codProceso", proceso.getPkCodigoProceso());

			List<Productovariablevariacion> list = query.list();

			if (list != null && list.size() > 0) {
				Productovariablevariacion productovariablevariacion = list.get(0);
				return productovariablevariacion;
			}
			return null;
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Obtiene el valor mas reciente registrado en las variables de variacion de
	 * un producto
	 * 
	 * @param consumopuestotrabajo Consumopuestotrabajo
	 * @param nombreVariableVar String
	 * @param session Session
	 * @return double
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static double obtenerValorVaribaleVariacionSegunNombre(Long codigoProceso, Long codigoProducto, Long codPuestoTrab,
			String nombreVariableVar) throws EntornoEjecucionException, SesionVencidaException, SesionVencidaException,
			EntornoEjecucionException {

		StringBuilder hql = new StringBuilder("FROM Productovariablevariacion as pvv WHERE ");
		hql.append("pvv.producto.pkCodigoProducto = :codProducto AND ");
		hql.append("pvv.proceso.pkCodigoProceso = :codProceso AND ");
		hql.append("pvv.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
		hql.append("pvv.variablevariacion.nombreVariablevariacion = :nombreVar ORDER BY fechaVariablevariacion DESC");

		try {
			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codProceso", codigoProceso);
			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setString("nombreVar", nombreVariableVar);

			List<Productovariablevariacion> list = query.list();

			if (list != null && list.size() > 0) {
				return list.get(0).getValorProductovariablevariacion();
			}

			return 0d;
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Jordan "Reporte Variable Variacion"
	 * 
	 * @param codigoProceso Long
	 * @param fechaInicio Date
	 * @param fechaFin Date
	 * @return List<Productovariablevariacion>
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Productovariablevariacion> obtenerValorVaribaleVariacionByUnidad(Long valorunidad)
			throws EntornoEjecucionException, SesionVencidaException, SesionVencidaException, EntornoEjecucionException {

		try {

			StringBuilder hql = new StringBuilder("FROM Productovariablevariacion as pvv WHERE 1=1 ");
			if (valorunidad != null) {
				hql.append("AND pvv.proceso.lineanegocio.unidad.pkCodigoUnidad = :valorunidad  ");
			}

			hql.append("ORDER BY fechaVariablevariacion DESC");

			Query query = Querier.query(hql.toString());
			if (valorunidad != null) {
				query.setLong("valorunidad", valorunidad);
			}

			return query.list();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * @param valorunidad
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	public static List<Proceso> obtenerProcesosValorVaribaleVariacionByUnidad(Long valorunidad) throws EntornoEjecucionException,
			SesionVencidaException, SesionVencidaException, EntornoEjecucionException {

		try {

			StringBuilder hql = new StringBuilder("SELECT DISTINCT  pvv.proceso FROM Productovariablevariacion as pvv WHERE 1=1 ");
			if (valorunidad != null) {
				hql.append("AND pvv.proceso.lineanegocio.unidad.pkCodigoUnidad = :valorunidad  ");
			}

			hql.append("ORDER BY pvv.proceso.ordenEjecucionProceso DESC");

			Query query = Querier.query(hql.toString());
			if (valorunidad != null) {
				query.setLong("valorunidad", valorunidad);
			}

			return query.list();
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Jordan "Reporte Variable Variacion" para Detalle
	 * 
	 * @param codigoProceso Long
	 * @param codigoProducto Long
	 * @param codPuestoTrab Long
	 * @param nombreVariableVar String
	 * @param fechaInicio Date
	 * @param fechaFin Date
	 * @return List<Object[]>
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerValorVaribaleVariacionSegunPuestoProductoNombreVar(Long codigoProceso,
			Long codigoProducto, Long codPuestoTrab, String nombreVariableVar, Date fechaInicio, Date fechaFin)
			throws EntornoEjecucionException, SesionVencidaException, SesionVencidaException, EntornoEjecucionException {

		StringBuilder hql = new StringBuilder("FROM Productovariablevariacion as pvv WHERE ");
		hql.append("pvv.producto.pkCodigoProducto = :codProducto AND ");
		hql.append("pvv.proceso.pkCodigoProceso = :codProceso AND ");
		hql.append("pvv.fechaVariablevariacion >= :fechaInicial AND ");
		hql.append("pvv.fechaVariablevariacion < :fechaFinal AND ");
		hql.append("pvv.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
		hql.append("pvv.variablevariacion.nombreVariablevariacion = :nombreVar ORDER BY fechaVariablevariacion DESC");

		try {
			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codProceso", codigoProceso);
			query.setDate("fechaInicial", fechaInicio);
			query.setDate("fechaFinal", fechaFin);
			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setString("nombreVar", nombreVariableVar);

			List<Productovariablevariacion> list = query.list();

			List<Object[]> varVariancia = new ArrayList<Object[]>();
			Object[] valores;

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					valores = new Object[2];

					valores[0] = list.get(i).getValorProductovariablevariacion();
					valores[1] = list.get(i).getFechaVariablevariacion();
					varVariancia.add(valores);
				}

			}
			return varVariancia;
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Jordan "Reporte Variable Variacion" para Detalle
	 * 
	 * @param codigoProceso Long
	 * @param codigoProducto Long
	 * @param codPuestoTrab Long
	 * @param nombreVariableVar String
	 * @param fechaInicio Date
	 * @param fechaFin Date
	 * @return List<Object[]>
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static Double obtenerValorVaribaleVariacionFechasAnteriores(Long codigoProceso, Long codigoProducto,
			Long codPuestoTrab, String nombreVariableVar, Date fechaInicio) throws EntornoEjecucionException,
			SesionVencidaException, SesionVencidaException, EntornoEjecucionException {

		StringBuilder hql = new StringBuilder("FROM Productovariablevariacion as pvv WHERE ");
		hql.append("pvv.producto.pkCodigoProducto = :codProducto AND ");
		hql.append("pvv.proceso.pkCodigoProceso = :codProceso AND ");
		hql.append("pvv.fechaVariablevariacion < :fechaInicial AND ");
		hql.append("pvv.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
		hql.append("pvv.variablevariacion.nombreVariablevariacion = :nombreVar ORDER BY fechaVariablevariacion DESC");

		try {
			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codProceso", codigoProceso);
			query.setDate("fechaInicial", fechaInicio);
			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setString("nombreVar", nombreVariableVar);
			query.setMaxResults(1);

			List<Productovariablevariacion> list = query.list();
			Double valor = null;

			if (list != null && list.size() > 0) {
				valor = list.get(0).getValorProductovariablevariacion();
			}
			return valor;

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

	public static Productovariablevariacion obtenerValorVaribaleVariacionProduccion(Long codigoProducto, Long codigoProceso,
			Long codPuestoTrab, Date fechanotificacion) {
		StringBuilder hql = new StringBuilder("FROM Productovariablevariacion as pvv WHERE ");
		hql.append("pvv.producto.pkCodigoProducto = :codProducto AND ");
		hql.append("pvv.proceso.pkCodigoProceso = :codProceso AND ");
		hql.append("pvv.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
		hql.append("pvv.variablevariacion.produccion = :produccion ");
		hql.append(" AND pvv.fechaVariablevariacion <= :fecha ");
		hql.append(" ORDER BY fechaVariablevariacion DESC ");
		try {
			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codProceso", codigoProceso);
			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setBoolean("produccion", Boolean.TRUE);
			query.setDate("fecha", fechanotificacion);
			query.setMaxResults(1);
			Productovariablevariacion list = (Productovariablevariacion) query.uniqueResult();

			return list;

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

	public static double obtenerValorVaribaleVariacionProduccionPorFecha(Long codigoProducto, Long codigoProceso,
			Long codPuestoTrab, Date fecha) {
		StringBuilder hql = new StringBuilder("FROM Productovariablevariacion as pvv WHERE ");
		hql.append("pvv.producto.pkCodigoProducto = :codProducto AND ");
		hql.append("pvv.proceso.pkCodigoProceso = :codProceso AND ");
		hql.append("pvv.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrab AND ");
		hql.append("pvv.fechaVariablevariacion <= :fechavariablevariacion ");
		hql.append(" ORDER BY fechaVariablevariacion DESC ");
		try {
			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codProceso", codigoProceso);
			query.setLong("codPuestoTrab", codPuestoTrab);
			query.setDate("fechavariablevariacion", fecha);

			query.setMaxResults(1);
			Productovariablevariacion list = (Productovariablevariacion) query.uniqueResult();

			if (list != null) {
				return list.getValorProductovariablevariacion();
			}

			return 0d;
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(e);
			throw new EntornoEjecucionException(e.getMessage(), e.getCause());
		}
	}

}
