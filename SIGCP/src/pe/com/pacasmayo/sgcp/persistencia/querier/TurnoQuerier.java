package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: TurnoQuerier.java
 * Modificado: Jan 9, 2010 10:38:51 AM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Turno;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class TurnoQuerier extends Querier implements ConstantesMensajeAplicacion {

	public static final String CODIGO_UNIDAD = "unidad.pkCodigoUnidad";
	private static final String CODIGO_SCC = "codigoSccTurno";
	public static final String NOMBRE_TURNO = "nombreTurno";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * M�todo para obtener la lista de objectos Turno
	 * 
	 * @return
	 */
	public static List<Turno> getAll() throws AplicacionException {

		return Querier.getAll(Turno.class);
	}

	/**
	 * M�todo para obtener la lista de objectos Turno, ordenado por nombre
	 * 
	 * @return
	 */
	public static List<Turno> getAllOrderBy(String order) {

		return Querier.getAll(Turno.class, order);
	}

	/**
	 * M�todo para obtener un Turno de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Turno getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Turno.class, codigo);
	}

	/**
	 * M�todo para obtener los turnos por codigo unidad.
	 * 
	 * @param codigo
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Turno> findByUnidad(Long codigo) throws AplicacionException {

		try {
			return Querier.findByProperty(Turno.class, CODIGO_UNIDAD, codigo);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * M�todo para obtener los turnos por codigo unidad.
	 * 
	 * @param codigo
	 * @return
	 * @throws AplicacionException
	 */
	public static Turno findByCodigoSCC(Long codigoSCC) throws ElementoNoEncontradoException, EntornoEjecucionException,
			SesionVencidaException {
		return Querier.findByPropertyUniqueResult(Turno.class, CODIGO_SCC, codigoSCC);
	}

	/*
	 * M�todo para obtener un Turno por medio del nombre @param nombre @return
	 * @throws AplicacionException
	 */
	public static List<Turno> findByNombre(String nombre) throws AplicacionException {

		try {
			return Querier.getByLikeStringPropertie(Turno.class, NOMBRE_TURNO, nombre);
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
	 * M�todo para insertar un Turno en la BD.
	 * 
	 * @param turno
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Turno turno) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(turno);
	}

	/**
	 * M�todo para modificar un Turno de la BD.
	 * 
	 * @param turno
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Turno turno) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(turno);
	}

	/**
	 * M�todo para eliminar un Turno de la BD.
	 * 
	 * @param turno
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Turno turno) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(turno);
	}

	/**
	 * Metodo que permite filtrar la lista de Turnos segun un conjunto de
	 * propiedades simult�neamente
	 * 
	 * @param propiedades
	 * @return
	 * @throws SesionVencidaException
	 * @throws EntornoEjecucionException
	 * @throws AplicacionException
	 */
	public static List<Turno> buscarPorPropiedades(Map<String, Object> propiedades) throws ElementoNoEncontradoException,
			SesionVencidaException, EntornoEjecucionException, AplicacionException {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append("FROM Turno As tur WHERE");

		String caracterPuntoRegex = "\\.";
		String cadenaVacia = "";

		for (Iterator<String> iterator = propiedades.keySet().iterator(); iterator.hasNext();) {
			String clausulaWhere = " tur.{0} = :{1} {2}";
			String clave = iterator.next();
			String clausulaAnd = iterator.hasNext() ? "AND" : cadenaVacia;
			String appendWhere = MessageFormat.format(clausulaWhere,
					new Object[] { clave, clave.replaceAll(caracterPuntoRegex, cadenaVacia), clausulaAnd });
			queryStr.append(appendWhere);
		}

		try {
			String consulta = queryStr.toString();
			Session session = getSession();
			Query query = session.createQuery(consulta);

			for (Iterator<String> iterator = propiedades.keySet().iterator(); iterator.hasNext();) {
				String clave = iterator.next();
				query.setParameter(clave.replaceAll(caracterPuntoRegex, cadenaVacia), propiedades.get(clave));
			}

			return query.list();
		} catch (UnresolvableObjectException uOException) {
			logger.error(ERROR_OBJETO_NO_VALIDO, uOException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			logger.error(ERROR_OBJETO_NO_ENCONTRADO, oNFException);
			throw new ElementoNoEncontradoException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			logger.error(ERROR_HIBERNATE, hException);
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}

	}
}
