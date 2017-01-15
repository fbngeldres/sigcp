package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnaplantillaproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ColumnaPlantillaProductoQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static List<Columnaplantillaproducto> getAll() throws AplicacionException {

		return Querier.getAll(Columnaplantillaproducto.class);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar una Plantilla en la BD.
	 * 
	 * @param columna
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Columnareporte columna) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(columna);
	}

	public static void save(Columnaplantillaproducto columna) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(columna);
	}

	/**
	 * Método para modificar una Plantilla de la BD.
	 * 
	 * @param columna
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Columnaplantillaproducto columna) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(columna);
	}

	/**
	 * Método para eliminar una Plantilla de la BD.
	 * 
	 * @param columna
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Columnaplantillaproducto columna) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(columna);
	}

	/**
	 * Método para obtener una Columnaplantillaproducto de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Columnaplantillaproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Columnaplantillaproducto.class, codigo);
	}

	public static List<Columnaplantillaproducto> getByCodigoColumnaReporte(Long codigoColumnaReporte) {
		List<Columnaplantillaproducto> lista = null;
		String consulta = "SELECT cpp FROM Columnaplantillaproducto cpp,Columnareporte as cr "
				+ "WHERE cpp.columnareporte.pkCodigoColumnareporte = cr.pkCodigoColumnareporte "
				+ "AND cr.pkCodigoColumnareporte  = ? ";
		String mensajeError = "";
		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoColumnaReporte);
			lista = query.list();

			return lista;

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
	 * Método para obtener las columnas de una plantilla dada con un estado
	 * determinado
	 * 
	 * @param codigoPlantilla
	 * @param estado
	 * @return
	 */
	public static List<Columnaplantillaproducto> obtenerColumnasPorPlantillaYEstado(Long codigoPlantilla, String estado) {
		List<Columnaplantillaproducto> columnas = new ArrayList<Columnaplantillaproducto>();

		String consulta = "from Columnaplantillaproducto cr where cr.plantillareporte.pkCodigoPlantillareporte = ? and"
				+ " cr.estadoColumnaplantillaproducto.nombreEstadoColumnaplantillaproducto = ? and cr.posicionColumnaplantillaproducto between 1 and 14 ";
		String mensajeError = "";

		try {

			Query query = Querier.query(consulta);
			query.setLong(0, codigoPlantilla);
			query.setString(1, estado);

			columnas = query.list();

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

		return columnas;
	}

	/**
	 * Método para obtener las columnas de una plantilla dada con un estado
	 * determinado
	 * 
	 * @param codigoPlantilla
	 * @param estado
	 * @return
	 */
	public static List<Columnaplantillaproducto> obtenerColumnasPorCodigoPlantillaProducto(Long codigoPlantilla) {
		List<Columnaplantillaproducto> columnas = new ArrayList<Columnaplantillaproducto>();
		String consulta = "FROM Columnaplantillaproducto AS cpp WHERE cpp.plantillaproducto.pkCodigoPlantillaproducto = ? AND cpp.proporcionColumnareporte > 0.00";

		String mensajeError = "";

		try {

			Query query = Querier.query(consulta);
			query.setLong(0, codigoPlantilla);

			columnas = query.list();

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

		return columnas;
	}

	public static List<Columnaplantillaproducto> obtenerColumnas(Long codigoPuestoTrabajo, Long codigoProceso,
			String estadoColumna, String estadoPlantilla, String nombreArchivo) throws AplicacionException {

		List<Columnaplantillaproducto> columnas = new ArrayList<Columnaplantillaproducto>();

		String consulta = "SELECT cr FROM Columnaplantillaproducto as cr, Plantillareporte as pr"
				+ " WHERE pr.pkCodigoPlantillareporte = cr.plantillareporte.pkCodigoPlantillareporte"

				// Like agregado para validar nombre del reporte.
				+ " AND upper(pr.nombrePlantillareporte) like :nombreArchivo "

				+ " AND pr.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo"
				+ " AND pr.proceso.pkCodigoProceso = :codigoProceso"
				+ " AND upper(pr.estadoplantillareporte.nombreEstadoplantillareporte) = upper(:nombreEstadoPlantilla)"
				+ " AND upper(cr.estadoColumnaplantillaproducto.nombreEstadoColumnaplantillaproducto) = upper(:nombreEstadoColumna)";

		try {

			Query query = Querier.query(consulta);
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			query.setLong("codigoProceso", codigoProceso);
			query.setString("nombreEstadoPlantilla", estadoPlantilla);
			query.setString("nombreEstadoColumna", estadoColumna);
			query.setString("nombreArchivo", nombreArchivo);

			columnas = query.list();

		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

		return columnas;
	}

	@SuppressWarnings("unchecked")
	public static List<Columnaplantillaproducto> obtenerPorDatosDePlantillaProducto(Long codigoPlantillaReporte,
			Long codigoProducto) {
		String consulta = "SELECT cpp FROM Columnaplantillaproducto cpp "
				+ "WHERE cpp.plantillaproducto.plantillareporte.pkCodigoPlantillareporte = ? "
				+ "AND cpp.plantillaproducto.producto.pkCodigoProducto  = ? ";
		String mensajeError = "";
		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoPlantillaReporte);
			query.setLong(1, codigoProducto);
			List<Columnaplantillaproducto> lista = query.list();

			return lista;

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
	 * Obtiene una lista de objetos Columnaplantillaproducto segun la columna
	 * del reporte y el componente especificacods
	 * 
	 * @param codigoColumnaReporte
	 * @param codigoComponente
	 * @return List<Columnaplantillaproducto>
	 * @throws EntornoEjecucionException si la busqueda falla
	 * @throws SesionVencidaException si la sesion ha expirado o esta en estado
	 *             invalido
	 */
	@SuppressWarnings("unchecked")
	public static List<Columnaplantillaproducto> obtenerPorColumnayComponente(Long codigoColumnaReporte, Long codigoComponente) {

		String consulta = "SELECT cpp FROM Columnaplantillaproducto cpp "
				+ "WHERE cpp.columnareporte.pkCodigoColumnareporte = ? " + "AND cpp.componente.pkCodigoComponente  = ? ";
		String mensajeError = "";
		try {
			Query query = Querier.query(consulta);
			query.setLong(0, codigoColumnaReporte);
			query.setLong(1, codigoComponente);
			List<Columnaplantillaproducto> lista = query.list();

			return lista;

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

}
