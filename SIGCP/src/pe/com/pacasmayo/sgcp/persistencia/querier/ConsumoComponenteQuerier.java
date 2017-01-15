package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponente;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ConsumoComponenteQuerier extends Querier {
	private static String mensajeError = "";

	@SuppressWarnings("unchecked")
	public static List<Consumocomponente> obtenerRegistroDiario(Date fechaTablakardex, Long codigoTipoProducto, Long codigoAlmacen)
			throws AplicacionException {
		try {
			String queryString = "SELECT cc FROM Consumocomponente as cc WHERE "
					+ "cc.tablakardex.producciondiaria.ordenproduccion.produccion.producto.tipoproducto.pkCodigoTipoproducto <> :pkCodigoTipoproducto AND "
					+ "cc.tablakardex.almacen.pkCodigoAlmacen = :pkCodigoAlmacen AND cc.tablakardex.fechaTablakardex = :fechaTablakardex";

			Query query = Querier.query(queryString);
			query.setLong("pkCodigoTipoproducto", codigoTipoProducto);
			query.setLong("pkCodigoAlmacen", codigoAlmacen);
			query.setDate("fechaTablakardex", fechaTablakardex);

			return query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Obtienen una lista de consumo de compoenetes para la fecha del registro
	 * del kardex al que pertenecen
	 * 
	 * @param fechaKardex fecha
	 * @return
	 * @throws AplicacionException, SesionVencidaException,
	 *             EntornoEjecucionException
	 */
	@SuppressWarnings("unchecked")
	public static List<Consumocomponente> getPorFecha(Date fechaKardex) throws AplicacionException, SesionVencidaException,
			EntornoEjecucionException {
		try {
			String queryString = "SELECT cc FROM Consumocomponente as cc WHERE cc.tablakardex.fechaTablakardex = :fechaTablakardex";

			Query query = Querier.query(queryString);
			query.setDate("fechaTablakardex", fechaKardex);

			return query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(e);
			throw new SesionVencidaException(mensajeError, e.getCause());
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e.getCause());
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new AplicacionException(e.getMessage(), e.getCause());
		}
	}

	public static void eliminarPorCodigosTablaKardex(List<Long> codigosKardex) {
		if (codigosKardex != null && codigosKardex.size() > 0) {
			StringBuilder querystr = new StringBuilder(
					"DELETE FROM Consumocomponente c where c.tablakardex.pkCodigoTablakardex  in (:codigosKardex) ");
			Query query = Querier.query(querystr.toString());
			query.setParameterList("codigosKardex", codigosKardex);
			int row = query.executeUpdate();
		}
	}
}
