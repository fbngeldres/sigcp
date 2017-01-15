package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Alturasilo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medicion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Medioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.SQLGrammarException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedicionDiariaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.RegistroTablaMedicionDiaDTO;
import pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.StockDTO;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;
import pe.com.pacasmayo.sgcp.util.NumberUtil;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: MedicionQuerier.java
 * Modificado: Apr 27, 2010 11:11:22 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class MedicionQuerier extends Querier implements
		ConstantesMensajeAplicacion, ConstantesLogicaNegocio {

	private static Long CODIGO_ESTADO_APROBADO_MEDICION = new Long(
			ManejadorPropiedades
					.obtenerPropiedadPorClave(STOCK_MEDICION_ESTADO_APROBADO));
	private static Logger logger = Logger.getLogger(MedicionQuerier.class);
	private static String mensajeError;

	/**
	 * Método para obtener una Registromedicion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Medicion getById(Long codigo)
			throws ElementoNoEncontradoException {

		return Querier.getById(Medicion.class, codigo);
	}

	/**
	 * Método para obtener la lista de objectos Medicion
	 * 
	 * @return
	 */
	public static List<Medicion> getAll() throws EntornoEjecucionException,
			SesionVencidaException {

		return Querier.getAll(Medicion.class);
	}

	/**
	 * Metodo que permite filtrar la lista de Medicion segun un conjunto de
	 * propiedades simultaneamente
	 * 
	 * @param propiedades
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Medicion> findByProperties(Map propiedades) {

		return Querier.findByProperties(Medicion.class, propiedades);
	}

	/**
	 * Metodo que retorna el stock mensual de productos asociados a un proceso.
	 * Tambien es posible filtrar adicionalmente por estado del registro de
	 * medicion y por tipo de medio de alamcenamiento
	 * 
	 * @param codigoProceso
	 * @param anno
	 * @param mes
	 * @param codigoEstadoRegistroMedicion
	 * @param codigoTipoMedioAlamacenamiento
	 * @return
	 */
	public static List<StockDTO> obtenerStockMensualProductosPorProceso(
			Long codigoProceso, Integer anno, Short mes,
			Long codigoEstadoRegistroMedicion,
			Long codigoTipoMedioAlamacenamiento) throws AplicacionException {

		// Mapa de parametros de filtrado
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("codigoProceso", codigoProceso);
		parameters.put("anno", anno);
		parameters.put("mes", mes);

		// Query base segmentado en tres secciones para facil
		// extensibilidad,segun se reciban parametros
		String select = "select registromedicion.pkCodigoRegistromedicion,"
				+ "registromedicion.fechaRegistromedicion,"
				+ "producto.pkCodigoProducto," + "producto.nombreProducto,"
				+ "estadoregistromedicion.pkCodigoEstadoregistromedicio,"
				+ "estadoregistromedicion.nombreEstadoregistromedicion,"
				+ "sum(medicion.cantidadAlmacenadaMedicion) ";
		String from = "from Producto producto," + "Produccion produccion,"
				+ "Registromedicion registromedicion," + "Medicion medicion,"
				+ "Proceso proceso,"
				+ "Estadoregistromedicion estadoregistromedicion ";
		String where = "where produccion.pkProduccion = medicion.produccion.pkProduccion "
				+ "and producto.pkCodigoProducto = produccion.producto.pkCodigoProducto "
				+ "and medicion.registromedicion.pkCodigoRegistromedicion = registromedicion.pkCodigoRegistromedicion "
				+ "and registromedicion.estadoregistromedicion.pkCodigoEstadoregistromedicio = estadoregistromedicion.pkCodigoEstadoregistromedicio "
				+ "and proceso.pkCodigoProceso = produccion.proceso.pkCodigoProceso "
				+ "and registromedicion.anoRegistromedicion = :anno "
				+ "and registromedicion.mesRegistromedicion = :mes "
				+ "and proceso.pkCodigoProceso = :codigoProceso ";

		// Si se desea filtrar adicionalmente por codigo de estado de registro
		// de medicion, se agrega el parametro al mapa
		if (codigoEstadoRegistroMedicion != null) {

			parameters.put("codigoEstadoRegistroMedicion",
					codigoEstadoRegistroMedicion);
			where += "and estadoregistromedicion.pkCodigoEstadoregistromedicio = :codigoEstadoRegistroMedicion ";

		}
		// Si se desea filtrar adicionalmente por codigo de tipo de medio de
		// almacenamiento, se agrega el parametro al mapa
		if (codigoTipoMedioAlamacenamiento != null) {

			parameters.put("codigoTipoMedioAlamacenamiento",
					codigoTipoMedioAlamacenamiento);
			from += ",Tipomedioalmacenamiento tipomedioalmacenamiento ";
			where += "and registromedicion.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien = tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien "
					+ "and tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien = :codigoTipoMedioAlamacenamiento ";
		}

		String order = "order by registromedicion.fechaRegistromedicion ";
		String group = "group by registromedicion.pkCodigoRegistromedicion,producto.pkCodigoProducto,producto.nombreProducto,registromedicion.fechaRegistromedicion,"
				+ "estadoregistromedicion.pkCodigoEstadoregistromedicio,"
				+ "estadoregistromedicion.nombreEstadoregistromedicion ";

		// Concatenamos la clausula las partes del query
		String queryString = select + from + where + group + order;
		List<Object[]> listaResultado = null;
		List<StockDTO> listaStock = new ArrayList<StockDTO>();
		try {
			listaResultado = Querier.executeQuery(queryString, parameters);

			if (listaResultado.size() != 0) {
				listaStock = transformarListaToListaStock(listaResultado);
			}
			return listaStock;

		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO,
					uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO,
					oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE,
					hException.getCause());
		}
	}

	/**
	 * Metodo que transforma la lista retornada por el query a lista de objetos
	 * StockBean
	 * 
	 * @param listaStock
	 * @param listaResultado
	 */
	@SuppressWarnings("unchecked")
	private static List<StockDTO> transformarListaToListaStock(
			List<Object[]> listaResultado) {

		List<StockDTO> listaStock = new ArrayList<StockDTO>();
		// Recorremos la lista devuelta por el query,
		for (Iterator<Object[]> iterator = listaResultado.iterator(); iterator
				.hasNext();) {
			Object[] arregloObject = iterator.next();

			// Construimos los objetos del StockDTO, se los agregamos y lo
			// agregamos a la lista

			Long codigoRegistro = (Long) arregloObject[0];
			Date fecha = (Date) arregloObject[1];
			Long codigoProducto = (Long) arregloObject[2];
			String nombreProducto = (String) arregloObject[3];
			Long codigoEstado = (Long) arregloObject[4];
			String nombreEstado = (String) arregloObject[5];
			Double total = (Double) arregloObject[6];

			StockDTO stockDTO = new StockDTO();
			stockDTO.setCodigoRegistroMedicion(codigoRegistro);
			stockDTO.setFecha(FechaUtil.convertirDateToString(fecha,
					FechaUtil.PATRON_FECHA_APLICACION));
			stockDTO.setCodigoProducto(codigoProducto);
			stockDTO.setNombreProducto(nombreProducto);
			stockDTO.setCodigoEstado(codigoEstado);
			stockDTO.setNombreEstado(nombreEstado);
			stockDTO.setStock(total);

			listaStock.add(stockDTO);

		}

		return listaStock;
	}

	/**
	 * Metodo que retorna la lista de RegistroTablaMedicionDiaDTO con los
	 * detalles de medicion de los silos para el dia. Este metodo es llamado
	 * para cargar los datos en la ventana de consulta de medicion.
	 * 
	 * @param codigoProceso
	 * @param anno
	 * @param mes
	 * @param codigoEstadoRegistroMedicion
	 * @param codigoTipoMedioAlamacenamiento
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(
			Long codigoregistroMedicion) throws AplicacionException {

		StringBuilder strBuilder = new StringBuilder("FROM Medicion m WHERE ");
		strBuilder
				.append("m.registromedicion.pkCodigoRegistromedicion = :codigo ");
		strBuilder
				.append("ORDER by m.medioalmacenamiento.nombreMedioalmacenamiento ASC");

		List<Medicion> listaResultado = null;
		List<RegistroTablaMedicionDiaDTO> listaDetalle = null;
		try {

			Query query = Querier.query(strBuilder.toString());
			query.setLong("codigo", codigoregistroMedicion);

			listaResultado = query.list();

			if (listaResultado.size() > 0) {
				listaDetalle = transformarListaToListaDetalles(listaResultado);
			}

			return listaDetalle;

		} catch (UnresolvableObjectException uOException) {
			logger.error(uOException);
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO,
					uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO,
					oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE,
					hException.getCause());
		}
	}

	/**
	 * Metodo que retorna la lista de RegistroTablaMedicionDiaDTO con los
	 * detalles de medicion de los silos para el dia. Este metodo es llamado
	 * para cargar la lista de registros en la ventana de registro, antes de
	 * realizar el registro.
	 * 
	 * @param codigoProceso
	 * @param anno
	 * @param mes
	 * @param codigoEstadoRegistroMedicion
	 * @param codigoTipoMedioAlamacenamiento
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStockRegistrados(
			Long codigoRegistroMedicion) throws AplicacionException {

		String queryString = "from Medicion m where m.registromedicion.pkCodigoRegistromedicion=:codigoRegistroMedicion";

		List<Medicion> listaResultado = null;
		List<RegistroTablaMedicionDiaDTO> listaDetalle = null;
		try {

			Query query = Querier.query(queryString);
			query.setLong("codigoRegistroMedicion", codigoRegistroMedicion);

			listaResultado = query.list();

			if (listaResultado.size() > 0) {
				listaDetalle = new ArrayList<RegistroTablaMedicionDiaDTO>();
				for (Iterator<Medicion> iterator = listaResultado.iterator(); iterator
						.hasNext();) {
					Medicion medicion = iterator.next();

					RegistroTablaMedicionDiaDTO registro = null;

					listaDetalle.add(registro);

				}
			}

			return listaDetalle;

		} catch (UnresolvableObjectException uOException) {
			logger.error(uOException);
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO,
					uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO,
					oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE,
					hException.getCause());
		}
	}

	/**
	 * Metodo que retorna la lista de RegistroTablaMedicionDiaDTO con los
	 * detalles de medicion de los silos para el dia. Este metodo es llamado
	 * para cargar la lista de registros en la ventana de registro, antes de
	 * realizar el registro.
	 * 
	 * @param codigoProceso
	 * @param anno
	 * @param mes
	 * @param codigoEstadoRegistroMedicion
	 * @param codigoTipoMedioAlamacenamiento
	 * @return
	 * @throws AplicacionException
	 */
	@SuppressWarnings("unchecked")
	public static List<RegistroTablaMedicionDiaDTO> obtenerListaRegistroDetallesStock(
			Long codigoProceso, Long codigoTipoMedio)
			throws AplicacionException {

		String queryString = "FROM Medioalmacenamiento m"
				+ " WHERE m.produccion.proceso.pkCodigoProceso = :codigoProceso"
				+ " AND m.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien = :codigoTipoMedio"
				+ " ORDER BY m.nombreMedioalmacenamiento ASC";

		List<Medioalmacenamiento> listaResultado = null;
		List<RegistroTablaMedicionDiaDTO> listaDetalle = null;
		try {

			Query query = Querier.query(queryString);
			query.setLong("codigoProceso", codigoProceso);

			if (codigoTipoMedio != null) {
				query.setLong("codigoTipoMedio", codigoTipoMedio);
			}

			listaResultado = query.list();

			if (listaResultado.size() > 0) {
				listaDetalle = new ArrayList<RegistroTablaMedicionDiaDTO>();
				for (Iterator<Medioalmacenamiento> iterator = listaResultado
						.iterator(); iterator.hasNext();) {
					Medioalmacenamiento medioAlmc = iterator.next();

					RegistroTablaMedicionDiaDTO registro = new RegistroTablaMedicionDiaDTO();
					List<Double> listaAlturas = new ArrayList<Double>();

					registro.setCodigo(null);
					registro.setCodigoProducto(medioAlmc.getProduccion()
							.getProducto().getPkCodigoProducto());
					registro.setNombreProducto(medioAlmc.getProduccion()
							.getProducto().getNombreProducto());
					registro.setCodigoProduccion(medioAlmc.getProduccion()
							.getPkProduccion());
					registro.setCodigoSilo(medioAlmc
							.getPkCodigoMedioalmacenamiento());
					registro.setNombreSilo(medioAlmc
							.getNombreMedioalmacenamiento());
					registro.setCapacidad(medioAlmc
							.getCapacidadMaximaMedioalmacenam());
					registro.setNumeroAlturas(medioAlmc
							.getNumeroAlturasMedioalmacenamie());
					registro.setListaAlturas(listaAlturas);
					registro.setAlturaSilo(medioAlmc
							.getAlturaEspecificaMedioalmacena());
					registro.setFactorMetrosCubicos(medioAlmc
							.getFactorMetrosCubicosMedioalma());
					registro.setStockSeguridad(medioAlmc
							.getStockSeguridadMedioalmacenami());
					listaDetalle.add(registro);

				}
			}

			return listaDetalle;

		} catch (UnresolvableObjectException uOException) {
			logger.error(uOException);
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO,
					uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO,
					oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE,
					hException.getCause());
		}
	}

	/**
	 * Metodo para transformar la lista resultado del query en lista de
	 * RegistroTablaMedicionDiaDTO
	 * 
	 * @param listaResultado
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static List<RegistroTablaMedicionDiaDTO> transformarListaToListaDetalles(
			List<Medicion> listaResultado) {

		List<RegistroTablaMedicionDiaDTO> lista = new ArrayList<RegistroTablaMedicionDiaDTO>();
		for (Iterator<Medicion> iterator = listaResultado.iterator(); iterator
				.hasNext();) {
			Medicion medicion = iterator.next();

			Producto producto = medicion.getProduccion().getProducto();
			Long codigoProducto = producto.getPkCodigoProducto();
			String nombreProducto = producto.getNombreProducto();

			Medioalmacenamiento medioalmacenamiento = medicion
					.getMedioalmacenamiento();
			Long codigoSilo = medioalmacenamiento
					.getPkCodigoMedioalmacenamiento();
			String nombreSilo = medioalmacenamiento
					.getNombreMedioalmacenamiento();
			Double capacidad = medioalmacenamiento
					.getCapacidadMaximaMedioalmacenam();

			Double cantidad = medicion.getCantidadAlmacenadaMedicion();
			Double porcentajeUso = medicion.getPorcentajeUsoMedicion();

			Long codigoEstado = null;

			RegistroTablaMedicionDiaDTO registro = new RegistroTablaMedicionDiaDTO();

			List<Double> listaAlturas = new ArrayList<Double>();

			Set alturasilos = medicion.getAlturasilos();
			List<Alturasilo> alturas = new ArrayList<Alturasilo>(alturasilos);

			Collections.sort(alturas, new Comparator<Alturasilo>() {
				public int compare(Alturasilo o1, Alturasilo o2) {
					return o1.getNumeroAlturaAlturasilo()
							- o2.getNumeroAlturaAlturasilo();
				}
			});

			for (Iterator<Alturasilo> itt = alturas.iterator(); itt.hasNext();) {
				Alturasilo alturasilo = itt.next();
				listaAlturas.add(alturasilo.getMedicionAlturaAlturasilo());
			}

			registro.setCodigoProducto(codigoProducto);
			registro.setNombreProducto(nombreProducto);

			registro.setCodigoSilo(codigoSilo);
			registro.setNombreSilo(nombreSilo);

			registro.setCapacidad(capacidad);
			registro.setCantidad(cantidad);
			registro.setPorcentajeUso(porcentajeUso);
			registro.setListaAlturas(listaAlturas);

			registro.setCodigoEstado(codigoEstado);

			lista.add(registro);
		}
		return lista;
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Medicion en la BD.
	 * 
	 * @param medicion
	 * @throws ConstrainViolationException
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void save(Medicion medicion)
			throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(medicion);
	}

	/**
	 * Metodo para modificar un Medicion de la BD.
	 * 
	 * @param medicion
	 * @throws ConstrainViolationException
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void update(Medicion medicion)
			throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(medicion);
	}

	/**
	 * Metodo para eliminar una Medicion de la BD.
	 * 
	 * @param codigo
	 * @throws ConstrainViolationException
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void delete(Medicion medicion)
			throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(medicion);
	}

	@SuppressWarnings("unchecked")
	public static double obtenerFisico(Long codigoLinea, Long codigoProducto,
			Integer anioContable, Short mesContable, Date fecha)
			throws EntornoEjecucionException, SesionVencidaException {

		try {
			StringBuilder hql = new StringBuilder("From Medicion m WHERE ");
			hql.append("m.registromedicion.anoRegistromedicion = :anioContable AND ");
			hql.append("m.registromedicion.mesRegistromedicion = :mesContable AND ");
			hql.append("m.registromedicion.estadoregistromedicion.pkCodigoEstadoregistromedicio = :estadoAprobado AND ");
			hql.append("m.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codigoLinea AND ");
			hql.append("m.produccion.producto.pkCodigoProducto = :producto Order by m.registromedicion.fechaRegistromedicion DESC");

			Query query = Querier.query(hql.toString());
			query.setLong("anioContable", anioContable);
			query.setLong("mesContable", mesContable);
			query.setLong("codigoLinea", codigoLinea);

			query.setLong("estadoAprobado", CODIGO_ESTADO_APROBADO_MEDICION);
			query.setLong("producto", codigoProducto);

			List<Medicion> mediciones = query.list();
			if (mediciones == null || mediciones.size() == 0) {
				return 0d;
			}

			double total = 0d;
			for (int i = 0; i < mediciones.size(); i++) {
				Medicion medicion = mediciones.get(i);
				Date fechaRegistromedicion = medicion.getRegistromedicion()
						.getFechaRegistromedicion();
				if (FechaUtil.comparaFechasSoloDiaMesYAnio(
						fechaRegistromedicion, fecha)) {
					total += medicion.getCantidadAlmacenadaMedicion();
				}
			}

			return total;
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
		} catch (RuntimeException e) {
			logger.error(e);
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	/**
	 * Método que retorna el stock actual de un medio de almancenamiento (silo)
	 * para una fecha
	 * 
	 * @param codigoLinea
	 * @param codigoProducto
	 * @param anioContable
	 * @param mesContable
	 * @param fecha
	 * @param codigoMedioAlmacenamiento
	 * @return
	 * @throws EntornoEjecucionException
	 * @throws SesionVencidaException
	 */
	public static Double obtenerStockActualMedioAlmacenamiento(
			Long codigoOrdenProduccion, Long codigoMedioAlmacenamiento,
			Date fechaMedicion) throws EntornoEjecucionException,
			SesionVencidaException {

		try {

			StringBuilder hql = new StringBuilder(
					"SELECT SUM(m.cantidadAlmacenadaMedicion) From Medicion m WHERE ");
			hql.append("m.registromedicion.estadoregistromedicion.pkCodigoEstadoregistromedicio = :estadoAprobado AND ");
			hql.append("m.produccion.pkProduccion in (select op.produccion.pkProduccion from Ordenproduccion op where op.pkCodigoOrdenproduccion = :codigoOrdenProduccion) AND ");
			hql.append("m.medioalmacenamiento.pkCodigoMedioalmacenamiento = :codigoMedioAlmacenamiento AND ");
			hql.append("m.registromedicion.fechaRegistromedicion = :fecha");

			Query query = Querier.query(hql.toString());
			query.setLong("estadoAprobado", CODIGO_ESTADO_APROBADO_MEDICION);
			query.setLong("codigoOrdenProduccion", codigoOrdenProduccion);
			query.setLong("codigoMedioAlmacenamiento",
					codigoMedioAlmacenamiento);
			query.setDate("fecha", fechaMedicion);

			Double medicion = (Double) query.uniqueResult();
			return medicion == null ? 0d : medicion;
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
		} catch (RuntimeException e) {
			logger.error(e);
			mensajeError = ManejadorPropiedades
					.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	public static List<MedicionDiariaDTO> obtenerDetalleRegistroMedicion(
			Long codigoProceso, Long codigoProducto, Long lineaNegocio,
			Date fechainicio, Date fechaFin) throws AplicacionException {

		StringBuffer queryString = new StringBuffer(
				" SELECT new pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente.MedicionDiariaDTO( m.registromedicion.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien,m.registromedicion.tipomedioalmacenamiento.nombreTipomedioalmacenamiento ");
		queryString
				.append(" ,m.registromedicion.anoRegistromedicion, m.registromedicion.mesRegistromedicion,");
		queryString
				.append(" m.registromedicion.fechaRegistromedicion-1, SUM(m.cantidadAlmacenadaMedicion)) ");
		queryString.append(" FROM Medicion m ");
		queryString
				.append(" WHERE m.produccion.proceso.pkCodigoProceso = :codigoProceso ");
		queryString
				.append(" AND  m.produccion.producto.pkCodigoProducto = :codigoProducto ");
		queryString
				.append(" AND  m.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :lineaNegocio ");
		queryString
				.append(" AND  m.registromedicion.fechaRegistromedicion > :fechaInicio ");
		queryString
				.append(" AND  m.registromedicion.fechaRegistromedicion <= :fechaFin ");
		queryString
				.append(" AND  m.registromedicion.estadoregistromedicion.pkCodigoEstadoregistromedicio = :estadoRegistro ");
		queryString
				.append(" GROUP BY m.registromedicion.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien,");
		queryString
				.append(" m.registromedicion.tipomedioalmacenamiento.nombreTipomedioalmacenamiento, ");
		queryString
				.append(" m.registromedicion.anoRegistromedicion, m.registromedicion.mesRegistromedicion, ");
		queryString.append(" m.registromedicion.fechaRegistromedicion");
		queryString
				.append(" ORDER BY m.registromedicion.fechaRegistromedicion ASC");

		try {

			Query query = Querier.query(queryString.toString());
			query.setLong("codigoProceso", codigoProceso);
			query.setLong("codigoProducto", codigoProducto);
			query.setLong("lineaNegocio", lineaNegocio);
			query.setDate("fechaInicio", fechainicio);
			query.setDate("fechaFin", fechaFin);
			query.setLong("estadoRegistro", CODIGO_ESTADO_APROBADO_MEDICION);

			if (codigoProducto != null) {
				query.setLong("codigoProducto", codigoProducto);
			}

			return query.list();

		} catch (UnresolvableObjectException uOException) {
			logger.error(uOException);
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO,
					uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO,
					oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE,
					hException.getCause());
		}
	}

	public static BigDecimal obtenerCantidadAlmacenada(Long codigoSilo,
			double promedioAlturas, Double[] alturas) {
		try {
			StringBuilder sql = new StringBuilder(
					"SELECT fn_calcularcantidadalmacenada(?,?,?,?,?);");
			Query querysql = getSession().createSQLQuery(sql.toString());
			querysql.setInteger(0, codigoSilo.intValue());
			querysql.setBigDecimal(1,
					NumberUtil.convertirDoubleToBigDecimal(promedioAlturas));
			querysql.setBigDecimal(2,
					NumberUtil.convertirDoubleToBigDecimal(alturas[0]));
			querysql.setBigDecimal(3,
					NumberUtil.convertirDoubleToBigDecimal(alturas[1]));
			querysql.setBigDecimal(4,
					NumberUtil.convertirDoubleToBigDecimal(alturas[2]));

			return (BigDecimal) querysql.uniqueResult();
		} catch (SQLGrammarException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return new BigDecimal(0);

	}
}