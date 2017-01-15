package pe.com.pacasmayo.sgcp.persistencia.querier;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Documentomaterial;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: DocumentoMaterialQuerier.java
 * Modificado: Jan 9, 2010 11:04:31 AM 
 * Autor: jesus.becerra
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class DocumentoMaterialQuerier extends Querier {

	private static String mensajeError;

	/**
	 * Método para obtener la lista de objectos Tipodocumentomaterial
	 * 
	 * @return
	 */
	public static List<Documentomaterial> getAll() throws EntornoEjecucionException, SesionVencidaException {

		return Querier.getAll(Documentomaterial.class);
	}

	/**
	 * Método para obtener la lista de objectos Tipodocumentomaterial, ordenada
	 * por un atributo
	 * 
	 * @param order
	 * @return
	 */
	public static List<Documentomaterial> getAllOrderBy(String order) {

		return Querier.getAll(Documentomaterial.class, order);
	}

	/**
	 * Método para obtener un tipo de documento material de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Documentomaterial getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Documentomaterial.class, codigo);
	}

	/**
	 * Método para insertar un tipo de documento material en la BD.
	 * 
	 * @param tipodocumentomaterial
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Documentomaterial documentomaterial) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(documentomaterial);
	}

	/**
	 * Método para modificar un tipo de documento material de la BD.
	 * 
	 * @param tipodocumentomaterial
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Documentomaterial documentomaterial) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(documentomaterial);
	}

	/**
	 * Método para eliminar un tipo de documento material de la BD.
	 * 
	 * @param tipodocumentomaterial
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Documentomaterial documentomaterial) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(documentomaterial);
	}

	@SuppressWarnings("unchecked")
	public static void eliminarDocumentosGeneradosSap(Date fechaNotif) throws ParametroInvalidoException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		StringBuilder queryBuilder = new StringBuilder("FROM Documentomaterial AS dm WHERE ");
		queryBuilder.append("dm.fechaDocumentomaterial = :fecha AND ");
		queryBuilder.append("dm.origenSapMovimiento = :origenSapMovimiento");

		Session session = null;

		try {
			session = PersistenciaFactory.currentSession();
			session.beginTransaction();

			Query query = query(queryBuilder.toString());

			query.setDate("fecha", fechaNotif);
			query.setBoolean("origenSapMovimiento", new Boolean(true));

			List<Documentomaterial> list = query.list();

			for (Iterator<Documentomaterial> iterator = list.iterator(); iterator.hasNext();) {
				Documentomaterial item = iterator.next();
				session.delete(item);
			}
		} catch (PropertyValueException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_VALIDO);
			logger.error(mensajeError, e);
			throw new ParametroInvalidoException(mensajeError, e);
		} catch (ObjectDeletedException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ELIMINADO);
			logger.error(mensajeError, e);
			throw new ElementoEliminadoException(mensajeError, e);
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(mensajeError, e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(mensajeError, e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (TransactionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_TRANSACCION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (HibernateException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_HIBERNATE);
			logger.error(mensajeError);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(mensajeError, e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}

	public static void eliminarPorFechaYMovimientoSAP(Date fecha, boolean movimientoSAP) {
		StringBuilder querystr = new StringBuilder("DELETE FROM Documentomaterial dm ");
		querystr.append(" WHERE dm.fechaDocumentomaterial=:fecha ");
		querystr.append(" AND dm.origenSapMovimiento=:condicion ");
		Query query = Querier.query(querystr.toString());
		query.setDate("fecha", fecha);
		query.setBoolean("condicion", movimientoSAP);
		query.executeUpdate();

	}
}
