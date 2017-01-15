package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.UnresolvableObjectException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.transform.Transformers;

import pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.PuestoTrabajoProduccionGraficoBean;
import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablaoperacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class TablaOperacionQuerier extends Querier {

	private static String mensajeError;
	private static Logger logger = Logger.getLogger(TablaOperacionQuerier.class);
	private static final String NOMBRE_CLASE_TABLA_OPERACION = Tablaoperacion.class.getSimpleName();
	private static final String PRODUCCION_PUESTO_TRABAJO = "produccionpuestotrabajo.pkCodigoProduccionpuestotraba";

	public static List<Tablaoperacion> obtenerTablaOperacion(Long codigoPuestoTrabajo, Long codigoLineaNegocio, Integer anno,
			Short mes) throws AplicacionException {

		try {

			String queryString = "select tablaOperacion "
					+ "from Tablaoperacion tablaOperacion "
					+ "where tablaOperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo = :pkCodigoPuestotrabajo and "
					+ "tablaOperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :pkCodigoLineanegocio and "
					+ "tablaOperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anoPeriodocontable and "
					+ "tablaOperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mesPeriodocontable ORDER BY fechaTablaoperacion ASC";
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("pkCodigoPuestotrabajo", codigoPuestoTrabajo);
			parameters.put("pkCodigoLineanegocio", codigoLineaNegocio);
			parameters.put("anoPeriodocontable", anno);
			parameters.put("mesPeriodocontable", mes);

			return Querier.executeQuery(queryString, parameters);

		} catch (RuntimeException e) {
			throw new AplicacionException(e);
		}
	}

	public static Tablaoperacion obtenerPorPuestoTrabajoyFecha(Long pkCodigoProduccionpuestotraba, Date fecha)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		String consulta = "FROM {0} AS pc where pc.{1} = :pk AND pc.{2} = :fecha";
		consulta = MessageFormat.format(consulta, new Object[] { Tablaoperacion.class.getName(), "pkCodigoTablaoperacion",
				"fechaTablaoperacion" });

		try {
			Query query = Querier.query(consulta);
			query.setLong("pk", pkCodigoProduccionpuestotraba);
			query.setDate("fecha", fecha);

			Tablaoperacion tablaoperacion = (Tablaoperacion) query.uniqueResult();
			return tablaoperacion;

		} catch (NonUniqueResultException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException);
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException);
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	public static List<Tablaoperacion> obtenerPorProduccionPuestoTrabajo(Long codigoProduccionPuestoTrabajo)
			throws ElementoNoEncontradoException, EntornoEjecucionException {
		String consulta = "FROM {0} AS to where to.{1} = :codigoProduccionPuestoTrabajo ";
		consulta = MessageFormat.format(consulta, new Object[] { NOMBRE_CLASE_TABLA_OPERACION, PRODUCCION_PUESTO_TRABAJO });

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoProduccionPuestoTrabajo", codigoProduccionPuestoTrabajo);

			List<Tablaoperacion> tablaOperacion = query.list();
			return tablaOperacion;

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	public static void eliminarPorFechaLineaNeg(Long lineaNegocio, Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		Integer anio = cal.get(Calendar.YEAR);

		StringBuilder querystr = new StringBuilder("DELETE FROM  Tablaoperacion to ");
		querystr.append(" WHERE to.fechaTablaoperacion = :fechaTablaOperacion AND to.produccionpuestotrabajo in ");
		querystr.append(" (SELECT pt FROM Produccionpuestotrabajo  pt WHERE ");
		querystr.append("  pt.partediario.periodocontable.mesPeriodocontable = :mesPartediario  ");
		querystr.append("  AND  pt.partediario.periodocontable.anoPeriodocontable = :anoPartediario  ");
		querystr.append("  AND  pt.partediario.lineanegocio.pkCodigoLineanegocio = :lineNegParteDiario ) ");

		Query query = Querier.query(querystr.toString());
		query.setDate("fechaTablaOperacion", fecha);
		query.setShort("mesPartediario", mes.shortValue());
		query.setInteger("anoPartediario", anio);
		query.setLong("lineNegParteDiario", lineaNegocio);

		query.executeUpdate();

	}

	

	public static List<PuestoTrabajoProduccionGraficoBean> obtenerProduccionPuestoTrabajo(Integer anio, Short mes, Long unidad,
			Long[] puestostrabajos, String nombre, Boolean muestraTMPH, Long[] procesos) {

		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append("new pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.PuestoTrabajoProduccionGraficoBean( ");
		sql.append("  day(pg.tablaoperacion.fechaTablaoperacion) ");
		sql.append("  ,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo");

		sql.append("  ,SUM(pg.produccionTmProductogenerado) as prod ");

		sql.append("  ,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.siglasPuestotrabajo");

		if (muestraTMPH) {

			sql.append("  ,'" + nombre + "'");
			sql.append("  ,'" + nombre + "'||pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo");
			sql.append("  ,SUM(pg.horasProductogenerado) AS p ");
		}
		sql.append("   )");
		sql.append("  FROM  Productogenerado pg ");
		sql.append("  WHERE ");
		sql.append(" pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mesPartediario  ");
		sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anoPartediario  ");
		sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.unidad.pkCodigoUnidad = :unidad  ");
		sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo in (:puestostrabajos) ");
		sql.append(" AND  pg.ordenproduccion.produccion.proceso.pkCodigoProceso in (:procesos) ");
		sql.append(" GROUP BY  pg.tablaoperacion.fechaTablaoperacion,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.siglasPuestotrabajo  ");
		// if (muestraTMPH) {
		//
		// sql.append("  ,'" + nombre + "'");
		// sql.append("  ,'" + nombre +
		// "'||pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo");
		//
		// }
		sql.append(" ORDER BY  pg.tablaoperacion.fechaTablaoperacion,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo ASC ");

		// sql.append("new pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.PuestoTrabajoProduccionGraficoBean( ");
		// sql.append("  day(pg.tablaoperacion.fechaTablaoperacion)");
		// sql.append("  ,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo");
		// sql.append("  ,SUM(pg.produccionTmProductogenerado)");
		// sql.append("  ,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.siglasPuestotrabajo");
		// if (muestraTMPH) {
		// sql.append("  ,'" + nombre + "'");
		// sql.append("  ,'" + nombre +
		// "'||pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo");
		// sql.append("  ,SUM(pg.horasProductogenerado) ");
		// }
		// sql.append("   )");
		// sql.append("  FROM  Productogenerado pg ");
		// sql.append("  WHERE ");
		// sql.append(" pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mesPartediario  ");
		// sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anoPartediario  ");
		// sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.unidad.pkCodigoUnidad = :unidad  ");
		// sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo in (:puestostrabajos) ");
		// sql.append(" GROUP BY  day(pg.tablaoperacion.fechaTablaoperacion),pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.siglasPuestotrabajo  ");
		// if (muestraTMPH) {
		//
		// sql.append("  ,'" + nombre + "'");
		// sql.append("  ,'" + nombre +
		// "'||pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo");
		//
		// }
		// sql.append(" ORDER BY  day(pg.tablaoperacion.fechaTablaoperacion),pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo ASC ");

		Query query = Querier.query(sql.toString());

		query.setShort("mesPartediario", mes.shortValue());
		query.setInteger("anoPartediario", anio);
		query.setLong("unidad", unidad);
		query.setParameterList("puestostrabajos", puestostrabajos);
		query.setParameterList("procesos", procesos);

		// List<Object> p = query.list();

		return query.list();

	}
	
	

	public static List<PuestoTrabajoProduccionGraficoBean> obtenerProduccionPuestoTrabajoHoras(Integer anio, Short mes,
			Long unidad, Long[] puestostrabajos, String nombre, Boolean muestraTMPH, Long[] procesos) {

		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append("new pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion.PuestoTrabajoProduccionGraficoBean( ");
		sql.append("  day(pg.tablaoperacion.fechaTablaoperacion)");
		sql.append("  ,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo");
		sql.append("  ,SUM(pg.produccionTmProductogenerado)");
		sql.append("  ,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.siglasPuestotrabajo");
		if (muestraTMPH) {
			sql.append("  ,'" + nombre + "'");
			sql.append("  ,SUM(pg.horasProductogenerado)");
		}
		sql.append("   )");
		sql.append("  FROM  Productogenerado pg ");
		sql.append("  WHERE ");
		sql.append(" pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mesPartediario  ");
		sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anoPartediario  ");
		sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.unidad.pkCodigoUnidad = :unidad  ");
		sql.append(" AND  pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo in (:puestostrabajos) ");
		sql.append(" AND  pg.ordenproduccion.produccion.proceso.pkCodigoProceso in (:procesos) ");
		
		sql.append(" GROUP BY  pg.tablaoperacion.fechaTablaoperacion,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.siglasPuestotrabajo  ");
		sql.append(" ORDER BY  pg.tablaoperacion.fechaTablaoperacion,pg.tablaoperacion.produccionpuestotrabajo.puestotrabajo.nombrePuestotrabajo ASC");

		Query query = Querier.query(sql.toString());

		query.setShort("mesPartediario", mes.shortValue());
		query.setInteger("anoPartediario", anio);
		query.setLong("unidad", unidad);
		query.setParameterList("puestostrabajos", puestostrabajos);
		query.setParameterList("procesos", procesos);
		return query.list();

	}
	
	public static void main(String[] args) {
		// "96;97;98;99;100;101;102;112;113,TMPH,true,true,PRODUCCIÓN CLINKER,_,_,_"
		Long[] ss = new Long[] { 96L, 97L, 98L};
		Long[] procesp = new Long[] { 39L };
		TablaOperacionQuerier.obtenerProduccionPuestoTrabajoKW(2016, Short.valueOf("04"), 1L, 96L, "PRODUCCIÓN CLINKER", true,"",
				procesp);
	}

	public static List<PuestoTrabajoProduccionGraficoBean> obtenerProduccionPuestoTrabajoKW(Integer anio, Short mes, Long unidad,
			Long puestotrabajo, String nombre, Boolean muestraTMPH,String nombreFactor,Long[] procesos) {

		StringBuilder sql = new StringBuilder("SELECT ");

		sql.append(" CAST(date_part('day',tp.fecha_tablaoperacion) as int) as dia");
		sql.append(" ,pt.nombre_puestotrabajo as \"nombrePuestoTrabajo\"");
		sql.append(" ,CAST(SUM(pg.produccion_tm_productogenerado) as double precision) as toneladasproducidas");
		sql.append(" ,pt.siglas_puestotrabajo as \"siglaProducto\"");
		sql.append(" ," + muestraTMPH + " as reportetoneladasporhora");
		if (muestraTMPH) {
			sql.append("  ,CAST('" + nombre + "' as varchar) as \"nombreEjeY\" ");
			sql.append("  ,CAST('" + nombre + "'||pt.nombre_puestotrabajo as varchar) as \"nombrePuestoTrabajoporhora\" ");
			sql.append("  ,CAST(dbl.valor as double precision) as toneladasproducidasporhora");
			sql.append("  ,CAST(SUM(pg.horas_productogenerado) as double precision) as \"horaProduccion\" ");
		}
		sql.append(" FROM  productogenerado pg ");
		sql.append(" INNER JOIN tablaoperacion tp ON tp.pk_codigo_tablaoperacion = pg.fk_codigo_tablaoperacion ");
		sql.append(" INNER JOIN produccionpuestotrabajo ppt ON tp.fk_codigo_produccionpuestotraba = ppt.pk_codigo_produccionpuestotraba ");
		sql.append(" INNER JOIN partediario pd ON ppt.fk_codigo_partediario = pd.pk_codigo_partediario ");
		sql.append(" INNER JOIN periodocontable pc ON pd.fk_codigo_peridocontable = pc.pk_codigo_peridocontable ");
		sql.append(" INNER JOIN lineanegocio ln ON pd.fk_codigo_lineanegocio = ln.pk_codigo_lineanegocio ");
		sql.append(" INNER JOIN unidad uni ON ln.fk_codigo_unidad = uni.pk_codigo_unidad ");
		sql.append(" INNER JOIN puestotrabajo pt ON ppt.fk_codigo_puestotrabajo = pt.pk_codigo_puestotrabajo ");
		sql.append(" INNER JOIN ordenproduccion op ON op.pk_codigo_ordenproduccion = pg.fk_codigo_ordenproduccion  ");
		sql.append(" INNER JOIN produccion p ON p.pk_produccion = op.fk_produccion  ");
		
		
		sql.append(" INNER JOIN (SELECT t1.id_puesto_trabajo, t1.valor, t1.fecha ");
		sql.append(" FROM dblink('host=10.34.2.68 port=5432 user=postgres password=postgres dbname=SCE', ");
		sql.append("'select id_puesto_trabajo,valor,fecha from sce.sce_vis_reporte_cons_esp_detallado where date_part(\\'month\\', fecha) = "
				+ mes);
		sql.append(" and date_part(\\'year\\', fecha) = " + anio + " and id_puesto_trabajo = " + puestotrabajo
				+ " and nombre_factor = \\'" + nombreFactor +"\\' order by fecha') ");
		sql.append(" t1(id_puesto_trabajo numeric(10,0),valor numeric(10,2), fecha date)) dbl ");
		sql.append(" ON dbl.id_puesto_trabajo = pt.pk_codigo_puestotrabajo AND dbl.fecha =  tp.fecha_tablaoperacion ");

		sql.append(" WHERE pc.mes_periodocontable = :mesPartediario  ");
		sql.append(" AND  pc.ano_periodocontable = :anoPartediario  ");
		sql.append(" AND  uni.pk_codigo_unidad = :unidad  ");
		sql.append(" AND  pt.pk_codigo_puestotrabajo = :puestotrabajo ");
		sql.append(" AND  p.fk_codigo_proceso IN (:procesos) ");
		sql.append(" GROUP BY  tp.fecha_tablaoperacion,pt.nombre_puestotrabajo ,pt.siglas_puestotrabajo,dbl.valor  ");
		sql.append(" ORDER BY  tp.fecha_tablaoperacion,pt.nombre_puestotrabajo ASC ");

		Query query = Querier.querySQL(sql.toString()).setResultTransformer(
				Transformers.aliasToBean(PuestoTrabajoProduccionGraficoBean.class));

		query.setShort("mesPartediario", mes.shortValue());
		query.setInteger("anoPartediario", anio);
		query.setLong("unidad", unidad);
		query.setLong("puestotrabajo", puestotrabajo);
		query.setParameterList("procesos", procesos);

		return query.list();

	}

	public static Date obtenerUltimoDiaProduccionCrudoNegro(String nombreProducto) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT pg.tablaoperacion.fechaTablaoperacion ");

		sql.append(" FROM Productogenerado pg");
		sql.append(" WHERE ");
		sql.append(" lower(pg.ordenproduccion.produccion.producto.nombreProducto) = :nombreProducto  ");
		sql.append(" ORDER BY pg.tablaoperacion.fechaTablaoperacion DESC ");

		Query query = Querier.query(sql.toString());
		query.setString("nombreProducto", nombreProducto.toLowerCase());
		query.setMaxResults(1);
		return (Date) query.uniqueResult();
	}
}
