package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Registromedicion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: RegistroMedicionQuerier.java
 * Modificado: Apr 27, 2010 11:04:43 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class RegistroMedicionQuerier extends Querier implements ConstantesMensajeAplicacion, ConstantesLogicaNegocio {

	/** Logger Instance */
	private static Logger log = Logger.getLogger(RegistroMedicionQuerier.class);

	/**
	 * Método para obtener una Registromedicion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Registromedicion getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Registromedicion.class, codigo);
	}

	/**
	 * Método para obtener la lista de objectos Registromedicion
	 * 
	 * @return
	 */
	public static List<Registromedicion> getAll() throws AplicacionException {

		return Querier.getAll(Registromedicion.class);
	}

	/**
	 * Metodo que permite filtrar la lista de Registromedicion segun un conjunto
	 * de propiedades simultaneamente
	 * 
	 * @param propiedades
	 * @return
	 */
	public static List<Registromedicion> findByProperties(Map propiedades) {

		return Querier.findByProperties(Registromedicion.class, propiedades);
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Registromedicion en la BD.
	 * 
	 * @param produccionSemanal
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void save(Registromedicion registromedicion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(registromedicion);
	}

	/**
	 * Metodo para modificar un Registromedicion de la BD.
	 * 
	 * @param produccionSemanal
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void update(Registromedicion registromedicion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(registromedicion);
	}

	/**
	 * Metodo para eliminar una Registromedicion de la BD.
	 * 
	 * @param codigo
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void delete(Registromedicion registromedicion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(registromedicion);
	}

	public static Registromedicion obtenerRegistroMedicionPorVarios(Long codigoProceso, Integer anno, Short mes,
			Long codigoTipoMedio, Long codigoLinea, Date fecha) throws AplicacionException {

		StringBuilder strBuilder = new StringBuilder("FROM Registromedicion rm");
		strBuilder.append(" WHERE rm.proceso.pkCodigoProceso = :codigoProceso");
		strBuilder.append(" AND rm.anoRegistromedicion = :anno");
		strBuilder.append(" AND rm.mesRegistromedicion = :mes");
		strBuilder.append(" AND rm.tipomedioalmacenamiento.pkCodigoTipomedioalmacenamien = :codigoTipoMedio");
		strBuilder.append(" AND rm.proceso.lineanegocio.pkCodigoLineanegocio = :codigoLinea");
		strBuilder.append(" AND rm.fechaRegistromedicion = :fecha ");
		strBuilder.append(" AND rm.estadoregistromedicion.pkCodigoEstadoregistromedicio != :estadoAnulado ");

		Query query = Querier.query(strBuilder.toString());
		query.setLong("codigoProceso", codigoProceso);
		query.setInteger("anno", anno);
		query.setShort("mes", mes);
		query.setLong("codigoTipoMedio", codigoTipoMedio);
		query.setLong("codigoLinea", codigoLinea);
		query.setDate("fecha", fecha);

		long codigoAnulado = Long.parseLong(ManejadorPropiedades.obtenerPropiedadPorClave(STOCK_MEDICION_ESTADO_ANULADO));
		query.setLong("estadoAnulado", codigoAnulado);

		try {
			return (Registromedicion) query.uniqueResult();

		} catch (ObjectNotFoundException oNFException) {
			log.error(oNFException);
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			log.error(uOException);
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException);
		} catch (HibernateException hException) {
			log.error(hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException);
		}
	}

}