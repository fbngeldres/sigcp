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

import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Conceptomensual;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class ConceptoMensualQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Concepto
	 * 
	 * @return
	 */
	public static List<Conceptomensual> getAll() {

		return Querier.getAll(Conceptomensual.class);
	}

	/**
	 * Método para obtener la lista de objetos Conceptomensual, ordenados por un
	 * campo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Conceptomensual> getAllOrderBy(String order) {

		return Querier.getAll(Conceptomensual.class, order);
	}

	/**
	 * Método para obtener una Conceptomensual de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Conceptomensual getById(Long codigo) throws ElementoNoEncontradoException {

		return (Conceptomensual) Querier.getById(Conceptomensual.class, codigo);
	}

	/**
	 * Método para obtener una Conceptomensual de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Conceptomensual getByConceptoAndProduccion(Long codigoConcepto, Long codigoProduccion)
			throws ElementoNoEncontradoException {
		Query query = query("from Conceptomensual c " + "where c.concepto.pkCodigoConcepto = ? "
				+ "and c.produccion.pkProduccion = ?  ");
		query.setLong(0, codigoConcepto);
		query.setLong(1, codigoProduccion);
		return (Conceptomensual) query.uniqueResult();
	}

	/**
	 * Método para obtener una Conceptomensual de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Conceptomensual getByConceptoAndProduccionAndMesAndAnnio(Long codigoConcepto, Long codigoProduccion, Short mes,
			Integer annio) throws ElementoNoEncontradoException {
		Query query = query("from Conceptomensual c "
				+ "where c.concepto.pkCodigoConcepto = ? "
				+ "and c.produccion.pkProduccion = ?  "
				+ "and c.pkCodigoConceptomensual in "
				+ "(select crm.conceptomensual.pkCodigoConceptomensual from Conceptoregistromensual crm where crm.mesConceptoregistromensual = ? and crm.annoConceptoregistromensual = ?)");
		query.setLong(0, codigoConcepto);
		query.setLong(1, codigoProduccion);
		query.setShort(2, mes);
		query.setInteger(3, annio);

		return (Conceptomensual) query.uniqueResult();
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Conceptomensual en la BD.
	 * 
	 * @param conceptoMensual
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Conceptomensual conceptoMensual) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(conceptoMensual);
	}

	/**
	 * Metodo para modificar una Conceptomensual de la BD.
	 * 
	 * @param conceptoMensual
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Conceptomensual conceptoMensual) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(conceptoMensual);
	}

	/**
	 * Metodo para eliminar una Conceptomensual de la BD.
	 * 
	 * @param conceptoMensual
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Conceptomensual conceptoMensual) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(conceptoMensual);
	}
}
