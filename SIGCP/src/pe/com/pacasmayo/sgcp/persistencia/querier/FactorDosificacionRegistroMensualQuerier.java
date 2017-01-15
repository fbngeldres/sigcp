package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: FactorDosificacionRegistroMensualQuerier.java
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
import org.hibernate.QueryException;
import org.hibernate.SessionException;
import org.hibernate.exception.JDBCConnectionException;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.EntornoEjecucionException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.excepciones.SesionVencidaException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Factordosificacionregistromensu;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;
import pe.com.pacasmayo.sgcp.util.ManejadorPropiedades;

public class FactorDosificacionRegistroMensualQuerier extends Querier implements ConstantesMensajeAplicacion {

	/***************************************************************************
	 * DECLARACIONES Y CONSTANTES
	 **************************************************************************/

	private static String CODIGO_FD = "factordosificacion.pkCodigoFactordosificacion";

	/***************************************************************************
	 * METODOS GETS Y FINDS
	 **************************************************************************/

	/**
	 * Método para obtener la lista de objectos Factordosificacionregistromensu
	 * 
	 * @return
	 */
	public static List<Factordosificacionregistromensu> getAll() throws AplicacionException {

		return Querier.getAll(Factordosificacionregistromensu.class);
	}

	/**
	 * Método para modificar una Factordosificacionregistromensu de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static Factordosificacionregistromensu getById(Long codigo) throws ElementoNoEncontradoException {

		return Querier.getById(Factordosificacionregistromensu.class, codigo);
	}

	/**
	 * Método para obtener la lista de registros mensuales dado un
	 * factordosificacion de la BD.
	 * 
	 * @param codigo
	 * @throws ElementoNoEncontradoException
	 */
	public static List<Factordosificacionregistromensu> getByFactorDosificacion(Long codigoFactordosificacion)
			throws AplicacionException {

		try {
			return Querier.findByProperty(Factordosificacionregistromensu.class, CODIGO_FD, codigoFactordosificacion);
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
	 * Metodo para insertar un Factordosificacionregistromensu en la BD.
	 * 
	 * @param factorDosificacionRegistroMensu
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ConstrainViolationException
	 * @throws ElementoNoEncontradoExceptions
	 */
	public static void save(Factordosificacionregistromensu factorDosificacionRegistroMensu) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(factorDosificacionRegistroMensu);
	}

	/**
	 * Metodo para modificar una Factordosificacionregistromensu de la BD.
	 * 
	 * @param factorDosificacionRegistroMensualImplBean
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void update(Factordosificacionregistromensu factorDosificacionRegistroMensu) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.update(factorDosificacionRegistroMensu);
	}

	/**
	 * Metodo para eliminar una Factordosificacionregistromensu de la BD.
	 * 
	 * @param factorDosificacionRegistroMensu
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 * @throws ConstrainViolationException
	 */
	public static void delete(Factordosificacionregistromensu factorDosificacionRegistroMensu) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.delete(factorDosificacionRegistroMensu);
	}

	/**
	 * Método para eliminar una lista de Factor de Dosificación registro mensual
	 * de la BD, por medio del código de la hoja de ruta.
	 * 
	 * @param codigoHojaRuta
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoEliminadoException
	 * @throws ElementoExistenteException
	 * @throws ParametroInvalidoException
	 */
	public static void deleteByCodigoHojaRuta(Long codigoHojaRuta) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete Factordosificacionregistromensu fdrm where fdrm.factordosificacion.pkCodigoFactordosificacion IN (from Factordosificacion fd where fd.hojaruta.pkCodigoHojaruta = :codigo)";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoHojaRuta).executeUpdate();
	}

	/**
	 * Método para eliminar todos los factores de dosificación registro mensual,
	 * por hoja de ruta y año
	 * 
	 * @param codigoHojaRuta
	 * @param annio
	 * @throws ParametroInvalidoException
	 * @throws ElementoExistenteException
	 * @throws ElementoEliminadoException
	 * @throws ElementoNoEncontradoException
	 */
	public static void deleteByCodigoHojaRutaYAnnio(Long codigoHojaRuta, Integer annio) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		Query query = query("delete Factordosificacionregistromensu fdrm where fdrm.annoFactordosificacionregistro = ? and fdrm.factordosificacion.pkCodigoFactordosificacion in (select fd.pkCodigoFactordosificacion from Factordosificacion fd where fd.hojaruta.pkCodigoHojaruta = ?)");
		query.setInteger(0, annio);
		query.setLong(1, codigoHojaRuta);

		query.executeUpdate();

	}

	public static void deleteByCodigoFactorDosificacion(Long codigoFactorDosificacion) throws ParametroInvalidoException,
			ElementoExistenteException, ElementoEliminadoException, ElementoNoEncontradoException {

		String hqlDelete = "delete Factordosificacionregistromensu fdrm where fdrm.factordosificacion.pkCodigoFactordosificacion = :codigo";
		getSession().createQuery(hqlDelete).setLong("codigo", codigoFactorDosificacion).executeUpdate();
	}

	public static Factordosificacionregistromensu obtenerFactorSegunProductoCompoeneteYMes(Long codigoProducto,
			Long codigoComponente, Short mes, Integer anio) throws ElementoNoEncontradoException, EntornoEjecucionException,
			SesionVencidaException {

		String mensajeError = "";
		Factordosificacionregistromensu object = null;
		try {
			StringBuilder queryString = new StringBuilder("FROM Factordosificacionregistromensu AS f WHERE");
			queryString.append(" f.factordosificacion.hojaruta.produccion.producto.pkCodigoProducto = :codProducto");
			queryString.append(" AND f.factordosificacion.componente.pkCodigoComponente = :componente");
			queryString.append(" AND f.factordosificacion.unidadmedida.pkCodigoUnidadMedida = :unidad");
			queryString.append(" AND f.mesFactordosificacionregistrom = :mes");
			queryString.append(" AND f.annoFactordosificacionregistro = :anio");

			Query queryObject = getSession().createQuery(queryString.toString());
			queryObject.setLong("codProducto", codigoProducto);
			queryObject.setLong("componente", codigoComponente);
			queryObject.setLong("unidad", new Long(2));
			queryObject.setInteger("anio", anio);
			queryObject.setShort("mes", mes);

			object = (Factordosificacionregistromensu) queryObject.uniqueResult();
			return object;
		} catch (ObjectNotFoundException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_OBJETO_NO_ENCONTRADO);
			logger.error(e);
			throw new ElementoNoEncontradoException(mensajeError, e);
		} catch (QueryException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_QUERY_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (SessionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_USO_SESION_INAPROPIADA);
			logger.error(e);
			throw new SesionVencidaException(mensajeError, e);
		} catch (JDBCConnectionException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_COMUNICACION_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		} catch (RuntimeException e) {
			mensajeError = ManejadorPropiedades.obtenerPropiedadPorClave(ERROR_FATAL_FALLO);
			logger.error(e);
			throw new EntornoEjecucionException(mensajeError, e);
		}

	}
}
