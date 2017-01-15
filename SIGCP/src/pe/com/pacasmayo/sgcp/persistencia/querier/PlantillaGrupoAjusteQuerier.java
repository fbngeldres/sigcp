package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillaajusteproducto;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plantillagrupoajuste;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlantillaGrupoAjusteQuerier.java
 * Modificado: Aug 6, 2010 2:07:18 PM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class PlantillaGrupoAjusteQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String FK_CODIGO_GRUPOAJUSTE = "plantillagrupoajuste.pkCodigoPlantillagrupoajuste";

	/**
	 * Método para obtener la plantilla de un ajuste en BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Plantillagrupoajuste getById(Long codigo) throws ElementoNoEncontradoException {

		return (Plantillagrupoajuste) Querier.getById(Plantillagrupoajuste.class, codigo);
	}

	/**
	 * Método para obtener los grupos de ajustes por Linea de Negocio
	 * 
	 * @param codigoLineaNegocio
	 * @return
	 */
	public static List<Plantillagrupoajuste> obtenerGruposAjustePorLineaNegocio(Long codigoLineaNegocio) {
		List<Plantillagrupoajuste> gruposAjuste = new ArrayList<Plantillagrupoajuste>();
		String mensajeError = "";

		try {

			String consulta = "from Plantillagrupoajuste pga " + "where " + "pga.lineanegocio.pkCodigoLineanegocio = ? ";

			Query query = Querier.query(consulta);

			query.setLong(0, codigoLineaNegocio);

			gruposAjuste = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return gruposAjuste;
	}

	/**
	 * Fabian Geldres Método para obtener los grupos de ajustes por Linea de
	 * Negocio Valido si la linea de negocio es nula o no
	 * 
	 * @param codigoLineaNegocio
	 * @return
	 */
	public static List<Plantillagrupoajuste> obtenerGruposAjuste(Long codigoLineaNegocio) {
		List<Plantillagrupoajuste> gruposAjuste = new ArrayList<Plantillagrupoajuste>();
		String mensajeError = "";

		try {

			String consulta = "from Plantillagrupoajuste pga " + "where 1=1 ";
			if (codigoLineaNegocio != null) {
				consulta += " AND pga.lineanegocio.pkCodigoLineanegocio = ? ";
			}

			Query query = Querier.query(consulta);
			if (codigoLineaNegocio != null) {
				query.setLong(0, codigoLineaNegocio);
			}
			gruposAjuste = query.list();

		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);

		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		}

		return gruposAjuste;
	}

	/**
	 * @param propiedades
	 * @return
	 */
	public static List<Plantillaajusteproducto> findByProperties(Map propiedades) {

		return Querier.findByProperties(Plantillaajusteproducto.class, propiedades);

	}
}
