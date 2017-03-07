package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tipomedioalmacenamiento;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: TipoMedioAlmacenamientoQuerier.java
 * Modificado: Mar 11, 2010 3:53:05 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class TipoMedioAlmacenamientoQuerier extends Querier {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_TIPO_MEDIO_ALMACENAMIENTO = "nombreTipomedioalmacenamiento";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Tipomedioalmacenamiento
	 * 
	 * @return
	 */
	public static List<Tipomedioalmacenamiento> getAll() throws AplicacionException {

		return Querier.getAll(Tipomedioalmacenamiento.class);
	}

	/**
	 * Método para obtener la lista de objectos Tipomedioalmacenamiento
	 * ordenados por nombre
	 * 
	 * @return
	 */
	public static List<Tipomedioalmacenamiento> getAllPorNombre() throws AplicacionException {
		return getAllOrderBy(NOMBRE_TIPO_MEDIO_ALMACENAMIENTO);
	}

	/**
	 * Método para obtener la lista de objectos Tipomedioalmacenamiento,
	 * ordenados por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Tipomedioalmacenamiento> getAllOrderBy(String order) {

		return Querier.getAll(Tipomedioalmacenamiento.class, order);
	}

	/**
	 * Método para obtener un Tipomedioalmacenamiento de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Tipomedioalmacenamiento getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Tipomedioalmacenamiento.class, codigo);
	}

	/**
	 * Método para obtener un Tipomedioalmacenamiento por el nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Tipomedioalmacenamiento> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Tipomedioalmacenamiento.class, NOMBRE_TIPO_MEDIO_ALMACENAMIENTO, nombre);
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

}
