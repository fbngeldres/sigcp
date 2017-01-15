package pe.com.pacasmayo.sgcp.persistencia.querier;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: PlanAnualQuerier.java
 * Modificado: Jan 4, 2010 10:30:31 AM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoEliminadoException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoExistenteException;
import pe.com.pacasmayo.sgcp.excepciones.ElementoNoEncontradoException;
import pe.com.pacasmayo.sgcp.excepciones.ParametroInvalidoException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estadoplananual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Estimacionregistromensual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plananual;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Plancomercializacion;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.ConstrainViolationException;
import pe.com.pacasmayo.sgcp.persistencia.excepciones.UnresolvableObjectException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class PlanAnualQuerier extends Querier implements ConstantesMensajeAplicacion {

	private static String PLAN_COMERCIALIZACION = "plancomercializacion";
	public static String CODIGO_DIVISION = "lineanegocio.unidad.sociedad.division.pkCodigoDivision";
	public static String CODIGO_SOCIEDAD = "lineanegocio.unidad.sociedad.pkCodigoSociedad";
	public static String CODIGO_UNIDAD = "lineanegocio.unidad.pkCodigoUnidad";
	public static String CODIGO_LINEANEGOCIO = "lineanegocio.pkCodigoLineanegocio";
	public static String CODIGO_ANNIO = "annoPlananual";
	public static String CODIGO_ESTADO = "estadoplananual.pkCodigoEstadoplananual";

	/**
	 * Metodo para insertar una Division en la BD.
	 * 
	 * @param divisionImplBean
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void save(Plananual plananual) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Querier.save(plananual);
	}

	/**
	 * Metodo para modificar una Division de la BD.
	 * 
	 * @param divisionImplBean
	 * @throws Exception
	 */
	public static Plananual getById(Long codigo) throws ElementoNoEncontradoException {

		Plananual planAnual = (Plananual) Querier.getById(Plananual.class, codigo);

		return planAnual;
	}

	/**
	 * Metodo para modificar una Division de la BD.
	 * 
	 * @param divisionImplBean
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void update(Plananual plananual) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {
		Querier.update(plananual);
	}

	/**
	 * Metodo para eliminar una Division de la BD.
	 * 
	 * @param divisionImplBean
	 * @throws ConstrainViolationException
	 * @throws Exception
	 */
	public static void delete(Long codigoPlanAnual) throws ParametroInvalidoException, ElementoExistenteException,
			ElementoEliminadoException, ElementoNoEncontradoException {

		Plananual planAnual = new Plananual();
		planAnual.setPkCodigoPlananual(codigoPlanAnual);
		Querier.delete(planAnual);
	}

	/**
	 * Metodo para obtener la lista de objectos DivisionImplBean
	 * 
	 * @return
	 */
	public static List<Plananual> getAll() throws AplicacionException {

		List<Plananual> planAnualList = Querier.getAll(Plananual.class);

		return planAnualList;
	}

	/**
	 * @param order
	 * @return
	 */
	public static List<Plananual> getAllOrderBy(String order) {
		return Querier.getAll(Plananual.class, order);
	}

	public static List<Estadoplananual> getAllEstadosPlan() {
		return Querier.getAll(Estadoplananual.class);
	}

	public static List<Estimacionregistromensual> getListaEstimacionRegistroMensual(Plancomercializacion plancomercializacion) {
		return Querier.findByProperty(Estimacionregistromensual.class, PLAN_COMERCIALIZACION, plancomercializacion);
	}

	public static List<Plananual> getByLineaNegocioAnnoAndEstatus(Long codigoLineaNegocio, int anno, Long codigoEstado) {
		String consulta = "from Plananual plan " + "where plan.lineanegocio.pkCodigoLineanegocio = ? "
				+ "and plan.annoPlananual = ? " + "and plan.estadoplananual.pkCodigoEstadoplananual = ?";

		Query query = Querier.query(consulta);
		query.setLong(0, codigoLineaNegocio);
		query.setInteger(1, anno);
		query.setLong(2, codigoEstado);

		return (List<Plananual>) query.list();
	}

	public static List<Plananual> findByLineaNegocioAnioYEstado(Long codigoLineaNegocio, Integer anio, Long codigoEstado)
			throws AplicacionException {
		try {

			Query query = query("from Plananual pa where pa.lineanegocio.pkCodigoLineanegocio = ? and pa.annoPlananual = ? and pa.estadoplananual.pkCodigoEstadoplananual = ? ");

			query.setLong(0, codigoLineaNegocio);
			query.setInteger(1, anio);
			query.setLong(2, codigoEstado);

			return (List<Plananual>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static Plananual findByLineaNegocioAnioYEstadoYVersion(Long codigoLineaNegocio, Integer anio, Long codigoEstado,
			String version) throws AplicacionException {
		try {

			Query query = query("from Plananual pa where pa.lineanegocio.pkCodigoLineanegocio = ? and pa.annoPlananual = ? and pa.estadoplananual.pkCodigoEstadoplananual = ? and pa.versionPlananual =  ?");

			query.setLong(0, codigoLineaNegocio);
			query.setInteger(1, anio);
			query.setLong(2, codigoEstado);
			query.setString(3, version);

			return (Plananual) query.uniqueResult();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Plananual> findByLineaNegocioYAnio(Long codigoLineaNegocio, Integer anio) throws AplicacionException {
		try {

			Query query = query("from Plananual pa where pa.lineanegocio.pkCodigoLineanegocio = ? and pa.annoPlananual = ? ");

			query.setLong(0, codigoLineaNegocio);
			query.setInteger(1, anio);

			return (List<Plananual>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Plananual> findByLineaNegocio(Long codigoLineaNegocio) throws AplicacionException {
		try {

			Query query = query("from Plananual pa where pa.lineanegocio.pkCodigoLineanegocio = ? ");

			query.setLong(0, codigoLineaNegocio);

			return (List<Plananual>) query.list();
		} catch (UnresolvableObjectException uOException) {
			throw new AplicacionException(ERROR_OBJETO_NO_VALIDO, uOException.getCause());
		} catch (ObjectNotFoundException oNFException) {
			throw new AplicacionException(ERROR_OBJETO_NO_ENCONTRADO, oNFException.getCause());
		} catch (HibernateException hException) {
			throw new AplicacionException(ERROR_HIBERNATE, hException.getCause());
		}
	}

	public static List<Integer> obtenerAnniosPlanAnual() throws AplicacionException {

		Query query = query("select distinct pa.annoPlananual from Plananual pa order by pa.annoPlananual ");

		return (List<Integer>) query.list();
	}

	public static List<Plananual> findByPropiedades(Map propiedades) {

		return Querier.findByProperties(Plananual.class, propiedades);

	}
}
