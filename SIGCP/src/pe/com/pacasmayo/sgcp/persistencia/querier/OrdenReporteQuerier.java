package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenreporte;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: OrdenReporteQuerier.java
 * Modificado: Aug 23, 2012 6:17:15 PM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class OrdenReporteQuerier extends Querier {

	private static final String TIPO_REPORTE = "tipoOrdenReporte";
	private static final String ORDEN_REPORTE = "ordenReporte";

	public static List<Ordenreporte> findByTipo(String tipoReporte) throws AplicacionException {
		try {
			return Querier.findByProperty(Ordenreporte.class, TIPO_REPORTE, tipoReporte);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

	}

	public static List<Ordenreporte> findByTipoOrderByOrden(String tipoReporte) throws AplicacionException {
		try {
			return Querier.findByPropertyAndOrderBy(Ordenreporte.class, TIPO_REPORTE, tipoReporte, ORDEN_REPORTE);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

	}

	/**
	 * Obtiene un registro Ordenreporte
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 * @throws Exception
	 */
	public static Ordenreporte getById(Long codigo) throws ElementoNoEncontradoException {

		Ordenreporte ordenreporte = Querier.getById(Ordenreporte.class, codigo);
		return ordenreporte;
	}

	/**
	 * Obtiene un registro Ordenreporte
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 * @throws Exception
	 */
	public static List<String> getTipoReporte() {

		StringBuilder sql = new StringBuilder(
				"SELECT distinct opr.tipoOrdenReporte FROM Ordenreporte opr ORDER BY opr.tipoOrdenReporte");

		Query query = query(sql.toString());
		return query.list();
	}

}
