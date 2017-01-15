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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadoperativa;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class CapacidadOperativaQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static final Long TIPO_CAPACIDAD_TONELADAS = new Long(1);

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
	public static List<Capacidadoperativa> getAll() {

		return Querier.getAll(Capacidadoperativa.class);
	}

	/**
	 * Método para obtener la lista de objetos Conceptomensual, ordenados por un
	 * campo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Capacidadoperativa> getAllOrderBy(String order) {

		return Querier.getAll(Capacidadoperativa.class, order);
	}

	/**
	 * Método para obtener una Conceptomensual de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Capacidadoperativa getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Capacidadoperativa.class, codigo);
	}

	/**
	 * Método para obtener una Conceptomensual de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Capacidadoperativa getByOperacionTasaRealAndTipoCapacidad(Long codigoOperacion, Long codigoTasaReal,
			Long codigoTipoCapacidad) throws ElementoNoEncontradoException {
		Query query = query("from Capacidadoperativa c " + "where c.operacion.pkCodigoOperacion = ? "
				+ "and c.tasarealproduccion.pkCodigoTasarealproduccion = ? "
				+ "and c.tipocapacidadoperativa.pkCodigoTipocapacidadoperativ = ?");
		query.setLong(0, codigoOperacion);
		query.setLong(1, codigoTasaReal);
		query.setLong(2, codigoTipoCapacidad);
		return (Capacidadoperativa) query.uniqueResult();
	}

	public static List<Capacidadoperativa> getByOperacionYAnnio(Long codigoOperacion, Integer annio)
			throws ElementoNoEncontradoException {
		Query query = query("from Capacidadoperativa co where co.operacion.pkCodigoOperacion = ? "
				+ "and co.pkCodigoCapacidadoperativa in (select corm.capacidadoperativa.pkCodigoCapacidadoperativa from Capacidadoperativaregistromensu corm where corm.annoCapacidadoperativaregistro = ?) ");
		query.setLong(0, codigoOperacion);
		query.setInteger(1, annio);

		return (List<Capacidadoperativa>) query.list();
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
	public static void save(Capacidadoperativa capacidadoperativa) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(capacidadoperativa);
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
	public static void update(Capacidadoperativa capacidadoperativa) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(capacidadoperativa);
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
	public static void delete(Capacidadoperativa capacidadoperativa) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(capacidadoperativa);
	}

	public static Double getTotalByHojaRutaYPlanYAnnioYMes(Long codigoHoja, Integer annio, Short mes, Long codigoPlanAnual) {
		Query query = query(" select sum(corm.cantidadRegistroCapacidadoper) from Capacidadoperativaregistromensu corm "
				+ "where corm.annoCapacidadoperativaregistro = ? "
				+ "and corm.mesCapacidadoperativaregistrom = ? "
				+ "and corm.plananual.pkCodigoPlananual = ? "
				+ "and corm.capacidadoperativa.operacion.hojaruta.pkCodigoHojaruta = ? "
				+ "and corm.capacidadoperativa.operacion.hojaruta.estadohojaruta.pkCodigoEstadohojaruta = 1 "
				+ "and corm.capacidadoperativa.tipocapacidadoperativa.pkCodigoTipocapacidadoperativ = ? "
				+ "and corm.capacidadoperativa.tasarealproduccion.pkCodigoTasarealproduccion in (select trprm.tasarealproduccion.pkCodigoTasarealproduccion from Tasarealprodregistromensual trprm where trprm.annoTasarealprodregmensual = ? )");

		query.setInteger(0, annio);
		query.setShort(1, mes);
		query.setLong(2, codigoPlanAnual);
		query.setLong(3, codigoHoja);
		query.setLong(4, TIPO_CAPACIDAD_TONELADAS);
		query.setInteger(5, annio);

		return (Double) query.uniqueResult();
	}
}
