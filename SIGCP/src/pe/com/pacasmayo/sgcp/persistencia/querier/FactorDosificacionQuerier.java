package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorDosificacionRegistroMensualQuerier.java
 * Modificado: Jan 11, 2010 10:42:50 AM 
 * Autor: hector.longarte
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
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class FactorDosificacionQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String CODIGO_COMPONENTE = "componente.pkCodigoComponente";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Factordosificacion
	 * 
	 * @return
	 */
	public static List<Factordosificacion> getAll() throws AplicacionException {

		return Querier.getAll(Factordosificacion.class);
	}

	/**
	 * Método para obtener un Factordosificacion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Factordosificacion getById(Long codigo) throws ElementoNoEncontradoException {

		return (Factordosificacion) Querier.getById(Factordosificacion.class, codigo);
	}

	/**
	 * Método para obtener una Factordosificacion de la BD.
	 * 
	 * @param codigoHojaRuta
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Factordosificacion> findByHojaRuta(Long codigoHojaRuta) throws AplicacionException {

		try {

			Query query = query(" from Factordosificacion fd where fd.hojaruta.pkCodigoHojaruta = ? and fd.factordosificacionregistromensus.size > 0 ");
			query.setLong(0, codigoHojaRuta);

			return (List<Factordosificacion>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una Factordosificacion de la BD.
	 * 
	 * @param codigoComponente
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Factordosificacion> findByComponente(Long codigoComponente) throws AplicacionException {

		try {
			return Querier.findByProperty(Factordosificacion.class, CODIGO_COMPONENTE, codigoComponente);
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Factordosificacion> findByHojaRutaAndPlanAnual(Long codigoHojaRuta, Integer anio)
			throws AplicacionException {
		try {

			Query query = query("select distinct(fd) from Factordosificacion fd where fd.hojaruta.pkCodigoHojaruta = ? and fd.pkCodigoFactordosificacion in (select fdrm.factordosificacion.pkCodigoFactordosificacion from Factordosificacionregistromensu fdrm where fdrm.annoFactordosificacionregistro = ?) ");

			query.setLong(0, codigoHojaRuta);
			query.setInteger(1, anio);

			return (List<Factordosificacion>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de Factordosificacion por medio del año del
	 * registro mensual
	 * 
	 * @param anio
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Factordosificacion> findByAnnio(Integer anio) throws AplicacionException {
		try {
			Query query = query(" select distinct(f) from Factordosificacion  as f, Factordosificacionregistromensu as frm "
					+ "where  f.pkCodigoFactordosificacion = frm.factordosificacion.pkCodigoFactordosificacion "
					+ "and frm.annoFactordosificacionregistro = ?");
			query.setInteger(0, anio);

			return (List<Factordosificacion>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	/**
	 * Método para obtener una lista de Factordosificacion por medio del nombre
	 * de la hoja de ruta
	 * 
	 * @param anio
	 * @return
	 * @throws AplicacionException
	 */
	public static List<Factordosificacion> findByNombreHojaRuta(String nombreHojaRuta) throws AplicacionException {
		try {
			Query query = query(" from Factordosificacion fd where fd.hojaruta.nombreHojaruta = ? and fd.factordosificacionregistromensus.size > 0");
			query.setString(0, nombreHojaRuta);

			return (List<Factordosificacion>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Factordosificacion> findWithRegistroMensual() throws AplicacionException {

		try {
			Query query = query("from Factordosificacion fd where fd.factordosificacionregistromensus.size > 0");

			return (List<Factordosificacion>) query.list();
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
	 * Método para insertar un Factordosificacion en la BD.
	 * 
	 * @param factorDosificacion
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Factordosificacion factorDosificacion) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(factorDosificacion);
	}

	/**
	 * Método para modificar una Factordosificacion de la BD.
	 * 
	 * @param factorDosificacionImplBean
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Factordosificacion factorDosificacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(factorDosificacion);
	}

	/**
	 * Método para eliminar una Factordosificacion de la BD.
	 * 
	 * @param factorDosificacion
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Factordosificacion factorDosificacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(factorDosificacion);
	}

	/**
	 * Método para eliminar una lista de Factor de Dosificación de la BD, por
	 * medio del código de la hoja de ruta.
	 * 
	 * @param hojaRutaComponente
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void deleteByCodigoHojaRuta(Long codigoHojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete Factordosificacion fd where fd.hojaruta.pkCodigoHojaruta = :codigo";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoHojaRuta).executeUpdate();

	}

	/**
	 * Método para eliminar una lista de Factor de Dosificación de la BD, por
	 * medio del código de la hoja de ruta y el año del registro mensual.
	 * 
	 * @param hojaRutaComponente
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void deleteByCodigoHojaRutaYAnnio(Long codigoHojaRuta, Integer annio) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete Factordosificacion fd where fd.hojaruta.pkCodigoHojaruta = ? and fd.pkCodigoFactordosificacion in (select fdrm.factordosificacion.pkCodigoFactordosificacion from Factordosificacionregistromensu fdrm where fdrm.annoFactordosificacionregistro = ?  )");
		query.setLong(0, codigoHojaRuta);
		query.setInteger(1, annio);

		query.executeUpdate();

	}
}