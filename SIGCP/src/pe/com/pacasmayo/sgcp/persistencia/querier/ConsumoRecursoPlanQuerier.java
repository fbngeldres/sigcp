package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoRecursoPlanQuerier.java
 * Modificado: Feb 5, 2010 5:58:03 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumorecursoplan;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ConsumoRecursoPlanQuerier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_ORDENPLAN = "ordenproduccionplan.pkCodigoOrdenproduccionplan";
	public static final String CODIGO_ORDEN_PPRODUCCION = "ordenproduccionplan.ordenproduccion.pkCodigoOrdenproduccion";

	public static List<Consumorecursoplan> findByOrdenProduccionPlan(Long codigoOrden) throws AplicacionException {
		try {
			return Querier.findByProperty(Consumorecursoplan.class, CODIGO_ORDENPLAN, codigoOrden);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static Consumorecursoplan obtenerPorOrdenProduccion(Long codigoOrdenProduccion) throws EntornoEjecucionException,
			AplicacionException {

		return Querier.findByPropertyUniqueResult(Consumorecursoplan.class, CODIGO_ORDEN_PPRODUCCION, codigoOrdenProduccion);

	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para persistir una entidad Consumorecursoplan.
	 * 
	 * @param consumoCapacidadPlan
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Consumorecursoplan consumoRecursoPlan) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(consumoRecursoPlan);

	}

	public static void delete(Consumorecursoplan consumoRecursoPlan) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(consumoRecursoPlan);

	}
}
