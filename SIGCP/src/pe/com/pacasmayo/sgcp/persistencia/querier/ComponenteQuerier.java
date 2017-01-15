package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ComponenteQuerier.java
 * Modificado: Feb 5, 2010 4:52:34 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Componente;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ConstantesParametro;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ComponenteQuerier extends Querier implements
		ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	public final static String ESTADO_HOJARUTACOMPONENTE_HOJARUTA = "hojarutacomponentes.hojaruta.estadohojaruta.pkCodigoEstadohojaruta";
	public final static String CODIGO_PRODUCTO = "productoByFkCodigoProducto.pkCodigoProducto";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Componente
	 * 
	 * @return
	 */
	public static List<Componente> getAll() throws AplicacionException {

		return Querier.getAll(Componente.class);
	}

	/**
	 * Método para obtener la lista de objetos Componente, ordenados por un
	 * campo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Componente> getAllOrderBy(String order) {

		return Querier.getAll(Componente.class, order);
	}

	/**
	 * Método para obtener una Componente de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Componente getById(Long codigo)
			throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return Querier.getById(Componente.class, codigo);
	}

	/**
	 * Método para obtener un Componente por medio del código del producto y del
	 * codigo del producto componente
	 * 
	 * @param codigoProducto
	 * @param codigoProductoComponente
	 */
	public static Componente getByProductoProductoComponente(
			Long codigoProductoComponente) {

		String consulta = "from Componente comp "
				+ "where comp.productoByFkCodigoProducto.pkCodigoProducto = ? "
				+ "and comp.productoByFkCodigoProductoComponente.pkCodigoProducto = ?  ";

		Query query = Querier.query(consulta);
		query.setLong(1, codigoProductoComponente);

		return (Componente) query.uniqueResult();
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Componente en la BD.
	 * 
	 * @param componente
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Componente componente)
			throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(componente);
	}

	/**
	 * Metodo para modificar una Componente de la BD.
	 * 
	 * @param componente
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Componente componente)
			throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(componente);
	}

	/**
	 * Metodo para eliminar una Componente de la BD.
	 * 
	 * @param componente
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Componente componente)
			throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(componente);
	}

	/**
	 * Método para obtener una lista de componentes según propiedades de
	 * filtrado específicas.
	 * 
	 * @param propiedades
	 *            Propiedades de filtrado.
	 * @return Lista de Componentes
	 */
	public static List<Componente> findByProperties(Map propiedades) {
		return Querier.findByProperties(Componente.class, propiedades);
	}

	/**
	 * Obtiene la lista de componentes y su consumo mensual.
	 * 
	 * @param codigoPlanAnual
	 * @param codigoProducto
	 * @param codigoEstadoHojaRuta
	 * @param codigoConcepto
	 * @return
	 * @throws AplicacionException
	 * @throws AplicacionException
	 */
	public static List<Componente> findConsumoComponente(Long codigoPlanAnual,
			Long codigoProducto, Long codigoEstadoHojaRuta,
			Long codigoConcepto, Long codigoProduccion) {

		String queryString = "from Componente componente "
				+ "where componente.productoByFkCodigoProducto.pkCodigoProducto = :pkCodigoProducto and "
				+ "componente.pkCodigoComponente in (select hrc.componente.pkCodigoComponente from Hojarutacomponente hrc where hrc.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :pkCodigoEstadohojaruta  and hrc.hojaruta.produccion.pkProduccion = :pkProduccion ) "
				+ "order by componente.productoByFkCodigoProductoComponente.nombreProducto asc ";

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("pkCodigoProducto", codigoProducto);
		parameters.put("pkCodigoEstadohojaruta", codigoEstadoHojaRuta);
		parameters.put("pkProduccion", codigoProduccion);

		List<Componente> lista = Querier.executeQuery(queryString, parameters);
		List<Componente> listaComponenteRetornar = new ArrayList<Componente>();

		for (Componente componente : lista) {
			if (!listaComponenteRetornar.contains(componente))
				listaComponenteRetornar.add(componente);
		}

		return listaComponenteRetornar;

	}

	/**
	 * Método que consulta la hoja de ruta activa de una producción definida por
	 * el proceso, producto especifico y tipo de producto MP
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param activo
	 * @return
	 */
	public static List<Object[]> obtenerComponentesPorHojaRutaMateriaPrima(
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio) {
		List<Object[]> productosProcesos = new ArrayList<Object[]>();
		String mensajeError = "";

		try {

			String consultarUnidad = "and pro.proceso.lineanegocio.unidad.pkCodigoUnidad = ? ";
			String consultarLineaNegocio = "and pro.proceso.lineanegocio.pkCodigoLineanegocio = ? ";

			String consulta = "select distinct pro.producto, pro.proceso "
					+ "from Hojaruta hr, Produccion pro, Producto p "
					+ "where "
					+ "pro.proceso.tipoproducto.siglasTipoproducto = 'MP' and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = ? and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = ? "
					+ (codigoUnidad.equals(new Long(-1)) ? "" : consultarUnidad)
					+ (codigoLineaNegocio.equals(new Long(-1)) ? ""
							: consultarLineaNegocio)
					+ "and hr.produccion = pro and "
					+ "hr.estadohojaruta.pkCodigoEstadohojaruta = 1 "
					+ "order by pro.proceso.lineanegocio.pkCodigoLineanegocio ,pro.proceso.ordenEjecucionProceso ";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoDivision);
			query.setLong(1, codigoSociedad);

			if (!codigoUnidad.equals(new Long(-1))
					&& !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(2, codigoUnidad);
				query.setLong(3, codigoLineaNegocio);
			}

			if (codigoUnidad.equals(new Long(-1))
					&& !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(2, codigoLineaNegocio);
			}

			if (!codigoUnidad.equals(new Long(-1))
					&& codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(2, codigoUnidad);
			}

			productosProcesos = query.list();

		} catch (QueryException e) {

			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productosProcesos;
	}

	/**
	 * Método que consulta la hoja de ruta activa de una producción definida por
	 * el proceso, producto especifico y tipo de producto (siglas)
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param activo
	 * @return List<Object[Producto,Proceso]>
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerComponentesPorHojaRutaTipoProducto(
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio) {
		List<Object[]> productosProcesos = new ArrayList<Object[]>();
		String mensajeError = "";

		try {

			String consultarUnidad = "and pro.proceso.lineanegocio.unidad.pkCodigoUnidad = ? ";
			String consultarLineaNegocio = "and pro.proceso.lineanegocio.pkCodigoLineanegocio = ? ";

			String consulta = "select distinct pro.producto, pro.proceso "
					+ "from Hojaruta hr, Produccion pro, Producto p "
					+ "where "
					+ "pro.proceso.tipoproducto.siglasTipoproducto <> 'MP' and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = ? and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = ? "
					+ (codigoUnidad.equals(new Long(-1)) ? "" : consultarUnidad)
					+ (codigoLineaNegocio.equals(new Long(-1)) ? ""
							: consultarLineaNegocio)
					+ "and hr.produccion = pro and "
					+ "hr.estadohojaruta.pkCodigoEstadohojaruta = 1 "
					+ "order by pro.proceso.lineanegocio.pkCodigoLineanegocio,pro.proceso.ordenEjecucionProceso ";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoDivision);
			query.setLong(1, codigoSociedad);

			if (!codigoUnidad.equals(new Long(-1))
					&& !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(2, codigoUnidad);
				query.setLong(3, codigoLineaNegocio);
			}

			if (codigoUnidad.equals(new Long(-1))
					&& !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(2, codigoLineaNegocio);
			}

			if (!codigoUnidad.equals(new Long(-1))
					&& codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(2, codigoUnidad);
			}

			productosProcesos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productosProcesos;
	}

	/**
	 * Método que consulta la hoja de ruta activa de una producción definida por
	 * el proceso, producto especifico y tipo de producto (siglas)
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param activo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerComponentesPorHojaRutaProduccionYTipoProducto(
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoProducto, boolean activo, String tipoProducto,
			Long codigoLineaNegocio) {
		List<Object[]> componentes = new ArrayList<Object[]>();
		String mensajeError = "";

		try {

			String consultarUnidad = "and hr.produccion.proceso.lineanegocio.unidad.pkCodigoUnidad = ? ";
			String consultarLineaNegocio = "and hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio = ? ";

			String consulta = "select hrcomp.componente, hrcomp.hojaruta.produccion.proceso, hrcomp from Hojaruta hr, Hojarutacomponente hrcomp "
					+ "where "
					+ "hr.produccion.producto.pkCodigoProducto = ? and "
					+ "hr.produccion.producto.estadoproducto.pkCodigoEstadoproducto = 1 and "
					+ "hr.produccion.proceso.tipoproducto.siglasTipoproducto = ? and "
					+ "hr.estadohojaruta.pkCodigoEstadohojaruta = ? and "
					+ "hr.produccion.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = ? and "
					+ "hr.produccion.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = ? "
					+ (codigoUnidad.equals(new Long(-1)) ? "" : consultarUnidad)
					+ (codigoLineaNegocio.equals(new Long(-1)) ? ""
							: consultarLineaNegocio)
					+ " and hrcomp.hojaruta = hr "
					+ "order by hr.produccion.proceso.ordenEjecucionProceso,hr.produccion.proceso.tipoproducto.pkCodigoTipoproducto  desc";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoProducto);
			query.setString(1, tipoProducto);
			query.setLong(2, new Long(1));
			query.setLong(3, codigoDivision);
			query.setLong(4, codigoSociedad);

			if (!codigoUnidad.equals(new Long(-1))
					&& !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(5, codigoUnidad);
				query.setLong(6, codigoLineaNegocio);
			}

			if (codigoUnidad.equals(new Long(-1))
					&& !codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(5, codigoLineaNegocio);
			}

			if (!codigoUnidad.equals(new Long(-1))
					&& codigoLineaNegocio.equals(new Long(-1))) {
				query.setLong(5, codigoUnidad);
			}

			componentes = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return componentes;
	}

	/**
	 * Método que consulta la hoja de ruta activa de una producción definida por
	 * el proceso y producto especifico
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param activo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Componente> obtenerComponentesPorHojaRutaProduccion(
			Long codigoProceso, Long codigoProducto, boolean activo) {
		List<Componente> componentes = new ArrayList<Componente>();
		String mensajeError = "";

		try {
			String consultarEstado = " hr.estadohojaruta.pkCodigoEstadohojaruta = 1 and ";
			String consultarProceso = "hr.produccion.proceso.pkCodigoProceso = ? and ";
			String consulta = "select hrcomp.componente from Hojaruta hr, Hojarutacomponente hrcomp "
					+ "where "
					+ "hr.produccion.producto.pkCodigoProducto = ? and "
					+ (codigoProceso.equals(new Long(-1)) ? ""
							: consultarProceso)
					+ (activo ? consultarEstado : "")
					+ "hrcomp.hojaruta.pkCodigoHojaruta = hr.pkCodigoHojaruta";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoProducto);

			if (!codigoProceso.equals(new Long(-1)))
				query.setLong(1, codigoProceso);

			componentes = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return componentes;
	}

	/**
	 * Método que consultatodos los componentes de las hojas de ruta activas
	 * 
	 * @return List<Componente>
	 */
	@SuppressWarnings("unchecked")
	public static List<Componente> obtenerComponentesDeHojasRutaActivas() {
		List<Componente> componentes = new ArrayList<Componente>();
		String mensajeError = "";

		Long codigoEstadoHRActiva = Long
				.parseLong(ManejadorPropiedades
						.obtenerPropiedadPorClave(ConstantesLogicaNegocio.ESTADO_HOJA_RUTA_ACTIVA));

		try {
			String consulta = "SELECT DISTINCT(hrcomp.componente) FROM Hojarutacomponente hrcomp WHERE "
					+ "hrcomp.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = :pkCodigoEstadohojaruta";

			Query query = Querier.query(consulta);
			query.setLong("pkCodigoEstadohojaruta", codigoEstadoHRActiva);

			componentes = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return componentes;
	}

	@SuppressWarnings("unchecked")
	public static List<Componente> getByCodigoProducto(Long codigoProducto) {

		String consulta = "from Componente c where c.productoByFkCodigoProducto.pkCodigoProducto = ? ";

		Query query = Querier.query(consulta);
		query.setLong(0, codigoProducto);

		return query.list();
	}

	// RegistroDistribuible - Carga Automatica
	@SuppressWarnings("unchecked")
	public static List<Long> getByCodigoProductoPorComponente(
			Long codigoComponente) {

		String consulta = "SELECT c.productoByFkCodigoProducto.pkCodigoProducto FROM "
				+ "Componente c where c.pkCodigoComponente = :pkCodigoComponente";

		Query query = Querier.query(consulta);
		query.setLong("pkCodigoComponente", codigoComponente);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Long> getByCodigoProductoComponentePorComponente(
			Long codigoComponente) {

		String consulta = "SELECT c.productoByFkCodigoProductoComponente.pkCodigoProducto FROM "
				+ "Componente c where c.pkCodigoComponente = :pkCodigoComponente";

		Query query = Querier.query(consulta);
		query.setLong("pkCodigoComponente", codigoComponente);

		return query.list();
	}

	public static List<Long> getByCodigoPorComponenteYProducto(
			List<Long> productos, List<Long> componentes) {

		String consulta = "SELECT c.pkCodigoComponente FROM "
				+ "Componente c where c.productoByFkCodigoProductoComponente.pkCodigoProducto in (:componentes)"
				+ " AND c.productoByFkCodigoProducto.pkCodigoProducto in (:productos)";

		Query query = Querier.query(consulta);
		query.setParameterList("componentes", componentes);
		query.setParameterList("productos", productos);

		return query.list();
	}

	/**
	 * Obtengo la suma de TM Real Consumo de un producto
	 * 
	 * @param producto
	 * @return
	 */
	public static Double getSumaConsumoComponentePorProducto(Short mes,
			Integer anio, Long producto) {

		String consulta = "SELECT SUM(cca.tmRealConsumocomponenteajuste) FROM Consumocomponenteajuste cca "
				+ " where cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.mesPeriodocontable = :mes"
				+ " AND cca.puestotrabajoproduccion.ajusteproducto.ajusteproduccion.periodocontable.anoPeriodocontable = :anio"
				+ " AND cca.componente.pkCodigoComponente "
				+ "in(SELECT c.pkCodigoComponente FROM Componente c "
				+ " where c.productoByFkCodigoProducto.pkCodigoProducto in (:producto))";

		Query query = Querier.query(consulta);
		query.setShort("mes", mes);
		query.setInteger("anio", anio);
		query.setLong("producto", producto);
		Double cantidad = (Double) query.uniqueResult();
		if (cantidad == null) {
			cantidad = 0.0;
		}
		return cantidad;
	}

	// Terminar aqui registro distribuible

	@SuppressWarnings("unchecked")
	public static List<Componente> getComponentesParaHojaRuta(
			Long codigoProducto) throws SesionVencidaException,
			EntornoEjecucionException {
		String mensajeError = "";
		try {
			String consulta = "SELECT DISTINCT c FROM Componente c WHERE c.productoByFkCodigoProducto.pkCodigoProducto != ? "
					+ "order by c.productoByFkCodigoProducto.nombreProducto ASC";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoProducto);
			return query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	/**
	 * Elimina los componentes que se idnentifiquen tanto en los productos, como
	 * en los productos componentes
	 * 
	 * @param codigoMaterial
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void deleteByCodigoProducto(Long codigoMaterial)
			throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete Componente comp where comp.productoByFkCodigoProductoComponente.pkCodigoProducto = :codigo or comp.productoByFkCodigoProducto.pkCodigoProducto = :codigo ";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoMaterial)
				.executeUpdate();

	}

	/**
	 * AGREGADO POR FABIAN
	 */
	/**
	 * Método que consulta la hoja de ruta activa de una producción definida por
	 * y obtiene los productos sin repetir
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param activo
	 * @return List<Object[Producto,Proceso]>
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> obtenerComponentes(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoLineaNegocio) {
		List<Object[]> productosProcesos = new ArrayList<Object[]>();
		String mensajeError = "";

		try {

			String consultarUnidad = "and pro.proceso.lineanegocio.unidad.pkCodigoUnidad = :unidad ";
			String consultarLineaNegocio = "and pro.proceso.lineanegocio.pkCodigoLineanegocio = :linea ";

			String consulta = "select distinct pro.producto, pro.proceso "
					+ "from Hojaruta hr, Produccion pro, Producto p "
					+ "where "
					// + "pro.proceso.tipoproducto.siglasTipoproducto <> 'MP'
					// and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :division and "
					+ "pro.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = :sociedad "
					+ (codigoUnidad.equals(Long.valueOf(-1)) ? ""
							: consultarUnidad)
					+ (codigoLineaNegocio.equals(Long.valueOf(-1)) ? ""
							: consultarLineaNegocio)
					+ "and hr.produccion = pro and "
					+ "hr.estadohojaruta.pkCodigoEstadohojaruta = 1 and "
					+ "pro.producto.estadoproducto.pkCodigoEstadoproducto = 1 "
					+ "order by pro.proceso.lineanegocio.pkCodigoLineanegocio,pro.proceso.ordenEjecucionProceso ASC ,pro.producto.nombreProducto ";

			Query query = Querier.query(consulta);
			query.setLong("division", codigoDivision);
			query.setLong("sociedad", codigoSociedad);

			if (codigoUnidad != null && codigoUnidad.longValue() != -1L) {
				query.setLong("unidad", codigoUnidad);
			}
			if (codigoLineaNegocio != null
					&& codigoLineaNegocio.longValue() != -1L) {
				query.setLong("linea", codigoLineaNegocio);
			}

			// ELIMINO LOS PRODUCTOS REPETIDOS
			productosProcesos = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productosProcesos;
	}

	public static List<Object[]> obtenerProductosPorProceso(
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long codigoProceso, String OrderBy) {
		List<Object[]> productosPP;
		String mensajeError = "";

		try {

			String consulta = "SELECT pro.producto , pro.proceso FROM Produccion pro "
					+ " where "
					+ " pro.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :codigoDivision and "
					+ " pro.proceso.lineanegocio.unidad.pkCodigoUnidad = :codigoUnidad and "
					+ " pro.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = :codigoSociedad and "
					+ " pro.proceso.lineanegocio.pkCodigoLineanegocio = :codigoLineaNegocio and "
					+ " pro.proceso.pkCodigoProceso in (:codigosProcesos)"
					+ " order by pro.producto.nombreProducto " + OrderBy;

			Query query = Querier.query(consulta);
			query.setLong("codigoDivision", codigoDivision);
			query.setLong("codigoUnidad", codigoUnidad);
			query.setLong("codigoSociedad", codigoSociedad);
			query.setLong("codigoLineaNegocio", codigoLineaNegocio);
			query.setLong("codigosProcesos", codigoProceso);

			productosPP = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return productosPP;
	}

	public static List<Long> getDescripcionProceso() {

		return null;
	}

	public static List<Object[]> obtenerComponentesPorProductos(
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long ordenProducto) {

		List<Object[]> componentes = new ArrayList<Object[]>();

		String consulta = "SELECT distinct pro.producto, pro.proceso "
				+ "FROM Produccion pro WHERE pro.producto.pkCodigoProducto in (:codigosProductos) "
				+ "AND pro.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :division "
				+ "AND pro.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = :sociedad "
				+ "AND pro.proceso.lineanegocio.unidad.pkCodigoUnidad = :unidad "
				+ "AND pro.proceso.lineanegocio.pkCodigoLineanegocio = :linea "
				+ "AND pro.producto.estadoproducto.pkCodigoEstadoproducto = 1 "
				+ "AND pro.producto.tipocategoriaproducto.pkCodigoTipocategoriaproducto is null ";

		Query query = Querier.query(consulta);
		query.setLong("division", codigoDivision);
		query.setLong("sociedad", codigoSociedad);
		// query.setLong("ordenProducto", ordenProducto);
		query.setParameterList("codigosProductos",
				getComponentesHojaRutaPorProducto(ordenProducto));

		if (codigoUnidad != null && codigoUnidad.longValue() != -1L) {
			query.setLong("unidad", codigoUnidad);
		}
		if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() != -1L) {
			query.setLong("linea", codigoLineaNegocio);
		}

		componentes = query.list();

		return componentes;

	}

	public static List<Object[]> obtenerCodigoComponentesParaBunker(
			Long codigoProducto, Long codigoLinea, Short mesContable,
			Integer anioContable) {
		String mensajeError = "";
		String combustible = "combustible";
		List<Object[]> consumos = new ArrayList<Object[]>();

		try {
			StringBuilder hql = new StringBuilder("SELECT");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo,");
			hql.append(" cpt.componente.pkCodigoComponente");
			hql.append(" FROM Consumopuestotrabajo AS cpt WHERE");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.mesPeriodocontable = :mes");
			hql.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.periodocontable.anoPeriodocontable = :anio");
			hql.append(" AND cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :lineaNeg");
			hql.append(" AND cpt.productogenerado.ordenproduccion.produccion.producto.pkCodigoProducto = :codigoProducto");
			hql.append(" AND cpt.componente.productoByFkCodigoProductoComponente.tipocategoriaproducto.nombreTipocategoriaproducto LIKE :combustible");
			hql.append(" AND cpt.componente.productoByFkCodigoProductoComponente.estadoFisicoSolidoProducto = :VALOR");
			hql.append(" GROUP BY");
			hql.append(" cpt.productogenerado.tablaoperacion.produccionpuestotrabajo.puestotrabajo.pkCodigoPuestotrabajo, ");
			hql.append(" cpt.componente.pkCodigoComponente");
			hql.append(" ORDER BY 1 ASC");

			Query query = query(hql.toString());

			query.setLong("lineaNeg", codigoLinea);
			query.setShort("mes", mesContable);
			query.setInteger("anio", anioContable);
			query.setLong("codigoProducto", codigoProducto);
			query.setString("combustible", "%" + combustible + "%");
			query.setBoolean("VALOR", Boolean.FALSE);

			consumos = query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return consumos;
	}

	public static List<Object[]> obtenerComponentesPorProductosCombustibles(
			Long codigoDivision, Long codigoSociedad, Long codigoUnidad,
			Long codigoLineaNegocio, Long ordenProducto, boolean solidoLiquido) {

		List<Object[]> componentes = new ArrayList<Object[]>();

		String consulta = "SELECT distinct pro.producto, pro.proceso "
				+ "FROM Produccion pro WHERE pro.producto.pkCodigoProducto in (:codigosProductos) "
				+ "AND pro.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = :division "
				+ "AND pro.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = :sociedad "
				+ "AND pro.proceso.lineanegocio.unidad.pkCodigoUnidad = :unidad "
				+ "AND pro.proceso.lineanegocio.pkCodigoLineanegocio = :linea "
				+ "AND pro.producto.estadoproducto.pkCodigoEstadoproducto = 1 "
				+ "AND pro.producto.tipocategoriaproducto.pkCodigoTipocategoriaproducto is not null "
				+ "AND pro.producto.estadoFisicoSolidoProducto = :estadoSolidoLiquido ";

		Query query = Querier.query(consulta);
		query.setLong("division", codigoDivision);
		query.setLong("sociedad", codigoSociedad);
		query.setBoolean("estadoSolidoLiquido", solidoLiquido);
		query.setParameterList("codigosProductos",
				getComponentesHojaRutaPorProducto(ordenProducto));

		if (codigoUnidad != null && codigoUnidad.longValue() != -1L) {
			query.setLong("unidad", codigoUnidad);
		}
		if (codigoLineaNegocio != null && codigoLineaNegocio.longValue() != -1L) {
			query.setLong("linea", codigoLineaNegocio);
		}

		componentes = query.list();

		return componentes;

	}

	public static List<Long> getComponentesHojaRutaPorProducto(
			Long codigosProducto) {

		String consulta = "SELECT hrc.componente.productoByFkCodigoProductoComponente.pkCodigoProducto "
				+ "FROM Hojarutacomponente hrc WHERE hrc.componente.productoByFkCodigoProducto.pkCodigoProducto in (:codigosProductos) "
				+ "and hrc.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = 1 "
				+ "order by hrc.componente.productoByFkCodigoProductoComponente.nombreProducto DESC ";

		Query query = Querier.query(consulta);
		query.setLong("codigosProductos", codigosProducto);
		List<Long> lista = query.list();
		if (lista.size() == 0) {
			Producto producto;
			try {
				producto = ProductoQuerier.getById(codigosProducto);
				String mensajeError = MessageFormat.format(
						PRODUCTO_SIN_HOJARUTA_SIN_COMPONENTES,
						new Object[] { producto.getNombreProducto() });

				logger.error(mensajeError);
				throw new EntornoEjecucionException(mensajeError);
			} catch (ElementoNoEncontradoException e) {
				throw new EntornoEjecucionException(e);
			}

		}
		return lista;
	}

	// Fin Query para Generar Parte Tecnico por PRocesos (exportarPDF - Resumen)

	/**
	 * Método que consulta la hoja de ruta activa de una producción definida por
	 * el proceso, producto especifico y tipo de producto (siglas)
	 * 
	 * @param codigoProceso
	 * @param codigoProducto
	 * @param activo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object[]> tablavalor(Long codigoDivision,
			Long codigoSociedad, Long codigoUnidad, Long codigoProducto,
			String tipoProducto, Long codigoLineaNegocio) {
		List<Object[]> componentes = new ArrayList<Object[]>();
		String mensajeError = "";

		try {

			String consultarUnidad = "and hr.produccion.proceso.lineanegocio.unidad.pkCodigoUnidad = ? ";
			String consultarLineaNegocio = "and hr.produccion.proceso.lineanegocio.pkCodigoLineanegocio = ? ";

			String consulta = "select hrcomp.componente, hrcomp.hojaruta.produccion.proceso, hrcomp from Hojaruta hr, Hojarutacomponente hrcomp "
					+ "where "
					+ "hr.produccion.producto.pkCodigoProducto = ? and "
					+ "hr.produccion.producto.estadoproducto.pkCodigoEstadoproducto = 1 and "
					+ "hr.produccion.proceso.tipoproducto.siglasTipoproducto = ? and "
					+ "hr.estadohojaruta.pkCodigoEstadohojaruta = ? and "
					+ "hr.produccion.proceso.lineanegocio.unidad.sociedad.division.pkCodigoDivision = ? and "
					+ "hr.produccion.proceso.lineanegocio.unidad.sociedad.pkCodigoSociedad = ? "
					+ (codigoUnidad.equals(new Long(-1)) ? "" : consultarUnidad)
					+ (codigoLineaNegocio.equals(new Long(-1)) ? ""
							: consultarLineaNegocio)
					+ " and hrcomp.hojaruta = hr "
					+ "order by hr.produccion.proceso.ordenEjecucionProceso,hr.produccion.proceso.tipoproducto.pkCodigoTipoproducto  desc";

			Query query = Querier.query(consulta);
			query.setLong(0, codigoProducto);
			query.setString(1, tipoProducto);
			query.setLong(2, new Long(1));
			query.setLong(3, codigoDivision);
			query.setLong(4, codigoSociedad);
			query.setLong(5, codigoUnidad);
			query.setLong(6, codigoLineaNegocio);

			componentes = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return componentes;
	}

}
