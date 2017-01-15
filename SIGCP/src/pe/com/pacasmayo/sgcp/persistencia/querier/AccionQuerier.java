package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Accion;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: AccionQuerier.java
 * Modificado: Sep 29, 2011 11:08:22 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class AccionQuerier extends Querier {

	public static List<Accion> obtenerAccionPorMenu(Long codigo) throws AplicacionException {

		Query query = query("from Accion acc where acc.menu.pkCodigoMenu = ? ");
		query.setLong(0, codigo);

		return (List<Accion>) query.list();
	}

	public static void deleteAllByCodigoMenu(Long codigoMenu) throws ElementoNoEncontradoException, LogicaException {
		try {
			Query query = query("delete from Accion a " + "where a.menu.pkCodigoMenu = ? ");
			query.setLong(0, codigoMenu);

			query.executeUpdate();
		} catch (ConstraintViolationException e) {
			throw new HibernateException("No es posible Eliminar la Acción porque están relada con otros datos.");
		}
	}

	public static void deleteAllById(Long codigo) throws ElementoNoEncontradoException, LogicaException {
		try {
			Query query = query("delete from Accion a " + "where a.pkCodigoAccion = ? ");
			query.setLong(0, codigo);

			query.executeUpdate();
		} catch (ConstraintViolationException e) {
			throw new HibernateException("No es posible Eliminar la Acción porque están relada con otros datos.");
		}
	}
}
