package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Columnareporte;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ColumnaReporteQuerier.java
 * Modificado: Jun 10, 2010 2:36:15 AM 
 * Autor: lplaz
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class ColumnaReporteQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Método para insertar una Plantilla en la BD.
	 * 
	 * @param columna
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Columnareporte columna) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(columna);
	}

	/**
	 * Método para modificar una Plantilla de la BD.
	 * 
	 * @param columna
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Columnareporte columna) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(columna);
	}

	/**
	 * Método para eliminar una Plantilla de la BD.
	 * 
	 * @param columna
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Columnareporte columna) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(columna);
	}

	/**
	 * Método para obtener una Columnareporte de la BD por el código.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Columnareporte getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Columnareporte.class, codigo);
	}

	/**
	 * Método para obtener las columnas de una plantilla dada con un estado
	 * determinado
	 * 
	 * @param codigoPlantilla
	 * @param estado
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Columnareporte> obtenerColumnasPorPlantillaYEstado(Long codigoPlantilla, String estado) {
		List<Columnareporte> columnas = new ArrayList<Columnareporte>();

		String consulta = "from Columnareporte cr where cr.plantillareporte.pkCodigoPlantillareporte = ? and"
				+ " cr.estadocolumnareporte.nombreEstadocolumnareporte = ? and cr.posicionColumnareporte between 1 and 14 ";
		String mensajeError = "";

		try {

			Query query = Querier.query(consulta);
			query.setLong(0, codigoPlantilla);
			query.setString(1, estado);

			columnas = query.list();

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

		return columnas;
	}

	@SuppressWarnings("unchecked")
	public static List<Columnareporte> obtenerColumnas(Long codigoPuestoTrabajo, Long codigoProceso, String estadoColumna,
			String estadoPlantilla) throws AplicacionException {

		List<Columnareporte> columnas = new ArrayList<Columnareporte>();

		String consulta = "FROM Columnareporte AS cr WHERE"
				+ " cr.plantillareporte.puestotrabajo.pkCodigoPuestotrabajo = :codigoPuestoTrabajo"
				+ " AND cr.plantillareporte.proceso.pkCodigoProceso = :codigoProceso"
				+ " AND upper(cr.plantillareporte.estadoplantillareporte.nombreEstadoplantillareporte) = upper(:nombreEstadoPlantilla)"
				+ " AND upper(cr.estadocolumnareporte.nombreEstadocolumnareporte) = upper(:nombreEstadoColumna)"
				+ " ORDER BY cr.posicionColumnareporte";

		try {

			Query query = Querier.query(consulta);
			query.setLong("codigoPuestoTrabajo", codigoPuestoTrabajo);
			query.setLong("codigoProceso", codigoProceso);
			query.setString("nombreEstadoPlantilla", estadoPlantilla);
			query.setString("nombreEstadoColumna", estadoColumna);

			columnas = query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

		return columnas;
	}
}
