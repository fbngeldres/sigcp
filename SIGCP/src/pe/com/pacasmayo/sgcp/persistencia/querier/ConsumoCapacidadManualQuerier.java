package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoCapacidadPlanQuerier.java
 * Modificado: Feb 5, 2010 4:42:12 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocapacidadmanual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class ConsumoCapacidadManualQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_ORDENMAN = "ordenproduccionmanual.pkCodigoOrdenproduccionmanual";

	public static List<Consumocapacidadmanual> findByOrdenProduccionMan(Long codigoOrden) throws AplicacionException {
		try {
			return Querier.findByProperty(Consumocapacidadmanual.class, CODIGO_ORDENMAN, codigoOrden);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Este método obtiene una lista de Consumo Capacidad Manual, de acuerdo al
	 * código de la orden de producción manual y el puesto de trabajo
	 * 
	 * @param codigoOrdenProduccionManual
	 * @param codigoPuestoTrabajo
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Consumocapacidadmanual> findByProduccionManualYPuestoTrabajo(Long codigoOrdenProduccionManual,
			Long codigoPuestoTrabajo) throws ElementoNoEncontradoException, AplicacionException {
		String mensajeError = "";
		try {
			Query query = query("from Consumocapacidadmanual ccm where ccm.ordenproduccionmanual.pkCodigoOrdenproduccionmanual = ? and ccm.puestotrabajo.pkCodigoPuestotrabajo = ? ");
			query.setLong(0, codigoOrdenProduccionManual);
			query.setLong(1, codigoPuestoTrabajo);

			return (List<Consumocapacidadmanual>) query.list();
		} catch (UnresolvableObjectException uOException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, uOException);
			throw new ElementoNoEncontradoException(mensajeError, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, oNFException);
			throw new ElementoNoEncontradoException(mensajeError, oNFException.getCause());
		} catch (HibernateException hException) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, hException);
			throw new AplicacionException(mensajeError, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para persistir una entidad Consumocapacidadmanual.
	 * 
	 * @param consumoCapacidadPlan
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Consumocapacidadmanual consumoCapacidad) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(consumoCapacidad);

	}

	public static void delete(Consumocapacidadmanual consumoCapacidad) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(consumoCapacidad);
	}

	public static void deleteByCodigoOrdenProduccionManual(Long codigoOrdenProduccionManual) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete from Consumocapacidadmanual ccm where ccm.ordenproduccionmanual.pkCodigoOrdenproduccionmanual = ?");
		query.setLong(0, codigoOrdenProduccionManual);

		query.executeUpdate();

	}
}
