package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: EstadoNotificacionQuerier.java
 * Modificado: Dec 3, 2009 4:13:19 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadonotificacion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class EstadoNotificacionQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_ESTADO_NOTIFICACION = "estadonotificacion.pkCodigoEstadonotificacion";
	private static String NOMBRE_ESTADO_NOTIFICACION = "nombreEstadonotificacion";
	public static String ESTADO_APROBADO = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.notificacionPlanta.estadoAprobado");
	public static String ESTADO_CERRADO = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.notificacionPlanta.estadoCerrado");
	public static String ESTADO_ABIERTO = ManejadorPropiedades
			.obtenerPropiedadPorClave("notificacion.notificacionPlanta.estadoAbierto");

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Estadonotificacion
	 * 
	 * @return
	 */
	public static List<Estadonotificacion> getAll() throws AplicacionException {

		return Querier.getAll(Estadonotificacion.class);
	}

	/**
	 * Método para obtener una Estadonotificacion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException {
	 */
	public static Estadonotificacion getById(Long codigo) throws ElementoNoEncontradoException {

		return (Estadonotificacion) Querier.getById(Estadonotificacion.class, codigo);
	}

	/**
	 * Método para obtener un tipo de documento material de la BD por el nombre.
	 * 
	 * @param nombre
	 * @throws AplicacionException
	 * @throws Exception
	 */
	public static Estadonotificacion findByNombreUniqueResult(String nombre) throws AplicacionException {

		try {
			return (Estadonotificacion) Querier.findByPropertyUniqueResult(Estadonotificacion.class, NOMBRE_ESTADO_NOTIFICACION,
					nombre);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/***************************************************************************
	 * METODOS DEL CRUD
	 **************************************************************************/

	/**
	 * Metodo para insertar una Estadonotificacion en la BD.
	 * 
	 * @param estadonotificacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Estadonotificacion estadonotificacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(estadonotificacion);
	}

	/**
	 * Metodo para modificar una Estadonotificacion de la BD.
	 * 
	 * @param estadonotificacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Estadonotificacion estadonotificacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(estadonotificacion);
	}

	/**
	 * Metodo para eliminar una Estadonotificacion de la BD.
	 * 
	 * @param estadonotificacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Estadonotificacion estadonotificacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(estadonotificacion);
	}

	/**
	 * Metodo que trae solo los estados "Cerrado" y "AProbado".
	 * 
	 * @param estadonotificacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static List<Estadonotificacion> getEstadosGestionar() throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		StringBuilder sql = new StringBuilder("FROM Estadonotificacion est");
		sql.append(" WHERE ");
		sql.append(" est.nombreEstadonotificacion = :estadoAprobado  ");
		sql.append(" OR est.nombreEstadonotificacion = :estadoCerrado  ");

		Query query = query(sql.toString());

		query.setString("estadoAprobado", ESTADO_APROBADO);
		query.setString("estadoCerrado", ESTADO_CERRADO);

		return query.list();
	}

}
