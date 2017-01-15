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
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumorecursomanual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ConsumoRecursoManualQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_ORDENMAN = "ordenproduccionmanual.pkCodigoOrdenproduccionmanual";
	public static final String CODIGO_ORDEN_PRODUCCION = "ordenproduccionmanual.ordenproduccion.pkCodigoOrdenproduccion";

	public static List<Consumorecursomanual> findByOrdenProduccionMan(Long codigoOrden) throws AplicacionException {
		try {
			return Querier.findByProperty(Consumorecursomanual.class, CODIGO_ORDENMAN, codigoOrden);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static final Consumorecursomanual obtenerPorOrdenProduccion(Long codigoOrdenProduccion) throws AplicacionException,
			EntornoEjecucionException {
		return Querier.findByPropertyUniqueResult(Consumorecursomanual.class, CODIGO_ORDEN_PRODUCCION, codigoOrdenProduccion);
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
	public static void save(Consumorecursomanual consumoRecurso) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(consumoRecurso);

	}

	public static void delete(Consumorecursomanual consumoRecurso) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(consumoRecurso);

	}

	public static void deleteByCodigoOrdenProduccionManual(Long codigoOrdenProduccionManual) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete from Consumorecursomanual crm where crm.ordenproduccionmanual.pkCodigoOrdenproduccionmanual = ?");
		query.setLong(0, codigoOrdenProduccionManual);

		query.executeUpdate();

	}
}
