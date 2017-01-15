package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.List;

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Balanceproducto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class BalanceProductoQuerier extends Querier implements ConstantesMensajeAplicacion, ConstantesLogicaNegocio {

	/**
	 * Método para obtener la lista de objectos Balanceproducto
	 * 
	 * @return
	 */
	public static List<Balanceproducto> getAll() {

		return Querier.getAll(Balanceproducto.class);
	}

	/**
	 * Método para obtener un Balanceproducto de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Balanceproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return (Balanceproducto) Querier.getById(Balanceproducto.class, codigo);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar un Balanceproducto en la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Balanceproducto balanceproducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(balanceproducto);
	}

	/**
	 * Metodo para modificar un Balanceproducto de la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Balanceproducto balanceproducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(balanceproducto);
	}

	/**
	 * Método para eliminar un Balanceproducto de la BD.
	 * 
	 * @param ajusteProduccion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Balanceproducto balanceproducto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(balanceproducto);
	}

	/**
	 * Método para obtener el total de conceptos acumulados del mes
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param anio
	 * @return
	 */
	public static List<Balanceproducto> obtenerConceptosAcumulados(Long codigoProducto, Long codigoLineaNegocio, Integer anio) {
		String mensajeError = "";

		List<Balanceproducto> balancesProducto = new ArrayList<Balanceproducto>();

		try {

			String consulta = "select bp " + " from Ajusteproducto ap, Balanceproducto bp " + "where "
					+ "ap.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = ? and "
					+ "ap.ajusteproduccion.periodocontable.anoPeriodocontable = ? and "
					+ "ap.estadoajusteproducto.nombreEstadoajusteproducto = 'Aprobado' and "
					+ "ap.producto.pkCodigoProducto = ? and " + "bp.ajusteproducto = ap";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoLineaNegocio);
			query.setInteger(1, anio);
			query.setLong(2, codigoProducto);

			balancesProducto = query.list();

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

		return balancesProducto;
	}

	public static Balanceproducto obtenerPorConceptoTipoBalanceAjusteProducto(Long codigoConcepto, Long tipoConcepto,
			Long codigoAjusteProducto) throws ParametroInvalidoException, ElementoExistenteException, ElementoEliminadoException,
			ElementoNoEncontradoException {
		String mensajeError = "";

		Balanceproducto balanceProducto = null;

		try {

			String consulta = "select bp " + " from Balanceproducto bp " + "where bp.concepto.pkCodigoConcepto = ? and "
					+ "bp.tipobalance.pkCodigoTipobalance = ? and " + "bp.ajusteproducto.pkCodigoAjusteproducto = ?";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoConcepto);
			query.setLong(1, tipoConcepto);
			query.setLong(2, codigoAjusteProducto);

			balanceProducto = (Balanceproducto) query.uniqueResult();

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

		return balanceProducto;
	}

	/**
	 * Método para obtener el balance de un producto a través de la linea y
	 * periodo contable
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Balanceproducto> obtenerBalanceProducto(Long codigoProducto, Long codigoLineaNegocio, Short mes,
			Integer anio) {
		String mensajeError = "";
		List<Balanceproducto> balancesProducto = new ArrayList<Balanceproducto>();

		try {

			String consulta = "FROM Balanceproducto bp WHERE "
					+ "bp.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes AND "
					+ "bp.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio AND "

					+ "bp.ajusteproducto.producto.pkCodigoProducto = :producto ";
			if (codigoLineaNegocio.longValue() != -1L) {
				consulta += "AND bp.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :linea ";
			}

			Query query = Querier.query(consulta);

			query.setShort("mes", mes);
			query.setInteger("anio", anio);
			// query.setString("estado",
			// ManejadorPropiedades.obtenerPropiedadPorClave(NOMBRE_ESTADO_AJUSTE_PRODUCTO_AJUSTADO));
			query.setLong("producto", codigoProducto);
			if (codigoLineaNegocio.longValue() != -1L) {
				query.setLong("linea", codigoLineaNegocio);
			}

			balancesProducto = query.list();

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

		return balancesProducto;
	}

	public static List<Balanceproducto> obtenerConceptosAcumulados(Long codigoProducto, Long codigoLineaNegocio, Integer anio,
			Long estadoAjuste, Short mes) {
		String mensajeError = "";

		List<Balanceproducto> balancesProducto = new ArrayList<Balanceproducto>();

		try {
			StringBuilder consulta = new StringBuilder("select bp from Ajusteproducto ap, Balanceproducto bp ");
			consulta.append(" where ");
			consulta.append(" ap.ajusteproduccion.periodocontable.anoPeriodocontable = :anio ");
			consulta.append(" AND ap.ajusteproduccion.periodocontable.mesPeriodocontable <= :mes ");
			consulta.append(" AND ap.producto.pkCodigoProducto = :producto ");
			consulta.append(" AND bp.ajusteproducto = ap ");
			consulta.append(" AND bp.tipobalance.pkCodigoTipobalance=1 ");

			if (codigoLineaNegocio.longValue() != -1L) {
				consulta.append(" AND ap.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :lineaNeg ");
			}
			if (estadoAjuste != null) {
				consulta.append("AND ap.estadoajusteproducto.nombreEstadoajusteproducto = :estado  ");
			}

			Query query = Querier.query(consulta.toString());

			query.setInteger("anio", anio);
			if (estadoAjuste != null) {
				query.setString("estado", EstadoAjusteProductoQuerier.getById(estadoAjuste).getNombreEstadoajusteproducto());
			}
			query.setLong("producto", codigoProducto);
			query.setShort("mes", mes);
			if (codigoLineaNegocio.longValue() != -1L) {
				query.setLong("lineaNeg", codigoLineaNegocio);
			}

			balancesProducto = query.list();

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
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return balancesProducto;
	}

	/**
	 * Agregado por Fabian
	 */

	/**
	 * Método para obtener el balance de un producto a través de la linea y
	 * periodo contable Y el Estado del Balance
	 * 
	 * @param codigoProducto
	 * @param codigoLineaNegocio
	 * @param mes
	 * @param anio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Balanceproducto> obtenerBalanceProducto_(Long codigoProducto, Long codigoLineaNegocio, Short mes,
			Integer anio, Long estadoAjuste) {
		String mensajeError = "";
		List<Balanceproducto> balancesProducto = new ArrayList<Balanceproducto>();

		try {
			// FIXME : CAMBIAR CODIGO BALANCE
			String consulta = "FROM Balanceproducto bp WHERE "
					+ "bp.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes AND "
					+ "bp.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio AND "
					+ "bp.ajusteproducto.producto.pkCodigoProducto = :producto AND " + "bp.tipobalance.pkCodigoTipobalance=1 ";
			if (codigoLineaNegocio.longValue() != -1L) {
				consulta += " AND bp.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :linea ";
			}
			if (estadoAjuste != null) {
				consulta += " AND bp.ajusteproducto.estadoajusteproducto.nombreEstadoajusteproducto = :estado  ";
			}

			Query query = Querier.query(consulta);

			query.setShort("mes", mes);
			query.setInteger("anio", anio);
			if (estadoAjuste != null) {
				query.setString("estado", EstadoAjusteProductoQuerier.getById(estadoAjuste).getNombreEstadoajusteproducto());
			}
			query.setLong("producto", codigoProducto);
			if (codigoLineaNegocio.longValue() != -1L) {
				query.setLong("linea", codigoLineaNegocio);
			}

			balancesProducto = query.list();

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
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return balancesProducto;
	}
}
