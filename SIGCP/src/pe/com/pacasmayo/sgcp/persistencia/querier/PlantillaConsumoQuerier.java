package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaconsumo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Proceso;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class PlantillaConsumoQuerier extends Querier {

	public static final String CODIGO_PROCESO = "proceso.pkCodigoProceso";
	public static final String CODIGO_PRODUCTO = "producto.pkCodigoProducto";
	public static final String CODIGO_PUESTO_TRABAJO = "puestotrabajo.pkCodigoPuestotrabajo";
	public static final String CODIGO_SOCIEDAD = "sociedad.pkCodigoSociedad";

	/**
	 * Método para obtener un Producto por medio del código
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static Plantillaconsumo getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Plantillaconsumo.class, codigo);
	}

	/**
	 * Método para eliminar un Producto de la BD.
	 * 
	 * @param plantillaconsumo
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Plantillaconsumo plantillaconsumo) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(plantillaconsumo);
	}

	public static Plantillaconsumo obtenerPlantillaConsumo(Proceso proceso, Producto producto, Componente componente)
			throws AplicacionException {
		String mensaje;
		StringBuilder sql = new StringBuilder("FROM Plantillaconsumo pc ");
		sql.append(" WHERE ");
		sql.append(" pc.producto.pkCodigoProducto=:producto ");
		sql.append(" AND pc.proceso.pkCodigoProceso=:proceso ");
		sql.append(" AND pc.componente.productoByFkCodigoProductoComponente.pkCodigoProducto=:componente ");

		sql.append(" AND pc.estado=:estado ");

		Query query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());
		query.setLong("proceso", proceso.getPkCodigoProceso());
		query.setLong("componente", componente.getProductoByFkCodigoProductoComponente().getPkCodigoProducto());

		query.setBoolean("estado", Boolean.TRUE);

		Plantillaconsumo plantillaConsumo = null;
		try {
			plantillaConsumo = (Plantillaconsumo) query.uniqueResult();

		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CODIGO_SAP_NO_UNICO);
			mensaje = MessageFormat.format(mensaje, new Object[] { proceso.getNombreProceso(), producto.getNombreProducto(),
					componente.getProductoByFkCodigoProductoComponente().getNombreProducto() });
			throw new AplicacionException(mensaje, e.getCause());
		}

		return plantillaConsumo;
	}

	public static Plantillaconsumo obtenerPlantillaConsumo(Long codigoProductoAjusteProducto, Long codigoProductoComponente)
			throws AplicacionException {
		String mensaje;
		StringBuilder sql = new StringBuilder("FROM Plantillaconsumo pc ");
		sql.append(" WHERE ");
		sql.append(" pc.producto.pkCodigoProducto=:codigoProductoAjusteProducto ");
		sql.append(" AND pc.componente.productoByFkCodigoProductoComponente.pkCodigoProducto=:codigoProductoComponente ");
		sql.append(" AND pc.estado=:estado ");

		Query query = query(sql.toString());
		query.setLong("codigoProductoAjusteProducto", codigoProductoAjusteProducto);
		query.setLong("codigoProductoComponente", codigoProductoComponente);

		query.setBoolean("estado", Boolean.TRUE);

		Plantillaconsumo plantillaConsumo = null;
		try {
			plantillaConsumo = (Plantillaconsumo) query.uniqueResult();

		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CODIGO_SAP_NO_UNICO);
			throw new AplicacionException(mensaje, e.getCause());
		}

		return plantillaConsumo;
	}

	public static Plantillaconsumo obtenerPlantillaConsumoByProductoPuestoTrabajo(Long codigoProducto, Long codigoPuestoTrabajo)
			throws AplicacionException {
		String mensaje;
		StringBuilder sql = new StringBuilder("FROM Plantillaconsumo pc ");
		sql.append(" WHERE ");
		sql.append(" pc.producto.pkCodigoProducto=:producto ");
		sql.append(" AND pc.puestotrabajo.pkCodigoPuestotrabajo=:puestotrabajo ");
		sql.append(" AND pc.estado=:estado ");

		Query query = query(sql.toString());
		query.setLong("producto", codigoProducto);
		query.setLong("puestotrabajo", codigoPuestoTrabajo);

		query.setBoolean("estado", Boolean.TRUE);

		Plantillaconsumo plantillaConsumo = null;
		try {
			plantillaConsumo = (Plantillaconsumo) query.uniqueResult();

		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			mensaje = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_CODIGO_SAP_NO_UNICO);
			throw new AplicacionException(mensaje, e.getCause());
		}

		return plantillaConsumo;
	}

	public static List<Plantillaconsumo> obtenerPlantillaConsumoPorPropiedades(Map<String, Long> mapaPropiedades) {
		// TODO Auto-generated method stub
		return Querier.findByProperties(Plantillaconsumo.class, mapaPropiedades);
	}

}
