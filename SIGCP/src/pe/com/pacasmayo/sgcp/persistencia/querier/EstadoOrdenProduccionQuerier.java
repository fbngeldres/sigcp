package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoOrdenProduccionQuerier.java
 * Modificado: Feb 5, 2010 9:26:39 AM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EstadoOrdenProduccionQuerier implements ConstantesMensajeAplicacion {

	private static Log loggerQ = LogFactory.getLog(EstadoOrdenProduccionQuerier.class);

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_ESTADO_ORDENPRODUCCION = "nombreEstadoorden";
	public static final String NOMBRE_CLASE_ESTADO = (Estadoordenproduccion.class).getName();
	private static String CODIGO_ESTADO_ORDENPRODUCCION = "pkCodigoEstadoorden";

	/**
	 * Metodo que retorna todos los estados de ordenes de produccion
	 * 
	 * @return
	 */
	public static List<Estadoordenproduccion> getAll() throws AplicacionException {

		return Querier.getAll(Estadoordenproduccion.class);
	}

	public static Estadoordenproduccion getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return (Estadoordenproduccion) Querier.getById(Estadoordenproduccion.class, codigo);
	}

	/**
	 * Retorna una lista de Estados de ordenenes de produccion que tengan como
	 * nombre el valor del parametro value
	 * 
	 * @param value nombre del estadoa buscar
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Estadoordenproduccion> findByNombre(String value) throws AplicacionException {
		try {
			return (List<Estadoordenproduccion>) Querier.findByProperty(Estadoordenproduccion.class,
					NOMBRE_ESTADO_ORDENPRODUCCION, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Retorna un el codigo de un Estado de ordenenes de produccion que tenga
	 * como nombre el valor del parametro value
	 * 
	 * @param value nombre del estado a buscar
	 * @return
	 * @throws AplicacionException
	 */
	public static Long obtenerCodigoPorNombre(String nombre) throws AplicacionException {
		try {
			String consulta = "Select e.{0} from {1} As e where lower(e.{2}) = lower(:nombre)";
			consulta = MessageFormat.format(consulta, new Object[] { CODIGO_ESTADO_ORDENPRODUCCION, NOMBRE_CLASE_ESTADO,
					NOMBRE_ESTADO_ORDENPRODUCCION });

			Query query = Querier.query(consulta);
			query.setString("nombre", nombre);
			return (Long) query.uniqueResult();
		} catch (NonUniqueResultException e) {
			loggerQ.error(e);
			throw new AplicacionException(ERROR_QUERY_FALLO_NON_UNNIQUE_RESULT, e);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			loggerQ.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			loggerQ.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}
}
