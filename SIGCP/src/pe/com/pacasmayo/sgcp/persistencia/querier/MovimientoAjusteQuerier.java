package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.log4j.Logger;
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajuste;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class MovimientoAjusteQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static String mensajeError;
	private static Logger log = Logger.getLogger(MovimientoAjusteQuerier.class);

	/**
	 * Método para obtener la lista de objectos Movimientoajuste
	 * 
	 * @return
	 */
	public static List<Movimientoajuste> getAll() {

		return Querier.getAll(Movimientoajuste.class);
	}

	/**
	 * Método para obtener una Movimientoajuste de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Movimientoajuste getById(Long codigo) throws ElementoNoEncontradoException {
		return Querier.getById(Movimientoajuste.class, codigo);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar una Movimientoajuste en la BD.
	 * 
	 * @param movimientoajuste
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Movimientoajuste movimientoajuste) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(movimientoajuste);
	}

	/**
	 * Metodo para modificar una Movimientoajuste de la BD.
	 * 
	 * @param movimientoajuste
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Movimientoajuste movimientoajuste) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(movimientoajuste);
	}

	/**
	 * Método para eliminar una Movimientoajuste de la BD.
	 * 
	 * @param movimientoajuste
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Movimientoajuste movimientoajuste) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(movimientoajuste);
	}

	/**
	 * Obtiene la cantidad ajustada en el consumo de un componente, para todos
	 * los puestos de trabajo y todos los productos en los que se usa el
	 * componente
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeComponente(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder("SELECT sum(ma.cantidadMovimientoajuste) FROM Movimientoajuste AS ma WHERE ");
			hql.append("ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Obtiene la cantidad ajustada en el consumo de un componente, para un
	 * puesto de trabajo y todos los productos en los que se usa el componente
	 * 
	 * @param codigoPuesto Long codigo del puesto de trabajo
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeComponentePorPuestoTrabajo(Long codigoPuesto, Long codigoProducto,
			Long codigoLinea, Short numeroMes, Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder("SELECT sum(ma.cantidadMovimientoajuste) FROM Movimientoajuste AS ma WHERE ");
			hql.append("ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrabajo");

			Query query = Querier.query(hql.toString());
			query.setLong("codPuestoTrabajo", codigoPuesto);
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Obtiene la cantidad ajustada en el consumo de un producto, para todos los
	 * puestos de trabajo y todos los productos en los que se usa el producto
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeProductoExceptuando(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable, Long codigoProductoExceptuar) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT sum(map.cantidadMovimientoajusteproducto) FROM Movimientoajusteproducto AS map WHERE ");
			hql.append("map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto <> :codigoProductoExceptuar");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);
			query.setLong("codigoProductoExceptuar", codigoProductoExceptuar);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	public static double obtenerAjustesPorConsumoDeProducto(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT sum(map.cantidadMovimientoajusteproducto) FROM Movimientoajusteproducto AS map WHERE ");
			hql.append("map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Obtiene la cantidad ajustada en el consumo de un producto, para todos los
	 * puestos de trabajo y todos los productos en los que se usa el producto
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeProductoPorPuestoTrabajo(Long codigoPuesto, Long codigoProducto,
			Long codigoLinea, Short numeroMes, Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT sum(map.cantidadMovimientoajusteproducto) FROM Movimientoajusteproducto AS map WHERE ");
			hql.append("map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");
			hql.append(" AND map.puestotrabajoproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codPuestoTrabajo");

			Query query = Querier.query(hql.toString());
			query.setLong("codPuestoTrabajo", codigoPuesto);
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Obtiene el total
	 * 
	 * @param codigoAjusteProduccion
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerAjustesPorProducto(Long codigoAjusteProduccion) {
		try {
			// pkCodigoProducto
			StrBuilder hqlBld = new StrBuilder("SELECT ma.producto.pkCodigoProducto, SUM(ma.cantidadMovimientoajuste)");
			hqlBld.append(" FROM Movimientoajuste ma WHERE");
			hqlBld.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion = :codAjusteProd");
			hqlBld.append(" GROUP BY ma.producto.pkCodigoProducto");

			Query query = Querier.query(hqlBld.toString());
			query.setLong("codAjusteProd", codigoAjusteProduccion);

			List<Object[]> lista = query.list();

			return lista;

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

	/**
	 * Obtiene la cantidad ajustada en el consumo de un componente, para todos
	 * los puestos de trabajo y todos los productos en los que se usa el
	 * componente, excepto los ajustes provenientes de exceptuandoCodProdAjusta
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @param exceptuandoCodProdAjusta
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeComponenteExceptuando(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable, Long exceptuandoCodProdAjusta) {
		try {
			StringBuilder hql = new StringBuilder("SELECT sum(ma.cantidadMovimientoajuste) FROM Movimientoajuste AS ma WHERE ");
			hql.append("ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProducto.pkCodigoProducto <> :exceptuandoCodProdAjusta");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setLong("exceptuandoCodProdAjusta", exceptuandoCodProdAjusta);
			query.setShort("mes", numeroMes);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Este metodo es usado por
	 * AjusteProduccionMesQuerier.obtenerConceptosComponentesProducto la lista
	 * que retorna tiene las mismas columnas en la misma posición, las columnas
	 * que no aplican están forzadas a null
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param numeroMes
	 * @param anioContable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerProductosAjustadosViaMovimientoAjusteProducto(Set<String> otrosProductosAjuste) {
		try {

			if (otrosProductosAjuste == null || otrosProductosAjuste.size() == 0) {
				return new LinkedList<Object[]>();
			}

			// Lista columnas:
			// 0 = pkCodigoComponente, 1 = nombreProducto (productoComponente),
			// 2 = suma consumo, 3 = grupo (productoComponente),
			// 4 = pkCodigoProducto (productoComponente)

			StringBuilder hql = new StringBuilder(
					"SELECT prod.nombreProducto, 0, prod.grupoProducto, prod.pkCodigoProducto FROM Producto AS prod WHERE prod.pkCodigoProducto in (");
			hql.append(StringUtils.join(otrosProductosAjuste, ","));
			hql.append(")");

			Query query = Querier.query(hql.toString());

			// HACK: increiblemente no hubo forma de hacer select null (ej. cast
			// fallo) en HQL
			// y fue necesario este hack de agregar a mano un elemento 0 con
			// null en la lista.

			List<Object[]> result = new LinkedList<Object[]>();
			List<Object[]> lista = query.list();
			for (Object[] objects : lista) {
				Object[] arreglo = new Object[5];
				arreglo[0] = null;
				arreglo[1] = objects[0];
				arreglo[2] = objects[1];
				arreglo[3] = objects[2];
				arreglo[4] = objects[3];
				result.add(arreglo);
			}

			return result;

		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}

	}

	/**
	 * Agregado Por Fabian
	 */

	/**
	 * Obtiene la cantidad ajustada en el consumo de un componente, para todos
	 * los puestos de trabajo y todos los productos en los que se usa el
	 * componente, excepto los ajustes provenientes de exceptuandoCodProdAjusta
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @param exceptuandoCodProdAjusta
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeComponente_(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder("SELECT sum(ma.cantidadMovimientoajuste) FROM Movimientoajuste AS ma WHERE ");
			hql.append("  ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");

			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea ");
			}

			if (numeroMes != null) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			}
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}
			query.setInteger("anio", anioContable);
			if (numeroMes != null) {
				query.setShort("mes", numeroMes);
			}
			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	public static double obtenerAjustesPorConsumoDeComponenteAnual(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder("SELECT sum(ma.cantidadMovimientoajuste) FROM Movimientoajuste AS ma WHERE ");
			hql.append("  ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");

			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea ");
			}

			if (numeroMes != null) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes");
			}
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}
			query.setInteger("anio", anioContable);
			if (numeroMes != null) {
				query.setShort("mes", numeroMes);
			}
			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Obtiene la cantidad ajustada en el consumo de un producto, para todos los
	 * puestos de trabajo y todos los productos en los que se usa el producto en
	 * lo que va del año
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeProducto_(Long codigoProducto, Long codigoLinea, Short mes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT sum(map.cantidadMovimientoajusteproducto) FROM Movimientoajusteproducto AS map WHERE ");

			hql.append(" map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");

			if (mes != null) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes ");
			}

			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea ");
			}

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}
			if (mes != null) {
				query.setShort("mes", mes);
			}
			query.setInteger("anio", anioContable);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	public static double obtenerAjustesPorConsumoDeProductoAnual(Long codigoProducto, Long codigoLinea, Short mes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT sum(map.cantidadMovimientoajusteproducto) FROM Movimientoajusteproducto AS map WHERE ");

			hql.append(" map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");

			if (mes != null) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes ");
			}

			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea ");
			}

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}
			if (mes != null) {
				query.setShort("mes", mes);
			}
			query.setInteger("anio", anioContable);

			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Agregado por Fabian
	 */
	/**
	 * Obtiene la cantidad ajustada en el consumo de un componente, para todos
	 * los puestos de trabajo y todos los productos en los que se usa el
	 * componente
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeComponenteValidLineNego(Long codigoProducto, Long codigoLinea,
			Short numeroMes, Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder("SELECT sum(ma.cantidadMovimientoajuste) FROM Movimientoajuste AS ma WHERE ");

			hql.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			if (numeroMes != null) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			}

			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			}

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}

			query.setInteger("anio", anioContable);
			if (numeroMes != null) {
				query.setShort("mes", numeroMes);
			}
			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Agregado por Fabian Obtiene la cantidad ajustada en el consumo de un
	 * producto, para todos los puestos de trabajo y todos los productos en los
	 * que se usa el producto
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeProductoValidandoLineaNegocio(Long codigoProducto, Long codigoLinea,
			Short numeroMes, Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT sum(map.cantidadMovimientoajusteproducto) FROM Movimientoajusteproducto AS map WHERE ");
			hql.append("  map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			if (numeroMes != null) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			}
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			}

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}
			query.setInteger("anio", anioContable);
			if (numeroMes != null) {
				query.setShort("mes", numeroMes);
			}
			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	public static double obtenerAjustesPorConsumoDeComponenteAcumulado(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder("SELECT sum(ma.cantidadMovimientoajuste) FROM Movimientoajuste AS ma WHERE ");

			hql.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			if (numeroMes != null) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes");
			}

			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			}

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}

			query.setInteger("anio", anioContable);
			if (numeroMes != null) {
				query.setShort("mes", numeroMes);
			}
			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Agregado por Fabian Obtiene la cantidad ajustada en el consumo de un
	 * producto, para todos los puestos de trabajo y todos los productos en los
	 * que se usa el producto
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static double obtenerAjustesPorConsumoDeProductoAcumulado(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT sum(map.cantidadMovimientoajusteproducto) FROM Movimientoajusteproducto AS map WHERE ");
			hql.append("  map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			if (numeroMes != null) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes");
			}
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			}

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			if (codigoLinea != null && codigoLinea.longValue() != -1L) {
				query.setLong("codigoLinea", codigoLinea);
			}
			query.setInteger("anio", anioContable);
			if (numeroMes != null) {
				query.setShort("mes", numeroMes);
			}
			Double consumoPorAjuste = (Double) query.uniqueResult();

			if (consumoPorAjuste == null) {
				return 0d;
			}

			return consumoPorAjuste.doubleValue();
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

	/**
	 * Obtiene los Moviemientos de los componentes
	 * 
	 * @param valorMes
	 * @param valorAnio
	 * @param valorLineaNegocio
	 */

	public static List<Movimientoajuste> obtenerMovimientosAjustes(Integer valorAnio, Short valorMes, Long valorLineaNegocio,
			List<Long> productosExcluidos) {
		StringBuilder consulta = new StringBuilder(" FROM Movimientoajuste ma ");
		consulta.append(" WHERE ");
		consulta.append(" ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
		consulta.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");

		if (productosExcluidos != null && productosExcluidos.size() > 0) {
			consulta.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto not in (:productoExcluidos)");
		}

		if (valorLineaNegocio != null && valorLineaNegocio.compareTo(Long.valueOf(-1)) != 0) {
			consulta.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :linea");
		}

		consulta.append(" order by  ma.producto.nombreProducto,ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.nombreProducto,ma.consumocomponenteajuste.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo");
		Query query = query(consulta.toString());
		query.setShort("mes", valorMes);
		query.setInteger("anio", valorAnio);
		if (valorLineaNegocio != null && valorLineaNegocio.compareTo(Long.valueOf(-1)) != 0) {
			query.setLong("linea", valorLineaNegocio);
		}
		if (productosExcluidos != null && productosExcluidos.size() > 0) {
			query.setParameterList("productoExcluidos", productosExcluidos);
		}
		return query.list();
	}

	/**
	 * Obtiene los Moviemientos de los componentes
	 * 
	 * @param valorMes
	 * @param valorAnio
	 * @param valorLineaNegocio
	 */

	public static List<Object[]> obtenerMovimientosAjustesCombustible(Integer valorAnio, Short valorMes, Long valorLineaNegocio,
			List<Long> productosExcluidos) {
		StringBuilder consulta = new StringBuilder(" SELECT ");

		consulta.append(" cca.componente.productoByFkCodigoProductoComponente.nombreProducto, ");
		consulta.append(" cca.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo, ");
		consulta.append(" SUM(cca.tmProdConsumocomponenteajus),");
		consulta.append(" SUM((cca.tmProdConsumocomponenteajus+cca.diferenciaConsumocomponenteaju)),");
		consulta.append(" SUM(cca.diferenciaConsumocomponenteaju),");
		consulta.append(" SUM(cca.porcentCarbonConsucompajuste) ");

		consulta.append(" FROM  Consumocomponenteajuste cca  ");
		consulta.append(" WHERE ");
		consulta.append(" cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
		consulta.append(" AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
		consulta.append(" AND cca.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto in (:productoExcluidos)");
		if (valorLineaNegocio != null && valorLineaNegocio.compareTo(Long.valueOf(-1)) != 0) {
			consulta.append(" AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :linea");
		}
		consulta.append(" GROUP by  cca.componente.productoByFkCodigoProductoComponente.nombreProducto,cca.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo");
		consulta.append(" order by  cca.puestotrabajoproduccion.puestotrabajo.nombrePuestotrabajo");

		Query query = query(consulta.toString());
		query.setShort("mes", valorMes);
		query.setInteger("anio", valorAnio);
		if (valorLineaNegocio != null && valorLineaNegocio.compareTo(Long.valueOf(-1)) != 0) {
			query.setLong("linea", valorLineaNegocio);
		}
		query.setParameterList("productoExcluidos", productosExcluidos);
		return query.list();
	}

	/**
	 * Igual a obtenerAjustesPorConsumoDeComponente, pero en lugar de retornar
	 * suma retorna detalle
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param numeroMes
	 * @param anioContable
	 * @return
	 */
	public static List<Map> obtenerObjetosAjustesPorConsumoDeComponente(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"select new map(ma.producto.nombreProducto as nombreProducto, ma.cantidadMovimientoajuste as cantidad) from Movimientoajuste AS ma WHERE ");
			hql.append("ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);

			return query.list();
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

	/**
	 * Igual a obtenerAjustesPorConsumoDeProducto, pero en lugar de retornar
	 * suma, retorna detalle
	 * 
	 * @param codigoProducto
	 * @param codigoLinea
	 * @param numeroMes
	 * @param anioContable
	 * @return
	 */
	public static List<Map> obtenerObjetosAjustesPorConsumoDeProducto(Long codigoProducto, Long codigoLinea, Short numeroMes,
			Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(
					"SELECT new map(map.puestotrabajoproduccion.ajusteproducto.producto.nombreProducto as producto,cantidadMovimientoajusteproducto as cantidad) FROM Movimientoajusteproducto AS map WHERE ");
			hql.append("map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND map.producto.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);

			return query.list();
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

	/**
	 * Obtiene las lista de cantidades ajustadas en el consumo de un componente,
	 * para todos los puestos de trabajo y todos los productos en los que se usa
	 * el componente para mostrarlo en el Reporte PD
	 * 
	 * @param codigoProducto Long codigo del prodcuto (componente)
	 * @param codigoLinea Long
	 * @param numeroMes Short
	 * @param anioContable Integer
	 * @return
	 */
	public static List<Movimientoajuste> obtenerAjustesPorConsumoDeComponentePD(Long codigoProducto, Long codigoLinea,
			Short numeroMes, Integer anioContable) {
		try {
			StringBuilder hql = new StringBuilder(" FROM Movimientoajuste AS ma WHERE ");
			hql.append("ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND ma.consumocomponenteajuste.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND ma.consumocomponenteajuste.componente.productoByFkCodigoProductoComponente.pkCodigoProducto = :codProducto");

			Query query = Querier.query(hql.toString());
			query.setLong("codProducto", codigoProducto);
			query.setLong("codigoLinea", codigoLinea);
			query.setInteger("anio", anioContable);
			query.setShort("mes", numeroMes);

			return query.list();
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
