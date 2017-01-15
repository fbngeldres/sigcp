package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.ParametroSistema;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ParametroSistemaQuerier.java
 * Modificado: Sep 29, 2011 11:08:22 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ParametroSistemaQuerier extends Querier {

	private final static int MAXIMO_RESULTADO_UNO = 1;

	public static ParametroSistema obtenerParametroSistema(String nombreParametro) {

		StringBuilder sql = new StringBuilder("FROM ParametroSistema ps where ps.nombreParametro = :parametro");
		Query query = query(sql.toString());
		query.setString("parametro", nombreParametro);
		query.setMaxResults(MAXIMO_RESULTADO_UNO);
		return (ParametroSistema) query.uniqueResult();
	}

	public static List<String> obtenerValorParametroLike(String nombreParametro) {
		StringBuilder sql = new StringBuilder(
				"SELECT ps.valorParametro FROM ParametroSistema ps where ps.nombreParametro like :parametro ");
		Query query = query(sql.toString());
		query.setString("parametro", "%" + nombreParametro + "%");
		List<String> listaProcesos = query.list();
		return listaProcesos;
	}

	public static List<ParametroSistema> obtenerParametrosLike(String nombreParametro) {
		StringBuilder sql = new StringBuilder("FROM ParametroSistema ps");
		sql.append(" where ps.nombreParametro like :parametro  ");
		sql.append(" ORDER BY ps.nombreParametro ASC ");

		Query query = query(sql.toString());
		query.setString("parametro", "%" + nombreParametro + "%");
		List<ParametroSistema> listaParametros = query.list();
		return listaParametros;
	}
}
