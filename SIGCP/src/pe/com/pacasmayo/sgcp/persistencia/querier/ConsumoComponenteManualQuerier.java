package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConsumoComponentePlanQuerier.java
 * Modificado: Feb 5, 2010 5:45:30 PM 
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Consumocomponentemanual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ConsumoComponenteManualQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_ORDENMAN = "ordenproduccionmanual.pkCodigoOrdenproduccionmanual";

	public static List<Consumocomponentemanual> findByOrdenProduccionManual(Long codigoOrden) throws AplicacionException {
		try {
			return Querier.findByProperty(Consumocomponentemanual.class, CODIGO_ORDENMAN, codigoOrden);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Este método devuelve un consumo componente manual de una orden manual y
	 * un componente
	 * 
	 * @param codigoOrdenManual
	 * @param codigoComponente
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Consumocomponentemanual> getOrdenManualYComponente(Long codigoOrdenManual, Long codigoComponente)
			throws AplicacionException {

		try {

			Query query = query("from Consumocomponentemanual ccm where ccm.ordenproduccionmanual.pkCodigoOrdenproduccionmanual = ? and ccm.componente.pkCodigoComponente = ? ");
			query.setLong(0, codigoOrdenManual);
			query.setLong(1, codigoComponente);

			return (List<Consumocomponentemanual>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para persistir una entidad Consumocomponenteplan.
	 * 
	 * @param consumoCapacidadPlan
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Consumocomponentemanual consumoComponente) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(consumoComponente);

	}

	public static void delete(Consumocomponentemanual consumoComponente) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(consumoComponente);
	}

	public static void deleteByCodigoOrdenProduccionManual(Long codigoOrdenProduccionManual) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete from Consumocomponentemanual ccm where ccm.ordenproduccionmanual.pkCodigoOrdenproduccionmanual = ?");
		query.setLong(0, codigoOrdenProduccionManual);

		query.executeUpdate();

	}
}
