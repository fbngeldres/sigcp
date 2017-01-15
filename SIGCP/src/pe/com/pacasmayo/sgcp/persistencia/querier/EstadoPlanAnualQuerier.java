package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoPlanAnualQuerier.java
 * Modificado: Feb 4, 2010 3:21:24 PM 
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
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoplananual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class EstadoPlanAnualQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_ESTADOPLANANUAL = "nombreEstadoplan";

	/**
	 * Metodo para modificar una Division de la BD.
	 * 
	 * @param divisionImplBean
	 * @throws Exception
	 */
	public static Estadoplananual getById(Long codigo) throws ElementoNoEncontradoException {

		Estadoplananual estadoPlanAnual = (Estadoplananual) Querier.getById(Estadoplananual.class, codigo);

		return estadoPlanAnual;
	}

	/**
	 * Metodo que busca los estados de un plan anual por su nombre
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Estadoplananual> findByNombre(String value) throws AplicacionException {
		try {
			return (List<Estadoplananual>) Querier.findByProperty(Estadoplananual.class, NOMBRE_ESTADOPLANANUAL, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

}
