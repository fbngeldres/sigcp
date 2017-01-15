package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TasaRealProduccionQuerier.java
 * Modificado: Mar 11, 2010 8:08:15 PM 
 * Autor: Ana Rosa
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Periodocontable;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Puestotrabajoproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tasarealprodregistromensual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class TasaRealProduccionRegistroMensualQuerier extends Querier implements ConstantesMensajeAplicacion {
	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static Log loggerQ = LogFactory.getLog(TasaRealProduccionRegistroMensualQuerier.class);

	private static final String CODIGO_TASA_REAL = "tasarealproduccion.pkCodigoTasarealproduccion";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Metodo para obtener la lista de tasas reales de produccion reg men
	 * 
	 * @return
	 */
	public static List<Tasarealprodregistromensual> getAll() throws AplicacionException {
		return Querier.getAll(Tasarealprodregistromensual.class);
	}

	/**
	 * Método para obtener una tasa real de produccion reg men por medio del
	 * código
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tasarealprodregistromensual getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tasarealprodregistromensual.class, codigo);
	}

	/**
	 * Método para obtener una produccion por medio del código del producto
	 * 
	 * @param codigo
	 * @throws AplicacionException
	 */
	public static List<Tasarealprodregistromensual> findByTasaRealProduccion(Long codigoTasa) throws AplicacionException {

		try {
			return Querier.findByProperty(Tasarealprodregistromensual.class, CODIGO_TASA_REAL, codigoTasa);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Obtiene la tasa real de produccion de un puesto de trabajo para una orden
	 * de produccion en un año y con una unidad de medida especifica
	 * 
	 * @param codigoOrdenProduccion
	 * @param anio
	 * @param codigoPuestoTrabajo
	 * @param codigoUnidadMedida
	 * @return
	 * @throws AplicacionException
	 */
	public static Double obtenerTasaReal(Long codigoOrdenProduccion, Integer anio, Long codigoPuestoTrabajo,
			Long codigoUnidadMedida) throws AplicacionException {
		String consulta = "select tm.cantidadTasarealprodregmensual"
				+ " from Tasarealprodregistromensual As tm, Ordenproduccion o"
				+ " where o.pkCodigoOrdenproduccion = :pkCodigoOrdenproduccion"
				+ " and tm.tasarealproduccion.produccion.pkProduccion = o.produccion.pkProduccion"
				+ " and tm.mesTasarealprodregmensual = o.mesOrdenproduccion" + " and tm.anno_tasarealprodregmensual = :anio"
				+ " and tm.tasarealproduccion.unidadmedida.pkCodigoUnidadMedida = :codigoUnidadMedida"
				+ " and tm.tasarealproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo";

		try {
			Query query = Querier.query(consulta);
			query.setLong("codigoOrdenProduccion", codigoOrdenProduccion);
			query.setInteger("anio", anio);
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			query.setLong("codigoUnidadMedida", codigoUnidadMedida);

			return (Double) query.uniqueResult();

		} catch (ObjectNotFoundException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			loggerQ.error(mensajeError, e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			loggerQ.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			String mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			loggerQ.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			loggerQ.error(e);
			throw new AplicacionException(ERROR_HIBERNATE, e.getCause());
		}
	}

	/**
	 * Elimina los registros mensuales de una tasa real de produccion
	 * 
	 * @param codigoTasaRealProduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void deleteByCodigoTasaRealProduccion(Long codigoTasaRealProduccion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete Tasarealprodregistromensual trprm where trprm.tasarealproduccion.pkCodigoTasarealproduccion = ? ");
		query.setLong(0, codigoTasaRealProduccion);

		query.executeUpdate();

	}

	/**
	 * Actualiza los valores de tasas mesuleas con los valores resultantes de
	 * los ajustes del mes
	 * 
	 * @param puestotrabajoproduccion Puestotrabajoproduccion
	 * @throws ParametroInvalidoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void actualizarTasas(Puestotrabajoproduccion puestotrabajoproduccion, Session session)
			throws ParametroInvalidoException, ElementoNoEncontradoException, EntornoEjecucionException {
		String mensajeError;

		Ajusteproducto ajusteproducto = puestotrabajoproduccion.getAjusteproducto();
		Ajusteproduccion ajusteproduccion = ajusteproducto.getAjusteproduccion();
		Periodocontable periodocontable = ajusteproduccion.getPeriodocontable();

		try {
			StringBuilder hql = new StringBuilder();
			hql.append("FROM Tasarealprodregistromensual WHERE ");
			hql.append(" tasarealproduccion.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo");
			hql.append(" AND tasarealproduccion.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codLinea");
			hql.append(" AND tasarealproduccion.produccion.producto.pkCodigoProducto = :codProducto");
			hql.append(" AND mesTasarealprodregmensual = :mes");
			hql.append(" AND anno_tasarealprodregmensual = :anio");
			Query query = session.createQuery(hql.toString());
			query.setLong("codigoPuestoTrabajo", puestotrabajoproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo());
			query.setLong("codLinea", ajusteproduccion.getLineanegocio().getPkCodigoLineanegocio());
			query.setLong("codProducto", ajusteproducto.getProducto().getPkCodigoProducto());
			query.setShort("mes", periodocontable.getMesPeriodocontable());
			query.setInteger("anio", periodocontable.getAnoPeriodocontable());

			Tasarealprodregistromensual tasa = (Tasarealprodregistromensual) query.uniqueResult();

			if (tasa != null) {
				if (puestotrabajoproduccion.getTmphRealPuestotrabajoproducci() > 0) {
					tasa.setCantidadTasarealprodregmensual(puestotrabajoproduccion.getTmphRealPuestotrabajoproducci());

				} else {

					Puestotrabajoproduccion p = PuestoTrabajoProduccionQuerier.obtenerPorPeriodoContableProductoPuestoTrabajo(
							ajusteproduccion.getLineanegocio().getPkCodigoLineanegocio(), ajusteproducto.getProducto()
									.getPkCodigoProducto(),
							puestotrabajoproduccion.getPuestotrabajo().getPkCodigoPuestotrabajo(), periodocontable
									.getMesPeriodocontable(), periodocontable.getAnoPeriodocontable());

					if (p != null) {

						tasa.setCantidadTasarealprodregmensual(p.getTmphRealPuestotrabajoproducci());

					}

				}

				session.update(tasa);
			}

		} catch (PropertyValueException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, e);
			throw new ParametroInvalidoException(mensajeError, e);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}

	public static List<Tasarealprodregistromensual> obtenerTasaRealesPorAnnoMesProducto(Periodocontable periodocontable,
			Long lineaNegocio) {
		String mensajeError;

		try {
			StringBuilder hql = new StringBuilder();
			hql.append("FROM Tasarealprodregistromensual WHERE ");
			hql.append(" tasarealproduccion.produccion.proceso.lineanegocio.pkCodigoLineanegocio = :codLinea");
			hql.append(" AND mesTasarealprodregmensual = :mes");
			hql.append(" AND anno_tasarealprodregmensual = :anio");
			Query query = query(hql.toString());

			query.setLong("codLinea", lineaNegocio);
			query.setShort("mes", periodocontable.getMesPeriodocontable());
			query.setInteger("anio", periodocontable.getAnoPeriodocontable());

			return query.list();

		} catch (SessionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}
	}
}
