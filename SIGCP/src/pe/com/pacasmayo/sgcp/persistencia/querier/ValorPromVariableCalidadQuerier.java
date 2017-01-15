package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Producto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Valorpromvariablecalidad;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) Archivo:
 * ValorPromVariableCalidadQuerier.java Modificado: Jul 2, 2010 02:11:31 PM
 * Autor: daniel.loreto
 * 
 * Copyright (C) DBAccess, 2010. All rights reserved.
 * 
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ValorPromVariableCalidadQuerier extends Querier {

	public static List<Valorpromvariablecalidad> obtenerValorPromVariableCalidad(Long codigoTablaKardex,
			Long codigoVariableCalidad) throws AplicacionException {

		try {
			String queryString = "FROM Valorpromvariablecalidad valorPromVariableCalidad "
					+ "WHERE valorPromVariableCalidad.tablakardex.pkCodigoTablakardex = :pkCodigoTablakardex and "
					+ "valorPromVariableCalidad.productovariablecalidad.variablecalidad.pkCodigoVariablecalidad = :pkCodigoVariablecalidad";
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("pkCodigoTablakardex", codigoTablaKardex);
			parameters.put("pkCodigoVariablecalidad", codigoVariableCalidad);

			return Querier.executeQuery(queryString, parameters);

		} catch (RuntimeException e) {
			throw new AplicacionException(e);
		}
	}

	public static void eliminarPorCodigoTablaKardex(List<Long> codigosKardex) {
		StringBuilder querystr = new StringBuilder(
				"DELETE FROM Valorpromvariablecalidad c where c.tablakardex.pkCodigoTablakardex  in (:codigosKardex) ");
		Query query = Querier.query(querystr.toString());
		query.setParameterList("codigosKardex", codigosKardex);
		query.executeUpdate();

	}

	public static Tablakardex obtenerUltimoKardexHumedadIngreso(Producto producto) {

		StringBuilder sql = new StringBuilder("SELECT vpc.tablakardex FROM Valorpromvariablecalidad vpc ");
		sql.append(" WHERE ");
		sql.append("  vpc.productovariablecalidad.hojaruta.produccion.producto.pkCodigoProducto= :producto  ");
		sql.append(" AND vpc.valorValorpromvariblecalidad > 0");

		sql.append(" ORDER BY vpc.tablakardex.fechaTablakardex DESC ");

		Query query = query(sql.toString());
		query.setLong("producto", producto.getPkCodigoProducto());

		query.setMaxResults(1);
		Tablakardex suma = (Tablakardex) query.uniqueResult();

		return suma;

	}

	public static Valorpromvariablecalidad obtenerUltimoValorPromCalidadIngreso(Long codigoProductoVariableCalidad,
			Date fechaNotif) {

		StringBuilder sql = new StringBuilder("SELECT vpc FROM Valorpromvariablecalidad vpc ");
		sql.append(" WHERE ");
		sql.append("  vpc.productovariablecalidad.pkCodigoProductovariablecalid= :codigoproductovarvalidad  ");
		sql.append(" AND vpc.valorValorpromvariblecalidad > 0");
		sql.append(" AND vpc.tablakardex.fechaTablakardex <= :fechaKardex");
		sql.append(" ORDER BY vpc.tablakardex.fechaTablakardex DESC ");

		Query query = query(sql.toString());
		query.setLong("codigoproductovarvalidad", codigoProductoVariableCalidad);
		query.setDate("fechaKardex", fechaNotif);
		query.setMaxResults(1);
		Valorpromvariablecalidad promvarcalidad = (Valorpromvariablecalidad) query.uniqueResult();

		return promvarcalidad;

	}

}
