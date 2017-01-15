package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: CapacidadOperativaRegistroMensuQuerier.java
 * Modificado: Feb 5, 2010 4:52:34 PM 
 * Autor: judith.rondon
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadoperativa;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Capacidadoperativaregistromensu;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class CapacidadOperativaRegistroMensuQuerier extends Querier implements ConstantesMensajeAplicacion {

	/**
	 * Método para persistir una entidad Capacidadoperativaregistromensu.
	 * 
	 * @param area
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Capacidadoperativaregistromensu capacidadoperativaregistromensu) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(capacidadoperativaregistromensu);

	}

	/**
	 * Método para obtener una Conceptomensual de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 * @throws LogicaException
	 */
	public static void deleteAllByCapacidadOperativa(Long codigoCapacidadOperativa, Long codigoPlanAnual)
			throws ElementoNoEncontradoException, LogicaException {
		try {
			Query query = query("delete Capacidadoperativaregistromensu c "
					+ "where c.capacidadoperativa.pkCodigoCapacidadoperativa = ? and c.plananual.pkCodigoPlananual = ?");
			query.setLong(0, codigoCapacidadOperativa);
			query.setLong(1, codigoPlanAnual);

			query.executeUpdate();
		} catch (ConstraintViolationException e) {
			throw new HibernateException("Se han asignado valores de Consumo al Plan en Estado Generado.");
		}
	}

	public static Set<Capacidadoperativaregistromensu> getByCapacidadTipoAndPlanAnual(Capacidadoperativa capacidadoperativa,
			Long codigoTipoCapacidadDias, Long codigoPlanAnual, Integer annio) {
		Query query = query("from Capacidadoperativaregistromensu corm "
				+ "where corm.annoCapacidadoperativaregistro = ? "
				+ "and corm.capacidadoperativa.pkCodigoCapacidadoperativa = ? "
				+ "and corm.plananual.pkCodigoPlananual = ? "
				+ "and corm.capacidadoperativa.tipocapacidadoperativa.pkCodigoTipocapacidadoperativ = ? order by corm.mesCapacidadoperativaregistrom ");
		query.setInteger(0, annio);
		query.setLong(1, capacidadoperativa.getPkCodigoCapacidadoperativa());
		query.setLong(2, codigoPlanAnual);
		query.setLong(3, codigoTipoCapacidadDias);
		List<Capacidadoperativaregistromensu> listaCapacidadRM = query.list();

		Set<Capacidadoperativaregistromensu> setCapacidadRM = new HashSet<Capacidadoperativaregistromensu>(listaCapacidadRM);
		return setCapacidadRM;

	}

}
