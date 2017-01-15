package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: SociedadQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.UnresolvableObjectException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Sociedad;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class SociedadQuerier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_SOCIEDAD = "nombreSociedad";
	private static String CODIGO_SCC_SOCIEDAD = "codigoSccSociedad";
	private static String CODIGO_DIVISION = "division.pkCodigoDivision";
	private static String SIGLAS_SOCIEDAD = "siglasSociedad";
	private static String CODIGO_SAP = "codigoSapSociedad";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Sociedad
	 * 
	 * @return
	 */
	public static List<Sociedad> getAll() throws AplicacionException {

		return Querier.getAll(Sociedad.class);
	}

	/**
	 * Método para obtener la lista de objectos Sociedad, ordenadas por un
	 * atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Sociedad> getAllOrderBy(String order) {

		return Querier.getAll(Sociedad.class, order);
	}

	/**
	 * Método para obtener una Sociedad de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Sociedad getById(Long codigo) throws ElementoNoEncontradoException, SesionVencidaException,
			EntornoEjecucionException {

		return (Sociedad) Querier.getById(Sociedad.class, codigo);
	}

	/**
	 * Método para obtener una sociedad por el nombre
	 * 
	 * @param nombre
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Sociedad> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Sociedad.class, NOMBRE_SOCIEDAD, nombre);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener una sociedad por el codigo SAP
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public static Sociedad findByCodigoSAP(String codigoSAP) throws ElementoNoEncontradoException {
		return (Sociedad) Querier.findByPropertyUniqueResult(Sociedad.class, CODIGO_SAP, codigoSAP);
	}

	public static Sociedad findByNombreAndDivision(String nombre, Long codigoDivision) throws AplicacionException {

		Sociedad sociedad = (Sociedad) Querier.findByProperties(Sociedad.class, NOMBRE_SOCIEDAD, nombre, CODIGO_DIVISION,
				codigoDivision);

		return sociedad;
	}

	/**
	 * Método para obtener una sociedad por el codigo SCC
	 * 
	 * @param codigoSCC
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Sociedad> findByCodigoSCC(Long codigoSCC) throws AplicacionException {

		try {
			return Querier.findByProperty(Sociedad.class, CODIGO_SCC_SOCIEDAD, codigoSCC);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/**
	 * Método para obtener una sociedad por el codigo Division
	 * 
	 * @param codDivision
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Sociedad> findByCodigoDivision(Long codDivision) throws AplicacionException {

		try {
			return Querier.findByPropertyAndOrderBy(Sociedad.class, CODIGO_DIVISION, codDivision, NOMBRE_SOCIEDAD);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (org.hibernate.UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		} catch (RuntimeException hException) {
			throw new AplicacionException(ERROR_FATAL_FALLO, hException.getCause());
		}
	}

	/**
	 * Método para obtener una sociedad por siglas
	 * 
	 * @param siglas
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Sociedad> findBySiglas(String siglas) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Sociedad.class, SIGLAS_SOCIEDAD, siglas);
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO),
					oNFException.getCause());
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO),
					uOException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE), hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar una Sociedad en la BD.
	 * 
	 * @param sociedad
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Sociedad sociedad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(sociedad);
	}

	/**
	 * Método para modificar una Sociedad de la BD.
	 * 
	 * @param sociedad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Sociedad sociedad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(sociedad);
	}

	/**
	 * Método para eliminar una Sociedad de la BD.
	 * 
	 * @param sociedad
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Sociedad sociedad) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(sociedad);
	}
}
