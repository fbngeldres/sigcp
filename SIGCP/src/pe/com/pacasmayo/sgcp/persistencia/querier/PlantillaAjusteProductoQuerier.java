package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class PlantillaAjusteProductoQuerier extends Querier {

	private static Log loggerQ = LogFactory.getLog(ConsumoComponenteAjusteQuerier.class);

	/**
	 * Método para obtener un Producto por medio del código
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static Plantillaajusteproducto getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Plantillaajusteproducto.class, codigo);
	}

	/**
	 * Método para obtener los productos ajustados por Linea de Negocio
	 * 
	 * @param codigoLineaNegocio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductosPorLineaNegocio(Long codigoLineaNegocio) {

		List<Producto> productosGrupoAjuste = new ArrayList<Producto>();
		String mensajeError = "";

		try {

			String consulta = "select pap.produccion.producto from Plantillaajusteproducto as pap where ";

			if (codigoLineaNegocio.equals("-1"))
				consulta += " pap.plantillagrupoajuste.lineanegocio.pkCodigoLineanegocio = :codLineaNegocio and ";

			consulta += " pap.produccion.proceso.tipoproducto.siglasTipoproducto = :siglasProducto ";

			Query query = Querier.query(consulta);
			if (codigoLineaNegocio.equals("-1"))
				query.setLong("codLineaNegocio", codigoLineaNegocio);

			query.setString("siglasProducto", "PT");

			productosGrupoAjuste = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productosGrupoAjuste;
	}

	/**
	 * Este método devuelve los productos por medio de la línea de negocio y la
	 * Clase de Producto (Combustible, etc)
	 * 
	 * @param codigoLineaNegocio
	 * @param claseProducto
	 * @return
	 */
	public static List<Producto> obtenerProductosPorLineaNegocioYClaseProducto(Long codigoLineaNegocio, Long claseProducto) {

		List<Producto> productosGrupoAjuste = new ArrayList<Producto>();
		String mensajeError = "";

		try {

			String consulta = "select pap.produccion.producto from Plantillaajusteproducto as pap where ";

			if (codigoLineaNegocio > 0)
				consulta += " pap.plantillagrupoajuste.lineanegocio.pkCodigoLineanegocio = :codLineaNegocio and ";

			consulta += " pap.produccion.producto.tipocategoriaproducto.pkCodigoTipocategoriaproducto = :codCategoriaProducto ";

			Query query = Querier.query(consulta);
			if (codigoLineaNegocio > 0)
				query.setLong("codLineaNegocio", codigoLineaNegocio);

			query.setLong("codCategoriaProducto", claseProducto);

			productosGrupoAjuste = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productosGrupoAjuste;
	}

	/**
	 * Método para obtener los producctos del grupo de ajuste por Linea de
	 * Negocio
	 * 
	 * @param codigoLineaNegocio
	 * @return
	 */
	public static List<Producto> obtenerProductosPorGruposAjuste(Long codigoGrupoAjuste) {
		List<Producto> productosGrupoAjuste = new ArrayList<Producto>();
		String mensajeError = "";

		try {

			String consulta = "select pap.produccion.producto from Plantillaajusteproducto as pap  "
					+ " where pap.plantillagrupoajuste.pkCodigoPlantillagrupoajuste = ? ";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoGrupoAjuste);

			productosGrupoAjuste = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productosGrupoAjuste;
	}

	/**
	 * Método que obtiene los productos terminados asociados a un Grupo de
	 * Ajuste y a un Proceso
	 * 
	 * @param nombreGrupo
	 * @param nombreProceso
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws AplicacionException
	 */
	public static List<Producto> obtenerProductosPorGrupo(String nombreGrupo, String nombreProceso)
			throws EntornoEjecucionException, AplicacionException {

		String queryString = "select pap.produccion.producto from Plantillaajusteproducto as pap"
				+ " where lower(pap.plantillagrupoajuste.nombrePlantillagrupoajuste) = lower(:nombreGrupo) "
				+ " and lower(pap.produccion.proceso.nombreProceso) = lower(:nombreProceso) "
				+ " order by pap.produccion.producto.nombreProducto asc";
		try {

			Query query = Querier.query(queryString);

			query.setString("nombreGrupo", nombreGrupo);
			query.setString("nombreProceso", nombreProceso);

			return (List<Producto>) query.list();

		} catch (QueryException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (EntornoEjecucionException e) {
			throw e;

		} catch (HibernateException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerQ.error(mensajeError);
			throw new AplicacionException(mensajeError, e);
		} catch (RuntimeException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			loggerQ.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}
}
