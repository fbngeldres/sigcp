package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factorvariacionpuestotrabajo;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Lineanegocio;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Ordenproduccion;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Tablakardex;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorvariacionpuestotrabajoQuerier.java
 * Modificado: May 13, 2011 5:03:24 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class FactorVariacionPuestoTrabajoQuerier extends Querier {

	private static Logger log = Logger.getLogger(FactorVariacionPuestoTrabajoQuerier.class);
	private static String mensajeError;

	@SuppressWarnings("unchecked")
	public static List<Factorvariacionpuestotrabajo> obtenerFactoresVariableVariacion(Tablakardex tablaKardex) {
		try {
			Ordenproduccion ordenproduccion = tablaKardex.getProducciondiaria().getOrdenproduccion();
			Date fecha = tablaKardex.getFechaTablakardex();
			Lineanegocio lineanegocio = tablaKardex.getProducciondiaria().getPartediario().getLineanegocio();

			StringBuilder hql = new StringBuilder("FROM Factorvariacionpuestotrabajo AS f WHERE ");
			hql.append("f.consumopuestotrabajo.productogenerado.ordenproduccion.pkCodigoOrdenproduccion = :codOrdenProd AND ");
			hql.append("f.consumopuestotrabajo.productogenerado.tablaoperacion.produccionpuestotrabajo.partediario.lineanegocio.pkCodigoLineanegocio = :codLineaNeg AND ");
			hql.append("f.consumopuestotrabajo.productogenerado.tablaoperacion.fechaTablaoperacion = :fecha");

			Query query = Querier.query(hql.toString());

			query.setLong("codOrdenProd", ordenproduccion.getPkCodigoOrdenproduccion());
			query.setLong("codLineaNeg", lineanegocio.getPkCodigoLineanegocio());
			query.setDate("fecha", fecha);

			return query.list();
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			log.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			log.error(mensajeError, e);
			throw new EntornoEjecucionException(ERROR_FATAL_FALLO, e);
		}
	}

	public static void eliminarPorCodigodConsumoPuestoTrabajo(List<Long> codigosConsumoPuestosT) {
		StringBuilder querystr = new StringBuilder(
				"DELETE FROM Factorvariacionpuestotrabajo c where c.consumopuestotrabajo.pkCodigoConsumopuestotrabajo  in (:codigos) ");
		Query query = Querier.query(querystr.toString());
		query.setParameterList("codigos", codigosConsumoPuestosT);
		query.executeUpdate();
	}

}
