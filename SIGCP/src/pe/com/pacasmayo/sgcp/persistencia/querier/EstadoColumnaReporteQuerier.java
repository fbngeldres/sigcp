package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadocolumnareporte;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoColumnaReporteQuerier.java
 * Modificado: Jun 10, 2010 3:20:45 AM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class EstadoColumnaReporteQuerier extends Querier {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_ESTADO_COLUMNA_REPORTE = "nombreEstadocolumnareporte";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener una lista de Estadocolumnareporte
	 * 
	 * @return
	 */
	public static List<Estadocolumnareporte> getAll() {

		return Querier.getAll(Estadocolumnareporte.class);
	}

	/**
	 * Método para obtener una lista de Estadocolumnareporte, ordenados por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Estadocolumnareporte> getAllOrderBy(String order) {

		return Querier.getAll(Estadocolumnareporte.class, order);
	}

	/**
	 * Método para obtener un Estadocolumnareporte de la BD por código.
	 * 
	 * @param codigo
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadocolumnareporte getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadocolumnareporte) Querier.getById(Estadocolumnareporte.class, codigo);

	}

	/**
	 * Método para obtener las Actvidades de la BD por nombre.
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 * @throws ParametroInvalidoException
	 * @throws ElementoNoEncontradoException
	 */
	public static Estadocolumnareporte findByNombre(String nombre) throws AplicacionException {

		try {
			return (Estadocolumnareporte) (EstadoColumnaReporteQuerier.findByProperty(Estadocolumnareporte.class,
					NOMBRE_ESTADO_COLUMNA_REPORTE, nombre)).get(0);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

	}
}
