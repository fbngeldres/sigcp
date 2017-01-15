package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.bean.AprobarAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Movimientoajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class MovimientoAjusteProductoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para Insertar una Movimientoajusteproducto en la BD.
	 * 
	 * @param movimientoajusteproducto
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Movimientoajusteproducto movimientoajusteproducto) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(movimientoajusteproducto);
	}

	public static List<Movimientoajusteproducto> obtenerMovimientoAjusteProductoByCodigoAjusteProducto(Long codigoLineaNegocio,
			Long codigoUnidad, List<Long> codigoProducto, Integer anio, Short mes, String nombreGrupo) {
		StringBuilder sql = new StringBuilder("FROM Movimientoajusteproducto map");
		sql.append(" WHERE ");
		sql.append(" map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio");
		sql.append(" and map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad");
		sql.append(" and map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
		sql.append(" and map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
		sql.append(" and lower(map.puestotrabajoproduccion.ajusteproducto.plantillagrupoajuste.nombrePlantillagrupoajuste)	 = lower(:nombreGrupo) ");
		sql.append(" and map.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto in (:listaProductos) ");
		Query query = query(sql.toString());
		query.setLong("codigoLineaNegocio", codigoLineaNegocio);
		query.setLong("codigoUnidad", codigoUnidad);
		query.setInteger("anio", anio);
		query.setShort("mes", mes);
		query.setString("nombreGrupo", nombreGrupo);
		query.setParameterList("listaProductos", codigoProducto);
		return query.list();
	}

	public static Double getSumaConsumoComponentes(Short mes, int anio, Long componente, Long producto) {
		StringBuilder sql = new StringBuilder("SELECT SUM (cantidadMovimientoajusteproducto)FROM Movimientoajusteproducto map");
		sql.append(" WHERE ");
		// sql.append("
		// map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.pkCodigoLineanegocio
		// = :codigoLineaNegocio");
		// sql.append(" and
		// map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.lineanegocio.unidad.pkCodigoUnidad
		// = :codigoUnidad");
		sql.append(" map.producto.pkCodigoProducto  = :componente");
		sql.append(" and map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio");
		sql.append(" and map.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes");
		sql.append(" and map.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto in (:listaProductos) ");
		Query query = query(sql.toString());
		// query.setLong("codigoLineaNegocio", codigoLineaNegocio);
		// query.setLong("codigoUnidad", codigoUnidad);
		query.setInteger("anio", anio);
		query.setShort("mes", mes);
		query.setLong("componente", componente);
		query.setLong("listaProductos", producto);
		Double sumaCantidadMovimientoAjuste = (Double) query.uniqueResult();
		if (sumaCantidadMovimientoAjuste == null) {
			sumaCantidadMovimientoAjuste = 0d;
		}
		return sumaCantidadMovimientoAjuste;
	}

	public static List<AprobarAjusteProduccionBean> obtenerAjusteComponenteConsolidado(Long ajusteProduccion) {

		StringBuffer sql = new StringBuffer("SELECT new pe.com.pacasmayo.sgcp.bean.impl.AprobarAjusteProduccionBeanImpl(");
		sql.append(" mapro.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto,");
		sql.append(" mapro.puestotrabajoproduccion.ajusteproducto.producto.nombreProducto,");
		sql.append(" mapro.puestotrabajoproduccion.ajusteproducto.producto.codigoSapProducto,");
		sql.append(" mapro.producto.pkCodigoProducto ,");
		sql.append(" mapro.producto.nombreProducto,");
		sql.append(" mapro.producto.codigoSapProducto,");
		sql.append(" SUM(mapro.cantidadMovimientoajusteproducto) )");
		sql.append("  FROM Movimientoajusteproducto mapro ");
		sql.append(" WHERE ");
		sql.append("  mapro.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.pkCodigoAjusteproduccion = :codigoAjusteProduccion ");
		sql.append("  GROUP BY  ");
		sql.append(" mapro.puestotrabajoproduccion.ajusteproducto.producto.pkCodigoProducto,");
		sql.append(" mapro.puestotrabajoproduccion.ajusteproducto.producto.nombreProducto,");
		sql.append(" mapro.puestotrabajoproduccion.ajusteproducto.producto.codigoSapProducto,");
		sql.append(" mapro.producto.pkCodigoProducto ,");
		sql.append(" mapro.producto.nombreProducto,");
		sql.append(" mapro.producto.codigoSapProducto");
		Query query = query(sql.toString());
		query.setLong("codigoAjusteProduccion", ajusteProduccion);

		return query.list();
	}
}
