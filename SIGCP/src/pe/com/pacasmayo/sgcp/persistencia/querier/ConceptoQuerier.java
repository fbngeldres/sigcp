package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConceptoQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Concepto;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ConceptoQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String NOMBRE_DIVISION = "nombreConcepto";
	public static String CONCEPTO_NOMBRE = "nombreConcepto";
	public static String CONCEPTO_SALDO_INICIAL = "1";
	public static String CONCEPTO_PRODUCCION = "2";
	public static String CONCEPTO_CONSUMO = "3";
	public static String CONCEPTO_SALDO_FINAL = "4";
	public static String CONCEPTO_VENTAS = "5";
	public static String CONCEPTO_OTROS_CONSUMOS = "6";
	public static String CONCEPTO_COMPONENTE = "7";
	public static String CONCEPTO_CONSUMO_INTERNO = "8";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Concepto
	 * 
	 * @return
	 */
	public static List<Concepto> getAll() throws AplicacionException {

		return Querier.getAll(Concepto.class);
	}

	/**
	 * Método para obtener la lista de objectos Concepto
	 * 
	 * @return
	 */
	public static List<Concepto> getConceptoPorPorpiedades(Map propiedades) throws AplicacionException {

		return Querier.findByProperties(Concepto.class, propiedades);
	}

	/**
	 * Método para obtener la lista de objetos Concepto, ordenados por un campo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Concepto> getAllOrderBy(String order) {

		return Querier.getAll(Concepto.class, order);
	}

	/**
	 * Método para obtener una Concepto de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Concepto getById(Long codigo) throws ElementoNoEncontradoException {

		return (Concepto) Querier.getById(Concepto.class, codigo);
	}

	/**
	 * Método para obtener una Concepto de la BD.
	 * 
	 * @param nombre
	 * @throws ElementoNoEncontradoException
	 */
	public static Concepto getByNombre(String nombre) throws ElementoNoEncontradoException {
		List conceptos = Querier.findByProperty(Concepto.class, "nombreConcepto", nombre);
		if (conceptos != null && !conceptos.isEmpty()) {
			return (Concepto) conceptos.get(0);
		} else {
			throw new ElementoNoEncontradoException(
					"No se encontro el concepto, o se encontro mas de un elemento asociado a este nombre");
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Concepto en la BD.
	 * 
	 * @param concepto
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Concepto concepto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(concepto);
	}

	/**
	 * Metodo para modificar una Concepto de la BD.
	 * 
	 * @param concepto
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Concepto concepto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(concepto);
	}

	/**
	 * Metodo para eliminar una Concepto de la BD.
	 * 
	 * @param concepto
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Concepto concepto) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(concepto);
	}
}
