package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: LineaNegocioQuerier.java
 * Modificado: Dec 3, 2009 4:13:19 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class LineaNegocioQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static final String NOMBRE_LINEA_NEGOCIO = "nombreLineanegocio";
	private static final String CODIGO_SCC = "codigoSccLineanegocio";
	private static final String CODIGO_UNIDAD = "unidad.pkCodigoUnidad";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Lineanegocio
	 * 
	 * @return
	 */
	public static List<Lineanegocio> getAll() throws AplicacionException {

		return Querier.getAll(Lineanegocio.class);
	}

	/**
	 * Método para obtener todas las Lineanegocio, ordenados por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Lineanegocio> getAllOrderBy(String order) {

		return Querier.getAll(Lineanegocio.class, order);
	}

	/**
	 * Método para obtener una Línea de Negocio de la BD por código.
	 * 
	 * @param codigo
	 * @return
	 */
	public static Lineanegocio getById(Long codigo) throws ElementoNoEncontradoException {

		return (Lineanegocio) Querier.getById(Lineanegocio.class, codigo);
	}

	/**
	 * Método para obtener los Línea de Negocio de la BD por codigoSCC.
	 * 
	 * @param codigoSCC
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Lineanegocio> findByCodigoSCC(Long codigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Lineanegocio.class, CODIGO_SCC, codigoSCC);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Línea de Negocio de la BD por nombre.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Lineanegocio> findByNombre(String value) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Lineanegocio.class, NOMBRE_LINEA_NEGOCIO, value);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener los Línea de Negocio de la BD por unidad.
	 * 
	 * @param value
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Lineanegocio> findByCodigoUnidad(Long codigo) throws AplicacionException {

		try {
			List<Lineanegocio> lineas = Querier.findByPropertyAndOrderBy(Lineanegocio.class, CODIGO_UNIDAD, codigo,
					NOMBRE_LINEA_NEGOCIO);
			List<Lineanegocio> retorno = new ArrayList<Lineanegocio>();
			for (Iterator<Lineanegocio> iterLinea = lineas.iterator(); iterLinea.hasNext();) {
				Lineanegocio linea = (Lineanegocio) iterLinea.next();
				linea.getCodigoSapLineanegocio();
				retorno.add(linea);
			}
			return retorno;
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
	 * Método para Insertar una Línea de Negocio en la BD.
	 * 
	 * @param lineaNegocio
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Lineanegocio lineaNegocio) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(lineaNegocio);
	}

	/**
	 * Metodo para modificar una Línea de Negocio de la BD.
	 * 
	 * @param lineaNegocio
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Lineanegocio lineaNegocio) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(lineaNegocio);
	}

	/**
	 * Método para eliminar una Línea de Negocio de la BD.
	 * 
	 * @param lineaNegocio
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Lineanegocio lineaNegocio) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(lineaNegocio);
	}
}